package com.xcs.phase2.dao.master;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.master.MasProductCategoryGroupPRC;
import com.xcs.phase2.request.master.MasProductCategoryGroupPRCgetByConReq;
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
public class MasProductCategoryGroupPRCDAOImpl extends MasterExt implements MasProductCategoryGroupPRCDAO{

    private static final Logger log = LoggerFactory.getLogger(MasProductCategoryGroupPRCDAOImpl.class);

    @Override
    public List<MasProductCategoryGroupPRC> MasProductCategoryGroupPRCgetByCon(MasProductCategoryGroupPRCgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  select " +
                        "  DUTY_CODE," +
                        "  TYPE_CODE," +
                        "  SUBTYPE_CODE," +
                        "  PRODUCT_CODE," +
                        "  PRODUCT_NAME," +
                        " to_char(BEGIN_DATE,'" + Pattern.FORMAT_DATE + "') as BEGIN_DATE," +
                        " to_char(END_DATE,'" + Pattern.FORMAT_DATE + "') as END_DATE," +
                        "  UPD_USERID," +
                        " to_char(UPD_DATE,'" + Pattern.FORMAT_DATE + "') as UPD_DATE," +
                        "  PRC_PARAM," +
                        "  LAW_DUTY_CODE," +
                        "  DUTY_FLAG," +
                        "  SETTYPE_CODE," +
                        "  PRODUCT_CODE_OLD," +
                        "  NAME_DUTY," +
                        "  CATAGORY_FLAG," +
                        "  IS_ACTIVE" +
                        "  from MAS_PRODUCT_PRC" +
                        "  where IS_ACTIVE = 1 and SUBSTR(DUTY_CODE, 1, 2) = '"+req.getDUTY_CODE()+"'");


        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductCategoryGroupPRC> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductCategoryGroupPRC mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductCategoryGroupPRC item = new MasProductCategoryGroupPRC();
                item.setDUTY_CODE(rs.getString("DUTY_CODE"));
                item.setTYPE_CODE(rs.getString("TYPE_CODE"));
                item.setSUBTYPE_CODE(rs.getString("SUBTYPE_CODE"));
                item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
                item.setPRODUCT_NAME(rs.getString("PRODUCT_NAME"));
                item.setBEGIN_DATE(rs.getString("BEGIN_DATE"));
                item.setEND_DATE(rs.getString("END_DATE"));
                item.setUPD_USERID(rs.getString("UPD_USERID"));
                item.setUPD_DATE(rs.getString("UPD_DATE"));
                item.setPRC_PARAM(rs.getString("PRC_PARAM"));
                item.setLAW_DUTY_CODE(rs.getString("LAW_DUTY_CODE"));
                item.setDUTY_FLAG(rs.getString("DUTY_FLAG"));
                item.setSETTYPE_CODE(rs.getString("SETTYPE_CODE"));
                item.setPRODUCT_CODE_OLD(rs.getString("PRODUCT_CODE_OLD"));
                item.setNAME_DUTY(rs.getString("NAME_DUTY"));
                item.setCATAGORY_FLAG(rs.getString("CATAGORY_FLAG"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;

    }
}
