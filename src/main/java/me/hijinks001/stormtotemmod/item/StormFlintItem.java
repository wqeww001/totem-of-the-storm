package me.hijinks001.stormtotemmod.item;

import me.hijinks001.stormtotemmod.block.ModBlocks;
import me.hijinks001.stormtotemmod.block.StormPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class StormFlintItem extends Item {
    public StormFlintItem() {
        super(new Item.Properties().durability(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction facing = context.getHorizontalDirection(); // Направление, куда смотрит игрок
        
        if (!level.isClientSide) {
            // Ищем рамку портала относительно направления игрока
            BlockPos frameStart = findPortalFrame(level, pos, facing);
            if (frameStart != null) {
                lightPortal(level, frameStart, facing);
                level.playSound(null, pos, SoundEvents.LIGHTNING_BOLT_THUNDER, 
                        SoundSource.BLOCKS, 1.0F, 1.0F);
                context.getItemInHand().shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        
        return InteractionResult.PASS;
    }
    
    private BlockPos findPortalFrame(Level level, BlockPos pos, Direction facing) {
        // Ищем рамку в радиусе 3 блоков
        for (int dx = -3; dx <= 3; dx++) {
            for (int dy = -3; dy <= 3; dy++) {
                for (int dz = -3; dz <= 3; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    if (isValidFrame(level, checkPos, facing)) {
                        return checkPos;
                    }
                }
            }
        }
        return null;
    }
    
    private boolean isValidFrame(Level level, BlockPos start, Direction facing) {
        Direction left = facing.getClockWise(); // Левая сторона относительно направления игрока
        
        // Проверяем рамку 4x5
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 4; x++) {
                BlockPos checkPos = start.relative(left, x).above(y);
                BlockState state = level.getBlockState(checkPos);
                
                // Края рамки
                if (y == 0 || y == 4 || x == 0 || x == 3) {
                    if (!state.is(ModBlocks.STORM_BLOCK.get())) {
                        return false;
                    }
                } else {
                    // Внутри - воздух
                    if (!state.is(Blocks.AIR)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private void lightPortal(Level level, BlockPos start, Direction facing) {
        Direction left = facing.getClockWise();
        // Ось портала = направление лево-право (вдоль рамки)
        Direction.Axis axis = left.getAxis();
        
        // Зажигаем внутренность портала (2x3)
        for (int y = 1; y <= 3; y++) {
            for (int x = 1; x <= 2; x++) {
                BlockPos portalPos = start.relative(left, x).above(y);
                level.setBlock(portalPos, ModBlocks.STORM_PORTAL.get().defaultBlockState()
                        .setValue(StormPortalBlock.AXIS, axis), 3);
            }
        }
    }

    @Override
    public boolean isEnchantable(ItemStack stack) { return false; }
    @Override
    public boolean isRepairable(ItemStack stack) { return false; }
}