package com.xcs.phase2.model.master;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class MasProductGROUPCategoryForLiquor extends Request {

    private int PRODUCT_GROUP_ID;
    private String PRODUCT_GROUP_CODE;
    private String PRODUCT_GROUP_NAME;
    private int PRODUCT_CATEGORY_ID;
    private String PRODUCT_CATEGORY_CODE;
    private String PRODUCT_CATEGORY_NAME;

}
