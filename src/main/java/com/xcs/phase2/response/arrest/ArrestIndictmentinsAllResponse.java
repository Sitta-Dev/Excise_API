package com.xcs.phase2.response.arrest;

import java.util.List;

import lombok.Data;

@Data
public class ArrestIndictmentinsAllResponse extends ArrestResponse{

	private String IsSuccess;
	private String Msg;
	private List<ArrestIndictmentResponse> ArrestIndictment;
}
