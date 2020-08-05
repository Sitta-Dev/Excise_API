package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.adjust.*;
import com.xcs.phase2.request.adjust.AdjustCompareDetailupdDeleteReq;
import com.xcs.phase2.response.adjust.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdjustCompareDetailDAOImpl extends AdjustExt implements AdjustCompareDetailDAO {

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareDetailDAOImpl.class);

    @Override
    public AdjustCompareDetailinsAllResponse AdjustCompareDetailinsAll(AdjustCompareDetail req) {

        AdjustCompareDetailinsAllResponse res = new AdjustCompareDetailinsAllResponse();

        try {

            String COMPARE_DETAIL_ID = getSequences("SELECT OPS_COMPARE_DETAIL_SEQ.NEXTVAL FROM DUAL");
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
                            "IS_ACTIVE " +
                            " ) values (" +
                            "'"+COMPARE_DETAIL_ID+"'," +
                            "'"+req.getCOMPARE_MAPPING_ID()+"'," +
                            "'"+req.getRECEIPT_OFFICE_ID()+"'," +
                            "'"+req.getAPPROVE_OFFICE_ID()+"'," +
                            "'"+req.getMISTREAT_NO()+"'," +
                            "'"+req.getOLD_PAYMENT_FINE()+"'," +
                            "'"+req.getPAYMENT_FINE()+"'," +
                            "'"+req.getDIFFERENCE_PAYMENT_FINE()+"'," +
                            "'"+req.getTREASURY_MONEY()+"'," +
                            "'"+req.getBRIBE_MONEY()+"'," +
                            "'"+req.getREWARD_MONEY()+"'," +
                            "TO_TIMESTAMP_TZ('"+req.getPAYMENT_FINE_DUE_DATE()+"', '"+ Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                            "TO_TIMESTAMP_TZ('"+req.getPAYMENT_VAT_DUE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                            "'"+req.getINSURANCE()+"'," +
                            "'"+req.getGAURANTEE()+"'," +
                            "TO_TIMESTAMP_TZ('"+req.getPAYMENT_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                            "'"+req.getRECEIPT_TYPE()+"'," +
                            "'"+req.getRECEIPT_BOOK_NO()+"'," +
                            "'"+req.getRECEIPT_NO()+"'," +
                            "'"+req.getRECEIPT_OFFICE_CODE()+"'," +
                            "'"+req.getRECEIPT_OFFICE_NAME()+"'," +
                            "'"+req.getAPPROVE_OFFICE_CODE()+"'," +
                            "'"+req.getAPPROVE_OFFICE_NAME()+"'," +
                            "TO_TIMESTAMP_TZ('"+req.getAPPROVE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                            "'"+req.getAPPROVE_TYPE()+"'," +
                            "'"+req.getCOMMAND_NO()+"'," +
                            "TO_TIMESTAMP_TZ('"+req.getCOMMAND_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                            "'"+req.getREMARK_NOT_AGREE()+"'," +
                            "'"+req.getREMARK_NOT_APPROVE()+"'," +
                            "'"+req.getFACT()+"'," +
                            "'"+req.getCOMPARE_REASON()+"'," +
                            "'"+req.getADJUST_REASON()+"'," +
                            "'"+req.getCOMPARE_TYPE()+"'," +
                            "'"+req.getIS_REQUEST()+"'," +
                            "'"+req.getIS_TEMP_RELEASE()+"'," +
                            "'"+req.getIS_INSURANCE()+"'," +
                            "'"+req.getIS_GAURANTEE()+"'," +
                            "'"+req.getIS_PAYMENT()+"'," +
                            "'"+req.getIS_REVENUE()+"'," +
                            "'"+req.getIS_AGREE()+"'," +
                            "'"+req.getIS_APPROVE()+"'," +
                            "'"+req.getIS_AUTHORITY()+"'," +
                            "'"+req.getIS_ACTIVE()+"'" +
                            " )");
            log.info("[SQL] : "+sqlBuilderCompareDetail.toString());

            getJdbcTemplate().update(sqlBuilderCompareDetail.toString(), new Object[] {});
            res.setCOMPARE_DETAIL_ID(Integer.parseInt(COMPARE_DETAIL_ID));

            // st CompareDetailPayment
            if(req.getAdjustCompareDetailPayment() != null) {
                log.info("[Sub] Size : "+req.getAdjustCompareDetailPayment().size());
                List<AdjustCompareDetailPaymentResponse> compareDetailPaymentList = new ArrayList<AdjustCompareDetailPaymentResponse>();

                if(req.getAdjustCompareDetailPayment().size() > 0){
                    for(AdjustCompareDetailPayment item2 : req.getAdjustCompareDetailPayment()){

                        String PAYMENT_ID = getSequences("SELECT OPS_COMPARE_DETAIL_PAYMENT_SEQ.NEXTVAL FROM DUAL");
                        AdjustCompareDetailPaymentResponse  objCompareDetailPayment = new AdjustCompareDetailPaymentResponse();
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
                res.setAdjustCompareDetailPayment(compareDetailPaymentList);
            }
            //en CompareDetailPayment

            // st CompareDetailFine
            if(req.getAdjustCompareDetailFine() != null) {
                log.info("[Sub] Size : "+req.getAdjustCompareDetailFine().size());
                List<AdjustCompareDetailFineResponse> compareDetailFineList = new ArrayList<AdjustCompareDetailFineResponse>();

                if(req.getAdjustCompareDetailPayment().size() > 0){
                    for(AdjustCompareDetailFine item2 : req.getAdjustCompareDetailFine()){

                        String FINE_ID = getSequences("SELECT OPS_COMPARE_FINE_SEQ.NEXTVAL FROM DUAL");
                        AdjustCompareDetailFineResponse  objCompareDetailFine = new AdjustCompareDetailFineResponse();
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
                res.setAdjustCompareDetailFine(compareDetailFineList);
            }
            //en CompareDetailFine

            // st ComparePayment
            if(req.getAdjustComparePayment() != null) {
                log.info("[Sub] Size : "+req.getAdjustComparePayment().size());
                List<AdjustComparePaymentResponse> comparePaymentList = new ArrayList<AdjustComparePaymentResponse>();

                if(req.getAdjustComparePayment().size() > 0){
                    for(AdjustComparePayment item2 : req.getAdjustComparePayment()){

                        String PAYMENT_ID = getSequences("SELECT OPS_PAYMENT_SEQ.NEXTVAL FROM DUAL");
                        AdjustComparePaymentResponse  objComparePayment = new AdjustComparePaymentResponse();
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
                                        "IS_ACTIVE " +
                                        " ) values (" +
                                        "'"+PAYMENT_ID+"'," +
                                        "'"+item2.getLAWSUIT_DETAIL_ID()+"'," +
                                        "'"+COMPARE_DETAIL_ID+"'," +
                                        "'"+item2.getFINE_TYPE()+"'," +
                                        "'"+item2.getFINE()+"'," +
                                        "'"+item2.getPAYMENT_PERIOD_NO()+"'," +
                                        "TO_TIMESTAMP_TZ('"+item2.getPAYMENT_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                                        "'"+item2.getIS_REQUEST_REWARD()+"'," +
                                        "'"+item2.getIS_ACTIVE()+"'" +
                                        " )");
                        log.info("[SQL] : "+sqlBuilderComparePayment.toString());

                        getJdbcTemplate().update(sqlBuilderComparePayment.toString(), new Object[] {});

                        // st paymentdetail
                        if(item2.getAdjustComparePaymentDetail() != null) {
                            log.info("[Sub] Size : "+item2.getAdjustComparePaymentDetail().size());
                            List<AdjustComparePaymentDetailResponse> comparePaymentDetailList = new ArrayList<AdjustComparePaymentDetailResponse>();

                            if(item2.getAdjustComparePaymentDetail().size() > 0){
                                for(AdjustComparePaymentDetail item3 : item2.getAdjustComparePaymentDetail()){

                                    String PAYMENT_DETAIL_ID = getSequences("SELECT OPS_PAYMENT_DETAIL_SEQ.NEXTVAL FROM DUAL");
                                    AdjustComparePaymentDetailResponse objComparePaymentDetail = new AdjustComparePaymentDetailResponse();
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
                                                    "'"+item2.getIS_ACTIVE()+"'" +
                                                    " )");
                                    log.info("[SQL] : "+sqlBuilderComparePaymentDetail.toString());

                                    getJdbcTemplate().update(sqlBuilderComparePaymentDetail.toString(), new Object[] {});
                                    comparePaymentDetailList.add(objComparePaymentDetail);
                                }
                            }
                            objComparePayment.setAdjustComparePaymentDetail(comparePaymentDetailList);
                        }

                        //en paymentdetail


                        comparePaymentList.add(objComparePayment);
                    }
                }
                res.setAdjustComparePayment(comparePaymentList);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setCOMPARE_DETAIL_ID(0);
            res.setAdjustCompareDetailPayment(null);
            res.setAdjustCompareDetailFine(null);
            res.setAdjustComparePayment(null);
            return res;
        }
    }

    @Override
    public Boolean AdjustCompareDetailupdByCon(AdjustCompareDetail req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_COMPARE_DETAIL SET "
                        + "COMPARE_MAPPING_ID=  '"+req.getCOMPARE_MAPPING_ID()+"', "
                        + "RECEIPT_OFFICE_ID=  '"+req.getRECEIPT_OFFICE_ID()+"', "
                        + "APPROVE_OFFICE_ID=  '"+req.getAPPROVE_OFFICE_ID()+"', "
                        + "MISTREAT_NO=  '"+req.getMISTREAT_NO()+"', "
                        + "OLD_PAYMENT_FINE=  '"+req.getOLD_PAYMENT_FINE()+"', "
                        + "PAYMENT_FINE=  '"+req.getPAYMENT_FINE()+"', "
                        + "DIFFERENCE_PAYMENT_FINE=  '"+req.getDIFFERENCE_PAYMENT_FINE()+"', "
                        + "TREASURY_MONEY=  '"+req.getTREASURY_MONEY()+"', "
                        + "BRIBE_MONEY=  '"+req.getBRIBE_MONEY()+"', "
                        + "REWARD_MONEY=  '"+req.getREWARD_MONEY()+"', "
                        + "PAYMENT_FINE_DUE_DATE = TO_TIMESTAMP_TZ('"+req.getPAYMENT_FINE_DUE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
                        + "PAYMENT_VAT_DUE_DATE = TO_TIMESTAMP_TZ('"+req.getPAYMENT_VAT_DUE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
                        + "INSURANCE=  '"+req.getINSURANCE()+"', "
                        + "GAURANTEE=  '"+req.getGAURANTEE()+"', "
                        + "PAYMENT_DATE = TO_TIMESTAMP_TZ('"+req.getPAYMENT_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
                        + "RECEIPT_TYPE=  '"+req.getRECEIPT_TYPE()+"', "
                        + "RECEIPT_BOOK_NO=  '"+req.getRECEIPT_BOOK_NO()+"', "
                        + "RECEIPT_NO=  '"+req.getRECEIPT_NO()+"', "
                        + "RECEIPT_OFFICE_CODE=  '"+req.getRECEIPT_OFFICE_CODE()+"', "
                        + "RECEIPT_OFFICE_NAME=  '"+req.getRECEIPT_OFFICE_NAME()+"', "
                        + "APPROVE_OFFICE_CODE=  '"+req.getAPPROVE_OFFICE_CODE()+"', "
                        + "APPROVE_OFFICE_NAME=  '"+req.getAPPROVE_OFFICE_NAME()+"', "
                        + "APPROVE_DATE = TO_TIMESTAMP_TZ('"+req.getAPPROVE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
                        + "APPROVE_TYPE=  '"+req.getAPPROVE_TYPE()+"', "
                        + "COMMAND_NO=  '"+req.getCOMMAND_NO()+"', "
                        + "COMMAND_DATE = TO_TIMESTAMP_TZ('"+req.getCOMMAND_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
                        + "REMARK_NOT_AGREE=  '"+req.getREMARK_NOT_AGREE()+"', "
                        + "REMARK_NOT_APPROVE=  '"+req.getREMARK_NOT_APPROVE()+"', "
                        + "FACT=  '"+req.getFACT()+"', "
                        + "COMPARE_REASON=  '"+req.getCOMPARE_REASON()+"', "
                        + "ADJUST_REASON=  '"+req.getADJUST_REASON()+"', "
                        + "COMPARE_TYPE=  '"+req.getCOMPARE_TYPE()+"', "
                        + "IS_REQUEST=  '"+req.getIS_REQUEST()+"', "
                        + "IS_TEMP_RELEASE=  '"+req.getIS_TEMP_RELEASE()+"', "
                        + "IS_INSURANCE=  '"+req.getIS_INSURANCE()+"', "
                        + "IS_GAURANTEE=  '"+req.getIS_GAURANTEE()+"', "
                        + "IS_PAYMENT=  '"+req.getIS_PAYMENT()+"', "
                        + "IS_REVENUE=  '"+req.getIS_REVENUE()+"', "
                        + "IS_AGREE=  '"+req.getIS_AGREE()+"', "
                        + "IS_APPROVE=  '"+req.getIS_APPROVE()+"', "
                        + "IS_AUTHORITY=  '"+req.getIS_AUTHORITY()+"', "
                        + "IS_ACTIVE=  '"+req.getIS_ACTIVE()+"'  "
                        + " WHERE COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"' ");

        log.info("[SQL] : "+sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});



        return true;
    }

    @Override
    public Boolean AdjustCompareDetailupdDelete(AdjustCompareDetailupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL SET IS_ACTIVE = '0' WHERE COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL_PAYMENT SET IS_ACTIVE = '0' WHERE COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"' ");
        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL_FINE SET IS_ACTIVE = '0' WHERE COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"' ");
        StringBuilder sqlBuilder4 = new StringBuilder().append("UPDATE OPS_PAYMENT SET IS_ACTIVE = '0' WHERE COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"' ");
        StringBuilder sqlBuilder5 = new StringBuilder().append("UPDATE OPS_PAYMENT_DETAIL SET IS_ACTIVE = '0' WHERE PAYMENT_ID in(select PAYMENT_ID from OPS_PAYMENT where COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"') ");
        StringBuilder sqlBuilder6 = new StringBuilder().append("UPDATE OPS_COMPARE_STAFF SET IS_ACTIVE = '0' WHERE COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"' ");



        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder4.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder5.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder6.toString(), new Object[] {});
        return true;
    }
}
