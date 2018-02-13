package com.lge.sm.cr_core.task;

import com.lge.framework.ceasar.watchable.Watchable;

public interface ITask extends Watchable{
	public void init();
	
	/** ITask의 run은 주기적 혹은 비 주기적으로 호출되는 Task의 1회 실행을 의미함.
	 * 1회 실행은 주기적 혹은 비주기적으로 반복 호출되고 호출될 때마다 실행시간이 관리됨.
	 **/
	public void run();
}