package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class ArrestNoticeupdByConReq extends ArrestRequest {

	private int NOTICE_ID;
	private int ARREST_ID;
}
