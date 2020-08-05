package com.xcs.phase2.model.prove;

import lombok.Data;

@Data
public class NewProveArrestProductJoin extends ProveModel {

    private int ARREST_PRODUCT_JOIN;
    private int INDICTMENT_ID_MAIN;
    private int INDICTMENT_ID_SUB;
    private int PRODUCT_ID;
    private int IS_ACTIVE;
    private int ARREST_ID;
    private int PRODUCT_INDICTMENT_ID_SUB;
    private int PRODUCT_INDICTMENT_ID_MAIN;


}
