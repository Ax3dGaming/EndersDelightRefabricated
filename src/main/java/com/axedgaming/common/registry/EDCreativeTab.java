package com.axedgaming.common.registry;

import com.axedgaming.EndersDelight;
import com.axedgaming.common.utility.EDTextUtils;
import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class EDCreativeTab {
    public static final LazyRegistrar<CreativeModeTab> TABS = LazyRegistrar.create(Registries.CREATIVE_MODE_TAB, "endersdelight");

    public static final Supplier<CreativeModeTab> ENDERS_DELIGHT_TAB = TABS.register("endersdelight",
            () -> FabricItemGroup.builder()
                    .title(EDTextUtils.getTranslation("itemGroup.endersdelight"))
                    .icon(EDItems.ENDSTONE_STOVE.get()::getDefaultInstance)
                    .displayItems((itemDisplayParameters, output) -> EDMainTableContents(output::accept))
                    .build()
    );

    private static void EDMainTableContents(Consumer<Item> event) {
        event.accept(EDItems.ENDSTONE_STOVE.get());
    }
}
