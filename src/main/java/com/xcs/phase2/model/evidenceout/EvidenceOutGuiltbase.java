package com.xcs.phase2.model.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutGuiltbase extends EvidenceOutModel {

    private int GUILTBASE_ID;
    private int SUBSECTION_RULE_ID;
    private String GUILTBASE_NAME;
    private String FINE;
    private int IS_PROVE;
    private int IS_COMPARE;
    private String REMARK;
    private int IS_ACTIVE;
    private EvidenceOutGroupSubsectionRule EvidenceOutGroupSubsectionRule;

}
