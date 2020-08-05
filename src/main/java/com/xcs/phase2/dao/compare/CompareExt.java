package com.xcs.phase2.dao.compare;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.arrest.ArrestLawbreaker;
import com.xcs.phase2.model.compare.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CompareExt {

	private static final Logger log = LoggerFactory.getLogger(CompareExt.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	protected String getSequences(String strSql) {
		log.info("[SQL]  : " + strSql);
		return getJdbcTemplate().queryForObject(strSql, String.class);
	}

	protected List<CompareMapping> getCompareMapping(int COMPARE_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("select " +
						"COMPARE_MAPPING_ID," +
						"COMPARE_ID," +
						"INDICTMENT_DETAIL_ID," +
						"PAST_LAWSUIT_ID," +
						"IS_EVER_WRONG," +
						"IS_ACTIVE " +
						"from OPS_COMPARE_MAPPING  where IS_ACTIVE = 1 ");
		sqlBuilder.append("and COMPARE_ID = '"+COMPARE_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<CompareMapping> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public CompareMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompareMapping item = new CompareMapping();
				item.setCOMPARE_MAPPING_ID(rs.getInt("COMPARE_MAPPING_ID"));
				item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
				item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
				item.setPAST_LAWSUIT_ID(rs.getInt("PAST_LAWSUIT_ID"));
				item.setIS_EVER_WRONG(rs.getInt("IS_EVER_WRONG"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				item.setCompareDetail(getCompareDetail(rs.getInt("COMPARE_MAPPING_ID")));
				item.setCompareArrestIndictmentDetail(getCompareArrestIndictmentDetail(rs.getInt("INDICTMENT_DETAIL_ID")));
				return item;
			}
		});

		return dataList;
	}

	protected List<CompareDetail> getCompareDetail(int COMPARE_MAPPING_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("select " +
						"COMPARE_DETAIL_ID," +
						"COMPARE_MAPPING_ID," +
						"RECEIPT_OFFICE_ID," +
						"APPROVE_OFFICE_ID," +
						"MISTREAT_NO," +
						"OLD_PAYMENT_FINE," +
						"PAYMENT_FINE," +
						"DIFFERENCE_PAYMENT_FINE," +
						"TREASURY_MONEY," +
						"BRIBE_MONEY," +
						"REWARD_MONEY," +
						"to_char(PAYMENT_FINE_DUE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as PAYMENT_FINE_DUE_DATE," +
						"to_char(PAYMENT_VAT_DUE_DATE,'"+Pattern.FORMAT_DATETIME+"') as PAYMENT_VAT_DUE_DATE," +
						"INSURANCE," +
						"GAURANTEE," +
						"to_char(PAYMENT_DATE,'"+Pattern.FORMAT_DATETIME+"') as PAYMENT_DATE," +
						"RECEIPT_TYPE," +
						"RECEIPT_BOOK_NO," +
						"RECEIPT_NO," +
						"RECEIPT_OFFICE_CODE," +
						"RECEIPT_OFFICE_NAME," +
						"APPROVE_OFFICE_CODE," +
						"APPROVE_OFFICE_NAME," +
						"to_char(APPROVE_DATE,'"+Pattern.FORMAT_DATETIME+"') as APPROVE_DATE," +
						"APPROVE_TYPE," +
						"COMMAND_NO," +
						"to_char(COMMAND_DATE,'"+Pattern.FORMAT_DATETIME+"') as COMMAND_DATE," +
						"REMARK_NOT_AGREE," +
						"REMARK_NOT_APPROVE," +
						"FACT," +
						"COMPARE_REASON," +
						"ADJUST_REASON," +
						"COMPARE_TYPE," +
						"IS_REQUEST," +
						"IS_TEMP_RELEASE," +
						"IS_INSURANCE," +
						"IS_GAURANTEE," +
						"IS_PAYMENT," +
						"IS_REVENUE," +
						"IS_AGREE," +
						"IS_APPROVE," +
						"IS_AUTHORITY," +
						"DID," +
						"IS_ACTIVE " +
						"from OPS_COMPARE_DETAIL  where IS_ACTIVE = 1 ");
		sqlBuilder.append("and COMPARE_MAPPING_ID = '"+COMPARE_MAPPING_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<CompareDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public CompareDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompareDetail item = new CompareDetail();
				item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
				item.setCOMPARE_MAPPING_ID(rs.getInt("COMPARE_MAPPING_ID"));
				item.setRECEIPT_OFFICE_ID(rs.getInt("RECEIPT_OFFICE_ID"));
				item.setAPPROVE_OFFICE_ID(rs.getInt("APPROVE_OFFICE_ID"));
				item.setMISTREAT_NO(rs.getInt("MISTREAT_NO"));
				item.setOLD_PAYMENT_FINE(rs.getFloat("OLD_PAYMENT_FINE"));
				item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
				item.setDIFFERENCE_PAYMENT_FINE(rs.getFloat("DIFFERENCE_PAYMENT_FINE"));
				item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
				item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
				item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
				item.setPAYMENT_FINE_DUE_DATE(rs.getString("PAYMENT_FINE_DUE_DATE"));
				item.setPAYMENT_VAT_DUE_DATE(rs.getString("PAYMENT_VAT_DUE_DATE"));
				item.setINSURANCE(rs.getString("INSURANCE"));
				item.setGAURANTEE(rs.getString("GAURANTEE"));
				item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
				item.setRECEIPT_TYPE(rs.getInt("RECEIPT_TYPE"));
				item.setRECEIPT_BOOK_NO(rs.getInt("RECEIPT_BOOK_NO"));
				item.setRECEIPT_NO(rs.getString("RECEIPT_NO"));
				item.setRECEIPT_OFFICE_CODE(rs.getString("RECEIPT_OFFICE_CODE"));
				item.setRECEIPT_OFFICE_NAME(rs.getString("RECEIPT_OFFICE_NAME"));
				item.setAPPROVE_OFFICE_CODE(rs.getString("APPROVE_OFFICE_CODE"));
				item.setAPPROVE_OFFICE_NAME(rs.getString("APPROVE_OFFICE_NAME"));
				item.setAPPROVE_DATE(rs.getString("APPROVE_DATE"));
				item.setAPPROVE_TYPE(rs.getInt("APPROVE_TYPE"));
				item.setCOMMAND_NO(rs.getString("COMMAND_NO"));
				item.setCOMMAND_DATE(rs.getString("COMMAND_DATE"));
				item.setREMARK_NOT_AGREE(rs.getString("REMARK_NOT_AGREE"));
				item.setREMARK_NOT_APPROVE(rs.getString("REMARK_NOT_APPROVE"));
				item.setFACT(rs.getString("FACT"));
				item.setCOMPARE_REASON(rs.getString("COMPARE_REASON"));
				item.setADJUST_REASON(rs.getString("ADJUST_REASON"));
				item.setCOMPARE_TYPE(rs.getInt("COMPARE_TYPE"));
				item.setIS_REQUEST(rs.getInt("IS_REQUEST"));
				item.setIS_TEMP_RELEASE(rs.getInt("IS_TEMP_RELEASE"));
				item.setIS_INSURANCE(rs.getInt("IS_INSURANCE"));
				item.setIS_GAURANTEE(rs.getInt("IS_GAURANTEE"));
				item.setIS_PAYMENT(rs.getInt("IS_PAYMENT"));
				item.setIS_REVENUE(rs.getInt("IS_REVENUE"));
				item.setIS_AGREE(rs.getInt("IS_AGREE"));
				item.setIS_APPROVE(rs.getInt("IS_APPROVE"));
				item.setIS_AUTHORITY(rs.getInt("IS_AUTHORITY"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				item.setDID(rs.getString("DID"));
				item.setCompareStaff(getCompareStaff(rs.getInt("COMPARE_DETAIL_ID")));
				item.setCompareDetailPayment(getCompareDetailPayment(rs.getInt("COMPARE_DETAIL_ID")));
				item.setCompareDetailFine(getCompareDetailFine(rs.getInt("COMPARE_DETAIL_ID")));
				item.setComparePayment(getComparePayment(rs.getInt("COMPARE_DETAIL_ID")));


				return item;
			}
		});

		return dataList;
	}

	protected List<CompareDetailPayment> getCompareDetailPayment(int COMPARE_DETAIL_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("select " +
						"PAYMENT_ID," +
						"COMPARE_DETAIL_ID," +
						"PAYMENT_TYPE," +
						"PAYMENT_FINE," +
						"REFFERENCE_NO," +
						"IS_ACTIVE " +
						"from OPS_COMPARE_DETAIL_PAYMENT  where IS_ACTIVE = 1 ");
		sqlBuilder.append("and COMPARE_DETAIL_ID = '"+COMPARE_DETAIL_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<CompareDetailPayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public CompareDetailPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompareDetailPayment item = new CompareDetailPayment();
				item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
				item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
				item.setPAYMENT_TYPE(rs.getInt("PAYMENT_TYPE"));
				item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
				item.setREFFERENCE_NO(rs.getString("REFFERENCE_NO"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				return item;
			}
		});

		return dataList;
	}

	protected List<CompareDetailFine> getCompareDetailFine(int COMPARE_DETAIL_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("select " +
						"OPS_COMPARE_DETAIL_FINE.*," +
						"OPS_PROVE_PRODUCT.PRODUCT_GROUP_NAME," +
						"OPS_PROVE_PRODUCT.PRODUCT_CATEGORY_NAME," +
						"OPS_PROVE_PRODUCT.PRODUCT_TYPE_NAME," +
						"OPS_PROVE_PRODUCT.PRODUCT_SUBTYPE_NAME," +
						"OPS_PROVE_PRODUCT.PRODUCT_SUBSETTYPE_NAME," +
						"OPS_PROVE_PRODUCT.PRODUCT_BRAND_NAME_TH," +
						"OPS_PROVE_PRODUCT.PRODUCT_BRAND_NAME_EN," +
						"OPS_PROVE_PRODUCT.PRODUCT_SUBBRAND_NAME_TH," +
						"OPS_PROVE_PRODUCT.PRODUCT_SUBBRAND_NAME_EN," +
						"OPS_PROVE_PRODUCT.PRODUCT_MODEL_NAME_TH," +
						"OPS_PROVE_PRODUCT.PRODUCT_MODEL_NAME_EN," +
						"OPS_PROVE_PRODUCT.SIZES," +
						"OPS_PROVE_PRODUCT.SIZES_UNIT," +
						"OPS_PROVE_PRODUCT.QUANTITY," +
						"OPS_PROVE_PRODUCT.QUANTITY_UNIT," +
						"OPS_PROVE_PRODUCT.VOLUMN," +
						"OPS_PROVE_PRODUCT.PRODUCT_DESC," +
						"OPS_PROVE_PRODUCT.VOLUMN_UNIT" +
						" from OPS_COMPARE_DETAIL_FINE" +
						" LEFT JOIN OPS_PROVE_PRODUCT ON OPS_COMPARE_DETAIL_FINE.PRODUCT_ID = OPS_PROVE_PRODUCT.PRODUCT_ID AND OPS_PROVE_PRODUCT.IS_ACTIVE = 1" +
						" where OPS_COMPARE_DETAIL_FINE.IS_ACTIVE = 1 and OPS_COMPARE_DETAIL_FINE.COMPARE_DETAIL_ID = '"+COMPARE_DETAIL_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<CompareDetailFine> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public CompareDetailFine mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompareDetailFine item = new CompareDetailFine();
				item.setFINE_ID(rs.getInt("FINE_ID"));
				item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
				item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
				item.setFINE_RATE(rs.getFloat("FINE_RATE"));
				item.setVAT(rs.getFloat("VAT"));
				item.setFINE(rs.getFloat("FINE"));
				item.setNET_FINE(rs.getFloat("NET_FINE"));
				item.setOLD_PAYMENT_FINE(rs.getFloat("OLD_PAYMENT_FINE"));
				item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
				item.setDIFFERENCE_PAYMENT_FINE(rs.getFloat("DIFFERENCE_PAYMENT_FINE"));
				item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
				item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
				item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
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
				item.setSIZES(rs.getFloat("SIZES"));
				item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
				item.setQUANTITY(rs.getFloat("QUANTITY"));
				item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
				item.setVOLUMN(rs.getFloat("VOLUMN"));
				item.setVOLUMN_UNIT(rs.getString("VOLUMN_UNIT"));
				item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));


				return item;
			}
		});

		return dataList;
	}

	protected List<ComparePayment> getComparePayment(int COMPARE_DETAIL_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("select " +
						"PAYMENT_ID," +
						"LAWSUIT_DETAIL_ID," +
						"COMPARE_DETAIL_ID," +
						"FINE_TYPE," +
						"FINE," +
						"PAYMENT_PERIOD_NO," +
						"PAYMENT_DATE," +
						"IS_REQUEST_REWARD," +
						"IS_ACTIVE, " +
						"PAYMENT_CHANNEL, " +
						"PAYMENT_BANK, " +
						"PAYMENT_REF_NO, " +
						"IS_REVENUE " +
						"from OPS_PAYMENT  where IS_ACTIVE = 1 ");
		sqlBuilder.append("and COMPARE_DETAIL_ID = '"+COMPARE_DETAIL_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ComparePayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ComparePayment mapRow(ResultSet rs, int rowNum) throws SQLException {
				ComparePayment item = new ComparePayment();
				item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
				item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
				item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
				item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
				item.setFINE(rs.getFloat("FINE"));
				item.setPAYMENT_PERIOD_NO(rs.getInt("PAYMENT_PERIOD_NO"));
				item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
				item.setIS_REQUEST_REWARD(rs.getInt("IS_REQUEST_REWARD"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				item.setPAYMENT_CHANNEL(rs.getInt("PAYMENT_CHANNEL"));
				item.setPAYMENT_BANK(rs.getString("PAYMENT_BANK"));
				item.setPAYMENT_REF_NO(rs.getString("PAYMENT_REF_NO"));
				item.setIS_REVENUE(rs.getInt("IS_REVENUE"));
				item.setComparePaymentDetail(getComparePaymentDetail(rs.getInt("PAYMENT_ID")));
				return item;
			}
		});

		return dataList;
	}

	protected List<ComparePaymentDetail> getComparePaymentDetail(int PAYMENT_ID) {

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
		List<ComparePaymentDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ComparePaymentDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				ComparePaymentDetail item = new ComparePaymentDetail();
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

	protected List<CompareArrestIndictmentProduct> getCompareArrestIndictmentProduct(int INDICTMENT_ID) {

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

						"OPS_ARREST_PRODUCT.PRODUCT_DESC," +
						"OPS_ARREST_PRODUCT.PRODUCT_CODE," +
						"OPS_ARREST_PRODUCT.PRODUCT_GROUP_ID," +
						"OPS_ARREST_PRODUCT.PRODUCT_GROUP_CODE," +
						"OPS_ARREST_PRODUCT.PRODUCT_CATEGORY_ID," +
						"OPS_ARREST_PRODUCT.PRODUCT_CATEGORY_CODE" +

						" from " +
						" OPS_ARREST_INDICTMENT_PRODUCT  " +
						" INNER JOIN OPS_ARREST_PRODUCT ON OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID = OPS_ARREST_PRODUCT.PRODUCT_ID" +
						" AND OPS_ARREST_PRODUCT.IS_ACTIVE = 1" +
						" AND OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE = 1" +
						" AND OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID in (select max(INDICTMENT_DETAIL_ID) as INDICTMENT_DETAIL_ID  from OPS_ARREST_INDICTMENT_DETAIL where INDICTMENT_ID = '"+INDICTMENT_ID+"') ");




		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<CompareArrestIndictmentProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public CompareArrestIndictmentProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompareArrestIndictmentProduct item = new CompareArrestIndictmentProduct();
				item.setPRODUCT_INDICTMENT_ID(rs.getInt("PRODUCT_INDICTMENT_ID"));
				item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
				item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
				item.setSIZES(rs.getFloat("SIZES"));
				item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
				item.setQUANTITY(rs.getFloat("QUANTITY"));
				item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
				item.setVOLUMN(rs.getFloat("VOLUMN"));
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
				item.setPRODUCT_GROUP_ID(rs.getString("PRODUCT_GROUP_ID"));
				item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
				item.setPRODUCT_CATEGORY_ID(rs.getString("PRODUCT_CATEGORY_ID"));
				item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
				item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
				return item;
			}
		});

		return dataList;

	}

	protected List<CompareArrestIndictmentDetail> getCompareArrestIndictmentDetail(int INDICTMENT_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("select " +
						"OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID," +
						"OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID," +
						"OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID," +
						"OPS_ARREST_LAWBREAKER.PERSON_ID," +
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
						"MAS_PERSON.COMPANY_NAME," +
						"MAS_PERSON.OTHER_NAME" +
						" from OPS_ARREST_INDICTMENT_DETAIL" +
						" INNER JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
						" INNER JOIN MAS_PERSON ON OPS_ARREST_LAWBREAKER.PERSON_ID = MAS_PERSON.PERSON_ID" +
						" where  OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1 and OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = '"+INDICTMENT_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<CompareArrestIndictmentDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public CompareArrestIndictmentDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompareArrestIndictmentDetail item = new CompareArrestIndictmentDetail();
				item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
				item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
				item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
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
				item.setCOMPANY_NAME(rs.getString("COMPANY_NAME"));

				return item;
			}
		});

		return dataList;

	}

	protected List<CompareStaff> getCompareStaff(int COMPARE_DETAIL_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("select " +
						"STAFF_ID," +
						"COMPARE_ID," +
						"COMPARE_DETAIL_ID," +
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
						"from OPS_COMPARE_STAFF  where IS_ACTIVE = 1 ");
		sqlBuilder.append("and COMPARE_DETAIL_ID = '"+COMPARE_DETAIL_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<CompareStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public CompareStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompareStaff item = new CompareStaff();
				item.setSTAFF_ID(rs.getInt("STAFF_ID"));
				item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
				item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
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

	protected List<CompareProveProduct> getCompareProveProduct(int PROVE_ID) {

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
						"IS_ACTIVE " +
						"from OPS_PROVE_PRODUCT  where IS_ACTIVE = 1 ");
		sqlBuilder.append("and PROVE_ID = '"+PROVE_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<CompareProveProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public CompareProveProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompareProveProduct item = new CompareProveProduct();
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
				item.setTAX_VOLUMN(rs.getInt("TAX_VOLUMN"));
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

				return item;
			}
		});

		return dataList;
	}

	protected List<CompareGuiltbaseFine> getCompareGuiltbaseFine(int SUBSECTION_RULE_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("select " +
						"FINE_ID," +
						"SUBSECTION_RULE_ID," +
						"PRODUCT_GROUP_ID," +
						"MISTREAT_START_NO," +
						"MISTREAT_TO_NO," +
						"IS_FINE," +
						"FINE_RATE," +
						"MISTREAT_DESC," +
						"MISTREAT_START_VOLUMN," +
						"MISTREAT_TO_VOLUMN," +
						"FINE_AMOUNT," +
						"FINE_TAX," +
						"IS_ACTIVE " +
						"from MAS_LAW_GUILTBASE_FINE  where IS_ACTIVE = 1 ");
		sqlBuilder.append("and SUBSECTION_RULE_ID = '"+SUBSECTION_RULE_ID+"'");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<CompareGuiltbaseFine> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public CompareGuiltbaseFine mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompareGuiltbaseFine item = new CompareGuiltbaseFine();
				item.setFINE_ID(rs.getInt("FINE_ID"));
				item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
				item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
				item.setMISTREAT_START_NO(rs.getInt("MISTREAT_START_NO"));
				item.setMISTREAT_TO_NO(rs.getInt("MISTREAT_TO_NO"));
				item.setIS_FINE(rs.getInt("IS_FINE"));
				item.setFINE_RATE(rs.getFloat("FINE_RATE"));
				item.setMISTREAT_DESC(rs.getString("MISTREAT_DESC"));
				item.setMISTREAT_START_VOLUMN(rs.getFloat("MISTREAT_START_VOLUMN"));
				item.setMISTREAT_TO_VOLUMN(rs.getFloat("MISTREAT_TO_VOLUMN"));
				item.setFINE_AMOUNT(rs.getFloat("FINE_AMOUNT"));
				item.setFINE_TAX(rs.getFloat("FINE_TAX"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				return item;
			}
		});

		return dataList;
	}

	protected List<NewCompareArrestIndictmentDetail> getCompareLawbreaker(int INDICTMENT_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("SELECT " +
						"    OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID," +
						"    OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID," +
						"    OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID," +
						"    OPS_ARREST_LAWBREAKER.ARREST_ID," +
						"    OPS_ARREST_LAWBREAKER.PERSON_ID," +
						"    OPS_ARREST_LAWBREAKER.TITLE_ID," +
						"    OPS_ARREST_LAWBREAKER.PERSON_TYPE," +
						"    OPS_ARREST_LAWBREAKER.ENTITY_TYPE," +
						"    OPS_ARREST_LAWBREAKER.TITLE_NAME_TH," +
						"    OPS_ARREST_LAWBREAKER.TITLE_NAME_EN," +
						"    OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH," +
						"    OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN," +
						"    OPS_ARREST_LAWBREAKER.FIRST_NAME," +
						"    OPS_ARREST_LAWBREAKER.MIDDLE_NAME," +
						"    OPS_ARREST_LAWBREAKER.LAST_NAME," +
						"    OPS_ARREST_LAWBREAKER.OTHER_NAME," +
						"    OPS_ARREST_LAWBREAKER.COMPANY_NAME," +
						"    OPS_ARREST_LAWBREAKER.COMPANY_REGISTRATION_NO," +
						"    OPS_ARREST_LAWBREAKER.EXCISE_REGISTRATION_NO," +
						"    OPS_ARREST_LAWBREAKER.ID_CARD," +
						"    OPS_ARREST_LAWBREAKER.AGE," +
						"    OPS_ARREST_LAWBREAKER.PASSPORT_NO," +
						"    OPS_ARREST_LAWBREAKER.CAREER," +
						"    OPS_ARREST_LAWBREAKER.PERSON_DESC," +
						"    OPS_ARREST_LAWBREAKER.EMAIL," +
						"    OPS_ARREST_LAWBREAKER.TEL_NO," +
						"    OPS_ARREST_LAWBREAKER.MISTREAT_NO," +
						"    OPS_ARREST_LAWBREAKER.COMPANY_NAME," +
						"    OPS_ARREST_LAWBREAKER.IS_ACTIVE" +
						" FROM OPS_ARREST_INDICTMENT" +
						" LEFT JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID AND OPS_ARREST.IS_ACTIVE = 1" +
						" LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
						" LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID AND OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
						" WHERE OPS_ARREST_INDICTMENT.INDICTMENT_ID = '"+INDICTMENT_ID+"' ");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<NewCompareArrestIndictmentDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public NewCompareArrestIndictmentDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				NewCompareArrestIndictmentDetail item = new NewCompareArrestIndictmentDetail();
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

				item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
				item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
				item.setCompareArrestIndictmentProduct(getNewCompareArrestIndictmentProduct(rs.getInt("INDICTMENT_DETAIL_ID")));
				item.setCompareProveProduct(getNewCompareProveProduct(rs.getInt("INDICTMENT_ID")));
				return item;
			}
		});

		return dataList;
	}

	protected List<NewCompareArrestIndictmentProduct> getNewCompareArrestIndictmentProduct(int INDICTMENT_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT " +
						"    OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_INDICTMENT_ID," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID INDICTMENT_DETAIL_ID," +
						"    OPS_ARREST_PRODUCT.PRODUCT_GROUP_ID, " +
						"    OPS_ARREST_PRODUCT.PRODUCT_GROUP_CODE, " +
						"    OPS_ARREST_PRODUCT.PRODUCT_GROUP_NAME, " +
						"    OPS_ARREST_INDICTMENT_PRODUCT.SIZES_UNIT_ID," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.QUATITY_UNIT_ID," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.VOLUMN_UNIT_ID," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.SIZES," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.SIZES_UNIT," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.QUANTITY," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.QUANTITY_UNIT," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.VOLUMN," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.VOLUMN_UNIT," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.FINE_ESTIMATE," +
						"    OPS_ARREST_PRODUCT.PRODUCT_DESC, " +
						"    OPS_ARREST_INDICTMENT_PRODUCT.IS_ILLEGAL," +
						"    OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE" +
						"    FROM OPS_ARREST_INDICTMENT" +
						"    LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
						"    LEFT JOIN OPS_ARREST_INDICTMENT_PRODUCT ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID AND OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE = 1" +
						"    LEFT JOIN OPS_ARREST_PRODUCT ON OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID = OPS_ARREST_PRODUCT.PRODUCT_ID AND OPS_ARREST_PRODUCT.IS_ACTIVE = 1 " +
						"    WHERE OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID" +
						"    AND OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID = "+INDICTMENT_ID);

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<NewCompareArrestIndictmentProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public NewCompareArrestIndictmentProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
				NewCompareArrestIndictmentProduct item = new NewCompareArrestIndictmentProduct();

				item.setPRODUCT_INDICTMENT_ID(rs.getInt("PRODUCT_INDICTMENT_ID"));
				item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
				item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
				item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
				item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
				item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
				item.setSIZES_UNIT_ID(rs.getInt("SIZES_UNIT_ID"));
				item.setQUATITY_UNIT_ID(rs.getInt("QUATITY_UNIT_ID"));
				item.setVOLUMN_UNIT_ID(rs.getInt("VOLUMN_UNIT_ID"));
				item.setSIZES(rs.getFloat("SIZES"));
				item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
				item.setQUANTITY(rs.getFloat("QUANTITY"));
				item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
				item.setVOLUMN(rs.getFloat("VOLUMN"));
				item.setVOLUMN_UNIT(rs.getString("VOLUMN_UNIT"));
				item.setFINE_ESTIMATE(rs.getInt("FINE_ESTIMATE"));
				item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
				item.setIS_ILLEGAL(rs.getInt("IS_ILLEGAL"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

				return item;
			}
		});

		return dataList;
	}

	protected List<CompareProveProduct> getNewCompareProveProduct(int INDICTMENT_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT DISTINCT" +
						"    OPS_PROVE_PRODUCT.PRODUCT_ID," +
						"    OPS_ARREST_INDICTMENT.INDICTMENT_ID," +
						"    OPS_PROVE_PRODUCT.PROVE_ID," +
						"    OPS_PROVE_PRODUCT.SCIENCE_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_MAPPING_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_INDICTMENT_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_MAPPING_REF_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_CODE," +
						"    OPS_PROVE_PRODUCT.PRODUCT_REF_CODE," +
						"    OPS_PROVE_PRODUCT.PRODUCT_GROUP_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_CATEGORY_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_TYPE_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_SUBTYPE_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_SUBSETTYPE_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_BRAND_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_SUBBRAND_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_MODEL_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_TAXDETAIL_ID," +
						"    OPS_PROVE_PRODUCT.SIZES_UNIT_ID," +
						"    OPS_PROVE_PRODUCT.QUATITY_UNIT_ID," +
						"    OPS_PROVE_PRODUCT.VOLUMN_UNIT_ID," +
						"    OPS_PROVE_PRODUCT.REMAIN_SIZES_UNIT_ID," +
						"    OPS_PROVE_PRODUCT.REMAIN_QUATITY_UNIT_ID," +
						"    OPS_PROVE_PRODUCT.REMAIN_VOLUMN_UNIT_ID," +
						"    OPS_PROVE_PRODUCT.PRODUCT_GROUP_CODE," +
						"    OPS_PROVE_PRODUCT.PRODUCT_GROUP_NAME," +
						"    OPS_PROVE_PRODUCT.PRODUCT_CATEGORY_CODE," +
						"    OPS_PROVE_PRODUCT.PRODUCT_CATEGORY_NAME," +
						"    OPS_PROVE_PRODUCT.PRODUCT_TYPE_CODE," +
						"    OPS_PROVE_PRODUCT.PRODUCT_TYPE_NAME," +
						"    OPS_PROVE_PRODUCT.PRODUCT_SUBTYPE_CODE," +
						"    OPS_PROVE_PRODUCT.PRODUCT_SUBTYPE_NAME," +
						"    OPS_PROVE_PRODUCT.PRODUCT_SUBSETTYPE_CODE," +
						"    OPS_PROVE_PRODUCT.PRODUCT_SUBSETTYPE_NAME," +
						"    OPS_PROVE_PRODUCT.PRODUCT_BRAND_CODE," +
						"    OPS_PROVE_PRODUCT.PRODUCT_BRAND_NAME_TH," +
						"    OPS_PROVE_PRODUCT.PRODUCT_BRAND_NAME_EN," +
						"    OPS_PROVE_PRODUCT.PRODUCT_SUBBRAND_CODE," +
						"    OPS_PROVE_PRODUCT.PRODUCT_SUBBRAND_NAME_TH," +
						"    OPS_PROVE_PRODUCT.PRODUCT_SUBBRAND_NAME_EN," +
						"    OPS_PROVE_PRODUCT.PRODUCT_MODEL_CODE," +
						"    OPS_PROVE_PRODUCT.PRODUCT_MODEL_NAME_TH," +
						"    OPS_PROVE_PRODUCT.PRODUCT_MODEL_NAME_EN," +
						"    OPS_PROVE_PRODUCT.IS_TAX_VALUE," +
						"    OPS_PROVE_PRODUCT.TAX_VALUE," +
						"    OPS_PROVE_PRODUCT.IS_TAX_VOLUMN," +
						"    OPS_PROVE_PRODUCT.TAX_VOLUMN," +
						"    OPS_PROVE_PRODUCT.TAX_VOLUMN_UNIT," +
						"    OPS_PROVE_PRODUCT.LICENSE_PLATE," +
						"    OPS_PROVE_PRODUCT.ENGINE_NO," +
						"    OPS_PROVE_PRODUCT.CHASSIS_NO," +
						"    OPS_PROVE_PRODUCT.PRODUCT_DESC," +
						"    OPS_PROVE_PRODUCT.SUGAR," +
						"    OPS_PROVE_PRODUCT.CO2," +
						"    OPS_PROVE_PRODUCT.DEGREE," +
						"    OPS_PROVE_PRODUCT.PRICE," +
						"    OPS_PROVE_PRODUCT.SIZES," +
						"    OPS_PROVE_PRODUCT.SIZES_UNIT," +
						"    OPS_PROVE_PRODUCT.QUANTITY," +
						"    OPS_PROVE_PRODUCT.QUANTITY_UNIT," +
						"    OPS_PROVE_PRODUCT.VOLUMN," +
						"    OPS_PROVE_PRODUCT.VOLUMN_UNIT," +
						"    OPS_PROVE_PRODUCT.REMAIN_SIZES," +
						"    OPS_PROVE_PRODUCT.REMAIN_SIZES_UNIT," +
						"    OPS_PROVE_PRODUCT.REMAIN_QUANTITY," +
						"    OPS_PROVE_PRODUCT.REMAIN_QUANTITY_UNIT," +
						"    OPS_PROVE_PRODUCT.REMAIN_VOLUMN," +
						"    OPS_PROVE_PRODUCT.REMAIN_VOLUMN_UNIT," +
						"    OPS_PROVE_PRODUCT.REMARK," +
						"    OPS_PROVE_PRODUCT.REMAIN_REMARK," +
						"    OPS_PROVE_PRODUCT.PRODUCT_RESULT," +
						"    OPS_PROVE_PRODUCT.SCIENCE_RESULT_DESC," +
						"    OPS_PROVE_PRODUCT.VAT," +
						"    OPS_PROVE_PRODUCT.IS_DOMESTIC," +
						"    OPS_PROVE_PRODUCT.IS_ILLEGAL," +
						"    OPS_PROVE_PRODUCT.IS_SCIENCE," +
						"    OPS_PROVE_PRODUCT.IS_ACTIVE," +
						"    OPS_PROVE_PRODUCT.IS_PROVE" +
						"    FROM OPS_ARREST_INDICTMENT" +
						"    INNER JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID AND OPS_LAWSUIT.IS_ACTIVE = 1" +
						"    INNER JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID AND OPS_PROVE.IS_ACTIVE = 1" +
						"    LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
						"    LEFT JOIN OPS_PROVE_PRODUCT ON OPS_PROVE.PROVE_ID = OPS_PROVE_PRODUCT.PROVE_ID AND OPS_PROVE.IS_ACTIVE = 1" +
						"    WHERE OPS_ARREST_INDICTMENT.INDICTMENT_ID = "+INDICTMENT_ID+"");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<CompareProveProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public CompareProveProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompareProveProduct item = new CompareProveProduct();
				item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
				item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
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


}
