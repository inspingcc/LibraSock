package com.insping.libra.net.handler.imp;

import com.insping.libra.core.LibraMessage;
import com.insping.libra.net.handler.ServerHandler;
import com.insping.libra.net.proto.ReqRegist.RegistData;
import com.insping.libra.net.response.GeneralResponse;

public class RegistHandler extends ServerHandler {

	@Override
	public GeneralResponse doLogic0(LibraMessage message) {
		// 用户注册
		GeneralResponse resp = new GeneralResponse(message.getHead().getUserInfo(), message);
		if (message == null || message.getBody() == null || !(message.getBody() instanceof RegistData)) {
			resp.fail("服务器异常,请重试");
			return resp;
		}
		RegistData data = (RegistData) message.getBody();

		if (!world.userRegister(data)) {
			resp.fail("账号已经存在!");
			return resp;
		}
		resp.setDesc("注册成功");
		return resp;
	}
}
