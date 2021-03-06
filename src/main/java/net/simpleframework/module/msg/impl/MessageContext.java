package net.simpleframework.module.msg.impl;

import static net.simpleframework.common.I18n.$m;

import net.simpleframework.ado.db.DbEntityTable;
import net.simpleframework.ctx.IApplicationContext;
import net.simpleframework.ctx.Module;
import net.simpleframework.module.common.AbstractCommonModuleContext;
import net.simpleframework.module.msg.IEmailService;
import net.simpleframework.module.msg.IMessageContext;
import net.simpleframework.module.msg.IP2PMessageLogService;
import net.simpleframework.module.msg.IP2PMessageService;
import net.simpleframework.module.msg.ISMSService;
import net.simpleframework.module.msg.ISubscribeMessageService;
import net.simpleframework.module.msg.MessageContextSettings;
import net.simpleframework.module.msg.P2PMessage;
import net.simpleframework.module.msg.P2PMessageLog;
import net.simpleframework.module.msg.SubscribeMessage;
import net.simpleframework.module.msg.SubscribeMessageRead;
import net.simpleframework.module.msg.plugin.NoticeMessagePlugin;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class MessageContext extends AbstractCommonModuleContext
		implements IMessageContext {

	@Override
	public void onInit(final IApplicationContext application) throws Exception {
		super.onInit(application);

		getPluginRegistry().registPlugin(NoticeMessagePlugin.class);
	}

	@Override
	protected DbEntityTable[] createEntityTables() {
		return new DbEntityTable[] { new DbEntityTable(P2PMessage.class, "sf_msg_p2p"),
				new DbEntityTable(SubscribeMessage.class, "sf_msg_subscribe"),
				new DbEntityTable(SubscribeMessageRead.class, "sf_msg_subscribe_read"),
				new DbEntityTable(P2PMessageLog.class, "sf_msg_p2plog") };
	}

	@Override
	protected Module createModule() {
		return super.createModule().setName(MODULE_NAME).setText($m("MessageContext.0")).setOrder(2);
	}

	@Override
	public abstract MessageContextSettings getContextSettings();

	@Override
	public IP2PMessageService getP2PMessageService() {
		return singleton(P2PMessageService.class);
	}

	@Override
	public ISubscribeMessageService getSubscribeMessageService() {
		return singleton(SubscribeMessageService.class);
	}

	@Override
	public IP2PMessageLogService getP2PMessageLogService() {
		return singleton(P2PMessageLogService.class);
	}

	@Override
	public IEmailService getEmailService() {
		return singleton(EmailService.class);
	}

	@Override
	public ISMSService getSMSService() {
		return singleton(SMSService.class);
	}

	@Override
	public MessagePluginRegistry getPluginRegistry() {
		return singleton(MessagePluginRegistry.class);
	}

	@Override
	public NoticeMessagePlugin getNoticeMessagePlugin() {
		return (NoticeMessagePlugin) getPluginRegistry().getPlugin(NOTICEMESSAGE_MARK);
	}
}
