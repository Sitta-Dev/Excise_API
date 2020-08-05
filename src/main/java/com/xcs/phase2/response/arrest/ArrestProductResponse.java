package com.xcs.phase2.response.arrest;

import lombok.Data;

import java.util.List;

@Data
public class ArrestProductResponse extends ArrestResponse{

	private int PRODUCT_ID;
	private int PRODUCT_MAPPING_ID ;
	private String PRODUCT_REF_CODE ;
	private List<ArrestProductMappingResponse> ArrestProductMapping;
}
