package com.insping.libra.net.codec;

import com.insping.Instances;
import com.insping.libra.core.LibraMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class LibraEncoder extends MessageToByteEncoder<LibraMessage> implements Instances {

	@Override
	protected void encode(ChannelHandlerContext ctx, LibraMessage msg, ByteBuf out) throws Exception {
		msg.encode(out);
		return;
	}

}
