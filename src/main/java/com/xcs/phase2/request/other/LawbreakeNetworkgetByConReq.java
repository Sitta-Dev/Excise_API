package com.xcs.phase2.request.other;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class LawbreakeNetworkgetByConReq extends Request {

    private int LAWBREAKER_ID;
    private String ARREST_ID;
}
