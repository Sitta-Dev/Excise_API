package com.xcs.phase2.model.prove;

import lombok.Data;

@Data
public class NewProveArrestIndictmentProduct extends ProveModel {

    private String PRODUCT_INDICTMENT_ID;
    private String PRODUCT_ID;
    private String INDICTMENT_ID;
    private String SIZES_UNIT_ID;
    private String QUATITY_UNIT_ID;
    private String VOLUMN_UNIT_ID;
    private float SIZES;
    private String SIZES_UNIT;
    private float QUANTITY;
    private String QUANTITY_UNIT;
    private float VOLUMN;
    private String VOLUMN_UNIT;
    private float FINE_ESTIMATE;
    private int IS_ILLEGAL;
    private int IS_ACTIVE;

}
