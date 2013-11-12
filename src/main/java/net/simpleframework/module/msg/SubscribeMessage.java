package net.simpleframework.module.msg;

import net.simpleframework.ado.db.DbEntityTable;
import net.simpleframework.ado.db.common.EntityInterceptor;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
@EntityInterceptor(listenerTypes = { "net.simpleframework.module.log.EntityDeleteLogAdapter" })
public class SubscribeMessage extends AbstractMessage {

	public static final DbEntityTable TBL = new DbEntityTable(SubscribeMessage.class,
			"sf_msg_subscribe");

	private static final long serialVersionUID = 1657141244991016933L;
}
