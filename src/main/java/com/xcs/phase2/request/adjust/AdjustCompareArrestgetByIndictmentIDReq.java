package com.xcs.phase2.request.adjust;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class AdjustCompareArrestgetByIndictmentIDReq extends Request {

    private Integer INDICTMENT_ID;
}
