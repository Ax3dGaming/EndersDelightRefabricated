package com.axedgaming.common.registry;

import com.axedgaming.common.blocks.EndstoneStoveBlock;
import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.StoveBlock;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class EDBlocks {
    public static final LazyRegistrar<Block> BLOCKS = LazyRegistrar.create(Registries.BLOCK, "endersdelight");

    public static final Supplier<Block> ENDSTONE_STOVE;
    public static final Supplier<Block> CHORUS_CRATE;
    public static final Supplier<Block> CHORUS_PIE;
    public static final Supplier<Block> STUFFED_SHULKER;

    public EDBlocks() {
    }

    static {
        ENDSTONE_STOVE = BLOCKS.register("endstone_stove", () ->
                new EndstoneStoveBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS).lightLevel(litBlockEmission(13))));

        CHORUS_CRATE = BLOCKS.register("chorus_crate", () ->
                new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

        CHORUS_PIE = BLOCKS.register("chorus_pie", () ->
                new PieBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), EDItems.CHORUS_PIE_SLICE));

        STUFFED_SHULKER = BLOCKS.register("stuffed_shulker_block", () ->
                new FeastBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN), EDItems.STUFFED_SHULKER_BOWL, true));
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }
}
