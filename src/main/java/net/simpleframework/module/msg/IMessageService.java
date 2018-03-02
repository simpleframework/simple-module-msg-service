package net.simpleframework.module.msg;

import java.util.List;

import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.common.ID;
import net.simpleframework.ctx.service.ado.db.IDbBeanService;
import net.simpleframework.module.common.bean.CategoryStat;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IMessageService<T extends AbstractMessage> extends IDbBeanService<T> {

	/**
	 * 获取未读数量
	 * 
	 * @param userId
	 * @return
	 */
	int getUnreadMessageCount(Object userId);

	/**
	 * 判断消息是否已读
	 * 
	 * @param userId
	 * @param message
	 * @return
	 */
	boolean isRead(Object userId, T message);

	/**
	 * 标记已读
	 * 
	 * @param userId
	 * @param message
	 */
	void doRead(Object userId, T message);

	/**
	 * 标记未读
	 * 
	 * @param userId
	 * @param message
	 */
	void doUnRead(Object userId, T message);

	/**
	 * 标记指定用户所有的消息为已读
	 * 
	 * @param userId
	 * @param userId
	 */
	void doAllRead(Object userId, ID shopId);

	/**
	 * 删除消息
	 * 
	 * @param userId
	 * @param ids
	 * @return
	 */
	int doDelete(Object userId, Object[] ids);

	IDataQuery<T> queryMessages(Object userId, ID shopId, Boolean read);

	/**
	 * 查询接收者的消息
	 * 
	 * @param userId
	 * @param shopId
	 * @param read
	 *        null 全部; true 已读; false 未读
	 * @param category
	 * @return
	 */
	IDataQuery<T> queryMessages(Object userId, ID shopId, Boolean read, String category);

	/**
	 * 查询发送者的消息
	 * 
	 * @param userId
	 * @param read
	 * @param category
	 * @return
	 */
	IDataQuery<T> queryFromMessages(Object userId, Boolean read, String category);

	/**
	 * 按mark查询消息
	 * 
	 * @return
	 */
	IDataQuery<T> queryMessages();

	List<CategoryStat> queryCategoryItems(Object userId);
}
