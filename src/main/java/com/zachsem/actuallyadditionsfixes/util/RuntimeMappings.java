package com.zachsem.actuallyadditionsfixes.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public final class RuntimeMappings {
    private static final Method GET_MAX_STACK_SIZE = ReflectionHelper.findMethod(
            ItemStack.class,
            "getMaxStackSize",
            "func_77976_d"
    );

    private static final Field WORLD_RANDOM = ReflectionHelper.findField(
            World.class,
            "rand",
            "field_73012_v"
    );

    private static final Field AIR_BLOCK = ReflectionHelper.findField(
            Blocks.class,
            "AIR",
            "field_150350_a"
    );

    private RuntimeMappings() {
    }

    public static int getMaxStackSize(ItemStack stack) {
        try {
            return (Integer) GET_MAX_STACK_SIZE.invoke(stack);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Unable to read ItemStack max stack size", e);
        }
    }

    public static boolean isAirBlock(Block block) {
        try {
            return block == AIR_BLOCK.get(null);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to resolve the Minecraft air block", e);
        }
    }

    public static float nextWorldFloat(World world) {
        try {
            return ((Random) WORLD_RANDOM.get(world)).nextFloat();
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to access World random source", e);
        }
    }
}
