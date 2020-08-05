package com.xcs.phase2.model.evidencein;

import lombok.Data;

@Data
public class EvidenceInOutItem extends EvidenceInModel {

    private int EVIDENCE_OUT_ITEM_ID;
    private int EVIDENCE_OUT_ID;
    private int STOCK_ID;
    private float QTY;
    private String QTY_UNIT;
    private float PRODUCT_SIZE;
    private String PRODUCT_SIZE_UNIT;
    private float NET_VOLUMN;
    private String NET_VOLUMN_UNIT;
    private int IS_RETURN;
    private int IS_ACTIVE;
    private EvidenceInStockBalanceForEvidenceInOut evidenceInStockBalance;
}
