package com.xcs.phase2.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,setterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility=JsonAutoDetect.Visibility.NONE)
public class MessageResponse {
	
	
	private String IsSuccess;
	private String Msg;
	
	public String getIsSuccess() {
		return IsSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		IsSuccess = isSuccess;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}

	
	
	

}
