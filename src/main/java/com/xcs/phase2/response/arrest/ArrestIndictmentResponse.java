package com.xcs.phase2.response.arrest;

import java.util.List;

import lombok.Data;

@Data
public class ArrestIndictmentResponse extends ArrestResponse{

	private int INDICTMENT_ID;
	private List<ArrestIndictmentDetailResponse> ArrestIndictmentDetail;
}
