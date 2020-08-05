package com.xcs.phase2.dao.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.lawsult.*;
import com.xcs.phase2.request.lawsult.LawsuitVerifyLawsuitNoReq;
import com.xcs.phase2.request.lawsult.LawsuitgetByConReq;
import com.xcs.phase2.request.lawsult.LawsuitupdDeleteReq;
import com.xcs.phase2.response.lawsult.*;
import com.xcs.phase2.units.StringUtil;
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
public class LawsuitDAOImpl extends LawsultExt implements LawsuitDAO {

    private static final Logger log = LoggerFactory.getLogger(LawsuitDAOImpl.class);

    @Override
    public LawsuitinsAllResponse LawsuitinsAll(Lawsuit req) {

        LawsuitinsAllResponse res = new LawsuitinsAllResponse();

        try {

            String LAWSUIT_ID = getSequences("SELECT \"OPS_LAWSUIT_SEQ\".NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_LAWSUIT ( " +
                            "LAWSUIT_ID," +
                            "INDICTMENT_ID," +
                            "OFFICE_ID," +
                            "OFFICE_CODE," +
                            "OFFICE_NAME," +
                            "IS_LAWSUIT," +
                            "REMARK_NOT_LAWSUIT," +
                            "LAWSUIT_NO," +
                            "LAWSUIT_NO_YEAR," +
                            "LAWSUIT_DATE," +
                            "TESTIMONY," +
                            "DELIVERY_DOC_NO_1," +
                            "DELIVERY_DOC_NO_2," +
                            "DELIVERY_DOC_DATE," +
                            "IS_OUTSIDE," +
                            "IS_SEIZE," +
                            "IS_ACTIVE " +
                            " ) values (" +
                            "'" + LAWSUIT_ID + "'," +
                            "'" + req.getINDICTMENT_ID() + "'," +
                            "'" + req.getOFFICE_ID() + "'," +
                            "'" + req.getOFFICE_CODE() + "'," +
                            "'" + req.getOFFICE_NAME() + "'," +
                            "'" + req.getIS_LAWSUIT() + "'," +
                            "'" + req.getREMARK_NOT_LAWSUIT() + "'," +
                            "'" + req.getLAWSUIT_NO() + "'," +
                            "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getLAWSUIT_NO_YEAR()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getLAWSUIT_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getTESTIMONY() + "'," +
                            "'" + req.getDELIVERY_DOC_NO_1() + "'," +
                            "'" + req.getDELIVERY_DOC_NO_2() + "'," +
                            "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getDELIVERY_DOC_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getIS_OUTSIDE() + "'," +
                            "'" + req.getIS_SEIZE() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setLAWSUIT_ID(Integer.parseInt(LAWSUIT_ID));


            if (req.getLawsuitStaff() != null) {
                log.info("[Sub] Size : " + req.getLawsuitStaff().size());
                List<LawsuitStaffResponse> list = new ArrayList<LawsuitStaffResponse>();

                if (req.getLawsuitStaff().size() > 0) {
                    for (LawsuitStaff item : req.getLawsuitStaff()) {

                        String STAFF_ID = getSequences("SELECT \"OPS_LAWSUIT_STAFF_SEQ\".NEXTVAL FROM DUAL");
                        LawsuitStaffResponse obj = new LawsuitStaffResponse();
                        obj.setSTAFF_ID(Integer.parseInt(STAFF_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_LAWSUIT_STAFF ( " +
                                        "STAFF_ID," +
                                        "LAWSUIT_ID," +
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
                                        "'" + STAFF_ID + "'," +
                                        "'" + LAWSUIT_ID + "'," +
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
                                        "'" + item.getIS_ACTIVE() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setLawsuitStaff(list);
            }

            if (req.getLawsuitDetail() != null) {
                log.info("[Sub] Size : " + req.getLawsuitDetail().size());
                List<LawsuitDetailResponse> list = new ArrayList<LawsuitDetailResponse>();

                if (req.getLawsuitDetail().size() > 0) {
                    for (LawsuitDetail item : req.getLawsuitDetail()) {

                        String LAWSUIT_DETAIL_ID = getSequences("SELECT \"OPS_LAWSUIT_DETAIL_SEQ\".NEXTVAL FROM DUAL");
                        LawsuitDetailResponse obj = new LawsuitDetailResponse();
                        obj.setLAWSUIT_DETAIL_ID(Integer.parseInt(LAWSUIT_DETAIL_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_LAWSUIT_DETAIL ( " +
                                        "LAWSUIT_DETAIL_ID," +
                                        "LAWSUIT_ID," +
                                        "INDICTMENT_DETAIL_ID," +
                                        "COURT_ID," +
                                        "LAWSUIT_TYPE," +
                                        "LAWSUIT_END," +
                                        "COURT_NAME," +
                                        "UNDECIDE_NO_1," +
                                        "UNDECIDE_NO_YEAR_1," +
                                        "DECIDE_NO_1," +
                                        "DECIDE_NO_YEAR_1," +
                                        "UNDECIDE_NO_2," +
                                        "UNDECIDE_NO_YEAR_2," +
                                        "DECIDE_NO_2," +
                                        "DECIDE_NO_YEAR_2," +
                                        "JUDGEMENT_NO," +
                                        "JUDGEMENT_NO_YEAR," +
                                        "JUDGEMENT_DATE," +
                                        "IS_IMPRISON," +
                                        "IMPRISON_TIME," +
                                        "IMPRISON_TIME_UNIT," +
                                        "IS_FINE," +
                                        "FINE," +
                                        "IS_PAYONCE," +
                                        "FINE_DATE," +
                                        "PAYMENT_PERIOD," +
                                        "PAYMENT_PERIOD_DUE," +
                                        "PAYMENT_PERIOD_DUE_UNIT," +
                                        "PAYMENT_CHANNEL," +
                                        "PAYMENT_BANK," +
                                        "PAYMENT_REF_NO," +
                                        "PAYMENT_DATE," +
                                        "IS_DISMISS," +
                                        "UNJUDGEMENT_NO," +
                                        "UNJUDGEMENT_NO_YEAR," +
                                        "IS_ACTIVE " +
                                        " ) values (" +
                                        "'" + LAWSUIT_DETAIL_ID + "'," +
                                        "'" + LAWSUIT_ID + "'," +
                                        "'" + item.getINDICTMENT_DETAIL_ID() + "'," +
                                        "'" + item.getCOURT_ID() + "'," +
                                        "'" + item.getLAWSUIT_TYPE() + "'," +
                                        "'" + item.getLAWSUIT_END() + "'," +
                                        "'" + item.getCOURT_NAME() + "'," +
                                        "'" + item.getUNDECIDE_NO_1() + "'," +
                                        "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getUNDECIDE_NO_YEAR_1()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                        "'" + item.getDECIDE_NO_1() + "'," +
                                        "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getDECIDE_NO_YEAR_1()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                        "'" + item.getUNDECIDE_NO_2() + "'," +
                                        "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getUNDECIDE_NO_YEAR_2()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                        "'" + item.getDECIDE_NO_2() + "'," +
                                        "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getDECIDE_NO_YEAR_2()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                        "'" + item.getJUDGEMENT_NO() + "'," +
                                        "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getJUDGEMENT_NO_YEAR()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                        "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getJUDGEMENT_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                        "'" + item.getIS_IMPRISON() + "'," +
                                        "'" + item.getIMPRISON_TIME() + "'," +
                                        "'" + item.getIMPRISON_TIME_UNIT() + "'," +
                                        "'" + item.getIS_FINE() + "'," +
                                        "'" + item.getFINE() + "'," +
                                        "'" + item.getIS_PAYONCE() + "'," +
                                        "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getFINE_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                        "'" + item.getPAYMENT_PERIOD() + "'," +
                                        "'" + item.getPAYMENT_PERIOD_DUE() + "'," +
                                        "'" + item.getPAYMENT_PERIOD_DUE_UNIT() + "'," +
                                        "'" + item.getPAYMENT_CHANNEL() + "'," +
                                        "'" + item.getPAYMENT_BANK() + "'," +
                                        "'" + item.getPAYMENT_REF_NO() + "'," +
                                        "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getPAYMENT_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                        "'" + item.getIS_DISMISS() + "'," +
                                        "'" + item.getUNJUDGEMENT_NO() + "'," +
                                        "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getUNJUDGEMENT_NO_YEAR()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                        "'" + item.getIS_ACTIVE() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                        // st payment
                        if (item.getLawsuitPayment() != null) {
                            log.info("[Sub] Size : " + item.getLawsuitPayment().size());
                            List<LawsuitPaymentResponse> paymentList = new ArrayList<LawsuitPaymentResponse>();

                            if (item.getLawsuitPayment().size() > 0) {
                                for (LawsuitPayment item1 : item.getLawsuitPayment()) {

                                    String PAYMENT_ID = getSequences("SELECT \"OPS_PAYMENT_SEQ\".NEXTVAL FROM DUAL");
                                    LawsuitPaymentResponse objPayment = new LawsuitPaymentResponse();
                                    objPayment.setPAYMENT_ID(Integer.parseInt(PAYMENT_ID));

                                    StringBuilder sqlBuilderPayment = new StringBuilder()
                                            .append("Insert into OPS_PAYMENT ( " +
                                                    "PAYMENT_ID," +
                                                    "LAWSUIT_DETAIL_ID," +
                                                    "COMPARE_DETAIL_ID," +
                                                    "FINE_TYPE," +
                                                    "FINE," +
                                                    "PAYMENT_PERIOD_NO," +
                                                    "PAYMENT_DATE," +
                                                    "IS_REQUEST_REWARD," +
                                                    "PAYMENT_CHANNEL," +
                                                    "PAYMENT_BANK," +
                                                    "PAYMENT_REF_NO," +
                                                    "IS_REVENUE," +
                                                    "IS_ACTIVE " +
                                                    " ) values (" +
                                                    "'" + PAYMENT_ID + "'," +
                                                    "'" + item1.getLAWSUIT_DETAIL_ID() + "'," +
                                                    "'" + item1.getCOMPARE_DETAIL_ID() + "'," +
                                                    "'" + item1.getFINE_TYPE() + "'," +
                                                    "'" + item1.getFINE() + "'," +
                                                    "'" + item1.getPAYMENT_PERIOD_NO() + "'," +
                                                    "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item1.getPAYMENT_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                                    "'" + item1.getIS_REQUEST_REWARD() + "'," +
                                                    "'" + item1.getPAYMENT_CHANNEL() + "'," +
                                                    "'" + item1.getPAYMENT_BANK() + "'," +
                                                    "'" + item1.getPAYMENT_REF_NO() + "'," +
                                                    "'" + item1.getIS_REVENUE() + "'," +
                                                    "'" + item1.getIS_ACTIVE() + "'" +
                                                    " )");
                                    log.info("[SQL] : " + sqlBuilderPayment.toString());

                                    getJdbcTemplate().update(sqlBuilderPayment.toString(), new Object[]{});

                                    // st paymentdetail
                                    if (item1.getLawsuitPaymentDetail() != null) {
                                        log.info("[Sub] Size : " + item1.getLawsuitPaymentDetail().size());
                                        List<LawsuitPaymentDetailResponse> paymentDetailList = new ArrayList<LawsuitPaymentDetailResponse>();

                                        if (item1.getLawsuitPaymentDetail().size() > 0) {
                                            for (LawsuitPaymentDetail item2 : item1.getLawsuitPaymentDetail()) {

                                                String PAYMENT_DETAIL_ID = getSequences("SELECT \"OPS_PAYMENT_DETAIL_SEQ\".NEXTVAL FROM DUAL");
                                                LawsuitPaymentDetailResponse objPaymentDetail = new LawsuitPaymentDetailResponse();
                                                objPaymentDetail.setPAYMENT_DETAIL_ID(Integer.parseInt(PAYMENT_DETAIL_ID));

                                                StringBuilder sqlBuilderPaymentDetail = new StringBuilder()
                                                        .append("Insert into OPS_PAYMENT_DETAIL ( " +
                                                                "PAYMENT_DETAIL_ID," +
                                                                "PAYMENT_ID," +
                                                                "NOTICE_ID," +
                                                                "IS_REQUEST_BRIBE," +
                                                                "IS_ACTIVE " +
                                                                " ) values (" +
                                                                "'" + PAYMENT_DETAIL_ID + "'," +
                                                                "'" + PAYMENT_ID + "'," +
                                                                "'" + item2.getNOTICE_ID() + "'," +
                                                                "'" + item2.getIS_REQUEST_BRIBE() + "'," +
                                                                "'" + item2.getIS_ACTIVE() + "'" +
                                                                " )");
                                                log.info("[SQL] : " + sqlBuilderPaymentDetail.toString());

                                                getJdbcTemplate().update(sqlBuilderPaymentDetail.toString(), new Object[]{});
                                                paymentDetailList.add(objPaymentDetail);
                                            }
                                        }
                                        objPayment.setLawsuitPaymentDetail(paymentDetailList);
                                    }

                                    //en paymentdetail


                                    paymentList.add(objPayment);
                                }
                            }
                            obj.setLawsuitPayment(paymentList);
                        }

                        //en payment

                        list.add(obj);
                    }
                }
                res.setLawsuitDetail(list);
            }


            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setLAWSUIT_ID(0);
            res.setLawsuitDetail(null);
            res.setLawsuitStaff(null);
            return res;
        }
    }

    @Override
    public Boolean LawsuitupdAll(Lawsuit req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_LAWSUIT SET "
                        + "INDICTMENT_ID='" + req.getINDICTMENT_ID() + "', "
                        + "OFFICE_ID='" + req.getOFFICE_ID() + "', "
                        + "OFFICE_CODE='" + req.getOFFICE_CODE() + "', "
                        + "OFFICE_NAME='" + req.getOFFICE_NAME() + "', "
                        + "IS_LAWSUIT='" + req.getIS_LAWSUIT() + "', "
                        + "REMARK_NOT_LAWSUIT='" + req.getREMARK_NOT_LAWSUIT() + "', "
                        + "LAWSUIT_NO='" + req.getLAWSUIT_NO() + "', "
                        + "LAWSUIT_NO_YEAR = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getLAWSUIT_NO_YEAR()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "LAWSUIT_DATE = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getLAWSUIT_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "TESTIMONY='" + req.getTESTIMONY() + "', "
                        + "DELIVERY_DOC_NO_1='" + req.getDELIVERY_DOC_NO_1() + "', "
                        + "DELIVERY_DOC_NO_2='" + req.getDELIVERY_DOC_NO_2() + "', "
                        + "DELIVERY_DOC_DATE = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getDELIVERY_DOC_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "IS_OUTSIDE='" + req.getIS_OUTSIDE() + "', "
                        + "IS_SEIZE='" + req.getIS_SEIZE() + "', "
                        + "IS_ACTIVE='" + req.getIS_ACTIVE() + "' "
                        + " WHERE LAWSUIT_ID = '" + req.getLAWSUIT_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        if (req.getLawsuitStaff() != null) {
            log.info("[Sub] Size : " + req.getLawsuitStaff().size());

            if (req.getLawsuitStaff().size() > 0) {
                for (LawsuitStaff item : req.getLawsuitStaff()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_LAWSUIT_STAFF "
                                    + "SET "
                                    + "LAWSUIT_ID = '" + item.getLAWSUIT_ID() + "',"
                                    + "STAFF_REF_ID = '" + item.getSTAFF_REF_ID() + "',"
                                    + "TITLE_ID = '" + item.getTITLE_ID() + "',"
                                    + "STAFF_CODE = '" + item.getSTAFF_CODE() + "',"
                                    + "ID_CARD = '" + item.getID_CARD() + "',"
                                    + "STAFF_TYPE = '" + item.getSTAFF_TYPE() + "',"
                                    + "TITLE_NAME_TH = '" + item.getTITLE_NAME_TH() + "',"
                                    + "TITLE_NAME_EN = '" + item.getTITLE_NAME_EN() + "',"
                                    + "TITLE_SHORT_NAME_TH = '" + item.getTITLE_SHORT_NAME_TH() + "',"
                                    + "TITLE_SHORT_NAME_EN = '" + item.getTITLE_SHORT_NAME_EN() + "',"
                                    + "FIRST_NAME = '" + item.getFIRST_NAME() + "',"
                                    + "LAST_NAME = '" + item.getLAST_NAME() + "',"
                                    + "AGE = '" + item.getAGE() + "',"
                                    + "OPERATION_POS_CODE = '" + item.getOPERATION_POS_CODE() + "',"
                                    + "OPREATION_POS_NAME = '" + item.getOPREATION_POS_NAME() + "',"
                                    + "OPREATION_POS_LEVEL = '" + item.getOPREATION_POS_LEVEL() + "',"
                                    + "OPERATION_POS_LEVEL_NAME = '" + item.getOPERATION_POS_LEVEL_NAME() + "',"
                                    + "OPERATION_DEPT_CODE = '" + item.getOPERATION_DEPT_CODE() + "',"
                                    + "OPERATION_DEPT_NAME = '" + item.getOPERATION_DEPT_NAME() + "',"
                                    + "OPERATION_DEPT_LEVEL = '" + item.getOPERATION_DEPT_LEVEL() + "',"
                                    + "OPERATION_UNDER_DEPT_CODE = '" + item.getOPERATION_UNDER_DEPT_CODE() + "',"
                                    + "OPERATION_UNDER_DEPT_NAME = '" + item.getOPERATION_UNDER_DEPT_NAME() + "',"
                                    + "OPERATION_UNDER_DEPT_LEVEL = '" + item.getOPERATION_UNDER_DEPT_LEVEL() + "',"
                                    + "OPERATION_WORK_DEPT_CODE = '" + item.getOPERATION_WORK_DEPT_CODE() + "',"
                                    + "OPERATION_WORK_DEPT_NAME = '" + item.getOPERATION_WORK_DEPT_NAME() + "',"
                                    + "OPERATION_WORK_DEPT_LEVEL = '" + item.getOPERATION_WORK_DEPT_LEVEL() + "',"
                                    + "OPERATION_OFFICE_CODE = '" + item.getOPERATION_OFFICE_CODE() + "',"
                                    + "OPERATION_OFFICE_NAME = '" + item.getOPERATION_OFFICE_NAME() + "',"
                                    + "OPERATION_OFFICE_SHORT_NAME = '" + item.getOPERATION_OFFICE_SHORT_NAME() + "',"
                                    + "MANAGEMENT_POS_CODE = '" + item.getMANAGEMENT_POS_CODE() + "',"
                                    + "MANAGEMENT_POS_NAME = '" + item.getMANAGEMENT_POS_NAME() + "',"
                                    + "MANAGEMENT_POS_LEVEL = '" + item.getMANAGEMENT_POS_LEVEL() + "',"
                                    + "MANAGEMENT_POS_LEVEL_NAME = '" + item.getMANAGEMENT_POS_LEVEL_NAME() + "',"
                                    + "MANAGEMENT_DEPT_CODE = '" + item.getMANAGEMENT_DEPT_CODE() + "',"
                                    + "MANAGEMENT_DEPT_NAME = '" + item.getMANAGEMENT_DEPT_NAME() + "',"
                                    + "MANAGEMENT_DEPT_LEVEL = '" + item.getMANAGEMENT_DEPT_LEVEL() + "',"
                                    + "MANAGEMENT_UNDER_DEPT_CODE = '" + item.getMANAGEMENT_UNDER_DEPT_CODE() + "',"
                                    + "MANAGEMENT_UNDER_DEPT_NAME = '" + item.getMANAGEMENT_UNDER_DEPT_NAME() + "',"
                                    + "MANAGEMENT_UNDER_DEPT_LEVEL = '" + item.getMANAGEMENT_UNDER_DEPT_LEVEL() + "',"
                                    + "MANAGEMENT_WORK_DEPT_CODE = '" + item.getMANAGEMENT_WORK_DEPT_CODE() + "',"
                                    + "MANAGEMENT_WORK_DEPT_NAME = '" + item.getMANAGEMENT_WORK_DEPT_NAME() + "',"
                                    + "MANAGEMENT_WORK_DEPT_LEVEL = '" + item.getMANAGEMENT_WORK_DEPT_LEVEL() + "',"
                                    + "MANAGEMENT_OFFICE_CODE = '" + item.getMANAGEMENT_OFFICE_CODE() + "',"
                                    + "MANAGEMENT_OFFICE_NAME = '" + item.getMANAGEMENT_OFFICE_NAME() + "',"
                                    + "MANAGEMENT_OFFICE_SHORT_NAME = '" + item.getMANAGEMENT_OFFICE_SHORT_NAME() + "',"
                                    + "REPRESENT_POS_CODE = '" + item.getREPRESENT_POS_CODE() + "',"
                                    + "REPRESENT_POS_NAME = '" + item.getREPRESENT_POS_NAME() + "',"
                                    + "REPRESENT_POS_LEVEL = '" + item.getREPRESENT_POS_LEVEL() + "',"
                                    + "REPRESENT_POS_LEVEL_NAME = '" + item.getREPRESENT_POS_LEVEL_NAME() + "',"
                                    + "REPRESENT_DEPT_CODE = '" + item.getREPRESENT_DEPT_CODE() + "',"
                                    + "REPRESENT_DEPT_NAME = '" + item.getREPRESENT_DEPT_NAME() + "',"
                                    + "REPRESENT_DEPT_LEVEL = '" + item.getREPRESENT_DEPT_LEVEL() + "',"
                                    + "REPRESENT_UNDER_DEPT_CODE = '" + item.getREPRESENT_UNDER_DEPT_CODE() + "',"
                                    + "REPRESENT_UNDER_DEPT_NAME = '" + item.getREPRESENT_UNDER_DEPT_NAME() + "',"
                                    + "REPRESENT_UNDER_DEPT_LEVEL = '" + item.getREPRESENT_UNDER_DEPT_LEVEL() + "',"
                                    + "REPRESENT_WORK_DEPT_CODE = '" + item.getREPRESENT_WORK_DEPT_CODE() + "',"
                                    + "REPRESENT_WORK_DEPT_NAME = '" + item.getREPRESENT_WORK_DEPT_NAME() + "',"
                                    + "REPRESENT_WORK_DEPT_LEVEL = '" + item.getREPRESENT_WORK_DEPT_LEVEL() + "',"
                                    + "REPRESENT_OFFICE_CODE = '" + item.getREPRESENT_OFFICE_CODE() + "',"
                                    + "REPRESENT_OFFICE_NAME = '" + item.getREPRESENT_OFFICE_NAME() + "',"
                                    + "REPRESENT_OFFICE_SHORT_NAME = '" + item.getREPRESENT_OFFICE_SHORT_NAME() + "',"
                                    + "STATUS = '" + item.getSTATUS() + "',"
                                    + "REMARK = '" + item.getREMARK() + "',"
                                    + "CONTRIBUTOR_ID = '" + item.getCONTRIBUTOR_ID() + "',"
                                    + "IS_ACTIVE = '" + item.getIS_ACTIVE() + "' "
                                    + "WHERE STAFF_ID = '" + item.getSTAFF_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());


                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});


                }
            }
        }

        if (req.getLawsuitDetail() != null) {
            log.info("[Sub] Size : " + req.getLawsuitDetail().size());

            if (req.getLawsuitDetail().size() > 0) {
                for (LawsuitDetail item : req.getLawsuitDetail()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_LAWSUIT_DETAIL SET "
                                    + "LAWSUIT_ID=  '" + item.getLAWSUIT_ID() + "', "
                                    + "INDICTMENT_DETAIL_ID=  '" + item.getINDICTMENT_DETAIL_ID() + "', "
                                    + "COURT_ID=  '" + item.getCOURT_ID() + "', "
                                    + "LAWSUIT_TYPE=  '" + item.getLAWSUIT_TYPE() + "', "
                                    + "LAWSUIT_END=  '" + item.getLAWSUIT_END() + "', "
                                    + "COURT_NAME=  '" + item.getCOURT_NAME() + "', "
                                    + "UNDECIDE_NO_1=  '" + item.getUNDECIDE_NO_1() + "', "
                                    + "UNDECIDE_NO_YEAR_1 = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getUNDECIDE_NO_YEAR_1()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                                    + "DECIDE_NO_1=  '" + item.getDECIDE_NO_1() + "', "
                                    + "DECIDE_NO_YEAR_1 = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getDECIDE_NO_YEAR_1()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                                    + "UNDECIDE_NO_2=  '" + item.getUNDECIDE_NO_2() + "', "
                                    + "UNDECIDE_NO_YEAR_2 = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getUNDECIDE_NO_YEAR_2()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                                    + "DECIDE_NO_2=  '" + item.getDECIDE_NO_2() + "', "
                                    + "DECIDE_NO_YEAR_2 = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getDECIDE_NO_YEAR_2()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                                    + "JUDGEMENT_NO=  '" + item.getJUDGEMENT_NO() + "', "
                                    + "JUDGEMENT_NO_YEAR = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getJUDGEMENT_NO_YEAR()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                                    + "JUDGEMENT_DATE = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getJUDGEMENT_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                                    + "IS_IMPRISON=  '" + item.getIS_IMPRISON() + "', "
                                    + "IMPRISON_TIME=  '" + item.getIMPRISON_TIME() + "', "
                                    + "IMPRISON_TIME_UNIT=  '" + item.getIMPRISON_TIME_UNIT() + "', "
                                    + "IS_FINE=  '" + item.getIS_FINE() + "', "
                                    + "FINE=  '" + item.getFINE() + "', "
                                    + "IS_PAYONCE=  '" + item.getIS_PAYONCE() + "', "
                                    + "FINE_DATE = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getFINE_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                                    + "PAYMENT_PERIOD=  '" + item.getPAYMENT_PERIOD() + "', "
                                    + "PAYMENT_PERIOD_DUE=  '" + item.getPAYMENT_PERIOD_DUE() + "', "
                                    + "PAYMENT_PERIOD_DUE_UNIT=  '" + item.getPAYMENT_PERIOD_DUE_UNIT() + "', "
                                    + "PAYMENT_CHANNEL=  '" + item.getPAYMENT_CHANNEL() + "', "
                                    + "PAYMENT_BANK=  '" + item.getPAYMENT_BANK() + "', "
                                    + "PAYMENT_REF_NO=  '" + item.getPAYMENT_REF_NO() + "', "
                                    + "PAYMENT_DATE = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getPAYMENT_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                                    + "UNJUDGEMENT_NO=  '" + item.getUNJUDGEMENT_NO() + "', "
                                    + "UNJUDGEMENT_NO_YEAR = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(item.getUNJUDGEMENT_NO_YEAR()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                                    + "IS_DISMISS=  '" + item.getIS_DISMISS() + "' "
                                    + " WHERE LAWSUIT_DETAIL_ID = '" + item.getLAWSUIT_DETAIL_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());


                    if (item.getLawsuitPayment() != null) {
                        log.info("[Sub] Size : " + item.getLawsuitPayment().size());

                        if (item.getLawsuitPayment().size() > 0) {
                            for (LawsuitPayment itemPayment : item.getLawsuitPayment()) {

                                StringBuilder sqlBuilderPayment = new StringBuilder()
                                        .append("UPDATE OPS_PAYMENT SET "
                                                + "LAWSUIT_DETAIL_ID=  '" + itemPayment.getLAWSUIT_DETAIL_ID() + "', "
                                                + "COMPARE_DETAIL_ID=  '" + itemPayment.getCOMPARE_DETAIL_ID() + "', "
                                                + "FINE_TYPE=  '" + itemPayment.getFINE_TYPE() + "', "
                                                + "FINE=  '" + itemPayment.getFINE() + "', "
                                                + "PAYMENT_PERIOD_NO=  '" + itemPayment.getPAYMENT_PERIOD_NO() + "', "
                                                + "PAYMENT_DATE = TO_TIMESTAMP_TZ('" + StringUtil.checkNull(itemPayment.getPAYMENT_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                                                + "IS_REQUEST_REWARD=  '" + itemPayment.getIS_REQUEST_REWARD() + "', "
                                                + "IS_ACTIVE=  '" + itemPayment.getIS_ACTIVE() + "', "
                                                + "PAYMENT_CHANNEL=  '" + itemPayment.getPAYMENT_CHANNEL() + "', "
                                                + "PAYMENT_BANK=  '" + itemPayment.getPAYMENT_BANK() + "', "
                                                + "PAYMENT_REF_NO=  '" + itemPayment.getPAYMENT_REF_NO() + "', "
                                                + "IS_REVENUE=  '" + itemPayment.getIS_REVENUE() + "' "
                                                + " WHERE PAYMENT_ID = '" + itemPayment.getPAYMENT_ID() + "' ");
                                log.info("[SQL] : " + sqlBuilderPayment.toString());

                                if (itemPayment.getLawsuitPaymentDetail() != null) {
                                    log.info("[Sub] Size : " + itemPayment.getLawsuitPaymentDetail().size());

                                    if (itemPayment.getLawsuitPaymentDetail().size() > 0) {
                                        for (LawsuitPaymentDetail itemPaymentDetail : itemPayment.getLawsuitPaymentDetail()) {

                                            StringBuilder sqlBuilderPaymentDetail = new StringBuilder()
                                                    .append("UPDATE OPS_PAYMENT_DETAIL SET "
                                                            + "PAYMENT_ID=  '" + itemPaymentDetail.getPAYMENT_ID() + "', "
                                                            + "NOTICE_ID=  '" + itemPaymentDetail.getNOTICE_ID() + "', "
                                                            + "IS_REQUEST_BRIBE=  '" + itemPaymentDetail.getIS_REQUEST_BRIBE() + "', "
                                                            + "IS_ACTIVE=  '" + itemPaymentDetail.getIS_ACTIVE() + "', "
                                                            + "IS_ACTIVE=  '" + itemPaymentDetail.getIS_ACTIVE() + "' "
                                                            + " WHERE PAYMENT_DETAIL_ID = '" + itemPaymentDetail.getPAYMENT_DETAIL_ID() + "' ");
                                            log.info("[SQL] : " + sqlBuilderPayment.toString());

                                            getJdbcTemplate().update(sqlBuilderPayment.toString(), new Object[]{});
                                        }
                                    }
                                }

                                getJdbcTemplate().update(sqlBuilderPayment.toString(), new Object[]{});
                            }
                        }
                    }

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});


                }
            }
        }

        return true;
    }

    @Override
    public Boolean LawsuitupdDelete(LawsuitupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_LAWSUIT SET IS_ACTIVE = '0' WHERE LAWSUIT_ID = '" + req.getLAWSUIT_ID() + "' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_LAWSUIT_STAFF SET IS_ACTIVE = '0' WHERE LAWSUIT_ID = '" + req.getLAWSUIT_ID() + "' ");
        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_LAWSUIT_DETAIL SET IS_ACTIVE = '0' WHERE LAWSUIT_ID = '" + req.getLAWSUIT_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[]{});
        return true;
    }

    @Override
    public Lawsuit LawsuitgetByCon(LawsuitgetByConReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "LAWSUIT_ID," +
                        "INDICTMENT_ID," +
                        "OFFICE_ID," +
                        "OFFICE_CODE," +
                        "OFFICE_NAME," +
                        "IS_LAWSUIT," +
                        "REMARK_NOT_LAWSUIT," +
                        "LAWSUIT_NO," +
                        "to_char(LAWSUIT_NO_YEAR,'" + Pattern.FORMAT_DATETIME + "') as LAWSUIT_NO_YEAR," +
                        "to_char(LAWSUIT_DATE,'" + Pattern.FORMAT_DATETIME + "') as LAWSUIT_DATE," +
                        "TESTIMONY," +
                        "DELIVERY_DOC_NO_1," +
                        "DELIVERY_DOC_NO_2," +
                        "to_char(DELIVERY_DOC_DATE,'" + Pattern.FORMAT_DATETIME + "') as DELIVERY_DOC_DATE," +
                        "IS_OUTSIDE," +
                        "IS_SEIZE," +
                        "IS_ACTIVE " +
                        "from OPS_LAWSUIT  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and LAWSUIT_ID = '" + req.getLAWSUIT_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Lawsuit>() {

            public Lawsuit extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    Lawsuit item = new Lawsuit();
                    item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                    item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                    item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                    item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                    item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                    item.setIS_LAWSUIT(rs.getInt("IS_LAWSUIT"));
                    item.setREMARK_NOT_LAWSUIT(rs.getString("REMARK_NOT_LAWSUIT"));
                    item.setLAWSUIT_NO(rs.getInt("LAWSUIT_NO"));
                    item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                    item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                    item.setTESTIMONY(rs.getString("TESTIMONY"));
                    item.setDELIVERY_DOC_NO_1(rs.getString("DELIVERY_DOC_NO_1"));
                    item.setDELIVERY_DOC_NO_2(rs.getString("DELIVERY_DOC_NO_2"));
                    item.setDELIVERY_DOC_DATE(rs.getString("DELIVERY_DOC_DATE"));
                    item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                    item.setIS_SEIZE(rs.getInt("IS_SEIZE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    item.setLawsuitStaff(getLawsuitStaff(rs.getInt("LAWSUIT_ID")));
                    item.setLawsuitDetail(getLawsuitDetail(rs.getInt("LAWSUIT_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public List<Lawsuit> LawsuitVerifyLawsuitNo(LawsuitVerifyLawsuitNoReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "LAWSUIT_ID," +
                        "INDICTMENT_ID," +
                        "OFFICE_ID," +
                        "OFFICE_CODE," +
                        "OFFICE_NAME," +
                        "IS_LAWSUIT," +
                        "REMARK_NOT_LAWSUIT," +
                        "LAWSUIT_NO," +
                        "to_char(LAWSUIT_NO_YEAR,'" + Pattern.FORMAT_DATETIME + "') as LAWSUIT_NO_YEAR," +
                        "to_char(LAWSUIT_DATE,'" + Pattern.FORMAT_DATETIME + "') as LAWSUIT_DATE," +
                        "TESTIMONY," +
                        "DELIVERY_DOC_NO_1," +
                        "DELIVERY_DOC_NO_2," +
                        "to_char(DELIVERY_DOC_DATE,'" + Pattern.FORMAT_DATETIME + "') as DELIVERY_DOC_DATE," +
                        "IS_OUTSIDE," +
                        "IS_SEIZE," +
                        "IS_ACTIVE " +
                        "from OPS_LAWSUIT  where IS_ACTIVE = 1 ");


        if (req.getLAWSUIT_NO() != null && req.getLAWSUIT_NO() != 0) {
            sqlBuilder.append("and LAWSUIT_NO = '" + req.getLAWSUIT_NO() + "'");
        }

        if (req.getLAWSUIT_NO_YEAR() != null && !"".equals(req.getLAWSUIT_NO_YEAR())) {
            sqlBuilder.append("AND EXTRACT(YEAR FROM LAWSUIT_NO_YEAR) = '" + req.getLAWSUIT_NO_YEAR() + "' ");
        }

        if (req.getIS_OUTSIDE() != null && req.getIS_OUTSIDE() != 0) {
            sqlBuilder.append("and IS_OUTSIDE = '" + req.getIS_OUTSIDE() + "'");
        }

        if (req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE())) {
            sqlBuilder.append("AND OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "' ");
        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<Lawsuit> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public Lawsuit mapRow(ResultSet rs, int rowNum) throws SQLException {
                Lawsuit item = new Lawsuit();
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setIS_LAWSUIT(rs.getInt("IS_LAWSUIT"));
                item.setREMARK_NOT_LAWSUIT(rs.getString("REMARK_NOT_LAWSUIT"));
                item.setLAWSUIT_NO(rs.getInt("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setTESTIMONY(rs.getString("TESTIMONY"));
                item.setDELIVERY_DOC_NO_1(rs.getString("DELIVERY_DOC_NO_1"));
                item.setDELIVERY_DOC_NO_2(rs.getString("DELIVERY_DOC_NO_2"));
                item.setDELIVERY_DOC_DATE(rs.getString("DELIVERY_DOC_DATE"));
                item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                item.setIS_SEIZE(rs.getInt("IS_SEIZE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setLawsuitStaff(getLawsuitStaff(rs.getInt("LAWSUIT_ID")));
                item.setLawsuitDetail(getLawsuitDetail(rs.getInt("LAWSUIT_ID")));

                return item;
            }
        });

        return dataList;

    }


}
