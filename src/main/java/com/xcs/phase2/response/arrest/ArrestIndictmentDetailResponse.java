package com.xcs.phase2.response.arrest;

import lombok.Data;

import java.util.List;

@Data
public class ArrestIndictmentDetailResponse extends ArrestResponse{

	private int INDICTMENT_DETAIL_ID;
	private List<ArrestIndictmentProductResponse> ArrestIndictmentProduct;
}

