package net.simpleframework.module.msg;

import net.simpleframework.ado.query.IDataQuery;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IP2PMessageLogService extends IMessageService<P2PMessageLog> {

	/**
	 * 
	 * @param sendTo
	 * @return
	 */
	IDataQuery<P2PMessageLog> queryMessages(EMessageSendTo sendTo);
}
