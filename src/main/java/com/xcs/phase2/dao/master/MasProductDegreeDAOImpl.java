package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductDegree;
import com.xcs.phase2.request.master.MasProductDegreegetByConReq;
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
public class MasProductDegreeDAOImpl extends MasterExt implements MasProductDegreeDAO{

    private static final Logger log = LoggerFactory.getLogger(MasProductDegreeDAOImpl.class);

    @Override
    public List<MasProductDegree> MasProductDegreegetByCon(MasProductDegreegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT " +
                        "  DEGREE_CODE," +
                        "  DEGREE" +
                        "  FROM MAS_PRODUCT_DEGREE" +
                        "  WHERE " +
                        "  LOWER(DEGREE) LIKE LOWER(REPLACE('%"+req.getDEGREE()+"%',' ',''))");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductDegree> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductDegree mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductDegree item = new MasProductDegree();
                item.setDEGREE_CODE(rs.getString("DEGREE_CODE"));
                item.setDEGREE(rs.getString("DEGREE"));


                return item;
            }
        });

        return dataList;

    }
}
