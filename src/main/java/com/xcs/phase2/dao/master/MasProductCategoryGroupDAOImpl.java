package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductCategoryGroup;
import com.xcs.phase2.request.master.MasProductCategoryGroupgetByConReq;
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
public class MasProductCategoryGroupDAOImpl extends MasterExt implements MasProductCategoryGroupDAO {

    private static final Logger log = LoggerFactory.getLogger(MasProductCategoryGroupDAOImpl.class);

    @Override
    public List<MasProductCategoryGroup> MasProductCategoryGroupgetByCon(MasProductCategoryGroupgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT " +
                        "    PRODUCT_CODE," +
                        "    CATEGORY_GROUP," +
                        "    CATEGORY_GROUP_DESC," +
                        "    IS_ACTIVE" +
                        "    FROM MAS_PRODUCT_CATEGORY_GROUP WHERE  IS_ACTIVE = 1 ");

        if(!StringUtils.isEmpty(req.getPRODUCT_CODE())){
            sqlBuilder.append(" AND PRODUCT_CODE = "+req.getPRODUCT_CODE()+" ");
        }

        if(!StringUtils.isEmpty(req.getCATEGORY_GROUP())){
            sqlBuilder.append(" AND CATEGORY_GROUP = "+req.getCATEGORY_GROUP()+" ");
        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductCategoryGroup> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductCategoryGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductCategoryGroup item = new MasProductCategoryGroup();
                item.setCATEGORY_GROUP_DESC(rs.getString("CATEGORY_GROUP_DESC"));
                item.setCATEGORY_GROUP(rs.getString("CATEGORY_GROUP"));
                item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;

    }
}
