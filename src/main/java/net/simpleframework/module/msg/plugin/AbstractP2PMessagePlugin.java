package net.simpleframework.module.msg.plugin;

import java.util.Date;

import net.simpleframework.common.ID;
import net.simpleframework.module.msg.IP2PMessageLogService;
import net.simpleframework.module.msg.P2PMessage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class AbstractP2PMessagePlugin extends AbstractMessagePlugin
		implements IP2PMessagePlugin {

	@Override
	public IP2PMessageLogService getMessageLogService() {
		return messageContext.getP2PMessageLogService();
	}

	@Override
	public Object getFrom(final ID fromId) {
		return messageContext.getPermission().getUser(fromId);
	}

	@Override
	public P2PMessage sentMessage(final ID toId, final ID fromId, final ID shopId,
			final String topic, final String content) {
		return sentMessage(toId, fromId, shopId, topic, content, null);
	}

	@Override
	public P2PMessage sentMessage(final ID toId, final ID fromId, final ID shopId,
			final String topic, final String content, final String category) {
		final P2PMessage msg = new P2PMessage();
		msg.setUserId(toId);
		msg.setCreateDate(new Date());
		msg.setMessageMark(getMark());
		msg.setFromId(fromId);
		msg.setShopId(shopId);
		msg.setTopic(topic);
		msg.setContent(content);
		msg.setCategory(category);
		getMessageService().insert(msg);
		return msg;
	}
}
