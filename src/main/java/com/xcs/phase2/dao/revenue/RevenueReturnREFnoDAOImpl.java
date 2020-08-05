package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.revenue.RevenueCompareDetailModel;
import com.xcs.phase2.model.revenue.RevenueDetailModel;
import com.xcs.phase2.model.revenue.RevenuePaymentModel;
import com.xcs.phase2.model.revenue.RevenueReturnREFnoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RevenueReturnREFnoDAOImpl extends RevenueExt implements RevenueReturnREFnoDAO {

    private static final Logger log = LoggerFactory.getLogger(RevenueReturnREFnoDAOImpl.class);



    @Override
    public List<RevenueReturnREFnoModel> RevenueReturnUpdateREFno(List<RevenueReturnREFnoModel> req) {
        // TODO Auto-generated method stub

        if(req != null){

            for(RevenueReturnREFnoModel item : req){

                StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_REVENUE SET " +
                        " RECEIVE_REF_NO = '"+item.getRECEIVE_REF_NO()+"' , " +
                        " REVENUE_STATUS = '2' , " +
                        " RECEIVE_DATE = TO_TIMESTAMP_TZ('" + item.getRECEIVE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "') " +
                        " WHERE REVENUE_ID = '"+item.getREVENUE_ID()+"' ");

                getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

                if(item.getREVENUE_DETAIL() != null){
                    for(RevenueDetailModel item1 : item.getREVENUE_DETAIL()){
                        getJdbcTemplate().update("UPDATE OPS_REVENUE_DETAIL SET REVENUE_STATUS = '2' WHERE REVENUE_DETAIL_ID = '"+item1.getREVENUE_DETAIL_ID()+"' ", new Object[] {});
                    }
                }

                if(item.getPAYMENT() != null){
                    for(RevenuePaymentModel item1 : item.getPAYMENT()){
                        getJdbcTemplate().update("UPDATE OPS_PAYMENT SET IS_REVENUE = '2' WHERE PAYMENT_ID = '"+item1.getPAYMENT_ID()+"' ", new Object[] {});
                    }
                }

                if(item.getCOMPARE_DETAIL() != null){
                    for(RevenueCompareDetailModel item1 : item.getCOMPARE_DETAIL()){
                        getJdbcTemplate().update("UPDATE OPS_COMPARE_DETAIL SET IS_REVENUE = '2' WHERE COMPARE_DETAIL_ID = '"+item1.getCOMPARE_DETAIL_ID()+"' ", new Object[] {});
                    }
                }
            }
        }



        return req;
    }
}
