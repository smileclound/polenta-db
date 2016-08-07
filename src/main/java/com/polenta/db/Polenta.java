package com.polenta.db;

import com.polenta.db.exception.PolentaException;
import com.polenta.db.log.PolentaLogger;

public class Polenta {

	private static final int DEFAULT_PORT = 3110;
	
	public static PolentaLogger logger;
	
	public static void main(String[] args) throws Exception {
		try {
			PolentaLogger logger = new PolentaLogger(Polenta.extractLogLevelFromArguments(args));
			
			PolentaInstance polentaInstance = new PolentaInstance(Polenta.extractPortFromArguments(args), logger);
			polentaInstance.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(-1);
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
