package com.xcs.phase2.model.prove;

import lombok.Data;

@Data
public class Prove extends ProveModel {

    private int PROVE_ID;
    private int LAWSUIT_ID;
    private int DELIVERY_OFFICE_ID;
    private int RECEIVE_OFFICE_ID;
    private int PROVE_TYPE;
    private String DELIVERY_DOC_NO_1;
    private String DELIVERY_DOC_NO_2;
    private String DELIVERY_DOC_DATE;
    private String DELIVERY_OFFICE_CODE;
    private String DELIVERY_OFFICE_NAME;
    private String RECEIVE_OFFICE_CODE;
    private String RECEIVE_OFFICE_NAME;
    private String PROVE_NO;
    private String PROVE_NO_YEAR;
    private String RECEIVE_DOC_DATE;
    private String COMMAND;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String OCCURRENCE_DATE;
    private String OUT_OFFICE_NAME;
    private int IS_OUTSIDE;
    private int IS_SCIENCE;
    private int IS_RECEIVE;
    private int IS_ACTIVE;
    private String PROVE_DATE;
    private String DELIVERY_PROVE_DOC_NO_1;
    private String DELIVERY_PROVE_DOC_NO_2;
    private String PROVE_RESULT;

}
