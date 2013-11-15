package net.simpleframework.module.msg;

import net.simpleframework.ctx.AbstractModuleRef;
import net.simpleframework.ctx.ModuleContextFactory;
import net.simpleframework.module.msg.plugin.NoticeMessagePlugin;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class MessageRef extends AbstractModuleRef {

	@Override
	public IMessageContext getModuleContext() {
		return ModuleContextFactory.get(IMessageContext.class);
	}

	public NoticeMessagePlugin getNoticeMessagePlugin() {
		return getModuleContext().getNoticeMessagePlugin();
	}

	public IEmailService getEmailService() {
		return getModuleContext().getEmailService();
	}
}