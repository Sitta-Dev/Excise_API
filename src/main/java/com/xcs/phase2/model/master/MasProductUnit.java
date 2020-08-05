package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductUnit extends MasterProductModel {

    private int UNIT_ID;
    private String UNIT_NAME_TH;
    private String UNIT_NAME_EN;
    private String UNIT_SHORT_NAME;
    private String CREATE_DATE;
    private int CREATE_USER_ACCOUNT_ID;
    private String UPDATE_DATE;
    private String EFEXPIRE_DATE;
    private int IS_ACTIVE;
    private String UNIT_CODE;

}
