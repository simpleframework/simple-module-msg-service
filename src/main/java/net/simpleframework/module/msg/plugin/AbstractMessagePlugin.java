package net.simpleframework.module.msg.plugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.simpleframework.common.ID;
import net.simpleframework.module.common.plugin.AbstractModulePlugin;
import net.simpleframework.module.msg.IMessageContextAware;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class AbstractMessagePlugin extends AbstractModulePlugin implements IMessagePlugin,
		IMessageCategoryPlugin, IMessageContextAware {

	protected Map<Integer, IMessageCategoryPlugin> categoryCache;
	{
		categoryCache = new HashMap<Integer, IMessageCategoryPlugin>();
	}

	@Override
	public void registMessageCategoryPlugin(final IMessageCategoryPlugin mCategory) {
		final int key = mCategory.getMark();
		if (categoryCache.get(key) != null) {
		}
		categoryCache.put(key, mCategory);
	}

	@Override
	public Collection<IMessageCategoryPlugin> allMessageCategoryPlugins() {
		return categoryCache.values();
	}

	@Override
	public IMessageCategoryPlugin getMessageCategoryPlugin(final int mark) {
		return categoryCache.get(mark);
	}

	@Override
	public Object getFrom(final ID fromId) {
		return null;
	}

	@Override
	public Collection<IMessageCategoryPlugin> getChildren() {
		return null;
	}
}
