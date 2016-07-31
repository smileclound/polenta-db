package com.polenta.db.store;

import java.util.LinkedHashMap;
import java.util.Map;

import com.polenta.db.catalog.Catalog;
import com.polenta.db.catalog.CatalogItem;
import com.polenta.db.data.DataType;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.ObjectType;

public class Store {

	private Map<String, Storable> store = new LinkedHashMap<String, Storable>();
	
	private static Store INSTANCE = new Store();
	
	public static Store getInstance() {
		return INSTANCE;
	}
	
	private Store() {
		
	}

	public synchronized Storable add(ObjectType type, String name) throws PolentaException {
		return add(type, name, null);
	}

	public synchronized Storable add(ObjectType type, String name, Map<String, DataType> fields) throws PolentaException {
		Storable storable = type.create();
		store.put(name, storable);
		
		CatalogItem catalogItem = new CatalogItem(type, name, fields);
		Catalog.getInstance().add(catalogItem);
		
		return storable;
	}
	
	public synchronized void remove(String name) throws PolentaException {
		CatalogItem catalogItem = Catalog.getInstance().get(name);  

		if (catalogItem == null) {
			throw new InvalidStatementException("Object does not exist.");
		}
		
		Catalog.getInstance().remove(name);
		this.store.remove(name);
	}
	
	public Storable get(String name) {
		return store.get(name);
	}

}
