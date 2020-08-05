package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductCategoryRDB;
import com.xcs.phase2.request.master.MasProductCategoryRDBgetByConReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class MasProductCategoryRDBDAOImpl extends MasterExt implements MasProductCategoryRDBDAO {

    private static final Logger log = LoggerFactory.getLogger(MasProductCategoryRDBDAOImpl.class);

    @Override
    public List<MasProductCategoryRDB> MasProductCategoryRDBgetByCon(MasProductCategoryRDBgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  select " +
                        "  MAS_PRODUCT_CATEGORY_GROUP_PRC.PRODUCT_CODE," +
                        "  MAS_PRODUCT_CATEGORY_GROUP_PRC.CATEGORY_GROUP_CODE," +
                        "  MAS_PRODUCT_CATEGORY_GROUP.CATEGORY_GROUP_DESC," +
                        "  MAS_PRODUCT_CATEGORY_GROUP_PRC.CATEGORY_CODE," +
                        "  MAS_PRODUCT_CATEGORY_RDB.CATEGORY_DESC" +
                        "  FROM MAS_PRODUCT_CATEGORY_GROUP_PRC" +
                        "  LEFT JOIN MAS_PRODUCT_CATEGORY_RDB on MAS_PRODUCT_CATEGORY_GROUP_PRC.CATEGORY_CODE = MAS_PRODUCT_CATEGORY_RDB.PRODUCT_CATEGORY" +
                        "  LEFT JOIN MAS_PRODUCT_CATEGORY_GROUP on MAS_PRODUCT_CATEGORY_GROUP_PRC.CATEGORY_GROUP_CODE = MAS_PRODUCT_CATEGORY_GROUP.CATEGORY_GROUP" +
                        "  WHERE MAS_PRODUCT_CATEGORY_GROUP.IS_ACTIVE = 1" +
                        "  AND MAS_PRODUCT_CATEGORY_GROUP_PRC.IS_ACTIVE = 1" +
                        "  AND MAS_PRODUCT_CATEGORY_RDB.IS_ACTIVE = 1");

        if (!StringUtils.isEmpty(req.getGROUP_PRODUCT_CODE())) {
            sqlBuilder.append(" AND lower (MAS_PRODUCT_CATEGORY_GROUP.PRODUCT_CODE) LIKE LOWER(REPLACE('%" + req.getGROUP_PRODUCT_CODE() + "%',' ','')) ");
        }

        if (!StringUtils.isEmpty(req.getGROUP_PRC_PRODUCT_CODE())) {
            sqlBuilder.append(" AND lower (MAS_PRODUCT_CATEGORY_GROUP_PRC.PRODUCT_CODE) LIKE LOWER(REPLACE('%" + req.getGROUP_PRC_PRODUCT_CODE() + "%',' ','')) ");
        }

        if (!StringUtils.isEmpty(req.getRDB_PRODUCT_CODE())) {
            sqlBuilder.append(" AND lower (MAS_PRODUCT_CATEGORY_RDB.PRODUCT_CODE) LIKE LOWER(REPLACE('%" + req.getRDB_PRODUCT_CODE() + "%',' ','')) ");
        }

        if (!StringUtils.isEmpty(req.getCATEGORY_GROUP_CODE())) {
            sqlBuilder.append(" AND MAS_PRODUCT_CATEGORY_GROUP_PRC.CATEGORY_GROUP_CODE = '" + req.getCATEGORY_GROUP_CODE() + "' ");
        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductCategoryRDB> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductCategoryRDB mapRow(ResultSet rs, int rowNum) throws SQLException {

                MasProductCategoryRDB item = new MasProductCategoryRDB();

                item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
                item.setCATEGORY_GROUP_CODE(rs.getString("CATEGORY_GROUP_CODE"));
                item.setCATEGORY_GROUP_DESC(rs.getString("CATEGORY_GROUP_DESC"));
                item.setCATEGORY_CODE(rs.getString("CATEGORY_CODE"));
                item.setCATEGORY_DESC(rs.getString("CATEGORY_DESC"));

                return item;
            }
        });

        log.info("[dataList]  : " + dataList.size());
        return dataList;

    }
}
