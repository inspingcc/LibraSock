package com.insping.libra.world;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.insping.Instances;
import com.insping.common.utils.JsonUtil;
import com.insping.libra.net.proto.ReqLogin.LoginData;
import com.insping.libra.net.proto.ReqRegist.RegistData;
import com.insping.log.LibraLog;

public class World implements Instances {
	private static World instance = new World();

	public static World getInstance() {
		return instance;
	}

	// uid 生成器
	private AtomicLong uidMaker = new AtomicLong(100000L);

	// 所有用户
	Map<Long, LibraUser> users = new HashMap<>();
	//
	Map<String, LibraUser> accounts = new HashMap<>();

	public LibraUser searchLibraUser(long uid) {
		return users.get(uid);
	}

	/**
	 * 注册用户
	 * 
	 * @param account
	 * @param password
	 */
	public boolean userRegister(RegistData data) {
		if (accounts.get(data.getUserAccout()) != null) {
			return false;
		}
		LibraUser user = new LibraUser(uidMaker.incrementAndGet(), data.getUserAccout(), data.getPassword(), data.getNickName());
		users.put(user.getUid(), user);
		accounts.put(data.getUserAccout(), user);
		// 发送自己到客户端
		// user.sendMessage();
		LibraLog.info("用户注册成功>>>user = " + JsonUtil.ObjectToJsonString(user));
		return true;
	}

	/**
	 * 用户登录
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public LibraUser userLogin(LoginData data) {
		LibraUser user = accounts.get(data.getUserAccout());
		if (user == null || !user.getPassword().equals(data.getPassword())) {
			return null;
		}
		LibraLog.info("用户" + user.getName() + "登录成功>>>user = " + JsonUtil.ObjectToJsonString(user));
		return user;
	}

}
