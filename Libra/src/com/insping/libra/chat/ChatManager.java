package com.insping.libra.chat;

import com.insping.Instances;
import com.insping.libra.net.proto.ReqChatSendMessage.ChatSendMessageData;
import com.insping.libra.net.proto.ResChatRevMessage.ChatRevMessageData;
import com.insping.libra.world.LibraUser;

public class ChatManager implements Instances {
	private static ChatManager instance = new ChatManager();

	public static ChatManager getInstance() {
		return instance;
	}

	/**
	 * user 发送data数据
	 * 
	 * @param user
	 * @param data
	 * @return
	 */
	public boolean sendMessage(LibraUser user, ChatSendMessageData data) {
		ChatRevMessageData.Builder buider = ChatRevMessageData.newBuilder();
		buider.setUid(user.getUid());
		buider.setName(user.getName());
		buider.setTime(data.getTime());
		buider.setContent(data.getContent());
		connectionMgr.sendAllMessage(buider.build());
		return true;
	}

}
