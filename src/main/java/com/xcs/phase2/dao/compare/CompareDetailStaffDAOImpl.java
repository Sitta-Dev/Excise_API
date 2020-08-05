package com.xcs.phase2.dao.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.compare.CompareStaff;
import com.xcs.phase2.request.compare.CompareDetailStaffupdDeleteReq;
import com.xcs.phase2.response.compare.CompareDetailStaffinsAllResponse;
import com.xcs.phase2.response.compare.CompareStaffResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompareDetailStaffDAOImpl extends CompareExt implements CompareDetailStaffDAO{

    private static final Logger log = LoggerFactory.getLogger(CompareDetailStaffDAOImpl.class);

    @Override
    public CompareDetailStaffinsAllResponse CompareDetailStaffinsAll(List<CompareStaff> req) {

        CompareDetailStaffinsAllResponse res = new CompareDetailStaffinsAllResponse();

        try {


            if(req != null) {
                log.info("[Sub] Size : "+req.size());
                List<CompareStaffResponse> list = new ArrayList<CompareStaffResponse>();
                if(req.size() > 0){

                    for(CompareStaff item : req){

                        String STAFF_ID = getSequences("SELECT OPS_COMPARE_STAFF_SEQ.NEXTVAL FROM DUAL");
                        CompareStaffResponse  obj = new CompareStaffResponse();
                        obj.setSTAFF_ID(Integer.parseInt(STAFF_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_COMPARE_STAFF ( " +
                                        "STAFF_ID," +
                                        "COMPARE_ID," +
                                        "COMPARE_DETAIL_ID," +
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
                                        " ) values (" +
                                        "'"+STAFF_ID+"'," +
                                        "'"+item.getCOMPARE_ID()+"'," +
                                        "'"+item.getCOMPARE_DETAIL_ID()+"'," +
                                        "'"+item.getSTAFF_REF_ID()+"'," +
                                        "'"+item.getTITLE_ID()+"'," +
                                        "'"+item.getSTAFF_CODE()+"'," +
                                        "'"+item.getID_CARD()+"'," +
                                        "'"+item.getSTAFF_TYPE()+"'," +
                                        "'"+item.getTITLE_NAME_TH()+"'," +
                                        "'"+item.getTITLE_NAME_EN()+"'," +
                                        "'"+item.getTITLE_SHORT_NAME_TH()+"'," +
                                        "'"+item.getTITLE_SHORT_NAME_EN()+"'," +
                                        "'"+item.getFIRST_NAME()+"'," +
                                        "'"+item.getLAST_NAME()+"'," +
                                        "'"+item.getAGE()+"'," +
                                        "'"+item.getOPERATION_POS_CODE()+"'," +
                                        "'"+item.getOPREATION_POS_NAME()+"'," +
                                        "'"+item.getOPREATION_POS_LEVEL()+"'," +
                                        "'"+item.getOPERATION_POS_LEVEL_NAME()+"'," +
                                        "'"+item.getOPERATION_DEPT_CODE()+"'," +
                                        "'"+item.getOPERATION_DEPT_NAME()+"'," +
                                        "'"+item.getOPERATION_DEPT_LEVEL()+"'," +
                                        "'"+item.getOPERATION_UNDER_DEPT_CODE()+"'," +
                                        "'"+item.getOPERATION_UNDER_DEPT_NAME()+"'," +
                                        "'"+item.getOPERATION_UNDER_DEPT_LEVEL()+"'," +
                                        "'"+item.getOPERATION_WORK_DEPT_CODE()+"'," +
                                        "'"+item.getOPERATION_WORK_DEPT_NAME()+"'," +
                                        "'"+item.getOPERATION_WORK_DEPT_LEVEL()+"'," +
                                        "'"+item.getOPERATION_OFFICE_CODE()+"'," +
                                        "'"+item.getOPERATION_OFFICE_NAME()+"'," +
                                        "'"+item.getOPERATION_OFFICE_SHORT_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_POS_CODE()+"'," +
                                        "'"+item.getMANAGEMENT_POS_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_POS_LEVEL()+"'," +
                                        "'"+item.getMANAGEMENT_POS_LEVEL_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_DEPT_CODE()+"'," +
                                        "'"+item.getMANAGEMENT_DEPT_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_DEPT_LEVEL()+"'," +
                                        "'"+item.getMANAGEMENT_UNDER_DEPT_CODE()+"'," +
                                        "'"+item.getMANAGEMENT_UNDER_DEPT_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_UNDER_DEPT_LEVEL()+"'," +
                                        "'"+item.getMANAGEMENT_WORK_DEPT_CODE()+"'," +
                                        "'"+item.getMANAGEMENT_WORK_DEPT_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_WORK_DEPT_LEVEL()+"'," +
                                        "'"+item.getMANAGEMENT_OFFICE_CODE()+"'," +
                                        "'"+item.getMANAGEMENT_OFFICE_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_OFFICE_SHORT_NAME()+"'," +
                                        "'"+item.getREPRESENT_POS_CODE()+"'," +
                                        "'"+item.getREPRESENT_POS_NAME()+"'," +
                                        "'"+item.getREPRESENT_POS_LEVEL()+"'," +
                                        "'"+item.getREPRESENT_POS_LEVEL_NAME()+"'," +
                                        "'"+item.getREPRESENT_DEPT_CODE()+"'," +
                                        "'"+item.getREPRESENT_DEPT_NAME()+"'," +
                                        "'"+item.getREPRESENT_DEPT_LEVEL()+"'," +
                                        "'"+item.getREPRESENT_UNDER_DEPT_CODE()+"'," +
                                        "'"+item.getREPRESENT_UNDER_DEPT_NAME()+"'," +
                                        "'"+item.getREPRESENT_UNDER_DEPT_LEVEL()+"'," +
                                        "'"+item.getREPRESENT_WORK_DEPT_CODE()+"'," +
                                        "'"+item.getREPRESENT_WORK_DEPT_NAME()+"'," +
                                        "'"+item.getREPRESENT_WORK_DEPT_LEVEL()+"'," +
                                        "'"+item.getREPRESENT_OFFICE_CODE()+"'," +
                                        "'"+item.getREPRESENT_OFFICE_NAME()+"'," +
                                        "'"+item.getREPRESENT_OFFICE_SHORT_NAME()+"'," +
                                        "'"+item.getSTATUS()+"'," +
                                        "'"+item.getREMARK()+"'," +
                                        "'"+item.getCONTRIBUTOR_ID()+"'," +
                                        "'"+item.getIS_ACTIVE()+"'" +
                                        " )");
                        log.info("[SQL] : "+sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});

                        list.add(obj);
                    }
                }
                res.setCompareStaff(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setCompareStaff(null);
            return res;
        }
    }

    @Override
    public Boolean CompareDetailStaffupdByCon(List<CompareStaff> request) {

        if(request != null) {
            log.info("[Sub] Size : "+request.size());

            if(request.size() > 0){

                for(CompareStaff req : request){

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_COMPARE_STAFF "
                                    + "SET "
                                    +"COMPARE_ID = '"+req.getCOMPARE_ID()+"',"
                                    +"STAFF_REF_ID = '"+req.getSTAFF_REF_ID()+"',"
                                    +"TITLE_ID = '"+req.getTITLE_ID()+"',"
                                    +"STAFF_CODE = '"+req.getSTAFF_CODE()+"',"
                                    +"ID_CARD = '"+req.getID_CARD()+"',"
                                    +"STAFF_TYPE = '"+req.getSTAFF_TYPE()+"',"
                                    +"TITLE_NAME_TH = '"+req.getTITLE_NAME_TH()+"',"
                                    +"TITLE_NAME_EN = '"+req.getTITLE_NAME_EN()+"',"
                                    +"TITLE_SHORT_NAME_TH = '"+req.getTITLE_SHORT_NAME_TH()+"',"
                                    +"TITLE_SHORT_NAME_EN = '"+req.getTITLE_SHORT_NAME_EN()+"',"
                                    +"FIRST_NAME = '"+req.getFIRST_NAME()+"',"
                                    +"LAST_NAME = '"+req.getLAST_NAME()+"',"
                                    +"AGE = '"+req.getAGE()+"',"
                                    +"OPERATION_POS_CODE = '"+req.getOPERATION_POS_CODE()+"',"
                                    +"OPREATION_POS_NAME = '"+req.getOPREATION_POS_NAME()+"',"
                                    +"OPREATION_POS_LEVEL = '"+req.getOPREATION_POS_LEVEL()+"',"
                                    +"OPERATION_POS_LEVEL_NAME = '"+req.getOPERATION_POS_LEVEL_NAME()+"',"
                                    +"OPERATION_DEPT_CODE = '"+req.getOPERATION_DEPT_CODE()+"',"
                                    +"OPERATION_DEPT_NAME = '"+req.getOPERATION_DEPT_NAME()+"',"
                                    +"OPERATION_DEPT_LEVEL = '"+req.getOPERATION_DEPT_LEVEL()+"',"
                                    +"OPERATION_UNDER_DEPT_CODE = '"+req.getOPERATION_UNDER_DEPT_CODE()+"',"
                                    +"OPERATION_UNDER_DEPT_NAME = '"+req.getOPERATION_UNDER_DEPT_NAME()+"',"
                                    +"OPERATION_UNDER_DEPT_LEVEL = '"+req.getOPERATION_UNDER_DEPT_LEVEL()+"',"
                                    +"OPERATION_WORK_DEPT_CODE = '"+req.getOPERATION_WORK_DEPT_CODE()+"',"
                                    +"OPERATION_WORK_DEPT_NAME = '"+req.getOPERATION_WORK_DEPT_NAME()+"',"
                                    +"OPERATION_WORK_DEPT_LEVEL = '"+req.getOPERATION_WORK_DEPT_LEVEL()+"',"
                                    +"OPERATION_OFFICE_CODE = '"+req.getOPERATION_OFFICE_CODE()+"',"
                                    +"OPERATION_OFFICE_NAME = '"+req.getOPERATION_OFFICE_NAME()+"',"
                                    +"OPERATION_OFFICE_SHORT_NAME = '"+req.getOPERATION_OFFICE_SHORT_NAME()+"',"
                                    +"MANAGEMENT_POS_CODE = '"+req.getMANAGEMENT_POS_CODE()+"',"
                                    +"MANAGEMENT_POS_NAME = '"+req.getMANAGEMENT_POS_NAME()+"',"
                                    +"MANAGEMENT_POS_LEVEL = '"+req.getMANAGEMENT_POS_LEVEL()+"',"
                                    +"MANAGEMENT_POS_LEVEL_NAME = '"+req.getMANAGEMENT_POS_LEVEL_NAME()+"',"
                                    +"MANAGEMENT_DEPT_CODE = '"+req.getMANAGEMENT_DEPT_CODE()+"',"
                                    +"MANAGEMENT_DEPT_NAME = '"+req.getMANAGEMENT_DEPT_NAME()+"',"
                                    +"MANAGEMENT_DEPT_LEVEL = '"+req.getMANAGEMENT_DEPT_LEVEL()+"',"
                                    +"MANAGEMENT_UNDER_DEPT_CODE = '"+req.getMANAGEMENT_UNDER_DEPT_CODE()+"',"
                                    +"MANAGEMENT_UNDER_DEPT_NAME = '"+req.getMANAGEMENT_UNDER_DEPT_NAME()+"',"
                                    +"MANAGEMENT_UNDER_DEPT_LEVEL = '"+req.getMANAGEMENT_UNDER_DEPT_LEVEL()+"',"
                                    +"MANAGEMENT_WORK_DEPT_CODE = '"+req.getMANAGEMENT_WORK_DEPT_CODE()+"',"
                                    +"MANAGEMENT_WORK_DEPT_NAME = '"+req.getMANAGEMENT_WORK_DEPT_NAME()+"',"
                                    +"MANAGEMENT_WORK_DEPT_LEVEL = '"+req.getMANAGEMENT_WORK_DEPT_LEVEL()+"',"
                                    +"MANAGEMENT_OFFICE_CODE = '"+req.getMANAGEMENT_OFFICE_CODE()+"',"
                                    +"MANAGEMENT_OFFICE_NAME = '"+req.getMANAGEMENT_OFFICE_NAME()+"',"
                                    +"MANAGEMENT_OFFICE_SHORT_NAME = '"+req.getMANAGEMENT_OFFICE_SHORT_NAME()+"',"
                                    +"REPRESENT_POS_CODE = '"+req.getREPRESENT_POS_CODE()+"',"
                                    +"REPRESENT_POS_NAME = '"+req.getREPRESENT_POS_NAME()+"',"
                                    +"REPRESENT_POS_LEVEL = '"+req.getREPRESENT_POS_LEVEL()+"',"
                                    +"REPRESENT_POS_LEVEL_NAME = '"+req.getREPRESENT_POS_LEVEL_NAME()+"',"
                                    +"REPRESENT_DEPT_CODE = '"+req.getREPRESENT_DEPT_CODE()+"',"
                                    +"REPRESENT_DEPT_NAME = '"+req.getREPRESENT_DEPT_NAME()+"',"
                                    +"REPRESENT_DEPT_LEVEL = '"+req.getREPRESENT_DEPT_LEVEL()+"',"
                                    +"REPRESENT_UNDER_DEPT_CODE = '"+req.getREPRESENT_UNDER_DEPT_CODE()+"',"
                                    +"REPRESENT_UNDER_DEPT_NAME = '"+req.getREPRESENT_UNDER_DEPT_NAME()+"',"
                                    +"REPRESENT_UNDER_DEPT_LEVEL = '"+req.getREPRESENT_UNDER_DEPT_LEVEL()+"',"
                                    +"REPRESENT_WORK_DEPT_CODE = '"+req.getREPRESENT_WORK_DEPT_CODE()+"',"
                                    +"REPRESENT_WORK_DEPT_NAME = '"+req.getREPRESENT_WORK_DEPT_NAME()+"',"
                                    +"REPRESENT_WORK_DEPT_LEVEL = '"+req.getREPRESENT_WORK_DEPT_LEVEL()+"',"
                                    +"REPRESENT_OFFICE_CODE = '"+req.getREPRESENT_OFFICE_CODE()+"',"
                                    +"REPRESENT_OFFICE_NAME = '"+req.getREPRESENT_OFFICE_NAME()+"',"
                                    +"REPRESENT_OFFICE_SHORT_NAME = '"+req.getREPRESENT_OFFICE_SHORT_NAME()+"',"
                                    +"STATUS = '"+req.getSTATUS()+"',"
                                    +"REMARK = '"+req.getREMARK()+"',"
                                    +"CONTRIBUTOR_ID = '"+req.getCONTRIBUTOR_ID()+"',"
                                    +"IS_ACTIVE = '"+req.getIS_ACTIVE()+"' "
                                    + "WHERE STAFF_ID = '"+req.getSTAFF_ID()+"' ");
                    log.info("[SQL] : "+sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
                }
            }
        }

        return true;
    }

    @Override
    public Boolean CompareDetailStaffupdDelete(List<CompareDetailStaffupdDeleteReq> req) {

        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(CompareDetailStaffupdDeleteReq item : req){

                    StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_COMPARE_STAFF SET IS_ACTIVE = '0' WHERE STAFF_ID = '"+item.getSTAFF_ID()+"' ");
                    log.info("[SQL] Sub : "+sqlBuilder.toString());
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

                }
            }
        }

        return true;
    }


}
