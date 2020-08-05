package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.evidencein.TransactionRunningItem;
import com.xcs.phase2.request.evidencein.TransactionRunningItemgetByConReq;
import com.xcs.phase2.request.evidencein.TransactionRunningItemupdByConReq;
import com.xcs.phase2.response.evidencein.TransactionRunningIteminsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class TransactionRunningItemDAOImpl extends EvidenceInExt implements TransactionRunningItemDAO{

    private static final Logger log = LoggerFactory.getLogger(TransactionRunningItemDAOImpl.class);

    @Override
    public List<TransactionRunningItem> TransactionRunningItemgetByCon(TransactionRunningItemgetByConReq req) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select * from " +
                        "OPS_TRANSACTION_RUNNING_ITEM" +
                        " where OPS_TRANSACTION_RUNNING_ITEM.RUNNING_PREFIX = '"+req.getRUNNING_PREFIX()+"'" +
                        " and OPS_TRANSACTION_RUNNING_ITEM.PRODUCT_GROUP_ID = '"+req.getPRODUCT_GROUP_ID()+"' " +
                        " and OPS_TRANSACTION_RUNNING_ITEM.WAREHOUSE_ID = '"+req.getWAREHOUSE_ID()+"' " +
                        " and OPS_TRANSACTION_RUNNING_ITEM.RUNNING_YEAR = TO_CHAR(SYSDATE, 'yy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')" +
                        " order by OPS_TRANSACTION_RUNNING_ITEM.RUNNING_ID desc");



        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<TransactionRunningItem> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public TransactionRunningItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                TransactionRunningItem item = new TransactionRunningItem();

                item.setRUNNING_ID(rs.getInt("RUNNING_ID"));
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setWAREHOUSE_ID(rs.getInt("WAREHOUSE_ID"));
                item.setRUNNING_NO(rs.getInt("RUNNING_NO"));
                item.setRUNNING_PREFIX(rs.getString("RUNNING_PREFIX"));
                item.setRUNNING_YEAR(rs.getString("RUNNING_YEAR"));
                item.setRUNNING_MONTH(rs.getString("RUNNING_MONTH"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public TransactionRunningIteminsAllResponse TransactionRunningIteminsAll(TransactionRunningItem req) {

        TransactionRunningIteminsAllResponse res = new TransactionRunningIteminsAllResponse();

        try {

            String RUNNING_ID = getSequences("SELECT OPS_TRANSACTION_RUNNING_ITEM_.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("INSERT INTO OPS_TRANSACTION_RUNNING_ITEM (" +
                            "RUNNING_ID," +
                            "PRODUCT_GROUP_ID," +
                            "WAREHOUSE_ID," +
                            "RUNNING_NO," +
                            "RUNNING_PREFIX," +
                            "RUNNING_YEAR," +
                            "RUNNING_MONTH" +
                            " ) VALUES (" +
                            "'" + RUNNING_ID + "', " +
                            "'"+req.getPRODUCT_GROUP_ID()+"'," +
                            "'"+req.getWAREHOUSE_ID()+"'," +
                            "'1'," +
                            "'"+req.getRUNNING_PREFIX()+"'," +
                            " TO_CHAR(SYSDATE, 'yy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') ," +
                            " TO_CHAR(SYSDATE, 'mm', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') " +
                            ")");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setRUNNING_ID(Integer.parseInt(RUNNING_ID));


            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setRUNNING_ID(0);
            return res;
        }

    }

    @Override
    public Boolean TransactionRunningItemupdByCon(TransactionRunningItemupdByConReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_TRANSACTION_RUNNING_ITEM SET RUNNING_NO = (select RUNNING_NO + 1  from OPS_TRANSACTION_RUNNING_ITEM where RUNNING_ID = '"+req.getRUNNING_ID()+"') where RUNNING_ID = '"+req.getRUNNING_ID()+"' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});



        return true;
    }
}
