package me.hijinks001.stormtotemmod;

import me.hijinks001.stormtotemmod.block.ModBlocks;
import me.hijinks001.stormtotemmod.item.ModCreativeTab;
import me.hijinks001.stormtotemmod.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(StormTotemMod.MODID)
public class StormTotemMod {
    public static final String MODID = "stormtotemmod";

    public StormTotemMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModCreativeTab.CREATIVE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}