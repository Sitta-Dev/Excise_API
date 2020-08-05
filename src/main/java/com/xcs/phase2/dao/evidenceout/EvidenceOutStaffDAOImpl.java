package com.xcs.phase2.dao.evidenceout;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestExt;
import com.xcs.phase2.model.evidenceout.EvidenceOutStaff;
import com.xcs.phase2.request.evidenceout.EvidenceOutStaffupdDeleteReq;
import com.xcs.phase2.response.evidenceout.EvidenceOutStaffResponse;
import com.xcs.phase2.response.evidenceout.EvidenceOutStaffinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EvidenceOutStaffDAOImpl extends ArrestExt implements EvidenceOutStaffDAO{

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutStaffDAOImpl.class);

    @Override
    public EvidenceOutStaffinsAllResponse EvidenceOutStaffinsAll(List<EvidenceOutStaff> req) {

        EvidenceOutStaffinsAllResponse res = new EvidenceOutStaffinsAllResponse();

        try {


            if(req != null) {
                log.info("[Sub] Size : "+req.size());
                List<EvidenceOutStaffResponse> list = new ArrayList<EvidenceOutStaffResponse>();
                if(req.size() > 0){

                    for(EvidenceOutStaff item : req){

                        String EVIDENCE_OUT_STAFF_ID = getSequences("SELECT OPS_EVIDENCE_OUT_STAFF_SEQ.NEXTVAL FROM DUAL");
                        EvidenceOutStaffResponse obj = new EvidenceOutStaffResponse();
                        obj.setEVIDENCE_OUT_STAFF_ID(Integer.parseInt(EVIDENCE_OUT_STAFF_ID));

                        StringBuilder sqlBuilderSubStaff = new StringBuilder()
                                .append("INSERT INTO OPS_EVIDENCE_OUT_STAFF (" +
                                        "EVIDENCE_OUT_STAFF_ID," +
                                        "EVIDENCE_OUT_ID," +
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
                                        "'" + EVIDENCE_OUT_STAFF_ID + "', " +
                                        "'" + item.getEVIDENCE_OUT_ID() + "', " +
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
                        getJdbcTemplate().update(sqlBuilderSubStaff.toString(), new Object[] {});

                        list.add(obj);
                    }
                }
                res.setEvidenceOutStaff(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setEvidenceOutStaff(null);
            return res;
        }
    }

    @Override
    public boolean EvidenceOutStaffupdByCon(List<EvidenceOutStaff> request) {

        if(request != null) {
            log.info("[Sub] Size : "+request.size());

            if(request.size() > 0){

                for(EvidenceOutStaff item : request){

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_OUT_STAFF SET "
                                    + "EVIDENCE_OUT_ID=  '" + item.getEVIDENCE_OUT_ID() + "', "
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
                                    + " WHERE EVIDENCE_OUT_STAFF_ID = '" + item.getEVIDENCE_OUT_STAFF_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
                }
            }
        }

        return true;
    }

    @Override
    public boolean EvidenceOutStaffupdDelete(EvidenceOutStaffupdDeleteReq req) {


        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_EVIDENCE_OUT_STAFF SET IS_ACTIVE = '0' WHERE EVIDENCE_OUT_STAFF_ID = '"+req.getEVIDENCE_OUT_STAFF_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[] {});


        return true;

    }

}
