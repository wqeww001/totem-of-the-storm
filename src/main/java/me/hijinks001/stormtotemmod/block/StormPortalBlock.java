package me.hijinks001.stormtotemmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class StormPortalBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = EnumProperty.create("axis", Direction.Axis.class);
    protected static final VoxelShape X_AABB = Block.box(0.0, 0.0, 6.4, 16.0, 16.0, 9.6);
    protected static final VoxelShape Z_AABB = Block.box(6.4, 0.0, 0.0, 9.6, 16.0, 16.0);

    public static final ResourceKey<Level> STORM_DIM = ResourceKey.create(
            net.minecraft.core.registries.Registries.DIMENSION,
            new ResourceLocation("stormtotemmod", "storm_dimension")
    );

    public StormPortalBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_CYAN)
                .noCollission()
                .strength(-1.0F, 3600000.0F)
                .noLootTable()
                .lightLevel(state -> 11)
                .pushReaction(PushReaction.BLOCK));
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        return state.getValue(AXIS) == Direction.Axis.Z ? Z_AABB : X_AABB;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(100) == 0) {
            level.playLocalSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }
        for (int i = 0; i < 3; ++i) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + random.nextDouble();
            double z = pos.getZ() + random.nextDouble();
            level.addParticle(ParticleTypes.ELECTRIC_SPARK, x, y, z,
                    (random.nextDouble() - 0.5) * 0.5, (random.nextDouble() - 0.5) * 0.5, (random.nextDouble() - 0.5) * 0.5);
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity.canChangeDimensions() && !entity.isOnPortalCooldown()) {
            entity.setPortalCooldown();

            ServerLevel serverLevel = (ServerLevel) level;
            ResourceKey<Level> targetDim = level.dimension() == STORM_DIM ? Level.OVERWORLD : STORM_DIM;
            ServerLevel targetLevel = serverLevel.getServer().getLevel(targetDim);

            if (targetLevel != null) {
                final BlockPos currentPos = pos;

                if (targetDim == STORM_DIM) {
                    BlockPos spawnPos = new BlockPos(currentPos.getX(), 58, currentPos.getZ());
                    createReturnPortal(targetLevel, spawnPos);
                }

                entity.changeDimension(targetLevel, new ITeleporter() {
                    @Override
                    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld,
                                               float yaw, Function<Boolean, Entity> repositionEntity) {
                        Entity moved = repositionEntity.apply(false);

                        if (destWorld.dimension() == STORM_DIM) {
                            // Вход в Storm Dimension — стоим на платформе перед порталом
                            moved.teleportTo(currentPos.getX() + 0.5, 59, currentPos.getZ() + 4.5);
                        } else {
                            // Возврат в Overworld — ищем портал рядом или ставим на землю
                            BlockPos returnPos = findSafeSpawn(destWorld, currentPos);
                            moved.teleportTo(returnPos.getX() + 0.5, returnPos.getY(), returnPos.getZ() + 0.5);
                        }
                        return moved;
                    }
                });
            }
        }
    }

    private BlockPos findSafeSpawn(ServerLevel level, BlockPos pos) {
        for (int y = pos.getY(); y > level.getMinBuildHeight() + 1; y--) {
            BlockPos below = new BlockPos(pos.getX(), y, pos.getZ());
            if (!level.getBlockState(below).isAir()) {
                return below.above();
            }
        }
        return new BlockPos(pos.getX(), 64, pos.getZ());
    }

    private void createReturnPortal(ServerLevel level, BlockPos basePos) {
        BlockPos base;

        if (level.dimension() == STORM_DIM) {
            base = new BlockPos(basePos.getX(), 58, basePos.getZ());
        } else {
            base = findSafeGround(level, basePos);
        }

        if (isPortalExists(level, base)) {
            return;
        }

        clearPortalArea(level, base);

        // Нижняя перекладина
        level.setBlock(base, ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(2), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(3), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);

        // Платформа 2×1 перед порталом
        level.setBlock(base.south(), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east().south(), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(2).south(), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(3).south(), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);

        level.setBlock(base.south(2), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east().south(2), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(2).south(2), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(3).south(2), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);

        // Верхняя перекладина
        level.setBlock(base.above(4), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east().above(4), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(2).above(4), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(3).above(4), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);

        // Левая стойка
        level.setBlock(base.above(), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.above(2), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.above(3), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);

        // Правая стойка
        level.setBlock(base.east(3).above(), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(3).above(2), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);
        level.setBlock(base.east(3).above(3), ModBlocks.STORM_BLOCK.get().defaultBlockState(), 3);

        // Зажигаем портал внутри
        Direction.Axis axis = Direction.Axis.X;
        for (int y = 1; y < 4; y++) {
            for (int x = 1; x < 3; x++) {
                BlockPos portalPos = base.east(x).above(y);
                level.setBlock(portalPos, ModBlocks.STORM_PORTAL.get().defaultBlockState()
                        .setValue(StormPortalBlock.AXIS, axis), 3);
            }
        }
    }

    private BlockPos findSafeGround(ServerLevel level, BlockPos pos) {
        for (int y = pos.getY(); y > level.getMinBuildHeight() + 5; y--) {
            BlockPos check = new BlockPos(pos.getX(), y, pos.getZ());
            if (!level.getBlockState(check).isAir()) {
                return check.above();
            }
        }
        return new BlockPos(pos.getX(), 64, pos.getZ());
    }

    private boolean isPortalExists(ServerLevel level, BlockPos base) {
        for (int y = 1; y <= 3; y++) {
            for (int x = 1; x <= 2; x++) {
                BlockPos portalPos = base.east(x).above(y);
                if (level.getBlockState(portalPos).getBlock() instanceof StormPortalBlock) {
                    return true;
                }
            }
        }
        return false;
    }

    private void clearPortalArea(ServerLevel level, BlockPos base) {
        for (int y = 0; y <= 4; y++) {
            for (int x = 0; x <= 3; x++) {
                BlockPos pos = base.east(x).above(y);
                BlockState state = level.getBlockState(pos);
                if (!(state.getBlock() instanceof StormPortalBlock) &&
                    state.getBlock() != ModBlocks.STORM_BLOCK.get()) {
                    level.destroyBlock(pos, false);
                }
            }
        }
        for (int y = 1; y <= 3; y++) {
            for (int x = 1; x <= 2; x++) {
                BlockPos pos = base.east(x).above(y);
                if (level.getBlockState(pos).getBlock() != ModBlocks.STORM_PORTAL.get()) {
                    level.destroyBlock(pos, false);
                }
            }
        }
    }
}