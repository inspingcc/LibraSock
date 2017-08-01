package com.insping.libra.net.handler.imp;

import com.insping.libra.core.LibraMessage;
import com.insping.libra.net.handler.ServerHandler;
import com.insping.libra.net.proto.ReqChatSendMessage.ChatSendMessageData;
import com.insping.libra.net.response.GeneralResponse;
import com.insping.libra.world.LibraUser;

public class ChatSendMessageHandler extends ServerHandler {

	@Override
	public GeneralResponse doLogic0(LibraMessage message) {
		GeneralResponse resp = new GeneralResponse(message.getHead().getUserInfo(),message);
		if (message == null || message.getBody() == null || !(message.getBody() instanceof ChatSendMessageData)) {
			resp.fail();
			return resp;
		}
		LibraUser user = world.searchLibraUser(message.getHead().getUserInfo().getUid());
		if(user == null){
			resp.fail();
			return resp;
		}
		ChatSendMessageData data = (ChatSendMessageData) message.getBody();
		if (!chatMgr.sendMessage(user,data)) {
			resp.fail();
			return resp;
		}
		resp.setDesc("regist is suc!");
		return resp;
	}
}
