package com.xcs.phase2.model.master;

import lombok.Data;

import java.util.List;

@Data
public class MasLawGroupSection extends MasterProductModel {

    private int SECTION_ID;
    private int LAW_GROUP_ID;
    private String SECTION_NAME;
    private String SECTION_DESC_1;
    private String SECTION_DESC_2;
    private String SECTION_DESC_3;
    private int IS_ACTIVE;
    private String EFFECTIVE_DATE;
    private String EXPIRE_DATE;
    private List<MasLawGroupSubSection> masLawGroupSubSections;
   // private List<MasLawPenalty> masLawPenalties;
    private MasLawGroup masLawGroup;

}
