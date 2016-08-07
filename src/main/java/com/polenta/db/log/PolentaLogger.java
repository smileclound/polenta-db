package com.polenta.db.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PolentaLogger {
	
	private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.zzz");
	
	public enum Level {
		DEBUG, 
		INFO,
		WARNING,
		ERROR,
		SYSTEM
	}
	
	private Level level;

	public PolentaLogger(String level) {
		if (level == null) {
			this.level = Level.SYSTEM;
		} else {
			this.level = Level.valueOf(level.toUpperCase());
			if (this.level == null) {
				this.level = Level.SYSTEM;
			}
		}
	}

	public void logDebug(String log) {
		if (this.level.ordinal() == Level.DEBUG.ordinal()) {
			this.log(Level.DEBUG, log);
		}
	}

	public void logInfo(String log) {
		if (this.level.ordinal() <= Level.INFO.ordinal()) {
			this.log(Level.INFO, log);
		}
	}

	public void logWarning(String log) {
		if (this.level.ordinal() <= Level.WARNING.ordinal()) {
			this.log(Level.WARNING, log);
		}
	}

	public void logError(String log) {
		if (this.level.ordinal() <= Level.ERROR.ordinal()) {
			this.log(Level.ERROR, log);
		}
	}

	public void logSystem(String log) {
		this.log(Level.SYSTEM, log);
	}
	
	protected void log(Level level, String log) {
		System.out.println(SDF.format(new Date()) + " - " + level.toString() + " - " + log);
	}

	public Level getLevel() {
		return this.level;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}

}
