package net.simpleframework.module.msg.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.common.coll.ArrayUtils;
import net.simpleframework.common.th.NotImplementedException;
import net.simpleframework.ctx.service.ado.db.AbstractDbBeanService;
import net.simpleframework.module.common.bean.CategoryStat;
import net.simpleframework.module.msg.AbstractMessage;
import net.simpleframework.module.msg.IMessageContextAware;
import net.simpleframework.module.msg.IMessageService;
import net.simpleframework.module.msg.P2PMessage;
import net.simpleframework.module.msg.SubscribeMessage;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class AbstractMessageService<T extends AbstractMessage> extends
		AbstractDbBeanService<T> implements IMessageService<T>, IMessageContextAware {

	/**
	 * 子类覆盖,模块标识
	 * 
	 * @return
	 */
	protected int getMark() {
		return 0;
	}

	@Override
	public IDataQuery<T> queryMessages() {
		String expr = "1=1";
		final ArrayList<Object> al = new ArrayList<Object>();
		final int mark = getMark();
		if (mark != 0) {
			expr += " and messagemark=?";
			al.add(mark);
		}
		return query(expr + " order by createDate desc", al.toArray());
	}

	@Override
	public void doAllRead(final Object userId) {
		final IDataQuery<T> dq = queryMessages(userId, false);
		T msg;
		while ((msg = dq.next()) != null) {
			doRead(userId, msg);
		}
	}

	@Override
	public int doDelete(final Object userId, final Object[] ids) {
		return delete(ids);
	}

	@Override
	public IDataQuery<T> queryMessages(final Object userId, final Boolean read) {
		return queryMessages(userId, read, null);
	}

	@Override
	public IDataQuery<T> queryMessages(final Object userId, final Boolean read, final String category) {
		throw NotImplementedException.of(getClass(),
				"queryMessages(Object userId, Boolean read, String category);");
	}

	@Override
	public IDataQuery<T> queryFromMessages(final Object userId, final Boolean read,
			final String category) {
		throw NotImplementedException.of(getClass(),
				"queryFromMessages(Object userId, Boolean read, String category);");
	}

	@Override
	public List<CategoryStat> queryCategoryItems(final Object userId) {
		final List<CategoryStat> l = new ArrayList<CategoryStat>();
		if (userId != null) {
			Object[] params = new Object[] { userId };
			String sql = "select category, count(*) as cc from "
					+ (this instanceof P2PMessageService ? getTablename(P2PMessage.class)
							: getTablename(SubscribeMessage.class)) + " where userId=?";
			final int mark = getMark();
			if (mark != 0) {
				sql += " and messagemark=?";
				params = ArrayUtils.add(params, mark);
			}
			sql += " and category is not null group by category";
			final IDataQuery<Map<String, Object>> dq = getQueryManager().query(sql, params);
			for (Map<String, Object> m; (m = dq.next()) != null;) {
				final String category = (String) m.get("category");
				l.add(new CategoryStat(category, m.get("cc")));
			}
		}
		return l;
	}
}
