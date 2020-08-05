package com.xcs.phase2.response.evidencein;


import lombok.Data;

import java.util.List;

@Data
public class EvidenceIninsAllResponse extends EvidenceInResponse{

	private String IsSuccess;
	private String Msg;
	private int EVIDENCE_IN_ID;
	private List<EvidenceInItemResponse> EvidenceInItem;
	private List<EvidenceInStaffResponse> EvidenceInStaff;
}
