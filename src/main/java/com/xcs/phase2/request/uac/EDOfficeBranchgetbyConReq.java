package com.xcs.phase2.request.uac;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class EDOfficeBranchgetbyConReq extends Request {

    private String ACCOUNT_OFFICE_CODE;
}
