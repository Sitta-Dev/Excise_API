package com.xcs.phase2.request.compare;

import lombok.Data;

@Data
public class CompareDetailCheckReceriptReq extends CompareRequest{
	private int RECEIPT_BOOK_NO;
	private int RECEIPT_NO;

}
