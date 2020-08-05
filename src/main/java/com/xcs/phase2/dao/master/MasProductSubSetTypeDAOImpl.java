package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductSubSetType;
import com.xcs.phase2.request.master.MasProductSubSetTypegetByConReq;
import com.xcs.phase2.request.master.MasProductSubSetTypeupdDeleteReq;
import com.xcs.phase2.response.master.MasProductSubSetTypeinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MasProductSubSetTypeDAOImpl extends MasterExt implements MasProductSubSetTypeDAO{

    private static final Logger log = LoggerFactory.getLogger(MasProductSubSetTypeDAOImpl.class);


    @Override
    public List<MasProductSubSetType> MasProductSubSetTypegetByCon(MasProductSubSetTypegetByConReq req) {
        return null;
    }

    @Override
    public MasProductSubSetTypeinsAllResponse MasProductSubSetTypeinsAll(MasProductSubSetType req) {
        return null;
    }

    @Override
    public Boolean MasProductSubSetTypeupdAll(MasProductSubSetType req) {
        return null;
    }

    @Override
    public Boolean MasProductSubSetTypeupdDelete(MasProductSubSetTypeupdDeleteReq req) {
        return null;
    }
}
