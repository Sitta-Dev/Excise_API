package com.xcs.phase2.request.prove;

import lombok.Data;

@Data
public class ProveVerifyProveNoReq extends ProveRequest {

    private String PROVE_NO;
    private String PROVE_NO_YEAR;
    private String RECEIVE_OFFICE_CODE;
    private String IS_OUTSIDE;
    private String PROVE_TYPE;

}
