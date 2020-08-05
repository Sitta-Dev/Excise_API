package com.xcs.phase2.request.adjust;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class AdjustComparePaymentupdDeleteReq extends Request {
    private Integer PAYMENT_ID;
}
