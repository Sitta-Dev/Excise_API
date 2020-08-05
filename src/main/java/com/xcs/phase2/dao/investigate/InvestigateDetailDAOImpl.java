package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.investigate.InvestigateDetail;
import com.xcs.phase2.model.investigate.InvestigateDetailLocale;
import com.xcs.phase2.model.investigate.InvestigateDetailStaff;
import com.xcs.phase2.request.investigate.InvestigateDetailgetByConReq;
import com.xcs.phase2.request.investigate.InvestigateDetailupdDeleteReq;
import com.xcs.phase2.response.investigate.InvestigateDetailLocaleResponse;
import com.xcs.phase2.response.investigate.InvestigateDetailStaffResponse;
import com.xcs.phase2.response.investigate.InvestigateDetailinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class InvestigateDetailDAOImpl extends InvestigateExt implements InvestigateDetailDAO {

    private static final Logger log = LoggerFactory.getLogger(InvestigateDetailDAOImpl.class);

    @Override
    public InvestigateDetail InvestigateDetailgetByCon(InvestigateDetailgetByConReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "INVESTIGATE_DETAIL_ID," +
                        "INVESTIGATE_ID," +
                        "OFFICE_ID," +
                        "INVESTIGATE_SEQUENCE," +
                        "OFFICE_CODE," +
                        "OFFICE_NAME," +
                        "to_char(DATE_START,'" + Pattern.FORMAT_DATETIME + "') as DATE_START, " +
                        "to_char(DATE_END,'" + Pattern.FORMAT_DATETIME + "') as DATE_END, " +
                        "INVESTIGATE_DETAIL_DESCRIPTION," +
                        "CONFIDENCE_OF_NEWS," +
                        "VALUE_OF_NEWS," +
                        "COMMAND," +
                        "IS_ACTIVE" +
                        " from OPS_INVESTIGATE_DETAIL where IS_ACTIVE = 1 and INVESTIGATE_DETAIL_ID = '" + req.getINVESTIGATE_DETAIL_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<InvestigateDetail>() {

            public InvestigateDetail extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    InvestigateDetail item = new InvestigateDetail();
                    item.setINVESTIGATE_DETAIL_ID(rs.getInt("INVESTIGATE_DETAIL_ID"));
                    item.setINVESTIGATE_ID(rs.getString("INVESTIGATE_ID"));
                    item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                    item.setINVESTIGATE_SEQUENCE(rs.getString("INVESTIGATE_SEQUENCE"));
                    item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                    item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                    item.setDATE_START(rs.getString("DATE_START"));
                    item.setDATE_END(rs.getString("DATE_END"));
                    item.setINVESTIGATE_DETAIL_DESCRIPTION(rs.getString("INVESTIGATE_DETAIL_DESCRIPTION"));
                    item.setCONFIDENCE_OF_NEWS(rs.getInt("CONFIDENCE_OF_NEWS"));
                    item.setVALUE_OF_NEWS(rs.getInt("VALUE_OF_NEWS"));
                    item.setCOMMAND(rs.getString("COMMAND"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    item.setInvestigateDetailStaff(getInvestigateDetailStaff(rs.getInt("INVESTIGATE_DETAIL_ID")));
                    item.setInvestigateDetailSuspect(getInvestigateDetailSuspect(rs.getInt("INVESTIGATE_DETAIL_ID")));
                    item.setInvestigateDetailLocale(getInvestigateDetailLocale(rs.getInt("INVESTIGATE_DETAIL_ID")));
                    item.setInvestigateDetailProduct(getInvestigateDetailProduct(rs.getInt("INVESTIGATE_DETAIL_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public InvestigateDetailinsAllResponse InvestigateDetailinsAll(InvestigateDetail req) {

        InvestigateDetailinsAllResponse res = new InvestigateDetailinsAllResponse();

        try {

            String INVESTIGATE_DETAIL_ID = getSequences("SELECT OPS_INVESTI_DETAIL_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("INSERT INTO OPS_INVESTIGATE_DETAIL (" +
                            "INVESTIGATE_DETAIL_ID," +
                            "INVESTIGATE_ID," +
                            "OFFICE_ID," +
                            "INVESTIGATE_SEQUENCE," +
                            "OFFICE_CODE," +
                            "OFFICE_NAME," +
                            "DATE_START," +
                            "DATE_END," +
                            "INVESTIGATE_DETAIL_DESCRIPTION," +
                            "CONFIDENCE_OF_NEWS," +
                            "VALUE_OF_NEWS," +
                            "COMMAND," +
                            "IS_ACTIVE" +
                            " ) VALUES (" +
                            "'" + INVESTIGATE_DETAIL_ID + "', " +
                            "'" + req.getINVESTIGATE_ID() + "'," +
                            "'" + req.getOFFICE_ID() + "'," +
                            "'" + req.getINVESTIGATE_SEQUENCE() + "'," +
                            "'" + req.getOFFICE_CODE() + "'," +
                            "'" + req.getOFFICE_NAME() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getDATE_START() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + req.getDATE_END() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getINVESTIGATE_DETAIL_DESCRIPTION() + "'," +
                            "'" + req.getCONFIDENCE_OF_NEWS() + "'," +
                            "'" + req.getVALUE_OF_NEWS() + "'," +
                            "'" + req.getCOMMAND() + "'," +
                            "'1')");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setINVESTIGATE_DETAIL_ID(Integer.parseInt(INVESTIGATE_DETAIL_ID));


            if (req.getInvestigateDetailLocale() != null) {
                log.info("[Sub] Size : " + req.getInvestigateDetailLocale().size());
                List<InvestigateDetailLocaleResponse> list = new ArrayList<InvestigateDetailLocaleResponse>();

                if (req.getInvestigateDetailLocale().size() > 0) {

                    for (InvestigateDetailLocale item : req.getInvestigateDetailLocale()) {

                        String LOCALE_ID = getSequences("SELECT OPS_INVESTI_DETAIL_LOCAL_SEQ.NEXTVAL FROM DUAL");
                        InvestigateDetailLocaleResponse obj = new InvestigateDetailLocaleResponse();
                        obj.setLOCALE_ID(Integer.parseInt(LOCALE_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
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
                                        "'" + INVESTIGATE_DETAIL_ID + "', " +
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
                        log.info("[SQL] Sub : " + sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                        list.add(obj);
                    }
                }
                res.setInvestigateDetailLocale(list);
            }

            if (req.getInvestigateDetailStaff() != null) {
                log.info("[Sub] Size : " + req.getInvestigateDetailStaff().size());
                List<InvestigateDetailStaffResponse> list = new ArrayList<InvestigateDetailStaffResponse>();

                if (req.getInvestigateDetailStaff().size() > 0) {
                    for (InvestigateDetailStaff item : req.getInvestigateDetailStaff()) {

                        String STAFF_ID = getSequences("SELECT OPS_INVESTI_DETAIL_STAFF_SEQ.NEXTVAL FROM DUAL");
                        InvestigateDetailStaffResponse obj = new InvestigateDetailStaffResponse();
                        obj.setSTAFF_ID(Integer.parseInt(STAFF_ID));

                        StringBuilder sqlBuilderSubStaff = new StringBuilder()
                                .append("INSERT INTO OPS_INVESTIGATE_DETAIL_STAFF (" +
                                        "STAFF_ID," +
                                        "INVESTIGATE_DETAIL_ID," +
                                        "STAFF_REF_ID," +
                                        "TITLE_ID," +
                                        "STAFF_CODE," +
                                        "ID_CARD," +
                                        "STAFF_TYPE," +
                                        "TITLE_NAME_TH," +
                                        "TITLE_NAME_EN," +
                                        "TITLE_SHORT_NAME_TH," +
                                        "TITLE_SHORT_NAME_EN," +
                                        "FIRST_NAME," +
                                        "LAST_NAME," +
                                        "AGE," +
                                        "OPERATION_POS_CODE," +
                                        "OPREATION_POS_NAME," +
                                        "OPREATION_POS_LEVEL," +
                                        "OPERATION_POS_LEVEL_NAME," +
                                        "OPERATION_DEPT_CODE," +
                                        "OPERATION_DEPT_NAME," +
                                        "OPERATION_DEPT_LEVEL," +
                                        "OPERATION_UNDER_DEPT_CODE," +
                                        "OPERATION_UNDER_DEPT_NAME," +
                                        "OPERATION_UNDER_DEPT_LEVEL," +
                                        "OPERATION_WORK_DEPT_CODE," +
                                        "OPERATION_WORK_DEPT_NAME," +
                                        "OPERATION_WORK_DEPT_LEVEL," +
                                        "OPERATION_OFFICE_CODE," +
                                        "OPERATION_OFFICE_NAME," +
                                        "OPERATION_OFFICE_SHORT_NAME," +
                                        "MANAGEMENT_POS_CODE," +
                                        "MANAGEMENT_POS_NAME," +
                                        "MANAGEMENT_POS_LEVEL," +
                                        "MANAGEMENT_POS_LEVEL_NAME," +
                                        "MANAGEMENT_DEPT_CODE," +
                                        "MANAGEMENT_DEPT_NAME," +
                                        "MANAGEMENT_DEPT_LEVEL," +
                                        "MANAGEMENT_UNDER_DEPT_CODE," +
                                        "MANAGEMENT_UNDER_DEPT_NAME," +
                                        "MANAGEMENT_UNDER_DEPT_LEVEL," +
                                        "MANAGEMENT_WORK_DEPT_CODE," +
                                        "MANAGEMENT_WORK_DEPT_NAME," +
                                        "MANAGEMENT_WORK_DEPT_LEVEL," +
                                        "MANAGEMENT_OFFICE_CODE," +
                                        "MANAGEMENT_OFFICE_NAME," +
                                        "MANAGEMENT_OFFICE_SHORT_NAME," +
                                        "REPRESENT_POS_CODE," +
                                        "REPRESENT_POS_NAME," +
                                        "REPRESENT_POS_LEVEL," +
                                        "REPRESENT_POS_LEVEL_NAME," +
                                        "REPRESENT_DEPT_CODE," +
                                        "REPRESENT_DEPT_NAME," +
                                        "REPRESENT_DEPT_LEVEL," +
                                        "REPRESENT_UNDER_DEPT_CODE," +
                                        "REPRESENT_UNDER_DEPT_NAME," +
                                        "REPRESENT_UNDER_DEPT_LEVEL," +
                                        "REPRESENT_WORK_DEPT_CODE," +
                                        "REPRESENT_WORK_DEPT_NAME," +
                                        "REPRESENT_WORK_DEPT_LEVEL," +
                                        "REPRESENT_OFFICE_CODE," +
                                        "REPRESENT_OFFICE_NAME," +
                                        "REPRESENT_OFFICE_SHORT_NAME," +
                                        "STATUS," +
                                        "REMARK," +
                                        "CONTRIBUTOR_ID," +
                                        "IS_ACTIVE" +
                                        " ) VALUES (" +
                                        "'" + STAFF_ID + "', " +
                                        "'" + INVESTIGATE_DETAIL_ID + "', " +
                                        "'" + item.getSTAFF_REF_ID() + "'," +
                                        "'" + item.getTITLE_ID() + "'," +
                                        "'" + item.getSTAFF_CODE() + "'," +
                                        "'" + item.getID_CARD() + "'," +
                                        "'" + item.getSTAFF_TYPE() + "'," +
                                        "'" + item.getTITLE_NAME_TH() + "'," +
                                        "'" + item.getTITLE_NAME_EN() + "'," +
                                        "'" + item.getTITLE_SHORT_NAME_TH() + "'," +
                                        "'" + item.getTITLE_SHORT_NAME_EN() + "'," +
                                        "'" + item.getFIRST_NAME() + "'," +
                                        "'" + item.getLAST_NAME() + "'," +
                                        "'" + item.getAGE() + "'," +
                                        "'" + item.getOPERATION_POS_CODE() + "'," +
                                        "'" + item.getOPREATION_POS_NAME() + "'," +
                                        "'" + item.getOPREATION_POS_LEVEL() + "'," +
                                        "'" + item.getOPERATION_POS_LEVEL_NAME() + "'," +
                                        "'" + item.getOPERATION_DEPT_CODE() + "'," +
                                        "'" + item.getOPERATION_DEPT_NAME() + "'," +
                                        "'" + item.getOPERATION_DEPT_LEVEL() + "'," +
                                        "'" + item.getOPERATION_UNDER_DEPT_CODE() + "'," +
                                        "'" + item.getOPERATION_UNDER_DEPT_NAME() + "'," +
                                        "'" + item.getOPERATION_UNDER_DEPT_LEVEL() + "'," +
                                        "'" + item.getOPERATION_WORK_DEPT_CODE() + "'," +
                                        "'" + item.getOPERATION_WORK_DEPT_NAME() + "'," +
                                        "'" + item.getOPERATION_WORK_DEPT_LEVEL() + "'," +
                                        "'" + item.getOPERATION_OFFICE_CODE() + "'," +
                                        "'" + item.getOPERATION_OFFICE_NAME() + "'," +
                                        "'" + item.getOPERATION_OFFICE_SHORT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_POS_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_POS_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_POS_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_POS_LEVEL_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_DEPT_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_DEPT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_DEPT_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_UNDER_DEPT_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_UNDER_DEPT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_UNDER_DEPT_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_WORK_DEPT_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_WORK_DEPT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_WORK_DEPT_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_OFFICE_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_OFFICE_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_OFFICE_SHORT_NAME() + "'," +
                                        "'" + item.getREPRESENT_POS_CODE() + "'," +
                                        "'" + item.getREPRESENT_POS_NAME() + "'," +
                                        "'" + item.getREPRESENT_POS_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_POS_LEVEL_NAME() + "'," +
                                        "'" + item.getREPRESENT_DEPT_CODE() + "'," +
                                        "'" + item.getREPRESENT_DEPT_NAME() + "'," +
                                        "'" + item.getREPRESENT_DEPT_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_UNDER_DEPT_CODE() + "'," +
                                        "'" + item.getREPRESENT_UNDER_DEPT_NAME() + "'," +
                                        "'" + item.getREPRESENT_UNDER_DEPT_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_WORK_DEPT_CODE() + "'," +
                                        "'" + item.getREPRESENT_WORK_DEPT_NAME() + "'," +
                                        "'" + item.getREPRESENT_WORK_DEPT_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_OFFICE_CODE() + "'," +
                                        "'" + item.getREPRESENT_OFFICE_NAME() + "'," +
                                        "'" + item.getREPRESENT_OFFICE_SHORT_NAME() + "'," +
                                        "'" + item.getSTATUS() + "'," +
                                        "'" + item.getREMARK() + "'," +
                                        "'" + item.getCONTRIBUTOR_ID() + "'," +
                                        "'" + item.getIS_ACTIVE() + "' )");
                        log.info("[SQL] : " + sqlBuilderSubStaff.toString());

                        getJdbcTemplate().update(sqlBuilderSubStaff.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setInvestigateDetailStaff(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setINVESTIGATE_DETAIL_ID(0);
            res.setInvestigateDetailLocale(null);
            res.setInvestigateDetailStaff(null);
            return res;
        }

    }

    @Override
    public Boolean InvestigateDetailupdByCon(InvestigateDetail req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_INVESTIGATE_DETAIL SET "
                        + "INVESTIGATE_DETAIL_ID=  '"+req.getINVESTIGATE_DETAIL_ID()+"', "
                        + "INVESTIGATE_ID=  '"+req.getINVESTIGATE_ID()+"', "
                        + "OFFICE_ID=  '"+req.getOFFICE_ID()+"', "
                        + "INVESTIGATE_SEQUENCE=  '"+req.getINVESTIGATE_SEQUENCE()+"', "
                        + "OFFICE_CODE=  '"+req.getOFFICE_CODE()+"', "
                        + "OFFICE_NAME=  '"+req.getOFFICE_NAME()+"', "
                        + "DATE_START=  TO_TIMESTAMP_TZ('" + req.getDATE_START() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "DATE_END=  TO_TIMESTAMP_TZ('" + req.getDATE_END() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "INVESTIGATE_DETAIL_DESCRIPTION=  '"+req.getINVESTIGATE_DETAIL_DESCRIPTION()+"', "
                        + "CONFIDENCE_OF_NEWS=  '"+req.getCONFIDENCE_OF_NEWS()+"', "
                        + "VALUE_OF_NEWS=  '"+req.getVALUE_OF_NEWS()+"', "
                        + "COMMAND=  '"+req.getCOMMAND()+"', "
                        + "IS_ACTIVE='" + req.getIS_ACTIVE() + "' "
                        + " WHERE INVESTIGATE_DETAIL_ID = '" + req.getINVESTIGATE_DETAIL_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        return true;
    }

    @Override
    public Boolean InvestigateDetailupdDelete(InvestigateDetailupdDeleteReq req) {


        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_INVESTIGATE_DETAIL SET IS_ACTIVE = '0' WHERE INVESTIGATE_DETAIL_ID = '" + req.getINVESTIGATE_DETAIL_ID() + "' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_INVESTIGATE_DETAIL_LOCALE SET IS_ACTIVE = '0' WHERE INVESTIGATE_DETAIL_ID = '" + req.getINVESTIGATE_DETAIL_ID() + "' ");
        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_INVESTIGATE_DETAIL_PRODUCT SET IS_ACTIVE = '0' WHERE INVESTIGATE_DETAIL_ID = '" + req.getINVESTIGATE_DETAIL_ID() + "' ");
        StringBuilder sqlBuilder4 = new StringBuilder().append("UPDATE OPS_INVESTIGATE_DETAIL_STAFF SET IS_ACTIVE = '0' WHERE INVESTIGATE_DETAIL_ID = '" + req.getINVESTIGATE_DETAIL_ID() + "' ");
        StringBuilder sqlBuilder5 = new StringBuilder().append("UPDATE OPS_INVESTIGATE_DETAIL_SUSPECT SET IS_ACTIVE = '0' WHERE INVESTIGATE_DETAIL_ID = '" + req.getINVESTIGATE_DETAIL_ID() + "' ");



        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder4.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder5.toString(), new Object[]{});


        return true;

    }
}
