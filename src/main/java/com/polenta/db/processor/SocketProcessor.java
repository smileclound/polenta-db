package com.polenta.db.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.polenta.db.exception.PolentaException;

public class SocketProcessor implements Runnable {

	private BufferedReader reader;
	private BufferedWriter writer;
	
	private static int READ_TIMEOUT_FIVE_SECONDS = 5000;
	
	private static int MAX_CONNECTION_IDLE_TIME_HALF_MINUTE = 30000;
	
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
					System.out.println("\nStatement received: " + statement + " processor: " + this);
					StatementProcessor processor = new StatementProcessor(statement.trim());
					String result;
					try {
						result = processor.execute();
					} catch (PolentaException e) {
						result = e.getMessage();
						e.printStackTrace();
					} catch (Exception e) {
						result = "Failed to process statement.";
						e.printStackTrace();
					}
					System.out.println("Statement executed. Result: " + result);
					writer.write("OK");
					writer.newLine();
					writer.flush();
				} else {
					connected = false;
				}
			} catch (java.net.SocketTimeoutException ste) {
				socketIdleTime = socketIdleTime + READ_TIMEOUT_FIVE_SECONDS;
				if (socketIdleTime >= MAX_CONNECTION_IDLE_TIME_HALF_MINUTE) {
					connected = false;
					System.out.println("Socket client connection will be closed due to inactivity.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			clientSocket.close();
			System.out.println("Socket client connection has been closed.");
		} catch (Exception e) {
			System.out.println("Failed to close socket client connection.");
		}
	}

}
