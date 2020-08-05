package com.xcs.phase2.dao.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.compare.ComparePayment;
import com.xcs.phase2.model.compare.ComparePaymentDetail;
import com.xcs.phase2.request.compare.ComparePaymentupdDeleteReq;
import com.xcs.phase2.response.compare.ComparePaymentDetailResponse;
import com.xcs.phase2.response.compare.ComparePaymentResponse;
import com.xcs.phase2.response.compare.ComparePaymentinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ComparePaymentDAOImpl extends CompareExt implements ComparePaymentDAO{

    private static final Logger log = LoggerFactory.getLogger(CompareDetailStaffDAOImpl.class);


    @Override
    public ComparePaymentinsAllResponse ComparePaymentinsAll(List<ComparePayment> req) {

        ComparePaymentinsAllResponse res = new ComparePaymentinsAllResponse();

        try {


            if(req != null) {
                log.info("[Sub] Size : "+req.size());
                List<ComparePaymentResponse> list = new ArrayList<ComparePaymentResponse>();
                if(req.size() > 0){

                    for(ComparePayment item : req){

                        String PAYMENT_ID = getSequences("SELECT OPS_PAYMENT_SEQ.NEXTVAL FROM DUAL");
                        ComparePaymentResponse  obj = new ComparePaymentResponse();
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
                                        "IS_ACTIVE, " +
                                        "PAYMENT_CHANNEL, " +
                                        "PAYMENT_BANK," +
                                        "PAYMENT_REF_NO, " +
                                        "IS_REVENUE " +
                                        " ) values (" +
                                        "'"+PAYMENT_ID+"'," +
                                        "'"+item.getLAWSUIT_DETAIL_ID()+"'," +
                                        "'"+item.getCOMPARE_DETAIL_ID()+"'," +
                                        "'"+item.getFINE_TYPE()+"'," +
                                        "'"+item.getFINE()+"'," +
                                        "'"+item.getPAYMENT_PERIOD_NO()+"'," +
                                        "TO_TIMESTAMP_TZ('"+item.getPAYMENT_DATE()+"', '"+ Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
                                        "'"+item.getIS_REQUEST_REWARD()+"'," +
                                        "'"+item.getIS_ACTIVE()+"'," +
                                        "'"+item.getPAYMENT_CHANNEL()+"'," +
                                        "'"+item.getPAYMENT_BANK()+"'," +
                                        "'"+item.getPAYMENT_REF_NO()+"'," +
                                        "'"+item.getIS_REVENUE()+"'" +
                                        " )");
                        log.info("[SQL] : "+sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});


                        if(item.getComparePaymentDetail() != null) {
                            log.info("[Sub] Size : "+item.getComparePaymentDetail().size());
                            List<ComparePaymentDetailResponse> list1 = new ArrayList<ComparePaymentDetailResponse>();

                            if(item.getComparePaymentDetail().size() > 0){
                                for(ComparePaymentDetail item1 : item.getComparePaymentDetail()){

                                    String PAYMENT_DETAIL_ID = getSequences("SELECT OPS_PAYMENT_DETAIL_SEQ.NEXTVAL FROM DUAL");
                                    ComparePaymentDetailResponse  obj1 = new ComparePaymentDetailResponse();
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
                            obj.setComparePaymentDetail(list1);
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
    public Boolean ComparePaymentupdDelete(List<ComparePaymentupdDeleteReq> req) {

        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(ComparePaymentupdDeleteReq item : req){

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
