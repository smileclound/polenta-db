package com.polenta.db.object.behavior;

import java.util.Map;

public interface Insertable {

	public void insert(Map<String, Object> insertValues);
	
}
