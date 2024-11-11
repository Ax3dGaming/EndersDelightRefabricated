package com.axedgaming.common.effects;

import com.axedgaming.common.registry.EDDamageSource;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class PhasingEffect extends MobEffect {
    public PhasingEffect() {
        super(MobEffectCategory.BENEFICIAL, 10816424);
    }

    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level world = pLivingEntity.level();
        if (pLivingEntity.isInWaterRainOrBubble()) {
            pLivingEntity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(EDDamageSource.WATERED)), 1);
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
