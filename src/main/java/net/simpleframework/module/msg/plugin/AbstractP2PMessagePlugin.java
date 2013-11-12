package net.simpleframework.module.msg.plugin;

import java.util.Date;

import net.simpleframework.common.ID;
import net.simpleframework.ctx.permission.PermissionFactory;
import net.simpleframework.module.msg.IP2PMessageLogService;
import net.simpleframework.module.msg.P2PMessage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class AbstractP2PMessagePlugin extends AbstractMessagePlugin implements
		IP2PMessagePlugin {

	@Override
	public IP2PMessageLogService getMessageLogService() {
		return context.getP2PMessageLogService();
	}

	@Override
	public Object getFrom(final ID fromId) {
		return PermissionFactory.get().getUser(fromId);
	}

	@Override
	public P2PMessage sentMessage(final ID toId, final ID fromId, final String topic,
			final String content) {
		return sentMessage(toId, fromId, topic, content, 0);
	}

	@Override
	public P2PMessage sentMessage(final ID toId, final ID fromId, final String topic,
			final String content, final int category) {
		final P2PMessage msg = new P2PMessage();
		msg.setUserId(toId);
		msg.setCreateDate(new Date());
		msg.setMessageMark(getMark());
		msg.setFromId(fromId);
		msg.setTopic(topic);
		msg.setContent(content);
		msg.setCategory(category);
		getMessageService().insert(msg);
		return msg;
	}
}
