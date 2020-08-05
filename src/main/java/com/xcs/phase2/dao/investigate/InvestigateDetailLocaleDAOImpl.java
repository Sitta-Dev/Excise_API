package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.investigate.InvestigateDetailLocale;
import com.xcs.phase2.request.investigate.InvestigateDetailLocalupdDeleteReq;
import com.xcs.phase2.response.investigate.InvestigateDetailLocaleResponse;
import com.xcs.phase2.response.investigate.InvestigateDetailLocalinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InvestigateDetailLocaleDAOImpl extends InvestigateExt implements InvestigateDetailLocaleDAO {

    private static final Logger log = LoggerFactory.getLogger(InvestigateDetailLocaleDAOImpl.class);


    @Override
    public InvestigateDetailLocalinsAllResponse InvestigateDetailLocalinsAll(List<InvestigateDetailLocale> req) {

        InvestigateDetailLocalinsAllResponse res = new InvestigateDetailLocalinsAllResponse();

        try {

            if (req != null) {
                log.info("[Sub] Size : " + req.size());
                List<InvestigateDetailLocaleResponse> list = new ArrayList<InvestigateDetailLocaleResponse>();
                if (req.size() > 0) {

                    for (InvestigateDetailLocale item : req) {

                        String LOCALE_ID = getSequences("SELECT OPS_INVESTI_DETAIL_LOCAL_SEQ.NEXTVAL FROM DUAL");
                        InvestigateDetailLocaleResponse obj = new InvestigateDetailLocaleResponse();
                        obj.setLOCALE_ID(Integer.parseInt(LOCALE_ID));

                        StringBuilder sqlBuilder = new StringBuilder()
                                .append("INSERT INTO OPS_INVESTIGATE_DETAIL_LOCALE (" +
                                        "LOCALE_ID," +
                                        "INVESTIGATE_DETAIL_ID," +
                                        "SUB_DISTRICT_ID," +
                                        "GPS," +
                                        "ADDRESS_NO," +
                                        "VILLAGE_NO," +
                                        "BUILDING_NAME," +
                                        "ROOM_NO," +
                                        "FLOOR," +
                                        "VILLAGE_NAME," +
                                        "ALLEY," +
                                        "LANE," +
                                        "ROAD," +
                                        "ADDRESS_TYPE," +
                                        "ADDRESS_STATUS," +
                                        "POLICE_STATION," +
                                        "IS_ACTIVE" +
                                        " ) VALUES (" +
                                        "'" + LOCALE_ID + "', " +
                                        "'" + item.getINVESTIGATE_DETAIL_ID() + "', " +
                                        "'" + item.getSUB_DISTRICT_ID() + "'," +
                                        "'" + item.getGPS() + "'," +
                                        "'" + item.getADDRESS_NO() + "'," +
                                        "'" + item.getVILLAGE_NO() + "'," +
                                        "'" + item.getBUILDING_NAME() + "'," +
                                        "'" + item.getROOM_NO() + "'," +
                                        "'" + item.getFLOOR() + "'," +
                                        "'" + item.getVILLAGE_NAME() + "'," +
                                        "'" + item.getALLEY() + "'," +
                                        "'" + item.getLANE() + "'," +
                                        "'" + item.getROAD() + "'," +
                                        "'" + item.getADDRESS_TYPE() + "'," +
                                        "'" + item.getADDRESS_STATUS() + "'," +
                                        "'" + item.getPOLICE_STATION() + "'," +
                                        "'" + item.getIS_ACTIVE() + "' )");
                        log.info("[SQL] Sub : " + sqlBuilder.toString());
                        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

                        list.add(obj);
                    }
                }
                res.setInvestigateDetailLocale(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setInvestigateDetailLocale(null);
            return res;
        }

    }

    @Override
    public Boolean InvestigateDetailLocalupdByCon(List<InvestigateDetailLocale> request) {

        if (request != null) {
            log.info("[Sub] Size : " + request.size());

            if (request.size() > 0) {

                for (InvestigateDetailLocale req : request) {

                    StringBuilder sqlBuilder = new StringBuilder()
                            .append("UPDATE OPS_INVESTIGATE_DETAIL_LOCALE SET "
                                    + "INVESTIGATE_DETAIL_ID=  '" + req.getINVESTIGATE_DETAIL_ID() + "', "
                                    + "SUB_DISTRICT_ID=  '" + req.getSUB_DISTRICT_ID() + "', "
                                    + "GPS=  '" + req.getGPS() + "', "
                                    + "ADDRESS_NO=  '" + req.getADDRESS_NO() + "', "
                                    + "VILLAGE_NO=  '" + req.getVILLAGE_NO() + "', "
                                    + "BUILDING_NAME=  '" + req.getBUILDING_NAME() + "', "
                                    + "ROOM_NO=  '" + req.getROOM_NO() + "', "
                                    + "FLOOR=  '" + req.getFLOOR() + "', "
                                    + "VILLAGE_NAME=  '" + req.getVILLAGE_NAME() + "', "
                                    + "ALLEY=  '" + req.getALLEY() + "', "
                                    + "LANE=  '" + req.getLANE() + "', "
                                    + "ROAD=  '" + req.getROAD() + "', "
                                    + "ADDRESS_TYPE=  '" + req.getADDRESS_TYPE() + "', "
                                    + "ADDRESS_STATUS=  '" + req.getADDRESS_STATUS() + "', "
                                    + "POLICE_STATION=  '" + req.getPOLICE_STATION() + "', "
                                    + "IS_ACTIVE='" + req.getIS_ACTIVE() + "' "
                                    + " WHERE LOCALE_ID = '" + req.getLOCALE_ID() + "' ");

                    log.info("[SQL] : " + sqlBuilder.toString());
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
                }
            }
        }
        return true;
    }

    @Override
    public Boolean InvestigateDetailLocalupdDelete(List<InvestigateDetailLocalupdDeleteReq> req) {

        if (req != null) {
            log.info("[Sub] Size : " + req.size());

            if (req.size() > 0) {

                for (InvestigateDetailLocalupdDeleteReq item : req) {

                    StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_INVESTIGATE_DETAIL_LOCALE SET IS_ACTIVE = '0' WHERE LOCALE_ID = '" + item.getLOCALE_ID() + "' ");
                    log.info("[SQL] Sub : " + sqlBuilder.toString());
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

                }
            }
        }

        return true;

    }
}
