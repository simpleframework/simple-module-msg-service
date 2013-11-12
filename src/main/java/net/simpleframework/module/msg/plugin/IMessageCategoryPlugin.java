package net.simpleframework.module.msg.plugin;

import net.simpleframework.common.ID;
import net.simpleframework.module.common.plugin.IModulePlugin;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IMessageCategoryPlugin extends IModulePlugin {

	/**
	 * 获取来源对象,如果有多个来源,则覆盖实现类
	 * 
	 * @param fromId
	 * @return
	 */
	Object getFrom(ID fromId);
}
