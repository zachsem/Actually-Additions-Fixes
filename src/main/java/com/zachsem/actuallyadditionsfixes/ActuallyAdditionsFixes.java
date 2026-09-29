package com.zachsem.actuallyadditionsfixes;

import net.minecraftforge.fml.common.Mod;

@Mod(
        modid = ActuallyAdditionsFixes.MODID,
        name = ActuallyAdditionsFixes.NAME,
        version = ActuallyAdditionsFixes.VERSION,
        acceptedMinecraftVersions = "[1.12.2]",
        dependencies = "required-after:actuallyadditions@[1.12.2-r152];required-after:mixinbooter@[11.17,)"
)
public final class ActuallyAdditionsFixes {
    public static final String MODID = "actuallyadditionsfixes";
    public static final String NAME = "Actually Additions Fixes";
    public static final String VERSION = "1.0.0";
}
