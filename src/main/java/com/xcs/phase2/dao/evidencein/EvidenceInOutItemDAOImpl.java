package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.request.evidencein.EvidenceInOutItemupdDeleteIsReturnReq;
import com.xcs.phase2.request.evidencein.EvidenceInOutItemupdIsReturnReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EvidenceInOutItemDAOImpl extends EvidenceInExt implements EvidenceInOutItemDAO{

    private static final Logger log = LoggerFactory.getLogger(EvidenceInOutItemDAOImpl.class);

    @Override
    public Boolean EvidenceInOutItemupdIsReturn(List<EvidenceInOutItemupdIsReturnReq> req) {


        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(EvidenceInOutItemupdIsReturnReq item : req){

                    StringBuilder sqlBuilder = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_OUT_ITEM SET " +
                                    " IS_RETURN = '1' " +
                                    " WHERE EVIDENCE_OUT_ITEM_ID = '"+item.getEVIDENCE_OUT_ITEM_ID()+"'");
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

                }
            }
        }

        return true;
    }

    @Override
    public Boolean EvidenceInOutItemupdDeleteIsReturn(List<EvidenceInOutItemupdDeleteIsReturnReq> req) {


        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(EvidenceInOutItemupdDeleteIsReturnReq item : req){

                    StringBuilder sqlBuilder = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_OUT_ITEM SET " +
                                    " IS_RETURN = '0' " +
                                    " WHERE EVIDENCE_OUT_ITEM_ID = '"+item.getEVIDENCE_OUT_ITEM_ID()+"'");
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

                }
            }
        }

        return true;
    }
}
