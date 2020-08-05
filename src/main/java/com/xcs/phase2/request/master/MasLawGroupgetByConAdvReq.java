package com.xcs.phase2.request.master;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class MasLawGroupgetByConAdvReq extends Request {

    private String LAW_GROUP_NAME;
    private String PART_NAME;
}
