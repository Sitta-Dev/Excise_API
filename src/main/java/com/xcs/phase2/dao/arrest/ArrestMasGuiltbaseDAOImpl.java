package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestMasGuiltbase;
import com.xcs.phase2.model.arrest.NewArrestMasGuiltbase;
import com.xcs.phase2.request.arrest.ArrestMasGuiltbasegetByKeywordReq;
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
public class ArrestMasGuiltbaseDAOImpl extends ArrestExt implements ArrestMasGuiltbaseDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestMasGuiltbaseDAOImpl.class);

	@Override
	public List<ArrestMasGuiltbase> ArrestMasGuiltbasegetByKeyword(ArrestMasGuiltbasegetByKeywordReq req) {

		StringBuilder sqlBuilder = new StringBuilder()
			    .append(" SELECT" +
			    		" MAS_LAW_GUILTBASE.GUILTBASE_ID," +
			    		"MAS_LAW_GUILTBASE.GUILTBASE_NAME," +
			    		"MAS_LAW_GUILTBASE.FINE," +
			    		"MAS_LAW_GUILTBASE.IS_PROVE," +
			    		"MAS_LAW_GUILTBASE.IS_COMPARE," +
			    		"MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME," +
			    		"MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC," +
			    		"MAS_LAW_GROUP_SECTION.SECTION_NAME," +
			    		"MAS_LAW_PENALTY.PENALTY_DESC" +
			    		" FROM MAS_LAW_GUILTBASE")
			    .append(" INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
			    		" INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
			    		" INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
			    		" LEFT JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID" +
			    		" AND MAS_LAW_PENALTY.IS_ACTIVE = 1" +
			    		" WHERE MAS_LAW_GUILTBASE.IS_ACTIVE = 1" +
			    		" AND MAS_LAW_GROUP_SUBSECTION_RULE.IS_ACTIVE = 1" +
			    		" AND MAS_LAW_GROUP_SUBSECTION.IS_ACTIVE = 1" +
			    		" AND MAS_LAW_GROUP_SECTION.IS_ACTIVE = 1" +
			    		" AND" +
			    		" (" +
			    		"  LOWER(MAS_LAW_GUILTBASE.GUILTBASE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
			    		"  OR LOWER(MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) LIKE LOWER('"+req.getTEXT_SEARCH()+"%')" +
			    		"  OR LOWER(MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC) LIKE LOWER('"+req.getTEXT_SEARCH()+"%')" +
			    		"  OR LOWER(MAS_LAW_GROUP_SECTION.SECTION_NAME) LIKE LOWER('"+req.getTEXT_SEARCH()+"%')" +
			    		"  OR LOWER(MAS_LAW_PENALTY.PENALTY_DESC) LIKE LOWER('"+req.getTEXT_SEARCH()+"%')" +
			    		" )" +
			    		" ORDER BY MAS_LAW_GUILTBASE.GUILTBASE_ID ASC");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ArrestMasGuiltbase> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ArrestMasGuiltbase mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArrestMasGuiltbase item = new ArrestMasGuiltbase();
				item.setGUILTBASE_ID(rs.getInt("GUILTBASE_ID"));
				item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
				item.setFINE(rs.getString("FINE"));
				item.setIS_PROVE(rs.getInt("IS_PROVE"));
				item.setIS_COMPARE(rs.getInt("IS_COMPARE"));
				item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
				item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
				item.setSECTION_NAME(rs.getString("SECTION_NAME"));
				item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));

				return item;
			}
		});

		return dataList;

	}

	@Override
	public List<NewArrestMasGuiltbase> NewArrestMasGuiltbasegetByKeyword(ArrestMasGuiltbasegetByKeywordReq req) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT DISTINCT" +
						"    MAS_LAW_GUILTBASE.GUILTBASE_ID," +
						"    MAS_LAW_GUILTBASE.GUILTBASE_NAME," +
						"    MAS_LAW_GUILTBASE.FINE," +
						"    MAS_LAW_GUILTBASE.IS_PROVE," +
						"    MAS_LAW_GUILTBASE.IS_COMPARE," +
						"    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME," +
						"    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC," +
						"    MAS_LAW_GROUP_SECTION.SECTION_NAME," +
						"    MAS_LAW_PENALTY.PENALTY_DESC" +
						"    FROM MAS_LAW_GUILTBASE" +
						"    INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
						"    INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
						"    INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
						"    LEFT JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID" +
						"    AND MAS_LAW_PENALTY.IS_ACTIVE = 1" +
						"    WHERE MAS_LAW_GUILTBASE.IS_ACTIVE = 1" +
						"    AND MAS_LAW_GROUP_SUBSECTION_RULE.IS_ACTIVE = 1" +
						"    AND MAS_LAW_GROUP_SUBSECTION.IS_ACTIVE = 1" +
						"    AND MAS_LAW_GROUP_SECTION.IS_ACTIVE = 1" +
						"    AND" +
						"    (" +
						"        LOWER(MAS_LAW_GUILTBASE.GUILTBASE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
						"        OR LOWER(MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) LIKE LOWER('"+req.getTEXT_SEARCH()+"%')" +
						"        OR LOWER(MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC) LIKE LOWER('"+req.getTEXT_SEARCH()+"%')" +
						"        OR LOWER(MAS_LAW_GROUP_SECTION.SECTION_NAME) LIKE LOWER('"+req.getTEXT_SEARCH()+"%')" +
						"        OR LOWER(MAS_LAW_PENALTY.PENALTY_DESC) LIKE LOWER('"+req.getTEXT_SEARCH()+"%')" +
						"    )" +
						"    ORDER BY MAS_LAW_GUILTBASE.GUILTBASE_ID ASC");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<NewArrestMasGuiltbase> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public NewArrestMasGuiltbase mapRow(ResultSet rs, int rowNum) throws SQLException {
				NewArrestMasGuiltbase item = new NewArrestMasGuiltbase();
				item.setGUILTBASE_ID(rs.getInt("GUILTBASE_ID"));
				item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
				item.setFINE(rs.getString("FINE"));
				item.setIS_PROVE(rs.getInt("IS_PROVE"));
				item.setIS_COMPARE(rs.getInt("IS_COMPARE"));
				item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
				item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
				item.setSECTION_NAME(rs.getString("SECTION_NAME"));
				item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));

				return item;
			}
		});

		return dataList;

	}
}
