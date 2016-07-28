package com.polenta.db.command.impl;

import com.polenta.db.catalog.Catalog;
import com.polenta.db.catalog.CatalogItem;
import com.polenta.db.command.Command;
import com.polenta.db.command.ObjectType;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.type.Bag;

public class DropCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() throws PolentaException {
		String objectName;
		try {
			objectName = statement.trim().toUpperCase().split(" ")[1];
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new InvalidStatementException("Object name must be defined on CREATE statement.");
		}

		CatalogItem catalogItem = Catalog.getInstance().get(objectName);  

		if (catalogItem == null) {
			throw new InvalidStatementException("Object does not exist.");
		}

		performDrop(catalogItem.getType(), objectName);
		
		return "OK";
	}

	protected static void performDrop(ObjectType type, String objectName) throws PolentaException {
		if (type == ObjectType.BAG) {
			Bag.get(objectName).drop();
		} else {
			throw new InvalidStatementException("DROP is not supported by this object type.");
		}
	}

}
