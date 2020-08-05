package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductGROUPCategoryForLiquor;
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
public class MasProductGROUPCategoryForLiquorDAOImpl extends MasterExt implements MasProductGROUPCategoryForLiquorDAO {

    private static final Logger log = LoggerFactory.getLogger(MasProductGROUPCategoryForLiquorDAOImpl.class);


    @Override
    public List<MasProductGROUPCategoryForLiquor> MasProductGROUPCategoryForLiquorgetByCon() {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT " +
                        "  MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID," +
                        "  MAS_PRODUCT_GROUP.PRODUCT_GROUP_CODE," +
                        "  MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME," +
                        "  MAS_PRODUCT_CATEGORY.PRODUCT_CATEGORY_ID," +
                        "  MAS_PRODUCT_CATEGORY.PRODUCT_CATEGORY_CODE," +
                        "  MAS_PRODUCT_CATEGORY.PRODUCT_CATEGORY_NAME" +
                        "  FROM MAS_PRODUCT_GROUP" +
                        "  LEFT JOIN MAS_PRODUCT_CATEGORY on MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID = MAS_PRODUCT_CATEGORY.PRODUCT_GROUP_ID" +
                        "  where MAS_PRODUCT_GROUP.IS_ACTIVE = 1" +
                        "  AND MAS_PRODUCT_CATEGORY.IS_ACTIVE = 1" +
                        "  ORDER BY PRODUCT_GROUP_CODE , PRODUCT_CATEGORY_CODE");



        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductGROUPCategoryForLiquor> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductGROUPCategoryForLiquor mapRow(ResultSet rs, int rowNum) throws SQLException {

                MasProductGROUPCategoryForLiquor item = new MasProductGROUPCategoryForLiquor();
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
                item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
                item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));

                return item;
            }
        });

        return dataList;

    }
}
