package com.polenta.db.executor.impl;

import java.util.Map;

import com.polenta.db.catalog.Catalog;
import com.polenta.db.catalog.CatalogItem;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.executor.StatementExecutor;
import com.polenta.db.object.behavior.Updatable;
import com.polenta.db.store.Store;

public class UpdateExecutor implements StatementExecutor {

	public Map<String, Object> execute(String statement) throws PolentaException {
		String objectName;
		try {
			objectName = statement.trim().toUpperCase().split(" ")[1];
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new InvalidStatementException("Object name must be defined on UPDATE statement.");
		}
		
		CatalogItem catalogItem = Catalog.getInstance().get(objectName);  

		if (catalogItem == null) {
			throw new InvalidStatementException("Object does not exist.");
		}

		try {
			((Updatable)Store.getInstance().get(objectName)).update(null, null);
		} catch (ClassCastException e) {
			throw new InvalidStatementException("UPDATE is not supported by this object type.");
		}
		
		return success();
	}

}
