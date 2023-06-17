package com.projecturanus.betterp2p_plusplus.network

import com.projecturanus.betterp2p_plusplus.capability.MemoryInfo
import com.projecturanus.betterp2p_plusplus.item.BetterMemoryCardModes
import io.netty.buffer.ByteBuf
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

fun writeMemoryInfo(buf: ByteBuf, info: MemoryInfo) {
    buf.writeInt(info.selectedIndex)
    buf.writeShort(info.frequency.toInt())
    buf.writeInt(info.mode.ordinal)
}

fun readMemoryInfo(buf: ByteBuf): MemoryInfo {
    return MemoryInfo(buf.readInt(), buf.readShort(), BetterMemoryCardModes.values()[buf.readInt()])
}

class C2SUpdateInfo(var info: MemoryInfo = MemoryInfo()) : IMessage {
    override fun fromBytes(buf: ByteBuf) {
        info = readMemoryInfo(buf)
    }

    override fun toBytes(buf: ByteBuf) {
        writeMemoryInfo(buf, info)
    }
}
