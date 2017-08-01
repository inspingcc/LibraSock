package com.insping.libra.net.handler.imp;

import com.insping.libra.core.LibraMessage;
import com.insping.libra.net.handler.ServerHandler;
import com.insping.libra.net.proto.ReqLogin.LoginData;
import com.insping.libra.net.response.GeneralResponse;
import com.insping.libra.world.LibraUser;

import io.netty.channel.ChannelHandlerContext;

public class LoginHandler extends ServerHandler {

	@Override
	public GeneralResponse doLogic00(ChannelHandlerContext ctx, LibraMessage message) {
		GeneralResponse resp = new GeneralResponse(message.getHead().getUserInfo(), message);
		if (message == null || message.getBody() == null || !(message.getBody() instanceof LoginData)) {
			resp.fail("服务器异常,请重试");
			return resp;
		}
		LoginData data = (LoginData) message.getBody();

		LibraUser user = world.userLogin(data);
		if (user == null) {
			resp.fail("账号不存在或账号密码不正确!");
			return resp;
		}
		// 添加用户的通道
		connectionMgr.addConnection(user.getUid(), ctx);
		// 发送自己到客户端
		user.sendMessage();
		resp.setDesc("登录成功!");
		return resp;
	}

	@Override
	public GeneralResponse doLogic0(LibraMessage message) {
		return null;
	}
}
