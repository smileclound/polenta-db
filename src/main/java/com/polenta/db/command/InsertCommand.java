package com.polenta.db.command;

import java.util.Map;

import com.polenta.db.Command;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.type.Bag;

public class InsertCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() {
		return null;
	}

	public static void performInsert(Class clazz, Map<String, Object> insertValues) throws PolentaException {
		if (Bag.class.isAssignableFrom(clazz)) {
			Bag.getInstance().insert(insertValues);
		} else {
			throw new InvalidStatementException("INSERT is not supported by this object type.");
		}
	}

}
