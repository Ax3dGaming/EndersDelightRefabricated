package com.axedgaming.common.registry;

import com.axedgaming.common.blocks.EndstoneStoveBlock;
import com.axedgaming.common.items.EDFoodValues;
import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.function.Supplier;

public class EDItems {
    public static final LazyRegistrar<Item> ITEMS = LazyRegistrar.create(Registries.ITEM, "endersdelight");

    //BLOCKS
    public static final Supplier<Item> ENDSTONE_STOVE;
    public static final Supplier<Item> STUFFED_SHULKER;
    public static final Supplier<Item> CHORUS_CRATE;
    public static final Supplier<Item> CHORUS_PIE;

    //ITEMS
    public static final Supplier<Item> SHULKER_BOWL;
    public static final Supplier<Item> MITE_CRUST;
    public static final Supplier<Item> ENDER_SHARD;
    public static final Supplier<Item> ENDERMAN_SIGHT;
    public static final Supplier<Item> SIGHT_FRAGMENT;
    public static final Supplier<Item> SHULKER_MOLLUSK;
    public static final Supplier<Item> SHULKER_FILET;
    public static final Supplier<Item> UNCANNY_COOKIES;
    public static final Supplier<Item> CHORUS_JUICE;
    public static final Supplier<Item> STRANGE_ECLAIR;
    public static final Supplier<Item> CHORUS_PIE_SLICE;
    public static final Supplier<Item> CHORUS_STEW;
    public static final Supplier<Item> CRISPY_SKEWER;
    public static final Supplier<Item> CRAWLING_SANDWICH;
    public static final Supplier<Item> TWISTED_CEREAL;
    public static final Supplier<Item> ENDERMITE_STEW;
    public static final Supplier<Item> PEARL_PASTA;
    public static final Supplier<Item> ENDER_PAELLA;
    public static final Supplier<Item> STUFFED_SHULKER_BOWL;

    public EDItems() {
    }

    public static Item.Properties basicItem() {
        return (new Item.Properties());
    }
    public static Item.Properties foodItem(FoodProperties food) {
        return (new Item.Properties()).food(food);
    }

    public static Item.Properties bowlFoodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).craftRemainder(EDItems.SHULKER_BOWL.get()).stacksTo(16);
    }

    public static Item.Properties drinkableItem(FoodProperties food) {
        return (new Item.Properties()).food(food).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }

    static {
        ENDSTONE_STOVE = ITEMS.register("endstone_stove", () -> new BlockItem(EDBlocks.ENDSTONE_STOVE.get(), basicItem())
            { protected boolean placeBlock(BlockPlaceContext context, BlockState state)
            { return super.placeBlock(context, state.setValue(EndstoneStoveBlock.LIT, true));}}
        );
        STUFFED_SHULKER = ITEMS.register("stuffed_shulker_block", () -> new BlockItem(EDBlocks.STUFFED_SHULKER.get(), basicItem().stacksTo(1)));
        CHORUS_CRATE = ITEMS.register("chorus_crate", () -> new BlockItem(EDBlocks.CHORUS_CRATE.get(), basicItem()));
        CHORUS_PIE = ITEMS.register("chorus_pie", () -> new BlockItem(EDBlocks.CHORUS_PIE.get(), basicItem()));

        SHULKER_BOWL = ITEMS.register("shulker_bowl", () -> new Item(new Item.Properties()));
        MITE_CRUST = ITEMS.register("mite_crust", () -> new ConsumableItem(new Item.Properties().food(EDFoodValues.DIET)));
        ENDER_SHARD = ITEMS.register("ender_shard", () -> new Item(new Item.Properties()));
        ENDERMAN_SIGHT = ITEMS.register("enderman_sight", () -> new Item(new Item.Properties()));
        SIGHT_FRAGMENT = ITEMS.register("sight_fragment", () -> new Item(new Item.Properties()));
        SHULKER_MOLLUSK = ITEMS.register("shulker_mollusk", () -> new ConsumableItem(new Item.Properties().food(EDFoodValues.DIET)));
        SHULKER_FILET = ITEMS.register("shulker_filet", () -> new ConsumableItem(new Item.Properties().food(EDFoodValues.DIET)));
        UNCANNY_COOKIES = ITEMS.register("uncanny_cookies", () -> new ConsumableItem(new Item.Properties().food(EDFoodValues.UNCANNY_COOKIES)));
        CHORUS_JUICE = ITEMS.register("chorus_juice", () -> new DrinkableItem(new Item.Properties().food(EDFoodValues.FINGER)));
        STRANGE_ECLAIR = ITEMS.register("strange_eclair", () -> new ConsumableItem(new Item.Properties().food(EDFoodValues.STRANGE_ECLAIR)));
        CHORUS_PIE_SLICE = ITEMS.register("chorus_pie_slice", () -> new ConsumableItem(new Item.Properties().food(FoodValues.PIE_SLICE)));
        CHORUS_STEW = ITEMS.register("chorus_stew", () -> new ConsumableItem(bowlFoodItem(EDFoodValues.CHORUS_STEW)));
        CRISPY_SKEWER = ITEMS.register("crispy_skewer", () -> new ConsumableItem(new Item.Properties().food(EDFoodValues.FINGER)));
        CRAWLING_SANDWICH = ITEMS.register("crawling_sandwich", () -> new ConsumableItem(new Item.Properties().food(EDFoodValues.CRAWLING_SANDWICH)));
        TWISTED_CEREAL = ITEMS.register("twisted_cereal", () -> new ConsumableItem(bowlFoodItem(EDFoodValues.TWISTED_CEREAL), true));
        ENDERMITE_STEW = ITEMS.register("endermite_stew", () -> new ConsumableItem(bowlFoodItem(EDFoodValues.ENDERMITE_STEW)));
        PEARL_PASTA = ITEMS.register("pearl_pasta", () -> new ConsumableItem(bowlFoodItem(EDFoodValues.PEARL_PASTA), true));
        ENDER_PAELLA = ITEMS.register("ender_paella", () -> new ConsumableItem(bowlFoodItem(EDFoodValues.ENDER_PAELLA), true));
        STUFFED_SHULKER_BOWL = ITEMS.register("stuffed_shulker_bowl", () -> new ConsumableItem(bowlFoodItem(EDFoodValues.STUFFED_SHULKER), true));
    }

}
