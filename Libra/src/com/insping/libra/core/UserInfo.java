package com.insping.libra.core;

import io.netty.buffer.ByteBuf;

public class UserInfo {
	private long uid;
	private int cid;
	private int eid;

	public UserInfo() {

	}

	private UserInfo(long uid) {
		this.uid = uid;
	}

	public static UserInfo decode(ByteBuf in) {
		UserInfo userInfo = new UserInfo();
		userInfo.uid = in.readLong();
		userInfo.cid = in.readInt();
		userInfo.eid = in.readInt();
		return userInfo;
	}
	
	public void encode(ByteBuf out) {
		out.writeLong(uid);
		out.writeInt(cid);
		out.writeInt(eid);
	}

	public static UserInfo createUserInfo(long uid) {
		return new UserInfo(uid);
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}
}
