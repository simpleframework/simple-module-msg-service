package net.simpleframework.module.msg.plugin;

import java.util.Date;

import net.simpleframework.common.ID;
import net.simpleframework.module.msg.SubscribeMessage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class AbstractSubscribeMessagePlugin extends AbstractMessagePlugin implements
		ISubscribeMessagePlugin {

	@Override
	public SubscribeMessage sentMessage(final ID fromId, final String topic, final String content,
			final int category) {
		final SubscribeMessage msg = new SubscribeMessage();
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
