package com.polenta.db.executor;

import com.polenta.db.exception.PolentaException;

public interface StatementExecutor {

	public String execute(String statement) throws PolentaException;
	
}
