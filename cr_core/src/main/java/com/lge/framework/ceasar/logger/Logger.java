package com.lge.framework.ceasar.logger;

public class Logger {
	private static LoggerInterface logger = new PrintLogger();
	public static void setLogger(LoggerInterface logger) {
		
	}
	
	public static void init(String path, LogLevel level) {
		logger.init(path, level);
	}
	public static void debug(String src, String msg) {
		logger.debug(src, msg);
	}
	
	public static void info(String src, String msg) {
		logger.debug(src, msg);
	}
	
	public static void error(String src, String msg) {
		logger.error(src, msg);
	}
	
	public static void fatal(String src, String msg) {
		logger.fatal(src, msg);
	}
}
