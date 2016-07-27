package com.polenta.db.catalog;

import java.util.LinkedHashMap;
import java.util.Map;

public class CatalogItem {

	private String name;
	private Class clazz;
	private Map<String, String> definitions;
	
	public CatalogItem(String name, Class clazz) {
		this.name = name.toUpperCase();
		this.clazz = clazz;
		this.definitions = new LinkedHashMap<String, String>();
	}

	public CatalogItem(String name, Class clazz, Map<String, String> _definitions) {
		this.name = name;
		this.clazz = clazz;
		this.definitions = new LinkedHashMap<String, String>();
		
		for (String key: _definitions.keySet()) {
			addDefinitionKey(key, _definitions.get(key));
		}
	}

	public String getName() {
		return name;
	}

	public Class getClazz() {
		return clazz;
	}
	
	public void addDefinitionKey(String key, String value) {
		definitions.put(key.toUpperCase(), value.toUpperCase());
	}
	
	public boolean hasDefinitionKey(String key) {
		return definitions.containsKey(key.toUpperCase());
	}
	
	public String getDefinitionValue(String key) {
		return definitions.get(key.toUpperCase());
	}
	
}
