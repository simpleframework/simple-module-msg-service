package net.simpleframework.module.msg;

import net.simpleframework.ado.bean.AbstractIdBean;
import net.simpleframework.common.ID;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class Subscriber extends AbstractIdBean {

	private ID userId;

	/* 通知源标识 */
	private int noticeMark;

	/* 是否停止订阅 */
	private boolean reject;

	/* 是否同时发送邮件 */
	private boolean mail;

	/* 邮件地址 */
	private String mailAddress;

	public ID getUserId() {
		return userId;
	}

	public void setUserId(final ID userId) {
		this.userId = userId;
	}

	public int getNoticeMark() {
		return noticeMark;
	}

	public void setNoticeMark(final int noticeMark) {
		this.noticeMark = noticeMark;
	}

	public boolean isReject() {
		return reject;
	}

	public void setReject(final boolean reject) {
		this.reject = reject;
	}

	public boolean isMail() {
		return mail;
	}

	public void setMail(final boolean mail) {
		this.mail = mail;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(final String mailAddress) {
		this.mailAddress = mailAddress;
	}

	private static final long serialVersionUID = 1542071155891419906L;
}
