package com.xcs.phase2.request.deliverystorage;

import com.xcs.phase2.request.compare.CompareRequest;
import lombok.Data;

@Data
public class DeliverstorageListgetByKeywordReq extends CompareRequest {

    private String TEXT_SEARCH;
    private String ACCOUNT_OFFICE_CODE;

}
