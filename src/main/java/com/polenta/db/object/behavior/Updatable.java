package com.polenta.db.object.behavior;

import java.util.Map;

public interface Updatable {

	public void update(Map<String, Object> filterValues, Map<String, Object> newValues);
	
}
