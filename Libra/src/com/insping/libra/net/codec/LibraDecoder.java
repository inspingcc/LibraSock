package com.insping.libra.net.codec;

import java.util.List;

import com.insping.Instances;
import com.insping.libra.core.LibraMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class LibraDecoder extends ByteToMessageDecoder implements Instances {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		try {
			//LibraLog.info("decode is bytebuf in is :" + JsonUtil.ObjectToJsonString(in.array()));
			// 空的buf
			if (in instanceof EmptyByteBuf || in.readableBytes() < 0) {
				return;
			}
			short length = in.readShort();
			if (length != in.readableBytes()) {
				return;
			}
			// 反序列化
			LibraMessage message = LibraMessage.decode(in);
			if (message == null) {
				return;
			}
			out.add(message);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
