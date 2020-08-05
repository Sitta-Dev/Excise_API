package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasLaw extends MasterProductModel {

    private int LAW_GROUP_ID;
    private String LAW_GROUP_NO;
    private String LAW_GROUP_NAME;
    private String PART_NO;
    private String PART_NAME;
    private int SECTION_ID;
    private String SECTION_NAME;


}
