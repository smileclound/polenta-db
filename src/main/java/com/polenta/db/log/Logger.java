package com.polenta.db.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.zzz");
	
	public enum Level {
		DEBUG, 
		WARNING,
		ERROR,
		SYSTEM
	}
	
	private static Level logLevel;
	
	private Logger() {
		
	}

	public static void init(String level) {
		if (level == null) {
			logLevel = Level.SYSTEM;
		} else {
			logLevel = Level.valueOf(level.toUpperCase());
			if (logLevel == null) {
				logLevel = Level.SYSTEM;
			}
		}
	}

	public static void logDebug(String log) {
		if (logLevel.ordinal() == Level.DEBUG.ordinal()) {
			log(Level.DEBUG, log);
		}
	}

	public static void logWarning(String log) {
		if (logLevel.ordinal() <= Level.WARNING.ordinal()) {
			log(Level.WARNING, log);
		}
	}

	public static void logError(String log) {
		if (logLevel.ordinal() <= Level.ERROR.ordinal()) {
			log(Level.ERROR, log);
		}
	}

	public static void logSystem(String log) {
		log(Level.SYSTEM, log);
	}
	
	protected static void log(Level level, String log) {
		System.out.println(SDF.format(new Date()) + " - " + level.toString() + " - " + log);
	}

	public static Level getLevel() {
		return logLevel;
	}
	
	public static void setLevel(Level level) {
		logLevel = level;
	}

}
