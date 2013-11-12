package net.simpleframework.module.msg;

import net.simpleframework.common.mail.Email;
import net.simpleframework.ctx.service.IBaseService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IEmailService extends IBaseService {

	/**
	 * 
	 * @param smtp
	 * @param email
	 */
	void sentMail(final String smtp, final Email... emails);

	void sentMail(final Email... emails);
}
