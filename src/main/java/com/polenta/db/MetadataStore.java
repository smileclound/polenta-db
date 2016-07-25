package com.polenta.db;

import java.util.HashMap;
import java.util.Map;

import com.polenta.db.object.type.Bag;
import com.polenta.db.object.type.User;

public class MetadataStore {
	
	private MetadataStore() {
		
	}
	
	private static MetadataStore INSTANCE = new MetadataStore();
	
	public static MetadataStore getInstance() {
		return INSTANCE;
	}
	
	private Map<String, Class> objects = new HashMap<String, Class>();
	
	public boolean containsObject(String objectName) {
		return objects.containsKey(objectName);
	}

	public void addObject(String objectName, Class clazz) {
		objects.put(objectName, clazz);
	}
	
	public Class retrieveObjectClass(String objectName) {
		return objects.get(objectName);
	}
	
	@SuppressWarnings("rawtypes")
	public static Class retrieveObjectTypeClass(String objectType) {
		if (objectType.equalsIgnoreCase("BAG")) {
			return Bag.class;
		} else if (objectType.equalsIgnoreCase("USER")) { 
			return User.class; 
		} else {
			return null;
		}
	}
	
}
