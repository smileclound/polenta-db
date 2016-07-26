package com.polenta.db.command;

import com.polenta.db.Command;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.type.Bag;

public class DropCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void performDrop(String name, Class clazz, String objectName) throws PolentaException {
		if (Bag.class.isAssignableFrom(clazz)) {
			Bag.get(name).drop();
		} else {
			throw new InvalidStatementException("DROP is not supported by this object type.");
		}
	}

}
