package com.polenta.db.object.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.polenta.db.MetadataStore;
import com.polenta.db.exception.BagAlreadyExistsException;
import com.polenta.db.exception.ObjectAlreadyExistsException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.Metadata;
import com.polenta.db.object.behavior.Creatable;
import com.polenta.db.object.behavior.Dropable;
import com.polenta.db.object.behavior.Insertable;
import com.polenta.db.object.behavior.Selectable;
import com.polenta.db.object.store.BagStore;

public class Bag implements Creatable, Insertable, Selectable, Dropable, Metadata {
	
	private Bag() {
		
	}
	
	private Map<String, Map<String, String>> bags = new HashMap<String, Map<String, String>>();
	
	
	
	
	private static Bag INSTANCE = new Bag();
	
	public static Bag getInstance() {
		return INSTANCE;
	}
	
	
	public void drop(String bagName) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> select(List<String> returnFields, Map<String, Object> filterValues) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Map<String, Object> insertValues) {
		// TODO Auto-generated method stub
		
	}

	public void create(String bagName, Map<String, String> definitionValues) throws PolentaException {
		//join verify and add in atomic operation?
		if (MetadataStore.getInstance().containsObject(bagName)) {
			throw new ObjectAlreadyExistsException();
		}
		MetadataStore.getInstance().addObject(bagName, Bag.class);
		//join verify and add in atomic operation?
		if (BagStore.getInstance().contains(bagName)) {
			throw new BagAlreadyExistsException();
		}
		BagStore.getInstance().create(bagName);
		bags.put(bagName, definitionValues);
		System.out.println("New bag " + bagName + " created");
	}


	public Map<String, String> retrieveMetadataForObject(String bagName) {
		//should return a copy
		return bags.get(bagName);
	}

}
