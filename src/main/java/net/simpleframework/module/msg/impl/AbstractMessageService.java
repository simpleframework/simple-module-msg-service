package net.simpleframework.module.msg.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.simpleframework.ado.db.common.SQLValue;
import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.common.Convert;
import net.simpleframework.common.coll.ArrayUtils;
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
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
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
		final IDataQuery<T> dq = queryMessages(userId, false, 0);
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
	public List<CategoryStat> queryCategoryItems(final Object userId) {
		final List<CategoryStat> l = new ArrayList<CategoryStat>();
		if (userId != null) {
			Object[] params = new Object[] { userId };
			String sql = "select category, count(*) as cc from "
					+ (this instanceof P2PMessageService ? P2PMessage.TBL.getName()
							: SubscribeMessage.TBL.getName()) + " where userId=?";
			final int mark = getMark();
			if (mark != 0) {
				sql += " and messagemark=?";
				params = ArrayUtils.add(params, mark);
			}
			sql += " group by category";
			final IDataQuery<Map<String, Object>> dq = getQueryManager().query(
					new SQLValue(sql, params));
			for (Map<String, Object> m; (m = dq.next()) != null;) {
				final int category = Convert.toInt(m.get("category"));
				if (category == 0) {
					continue;
				}
				l.add(new CategoryStat(category, m.get("cc")));
			}
		}
		return l;
	}
}
