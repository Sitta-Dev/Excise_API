package com.xcs.phase2.model.evidence;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class EvidenceAccountWarehouse extends Request {

    private int INSIDE;
    private int DESTROY;
    private int SELL;
    private int LEND;
    private int KEEP;
    private int DONATE;
    private int TRANSFER;
}
