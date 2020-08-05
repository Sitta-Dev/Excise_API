package com.xcs.phase2.request.deliverystorage;

import com.xcs.phase2.request.compare.CompareRequest;
import lombok.Data;

@Data
public class DeliverstorageListgetByConAdvReq extends CompareRequest {

    private String ARREST_CODE;
    private String OCCURRENCE_DATE_START;
    private String OCCURRENCE_DATE_END;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE_START;
    private String LAWSUIT_DATE_END;
    private String DELIVERY_NO;
    private String DELIVERY_DATE_START;
    private String DELIVERY_DATE_END;
    private String DELIVERY_NAME;
    private String DELIVERY_OFFICE_NAME;
    private String IS_RECEIVE;
    private String ACCOUNT_OFFICE_CODE;






}
