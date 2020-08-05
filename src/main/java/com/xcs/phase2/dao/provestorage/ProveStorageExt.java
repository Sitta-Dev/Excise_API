package com.xcs.phase2.dao.provestorage;


import com.xcs.phase2.model.provestorage.ProveStorageArrestLawbeaker;
import com.xcs.phase2.model.provestorage.ProveStorageEvidenceInItem;
import com.xcs.phase2.model.provestorage.ProveStorageEvidenceInStaff;
import com.xcs.phase2.model.provestorage.ProveStorageLawsuitDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ProveStorageExt {

	private static final Logger log = LoggerFactory.getLogger(ProveStorageExt.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	protected String getSequences(String strSql) {
		log.info("[SQL]  : " + strSql);
		return getJdbcTemplate().queryForObject(strSql, String.class);
	}

	protected List<ProveStorageLawsuitDetail> getProveStorageLawsuitDetail(int ARREST_ID) {

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
		List<ProveStorageLawsuitDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ProveStorageLawsuitDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProveStorageLawsuitDetail item = new ProveStorageLawsuitDetail();
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
				item.setProveStorageArrestLawbeaker(getProveStorageArrestLawbeaker(rs.getInt("ARREST_ID"),rs.getInt("INDICTMENT_ID")));
				return item;
			}
		});

		return dataList;
	}

	protected List<ProveStorageArrestLawbeaker> getProveStorageArrestLawbeaker(int ARREST_ID, int INDICTMENT_ID) {

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
		List<ProveStorageArrestLawbeaker> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ProveStorageArrestLawbeaker mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProveStorageArrestLawbeaker item = new ProveStorageArrestLawbeaker();
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

	protected List<ProveStorageEvidenceInItem> getProveStorageEvidenceInItem(int EVIDENCE_IN_ID) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT DISTINCT " +
						"    OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID," +
						"    OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE," +
						"    OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID," +
						"    OPS_EVIDENCE_IN_ITEM.PRODUCT_DESC," +
						"    OPS_EVIDENCE_IN_ITEM.DELIVERY_QTY," +
						"    OPS_EVIDENCE_IN_ITEM.DELIVERY_QTY_UNIT," +
						"    OPS_EVIDENCE_IN_ITEM.DELIVERY_SIZE," +
						"    OPS_EVIDENCE_IN_ITEM.DELIVERY_SIZE_UNIT," +
						"    OPS_EVIDENCE_IN_ITEM.DELIVERY_NET_VOLUMN," +
						"    OPS_EVIDENCE_IN_ITEM.DELIVERY_NET_VOLUMN_UNIT," +
						"    OPS_EVIDENCE_IN_ITEM.DAMAGE_QTY," +
						"    OPS_EVIDENCE_IN_ITEM.DAMAGE_QTY_UNIT," +
						"    OPS_EVIDENCE_IN_ITEM.DAMAGE_SIZE," +
						"    OPS_EVIDENCE_IN_ITEM.DAMAGE_SIZE_UNIT," +
						"    OPS_EVIDENCE_IN_ITEM.DAMAGE_NET_VOLUMN," +
						"    OPS_EVIDENCE_IN_ITEM.DAMAGE_NET_VOLUMN_UNIT," +
						"    OPS_EVIDENCE_STOCK_BALANCE.STOCK_ID," +
						"    OPS_EVIDENCE_STOCK_BALANCE.RECEIVE_QTY," +
						"    OPS_EVIDENCE_STOCK_BALANCE.RECEIVE_QTY_UNIT," +
						"    OPS_EVIDENCE_STOCK_BALANCE.RECEIVE_SIZE," +
						"    OPS_EVIDENCE_STOCK_BALANCE.RECEIVE_SIZE_UNIT," +
						"    OPS_EVIDENCE_STOCK_BALANCE.RECEIVE_NET_VOLUMN," +
						"    OPS_EVIDENCE_STOCK_BALANCE.RECEIVE_NET_VOLUMN_UNIT," +
						"    OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY," +
						"    OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY_UNIT," +
						"    OPS_EVIDENCE_STOCK_BALANCE.BALANCE_SIZE," +
						"    OPS_EVIDENCE_STOCK_BALANCE.BALANCE_SIZE_UNIT," +
						"    OPS_EVIDENCE_STOCK_BALANCE.BALANCE_NET_VOLUMN," +
						"    OPS_EVIDENCE_STOCK_BALANCE.BALANCE_NET_VOLUMN_UNIT," +
						"    OPS_EVIDENCE_IN_ITEM.REMARK" +
						"    FROM OPS_EVIDENCE_IN_ITEM " +
						"    LEFT JOIN OPS_EVIDENCE_STOCK_BALANCE ON (OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID = OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID) " +
						"    WHERE OPS_EVIDENCE_IN_ITEM.IS_ACTIVE = 1 AND EVIDENCE_IN_ID = "+EVIDENCE_IN_ID);

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ProveStorageEvidenceInItem> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ProveStorageEvidenceInItem mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProveStorageEvidenceInItem item = new ProveStorageEvidenceInItem();
				item.setEVIDENCE_IN_ITEM_ID(rs.getInt("EVIDENCE_IN_ITEM_ID"));
				item.setEVIDENCE_IN_ITEM_CODE(rs.getString("EVIDENCE_IN_ITEM_CODE"));
				item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
				item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
				item.setDELIVERY_QTY(rs.getFloat("DELIVERY_QTY"));
				item.setDELIVERY_QTY_UNIT(rs.getString("DELIVERY_QTY_UNIT"));
				item.setDELIVERY_SIZE(rs.getFloat("DELIVERY_SIZE"));
				item.setDELIVERY_SIZE_UNIT(rs.getString("DELIVERY_SIZE_UNIT"));
				item.setDELIVERY_NET_VOLUMN(rs.getFloat("DELIVERY_NET_VOLUMN"));
				item.setDELIVERY_NET_VOLUMN_UNIT(rs.getString("DELIVERY_NET_VOLUMN_UNIT"));
				item.setDAMAGE_QTY(rs.getFloat("DAMAGE_QTY"));
				item.setDAMAGE_QTY_UNIT(rs.getString("DAMAGE_QTY_UNIT"));
				item.setDAMAGE_SIZE(rs.getFloat("DAMAGE_SIZE"));
				item.setDAMAGE_SIZE_UNIT(rs.getString("DAMAGE_SIZE_UNIT"));
				item.setDAMAGE_NET_VOLUMN(rs.getFloat("DAMAGE_NET_VOLUMN"));
				item.setDAMAGE_NET_VOLUMN_UNIT(rs.getString("DAMAGE_NET_VOLUMN_UNIT"));
				item.setSTOCK_ID(rs.getInt("STOCK_ID"));
				item.setRECEIVE_QTY(rs.getFloat("RECEIVE_QTY"));
				item.setRECEIVE_QTY_UNIT(rs.getString("RECEIVE_QTY_UNIT"));
				item.setRECEIVE_SIZE(rs.getFloat("RECEIVE_SIZE"));
				item.setRECEIVE_SIZE_UNIT(rs.getString("RECEIVE_SIZE_UNIT"));
				item.setRECEIVE_NET_VOLUMN(rs.getFloat("RECEIVE_NET_VOLUMN"));
				item.setRECEIVE_NET_VOLUMN_UNIT(rs.getString("RECEIVE_NET_VOLUMN_UNIT"));
				item.setBALANCE_QTY(rs.getFloat("BALANCE_QTY"));
				item.setBALANCE_QTY_UNIT(rs.getString("BALANCE_QTY_UNIT"));
				item.setBALANCE_SIZE(rs.getFloat("BALANCE_SIZE"));
				item.setBALANCE_SIZE_UNIT(rs.getString("BALANCE_SIZE_UNIT"));
				item.setBALANCE_NET_VOLUMN(rs.getFloat("BALANCE_NET_VOLUMN"));
				item.setBALANCE_NET_VOLUMN_UNIT(rs.getString("BALANCE_NET_VOLUMN_UNIT"));
				item.setREMARK(rs.getString("REMARK"));
				return item;
			}
		});

		return dataList;
	}

	protected List<ProveStorageEvidenceInStaff> getProveStorageEvidenceInStaff(int EVIDENCE_IN_ID) {

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
						"    WHERE IS_ACTIVE = 1 AND CONTRIBUTOR_ID IN(60,61,62,63) AND EVIDENCE_IN_ID = "+EVIDENCE_IN_ID);

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ProveStorageEvidenceInStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ProveStorageEvidenceInStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProveStorageEvidenceInStaff item = new ProveStorageEvidenceInStaff();
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
