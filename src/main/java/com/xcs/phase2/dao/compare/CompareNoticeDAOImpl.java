package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.CompareNotice;
import com.xcs.phase2.request.compare.CompareNoticegetByArrestIDReq;
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
public class CompareNoticeDAOImpl extends CompareExt implements CompareNoticeDAO{

    private static final Logger log = LoggerFactory.getLogger(CompareListDAOImpl.class);

    @Override
    public List<CompareNotice> CompareNoticegetByArrestID(CompareNoticegetByArrestIDReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT " +
                        "OPS_NOTICE.NOTICE_ID," +
                        "OPS_ARREST.ARREST_ID" +
                        " FROM OPS_NOTICE" +
                        " INNER JOIN OPS_ARREST ON OPS_NOTICE.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        " WHERE OPS_NOTICE.IS_ACTIVE = 1" +
                        " AND OPS_ARREST.IS_ACTIVE = 1" +
                        " AND OPS_NOTICE.ARREST_ID = '"+req.getARREST_ID()+"' ");


        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<CompareNotice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public CompareNotice mapRow(ResultSet rs, int rowNum) throws SQLException {
                CompareNotice item = new CompareNotice();
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                return item;
            }
        });

        return dataList;

    }
}
