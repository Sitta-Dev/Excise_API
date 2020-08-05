package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.revenue.RevenueIncPayment;
import com.xcs.phase2.model.revenue.RevenueIncPaymentType;
import com.xcs.phase2.request.revenue.IncPaymentListGetByConReq;
import com.xcs.phase2.request.revenue.IncPaymentListUpdDeleteReq;
import com.xcs.phase2.response.revenue.IncPaymentListinsAllResponse;
import com.xcs.phase2.response.revenue.RevenueIncPaymentResponse;
import com.xcs.phase2.response.revenue.RevenueIncPaymentTypeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IncPaymentListinsAllDAOImpl extends RevenueExt implements IncPaymentListDAO{

    private static final Logger log = LoggerFactory.getLogger(IncPaymentListinsAllDAOImpl.class);


    @Override
    @Transactional(rollbackFor = Exception.class)
    public IncPaymentListinsAllResponse IncPaymentListinsAll(List<RevenueIncPayment> req) {

        IncPaymentListinsAllResponse res = new IncPaymentListinsAllResponse();

        try {

            if (req != null) {
                log.info("[Sub] Size : " + req.size());
                List<RevenueIncPaymentResponse> list = new ArrayList<RevenueIncPaymentResponse>();

                if (req.size() > 0) {
                    for (RevenueIncPayment item : req) {

                        String INC_PAYMENT_ID = getSequences("SELECT INC_PAYMENT_SEQ.NEXTVAL FROM DUAL");
                        RevenueIncPaymentResponse obj = new RevenueIncPaymentResponse();
                        obj.setINC_PAYMENT_ID(Integer.parseInt(INC_PAYMENT_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into INC_PAYMENT ( " +
                                        "INC_PAYMENT_ID," +
                                        "SEQ_NO," +
                                        "GROUPID," +
                                        "TAX_AMT," +
                                        "BRIBE_AMT," +
                                        "REWARD_AMT," +
                                        "COUNT_NUM," +
                                        "REVENUE_ID," +
                                        "IS_ACTIVE" +
                                        " ) values (" +
                                        "'" + INC_PAYMENT_ID + "'," +
                                        "'" + item.getSEQ_NO() + "'," +
                                        "'" + item.getGROUPID() + "'," +
                                        "'" + item.getTAX_AMT() + "'," +
                                        "'" + item.getBRIBE_AMT() + "'," +
                                        "'" + item.getREWARD_AMT() + "'," +
                                        "'" + item.getCOUNT_NUM() + "'," +
                                        "'" + item.getREVENUE_ID() + "'," +
                                        "'" + item.getIS_ACTIVE() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                        if (item.getRevenueIncPaymentType().size() > 0) {

                            List<RevenueIncPaymentTypeResponse> list1 = new ArrayList<RevenueIncPaymentTypeResponse>();

                            for (RevenueIncPaymentType item1 : item.getRevenueIncPaymentType()) {

                                String INC_PAYMENT_TYPE_ID = getSequences("SELECT INC_PAYMENT_TYPE_SEQ.NEXTVAL FROM DUAL");
                                RevenueIncPaymentTypeResponse obj1 = new RevenueIncPaymentTypeResponse();
                                obj1.setINC_PAYMENT_TYPE_ID(Integer.parseInt(INC_PAYMENT_TYPE_ID));

                                StringBuilder sqlBuilderSub1 = new StringBuilder()
                                        .append("Insert into INC_PAYMENT_TYPE ( " +
                                                "INC_PAYMENT_TYPE_ID," +
                                                "INC_PAYMENT_ID," +
                                                "PAYMENT_TYPE," +
                                                "BANK_CODE," +
                                                "BRANCH_CODE," +
                                                "CHEQUE_TYPE," +
                                                "CHEQUE_FLAG," +
                                                "CHWQUE_NO," +
                                                "CHEQUE_DATE," +
                                                "PAYMENT_AMT," +
                                                "ADJUST_TYPE," +
                                                "PAYMENT_ID," +
                                                "IS_ACTIVE" +
                                                " ) values (" +
                                                "'"+INC_PAYMENT_TYPE_ID+"'," +
                                                "'"+INC_PAYMENT_ID+"'," +
                                                "'"+item1.getPAYMENT_TYPE()+"'," +
                                                "'"+item1.getBANK_CODE()+"'," +
                                                "'"+item1.getBRANCH_CODE()+"'," +
                                                "'"+item1.getCHEQUE_TYPE()+"'," +
                                                "'"+item1.getCHEQUE_FLAG()+"'," +
                                                "'"+item1.getCHWQUE_NO()+"'," +
                                                "'"+item1.getCHEQUE_DATE()+"'," +
                                                "'"+item1.getPAYMENT_AMT()+"'," +
                                                "'"+item1.getADJUST_TYPE()+"'," +
                                                "'"+item1.getPAYMENT_ID()+"'," +
                                                "'"+item1.getIS_ACTIVE()+"'" +
                                                " )");
                                log.info("[SQL] : " + sqlBuilderSub1.toString());

                                getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[]{});
                                list1.add(obj1);

                            }

                            obj.setRevenueIncPaymentType(list1);
                        }

                        list.add(obj);

                    }
                }
                res.setRevenueIncPayment(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setRevenueIncPayment(null);
            return res;
        }
    }

    @Override
    public List<RevenueIncPayment> IncPaymentListGetByCon(IncPaymentListGetByConReq req) {
        // TODO Auto-generated method stub


        StringBuilder sqlBuilder = new StringBuilder().append(" select * from inc_payment where is_active = 1 and revenue_id =  "+req.getREVENUE_ID());

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueIncPayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueIncPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueIncPayment item = new RevenueIncPayment();
                item.setINC_PAYMENT_ID(rs.getInt("INC_PAYMENT_ID"));
                item.setSEQ_NO(rs.getInt("SEQ_NO"));
                item.setGROUPID(rs.getString("GROUPID"));
                item.setTAX_AMT(rs.getInt("TAX_AMT"));
                item.setBRIBE_AMT(rs.getInt("BRIBE_AMT"));
                item.setREWARD_AMT(rs.getInt("REWARD_AMT"));
                item.setCOUNT_NUM(rs.getInt("COUNT_NUM"));
                item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setRevenueIncPaymentType(getRevenueIncPaymentType(rs.getInt("REVENUE_ID")));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public boolean IncPaymentListUpdDelete(IncPaymentListUpdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE INC_PAYMENT SET IS_ACTIVE = '0' WHERE REVENUE_ID = '"+req.getREVENUE_ID()+"' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE INC_PAYMENT_TYPE SET IS_ACTIVE = '0' WHERE inc_payment_id in(select inc_payment_id from inc_payment where revenue_id = "+req.getREVENUE_ID()+")  ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
        return true;
    }
}
