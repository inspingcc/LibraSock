package com.insping;import com.insping.libra.chat.ChatManager;import com.insping.libra.core.ConnectionManager;import com.insping.libra.net.handler.HandlerManager;import com.insping.libra.world.World;public interface Instances {	public static final World world = World.getInstance();	public static final HandlerManager handlerMgr = HandlerManager.getInstance();	public static final ChatManager chatMgr = ChatManager.getInstance();	public static final ConnectionManager connectionMgr = ConnectionManager.getInstance();}