package com.axedgaming.client.event;

import com.axedgaming.client.renderer.EndstoneStoveRenderer;
import com.axedgaming.common.registry.EDBlockEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

@Environment(EnvType.CLIENT)
public class ClientSetupEvent {

    public static void onRegisterRenderers() {
        BlockEntityRenderers.register(EDBlockEntityTypes.ENDSTONE_STOVE_BE.get(), EndstoneStoveRenderer::new);
    }
}
