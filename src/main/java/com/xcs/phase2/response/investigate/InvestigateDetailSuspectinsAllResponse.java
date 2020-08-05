package com.xcs.phase2.response.investigate;

import lombok.Data;

import java.util.List;

@Data
public class InvestigateDetailSuspectinsAllResponse extends InvestigateResponse {

    private String IsSuccess;
    private String Msg;
    private List<InvestigateDetailSuspectResponse> InvestigateDetailSuspect;
}
