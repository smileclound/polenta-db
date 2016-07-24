package com.polenta.db.object.behavior;

import java.util.Map;

public interface Deletable {

	public void delete(Map<String, Object> filterValues);
	
}
