package com.polenta.db.command.impl;

import com.polenta.db.PolentaInstance;
import com.polenta.db.command.Command;
import com.polenta.db.exception.PolentaException;

public class AdminDatabaseCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() throws PolentaException {
		if (statement.toUpperCase().startsWith("SHUTDOWN")) {
			PolentaInstance.self.shutdown();
		}
		return "OK";
	}

}
