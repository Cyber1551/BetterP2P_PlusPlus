package com.projecturanus.enhancedp2p.network

import io.netty.buffer.ByteBuf
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import java.nio.charset.StandardCharsets

fun readInfo(buf: ByteBuf): P2PInfo {
    val index = buf.readInt()
    val freq = buf.readShort()
    val pos = BlockPos.fromLong(buf.readLong())
    val facing = EnumFacing.values()[buf.readInt()]
    val output = buf.readBoolean()
    val hasChannel = buf.readBoolean()
    val displayNameLength:Int = buf.readInt();
    val displayName:String = buf.readCharSequence(displayNameLength, StandardCharsets.UTF_8).toString()

    return P2PInfo(index, freq, pos, facing, output, hasChannel, displayName)
}

fun writeInfo(buf: ByteBuf, info: P2PInfo) {
    buf.writeInt(info.index)
    buf.writeShort(info.frequency.toInt())
    buf.writeLong(info.pos.toLong())
    buf.writeInt(info.facing.index)
    buf.writeBoolean(info.output)
    buf.writeBoolean(info.hasChannel)
    buf.writeInt(info.displayName.length)
    buf.writeCharSequence(info.displayName, StandardCharsets.UTF_8)
}

class S2CRefreshInfo(var infos: List<P2PInfo> = emptyList()) : IMessage {
    override fun fromBytes(buf: ByteBuf) {
        val length = buf.readInt()
        val list = ArrayList<P2PInfo>(length)
        for (i in 0 until length) {
            list += readInfo(buf)
        }
        infos = list
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeInt(infos.size)
        for (info in infos) {
            writeInfo(buf, info)
        }
    }
}
