package com.xcs.phase2.response.investigate;

import lombok.Data;

import java.util.List;

@Data
public class InvestigateDetailStaffinsAllResponse extends InvestigateResponse {

    private String IsSuccess;
    private String Msg;
    private List<InvestigateDetailStaffResponse> InvestigateDetailStaff;
}
