package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductUnit;
import com.xcs.phase2.request.master.MasProductUnitgetByConAdvReq;
import com.xcs.phase2.request.master.MasProductUnitgetByConReq;
import com.xcs.phase2.request.master.MasProductUnitgetByKeywordReq;
import com.xcs.phase2.request.master.MasProductUnitupdDeleteReq;
import com.xcs.phase2.response.master.MasProductUnitinsAllResponse;
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
public class MasProductUnitDAOImpl extends MasterExt implements MasProductUnitDAO {

    private static final Logger log = LoggerFactory.getLogger(MasProductUnitDAOImpl.class);

    @Override
    public List<MasProductUnit> MasProductUnitgetByKeyword(MasProductUnitgetByKeywordReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT *" +
                        "    FROM MAS_PRODUCT_UNIT" +
                        "    WHERE IS_ACTIVE = 1" +
                        "    AND" +
                        "    (  " +
                        "        lower(UNIT_NAME_TH) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "        OR lower(UNIT_NAME_EN) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "        OR lower(UNIT_SHORT_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "    ) ORDER BY UNIT_ID asc" );

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductUnit> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductUnit mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductUnit item = new MasProductUnit();
                item.setUNIT_ID(rs.getInt("UNIT_ID"));
                item.setUNIT_NAME_TH(rs.getString("UNIT_NAME_TH"));
                item.setUNIT_NAME_EN(rs.getString("UNIT_NAME_EN"));
                item.setUNIT_SHORT_NAME(rs.getString("UNIT_SHORT_NAME"));
                item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                item.setCREATE_USER_ACCOUNT_ID(rs.getInt("CREATE_USER_ACCOUNT_ID"));
                item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                item.setEFEXPIRE_DATE(rs.getString("EFEXPIRE_DATE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setUNIT_CODE(rs.getString("UNIT_CODE"));
                return item;
            }
        });
        return dataList;
    }

    @Override
    public List<MasProductUnit> MasProductUnitgetByConAdv(MasProductUnitgetByConAdvReq req) {
        return null;
    }

    @Override
    public MasProductUnit MasProductUnitgetByCon(MasProductUnitgetByConReq req) {
        return null;
    }

    @Override
    public MasProductUnitinsAllResponse MasProductUnitinsAll(MasProductUnit req) {
        return null;
    }

    @Override
    public Boolean MasProductUnitupdAll(MasProductUnit req) {
        return null;
    }

    @Override
    public Boolean MasProductUnitupdDelete(MasProductUnitupdDeleteReq req) {
        return null;
    }
}

