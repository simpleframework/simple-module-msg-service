package net.simpleframework.module.msg;

import net.simpleframework.common.th.RuntimeExceptionEx;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class MessageException extends RuntimeExceptionEx {

	public MessageException(final String msg, final Throwable cause) {
		super(msg, cause);
	}

	public static MessageException of(final String msg) {
		return _of(MessageException.class, msg);
	}

	public static RuntimeException of(final Throwable throwable) {
		return _of(MessageException.class, null, throwable);
	}

	private static final long serialVersionUID = 8964555866578533829L;
}
