package net.simpleframework.module.msg.plugin;

import static net.simpleframework.common.I18n.$m;

import java.util.Date;
import java.util.Map;

import net.simpleframework.common.ID;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.mail.Email;
import net.simpleframework.common.web.html.HtmlUtils;
import net.simpleframework.ctx.permission.PermissionFactory;
import net.simpleframework.module.msg.EMessageSendTo;
import net.simpleframework.module.msg.IMessageContext;
import net.simpleframework.module.msg.IP2PMessageService;
import net.simpleframework.module.msg.P2PMessage;
import net.simpleframework.module.msg.P2PMessageLog;
import net.simpleframework.module.msg.impl.P2PMessageService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class NoticeMessagePlugin extends AbstractP2PMessagePlugin {

	@Override
	public IP2PMessageService getMessageService() {
		return singleton(NoticeMessageService.class);
	}

	public static class NoticeMessageService extends P2PMessageService {
		@Override
		protected int getMark() {
			return IMessageContext.NOTICEMESSAGE_MARK;
		}
	}

	@Override
	public int getMark() {
		return IMessageContext.NOTICEMESSAGE_MARK;
	}

	@Override
	public String getText() {
		return $m("NoticeMessagePlugin.0");
	}

	public P2PMessage sentMessage(final ID toId, final ID fromId,
			final NoticeMessageCategoryPlugin mCategory, final Map<String, Object> variables) {
		final String topic = mCategory.getTopic(variables);
		final String content = mCategory.getContent(variables);
		P2PMessage msg = null;
		if (mCategory.isSendTo_normal()) {
			msg = sentMessage(toId, fromId, topic, content, mCategory.getMark());
		}

		// email
		String email;
		if (mCategory.isSendTo_email()
				&& StringUtils.hasText(email = PermissionFactory.get().getUser(toId).getEmail())) {
			context.getEmailService()
					.sentMail(
							Email.create().to(email).subject(topic)
									.addHtml(HtmlUtils.convertHtmlLines(content)));
			// 插入发送日志
			final P2PMessageLog log = new P2PMessageLog();
			log.setUserId(toId);
			log.setFromId(fromId);
			log.setCreateDate(new Date());
			log.setMessageMark(getMark());
			log.setTopic(topic);
			log.setContent(content);
			log.setCategory(mCategory.getMark());
			log.setMessageSendTo(EMessageSendTo.email);
			log.setToUsers(email);
			getMessageLogService().insert(log);
		}
		return msg;
	}

	public P2PMessage sentMessage(final ID toId, final NoticeMessageCategoryPlugin mCategory,
			final Map<String, Object> variables) {
		return sentMessage(toId, null, mCategory, variables);
	}
}
