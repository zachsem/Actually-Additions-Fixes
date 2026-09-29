package com.zachsem.actuallyadditionsfixes.mixin;

import com.zachsem.actuallyadditionsfixes.util.RuntimeMappings;
import de.ellpeck.actuallyadditions.mod.crafting.RecipeBioMash;
import de.ellpeck.actuallyadditions.mod.items.InitItems;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheMiscItems;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = RecipeBioMash.class, remap = false)
public abstract class MixinRecipeBioMash {
    @ModifyConstant(
            method = "func_77572_b(Lnet/minecraft/inventory/InventoryCrafting;)Lnet/minecraft/item/ItemStack;",
            constant = @Constant(intValue = 64),
            require = 1,
            remap = false
    )
    private int actuallyAdditionsFixes$useMashedFoodStackLimit(int originalLimit) {
        ItemStack mashedFood = new ItemStack(
                InitItems.itemMisc,
                1,
                TheMiscItems.MASHED_FOOD.ordinal()
        );
        return RuntimeMappings.getMaxStackSize(mashedFood);
    }
}
