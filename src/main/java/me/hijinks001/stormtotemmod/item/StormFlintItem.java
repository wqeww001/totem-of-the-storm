package me.hijinks001.stormtotemmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class StormFlintItem extends Item {
    public StormFlintItem() {
        super(new Item.Properties().durability(1));
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