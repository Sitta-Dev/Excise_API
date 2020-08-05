package com.xcs.phase2.model.prove;

import lombok.Data;

import java.util.List;

@Data
public class NewProveArrestIndictment extends ProveModel {

    private int INDICTMENT_ID;
    private int ARREST_ID;
    private int GUILTBASE_ID;
    private float FINE_ESTIMATE;
    private int IS_LAWSUIT_COMPLETE;
    private int IS_ACTIVE;

    private List<NewProveArrestProduct> ProveArrestProduct;

}
