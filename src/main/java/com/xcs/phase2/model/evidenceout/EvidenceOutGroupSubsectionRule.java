package com.xcs.phase2.model.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutGroupSubsectionRule extends EvidenceOutModel {

    private int SUBSECTION_RULE_ID;
    private int SUBSECTION_ID;
    private int SECTION_ID;
    private int FINE_TYPE;
    private int IS_ACTIVE;
    private EvidenceOutGroupSubsection EvidenceOutGroupSubsection;

}
