package com.xcs.phase2.dao.master;

import java.util.List;

import com.xcs.phase2.model.master.MasProductUnitMapping;
import com.xcs.phase2.response.master.MasProductUnitMappinginsAllResponse;

public interface MasProductUnitMappingDAO {
	
	public MasProductUnitMappinginsAllResponse MasProductUnitMappinginsAll(List<MasProductUnitMapping> req); 
	public Boolean MasProductUnitMappingupdAll(List<MasProductUnitMapping> req); 
	public Boolean MasProductUnitMappingupdDelete(List<MasProductUnitMapping> req); 

}
