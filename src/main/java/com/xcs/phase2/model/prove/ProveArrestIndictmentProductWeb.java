package com.xcs.phase2.model.prove;

import lombok.Data;

@Data
public class ProveArrestIndictmentProductWeb extends ProveModel {

    private int PRODUCT_ID;
    private int IS_SCIENCE;
    private int IS_PROVE;
    private String PRODUCT_DESC;
    private int OLD_QTY;

}
