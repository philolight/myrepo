package com.lge.framework.ceasar.event.load;

public class Load {
	// 디스플레이용 변수
	private final LoadType type;
	private String name = "";
	private long latency;		// 이벤트를 처리하는데 걸린 총 시간
	private long times;			// 이벤트의 개수
	private float frequency;	// 초 당 발생 이벤트 수
	private long duration;		// 이벤트 수집 기간(ms)
	
	// 내부 변수
	private long count = 0; 	// 전달된 event 개수
	private long value = 0; 	// 처리하는데 걸린 시간(ms)
	private long lastTime = 0;	// 이전 주기의 시작 시각
	
	public Load(LoadType type) {
		this.type = type;
		lastTime = System.currentTimeMillis();
	}
	
	public LoadType getType() { return type; }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/** 새로운 load를 더하는 메소드 */
	public void add(long value, long count) {
		this.value += value;
		this.count += count;
	}
	
	/** load 연산 메소드 */
	public long calculate() {
		long now = System.currentTimeMillis();		
		long ret = value;
		duration = (now - lastTime);
		if(duration != 0) frequency = (count * 1000f) / duration;
		latency = value;
		times = count;
		
		value = 0;
		count = 0;
		lastTime = now;
		return ret;
	}
	
	/** 이벤트를 수집한 기간을 리턴하는 메소드.
	 *	calculate() 메소드는 주기적으로 호출되고 이전 데이터를 초기화 하게 된다.
	 *	duration은 이전 주기의 시작 시간부터 최근 주기의 시작시간까지의 간격을 의미한다.
	 **/
	public long duration() { return duration; }
	
	/** 이벤트를 모두 처리하는데 걸린 총 시간을 리턴하는 메소드 */
	public long latency() { return latency; }
	
	/** 초 당 이벤트 처리 개수를 리턴하는 메소드 */
	public Float frequency() {
		if(frequency != -1) return frequency;
		long now = System.currentTimeMillis();
		if(now - lastTime != 0) frequency = (count * 1000f) / (now - lastTime);
		return frequency;
	}
	
	/** 기간동안 이벤트 발생 개수를 리턴하는 메소드 */
	public long times() { return times;}
	
	@Override
	public String toString() {
		return "{ \"type\":\"" + type + "\", " +
				"\"name\":\"" + name + "\", " +
				"\"times\":\"" + times + "\", " +
				"\"load\":\"" + latency + "\", " +
				"\"frequency\":\"" + frequency + "\"}";
	}
}