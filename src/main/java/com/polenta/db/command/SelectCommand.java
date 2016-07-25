package com.polenta.db.command;

import java.util.List;
import java.util.Map;

import com.polenta.db.Command;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.type.Bag;
import com.polenta.db.object.type.User;

public class SelectCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static void performSelect(Class clazz, List<String> returnFields, Map<String, Object> filterValues) throws PolentaException {
		if (Bag.class.isAssignableFrom(clazz)) {
			Bag.getInstance().select(returnFields, filterValues);
		} else if (User.class.isAssignableFrom(clazz)) {
			User.getInstance().select(returnFields, filterValues);
		} else {
			throw new InvalidStatementException("SELECT is not supported by this object type.");
		}
	}

	
}
