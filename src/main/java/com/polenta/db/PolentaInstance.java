package com.polenta.db;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.polenta.db.processor.SocketProcessor;

public class PolentaInstance {

	private int port;
	
	protected PolentaInstance(int port) {
		this.port = port;
	}

	ServerSocket serverSocket;

	public final void start() {
		System.out.println("Starting Polenta...");
		System.out.println("Opening port " + this.port + ".");
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Impossible to open port " + this.port + ". Verify if it is not already in use.");
			return;
		}		
		System.out.println("Polenta running and waiting for statements and commands!");
		
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
			System.out.println("Polenta shutdown in progress.");
			System.exit(0);
		} catch (IOException e) {
			System.err.println("Polenta could not friendly close port + " + port + "!");
		}
	}
	
	
}