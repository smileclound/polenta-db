package com.polenta.db.command;

import com.polenta.db.command.impl.AlterCommand;
import com.polenta.db.command.impl.CreateCommand;
import com.polenta.db.command.impl.DeleteCommand;
import com.polenta.db.command.impl.DropCommand;
import com.polenta.db.command.impl.InsertCommand;
import com.polenta.db.command.impl.SelectCommand;
import com.polenta.db.command.impl.AdminDatabaseCommand;
import com.polenta.db.command.impl.AdminUserCommand;
import com.polenta.db.command.impl.UpdateCommand;
import com.polenta.db.processor.StatementParser;

public class CommandBuilder {

	private CommandBuilder() {
		
	}
	
	private  static CommandBuilder INSTANCE = new CommandBuilder();

	public static CommandBuilder getInstance() {
		return CommandBuilder.INSTANCE;
	}
	
	public Command build(String statement) {
		CommandType commandType = StatementParser.extractCommandType(statement);
		Command command;
		if (commandType == CommandType.ALTER) {
			command = new AlterCommand();
		} else if (commandType == CommandType.CREATE) {
			command = new CreateCommand();
		} else if (commandType == CommandType.DELETE) {
			command = new DeleteCommand();
		} else if (commandType == CommandType.DROP) {
			command = new DropCommand();
		} else if (commandType == CommandType.INSERT) {
			command = new InsertCommand();
		} else if (commandType == CommandType.SELECT) {
			command = new SelectCommand();
		} else if (commandType == CommandType.ADMIN_USER) {
			command = new AdminUserCommand();
		} else if (commandType == CommandType.ADMIN_DATABASE) {
			command = new AdminDatabaseCommand();
		} else if (commandType == CommandType.UPDATE) {
			command = new UpdateCommand();
		} else {
			return null;
		}
		command.setStatement(statement);
		return command;
	}
	
	
}
