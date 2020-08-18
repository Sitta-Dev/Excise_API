package com.xcs.phase2.model.revenue;

import lombok.Data;

import java.util.List;

@Data
public class RevenueCompareArrestIndictmentDetail extends RevenueModel {

	private int INDICTMENT_DETAIL_ID;
	private int INDICTMENT_ID;
	private int LAWBREAKER_ID;
    private int IS_ACTIVE;
    private int IS_PROVE;
    private float FINE_ESTIMATE;
	private RevenueLawbreaker RevenueLawbreaker;
    private List<RevenueProductDetail> RevenueProductDetail;
	private List<RevenueNotice> RevenueNotice;

}
