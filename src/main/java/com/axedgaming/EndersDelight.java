package com.axedgaming;

import com.axedgaming.common.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndersDelight implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("endersdelight");
    public static final String MODID = "endersdelight";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MODID, name);
    }

    @Override
    public void onInitialize() {

        EDBlocks.BLOCKS.register();
        EDBlockEntityTypes.TILES.register();
        EDItems.ITEMS.register();
        EDCreativeTab.TABS.register();
        EDEffects.init();
        EDPlayer.init();
        EDDamageSource.WATERED.registry();
    }
}
