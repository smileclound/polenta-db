package com.polenta.db.object.type;

import java.util.List;
import java.util.Map;

import com.polenta.db.object.behavior.Deletable;
import com.polenta.db.object.behavior.Selectable;
import com.polenta.db.object.behavior.Updatable;

public class User implements Updatable, Selectable, Deletable {

	public User() {
		
	}
	
	public static void create(String bagName, Map<String, String> fields) {
		
	}
	
	private static User INSTANCE = new User();
	
	public static User getInstance() {
		return INSTANCE;
	}

	public void delete(Map<String, Object> filterValues) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> select(List<String> returnFields, Map<String, Object> filterValues) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Map<String, Object> filterValues, Map<String, Object> newValues) {
		// TODO Auto-generated method stub
		
	}

}
