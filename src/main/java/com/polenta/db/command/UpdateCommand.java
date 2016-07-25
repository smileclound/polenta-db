package com.polenta.db.command;

import java.util.Map;

import com.polenta.db.Command;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.type.User;

public class UpdateCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void performUpdate(Class clazz, Map<String, Object> filterValues, Map<String, Object> newValues) throws PolentaException {
		if (User.class.isAssignableFrom(clazz)) {
			User.getInstance().update(filterValues, newValues);
		} else {
			throw new InvalidStatementException("UPDATE is not supported by this object type.");
		}
	}


}
