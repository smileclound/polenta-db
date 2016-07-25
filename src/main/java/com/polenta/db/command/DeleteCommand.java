package com.polenta.db.command;

import java.util.Map;

import com.polenta.db.Command;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.type.User;

public class DeleteCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static void performDelete(Class clazz, Map<String, Object> filterValues) throws PolentaException {
		if (User.class.isAssignableFrom(clazz)) {
			User.getInstance().delete(filterValues);
		} else {
			throw new InvalidStatementException("DELETE is not supported by this object type.");
		}
	}

}
