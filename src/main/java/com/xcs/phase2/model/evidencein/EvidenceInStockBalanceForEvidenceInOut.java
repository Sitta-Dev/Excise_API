package com.xcs.phase2.model.evidencein;

import lombok.Data;

@Data
public class EvidenceInStockBalanceForEvidenceInOut extends EvidenceInModel {

    private int STOCK_ID;
    private int WAREHOUSE_ID;
    private int EVIDENCE_IN_ITEM_ID;
    private float RECEIVE_QTY;
    private String RECEIVE_QTY_UNIT;
    private float RECEIVE_SIZE;
    private String RECEIVE_SIZE_UNIT;
    private float RECEIVE_NET_VOLUMN;
    private String RECEIVE_NET_VOLUMN_UNIT;
    private float BALANCE_QTY;
    private String BALANCE_QTY_UNIT;
    private float BALANCE_SIZE;
    private String BALANCE_SIZE_UNIT;
    private float BALANCE_NET_VOLUMN;
    private String BALANCE_NET_VOLUMN_UNIT;
    private int IS_FINISH;
    private int IS_RECEIVE;
    private EvidenceInItem EvidenceInItem;
}
