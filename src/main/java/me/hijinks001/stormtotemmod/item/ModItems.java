package me.hijinks001.stormtotemmod.item;

import me.hijinks001.stormtotemmod.StormTotemMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, StormTotemMod.MODID);

    // Заряженный медный слиток
    public static final RegistryObject<Item> CHARGED_COPPER_INGOT = ITEMS.register(
            "charged_copper_ingot",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.stormtotemmod.charged_copper_ingot"));
                    if (flag.isAdvanced()) {
                        tooltip.add(Component.translatable("tooltip.stormtotemmod.charged_copper_ingot.shift"));
                    }
                }
            }
    );

    // Заряженный самородок
    public static final RegistryObject<Item> CHARGED_COPPER_NUGGET = ITEMS.register(
            "charged_copper_nugget",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.stormtotemmod.charged_copper_nugget"));
                }
            }
    );

    // Пыльца бури
    public static final RegistryObject<Item> STORM_DUST = ITEMS.register(
            "storm_dust",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.stormtotemmod.storm_dust"));
                }
            }
    );

    // Огниво бури
    public static final RegistryObject<Item> STORM_FLINT = ITEMS.register(
            "storm_flint",
            () -> new StormFlintItem() {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.stormtotemmod.storm_flint"));
                }
            }
    );

    // Грозовой жезл
    public static final RegistryObject<Item> STORM_WAND = ITEMS.register(
            "storm_wand",
            () -> new StormWandItem() {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.stormtotemmod.storm_wand"));
                    int charges = stack.getMaxDamage() - stack.getDamageValue();
                    tooltip.add(Component.literal("§b⚡ Charges: " + charges + "/5"));
                }
            }
    );

    // Грозовой компас
    public static final RegistryObject<Item> STORM_COMPASS = ITEMS.register(
            "storm_compass",
            () -> new StormCompassItem() {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.stormtotemmod.storm_compass"));
                }
            }
    );

    // Книга знаний
    public static final RegistryObject<Item> STORM_GUIDE_BOOK = ITEMS.register(
            "storm_guide_book",
            () -> new StormGuideBookItem() {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.stormtotemmod.storm_guide_book"));
                }
            }
    );

    public static final RegistryObject<Item> STORM_CRYSTAL = ITEMS.register(
            "storm_crystal",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.stormtotemmod.storm_crystal"));
                }
            }
    );
}