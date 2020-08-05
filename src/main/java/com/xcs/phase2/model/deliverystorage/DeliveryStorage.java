package com.xcs.phase2.model.deliverystorage;

import lombok.Data;

import java.util.List;

@Data
public class DeliveryStorage extends DeliveryStorageModel {

    private int ARREST_ID;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String OCCURRENCE_TIME;
    private String ARREST_STAFF_NAME;
    private String OPERATION_POS_NAME;
    private String OPERATION_OFFICE_SHORT_NAME;
    private String OPERATION_OFFICE_NAME;
    private String ARREST_OFFICE_NAME;
    private List<DeliveryStorageLawsuitDetail> DeliveryStorageLawsuitDetail;












}
