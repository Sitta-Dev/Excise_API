package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.TransactionRunning;
import com.xcs.phase2.request.compare.TransactionRunninggetByConReq;
import com.xcs.phase2.request.compare.TransactionRunningupdByConReq;
import com.xcs.phase2.response.compare.CompareinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class TransactionRunningDAOImpl extends CompareExt implements TransactionRunningDAO{

    private static final Logger log = LoggerFactory.getLogger(TransactionRunningDAOImpl.class);

    @Override
    public List<TransactionRunning> TransactionRunninggetByCon(TransactionRunninggetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT " +
                        "OPS_TRANSACTION_RUNNING.*" +
                        "FROM OPS_TRANSACTION_RUNNING" +
                        " WHERE RUNNING_TABLE = '"+req.getRUNNING_TABLE()+"'" +
                        " AND RUNNING_OFFICE_CODE = '"+req.getRUNNING_OFFICE_CODE()+"'" +
                        " AND RUNNING_YEAR = TO_CHAR(SYSDATE, 'yy' ,'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')" +
                        " ORDER BY RUNNING_ID");


        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<TransactionRunning> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public TransactionRunning mapRow(ResultSet rs, int rowNum) throws SQLException {
                TransactionRunning item = new TransactionRunning();
                item.setRUNNING_ID(rs.getInt("RUNNING_ID"));
                item.setRUNNING_OFFICE_ID(rs.getInt("RUNNING_OFFICE_ID"));
                item.setRUNNING_NO(rs.getInt("RUNNING_NO"));
                item.setRUNNING_TABLE(rs.getString("RUNNING_TABLE"));
                item.setRUNNING_PREFIX(rs.getString("RUNNING_PREFIX"));
                item.setRUNNING_OFFICE_CODE(rs.getString("RUNNING_OFFICE_CODE"));
                item.setRUNNING_YEAR(rs.getString("RUNNING_YEAR"));
                item.setRUNNING_MONTH(rs.getString("RUNNING_MONTH"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public Boolean TransactionRunninginsAll(TransactionRunning req) {

        CompareinsAllResponse res = new CompareinsAllResponse();

        try {

            String RUNNING_ID = getSequences("SELECT OPS_TRANSACTION_RUNNING_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_TRANSACTION_RUNNING ( " +
                            "RUNNING_ID," +
                            "RUNNING_OFFICE_ID," +
                            "RUNNING_NO," +
                            "RUNNING_TABLE," +
                            "RUNNING_PREFIX," +
                            "RUNNING_OFFICE_CODE," +
                            "RUNNING_YEAR," +
                            "RUNNING_MONTH " +
                            " ) values (" +
                            "'"+RUNNING_ID+"'," +
                            "'"+req.getRUNNING_OFFICE_ID()+"'," +
                            "'1'," +
                            "'"+req.getRUNNING_TABLE()+"'," +
                            "'"+req.getRUNNING_PREFIX()+"'," +
                            "'"+req.getRUNNING_OFFICE_CODE()+"'," +
                            " TO_CHAR(SYSDATE, 'yy' ,'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') ," +
                            " TO_CHAR(SYSDATE, 'mm' ,'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') " +
                            " )");

            log.info("[SQL] : "+sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean TransactionRunningupdByCon(TransactionRunningupdByConReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_TRANSACTION_RUNNING SET RUNNING_NO = (select RUNNING_NO + 1  from OPS_TRANSACTION_RUNNING where RUNNING_ID = '"+req.getRUNNING_ID()+"') WHERE RUNNING_ID = '"+req.getRUNNING_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        return true;
    }

    @Override
    public TransactionRunning getTransactionRunninggetByCon(TransactionRunninggetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT " +
                        "OPS_TRANSACTION_RUNNING.*" +
                        "FROM OPS_TRANSACTION_RUNNING" +
                        " WHERE RUNNING_TABLE = '"+req.getRUNNING_TABLE()+"'" +
                        " AND RUNNING_OFFICE_CODE = '"+req.getRUNNING_OFFICE_CODE()+"'" +
                        " AND RUNNING_YEAR = TO_CHAR(SYSDATE, 'yy' ,'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')" +
                        " ORDER BY RUNNING_ID");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<TransactionRunning>() {

            public TransactionRunning extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    TransactionRunning item = new TransactionRunning();
                    item.setRUNNING_ID(rs.getInt("RUNNING_ID"));
                    item.setRUNNING_OFFICE_ID(rs.getInt("RUNNING_OFFICE_ID"));
                    item.setRUNNING_NO(rs.getInt("RUNNING_NO"));
                    item.setRUNNING_TABLE(rs.getString("RUNNING_TABLE"));
                    item.setRUNNING_PREFIX(rs.getString("RUNNING_PREFIX"));
                    item.setRUNNING_OFFICE_CODE(rs.getString("RUNNING_OFFICE_CODE"));
                    item.setRUNNING_YEAR(rs.getString("RUNNING_YEAR"));
                    item.setRUNNING_MONTH(rs.getString("RUNNING_MONTH"));

                    return item;
                }

                return null;
            }
        });
    }
}
