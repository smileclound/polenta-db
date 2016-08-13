package com.polenta.db.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;

import com.polenta.db.executor.StatementExecutor;
import com.polenta.db.executor.StatementExecutorBuilder;
import com.polenta.db.log.Logger;

public class StatementListener implements Runnable {

	private BufferedReader reader;
	private BufferedWriter writer;
	
	private static int READ_TIMEOUT_FIVE_SECONDS = 5000;
	
	private static int MAX_CONNECTION_IDLE_TIME = 5 * 60 * 1000; //FIVE MINUTES
	
	private Socket clientSocket;
	private int socketIdleTime;
	
	protected StatementListener(Socket clientSocket) throws IOException, SocketException {
		this.clientSocket = clientSocket;
		
		clientSocket.setKeepAlive(true);
		clientSocket.setSoTimeout(StatementListener.READ_TIMEOUT_FIVE_SECONDS);

		reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
	}

	public void run() {
		if (clientSocket == null) return;
		//
		String statement;
		boolean connected = clientSocket.isConnected();
		while (connected) {
			try {
				statement = reader.readLine();
				if (statement != null) {
					socketIdleTime = 0;
					Logger.logDebug("\nStatement received: " + statement);
					writer.write(process(statement.toUpperCase()));
					writer.newLine();
					writer.flush();
				} else {
					connected = false;
				}
			} catch (java.net.SocketTimeoutException ste) {
				socketIdleTime = socketIdleTime + READ_TIMEOUT_FIVE_SECONDS;
				if (socketIdleTime >= MAX_CONNECTION_IDLE_TIME) {
					connected = false;
					Logger.logDebug("Socket client connection will be closed due to inactivity.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			clientSocket.close();
			Logger.logDebug("Socket client connection has been closed.");
		} catch (Exception e) {
			Logger.logDebug("Failed to close socket client connection.");
		}
	}

	public String process(String statement) {
		if (statement == null || statement.trim().equals("")) {
			return "ERROR|Statement required.";
		}

		String[] words = statement.split(" ");
		if (words.length == 0) {
			return "ERROR|Statement required.";
		}
		
		StatementExecutor command = StatementExecutorBuilder.getInstance().build(statement);
		
		if (command == null) { 
			return "ERROR|Operation not supported.";
		}
		
		try {
			return "OK|" + command.execute(statement);
		} catch (Exception e) {
			return "ERROR|" + e.getMessage();
		}
		
	}

}
