package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasOperationPosition extends MasterProductModel {

    private int OPERATION_POS_ID;
    private String OPERATION_POS_CODE;
    private String OPERATION_POS_NAME;
    private int IS_ACTIVE;


}
