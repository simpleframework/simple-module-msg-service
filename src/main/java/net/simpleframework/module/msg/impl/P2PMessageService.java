package net.simpleframework.module.msg.impl;

import java.util.ArrayList;
import java.util.Date;

import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.module.msg.EMessageStatus;
import net.simpleframework.module.msg.IP2PMessageService;
import net.simpleframework.module.msg.P2PMessage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class P2PMessageService extends AbstractMessageService<P2PMessage> implements
		IP2PMessageService {

	@Override
	public boolean isRead(final Object userId, final P2PMessage message) {
		return message.getReadDate() != null;
	}

	@Override
	public void doRead(final Object userId, final P2PMessage message) {
		message.setReadDate(new Date());
		update(new String[] { "readdate" }, message);
	}

	@Override
	public void doUnRead(final Object userId, final P2PMessage message) {
		if (message.getReadDate() == null) {
			return;
		}
		message.setReadDate(null);
		update(new String[] { "readdate" }, message);
	}

	@Override
	public int getUnreadMessageCount(final Object userId) {
		String expr = "userid=?";
		final ArrayList<Object> al = new ArrayList<Object>();
		al.add(userId);
		final int mark = getMark();
		if (mark != 0) {
			expr += " and messagemark=?";
			al.add(mark);
		}
		expr += " and readdate is null";
		return count(expr, al.toArray());
	}

	@Override
	public IDataQuery<P2PMessage> queryMessages(final Object userId, final Boolean read,
			final int category) {
		String expr = "userid=? and messagestatus=?";
		final ArrayList<Object> al = new ArrayList<Object>();
		al.add(userId);
		al.add(EMessageStatus.S_VIEW);
		final int mark = getMark();
		if (mark != 0) {
			expr += " and messagemark=?";
			al.add(mark);
		}
		if (read != null) {
			if (read) {
				expr += " and readdate is not null";
			} else {
				expr += " and readdate is null";
			}
		}
		if (category != 0) {
			expr += " and category=?";
			al.add(category);
		}
		return query(expr + " order by createDate desc", al.toArray());
	}

	@Override
	public IDataQuery<P2PMessage> querySentMessages(final int mark, final Object userId) {
		String expr = "fromId=? and messagestatus=?";
		final ArrayList<Object> al = new ArrayList<Object>();
		al.add(userId);
		al.add(EMessageStatus.S_COPY);
		if (mark != 0) {
			expr += " and messagemark=?";
			al.add(mark);
		}
		return query(expr + " order by createDate desc", al.toArray());
	}

	@Override
	public IDataQuery<P2PMessage> queryDraftMessages(final int mark, final Object userId) {
		String expr = "fromId=? and messagestatus=?";
		final ArrayList<Object> al = new ArrayList<Object>();
		al.add(userId);
		al.add(EMessageStatus.S_EDIT);
		if (mark != 0) {
			expr += " and messagemark=?";
			al.add(mark);
		}
		return query(expr + " order by createDate desc", al.toArray());
	}
}
