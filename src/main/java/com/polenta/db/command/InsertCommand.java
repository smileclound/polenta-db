package com.polenta.db.command;

import com.polenta.db.Command;

public class InsertCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() {
		return null;
	}

}
