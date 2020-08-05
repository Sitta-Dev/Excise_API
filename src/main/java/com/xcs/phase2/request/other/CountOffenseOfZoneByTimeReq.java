package com.xcs.phase2.request.other;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class CountOffenseOfZoneByTimeReq extends Request {

    private String LAWSUIT_DATE_FROM;
    private String LAWSUIT_DATE_TO;
}
