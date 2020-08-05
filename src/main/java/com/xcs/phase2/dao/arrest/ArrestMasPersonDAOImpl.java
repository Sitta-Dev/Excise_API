package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestMasPerson;
import com.xcs.phase2.request.arrest.ArrestMasPersongetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestMasPersongetByKeywordReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ArrestMasPersonDAOImpl extends ArrestExt implements ArrestMasPersonDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestMasPersonDAOImpl.class);

	@Override
	public List<ArrestMasPerson> ArrestMasPersongetByKeyword(final ArrestMasPersongetByKeywordReq req) {
		
		StringBuilder sqlBuilder = new StringBuilder()
			    .append("    SELECT DISTINCT" +
						"    MAS_PERSON.PERSON_ID," +
						"    MAS_PERSON.PERSON_TYPE," +
						"    MAS_PERSON.ENTITY_TYPE," +
						"    MAS_PERSON.ID_CARD," +
						"    MAS_PERSON.PASSPORT_NO," +
						"    MAS_PERSON.COMPANY_REGISTRATION_NO," +
						"    MAS_PERSON.COMPANY_NAME," +
						"    MAS_PERSON.TITLE_ID," +
						"    MAS_PERSON.TITLE_NAME_TH," +
						"    MAS_PERSON.TITLE_NAME_EN," +
						"    MAS_PERSON.TITLE_SHORT_NAME_TH," +
						"    MAS_PERSON.TITLE_SHORT_NAME_EN," +
						"    MAS_PERSON.FIRST_NAME," +
						"    MAS_PERSON.MIDDLE_NAME," +
						"    MAS_PERSON.LAST_NAME," +
						"    MAS_PERSON.OTHER_NAME," +
						"    MAS_COUNTRY.COUNTRY_NAME_TH," +
						"    MAS_PERSON.MISTREAT_NO" +
						"    FROM MAS_PERSON" +
						"    LEFT JOIN MAS_PERSON_RELATIONSHIP ON MAS_PERSON.PERSON_ID = MAS_PERSON_RELATIONSHIP.PERSON_ID" +
						"    AND MAS_PERSON_RELATIONSHIP.IS_ACTIVE = 1" +
						"    AND MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID BETWEEN 1 AND 2" +
						"    LEFT JOIN MAS_RELATIONSHIP ON MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID = MAS_RELATIONSHIP.RELATIONSHIP_ID" +
						"    LEFT JOIN MAS_COUNTRY ON MAS_PERSON.COUNTRY_ID = MAS_COUNTRY.COUNTRY_ID" +
						"    AND MAS_COUNTRY.IS_ACTIVE = 1" +
						"    WHERE MAS_PERSON.IS_ACTIVE = 1" +
						"    AND" +
						"    (" +
						"      LOWER(MAS_PERSON.ID_CARD) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(MAS_PERSON.PASSPORT_NO) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(MAS_PERSON.COMPANY_REGISTRATION_NO) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(MAS_COUNTRY.COUNTRY_NAME_TH) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(MAS_PERSON.EXCISE_REGISTRATION_NO) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(MAS_PERSON.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(MAS_PERSON.TITLE_NAME_TH||MAS_PERSON.FIRST_NAME||MAS_PERSON.MIDDLE_NAME||MAS_PERSON.LAST_NAME||MAS_PERSON.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"    )" +
						"    ORDER BY MAS_PERSON.PERSON_ID DESC");
		
		
		
		log.info("[SQL ] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<ArrestMasPerson> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			                public ArrestMasPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	ArrestMasPerson item = new ArrestMasPerson();
								item.setPERSON_ID(rs.getInt("PERSON_ID"));
								item.setPERSON_TYPE(rs.getInt("PERSON_TYPE"));
								item.setENTITY_TYPE(rs.getInt("ENTITY_TYPE"));
								item.setID_CARD(rs.getString("ID_CARD"));
								item.setPASSPORT_NO(rs.getString("PASSPORT_NO"));
								item.setCOMPANY_REGISTRATION_NO(rs.getString("COMPANY_REGISTRATION_NO"));
								item.setCOMPANY_NAME(rs.getString("COMPANY_NAME"));
								item.setTITLE_ID(rs.getInt("TITLE_ID"));
								item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
								item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
								item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
								item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
								item.setFIRST_NAME(rs.getString("FIRST_NAME"));
								item.setMIDDLE_NAME(rs.getString("MIDDLE_NAME"));
								item.setLAST_NAME(rs.getString("LAST_NAME"));
								item.setOTHER_NAME(rs.getString("OTHER_NAME"));
								item.setCOUNTRY_NAME_TH(rs.getString("COUNTRY_NAME_TH"));
								item.setMISTREAT_NO(rs.getInt("MISTREAT_NO"));
			                	item.setArrestMasPersonRelationship(getArrestMasPersonRelationship(rs.getInt("PERSON_ID"),req.getTEXT_SEARCH()));

								return item;

			                }
			});      
		return dataList;
	}

	@Override
	public List<ArrestMasPerson> ArrestMasPersongetByConAdv(final ArrestMasPersongetByConAdvReq req) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT DISTINCT" +
						"    MAS_PERSON.PERSON_ID," +
						"    MAS_PERSON.PERSON_TYPE," +
						"    MAS_PERSON.ENTITY_TYPE," +
						"    MAS_PERSON.ID_CARD," +
						"    MAS_PERSON.PASSPORT_NO," +
						"    MAS_PERSON.COMPANY_REGISTRATION_NO," +
						"    MAS_PERSON.COMPANY_NAME," +
						"    MAS_PERSON.TITLE_ID," +
						"    MAS_PERSON.TITLE_NAME_TH," +
						"    MAS_PERSON.TITLE_NAME_EN," +
						"    MAS_PERSON.TITLE_SHORT_NAME_TH," +
						"    MAS_PERSON.TITLE_SHORT_NAME_EN," +
						"    MAS_PERSON.FIRST_NAME," +
						"    MAS_PERSON.MIDDLE_NAME," +
						"    MAS_PERSON.LAST_NAME," +
						"    MAS_PERSON.OTHER_NAME," +
						"    MAS_PERSON.EXCISE_REGISTRATION_NO," +
						"    MAS_COUNTRY.COUNTRY_NAME_TH," +
						"    MAS_COUNTRY.COUNTRY_NAME_EN," +
						"    MAS_PERSON.MISTREAT_NO" +
						"    FROM MAS_PERSON" +
						"    LEFT JOIN MAS_PERSON_RELATIONSHIP ON MAS_PERSON.PERSON_ID = MAS_PERSON_RELATIONSHIP.PERSON_ID" +
						"    AND MAS_PERSON_RELATIONSHIP.IS_ACTIVE = 1" +
						"    AND MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID in ('1','2','3') " +
						"    LEFT JOIN MAS_RELATIONSHIP ON MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID = MAS_RELATIONSHIP.RELATIONSHIP_ID" +
						"    LEFT JOIN MAS_COUNTRY ON MAS_PERSON.COUNTRY_ID = MAS_COUNTRY.COUNTRY_ID" +
						"    AND MAS_COUNTRY.IS_ACTIVE = 1" +
						"    WHERE MAS_PERSON.IS_ACTIVE = 1 ");

		if(req.getPERSON_TYPE() != null ) {
			sqlBuilder.append(" AND LOWER(MAS_PERSON.PERSON_TYPE) = '"+req.getPERSON_TYPE()+"' ");
		}
		
		if(req.getENTITY_TYPE() != null ) {
			sqlBuilder.append(" AND LOWER(MAS_PERSON.ENTITY_TYPE) = '"+req.getENTITY_TYPE()+"' ");
		}
		
		
		if(req.getID_CARD() != null && !"".equals(req.getID_CARD())) {
			sqlBuilder.append(" AND LOWER(MAS_PERSON.ID_CARD) LIKE LOWER(REPLACE('%"+req.getID_CARD()+"%',' ','')) ");
		}

		if(req.getEXCISE_REGISTRATION_NO() != null && !"".equals(req.getEXCISE_REGISTRATION_NO())) {
			sqlBuilder.append(" AND LOWER(MAS_PERSON.EXCISE_REGISTRATION_NO) LIKE LOWER(REPLACE('%"+req.getEXCISE_REGISTRATION_NO()+"%',' ','')) ");
		}
		
		if(req.getPASSPORT_NO() != null && !"".equals(req.getPASSPORT_NO())) {
			sqlBuilder.append(" AND LOWER(MAS_PERSON.PASSPORT_NO) LIKE LOWER(REPLACE('%"+req.getPASSPORT_NO()+"%',' ','')) ");
		}


		if(req.getCOUNTRY_NAME() != null && !"".equals(req.getCOUNTRY_NAME())) {
			sqlBuilder.append(" AND LOWER(MAS_COUNTRY.COUNTRY_NAME_TH||MAS_COUNTRY.COUNTRY_NAME_EN) LIKE LOWER('%"+req.getCOUNTRY_NAME()+"%') ");
		}
		
		if(req.getCOMPANY_REGISTRATION_NO() != null && !"".equals(req.getCOMPANY_REGISTRATION_NO())) {
			sqlBuilder.append(" AND LOWER(MAS_PERSON.COMPANY_REGISTRATION_NO) LIKE LOWER(REPLACE('%"+req.getCOMPANY_REGISTRATION_NO()+"%',' ','')) ");
		}
		
		if(req.getPERSON_NAME() != null && !"".equals(req.getPERSON_NAME())) {
			sqlBuilder.append(" AND LOWER(MAS_PERSON.TITLE_NAME_TH||MAS_PERSON.FIRST_NAME||MAS_PERSON.MIDDLE_NAME||MAS_PERSON.LAST_NAME||MAS_PERSON.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getPERSON_NAME()+"%',' ','')) ");
		}

		if(req.getCOMPANY_NAME() != null && !"".equals(req.getCOMPANY_NAME())) {
			sqlBuilder.append(" AND LOWER(MAS_PERSON.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPANY_NAME()+"%',' ','')) ");
		}

		if(!StringUtils.isEmpty(req.getFATHER_NAME()) && !StringUtils.isEmpty(req.getMOTHER_NAME())){
			sqlBuilder.append(" AND (" +
					"        (" +
					"            LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||' '||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER('%"+req.getFATHER_NAME()+"%')" +
					"            OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_EN||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||' '||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER('%"+req.getFATHER_NAME()+"%')" +
					"            OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||' '||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER('%"+req.getFATHER_NAME()+"%')" +
					"            OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_EN||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||' '||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER('%"+req.getFATHER_NAME()+"%')" +
					"        ) " +
					"        OR " +
					"        (" +
					"            LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||' '||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER('%"+req.getMOTHER_NAME()+"%')" +
					"            OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_EN||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||' '||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER('%"+req.getMOTHER_NAME()+"%')" +
					"            OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||' '||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER('%"+req.getMOTHER_NAME()+"%')" +
					"            OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_EN||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||' '||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER('%"+req.getMOTHER_NAME()+"%')" +
					"        )" +
					"    )");
		}else{
			if(req.getFATHER_NAME() != null && !"".equals(req.getFATHER_NAME())) {
				sqlBuilder.append(" AND ( " +
						" LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getFATHER_NAME()+"%',' ','')) " +
						" OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_EN||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getFATHER_NAME()+"%',' ','')) " +
						" OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getFATHER_NAME()+"%',' ','')) " +
						" OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_EN||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getFATHER_NAME()+"%',' ','')) " +
						" ) ");
			}

			if(req.getMOTHER_NAME() != null && !"".equals(req.getMOTHER_NAME())) {
				sqlBuilder.append(" AND ( " +
						" LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getMOTHER_NAME()+"%',' ','')) " +
						" OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_EN||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getMOTHER_NAME()+"%',' ','')) " +
						" OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getMOTHER_NAME()+"%',' ','')) " +
						" OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_EN||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getMOTHER_NAME()+"%',' ','')) " +
						" ) ");
			}
		}

		if(req.getRELATIONSHIP_NAME() != null && !"".equals(req.getRELATIONSHIP_NAME())) {
			sqlBuilder.append(" AND ( " +
					" LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getRELATIONSHIP_NAME()+"%',' ','')) " +
					" OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_NAME_EN||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getRELATIONSHIP_NAME()+"%',' ','')) " +
					" OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_TH||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getRELATIONSHIP_NAME()+"%',' ','')) " +
					" OR LOWER(MAS_PERSON_RELATIONSHIP.TITLE_SHORT_NAME_EN||MAS_PERSON_RELATIONSHIP.FIRST_NAME||MAS_PERSON_RELATIONSHIP.MIDDLE_NAME||MAS_PERSON_RELATIONSHIP.LAST_NAME||MAS_PERSON_RELATIONSHIP.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getRELATIONSHIP_NAME()+"%',' ','')) " +
					" ) ");
		}

		sqlBuilder.append(" ORDER BY MAS_PERSON.COMPANY_NAME asc,MAS_PERSON.FIRST_NAME asc,MAS_PERSON.LAST_NAME asc");


		log.info("[SQL ] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<ArrestMasPerson> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			                public ArrestMasPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	ArrestMasPerson item = new ArrestMasPerson();
								item.setPERSON_ID(rs.getInt("PERSON_ID"));
								item.setPERSON_TYPE(rs.getInt("PERSON_TYPE"));
								item.setENTITY_TYPE(rs.getInt("ENTITY_TYPE"));
								item.setID_CARD(rs.getString("ID_CARD"));
								item.setPASSPORT_NO(rs.getString("PASSPORT_NO"));
								item.setCOMPANY_REGISTRATION_NO(rs.getString("COMPANY_REGISTRATION_NO"));
								item.setCOMPANY_NAME(rs.getString("COMPANY_NAME"));
								item.setTITLE_ID(rs.getInt("TITLE_ID"));
								item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
								item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
								item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
								item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
								item.setFIRST_NAME(rs.getString("FIRST_NAME"));
								item.setMIDDLE_NAME(rs.getString("MIDDLE_NAME"));
								item.setLAST_NAME(rs.getString("LAST_NAME"));
								item.setOTHER_NAME(rs.getString("OTHER_NAME"));
								item.setCOUNTRY_NAME_TH(rs.getString("COUNTRY_NAME_TH"));
								item.setCOUNTRY_NAME_EN(rs.getString("COUNTRY_NAME_EN"));
								item.setEXCISE_REGISTRATION_NO(rs.getString("EXCISE_REGISTRATION_NO"));
								item.setMISTREAT_NO(rs.getInt("MISTREAT_NO"));
								item.setArrestMasPersonRelationship(getArrestMasPersonRelationship(rs.getInt("PERSON_ID"),req.getRELATIONSHIP_NAME()));

								return item;

			                }
			});      
		return dataList;
	}
}
