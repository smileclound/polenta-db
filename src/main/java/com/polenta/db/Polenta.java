package com.polenta.db;

import com.polenta.db.exception.PolentaException;

public class Polenta {

	private static final int DEFAULT_PORT = 3110;

	public static void main(String[] args) throws Exception {
		PolentaInstance polentaInstance = new PolentaInstance(extractPortFromArguments(args));
		polentaInstance.start();
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
	
}
