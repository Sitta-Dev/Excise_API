package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.investigate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InvestigateExt {

    private static final Logger log = LoggerFactory.getLogger(InvestigateExt.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected String getSequences(String strSql) {
        log.info("[SQL]  : " + strSql);
        return getJdbcTemplate().queryForObject(strSql, String.class);
    }

    protected List<InvestigateDetail> getInvestigateDetail(int INVESTIGATE_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "INVESTIGATE_DETAIL_ID," +
                        "INVESTIGATE_ID," +
                        "OFFICE_ID," +
                        "INVESTIGATE_SEQUENCE," +
                        "OFFICE_CODE," +
                        "OFFICE_NAME," +
                        "to_char(DATE_START,'" + Pattern.FORMAT_DATETIME + "') as DATE_START, " +
                        "to_char(DATE_END,'" + Pattern.FORMAT_DATETIME + "') as DATE_END, " +
                        "INVESTIGATE_DETAIL_DESCRIPTION," +
                        "CONFIDENCE_OF_NEWS," +
                        "VALUE_OF_NEWS," +
                        "COMMAND," +
                        "IS_ACTIVE" +
                        " from OPS_INVESTIGATE_DETAIL where IS_ACTIVE = 1 and INVESTIGATE_ID = '" + INVESTIGATE_ID + "' ");


        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<InvestigateDetail> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public InvestigateDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                InvestigateDetail item = new InvestigateDetail();

                item.setINVESTIGATE_DETAIL_ID(rs.getInt("INVESTIGATE_DETAIL_ID"));
                item.setINVESTIGATE_ID(rs.getString("INVESTIGATE_ID"));
                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                item.setINVESTIGATE_SEQUENCE(rs.getString("INVESTIGATE_SEQUENCE"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setDATE_START(rs.getString("DATE_START"));
                item.setDATE_END(rs.getString("DATE_END"));
                item.setINVESTIGATE_DETAIL_DESCRIPTION(rs.getString("INVESTIGATE_DETAIL_DESCRIPTION"));
                item.setCONFIDENCE_OF_NEWS(rs.getInt("CONFIDENCE_OF_NEWS"));
                item.setVALUE_OF_NEWS(rs.getInt("VALUE_OF_NEWS"));
                item.setCOMMAND(rs.getString("COMMAND"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setInvestigateDetailStaff(getInvestigateDetailStaff(rs.getInt("INVESTIGATE_DETAIL_ID")));

                return item;
            }
        });

        return dataList;

    }

    protected List<InvestigateDetailStaff> getInvestigateDetailStaff(int INVESTIGATE_DETAIL_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "STAFF_ID," +
                        "INVESTIGATE_DETAIL_ID," +
                        "STAFF_REF_ID," +
                        "TITLE_ID," +
                        "STAFF_CODE," +
                        "ID_CARD," +
                        "STAFF_TYPE," +
                        "TITLE_NAME_TH," +
                        "TITLE_NAME_EN," +
                        "TITLE_SHORT_NAME_TH," +
                        "TITLE_SHORT_NAME_EN," +
                        "FIRST_NAME," +
                        "LAST_NAME," +
                        "AGE," +
                        "OPERATION_POS_CODE," +
                        "OPREATION_POS_NAME," +
                        "OPREATION_POS_LEVEL," +
                        "OPERATION_POS_LEVEL_NAME," +
                        "OPERATION_DEPT_CODE," +
                        "OPERATION_DEPT_NAME," +
                        "OPERATION_DEPT_LEVEL," +
                        "OPERATION_UNDER_DEPT_CODE," +
                        "OPERATION_UNDER_DEPT_NAME," +
                        "OPERATION_UNDER_DEPT_LEVEL," +
                        "OPERATION_WORK_DEPT_CODE," +
                        "OPERATION_WORK_DEPT_NAME," +
                        "OPERATION_WORK_DEPT_LEVEL," +
                        "OPERATION_OFFICE_CODE," +
                        "OPERATION_OFFICE_NAME," +
                        "OPERATION_OFFICE_SHORT_NAME," +
                        "MANAGEMENT_POS_CODE," +
                        "MANAGEMENT_POS_NAME," +
                        "MANAGEMENT_POS_LEVEL," +
                        "MANAGEMENT_POS_LEVEL_NAME," +
                        "MANAGEMENT_DEPT_CODE," +
                        "MANAGEMENT_DEPT_NAME," +
                        "MANAGEMENT_DEPT_LEVEL," +
                        "MANAGEMENT_UNDER_DEPT_CODE," +
                        "MANAGEMENT_UNDER_DEPT_NAME," +
                        "MANAGEMENT_UNDER_DEPT_LEVEL," +
                        "MANAGEMENT_WORK_DEPT_CODE," +
                        "MANAGEMENT_WORK_DEPT_NAME," +
                        "MANAGEMENT_WORK_DEPT_LEVEL," +
                        "MANAGEMENT_OFFICE_CODE," +
                        "MANAGEMENT_OFFICE_NAME," +
                        "MANAGEMENT_OFFICE_SHORT_NAME," +
                        "REPRESENT_POS_CODE," +
                        "REPRESENT_POS_NAME," +
                        "REPRESENT_POS_LEVEL," +
                        "REPRESENT_POS_LEVEL_NAME," +
                        "REPRESENT_DEPT_CODE," +
                        "REPRESENT_DEPT_NAME," +
                        "REPRESENT_DEPT_LEVEL," +
                        "REPRESENT_UNDER_DEPT_CODE," +
                        "REPRESENT_UNDER_DEPT_NAME," +
                        "REPRESENT_UNDER_DEPT_LEVEL," +
                        "REPRESENT_WORK_DEPT_CODE," +
                        "REPRESENT_WORK_DEPT_NAME," +
                        "REPRESENT_WORK_DEPT_LEVEL," +
                        "REPRESENT_OFFICE_CODE," +
                        "REPRESENT_OFFICE_NAME," +
                        "REPRESENT_OFFICE_SHORT_NAME," +
                        "STATUS," +
                        "REMARK," +
                        "CONTRIBUTOR_ID," +
                        "IS_ACTIVE" +
                        " from OPS_INVESTIGATE_DETAIL_STAFF where INVESTIGATE_DETAIL_ID = " + INVESTIGATE_DETAIL_ID + " and IS_ACTIVE = 1");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<InvestigateDetailStaff> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public InvestigateDetailStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                InvestigateDetailStaff item = new InvestigateDetailStaff();
                item.setSTAFF_ID(rs.getInt("STAFF_ID"));
                item.setINVESTIGATE_DETAIL_ID(rs.getInt("INVESTIGATE_DETAIL_ID"));
                item.setSTAFF_REF_ID(rs.getInt("STAFF_REF_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setSTAFF_CODE(rs.getString("STAFF_CODE"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setSTAFF_TYPE(rs.getInt("STAFF_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setAGE(rs.getInt("AGE"));
                item.setOPERATION_POS_CODE(rs.getString("OPERATION_POS_CODE"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setOPREATION_POS_LEVEL(rs.getString("OPREATION_POS_LEVEL"));
                item.setOPERATION_POS_LEVEL_NAME(rs.getString("OPERATION_POS_LEVEL_NAME"));
                item.setOPERATION_DEPT_CODE(rs.getString("OPERATION_DEPT_CODE"));
                item.setOPERATION_DEPT_NAME(rs.getString("OPERATION_DEPT_NAME"));
                item.setOPERATION_DEPT_LEVEL(rs.getInt("OPERATION_DEPT_LEVEL"));
                item.setOPERATION_UNDER_DEPT_CODE(rs.getString("OPERATION_UNDER_DEPT_CODE"));
                item.setOPERATION_UNDER_DEPT_NAME(rs.getString("OPERATION_UNDER_DEPT_NAME"));
                item.setOPERATION_UNDER_DEPT_LEVEL(rs.getInt("OPERATION_UNDER_DEPT_LEVEL"));
                item.setOPERATION_WORK_DEPT_CODE(rs.getString("OPERATION_WORK_DEPT_CODE"));
                item.setOPERATION_WORK_DEPT_NAME(rs.getString("OPERATION_WORK_DEPT_NAME"));
                item.setOPERATION_WORK_DEPT_LEVEL(rs.getInt("OPERATION_WORK_DEPT_LEVEL"));
                item.setOPERATION_OFFICE_CODE(rs.getString("OPERATION_OFFICE_CODE"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                item.setMANAGEMENT_POS_CODE(rs.getString("MANAGEMENT_POS_CODE"));
                item.setMANAGEMENT_POS_NAME(rs.getString("MANAGEMENT_POS_NAME"));
                item.setMANAGEMENT_POS_LEVEL(rs.getString("MANAGEMENT_POS_LEVEL"));
                item.setMANAGEMENT_POS_LEVEL_NAME(rs.getString("MANAGEMENT_POS_LEVEL_NAME"));
                item.setMANAGEMENT_DEPT_CODE(rs.getString("MANAGEMENT_DEPT_CODE"));
                item.setMANAGEMENT_DEPT_NAME(rs.getString("MANAGEMENT_DEPT_NAME"));
                item.setMANAGEMENT_DEPT_LEVEL(rs.getInt("MANAGEMENT_DEPT_LEVEL"));
                item.setMANAGEMENT_UNDER_DEPT_CODE(rs.getString("MANAGEMENT_UNDER_DEPT_CODE"));
                item.setMANAGEMENT_UNDER_DEPT_NAME(rs.getString("MANAGEMENT_UNDER_DEPT_NAME"));
                item.setMANAGEMENT_UNDER_DEPT_LEVEL(rs.getInt("MANAGEMENT_UNDER_DEPT_LEVEL"));
                item.setMANAGEMENT_WORK_DEPT_CODE(rs.getString("MANAGEMENT_WORK_DEPT_CODE"));
                item.setMANAGEMENT_WORK_DEPT_NAME(rs.getString("MANAGEMENT_WORK_DEPT_NAME"));
                item.setMANAGEMENT_WORK_DEPT_LEVEL(rs.getInt("MANAGEMENT_WORK_DEPT_LEVEL"));
                item.setMANAGEMENT_OFFICE_CODE(rs.getString("MANAGEMENT_OFFICE_CODE"));
                item.setMANAGEMENT_OFFICE_NAME(rs.getString("MANAGEMENT_OFFICE_NAME"));
                item.setMANAGEMENT_OFFICE_SHORT_NAME(rs.getString("MANAGEMENT_OFFICE_SHORT_NAME"));
                item.setREPRESENT_POS_CODE(rs.getString("REPRESENT_POS_CODE"));
                item.setREPRESENT_POS_NAME(rs.getString("REPRESENT_POS_NAME"));
                item.setREPRESENT_POS_LEVEL(rs.getString("REPRESENT_POS_LEVEL"));
                item.setREPRESENT_POS_LEVEL_NAME(rs.getString("REPRESENT_POS_LEVEL_NAME"));
                item.setREPRESENT_DEPT_CODE(rs.getString("REPRESENT_DEPT_CODE"));
                item.setREPRESENT_DEPT_NAME(rs.getString("REPRESENT_DEPT_NAME"));
                item.setREPRESENT_DEPT_LEVEL(rs.getInt("REPRESENT_DEPT_LEVEL"));
                item.setREPRESENT_UNDER_DEPT_CODE(rs.getString("REPRESENT_UNDER_DEPT_CODE"));
                item.setREPRESENT_UNDER_DEPT_NAME(rs.getString("REPRESENT_UNDER_DEPT_NAME"));
                item.setREPRESENT_UNDER_DEPT_LEVEL(rs.getInt("REPRESENT_UNDER_DEPT_LEVEL"));
                item.setREPRESENT_WORK_DEPT_CODE(rs.getString("REPRESENT_WORK_DEPT_CODE"));
                item.setREPRESENT_WORK_DEPT_NAME(rs.getString("REPRESENT_WORK_DEPT_NAME"));
                item.setREPRESENT_WORK_DEPT_LEVEL(rs.getInt("REPRESENT_WORK_DEPT_LEVEL"));
                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setREPRESENT_OFFICE_SHORT_NAME(rs.getString("REPRESENT_OFFICE_SHORT_NAME"));
                item.setSTATUS(rs.getInt("STATUS"));
                item.setREMARK(rs.getString("REMARK"));
                item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;

    }

    protected List<InvestigateDetailSuspect> getInvestigateDetailSuspect(int INVESTIGATE_DETAIL_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "SUSPECT_ID," +
                        "INVESTIGATE_DETAIL_ID," +
                        "PERSON_ID," +
                        "TITLE_ID," +
                        "PERSON_TYPE," +
                        "ENTITY_TYPE," +
                        "TITLE_NAME_TH," +
                        "TITLE_NAME_EN," +
                        "TITLE_SHORT_NAME_TH," +
                        "TITLE_SHORT_NAME_EN," +
                        "FIRST_NAME," +
                        "MIDDLE_NAME," +
                        "LAST_NAME," +
                        "OTHER_NAME," +
                        "COMPANY_REGISTRATION_NO," +
                        "EXCISE_REGISTRATION_NO," +
                        "ID_CARD," +
                        "AGE," +
                        "PASSPORT_NO," +
                        "CAREER," +
                        "PERSON_DESC," +
                        "EMAIL," +
                        "TEL_NO," +
                        "MISTREAT_NO," +
                        "IS_ACTIVE" +
                        " from OPS_INVESTIGATE_DETAIL_SUSPECT  where IS_ACTIVE = 1 ");
        sqlBuilderDetail.append("and INVESTIGATE_DETAIL_ID =  '" + INVESTIGATE_DETAIL_ID + "'  ");


        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<InvestigateDetailSuspect> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public InvestigateDetailSuspect mapRow(ResultSet rs, int rowNum) throws SQLException {
                InvestigateDetailSuspect item = new InvestigateDetailSuspect();

                item.setSUSPECT_ID(rs.getInt("SUSPECT_ID"));
                item.setINVESTIGATE_DETAIL_ID(rs.getInt("INVESTIGATE_DETAIL_ID"));
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

                return item;
            }
        });

        return dataList;

    }

    protected List<InvestigateDetailLocale> getInvestigateDetailLocale(int INVESTIGATE_DETAIL_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "LOCALE_ID," +
                        "INVESTIGATE_DETAIL_ID," +
                        "SUB_DISTRICT_ID," +
                        "GPS," +
                        "ADDRESS_NO," +
                        "VILLAGE_NO," +
                        "BUILDING_NAME," +
                        "ROOM_NO," +
                        "FLOOR," +
                        "VILLAGE_NAME," +
                        "ALLEY," +
                        "LANE," +
                        "ROAD," +
                        "ADDRESS_TYPE," +
                        "ADDRESS_STATUS," +
                        "POLICE_STATION," +
                        "IS_ACTIVE" +
                        " from OPS_INVESTIGATE_DETAIL_LOCALE  where IS_ACTIVE = 1 ");
        sqlBuilderDetail.append("and INVESTIGATE_DETAIL_ID =  '" + INVESTIGATE_DETAIL_ID + "'  ");


        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<InvestigateDetailLocale> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public InvestigateDetailLocale mapRow(ResultSet rs, int rowNum) throws SQLException {
                InvestigateDetailLocale item = new InvestigateDetailLocale();

                item.setLOCALE_ID(rs.getInt("LOCALE_ID"));
                item.setINVESTIGATE_DETAIL_ID(rs.getInt("INVESTIGATE_DETAIL_ID"));
                item.setSUB_DISTRICT_ID(rs.getInt("SUB_DISTRICT_ID"));
                item.setGPS(rs.getString("GPS"));
                item.setADDRESS_NO(rs.getString("ADDRESS_NO"));
                item.setVILLAGE_NO(rs.getString("VILLAGE_NO"));
                item.setBUILDING_NAME(rs.getString("BUILDING_NAME"));
                item.setROOM_NO(rs.getString("ROOM_NO"));
                item.setFLOOR(rs.getString("FLOOR"));
                item.setVILLAGE_NAME(rs.getString("VILLAGE_NAME"));
                item.setALLEY(rs.getString("ALLEY"));
                item.setLANE(rs.getString("LANE"));
                item.setROAD(rs.getString("ROAD"));
                item.setADDRESS_TYPE(rs.getInt("ADDRESS_TYPE"));
                item.setADDRESS_STATUS(rs.getInt("ADDRESS_STATUS"));
                item.setPOLICE_STATION(rs.getString("POLICE_STATION"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;

    }

    protected List<InvestigateDetailProduct> getInvestigateDetailProduct(int INVESTIGATE_DETAIL_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "PRODUCT_ID," +
                        "INVESTIGATE_DETAIL_ID," +
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
                        "IS_ACTIVE" +
                        " from OPS_INVESTIGATE_DETAIL_PRODUCT  where IS_ACTIVE = 1 ");
        sqlBuilderDetail.append("and INVESTIGATE_DETAIL_ID =  '" + INVESTIGATE_DETAIL_ID + "'  ");


        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<InvestigateDetailProduct> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public InvestigateDetailProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                InvestigateDetailProduct item = new InvestigateDetailProduct();

                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setINVESTIGATE_DETAIL_ID(rs.getInt("INVESTIGATE_DETAIL_ID"));
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
        });

        return dataList;

    }


}
