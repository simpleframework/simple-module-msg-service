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
public abstract class AbstractMessagePlugin extends AbstractModulePlugin
		implements IMessagePlugin, IMessageContextAware {

	protected Map<String, IMessageCategory> categoryCache;
	{
		categoryCache = new HashMap<String, IMessageCategory>();
	}

	@Override
	public void registMessageCategory(final IMessageCategory mCategory) {
		final String name = mCategory.getName();
		// if (categoryCache.get(key) != null) {
		// }
		categoryCache.put(name, mCategory);
	}

	@Override
	public Collection<IMessageCategory> allMessageCategory() {
		return categoryCache.values();
	}

	@Override
	public IMessageCategory getMessageCategory(final String name) {
		return categoryCache.get(name);
	}

	@Override
	public Object getFrom(final ID fromId) {
		return null;
	}
}
