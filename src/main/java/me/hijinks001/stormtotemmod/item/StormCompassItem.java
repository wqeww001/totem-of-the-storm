package me.hijinks001.stormtotemmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StormCompassItem extends Item {
    public StormCompassItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            String key;
            if (serverLevel.isThundering()) {
                key = "message.stormtotemmod.compass.thunder";
            } else if (serverLevel.isRaining()) {
                key = "message.stormtotemmod.compass.rain";
            } else {
                key = "message.stormtotemmod.compass.clear";
            }
            player.displayClientMessage(Component.translatable(key), false);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }
}