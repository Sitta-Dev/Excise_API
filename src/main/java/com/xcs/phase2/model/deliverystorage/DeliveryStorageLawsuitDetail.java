package com.xcs.phase2.model.deliverystorage;

import lombok.Data;

import java.util.List;

@Data
public class DeliveryStorageLawsuitDetail extends DeliveryStorageModel {

    private int ARREST_ID;
    private int INDICTMENT_ID;
    private int LAWSUIT_ID;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE;
    private String LAWSUIT_STAFF_NAME;
    private String OPERATION_POS_NAME;
    private String OPERATION_OFFICE_SHORT_NAME;
    private String OPERATION_OFFICE_NAME;
    private String LAWSUIT_OFFICE_NAME;
    private List<DeliveryStorageArrestLawbeaker> DeliveryStorageArrestLawbeaker;













}
