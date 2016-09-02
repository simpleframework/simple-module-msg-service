package net.simpleframework.module.msg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import net.simpleframework.common.Convert;
import net.simpleframework.common.coll.KVMap;
import net.simpleframework.ctx.common.xml.XmlElement;
import net.simpleframework.ctx.settings.XmlContextSettings;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public abstract class MessageContextSettings extends XmlContextSettings
		implements IMessageContextAware {

	public MessageContextSettings(final File file) throws FileNotFoundException {
		super(file);
		setApplicationSettings(messageContext.getApplicationContext().getContextSettings());
	}

	@Override
	protected String getRootTag() {
		return "message-settings";
	}

	public Map<String, Object> getTagProps(final String tag, final String name) {
		KVMap kv = new KVMap();
		final Iterator<XmlElement> it = getRoot().elementIterator(tag);
		while (it.hasNext()) {
			final XmlElement ele = it.next();
			if (name.equals(ele.attributeValue("name"))) {
				kv = new KVMap();
				final Iterator<XmlElement> it2 = ele.elementIterator();
				while (it2.hasNext()) {
					final XmlElement pEle = it2.next();
					kv.add(pEle.attributeValue("name"), pEle.getText());
				}
				break;
			}
		}
		return kv;
	}

	protected abstract void save() throws IOException;

	public void saveNoticeMessageCategoryProps(final String name, final Map<String, Object> props)
			throws IOException {
		final XmlElement p = element("notice-message");
		XmlElement category = null;
		final Iterator<XmlElement> it = p.elementIterator("category");
		while (it.hasNext()) {
			final XmlElement ele = it.next();
			if (name.equals(ele.attributeValue("name"))) {
				category = ele;
				break;
			}
		}
		if (category == null) {
			category = p.addElement("category").addAttribute("name", name);
		} else {
			category.clearContent();
		}
		for (final String s : new String[] { "sendto-normal", "sendto-email", "sendto-mobile",
				"topic" }) {
			category.addElement("property").addAttribute("name", s)
					.setText(Convert.toString(props.get(s)));
		}
		category.addElement("property").addAttribute("name", "content")
				.addCDATA(Convert.toString(props.get("content")).replace("\r\n", "\r"));
		save();
		removeAttr("notice-message:" + name);
	}

	public Map<String, Object> getNoticeMessageCategoryProps(final String name) {
		final String key = "notice-message:" + name;
		KVMap kv = (KVMap) getAttr(key);
		if (kv != null) {
			return kv;
		}
		final Iterator<XmlElement> it = element("notice-message").elementIterator("category");
		while (it.hasNext()) {
			final XmlElement ele = it.next();
			if (name.equals(ele.attributeValue("name"))) {
				kv = new KVMap();
				final Iterator<XmlElement> it2 = ele.elementIterator();
				while (it2.hasNext()) {
					final XmlElement pEle = it2.next();
					kv.add(pEle.attributeValue("name"), pEle.getText());
				}
				setAttr(key, kv);
				break;
			}
		}
		return kv;
	}
}
