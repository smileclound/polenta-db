package com.polenta.db.executor.impl;

import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.executor.StatementExecutor;
import com.polenta.db.object.behavior.Alterable;
import com.polenta.db.store.Store;

public class AlterExecutor implements StatementExecutor {

	public String execute(String statement) throws PolentaException {
		try {
			((Alterable)Store.getInstance().get(null)).alter(null);;
		} catch (ClassCastException e) {
			throw new InvalidStatementException("ALTER is not supported by this object type.");
		}
		
		return "OK";
	}
	
}
