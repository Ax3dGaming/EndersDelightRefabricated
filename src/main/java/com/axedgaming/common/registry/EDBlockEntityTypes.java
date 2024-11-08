package com.axedgaming.common.registry;

import com.axedgaming.common.blocks.entity.EndstoneStoveBlockEntity;
import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class EDBlockEntityTypes {
    public static final LazyRegistrar<BlockEntityType<?>> TILES = LazyRegistrar.create(Registries.BLOCK_ENTITY_TYPE, "endersdelight");
    public static final Supplier<BlockEntityType<EndstoneStoveBlockEntity>> ENDSTONE_STOVE_BE;

    public EDBlockEntityTypes () {
    }

    static {
        ENDSTONE_STOVE_BE = TILES.register("endstone_stove", () ->
                BlockEntityType.Builder.of(EndstoneStoveBlockEntity::new,
                        new Block[]{EDBlocks.ENDSTONE_STOVE.get()}).build(null)
        );
    }
}
