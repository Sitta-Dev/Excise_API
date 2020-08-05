package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.investigate.InvestigateDetailSuspect;
import com.xcs.phase2.request.investigate.InvestigateDetailSuspectupdDeleteReq;
import com.xcs.phase2.response.investigate.InvestigateDetailSuspectResponse;
import com.xcs.phase2.response.investigate.InvestigateDetailSuspectinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InvestigateDetailSuspectDAOImpl extends InvestigateExt implements InvestigateDetailSuspectDAO{

    private static final Logger log = LoggerFactory.getLogger(InvestigateDetailSuspectDAOImpl.class);


    @Override
    public InvestigateDetailSuspectinsAllResponse InvestigateDetailSuspectinsAll(List<InvestigateDetailSuspect> request) {

        InvestigateDetailSuspectinsAllResponse res = new InvestigateDetailSuspectinsAllResponse();

        try {


            if (request != null) {
                log.info("[Sub] Size : " + request.size());
                List<InvestigateDetailSuspectResponse> list = new ArrayList<InvestigateDetailSuspectResponse>();
                if (request.size() > 0) {

                    for (InvestigateDetailSuspect req : request) {

                        String SUSPECT_ID = getSequences("SELECT OPS_INVESTI_DETAIL_SUSPECT_SEQ.NEXTVAL FROM DUAL");
                        InvestigateDetailSuspectResponse obj = new InvestigateDetailSuspectResponse();
                        obj.setSUSPECT_ID(Integer.parseInt(SUSPECT_ID));

                        StringBuilder sqlBuilder = new StringBuilder()
                                .append("INSERT INTO OPS_INVESTIGATE_DETAIL_SUSPECT (" +
                                        "SUSPECT_ID," +
                                        "INVESTIGATE_DETAIL_ID," +
                                        "PERSON_ID," +
                                        "TITLE_ID," +
                                        "PERSON_TYPE," +
                                        "ENTITY_TYPE," +
                                        "TITLE_NAME_TH," +
                                        "TITLE_NAME_EN," +
                                        "TITLE_SHORT_NAME_TH," +
                                        "TITLE_SHORT_NAME_EN," +
                                        "FIRST_NAME," +
                                        "MIDDLE_NAME," +
                                        "LAST_NAME," +
                                        "OTHER_NAME," +
                                        "COMPANY_REGISTRATION_NO," +
                                        "EXCISE_REGISTRATION_NO," +
                                        "ID_CARD," +
                                        "AGE," +
                                        "PASSPORT_NO," +
                                        "CAREER," +
                                        "PERSON_DESC," +
                                        "EMAIL," +
                                        "TEL_NO," +
                                        "MISTREAT_NO," +
                                        "IS_ACTIVE" +
                                        " ) VALUES (" +
                                        "'" + SUSPECT_ID + "', " +
                                        "'"+req.getINVESTIGATE_DETAIL_ID()+"'," +
                                        "'"+req.getPERSON_ID()+"'," +
                                        "'"+req.getTITLE_ID()+"'," +
                                        "'"+req.getPERSON_TYPE()+"'," +
                                        "'"+req.getENTITY_TYPE()+"'," +
                                        "'"+req.getTITLE_NAME_TH()+"'," +
                                        "'"+req.getTITLE_NAME_EN()+"'," +
                                        "'"+req.getTITLE_SHORT_NAME_TH()+"'," +
                                        "'"+req.getTITLE_SHORT_NAME_EN()+"'," +
                                        "'"+req.getFIRST_NAME()+"'," +
                                        "'"+req.getMIDDLE_NAME()+"'," +
                                        "'"+req.getLAST_NAME()+"'," +
                                        "'"+req.getOTHER_NAME()+"'," +
                                        "'"+req.getCOMPANY_REGISTRATION_NO()+"'," +
                                        "'"+req.getEXCISE_REGISTRATION_NO()+"'," +
                                        "'"+req.getID_CARD()+"'," +
                                        "'"+req.getAGE()+"'," +
                                        "'"+req.getPASSPORT_NO()+"'," +
                                        "'"+req.getCAREER()+"'," +
                                        "'"+req.getPERSON_DESC()+"'," +
                                        "'"+req.getEMAIL()+"'," +
                                        "'"+req.getTEL_NO()+"'," +
                                        "'"+req.getMISTREAT_NO()+"'," +
                                        "'1')");
                        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

                        list.add(obj);
                    }
                }
                res.setInvestigateDetailSuspect(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setInvestigateDetailSuspect(null);
            return res;
        }

    }

    @Override
    public Boolean InvestigateDetailSuspectupdDelete(List<InvestigateDetailSuspectupdDeleteReq> req) {

        if (req != null) {
            log.info("[Sub] Size : " + req.size());

            if (req.size() > 0) {

                for (InvestigateDetailSuspectupdDeleteReq item : req) {

                    StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_INVESTIGATE_DETAIL_SUSPECT SET IS_ACTIVE = '0' WHERE SUSPECT_ID = '" + item.getSUSPECT_ID() + "' ");
                    log.info("[SQL] Sub : " + sqlBuilder.toString());
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

                }
            }
        }

        return true;

    }
}
