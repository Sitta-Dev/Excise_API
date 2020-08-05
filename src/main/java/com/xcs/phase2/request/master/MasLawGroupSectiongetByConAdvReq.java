package com.xcs.phase2.request.master;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class MasLawGroupSectiongetByConAdvReq extends Request {

    private String LAW_GROUP_ID;
    private String PART_NO;
    private String SECTION_ID;

}
