package com.xcs.phase2.request.other;

import com.xcs.phase2.request.lawsult.LawsuitRequest;
import lombok.Data;

@Data
public class RunningNoReq extends LawsuitRequest {

	private String OFFICE_CODE;
	private String YEAR;
	private String IS_OUTSIDE;
}
