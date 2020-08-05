package com.xcs.phase2.dao.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.compare.*;
import com.xcs.phase2.request.compare.CompareVerifyCompareNoReq;
import com.xcs.phase2.request.compare.ComparegetByConReq;
import com.xcs.phase2.request.compare.CompareupdDeleteReq;
import com.xcs.phase2.response.compare.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompareDAOImpl extends CompareExt implements CompareDAO{

    private static final Logger log = LoggerFactory.getLogger(CompareDAOImpl.class);

    @Override
    public Compare ComparegetByCon(ComparegetByConReq req){

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "COMPARE_ID," +
                        "LAWSUIT_ID," +
                        "OFFICE_ID," +
                        "TREASURY_RATE," +
                        "BRIBE_RATE," +
                        "REWARD_RATE," +
                        "OFFICE_CODE," +
                        "OFFICE_NAME," +
                        "COMPARE_NO," +
                        "to_char(COMPARE_NO_YEAR,'"+ Pattern.FORMAT_DATETIME+"') as COMPARE_NO_YEAR," +
                        "to_char(COMPARE_DATE,'"+Pattern.FORMAT_DATETIME+"') as COMPARE_DATE," +
                        "IS_OUTSIDE," +
                        "IS_ACTIVE," +
                        "to_char(CREATE_DATE,'"+Pattern.FORMAT_DATETIME+"') as CREATE_DATE," +
                        "CREATE_USER_ACCOUNT_ID," +
                        "to_char(UPDATE_DATE,'"+Pattern.FORMAT_DATETIME+"') as UPDATE_DATE," +
                        "UPDATE_USER_ACCOUNT_ID " +
                        "from OPS_COMPARE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and COMPARE_ID = '"+req.getCOMPARE_ID()+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Compare>() {

            public Compare extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    Compare item = new Compare();
                    item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                    item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                    item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                    item.setTREASURY_RATE(rs.getFloat("TREASURY_RATE"));
                    item.setBRIBE_RATE(rs.getFloat("BRIBE_RATE"));
                    item.setREWARD_RATE(rs.getFloat("REWARD_RATE"));
                    item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                    item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                    item.setCOMPARE_NO(rs.getInt("COMPARE_NO"));
                    item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
                    item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                    item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                    item.setCREATE_USER_ACCOUNT_ID(rs.getInt("CREATE_USER_ACCOUNT_ID"));
                    item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                    item.setUPDATE_USER_ACCOUNT_ID(rs.getInt("UPDATE_USER_ACCOUNT_ID"));


                    item.setCompareMapping(getCompareMapping(rs.getInt("COMPARE_ID")));


                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public CompareinsAllResponse CompareinsAll(Compare req){

        CompareinsAllResponse res = new CompareinsAllResponse();

        try {

            String COMPARE_ID = getSequences("SELECT OPS_COMPARE_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_COMPARE ( " +
                            "COMPARE_ID," +
                            "LAWSUIT_ID," +
                            "OFFICE_ID," +
                            "TREASURY_RATE," +
                            "BRIBE_RATE," +
                            "REWARD_RATE," +
                            "OFFICE_CODE," +
                            "OFFICE_NAME," +
                            "COMPARE_NO," +
                            "COMPARE_NO_YEAR," +
                            "COMPARE_DATE," +
                            "IS_OUTSIDE," +
                            "IS_ACTIVE," +
                            "CREATE_DATE," +
                            "CREATE_USER_ACCOUNT_ID," +
                            "UPDATE_DATE," +
                            "UPDATE_USER_ACCOUNT_ID " +
                            " ) values (" +
                            "'"+COMPARE_ID+"'," +
                            "'"+req.getLAWSUIT_ID()+"'," +
                            "'"+req.getOFFICE_ID()+"'," +
                            "'"+req.getTREASURY_RATE()+"'," +
                            "'"+req.getBRIBE_RATE()+"'," +
                            "'"+req.getREWARD_RATE()+"'," +
                            "'"+req.getOFFICE_CODE()+"'," +
                            "'"+req.getOFFICE_NAME()+"'," +
                            "'"+req.getCOMPARE_NO()+"'," +
                            "TO_TIMESTAMP_TZ('"+req.getCOMPARE_NO_YEAR()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                            "TO_TIMESTAMP_TZ('"+req.getCOMPARE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                            "'"+req.getIS_OUTSIDE()+"'," +
                            "'"+req.getIS_ACTIVE()+"'," +
                            "TO_TIMESTAMP_TZ('"+req.getCREATE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                            "'"+req.getCREATE_USER_ACCOUNT_ID()+"'," +
                            "TO_TIMESTAMP_TZ('"+req.getUPDATE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                            "'"+req.getUPDATE_USER_ACCOUNT_ID()+"' " +
                            " )");

            log.info("[SQL] : "+sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
            res.setCOMPARE_ID(Integer.parseInt(COMPARE_ID));




            if(req.getCompareMapping() != null) {
                log.info("[Sub] Size : "+req.getCompareMapping().size());
                List<CompareMappingResponse> list = new ArrayList<CompareMappingResponse>();

                if(req.getCompareMapping().size() > 0){
                    for(CompareMapping item : req.getCompareMapping()){

                        String COMPARE_MAPPING_ID = getSequences("SELECT OPS_COMPARE_MAPPING_SEQ.NEXTVAL FROM DUAL");
                        CompareMappingResponse  obj = new CompareMappingResponse();
                        obj.setCOMPARE_MAPPING_ID(Integer.parseInt(COMPARE_MAPPING_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_COMPARE_MAPPING ( " +
                                        "COMPARE_MAPPING_ID," +
                                        "COMPARE_ID," +
                                        "INDICTMENT_DETAIL_ID," +
                                        "PAST_LAWSUIT_ID," +
                                        "IS_EVER_WRONG," +
                                        "IS_ACTIVE " +
                                        " ) values (" +
                                        "'"+COMPARE_MAPPING_ID+"'," +
                                        "'"+COMPARE_ID+"'," +
                                        "'"+item.getINDICTMENT_DETAIL_ID()+"'," +
                                        "'"+item.getPAST_LAWSUIT_ID()+"'," +
                                        "'"+item.getIS_EVER_WRONG()+"'," +
                                        "'"+item.getIS_ACTIVE()+"'" +
                                        " )");
                        log.info("[SQL] : "+sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});

                        // st compareDetail
                        if(item.getCompareDetail() != null) {
                            log.info("[Sub] Size : "+item.getCompareDetail().size());
                            List<CompareDetailResponse> compareDetailList = new ArrayList<CompareDetailResponse>();

                            if(item.getCompareDetail().size() > 0){
                                for(CompareDetail item1 : item.getCompareDetail()){

                                    String COMPARE_DETAIL_ID = getSequences("SELECT OPS_COMPARE_DETAIL_SEQ.NEXTVAL FROM DUAL");
                                    CompareDetailResponse  objCompareDetail = new CompareDetailResponse();
                                    objCompareDetail.setCOMPARE_DETAIL_ID(Integer.parseInt(COMPARE_DETAIL_ID));

                                    StringBuilder sqlBuilderCompareDetail = new StringBuilder()
                                            .append("Insert into OPS_COMPARE_DETAIL ( " +
                                                    "COMPARE_DETAIL_ID," +
                                                    "COMPARE_MAPPING_ID," +
                                                    "RECEIPT_OFFICE_ID," +
                                                    "APPROVE_OFFICE_ID," +
                                                    "MISTREAT_NO," +
                                                    "OLD_PAYMENT_FINE," +
                                                    "PAYMENT_FINE," +
                                                    "DIFFERENCE_PAYMENT_FINE," +
                                                    "TREASURY_MONEY," +
                                                    "BRIBE_MONEY," +
                                                    "REWARD_MONEY," +
                                                    "PAYMENT_FINE_DUE_DATE," +
                                                    "PAYMENT_VAT_DUE_DATE," +
                                                    "INSURANCE," +
                                                    "GAURANTEE," +
                                                    "PAYMENT_DATE," +
                                                    "RECEIPT_TYPE," +
                                                    "RECEIPT_BOOK_NO," +
                                                    "RECEIPT_NO," +
                                                    "RECEIPT_OFFICE_CODE," +
                                                    "RECEIPT_OFFICE_NAME," +
                                                    "APPROVE_OFFICE_CODE," +
                                                    "APPROVE_OFFICE_NAME," +
                                                    "APPROVE_DATE," +
                                                    "APPROVE_TYPE," +
                                                    "COMMAND_NO," +
                                                    "COMMAND_DATE," +
                                                    "REMARK_NOT_AGREE," +
                                                    "REMARK_NOT_APPROVE," +
                                                    "FACT," +
                                                    "COMPARE_REASON," +
                                                    "ADJUST_REASON," +
                                                    "COMPARE_TYPE," +
                                                    "IS_REQUEST," +
                                                    "IS_TEMP_RELEASE," +
                                                    "IS_INSURANCE," +
                                                    "IS_GAURANTEE," +
                                                    "IS_PAYMENT," +
                                                    "IS_REVENUE," +
                                                    "IS_AGREE," +
                                                    "IS_APPROVE," +
                                                    "IS_AUTHORITY," +
                                                    "DID," +
                                                    "IS_ACTIVE " +
                                                    " ) values (" +
                                                    "'"+COMPARE_DETAIL_ID+"'," +
                                                    "'"+COMPARE_MAPPING_ID+"'," +
                                                    "'"+item1.getRECEIPT_OFFICE_ID()+"'," +
                                                    "'"+item1.getAPPROVE_OFFICE_ID()+"'," +
                                                    "'"+item1.getMISTREAT_NO()+"'," +
                                                    "'"+item1.getOLD_PAYMENT_FINE()+"'," +
                                                    "'"+item1.getPAYMENT_FINE()+"'," +
                                                    "'"+item1.getDIFFERENCE_PAYMENT_FINE()+"'," +
                                                    "'"+item1.getTREASURY_MONEY()+"'," +
                                                    "'"+item1.getBRIBE_MONEY()+"'," +
                                                    "'"+item1.getREWARD_MONEY()+"'," +
                                                    "TO_TIMESTAMP_TZ('"+item1.getPAYMENT_FINE_DUE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                                                    "TO_TIMESTAMP_TZ('"+item1.getPAYMENT_VAT_DUE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                                                    "'"+item1.getINSURANCE()+"'," +
                                                    "'"+item1.getGAURANTEE()+"'," +
                                                    "TO_TIMESTAMP_TZ('"+item1.getPAYMENT_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                                                    "'"+item1.getRECEIPT_TYPE()+"'," +
                                                    "'"+item1.getRECEIPT_BOOK_NO()+"'," +
                                                    "'"+item1.getRECEIPT_NO()+"'," +
                                                    "'"+item1.getRECEIPT_OFFICE_CODE()+"'," +
                                                    "'"+item1.getRECEIPT_OFFICE_NAME()+"'," +
                                                    "'"+item1.getAPPROVE_OFFICE_CODE()+"'," +
                                                    "'"+item1.getAPPROVE_OFFICE_NAME()+"'," +
                                                    "TO_TIMESTAMP_TZ('"+item1.getAPPROVE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                                                    "'"+item1.getAPPROVE_TYPE()+"'," +
                                                    "'"+item1.getCOMMAND_NO()+"'," +
                                                    "TO_TIMESTAMP_TZ('"+item1.getCOMMAND_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                                                    "'"+item1.getREMARK_NOT_AGREE()+"'," +
                                                    "'"+item1.getREMARK_NOT_APPROVE()+"'," +
                                                    "'"+item1.getFACT()+"'," +
                                                    "'"+item1.getCOMPARE_REASON()+"'," +
                                                    "'"+item1.getADJUST_REASON()+"'," +
                                                    "'"+item1.getCOMPARE_TYPE()+"'," +
                                                    "'"+item1.getIS_REQUEST()+"'," +
                                                    "'"+item1.getIS_TEMP_RELEASE()+"'," +
                                                    "'"+item1.getIS_INSURANCE()+"'," +
                                                    "'"+item1.getIS_GAURANTEE()+"'," +
                                                    "'"+item1.getIS_PAYMENT()+"'," +
                                                    "'"+item1.getIS_REVENUE()+"'," +
                                                    "'"+item1.getIS_AGREE()+"'," +
                                                    "'"+item1.getIS_APPROVE()+"'," +
                                                    "'"+item1.getIS_AUTHORITY()+"'," +
                                                    "'"+item1.getDID()+"'," +
                                                    "'"+item1.getIS_ACTIVE()+"'" +
                                                    " )");
                                    log.info("[SQL] : "+sqlBuilderCompareDetail.toString());

                                    getJdbcTemplate().update(sqlBuilderCompareDetail.toString(), new Object[] {});


                                    if(item1.getCompareStaff() != null) {
                                        log.info("[Sub] Size : "+item1.getCompareStaff().size());
                                        List<CompareStaffResponse> listCS = new ArrayList<CompareStaffResponse>();

                                        if(item1.getCompareStaff().size() > 0){
                                            for(CompareStaff itemCS : item1.getCompareStaff()){

                                                String STAFF_ID = getSequences("SELECT OPS_COMPARE_STAFF_SEQ.NEXTVAL FROM DUAL");
                                                CompareStaffResponse  objCS = new CompareStaffResponse();
                                                objCS.setSTAFF_ID(Integer.parseInt(STAFF_ID));

                                                StringBuilder sqlBuilderCS = new StringBuilder()
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
                                                                "'"+COMPARE_ID+"'," +
                                                                "'"+COMPARE_DETAIL_ID+"'," +
                                                                "'"+itemCS.getSTAFF_REF_ID()+"'," +
                                                                "'"+itemCS.getTITLE_ID()+"'," +
                                                                "'"+itemCS.getSTAFF_CODE()+"'," +
                                                                "'"+itemCS.getID_CARD()+"'," +
                                                                "'"+itemCS.getSTAFF_TYPE()+"'," +
                                                                "'"+itemCS.getTITLE_NAME_TH()+"'," +
                                                                "'"+itemCS.getTITLE_NAME_EN()+"'," +
                                                                "'"+itemCS.getTITLE_SHORT_NAME_TH()+"'," +
                                                                "'"+itemCS.getTITLE_SHORT_NAME_EN()+"'," +
                                                                "'"+itemCS.getFIRST_NAME()+"'," +
                                                                "'"+itemCS.getLAST_NAME()+"'," +
                                                                "'"+itemCS.getAGE()+"'," +
                                                                "'"+itemCS.getOPERATION_POS_CODE()+"'," +
                                                                "'"+itemCS.getOPREATION_POS_NAME()+"'," +
                                                                "'"+itemCS.getOPREATION_POS_LEVEL()+"'," +
                                                                "'"+itemCS.getOPERATION_POS_LEVEL_NAME()+"'," +
                                                                "'"+itemCS.getOPERATION_DEPT_CODE()+"'," +
                                                                "'"+itemCS.getOPERATION_DEPT_NAME()+"'," +
                                                                "'"+itemCS.getOPERATION_DEPT_LEVEL()+"'," +
                                                                "'"+itemCS.getOPERATION_UNDER_DEPT_CODE()+"'," +
                                                                "'"+itemCS.getOPERATION_UNDER_DEPT_NAME()+"'," +
                                                                "'"+itemCS.getOPERATION_UNDER_DEPT_LEVEL()+"'," +
                                                                "'"+itemCS.getOPERATION_WORK_DEPT_CODE()+"'," +
                                                                "'"+itemCS.getOPERATION_WORK_DEPT_NAME()+"'," +
                                                                "'"+itemCS.getOPERATION_WORK_DEPT_LEVEL()+"'," +
                                                                "'"+itemCS.getOPERATION_OFFICE_CODE()+"'," +
                                                                "'"+itemCS.getOPERATION_OFFICE_NAME()+"'," +
                                                                "'"+itemCS.getOPERATION_OFFICE_SHORT_NAME()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_POS_CODE()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_POS_NAME()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_POS_LEVEL()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_POS_LEVEL_NAME()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_DEPT_CODE()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_DEPT_NAME()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_DEPT_LEVEL()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_UNDER_DEPT_CODE()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_UNDER_DEPT_NAME()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_UNDER_DEPT_LEVEL()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_WORK_DEPT_CODE()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_WORK_DEPT_NAME()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_WORK_DEPT_LEVEL()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_OFFICE_CODE()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_OFFICE_NAME()+"'," +
                                                                "'"+itemCS.getMANAGEMENT_OFFICE_SHORT_NAME()+"'," +
                                                                "'"+itemCS.getREPRESENT_POS_CODE()+"'," +
                                                                "'"+itemCS.getREPRESENT_POS_NAME()+"'," +
                                                                "'"+itemCS.getREPRESENT_POS_LEVEL()+"'," +
                                                                "'"+itemCS.getREPRESENT_POS_LEVEL_NAME()+"'," +
                                                                "'"+itemCS.getREPRESENT_DEPT_CODE()+"'," +
                                                                "'"+itemCS.getREPRESENT_DEPT_NAME()+"'," +
                                                                "'"+itemCS.getREPRESENT_DEPT_LEVEL()+"'," +
                                                                "'"+itemCS.getREPRESENT_UNDER_DEPT_CODE()+"'," +
                                                                "'"+itemCS.getREPRESENT_UNDER_DEPT_NAME()+"'," +
                                                                "'"+itemCS.getREPRESENT_UNDER_DEPT_LEVEL()+"'," +
                                                                "'"+itemCS.getREPRESENT_WORK_DEPT_CODE()+"'," +
                                                                "'"+itemCS.getREPRESENT_WORK_DEPT_NAME()+"'," +
                                                                "'"+itemCS.getREPRESENT_WORK_DEPT_LEVEL()+"'," +
                                                                "'"+itemCS.getREPRESENT_OFFICE_CODE()+"'," +
                                                                "'"+itemCS.getREPRESENT_OFFICE_NAME()+"'," +
                                                                "'"+itemCS.getREPRESENT_OFFICE_SHORT_NAME()+"'," +
                                                                "'"+itemCS.getSTATUS()+"'," +
                                                                "'"+itemCS.getREMARK()+"'," +
                                                                "'"+itemCS.getCONTRIBUTOR_ID()+"'," +
                                                                "'"+item.getIS_ACTIVE()+"'" +
                                                                " )");
                                                log.info("[SQL] : "+sqlBuilderCS.toString());

                                                getJdbcTemplate().update(sqlBuilderCS.toString(), new Object[] {});
                                                listCS.add(objCS);
                                            }
                                        }
                                        objCompareDetail.setCompareStaff(listCS);
                                    }

                                    // st CompareDetailPayment
                                    if(item1.getCompareDetailPayment() != null) {
                                        log.info("[Sub] Size : "+item1.getCompareDetailPayment().size());
                                        List<CompareDetailPaymentResponse> compareDetailPaymentList = new ArrayList<CompareDetailPaymentResponse>();

                                        if(item1.getCompareDetailPayment().size() > 0){
                                            for(CompareDetailPayment item2 : item1.getCompareDetailPayment()){

                                                String PAYMENT_ID = getSequences("SELECT OPS_COMPARE_DETAIL_PAYMENT_SEQ.NEXTVAL FROM DUAL");
                                                CompareDetailPaymentResponse  objCompareDetailPayment = new CompareDetailPaymentResponse();
                                                objCompareDetailPayment.setPAYMENT_ID(Integer.parseInt(PAYMENT_ID));

                                                StringBuilder sqlBuilderCompareDetailPayment = new StringBuilder()
                                                        .append("Insert into OPS_COMPARE_DETAIL_PAYMENT ( " +
                                                                "PAYMENT_ID," +
                                                                "COMPARE_DETAIL_ID," +
                                                                "PAYMENT_TYPE," +
                                                                "PAYMENT_FINE," +
                                                                "REFFERENCE_NO," +
                                                                "IS_ACTIVE " +
                                                                " ) values (" +
                                                                "'"+PAYMENT_ID+"'," +
                                                                "'"+COMPARE_DETAIL_ID+"'," +
                                                                "'"+item2.getPAYMENT_TYPE()+"'," +
                                                                "'"+item2.getPAYMENT_FINE()+"'," +
                                                                "'"+item2.getREFFERENCE_NO()+"'," +
                                                                "'"+item2.getIS_ACTIVE()+"'" +
                                                                " )");
                                                log.info("[SQL] : "+sqlBuilderCompareDetailPayment.toString());

                                                getJdbcTemplate().update(sqlBuilderCompareDetailPayment.toString(), new Object[] {});
                                                compareDetailPaymentList.add(objCompareDetailPayment);
                                            }
                                        }
                                        objCompareDetail.setCompareDetailPayment(compareDetailPaymentList);
                                    }
                                    //en CompareDetailPayment

                                    // st CompareDetailFine
                                    if(item1.getCompareDetailFine() != null) {
                                        log.info("[Sub] Size : "+item1.getCompareDetailFine().size());
                                        List<CompareDetailFineResponse> compareDetailFineList = new ArrayList<CompareDetailFineResponse>();

                                        if(item1.getCompareDetailPayment().size() > 0){
                                            for(CompareDetailFine item2 : item1.getCompareDetailFine()){

                                                String FINE_ID = getSequences("SELECT OPS_COMPARE_FINE_SEQ.NEXTVAL FROM DUAL");
                                                CompareDetailFineResponse  objCompareDetailFine = new CompareDetailFineResponse();
                                                objCompareDetailFine.setFINE_ID(Integer.parseInt(FINE_ID));

                                                StringBuilder sqlBuilderCompareDetailFine = new StringBuilder()
                                                        .append("Insert into OPS_COMPARE_DETAIL_FINE ( " +
                                                                "FINE_ID," +
                                                                "COMPARE_DETAIL_ID," +
                                                                "PRODUCT_ID," +
                                                                "FINE_RATE," +
                                                                "VAT," +
                                                                "FINE," +
                                                                "NET_FINE," +
                                                                "OLD_PAYMENT_FINE," +
                                                                "PAYMENT_FINE," +
                                                                "DIFFERENCE_PAYMENT_FINE," +
                                                                "TREASURY_MONEY," +
                                                                "BRIBE_MONEY," +
                                                                "REWARD_MONEY," +
                                                                "IS_ACTIVE " +
                                                                " ) values (" +
                                                                "'"+FINE_ID+"'," +
                                                                "'"+COMPARE_DETAIL_ID+"'," +
                                                                "'"+item2.getPRODUCT_ID()+"'," +
                                                                "'"+item2.getFINE_RATE()+"'," +
                                                                "'"+item2.getVAT()+"'," +
                                                                "'"+item2.getFINE()+"'," +
                                                                "'"+item2.getNET_FINE()+"'," +
                                                                "'"+item2.getOLD_PAYMENT_FINE()+"'," +
                                                                "'"+item2.getPAYMENT_FINE()+"'," +
                                                                "'"+item2.getDIFFERENCE_PAYMENT_FINE()+"'," +
                                                                "'"+item2.getTREASURY_MONEY()+"'," +
                                                                "'"+item2.getBRIBE_MONEY()+"'," +
                                                                "'"+item2.getREWARD_MONEY()+"'," +
                                                                "'"+item2.getIS_ACTIVE()+"'" +
                                                                " )");
                                                log.info("[SQL] : "+sqlBuilderCompareDetailFine.toString());

                                                getJdbcTemplate().update(sqlBuilderCompareDetailFine.toString(), new Object[] {});
                                                compareDetailFineList.add(objCompareDetailFine);
                                            }
                                        }
                                        objCompareDetail.setCompareDetailFine(compareDetailFineList);
                                    }
                                    //en CompareDetailFine

                                    // st ComparePayment
                                    if(item1.getComparePayment() != null) {
                                        log.info("[Sub] Size : "+item1.getComparePayment().size());
                                        List<ComparePaymentResponse> comparePaymentList = new ArrayList<ComparePaymentResponse>();

                                        if(item1.getComparePayment().size() > 0){
                                            for(ComparePayment item2 : item1.getComparePayment()){

                                                String PAYMENT_ID = getSequences("SELECT OPS_PAYMENT_SEQ.NEXTVAL FROM DUAL");
                                                ComparePaymentResponse  objComparePayment = new ComparePaymentResponse();
                                                objComparePayment.setPAYMENT_ID(Integer.parseInt(PAYMENT_ID));

                                                StringBuilder sqlBuilderComparePayment = new StringBuilder()
                                                        .append("Insert into OPS_PAYMENT ( " +
                                                                "PAYMENT_ID," +
                                                                "LAWSUIT_DETAIL_ID," +
                                                                "COMPARE_DETAIL_ID," +
                                                                "FINE_TYPE," +
                                                                "FINE," +
                                                                "PAYMENT_PERIOD_NO," +
                                                                "PAYMENT_DATE," +
                                                                "IS_REQUEST_REWARD," +
                                                                "IS_ACTIVE, " +
                                                                "PAYMENT_CHANNEL, " +
                                                                "PAYMENT_BANK," +
                                                                "PAYMENT_REF_NO, " +
                                                                "IS_REVENUE " +
                                                                " ) values (" +
                                                                "'"+PAYMENT_ID+"'," +
                                                                "'"+item2.getLAWSUIT_DETAIL_ID()+"'," +
                                                                "'"+COMPARE_DETAIL_ID+"'," +
                                                                "'"+item2.getFINE_TYPE()+"'," +
                                                                "'"+item2.getFINE()+"'," +
                                                                "'"+item2.getPAYMENT_PERIOD_NO()+"'," +
                                                                "TO_TIMESTAMP_TZ('"+item2.getPAYMENT_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                                                                "'"+item2.getIS_REQUEST_REWARD()+"'," +
                                                                "'"+item2.getIS_ACTIVE()+"'," +
                                                                "'"+item2.getPAYMENT_CHANNEL()+"'," +
                                                                "'"+item2.getPAYMENT_BANK()+"'," +
                                                                "'"+item2.getPAYMENT_REF_NO()+"'," +
                                                                "'"+item2.getIS_REVENUE()+"'" +
                                                                " )");
                                                log.info("[SQL] : "+sqlBuilderComparePayment.toString());

                                                getJdbcTemplate().update(sqlBuilderComparePayment.toString(), new Object[] {});

                                                // st paymentdetail
                                                if(item2.getComparePaymentDetail() != null) {
                                                    log.info("[Sub] Size : "+item2.getComparePaymentDetail().size());
                                                    List<ComparePaymentDetailResponse> comparePaymentDetailList = new ArrayList<ComparePaymentDetailResponse>();

                                                    if(item2.getComparePaymentDetail().size() > 0){
                                                        for(ComparePaymentDetail item3 : item2.getComparePaymentDetail()){

                                                            String PAYMENT_DETAIL_ID = getSequences("SELECT OPS_PAYMENT_DETAIL_SEQ.NEXTVAL FROM DUAL");
                                                            ComparePaymentDetailResponse  objComparePaymentDetail = new ComparePaymentDetailResponse();
                                                            objComparePaymentDetail.setPAYMENT_DETAIL_ID(Integer.parseInt(PAYMENT_DETAIL_ID));

                                                            StringBuilder sqlBuilderComparePaymentDetail = new StringBuilder()
                                                                    .append("Insert into OPS_PAYMENT_DETAIL ( " +
                                                                            "PAYMENT_DETAIL_ID," +
                                                                            "PAYMENT_ID," +
                                                                            "NOTICE_ID," +
                                                                            "IS_REQUEST_BRIBE," +
                                                                            "IS_ACTIVE " +
                                                                            " ) values (" +
                                                                            "'"+PAYMENT_DETAIL_ID+"'," +
                                                                            "'"+PAYMENT_ID+"'," +
                                                                            "'"+item3.getNOTICE_ID()+"'," +
                                                                            "'"+item3.getIS_REQUEST_BRIBE()+"'," +
                                                                            "'"+item3.getIS_ACTIVE()+"'" +
                                                                            " )");
                                                            log.info("[SQL] : "+sqlBuilderComparePaymentDetail.toString());

                                                            getJdbcTemplate().update(sqlBuilderComparePaymentDetail.toString(), new Object[] {});
                                                            comparePaymentDetailList.add(objComparePaymentDetail);
                                                        }
                                                    }
                                                    objComparePayment.setComparePaymentDetail(comparePaymentDetailList);
                                                }

                                                //en paymentdetail


                                                comparePaymentList.add(objComparePayment);
                                            }
                                        }
                                        objCompareDetail.setComparePayment(comparePaymentList);
                                    }

                                    //en ComparePayment

                                    compareDetailList.add(objCompareDetail);
                                }
                            }
                            obj.setCompareDetail(compareDetailList);
                        }

                        list.add(obj);
                    }
                }
                res.setCompareMapping(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setCOMPARE_ID(0);
            res.setCompareMapping(null);

            return res;
        }
    }

    @Override
    public Boolean CompareupdByCon(Compare req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_COMPARE SET "
                        + "LAWSUIT_ID=  '"+req.getLAWSUIT_ID()+"', "
                        + "OFFICE_ID=  '"+req.getOFFICE_ID()+"', "
                        + "TREASURY_RATE=  '"+req.getTREASURY_RATE()+"', "
                        + "BRIBE_RATE=  '"+req.getBRIBE_RATE()+"', "
                        + "REWARD_RATE=  '"+req.getREWARD_RATE()+"', "
                        + "OFFICE_CODE=  '"+req.getOFFICE_CODE()+"', "
                        + "OFFICE_NAME=  '"+req.getOFFICE_NAME()+"', "
                        + "COMPARE_NO=  '"+req.getCOMPARE_NO()+"', "
                        + "COMPARE_NO_YEAR = TO_TIMESTAMP_TZ('"+req.getCOMPARE_NO_YEAR()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
                        + "COMPARE_DATE = TO_TIMESTAMP_TZ('"+req.getCOMPARE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
                        + "IS_OUTSIDE=  '"+req.getIS_OUTSIDE()+"', "
                        + "IS_ACTIVE=  '"+req.getIS_ACTIVE()+"', "
                        + "CREATE_DATE = TO_TIMESTAMP_TZ('"+req.getCREATE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
                        + "CREATE_USER_ACCOUNT_ID=  '"+req.getCREATE_USER_ACCOUNT_ID()+"', "
                        + "UPDATE_DATE = TO_TIMESTAMP_TZ('"+req.getUPDATE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
                        + "UPDATE_USER_ACCOUNT_ID=  '"+req.getUPDATE_USER_ACCOUNT_ID()+"' "
                        + " WHERE COMPARE_ID = '"+req.getCOMPARE_ID()+"' ");

        log.info("[SQL] : "+sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

        if(req.getCompareMapping() != null) {
            log.info("[Sub] Size : "+req.getCompareMapping().size());

            if(req.getCompareMapping().size() > 0){
                for(CompareMapping item : req.getCompareMapping()){

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_COMPARE_MAPPING SET "
                                    + "COMPARE_ID=  '"+req.getCOMPARE_ID()+"', "
                                    + "INDICTMENT_DETAIL_ID=  '"+item.getINDICTMENT_DETAIL_ID()+"', "
                                    + "PAST_LAWSUIT_ID=  '"+item.getPAST_LAWSUIT_ID()+"', "
                                    + "IS_EVER_WRONG=  '"+item.getIS_EVER_WRONG()+"', "
                                    + "IS_ACTIVE=  '"+item.getIS_ACTIVE()+"' "
                                    + " WHERE COMPARE_MAPPING_ID = '"+item.getCOMPARE_MAPPING_ID()+"' ");
                    log.info("[SQL] : "+sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
                }
            }
        }

        return true;
    }

    @Override
    public Boolean CompareupdDelete(CompareupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_COMPARE SET IS_ACTIVE = '0' WHERE COMPARE_ID = '"+req.getCOMPARE_ID()+"' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_COMPARE_MAPPING SET IS_ACTIVE = '0' WHERE COMPARE_ID = '"+req.getCOMPARE_ID()+"' ");
        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL SET IS_ACTIVE = '0' WHERE COMPARE_MAPPING_ID in(select COMPARE_MAPPING_ID from OPS_COMPARE_MAPPING where COMPARE_ID = '"+req.getCOMPARE_ID()+"')");
        StringBuilder sqlBuilder4 = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL_PAYMENT SET IS_ACTIVE = '0' WHERE COMPARE_DETAIL_ID in(select COMPARE_DETAIL_ID from OPS_COMPARE_DETAIL where COMPARE_MAPPING_ID in(select COMPARE_MAPPING_ID from OPS_COMPARE_MAPPING where COMPARE_ID = '"+req.getCOMPARE_ID()+"')) ");
        StringBuilder sqlBuilder5 = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL_FINE SET IS_ACTIVE = '0' WHERE COMPARE_DETAIL_ID in(select COMPARE_DETAIL_ID from OPS_COMPARE_DETAIL where COMPARE_MAPPING_ID in(select COMPARE_MAPPING_ID from OPS_COMPARE_MAPPING where COMPARE_ID = '"+req.getCOMPARE_ID()+"')) ");
        StringBuilder sqlBuilder6 = new StringBuilder().append("UPDATE OPS_PAYMENT SET IS_ACTIVE = '0' WHERE COMPARE_DETAIL_ID in(select COMPARE_DETAIL_ID from OPS_COMPARE_DETAIL where COMPARE_MAPPING_ID in(select COMPARE_MAPPING_ID from OPS_COMPARE_MAPPING where COMPARE_ID = '"+req.getCOMPARE_ID()+"')) ");
        StringBuilder sqlBuilder7 = new StringBuilder().append("UPDATE OPS_PAYMENT_DETAIL SET IS_ACTIVE = '0' WHERE PAYMENT_ID in(select PAYMENT_ID from OPS_PAYMENT where COMPARE_DETAIL_ID in(select COMPARE_DETAIL_ID from OPS_COMPARE_DETAIL where COMPARE_MAPPING_ID in(select COMPARE_MAPPING_ID from OPS_COMPARE_MAPPING where COMPARE_ID = '"+req.getCOMPARE_ID()+"'))) ");
        StringBuilder sqlBuilder8 = new StringBuilder().append("UPDATE OPS_COMPARE_STAFF SET IS_ACTIVE = '0' WHERE COMPARE_ID = '"+req.getCOMPARE_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder4.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder5.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder6.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder7.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder8.toString(), new Object[] {});
        return true;
    }

    @Override
    public List<Compare> CompareVerifyCompareNo(CompareVerifyCompareNoReq req){

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "COMPARE_ID," +
                        "LAWSUIT_ID," +
                        "OFFICE_ID," +
                        "TREASURY_RATE," +
                        "BRIBE_RATE," +
                        "REWARD_RATE," +
                        "OFFICE_CODE," +
                        "OFFICE_NAME," +
                        "COMPARE_NO," +
                        "to_char(COMPARE_NO_YEAR,'"+Pattern.FORMAT_DATETIME+"') as COMPARE_NO_YEAR," +
                        "to_char(COMPARE_DATE,'"+Pattern.FORMAT_DATETIME+"') as COMPARE_DATE," +
                        "IS_OUTSIDE," +
                        "IS_ACTIVE," +
                        "to_char(CREATE_DATE,'"+Pattern.FORMAT_DATETIME+"') as CREATE_DATE," +
                        "CREATE_USER_ACCOUNT_ID," +
                        "to_char(UPDATE_DATE,'"+Pattern.FORMAT_DATETIME+"') as UPDATE_DATE," +
                        "UPDATE_USER_ACCOUNT_ID " +
                        "from OPS_COMPARE  where IS_ACTIVE = 1 ");




        if(req.getCOMPARE_NO() != null && req.getCOMPARE_NO() != 0) {
            sqlBuilder.append("and COMPARE_NO = '"+req.getCOMPARE_NO()+"'");
        }

        if(req.getCOMPARE_NO_YEAR() != null && !"".equals(req.getCOMPARE_NO_YEAR())) {
            sqlBuilder.append("AND EXTRACT(YEAR FROM COMPARE_NO_YEAR) = '"+req.getCOMPARE_NO_YEAR()+"' ");
        }

        if(req.getIS_OUTSIDE() != null && req.getIS_OUTSIDE() != 0) {
            sqlBuilder.append("and IS_OUTSIDE = '"+req.getIS_OUTSIDE()+"'");
        }

        if(req.getOFFICE_CODE() != null && !"".equals(req.getOFFICE_CODE())) {
            sqlBuilder.append("AND OFFICE_CODE = '"+req.getOFFICE_CODE()+"' ");
        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<Compare> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public Compare mapRow(ResultSet rs, int rowNum) throws SQLException {
                Compare item = new Compare();
                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                item.setTREASURY_RATE(rs.getFloat("TREASURY_RATE"));
                item.setBRIBE_RATE(rs.getFloat("BRIBE_RATE"));
                item.setREWARD_RATE(rs.getFloat("REWARD_RATE"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setCOMPARE_NO(rs.getInt("COMPARE_NO"));
                item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                item.setCREATE_USER_ACCOUNT_ID(rs.getInt("CREATE_USER_ACCOUNT_ID"));
                item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                item.setUPDATE_USER_ACCOUNT_ID(rs.getInt("UPDATE_USER_ACCOUNT_ID"));


                item.setCompareMapping(getCompareMapping(rs.getInt("COMPARE_ID")));


                return item;
            }
        });

        return dataList;

    }
}
