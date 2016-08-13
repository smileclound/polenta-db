package com.polenta.db;

import java.io.IOException;

import com.polenta.db.log.Logger;
import com.polenta.db.socket.SocketServer;

public class Polenta {

	private static final int DEFAULT_PORT = 3110;
	private static SocketServer socketServer;
	
	public static void main(String[] args) throws Exception {
		init(args);
	}
	
	public static void init(String[] args) throws Exception {
		Logger.logSystem("Starting Polenta");
		try {
			Logger.init(Polenta.argument("logLevel", args));
		} catch (Exception e) {
			Logger.logSystem("Polenta could not start. Problem found creating log with given arguments.");
			System.exit(-1);
		}
		
		int port;

		try {
			port = Polenta.intArgument("port", Polenta.DEFAULT_PORT, args);
			start(port);
		} catch (NumberFormatException nfe) {
			Logger.logSystem("Polenta could not start. Value of port is invalid.");
			System.exit(-1);
		}
	}

	public static void start(int port) {
		Logger.logSystem("Listener port: " + port);
		Logger.logSystem("Log level....: " + Logger.getLevel());
		Logger.logSystem("Log target...: CONSOLE");
		
		socketServer = new SocketServer(port);
		try {
			socketServer.open();
			Logger.logSystem("Polenta running and waiting for statements!");
		} catch (IOException e) {
			Logger.logSystem("Polenta could not start. Port " + port + " is already in use.");
			System.exit(-1);
		}		


	}

	public static final void shutdown() {
		Logger.logSystem("Polenta shutdown in progress.");
		socketServer.close();
		System.exit(0);
	}
	
	protected static int intArgument(String argName, int defaultValue, String[] args) {
		String argValue = argument(argName, args);
		if (argValue == null) {
			return defaultValue;
		} else {
			return Integer.parseInt(argValue);
		}
	}

	protected static String argument(String argName, String[] args) {
		String argValue = null;
		if (args != null && args.length > 0) {
			for (String arg: args) {
				if (arg.startsWith("--" + argName + "=")) {
					argValue = arg.substring(argName.length() + 3);
				}
			}
		}
		return argValue;
	}

}
