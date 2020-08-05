package com.xcs.phase2.response.lawsult;

import java.util.List;

import lombok.Data;

@Data
public class LawsuitPaymentinsAllResposne extends LawsuitResponse{

	private String IsSuccess;
	private String Msg;
	private List<LawsuitPaymentResponse> LawsuitPayment;
}
