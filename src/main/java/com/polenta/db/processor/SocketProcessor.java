package com.polenta.db.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.polenta.db.PolentaInstance;

public class SocketProcessor implements Runnable {

	private BufferedReader reader;
	private BufferedWriter writer;
	
	private static int READ_TIMEOUT_FIVE_SECONDS = 5000;
	
	private static int MAX_CONNECTION_IDLE_TIME = 5 * 60 * 1000; //FIVE MINUTES
	
	private Socket clientSocket;
	private int socketIdleTime;
	
	public SocketProcessor(Socket clientSocket) throws Exception {
		this.clientSocket = clientSocket;
		
		clientSocket.setKeepAlive(true);
		clientSocket.setSoTimeout(SocketProcessor.READ_TIMEOUT_FIVE_SECONDS);

		reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
	}

	public void run() {
		String statement;
		boolean connected = clientSocket.isConnected();
		while (connected) {
			try {
				statement = reader.readLine();
				if (statement != null) {
					socketIdleTime = 0;
					PolentaInstance.logger.logDebug("\nStatement received: " + statement);
					StatementProcessor processor = new StatementProcessor(statement.trim());
					String result = processor.execute();
					writer.write(result);
					writer.newLine();
					writer.flush();
				} else {
					connected = false;
				}
			} catch (java.net.SocketTimeoutException ste) {
				socketIdleTime = socketIdleTime + READ_TIMEOUT_FIVE_SECONDS;
				if (socketIdleTime >= MAX_CONNECTION_IDLE_TIME) {
					connected = false;
					PolentaInstance.logger.logInfo("Socket client connection will be closed due to inactivity.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			clientSocket.close();
			PolentaInstance.logger.logInfo("Socket client connection has been closed.");
		} catch (Exception e) {
			PolentaInstance.logger.logInfo("Failed to close socket client connection.");
		}
	}

}
