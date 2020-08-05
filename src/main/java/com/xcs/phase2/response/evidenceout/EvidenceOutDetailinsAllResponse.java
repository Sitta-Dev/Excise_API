package com.xcs.phase2.response.evidenceout;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceOutDetailinsAllResponse extends EvidenceOutResponse{

	private String IsSuccess;
	private String Msg;
	private List<EvidenceOutDetailResponse> EvidenceOutDetailResponse;
}
