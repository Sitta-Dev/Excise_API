package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasStaff;
import com.xcs.phase2.request.master.MasStaffgetByConAdvReq;
import com.xcs.phase2.request.master.MasStaffgetByConReq;
import com.xcs.phase2.response.master.MasStaffgetByConResponse;

import java.util.List;

public interface MasStaffDAO {

    public List<MasStaff> MasStaffgetByCon(MasStaffgetByConReq req);
    public MasStaffgetByConResponse MasStaffgetByConNew(MasStaffgetByConReq req);
    public MasStaff MasStaffgetById(int staffId);
    public List<MasStaff> MasStaffgetByConAdv(MasStaffgetByConAdvReq req);
}
