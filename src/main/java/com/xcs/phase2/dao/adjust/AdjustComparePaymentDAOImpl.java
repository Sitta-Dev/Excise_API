package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.adjust.AdjustComparePayment;
import com.xcs.phase2.model.adjust.AdjustComparePaymentDetail;
import com.xcs.phase2.request.adjust.AdjustComparePaymentupdDeleteReq;
import com.xcs.phase2.response.adjust.AdjustComparePaymentDetailResponse;
import com.xcs.phase2.response.adjust.AdjustComparePaymentResponse;
import com.xcs.phase2.response.adjust.AdjustComparePaymentinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdjustComparePaymentDAOImpl extends AdjustExt implements AdjustComparePaymentDAO{

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareDetailStaffDAOImpl.class);


    @Override
    public AdjustComparePaymentinsAllResponse AdjustComparePaymentinsAll(List<AdjustComparePayment> req) {

        AdjustComparePaymentinsAllResponse res = new AdjustComparePaymentinsAllResponse();

        try {


            if(req != null) {
                log.info("[Sub] Size : "+req.size());
                List<AdjustComparePaymentResponse> list = new ArrayList<AdjustComparePaymentResponse>();
                if(req.size() > 0){

                    for(AdjustComparePayment item : req){

                        String PAYMENT_ID = getSequences("SELECT OPS_PAYMENT_SEQ.NEXTVAL FROM DUAL");
                        AdjustComparePaymentResponse  obj = new AdjustComparePaymentResponse();
                        obj.setPAYMENT_ID(Integer.parseInt(PAYMENT_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
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
                                        "'"+item.getLAWSUIT_DETAIL_ID()+"'," +
                                        "'"+item.getCOMPARE_DETAIL_ID()+"'," +
                                        "'"+item.getFINE_TYPE()+"'," +
                                        "'"+item.getFINE()+"'," +
                                        "'"+item.getPAYMENT_PERIOD_NO()+"'," +
                                        "TO_TIMESTAMP_TZ('"+item.getPAYMENT_DATE()+"', '"+ Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                                        "'"+item.getIS_REQUEST_REWARD()+"'," +
                                        "'"+item.getIS_ACTIVE()+"'" +
                                        " )");
                        log.info("[SQL] : "+sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});


                        if(item.getAdjustComparePaymentDetail() != null) {
                            log.info("[Sub] Size : "+item.getAdjustComparePaymentDetail().size());
                            List<AdjustComparePaymentDetailResponse> list1 = new ArrayList<AdjustComparePaymentDetailResponse>();

                            if(item.getAdjustComparePaymentDetail().size() > 0){
                                for(AdjustComparePaymentDetail item1 : item.getAdjustComparePaymentDetail()){

                                    String PAYMENT_DETAIL_ID = getSequences("SELECT OPS_PAYMENT_DETAIL_SEQ.NEXTVAL FROM DUAL");
                                    AdjustComparePaymentDetailResponse  obj1 = new AdjustComparePaymentDetailResponse();
                                    obj1.setPAYMENT_DETAIL_ID(Integer.parseInt(PAYMENT_DETAIL_ID));

                                    StringBuilder sqlBuilderSub1 = new StringBuilder()
                                            .append("Insert into OPS_PAYMENT_DETAIL ( " +
                                                    "PAYMENT_DETAIL_ID," +
                                                    "PAYMENT_ID," +
                                                    "NOTICE_ID," +
                                                    "IS_REQUEST_BRIBE," +
                                                    "IS_ACTIVE " +
                                                    " ) values (" +
                                                    "'"+PAYMENT_DETAIL_ID+"'," +
                                                    "'"+PAYMENT_ID+"'," +
                                                    "'"+item1.getNOTICE_ID()+"'," +
                                                    "'"+item1.getIS_REQUEST_BRIBE()+"'," +
                                                    "'"+item1.getIS_ACTIVE()+"'" +
                                                    " )");
                                    log.info("[SQL] : "+sqlBuilderSub1.toString());

                                    getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[] {});
                                    list1.add(obj1);
                                }
                            }
                            obj.setAdjustComparePaymentDetail(list1);
                        }

                        list.add(obj);
                    }
                }
                res.setComparePayment(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setComparePayment(null);
            return res;
        }
    }

    @Override
    public Boolean AdjustComparePaymentupdDelete(List<AdjustComparePaymentupdDeleteReq> req) {

        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(AdjustComparePaymentupdDeleteReq item : req){

                    StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_PAYMENT SET IS_ACTIVE = '0' WHERE PAYMENT_ID = '"+item.getPAYMENT_ID()+"' ");
                    StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_PAYMENT_DETAIL SET IS_ACTIVE = '0' WHERE PAYMENT_ID = '"+item.getPAYMENT_ID()+"' ");
                    log.info("[SQL] Sub : "+sqlBuilder.toString());
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
                    getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});

                }
            }
        }

        return true;
    }
}
