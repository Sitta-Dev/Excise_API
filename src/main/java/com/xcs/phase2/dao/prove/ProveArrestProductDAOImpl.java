package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.ProveArrestProduct;
import com.xcs.phase2.request.prove.ProveArrestProductgetByConReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@Transactional
public class ProveArrestProductDAOImpl extends ProveExt implements ProveArrestProductDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveArrestProductDAOImpl.class);

    @Override
    public ProveArrestProduct ProveArrestProductgetByCon(ProveArrestProductgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "PRODUCT_ID," +
                        "ARREST_ID," +
                        "PRODUCT_MAPPING_ID," +
                        "PRODUCT_CODE," +
                        "PRODUCT_REF_CODE," +
                        "PRODUCT_GROUP_ID," +
                        "PRODUCT_CATEGORY_ID," +
                        "PRODUCT_TYPE_ID," +
                        "PRODUCT_SUBTYPE_ID," +
                        "PRODUCT_SUBSETTYPE_ID," +
                        "PRODUCT_BRAND_ID," +
                        "PRODUCT_SUBBRAND_ID," +
                        "PRODUCT_MODEL_ID," +
                        "PRODUCT_TAXDETAIL_ID," +
                        "SIZES_UNIT_ID," +
                        "QUATITY_UNIT_ID," +
                        "VOLUMN_UNIT_ID," +
                        "PRODUCT_GROUP_CODE," +
                        "PRODUCT_GROUP_NAME," +
                        "PRODUCT_CATEGORY_CODE," +
                        "PRODUCT_CATEGORY_NAME," +
                        "PRODUCT_TYPE_CODE," +
                        "PRODUCT_TYPE_NAME," +
                        "PRODUCT_SUBTYPE_CODE," +
                        "PRODUCT_SUBTYPE_NAME," +
                        "PRODUCT_SUBSETTYPE_CODE," +
                        "PRODUCT_SUBSETTYPE_NAME," +
                        "PRODUCT_BRAND_CODE," +
                        "PRODUCT_BRAND_NAME_TH," +
                        "PRODUCT_BRAND_NAME_EN," +
                        "PRODUCT_SUBBRAND_CODE," +
                        "PRODUCT_SUBBRAND_NAME_TH," +
                        "PRODUCT_SUBBRAND_NAME_EN," +
                        "PRODUCT_MODEL_CODE," +
                        "PRODUCT_MODEL_NAME_TH," +
                        "PRODUCT_MODEL_NAME_EN," +
                        "IS_TAX_VALUE," +
                        "TAX_VALUE," +
                        "IS_TAX_VOLUMN," +
                        "TAX_VOLUMN," +
                        "TAX_VOLUMN_UNIT," +
                        "LICENSE_PLATE," +
                        "ENGINE_NO," +
                        "CHASSIS_NO," +
                        "PRODUCT_DESC," +
                        "SUGAR," +
                        "CO2," +
                        "DEGREE," +
                        "PRICE," +
                        "SIZES," +
                        "SIZES_UNIT," +
                        "QUANTITY," +
                        "QUANTITY_UNIT," +
                        "VOLUMN," +
                        "VOLUMN_UNIT," +
                        "REMARK," +
                        "IS_DOMESTIC," +
                        "IS_ILLEGAL," +
                        "IS_ACTIVE " +
                        " from OPS_ARREST_PRODUCT  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and PRODUCT_ID = '" + req.getPRODUCT_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<ProveArrestProduct>() {

            public ProveArrestProduct extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    ProveArrestProduct item = new ProveArrestProduct();
                    item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setPRODUCT_MAPPING_ID(rs.getInt("PRODUCT_MAPPING_ID"));
                    item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
                    item.setPRODUCT_REF_CODE(rs.getString("PRODUCT_REF_CODE"));
                    item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                    item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
                    item.setPRODUCT_TYPE_ID(rs.getInt("PRODUCT_TYPE_ID"));
                    item.setPRODUCT_SUBTYPE_ID(rs.getInt("PRODUCT_SUBTYPE_ID"));
                    item.setPRODUCT_SUBSETTYPE_ID(rs.getInt("PRODUCT_SUBSETTYPE_ID"));
                    item.setPRODUCT_BRAND_ID(rs.getInt("PRODUCT_BRAND_ID"));
                    item.setPRODUCT_SUBBRAND_ID(rs.getInt("PRODUCT_SUBBRAND_ID"));
                    item.setPRODUCT_MODEL_ID(rs.getInt("PRODUCT_MODEL_ID"));
                    item.setPRODUCT_TAXDETAIL_ID(rs.getInt("PRODUCT_TAXDETAIL_ID"));
                    item.setSIZES_UNIT_ID(rs.getInt("SIZES_UNIT_ID"));
                    item.setQUATITY_UNIT_ID(rs.getInt("QUATITY_UNIT_ID"));
                    item.setVOLUMN_UNIT_ID(rs.getInt("VOLUMN_UNIT_ID"));
                    item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                    item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
                    item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                    item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));
                    item.setPRODUCT_TYPE_CODE(rs.getString("PRODUCT_TYPE_CODE"));
                    item.setPRODUCT_TYPE_NAME(rs.getString("PRODUCT_TYPE_NAME"));
                    item.setPRODUCT_SUBTYPE_CODE(rs.getString("PRODUCT_SUBTYPE_CODE"));
                    item.setPRODUCT_SUBTYPE_NAME(rs.getString("PRODUCT_SUBTYPE_NAME"));
                    item.setPRODUCT_SUBSETTYPE_CODE(rs.getString("PRODUCT_SUBSETTYPE_CODE"));
                    item.setPRODUCT_SUBSETTYPE_NAME(rs.getString("PRODUCT_SUBSETTYPE_NAME"));
                    item.setPRODUCT_BRAND_CODE(rs.getString("PRODUCT_BRAND_CODE"));
                    item.setPRODUCT_BRAND_NAME_TH(rs.getString("PRODUCT_BRAND_NAME_TH"));
                    item.setPRODUCT_BRAND_NAME_EN(rs.getString("PRODUCT_BRAND_NAME_EN"));
                    item.setPRODUCT_SUBBRAND_CODE(rs.getString("PRODUCT_SUBBRAND_CODE"));
                    item.setPRODUCT_SUBBRAND_NAME_TH(rs.getString("PRODUCT_SUBBRAND_NAME_TH"));
                    item.setPRODUCT_SUBBRAND_NAME_EN(rs.getString("PRODUCT_SUBBRAND_NAME_EN"));
                    item.setPRODUCT_MODEL_CODE(rs.getString("PRODUCT_MODEL_CODE"));
                    item.setPRODUCT_MODEL_NAME_TH(rs.getString("PRODUCT_MODEL_NAME_TH"));
                    item.setPRODUCT_MODEL_NAME_EN(rs.getString("PRODUCT_MODEL_NAME_EN"));
                    item.setIS_TAX_VALUE(rs.getInt("IS_TAX_VALUE"));
                    item.setTAX_VALUE(rs.getFloat("TAX_VALUE"));
                    item.setIS_TAX_VOLUMN(rs.getInt("IS_TAX_VOLUMN"));
                    item.setTAX_VOLUMN(rs.getFloat("TAX_VOLUMN"));
                    item.setTAX_VOLUMN_UNIT(rs.getString("TAX_VOLUMN_UNIT"));
                    item.setLICENSE_PLATE(rs.getString("LICENSE_PLATE"));
                    item.setENGINE_NO(rs.getString("ENGINE_NO"));
                    item.setCHASSIS_NO(rs.getString("CHASSIS_NO"));
                    item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                    item.setSUGAR(rs.getFloat("SUGAR"));
                    item.setCO2(rs.getFloat("CO2"));
                    item.setDEGREE(rs.getFloat("DEGREE"));
                    item.setPRICE(rs.getFloat("PRICE"));
                    item.setSIZES(rs.getFloat("SIZES"));
                    item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
                    item.setQUANTITY(rs.getFloat("QUANTITY"));
                    item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
                    item.setVOLUMN(rs.getFloat("VOLUMN"));
                    item.setVOLUMN_UNIT(rs.getString("VOLUMN_UNIT"));
                    item.setREMARK(rs.getString("REMARK"));
                    item.setIS_DOMESTIC(rs.getInt("IS_DOMESTIC"));
                    item.setIS_ILLEGAL(rs.getInt("IS_ILLEGAL"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    return item;
                }

                return null;
            }
        });
    }
}
