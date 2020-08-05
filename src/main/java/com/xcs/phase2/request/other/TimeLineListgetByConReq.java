package com.xcs.phase2.request.other;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class TimeLineListgetByConReq extends Request {

    private String NOTICE_CODE;
    private String ARREST_CODE;
    private String LAWSUIT_ID;
    private String PROVE_ID;
    private String COMPARE_ID;
    private String REVENUE_ID;
    private String BRIBE_REWARD_ID;
}
