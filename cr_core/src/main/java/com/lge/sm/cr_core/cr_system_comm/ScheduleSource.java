package com.lge.sm.cr_core.cr_system_comm;

import org.json.simple.JSONObject;

import com.lge.framework.pacific.restful_util.RestfulConnector;

public class ScheduleSource implements IScheduleSource{
	@Override
	public JSONObject delete(JSONObject target, String hostName) {
		return RestfulConnector.postRestfulApiWithJson(hostName, CrSystemApi.DELETE_SCHEDULE.getMethodType(), target);
	}

	@Override
	public JSONObject getScheduleListByRoom(JSONObject target, String hostName) {
		return RestfulConnector.postRestfulApiWithJson(hostName, CrSystemApi.GET_SCHEDULE_LIST_SINGLE_MEETING_ROOM.getMethodType(), target);
	}

	@Override
	public JSONObject getScheduleListAll(JSONObject target, String hostName) {
		return RestfulConnector.postRestfulApiWithJson(hostName, CrSystemApi.GET_SCHEDULE_LIST_MULTI_MEETING_ROOM.getMethodType(), target);
	}
}
