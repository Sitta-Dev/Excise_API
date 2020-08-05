package com.xcs.phase2.request.investigate;

import lombok.Data;

@Data
public class InvestigateListgetByConAdvReq extends InvestigateRequest {

    private String INVESTIGATE_CODE;
    private String INVESTIGATE_NO;
    private String SUBJECT;
    private String DATE_START;
    private String DATE_END;
    private String STAFF_CODE;


}
