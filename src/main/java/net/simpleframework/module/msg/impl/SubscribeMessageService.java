package net.simpleframework.module.msg.impl;

import java.util.ArrayList;
import java.util.Date;

import net.simpleframework.ado.IParamsValue;
import net.simpleframework.ado.bean.AbstractIdBean;
import net.simpleframework.ado.db.IDbEntityManager;
import net.simpleframework.ado.db.common.ExpressionValue;
import net.simpleframework.ado.db.common.SQLValue;
import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.common.ID;
import net.simpleframework.common.coll.ArrayUtils;
import net.simpleframework.module.msg.ISubscribeMessageService;
import net.simpleframework.module.msg.SubscribeMessage;
import net.simpleframework.module.msg.SubscribeMessageRead;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class SubscribeMessageService extends AbstractMessageService<SubscribeMessage> implements
		ISubscribeMessageService {

	private SubscribeMessageRead getMessageRead(final Object userId, final Object message) {
		return getEntityManager(SubscribeMessageRead.class)
				.queryForBean(
						new ExpressionValue("userId=? and messageId=?", userId,
								message instanceof AbstractIdBean ? ((AbstractIdBean) message).getId()
										: message));
	}

	@Override
	public boolean isRead(final Object userId, final SubscribeMessage message) {
		final SubscribeMessageRead read = getMessageRead(userId, message);
		return read != null && read.getReadDate() != null;
	}

	@Override
	public void doRead(final Object userId, final SubscribeMessage message) {
		SubscribeMessageRead read = getMessageRead(userId, message);
		if (read == null) {
			read = new SubscribeMessageRead();
			read.setMessageId(message.getId());
			read.setUserId(ID.of(userId));
			read.setReadDate(new Date());
			getEntityManager(SubscribeMessageRead.class).insert(read);
		} else {
			read.setReadDate(new Date());
			getEntityManager(SubscribeMessageRead.class).update(new String[] { "readdate" }, read);
		}
	}

	@Override
	public void doUnRead(final Object userId, final SubscribeMessage message) {
		final SubscribeMessageRead read = getMessageRead(userId, message);
		if (read != null) {
			read.setReadDate(null);
			getEntityManager(SubscribeMessageRead.class).update(new String[] { "readdate" }, read);
		}
	}

	@Override
	public int doDelete(final Object userId, final Object[] ids) {
		int c = 0;
		final IDbEntityManager<SubscribeMessageRead> service = getEntityManager(SubscribeMessageRead.class);
		for (final Object id : ids) {
			SubscribeMessageRead read = getMessageRead(userId, id);
			if (read != null) {
				read.setDeleteDate(new Date());
				c += service.update(new String[] { "deletedate" }, read);
			} else {
				read = new SubscribeMessageRead();
				read.setMessageId(ID.of(id));
				read.setUserId(ID.of(userId));
				read.setDeleteDate(new Date());
				service.insert(read);
			}
		}
		return c;
	}

	private static String JOIN_SQL = "select a.* from " + SubscribeMessage.TBL.getName()
			+ " a left join " + SubscribeMessageRead.TBL.getName()
			+ " b on (a.id=b.messageid and b.userid=?) where ";

	@Override
	public int getUnreadMessageCount(final Object userId) {
		String sql = JOIN_SQL;
		final ArrayList<Object> al = new ArrayList<Object>();
		al.add(userId);
		final int mark = getMark();
		if (mark != 0) {
			sql += "a.messagemark=? and ";
			al.add(mark);
		}
		sql += "b.readdate is null and b.deletedate is null";
		return getEntityManager().count(new SQLValue(sql, al.toArray()));
	}

	@Override
	public IDataQuery<SubscribeMessage> queryMessages(final Object userId, final Boolean read,
			final int category) {
		String sql = JOIN_SQL + "a.messagemark=? and b.deletedate is null";
		Object[] params = new Object[] { userId, getMark() };
		if (read != null) {
			if (read) {
				sql += " and b.readdate is not null";
			} else {
				sql += " and b.readdate is null";
			}
		}
		if (category != 0) {
			sql += " and a.category=?";
			params = ArrayUtils.add(params, category);
		}
		return getEntityManager().queryBeans(
				new SQLValue(sql + " order by a.createDate desc", params));
	}

	@Override
	public void onInit() throws Exception {
		addListener(new DbEntityAdapterEx() {
			@Override
			public void onBeforeDelete(final IDbEntityManager<?> manager,
					final IParamsValue paramsValue) {
				super.onBeforeDelete(manager, paramsValue);
				final IDbEntityManager<SubscribeMessageRead> service = getEntityManager(SubscribeMessageRead.class);
				for (final SubscribeMessage message : coll(paramsValue)) {
					service.delete(new ExpressionValue("messageid=?", message.getId()));
				}
			}
		});
	}
}
