package com.xcs.phase2.dao.other;

import com.xcs.phase2.model.other.*;
import com.xcs.phase2.request.other.*;

import java.util.List;

public interface OtherDAO {

    public List<CountOffenseOfZone> CountOffenseOfZoneByTime(CountOffenseOfZoneByTimeReq req);
    public List<CountOffenseOfArea> CountOffenseOfAreaByZone(CountOffenseOfAreaByZoneReq req);
    public List<LawbreakeNetwork> LawbreakeNetworkgetByCon(LawbreakeNetworkgetByConReq req);

    public List<TimeLineList> TimeLineListgetByCon(TimeLineListgetByConReq req);


    public Running LawsuitRunningLawsuitNo(RunningNoReq req);
    public Running ProveRunningProveNo(RunningNoReq req);
    public Running CompareRunningCompareNo(RunningNoReq req);
}
