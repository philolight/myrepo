package com.lge.framework.ceasar.logger;

public interface LoggerInterface{
	public void init(String path, LogLevel level);
	public void debug(String src, String msg);
	public void info(String src, String msg);
	public void error(String src, String msg);
	public void fatal(String src, String msg);
}
