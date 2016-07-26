package com.polenta.db.command;

import com.polenta.db.exception.PolentaException;

public interface Command {

	public void setStatement(String statement);
	
	public String execute() throws PolentaException;
	
}
