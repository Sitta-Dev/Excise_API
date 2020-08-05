package com.xcs.phase2.response.arrest;

import lombok.Data;

import java.util.List;

@Data
public class ArrestIndictmentDetailinsAllResponse extends ArrestResponse{

	private String IsSuccess;
	private String Msg;
	private List<ArrestIndictmentDetailResponse> ArrestIndictmentDetail;
}
