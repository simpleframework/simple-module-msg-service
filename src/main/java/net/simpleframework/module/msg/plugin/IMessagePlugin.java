package net.simpleframework.module.msg.plugin;

import java.util.Collection;

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
	void registMessageCategoryPlugin(IMessageCategoryPlugin mCategory);

	/**
	 * 获取类目
	 * 
	 * @param category
	 * @return
	 */
	IMessageCategoryPlugin getMessageCategoryPlugin(int categoryMark);

	Collection<IMessageCategoryPlugin> allMessageCategoryPlugins();

	Collection<IMessageCategoryPlugin> getChildren();
}
