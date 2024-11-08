package com.axedgaming.client.event;

import com.axedgaming.client.renderer.EndstoneStoveRenderer;
import com.axedgaming.common.registry.EDBlockEntityTypes;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class ClientSetupEvent {
    public ClientSetupEvent() {
    }

    public static void onRegisterRenderers() {
        BlockEntityRenderers.register(EDBlockEntityTypes.ENDSTONE_STOVE_BE.get(), EndstoneStoveRenderer::new);
    }
}
