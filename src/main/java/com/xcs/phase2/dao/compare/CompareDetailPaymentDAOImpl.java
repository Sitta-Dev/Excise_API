package com.xcs.phase2.dao.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.compare.CompareDetailPayment;
import com.xcs.phase2.request.compare.CompareDetailPaymentupdDeleteReq;
import com.xcs.phase2.response.compare.CompareDetailPaymentResponse;
import com.xcs.phase2.response.compare.CompareDetailPaymentinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompareDetailPaymentDAOImpl extends CompareExt implements CompareDetailPaymentDAO{

    private static final Logger log = LoggerFactory.getLogger(CompareDetailPaymentDAOImpl.class);


    @Override
    public CompareDetailPaymentinsAllResponse CompareDetailPaymentinsAll(List<CompareDetailPayment> req) {

        CompareDetailPaymentinsAllResponse res = new CompareDetailPaymentinsAllResponse();

        try {


            if(req != null) {
                log.info("[Sub] Size : "+req.size());
                List<CompareDetailPaymentResponse> list = new ArrayList<CompareDetailPaymentResponse>();
                if(req.size() > 0){

                    for(CompareDetailPayment item : req){

                        String PAYMENT_ID = getSequences("SELECT OPS_COMPARE_DETAIL_PAYMENT_SEQ.NEXTVAL FROM DUAL");
                        CompareDetailPaymentResponse  obj = new CompareDetailPaymentResponse();
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
                res.setCompareDetailPayment(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setCompareDetailPayment(null);
            return res;
        }
    }

    @Override
    public Boolean CompareDetailPaymentupdByCon(List<CompareDetailPayment> request) {

        if(request != null) {
            log.info("[Sub] Size : "+request.size());

            if(request.size() > 0){

                for(CompareDetailPayment req : request){

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
    public Boolean CompareDetailPaymentupdDelete(List<CompareDetailPaymentupdDeleteReq> req) {

        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(CompareDetailPaymentupdDeleteReq item : req){

                    StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL_PAYMENT SET IS_ACTIVE = '0' WHERE PAYMENT_ID = '"+item.getPAYMENT_ID()+"' ");
                    log.info("[SQL] Sub : "+sqlBuilder.toString());
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

                }
            }
        }

        return true;
    }
}
