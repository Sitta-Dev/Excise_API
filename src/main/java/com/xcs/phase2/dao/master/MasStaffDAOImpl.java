package com.xcs.phase2.dao.master;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.master.MasStaff;
import com.xcs.phase2.request.master.MasStaffgetByConReq;
import com.xcs.phase2.response.master.MasStaffgetByConResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class MasStaffDAOImpl extends MasterExt implements MasStaffDAO{

    private static final Logger log = LoggerFactory.getLogger(MasStaffDAOImpl.class);

    @Override
    public List<MasStaff> MasStaffgetByCon(MasStaffgetByConReq req) {

        String str = "";

        if(!StringUtils.isEmpty(req.getSTAFF_ID())){

            str = " AND MAS_STAFF.STAFF_ID = '"+req.getSTAFF_ID()+"'";
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT " +
                        "STAFF_ID," +
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
                        "to_char(BIRTH_DATE,'"+ Pattern.FORMAT_DATETIME+"') as BIRTH_DATE," +
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
                        "IS_ACTIVE" +
                        "  FROM MAS_STAFF" +
                        "  WHERE " +
                        "  (" +
                        "    LOWER(MAS_STAFF.STAFF_CODE) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.ID_CARD) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.TITLE_NAME_TH||MAS_STAFF.FIRST_NAME||MAS_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.TITLE_NAME_EN||MAS_STAFF.FIRST_NAME||MAS_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.TITLE_SHORT_NAME_TH||MAS_STAFF.FIRST_NAME||MAS_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.TITLE_SHORT_NAME_EN||MAS_STAFF.FIRST_NAME||MAS_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.OPREATION_POS_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.OPERATION_OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.MANAGEMENT_POS_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.MANAGEMENT_OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.REPRESENT_POS_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER(MAS_STAFF.REPRESENT_OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  )" +
                        "  AND MAS_STAFF.IS_ACTIVE = 1"+str);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasStaff item = new MasStaff();
                item.setSTAFF_ID(rs.getInt("STAFF_ID"));
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
                item.setBIRTH_DATE(rs.getString("BIRTH_DATE"));
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
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public MasStaffgetByConResponse MasStaffgetByConNew(MasStaffgetByConReq req) {
        MasStaffgetByConResponse masStaffgetByConResponse = new MasStaffgetByConResponse();
        try {
            masStaffgetByConResponse.setSUCCESS(true);
            masStaffgetByConResponse.setRESPONSE_DATA(this.MasStaffgetByCon(req));
        }catch (Exception e){
            masStaffgetByConResponse.setRESPONSE_DATA(null);
            masStaffgetByConResponse.setSUCCESS(false);
        }

        return masStaffgetByConResponse;
    }

    @Override
    public MasStaff MasStaffgetById(int staffId) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT " +
                        "STAFF_ID," +
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
                        "to_char(BIRTH_DATE,'"+ Pattern.FORMAT_DATETIME+"') as BIRTH_DATE," +
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
                        "IS_ACTIVE" +
                        "  FROM MAS_STAFF" +
                        "  WHERE  STAFF_ID = "+staffId);

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasStaff>() {

            public MasStaff extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasStaff item = new MasStaff();
                    item.setSTAFF_ID(rs.getInt("STAFF_ID"));
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
                    item.setBIRTH_DATE(rs.getString("BIRTH_DATE"));
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
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    return item;
                }

                return null;
            }
        });
    }
}
