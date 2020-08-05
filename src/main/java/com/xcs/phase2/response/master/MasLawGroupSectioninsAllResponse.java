package com.xcs.phase2.response.master;

import lombok.Data;

import java.util.List;

@Data
public class MasLawGroupSectioninsAllResponse extends MasterResponse{

    private String IsSuccess;
    private String Msg;
    private int SECTION_ID;
    private List<MasLawGroupSubSectionResponse> masLawGroupSubSection;
    private List<MasLawPenaltyResponse> masLawPenalty;
}
