package com.projecturanus.betterp2p_plusplus.network

import io.netty.buffer.ByteBuf
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class C2SLinkP2P(var input: Int = -1, var output: Int = -1): IMessage {
    override fun fromBytes(buf: ByteBuf) {
        input = buf.readInt()
        output = buf.readInt()
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeInt(input)
        buf.writeInt(output)
    }
}
