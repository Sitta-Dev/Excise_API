package com.xcs.phase2.request.master;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class MasLawGroupSubSectionRulegetByConAdvReq extends Request {

    private String SECTION_NAME;
    private String SUBSECTION_NAME;
    private String GUILTBASE_NAME;
    private String SECTION_ID;
    private String FINE;
    private String FINE_TYPE;

}
