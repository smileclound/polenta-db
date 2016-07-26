package com.polenta.db.command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.polenta.db.catalog.Catalog;
import com.polenta.db.catalog.CatalogItem;
import com.polenta.db.command.Command;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.type.Bag;
import com.polenta.db.object.type.User;

public class SelectCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() throws PolentaException {
		String objectName = extractObjectName();
		
		CatalogItem catalogItem = Catalog.getInstance().get(objectName);  

		if (catalogItem == null) {
			throw new InvalidStatementException("Object does not exist.");
		}
		
		List<String> selectFields = extractOrderFields();
		Map<String, Object> whereConditions = extractWhereConditions();
		List<String> orderFields = extractOrderFields();
		
		if (orderFields.size() > 1) {
			throw new InvalidStatementException("ORDER BY does not support (yet!) more than a field.");
		}
		for (String orderField: orderFields) {
			if (!selectFields.contains(orderField)) {
				throw new InvalidStatementException("Field in ORDER BY clausule needs to be listed on SELECT clausule.");
			}
		}
		Map<String, Object> result = performSelect(objectName, catalogItem.getClazz(), selectFields, whereConditions, orderFields);
		return formatResultToTransport(result);
	}
	
	protected String extractObjectName() throws PolentaException {
		String name = null;
		if (statement.indexOf(" FROM ") == -1) {
			throw new InvalidStatementException("SELECT statement must have a FROM clausule");
		}
		
		return name;
	}

	protected List<String> extractSelectFields() throws PolentaException {
		List<String> fields = new ArrayList<String>();
		return fields;
	}

	protected Map<String, Object> extractWhereConditions() throws PolentaException {
		Map<String, Object> conditions = new HashMap<String, Object>();
		return conditions;
	}

	protected List<String> extractOrderFields() throws PolentaException {
		List<String> fields = new ArrayList<String>();
		return fields;
	}

	protected Map<String, Object> performSelect(String name, @SuppressWarnings("rawtypes") Class clazz, List<String> selectFields, Map<String, Object> whereConditions, List<String> orderFields) throws PolentaException {
		if (Bag.class.isAssignableFrom(clazz)) {
			return Bag.get(name).select(selectFields, whereConditions, orderFields);
		} else if (User.class.isAssignableFrom(clazz)) {
			return null; //User.getInstance().select(selectFields, whereConditions, orderFields);
		} else {
			throw new InvalidStatementException("SELECT is not supported by this object type.");
		}
	}
	
	protected String formatResultToTransport(Map<String, Object> result) {
		if (result == null || result.isEmpty()) {
			return "EMPTY_RESULT_SET";
		} else {
			//change this
			return result.toString();
		}
	}
	
}
