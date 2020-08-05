package com.xcs.phase2.response.lawsult;


import lombok.Data;
@Data
public class LawsuitMistreatNoResponse extends LawsuitResponse{

	private String IsSuccess;
	private String Msg;
	private Integer MISTREAT_NO;
}
