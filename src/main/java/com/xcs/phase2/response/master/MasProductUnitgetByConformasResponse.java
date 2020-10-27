package com.xcs.phase2.response.master;

import java.util.List;

import com.xcs.phase2.model.master.MasProductUnitMapping;
import lombok.Data;

@Data
public class MasProductUnitgetByConformasResponse extends MasterResponse{
	
	private int UNIT_ID;
    private String UNIT_NAME_TH;
    private String UNIT_NAME_EN;
    private String UNIT_SHORT_NAME;
    private String UNIT_CODE;
	public List<MasProductUnitMapping> MasProductUnitMapping;

}
