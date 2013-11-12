package net.simpleframework.module.msg.plugin;

import net.simpleframework.common.ID;
import net.simpleframework.module.msg.ISubscribeMessageService;
import net.simpleframework.module.msg.SubscribeMessage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface ISubscribeMessagePlugin extends IMessagePlugin {

	@Override
	ISubscribeMessageService getMessageService();

	/**
	 * 发送订阅消息
	 * 
	 * @param fromId
	 * @param topic
	 * @param topicUrl
	 * @param content
	 * @param category
	 * @return
	 */
	SubscribeMessage sentMessage(ID fromId, String topic, String content, int category);
}
