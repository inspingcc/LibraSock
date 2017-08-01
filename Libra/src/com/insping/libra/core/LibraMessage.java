package com.insping.libra.core;

import com.google.protobuf.MessageLite;
import com.insping.Instances;
import com.insping.common.utils.JsonUtil;
import com.insping.log.LibraLog;

import io.netty.buffer.ByteBuf;

public class LibraMessage implements Instances {
	private LibraHead head;// 消息头
	private MessageLite body;// 消息体

	public LibraMessage() {
	}

	public LibraMessage(LibraHead head, MessageLite body) {
		this.head = head;
		this.body = body;
	}

	/**
	 * 生成对应的消息体
	 * 
	 * @param uid
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public static LibraMessage createLibraMessage(long uid, MessageLite msg) throws Exception {
		Integer moduleId = handlerMgr.searchModuleIdByClass(msg.getClass().getName());
		if (moduleId == null) {
			LibraLog.error("protocolId is null,msg =:" + JsonUtil.ObjectToJsonString(msg));
			throw new Exception("LibraEncoder.encodeHeader >>> protocolId is null");
		}
		LibraHead head = LibraHead.createHead(uid, moduleId);
		LibraMessage message = new LibraMessage();
		message.setHead(head);
		message.setBody(msg);
		return message;
	}

	/**
	 * 解码
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static LibraMessage decode(ByteBuf in) throws Exception {
		LibraMessage message = new LibraMessage();
		message.setHead(LibraHead.decode(in));
		short bodyLength = in.readShort();// 消息体的长度
		if (bodyLength != in.readableBytes()) {
			LibraLog.error("LibraMessage.decode is error! params:bodyLength:" + bodyLength + "\treadableLength:" + in.readableBytes());
			return null;
		}
		ByteBuf bodyByteBuf = in.readBytes(in.readableBytes());
		byte[] array;
		// 反序列化数据的起始点
		int offset;
		// 可读的数据字节长度
		int readableLen = bodyByteBuf.readableBytes();
		// 分为包含数组数据和不包含数组数据两种形式
		if (bodyByteBuf.hasArray()) {
			array = bodyByteBuf.array();
			offset = bodyByteBuf.arrayOffset() + bodyByteBuf.readerIndex();
		} else {
			array = new byte[readableLen];
			bodyByteBuf.getBytes(bodyByteBuf.readerIndex(), array, 0, readableLen);
			offset = 0;
		}
		// 反序列化
		message.setBody(decodeBody(message.getHead().getProtocolID(), array, offset, readableLen));
		return message;
	}

	/**
	 * 根据协议号用响应的protobuf类型来解析协议数据
	 * 
	 * @param _typeId
	 * @param array
	 * @param offset
	 * @param length
	 * @return
	 * @throws Exception
	 */
	private static MessageLite decodeBody(int protocolId, byte[] array, int offset, int length) throws Exception {
		MessageLite messageLite = handlerMgr.searchReqClass(protocolId);
		if (messageLite == null) {
			LibraLog.error("decodeBody: searchReqClass is null." + " protocolId = " + protocolId);
			return null;
		}
		return messageLite.getDefaultInstanceForType().getParserForType().parseFrom(array, offset, length);
	}

	/**
	 * 编码
	 * 
	 * @param out
	 */
	public void encode(ByteBuf out) {
		byte[] head = encodeHead();
		byte[] body = encodeBody();
		out.writeShort((short) (head.length + body.length + 2)); // 消息的总长度
		out.writeBytes(head); // 协议头
		out.writeShort((short) body.length); // 消息体长度
		out.writeBytes(body); // 消息体
	}

	/**
	 * 编码消息头
	 * 
	 * @return
	 */
	public byte[] encodeHead() {
		return head.encode();
	}

	/**
	 * 编码消息体
	 * 
	 * @return
	 */
	public byte[] encodeBody() {
		return body.toByteArray();
	}

	public LibraHead getHead() {
		return head;
	}

	public void setHead(LibraHead head) {
		this.head = head;
	}

	public MessageLite getBody() {
		return body;
	}

	public void setBody(MessageLite body) {
		this.body = body;
	}

}
