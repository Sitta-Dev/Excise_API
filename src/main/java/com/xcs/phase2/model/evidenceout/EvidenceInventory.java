package com.xcs.phase2.model.evidenceout;

import lombok.Data;

@Data
public class EvidenceInventory extends EvidenceOutModel {

    private String REPRESENT_OFFICE_CODE;
    private String REPRESENT_OFFICE_NAME;
    private String EVIDENCE_IN_ITEM_CODE;
    private String PRODUCT_DESC;
    private float BALANCE_QTY;
    private String BALANCE_QTY_UNIT;
    private int EVIDENCE_IN_TYPE;
    private String DELIVERY_NO;
    private String TITLE_NAME_TH;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String REPRESENT_POS_NAME;
    private int CONTRIBUTOR_ID;

}
