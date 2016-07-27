package com.polenta.db.object.behavior;

import java.util.List;
import java.util.Map;

import com.polenta.db.Row;
import com.polenta.db.exception.PolentaException;

public interface Selectable {

	public List<Row> select(List<String> selectFields, Map<String, Object> whereConditions, List<String> orderByFields) throws PolentaException;
	
}
