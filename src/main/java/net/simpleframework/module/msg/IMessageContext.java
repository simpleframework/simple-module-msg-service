package net.simpleframework.module.msg;

import net.simpleframework.module.common.ICommonModuleContext;
import net.simpleframework.module.common.plugin.ModulePluginRegistry;
import net.simpleframework.module.msg.plugin.IMessagePlugin;
import net.simpleframework.module.msg.plugin.NoticeMessagePlugin;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IMessageContext extends ICommonModuleContext {

	static final String MODULE_NAME = "simple-module-msg";

	@Override
	MessageContextSettings getContextSettings();

	/**
	 * 获取订阅消息服务
	 * 
	 * @return
	 */
	ISubscribeMessageService getSubscribeMessageService();

	/**
	 * 获取点对点消息服务
	 * 
	 * @return
	 */
	IP2PMessageService getP2PMessageService();

	/**
	 * 获取日志消息服务
	 * 
	 * @return
	 */
	IP2PMessageLogService getP2PMessageLogService();

	static int NOTICEMESSAGE_MARK = 1001;
	static int SYSTEMMESSAGE_MARK = 1002;
	static int PRIVATEMESSAGE_MARK = 1003;

	/**
	 * 获取邮件服务
	 * 
	 * @return
	 */
	IEmailService getEmailService();

	MessagePluginRegistry getPluginRegistry();

	NoticeMessagePlugin getNoticeMessagePlugin();

	public static class MessagePluginRegistry extends ModulePluginRegistry<IMessagePlugin> {
	}
}
