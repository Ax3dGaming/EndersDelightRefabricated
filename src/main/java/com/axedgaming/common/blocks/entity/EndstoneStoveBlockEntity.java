package com.axedgaming.common.blocks.entity;

import com.axedgaming.common.blocks.EndstoneStoveBlock;
import com.axedgaming.common.registry.EDBlockEntityTypes;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.mixin.accessor.RecipeManagerAccessor;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.block.StoveBlock;

import java.util.Optional;

public class EndstoneStoveBlockEntity extends SyncedBlockEntity {
    private static final VoxelShape GRILLING_AREA = Block.box(3.0, 0.0, 3.0, 13.0, 1.0, 13.0);
    private static final int INVENTORY_SLOT_COUNT = 6;
    private final ItemStackHandlerContainer inventory = this.createHandler();
    private final int[] cookingTimes = new int[6];
    private final int[] cookingTimesTotal = new int[6];
    private ResourceLocation[] lastRecipeIDs = new ResourceLocation[6];

    public EndstoneStoveBlockEntity(BlockPos pos, BlockState state) {
        super(EDBlockEntityTypes.ENDSTONE_STOVE_BE.get(), pos, state);
    }

    public void load(CompoundTag compound) {
        super.load(compound);
        if (compound.contains("Inventory")) {
            this.inventory.deserializeNBT(compound.getCompound("Inventory"));
        } else {
            this.inventory.deserializeNBT(compound);
        }

        int[] arrayCookingTimesTotal;
        if (compound.contains("CookingTimes", 11)) {
            arrayCookingTimesTotal = compound.getIntArray("CookingTimes");
            System.arraycopy(arrayCookingTimesTotal, 0, this.cookingTimes, 0, Math.min(this.cookingTimesTotal.length, arrayCookingTimesTotal.length));
        }

        if (compound.contains("CookingTotalTimes", 11)) {
            arrayCookingTimesTotal = compound.getIntArray("CookingTotalTimes");
            System.arraycopy(arrayCookingTimesTotal, 0, this.cookingTimesTotal, 0, Math.min(this.cookingTimesTotal.length, arrayCookingTimesTotal.length));
        }

    }

    public void saveAdditional(CompoundTag compound) {
        this.writeItems(compound);
        compound.putIntArray("CookingTimes", this.cookingTimes);
        compound.putIntArray("CookingTotalTimes", this.cookingTimesTotal);
    }

    private CompoundTag writeItems(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("Inventory", this.inventory.serializeNBT());
        return compound;
    }

    public static void cookingTick(Level level, BlockPos pos, BlockState state, EndstoneStoveBlockEntity stove) {
        boolean isStoveLit = state.getValue(EndstoneStoveBlock.LIT);
        if (stove.isStoveBlockedAbove()) {
            if (!ItemUtils.isInventoryEmpty(stove.inventory)) {
                ItemUtils.dropItems(level, pos, stove.inventory);
                stove.inventoryChanged();
            }
        } else if (isStoveLit) {
            stove.cookAndOutputItems();
        } else {
            for(int i = 0; i < stove.inventory.getSlotCount(); ++i) {
                if (stove.cookingTimes[i] > 0) {
                    stove.cookingTimes[i] = Mth.clamp(stove.cookingTimes[i] - 2, 0, stove.cookingTimesTotal[i]);
                }
            }
        }

    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, EndstoneStoveBlockEntity stove) {
        for(int i = 0; i < stove.inventory.getSlotCount(); ++i) {
            if (!stove.inventory.getStackInSlot(i).isEmpty() && level.random.nextFloat() < 0.2F) {
                Vec2 stoveItemVector = stove.getStoveItemOffset(i);
                Direction direction = state.getValue(StoveBlock.FACING);
                int directionIndex = direction.get2DDataValue();
                Vec2 offset = directionIndex % 2 == 0 ? stoveItemVector : new Vec2(stoveItemVector.y, stoveItemVector.x);
                double x = (double)pos.getX() + 0.5 - (double)((float)direction.getStepX() * offset.x) + (double)((float)direction.getClockWise().getStepX() * offset.x);
                double y = (double)pos.getY() + 1.0;
                double z = (double)pos.getZ() + 0.5 - (double)((float)direction.getStepZ() * offset.y) + (double)((float)direction.getClockWise().getStepZ() * offset.y);

                for(int k = 0; k < 3; ++k) {
                    level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 5.0E-4, 0.0);
                }
            }
        }

    }

    private void cookAndOutputItems() {
        if (this.level != null) {
            boolean didInventoryChange = false;

            for(int i = 0; i < this.inventory.getSlotCount(); ++i) {
                ItemStack stoveStack = this.inventory.getStackInSlot(i);
                if (!stoveStack.isEmpty()) {
                    ++cookingTimes[i];
                    if (this.cookingTimes[i] >= this.cookingTimesTotal[i]) {
                        Container inventoryWrapper = new SimpleContainer(stoveStack);
                        Optional<CampfireCookingRecipe> recipe = this.getMatchingRecipe(inventoryWrapper, i);
                        if (recipe.isPresent()) {
                            ItemStack resultStack = recipe.get().getResultItem(level.registryAccess());
                            if (!resultStack.isEmpty()) {
                                ItemUtils.spawnItemEntity(level, resultStack.copy(),
                                        worldPosition.getX() + 0.5, worldPosition.getY() + 1.0, worldPosition.getZ() + 0.5,
                                        level.random.nextGaussian() * (double) 0.01F, 0.1F, level.random.nextGaussian() * (double) 0.01F);
                            }
                        }

                        this.inventory.setStackInSlot(i, ItemStack.EMPTY);
                        didInventoryChange = true;
                    }
                }
            }

            if (didInventoryChange) {
                this.inventoryChanged();
            }

        }
    }

    public int getNextEmptySlot() {
        for(int i = 0; i < this.inventory.getSlotCount(); ++i) {
            ItemStack slotStack = this.inventory.getStackInSlot(i);
            if (slotStack.isEmpty()) {
                return i;
            }
        }

        return -1;
    }

    public boolean addItem(ItemStack itemStackIn, CampfireCookingRecipe recipe, int slot) {
        if (0 <= slot && slot < this.inventory.getSlotCount()) {
            ItemStack slotStack = this.inventory.getStackInSlot(slot);
            if (slotStack.isEmpty()) {
                this.cookingTimesTotal[slot] = recipe.getCookingTime();
                this.cookingTimes[slot] = 0;
                this.inventory.setStackInSlot(slot, itemStackIn.split(1));
                this.lastRecipeIDs[slot] = recipe.getId();
                this.inventoryChanged();
                return true;
            }
        }

        return false;
    }

    public Optional<CampfireCookingRecipe> getMatchingRecipe(Container recipeWrapper, int slot) {
        if (this.level == null) {
            return Optional.empty();
        } else {
            if (this.lastRecipeIDs[slot] != null) {
                Recipe<Container> recipe = ((RecipeManagerAccessor)this.level.getRecipeManager()).getRecipeMap(RecipeType.CAMPFIRE_COOKING).get(this.lastRecipeIDs[slot]);
                if (recipe instanceof CampfireCookingRecipe && recipe.matches(recipeWrapper, this.level)) {
                    return Optional.of((CampfireCookingRecipe)recipe);
                }
            }

            return this.level.getRecipeManager().getRecipeFor(RecipeType.CAMPFIRE_COOKING, recipeWrapper, this.level);
        }
    }

    public ItemStackHandlerContainer getInventory() {
        return this.inventory;
    }

    public boolean isStoveBlockedAbove() {
        if (this.level != null) {
            BlockState above = this.level.getBlockState(this.worldPosition.above());
            return Shapes.joinIsNotEmpty(GRILLING_AREA, above.getShape(this.level, this.worldPosition.above()), BooleanOp.AND);
        } else {
            return false;
        }
    }

    public Vec2 getStoveItemOffset(int index) {
        float X_OFFSET = 0.3F;
        float Y_OFFSET = 0.2F;
        Vec2[] OFFSETS = new Vec2[]{new Vec2(0.3F, 0.2F), new Vec2(0.0F, 0.2F), new Vec2(-0.3F, 0.2F), new Vec2(0.3F, -0.2F), new Vec2(0.0F, -0.2F), new Vec2(-0.3F, -0.2F)};
        return OFFSETS[index];
    }

    private void addParticles() {
        if (this.level != null) {
            for(int i = 0; i < this.inventory.getSlotCount(); ++i) {
                if (!this.inventory.getStackInSlot(i).isEmpty() && this.level.random.nextFloat() < 0.2F) {
                    Vec2 stoveItemVector = this.getStoveItemOffset(i);
                    Direction direction = this.getBlockState().getValue(StoveBlock.FACING);
                    int directionIndex = direction.get2DDataValue();
                    Vec2 offset = directionIndex % 2 == 0 ? stoveItemVector : new Vec2(stoveItemVector.y, stoveItemVector.x);
                    double x = (double)this.worldPosition.getX() + 0.5 - (double)((float)direction.getStepX() * offset.x) + (double)((float)direction.getClockWise().getStepX() * offset.x);
                    double y = (double)this.worldPosition.getY() + 1.0;
                    double z = (double)this.worldPosition.getZ() + 0.5 - (double)((float)direction.getStepZ() * offset.y) + (double)((float)direction.getClockWise().getStepZ() * offset.y);

                    for(int k = 0; k < 3; ++k) {
                        this.level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 5.0E-4, 0.0);
                    }
                }
            }

        }
    }

    public CompoundTag getUpdateTag() {
        return this.writeItems(new CompoundTag());
    }

    private ItemStackHandlerContainer createHandler() {
        return new ItemStackHandlerContainer(6) {
            public int getSlotLimit(int slot) {
                return 1;
            }
        };
    }
}
