package net.simpleframework.module.msg;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class P2PMessageLog extends AbstractP2PMessage {

	/* 消息源 */
	private EMessageSendTo messageSendTo;

	public EMessageSendTo getMessageSendTo() {
		return messageSendTo == null ? EMessageSendTo.email : messageSendTo;
	}

	public void setMessageSendTo(final EMessageSendTo messageSendTo) {
		this.messageSendTo = messageSendTo;
	}

	private static final long serialVersionUID = -2346927508684737354L;
}