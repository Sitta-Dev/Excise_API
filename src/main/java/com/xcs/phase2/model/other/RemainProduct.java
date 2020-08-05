package com.xcs.phase2.model.other;

import lombok.Data;

@Data
public class RemainProduct extends OtherModel {

    private int REMAIN_PRODUCT_ID;
    private int ARREST_PRODUCT_ID;
    private float REMAIN_SIZES;
    private String REMAIN_SIZES_UNIT;
    private float REMAIN_QUANTITY;
    private String REMAIN_QUANTITY_UNIT;
    private float REMAIN_VOLUMN;
    private String REMAIN_VOLUMN_UNIT;
    private int ARREST_ID;
    private int IS_STATUS;
    private String PRODUCT_DESC;
    private String REMARK;
    private int IS_ACTIVE;


}
