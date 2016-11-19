package com.polenta.db.executor;

import java.util.HashMap;
import java.util.Map;

import com.polenta.db.exception.PolentaException;

public interface StatementExecutor {

	public Map<String, Object> execute(String statement) throws PolentaException;

	default public Map<String, Object> success() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS", "SUCCESS");
		return map;
	}
	
}
