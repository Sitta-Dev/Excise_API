package com.xcs.phase2.request.master;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class New_MasLawGroupSubSectionRulegetByConAdvReq extends Request {

    private String SECTION_SECTION_ID;
    private String SUBSECTION_NAME;
    private String FINE_TYPE;
    private String GUILTBASE_NAME;
    private String FINE;
    private String PENALTY_SECTION_ID;


}
