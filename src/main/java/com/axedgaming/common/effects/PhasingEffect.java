package com.axedgaming.common.effects;

import com.axedgaming.common.registry.EDDamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class PhasingEffect extends MobEffect {
    public PhasingEffect() {
        super(MobEffectCategory.BENEFICIAL, 10816424);
    }

    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.level().isRaining() || (pLivingEntity.isInWaterOrBubble())){
            pLivingEntity.hurt(EDDamageSource.getSimpleDamageSource(pLivingEntity.level(), EDDamageSource.WATERED), 1.0F);
        }
    }

    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
