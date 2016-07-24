package com.polenta.db;

public class Polenta {

	private static final int DEFAULT_PORT = 3110;

	public static void main(String[] args) {
		int _port = Polenta.DEFAULT_PORT;
		PolentaInstance polentaInstance = new PolentaInstance(_port);
		polentaInstance.start();
	}
	
}
