package me.hijinks001.stormtotemmod.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class StormWandItem extends Item {
    public StormWandItem() {
        super(new Item.Properties().durability(5));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            ServerLevel serverLevel = (ServerLevel) level;

            // Направление взгляда игрока
            Vec3 look = player.getLookAngle();
            Vec3 start = player.getEyePosition();

            // Ищем цель в радиусе 15 блоков по линии взгляда
            for (double d = 0; d < 15; d += 0.5) {
                Vec3 point = start.add(look.scale(d));
                AABB box = new AABB(point.x - 1, point.y - 1, point.z - 1,
                                     point.x + 1, point.y + 1, point.z + 1);

                for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, box)) {
                    if (target == player) continue;

                    // Урон 3 сердечка (6 единиц)
                    target.hurt(level.damageSources().lightningBolt(), 6);

                    // Частицы молнии в цели
                    serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                            target.getX(), target.getY() + 1, target.getZ(),
                            20, 0.3, 0.5, 0.3, 0.1);

                    // Звук
                    level.playSound(null, target.blockPosition(),
                            SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.PLAYERS, 0.5f, 1.2f);

                    // Урон прочности жезла
                    stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));

                    return InteractionResultHolder.success(stack);
                }
            }

            // Если цель не найдена — просто искры в воздухе
            Vec3 end = start.add(look.scale(15));
            serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                    end.x, end.y, end.z, 10, 0.1, 0.1, 0.1, 0.05);
            level.playSound(null, player.blockPosition(),
                    SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.3f, 1.5f);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }
}