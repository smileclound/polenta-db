package com.polenta.db.command.impl;

import java.util.Map;

import com.polenta.db.command.Command;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;

public class AlterCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void performAlter(Class clazz, Map<String, Object> newDefinitions) throws PolentaException {
		throw new InvalidStatementException("ALTER is not supported by this object type.");
	}

}
