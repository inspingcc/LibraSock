package com.insping.libra.world;

import java.util.Random;

import com.insping.Instances;
import com.insping.common.utils.StringUtils;
import com.insping.libra.net.proto.ResUserInfo.UserInfoData;

public class LibraUser implements Instances{
	private long uid;
	private String account;
	private String password;
	private String name;
	private int age;
	private String sex;
	private int status;
	private long registerTime;

	public LibraUser() {

	}

	public LibraUser(long uid, String account, String password, String name) {
		this.uid = uid;
		this.account = account;
		this.password = password;
		this.name = StringUtils.isNull(name) ? "libra_" + uid : name;
		this.age = 18;
		this.sex = new Random().nextInt() % 2 == 0 ? "保密" : "女";
		this.status = 0;
		this.registerTime = System.currentTimeMillis();
	}

	public LibraUser(long uid, String account, String password, String name, int age, String sex) {
		this.uid = uid;
		this.account = account;
		this.password = password;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.status = 0;
		this.registerTime = System.currentTimeMillis();
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}

	// 发送到客户端
	public void sendMessage() {
		UserInfoData.Builder builder = UserInfoData.newBuilder();
		builder.setUid(uid);
		builder.setSessionKey("sessionKey");
		builder.setUserAccout(account);
		builder.setName(name);
		builder.setSex(sex);
		builder.setAge(age);
		builder.setStatus(status);
		
		connectionMgr.sendMessage(this.uid,builder.build());
	}

}
