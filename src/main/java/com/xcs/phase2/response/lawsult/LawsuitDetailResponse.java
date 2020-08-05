package com.xcs.phase2.response.lawsult;

import lombok.Data;

import java.util.List;


@Data
public class LawsuitDetailResponse extends LawsuitResponse{

	private int LAWSUIT_DETAIL_ID;
	private List<LawsuitPaymentResponse> LawsuitPayment;
}
