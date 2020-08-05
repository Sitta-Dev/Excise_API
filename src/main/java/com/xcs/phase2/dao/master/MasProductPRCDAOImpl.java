package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductPRC;
import com.xcs.phase2.request.master.MasProductPRCgetByConReq;
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
public class MasProductPRCDAOImpl extends MasterExt implements MasProductPRCDAO{

    private static final Logger log = LoggerFactory.getLogger(MasProductPRCDAOImpl.class);

    @Override
    public List<MasProductPRC> MasProductPRCgetByCon(MasProductPRCgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT" +
                        "  MAS_PRODUCT_PRC.DUTY_CODE," +
                        "  MAS_PRODUCT_PRC.PRODUCT_CODE" +
                        "  FROM MAS_PRODUCT_PRC" +
                        "  WHERE MAS_PRODUCT_PRC.DUTY_CODE = '"+req.getDUTY_CODE()+"'" +
                        "  AND MAS_PRODUCT_PRC.PRODUCT_CODE = '"+req.getPRODUCT_CODE()+"'" +
                        "  GROUP BY MAS_PRODUCT_PRC.DUTY_CODE,MAS_PRODUCT_PRC.PRODUCT_CODE");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductPRC> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductPRC mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductPRC item = new MasProductPRC();
                item.setDUTY_CODE(rs.getString("DUTY_CODE"));
                item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));


                return item;
            }
        });

        return dataList;

    }
}
