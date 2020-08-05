package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasOffice extends MasterProductModel {

    private int OFFICE_ID;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private String OFFICE_SHORT_NAME;
    private int IS_ACTIVE;
}
