package com.xcs.phase2.dao.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.prove.ProveStaff;
import com.xcs.phase2.request.prove.ProveStaffgetByConReq;
import com.xcs.phase2.request.prove.ProveStaffupdDeleteReq;
import com.xcs.phase2.response.prove.ProveStaffinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ProveStaffDAOImpl extends ProveExt implements ProveStaffDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveStaffDAOImpl.class);

    @Override
    public List<ProveStaff> ProveStaffgetByCon(ProveStaffgetByConReq req) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "STAFF_ID," +
                        "PROVE_ID," +
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
                        " from OPS_PROVE_STAFF  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and PROVE_ID = '" + req.getPROVE_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveStaff item = new ProveStaff();
                item.setSTAFF_ID(rs.getInt("STAFF_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setSTAFF_REF_ID(rs.getInt("STAFF_REF_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setSTAFF_CODE(rs.getString("STAFF_CODE"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setSTAFF_TYPE(rs.getInt("STAFF_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setAGE(rs.getInt("AGE"));
                item.setOPERATION_POS_CODE(rs.getString("OPERATION_POS_CODE"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setOPREATION_POS_LEVEL(rs.getString("OPREATION_POS_LEVEL"));
                item.setOPERATION_POS_LEVEL_NAME(rs.getString("OPERATION_POS_LEVEL_NAME"));
                item.setOPERATION_DEPT_CODE(rs.getString("OPERATION_DEPT_CODE"));
                item.setOPERATION_DEPT_NAME(rs.getString("OPERATION_DEPT_NAME"));
                item.setOPERATION_DEPT_LEVEL(rs.getInt("OPERATION_DEPT_LEVEL"));
                item.setOPERATION_UNDER_DEPT_CODE(rs.getString("OPERATION_UNDER_DEPT_CODE"));
                item.setOPERATION_UNDER_DEPT_NAME(rs.getString("OPERATION_UNDER_DEPT_NAME"));
                item.setOPERATION_UNDER_DEPT_LEVEL(rs.getInt("OPERATION_UNDER_DEPT_LEVEL"));
                item.setOPERATION_WORK_DEPT_CODE(rs.getString("OPERATION_WORK_DEPT_CODE"));
                item.setOPERATION_WORK_DEPT_NAME(rs.getString("OPERATION_WORK_DEPT_NAME"));
                item.setOPERATION_WORK_DEPT_LEVEL(rs.getInt("OPERATION_WORK_DEPT_LEVEL"));
                item.setOPERATION_OFFICE_CODE(rs.getString("OPERATION_OFFICE_CODE"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                item.setMANAGEMENT_POS_CODE(rs.getString("MANAGEMENT_POS_CODE"));
                item.setMANAGEMENT_POS_NAME(rs.getString("MANAGEMENT_POS_NAME"));
                item.setMANAGEMENT_POS_LEVEL(rs.getString("MANAGEMENT_POS_LEVEL"));
                item.setMANAGEMENT_POS_LEVEL_NAME(rs.getString("MANAGEMENT_POS_LEVEL_NAME"));
                item.setMANAGEMENT_DEPT_CODE(rs.getString("MANAGEMENT_DEPT_CODE"));
                item.setMANAGEMENT_DEPT_NAME(rs.getString("MANAGEMENT_DEPT_NAME"));
                item.setMANAGEMENT_DEPT_LEVEL(rs.getInt("MANAGEMENT_DEPT_LEVEL"));
                item.setMANAGEMENT_UNDER_DEPT_CODE(rs.getString("MANAGEMENT_UNDER_DEPT_CODE"));
                item.setMANAGEMENT_UNDER_DEPT_NAME(rs.getString("MANAGEMENT_UNDER_DEPT_NAME"));
                item.setMANAGEMENT_UNDER_DEPT_LEVEL(rs.getInt("MANAGEMENT_UNDER_DEPT_LEVEL"));
                item.setMANAGEMENT_WORK_DEPT_CODE(rs.getString("MANAGEMENT_WORK_DEPT_CODE"));
                item.setMANAGEMENT_WORK_DEPT_NAME(rs.getString("MANAGEMENT_WORK_DEPT_NAME"));
                item.setMANAGEMENT_WORK_DEPT_LEVEL(rs.getInt("MANAGEMENT_WORK_DEPT_LEVEL"));
                item.setMANAGEMENT_OFFICE_CODE(rs.getString("MANAGEMENT_OFFICE_CODE"));
                item.setMANAGEMENT_OFFICE_NAME(rs.getString("MANAGEMENT_OFFICE_NAME"));
                item.setMANAGEMENT_OFFICE_SHORT_NAME(rs.getString("MANAGEMENT_OFFICE_SHORT_NAME"));
                item.setREPRESENT_POS_CODE(rs.getString("REPRESENT_POS_CODE"));
                item.setREPRESENT_POS_NAME(rs.getString("REPRESENT_POS_NAME"));
                item.setREPRESENT_POS_LEVEL(rs.getString("REPRESENT_POS_LEVEL"));
                item.setREPRESENT_POS_LEVEL_NAME(rs.getString("REPRESENT_POS_LEVEL_NAME"));
                item.setREPRESENT_DEPT_CODE(rs.getString("REPRESENT_DEPT_CODE"));
                item.setREPRESENT_DEPT_NAME(rs.getString("REPRESENT_DEPT_NAME"));
                item.setREPRESENT_DEPT_LEVEL(rs.getInt("REPRESENT_DEPT_LEVEL"));
                item.setREPRESENT_UNDER_DEPT_CODE(rs.getString("REPRESENT_UNDER_DEPT_CODE"));
                item.setREPRESENT_UNDER_DEPT_NAME(rs.getString("REPRESENT_UNDER_DEPT_NAME"));
                item.setREPRESENT_UNDER_DEPT_LEVEL(rs.getInt("REPRESENT_UNDER_DEPT_LEVEL"));
                item.setREPRESENT_WORK_DEPT_CODE(rs.getString("REPRESENT_WORK_DEPT_CODE"));
                item.setREPRESENT_WORK_DEPT_NAME(rs.getString("REPRESENT_WORK_DEPT_NAME"));
                item.setREPRESENT_WORK_DEPT_LEVEL(rs.getInt("REPRESENT_WORK_DEPT_LEVEL"));
                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setREPRESENT_OFFICE_SHORT_NAME(rs.getString("REPRESENT_OFFICE_SHORT_NAME"));
                item.setSTATUS(rs.getInt("STATUS"));
                item.setREMARK(rs.getString("REMARK"));
                item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public ProveStaffinsAllResponse ProveStaffinsAll(ProveStaff req) {

        ProveStaffinsAllResponse res = new ProveStaffinsAllResponse();

        try {

            String STAFF_ID = getSequences("SELECT OPS_PROVE_STAFF_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_PROVE_STAFF ( " +
                            "STAFF_ID," +
                            "PROVE_ID," +
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
                            " ) values (" +
                            "'" + STAFF_ID + "'," +
                            "'" + req.getPROVE_ID() + "'," +
                            "'" + req.getSTAFF_REF_ID() + "'," +
                            "'" + req.getTITLE_ID() + "'," +
                            "'" + req.getSTAFF_CODE() + "'," +
                            "'" + req.getID_CARD() + "'," +
                            "'" + req.getSTAFF_TYPE() + "'," +
                            "'" + req.getTITLE_NAME_TH() + "'," +
                            "'" + req.getTITLE_NAME_EN() + "'," +
                            "'" + req.getTITLE_SHORT_NAME_TH() + "'," +
                            "'" + req.getTITLE_SHORT_NAME_EN() + "'," +
                            "'" + req.getFIRST_NAME() + "'," +
                            "'" + req.getLAST_NAME() + "'," +
                            "'" + req.getAGE() + "'," +
                            "'" + req.getOPERATION_POS_CODE() + "'," +
                            "'" + req.getOPREATION_POS_NAME() + "'," +
                            "'" + req.getOPREATION_POS_LEVEL() + "'," +
                            "'" + req.getOPERATION_POS_LEVEL_NAME() + "'," +
                            "'" + req.getOPERATION_DEPT_CODE() + "'," +
                            "'" + req.getOPERATION_DEPT_NAME() + "'," +
                            "'" + req.getOPERATION_DEPT_LEVEL() + "'," +
                            "'" + req.getOPERATION_UNDER_DEPT_CODE() + "'," +
                            "'" + req.getOPERATION_UNDER_DEPT_NAME() + "'," +
                            "'" + req.getOPERATION_UNDER_DEPT_LEVEL() + "'," +
                            "'" + req.getOPERATION_WORK_DEPT_CODE() + "'," +
                            "'" + req.getOPERATION_WORK_DEPT_NAME() + "'," +
                            "'" + req.getOPERATION_WORK_DEPT_LEVEL() + "'," +
                            "'" + req.getOPERATION_OFFICE_CODE() + "'," +
                            "'" + req.getOPERATION_OFFICE_NAME() + "'," +
                            "'" + req.getOPERATION_OFFICE_SHORT_NAME() + "'," +
                            "'" + req.getMANAGEMENT_POS_CODE() + "'," +
                            "'" + req.getMANAGEMENT_POS_NAME() + "'," +
                            "'" + req.getMANAGEMENT_POS_LEVEL() + "'," +
                            "'" + req.getMANAGEMENT_POS_LEVEL_NAME() + "'," +
                            "'" + req.getMANAGEMENT_DEPT_CODE() + "'," +
                            "'" + req.getMANAGEMENT_DEPT_NAME() + "'," +
                            "'" + req.getMANAGEMENT_DEPT_LEVEL() + "'," +
                            "'" + req.getMANAGEMENT_UNDER_DEPT_CODE() + "'," +
                            "'" + req.getMANAGEMENT_UNDER_DEPT_NAME() + "'," +
                            "'" + req.getMANAGEMENT_UNDER_DEPT_LEVEL() + "'," +
                            "'" + req.getMANAGEMENT_WORK_DEPT_CODE() + "'," +
                            "'" + req.getMANAGEMENT_WORK_DEPT_NAME() + "'," +
                            "'" + req.getMANAGEMENT_WORK_DEPT_LEVEL() + "'," +
                            "'" + req.getMANAGEMENT_OFFICE_CODE() + "'," +
                            "'" + req.getMANAGEMENT_OFFICE_NAME() + "'," +
                            "'" + req.getMANAGEMENT_OFFICE_SHORT_NAME() + "'," +
                            "'" + req.getREPRESENT_POS_CODE() + "'," +
                            "'" + req.getREPRESENT_POS_NAME() + "'," +
                            "'" + req.getREPRESENT_POS_LEVEL() + "'," +
                            "'" + req.getREPRESENT_POS_LEVEL_NAME() + "'," +
                            "'" + req.getREPRESENT_DEPT_CODE() + "'," +
                            "'" + req.getREPRESENT_DEPT_NAME() + "'," +
                            "'" + req.getREPRESENT_DEPT_LEVEL() + "'," +
                            "'" + req.getREPRESENT_UNDER_DEPT_CODE() + "'," +
                            "'" + req.getREPRESENT_UNDER_DEPT_NAME() + "'," +
                            "'" + req.getREPRESENT_UNDER_DEPT_LEVEL() + "'," +
                            "'" + req.getREPRESENT_WORK_DEPT_CODE() + "'," +
                            "'" + req.getREPRESENT_WORK_DEPT_NAME() + "'," +
                            "'" + req.getREPRESENT_WORK_DEPT_LEVEL() + "'," +
                            "'" + req.getREPRESENT_OFFICE_CODE() + "'," +
                            "'" + req.getREPRESENT_OFFICE_NAME() + "'," +
                            "'" + req.getREPRESENT_OFFICE_SHORT_NAME() + "'," +
                            "'" + req.getSTATUS() + "'," +
                            "'" + req.getREMARK() + "'," +
                            "'" + req.getCONTRIBUTOR_ID() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setSTAFF_ID(Integer.parseInt(STAFF_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setSTAFF_ID(0);
            return res;
        }
    }

    @Override
    public Boolean ProveStaffupdByCon(ProveStaff req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_PROVE_STAFF SET "
                        + "PROVE_ID=  '" + req.getPROVE_ID() + "', "
                        + "STAFF_REF_ID=  '" + req.getSTAFF_REF_ID() + "', "
                        + "TITLE_ID=  '" + req.getTITLE_ID() + "', "
                        + "STAFF_CODE=  '" + req.getSTAFF_CODE() + "', "
                        + "ID_CARD=  '" + req.getID_CARD() + "', "
                        + "STAFF_TYPE=  '" + req.getSTAFF_TYPE() + "', "
                        + "TITLE_NAME_TH=  '" + req.getTITLE_NAME_TH() + "', "
                        + "TITLE_NAME_EN=  '" + req.getTITLE_NAME_EN() + "', "
                        + "TITLE_SHORT_NAME_TH=  '" + req.getTITLE_SHORT_NAME_TH() + "', "
                        + "TITLE_SHORT_NAME_EN=  '" + req.getTITLE_SHORT_NAME_EN() + "', "
                        + "FIRST_NAME=  '" + req.getFIRST_NAME() + "', "
                        + "LAST_NAME=  '" + req.getLAST_NAME() + "', "
                        + "AGE=  '" + req.getAGE() + "', "
                        + "OPERATION_POS_CODE=  '" + req.getOPERATION_POS_CODE() + "', "
                        + "OPREATION_POS_NAME=  '" + req.getOPREATION_POS_NAME() + "', "
                        + "OPREATION_POS_LEVEL=  '" + req.getOPREATION_POS_LEVEL() + "', "
                        + "OPERATION_POS_LEVEL_NAME=  '" + req.getOPERATION_POS_LEVEL_NAME() + "', "
                        + "OPERATION_DEPT_CODE=  '" + req.getOPERATION_DEPT_CODE() + "', "
                        + "OPERATION_DEPT_NAME=  '" + req.getOPERATION_DEPT_NAME() + "', "
                        + "OPERATION_DEPT_LEVEL=  '" + req.getOPERATION_DEPT_LEVEL() + "', "
                        + "OPERATION_UNDER_DEPT_CODE=  '" + req.getOPERATION_UNDER_DEPT_CODE() + "', "
                        + "OPERATION_UNDER_DEPT_NAME=  '" + req.getOPERATION_UNDER_DEPT_NAME() + "', "
                        + "OPERATION_UNDER_DEPT_LEVEL=  '" + req.getOPERATION_UNDER_DEPT_LEVEL() + "', "
                        + "OPERATION_WORK_DEPT_CODE=  '" + req.getOPERATION_WORK_DEPT_CODE() + "', "
                        + "OPERATION_WORK_DEPT_NAME=  '" + req.getOPERATION_WORK_DEPT_NAME() + "', "
                        + "OPERATION_WORK_DEPT_LEVEL=  '" + req.getOPERATION_WORK_DEPT_LEVEL() + "', "
                        + "OPERATION_OFFICE_CODE=  '" + req.getOPERATION_OFFICE_CODE() + "', "
                        + "OPERATION_OFFICE_NAME=  '" + req.getOPERATION_OFFICE_NAME() + "', "
                        + "OPERATION_OFFICE_SHORT_NAME=  '" + req.getOPERATION_OFFICE_SHORT_NAME() + "', "
                        + "MANAGEMENT_POS_CODE=  '" + req.getMANAGEMENT_POS_CODE() + "', "
                        + "MANAGEMENT_POS_NAME=  '" + req.getMANAGEMENT_POS_NAME() + "', "
                        + "MANAGEMENT_POS_LEVEL=  '" + req.getMANAGEMENT_POS_LEVEL() + "', "
                        + "MANAGEMENT_POS_LEVEL_NAME=  '" + req.getMANAGEMENT_POS_LEVEL_NAME() + "', "
                        + "MANAGEMENT_DEPT_CODE=  '" + req.getMANAGEMENT_DEPT_CODE() + "', "
                        + "MANAGEMENT_DEPT_NAME=  '" + req.getMANAGEMENT_DEPT_NAME() + "', "
                        + "MANAGEMENT_DEPT_LEVEL=  '" + req.getMANAGEMENT_DEPT_LEVEL() + "', "
                        + "MANAGEMENT_UNDER_DEPT_CODE=  '" + req.getMANAGEMENT_UNDER_DEPT_CODE() + "', "
                        + "MANAGEMENT_UNDER_DEPT_NAME=  '" + req.getMANAGEMENT_UNDER_DEPT_NAME() + "', "
                        + "MANAGEMENT_UNDER_DEPT_LEVEL=  '" + req.getMANAGEMENT_UNDER_DEPT_LEVEL() + "', "
                        + "MANAGEMENT_WORK_DEPT_CODE=  '" + req.getMANAGEMENT_WORK_DEPT_CODE() + "', "
                        + "MANAGEMENT_WORK_DEPT_NAME=  '" + req.getMANAGEMENT_WORK_DEPT_NAME() + "', "
                        + "MANAGEMENT_WORK_DEPT_LEVEL=  '" + req.getMANAGEMENT_WORK_DEPT_LEVEL() + "', "
                        + "MANAGEMENT_OFFICE_CODE=  '" + req.getMANAGEMENT_OFFICE_CODE() + "', "
                        + "MANAGEMENT_OFFICE_NAME=  '" + req.getMANAGEMENT_OFFICE_NAME() + "', "
                        + "MANAGEMENT_OFFICE_SHORT_NAME=  '" + req.getMANAGEMENT_OFFICE_SHORT_NAME() + "', "
                        + "REPRESENT_POS_CODE=  '" + req.getREPRESENT_POS_CODE() + "', "
                        + "REPRESENT_POS_NAME=  '" + req.getREPRESENT_POS_NAME() + "', "
                        + "REPRESENT_POS_LEVEL=  '" + req.getREPRESENT_POS_LEVEL() + "', "
                        + "REPRESENT_POS_LEVEL_NAME=  '" + req.getREPRESENT_POS_LEVEL_NAME() + "', "
                        + "REPRESENT_DEPT_CODE=  '" + req.getREPRESENT_DEPT_CODE() + "', "
                        + "REPRESENT_DEPT_NAME=  '" + req.getREPRESENT_DEPT_NAME() + "', "
                        + "REPRESENT_DEPT_LEVEL=  '" + req.getREPRESENT_DEPT_LEVEL() + "', "
                        + "REPRESENT_UNDER_DEPT_CODE=  '" + req.getREPRESENT_UNDER_DEPT_CODE() + "', "
                        + "REPRESENT_UNDER_DEPT_NAME=  '" + req.getREPRESENT_UNDER_DEPT_NAME() + "', "
                        + "REPRESENT_UNDER_DEPT_LEVEL=  '" + req.getREPRESENT_UNDER_DEPT_LEVEL() + "', "
                        + "REPRESENT_WORK_DEPT_CODE=  '" + req.getREPRESENT_WORK_DEPT_CODE() + "', "
                        + "REPRESENT_WORK_DEPT_NAME=  '" + req.getREPRESENT_WORK_DEPT_NAME() + "', "
                        + "REPRESENT_WORK_DEPT_LEVEL=  '" + req.getREPRESENT_WORK_DEPT_LEVEL() + "', "
                        + "REPRESENT_OFFICE_CODE=  '" + req.getREPRESENT_OFFICE_CODE() + "', "
                        + "REPRESENT_OFFICE_NAME=  '" + req.getREPRESENT_OFFICE_NAME() + "', "
                        + "REPRESENT_OFFICE_SHORT_NAME=  '" + req.getREPRESENT_OFFICE_SHORT_NAME() + "', "
                        + "STATUS=  '" + req.getSTATUS() + "', "
                        + "REMARK=  '" + req.getREMARK() + "', "
                        + "CONTRIBUTOR_ID=  '" + req.getCONTRIBUTOR_ID() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE STAFF_ID = '" + req.getSTAFF_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        return true;
    }

    @Override
    public Boolean ProveStaffupdDelete(ProveStaffupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_PROVE_STAFF SET IS_ACTIVE = '0' WHERE STAFF_ID = '" + req.getSTAFF_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        return true;
    }
}
