package com.xcs.phase2.model.evidencein;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceInItem extends EvidenceInModel {

    private int EVIDENCE_IN_ITEM_ID;
    private String EVIDENCE_IN_ITEM_CODE;
    private int EVIDENCE_IN_ID;
    private int PRODUCT_MAPPING_ID;
    private String PRODUCT_CODE;
    private String PRODUCT_REF_CODE;
    private int PRODUCT_GROUP_ID;
    private int PRODUCT_CATEGORY_ID;
    private int PRODUCT_TYPE_ID;
    private int PRODUCT_SUBTYPE_ID;
    private int PRODUCT_SUBSETTYPE_ID;
    private int PRODUCT_BRAND_ID;
    private int PRODUCT_SUBBRAND_ID;
    private int PRODUCT_MODEL_ID;
    private int PRODUCT_TAXDETAIL_ID;
    private int PRODUCT_GROUP_CODE;
    private String PRODUCT_GROUP_NAME;
    private int PRODUCT_CATEGORY_CODE;
    private String PRODUCT_CATEGORY_NAME;
    private int PRODUCT_TYPE_CODE;
    private String PRODUCT_TYPE_NAME;
    private int PRODUCT_SUBTYPE_CODE;
    private String PRODUCT_SUBTYPE_NAME;
    private int PRODUCT_SUBSETTYPE_CODE;
    private String PRODUCT_SUBSETTYPE_NAME;
    private int PRODUCT_BRAND_CODE;
    private String PRODUCT_BRAND_NAME_TH;
    private String PRODUCT_BRAND_NAME_EN;
    private int PRODUCT_SUBBRAND_CODE;
    private String PRODUCT_SUBBRAND_NAME_TH;
    private String PRODUCT_SUBBRAND_NAME_EN;
    private int PRODUCT_MODEL_CODE;
    private String PRODUCT_MODEL_NAME_TH;
    private String PRODUCT_MODEL_NAME_EN;
    private String LICENSE_PLATE;
    private String ENGINE_NO;
    private String CHASSIS_NO;
    private String PRODUCT_DESC;
    private float SUGAR;
    private float CO2;
    private float DEGREE;
    private float PRICE;
    private float DELIVERY_QTY;
    private String DELIVERY_QTY_UNIT;
    private float DELIVERY_SIZE;
    private String DELIVERY_SIZE_UNIT;
    private float DELIVERY_NET_VOLUMN;
    private String DELIVERY_NET_VOLUMN_UNIT;
    private float DAMAGE_QTY;
    private String DAMAGE_QTY_UNIT;
    private float DAMAGE_SIZE;
    private String DAMAGE_SIZE_UNIT;
    private float DAMAGE_NET_VOLUMN;
    private String DAMAGE_NET_VOLUMN_UNIT;
    private int IS_DOMESTIC;
    private int IS_ACTIVE;
    private List<EvidenceInStockBalance> EvidenceOutStockBalance;


}
