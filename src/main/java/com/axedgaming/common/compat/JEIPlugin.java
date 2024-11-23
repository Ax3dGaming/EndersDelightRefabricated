package com.axedgaming.common.compat;

import com.axedgaming.common.registry.EDItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static final ResourceLocation ID = new ResourceLocation("endersdelight","jei_plugin");

    public JEIPlugin() {
    }

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(EDItems.ENDSTONE_STOVE.get()), RecipeTypes.CAMPFIRE_COOKING);
    }

    public @NotNull ResourceLocation getPluginUid() {
        return ID;
    }
}
