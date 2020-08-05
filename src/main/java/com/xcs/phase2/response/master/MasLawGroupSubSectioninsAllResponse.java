package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasLawGroupSubSectioninsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int SUBSECTION_ID;
}
