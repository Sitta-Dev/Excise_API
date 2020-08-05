package com.xcs.phase2.model.deliverystorage;

import lombok.Data;

import java.util.List;

@Data
public class DeliveryStorageEvidenceIn extends DeliveryStorageModel {

    private int EVIDENCE_IN_ID;
    private int ARREST_ID;
    private String DELIVERY_CODE;
    private String DELIVERY_NO;
    private String DELIVERY_DATE;
    private String DELIVERY_TIME;
    private String DELIVERY_OFFICE_CODE;
    private String DELIVERY_OFFICE_NAME;
    private String DELIVERY_TITTLE;
    private String DELIVERY_DEAR;
    private String REMARK;
    private List<DeliveryStorageEvidenceInItem> DeliveryStorageEvidenceInItem;
    private List<DeliveryStorageEvidenceInStaff> DeliveryStorageEvidenceInStaff;












}
