package com.polenta.db.object.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.polenta.db.catalog.Catalog;
import com.polenta.db.catalog.CatalogItem;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.behavior.Dropable;
import com.polenta.db.object.behavior.Insertable;
import com.polenta.db.object.behavior.Selectable;

public class Bag implements Insertable, Selectable, Dropable {
	
	private String name;
	private Map<Integer, Map<String, Object>> items;
	
	private static Map<String, Bag> BAGS = new HashMap<String, Bag>();

	public static void create(String bagName, Map<String, String> fields) throws PolentaException {
		Bag bag = new Bag(bagName, fields);
		BAGS.put(bagName, bag);
		System.out.println("New bag " + bagName + " created");
	}
	
	private Bag(String bagName, Map<String, String> fields) throws PolentaException {
		CatalogItem catalogItem = new CatalogItem(bagName, Bag.class, fields);
		Catalog.getInstance().add(catalogItem);
		this.name = bagName; 
		items = new HashMap<Integer, Map<String, Object>>();
	}
	
	public static Bag get(String bagName) {
		return BAGS.get(bagName);
	}

	public void drop() {
		this.items.clear();
		BAGS.remove(this.name);
	}

	public Map<String, Object> select(List<String> selectFields, Map<String, Object> whereConditions, List<String> orderFields) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Map<String, Object> values) {
		items.put(items.size() + 1, values);
	}

	public void create() throws PolentaException {
	}


}
