package com.axedgaming;

import com.axedgaming.common.registry.EDBlockEntityTypes;
import com.axedgaming.common.registry.EDBlocks;
import com.axedgaming.common.registry.EDCreativeTab;
import com.axedgaming.common.registry.EDItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndersDelight implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("endersdelight");

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        EDBlocks.BLOCKS.register();
        EDBlockEntityTypes.TILES.register();
        EDItems.ITEMS.register();
        EDCreativeTab.TABS.register();

        LOGGER.info("Hello Fabric world!");
    }
}
