package com.xcs.phase2.response.arrest;

import java.util.List;

import lombok.Data;

@Data
public class ArrestinsAllResponse extends ArrestResponse{

	private String IsSuccess;
	private String Msg;
	private int ARREST_ID;
	private List<ArrestStaffResponse> ArrestStaff;
	private List<ArrestLocaleResponse> ArrestLocale;
	private List<ArrestLawbreakerResponse> ArrestLawbreaker;
	private List<ArrestProductResponse> ArrestProduct;
}
