package com.xcs.phase2.response.investigate;

import lombok.Data;

import java.util.List;

@Data
public class InvestigateDetailinsAllResponse extends InvestigateResponse {

    private String IsSuccess;
    private String Msg;
    private int INVESTIGATE_DETAIL_ID;
    private List<InvestigateDetailStaffResponse> InvestigateDetailStaff;
    private List<InvestigateDetailLocaleResponse> InvestigateDetailLocale;
}
