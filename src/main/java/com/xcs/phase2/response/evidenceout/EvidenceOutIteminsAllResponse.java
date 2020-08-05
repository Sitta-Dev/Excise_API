package com.xcs.phase2.response.evidenceout;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceOutIteminsAllResponse extends EvidenceOutResponse{

	private String IsSuccess;
	private String Msg;
	private List<EvidenceOutItemResponse> EvidenceOutItemResponse;
}
