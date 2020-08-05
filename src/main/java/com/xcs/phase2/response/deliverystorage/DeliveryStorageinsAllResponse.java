package com.xcs.phase2.response.deliverystorage;


import lombok.Data;

@Data
public class DeliveryStorageinsAllResponse extends DeliveryStorageResponse{

	private String IsSuccess;
	private String Msg;
	private int EVIDENCE_IN_ID;
}
