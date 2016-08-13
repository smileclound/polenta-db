package com.polenta.db.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.polenta.db.log.Logger;

public class SocketServer {

	private int port;
	private boolean open = false;
	private static ServerSocket serverSocket;
	
	public SocketServer(int port) {
		this.port = port;
	}
	
	public void open() throws IOException  {
		serverSocket = new ServerSocket(port);
		this.open = true;
		Thread serverThread = new Thread() {
			@Override
			public void run() {
				while (open) {
					try {
						Socket clientSocket = serverSocket.accept();
						//
						StatementListener listener = new StatementListener(clientSocket);
						//
						Thread thread = new Thread(listener);
						thread.start();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					serverSocket.close();
				} catch (Exception e) {
					Logger.logError("Polenta could not friendly close port in use!");
				}
			}
		};
		serverThread.start();
	}
	
	public void close() {
		this.open = false;
	}
	
}
