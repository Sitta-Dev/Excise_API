package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.revenue.RevenueCompareDetail;
import com.xcs.phase2.request.revenue.RevenueCompareDetailReceiptupdByConReq;
import com.xcs.phase2.request.revenue.RevenueCompareDetailReceiptupdDeleteReq;
import com.xcs.phase2.request.revenue.RevenueComparegetByCompareReceiptIDReq;
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
public class RevenueCompareDetailDAOImpl extends RevenueExt implements RevenueCompareDetailDAO {

    private static final Logger log = LoggerFactory.getLogger(RevenueCompareDetailDAOImpl.class);

    @Override
    public List<RevenueCompareDetail> RevenueComparegetByCompareReceiptID(RevenueComparegetByCompareReceiptIDReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("with temp as(" +
                        "  select      " +
                        "  OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID," +
                        "  OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID," +
                        "  OPS_COMPARE_DETAIL.RECEIPT_OFFICE_ID," +
                        "  OPS_COMPARE_DETAIL.PAYMENT_FINE," +
                        "  OPS_COMPARE_DETAIL.TREASURY_MONEY," +
                        "  OPS_COMPARE_DETAIL.BRIBE_MONEY," +
                        "  OPS_COMPARE_DETAIL.REWARD_MONEY," +
                        "to_char(OPS_COMPARE_DETAIL.PAYMENT_DATE,'"+ Pattern.FORMAT_DATETIME+"') as PAYMENT_DATE," +
                        "  OPS_COMPARE_DETAIL.RECEIPT_TYPE," +
                        "  OPS_COMPARE_DETAIL.RECEIPT_BOOK_NO," +
                        "  OPS_COMPARE_DETAIL.RECEIPT_NO," +
                        "  OPS_COMPARE_DETAIL.COMPARE_TYPE," +
                        "  OPS_COMPARE_DETAIL.IS_PAYMENT," +
                        "  OPS_COMPARE_DETAIL.IS_REVENUE," +
                        "  OPS_COMPARE_DETAIL.IS_AUTHORITY," +
                        "  OPS_COMPARE_DETAIL.IS_ACTIVE" +
                        "  from OPS_COMPARE" +
                        "  left join OPS_COMPARE_STAFF on OPS_COMPARE.COMPARE_ID = OPS_COMPARE_STAFF.COMPARE_ID" +
                        "  left join OPS_COMPARE_MAPPING on OPS_COMPARE.COMPARE_ID = OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "  left join OPS_COMPARE_DETAIL on OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID = OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "  left join OPS_ARREST_INDICTMENT_DETAIL on OPS_COMPARE_MAPPING.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID" +
                        "  left join OPS_ARREST_LAWBREAKER on OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
                        "  where OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"'" +
                        ")select DISTINCT * from temp");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCompareDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueCompareDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueCompareDetail item = new RevenueCompareDetail();
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setCOMPARE_MAPPING_ID(rs.getInt("COMPARE_MAPPING_ID"));
                item.setRECEIPT_OFFICE_ID(rs.getInt("RECEIPT_OFFICE_ID"));
                item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setRECEIPT_TYPE(rs.getInt("RECEIPT_TYPE"));
                item.setRECEIPT_BOOK_NO(rs.getString("RECEIPT_BOOK_NO"));
                item.setRECEIPT_NO(rs.getInt("RECEIPT_NO"));
                item.setCOMPARE_TYPE(rs.getString("COMPARE_TYPE"));
                item.setIS_PAYMENT(rs.getString("IS_PAYMENT"));
                item.setIS_REVENUE(rs.getString("IS_REVENUE"));
                item.setIS_AUTHORITY(rs.getInt("IS_AUTHORITY"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public Boolean RevenueCompareDetailReceiptupdByCon(RevenueCompareDetailReceiptupdByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL SET IS_REVENUE = '1' WHERE COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
        return true;
    }

    @Override
    public Boolean RevenueCompareDetailReceiptupdDelete(RevenueCompareDetailReceiptupdDeleteReq req) {

        StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL SET IS_REVENUE = '0' WHERE COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
        return true;
    }
}
