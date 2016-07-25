package com.polenta.db.object.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagStore {
	
	private BagStore() {
	}
	
	private static BagStore INSTANCE = new BagStore();
	
	public static BagStore getInstance() {
		return INSTANCE;
	}
	
	private Map<String, List<Map<String, Object>>> bags = new HashMap<String, List<Map<String, Object>>>();
	
	public boolean contains(String bagName) {
		return bags.containsKey(bagName);
	}
	
	public void create(String bagName) {
		bags.put(bagName, new ArrayList<Map<String, Object>>());
	}

	public void insert(String bagName, Map<String, Object> map) {
		bags.get(bagName).add(map);
	}
	
	public void drop(String bagName) {
		bags.remove(bagName);
	}
	
	
	
}
