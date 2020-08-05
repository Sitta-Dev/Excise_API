package com.xcs.phase2.model.deliverystorage;

import lombok.Data;

@Data
public class DeliveryStorageList extends DeliveryStorageModel {

    private String ARREST_ID;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String LAWSUIT_NO;
    private String LAWSUIT_DATE;
    private String DELIVERY_NO;
    private String DELIVERY_DATE;
    private String DELIVERY_FULL_NAME;
    private String DELIVERY_OFFICE_NAME;
    private int IS_RECEIVE;
    private String IS_RECEIVE_TEXT;
    private int EVIDENCE_IN_ID;
    private String OCCURRENCE_DATE_SORT;
    private String LAWSUIT_DATE_SORT;
    private String DELIVERY_DATE_SORT;

}
