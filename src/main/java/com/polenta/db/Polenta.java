package com.polenta.db;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.polenta.db.exception.PolentaException;
import com.polenta.db.log.Logger;
import com.polenta.db.processor.SocketProcessor;

public class Polenta {

	private static final int DEFAULT_PORT = 3110;
	private static ServerSocket serverSocket;
	
	public static void main(String[] args) throws Exception {
		init(args);
	}
	
	public static void init(String[] args) throws Exception {
		Logger.logSystem("Starting Polenta...");
		try {
			Logger.init(Polenta.extractLogLevelFromArguments(args));
		} catch (Exception e) {
			Logger.logSystem("Polenta could not init logger and will stop...");
			System.exit(-1);
		}
		
		int port = Polenta.extractPortFromArguments(args);
		
		start(port);
	}

	public static void start(int port) {
		Logger.logSystem("Opening port " + port + ".");
		Logger.logSystem("Log level: " + Logger.getLevel());
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			Logger.logError("Impossible to open port " + port + ". Verify if it is not already in use.");
			System.exit(-1);
		}		
		Logger.logSystem("Polenta running and waiting for statements and commands!");
		
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				SocketProcessor processor = new SocketProcessor(clientSocket);
				Thread thread = new Thread(processor);
				thread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public static final void shutdown() {
		try {
			serverSocket.close();
			Logger.logSystem("Polenta shutdown in progress.");
			System.exit(0);
		} catch (IOException e) {
			Logger.logError("Polenta could not friendly close port in use!");
		}
	}
	
	protected static int extractPortFromArguments(String[] args) throws PolentaException {
		int port = Polenta.DEFAULT_PORT;
		if (args != null && args.length > 0) {
			for (String arg: args) {
				if (arg.startsWith("--port=")) {
					try {
						int convertedPort = Integer.parseInt(arg.substring(7));
						port = convertedPort;
					} catch (Exception e) {
						throw new PolentaException("Polenta could not start. Verify argument --port.");
					}
				}
			}
		}
		return port;
	}

	protected static String extractLogLevelFromArguments(String[] args) throws PolentaException {
		String level = null;
		if (args != null && args.length > 0) {
			for (String arg: args) {
				if (arg.startsWith("--logLevel=")) {
					level = arg.substring(11);
				}
			}
		}
		return level;
	}
	
}
