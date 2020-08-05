package com.xcs.phase2.model.investigate;

import lombok.Data;

import java.util.List;

@Data
public class Investigate extends InvestigateModel {

    private int INVESTIGATE_ID;
    private String INVESTIGATE_CODE;
    private int INVESTIGATE_NO;
    private int INVESTIGATE_NO_YEAR;
    private String DATE_START;
    private String DATE_END;
    private String SUBJECT;
    private int IS_ACTIVE;
    private List<InvestigateDetail> InvestigateDetail;

}
