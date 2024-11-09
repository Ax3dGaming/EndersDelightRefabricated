package com.axedgaming.client.renderer;

import com.axedgaming.common.blocks.EndstoneStoveBlock;
import com.axedgaming.common.blocks.entity.EndstoneStoveBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class EndstoneStoveRenderer implements BlockEntityRenderer<EndstoneStoveBlockEntity> {
    public EndstoneStoveRenderer(BlockEntityRendererProvider.Context context) {
    }

    public void render(EndstoneStoveBlockEntity stoveEntity, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = stoveEntity.getBlockState().getValue(EndstoneStoveBlock.FACING).getOpposite();
        ItemStackHandlerContainer inventory = stoveEntity.getInventory();
        int posLong = (int) stoveEntity.getBlockPos().asLong();

        for (int i = 0; i < inventory.getSlotCount(); ++i) {
            ItemStack stoveStack = inventory.getStackInSlot(i);
            if (!stoveStack.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5, 1.02, 0.5);
                float f = -direction.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f));
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                Vec2 itemOffset = stoveEntity.getStoveItemOffset(i);
                poseStack.translate((double) itemOffset.x, (double) itemOffset.y, 0.0);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                if (stoveEntity.getLevel() != null) {
                    Minecraft.getInstance().getItemRenderer().renderStatic(stoveStack, ItemDisplayContext.FIXED, LevelRenderer.getLightColor(stoveEntity.getLevel(), stoveEntity.getBlockPos().above()), combinedOverlayIn, poseStack, buffer, stoveEntity.getLevel(), posLong + i);
                }

                poseStack.popPose();
            }
        }

    }
}
