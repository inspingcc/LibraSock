package com.insping.libra.core;

import com.insping.Instances;
import com.insping.libra.net.handler.ServerHandler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author houshanping
 *
 */
public class LogicServerHandler extends ChannelHandlerAdapter implements Instances {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		LibraMessage message = (LibraMessage) msg;

		ServerHandler handler = handlerMgr.searchHandlerByRequestClass(message.getHead().getProtocolID());
		if (handler == null) {
			System.out.println("protocolID is :" + message.getHead().getProtocolID() + ",handler is null.");
		}
		try {
			LibraMessage libraMessage = handler.doLogic(ctx, message);
			ctx.writeAndFlush(libraMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();// 发生异常，关闭链路
	}
}
