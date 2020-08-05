package com.xcs.phase2.model.uac;

import lombok.Data;

@Data
public class MasOperationPosition extends UacModel {

    private int OPERATION_POS_ID;
    private String OPERATION_POS_CODE;
    private String OPERATION_POS_NAME;
    private int IS_ACTIVE;
}
