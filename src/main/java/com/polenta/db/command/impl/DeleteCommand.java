package com.polenta.db.command.impl;

import java.util.Map;

import com.polenta.db.command.Command;
import com.polenta.db.command.ObjectType;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;

public class DeleteCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() {
		return null;
	}

	
	public static void performDelete(ObjectType type, Map<String, Object> filterValues) throws PolentaException {
		if (type == ObjectType.TABLE) {
			//Table.getInstance().delete(filterValues);
		} else {
			throw new InvalidStatementException("DELETE is not supported by this object type.");
		}
	}

}
