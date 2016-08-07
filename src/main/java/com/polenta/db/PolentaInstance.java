package com.polenta.db;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.polenta.db.exception.PolentaException;
import com.polenta.db.log.PolentaLogger;
import com.polenta.db.processor.SocketProcessor;

public class PolentaInstance {
	
	public static PolentaInstance self;
	public static PolentaLogger logger;
	
	private int port;
	
	@SuppressWarnings("static-access")
	protected PolentaInstance(int port, PolentaLogger logger) throws PolentaException {
		if (this.self != null) {
			throw new PolentaException("There is already an instance of Polenta running.");
		}
		
		this.logger = logger;
		this.port = port;
		this.self = this;
	}

	ServerSocket serverSocket;

	public final void start() {
		PolentaInstance.logger.logSystem("Starting Polenta...");
		PolentaInstance.logger.logSystem("Opening port " + this.port + ".");
		PolentaInstance.logger.logSystem("Log level: " + PolentaInstance.logger.getLevel());
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			PolentaInstance.logger.logError("Impossible to open port " + this.port + ". Verify if it is not already in use.");
			return;
		}		
		PolentaInstance.logger.logSystem("Polenta running and waiting for statements and commands!");
		
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

	public final void shutdown() {
		try {
			serverSocket.close();
			PolentaInstance.logger.logSystem("Polenta shutdown in progress.");
			System.exit(0);
		} catch (IOException e) {
			PolentaInstance.logger.logError("Polenta could not friendly close port + " + port + "!");
		}
	}
	
	
}
