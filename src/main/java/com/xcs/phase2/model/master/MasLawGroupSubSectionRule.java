package com.xcs.phase2.model.master;

import lombok.Data;

import java.util.List;

@Data
public class MasLawGroupSubSectionRule extends MasterProductModel {

    private int SUBSECTION_RULE_ID;
    private int SUBSECTION_ID;
    private int SECTION_ID;
    private int FINE_TYPE;
    private int IS_ACTIVE;
    private List<MasLawGuiltbase> masLawGuiltbase;
    private List<MasLawGuiltbaseFine> masLawGuiltbaseFines;

    private MasLawGroupSection masLawGroupSection;
    private MasLawGroupSubSection masLawGroupSubSection;


}
