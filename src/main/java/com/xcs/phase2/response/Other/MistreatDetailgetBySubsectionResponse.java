package com.xcs.phase2.response.Other;

import java.util.List;

import com.xcs.phase2.model.master.MasProductGroup;
import com.xcs.phase2.response.Response;

import lombok.Data;

@Data
public class MistreatDetailgetBySubsectionResponse extends Response{
	private int INDICTMENT_ID;
	private String ARREST_CODE;
	private String OCCURRENCE_DATE;
	private String ARREST_LOCALE;
	private String ARREST_STAFF;
	private String ARREST_STAFF_OFFICE;
	private String LAWSUIT_NO;
	private String LAWSUIT_STAFF;
	private String LAWSUIT_STAFF_OFFICE;
	private float PAYMENT_FINE;
	private List<MasProductGroup> ProductGroup;
	
}
