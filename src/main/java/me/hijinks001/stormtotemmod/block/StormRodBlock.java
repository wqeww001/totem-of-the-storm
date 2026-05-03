package me.hijinks001.stormtotemmod.block;

import me.hijinks001.stormtotemmod.Config;
import me.hijinks001.stormtotemmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class StormRodBlock extends Block {

    public static final BooleanProperty CHARGED = BooleanProperty.create("charged");

    public StormRodBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CHARGED, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                  Player player, InteractionHand hand, BlockHitResult hit) {

        ItemStack heldItem = player.getItemInHand(hand);

        if (heldItem.is(ModItems.STORM_FLINT.get()) && state.getValue(CHARGED)) {

            if (!isValidStructure(level, pos)) {
                if (!level.isClientSide) {
                    player.displayClientMessage(
                            net.minecraft.network.chat.Component.literal("§cНеверная структура! Нужно 2 Грозовых блока под стержнем."),
                            true
                    );
                }
                return InteractionResult.FAIL;
            }

            if (!level.isClientSide) {
                ServerLevel serverLevel = (ServerLevel) level;

                heldItem.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));

                level.playSound(null, pos, SoundEvents.LIGHTNING_BOLT_THUNDER,
                        SoundSource.BLOCKS, 1.5f, 1.0f);

                spawnFakeLightning(serverLevel, pos);
                applyPlayerEffects(player);
                chargeAnimals(serverLevel, pos);
                boostCrops(serverLevel, pos);

                level.setBlock(pos, state.setValue(CHARGED, false), 3);
                level.scheduleTick(pos, this, Config.totemCooldownSeconds * 20);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (!state.getValue(CHARGED) && !level.isClientSide) {
            player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal("§7Тотем истощён. Ждите восстановления..."),
                    true
            );
        }

        return InteractionResult.PASS;
    }

    private boolean isValidStructure(Level level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModBlocks.STORM_BLOCK.get()) &&
               level.getBlockState(pos.below(2)).is(ModBlocks.STORM_BLOCK.get());
    }

    private void spawnFakeLightning(ServerLevel level, BlockPos pos) {
        level.sendParticles(ParticleTypes.FLASH,
                pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5,
                1, 0, 0, 0, 0);

        for (int i = 0; i < 80; i++) {
            double x = pos.getX() + 0.5 + (level.random.nextDouble() - 0.5) * 3;
            double y = pos.getY() + 1.0 + level.random.nextDouble() * 2.5;
            double z = pos.getZ() + 0.5 + (level.random.nextDouble() - 0.5) * 3;
            level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z,
                    1, 0, 0, 0, 0.05);
        }

        level.sendParticles(ParticleTypes.LARGE_SMOKE,
                pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                10, 0.3, 0.1, 0.3, 0.02);
    }

    private void applyPlayerEffects(Player player) {
        int duration = Config.stormRushDuration * 20;
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 2, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, 1, false, true));
    }

    private void chargeAnimals(ServerLevel level, BlockPos pos) {
        int radius = Config.totemEffectRadius;
        AABB area = new AABB(pos).inflate(radius);

        List<Animal> animals = level.getEntitiesOfClass(Animal.class, area,
                a -> a instanceof Cow || a instanceof Sheep || a instanceof Pig ||
                     a instanceof Chicken || a instanceof Rabbit);

        for (Animal animal : animals) {
            for (int i = 0; i < 15; i++) {
                level.sendParticles(ParticleTypes.GLOW,
                        animal.getX() + (level.random.nextDouble() - 0.5) * 0.8,
                        animal.getY() + level.random.nextDouble() * 1.2,
                        animal.getZ() + (level.random.nextDouble() - 0.5) * 0.8,
                        1, 0, 0, 0, 0);
            }

            level.addFreshEntity(new net.minecraft.world.entity.ExperienceOrb(
                    level, animal.getX(), animal.getY(), animal.getZ(), 5));
        }
    }

    private void boostCrops(ServerLevel level, BlockPos pos) {
        int radius = Config.totemEffectRadius;

        BlockPos.betweenClosedStream(
                pos.offset(-radius, -3, -radius),
                pos.offset(radius, 3, radius)
        ).forEach(cropPos -> {
            BlockState cropState = level.getBlockState(cropPos);
            if (cropState.getBlock() instanceof net.minecraft.world.level.block.CropBlock crop) {
                if (!crop.isMaxAge(cropState)) {
                    level.setBlock(cropPos, crop.getStateForAge(crop.getMaxAge()), 3);
                    level.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                            cropPos.getX() + 0.5, cropPos.getY() + 0.5, cropPos.getZ() + 0.5,
                            3, 0.2, 0.2, 0.2, 0);
                }
            }
        });
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, net.minecraft.util.RandomSource random) {
        if (!state.getValue(CHARGED)) {
            level.setBlock(pos, state.setValue(CHARGED, true), 3);
            level.playSound(null, pos, SoundEvents.BEACON_POWER_SELECT,
                    SoundSource.BLOCKS, 0.5f, 1.5f);

            level.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                    pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                    20, 0.3, 0.3, 0.3, 0.05);
        }
    }
}