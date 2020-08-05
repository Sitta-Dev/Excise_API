package com.xcs.phase2.response.master;

import lombok.Data;

import java.util.List;

@Data
public class MasLawGroupSubSectionRuleinsAllResponse extends MasterResponse{

    private String IsSuccess;
    private String Msg;
    private int SUBSECTION_RULE_ID;
    private List<MasLawGuiltbaseResponse> masLawGuiltbaseResponse;
    private List<MasLawGuiltbaseFineResponse> masLawGuiltbaseFineResponses;
}
