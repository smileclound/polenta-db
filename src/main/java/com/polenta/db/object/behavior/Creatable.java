package com.polenta.db.object.behavior;

import java.util.Map;

import com.polenta.db.exception.PolentaException;

public interface Creatable {

	public void create(String name, Map<String, String> definitionValues) throws PolentaException;
	
}
