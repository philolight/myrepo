package com.lge.framework.ceasar.startable;

public interface Startable {
	public String getStartableId();
	public StartableType getStartableType();	
	public StartableStatus getStartableStatus();
	public void setStatus(StartableStatus status);
	
	/** boolean init() : Run interface에서 객체 초기화를 담당하는 메소드. 
	 *	@return true == success, false == fail 
	 */
	public boolean init();
	
	/**
	 * boolean init() : Run interface에서 객체를 시작하는 메소드.
	 * @return true == success, false == fail
	 */
	public boolean start();
	
	/**
	 * boolean init() : Run interface에서 객체의 동작을 멈추는 메소드.
	 * @return true == success, false == fail
	 */
	public boolean stop();
}
