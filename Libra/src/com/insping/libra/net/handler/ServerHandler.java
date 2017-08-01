package com.insping.libra.net.handler;

import com.insping.Instances;
import com.insping.libra.core.LibraMessage;
import com.insping.libra.core.UserInfo;
import com.insping.libra.net.handler.imp.LoginHandler;
import com.insping.libra.net.response.GeneralResponse;
import com.insping.log.LibraLog;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author houshanping
 * @since 2017-07-12
 */
public abstract class ServerHandler implements Instances {

	public LibraMessage doLogic(ChannelHandlerContext ctx, LibraMessage message) {
		try {
			GeneralResponse resp = null;
			if (this instanceof LoginHandler) {
				// 特殊处理
				resp = doLogic00(ctx, message);
			} else {
				resp = doLogic0(message);
			}
			// 如果逻辑处理类返回null 或者 非protoBuf数据,则发送默认的数据.
			if (resp == null) {
				LibraLog.info("handler return is null or illegal data.");
				return new GeneralResponse(new UserInfo(), message).result();
			}
			return resp.result();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public abstract GeneralResponse doLogic0(LibraMessage message);

	public GeneralResponse doLogic00(ChannelHandlerContext ctx, LibraMessage message) {
		return null;
	}
}
