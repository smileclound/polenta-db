package com.polenta.db.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.zzz");
	
	public enum Level {
		DEBUG, 
		INFO,
		WARNING,
		ERROR,
		SYSTEM
	}
	
	private static Level level;
	
	private Logger() {
		
	}

	public static void init(String l) {
		if (level == null) {
			level = Level.SYSTEM;
		} else {
			level = Level.valueOf(l.toUpperCase());
			if (level == null) {
				level = Level.SYSTEM;
			}
		}
	}

	public static void logDebug(String log) {
		if (level.ordinal() == Level.DEBUG.ordinal()) {
			log(Level.DEBUG, log);
		}
	}

	public static void logInfo(String log) {
		if (level.ordinal() <= Level.INFO.ordinal()) {
			log(Level.INFO, log);
		}
	}

	public static void logWarning(String log) {
		if (level.ordinal() <= Level.WARNING.ordinal()) {
			log(Level.WARNING, log);
		}
	}

	public static void logError(String log) {
		if (level.ordinal() <= Level.ERROR.ordinal()) {
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
		return level;
	}
	
	public static void setLevel(Level l) {
		level = l;
	}

}
