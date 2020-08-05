package com.xcs.phase2.response.evidenceout;


import com.xcs.phase2.response.evidencein.EvidenceInResponse;
import lombok.Data;

import java.util.List;

@Data
public class EvidenceOutStaffinsAllResponse extends EvidenceInResponse{

	private String IsSuccess;
	private String Msg;
	private List<EvidenceOutStaffResponse> EvidenceOutStaff;
}
