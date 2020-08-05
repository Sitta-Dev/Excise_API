package com.xcs.phase2.response.evidencein;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceInIteminsAllResponse extends EvidenceInResponse{

	private String IsSuccess;
	private String Msg;
	private List<EvidenceInItemResponse> EvidenceInItem;
}
