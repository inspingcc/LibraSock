package com.insping.libra.core;

import com.insping.Instances;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;

public class LibraHead implements Instances {
	private int destInstanceID;// 来源instance
	private int srcInstanceID;// 目的instance
	private long seqNum;// 相应次数
	private int flag;// 标志
	private int protocolID;// 协议ID
	private UserInfo userInfo;// 用户标识

	public LibraHead() {
	}

	/**
	 * 解码
	 * 
	 * @param in
	 * @return
	 */
	public static LibraHead decode(ByteBuf in) {
		LibraHead head = new LibraHead();
		head.destInstanceID = in.readInt();
		head.srcInstanceID = in.readInt();
		head.seqNum = in.readLong();
		head.flag = in.readInt();
		head.protocolID = in.readInt();
		head.userInfo = UserInfo.decode(in);
		return head;
	}

	public byte[] encode() {
		ByteBuf out = UnpooledByteBufAllocator.DEFAULT.buffer();
		out.writeInt(destInstanceID);// 来源instance
		out.writeInt(srcInstanceID);// 目的instance
		out.writeLong(seqNum);// 相应次数
		out.writeInt(flag);// 标志
		out.writeInt(protocolID);// 协议ID
		userInfo.encode(out);// 用户标识
		return out.readBytes(out.readableBytes()).array();
	}

	/**
	 * 
	 * @param uid
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public static LibraHead createHead(long uid, int moduleId) throws Exception {
		LibraHead head = new LibraHead();
		head.destInstanceID = 1;
		head.srcInstanceID = 2;
		head.seqNum = 3;
		head.flag = 1;// 0 客户端到服务器 1服务器到客户端
		head.protocolID = moduleId;
		head.userInfo = UserInfo.createUserInfo(uid);
		return head;
	}

	public int getDestInstanceID() {
		return destInstanceID;
	}

	public void setDestInstanceID(int destInstanceID) {
		this.destInstanceID = destInstanceID;
	}

	public int getSrcInstanceID() {
		return srcInstanceID;
	}

	public void setSrcInstanceID(int srcInstanceID) {
		this.srcInstanceID = srcInstanceID;
	}

	public long getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(long seqNum) {
		this.seqNum = seqNum;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getProtocolID() {
		return protocolID;
	}

	public void setProtocolID(int protocolID) {
		this.protocolID = protocolID;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
