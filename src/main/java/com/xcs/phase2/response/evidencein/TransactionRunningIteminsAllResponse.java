package com.xcs.phase2.response.evidencein;

import lombok.Data;

@Data
public class TransactionRunningIteminsAllResponse extends EvidenceInResponse {

    private String IsSuccess;
    private String Msg;
    private int RUNNING_ID;
}
