package com.axedgaming.client;

import com.axedgaming.client.event.ClientSetupEvent;
import net.fabricmc.api.ClientModInitializer;

public class EndersDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientSetupEvent.onRegisterRenderers();
    }
}
