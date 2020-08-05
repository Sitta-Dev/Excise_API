package com.xcs.phase2.request.provestorage;

import com.xcs.phase2.request.compare.CompareRequest;
import lombok.Data;

@Data
public class ProvestorageListgetByKeywordReq extends CompareRequest {

    private String TEXT_SEARCH;
    private String ACCOUNT_OFFICE_CODE;

}
