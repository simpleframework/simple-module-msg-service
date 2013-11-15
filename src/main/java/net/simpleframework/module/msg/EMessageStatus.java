package net.simpleframework.module.msg;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public enum EMessageStatus {

	/**
	 * 正常可以查看的状态
	 */
	S_VIEW,

	/**
	 * 消息正在编辑状态
	 */
	S_EDIT,

	/**
	 * 消息是保留的拷贝
	 */
	S_COPY
}
