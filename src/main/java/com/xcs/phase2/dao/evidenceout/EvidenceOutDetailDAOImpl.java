package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.evidenceout.EvidenceOutDetail;
import com.xcs.phase2.request.evidenceout.EvidenceOutDetailupdDeleteReq;
import com.xcs.phase2.response.evidenceout.EvidenceOutDetailResponse;
import com.xcs.phase2.response.evidenceout.EvidenceOutDetailinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EvidenceOutDetailDAOImpl extends EvidenceOutExt implements EvidenceOutDetailDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutDetailDAOImpl.class);

    @Override
    public EvidenceOutDetailinsAllResponse EvidenceOutDetailinsAll(List<EvidenceOutDetail> req) {

        EvidenceOutDetailinsAllResponse res = new EvidenceOutDetailinsAllResponse();

        try {
            if (req != null) {
                log.info("[Sub] Size : " + req.size());
                List<EvidenceOutDetailResponse> list = new ArrayList<EvidenceOutDetailResponse>();
                if (req.size() > 0) {

                    for (EvidenceOutDetail item : req) {

                        String EVIDENCE_OUT_DETAIL_ID = getSequences("SELECT OPS_EVIDENCE_OUT_DETAIL_SEQ.NEXTVAL FROM DUAL");
                        EvidenceOutDetailResponse obj = new EvidenceOutDetailResponse();
                        obj.setEVIDENCE_OUT_DETAIL_ID(Integer.parseInt(EVIDENCE_OUT_DETAIL_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("INSERT INTO OPS_EVIDENCE_OUT_DETAIL (" +
                                        "EVIDENCE_OUT_DETAIL_ID," +
                                        "EVIDENCE_OUT_ID," +
                                        "EVIDENCE_IN_ID," +
                                        "IS_ACTIVE" +
                                        " ) VALUES (" +
                                        "'" + EVIDENCE_OUT_DETAIL_ID + "', " +
                                        "'" + item.getEVIDENCE_OUT_ID() + "', " +
                                        "'" + item.getEVIDENCE_IN_ID() + "'," +
                                        "'" + item.getIS_ACTIVE() + "' )");
                        log.info("[SQL] Sub : " + sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});


                        list.add(obj);
                    }
                }
                res.setEvidenceOutDetailResponse(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setEvidenceOutDetailResponse(null);
            return res;
        }

    }

    @Override
    public Boolean EvidenceOutDetailupdByCon(List<EvidenceOutDetail> req) {

        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(EvidenceOutDetail item : req){

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_OUT_DETAIL SET "
                                    + "EVIDENCE_OUT_ID=  '" + item.getEVIDENCE_OUT_ID() + "', "
                                    + "EVIDENCE_IN_ID=  '" + item.getEVIDENCE_IN_ID() + "', "
                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
                                    + " WHERE EVIDENCE_OUT_DETAIL_ID = '" + item.getEVIDENCE_OUT_DETAIL_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());
                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                }
            }
        }
        return true;

    }

    @Override
    public Boolean EvidenceOutDetailupdDelete(EvidenceOutDetailupdDeleteReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_EVIDENCE_OUT_DETAIL SET IS_ACTIVE = '0' WHERE EVIDENCE_OUT_DETAIL_ID = '"+req.getEVIDENCE_OUT_DETAIL_ID()+"'");
        log.info("[SQL] Sub : "+sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        return true;
    }
}
