package com.xcs.phase2.response.master;

import java.util.List;

import com.xcs.phase2.response.arrest.ArrestStaffResponse;

import lombok.Data;

@Data
public class MasProductUnitMappinginsAllResponse extends MasterResponse {
	
	private String IsSuccess;
	private String Msg;
	private List<MasProductUnitMappingResponse> MasProductUnitMappingResponse;

}
