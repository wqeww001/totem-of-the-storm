package me.hijinks001.stormtotemmod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = StormTotemMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue TOTEM_COOLDOWN_SECONDS = BUILDER
            .comment("Cooldown in seconds after activating the Storm Totem")
            .defineInRange("totemCooldownSeconds", 300, 10, 3600);

    public static final ForgeConfigSpec.IntValue TOTEM_EFFECT_RADIUS = BUILDER
            .comment("Radius in blocks for the Storm Totem effects")
            .defineInRange("totemEffectRadius", 20, 5, 100);

    public static final ForgeConfigSpec.BooleanValue CHARGED_ANIMALS_DROP_DUST = BUILDER
            .comment("Whether charged animals drop Storm Dust on death")
            .define("chargedAnimalsDropDust", true);

    public static final ForgeConfigSpec.IntValue STORM_RUSH_DURATION = BUILDER
            .comment("Duration in seconds of the Storm Rush effect given to the player")
            .defineInRange("stormRushDuration", 12, 5, 60);

    public static final ForgeConfigSpec.BooleanValue GIVE_STARTER_KIT = BUILDER
            .comment("Give player Storm Guide Book and Storm Compass on first login")
            .define("giveStarterKit", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int totemCooldownSeconds;
    public static int totemEffectRadius;
    public static boolean chargedAnimalsDropDust;
    public static int stormRushDuration;
    public static boolean giveStarterKit;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        totemCooldownSeconds = TOTEM_COOLDOWN_SECONDS.get();
        totemEffectRadius = TOTEM_EFFECT_RADIUS.get();
        chargedAnimalsDropDust = CHARGED_ANIMALS_DROP_DUST.get();
        stormRushDuration = STORM_RUSH_DURATION.get();
        giveStarterKit = GIVE_STARTER_KIT.get();
    }
}