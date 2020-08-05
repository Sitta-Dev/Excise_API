package com.xcs.phase2.model.evidencein;

import com.xcs.phase2.request.Request;
import lombok.Data;

import java.util.List;

@Data
public class EvidenceInventoryListNew extends Request {

    private int LAWSUIT_ID;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private int PROVE_ID;
    private List<EvidenceInNew> EvidenceIn;

}
