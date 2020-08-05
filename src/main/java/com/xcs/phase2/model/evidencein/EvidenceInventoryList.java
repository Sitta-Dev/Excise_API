package com.xcs.phase2.model.evidencein;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class EvidenceInventoryList extends Request {

    private int LAWSUIT_ID;
    private String LAWSUIT_NO;
    private int IS_OUTSIDE;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE;
    private String DELIVERY_DOC_NO_1;
    private String EVIDENCE_IN_DATE;
    private String EVIDENCE_IN_ITEM_CODE;
    private String PRODUCT_DESC;
    private int STOCK_ID;
    private float BALANCE_QTY;
    private String BALANCE_QTY_UNIT;
    private String EVIDENCE_IN_TYPE;
    private int EVIDENCE_IN_ID;
    private String OPERATION_OFFICE_CODE;
    private String OPERATION_OFFICE_NAME;
    private String DELIVERY_NO;
//    private String TITLE_NAME_TH;
//    private String FIRST_NAME;
//    private String LAST_NAME;
//    private String OPREATION_POS_NAME;
//    private int CONTRIBUTOR_ID;


}
