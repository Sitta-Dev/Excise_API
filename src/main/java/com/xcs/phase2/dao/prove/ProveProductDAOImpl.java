package com.xcs.phase2.dao.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.prove.ProveProduct;
import com.xcs.phase2.request.prove.ProveProductgetByProductIdReq;
import com.xcs.phase2.request.prove.ProveProductgetByProveIdReq;
import com.xcs.phase2.request.prove.ProveProductupdDeleteReq;
import com.xcs.phase2.response.prove.ProveProductinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ProveProductDAOImpl extends ProveExt implements ProveProductDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveProductDAOImpl.class);

    @Override
    public ProveProduct ProveProductgetByProductId(ProveProductgetByProductIdReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "PRODUCT_ID," +
                        "PROVE_ID," +
                        "SCIENCE_ID," +
                        "PRODUCT_MAPPING_ID," +
                        "PRODUCT_INDICTMENT_ID," +
                        "PRODUCT_MAPPING_REF_ID," +
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
                        "REMAIN_SIZES_UNIT_ID," +
                        "REMAIN_QUATITY_UNIT_ID," +
                        "REMAIN_VOLUMN_UNIT_ID," +
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
                        "REMAIN_SIZES," +
                        "REMAIN_SIZES_UNIT," +
                        "REMAIN_QUANTITY," +
                        "REMAIN_QUANTITY_UNIT," +
                        "REMAIN_VOLUMN," +
                        "REMAIN_VOLUMN_UNIT," +
                        "REMARK," +
                        "REMAIN_REMARK," +
                        "PRODUCT_RESULT," +
                        "SCIENCE_RESULT_DESC," +
                        "VAT," +
                        "IS_DOMESTIC," +
                        "IS_ILLEGAL," +
                        "IS_SCIENCE," +
                        "IS_PROVE," +
                        "IS_ACTIVE" +
                        " from OPS_PROVE_PRODUCT  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and PRODUCT_ID = '" + req.getPRODUCT_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<ProveProduct>() {

            public ProveProduct extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    ProveProduct item = new ProveProduct();
                    item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                    item.setPROVE_ID(rs.getInt("PROVE_ID"));
                    item.setSCIENCE_ID(rs.getInt("SCIENCE_ID"));
                    item.setPRODUCT_MAPPING_ID(rs.getInt("PRODUCT_MAPPING_ID"));
                    item.setPRODUCT_INDICTMENT_ID(rs.getInt("PRODUCT_INDICTMENT_ID"));
                    item.setPRODUCT_MAPPING_REF_ID(rs.getInt("PRODUCT_MAPPING_REF_ID"));
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
                    item.setREMAIN_SIZES_UNIT_ID(rs.getInt("REMAIN_SIZES_UNIT_ID"));
                    item.setREMAIN_QUATITY_UNIT_ID(rs.getInt("REMAIN_QUATITY_UNIT_ID"));
                    item.setREMAIN_VOLUMN_UNIT_ID(rs.getInt("REMAIN_VOLUMN_UNIT_ID"));
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
                    item.setREMAIN_SIZES(rs.getFloat("REMAIN_SIZES"));
                    item.setREMAIN_SIZES_UNIT(rs.getString("REMAIN_SIZES_UNIT"));
                    item.setREMAIN_QUANTITY(rs.getFloat("REMAIN_QUANTITY"));
                    item.setREMAIN_QUANTITY_UNIT(rs.getString("REMAIN_QUANTITY_UNIT"));
                    item.setREMAIN_VOLUMN(rs.getFloat("REMAIN_VOLUMN"));
                    item.setREMAIN_VOLUMN_UNIT(rs.getString("REMAIN_VOLUMN_UNIT"));
                    item.setREMARK(rs.getString("REMARK"));
                    item.setREMAIN_REMARK(rs.getString("REMAIN_REMARK"));
                    item.setPRODUCT_RESULT(rs.getString("PRODUCT_RESULT"));
                    item.setSCIENCE_RESULT_DESC(rs.getString("SCIENCE_RESULT_DESC"));
                    item.setVAT(rs.getFloat("VAT"));
                    item.setIS_DOMESTIC(rs.getInt("IS_DOMESTIC"));
                    item.setIS_ILLEGAL(rs.getInt("IS_ILLEGAL"));
                    item.setIS_SCIENCE(rs.getInt("IS_SCIENCE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setIS_PROVE(rs.getInt("IS_PROVE"));
                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public List<ProveProduct> ProveProductgetByProveId(ProveProductgetByProveIdReq req) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "PRODUCT_ID," +
                        "PROVE_ID," +
                        "SCIENCE_ID," +
                        "PRODUCT_MAPPING_ID," +
                        "PRODUCT_INDICTMENT_ID," +
                        "PRODUCT_MAPPING_REF_ID," +
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
                        "REMAIN_SIZES_UNIT_ID," +
                        "REMAIN_QUATITY_UNIT_ID," +
                        "REMAIN_VOLUMN_UNIT_ID," +
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
                        "REMAIN_SIZES," +
                        "REMAIN_SIZES_UNIT," +
                        "REMAIN_QUANTITY," +
                        "REMAIN_QUANTITY_UNIT," +
                        "REMAIN_VOLUMN," +
                        "REMAIN_VOLUMN_UNIT," +
                        "REMARK," +
                        "REMAIN_REMARK," +
                        "PRODUCT_RESULT," +
                        "SCIENCE_RESULT_DESC," +
                        "VAT," +
                        "IS_DOMESTIC," +
                        "IS_ILLEGAL," +
                        "IS_SCIENCE," +
                        "IS_PROVE," +
                        "IS_ACTIVE" +
                        " from OPS_PROVE_PRODUCT  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and PROVE_ID = '" + req.getPROVE_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveProduct item = new ProveProduct();
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setSCIENCE_ID(rs.getInt("SCIENCE_ID"));
                item.setPRODUCT_MAPPING_ID(rs.getInt("PRODUCT_MAPPING_ID"));
                item.setPRODUCT_INDICTMENT_ID(rs.getInt("PRODUCT_INDICTMENT_ID"));
                item.setPRODUCT_MAPPING_REF_ID(rs.getInt("PRODUCT_MAPPING_REF_ID"));
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
                item.setREMAIN_SIZES_UNIT_ID(rs.getInt("REMAIN_SIZES_UNIT_ID"));
                item.setREMAIN_QUATITY_UNIT_ID(rs.getInt("REMAIN_QUATITY_UNIT_ID"));
                item.setREMAIN_VOLUMN_UNIT_ID(rs.getInt("REMAIN_VOLUMN_UNIT_ID"));
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
                item.setREMAIN_SIZES(rs.getFloat("REMAIN_SIZES"));
                item.setREMAIN_SIZES_UNIT(rs.getString("REMAIN_SIZES_UNIT"));
                item.setREMAIN_QUANTITY(rs.getFloat("REMAIN_QUANTITY"));
                item.setREMAIN_QUANTITY_UNIT(rs.getString("REMAIN_QUANTITY_UNIT"));
                item.setREMAIN_VOLUMN(rs.getFloat("REMAIN_VOLUMN"));
                item.setREMAIN_VOLUMN_UNIT(rs.getString("REMAIN_VOLUMN_UNIT"));
                item.setREMARK(rs.getString("REMARK"));
                item.setREMAIN_REMARK(rs.getString("REMAIN_REMARK"));
                item.setPRODUCT_RESULT(rs.getString("PRODUCT_RESULT"));
                item.setSCIENCE_RESULT_DESC(rs.getString("SCIENCE_RESULT_DESC"));
                item.setVAT(rs.getFloat("VAT"));
                item.setIS_DOMESTIC(rs.getInt("IS_DOMESTIC"));
                item.setIS_ILLEGAL(rs.getInt("IS_ILLEGAL"));
                item.setIS_SCIENCE(rs.getInt("IS_SCIENCE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setIS_PROVE(rs.getInt("IS_PROVE"));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public ProveProductinsAllResponse ProveProductinsAll(ProveProduct req) {

        ProveProductinsAllResponse res = new ProveProductinsAllResponse();

        try {

            String PRODUCT_ID = getSequences("SELECT OPS_PROVE_PRODUCT_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_PROVE_PRODUCT ( " +
                            "PRODUCT_ID," +
                            "PROVE_ID," +
                            "SCIENCE_ID," +
                            "PRODUCT_MAPPING_ID," +
                            "PRODUCT_INDICTMENT_ID," +
                            "PRODUCT_MAPPING_REF_ID," +
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
                            "REMAIN_SIZES_UNIT_ID," +
                            "REMAIN_QUATITY_UNIT_ID," +
                            "REMAIN_VOLUMN_UNIT_ID," +
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
                            "REMAIN_SIZES," +
                            "REMAIN_SIZES_UNIT," +
                            "REMAIN_QUANTITY," +
                            "REMAIN_QUANTITY_UNIT," +
                            "REMAIN_VOLUMN," +
                            "REMAIN_VOLUMN_UNIT," +
                            "REMARK," +
                            "REMAIN_REMARK," +
                            "PRODUCT_RESULT," +
                            "SCIENCE_RESULT_DESC," +
                            "VAT," +
                            "IS_DOMESTIC," +
                            "IS_ILLEGAL," +
                            "IS_SCIENCE," +
                            "IS_PROVE," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + PRODUCT_ID + "'," +
                            "'" + req.getPROVE_ID() + "'," +
                            "'" + req.getSCIENCE_ID() + "'," +
                            "'" + req.getPRODUCT_MAPPING_ID() + "'," +
                            "'" + req.getPRODUCT_INDICTMENT_ID() + "'," +
                            "'" + req.getPRODUCT_MAPPING_REF_ID() + "'," +
                            "'" + req.getPRODUCT_CODE() + "'," +
                            "'" + req.getPRODUCT_REF_CODE() + "'," +
                            "'" + req.getPRODUCT_GROUP_ID() + "'," +
                            "'" + req.getPRODUCT_CATEGORY_ID() + "'," +
                            "'" + req.getPRODUCT_TYPE_ID() + "'," +
                            "'" + req.getPRODUCT_SUBTYPE_ID() + "'," +
                            "'" + req.getPRODUCT_SUBSETTYPE_ID() + "'," +
                            "'" + req.getPRODUCT_BRAND_ID() + "'," +
                            "'" + req.getPRODUCT_SUBBRAND_ID() + "'," +
                            "'" + req.getPRODUCT_MODEL_ID() + "'," +
                            "'" + req.getPRODUCT_TAXDETAIL_ID() + "'," +
                            "'" + req.getSIZES_UNIT_ID() + "'," +
                            "'" + req.getQUATITY_UNIT_ID() + "'," +
                            "'" + req.getVOLUMN_UNIT_ID() + "'," +
                            "'" + req.getREMAIN_SIZES_UNIT_ID() + "'," +
                            "'" + req.getREMAIN_QUATITY_UNIT_ID() + "'," +
                            "'" + req.getREMAIN_VOLUMN_UNIT_ID() + "'," +
                            "'" + req.getPRODUCT_GROUP_CODE() + "'," +
                            "'" + req.getPRODUCT_GROUP_NAME() + "'," +
                            "'" + req.getPRODUCT_CATEGORY_CODE() + "'," +
                            "'" + req.getPRODUCT_CATEGORY_NAME() + "'," +
                            "'" + req.getPRODUCT_TYPE_CODE() + "'," +
                            "'" + req.getPRODUCT_TYPE_NAME() + "'," +
                            "'" + req.getPRODUCT_SUBTYPE_CODE() + "'," +
                            "'" + req.getPRODUCT_SUBTYPE_NAME() + "'," +
                            "'" + req.getPRODUCT_SUBSETTYPE_CODE() + "'," +
                            "'" + req.getPRODUCT_SUBSETTYPE_NAME() + "'," +
                            "'" + req.getPRODUCT_BRAND_CODE() + "'," +
                            "'" + req.getPRODUCT_BRAND_NAME_TH() + "'," +
                            "'" + req.getPRODUCT_BRAND_NAME_EN() + "'," +
                            "'" + req.getPRODUCT_SUBBRAND_CODE() + "'," +
                            "'" + req.getPRODUCT_SUBBRAND_NAME_TH() + "'," +
                            "'" + req.getPRODUCT_SUBBRAND_NAME_EN() + "'," +
                            "'" + req.getPRODUCT_MODEL_CODE() + "'," +
                            "'" + req.getPRODUCT_MODEL_NAME_TH() + "'," +
                            "'" + req.getPRODUCT_MODEL_NAME_EN() + "'," +
                            "'" + req.getIS_TAX_VALUE() + "'," +
                            "'" + req.getTAX_VALUE() + "'," +
                            "'" + req.getIS_TAX_VOLUMN() + "'," +
                            "'" + req.getTAX_VOLUMN() + "'," +
                            "'" + req.getTAX_VOLUMN_UNIT() + "'," +
                            "'" + req.getLICENSE_PLATE() + "'," +
                            "'" + req.getENGINE_NO() + "'," +
                            "'" + req.getCHASSIS_NO() + "'," +
                            "'" + req.getPRODUCT_DESC() + "'," +
                            "'" + req.getSUGAR() + "'," +
                            "'" + req.getCO2() + "'," +
                            "'" + req.getDEGREE() + "'," +
                            "'" + req.getPRICE() + "'," +
                            "'" + req.getSIZES() + "'," +
                            "'" + req.getSIZES_UNIT() + "'," +
                            "'" + req.getQUANTITY() + "'," +
                            "'" + req.getQUANTITY_UNIT() + "'," +
                            "'" + req.getVOLUMN() + "'," +
                            "'" + req.getVOLUMN_UNIT() + "'," +
                            "'" + req.getREMAIN_SIZES() + "'," +
                            "'" + req.getREMAIN_SIZES_UNIT() + "'," +
                            "'" + req.getREMAIN_QUANTITY() + "'," +
                            "'" + req.getREMAIN_QUANTITY_UNIT() + "'," +
                            "'" + req.getREMAIN_VOLUMN() + "'," +
                            "'" + req.getREMAIN_VOLUMN_UNIT() + "'," +
                            "'" + req.getREMARK() + "'," +
                            "'" + req.getREMAIN_REMARK() + "'," +
                            "'" + req.getPRODUCT_RESULT() + "'," +
                            "'" + req.getSCIENCE_RESULT_DESC() + "'," +
                            "'" + req.getVAT() + "'," +
                            "'" + req.getIS_DOMESTIC() + "'," +
                            "'" + req.getIS_ILLEGAL() + "'," +
                            "'" + req.getIS_SCIENCE() + "'," +
                            "'" + req.getIS_PROVE() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setPRODUCT_ID(Integer.parseInt(PRODUCT_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setPRODUCT_ID(0);
            return res;
        }
    }

    @Override
    public Boolean ProveProductupdByCon(ProveProduct req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_PROVE_PRODUCT SET "
                        + "PROVE_ID=  '" + req.getPROVE_ID() + "', "
                        + "SCIENCE_ID=  '" + req.getSCIENCE_ID() + "', "
                        + "PRODUCT_MAPPING_ID=  '" + req.getPRODUCT_MAPPING_ID() + "', "
                        + "PRODUCT_INDICTMENT_ID=  '" + req.getPRODUCT_INDICTMENT_ID() + "', "
                        + "PRODUCT_MAPPING_REF_ID=  '" + req.getPRODUCT_MAPPING_REF_ID() + "', "
                        + "PRODUCT_CODE=  '" + req.getPRODUCT_CODE() + "', "
                        + "PRODUCT_REF_CODE=  '" + req.getPRODUCT_REF_CODE() + "', "
                        + "PRODUCT_GROUP_ID=  '" + req.getPRODUCT_GROUP_ID() + "', "
                        + "PRODUCT_CATEGORY_ID=  '" + req.getPRODUCT_CATEGORY_ID() + "', "
                        + "PRODUCT_TYPE_ID=  '" + req.getPRODUCT_TYPE_ID() + "', "
                        + "PRODUCT_SUBTYPE_ID=  '" + req.getPRODUCT_SUBTYPE_ID() + "', "
                        + "PRODUCT_SUBSETTYPE_ID=  '" + req.getPRODUCT_SUBSETTYPE_ID() + "', "
                        + "PRODUCT_BRAND_ID=  '" + req.getPRODUCT_BRAND_ID() + "', "
                        + "PRODUCT_SUBBRAND_ID=  '" + req.getPRODUCT_SUBBRAND_ID() + "', "
                        + "PRODUCT_MODEL_ID=  '" + req.getPRODUCT_MODEL_ID() + "', "
                        + "PRODUCT_TAXDETAIL_ID=  '" + req.getPRODUCT_TAXDETAIL_ID() + "', "
                        + "SIZES_UNIT_ID=  '" + req.getSIZES_UNIT_ID() + "', "
                        + "QUATITY_UNIT_ID=  '" + req.getQUATITY_UNIT_ID() + "', "
                        + "VOLUMN_UNIT_ID=  '" + req.getVOLUMN_UNIT_ID() + "', "
                        + "REMAIN_SIZES_UNIT_ID=  '" + req.getREMAIN_SIZES_UNIT_ID() + "', "
                        + "REMAIN_QUATITY_UNIT_ID=  '" + req.getREMAIN_QUATITY_UNIT_ID() + "', "
                        + "REMAIN_VOLUMN_UNIT_ID=  '" + req.getREMAIN_VOLUMN_UNIT_ID() + "', "
                        + "PRODUCT_GROUP_CODE=  '" + req.getPRODUCT_GROUP_CODE() + "', "
                        + "PRODUCT_GROUP_NAME=  '" + req.getPRODUCT_GROUP_NAME() + "', "
                        + "PRODUCT_CATEGORY_CODE=  '" + req.getPRODUCT_CATEGORY_CODE() + "', "
                        + "PRODUCT_CATEGORY_NAME=  '" + req.getPRODUCT_CATEGORY_NAME() + "', "
                        + "PRODUCT_TYPE_CODE=  '" + req.getPRODUCT_TYPE_CODE() + "', "
                        + "PRODUCT_TYPE_NAME=  '" + req.getPRODUCT_TYPE_NAME() + "', "
                        + "PRODUCT_SUBTYPE_CODE=  '" + req.getPRODUCT_SUBTYPE_CODE() + "', "
                        + "PRODUCT_SUBTYPE_NAME=  '" + req.getPRODUCT_SUBTYPE_NAME() + "', "
                        + "PRODUCT_SUBSETTYPE_CODE=  '" + req.getPRODUCT_SUBSETTYPE_CODE() + "', "
                        + "PRODUCT_SUBSETTYPE_NAME=  '" + req.getPRODUCT_SUBSETTYPE_NAME() + "', "
                        + "PRODUCT_BRAND_CODE=  '" + req.getPRODUCT_BRAND_CODE() + "', "
                        + "PRODUCT_BRAND_NAME_TH=  '" + req.getPRODUCT_BRAND_NAME_TH() + "', "
                        + "PRODUCT_BRAND_NAME_EN=  '" + req.getPRODUCT_BRAND_NAME_EN() + "', "
                        + "PRODUCT_SUBBRAND_CODE=  '" + req.getPRODUCT_SUBBRAND_CODE() + "', "
                        + "PRODUCT_SUBBRAND_NAME_TH=  '" + req.getPRODUCT_SUBBRAND_NAME_TH() + "', "
                        + "PRODUCT_SUBBRAND_NAME_EN=  '" + req.getPRODUCT_SUBBRAND_NAME_EN() + "', "
                        + "PRODUCT_MODEL_CODE=  '" + req.getPRODUCT_MODEL_CODE() + "', "
                        + "PRODUCT_MODEL_NAME_TH=  '" + req.getPRODUCT_MODEL_NAME_TH() + "', "
                        + "PRODUCT_MODEL_NAME_EN=  '" + req.getPRODUCT_MODEL_NAME_EN() + "', "
                        + "IS_TAX_VALUE=  '" + req.getIS_TAX_VALUE() + "', "
                        + "TAX_VALUE=  '" + req.getTAX_VALUE() + "', "
                        + "IS_TAX_VOLUMN=  '" + req.getIS_TAX_VOLUMN() + "', "
                        + "TAX_VOLUMN=  '" + req.getTAX_VOLUMN() + "', "
                        + "TAX_VOLUMN_UNIT=  '" + req.getTAX_VOLUMN_UNIT() + "', "
                        + "LICENSE_PLATE=  '" + req.getLICENSE_PLATE() + "', "
                        + "ENGINE_NO=  '" + req.getENGINE_NO() + "', "
                        + "CHASSIS_NO=  '" + req.getCHASSIS_NO() + "', "
                        + "PRODUCT_DESC=  '" + req.getPRODUCT_DESC() + "', "
                        + "SUGAR=  '" + req.getSUGAR() + "', "
                        + "CO2=  '" + req.getCO2() + "', "
                        + "DEGREE=  '" + req.getDEGREE() + "', "
                        + "PRICE=  '" + req.getPRICE() + "', "
                        + "SIZES=  '" + req.getSIZES() + "', "
                        + "SIZES_UNIT=  '" + req.getSIZES_UNIT() + "', "
                        + "QUANTITY=  '" + req.getQUANTITY() + "', "
                        + "QUANTITY_UNIT=  '" + req.getQUANTITY_UNIT() + "', "
                        + "VOLUMN=  '" + req.getVOLUMN() + "', "
                        + "VOLUMN_UNIT=  '" + req.getVOLUMN_UNIT() + "', "
                        + "REMAIN_SIZES=  '" + req.getREMAIN_SIZES() + "', "
                        + "REMAIN_SIZES_UNIT=  '" + req.getREMAIN_SIZES_UNIT() + "', "
                        + "REMAIN_QUANTITY=  '" + req.getREMAIN_QUANTITY() + "', "
                        + "REMAIN_QUANTITY_UNIT=  '" + req.getREMAIN_QUANTITY_UNIT() + "', "
                        + "REMAIN_VOLUMN=  '" + req.getREMAIN_VOLUMN() + "', "
                        + "REMAIN_VOLUMN_UNIT=  '" + req.getREMAIN_VOLUMN_UNIT() + "', "
                        + "REMARK=  '" + req.getREMARK() + "', "
                        + "REMAIN_REMARK=  '" + req.getREMAIN_REMARK() + "', "
                        + "PRODUCT_RESULT=  '" + req.getPRODUCT_RESULT() + "', "
                        + "SCIENCE_RESULT_DESC=  '" + req.getSCIENCE_RESULT_DESC() + "', "
                        + "VAT=  '" + req.getVAT() + "', "
                        + "IS_DOMESTIC=  '" + req.getIS_DOMESTIC() + "', "
                        + "IS_ILLEGAL=  '" + req.getIS_ILLEGAL() + "', "
                        + "IS_SCIENCE=  '" + req.getIS_SCIENCE() + "', "
                        + "IS_PROVE=  '" + req.getIS_PROVE() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE PRODUCT_ID = '" + req.getPRODUCT_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        return true;
    }

    @Override
    public Boolean ProveProductupdDelete(ProveProductupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_PROVE_PRODUCT SET IS_ACTIVE = '0' WHERE PRODUCT_ID = '" + req.getPRODUCT_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        return true;
    }
}
