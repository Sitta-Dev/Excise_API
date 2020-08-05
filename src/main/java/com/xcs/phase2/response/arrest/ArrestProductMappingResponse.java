package com.xcs.phase2.response.arrest;

import lombok.Data;

@Data
public class ArrestProductMappingResponse extends ArrestResponse{

	private int PRODUCT_ID;
	private int PRODUCT_MAPPING_ID ;
	private String PRODUCT_REF_CODE ;

}
