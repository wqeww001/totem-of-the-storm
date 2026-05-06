package me.hijinks001.stormtotemmod.item;

import me.hijinks001.stormtotemmod.StormTotemMod;
import me.hijinks001.stormtotemmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StormTotemMod.MODID);

    public static final RegistryObject<CreativeModeTab> STORM_TOTEM_TAB = CREATIVE_TABS.register(
            "storm_totem_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.stormtotemmod"))
                    .icon(() -> new ItemStack(ModItems.CHARGED_COPPER_INGOT.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.CHARGED_COPPER_INGOT.get());
                        output.accept(ModItems.CHARGED_COPPER_NUGGET.get());
                        output.accept(ModItems.STORM_CRYSTAL.get());
                        output.accept(ModItems.STORM_DUST.get());
                        output.accept(ModItems.STORM_FLINT.get());
                        output.accept(ModItems.STORM_WAND.get());
                        output.accept(ModBlocks.STORM_BLOCK.get());
                        output.accept(ModBlocks.STORM_ROD.get());
                        output.accept(ModBlocks.STORM_ORE.get());
                        output.accept(ModBlocks.STORM_CRYSTAL_ORE.get());
                        output.accept(ModItems.STORM_COMPASS.get());
                        output.accept(ModItems.STORM_GUIDE_BOOK.get());
                    })
                    .build()
    );
}