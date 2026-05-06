package me.hijinks001.stormtotemmod.effect;

import me.hijinks001.stormtotemmod.StormTotemMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, StormTotemMod.MODID);

    public static final RegistryObject<MobEffect> STORM_RUSH = EFFECTS.register(
            "storm_rush",
            StormRushEffect::new
    );
}