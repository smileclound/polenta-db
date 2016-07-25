package com.polenta.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.polenta.db.object.store.BagStore;

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
	
}
