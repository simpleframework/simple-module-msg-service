package net.simpleframework.module.msg;

import net.simpleframework.ado.db.DbEntityTable;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
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

	public static final DbEntityTable TBL = new DbEntityTable(P2PMessageLog.class, "sf_msg_p2plog");

	private static final long serialVersionUID = -2346927508684737354L;
}