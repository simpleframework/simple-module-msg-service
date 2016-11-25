package net.simpleframework.module.msg;

import java.util.Date;

import net.simpleframework.ado.bean.AbstractIdBean;
import net.simpleframework.common.ID;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class SubscribeMessageRead extends AbstractIdBean {

	/* 消息id */
	private ID messageId;

	/* 接收人id */
	private ID userId;

	/* 阅读时间 */
	private Date readDate;

	/* 删除时间 */
	private Date deleteDate;

	public ID getMessageId() {
		return messageId;
	}

	public void setMessageId(final ID messageId) {
		this.messageId = messageId;
	}

	public ID getUserId() {
		return userId;
	}

	public void setUserId(final ID userId) {
		this.userId = userId;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(final Date readDate) {
		this.readDate = readDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(final Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	private static final long serialVersionUID = -7017211305566124073L;
}
