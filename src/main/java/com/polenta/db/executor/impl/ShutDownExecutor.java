package com.polenta.db.executor.impl;

import java.util.Map;

import com.polenta.db.Polenta;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.executor.StatementExecutor;

public class ShutDownExecutor implements StatementExecutor {

	public Map<String, Object> execute(String statement) throws PolentaException {
		Polenta.shutdown();
		return success();
	}
	
}
