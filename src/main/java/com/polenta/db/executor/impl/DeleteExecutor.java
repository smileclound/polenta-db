package com.polenta.db.executor.impl;

import java.util.Map;

import com.polenta.db.catalog.Catalog;
import com.polenta.db.catalog.CatalogItem;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.executor.StatementExecutor;
import com.polenta.db.object.behavior.Deletable;
import com.polenta.db.store.Store;

public class DeleteExecutor implements StatementExecutor {

	public Map<String, Object> execute(String statement) throws PolentaException {
		String objectName;
		try {
			objectName = statement.trim().toUpperCase().split(" ")[2];
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new InvalidStatementException("Object name must be defined on DELETE statement.");
		}
		
		CatalogItem catalogItem = Catalog.getInstance().get(objectName);  

		if (catalogItem == null) {
			throw new InvalidStatementException("Object does not exist.");
		}

		try {
			((Deletable)Store.getInstance().get(objectName)).delete(null);
		} catch (ClassCastException e) {
			throw new InvalidStatementException("DELETE is not supported by this object type.");
		}
		
		return success();
	}

}
