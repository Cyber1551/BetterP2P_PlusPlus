package com.projecturanus.betterp2p_plusplus.network

import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos

class P2PInfo(val index: Int, val frequency: Short, val pos: BlockPos, val facing: EnumFacing, val output: Boolean, val hasChannel: Boolean, val displayName: CharSequence = "") {
}
