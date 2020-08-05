package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.arrest.ArrestPurityCert;
import com.xcs.phase2.request.arrest.ArrestPurityCertgetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestPurityCertgetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestPurityCertupdByConReq;
import com.xcs.phase2.request.arrest.ArrestPurityCertupdDeleteReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



@Service
@Transactional
public class ArrestPurityCertDAOImpl extends ArrestExt implements ArrestPurityCertDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestPurityCertDAOImpl.class);

	@Override
	public List<ArrestPurityCert> ArrestPurityCertgetByKeyword(ArrestPurityCertgetByKeywordReq req) {

		String str = "";

		if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

			if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

				if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
					str = " ";
				}else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
					str = " AND " +
							"( " +
							"  SUBSTR(OPS_PURITYCERT.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							")";
				}else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
					str = " AND " +
							"( " +
							"  SUBSTR(OPS_PURITYCERT.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							")";
				}else {
					str = " AND " +
							"( " +
							"  OPS_PURITYCERT.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
							"  OR OPS_PURITYCERT_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' " +
							"  OR OPS_PURITYCERT_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' " +
							"  OR OPS_PURITYCERT_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' " +
							")";
				}

			}

		}

		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"OPS_PURITYCERT.PURITYCERT_ID," +
			    		"OPS_PURITYCERT.ARREST_ID," +
			    		"OPS_PURITYCERT.PURITYCERT_CODE," +
			    		"to_char(OPS_PURITYCERT.PURITYCERT_DATE,'"+ Pattern.FORMAT_DATETIME+"') as PURITYCERT_DATE," +
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
			    		" from OPS_PURITYCERT")
			    .append(" INNER JOIN OPS_PURITYCERT_STAFF ON OPS_PURITYCERT.PURITYCERT_ID = OPS_PURITYCERT_STAFF.PURITYCERT_ID" +
			    		" INNER JOIN MAS_SUB_DISTRICT PC_SUB_DISTRICT ON OPS_PURITYCERT.SUB_DISTRICT_ID = PC_SUB_DISTRICT.SUB_DISTRICT_ID" +
			    		" INNER JOIN MAS_DISTRICT PC_DISTRICT ON PC_SUB_DISTRICT.DISTRICT_ID = PC_DISTRICT.DISTRICT_ID" +
			    		" INNER JOIN MAS_PROVINCE PC_PROVINCE ON PC_DISTRICT.PROVINCE_ID = PC_PROVINCE.PROVINCE_ID" +
			    		" WHERE OPS_PURITYCERT.IS_ACTIVE = 1" +
			    		" AND OPS_PURITYCERT_STAFF.IS_ACTIVE = 1" +
			    		" AND OPS_PURITYCERT_STAFF.CONTRIBUTOR_ID = 5" +
			    		" AND OPS_PURITYCERT.IS_ARREST = 0" +
			    		" AND" +
			    		" (" +
			    		"  LOWER(OPS_PURITYCERT.PURITYCERT_CODE) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
			    		"  OR LOWER(OPS_PURITYCERT.OFFICE_NAME) LIKE LOWER('"+req.getTEXT_SEARCH()+"%') " +
						"  OR LOWER(OPS_PURITYCERT_STAFF.OPERATION_OFFICE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%') )" +
						"  OR LOWER(OPS_PURITYCERT_STAFF.OPERATION_OFFICE_SHORT_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%') " +
			    		"  OR LOWER(OPS_PURITYCERT_STAFF.TITLE_NAME_TH||OPS_PURITYCERT_STAFF.FIRST_NAME||OPS_PURITYCERT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
			    		"  OR LOWER(OPS_PURITYCERT_STAFF.TITLE_NAME_EN||OPS_PURITYCERT_STAFF.FIRST_NAME||OPS_PURITYCERT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
			    		"  OR LOWER(OPS_PURITYCERT_STAFF.TITLE_SHORT_NAME_TH||OPS_PURITYCERT_STAFF.FIRST_NAME||OPS_PURITYCERT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
			    		"  OR LOWER(OPS_PURITYCERT_STAFF.TITLE_SHORT_NAME_EN||OPS_PURITYCERT_STAFF.FIRST_NAME||OPS_PURITYCERT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
			    		"  OR LOWER(OPS_PURITYCERT.PERSON_TITLE_NAME_TH||OPS_PURITYCERT.PERSON_FIRST_NAME||OPS_PURITYCERT.PERSON_MIDDLE_NAME||OPS_PURITYCERT.PERSON_LAST_NAME||OPS_PURITYCERT.PERSON_OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
			    		"  OR LOWER(OPS_PURITYCERT.PERSON_TITLE_NAME_EN||OPS_PURITYCERT.PERSON_FIRST_NAME||OPS_PURITYCERT.PERSON_MIDDLE_NAME||OPS_PURITYCERT.PERSON_LAST_NAME||OPS_PURITYCERT.PERSON_OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
			    		"  OR LOWER(OPS_PURITYCERT.PERSON_TITLE_SHORT_NAME_TH||OPS_PURITYCERT.PERSON_FIRST_NAME||OPS_PURITYCERT.PERSON_MIDDLE_NAME||OPS_PURITYCERT.PERSON_LAST_NAME||OPS_PURITYCERT.PERSON_OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
			    		"  OR LOWER(OPS_PURITYCERT.PERSON_TITLE_SHORT_NAME_EN||OPS_PURITYCERT.PERSON_FIRST_NAME||OPS_PURITYCERT.PERSON_MIDDLE_NAME||OPS_PURITYCERT.PERSON_LAST_NAME||OPS_PURITYCERT.PERSON_OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ','')" +
			    		" )" +str+
			    		" ORDER BY OPS_PURITYCERT.PURITYCERT_CODE DESC");

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

	@Override
	public List<ArrestPurityCert> ArrestPurityCertgetByConAdv(ArrestPurityCertgetByConAdvReq req) {
	
		String tempPuritycertDateFrom   = "";
		String tempPuritycertDateTo = "";

		String str = "";

		if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

			if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

				if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
					str = " ";
				}else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
					str = " AND " +
							"( " +
							"  SUBSTR(OPS_PURITYCERT.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							")";
				}else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
					str = " AND " +
							"( " +
							"  SUBSTR(OPS_PURITYCERT.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_PURITYCERT_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							")";
				}else {
					str = " AND " +
							"( " +
							"  OPS_PURITYCERT.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
							"  OR OPS_PURITYCERT_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' " +
							"  OR OPS_PURITYCERT_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' " +
							"  OR OPS_PURITYCERT_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' " +
							")";
				}

			}

		}
		 
		 
		 if(!"".equals(req.getPURITYCERT_DATE_FROM())) {
			 tempPuritycertDateFrom = String.format("%s %s", req.getPURITYCERT_DATE_FROM(),Pattern.TIME_FROM);
		 }
		 
		 if(!"".equals(req.getPURITYCERT_DATE_TO())) {
			 tempPuritycertDateTo = String.format("%s %s", req.getPURITYCERT_DATE_TO(),Pattern.TIME_TO);
		 }

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
			    		" from OPS_PURITYCERT")
			    .append(" INNER JOIN OPS_PURITYCERT_STAFF ON OPS_PURITYCERT.PURITYCERT_ID = OPS_PURITYCERT_STAFF.PURITYCERT_ID" +
			    		" INNER JOIN MAS_SUB_DISTRICT PC_SUB_DISTRICT ON OPS_PURITYCERT.SUB_DISTRICT_ID = PC_SUB_DISTRICT.SUB_DISTRICT_ID" +
			    		" INNER JOIN MAS_DISTRICT PC_DISTRICT ON PC_SUB_DISTRICT.DISTRICT_ID = PC_DISTRICT.DISTRICT_ID" +
			    		" INNER JOIN MAS_PROVINCE PC_PROVINCE ON PC_DISTRICT.PROVINCE_ID = PC_PROVINCE.PROVINCE_ID" +
			    		" WHERE OPS_PURITYCERT.IS_ACTIVE = 1" +
			    		" AND OPS_PURITYCERT_STAFF.IS_ACTIVE = 1" +
			    		" AND OPS_PURITYCERT_STAFF.CONTRIBUTOR_ID = 5" +
			    		" AND OPS_PURITYCERT.IS_ARREST = 0");

		if(req.getPURITYCERT_CODE() != null && !"".equals(req.getPURITYCERT_CODE())) {
			sqlBuilder.append(" AND LOWER(OPS_PURITYCERT.PURITYCERT_CODE) LIKE LOWER(REPLACE('%"+req.getPURITYCERT_CODE()+"%',' ','')) ");
		}
		
		if(req.getPURITYCERT_DATE_FROM() != null && !"".equals(req.getPURITYCERT_DATE_FROM()) && req.getPURITYCERT_DATE_TO() != null && !"".equals(req.getPURITYCERT_DATE_TO())) {
			sqlBuilder.append(" AND OPS_PURITYCERT.PURITYCERT_DATE BETWEEN  to_date(nvl('"+tempPuritycertDateFrom+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempPuritycertDateTo+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
		}
		
		if(req.getOFFICE_NAME() != null && !"".equals(req.getOFFICE_NAME())) {
			sqlBuilder.append(" AND " +
					"  ( " +
					"    LOWER(OPS_PURITYCERT.OFFICE_NAME) LIKE LOWER('%"+req.getOFFICE_NAME()+"%')" +
					"    OR LOWER(OPS_PURITYCERT_STAFF.OPERATION_OFFICE_NAME) LIKE LOWER('%"+req.getOFFICE_NAME()+"%')" +
					"    OR LOWER(OPS_PURITYCERT_STAFF.OPERATION_OFFICE_SHORT_NAME) LIKE LOWER('%"+req.getOFFICE_NAME()+"%') " +
					"  ) ");
		}
		
		if(req.getSTAFF_NAME() != null && !"".equals(req.getSTAFF_NAME())) {
			sqlBuilder.append(" AND" +
					" (" +
					"  LOWER(OPS_PURITYCERT_STAFF.TITLE_NAME_TH||OPS_PURITYCERT_STAFF.FIRST_NAME||OPS_PURITYCERT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
					"  OR LOWER(OPS_PURITYCERT_STAFF.TITLE_NAME_EN||OPS_PURITYCERT_STAFF.FIRST_NAME||OPS_PURITYCERT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
					"  OR LOWER(OPS_PURITYCERT_STAFF.TITLE_SHORT_NAME_TH||OPS_PURITYCERT_STAFF.FIRST_NAME||OPS_PURITYCERT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
					"  OR LOWER(OPS_PURITYCERT_STAFF.TITLE_SHORT_NAME_EN||OPS_PURITYCERT_STAFF.FIRST_NAME||OPS_PURITYCERT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
					" )");
		}
		
		if(req.getPERSON_NAME() != null && !"".equals(req.getPERSON_NAME())) {
			sqlBuilder.append(" AND" +
					" (" +
					"  LOWER(OPS_PURITYCERT.PERSON_TITLE_NAME_TH||OPS_PURITYCERT.PERSON_FIRST_NAME||OPS_PURITYCERT.PERSON_MIDDLE_NAME||OPS_PURITYCERT.PERSON_LAST_NAME||OPS_PURITYCERT.PERSON_OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getPERSON_NAME()+"%',' ',''))" +
					"  OR LOWER(OPS_PURITYCERT.PERSON_TITLE_NAME_EN||OPS_PURITYCERT.PERSON_FIRST_NAME||OPS_PURITYCERT.PERSON_MIDDLE_NAME||OPS_PURITYCERT.PERSON_LAST_NAME||OPS_PURITYCERT.PERSON_OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getPERSON_NAME()+"%',' ',''))" +
					"  OR LOWER(OPS_PURITYCERT.PERSON_TITLE_SHORT_NAME_TH||OPS_PURITYCERT.PERSON_FIRST_NAME||OPS_PURITYCERT.PERSON_MIDDLE_NAME||OPS_PURITYCERT.PERSON_LAST_NAME||OPS_PURITYCERT.PERSON_OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getPERSON_NAME()+"%',' ',''))" +
					"  OR LOWER(OPS_PURITYCERT.PERSON_TITLE_SHORT_NAME_EN||OPS_PURITYCERT.PERSON_FIRST_NAME||OPS_PURITYCERT.PERSON_MIDDLE_NAME||OPS_PURITYCERT.PERSON_LAST_NAME||OPS_PURITYCERT.PERSON_OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getPERSON_NAME()+"%',' ','')" +
					" )");
		}
		
		sqlBuilder.append(" "+str+" ORDER BY OPS_PURITYCERT.PURITYCERT_CODE DESC ");
		
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

	@Override
	public Boolean ArrestPurityCertupdByCon(List<ArrestPurityCertupdByConReq> request) {

		if(request != null) {
	    	log.info("[Sub] Size : "+request.size());
	    	
		    if(request.size() > 0){
		    	
		    	for(ArrestPurityCertupdByConReq req : request){
		    		StringBuilder sqlBuilderSub = new StringBuilder()
		    				.append("UPDATE OPS_PURITYCERT "
		    						+ "SET "
		    						+"ARREST_ID = '"+req.getARREST_ID()+"',"
		    						+"IS_ARREST = '1' "
		    						+ "WHERE PURITYCERT_ID = '"+req.getPURITYCERT_ID()+"' ");
		    				log.info("[SQL] : "+sqlBuilderSub.toString());
		    				
		    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
		    	}
		    }
	    }
		
		return true;
	}

	@Override
	public Boolean ArrestPurityCertupdDelete(List<ArrestPurityCertupdDeleteReq> request) {

		if(request != null) {
	    	log.info("[Sub] Size : "+request.size());
	    	
		    if(request.size() > 0){
		    	
		    	for(ArrestPurityCertupdDeleteReq req : request){
		    		StringBuilder sqlBuilderSub = new StringBuilder()
		    				.append("UPDATE OPS_PURITYCERT "
		    						+ "SET "
		    						+"ARREST_ID = '0',"
		    						+"IS_ARREST = '0' "
		    						+ "WHERE PURITYCERT_ID = '"+req.getPURITYCERT_ID()+"' ");
		    				log.info("[SQL] : "+sqlBuilderSub.toString());
		    				
		    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
		    	}
		    }
	    }
		
		return true;
	}
}
