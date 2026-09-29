package com.zachsem.actuallyadditionsfixes.mixin;

import com.zachsem.actuallyadditionsfixes.util.RuntimeMappings;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityPhantomPlacer;
import de.ellpeck.actuallyadditions.mod.util.ItemStackHandlerAA;
import de.ellpeck.actuallyadditions.mod.util.StackUtil;
import de.ellpeck.actuallyadditions.mod.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = TileEntityPhantomPlacer.class, priority = 1100, remap = false)
public abstract class MixinTileEntityPhantomPlacer {
    @Shadow(remap = false)
    public BlockPos boundPosition;

    @Unique
    private boolean actuallyAdditionsFixes$eventAllowsBreak = true;

    @Redirect(
            method = "doWork",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getDrops(Lnet/minecraft/util/NonNullList;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;I)V",
                    remap = false
            ),
            require = 1,
            remap = false
    )
    private void actuallyAdditionsFixes$fireForgeBreakEvents(
            Block block,
            NonNullList<ItemStack> drops,
            IBlockAccess blockAccess,
            BlockPos dropPosition,
            IBlockState state,
            int fortune
    ) {
        block.getDrops(drops, blockAccess, dropPosition, state, fortune);

        // The r152 Phantom Breaker calls getDrops even when its linked position is AIR.
        // Do not synthesize Forge break/harvest events for that no-op cycle.
        if (RuntimeMappings.isAirBlock(block)) {
            this.actuallyAdditionsFixes$eventAllowsBreak = true;
            return;
        }

        World world = (World) blockAccess;
        float chance = WorldUtil.fireFakeHarvestEventsForDropChance(
                (TileEntity) (Object) this,
                drops,
                world,
                this.boundPosition
        );

        this.actuallyAdditionsFixes$eventAllowsBreak =
                chance > 0.0F && RuntimeMappings.nextWorldFloat(world) <= chance;
    }

    @Redirect(
            method = "doWork",
            at = @At(
                    value = "INVOKE",
                    target = "Lde/ellpeck/actuallyadditions/mod/util/StackUtil;canAddAll(Lde/ellpeck/actuallyadditions/mod/util/ItemStackHandlerAA;Ljava/util/List;Z)Z",
                    remap = false
            ),
            require = 1,
            remap = false
    )
    private boolean actuallyAdditionsFixes$respectForgeBreakEvents(
            ItemStackHandlerAA inventory,
            List<ItemStack> drops,
            boolean fromAutomation
    ) {
        boolean eventAllowsBreak = this.actuallyAdditionsFixes$eventAllowsBreak;
        this.actuallyAdditionsFixes$eventAllowsBreak = true;

        return eventAllowsBreak && StackUtil.canAddAll(inventory, drops, fromAutomation);
    }
}
