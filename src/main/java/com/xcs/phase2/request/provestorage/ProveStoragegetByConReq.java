package com.xcs.phase2.request.provestorage;

import com.xcs.phase2.request.compare.CompareRequest;
import lombok.Data;

@Data
public class ProveStoragegetByConReq extends CompareRequest {

    private String EVIDENCE_IN_ID;
}
