package net.simpleframework.module.msg.plugin;

import java.util.Map;

import net.simpleframework.common.Convert;
import net.simpleframework.common.ID;
import net.simpleframework.ctx.script.MVEL2Template;
import net.simpleframework.module.common.plugin.AbstractModulePlugin;
import net.simpleframework.module.msg.IMessageContextAware;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class NoticeMessageCategoryPlugin extends AbstractModulePlugin implements
		IMessageCategoryPlugin, IMessageContextAware {

	private final String defaultTopic;

	private final String defaultContent;

	/* 分组 */
	private String groupText;

	private boolean sendTo_normal = true;

	private boolean sendTo_email;

	private boolean sendTo_mobile;

	public NoticeMessageCategoryPlugin(final int mark, final String text, final String defaultTopic,
			final String defaultContent) {
		setMark(mark).setText(text);
		this.defaultTopic = defaultTopic;
		this.defaultContent = defaultContent;
	}

	public String getGroupText() {
		return groupText;
	}

	public NoticeMessageCategoryPlugin setGroupText(final String groupText) {
		this.groupText = groupText;
		return this;
	}

	private Map<String, Object> getProps() {
		return context.getContextSettings()
				.getNoticeMessageCategoryProps(Convert.toString(getMark()));
	}

	public boolean isSendTo_normal() {
		final Map<String, Object> props = getProps();
		if (props != null) {
			return Convert.toBool(props.get("sendto-normal"), true);
		}
		return sendTo_normal;
	}

	public NoticeMessageCategoryPlugin setSendTo_normal(final boolean sendTo_normal) {
		this.sendTo_normal = sendTo_normal;
		return this;
	}

	public boolean isSendTo_email() {
		final Map<String, Object> props = getProps();
		if (props != null) {
			return Convert.toBool(props.get("sendto-email"));
		}
		return sendTo_email;
	}

	public NoticeMessageCategoryPlugin setSendTo_email(final boolean sendTo_email) {
		this.sendTo_email = sendTo_email;
		return this;
	}

	public boolean isSendTo_mobile() {
		final Map<String, Object> props = getProps();
		if (props != null) {
			return Convert.toBool(props.get("sendto-mobile"));
		}
		return sendTo_mobile;
	}

	public NoticeMessageCategoryPlugin setSendTo_mobile(final boolean sendTo_mobile) {
		this.sendTo_mobile = sendTo_mobile;
		return this;
	}

	@Override
	public Object getFrom(final ID fromId) {
		return null;
	}

	public String getTopic(final Map<String, Object> variables) {
		final Map<String, Object> props = getProps();
		String topic = null;
		if (props != null) {
			topic = (String) props.get("topic");
		}
		if (topic == null) {
			topic = defaultTopic;
		}
		return variables == null ? topic : MVEL2Template.replace(variables, topic);
	}

	public String getContent(final Map<String, Object> variables) {
		final Map<String, Object> props = getProps();
		String content = null;
		if (props != null) {
			content = (String) props.get("content");
		}
		if (content == null) {
			content = defaultContent;
		}
		return variables == null ? content : MVEL2Template.replace(variables, content);
	}
}
