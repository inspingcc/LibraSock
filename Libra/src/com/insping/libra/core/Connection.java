package com.insping.libra.core;

import io.netty.channel.ChannelHandlerContext;

public class Connection {

	private long uid;// 连接标识uid

	private ChannelHandlerContext ctx;// 连接上下文 的通道

	private volatile boolean isClosed = false;// 是否关闭的标识

	private boolean isKick = false;// 是否是被踢下线

	public Connection(long uid, ChannelHandlerContext ctx) {
		this.uid = uid;
		this.ctx = ctx;
	}

	/**
	 * 往连接写入数据
	 *
	 * @param buffer
	 */
	public void write(LibraMessage msg) {
//		Channel channel = ctx.channel();
//		LibraLog.info(String.valueOf(channel.isActive()));
//		channel.writeAndFlush(msg);
		ctx.writeAndFlush(msg);
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		if (!isClosed) {
			isClosed = true;
			ctx.close();
		}
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public boolean isKick() {
		return isKick;
	}

	public void setKick(boolean isKick) {
		this.isKick = isKick;
	}

}
