package com.polenta.db.command;

import com.polenta.db.command.impl.AlterCommand;
import com.polenta.db.command.impl.CreateCommand;
import com.polenta.db.command.impl.DeleteCommand;
import com.polenta.db.command.impl.DropCommand;
import com.polenta.db.command.impl.InsertCommand;
import com.polenta.db.command.impl.SelectCommand;
import com.polenta.db.command.impl.ShutdownCommand;
import com.polenta.db.command.impl.UpdateCommand;

public class CommandBuilder {

	private CommandBuilder() {
		
	}
	
	private  static CommandBuilder INSTANCE = new CommandBuilder();

	public static CommandBuilder getInstance() {
		return CommandBuilder.INSTANCE;
	}
	
	public Command build(String statement) {
		String operation = statement.trim().toUpperCase().split(" ")[0];
		Command command;
		if (operation.equalsIgnoreCase("ALTER")) {
			command = new AlterCommand();
		} else if (operation.equalsIgnoreCase("CREATE")) {
			command = new CreateCommand();
		} else if (operation.equalsIgnoreCase("DELETE")) {
			command = new DeleteCommand();
		} else if (operation.equalsIgnoreCase("DROP")) {
			command = new DropCommand();
		} else if (operation.equalsIgnoreCase("INSERT")) {
			command = new InsertCommand();
		} else if (operation.equalsIgnoreCase("SELECT")) {
			command = new SelectCommand();
		} else if (operation.equalsIgnoreCase("SHUTDOWN")) {
			command = new ShutdownCommand();
		} else if (operation.equalsIgnoreCase("UPDATE")) {
			command = new UpdateCommand();
		} else {
			return null;
		}
		command.setStatement(statement);
		return command;
	}
	
	
}
