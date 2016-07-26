package com.polenta.db.object.behavior;

import java.util.List;
import java.util.Map;

public interface Selectable {

	public Map<String, Object> select(List<String> selectFields, Map<String, Object> whereConditions, List<String> orderByFields);
	
}
