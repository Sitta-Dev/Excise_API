package com.xcs.phase2.dao.evidencein;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestExt;
import com.xcs.phase2.model.evidencein.EvidenceInStaff;
import com.xcs.phase2.request.evidencein.EvidenceInStaffgetByConReq;
import com.xcs.phase2.response.evidencein.EvidenceInStaffResponse;
import com.xcs.phase2.response.evidencein.EvidenceInStaffinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EvidenceInStaffDAOImpl  extends ArrestExt implements EvidenceInStaffDAO{

    private static final Logger log = LoggerFactory.getLogger(EvidenceInStaffDAOImpl.class);

    @Override
    public EvidenceInStaffinsAllResponse EvidenceInStaffinsAll(List<EvidenceInStaff> req) {

        EvidenceInStaffinsAllResponse res = new EvidenceInStaffinsAllResponse();

        try {


            if(req != null) {
                log.info("[Sub] Size : "+req.size());
                List<EvidenceInStaffResponse> list = new ArrayList<EvidenceInStaffResponse>();
                if(req.size() > 0){

                    for(EvidenceInStaff item : req){

                        String EVIDENCE_IN_STAFF_ID = getSequences("SELECT OPS_EVIDENCE_IN_STAFF_SEQ.NEXTVAL FROM DUAL");
                        EvidenceInStaffResponse obj = new EvidenceInStaffResponse();
                        obj.setEVIDENCE_IN_STAFF_ID(Integer.parseInt(EVIDENCE_IN_STAFF_ID));

                        StringBuilder sqlBuilderSubStaff = new StringBuilder()
                                .append("INSERT INTO OPS_EVIDENCE_IN_STAFF (" +
                                        "EVIDENCE_IN_STAFF_ID," +
                                        "EVIDENCE_IN_ID," +
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
                                        "IS_ACTIVE " +
                                        " ) VALUES (" +
                                        "'" + EVIDENCE_IN_STAFF_ID + "', " +
                                        "'" + item.getEVIDENCE_IN_ID() + "', " +
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
                                        "'" + item.getIS_ACTIVE() + "') ");
                        log.info("[SQL] : " + sqlBuilderSubStaff.toString());
                        getJdbcTemplate().update(sqlBuilderSubStaff.toString(), new Object[] {});

                        list.add(obj);
                    }
                }
                res.setEvidenceInStaff(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setEvidenceInStaff(null);
            return res;
        }
    }

    @Override
    public boolean EvidenceInStaffupdByCon(List<EvidenceInStaff> request) {

        if(request != null) {
            log.info("[Sub] Size : "+request.size());

            if(request.size() > 0){

                for(EvidenceInStaff item : request){

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_IN_STAFF SET "
                                    + "EVIDENCE_IN_ID=  '" + item.getEVIDENCE_IN_ID() + "', "
                                    + "STAFF_REF_ID=  '" + item.getSTAFF_REF_ID() + "', "
                                    + "TITLE_ID=  '" + item.getTITLE_ID() + "', "
                                    + "STAFF_CODE=  '" + item.getSTAFF_CODE() + "', "
                                    + "ID_CARD=  '" + item.getID_CARD() + "', "
                                    + "STAFF_TYPE=  '" + item.getSTAFF_TYPE() + "', "
                                    + "TITLE_NAME_TH=  '" + item.getTITLE_NAME_TH() + "', "
                                    + "TITLE_NAME_EN=  '" + item.getTITLE_NAME_EN() + "', "
                                    + "TITLE_SHORT_NAME_TH=  '" + item.getTITLE_SHORT_NAME_TH() + "', "
                                    + "TITLE_SHORT_NAME_EN=  '" + item.getTITLE_SHORT_NAME_EN() + "', "
                                    + "FIRST_NAME=  '" + item.getFIRST_NAME() + "', "
                                    + "LAST_NAME=  '" + item.getLAST_NAME() + "', "
                                    + "AGE=  '" + item.getAGE() + "', "
                                    + "OPERATION_POS_CODE=  '" + item.getOPERATION_POS_CODE() + "', "
                                    + "OPREATION_POS_NAME=  '" + item.getOPREATION_POS_NAME() + "', "
                                    + "OPREATION_POS_LEVEL=  '" + item.getOPREATION_POS_LEVEL() + "', "
                                    + "OPERATION_POS_LEVEL_NAME=  '" + item.getOPERATION_POS_LEVEL_NAME() + "', "
                                    + "OPERATION_DEPT_CODE=  '" + item.getOPERATION_DEPT_CODE() + "', "
                                    + "OPERATION_DEPT_NAME=  '" + item.getOPERATION_DEPT_NAME() + "', "
                                    + "OPERATION_DEPT_LEVEL=  '" + item.getOPERATION_DEPT_LEVEL() + "', "
                                    + "OPERATION_UNDER_DEPT_CODE=  '" + item.getOPERATION_UNDER_DEPT_CODE() + "', "
                                    + "OPERATION_UNDER_DEPT_NAME=  '" + item.getOPERATION_UNDER_DEPT_NAME() + "', "
                                    + "OPERATION_UNDER_DEPT_LEVEL=  '" + item.getOPERATION_UNDER_DEPT_LEVEL() + "', "
                                    + "OPERATION_WORK_DEPT_CODE=  '" + item.getOPERATION_WORK_DEPT_CODE() + "', "
                                    + "OPERATION_WORK_DEPT_NAME=  '" + item.getOPERATION_WORK_DEPT_NAME() + "', "
                                    + "OPERATION_WORK_DEPT_LEVEL=  '" + item.getOPERATION_WORK_DEPT_LEVEL() + "', "
                                    + "OPERATION_OFFICE_CODE=  '" + item.getOPERATION_OFFICE_CODE() + "', "
                                    + "OPERATION_OFFICE_NAME=  '" + item.getOPERATION_OFFICE_NAME() + "', "
                                    + "OPERATION_OFFICE_SHORT_NAME=  '" + item.getOPERATION_OFFICE_SHORT_NAME() + "', "
                                    + "MANAGEMENT_POS_CODE=  '" + item.getMANAGEMENT_POS_CODE() + "', "
                                    + "MANAGEMENT_POS_NAME=  '" + item.getMANAGEMENT_POS_NAME() + "', "
                                    + "MANAGEMENT_POS_LEVEL=  '" + item.getMANAGEMENT_POS_LEVEL() + "', "
                                    + "MANAGEMENT_POS_LEVEL_NAME=  '" + item.getMANAGEMENT_POS_LEVEL_NAME() + "', "
                                    + "MANAGEMENT_DEPT_CODE=  '" + item.getMANAGEMENT_DEPT_CODE() + "', "
                                    + "MANAGEMENT_DEPT_NAME=  '" + item.getMANAGEMENT_DEPT_NAME() + "', "
                                    + "MANAGEMENT_DEPT_LEVEL=  '" + item.getMANAGEMENT_DEPT_LEVEL() + "', "
                                    + "MANAGEMENT_UNDER_DEPT_CODE=  '" + item.getMANAGEMENT_UNDER_DEPT_CODE() + "', "
                                    + "MANAGEMENT_UNDER_DEPT_NAME=  '" + item.getMANAGEMENT_UNDER_DEPT_NAME() + "', "
                                    + "MANAGEMENT_UNDER_DEPT_LEVEL=  '" + item.getMANAGEMENT_UNDER_DEPT_LEVEL() + "', "
                                    + "MANAGEMENT_WORK_DEPT_CODE=  '" + item.getMANAGEMENT_WORK_DEPT_CODE() + "', "
                                    + "MANAGEMENT_WORK_DEPT_NAME=  '" + item.getMANAGEMENT_WORK_DEPT_NAME() + "', "
                                    + "MANAGEMENT_WORK_DEPT_LEVEL=  '" + item.getMANAGEMENT_WORK_DEPT_LEVEL() + "', "
                                    + "MANAGEMENT_OFFICE_CODE=  '" + item.getMANAGEMENT_OFFICE_CODE() + "', "
                                    + "MANAGEMENT_OFFICE_NAME=  '" + item.getMANAGEMENT_OFFICE_NAME() + "', "
                                    + "MANAGEMENT_OFFICE_SHORT_NAME=  '" + item.getMANAGEMENT_OFFICE_SHORT_NAME() + "', "
                                    + "REPRESENT_POS_CODE=  '" + item.getREPRESENT_POS_CODE() + "', "
                                    + "REPRESENT_POS_NAME=  '" + item.getREPRESENT_POS_NAME() + "', "
                                    + "REPRESENT_POS_LEVEL=  '" + item.getREPRESENT_POS_LEVEL() + "', "
                                    + "REPRESENT_POS_LEVEL_NAME=  '" + item.getREPRESENT_POS_LEVEL_NAME() + "', "
                                    + "REPRESENT_DEPT_CODE=  '" + item.getREPRESENT_DEPT_CODE() + "', "
                                    + "REPRESENT_DEPT_NAME=  '" + item.getREPRESENT_DEPT_NAME() + "', "
                                    + "REPRESENT_DEPT_LEVEL=  '" + item.getREPRESENT_DEPT_LEVEL() + "', "
                                    + "REPRESENT_UNDER_DEPT_CODE=  '" + item.getREPRESENT_UNDER_DEPT_CODE() + "', "
                                    + "REPRESENT_UNDER_DEPT_NAME=  '" + item.getREPRESENT_UNDER_DEPT_NAME() + "', "
                                    + "REPRESENT_UNDER_DEPT_LEVEL=  '" + item.getREPRESENT_UNDER_DEPT_LEVEL() + "', "
                                    + "REPRESENT_WORK_DEPT_CODE=  '" + item.getREPRESENT_WORK_DEPT_CODE() + "', "
                                    + "REPRESENT_WORK_DEPT_NAME=  '" + item.getREPRESENT_WORK_DEPT_NAME() + "', "
                                    + "REPRESENT_WORK_DEPT_LEVEL=  '" + item.getREPRESENT_WORK_DEPT_LEVEL() + "', "
                                    + "REPRESENT_OFFICE_CODE=  '" + item.getREPRESENT_OFFICE_CODE() + "', "
                                    + "REPRESENT_OFFICE_NAME=  '" + item.getREPRESENT_OFFICE_NAME() + "', "
                                    + "REPRESENT_OFFICE_SHORT_NAME=  '" + item.getREPRESENT_OFFICE_SHORT_NAME() + "', "
                                    + "STATUS=  '" + item.getSTATUS() + "', "
                                    + "REMARK=  '" + item.getREMARK() + "', "
                                    + "CONTRIBUTOR_ID=  '" + item.getCONTRIBUTOR_ID() + "', "
                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
                                    + " WHERE EVIDENCE_IN_STAFF_ID = '" + item.getEVIDENCE_IN_STAFF_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
                }
            }
        }

        return true;
    }

    @Override
    public List<EvidenceInStaff> EvidenceInStaffgetByCon(EvidenceInStaffgetByConReq req) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "EVIDENCE_IN_STAFF_ID," +
                        "EVIDENCE_IN_ID," +
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
                        " from OPS_EVIDENCE_IN_STAFF where EVIDENCE_IN_ID = " + req.getEVIDENCE_IN_ID() + " and IS_ACTIVE = 1");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInStaff> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceInStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInStaff item = new EvidenceInStaff();
                item.setEVIDENCE_IN_STAFF_ID(rs.getInt("EVIDENCE_IN_STAFF_ID"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
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
}
