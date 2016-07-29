package com.polenta.db.command.impl;

import com.polenta.db.command.Command;

public class DBACommand implements Command {

	@SuppressWarnings("unused")
	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() {
		return "OK";
	}

}
