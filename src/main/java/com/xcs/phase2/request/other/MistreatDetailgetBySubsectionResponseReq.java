package com.xcs.phase2.request.other;

import com.xcs.phase2.request.Request;

import lombok.Data;
@Data
public class MistreatDetailgetBySubsectionResponseReq extends Request{
	private int PERSON_ID;
	private int SUBSECTION_ID;
	private String OCCURRENCE_DATE;

}
