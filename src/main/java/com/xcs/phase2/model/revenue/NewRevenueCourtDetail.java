package com.xcs.phase2.model.revenue;

import lombok.Data;

import java.util.List;

@Data
public class NewRevenueCourtDetail extends RevenueModel {

    private int LAWSUIT_ID;
    private int INDICTMENT_ID;
    private int OFFICE_ID;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private String LAWSUIT_NO;
    private int IS_LAWSUIT;
    private String LAWSUIT_DATE;
    private int IS_OUTSIDE;
    private int ARREST_ID;
    private List<NewRevenueLawsuitDetail> LawsuiltDetail;
    private List<NewRevenueNotice> Notice;
}
