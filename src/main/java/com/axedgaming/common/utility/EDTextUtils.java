package com.axedgaming.common.utility;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class EDTextUtils {
    public EDTextUtils() {
    }
    public static MutableComponent getTranslation(String key, Object... args) {
        return Component.translatable("endersdelight." + key, args);
    }
}
