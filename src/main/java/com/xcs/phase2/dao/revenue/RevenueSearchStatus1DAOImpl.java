package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.model.revenue.RevenueSearchStatus1;
import com.xcs.phase2.request.revenue.RevenueSearchStatus1Req;
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
public class RevenueSearchStatus1DAOImpl extends RevenueExt implements RevenueSearchStatus1DAO {

    private static final Logger log = LoggerFactory.getLogger(RevenueSearchStatus1DAOImpl.class);


    @Override
    public List<RevenueSearchStatus1> RevenueSearchStatus1(RevenueSearchStatus1Req req) {
        // TODO Auto-generated method stub


        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select DISTINCT" +
                        "    OPS_REVENUE.REVENUE_ID," +
                        "    OPS_REVENUE.REVENUE_CODE" +
                        "    from OPS_REVENUE" +
                        "    where " +
                        "    OPS_REVENUE.REVENUE_STATUS = 1" +
                        "    and OPS_REVENUE.IS_ACTIVE = 1" +
                        "    and OPS_REVENUE.DELIVERY_OFFICE_CODE = '"+req.getDELIVERY_OFFICE_CODE()+"'" +
                        "    order by OPS_REVENUE.REVENUE_ID asc");




        log.info("[SQL]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<RevenueSearchStatus1> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public RevenueSearchStatus1 mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueSearchStatus1 item = new RevenueSearchStatus1();
                item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                item.setREVENUE_CODE(rs.getString("REVENUE_CODE"));
                item.setRevenueDetail(getRevenueSearchStatus1Detail(rs.getInt("REVENUE_ID")));
                return item;
            }
        });

        return dataList;
    }
}
