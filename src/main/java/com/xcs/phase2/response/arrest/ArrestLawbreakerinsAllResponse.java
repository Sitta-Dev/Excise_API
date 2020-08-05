package com.xcs.phase2.response.arrest;

import java.util.List;

import lombok.Data;

@Data
public class ArrestLawbreakerinsAllResponse extends ArrestResponse{

	private String IsSuccess;
	private String Msg;
	private List<ArrestLawbreakerResponse> ArrestLawbreaker;
}
