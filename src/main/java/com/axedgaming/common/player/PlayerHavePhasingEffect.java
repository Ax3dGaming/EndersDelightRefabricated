package com.axedgaming.common.player;

import com.axedgaming.common.registry.EDEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

public class PlayerHavePhasingEffect {
    public PlayerHavePhasingEffect() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, damageSource, amount) -> {
            if (entity != null) {
                execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
            }
            return true;
        });
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        double attempts = 0;
        double nx = 0;
        double ny = 0;
        double nz = 0;
        boolean goodpos = false;
        if (!entity.isInWaterRainOrBubble()) {
            if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(EDEffects.PHASING.get())) {
                goodpos = false;
                //if (entity instanceof Player _player && !_player.level().isClientSide())
                //    _player.displayClientMessage(Component.literal("good pos = false"), false);
                while (attempts <= 5) {
                    if (goodpos == false) {
                        nx = x + Mth.nextInt(RandomSource.create(), -5, 5);
                        //if (entity instanceof Player _player && !_player.level().isClientSide())
                        //    _player.displayClientMessage(Component.literal("new nx"), false);
                        ny = y + Mth.nextInt(RandomSource.create(), -3, 3);
                        //if (entity instanceof Player _player && !_player.level().isClientSide())
                        //    _player.displayClientMessage(Component.literal("new ny"), false);
                        nz = z + Mth.nextInt(RandomSource.create(), -5, 5);
                        //if (entity instanceof Player _player && !_player.level().isClientSide())
                        //    _player.displayClientMessage(Component.literal("new nz"), false);
                        if ((world.getBlockState(BlockPos.containing(nx, ny + 1, nz))).getBlock() == Blocks.AIR && (world.getBlockState(BlockPos.containing(nx, ny, nz))).getBlock() == Blocks.AIR
                                && world.getBlockState(BlockPos.containing(nx, ny - 1, nz)).canOcclude()) {
                            //if (entity instanceof Player _player && !_player.level().isClientSide())
                            //    _player.displayClientMessage(Component.literal("good pos = true"), false);
                            goodpos = true;
                            attempts = 6;
                        } else {
                            attempts = attempts + 1;
                        }
                    } else {
                        attempts = 6;
                    }
                }
                if (goodpos == true) {
                    {
                        Entity _ent = entity;
                        _ent.teleportTo(nx, ny, nz);
                        if (_ent instanceof ServerPlayer _serverPlayer)
                            _serverPlayer.connection.teleport(nx, ny, nz, _ent.getYRot(), _ent.getXRot());
                    }
                }
            }
        }
    }
}



