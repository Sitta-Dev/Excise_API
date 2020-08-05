package com.xcs.phase2.request.document;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class PersonRelationshipgetByPersonIdReq extends Request {

	private String ARREST_CODE;
	private int PERSON_ID;
}
