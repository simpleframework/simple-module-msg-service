package net.simpleframework.module.msg;

import java.util.Map;

import net.simpleframework.ctx.service.IBaseService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface ISMSService extends IBaseService {

	/**
	 * 短信发送
	 * 
	 * @param sms
	 * @param mobile
	 *        电话
	 * @param template
	 *        模板
	 * @param vars
	 *        模板变量
	 */
	void sentSMS(String sms, String mobile, String template, Map<String, Object> vars);

	void sentSMS(String mobile, String template, Map<String, Object> vars);
}
