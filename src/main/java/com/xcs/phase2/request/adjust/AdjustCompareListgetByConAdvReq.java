package com.xcs.phase2.request.adjust;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class AdjustCompareListgetByConAdvReq extends Request {

    private String COMPARE_NO;
    private String COMPARE_NO_YEAR;
    private String COMPARE_DATE_FROM;
    private String COMPARE_DATE_TO;
    private String COMPARE_NAME;
    private String COMPARE_OFFICE_NAME;
    private Integer COMPARE_IS_OUTSIDE;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE_FROM;
    private String OCCURRENCE_DATE_TO;
    private String ARREST_NAME;
    private String ARREST_OFFICE_NAME;
    private String SUBSECTION_NAME;
    private String GUILTBASE_NAME;
    private Integer LAWSUIT_IS_OUTSIDE;
    private Integer LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;

    private String LAWSUIT_DATE_FROM;
    private String LAWSUIT_DATE_TO;

    private String LAWSUIT_OFFICE_NAME;
    private String LAWSUIT_NAME;
    private Integer PROVE_IS_OUTSIDE;
    private Integer PROVE_NO;
    private String PROVE_NO_YEAR;
    private String RECEIVE_DOC_DATE_FROM;
    private String RECEIVE_DOC_DATE_TO;
    private String RECEIVE_OFFICE_NAME;
    private String PROVE_NAME;
    private String ACCOUNT_OFFICE_CODE;

}
