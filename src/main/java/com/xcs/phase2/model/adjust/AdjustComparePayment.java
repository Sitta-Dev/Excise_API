package com.xcs.phase2.model.adjust;

import lombok.Data;

import java.util.List;

@Data
public class AdjustComparePayment extends AdjustModel {

    private	int	PAYMENT_ID	;
    private	int	LAWSUIT_DETAIL_ID	;
    private	int	COMPARE_DETAIL_ID	;
    private	int	FINE_TYPE	;
    private	float	FINE	;
    private	int	PAYMENT_PERIOD_NO	;
    private String PAYMENT_DATE	;
    private	int	IS_REQUEST_REWARD	;
    private	int	IS_ACTIVE	;
    private List<AdjustComparePaymentDetail> AdjustComparePaymentDetail;

}
