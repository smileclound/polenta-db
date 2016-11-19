package com.polenta.db.executor.impl;

import java.util.Map;

import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.executor.StatementExecutor;
import com.polenta.db.store.Store;

public class DropExecutor implements StatementExecutor {

	public Map<String, Object> execute(String statement) throws PolentaException {
		String objectName;
		try {
			objectName = statement.trim().toUpperCase().split(" ")[1];
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new InvalidStatementException("Object name must be defined on CREATE statement.");
		}

		Store.getInstance().remove(objectName);
		
		return success();
	}

}
