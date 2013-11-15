package net.simpleframework.module.msg;

import net.simpleframework.ado.query.IDataQuery;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IP2PMessageService extends IMessageService<P2PMessage> {

	/**
	 * 获取指定用户已发送的消息
	 * 
	 * @param mark
	 * @param userId
	 * @return
	 */
	IDataQuery<P2PMessage> querySentMessages(int mark, Object userId);

	/**
	 * 获取指定用户草稿箱中的消息
	 * 
	 * @param mark
	 * @param userId
	 * @return
	 */
	IDataQuery<P2PMessage> queryDraftMessages(int mark, Object userId);
}