package com.xcs.phase2.model.investigate;

import lombok.Data;

@Data
public class InvestigateList extends InvestigateModel {

    private String INVESTIGATE_CODE;
    private String INVESTIGATE_NO;
    private String SUBJECT;
    private String DATE_START;
    private String DATE_END;
    private int INVESTIGATE_SEQUENCE;

}
