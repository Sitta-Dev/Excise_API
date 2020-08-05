package com.xcs.phase2.model.investigate;

import lombok.Data;

import java.util.List;

@Data
public class InvestigateDetail extends InvestigateModel {

    private int INVESTIGATE_DETAIL_ID;
    private String INVESTIGATE_ID;
    private int OFFICE_ID;
    private String INVESTIGATE_SEQUENCE;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private String DATE_START;
    private String DATE_END;
    private String INVESTIGATE_DETAIL_DESCRIPTION;
    private int CONFIDENCE_OF_NEWS;
    private int VALUE_OF_NEWS;
    private String COMMAND;
    private int IS_ACTIVE;
    private List<InvestigateDetailStaff> InvestigateDetailStaff;
    private List<InvestigateDetailSuspect> InvestigateDetailSuspect;
    private List<InvestigateDetailLocale> InvestigateDetailLocale;
    private List<InvestigateDetailProduct> InvestigateDetailProduct;

}
