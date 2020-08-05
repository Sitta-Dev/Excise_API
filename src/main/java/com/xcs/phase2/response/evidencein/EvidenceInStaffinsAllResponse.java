package com.xcs.phase2.response.evidencein;


import lombok.Data;

import java.util.List;

@Data
public class EvidenceInStaffinsAllResponse extends EvidenceInResponse{

	private String IsSuccess;
	private String Msg;
	private List<EvidenceInStaffResponse> EvidenceInStaff;
}
