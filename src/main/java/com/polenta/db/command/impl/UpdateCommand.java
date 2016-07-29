package com.polenta.db.command.impl;

import java.util.Map;

import com.polenta.db.command.Command;
import com.polenta.db.command.ObjectType;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;

public class UpdateCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() {
		return null;
	}
	
	public static void performUpdate(ObjectType type, Map<String, Object> filterValues, Map<String, Object> newValues) throws PolentaException {
		if (type == ObjectType.TABLE) {
			//
		} else if (type == ObjectType.USER) {
			//User.getInstance().update(filterValues, newValues);
		} else {
			throw new InvalidStatementException("UPDATE is not supported by this object type.");
		}
	}


}
