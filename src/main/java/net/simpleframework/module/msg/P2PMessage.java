package net.simpleframework.module.msg;

import java.util.Date;

import net.simpleframework.ado.db.DbEntityTable;
import net.simpleframework.ado.db.common.EntityInterceptor;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
@EntityInterceptor(listenerTypes = { "net.simpleframework.module.log.EntityDeleteLogAdapter" })
public class P2PMessage extends AbstractP2PMessage {

	/* 阅读时间 */
	private Date readDate;

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(final Date readDate) {
		this.readDate = readDate;
	}

	public static final DbEntityTable TBL = new DbEntityTable(P2PMessage.class, "sf_msg_p2p");

	private static final long serialVersionUID = -7886025628200387796L;
}
