package com.lge.framework.ceasar.watchable;

import java.util.List;

public interface Watchable {
	/** Watchable의 동작 기준이 되는 TimeZoneId에 대한 수정자 */
	public void setTimeZoneId(String timeZoneId);	
	
	/** Watchable의 동작 기준이 되는 TimeZoneId에 대한 접근자 */
	public String getTimeZoneId();	
	
	/** Watchable의 Id 
	 * 	일반적으로 Watchable의 Id는 클래스명이 될 것이다.
	 *  동일한 타입의 객체가 여럿일 경우, 상위 Watchable로부터 셋팅될 것임.
	 **/
	public String getWatchableId();
	
	/** Watchable의 상위 Id를 읽는 메소드. Watchable은 보통 Task이고, Task의 상위는 보통 Service이다. */
	public String getUpperWatchableId();
	
	/** Watchable의 상위 Id를 설정하는 메소드. Watchable은 보통 Task이고, Task의 상위는 보통 Service이다.*/
	public void setUpperWatchableId(String upperWatchableId);
	
	/** Watchable이 마지막으로 수정된 시각
	 *	Watchable의 수행 시간을 추적할 때 변경사항이 발생하여 수행 시간이 변경된 경우를 파악하기 위해서 필요하다.
	 *  형식 : yyyyMMdd HHmmss
	 **/
	public String getModifiedTime();
	
	/** Watchable이 마지막으로 실행된 시각 */
	public String getLastStartTime();
	
	/** Watchable이 마지막으로 실행되었을 때 걸린 시간 + 시간 단위 */
	public String getLastLatency();
	
	/** Watchable이 실행된 회수 */
	public long getRunCount();
	
	/** 종속된 Watchable의 리스트 */
	public List<Watchable> getKids();
	
	/** WatchableType을 리턴 */
	public WatchableType getWatchableType(); 
	
	/** 2회 실행시 각 실행 사이의 시간 간격 + 시간 단위*/
	public String getPeriod();
	
	/** run의 시작시 호출*/
	public void runBegin();
	
	/** run의 끝에 호출*/
	public void runEnd();
	
	public WatchableStatus getWatchableStatus();
	
	public String getFaultCause();
	
	public String faultTime();
}
