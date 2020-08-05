package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasOperationPositionGetByConReq extends MasterRequest {

    private String OPERATION_POS_ID;
    private String TEXT_SEARCH;
}
