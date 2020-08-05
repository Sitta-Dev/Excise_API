package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.arrest.*;
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




public class ArrestExt {

	private static final Logger log = LoggerFactory.getLogger(ArrestExt.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	protected String getSequences(String strSql) {
		log.info("[SQL]  : " + strSql);
		return getJdbcTemplate().queryForObject(strSql, String.class);
	}
	
	
	//=================== ArrestStaff
	
	protected List<ArrestStaff> getArrestStaff(int ARREST_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"STAFF_ID," +
	    	    		"ARREST_ID," +
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
			    		"from OPS_ARREST_STAFF  where IS_ACTIVE = 1 ");
				sqlBuilder.append("and ARREST_ID = '"+ARREST_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestStaff item = new ArrestStaff();
				item.setSTAFF_ID(rs.getInt("STAFF_ID"));
				item.setARREST_ID(rs.getInt("ARREST_ID"));
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
	
	//=================== ArrestLocale
	
	protected List<ArrestLocale> getArrestLocale(int ARREST_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"OPS_ARREST_LOCALE.*," +
			    		"MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH," +
			    		"MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN," +
			    		"MAS_DISTRICT.DISTRICT_NAME_TH," +
			    		"MAS_DISTRICT.DISTRICT_NAME_EN," +
			    		"MAS_PROVINCE.PROVINCE_NAME_TH," +
			    		"MAS_PROVINCE.PROVINCE_NAME_EN" +
			    		" from OPS_ARREST_LOCALE" +
			    		" INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
			    		" INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID" +
			    		" INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID" +
			    		" where OPS_ARREST_LOCALE.IS_ACTIVE = 1 and OPS_ARREST_LOCALE.ARREST_ID = '"+ARREST_ID+"'");
				sqlBuilder.append("and ARREST_ID = '"+ARREST_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestLocale> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestLocale mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestLocale item = new ArrestLocale();
				item.setLOCALE_ID(rs.getInt("LOCALE_ID"));
				item.setARREST_ID(rs.getInt("ARREST_ID"));
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
				item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
				item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
				item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
				item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
				item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
				item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));


				return item;
			}
		});

		return dataList;

	}
	
	//=================== ArrestLawbreaker
	
	protected List<ArrestLawbreaker> getArrestLawbreaker(int ARREST_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"LAWBREAKER_ID," +
			    		"ARREST_ID," +
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
						"COMPANY_NAME " +
			    		" from OPS_ARREST_LAWBREAKER  where IS_ACTIVE = 1 ");
				sqlBuilder.append("and ARREST_ID = '"+ARREST_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

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
				return item;
			}
		});

		return dataList;

	}

    //=================== ArrestLawbreaker

    public ArrestLawbreaker getArrestLawbreakerByLAWBREAKER_ID(int LAWBREAKER_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "LAWBREAKER_ID," +
                        "ARREST_ID," +
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
						"COMPANY_NAME," +
                        "IS_ACTIVE " +
                        " from OPS_ARREST_LAWBREAKER  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and LAWBREAKER_ID = '"+LAWBREAKER_ID+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<ArrestLawbreaker>() {

            public ArrestLawbreaker extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

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

                    return item;
                }

                return null;
            }
        });
    }


	//=================== ArrestProduct

	protected List<ArrestProduct> getArrestProduct(int ARREST_ID) {

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
				sqlBuilder.append("and ARREST_ID = '"+ARREST_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
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
		});

		return dataList;

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
	
	protected List<ArrestIndictment> getArrestIndictment(int ARREST_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"OPS_ARREST_INDICTMENT.*," +
			    		"MAS_LAW_GUILTBASE.GUILTBASE_NAME," +
			    		"MAS_LAW_GUILTBASE.FINE," +
			    		"MAS_LAW_GUILTBASE.IS_PROVE," +
			    		"MAS_LAW_GUILTBASE.IS_COMPARE," +
			    		"MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME," +
			    		"MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC," +
			    		"MAS_LAW_GROUP_SECTION.SECTION_NAME," +
			    		"MAS_LAW_PENALTY.PENALTY_DESC" +
			    		" from OPS_ARREST_INDICTMENT" +
			    		" INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
			    		" INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
			    		" INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
			    		" INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
			    		" INNER JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID" +
			    		" where OPS_ARREST_INDICTMENT.ARREST_ID = '"+ARREST_ID+"' and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1 ");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestIndictment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestIndictment mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestIndictment item = new ArrestIndictment();
				item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
				item.setARREST_ID(rs.getInt("ARREST_ID"));
				item.setGUILTBASE_ID(rs.getInt("GUILTBASE_ID"));
				item.setFINE_ESTIMATE(rs.getFloat("FINE_ESTIMATE"));
				item.setIS_LAWSUIT_COMPLETE(rs.getInt("IS_LAWSUIT_COMPLETE"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
				item.setFINE(rs.getString("FINE"));
				item.setIS_PROVE(rs.getInt("IS_PROVE"));
				item.setIS_COMPARE(rs.getInt("IS_COMPARE"));
				item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
				item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
				item.setSECTION_NAME(rs.getString("SECTION_NAME"));
				item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
				item.setArrestIndictmentDetail(getArrestIndictmentDetail(rs.getInt("INDICTMENT_ID")));
				return item;
			}
		});

		return dataList;

	}
	
	protected List<ArrestIndictmentProduct> getArrestIndictmentProduct(int INDICTMENT_DETAIL_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		" OPS_ARREST_INDICTMENT_PRODUCT.*," +
			    		" IP.PRODUCT_GROUP_NAME," +
			    		" IP.PRODUCT_CATEGORY_NAME," +
			    		" IP.PRODUCT_TYPE_NAME," +
			    		" IP.PRODUCT_SUBTYPE_NAME," +
			    		" IP.PRODUCT_SUBSETTYPE_NAME," +
			    		" IP.PRODUCT_BRAND_NAME_TH," +
			    		" IP.PRODUCT_BRAND_NAME_EN," +
			    		" IP.PRODUCT_SUBBRAND_NAME_TH," +
			    		" IP.PRODUCT_SUBBRAND_NAME_EN," +
			    		" IP.PRODUCT_MODEL_NAME_TH," +
			    		" IP.PRODUCT_MODEL_NAME_EN," +
						" IP.PRODUCT_GROUP_ID" +
			    		" from OPS_ARREST_INDICTMENT_PRODUCT" +
			    		" LEFT JOIN OPS_ARREST_PRODUCT IP ON OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID = IP.PRODUCT_ID " +
			    		" where OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE = 1 AND IP.IS_ACTIVE = 1 and OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID = '"+INDICTMENT_DETAIL_ID+"' ");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestIndictmentProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestIndictmentProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestIndictmentProduct item = new ArrestIndictmentProduct();
				item.setPRODUCT_INDICTMENT_ID(rs.getInt("PRODUCT_INDICTMENT_ID"));
				item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
				item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_ID"));
				item.setSIZES_UNIT_ID(rs.getInt("SIZES_UNIT_ID"));
				item.setQUATITY_UNIT_ID(rs.getInt("QUATITY_UNIT_ID"));
				item.setVOLUMN_UNIT_ID(rs.getInt("VOLUMN_UNIT_ID"));
				item.setSIZES(rs.getFloat("SIZES"));
				item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
				item.setQUANTITY(rs.getFloat("QUANTITY"));
				item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
				item.setVOLUMN(rs.getFloat("VOLUMN"));
				item.setVOLUMN_UNIT(rs.getString("VOLUMN_UNIT"));
				item.setFINE_ESTIMATE(rs.getFloat("FINE_ESTIMATE"));
				item.setIS_ILLEGAL(rs.getInt("IS_ILLEGAL"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
				item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));
				item.setPRODUCT_TYPE_NAME(rs.getString("PRODUCT_TYPE_NAME"));
				item.setPRODUCT_SUBTYPE_NAME(rs.getString("PRODUCT_SUBTYPE_NAME"));
				item.setPRODUCT_SUBSETTYPE_NAME(rs.getString("PRODUCT_SUBSETTYPE_NAME"));
				item.setPRODUCT_BRAND_NAME_TH(rs.getString("PRODUCT_BRAND_NAME_TH"));
				item.setPRODUCT_BRAND_NAME_EN(rs.getString("PRODUCT_BRAND_NAME_EN"));
				item.setPRODUCT_SUBBRAND_NAME_TH(rs.getString("PRODUCT_SUBBRAND_NAME_TH"));
				item.setPRODUCT_SUBBRAND_NAME_EN(rs.getString("PRODUCT_SUBBRAND_NAME_EN"));
				item.setPRODUCT_MODEL_NAME_TH(rs.getString("PRODUCT_MODEL_NAME_TH"));
				item.setPRODUCT_MODEL_NAME_EN(rs.getString("PRODUCT_MODEL_NAME_EN"));
				item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
				return item;
			}
		});

		return dataList;

	}
	
	protected List<ArrestIndictmentDetail> getArrestIndictmentDetail(int INDICTMENT_ID) {

//		StringBuilder sqlBuilder = new StringBuilder()
//			    .append("select " +
//			    		"OPS_ARREST_INDICTMENT_DETAIL.*," +
//			    		"LB.TITLE_NAME_TH," +
//			    		"LB.TITLE_NAME_EN," +
//			    		"LB.TITLE_SHORT_NAME_TH," +
//			    		"LB.TITLE_SHORT_NAME_EN," +
//			    		"LB.FIRST_NAME," +
//			    		"LB.MIDDLE_NAME," +
//			    		"LB.LAST_NAME," +
//			    		"LB.OTHER_NAME" +
//			    		" from OPS_ARREST_INDICTMENT_DETAIL" +
//			    		" LEFT  JOIN OPS_ARREST_LAWBREAKER LB ON OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = LB.LAWBREAKER_ID" +
//			    		" where OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1 AND LB.IS_ACTIVE = 1 and OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID = '"+INDICTMENT_ID+"' ");

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "OPS_ARREST_INDICTMENT_DETAIL.*" +
                        " from OPS_ARREST_INDICTMENT_DETAIL where IS_ACTIVE = 1 and INDICTMENT_ID = '"+INDICTMENT_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestIndictmentDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestIndictmentDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestIndictmentDetail item = new ArrestIndictmentDetail();
				item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
				item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
				item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				item.setFINE_ESTIMATE(rs.getFloat("FINE_ESTIMATE"));// new
				item.setArrestIndictmentProduct(getArrestIndictmentProduct(rs.getInt("INDICTMENT_DETAIL_ID")));
				item.setArrestLawbreaker(getArrestLawbreakerByLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID")));
				return item;
			}
		});

		return dataList;

	}
	
	protected List<ArrestNotice> getArrestNotice(int ARREST_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT DISTINCT " +
						"    OPS_NOTICE.NOTICE_ID," +
						"    OPS_NOTICE.ARREST_ID," +
						"    OPS_NOTICE.NOTICE_CODE," +
						"    OPS_NOTICE.OFFICE_NAME," +
						"    OPS_NOTICE.NOTICE_DATE," +
						"    OPS_NOTICE.IS_MATCH," +
						"    OPS_NOTICE_STAFF.STAFF_ID," +
						"    OPS_NOTICE_STAFF.STAFF_REF_ID," +
						"    OPS_NOTICE_STAFF.TITLE_ID," +
						"    OPS_NOTICE_STAFF.STAFF_CODE," +
						"    OPS_NOTICE_STAFF.STAFF_TYPE," +
						"    OPS_NOTICE_STAFF.TITLE_NAME_TH," +
						"    OPS_NOTICE_STAFF.TITLE_NAME_EN," +
						"    OPS_NOTICE_STAFF.TITLE_SHORT_NAME_TH," +
						"    OPS_NOTICE_STAFF.TITLE_SHORT_NAME_EN," +
						"    OPS_NOTICE_STAFF.FIRST_NAME," +
						"    OPS_NOTICE_STAFF.LAST_NAME," +
						"    OPS_NOTICE_STAFF.STATUS," +
						"    OPS_NOTICE_STAFF.REMARK," +
						"    OPS_NOTICE_STAFF.CONTRIBUTOR_ID" +
						"    FROM OPS_NOTICE" +
						"    INNER JOIN OPS_NOTICE_STAFF ON OPS_NOTICE.NOTICE_ID = OPS_NOTICE_STAFF.NOTICE_ID" +
						"    WHERE OPS_NOTICE.IS_ACTIVE = 1 and OPS_NOTICE.ARREST_ID = '"+ARREST_ID+"'" );


		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestNotice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestNotice mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestNotice item = new ArrestNotice();
				item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
				item.setARREST_ID(rs.getInt("ARREST_ID"));
				item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
				item.setNOTICE_DATE(rs.getString("NOTICE_DATE"));
				item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));//
				item.setIS_MATCH(rs.getInt("IS_MATCH"));
				item.setSTAFF_ID(rs.getInt("STAFF_ID"));
				item.setSTAFF_REF_ID(rs.getInt("STAFF_REF_ID"));
				item.setTITLE_ID(rs.getInt("TITLE_ID"));
				item.setSTAFF_CODE(rs.getString("STAFF_CODE"));
				item.setSTAFF_TYPE(rs.getString("STAFF_TYPE"));
				item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
				item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
				item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
				item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
				item.setFIRST_NAME(rs.getString("FIRST_NAME"));
				item.setLAST_NAME(rs.getString("LAST_NAME"));
				item.setSTATUS(rs.getInt("STATUS"));
				item.setREMARK(rs.getString("REMARK"));
				item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));

				return item;
			}
		});

		return dataList;

	}
	
	protected List<ArrestPurityCert> getArrestPurityCert(int ARREST_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"OPS_PURITYCERT.PURITYCERT_ID," +
			    		"OPS_PURITYCERT.ARREST_ID," +
			    		"OPS_PURITYCERT.PURITYCERT_CODE," +
			    		"to_char(OPS_PURITYCERT.PURITYCERT_DATE,'"+Pattern.FORMAT_DATETIME+"') as PURITYCERT_DATE," +
			    		"OPS_PURITYCERT.OFFICE_NAME," +
			    		"OPS_PURITYCERT_STAFF.TITLE_NAME_TH as STAFF_TITLE_NAME_TH," +
			    		"OPS_PURITYCERT_STAFF.TITLE_NAME_EN as STAFF_TITLE_NAME_EN," +
			    		"OPS_PURITYCERT_STAFF.TITLE_SHORT_NAME_TH as STAFF_TITLE_SHORT_NAME_TH," +
			    		"OPS_PURITYCERT_STAFF.TITLE_SHORT_NAME_EN as STAFF_TITLE_SHORT_NAME_EN," +
			    		"OPS_PURITYCERT_STAFF.FIRST_NAME as STAFF_FIRST_NAME," +
			    		"OPS_PURITYCERT_STAFF.LAST_NAME as STAFF_LAST_NAME," +
			    		"OPS_PURITYCERT_STAFF.OPERATION_OFFICE_NAME as STAFF_OPERATION_OFFICE_NAME," +
			    		"OPS_PURITYCERT_STAFF.OPERATION_OFFICE_SHORT_NAME as STAFF_OPERATION_OFFICE_SHORT_NAME," +
			    		"OPS_PURITYCERT_STAFF.OPREATION_POS_NAME as STAFF_OPREATION_POS_NAME," +
			    		"OPS_PURITYCERT_STAFF.MANAGEMENT_OFFICE_NAME as STAFF_MANAGEMENT_OFFICE_NAME," +
			    		"OPS_PURITYCERT_STAFF.MANAGEMENT_OFFICE_SHORT_NAME as STAFF_MANAGEMENT_OFFICE_SHORT_NAME," +
			    		"OPS_PURITYCERT_STAFF.MANAGEMENT_POS_NAME as STAFF_MANAGEMENT_POS_NAME," +
			    		"OPS_PURITYCERT_STAFF.REPRESENT_OFFICE_NAME as STAFF_REPRESENT_OFFICE_NAME," +
			    		"OPS_PURITYCERT_STAFF.REPRESENT_OFFICE_SHORT_NAME as STAFF_REPRESENT_OFFICE_SHORT_NAME," +
			    		"OPS_PURITYCERT_STAFF.REPRESENT_POS_NAME as STAFF_REPRESENT_POS_NAME," +
			    		"OPS_PURITYCERT.PERSON_TITLE_NAME_TH," +
			    		"OPS_PURITYCERT.PERSON_TITLE_NAME_EN," +
			    		"OPS_PURITYCERT.PERSON_TITLE_SHORT_NAME_TH," +
			    		"OPS_PURITYCERT.PERSON_TITLE_SHORT_NAME_EN," +
			    		"OPS_PURITYCERT.PERSON_FIRST_NAME," +
			    		"OPS_PURITYCERT.PERSON_MIDDLE_NAME," +
			    		"OPS_PURITYCERT.PERSON_LAST_NAME," +
			    		"OPS_PURITYCERT.PERSON_OTHER_NAME," +
			    		"PC_SUB_DISTRICT.SUB_DISTRICT_NAME_TH," +
			    		"PC_SUB_DISTRICT.SUB_DISTRICT_NAME_EN," +
			    		"PC_DISTRICT.DISTRICT_NAME_TH," +
			    		"PC_DISTRICT.DISTRICT_NAME_EN," +
			    		"PC_PROVINCE.PROVINCE_NAME_TH," +
			    		"PC_PROVINCE.PROVINCE_NAME_EN" +
			    		" from OPS_PURITYCERT" +
			    		" LEFT JOIN OPS_PURITYCERT_STAFF ON OPS_PURITYCERT.PURITYCERT_ID = OPS_PURITYCERT_STAFF.PURITYCERT_ID AND OPS_PURITYCERT_STAFF.IS_ACTIVE = 1 AND OPS_PURITYCERT_STAFF.CONTRIBUTOR_ID = 5" +
			    		" LEFT JOIN MAS_SUB_DISTRICT PC_SUB_DISTRICT ON OPS_PURITYCERT.SUB_DISTRICT_ID = PC_SUB_DISTRICT.SUB_DISTRICT_ID" +
			    		" LEFT JOIN MAS_DISTRICT PC_DISTRICT ON PC_SUB_DISTRICT.DISTRICT_ID = PC_DISTRICT.DISTRICT_ID" +
			    		" LEFT JOIN MAS_PROVINCE PC_PROVINCE ON PC_DISTRICT.PROVINCE_ID = PC_PROVINCE.PROVINCE_ID" +
			    		" where OPS_PURITYCERT.ARREST_ID = '"+ARREST_ID+"' AND OPS_PURITYCERT.IS_ACTIVE = 1" );

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestPurityCert> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestPurityCert mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestPurityCert item = new ArrestPurityCert();
				item.setPURITYCERT_ID(rs.getInt("PURITYCERT_ID"));
				item.setARREST_ID(rs.getInt("ARREST_ID"));
				item.setPURITYCERT_CODE(rs.getString("PURITYCERT_CODE"));
				item.setPURITYCERT_DATE(rs.getString("PURITYCERT_DATE"));
				item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
				item.setSTAFF_TITLE_NAME_TH(rs.getString("STAFF_TITLE_NAME_TH"));
				item.setSTAFF_TITLE_NAME_EN(rs.getString("STAFF_TITLE_NAME_EN"));
				item.setSTAFF_TITLE_SHORT_NAME_TH(rs.getString("STAFF_TITLE_SHORT_NAME_TH"));
				item.setSTAFF_TITLE_SHORT_NAME_EN(rs.getString("STAFF_TITLE_SHORT_NAME_EN"));
				item.setSTAFF_FIRST_NAME(rs.getString("STAFF_FIRST_NAME"));
				item.setSTAFF_LAST_NAME(rs.getString("STAFF_LAST_NAME"));
				item.setSTAFF_OPERATION_OFFICE_NAME(rs.getString("STAFF_OPERATION_OFFICE_NAME"));
				item.setSTAFF_OPERATION_OFFICE_SHORT_NAME(rs.getString("STAFF_OPERATION_OFFICE_SHORT_NAME"));
				item.setSTAFF_OPREATION_POS_NAME(rs.getString("STAFF_OPREATION_POS_NAME"));
				item.setSTAFF_MANAGEMENT_OFFICE_NAME(rs.getString("STAFF_MANAGEMENT_OFFICE_NAME"));
				item.setSTAFF_MANAGEMENT_OFFICE_SHORT_NAME(rs.getString("STAFF_MANAGEMENT_OFFICE_SHORT_NAME"));
				item.setSTAFF_MANAGEMENT_POS_NAME(rs.getString("STAFF_MANAGEMENT_POS_NAME"));
				item.setSTAFF_REPRESENT_OFFICE_NAME(rs.getString("STAFF_REPRESENT_OFFICE_NAME"));
				item.setSTAFF_REPRESENT_OFFICE_SHORT_NAME(rs.getString("STAFF_REPRESENT_OFFICE_SHORT_NAME"));
				item.setSTAFF_REPRESENT_POS_NAME(rs.getString("STAFF_REPRESENT_POS_NAME"));
				item.setPERSON_TITLE_NAME_TH(rs.getString("PERSON_TITLE_NAME_TH"));
				item.setPERSON_TITLE_NAME_EN(rs.getString("PERSON_TITLE_NAME_EN"));
				item.setPERSON_TITLE_SHORT_NAME_TH(rs.getString("PERSON_TITLE_SHORT_NAME_TH"));
				item.setPERSON_TITLE_SHORT_NAME_EN(rs.getString("PERSON_TITLE_SHORT_NAME_EN"));
				item.setPERSON_FIRST_NAME(rs.getString("PERSON_FIRST_NAME"));
				item.setPERSON_MIDDLE_NAME(rs.getString("PERSON_MIDDLE_NAME"));
				item.setPERSON_LAST_NAME(rs.getString("PERSON_LAST_NAME"));
				item.setPERSON_OTHER_NAME(rs.getString("PERSON_OTHER_NAME"));
				item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
				item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
				item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
				item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
				item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
				item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));

				return item;
			}
		});

		return dataList;

	}
	
	protected List<ArrestSearchWarrant> getArrestSearchWarrant(int ARREST_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"OPS_SEARCH_WARRANT.SEARCH_WARRANT_ID," +
			    		"OPS_SEARCH_WARRANT.ARREST_ID," +
			    		"OPS_SEARCH_WARRANT_REQUEST.REQUEST_CODE," +
			    		"OPS_SEARCH_WARRANT_REQUEST.REQUEST_NO," +
			    		"to_char(OPS_SEARCH_WARRANT_REQUEST.REQUEST_NO_YEAR,'"+Pattern.FORMAT_DATETIME+"') as REQUEST_NO_YEAR," +
			    		"to_char(OPS_SEARCH_WARRANT_REQUEST.REQUEST_DATE,'"+Pattern.FORMAT_DATETIME+"') as REQUEST_DATE," +
			    		"OPS_SEARCH_WARRANT_STAFF.TITLE_NAME_TH as STAFF_TITLE_NAME_TH," +
			    		"OPS_SEARCH_WARRANT_STAFF.TITLE_NAME_EN as STAFF_TITLE_NAME_EN ," +
			    		"OPS_SEARCH_WARRANT_STAFF.TITLE_SHORT_NAME_TH as STAFF_TITLE_SHORT_NAME_TH," +
			    		"OPS_SEARCH_WARRANT_STAFF.TITLE_SHORT_NAME_EN as STAFF_TITLE_SHORT_NAME_EN," +
			    		"OPS_SEARCH_WARRANT_STAFF.FIRST_NAME as STAFF_FIRST_NAME ," +
			    		"OPS_SEARCH_WARRANT_STAFF.LAST_NAME as STAFF_LAST_NAME," +
			    		"OPS_SEARCH_WARRANT_STAFF.OPERATION_OFFICE_NAME as STAFF_OPERATION_OFFICE_NAME," +
			    		"OPS_SEARCH_WARRANT_STAFF.OPERATION_OFFICE_SHORT_NAME as STAFF_OPERATION_OFFICE_SHORT_NAME," +
			    		"OPS_SEARCH_WARRANT_STAFF.OPREATION_POS_NAME as STAFF_OPREATION_POS_NAME," +
			    		"OPS_SEARCH_WARRANT_STAFF.MANAGEMENT_OFFICE_NAME as STAFF_MANAGEMENT_OFFICE_NAME," +
			    		"OPS_SEARCH_WARRANT_STAFF.MANAGEMENT_OFFICE_SHORT_NAME as STAFF_MANAGEMENT_OFFICE_SHORT_NAME," +
			    		"OPS_SEARCH_WARRANT_STAFF.MANAGEMENT_POS_NAME as STAFF_MANAGEMENT_POS_NAME," +
			    		"OPS_SEARCH_WARRANT_STAFF.REPRESENT_OFFICE_NAME as STAFF_REPRESENT_OFFICE_NAME," +
			    		"OPS_SEARCH_WARRANT_STAFF.REPRESENT_OFFICE_SHORT_NAME as STAFF_REPRESENT_OFFICE_SHORT_NAME," +
			    		"OPS_SEARCH_WARRANT_STAFF.REPRESENT_POS_NAME as STAFF_REPRESENT_POS_NAME," + 
			    		"OPS_SEARCH_WARRANT_REQUEST.REQUEST_DATE_FROM," +
			    		"OPS_SEARCH_WARRANT_REQUEST.PRESENT_COURT_NAME," +
			    		"OPS_SEARCH_WARRANT_REQUEST.JUDGE_TITLE_NAME_TH," +
			    		"OPS_SEARCH_WARRANT_REQUEST.JUDGE_TITLE_NAME_EN," +
			    		"OPS_SEARCH_WARRANT_REQUEST.JUDGE_TITLE_SHORT_NAME_TH," +
			    		"OPS_SEARCH_WARRANT_REQUEST.JUDGE_TITLE_SHORT_NAME_EN," +
			    		"OPS_SEARCH_WARRANT_REQUEST.JUDGE_FIRST_NAME," +
			    		"OPS_SEARCH_WARRANT_REQUEST.JUDGE_LAST_NAME," +
			    		"OPS_SEARCH_WARRANT_REQUEST.PERSON_TITLE_NAME_TH," +
			    		"OPS_SEARCH_WARRANT_REQUEST.PERSON_TITLE_NAME_EN," +
			    		"OPS_SEARCH_WARRANT_REQUEST.PERSON_TITLE_SHORT_NAME_TH," +
			    		"OPS_SEARCH_WARRANT_REQUEST.PERSON_TITLE_SHORT_NAME_EN," +
			    		"OPS_SEARCH_WARRANT_REQUEST.PERSON_FIRST_NAME," +
			    		"OPS_SEARCH_WARRANT_REQUEST.PERSON_MIDDLE_NAME," +
			    		"OPS_SEARCH_WARRANT_REQUEST.PERSON_LAST_NAME," +
			    		"OPS_SEARCH_WARRANT_REQUEST.PERSON_OTHER_NAME," +
			    		"SW_SUB_DISTRICT.SUB_DISTRICT_NAME_TH," +
			    		"SW_SUB_DISTRICT.SUB_DISTRICT_NAME_EN," +
			    		"SW_DISTRICT.DISTRICT_NAME_TH," +
			    		"SW_DISTRICT.DISTRICT_NAME_EN," +
			    		"SW_PROVINCE.PROVINCE_NAME_TH," +
			    		"SW_PROVINCE.PROVINCE_NAME_EN," +
			    		"OPS_SEARCH_WARRANT.SEARCH_WARRANT_NO," +
						"to_char(OPS_SEARCH_WARRANT.SEARCH_WARRANT_NO_YEAR,'"+Pattern.FORMAT_DATETIME+"') as SEARCH_WARRANT_NO_YEAR," +
						"to_char(OPS_SEARCH_WARRANT.SEARCH_WARRANT_DATE,'"+Pattern.FORMAT_DATETIME+"') as SEARCH_WARRANT_DATE," +
			    		"to_char(OPS_SEARCH_WARRANT_CONSIDER.CONSIDER_UNDECIDE_NO_YEAR,'"+Pattern.FORMAT_DATETIME+"') as CONSIDER_UNDECIDE_NO_YEAR," +
                        "OPS_SEARCH_WARRANT_CONSIDER.CONSIDER_UNDECIDE_NO," +
			    		"OPS_SEARCH_WARRANT_CONSIDER.CONSIDER_DECIDE_NO," +
						"to_char(OPS_SEARCH_WARRANT_CONSIDER.CONSIDER_DECIDE_NO_YEAR,'"+Pattern.FORMAT_DATETIME+"') as CONSIDER_DECIDE_NO_YEAR," +
						"to_char(OPS_SEARCH_WARRANT_CONSIDER.CONSIDER_DATE,'"+Pattern.FORMAT_DATETIME+"') as CONSIDER_DATE" +
			    		" from OPS_SEARCH_WARRANT" +
			    		" LEFT JOIN OPS_SEARCH_WARRANT_CONSIDER ON OPS_SEARCH_WARRANT.CONSIDER_ID = OPS_SEARCH_WARRANT_CONSIDER.CONSIDER_ID AND OPS_SEARCH_WARRANT_CONSIDER.IS_ACTIVE = 1" +
			    		" LEFT JOIN OPS_SEARCH_WARRANT_REQUEST ON OPS_SEARCH_WARRANT_CONSIDER.REQUEST_ID = OPS_SEARCH_WARRANT_REQUEST.REQUEST_ID AND OPS_SEARCH_WARRANT_REQUEST.IS_ACTIVE = 1" +
			    		" LEFT JOIN OPS_SEARCH_WARRANT_STAFF ON OPS_SEARCH_WARRANT_REQUEST.REQUEST_ID = OPS_SEARCH_WARRANT_STAFF.REQUEST_ID AND OPS_SEARCH_WARRANT_STAFF.IS_ACTIVE = 1 AND OPS_SEARCH_WARRANT_STAFF.CONTRIBUTOR_ID = 1" +
			    		" LEFT JOIN MAS_SUB_DISTRICT SW_SUB_DISTRICT ON OPS_SEARCH_WARRANT_REQUEST.SUB_DISTRICT_ID = SW_SUB_DISTRICT.SUB_DISTRICT_ID" +
			    		" LEFT JOIN MAS_DISTRICT SW_DISTRICT ON SW_SUB_DISTRICT.DISTRICT_ID = SW_DISTRICT.DISTRICT_ID" +
			    		" LEFT JOIN MAS_PROVINCE SW_PROVINCE ON SW_DISTRICT.PROVINCE_ID = SW_PROVINCE.PROVINCE_ID" +
			    		" where OPS_SEARCH_WARRANT.ARREST_ID = '"+ARREST_ID+"' AND OPS_SEARCH_WARRANT.IS_ACTIVE = 1");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestSearchWarrant> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestSearchWarrant mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestSearchWarrant item = new ArrestSearchWarrant();
				item.setSEARCH_WARRANT_ID(rs.getInt("SEARCH_WARRANT_ID"));
				item.setARREST_ID(rs.getInt("ARREST_ID"));
				item.setREQUEST_CODE(rs.getString("REQUEST_CODE"));
				item.setREQUEST_NO(rs.getString("REQUEST_NO"));
				item.setREQUEST_NO_YEAR(rs.getString("REQUEST_NO_YEAR"));
				item.setREQUEST_DATE(rs.getString("REQUEST_DATE"));
				item.setSTAFF_TITLE_NAME_TH(rs.getString("STAFF_TITLE_NAME_TH"));
				item.setSTAFF_TITLE_NAME_EN(rs.getString("STAFF_TITLE_NAME_EN"));
				item.setSTAFF_TITLE_SHORT_NAME_TH(rs.getString("STAFF_TITLE_SHORT_NAME_TH"));
				item.setSTAFF_TITLE_SHORT_NAME_EN(rs.getString("STAFF_TITLE_SHORT_NAME_EN"));
				item.setSTAFF_FIRST_NAME(rs.getString("STAFF_FIRST_NAME"));
				item.setSTAFF_LAST_NAME(rs.getString("STAFF_LAST_NAME"));
				item.setSTAFF_OPERATION_OFFICE_NAME(rs.getString("STAFF_OPERATION_OFFICE_NAME"));
				item.setSTAFF_OPERATION_OFFICE_SHORT_NAME(rs.getString("STAFF_OPERATION_OFFICE_SHORT_NAME"));
				item.setSTAFF_OPREATION_POS_NAME(rs.getString("STAFF_OPREATION_POS_NAME"));
				item.setSTAFF_MANAGEMENT_OFFICE_NAME(rs.getString("STAFF_MANAGEMENT_OFFICE_NAME"));
				item.setSTAFF_MANAGEMENT_OFFICE_SHORT_NAME(rs.getString("STAFF_MANAGEMENT_OFFICE_SHORT_NAME"));
				item.setSTAFF_MANAGEMENT_POS_NAME(rs.getString("STAFF_MANAGEMENT_POS_NAME"));
				item.setSTAFF_REPRESENT_OFFICE_NAME(rs.getString("STAFF_REPRESENT_OFFICE_NAME"));
				item.setSTAFF_REPRESENT_OFFICE_SHORT_NAME(rs.getString("STAFF_REPRESENT_OFFICE_SHORT_NAME"));
				item.setSTAFF_REPRESENT_POS_NAME(rs.getString("STAFF_REPRESENT_POS_NAME"));

				item.setREQUEST_DATE_FROM(rs.getString("REQUEST_DATE_FROM"));
				item.setPRESENT_COURT_NAME(rs.getString("PRESENT_COURT_NAME"));
				item.setJUDGE_TITLE_NAME_TH(rs.getString("JUDGE_TITLE_NAME_TH"));
				item.setJUDGE_TITLE_NAME_EN(rs.getString("JUDGE_TITLE_NAME_EN"));
				item.setJUDGE_TITLE_SHORT_NAME_TH(rs.getString("JUDGE_TITLE_SHORT_NAME_TH"));
				item.setJUDGE_TITLE_SHORT_NAME_EN(rs.getString("JUDGE_TITLE_SHORT_NAME_EN"));
				item.setJUDGE_FIRST_NAME(rs.getString("JUDGE_FIRST_NAME"));
				item.setJUDGE_LAST_NAME(rs.getString("JUDGE_LAST_NAME"));
				item.setPERSON_TITLE_NAME_TH(rs.getString("PERSON_TITLE_NAME_TH"));
				item.setPERSON_TITLE_NAME_EN(rs.getString("PERSON_TITLE_NAME_EN"));
				item.setPERSON_TITLE_SHORT_NAME_TH(rs.getString("PERSON_TITLE_SHORT_NAME_TH"));
				item.setPERSON_TITLE_SHORT_NAME_EN(rs.getString("PERSON_TITLE_SHORT_NAME_EN"));
				item.setPERSON_FIRST_NAME(rs.getString("PERSON_FIRST_NAME"));
				item.setPERSON_MIDDLE_NAME(rs.getString("PERSON_MIDDLE_NAME"));
				item.setPERSON_LAST_NAME(rs.getString("PERSON_LAST_NAME"));
				item.setPERSON_OTHER_NAME(rs.getString("PERSON_OTHER_NAME"));
				item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
				item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
				item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
				item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
				item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
				item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));
				item.setSEARCH_WARRANT_NO(rs.getInt("SEARCH_WARRANT_NO"));
				item.setSEARCH_WARRANT_NO_YEAR(rs.getString("SEARCH_WARRANT_NO_YEAR"));
				item.setSEARCH_WARRANT_DATE(rs.getString("SEARCH_WARRANT_DATE"));
				item.setCONSIDER_UNDECIDE_NO(rs.getInt("CONSIDER_UNDECIDE_NO"));
				item.setCONSIDER_UNDECIDE_NO_YEAR(rs.getString("CONSIDER_UNDECIDE_NO_YEAR"));
				item.setCONSIDER_DECIDE_NO(rs.getInt("CONSIDER_DECIDE_NO"));
				item.setCONSIDER_DECIDE_NO_YEAR(rs.getString("CONSIDER_DECIDE_NO_YEAR"));
				item.setCONSIDER_DATE(rs.getString("CONSIDER_DATE"));
				return item;
			}
		});

		return dataList;

	}
	
	protected List<ArrestMasPersonRelationship> getArrestMasPersonRelationship(int PERSON_ID ,String TEXT_SEARCH) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("    select DISTINCT" +
						"    MAS_PERSON_RELATIONSHIP.PERSON_RELATIONSHIP_ID," +
						"    MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID," +
						"    MAS_PERSON_RELATIONSHIP.PERSON_ID," +
						"    DECODE(MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID, 1, 'บิดา',  2, 'มารดา' , 3 ,'ตัวแทน') as RELATIONSHIP_NAME," +
						"    MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH," +
						"    MAS_PERSON_RELATIONSHIP.TITLE_NAME_EN," +
						"    MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_TH," +
						"    MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_EN," +
						"    MAS_PERSON_RELATIONSHIP.FIRST_NAME," +
						"    MAS_PERSON_RELATIONSHIP.MIDDLE_NAME," +
						"    MAS_PERSON_RELATIONSHIP.LAST_NAME," +
						"    MAS_PERSON_RELATIONSHIP.OTHER_NAME" +
						"    FROM  MAS_PERSON_RELATIONSHIP " +
						"    WHERE MAS_PERSON_RELATIONSHIP.PERSON_ID = '"+PERSON_ID+"' " +
						"    AND MAS_PERSON_RELATIONSHIP.IS_ACTIVE = 1" +
						"    AND MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID in ('1','2','3')" );
						//"    AND LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+TEXT_SEARCH+"%',' ',''))");

		sqlBuilder.append(" order by MAS_PERSON_RELATIONSHIP.FIRST_NAME asc, MAS_PERSON_RELATIONSHIP.LAST_NAME asc");
		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestMasPersonRelationship> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestMasPersonRelationship mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestMasPersonRelationship item = new ArrestMasPersonRelationship();
				item.setPERSON_RELATIONSHIP_ID(rs.getInt("PERSON_RELATIONSHIP_ID"));
				item.setRELATIONSHIP_ID(rs.getInt("RELATIONSHIP_ID"));
				item.setPERSON_ID(rs.getInt("PERSON_ID"));
				item.setRELATIONSHIP_NAME(rs.getString("RELATIONSHIP_NAME"));
				item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
				item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
				item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
				item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
				item.setFIRST_NAME(rs.getString("FIRST_NAME"));
				item.setMIDDLE_NAME(rs.getString("MIDDLE_NAME"));
				item.setLAST_NAME(rs.getString("LAST_NAME"));
				item.setOTHER_NAME(rs.getString("OTHER_NAME"));
				//item.setRELATIONSHIP_NAME_TH(rs.getString("RELATIONSHIP_NAME_TH"));

				return item;
			}
		});

		return dataList;

	}
	
	protected List<ArrestMasPersonRelationship> getArrestMasPersonRelationshipCon(int PERSON_ID ,String  fatherName,String motherName) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("SELECT" +
			    		" PERSON_RELATIONSHIP_ID," +
			    		"RELATIONSHIP_ID," +
			    		"PERSON_ID," +
			    		"TITLE_NAME_TH||' '||FIRST_NAME||' '||MIDDLE_NAME||' '||LAST_NAME as RELATIONSHIP_NAME," +
			    		"TITLE_NAME_TH," +
			    		"TITLE_NAME_EN," +
			    		"TITLE_SHORT_NAME_TH," +
			    		"TITLE_SHORT_NAME_EN," +
			    		"FIRST_NAME," +
			    		"MIDDLE_NAME," +
			    		"LAST_NAME," +
			    		"OTHER_NAME" +
			    		" FROM MAS_PERSON_RELATIONSHIP" +
			    		" WHERE IS_ACTIVE = 1 AND PERSON_ID = '"+PERSON_ID+"' " );
		
		if((fatherName != null && !"".equals(fatherName)) || (motherName != null && !"".equals(motherName)) ) {
			sqlBuilder.append(" AND " +
		    		"(" +
		    		"  LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+fatherName+"%',' ',''))" +
		    		"  OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+motherName+"%',' ',''))" +
		    		")" );
		}
		

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestMasPersonRelationship> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestMasPersonRelationship mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestMasPersonRelationship item = new ArrestMasPersonRelationship();
				item.setPERSON_RELATIONSHIP_ID(rs.getInt("PERSON_RELATIONSHIP_ID"));
				item.setRELATIONSHIP_ID(rs.getInt("RELATIONSHIP_ID"));
				item.setPERSON_ID(rs.getInt("PERSON_ID"));
				item.setRELATIONSHIP_NAME(rs.getString("RELATIONSHIP_NAME"));
				item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
				item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
				item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
				item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
				item.setFIRST_NAME(rs.getString("FIRST_NAME"));
				item.setMIDDLE_NAME(rs.getString("MIDDLE_NAME"));
				item.setLAST_NAME(rs.getString("LAST_NAME"));
				item.setOTHER_NAME(rs.getString("OTHER_NAME"));

				return item;
			}
		});

		return dataList;

	}

	protected List<ArrestMasGuiltbase> getArrestMasGuiltbase(String GUILTBASE_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("     SELECT DISTINCT " +
						"     MAS_LAW_GUILTBASE.*," +
						"     MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME," +
						"     MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC," +
						"     MAS_LAW_GROUP_SECTION.SECTION_NAME," +
						"     MAS_LAW_GROUP_SECTION.SECTION_ID," +
						"     MAS_LAW_GROUP_SECTION.SECTION_DESC_1," +
						"     MAS_LAW_PENALTY.PENALTY_DESC " +
						"     FROM MAS_LAW_GUILTBASE " +
						"     INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID " +
						"     INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID " +
						"     INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID " +
						"     LEFT JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID AND MAS_LAW_PENALTY.IS_ACTIVE = 1 " +
						"     WHERE MAS_LAW_GUILTBASE.IS_ACTIVE = 1 AND MAS_LAW_GROUP_SUBSECTION_RULE.IS_ACTIVE = 1 AND MAS_LAW_GROUP_SUBSECTION.IS_ACTIVE = 1 " +
						"     AND MAS_LAW_GROUP_SECTION.IS_ACTIVE = 1" +
						"     AND MAS_LAW_GUILTBASE.GUILTBASE_ID IN ("+GUILTBASE_ID+")" );


		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestMasGuiltbase> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestMasGuiltbase mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestMasGuiltbase item = new ArrestMasGuiltbase();
				item.setGUILTBASE_ID(rs.getInt("GUILTBASE_ID"));
				item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
				item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
				item.setFINE(rs.getString("FINE"));
				item.setIS_PROVE(rs.getInt("IS_PROVE"));
				item.setIS_COMPARE(rs.getInt("IS_COMPARE"));
				item.setREMARK(rs.getString("REMARK"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

				item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
				item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
				item.setSECTION_NAME(rs.getString("SECTION_NAME"));
				item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
				item.setSECTION_DESC_1(rs.getString("SECTION_DESC_1"));
				item.setSECTION_ID(rs.getInt("SECTION_ID"));

				return item;
			}
		});

		return dataList;

	}

}
