package com.xcs.phase2.request.uac;

import lombok.Data;

@Data
public class UserAccountListgetByConAdvReq extends UacRequest {

    private String USER_NAME;
    private String STAFF_CODE;
    private String STAFF_NAME;
    private String OPREATION_POS_NAME;
    private String MANAGEMENT_POS_NAME;
    private String REPRESENT_POS_NAME;
    private String OPERATION_DEPT_NAME;
    private String OPERATION_UNDER_DEPT_NAME;
    private String OPERATION_OFFICE_NAME;
    private String OPERATION_OFFICE_SHORT_NAME;

}
