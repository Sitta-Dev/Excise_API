package com.xcs.phase2.dao.deliverystorage;


import com.xcs.phase2.model.deliverystorage.DeliveryStorageArrestLawbeaker;
import com.xcs.phase2.model.deliverystorage.DeliveryStorageEvidenceInItem;
import com.xcs.phase2.model.deliverystorage.DeliveryStorageEvidenceInStaff;
import com.xcs.phase2.model.deliverystorage.DeliveryStorageLawsuitDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DeliveryStorageExt {

	private static final Logger log = LoggerFactory.getLogger(DeliveryStorageExt.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	protected String getSequences(String strSql) {
		log.info("[SQL]  : " + strSql);
		return getJdbcTemplate().queryForObject(strSql, String.class);
	}

	protected List<DeliveryStorageLawsuitDetail> getDeliveryStorageLawsuitDetail(int ARREST_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT DISTINCT " +
						"    OPS_ARREST.ARREST_ID," +
						"    OPS_ARREST_INDICTMENT.INDICTMENT_ID," +
						"    OPS_LAWSUIT.LAWSUIT_ID," +
						"    CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO," +
						"    TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO_YEAR," +
						"    TO_CHAR(OPS_LAWSUIT.LAWSUIT_DATE, 'dd MON yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as LAWSUIT_DATE," +
						"    case when OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH is null or OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_LAWSUIT_STAFF.TITLE_NAME_TH ||''|| OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME " +
						"    else OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME end as LAWSUIT_STAFF_NAME, " +
						"    OPS_LAWSUIT_STAFF.OPREATION_POS_NAME OPERATION_POS_NAME, " +
						"    OPS_LAWSUIT_STAFF.OPERATION_OFFICE_NAME, " +
						"    OPS_LAWSUIT_STAFF.OPERATION_OFFICE_SHORT_NAME," +
						"    OPS_LAWSUIT.OFFICE_NAME LAWSUIT_OFFICE_NAME" +
						"    FROM OPS_ARREST" +
						"    INNER JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID = OPS_ARREST_INDICTMENT.ARREST_ID AND OPS_ARREST_INDICTMENT.IS_ACTIVE =1" +
						"    INNER JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID AND OPS_LAWSUIT.IS_ACTIVE =1" +
						"    INNER JOIN OPS_LAWSUIT_STAFF ON OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID AND OPS_LAWSUIT_STAFF.IS_ACTIVE =1" +
						"    INNER JOIN OPS_LAWSUIT_DETAIL ON OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_ID AND OPS_LAWSUIT_DETAIL.IS_ACTIVE =1" +
						"    WHERE OPS_ARREST.ARREST_ID = "+ARREST_ID +
						"    order by LAWSUIT_DATE desc");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<DeliveryStorageLawsuitDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public DeliveryStorageLawsuitDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeliveryStorageLawsuitDetail item = new DeliveryStorageLawsuitDetail();
				item.setARREST_ID(rs.getInt("ARREST_ID"));
				item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
				item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
				item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
				item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
				item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
				item.setLAWSUIT_STAFF_NAME(rs.getString("LAWSUIT_STAFF_NAME"));
				item.setOPERATION_POS_NAME(rs.getString("OPERATION_POS_NAME"));
				item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
				item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
				item.setLAWSUIT_OFFICE_NAME(rs.getString("LAWSUIT_OFFICE_NAME"));
				item.setDeliveryStorageArrestLawbeaker(getDeliveryStorageArrestLawbeaker(rs.getInt("ARREST_ID"),rs.getInt("INDICTMENT_ID")));
				return item;
			}
		});

		return dataList;
	}

	protected List<DeliveryStorageArrestLawbeaker> getDeliveryStorageArrestLawbeaker(int ARREST_ID, int INDICTMENT_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT DISTINCT " +
						"    OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
						"    OPS_ARREST_LAWBREAKER.ARREST_ID," +
						"    OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID," +
						"    OPS_ARREST_LAWBREAKER.PERSON_ID," +
						"    case when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 0 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is not null then " +
						"        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
						"          OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)" +
						"         when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 0 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is null then  " +
						"        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
						"          OPS_ARREST_LAWBREAKER.TITLE_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)" +
						"         when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 1 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN is not null then  " +
						"        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
						"          OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)" +
						"         when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 1 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN is null then  " +
						"        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
						"          OPS_ARREST_LAWBREAKER.TITLE_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)" +
						"     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 2 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is not null then  OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH ||  OPS_ARREST_LAWBREAKER.COMPANY_NAME  " +
						"     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 2 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is null then  OPS_ARREST_LAWBREAKER.TITLE_NAME_TH ||  OPS_ARREST_LAWBREAKER.COMPANY_NAME end as LAWBREAKER_NAME" +
						"    FROM OPS_ARREST" +
						"    INNER JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID = OPS_ARREST_INDICTMENT.ARREST_ID AND OPS_ARREST_INDICTMENT.IS_ACTIVE =1" +
						"    INNER JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID" +
						"    INNER JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
						"    WHERE OPS_ARREST.ARREST_ID = "+ARREST_ID+" and OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID = "+INDICTMENT_ID);

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<DeliveryStorageArrestLawbeaker> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public DeliveryStorageArrestLawbeaker mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeliveryStorageArrestLawbeaker item = new DeliveryStorageArrestLawbeaker();
				item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
				item.setARREST_ID(rs.getInt("ARREST_ID"));
				item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
				item.setPERSON_ID(rs.getInt("PERSON_ID"));
				item.setLAWBREAKER_NAME(rs.getString("LAWBREAKER_NAME"));
				return item;
			}
		});

		return dataList;
	}

	protected List<DeliveryStorageEvidenceInItem> getDeliveryStorageEvidenceInItem(int EVIDENCE_IN_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("SELECT " +
						"        EVIDENCE_IN_ITEM_ID," +
						"        PRODUCT_GROUP_ID," +
						"        EVIDENCE_IN_ITEM_CODE," +
						"        PRODUCT_GROUP_CODE," +
						"        PRODUCT_GROUP_NAME," +
						"        PRODUCT_CATEGORY_CODE," +
						"        PRODUCT_CATEGORY_NAME," +
						"        PRODUCT_DESC," +
						"        DEGREE," +
						"        PRICE," +
						"        DELIVERY_SIZE," +
						"        DELIVERY_SIZE_UNIT," +
						"        DELIVERY_QTY," +
						"        DELIVERY_QTY_UNIT," +
						"        DELIVERY_NET_VOLUMN," +
						"        DELIVERY_NET_VOLUMN_UNIT" +
						"    FROM OPS_EVIDENCE_IN_ITEM" +
						"    WHERE IS_ACTIVE = 1 AND EVIDENCE_IN_ID = "+EVIDENCE_IN_ID);

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<DeliveryStorageEvidenceInItem> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public DeliveryStorageEvidenceInItem mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeliveryStorageEvidenceInItem item = new DeliveryStorageEvidenceInItem();
				item.setEVIDENCE_IN_ITEM_ID(rs.getInt("EVIDENCE_IN_ITEM_ID"));
				item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
				item.setEVIDENCE_IN_ITEM_CODE(rs.getString("EVIDENCE_IN_ITEM_CODE"));
				item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
				item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
				item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
				item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));
				item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
				item.setDEGREE(rs.getFloat("DEGREE"));
				item.setPRICE(rs.getFloat("PRICE"));
				item.setDELIVERY_SIZE(rs.getFloat("DELIVERY_SIZE"));
				item.setDELIVERY_SIZE_UNIT(rs.getString("DELIVERY_SIZE_UNIT"));
				item.setDELIVERY_QTY(rs.getFloat("DELIVERY_QTY"));
				item.setDELIVERY_QTY_UNIT(rs.getString("DELIVERY_QTY_UNIT"));
				item.setDELIVERY_NET_VOLUMN(rs.getFloat("DELIVERY_NET_VOLUMN"));
				item.setDELIVERY_NET_VOLUMN_UNIT(rs.getString("DELIVERY_NET_VOLUMN_UNIT"));
				return item;
			}
		});

		return dataList;
	}

	protected List<DeliveryStorageEvidenceInStaff> getDeliveryStorageEvidenceInStaff(int EVIDENCE_IN_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT " +
						"    EVIDENCE_IN_STAFF_ID," +
						"    STAFF_REF_ID STAFF_ID," +
						"    TITLE_SHORT_NAME_TH," +
						"    FIRST_NAME," +
						"    LAST_NAME," +
						"    MANAGEMENT_POS_NAME," +
						"    OPERATION_OFFICE_SHORT_NAME," +
						"    CONTRIBUTOR_ID" +
						"    FROM OPS_EVIDENCE_IN_STAFF" +
						"    WHERE IS_ACTIVE = 1 AND EVIDENCE_IN_ID = "+EVIDENCE_IN_ID);

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<DeliveryStorageEvidenceInStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public DeliveryStorageEvidenceInStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeliveryStorageEvidenceInStaff item = new DeliveryStorageEvidenceInStaff();
				item.setEVIDENCE_IN_STAFF_ID(rs.getInt("EVIDENCE_IN_STAFF_ID"));
				item.setSTAFF_ID(rs.getInt("STAFF_ID"));
				item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
				item.setFIRST_NAME(rs.getString("FIRST_NAME"));
				item.setLAST_NAME(rs.getString("LAST_NAME"));
				item.setMANAGEMENT_POS_NAME(rs.getString("MANAGEMENT_POS_NAME"));
				item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
				item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
				return item;
			}
		});

		return dataList;
	}
}
