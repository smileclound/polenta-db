package com.polenta.db.object.behavior;

import java.util.List;
import java.util.Map;

public interface Selectable {

	public Map<String, Object> select(List<String> returnFields, Map<String, Object> filterValues);
	
}
