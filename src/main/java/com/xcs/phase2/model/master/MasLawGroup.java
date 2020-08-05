package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasLawGroup extends MasterProductModel {

    private int LAW_GROUP_ID;
    private String LAW_GROUP_NO;
    private String LAW_GROUP_NAME;
    private String PART_NO;
    private String PART_NAME;
    private int IS_ACTIVE;
    private String EFFECTIVE_DATE;
    private String EXPIRE_DATE;


}
