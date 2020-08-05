package com.xcs.phase2.dao.lawsult;

import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.lawsult.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;




public class LawsultExt {

	private static final Logger log = LoggerFactory.getLogger(LawsultExt.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	protected String getSequences(String strSql) {
		log.info("[SQL]  : " + strSql);
		return getJdbcTemplate().queryForObject(strSql, String.class);
	}
	
	protected List<LawsuitArrestIndictmentProduct> getLawsuitArrestIndictmentProduct(int INDICTMENT_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_INDICTMENT_ID," +
			    		"OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID," +
			    		"OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID," +
			    		"OPS_ARREST_INDICTMENT_PRODUCT.SIZES," +
			    		"OPS_ARREST_INDICTMENT_PRODUCT.SIZES_UNIT," +
			    		"OPS_ARREST_INDICTMENT_PRODUCT.QUANTITY," +
			    		"OPS_ARREST_INDICTMENT_PRODUCT.QUANTITY_UNIT," +
			    		"OPS_ARREST_INDICTMENT_PRODUCT.VOLUMN," +
			    		"OPS_ARREST_INDICTMENT_PRODUCT.VOLUMN_UNIT," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_GROUP_NAME," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_CATEGORY_NAME," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_TYPE_NAME," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_SUBTYPE_NAME," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_SUBSETTYPE_NAME," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_BRAND_NAME_TH," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_BRAND_NAME_EN," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_SUBBRAND_NAME_TH," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_SUBBRAND_NAME_EN," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_MODEL_NAME_TH," +
			    		"OPS_ARREST_PRODUCT.PRODUCT_MODEL_NAME_EN," +

						"OPS_ARREST_PRODUCT.LICENSE_PLATE," +
						"OPS_ARREST_PRODUCT.ENGINE_NO," +
						"OPS_ARREST_PRODUCT.CHASSIS_NO," +

						"OPS_ARREST_PRODUCT.SUGAR," +
						"OPS_ARREST_PRODUCT.CO2," +
						"OPS_ARREST_PRODUCT.DEGREE," +

						"OPS_ARREST_PRODUCT.PRODUCT_CODE," +
						"OPS_ARREST_PRODUCT.PRODUCT_GROUP_ID," +
						"OPS_ARREST_PRODUCT.PRODUCT_GROUP_CODE," +
						"OPS_ARREST_PRODUCT.PRODUCT_CATEGORY_ID," +
						"OPS_ARREST_PRODUCT.PRODUCT_DESC," +
						"OPS_ARREST_PRODUCT.PRICE," +
						"OPS_ARREST_PRODUCT.REMARK," +
						"OPS_ARREST_PRODUCT.PRODUCT_CATEGORY_CODE" +

			    		" from " +
			    		" OPS_ARREST_INDICTMENT_PRODUCT  " +
			    		" INNER JOIN OPS_ARREST_PRODUCT ON OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID = OPS_ARREST_PRODUCT.PRODUCT_ID" +
			    		" AND OPS_ARREST_PRODUCT.IS_ACTIVE = 1" +
			    		" AND OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE = 1" +
			    		" AND OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID in (select max(INDICTMENT_DETAIL_ID) as INDICTMENT_DETAIL_ID  from OPS_ARREST_INDICTMENT_DETAIL where INDICTMENT_ID = '"+INDICTMENT_ID+"') ");




		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<LawsuitArrestIndictmentProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public LawsuitArrestIndictmentProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
				LawsuitArrestIndictmentProduct item = new LawsuitArrestIndictmentProduct();
				item.setPRODUCT_INDICTMENT_ID(rs.getInt("PRODUCT_INDICTMENT_ID"));
				item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
				item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
				item.setSIZES(rs.getFloat("SIZES"));
				item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
				item.setQUANTITY(rs.getFloat("QUANTITY"));
				item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
				item.setVOLUMN(rs.getFloat("VOLUMN"));

				item.setSUGAR(rs.getFloat("SUGAR"));
				item.setCO2(rs.getFloat("CO2"));
				item.setDEGREE(rs.getFloat("DEGREE"));

				item.setVOLUMN_UNIT(rs.getString("VOLUMN_UNIT"));
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

				item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
				item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
				item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
				item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
				item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
				item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));

				item.setLICENSE_PLATE(rs.getString("LICENSE_PLATE"));
				item.setENGINE_NO(rs.getString("ENGINE_NO"));
				item.setCHASSIS_NO(rs.getString("CHASSIS_NO"));

				item.setPRICE(rs.getFloat("PRICE"));
				item.setREMARK(rs.getString("REMARK"));
				return item;
			}
		});

		return dataList;

	}
	
	protected List<LawsuitArrestIndictmentDetail> getLawsuitArrestIndictmentDetail(int INDICTMENT_ID, int PERSON_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID," +
			    		"OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID," +
			    		"OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID," +
			    		"MAS_PERSON.PERSON_ID," +
			    		"MAS_PERSON.COMPANY_REGISTRATION_NO," +
			    		"MAS_PERSON.EXCISE_REGISTRATION_NO," +
			    		"MAS_PERSON.ID_CARD," +
			    		"MAS_PERSON.PASSPORT_NO," +
			    		"MAS_PERSON.PERSON_TYPE," +
			    		"MAS_PERSON.ENTITY_TYPE," +
			    		"MAS_PERSON.TITLE_NAME_TH," +
			    		"MAS_PERSON.TITLE_NAME_EN," +
			    		"MAS_PERSON.TITLE_SHORT_NAME_TH," +
			    		"MAS_PERSON.TITLE_SHORT_NAME_EN," +
			    		"MAS_PERSON.FIRST_NAME," +
			    		"MAS_PERSON.MIDDLE_NAME," +
			    		"MAS_PERSON.LAST_NAME," +
						"OPS_ARREST_LAWBREAKER.COMPANY_NAME," +
						"OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
			    		"MAS_PERSON.OTHER_NAME," +
			    		"MAS_PERSON.MISTREAT_NO" +
			    		" from OPS_ARREST_INDICTMENT_DETAIL" +
			    		" LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
			    		" AND OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
			    		" LEFT JOIN MAS_PERSON ON OPS_ARREST_LAWBREAKER.PERSON_ID = MAS_PERSON.PERSON_ID" +
			    		" AND MAS_PERSON.IS_ACTIVE = 1" +
			    		" where OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1 and OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID = '"+INDICTMENT_ID+"'");

        if (PERSON_ID != 0) {
            sqlBuilder.append("AND MAS_PERSON.PERSON_ID = '"+PERSON_ID+"' ");
        }

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<LawsuitArrestIndictmentDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public LawsuitArrestIndictmentDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				LawsuitArrestIndictmentDetail item = new LawsuitArrestIndictmentDetail();
				item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
				item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
				item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
				item.setPERSON_ID(rs.getInt("PERSON_ID"));
				item.setCOMPANY_REGISTRATION_NO(rs.getString("COMPANY_REGISTRATION_NO"));
				item.setEXCISE_REGISTRATION_NO(rs.getString("EXCISE_REGISTRATION_NO"));
				item.setID_CARD(rs.getString("ID_CARD"));
				item.setPASSPORT_NO(rs.getString("PASSPORT_NO"));
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
				item.setMISTREAT_NO(rs.getInt("MISTREAT_NO"));
				item.setCOMPANY_NAME(rs.getString("COMPANY_NAME"));
				return item;
			}
		});

		return dataList;

	}
	
	protected List<LawsuitNotice> getLawsuitNotice(int ARREST_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append(" select NOTICE_ID , ARREST_ID from OPS_NOTICE where ARREST_ID = '"+ARREST_ID+"' ");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<LawsuitNotice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public LawsuitNotice mapRow(ResultSet rs, int rowNum) throws SQLException {
				LawsuitNotice item = new LawsuitNotice();
				item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
				item.setARREST_ID(rs.getInt("ARREST_ID"));
				return item;
			}
		});

		return dataList;

	}
	
	protected List<LawsuitStaff> getLawsuitStaff(int LAWSUIT_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"STAFF_ID," +
			    		"LAWSUIT_ID," +
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
			    		"from OPS_LAWSUIT_STAFF  where IS_ACTIVE = 1 ");
				sqlBuilder.append("and LAWSUIT_ID = '"+LAWSUIT_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<LawsuitStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public LawsuitStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
				LawsuitStaff item = new LawsuitStaff();
				item.setSTAFF_ID(rs.getInt("STAFF_ID"));
				item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
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
	
	protected List<LawsuitDetail> getLawsuitDetail(int LAWSUIT_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"LAWSUIT_DETAIL_ID," +
			    		"LAWSUIT_ID," +
			    		"INDICTMENT_DETAIL_ID," +
			    		"COURT_ID," +
			    		"LAWSUIT_TYPE," +
			    		"LAWSUIT_END," +
			    		"COURT_NAME," +
			    		"UNDECIDE_NO_1," +
						"to_char(UNDECIDE_NO_YEAR_1,'"+ Pattern.FORMAT_DATETIME+"') as UNDECIDE_NO_YEAR_1," +
			    		"DECIDE_NO_1," +
                        "to_char(DECIDE_NO_YEAR_1,'"+Pattern.FORMAT_DATETIME+"') as DECIDE_NO_YEAR_1," +
			    		"UNDECIDE_NO_2," +
                        "to_char(UNDECIDE_NO_YEAR_2,'"+Pattern.FORMAT_DATETIME+"') as UNDECIDE_NO_YEAR_2," +
			    		"DECIDE_NO_2," +
                        "to_char(DECIDE_NO_YEAR_2,'"+Pattern.FORMAT_DATETIME+"') as DECIDE_NO_YEAR_2," +
			    		"JUDGEMENT_NO," +
                        "to_char(JUDGEMENT_NO_YEAR,'"+Pattern.FORMAT_DATETIME+"') as JUDGEMENT_NO_YEAR," +
                        "to_char(JUDGEMENT_DATE,'"+Pattern.FORMAT_DATETIME+"') as JUDGEMENT_DATE," +
			    		"IS_IMPRISON," +
			    		"IMPRISON_TIME," +
			    		"IMPRISON_TIME_UNIT," +
			    		"IS_FINE," +
			    		"FINE," +
			    		"IS_PAYONCE," +
                        "to_char(FINE_DATE,'"+Pattern.FORMAT_DATETIME+"') as FINE_DATE," +
			    		"PAYMENT_PERIOD," +
			    		"PAYMENT_PERIOD_DUE," +
			    		"PAYMENT_PERIOD_DUE_UNIT," +
						"PAYMENT_CHANNEL," +
						"PAYMENT_BANK," +
						"PAYMENT_REF_NO," +
						"to_char(PAYMENT_DATE,'"+Pattern.FORMAT_DATETIME+"') as PAYMENT_DATE," +
						"IS_DISMISS," +
						"UNJUDGEMENT_NO," +
						"to_char(UNJUDGEMENT_NO_YEAR,'"+Pattern.FORMAT_DATETIME+"') as UNJUDGEMENT_NO_YEAR," +
			    		"IS_ACTIVE " +
			    		"from OPS_LAWSUIT_DETAIL  where IS_ACTIVE = 1 ");
				sqlBuilder.append("and LAWSUIT_ID = '"+LAWSUIT_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<LawsuitDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public LawsuitDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				LawsuitDetail item = new LawsuitDetail();
				item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
				item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
				item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
				item.setCOURT_ID(rs.getInt("COURT_ID"));
				item.setLAWSUIT_TYPE(rs.getInt("LAWSUIT_TYPE"));
				item.setLAWSUIT_END(rs.getInt("LAWSUIT_END"));
				item.setCOURT_NAME(rs.getString("COURT_NAME"));
				item.setUNDECIDE_NO_1(rs.getInt("UNDECIDE_NO_1"));
				item.setUNDECIDE_NO_YEAR_1(rs.getString("UNDECIDE_NO_YEAR_1"));
				item.setDECIDE_NO_1(rs.getInt("DECIDE_NO_1"));
				item.setDECIDE_NO_YEAR_1(rs.getString("DECIDE_NO_YEAR_1"));
				item.setUNDECIDE_NO_2(rs.getInt("UNDECIDE_NO_2"));
				item.setUNDECIDE_NO_YEAR_2(rs.getString("UNDECIDE_NO_YEAR_2"));
				item.setDECIDE_NO_2(rs.getInt("DECIDE_NO_2"));
				item.setDECIDE_NO_YEAR_2(rs.getString("DECIDE_NO_YEAR_2"));
				item.setJUDGEMENT_NO(rs.getInt("JUDGEMENT_NO"));
				item.setJUDGEMENT_NO_YEAR(rs.getString("JUDGEMENT_NO_YEAR"));
				item.setJUDGEMENT_DATE(rs.getString("JUDGEMENT_DATE"));
				item.setIS_IMPRISON(rs.getInt("IS_IMPRISON"));
				item.setIMPRISON_TIME(rs.getString("IMPRISON_TIME"));
				item.setIMPRISON_TIME_UNIT(rs.getInt("IMPRISON_TIME_UNIT"));
				item.setIS_FINE(rs.getInt("IS_FINE"));
				item.setFINE(rs.getFloat("FINE"));
				item.setIS_PAYONCE(rs.getInt("IS_PAYONCE"));
				item.setFINE_DATE(rs.getString("FINE_DATE"));
				item.setPAYMENT_PERIOD(rs.getInt("PAYMENT_PERIOD"));
				item.setPAYMENT_PERIOD_DUE(rs.getInt("PAYMENT_PERIOD_DUE"));
				item.setPAYMENT_PERIOD_DUE_UNIT(rs.getInt("PAYMENT_PERIOD_DUE_UNIT"));
				item.setPAYMENT_CHANNEL(rs.getInt("PAYMENT_CHANNEL"));
				item.setPAYMENT_BANK(rs.getInt("PAYMENT_BANK"));
				item.setPAYMENT_REF_NO(rs.getString("PAYMENT_REF_NO"));
				item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
				item.setIS_DISMISS(rs.getInt("IS_DISMISS"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				item.setUNJUDGEMENT_NO(rs.getString("UNJUDGEMENT_NO"));
				item.setUNJUDGEMENT_NO_YEAR(rs.getString("UNJUDGEMENT_NO_YEAR"));
				item.setLawsuitPayment(getLawsuitPayment(rs.getInt("LAWSUIT_DETAIL_ID")));

				return item;
			}
		});

		return dataList;

	}
	
	protected List<LawsuitPayment> getLawsuitPayment(int LAWSUIT_DETAIL_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("select " +
						"PAYMENT_ID," +
						"LAWSUIT_DETAIL_ID," +
						"COMPARE_DETAIL_ID," +
						"FINE_TYPE," +
						"FINE," +
						"PAYMENT_PERIOD_NO," +
						"to_char(PAYMENT_DATE,'"+Pattern.FORMAT_DATETIME+"') as PAYMENT_DATE," +
						"IS_REQUEST_REWARD," +
			    		"IS_ACTIVE " +
			    		"from OPS_PAYMENT  where IS_ACTIVE = 1 ");
				sqlBuilder.append("and LAWSUIT_DETAIL_ID = '"+LAWSUIT_DETAIL_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<LawsuitPayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public LawsuitPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
				LawsuitPayment item = new LawsuitPayment();
				item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
				item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
				item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
				item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
				item.setFINE(rs.getFloat("FINE"));
				item.setPAYMENT_PERIOD_NO(rs.getInt("PAYMENT_PERIOD_NO"));
				item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
				item.setIS_REQUEST_REWARD(rs.getInt("IS_REQUEST_REWARD"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				
				item.setLawsuitPaymentDetail(getLawsuitPaymentDetail(rs.getInt("PAYMENT_ID")));
				return item;
			}
		});

		return dataList;

	}
	
	protected List<LawsuitPaymentDetail> getLawsuitPaymentDetail(int PAYMENT_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("select " +
						"PAYMENT_DETAIL_ID," +
						"PAYMENT_ID," +
						"NOTICE_ID," +
						"IS_REQUEST_BRIBE," +
			    		"IS_ACTIVE " +
			    		"from OPS_PAYMENT_DETAIL  where IS_ACTIVE = 1 ");
				sqlBuilder.append("and PAYMENT_ID = '"+PAYMENT_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<LawsuitPaymentDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public LawsuitPaymentDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				LawsuitPaymentDetail item = new LawsuitPaymentDetail();
				item.setPAYMENT_DETAIL_ID(rs.getInt("PAYMENT_DETAIL_ID"));
				item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
				item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
				item.setIS_REQUEST_BRIBE(rs.getInt("IS_REQUEST_BRIBE"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				return item;
			}
		});

		return dataList;

	}

	protected List<LawsuitLocale> getLawsuitLocale(int ARREST_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    select " +
						"    OPS_ARREST_LOCALE.*," +
						"    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH," +
						"    MAS_DISTRICT.DISTRICT_NAME_TH," +
						"    MAS_PROVINCE.PROVINCE_NAME_TH" +
						"    from OPS_ARREST_LOCALE" +
						"    INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
						"    INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID" +
						"    INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID" +
						"    where OPS_ARREST_LOCALE.ARREST_ID = "+ARREST_ID+" and OPS_ARREST_LOCALE.IS_ACTIVE = 1");


		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<LawsuitLocale> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public LawsuitLocale mapRow(ResultSet rs, int rowNum) throws SQLException {
				LawsuitLocale item = new LawsuitLocale();
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
				item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
				item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));

				return item;
			}
		});

		return dataList;

	}

	public List<LawsuitLawbreaker> getLawsuitLawbreaker(int INDICTMENT_ID) {
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
		List<LawsuitLawbreaker> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public LawsuitLawbreaker mapRow(ResultSet rs, int rowNum) throws SQLException {
				LawsuitLawbreaker item = new LawsuitLawbreaker();
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
//				item.setLAWSUIT_TYPE(rs.getInt("LAWSUIT_TYPE"));
//				item.setLAWSUIT_END(rs.getInt("LAWSUIT_END"));
				return item;
			}
		});

		return dataList;
	}

}
