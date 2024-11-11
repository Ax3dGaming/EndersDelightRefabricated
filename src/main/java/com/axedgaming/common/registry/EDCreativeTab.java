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
        //event.accept(EDItems.CHORUS_CRATE.get());
        event.accept(EDItems.ENDSTONE_STOVE.get());
        event.accept(EDItems.SHULKER_BOWL.get());
        event.accept(EDItems.MITE_CRUST.get());
        event.accept(EDItems.ENDER_SHARD.get());
        event.accept(EDItems.ENDERMAN_SIGHT.get());
        event.accept(EDItems.SIGHT_FRAGMENT.get());
        event.accept(EDItems.SHULKER_MOLLUSK.get());
        event.accept(EDItems.SHULKER_FILET.get());
        event.accept(EDItems.UNCANNY_COOKIES.get());
        event.accept(EDItems.CHORUS_JUICE.get());
        event.accept(EDItems.STRANGE_ECLAIR.get());
        //event.accept(EDItems.CHORUS_PIE.get());
        event.accept(EDItems.CHORUS_PIE_SLICE.get());
        event.accept(EDItems.CHORUS_STEW.get());
        event.accept(EDItems.CRISPY_SKEWER.get());
        event.accept(EDItems.CRAWLING_SANDWICH.get());
        event.accept(EDItems.TWISTED_CEREAL.get());
        event.accept(EDItems.ENDERMITE_STEW.get());
        event.accept(EDItems.PEARL_PASTA.get());
        event.accept(EDItems.ENDER_PAELLA.get());
        event.accept(EDItems.STUFFED_SHULKER_BOWL.get());
        //event.accept(EDItems.STUFFED_SHULKER.get());
    }
}
