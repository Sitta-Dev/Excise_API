package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.revenue.RevenueDetail;
import com.xcs.phase2.request.revenue.RevenueDetailupdDeleteReq;
import com.xcs.phase2.response.revenue.RevenueDetailResponse;
import com.xcs.phase2.response.revenue.RevenueDetailinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RevenueDetailDAOImpl extends RevenueExt implements RevenueDetailDAO{

    private static final Logger log = LoggerFactory.getLogger(RevenueDetailDAOImpl.class);

    @Override
    public RevenueDetailinsAllResponse RevenueDetailinsAll(RevenueDetail req) {

        RevenueDetailinsAllResponse res = new RevenueDetailinsAllResponse();

        try {

            String REVENUE_DETAIL_ID = getSequences("SELECT OPS_REVENUE_DETAIL_SEQ.NEXTVAL FROM DUAL");
            RevenueDetailResponse obj = new RevenueDetailResponse();
            obj.setREVENUE_DETAIL_ID(Integer.parseInt(REVENUE_DETAIL_ID));

            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_REVENUE_DETAIL (" +
                            "REVENUE_DETAIL_ID," +
                            "REVENUE_ID," +
                            "COMPARE_DETAIL_ID," +
                            "REVENUE_STATUS," +
                            "LAWSUIT_DETAIL_ID," +
                            "PAYMENT_ID," +
                            "IS_ACTIVE " +
                            ")values (" +
                            "'" + REVENUE_DETAIL_ID + "'," +
                            "'" + req.getREVENUE_ID() + "'," +
                            "'" + req.getCOMPARE_DETAIL_ID() + "'," +
                            "'" + req.getREVENUE_STATUS() + "'," +
                            "'" + req.getLAWSUIT_DETAIL_ID() + "'," +
                            "'" + req.getPAYMENT_ID() + "'," +
                            "'" + req.getIS_ACTIVE() + "' )");
            log.info("[SQL] : " + sqlBuilder.toString());

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setREVENUE_DETAIL_ID(Integer.parseInt(REVENUE_DETAIL_ID));


            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setREVENUE_DETAIL_ID(0);
            return res;
        }
    }

    @Override
    public Boolean RevenueDetailupdDelete(RevenueDetailupdDeleteReq req) {

        StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_REVENUE_DETAIL SET IS_ACTIVE = '0' WHERE REVENUE_DETAIL_ID = '"+req.getREVENUE_DETAIL_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
        return true;
    }
}
