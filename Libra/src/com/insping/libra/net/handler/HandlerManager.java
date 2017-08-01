package com.insping.libra.net.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.protobuf.MessageLite;
import com.insping.Const;
import com.insping.common.utils.XmlUtils;
import com.insping.log.LibraLog;

public class HandlerManager {
	private static HandlerManager instance = new HandlerManager();

	public static HandlerManager getInstance() {
		return instance;
	}

	/**
	 * 协议对应的解析类的集合
	 */
	Map<Integer, MessageLite> protocols = new HashMap<>();
	/**
	 * 解析类对应的业务类的集合
	 */
	Map<Integer, ServerHandler> handlers = new HashMap<>();

	/**
	 * 回复类对应模块号的集合
	 */
	Map<String, Integer> modules = new HashMap<>();

	/**
	 * 注册相关解析数据类型
	 */
	public final void register() {
		// 注册下行数据反序列化及其对应业务类
		registerHandlers();
		// 注册上行数据序列化Map
		registerModules();
	}

	/**
	 * 注册数据反序列化
	 */
	@SuppressWarnings("unchecked")
	private void registerHandlers() {
		try {
			Document document = XmlUtils.load(Const.CONF_PATH + "ServiceHandlers.xml");
			Element element = document.getDocumentElement();
			Element[] elements = XmlUtils.getChildrenByName(element, "ServiceHandler");
			for (int i = 0; i < elements.length; ++i) {
				String code = XmlUtils.getAttribute(elements[i], "orderCode");
				String protocolClassName = XmlUtils.getAttribute(elements[i], "protocolClass");
				String handlerClassName = XmlUtils.getAttribute(elements[i], "handlerClass");
				int protocolId = Integer.parseInt(code.toLowerCase().replaceFirst("0x", ""), 16);

				// 协议类
				Class<? extends MessageLite> pclazz = (Class<? extends MessageLite>) Class.forName(protocolClassName);
				Method method = pclazz.getMethod("getDefaultInstance", null);
				Object phandler = method.invoke(null, null);
				if (phandler == null || !(phandler instanceof MessageLite)) {
					LibraLog.info("protocolClassName is error! code = " + code + "protocolClass: " + protocolClassName + " || handlerClass " + handlerClassName);
					continue;
				}
				// 处理类
				Class<? extends ServerHandler> hclazz = (Class<? extends ServerHandler>) Class.forName(handlerClassName);
				ServerHandler handler = hclazz.newInstance();

				LibraLog.info("regist protocolClass: " + protocolClassName + " || handlerClass " + handlerClassName + " code =" + code);

				// 存储protocol
				if (protocols.containsKey(protocolId)) {
					System.out.println(new Exception("protocols>>>" + protocols.get(protocolId) + "  had  registed by " + protocolId));
				}
				protocols.put(protocolId, (MessageLite) phandler);
				// 存储handler
				if (handlers.containsKey(protocolId)) {
					System.out.println(new Exception("handlers>>>" + protocols.get(protocolId) + "  had  registed by " + protocolId));
				}
				handlers.put(protocolId, handler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注册数据序列化
	 */
	private void registerModules() {
		try {
			Document document = XmlUtils.load(Const.CONF_PATH + "ModuleTypes.xml");
			Element element = document.getDocumentElement();
			Element[] elements = XmlUtils.getChildrenByName(element, "ModuleType");
			for (int i = 0; i < elements.length; ++i) {
				String code = XmlUtils.getAttribute(elements[i], "moduleId");
				String moduleClass = XmlUtils.getAttribute(elements[i], "moduleClass");
				int moduleId = Integer.parseInt(code.toLowerCase().replaceFirst("0x", ""), 16);

				LibraLog.info("regist module: " + moduleClass + "  moduleId = " + code);
				// 存储modules
				if (modules.containsKey(moduleClass)) {
					System.out.println(new Exception(modules.get(moduleClass) + "  had  registed by " + moduleClass));
				}
				modules.put(moduleClass, moduleId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据协议号,获取其对应的Request的protobuf类对象
	 * 
	 * @param protocolId
	 * @return
	 */
	public MessageLite searchReqClass(Integer protocolId) {
		return protocols.get(protocolId);
	}

	/**
	 * 根据消息类型,获取对应的业务类
	 * 
	 * @param className
	 * @return
	 */
	public ServerHandler searchHandlerByRequestClass(Integer protocolId) {
		return handlers.get(protocolId);
	}

	/**
	 * 根据模块类型名,获取对应的模块号
	 * 
	 * @param className
	 * @return
	 */
	public Integer searchModuleIdByClass(String className) {
		return modules.get(className);
	}
}
