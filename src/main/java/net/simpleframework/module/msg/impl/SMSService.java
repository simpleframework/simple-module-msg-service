package net.simpleframework.module.msg.impl;

import static net.simpleframework.common.I18n.$m;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.simpleframework.common.Convert;
import net.simpleframework.common.JsonUtils;
import net.simpleframework.common.StringUtils;
import net.simpleframework.ctx.service.AbstractBaseService;
import net.simpleframework.ctx.task.ExecutorRunnableEx;
import net.simpleframework.lib.org.jsoup.Jsoup;
import net.simpleframework.module.msg.IMessageContextAware;
import net.simpleframework.module.msg.ISMSService;
import net.simpleframework.module.msg.MessageContextSettings;
import net.simpleframework.module.msg.MessageException;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class SMSService extends AbstractBaseService implements ISMSService, IMessageContextAware {

	private final Map<String, IProvider> sCache = new HashMap<String, IProvider>();
	private final Map<String, Boolean> ASYNCs = new HashMap<String, Boolean>();

	private IProvider getSmsProvider(final String name) {
		IProvider provider = sCache.get(name);
		if (provider == null) {
			final Map<String, Object> settings = ((MessageContextSettings) getModuleContext()
					.getContextSettings()).getTagProps("mobile-sms", name);
			if (settings == null) {
				return null;
			}
			final String _provider = (String) settings.get("provider");
			if ("alidayu".equals(_provider)) {
				sCache.put(name, provider = new Alidayu(settings));
			}
			ASYNCs.put(name, Convert.toBool(settings.get("async")));
		}
		return provider;
	}

	@Override
	public void sentSMS(String sms, final String mobile, final String template,
			final Map<String, Object> vars) {
		if (!StringUtils.hasText(sms)) {
			sms = "default";
		}

		final IProvider provider = getSmsProvider(sms);
		if (provider != null) {
			if (ASYNCs.get(sms)) {
				getTaskExecutor().execute(new ExecutorRunnableEx("msg_sent_sms", $m("SMSService.1")) {
					@Override
					protected void task(final Map<String, Object> cache) throws Exception {
						provider.sent(mobile, template, vars);
					}
				});
			} else {
				provider.sent(mobile, template, vars);
			}
		} else {
			throw MessageException.of($m("SMSService.0", "mobile-sms:" + sms));
		}
	}

	@Override
	public void sentSMS(final String mobile, final String template, final Map<String, Object> vars) {
		sentSMS(null, mobile, template, vars);
	}

	class Alidayu implements IProvider {
		private final Map<String, Object> settings;

		Alidayu(final Map<String, Object> settings) {
			this.settings = settings;
		}

		@Override
		public void sent(final String mobile, final String template, final Map<String, Object> vars) {
			final String _url = (String) settings.get("url");
			final String _template = (String) settings.get("template." + template);
			final String _sign = (String) settings.get("template." + template + ".sign");
			try {
				final Map<String, ?> json = JsonUtils.toMap(Jsoup.connect(_url).ignoreContentType(true)
						.header("X-Ca-Key", (String) settings.get("app.key"))
						.header("X-Ca-Secret", (String) settings.get("app.secret"))
						.data("sms_template_code", _template).data("sms_free_sign_name", _sign)
						.data("rec_num", mobile).data("sms_param", JsonUtils.toJSON(vars)).execute()
						.body());
				System.out.println("SMS sent: " + json);
				if (!Convert.toBool(json.get("success"))) {
					throw MessageException.of((String) json.get("msg"));
				}
			} catch (final IOException e) {
				getLog().error(e);
			}
		}
	}

	interface IProvider {

		void sent(String mobile, String template, Map<String, Object> vars);
	}
}