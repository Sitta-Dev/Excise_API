package com.xcs.phase2.model.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutGroupSubsection extends EvidenceOutModel {

    private int SUBSECTION_ID;
    private int SECTION_ID;
    private String SUBSECTION_NO;
    private String SUBSECTION_NAME;
    private String SUBSECTION_DESC;
    private int IS_ACTIVE;

}
