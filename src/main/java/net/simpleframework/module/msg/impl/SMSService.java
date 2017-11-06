package net.simpleframework.module.msg.impl;

import static net.simpleframework.common.I18n.$m;

import java.util.HashMap;
import java.util.Map;

import net.simpleframework.common.Convert;
import net.simpleframework.common.StringUtils;
import net.simpleframework.ctx.service.AbstractBaseService;
import net.simpleframework.ctx.task.ExecutorRunnableEx;
import net.simpleframework.module.msg.IMessageContextAware;
import net.simpleframework.module.msg.ISMSService;
import net.simpleframework.module.msg.MessageException;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class SMSService extends AbstractBaseService implements ISMSService, IMessageContextAware {

	private final Map<String, IProvider> providers = new HashMap<>();

	protected void registProvider(final String name, final IProvider provider) {
		providers.put(name, provider);
	}

	@Override
	public void sentSMS(String sms, final String mobile, final String template,
			final Map<String, Object> vars) {
		if (!StringUtils.hasText(sms)) {
			sms = "default";
		}

		final IProvider provider = providers.get(sms);
		if (provider == null) {
			throw MessageException.of($m("SMSService.0", "mobile-sms:" + sms));
		}

		if (Convert.toBool(provider.prop("async"))) {
			getTaskExecutor().execute(new ExecutorRunnableEx("msg_sent_sms", $m("SMSService.1")) {
				@Override
				protected boolean isRun(final Map<String, Object> cache) throws Exception {
					return true;
				}

				@Override
				protected void task(final Map<String, Object> cache) throws Exception {
					provider.sent(mobile, template, vars);
				}
			});
		} else {
			provider.sent(mobile, template, vars);
		}
	}

	@Override
	public void sentSMS(final String mobile, final String template, final Map<String, Object> vars) {
		sentSMS(null, mobile, template, vars);
	}

	protected static interface IProvider {

		String prop(String key);

		void sent(String mobile, String template, Map<String, Object> vars);
	}
}