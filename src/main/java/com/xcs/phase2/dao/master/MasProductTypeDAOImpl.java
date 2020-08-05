package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductType;
import com.xcs.phase2.request.master.MasProductTypegetByConReq;
import com.xcs.phase2.request.master.MasProductTypeupdDeleteReq;
import com.xcs.phase2.response.master.MasProductTypeinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MasProductTypeDAOImpl extends MasterExt implements MasProductTypeDAO{

    private static final Logger log = LoggerFactory.getLogger(MasProductTypeDAOImpl.class);

    @Override
    public List<MasProductType> MasProductTypegetByCon(MasProductTypegetByConReq req) {
        return null;
    }

    @Override
    public MasProductTypeinsAllResponse MasProductTypeinsAll(MasProductType req) {
        return null;
    }

    @Override
    public Boolean MasProductTypeupdAll(MasProductType req) {
        return null;
    }

    @Override
    public Boolean MasProductTypeupdDelete(MasProductTypeupdDeleteReq req) {
        return null;
    }
}

