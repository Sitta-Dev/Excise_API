package com.xcs.phase2.response.lawsult;

import java.util.List;

import lombok.Data;

@Data
public class LawsuitinsAllResponse extends LawsuitResponse{

	private String IsSuccess;
	private String Msg;
	private int LAWSUIT_ID;
	private List<LawsuitStaffResponse> LawsuitStaff;
	private List<LawsuitDetailResponse> LawsuitDetail;
}
