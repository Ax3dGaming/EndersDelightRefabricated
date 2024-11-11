package com.axedgaming.common.registry;

import com.axedgaming.EndersDelight;
import com.axedgaming.common.effects.PhasingEffect;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EDEffects {
    static final List<Supplier<?>> EFFECTS = new ArrayList<>();

    @NotNull
    private static <T extends MobEffect> Supplier<T> register(String id, Supplier<T> supplier) {
        var v = Suppliers.memoize(() ->
                Registry.register(BuiltInRegistries.MOB_EFFECT, EndersDelight.res(id), supplier.get()));
        EFFECTS.add(v);
        return v;
    }

    // hacky but again needed for addons
    public static void init() {
        EFFECTS.forEach(Supplier::get);
        EFFECTS.clear();
    }

    // we need to register immediately so addons can use them if they happen to load before FD. We need suppliers so its same class as forge version
    public static final Supplier<MobEffect> PHASING = register("phasing", PhasingEffect::new);


}
