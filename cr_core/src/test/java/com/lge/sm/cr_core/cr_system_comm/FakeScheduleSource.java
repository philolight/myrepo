package com.lge.sm.cr_core.cr_system_comm;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FakeScheduleSource implements IScheduleSource{
	// delete(JSONObject target, String hostName) test 
	public boolean deleteOk = false;
	public boolean deleteReturnsNull = false;	
	public String userId;
	public String scheId;
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject delete(JSONObject target, String hostName) {
		userId = (String)target.get(Constants.KEY_BODY_USER_ID);
		scheId = (String)target.get(Constants.KEY_BODY_SCHEDULE_ID);
		
		if(userId == null || scheId == null) return null;

		if(deleteReturnsNull) return null;
		
		JSONObject ret = new JSONObject();
		JSONObject header = new JSONObject();
		ret.put(Constants.KEY_HEADER, header);
		if(deleteOk) header.put(Constants.KEY_HEADER_RETURN_DESC, Constants.STRING_RETURN_DESC_OK);
		else header.put(Constants.KEY_HEADER_RETURN_DESC, "notOk");
		
		return (JSONObject)ret;
	}

	// getScheduleListByRoom(JSONObject target, String hostName)를 위한 변수
	public String roomId;
	public String locationId;
	public String timeZoneId;
	public boolean getScheduleListByRoomReturnOk = false;
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getScheduleListByRoom(JSONObject target, String hostName) {
		this.roomId = (String)target.get(Constants.KEY_ROOM_ID);
		this.locationId = (String)target.get(Constants.KEY_LOCATION_ID);
		this.timeZoneId = (String)target.get(Constants.KEY_TIMEZONE_CODE);
		
		if(roomId == null || locationId == null || timeZoneId == null) return null;

		JSONObject ret = new JSONObject();
		JSONObject header = new JSONObject();
		JSONObject body = new JSONObject();
		JSONArray list = new JSONArray();
		ret.put(Constants.KEY_HEADER, header);
		if(getScheduleListByRoomReturnOk) header.put(Constants.KEY_HEADER_RETURN_DESC, Constants.STRING_RETURN_DESC_OK);
		else header.put(Constants.KEY_HEADER_RETURN_DESC, "notOk");
		ret.put(Constants.KEY_BODY, body);
		body.put(Constants.KEY_BODY_MEETING_LIST, list);
		
		for(int i = 1; i <= 3; i++) {
			JSONObject sche = new JSONObject();
			
			sche.put(Constants.KEY_BODY_DEPT_NAME, "dept" + i);
			sche.put(Constants.KEY_BODY_START_DATE, "20171111" + (10 + i) + "0000");
			sche.put(Constants.KEY_BODY_END_DATE, "20171111" + (10 + i) + "0000");
			sche.put(Constants.KEY_BODY_SCHEDULE_ID, "scheId" + i);
			sche.put(Constants.KEY_BODY_SCHEDULE_NAME, "scheNm" + i);
			sche.put(Constants.KEY_BODY_USER_ID, "userId" + i);
			sche.put(Constants.KEY_BODY_USER_NAME, "userNm" + i);
			list.add(sche);
		}

		return ret;
	}

	@Override
	public JSONObject getScheduleListAll(JSONObject target, String hostName) {

		return null;
	}

}
