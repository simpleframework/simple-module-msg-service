package net.simpleframework.module.msg;

import net.simpleframework.common.mail.Email;
import net.simpleframework.ctx.service.IBaseService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IEmailService extends IBaseService {

	/**
	 * 发送邮件
	 * 
	 * @param smtp
	 * @param emails
	 */
	void sentMail(String smtp, Email... emails);

	void sentMail(Email... emails);
}
