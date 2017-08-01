package com.insping.libra.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.protobuf.MessageLite;
import com.insping.log.LibraLog;

import io.netty.channel.ChannelHandlerContext;

public class ConnectionManager {
	private static ConnectionManager instance = new ConnectionManager();

	public static ConnectionManager getInstance() {
		return instance;
	}

	// 连接表
	private Map<Long, Connection> connections = new ConcurrentHashMap<Long, Connection>();

	/**
	 * 添加一个连接到集合
	 *
	 * @param c
	 */
	public void addConnection(long uid, Connection c) {
		connections.put(c.getUid(), c);
	}

	/**
	 * 根据连接上下文添加一个连接到集合
	 *
	 * @param ctx
	 */
	public Connection addConnection(long uid, ChannelHandlerContext ctx) {
		final Connection c = new Connection(uid, ctx);
		connections.put(c.getUid(), c);
		return c;
	}

	/**
	 * 从集合中获取一个连接
	 *
	 * @param id
	 * @return
	 */
	public Connection getConnection(long uid) {
		return connections.get(uid);
	}

	/**
	 * 从集合中删除一个连接
	 *
	 * @param c
	 */
	public void removeConnection(Connection c) {
		Connection conn = connections.remove(c.getUid());
		conn.close();
	}

	/**
	 * 对uid 发送消息
	 * 
	 * @param uid
	 * @param msg
	 * @throws Exception
	 */
	public void sendMessage(long uid, MessageLite msg) {
		Connection conn = connections.get(uid);
		if (conn == null || conn.isClosed() || conn.isKick()) {
			LibraLog.info("user is offline,uid = " + uid);
			return;
		}
		try {
			LibraMessage libraMessage = LibraMessage.createLibraMessage(uid, msg);
			conn.write(libraMessage);
		} catch (Exception e) {
			LibraLog.error(e.getMessage());
		}
	}

	public void sendAllMessage(MessageLite msg) {
		for (Connection conn : connections.values()) {
			if (conn.getUid() == 0 || conn.isClosed() || conn.isKick()) {
				continue;
			}
			sendMessage(conn.getUid(), msg);
		}
	}
}
