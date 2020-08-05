package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductCategory;
import com.xcs.phase2.request.master.MasProductCategorygetByConReq;
import com.xcs.phase2.request.master.MasProductCategoryupdDeleteReq;
import com.xcs.phase2.response.master.MasProductCategoryinsAllResponse;
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
public class MasProductCategoryDAOImpl extends MasterExt implements MasProductCategoryDAO {

    private static final Logger log = LoggerFactory.getLogger(MasProductCategoryDAOImpl.class);

    @Override
    public List<MasProductCategory> MasProductCategorygetByCon(MasProductCategorygetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT * " +
                        "    FROM MAS_PRODUCT_CATEGORY WHERE  IS_ACTIVE = 1 " +
                        "    AND (  LOWER(PRODUCT_CATEGORY_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%') ) ");

        if(!StringUtils.isEmpty(req.getPRODUCT_CATEGORY_CODE())){
            sqlBuilder.append(" AND PRODUCT_CATEGORY_CODE = "+req.getPRODUCT_CATEGORY_CODE()+" ");
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_GROUP_ID())){
            sqlBuilder.append(" AND PRODUCT_GROUP_ID = "+req.getPRODUCT_GROUP_ID()+" ");
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_CATEGORY_ID())){
            sqlBuilder.append(" AND PRODUCT_CATEGORY_ID = "+req.getPRODUCT_CATEGORY_ID()+" ");
        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductCategory> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductCategory item = new MasProductCategory();
                item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));
                item.setIS_TAX(rs.getInt("IS_TAX"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                item.setCREATE_USER_ACCOUNT_ID(rs.getLong("CREATE_USER_ACCOUNT_ID"));
                item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                item.setUPDATE_USER_ACCOUNT_ID(rs.getLong("UPDATE_USER_ACCOUNT_ID"));

                return item;
            }
        });

        return dataList;

    }

    @Override
    public MasProductCategoryinsAllResponse MasProductCategoryinsAll(MasProductCategory req) {
        return null;
    }

    @Override
    public Boolean MasProductCategoryupdAll(MasProductCategory req) {
        return null;
    }

    @Override
    public Boolean MasProductCategoryupdDelete(MasProductCategoryupdDeleteReq req) {
        return null;
    }
}
