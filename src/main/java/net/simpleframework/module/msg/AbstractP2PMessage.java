package net.simpleframework.module.msg;

import net.simpleframework.common.ID;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
@SuppressWarnings("serial")
public abstract class AbstractP2PMessage extends AbstractMessage {
	/* 接收人id */
	private ID userId;

	/* 接收人地址 */
	private String toUsers;

	public ID getUserId() {
		return userId;
	}

	public void setUserId(final ID userId) {
		this.userId = userId;
	}

	public String getToUsers() {
		return toUsers;
	}

	public void setToUsers(final String toUsers) {
		this.toUsers = toUsers;
	}
}
