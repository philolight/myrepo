package com.lge.sm.cr_core.cr_system_comm;

import org.json.simple.JSONObject;

public interface IScheduleSource {
	public JSONObject delete(JSONObject target, String hostName);
	public JSONObject getScheduleListByRoom(JSONObject target, String hostName);
	public JSONObject getScheduleListAll(JSONObject target, String hostName);
}
