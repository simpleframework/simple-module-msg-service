package net.simpleframework.module.msg;

import net.simpleframework.ado.bean.AbstractDateAwareBean;
import net.simpleframework.ado.bean.IDomainBeanAware;
import net.simpleframework.common.ID;
import net.simpleframework.common.StringUtils;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
@SuppressWarnings("serial")
public abstract class AbstractMessage extends AbstractDateAwareBean implements IDomainBeanAware {
	/* 域id */
	private ID domainId;

	/* 通知源标识 */
	private int messageMark;

	/* 关联的对象Id,含义由具体实现模块定义 */
	private ID fromId;

	/* 分类 */
	private String category;

	/* 标题 */
	private String topic;

	/* 正文内容 */
	private String content;

	@Override
	public ID getDomainId() {
		return domainId;
	}

	@Override
	public void setDomainId(final ID domainId) {
		this.domainId = domainId;
	}

	public int getMessageMark() {
		return messageMark;
	}

	public void setMessageMark(final int messageMark) {
		this.messageMark = messageMark;
	}

	public ID getFromId() {
		return fromId;
	}

	public void setFromId(final ID fromId) {
		this.fromId = fromId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(final String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return StringUtils.blank(content);
	}

	public void setContent(final String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return getTopic();
	}
}
