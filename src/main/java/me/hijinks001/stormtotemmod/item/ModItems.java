package me.hijinks001.stormtotemmod.item;

import me.hijinks001.stormtotemmod.StormTotemMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, StormTotemMod.MODID);

    public static final RegistryObject<Item> CHARGED_COPPER_INGOT = ITEMS.register(
            "charged_copper_ingot",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> CHARGED_COPPER_NUGGET = ITEMS.register(
            "charged_copper_nugget",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> STORM_DUST = ITEMS.register(
            "storm_dust",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> STORM_FLINT = ITEMS.register(
            "storm_flint",
            () -> new StormFlintItem()
    );

    public static final RegistryObject<Item> STORM_WAND = ITEMS.register(
            "storm_wand",
            () -> new StormWandItem()
    );
}