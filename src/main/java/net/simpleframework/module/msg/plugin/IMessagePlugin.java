package net.simpleframework.module.msg.plugin;

import java.util.Collection;

import net.simpleframework.common.ID;
import net.simpleframework.module.common.plugin.IModulePlugin;
import net.simpleframework.module.msg.AbstractMessage;
import net.simpleframework.module.msg.IMessageService;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IMessagePlugin extends IModulePlugin {

	/**
	 * 获取消息服务
	 * 
	 * @return
	 */
	IMessageService<? extends AbstractMessage> getMessageService();

	/**
	 * 注册一个标识为key的消息类目
	 * 
	 * @param mCategory
	 */
	void registMessageCategory(IMessageCategory mCategory);

	/**
	 * 获取类目
	 * 
	 * @param name
	 * @return
	 */
	IMessageCategory getMessageCategory(String name);

	/**
	 * 获取所有注册的消息类目
	 * 
	 * @return
	 */
	Collection<IMessageCategory> allMessageCategory();

	/**
	 * 获取来源(发送人)对象
	 * 
	 * @param fromId
	 * @return
	 */
	Object getFrom(ID fromId);
}
