package com.axedgaming.common.items;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class EDFoodValues {
    public static final FoodProperties FINGER = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).build();
    public static final FoodProperties MEAL = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4F).build();
    public static final FoodProperties DIET = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4F).build();
    public static final FoodProperties FEAST = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.8F).build();
    public static final FoodProperties PEARL_PASTA = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();
    public static final FoodProperties ENDER_PAELLA = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4F).build();
    public static final FoodProperties ENDERMITE_STEW = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4F).build();
    public static final FoodProperties CRAWLING_SANDWICH = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.2F).build();
    public static final FoodProperties TWISTED_CEREAL = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties CHORUS_STEW = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties STRANGE_ECLAIR = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();
    public static final FoodProperties UNCANNY_COOKIES = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();
    public static final FoodProperties STUFFED_SHULKER = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.9F).effect(new MobEffectInstance(MobEffects.LEVITATION, 400, 2), 1F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800, 0), 1F).effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1F).build();
}
