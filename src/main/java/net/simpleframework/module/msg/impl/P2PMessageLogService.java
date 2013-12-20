package net.simpleframework.module.msg.impl;

import java.util.ArrayList;

import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.module.msg.EMessageSendTo;
import net.simpleframework.module.msg.IP2PMessageLogService;
import net.simpleframework.module.msg.P2PMessageLog;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class P2PMessageLogService extends AbstractMessageService<P2PMessageLog> implements
		IP2PMessageLogService {
	@Override
	public boolean isRead(final Object userId, final P2PMessageLog message) {
		return true;
	}

	@Override
	public void doRead(final Object userId, final P2PMessageLog message) {
	}

	@Override
	public void doUnRead(final Object userId, final P2PMessageLog message) {
	}

	@Override
	public int getUnreadMessageCount(final Object userId) {
		return 0;
	}

	@Override
	public IDataQuery<P2PMessageLog> queryMessages(final EMessageSendTo sendTo) {
		String expr = "1=1";
		final ArrayList<Object> al = new ArrayList<Object>();
		final int mark = getMark();
		if (mark != 0) {
			expr += " and messagemark=?";
			al.add(mark);
		}
		if (sendTo != null) {
			expr += " and messagesendto=?";
			al.add(sendTo);
		}
		return query(expr + " order by createDate desc", al.toArray());
	}
}
