package com.xcs.phase2.request.prove;

import lombok.Data;

@Data
public class ProveListgetByConAdvReq extends ProveRequest {

    private String ARREST_CODE;
    private String OCCURRENCE_DATE_FROM;
    private String OCCURRENCE_DATE_TO;
    private String ARREST_STAFF_NAME;
    private String LAWBREAKER_STAFF_NAME;
    private String SECTION_NAME;
    private String GUILTBASE_NAME;
    private String ARREST_OFFICE_NAME;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_IS_OUTSIDE;
    private String LAWSUIT_DATE_FROM;
    private String LAWSUIT_DATE_TO;
    private String LAWSUIT_STAFF_NAME;
    private String LAWSUIT_TYPE;
    private String PROVE_STATUS;
    private String PROVE_NO;
    private String PROVE_NO_YEAR;
    private String PROVE_IS_OUTSIDE;
    private String PROVE_DATE_FROM;
    private String PROVE_DATE_TO;
    private String PROVE_STAFF_NAME;
    private String RECEIVE_OFFICE_NAME;
    private String ACCOUNT_OFFICE_CODE;

}
