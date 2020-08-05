package com.xcs.phase2.model.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutLawsuit extends EvidenceOutModel {

    private int LAWSUIT_ID;
    private int INDICTMENT_ID;
    private int OFFICE_ID;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private int IS_LAWSUIT;
    private String REMARK_NOT_LAWSUIT;
    private int LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE;
    private String TESTIMONY;
    private String DELIVERY_DOC_NO_1;
    private String DELIVERY_DOC_NO_2;
    private String DELIVERY_DOC_DATE;
    private int IS_OUTSIDE;
    private int IS_SEIZE;
    private EvidenceOutArrest EvidenceOutArrest;
    private EvidenceOutGuiltbase EvidenceOutGuiltbase;

}
