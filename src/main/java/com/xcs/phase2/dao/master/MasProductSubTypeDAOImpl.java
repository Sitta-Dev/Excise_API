package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductSubType;
import com.xcs.phase2.request.master.MasProductSubTypegetByConReq;
import com.xcs.phase2.request.master.MasProductSubTypeupdDeleteReq;
import com.xcs.phase2.response.master.MasProductSubTypeinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MasProductSubTypeDAOImpl extends MasterExt implements MasProductSubTypeDAO{

    private static final Logger log = LoggerFactory.getLogger(MasProductSubTypeDAOImpl.class);

    @Override
    public List<MasProductSubType> MasProductSubTypegetByCon(MasProductSubTypegetByConReq req) {
        return null;
    }

    @Override
    public MasProductSubTypeinsAllResponse MasProductSubTypeinsAll(MasProductSubType req) {
        return null;
    }

    @Override
    public Boolean MasProductSubTypeupdAll(MasProductSubType req) {
        return null;
    }

    @Override
    public Boolean MasProductSubTypeupdDelete(MasProductSubTypeupdDeleteReq req) {
        return null;
    }
}
