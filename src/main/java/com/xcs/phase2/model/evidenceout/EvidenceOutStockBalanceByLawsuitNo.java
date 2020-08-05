package com.xcs.phase2.model.evidenceout;

import com.xcs.phase2.model.evidencein.EvidenceInItem;
import lombok.Data;

import java.util.List;

@Data
public class EvidenceOutStockBalanceByLawsuitNo extends EvidenceOutModel {


    private String DELIVERY_NO;
    private String DELIVERY_DATE;
    private String EVIDENCE_IN_DATE;
    private int EVIDENCE_IN_ID;
    private String LAWSUIT_NO;
    private List<EvidenceInItem> EvidenceInItem;

}
