package com.polenta.db.catalog;

import java.util.LinkedHashMap;
import java.util.Map;

import com.polenta.db.command.ObjectType;

public class CatalogItem {

	private String name;
	private ObjectType type;
	private Map<String, String> definitions;
	
	public CatalogItem(String name, ObjectType type) {
		this.name = name.toUpperCase();
		this.type = type;
		this.definitions = new LinkedHashMap<String, String>();
	}

	public CatalogItem(String name, ObjectType type, Map<String, String> _definitions) {
		this.name = name;
		this.type = type;
		this.definitions = new LinkedHashMap<String, String>();
		this.definitions.putAll(_definitions);
	}

	public String getName() {
		return name;
	}

	public ObjectType getType() {
		return type;
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
