package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.CompareCountMistreat;
import com.xcs.phase2.request.compare.CompareCountMistreatgetByConReq;

public interface CompareCountMistreatDAO {

    public CompareCountMistreat CompareCountMistreatgetByCon(CompareCountMistreatgetByConReq req);
}
