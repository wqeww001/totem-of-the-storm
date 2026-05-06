package me.hijinks001.stormtotemmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

public class StormRushEffect extends MobEffect {
    public StormRushEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00CCFF);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level() instanceof ServerLevel level) {
            // Направление взгляда игрока
            double lookX = entity.getLookAngle().x;
            double lookZ = entity.getLookAngle().z;
            
            // Позиция ЗА спиной (противоположно направлению взгляда)
            double behindX = entity.getX() - lookX * 0.8;
            double behindY = entity.getY() + 1.0 + level.random.nextDouble() * 0.5;
            double behindZ = entity.getZ() - lookZ * 0.8;

            // Синие частицы (ELECTRIC_SPARK)
            for (int i = 0; i < 3; i++) {
                level.sendParticles(
                    ParticleTypes.SOUL_FIRE_FLAME,
                    behindX + (level.random.nextDouble() - 0.5) * 0.4,
                    behindY + level.random.nextDouble() * 0.6,
                    behindZ + (level.random.nextDouble() - 0.5) * 0.4,
                    1, 0, 0, 0, 0.02
                );
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 3 == 0; // Частицы каждые 3 тика (чаще)
    }
}