package com.kiwihouse.common.httpclient;

import org.json.JSONException;
import org.json.JSONObject;


public class SendSmsResult extends SmsResultBase {

	public String Result;
	
	public String Reason;
	
	public long MsgId;
	
	public int SplitCount;
	
	public SendSmsResult() {
		Result = "-1";
		Reason = "未知";
		MsgId = 0;
		SplitCount = 0;
	}
	
	@Override
	public SendSmsResult parseFromHttpResponse(HttpResponse response) throws JSONException {
		
		JSONObject jsonObject = parseToJson(response);
		
		if(jsonObject.has("Result")) {
			Result = jsonObject.getString("Result");
		}
		if(jsonObject.has("Reason")) {
			Reason = jsonObject.getString("Reason");
		}
		if(jsonObject.has("MsgId")) {
			MsgId = jsonObject.getLong("MsgId");
		}
		if(jsonObject.has("SplitCount")) {
			SplitCount = jsonObject.getInt("SplitCount");
		}
		
		return this;
	}
	
}
