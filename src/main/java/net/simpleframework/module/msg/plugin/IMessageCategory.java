package net.simpleframework.module.msg.plugin;

import net.simpleframework.common.ID;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.object.IObjectOrderAware;
import net.simpleframework.common.object.ObjectEx;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IMessageCategory extends IObjectOrderAware {

	/**
	 * 唯一标记
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 获取来源对象,如果有多个来源,则覆盖实现类
	 * 
	 * @param fromId
	 * @return
	 */
	Object getFrom(ID fromId);

	public abstract static class AbstractMessageCategory extends ObjectEx implements
			IMessageCategory {
		private String name;

		protected String _text;

		@Override
		public String getName() {
			return StringUtils.hasText(name) ? name : getClass().getSimpleName();
		}

		public void setName(final String name) {
			this.name = name;
		}

		@Override
		public int getOrder() {
			return 0;
		}

		@Override
		public String toString() {
			return _text;
		}
	}
}
