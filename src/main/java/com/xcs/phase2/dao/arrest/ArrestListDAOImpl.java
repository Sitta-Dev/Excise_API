package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.arrest.ArrestLawbreakerPerson;
import com.xcs.phase2.model.arrest.ArrestList;
import com.xcs.phase2.model.arrest.ArrestPerson;
import com.xcs.phase2.request.arrest.ArrestListgetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestListgetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestgetByPersonIdReq;
import com.xcs.phase2.request.arrest.LawbreakerRelationshipgetByPersonIdReq;
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
public class ArrestListDAOImpl extends ArrestExt implements ArrestListDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestListDAOImpl.class);

	@Override
	public List<ArrestList> ArrestListgetByKeyword(ArrestListgetByKeywordReq req) {
		// TODO Auto-generated method stub
		
		String str = "";
		
		if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {
			
			if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

				if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
					str = " ";
				}else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
					str = " AND" +
							"( " +
							"  SUBSTR(MAS_SUB_DISTRICT.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							") " ;
				}else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
					str = " AND " +
							"( " +
							"  SUBSTR(MAS_SUB_DISTRICT.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							" )";
				}else {
					str = " AND" +
							" (" +
							"  MAS_SUB_DISTRICT.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
							"  OR OPS_ARREST_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
							"  OR OPS_ARREST_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
							"  OR OPS_ARREST_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
							" )";
				}
			
			}
			
		}
		
		StringBuilder sqlBuilderDetail = new StringBuilder()
				.append("    SELECT DISTINCT " +
						"        OPS_ARREST.ARREST_ID,    " +
						"        OPS_ARREST.ARREST_CODE,    " +
						"        OPS_ARREST.OCCURRENCE_DATE,    " +
						"        OPS_ARREST_STAFF.TITLE_NAME_TH,    " +
						"        OPS_ARREST_STAFF.TITLE_NAME_EN,    " +
						"        OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH,    " +
						"        OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN,    " +
						"        OPS_ARREST_STAFF.FIRST_NAME,    " +
						"        OPS_ARREST_STAFF.LAST_NAME,    " +
						"        OPS_ARREST.OFFICE_NAME,   " +
						"        MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH,    " +
						"        MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN,   " +
						"        MAS_DISTRICT.DISTRICT_NAME_TH,    " +
						"        MAS_DISTRICT.DISTRICT_NAME_EN,    " +
						"        MAS_PROVINCE.PROVINCE_NAME_TH,    " +
						"        MAS_PROVINCE.PROVINCE_NAME_EN,    " +
						"        OPS_ARREST.IS_LAWSUIT_COMPLETE,    " +
						"        OPS_ARREST_INDICTMENT.GUILTBASE_ID " +
						"    " +
						"    FROM OPS_ARREST" +
						"    INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID" +
						"    INNER JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID" +
						"    INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
						"    INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID" +
						"    INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID" +
						"    LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST.ARREST_ID = OPS_ARREST_LAWBREAKER.ARREST_ID AND OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
						"    INNER JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID = OPS_ARREST_INDICTMENT.ARREST_ID" +
						"    INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
						"    INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
						"    INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
						"    WHERE OPS_ARREST.IS_ACTIVE = 1" +
						"    AND OPS_ARREST_STAFF.IS_ACTIVE = 1" +
						"    AND OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14" +
						"    AND OPS_ARREST_LOCALE.IS_ACTIVE = 1" +
						"    AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
						"    AND MAS_LAW_GUILTBASE.IS_ACTIVE = 1" +
						"    AND MAS_LAW_GROUP_SUBSECTION_RULE.IS_ACTIVE = 1" +
						"    AND MAS_LAW_GROUP_SUBSECTION.IS_ACTIVE = 1" +
						"    AND" +
						"    (" +
						"      LOWER(OPS_ARREST.ARREST_CODE) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_NAME_TH||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_NAME_EN||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
						"      OR LOWER(MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
						"      OR LOWER(MAS_LAW_GUILTBASE.GUILTBASE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
						"      OR LOWER(OPS_ARREST.OFFICE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
						"      OR LOWER(MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH||MAS_DISTRICT.DISTRICT_NAME_TH||MAS_PROVINCE.PROVINCE_NAME_TH) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"    )"  +str + " ORDER BY OPS_ARREST.ARREST_CODE DESC ");

		log.info("[SQL]  : " + sqlBuilderDetail.toString());
		//System.out.println("[SQL] [AdjustCompareListgetByKeyword]  : " + sqlBuilderDetail.toString());

		StringBuilder _tempSql = new StringBuilder();
		_tempSql.append("with temp as ("+ sqlBuilderDetail.toString() +")");
		_tempSql.append(" SELECT" +
				"       ARREST_ID," +
				"       ARREST_CODE," +
				"		OCCURRENCE_DATE," +
				"		TITLE_NAME_TH," +
				"		TITLE_NAME_EN," +
				"		TITLE_SHORT_NAME_TH," +
				"       TITLE_SHORT_NAME_EN," +
				"		FIRST_NAME," +
				"		LAST_NAME," +
				"		OFFICE_NAME," +
				"		SUB_DISTRICT_NAME_TH," +
				"       SUB_DISTRICT_NAME_EN," +
				"		DISTRICT_NAME_TH," +
				"		DISTRICT_NAME_EN," +
				"		PROVINCE_NAME_TH," +
				"		PROVINCE_NAME_EN," +
				"       IS_LAWSUIT_COMPLETE," +
				"       LISTAGG(GUILTBASE_ID, ',') WITHIN GROUP (ORDER BY " +
				"       ARREST_ID,ARREST_CODE,OCCURRENCE_DATE,TITLE_NAME_TH,TITLE_NAME_EN,TITLE_SHORT_NAME_TH," +
				"       TITLE_SHORT_NAME_EN,FIRST_NAME,LAST_NAME,OFFICE_NAME,SUB_DISTRICT_NAME_TH," +
				"       SUB_DISTRICT_NAME_EN,DISTRICT_NAME_TH,DISTRICT_NAME_EN,PROVINCE_NAME_TH,PROVINCE_NAME_EN," +
				"       IS_LAWSUIT_COMPLETE" +
				"       ) GUILTBASE_ID" +
				"  FROM temp" +
				"  GROUP BY ARREST_ID,ARREST_CODE,OCCURRENCE_DATE,TITLE_NAME_TH,TITLE_NAME_EN,TITLE_SHORT_NAME_TH," +
				"       TITLE_SHORT_NAME_EN,FIRST_NAME,LAST_NAME,OFFICE_NAME,SUB_DISTRICT_NAME_TH," +
				"       SUB_DISTRICT_NAME_EN,DISTRICT_NAME_TH,DISTRICT_NAME_EN,PROVINCE_NAME_TH,PROVINCE_NAME_EN," +
				"       IS_LAWSUIT_COMPLETE");



		@SuppressWarnings("unchecked")
		List<ArrestList> dataList = getJdbcTemplate().query(_tempSql.toString(), new RowMapper() {

			public ArrestList mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestList item = new ArrestList();
				item.setARREST_ID(rs.getInt("ARREST_ID"));
				item.setARREST_CODE(rs.getString("ARREST_CODE"));
				item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
				item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
				item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
				item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
				item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
				item.setFIRST_NAME(rs.getString("FIRST_NAME"));
				item.setLAST_NAME(rs.getString("LAST_NAME"));
//				item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
//				item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
//				item.setSECTION_ID(rs.getString("SECTION_ID"));
//				item.setSECTION_NAME(rs.getString("SECTION_NAME"));
//				item.setSECTION_DESC_1(rs.getString("SECTION_DESC_1"));
//				item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
				item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
				item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
				item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
				item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
				item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
				item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
				item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));
				item.setIS_LAWSUIT_COMPLETE(rs.getInt("IS_LAWSUIT_COMPLETE"));

				item.setArrestMasGuiltbase(getArrestMasGuiltbase(rs.getString("GUILTBASE_ID")));
				item.setArrestLawbreaker(getArrestLawbreaker(rs.getInt("ARREST_ID")));


				return item;
			}
		});

		return dataList;
	}

	@Override
	public List<ArrestList> ArrestListgetByConAdv(ArrestListgetByConAdvReq req) {
		// TODO Auto-generated method stub
		
		String tempOccurrenceDateFrom   = "";
		String tempOccurrenceDateTo = "";
		 
		 
		 if(!"".equals(req.getOCCURRENCE_DATE_FROM())) {
			 tempOccurrenceDateFrom = String.format("%s %s", req.getOCCURRENCE_DATE_FROM(), Pattern.TIME_FROM);
		 }
		 
		 if(!"".equals(req.getOCCURRENCE_DATE_TO())) {
			 tempOccurrenceDateTo = String.format("%s %s", req.getOCCURRENCE_DATE_TO(),Pattern.TIME_TO);
		 }
		
		String str = "";
		
		if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {
			
			if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

				if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
					str = " ";
				}else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
					str = " AND" +
							"( " +
							"  SUBSTR(MAS_SUB_DISTRICT.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
							") " ;
				}else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
					str = " AND " +
							"( " +
							"  SUBSTR(MAS_SUB_DISTRICT.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							"  OR SUBSTR(OPS_ARREST_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
							" )";
				}else {
					str = " AND" +
							" (" +
							"  MAS_SUB_DISTRICT.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
							"  OR OPS_ARREST_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
							"  OR OPS_ARREST_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
							"  OR OPS_ARREST_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
							" )";
				}
			
			}
			
		}

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT DISTINCT" +
						"    OPS_ARREST.ARREST_ID," +
						"    OPS_ARREST.ARREST_CODE," +
						"    OPS_ARREST.OCCURRENCE_DATE," +
						"    OPS_ARREST_STAFF.TITLE_NAME_TH," +
						"    OPS_ARREST_STAFF.TITLE_NAME_EN," +
						"    OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH," +
						"    OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN," +
						"    OPS_ARREST_STAFF.FIRST_NAME," +
						"    OPS_ARREST_STAFF.LAST_NAME," +

						//"    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME," +
						//"    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC," +
						//"    MAS_LAW_GROUP_SECTION.SECTION_ID," +
						//"    MAS_LAW_GROUP_SECTION.SECTION_NAME," +
						//"    MAS_LAW_GROUP_SECTION.SECTION_DESC_1," +
						//"    MAS_LAW_PENALTY.PENALTY_DESC," +

						"    OPS_ARREST.OFFICE_NAME," +
						"    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH," +
						"    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN," +
						"    MAS_DISTRICT.DISTRICT_NAME_TH," +
						"    MAS_DISTRICT.DISTRICT_NAME_EN," +
						"    MAS_PROVINCE.PROVINCE_NAME_TH," +
						"    MAS_PROVINCE.PROVINCE_NAME_EN," +
						"    OPS_ARREST.IS_LAWSUIT_COMPLETE," +
						"    OPS_ARREST_INDICTMENT.GUILTBASE_ID" +
						"    FROM OPS_ARREST" +
						"    INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID" +
						"    INNER JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID" +
						"    INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
						"    INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID" +
						"    INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID" +
						"    LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST.ARREST_ID = OPS_ARREST_LAWBREAKER.ARREST_ID AND OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
						"    INNER JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID = OPS_ARREST_INDICTMENT.ARREST_ID" +
						"    INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +

						"    INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
						"    INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
						//"    INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
						//"    INNER JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID" +
						"    WHERE OPS_ARREST.IS_ACTIVE = 1" +
						"    AND OPS_ARREST_STAFF.IS_ACTIVE = 1" +
						"    AND OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14" +
						"    AND OPS_ARREST_LOCALE.IS_ACTIVE = 1" +
						"    AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
						"    AND MAS_LAW_GUILTBASE.IS_ACTIVE = 1" +
						"    AND MAS_LAW_GROUP_SUBSECTION_RULE.IS_ACTIVE = 1" +
						"    AND MAS_LAW_GROUP_SUBSECTION.IS_ACTIVE = 1" );
						//"    AND MAS_LAW_GROUP_SECTION.IS_ACTIVE = 1" +
						//"    AND MAS_LAW_PENALTY.IS_ACTIVE = 1" );
		
		if(req.getARREST_CODE() != null && !"".equals(req.getARREST_CODE())) {
			sqlBuilder.append(" AND LOWER(OPS_ARREST.ARREST_CODE) LIKE LOWER(REPLACE('%"+req.getARREST_CODE()+"%',' ','')) ");
		}
		
		if(req.getOCCURRENCE_DATE_FROM() != null && !"".equals(req.getOCCURRENCE_DATE_FROM()) && req.getOCCURRENCE_DATE_TO() != null && !"".equals(req.getOCCURRENCE_DATE_TO())) {
			sqlBuilder.append(" AND OPS_ARREST.OCCURRENCE_DATE BETWEEN  to_date(nvl('"+tempOccurrenceDateFrom+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempOccurrenceDateTo+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
		}
		
		if(req.getSTAFF_NAME() != null && !"".equals(req.getSTAFF_NAME())) {
			sqlBuilder.append( "AND" +
					" ( " +
					"  LOWER(OPS_ARREST_STAFF.TITLE_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER('%"+req.getSTAFF_NAME()+"%')" +
					"  OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER('%"+req.getSTAFF_NAME()+"%')" +
					"  OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER('%"+req.getSTAFF_NAME()+"%')" +
					"  OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER('%"+req.getSTAFF_NAME()+"%')" +
					" )");
		}

		if(req.getLAWBREAKER_NAME() != null && !"".equals(req.getLAWBREAKER_NAME())) {
			sqlBuilder.append( "AND" +
					" ( " +
					"  LOWER(OPS_ARREST_LAWBREAKER.TITLE_NAME_TH||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER('%"+req.getLAWBREAKER_NAME()+"%')" +
					"  OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_NAME_EN||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER('%"+req.getLAWBREAKER_NAME()+"%')" +
					"  OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER('%"+req.getLAWBREAKER_NAME()+"%')" +
					"  OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER('%"+req.getLAWBREAKER_NAME()+"%')" +
					" )");
		}

		if(req.getGUILTBASE_NAME() != null && !"".equals(req.getGUILTBASE_NAME())) {
			sqlBuilder.append(" AND LOWER(MAS_LAW_GUILTBASE.GUILTBASE_NAME) LIKE LOWER(REPLACE('%"+req.getGUILTBASE_NAME()+"%',' ','')) ");
		}

		if(req.getSUBSECTION_NAME() != null && !"".equals(req.getSUBSECTION_NAME())) {
			sqlBuilder.append(" AND LOWER(MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) LIKE LOWER('%"+req.getSUBSECTION_NAME()+"%') ");
		}
		
		if(req.getOFFICE_NAME() != null && !"".equals(req.getOFFICE_NAME())) {
			sqlBuilder.append(" AND LOWER(OPS_ARREST.OFFICE_NAME) LIKE LOWER('%"+req.getOFFICE_NAME()+"%') ");
		}
		
		if(req.getLOCALE_NAME() != null && !"".equals(req.getLOCALE_NAME())) {
			sqlBuilder.append(" AND LOWER(MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH||MAS_DISTRICT.DISTRICT_NAME_TH||MAS_PROVINCE.PROVINCE_NAME_TH) LIKE LOWER(REPLACE('%"+req.getLOCALE_NAME()+"%',' ','')) ");
		}
		
		if(req.getIS_LAWSUIT_COMPLETE() != null ) {
			sqlBuilder.append(" AND OPS_ARREST.IS_LAWSUIT_COMPLETE = '"+req.getIS_LAWSUIT_COMPLETE()+"' ");
		}

//		if(req.getCOMPANY_NAME() != null && !"".equals(req.getCOMPANY_NAME())) {
//			sqlBuilder.append(" AND LOWER(OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER('%"+req.getCOMPANY_NAME()+"%') ");
//		}
		
		sqlBuilder.append(str+ " ORDER BY OPS_ARREST.ARREST_CODE DESC ");

		log.info("[SQL]  : " + sqlBuilder.toString());

		StringBuilder _tempSql = new StringBuilder();
		_tempSql.append("with temp as ("+ sqlBuilder.toString() +")");
		_tempSql.append(" SELECT" +
				"       ARREST_ID," +
				"       ARREST_CODE," +
				"		OCCURRENCE_DATE," +
				"		TITLE_NAME_TH," +
				"		TITLE_NAME_EN," +
				"		TITLE_SHORT_NAME_TH," +
				"       TITLE_SHORT_NAME_EN," +
				"		FIRST_NAME," +
				"		LAST_NAME," +
				"		OFFICE_NAME," +
				"		SUB_DISTRICT_NAME_TH," +
				"       SUB_DISTRICT_NAME_EN," +
				"		DISTRICT_NAME_TH," +
				"		DISTRICT_NAME_EN," +
				"		PROVINCE_NAME_TH," +
				"		PROVINCE_NAME_EN," +
				"       IS_LAWSUIT_COMPLETE," +
				"       LISTAGG(GUILTBASE_ID, ',') WITHIN GROUP (ORDER BY " +
				"       ARREST_ID,ARREST_CODE,OCCURRENCE_DATE,TITLE_NAME_TH,TITLE_NAME_EN,TITLE_SHORT_NAME_TH," +
				"       TITLE_SHORT_NAME_EN,FIRST_NAME,LAST_NAME,OFFICE_NAME,SUB_DISTRICT_NAME_TH," +
				"       SUB_DISTRICT_NAME_EN,DISTRICT_NAME_TH,DISTRICT_NAME_EN,PROVINCE_NAME_TH,PROVINCE_NAME_EN," +
				"       IS_LAWSUIT_COMPLETE" +
				"       ) GUILTBASE_ID" +
				"  FROM temp" +
				"  GROUP BY ARREST_ID,ARREST_CODE,OCCURRENCE_DATE,TITLE_NAME_TH,TITLE_NAME_EN,TITLE_SHORT_NAME_TH," +
				"       TITLE_SHORT_NAME_EN,FIRST_NAME,LAST_NAME,OFFICE_NAME,SUB_DISTRICT_NAME_TH," +
				"       SUB_DISTRICT_NAME_EN,DISTRICT_NAME_TH,DISTRICT_NAME_EN,PROVINCE_NAME_TH,PROVINCE_NAME_EN," +
				"       IS_LAWSUIT_COMPLETE");

		@SuppressWarnings("unchecked")
		List<ArrestList> dataList = getJdbcTemplate().query(_tempSql.toString(), new RowMapper() {

			public ArrestList mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestList item = new ArrestList();
				item.setARREST_ID(rs.getInt("ARREST_ID"));
				item.setARREST_CODE(rs.getString("ARREST_CODE"));
				item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
				item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
				item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
				item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
				item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
				item.setFIRST_NAME(rs.getString("FIRST_NAME"));
				item.setLAST_NAME(rs.getString("LAST_NAME"));

//				item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
//				item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
//				item.setSECTION_ID(rs.getString("SECTION_ID"));
//				item.setSECTION_NAME(rs.getString("SECTION_NAME"));
//				item.setSECTION_DESC_1(rs.getString("SECTION_DESC_1"));
//				item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));


				item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
				item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
				item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
				item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
				item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
				item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
				item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));
				item.setIS_LAWSUIT_COMPLETE(rs.getInt("IS_LAWSUIT_COMPLETE"));

				item.setArrestMasGuiltbase(getArrestMasGuiltbase(rs.getString("GUILTBASE_ID")));
				item.setArrestLawbreaker(getArrestLawbreaker(rs.getInt("ARREST_ID")));

				return item;
			}
		});

		return dataList;
	}

	@Override
	public List<ArrestPerson> ArrestgetByPersonId(ArrestgetByPersonIdReq req) {
		// TODO Auto-generated method stub


		StringBuilder sqlBuilderDetail = new StringBuilder()
				.append("  SELECT DISTINCT" +
						"  OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
						"  OPS_ARREST_LAWBREAKER.PERSON_ID," +
						"  MAS_PERSON_PHOTO.PHOTO," +
						"  CASE WHEN OPS_ARREST_LAWBREAKER.PERSON_TYPE=0 THEN OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH when OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH IS NULL THEN  OPS_ARREST_LAWBREAKER.TITLE_NAME_TH " +
						"  WHEN OPS_ARREST_LAWBREAKER.PERSON_TYPE=1 THEN OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN when OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN IS NULL THEN OPS_ARREST_LAWBREAKER.TITLE_NAME_EN END || '' || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' ' || OPS_ARREST_LAWBREAKER.LAST_NAME AS ARREST_LAWBREAKER_NAME," +
						"  OPS_ARREST.ARREST_ID," +
						"  OPS_ARREST.ARREST_CODE," +
						" to_char(OPS_ARREST.OCCURRENCE_DATE,'"+Pattern.FORMAT_DATETIME+"') as OCCURRENCE_DATE," +
						"  CASE WHEN OPS_ARREST_LOCALE.ADDRESS_NO IS NOT NULL THEN OPS_ARREST_LOCALE.ADDRESS_NO END AS ADDRESS_NO," +
						"  CASE WHEN OPS_ARREST_LOCALE.VILLAGE_NO IS NOT NULL THEN 'หมู่ที่' || OPS_ARREST_LOCALE.VILLAGE_NO END AS VILLAGE_NO," +
						"  CASE WHEN OPS_ARREST_LOCALE.BUILDING_NAME IS NOT NULL THEN 'อาคาร' || OPS_ARREST_LOCALE.BUILDING_NAME END AS BUILDING_NAME," +
						"  CASE WHEN OPS_ARREST_LOCALE.ROOM_NO IS NOT NULL THEN 'ห้อง'|| OPS_ARREST_LOCALE.ROOM_NO END AS ROOM_NO," +
						"  CASE WHEN OPS_ARREST_LOCALE.FLOOR IS NOT NULL THEN 'ชั้น'|| OPS_ARREST_LOCALE.FLOOR END AS FLOOR," +
						"  CASE WHEN OPS_ARREST_LOCALE.VILLAGE_NAME IS NOT NULL THEN 'หมู่บ้าน' || OPS_ARREST_LOCALE.VILLAGE_NAME END AS VILLAGE_NAME," +
						"  CASE WHEN OPS_ARREST_LOCALE.ALLEY IS NOT NULL THEN 'ตรอก'|| OPS_ARREST_LOCALE.ALLEY END AS ALLEY," +
						"  CASE WHEN OPS_ARREST_LOCALE.LANE IS NOT NULL THEN 'ซอย'|| OPS_ARREST_LOCALE.LANE END AS LANE," +
						"  CASE WHEN OPS_ARREST_LOCALE.ROAD IS NOT NULL THEN 'ถนน'|| OPS_ARREST_LOCALE.ROAD END AS ROAD," +
						"  MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH|| '/' ||MAS_DISTRICT.DISTRICT_NAME_TH|| '/' ||MAS_PROVINCE.PROVINCE_NAME_TH AS LOCATION," +
						"  MAS_LAW_GROUP_SECTION.SECTION_NAME," +
						"  MAS_LAW_GUILTBASE.GUILTBASE_NAME," +
						"  MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME," +
						"  OPS_DOCUMENT.DOCUMENT_ID," +
						"  OPS_DOCUMENT.FILE_PATH" +
						"  FROM" +
						"  OPS_ARREST " +
						"  LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID=OPS_ARREST_INDICTMENT.ARREST_ID" +
						"  LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST.ARREST_ID=OPS_ARREST_LAWBREAKER.ARREST_ID" +
						"  LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID" +
						"  LEFT JOIN OPS_ARREST_INDICTMENT_PRODUCT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID" +
						"  LEFT JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID=OPS_ARREST_LOCALE.ARREST_ID" +
						"  LEFT JOIN MAS_PRODUCT_GROUP ON OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID=MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID" +
						"  LEFT JOIN MAS_PERSON ON OPS_ARREST_LAWBREAKER.PERSON_ID=MAS_PERSON.PERSON_ID" +
						"  LEFT JOIN MAS_PERSON_RELATIONSHIP ON MAS_PERSON.PERSON_ID=MAS_PERSON_RELATIONSHIP.PERSON_ID" +
						"  LEFT JOIN MAS_RELATIONSHIP ON MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID=MAS_RELATIONSHIP.RELATIONSHIP_ID" +
						"  LEFT JOIN MAS_PERSON_PHOTO ON MAS_PERSON.PERSON_ID=MAS_PERSON_PHOTO.PERSON_ID" +
						"  LEFT JOIN OPS_DOCUMENT on TO_NUMBER(MAS_PERSON.PERSON_ID) = TO_NUMBER(OPS_DOCUMENT.REFERENCE_CODE)" +
						"  and OPS_DOCUMENT.IS_ACTIVE = 1" +
						"  LEFT JOIN MAS_LAW_GUILTBASE on MAS_LAW_GUILTBASE.GUILTBASE_ID=OPS_ARREST_INDICTMENT.GUILTBASE_ID and MAS_LAW_GUILTBASE.IS_PROVE=1" +
						"  LEFT JOIN MAS_LAW_GROUP_SUBSECTION_RULE on MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
						"  LEFT JOIN MAS_LAW_GROUP_SECTION on MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
						"  LEFT JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID=MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
						"  LEFT JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID=MAS_DISTRICT.DISTRICT_ID" +
						"  LEFT JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID=MAS_PROVINCE.PROVINCE_ID" +
						"  LEFT JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_LAWSUIT.INDICTMENT_ID" +
						"  WHERE" +
						"  OPS_ARREST.IS_ACTIVE = 1" +
						"  and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
						"  and OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
						"  and OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
						"  and OPS_LAWSUIT.IS_ACTIVE = 1" +
						"  and OPS_LAWSUIT.IS_LAWSUIT = 1" +
						"  and OPS_ARREST_LAWBREAKER.PERSON_ID ='"+req.getPERSON_ID()+"'" +
						"  ORDER BY OPS_ARREST_LAWBREAKER.LAWBREAKER_ID");

		log.info("[SQL]  : " + sqlBuilderDetail.toString());
		System.out.println("[SQL] [ArrestgetByPersonId]  : " + sqlBuilderDetail.toString());

		@SuppressWarnings("unchecked")
		List<ArrestPerson> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

			public ArrestPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestPerson item = new ArrestPerson();
				item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
				item.setPERSON_ID(rs.getInt("PERSON_ID"));
				item.setPHOTO(rs.getString("PHOTO"));
				item.setARREST_LAWBREAKER_NAME(rs.getString("ARREST_LAWBREAKER_NAME"));
				item.setARREST_ID(rs.getInt("ARREST_ID"));
				item.setARREST_CODE(rs.getString("ARREST_CODE"));
				item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
				item.setADDRESS_NO(rs.getString("ADDRESS_NO"));
				item.setVILLAGE_NO(rs.getString("VILLAGE_NO"));
				item.setBUILDING_NAME(rs.getString("BUILDING_NAME"));
				item.setROOM_NO(rs.getString("ROOM_NO"));
				item.setFLOOR(rs.getString("FLOOR"));
				item.setVILLAGE_NAME(rs.getString("VILLAGE_NAME"));
				item.setALLEY(rs.getString("ALLEY"));
				item.setLANE(rs.getString("LANE"));
				item.setROAD(rs.getString("ROAD"));
				item.setLOCATION(rs.getString("LOCATION"));
				item.setSECTION_NAME(rs.getString("SECTION_NAME"));
				item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
				item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
				item.setDOCUMENT_ID(rs.getInt("DOCUMENT_ID"));
				item.setFILE_PATH(rs.getString("FILE_PATH"));


				return item;
			}
		});

		return dataList;
	}

	@Override
	public List<ArrestLawbreakerPerson> LawbreakerRelationshipgetByPersonId(LawbreakerRelationshipgetByPersonIdReq req) {
		// TODO Auto-generated method stub


		StringBuilder sqlBuilderDetail = new StringBuilder()
				.append("  SELECT DISTINCT" +
						"  OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
						"  OPS_ARREST_LAWBREAKER.PERSON_ID," +
						"  MAS_PERSON_PHOTO.PHOTO," +
						"  CASE WHEN OPS_ARREST_LAWBREAKER.PERSON_TYPE=0 THEN OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH when OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH IS NULL THEN  OPS_ARREST_LAWBREAKER.TITLE_NAME_TH " +
						"  WHEN OPS_ARREST_LAWBREAKER.PERSON_TYPE=1 THEN OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN when OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN IS NULL THEN OPS_ARREST_LAWBREAKER.TITLE_NAME_EN END || '' || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' ' || OPS_ARREST_LAWBREAKER.LAST_NAME AS ARREST_LAWBREAKER_NAME, " +
						"  OPS_ARREST.ARREST_CODE," +
						" to_char(OPS_ARREST.OCCURRENCE_DATE,'"+Pattern.FORMAT_DATETIME+"') as OCCURRENCE_DATE," +
						"  MAS_LAW_GROUP_SECTION.SECTION_NAME, " +
						"  MAS_LAW_GUILTBASE.GUILTBASE_NAME, " +
						"  MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME," +
						"  OPS_DOCUMENT.DOCUMENT_ID," +
						"  OPS_DOCUMENT.FILE_PATH" +
						"  FROM OPS_ARREST " +
						"  LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID=OPS_ARREST_INDICTMENT.ARREST_ID" +
						"  LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST.ARREST_ID=OPS_ARREST_LAWBREAKER.ARREST_ID" +
						"  LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID" +
						"  LEFT JOIN OPS_ARREST_INDICTMENT_PRODUCT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID" +
						"  LEFT JOIN MAS_PRODUCT_GROUP ON OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID=MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID" +
						"  LEFT JOIN MAS_PERSON ON OPS_ARREST_LAWBREAKER.PERSON_ID=MAS_PERSON.PERSON_ID" +
						"  LEFT JOIN MAS_PERSON_RELATIONSHIP ON MAS_PERSON.PERSON_ID=MAS_PERSON_RELATIONSHIP.PERSON_ID" +
						"  LEFT JOIN MAS_RELATIONSHIP ON MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID=MAS_RELATIONSHIP.RELATIONSHIP_ID" +
						"  LEFT JOIN MAS_PERSON_PHOTO ON MAS_PERSON.PERSON_ID=MAS_PERSON_PHOTO.PERSON_ID" +
						"  LEFT JOIN OPS_DOCUMENT on TO_NUMBER(MAS_PERSON.PERSON_ID) = TO_NUMBER(OPS_DOCUMENT.REFERENCE_CODE)" +
						"  and OPS_DOCUMENT.IS_ACTIVE = 1" +
						"  LEFT JOIN MAS_LAW_GUILTBASE on MAS_LAW_GUILTBASE.GUILTBASE_ID=OPS_ARREST_INDICTMENT.GUILTBASE_ID and MAS_LAW_GUILTBASE.IS_PROVE=1" +
						"  LEFT JOIN MAS_LAW_GROUP_SUBSECTION_RULE on MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
						"  LEFT JOIN MAS_LAW_GROUP_SECTION on MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
						"  WHERE OPS_ARREST.IS_ACTIVE = 1" +
						"  and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
						"  and OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
						"  and OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
						"  and OPS_ARREST.ARREST_CODE ='"+req.getARREST_CODE()+"'" +
						"  ORDER BY OPS_ARREST_LAWBREAKER.LAWBREAKER_ID");

		log.info("[SQL]  : " + sqlBuilderDetail.toString());
		System.out.println("[SQL] [ArrestLawbreakerPerson]  : " + sqlBuilderDetail.toString());

		@SuppressWarnings("unchecked")
		List<ArrestLawbreakerPerson> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

			public ArrestLawbreakerPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestLawbreakerPerson item = new ArrestLawbreakerPerson();
				item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
				item.setPERSON_ID(rs.getInt("PERSON_ID"));
				item.setPHOTO(rs.getString("PHOTO"));
				item.setARREST_LAWBREAKER_NAME(rs.getString("ARREST_LAWBREAKER_NAME"));
				item.setARREST_CODE(rs.getString("ARREST_CODE"));
				item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
				item.setSECTION_NAME(rs.getString("SECTION_NAME"));
				item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
				item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
				item.setDOCUMENT_ID(rs.getInt("DOCUMENT_ID"));
				item.setFILE_PATH(rs.getString("FILE_PATH"));


				return item;
			}
		});

		return dataList;
	}
}
