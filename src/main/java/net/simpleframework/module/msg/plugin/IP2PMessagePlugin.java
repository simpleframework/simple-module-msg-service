package net.simpleframework.module.msg.plugin;

import net.simpleframework.common.ID;
import net.simpleframework.module.msg.IP2PMessageLogService;
import net.simpleframework.module.msg.IP2PMessageService;
import net.simpleframework.module.msg.P2PMessage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IP2PMessagePlugin extends IMessagePlugin {

	@Override
	IP2PMessageService getMessageService();

	/**
	 * 获取消息日志服务
	 * 
	 * @return
	 */
	IP2PMessageLogService getMessageLogService();

	P2PMessage sentMessage(ID toId, ID fromId, ID shopId, String topic, String content);

	/**
	 * 发送订阅消息
	 * 
	 * @param toId
	 * @param fromId
	 * @param shopId
	 * @param topic
	 * @param content
	 * @param category
	 * @return
	 */
	P2PMessage sentMessage(ID toId, ID fromId, ID shopId, String topic, String content,
			String category);
}
