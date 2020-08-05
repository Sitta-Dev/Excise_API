package com.xcs.phase2.model.prove;

import lombok.Data;

@Data
public class ProveScience extends ProveModel {

    private int SCIENCE_ID;
    private int PROVE_ID;
    private String SCIENCE_CODE;
    private String DELIVERY_DOC_NO_1;
    private String DELIVERY_DOC_NO_2;
    private String DELIVERY_DOC_DATE;
    private String REQUEST_DOC_NO;
    private String REQUEST_DOC_DATE;
    private String RESULT_DOC_NO;
    private String RESULT_DOC_DATE;
    private String SCIENCE_RESULT_DESC;
    private int IS_ACTIVE;

}
