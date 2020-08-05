package com.xcs.phase2.dao.notice;


import com.xcs.phase2.model.notice.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NoticeExt {

    private static final Logger log = LoggerFactory.getLogger(NoticeExt.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected String getSequences(String strSql) {
        log.info("[SQL]  : " + strSql);
        return getJdbcTemplate().queryForObject(strSql, String.class);
    }

    protected List<NoticeInformer> getNoticeInformer(int NOTICE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "INFORMER_ID," +
                        "NOTICE_ID," +
                        "TITLE_ID," +
                        "SUB_DISTRICT_ID," +
                        "INFORMER_STATUS," +
                        "TITLE_NAME_TH," +
                        "TITLE_NAME_EN," +
                        "TITLE_SHORT_NAME_TH," +
                        "TITLE_SHORT_NAME_EN," +
                        "FIRST_NAME," +
                        "MIDDLE_NAME," +
                        "LAST_NAME," +
                        "OTHER_NAME," +
                        "ID_CARD," +
                        "AGE," +
                        "CAREER," +
                        "POSITION," +
                        "PERSON_DESC," +
                        "EMAIL," +
                        "TEL_NO," +
                        "INFORMER_INFO," +
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
                        "INFORMER_PHOTO," +
                        "INFORMER_FINGER_PRINT," +
                        "IS_ACTIVE " +
                        " from OPS_NOTICE_INFORMER  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and NOTICE_ID = '" + NOTICE_ID + "'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NoticeInformer> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NoticeInformer mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeInformer item = new NoticeInformer();
                item.setINFORMER_ID(rs.getInt("INFORMER_ID"));
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setSUB_DISTRICT_ID(rs.getInt("SUB_DISTRICT_ID"));
                item.setINFORMER_STATUS(rs.getInt("INFORMER_STATUS"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setMIDDLE_NAME(rs.getString("MIDDLE_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setOTHER_NAME(rs.getString("OTHER_NAME"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setAGE(rs.getInt("AGE"));
                item.setCAREER(rs.getString("CAREER"));
                item.setPOSITION(rs.getString("POSITION"));
                item.setPERSON_DESC(rs.getString("PERSON_DESC"));
                item.setEMAIL(rs.getString("EMAIL"));
                item.setTEL_NO(rs.getString("TEL_NO"));
                item.setINFORMER_INFO(rs.getString("INFORMER_INFO"));
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
                item.setINFORMER_PHOTO(rs.getString("INFORMER_PHOTO"));
                item.setINFORMER_FINGER_PRINT(rs.getString("INFORMER_FINGER_PRINT"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;

    }

    protected List<NoticeLocale> getNoticeLocale(int NOTICE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "LOCALE_ID," +
                        "NOTICE_ID," +
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
                        "LOCATION," +
                        "IS_ACTIVE " +
                        " from OPS_NOTICE_LOCALE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and NOTICE_ID = '" + NOTICE_ID + "'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NoticeLocale> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NoticeLocale mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeLocale item = new NoticeLocale();
                item.setLOCALE_ID(rs.getInt("LOCALE_ID"));
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
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
                item.setLOCATION(rs.getString("LOCATION"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;

    }

    protected List<NoticeProduct> getNoticeProduct(int NOTICE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "PRODUCT_ID," +
                        "NOTICE_ID," +
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
                        " from OPS_NOTICE_PRODUCT  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and NOTICE_ID = '" + NOTICE_ID + "'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NoticeProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NoticeProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeProduct item = new NoticeProduct();
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
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

    protected List<NoticeSuspect> getNoticeSuspect(int NOTICE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "SUSPECT_ID," +
                        "NOTICE_ID," +
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
                        "IS_ACTIVE," +
                        "COMPANY_NAME" +
                        " from OPS_NOTICE_SUSPECT  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and NOTICE_ID = '" + NOTICE_ID + "'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NoticeSuspect> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NoticeSuspect mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeSuspect item = new NoticeSuspect();
                item.setSUSPECT_ID(rs.getInt("SUSPECT_ID"));
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
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

                return item;
            }
        });

        return dataList;

    }


    protected List<NoticeStaff> getNoticeStaff(int NOTICE_ID,int IS_AUTHORITY) {

        String sql = "";

        if(IS_AUTHORITY == 1) {
            sql += " and CONTRIBUTOR_ID = 8 ";
        }

        if(IS_AUTHORITY == 0) {
            sql += " and CONTRIBUTOR_ID = 7 ";
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "STAFF_ID," +
                        "NOTICE_ID," +
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
                        "IS_ACTIVE " +
                        " from OPS_NOTICE_STAFF  where IS_ACTIVE = 1  "+sql);
        sqlBuilder.append(" and NOTICE_ID = '" + NOTICE_ID + "'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NoticeStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NoticeStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeStaff item = new NoticeStaff();
                item.setSTAFF_ID(rs.getInt("STAFF_ID"));
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
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

}
