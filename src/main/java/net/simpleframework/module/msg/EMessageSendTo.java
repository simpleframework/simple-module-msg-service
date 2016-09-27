package net.simpleframework.module.msg;

import static net.simpleframework.common.I18n.$m;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public enum EMessageSendTo {

	/* 邮件 */
	email {
		@Override
		public String toString() {
			return $m("EMessageSendTo.email");
		}
	},

	/* 短信 */
	mobile {
		@Override
		public String toString() {
			return $m("EMessageSendTo.mobile");
		}
	}
}
