package com.axedgaming.common.registry;

import com.axedgaming.common.blocks.EndstoneStoveBlock;
import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class EDItems {
    public static final LazyRegistrar<Item> ITEMS = LazyRegistrar.create(Registries.ITEM, "endersdelight");

    public static final Supplier<Item> ENDSTONE_STOVE;

    public EDItems() {
    }

    public static Item.Properties basicItem() {
        return (new Item.Properties());
    }
    public static Item.Properties foodItem(FoodProperties food) {
        return (new Item.Properties()).food(food);
    }

    public static Item.Properties bowlFoodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).craftRemainder(Items.BOWL).stacksTo(16);
        //TODO: REPLACE BOWL WITH SHULKER BOWL
    }

    static {
        ENDSTONE_STOVE = ITEMS.register("endstone_stove", () -> new BlockItem(EDBlocks.ENDSTONE_STOVE.get(), basicItem())
            { protected boolean placeBlock(BlockPlaceContext context, BlockState state)
            { return super.placeBlock(context, state.setValue(EndstoneStoveBlock.LIT, true));}}
        );
    }
}
