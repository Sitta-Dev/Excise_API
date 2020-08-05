package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductCategoryGroupPRC extends MasterProductModel {


    private String DUTY_CODE;
    private String TYPE_CODE;
    private String SUBTYPE_CODE;
    private String PRODUCT_CODE;
    private String PRODUCT_NAME;
    private String BEGIN_DATE;
    private String END_DATE;
    private String UPD_USERID;
    private String UPD_DATE;
    private String PRC_PARAM;
    private String LAW_DUTY_CODE;
    private String DUTY_FLAG;
    private String SETTYPE_CODE;
    private String PRODUCT_CODE_OLD;
    private String NAME_DUTY;
    private String CATAGORY_FLAG;
    private int IS_ACTIVE;

}
