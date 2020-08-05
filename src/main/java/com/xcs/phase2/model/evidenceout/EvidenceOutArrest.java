package com.xcs.phase2.model.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutArrest extends EvidenceOutModel {

    private int ARREST_ID;
    private int OFFICE_ID;
    private String ARREST_CODE;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private String ARREST_DATE;
    private String OCCURRENCE_DATE;
    private String BEHAVIOR_1;
    private String BEHAVIOR_2;
    private String BEHAVIOR_3;
    private String BEHAVIOR_4;
    private String BEHAVIOR_5;
    private String TESTIMONY;
    private int IS_REQUEST;
    private String REQUEST_DESC;
    private int IS_LAWSUIT_COMPLETE;

}
