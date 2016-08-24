package net.simpleframework.module.msg;

import net.simpleframework.ctx.IModuleContextAware;
import net.simpleframework.ctx.ModuleContextFactory;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IMessageContextAware extends IModuleContextAware {

	static IMessageContext messageContext = ModuleContextFactory.get(IMessageContext.class);

	/* 短信服务 */
	static ISMSService _smsService = messageContext.getSMSService();
}
