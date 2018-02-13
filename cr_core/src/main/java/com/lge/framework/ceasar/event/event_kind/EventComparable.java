package com.lge.framework.ceasar.event.event_kind;

abstract public class EventComparable<T> implements Event<T>{
	private static final long serialVersionUID = 6669419847531233909L;
	protected T t;
	protected Object source;
	protected long time;
	
	public EventComparable(T t, Object source) {
		this.t = t;
		time = System.currentTimeMillis();
	}
	
	public EventComparable(T t) {
		this(t, DEFAULT_SOURCE);
	}
	
	@Override public T getTarget() { return t; }
	@Override public Object getSource() { return source; }
	@Override public Class<?> getTargetType(){ return (Class<?>) t.getClass(); }
	@Override public long getTime() { return time; }
	
	// 이벤트의 경우 Date를 기준으로 처리 우선순위를 결정하는데, 과거의 이벤트를 우선 처리해야 한다.
	@Override
	public int compareTo(Event<?> o) {
		return (time - o.getTime() < 0) ? 1 : time == o.getTime() ? 0 : -1;	// Date를 비교할 경우 Date가 작으면 과거의 이벤트 이므로 (-)를 붙여 과거의 Date가 우선하도록 한다.
	}
	
	@Override
	public String toString() {
		String eventName = getClass().getSimpleName();
		String targetName = t == null ? "<null>" : t.getClass().getSimpleName();
		String sourceName = source == null ? "<null>" : source.getClass().getSimpleName();
		
		return eventName + " for " + targetName + " from " + sourceName;
	}
}
