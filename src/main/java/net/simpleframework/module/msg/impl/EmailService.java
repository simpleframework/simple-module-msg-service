package net.simpleframework.module.msg.impl;

import java.util.HashMap;
import java.util.Map;

import net.simpleframework.common.Convert;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.mail.Email;
import net.simpleframework.common.mail.SendMailSession;
import net.simpleframework.common.mail.SmtpServer;
import net.simpleframework.common.mail.SmtpSslServer;
import net.simpleframework.ctx.service.AbstractBaseService;
import net.simpleframework.module.msg.IEmailService;
import net.simpleframework.module.msg.IMessageContextAware;
import net.simpleframework.module.msg.MessageContextSettings;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class EmailService extends AbstractBaseService implements IEmailService,
		IMessageContextAware {

	private final Map<String, SmtpServer> sCache = new HashMap<String, SmtpServer>();

	private SmtpServer getSmtpServer(final String name) {
		SmtpServer server = sCache.get(name);
		if (server == null) {
			final Map<String, Object> settings = ((MessageContextSettings) getModuleContext()
					.getContextSettings()).getSMTPProps(name);
			if (settings == null) {
				return null;
			}
			final String host = (String) settings.get("host");
			final int port = Convert.toInt(settings.get("port"), 25);
			final String username = (String) settings.get("username");
			if (StringUtils.hasText(username)) {
				final boolean ssl = Convert.toBool(settings.get("ssl"));
				final String password = (String) settings.get("password");
				server = ssl ? new SmtpSslServer(host, port, username, password) : new SmtpServer(host,
						port, username, password);
			} else {
				server = new SmtpServer(host, port);
			}
			server.setFrom((String) settings.get("from"));
			sCache.put(name, server);
		}
		return server;
	}

	@Override
	public void sentMail(String smtp, final Email... emails) {
		if (!StringUtils.hasText(smtp)) {
			smtp = "default";
		}
		final SmtpServer server = getSmtpServer(smtp);
		if (server == null) {
			return;
		}
		final SendMailSession session = server.createSession();
		try {
			session.open();
			final boolean auth = server.isAuth();
			for (final Email email : emails) {
				if (auth) {
					email.setFrom(server.getFrom());
				}
				session.sendMail(email);
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void sentMail(final Email... emails) {
		sentMail(null, emails);
	}
}