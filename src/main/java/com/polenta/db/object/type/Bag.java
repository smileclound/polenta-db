package com.polenta.db.object.type;

import java.util.List;
import java.util.Map;

import com.polenta.db.object.behavior.Creatable;
import com.polenta.db.object.behavior.Dropable;
import com.polenta.db.object.behavior.Insertable;
import com.polenta.db.object.behavior.Selectable;

public class Bag implements Creatable, Insertable, Selectable, Dropable {
	
	private Bag() {
		
	}
	
	private static Bag INSTANCE = new Bag();
	
	public static Bag getInstance() {
		return INSTANCE;
	}
	
	
	public void drop() {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> select(List<String> returnFields, Map<String, Object> filterValues) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Map<String, Object> insertValues) {
		// TODO Auto-generated method stub
		
	}

	public void create(Map<String, Object> definitionValues) {
		// TODO Auto-generated method stub
		System.out.println("new bag created");
	}

}
