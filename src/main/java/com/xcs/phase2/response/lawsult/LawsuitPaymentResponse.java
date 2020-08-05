package com.xcs.phase2.response.lawsult;

import java.util.List;

import lombok.Data;

@Data
public class LawsuitPaymentResponse extends LawsuitResponse{

	private int PAYMENT_ID;
	private List<LawsuitPaymentDetailResponse> LawsuitPaymentDetail;
}
