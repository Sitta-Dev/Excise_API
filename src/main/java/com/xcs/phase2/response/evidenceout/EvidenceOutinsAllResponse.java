package com.xcs.phase2.response.evidenceout;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceOutinsAllResponse extends EvidenceOutResponse{

	private String IsSuccess;
	private String Msg;
	private int EVIDENCE_OUT_ID;
	private List<EvidenceOutItemResponse> EvidenceOutItemResponse;
	private List<EvidenceOutDetailResponse> EvidenceOutDetailResponse;
	private List<EvidenceOutStaffResponse> EvidenceOutStaffResponse;
}
