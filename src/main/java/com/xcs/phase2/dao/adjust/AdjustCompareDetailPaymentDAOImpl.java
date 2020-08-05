package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.adjust.AdjustCompareDetailPayment;
import com.xcs.phase2.request.adjust.AdjustCompareDetailPaymentupdDeleteReq;
import com.xcs.phase2.response.adjust.AdjustCompareDetailPaymentResponse;
import com.xcs.phase2.response.adjust.AdjustCompareDetailPaymentinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdjustCompareDetailPaymentDAOImpl extends AdjustExt implements AdjustCompareDetailPaymentDAO{

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareDetailPaymentDAOImpl.class);

    @Override
    public AdjustCompareDetailPaymentinsAllResponse AdjustCompareDetailPaymentinsAll(List<AdjustCompareDetailPayment> req) {

        AdjustCompareDetailPaymentinsAllResponse res = new AdjustCompareDetailPaymentinsAllResponse();

        try {


            if(req != null) {
                log.info("[Sub] Size : "+req.size());
                List<AdjustCompareDetailPaymentResponse> list = new ArrayList<AdjustCompareDetailPaymentResponse>();
                if(req.size() > 0){

                    for(AdjustCompareDetailPayment item : req){

                        String PAYMENT_ID = getSequences("SELECT OPS_COMPARE_DETAIL_PAYMENT_SEQ.NEXTVAL FROM DUAL");
                        AdjustCompareDetailPaymentResponse  obj = new AdjustCompareDetailPaymentResponse();
                        obj.setPAYMENT_ID(Integer.parseInt(PAYMENT_ID));

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
                                        "'"+item.getCOMPARE_DETAIL_ID()+"'," +
                                        "'"+item.getPAYMENT_TYPE()+"'," +
                                        "'"+item.getPAYMENT_FINE()+"'," +
                                        "'"+item.getREFFERENCE_NO()+"'," +
                                        "'"+item.getIS_ACTIVE()+"'" +
                                        " )");
                        log.info("[SQL] : "+sqlBuilderCompareDetailPayment.toString());
                        getJdbcTemplate().update(sqlBuilderCompareDetailPayment.toString(), new Object[] {});

                        list.add(obj);
                    }
                }
                res.setAdjustCompareDetailPayment(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setAdjustCompareDetailPayment(null);
            return res;
        }
    }

    @Override
    public Boolean AdjustCompareDetailPaymentupdByCon(List<AdjustCompareDetailPayment> request) {

        if(request != null) {
            log.info("[Sub] Size : "+request.size());

            if(request.size() > 0){

                for(AdjustCompareDetailPayment req : request){

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_COMPARE_DETAIL_PAYMENT "
                                    + "SET "
                                    + "COMPARE_DETAIL_ID=  '"+req.getCOMPARE_DETAIL_ID()+"', "
                                    + "PAYMENT_TYPE=  '"+req.getPAYMENT_TYPE()+"', "
                                    + "PAYMENT_FINE=  '"+req.getPAYMENT_FINE()+"', "
                                    + "REFFERENCE_NO=  '"+req.getREFFERENCE_NO()+"', "
                                    +"IS_ACTIVE = '"+req.getIS_ACTIVE()+"' "
                                    + "WHERE PAYMENT_ID = '"+req.getPAYMENT_ID()+"' ");
                    log.info("[SQL] : "+sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
                }
            }
        }

        return true;
    }

    @Override
    public Boolean AdjustCompareDetailPaymentupdDelete(List<AdjustCompareDetailPaymentupdDeleteReq> req) {

        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(AdjustCompareDetailPaymentupdDeleteReq item : req){

                    StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL_PAYMENT SET IS_ACTIVE = '0' WHERE PAYMENT_ID = '"+item.getPAYMENT_ID()+"' ");
                    log.info("[SQL] Sub : "+sqlBuilder.toString());
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

                }
            }
        }

        return true;
    }
}
