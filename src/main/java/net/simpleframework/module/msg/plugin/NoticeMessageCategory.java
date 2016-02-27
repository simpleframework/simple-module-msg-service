package net.simpleframework.module.msg.plugin;

import java.util.Map;

import net.simpleframework.common.Convert;
import net.simpleframework.common.ID;
import net.simpleframework.ctx.script.MVEL2Template;
import net.simpleframework.module.msg.IMessageContextAware;
import net.simpleframework.module.msg.plugin.IMessageCategory.AbstractMessageCategory;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class NoticeMessageCategory extends AbstractMessageCategory implements IMessageContextAware {

	private final String defaultTopic;

	private final String defaultContent;

	/* 分组 */
	private String groupText;

	private boolean sendTo_normal = true;

	private boolean sendTo_email;

	private boolean sendTo_mobile;

	public NoticeMessageCategory(final String name, final String text, final String defaultTopic,
			final String defaultContent) {
		setName(name);
		_text = text;
		this.defaultTopic = defaultTopic;
		this.defaultContent = defaultContent;
	}

	public String getGroupText() {
		return groupText;
	}

	public NoticeMessageCategory setGroupText(final String groupText) {
		this.groupText = groupText;
		return this;
	}

	public boolean isSendTo_normal() {
		final String val = getProp("sendto-normal");
		if (val != null) {
			return Convert.toBool(val);
		}
		return sendTo_normal;
	}

	public NoticeMessageCategory setSendTo_normal(final boolean sendTo_normal) {
		this.sendTo_normal = sendTo_normal;
		return this;
	}

	public boolean isSendTo_email() {
		final String val = getProp("sendto-email");
		if (val != null) {
			return Convert.toBool(val);
		}
		return sendTo_email;
	}

	public NoticeMessageCategory setSendTo_email(final boolean sendTo_email) {
		this.sendTo_email = sendTo_email;
		return this;
	}

	public boolean isSendTo_mobile() {
		final String val = getProp("sendto-mobile");
		if (val != null) {
			return Convert.toBool(val);
		}
		return sendTo_mobile;
	}

	public NoticeMessageCategory setSendTo_mobile(final boolean sendTo_mobile) {
		this.sendTo_mobile = sendTo_mobile;
		return this;
	}

	@Override
	public Object getFrom(final ID fromId) {
		return null;
	}

	public String getTopic(final Map<String, Object> variables) {
		String topic = getProp("topic");
		if (topic == null) {
			topic = defaultTopic;
		}
		return variables == null ? topic : MVEL2Template.replace(variables, topic);
	}

	public String getContent(final Map<String, Object> variables) {
		String content = getProp("content");
		if (content == null) {
			content = defaultContent;
		}
		return variables == null ? content : MVEL2Template.replace(variables, content);
	}

	private String getProp(final String key) {
		final Map<String, Object> props = messageContext.getContextSettings()
				.getNoticeMessageCategoryProps(getName());
		if (props != null && props.containsKey(key)) {
			return (String) props.get(key);
		}
		return null;
	}
}
