package com.lge.framework.ceasar.logger;

public class PrintLogger implements LoggerInterface{
	@Override
	public void init(String path, LogLevel level) {
		// nothing
	}
	
	@Override
	public void debug(String src, String msg) {
		System.out.println("[DEBUG][" + src +"] " + msg);
	}
	
	@Override
	public void info(String src, String msg) {
		System.out.println("[INFO][" + src +"] " + msg);
	}

	@Override
	public void error(String src, String msg) {
		System.out.println("[ERROR][" + src +"] " + msg);
	}

	@Override
	public void fatal(String src, String msg) {
		System.out.println("[FATAL][" + src +"] " + msg);
	}
}
