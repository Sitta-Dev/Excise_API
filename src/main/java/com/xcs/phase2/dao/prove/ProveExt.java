package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.arrest.ArrestLawbreaker;
import com.xcs.phase2.model.arrest.ArrestProduct;
import com.xcs.phase2.model.arrest.ArrestProductMapping;
import com.xcs.phase2.model.prove.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProveExt {

    private static final Logger log = LoggerFactory.getLogger(ProveExt.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected String getSequences(String strSql) {
        log.info("[SQL]  : " + strSql);
        return getJdbcTemplate().queryForObject(strSql, String.class);
    }

    public List<ProveLawsuitStaff> getProveLawsuitStaff(int LAWSUIT_ID) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "    OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH || ' ' || OPS_LAWSUIT_STAFF.FIRST_NAME || ' ' || OPS_LAWSUIT_STAFF.LAST_NAME as LAWSUIT_STAFF_NAME," +
                        "    OPS_LAWSUIT_STAFF.OPREATION_POS_NAME as LAWSUIT_OPREATION_POS_NAME, " +
                        "    OPS_LAWSUIT_STAFF.OPERATION_OFFICE_NAME as LAWSUIT_OPERATION_OFFICE_NAME," +
                        "    OPS_LAWSUIT_STAFF.OPERATION_OFFICE_SHORT_NAME as LAWSUIT_OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_LAWSUIT_STAFF.MANAGEMENT_POS_NAME as LAWSUIT_MANAGEMENT_POS_NAME," +
                        "    OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_NAME as LAWSUIT_MANAGEMENT_OFFICE_NAME," +
                        "    OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_SHORT_NAME as LAWSUIT_MANAGEMENT_OFFICE_SHORT_NAME," +
                        "    OPS_LAWSUIT_STAFF.REPRESENT_POS_NAME as LAWSUIT_REPRESENT_POS_NAME," +
                        "    OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_NAME as LAWSUIT_REPRESENT_OFFICE_NAME," +
                        "    OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_SHORT_NAME as LAWSUIT_REPRESENT_OFFICE_SHORT_NAME," +
                        "    OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID" +
                        " from OPS_LAWSUIT_STAFF OPS_LAWSUIT_STAFF " +
                        " where (OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID = 16 or OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID = 21)" +
                        " and OPS_LAWSUIT_STAFF.LAWSUIT_ID = '"+LAWSUIT_ID+"'");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveLawsuitStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveLawsuitStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveLawsuitStaff item = new ProveLawsuitStaff();
                item.setLAWSUIT_STAFF_NAME(rs.getString("LAWSUIT_STAFF_NAME"));
                item.setLAWSUIT_OPREATION_POS_NAME(rs.getString("LAWSUIT_OPREATION_POS_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_NAME(rs.getString("LAWSUIT_OPERATION_OFFICE_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_SHORT_NAME(rs.getString("LAWSUIT_OPERATION_OFFICE_SHORT_NAME"));
                item.setLAWSUIT_MANAGEMENT_POS_NAME(rs.getString("LAWSUIT_MANAGEMENT_POS_NAME"));
                item.setLAWSUIT_MANAGEMENT_OFFICE_NAME(rs.getString("LAWSUIT_MANAGEMENT_OFFICE_NAME"));
                item.setLAWSUIT_MANAGEMENT_OFFICE_SHORT_NAME(rs.getString("LAWSUIT_MANAGEMENT_OFFICE_SHORT_NAME"));
                item.setLAWSUIT_REPRESENT_POS_NAME(rs.getString("LAWSUIT_REPRESENT_POS_NAME"));
                item.setLAWSUIT_REPRESENT_OFFICE_NAME(rs.getString("LAWSUIT_REPRESENT_OFFICE_NAME"));
                item.setLAWSUIT_REPRESENT_OFFICE_SHORT_NAME(rs.getString("LAWSUIT_REPRESENT_OFFICE_SHORT_NAME"));
                item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
                return item;
            }
        });

        return dataList;
    }

    public List<ProveLawsuitType> getProveLawsuitType(int LAWSUIT_ID) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select LAWSUIT_TYPE from OPS_LAWSUIT_DETAIL  where IS_ACTIVE = 1 and LAWSUIT_ID = '"+LAWSUIT_ID+"' ");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveLawsuitType> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveLawsuitType mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveLawsuitType item = new ProveLawsuitType();
                item.setLAWSUIT_TYPE(rs.getInt("LAWSUIT_TYPE"));
                return item;
            }
        });

        return dataList;
    }

    protected ArrestProduct getArrestProductByProductId(int PRODUCT_ID) {

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
        sqlBuilder.append("and PRODUCT_ID = '"+PRODUCT_ID+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<ArrestProduct>() {

            public ArrestProduct extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    ArrestProduct item = new ArrestProduct();
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
                    item.setArrestProductMapping(getArrestProductMapping(rs.getInt("PRODUCT_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    protected List<ArrestProductMapping> getArrestProductMapping(int PRODUCT_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "PRODUCT_MAPPING_ID," +
                        "PRODUCT_ID," +
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
                        "PRODUCT_RESULT," +
                        "SCIENCE_RESULT_DESC," +
                        "VAT," +
                        "IS_DOMESTIC," +
                        "IS_ILLEGAL," +
                        "IS_SCIENCE," +
                        "IS_ACTIVE" +
                        " from OPS_ARREST_PRODUCT_MAPPING  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and PRODUCT_ID = '"+PRODUCT_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<ArrestProductMapping> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ArrestProductMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
                ArrestProductMapping item = new ArrestProductMapping();
                item.setPRODUCT_MAPPING_ID(rs.getInt("PRODUCT_MAPPING_ID"));
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
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
                item.setPRODUCT_RESULT(rs.getString("PRODUCT_RESULT"));
                item.setSCIENCE_RESULT_DESC(rs.getString("SCIENCE_RESULT_DESC"));
                item.setVAT(rs.getFloat("VAT"));
                item.setIS_DOMESTIC(rs.getInt("IS_DOMESTIC"));
                item.setIS_ILLEGAL(rs.getInt("IS_ILLEGAL"));
                item.setIS_SCIENCE(rs.getInt("IS_SCIENCE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));


                return item;
            }
        });

        return dataList;

    }

    public List<ArrestLawbreaker> getArrestLawbreakerByLAWBREAKER_ID(int INDICTMENT_ID) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT distinct " +
                        "    OPS_ARREST_LAWBREAKER.*," +
                        "    OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE," +
                        "    OPS_LAWSUIT_DETAIL.LAWSUIT_END" +
                        "    FROM OPS_ARREST_LAWBREAKER" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_LAWBREAKER.LAWBREAKER_ID = OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID " +
                        "    LEFT JOIN OPS_LAWSUIT_DETAIL ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID " +
                        //"    WHERE OPS_ARREST_LAWBREAKER.LAWBREAKER_ID in ("+LAWBREAKER_ID+") " +
                        "    WHERE OPS_ARREST_LAWBREAKER.LAWBREAKER_ID in ((select lawbreaker_id from OPS_ARREST_INDICTMENT_DETAIL where  indictment_id = "+INDICTMENT_ID+")) " +
                        "    AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" );
                        //"    AND OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1 ");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ArrestLawbreaker> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ArrestLawbreaker mapRow(ResultSet rs, int rowNum) throws SQLException {
                ArrestLawbreaker item = new ArrestLawbreaker();
                item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setPERSON_ID(rs.getInt("PERSON_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setPERSON_TYPE(rs.getInt("PERSON_TYPE"));
                item.setENTITY_TYPE(rs.getInt("ENTITY_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setMIDDLE_NAME(rs.getString("MIDDLE_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setOTHER_NAME(rs.getString("OTHER_NAME"));
                item.setCOMPANY_REGISTRATION_NO(rs.getString("COMPANY_REGISTRATION_NO"));
                item.setEXCISE_REGISTRATION_NO(rs.getString("EXCISE_REGISTRATION_NO"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setAGE(rs.getInt("AGE"));
                item.setPASSPORT_NO(rs.getString("PASSPORT_NO"));
                item.setCAREER(rs.getString("CAREER"));
                item.setPERSON_DESC(rs.getString("PERSON_DESC"));
                item.setEMAIL(rs.getString("EMAIL"));
                item.setTEL_NO(rs.getString("TEL_NO"));
                item.setMISTREAT_NO(rs.getInt("MISTREAT_NO"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setCOMPANY_NAME(rs.getString("COMPANY_NAME"));
                item.setLAWSUIT_TYPE(rs.getInt("LAWSUIT_TYPE"));
                item.setLAWSUIT_END(rs.getInt("LAWSUIT_END"));
                return item;
            }
        });

        return dataList;
    }

    protected Integer getCount(int INDICTMENT_ID,String LAWSUIT_TYPE) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT count(*) as cnt FROM OPS_ARREST_LAWBREAKER " +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_LAWBREAKER.LAWBREAKER_ID = OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID " +
                        "    LEFT JOIN OPS_LAWSUIT_DETAIL ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID " +
                        "    WHERE OPS_ARREST_LAWBREAKER.LAWBREAKER_ID in ((select lawbreaker_id from OPS_ARREST_INDICTMENT_DETAIL where  indictment_id = "+INDICTMENT_ID+")) " +
                        "    AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE = "+LAWSUIT_TYPE);

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Integer>() {

            public Integer extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {


                    return rs.getInt("cnt");
                }

                return null;
            }
        });
    }

    public List<NewProveArrestProduct> getNewProveArrestProduct(int ARREST_ID) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select * from ops_arrest_product where IS_ACTIVE = 1 and arrest_id =  "+ARREST_ID);

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<NewProveArrestProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NewProveArrestProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                NewProveArrestProduct item = new NewProveArrestProduct();
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
                item.setPRODUCT_DESC_MAS(rs.getString("PRODUCT_DESC_MAS"));


                item.setProveArrestIndictmentProduct(getNewProveArrestIndictmentProduct(rs.getInt("PRODUCT_ID")));
                item.setProveArrestProductJoin(getNewProveArrestProductJoin(rs.getInt("ARREST_ID")));


                return item;
            }
        });

        return dataList;
    }

    public List<NewProveArrestProductJoin> getNewProveArrestProductJoin(int ARREST_ID) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select * from ops_arrest_product_join where IS_ACTIVE = 1 and arrest_id =  "+ARREST_ID);

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<NewProveArrestProductJoin> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NewProveArrestProductJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
                NewProveArrestProductJoin item = new NewProveArrestProductJoin();
                item.setARREST_PRODUCT_JOIN(rs.getInt("ARREST_PRODUCT_JOIN"));
                item.setINDICTMENT_ID_MAIN(rs.getInt("INDICTMENT_ID_MAIN"));
                item.setINDICTMENT_ID_SUB(rs.getInt("INDICTMENT_ID_SUB"));
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setPRODUCT_INDICTMENT_ID_SUB(rs.getInt("PRODUCT_INDICTMENT_ID_SUB"));
                item.setPRODUCT_INDICTMENT_ID_MAIN(rs.getInt("PRODUCT_INDICTMENT_ID_MAIN"));

                return item;
            }
        });

        return dataList;
    }

    public List<NewProveArrestIndictmentProduct> getNewProveArrestIndictmentProduct(int PRODUCT_ID) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select * from ops_arrest_indictment_product where IS_ACTIVE = 1 and product_id =  "+PRODUCT_ID);

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<NewProveArrestIndictmentProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NewProveArrestIndictmentProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                NewProveArrestIndictmentProduct item = new NewProveArrestIndictmentProduct();

                item.setPRODUCT_INDICTMENT_ID(rs.getString("PRODUCT_INDICTMENT_ID"));
                item.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
                item.setINDICTMENT_ID(rs.getString("INDICTMENT_ID"));
                item.setSIZES_UNIT_ID(rs.getString("SIZES_UNIT_ID"));
                item.setQUATITY_UNIT_ID(rs.getString("QUATITY_UNIT_ID"));
                item.setVOLUMN_UNIT_ID(rs.getString("VOLUMN_UNIT_ID"));
                item.setSIZES(rs.getFloat("SIZES"));
                item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
                item.setQUANTITY(rs.getFloat("QUANTITY"));
                item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
                item.setVOLUMN(rs.getFloat("VOLUMN"));
                item.setVOLUMN_UNIT(rs.getString("VOLUMN_UNIT"));
                item.setFINE_ESTIMATE(rs.getFloat("FINE_ESTIMATE"));
                item.setIS_ILLEGAL(rs.getInt("IS_ILLEGAL"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;
    }

    public List<ProveCompareDetail> getProveCompareDetailByLawsuitId(int LAWSUIT_ID) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("   SELECT  DISTINCT " +
                        "    OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID," +
                        "    OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE," +
                        "    OPS_LAWSUIT_DETAIL.FINE," +
                        "    OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID " +
                        "    FROM OPS_LAWSUIT" +
                        "    left JOIN OPS_COMPARE on OPS_COMPARE.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
                        "    AND OPS_COMPARE.IS_ACTIVE = 1" +
                        "    left join OPS_COMPARE_MAPPING on OPS_COMPARE_MAPPING.COMPARE_ID = OPS_COMPARE.COMPARE_ID" +
                        "    AND OPS_COMPARE_MAPPING.IS_ACTIVE = 1" +
                        "    left JOIN OPS_COMPARE_DETAIL on OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID" +
                        "    AND OPS_COMPARE_DETAIL.IS_ACTIVE = 1" +
                        "    left join OPS_LAWSUIT_DETAIL on OPS_LAWSUIT_DETAIL.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
                        "    LEFT JOIN OPS_PAYMENT ON OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID = OPS_PAYMENT.LAWSUIT_DETAIL_ID" +
                        "    and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "    WHERE OPS_LAWSUIT.IS_ACTIVE =1" +
                        "    AND OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT.LAWSUIT_ID = '"+LAWSUIT_ID+"'");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveCompareDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveCompareDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveCompareDetail item = new ProveCompareDetail();
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setLAWSUIT_TYPE(rs.getInt("LAWSUIT_TYPE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));

                if(rs.getInt("COMPARE_DETAIL_ID") == 0){
                    item.setProveComparePayment(getProveComparePaymentLawsuitDetailId(rs.getInt("LAWSUIT_DETAIL_ID")));
                }else{
                    item.setProveComparePayment(getProveComparePaymentCompareDetailId(rs.getInt("COMPARE_DETAIL_ID")));
                }

                return item;
            }
        });

        return dataList;
    }

    public List<ProveCompareDetail> getProveCompareDetailByCompareId(int COMPARE_ID) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "    OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID," +
                        "    OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE," +
                        "    OPS_LAWSUIT_DETAIL.FINE," +
                        "    OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID " +
                        "    from ops_compare" +
                        "    inner join ops_lawsuit on ops_compare.lawsuit_id = ops_lawsuit.lawsuit_id" +
                        "    inner join ops_lawsuit_detail on ops_lawsuit_detail.lawsuit_id = ops_lawsuit.lawsuit_id" +
                        "    inner join ops_compare_mapping on ops_compare_mapping.compare_id = ops_compare.compare_id" +
                        "    AND ops_compare_mapping.IS_ACTIVE = 1" +
                        "    inner join ops_compare_detail on ops_compare_detail.compare_mapping_id = ops_compare_mapping.compare_mapping_id" +
                        "    AND ops_compare_detail.IS_ACTIVE = 1" +
                        "    where ops_compare.compare_id = '"+COMPARE_ID+"'" +
                        "    AND ops_compare.IS_ACTIVE = 1");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveCompareDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveCompareDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveCompareDetail item = new ProveCompareDetail();
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setLAWSUIT_TYPE(rs.getInt("LAWSUIT_TYPE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));

                if(rs.getInt("COMPARE_DETAIL_ID") == 0){
                    item.setProveComparePayment(getProveComparePaymentLawsuitDetailId(rs.getInt("LAWSUIT_DETAIL_ID")));
                }else{
                    item.setProveComparePayment(getProveComparePaymentCompareDetailId(rs.getInt("COMPARE_DETAIL_ID")));
                }

                return item;
            }
        });

        return dataList;
    }

    public List<ProveComparePayment> getProveComparePaymentLawsuitDetailId(int LAWSUIT_DETAIL_ID) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT  DISTINCT" +
                        "    OPS_PAYMENT.PAYMENT_ID," +
                        "    OPS_PAYMENT.FINE" +
                        "    FROM OPS_LAWSUIT_DETAIL" +
                        "    inner JOIN OPS_PAYMENT ON OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID = OPS_PAYMENT.LAWSUIT_DETAIL_ID " +
                        "    and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "    WHERE " +
                        "    OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID = "+LAWSUIT_DETAIL_ID);

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveComparePayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveComparePayment mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveComparePayment item = new ProveComparePayment();
                item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
                item.setFINE(rs.getFloat("FINE"));
                return item;
            }
        });

        return dataList;
    }

    public List<ProveComparePayment> getProveComparePaymentCompareDetailId(int COMPARE_DETAIL_ID) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT  DISTINCT" +
                        "    OPS_PAYMENT.PAYMENT_ID," +
                        "    OPS_PAYMENT.FINE" +
                        "    FROM OPS_LAWSUIT" +
                        "    left JOIN OPS_COMPARE on OPS_COMPARE.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
                        "    AND OPS_COMPARE.IS_ACTIVE = 1" +
                        "    left join OPS_COMPARE_MAPPING on OPS_COMPARE_MAPPING.COMPARE_ID = OPS_COMPARE.COMPARE_ID" +
                        "    AND OPS_COMPARE_MAPPING.IS_ACTIVE = 1" +
                        "    left JOIN OPS_COMPARE_DETAIL on OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID" +
                        "    AND OPS_COMPARE_DETAIL.IS_ACTIVE = 1" +
                        "    left join OPS_LAWSUIT_DETAIL on OPS_LAWSUIT_DETAIL.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
                        "    LEFT JOIN OPS_PAYMENT  ON  OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID  = OPS_PAYMENT.COMPARE_DETAIL_ID" +
                        "    and OPS_PAYMENT.IS_ACTIVE = 1  " +
                        "    WHERE OPS_LAWSUIT.IS_ACTIVE =1" +
                        "    AND OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "    AND OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID  = "+COMPARE_DETAIL_ID);

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveComparePayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveComparePayment mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveComparePayment item = new ProveComparePayment();
                item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
                item.setFINE(rs.getFloat("FINE"));
                return item;
            }
        });

        return dataList;
    }
}
