package com.lge.sm.cr_core.task;

import com.lge.framework.ceasar.watchable.AbstractWatchable;

abstract public class Task extends AbstractWatchable implements ITask{	
	@Override
	protected void actualRun() {
		runTask();
	}
	
	abstract protected void runTask();
}
