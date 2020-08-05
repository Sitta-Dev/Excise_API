package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductSize;
import com.xcs.phase2.request.master.MasProductSizegetByConAdvReq;
import com.xcs.phase2.request.master.MasProductSizegetByConReq;
import com.xcs.phase2.request.master.MasProductSizegetByKeywordReq;
import com.xcs.phase2.request.master.MasProductSizeupdDeleteReq;
import com.xcs.phase2.response.master.MasProductSizeinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class MasProductSizeDAOImpl extends MasterExt implements MasProductSizeDAO{

    private static final Logger log = LoggerFactory.getLogger(MasProductSizeDAOImpl.class);

    @Override
    public List<MasProductSize> MasProductSizegetByKeyword(MasProductSizegetByKeywordReq req) {
        return null;
    }

    @Override
    public List<MasProductSize> MasProductSizegetByConAdv(MasProductSizegetByConAdvReq req) {
        return null;
    }

    @Override
    public MasProductSize MasProductSizegetByCon(MasProductSizegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select * from mas_product_size where size_id = "+req.getSIZE_ID());

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasProductSize>() {

            public MasProductSize extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasProductSize item = new MasProductSize();
                    item.setSIZE_ID(rs.getInt("SIZE_ID"));
                    item.setSIZE_DESC(rs.getString("SIZE_DESC"));
                    item.setSIZE_NAME_EN(rs.getString("SIZE_NAME_EN"));
                    item.setSIZE_SHORT_NAME(rs.getString("SIZE_SHORT_NAME"));
                    item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                    item.setCREATE_USER_ACCOUNT_ID(rs.getLong("CREATE_USER_ACCOUNT_ID"));
                    item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                    item.setEXPIRED_DATE(rs.getString("EXPIRED_DATE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                    item.setUNIT_CODE(rs.getString("UNIT_CODE"));
                    item.setSIZE_CODE(rs.getString("SIZE_CODE"));
                    item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public MasProductSizeinsAllResponse MasProductSizeinsAll(MasProductSize req) {
        return null;
    }

    @Override
    public Boolean MasProductSizeupdAll(MasProductSize req) {
        return null;
    }

    @Override
    public Boolean MasProductSizeupdDelete(MasProductSizeupdDeleteReq req) {
        return null;
    }
}
