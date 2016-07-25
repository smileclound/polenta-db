package com.polenta.db.object.behavior;

import java.util.Map;

import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.KeyValue;

public interface Creatable {

	public void create(String name, Map<String, KeyValue> definitionValues) throws PolentaException;
	
}
