package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.arrest.ArrestNotice;
import com.xcs.phase2.request.arrest.ArrestNoticegetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestNoticegetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestNoticeupdByConReq;
import com.xcs.phase2.request.arrest.ArrestNoticeupdDeleteReq;
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
public class ArrestNoticeDAOImpl extends ArrestExt implements ArrestNoticeDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestNoticeDAOImpl.class);

	@Override
	public List<ArrestNotice> ArrestNoticegetByKeyword(ArrestNoticegetByKeywordReq req) {

		String str = "";

		if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

			if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

				if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
					str = " ";
				}else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
					str = " AND SUBSTR(OPS_NOTICE.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) "  ;
				}else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
					str = " AND SUBSTR(OPS_NOTICE.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) "  ;
				}else {
					str = " AND OPS_NOTICE.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' ";
				}

			}

		}

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
						"    OPS_NOTICE_STAFF.CONTRIBUTOR_ID ," +
						"    ops_notice.is_authority " +
						"    FROM OPS_NOTICE" +
						"    INNER JOIN OPS_NOTICE_STAFF ON OPS_NOTICE.NOTICE_ID = OPS_NOTICE_STAFF.NOTICE_ID" +
						"    WHERE OPS_NOTICE.IS_ACTIVE = 1" +
						"    AND OPS_NOTICE_STAFF.IS_ACTIVE = 1" +
						//"    AND OPS_NOTICE_STAFF.CONTRIBUTOR_ID IN (7,8)" +
						" 	 AND( " +
						"        CASE " +
						"            WHEN (ops_notice.is_authority = 0)  AND OPS_NOTICE_STAFF.CONTRIBUTOR_ID = 7" +
						"              THEN 1" +
						"            WHEN (ops_notice.is_authority = 1) AND OPS_NOTICE_STAFF.CONTRIBUTOR_ID = 8 " +
						"              THEN 1" +
						"            ELSE 0" +
						"        END" +
						"       ) = 1 " +
						"    AND OPS_NOTICE.IS_ARREST = 0" +
						"    AND" +
						"    (" +
						"      LOWER(OPS_NOTICE.NOTICE_CODE) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_NOTICE_STAFF.TITLE_NAME_TH||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_NOTICE_STAFF.TITLE_NAME_EN||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_NOTICE_STAFF.TITLE_SHORT_NAME_TH||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_NOTICE_STAFF.TITLE_SHORT_NAME_EN||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"      OR LOWER(OPS_NOTICE.OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
						"    )" + str +
						"    ORDER BY OPS_NOTICE.NOTICE_CODE DESC" );

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

	@Override
	public List<ArrestNotice> ArrestNoticegetByConAdv(ArrestNoticegetByConAdvReq req) {
		
		String tempNoticeDateFrom  = "";
		String tempNoticeDateTo = "";

		String str = "";

		if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

			if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

				if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
					str = " ";
				}else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
					str = " AND SUBSTR(OPS_NOTICE.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) "  ;
				}else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
					str = " AND SUBSTR(OPS_NOTICE.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) "  ;
				}else {
					str = " AND OPS_NOTICE.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' ";
				}

			}

		}
		 
		 
		 if(!"".equals(req.getNOTICE_DATE_FROM())) {
			 tempNoticeDateFrom = String.format("%s %s", req.getNOTICE_DATE_FROM(),Pattern.TIME_FROM);
		 }
		 
		 if(!"".equals(req.getNOTICE_DATE_TO())) {
			 tempNoticeDateTo = String.format("%s %s", req.getNOTICE_DATE_TO(),Pattern.TIME_TO);
		 }

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
						"    OPS_NOTICE_STAFF.CONTRIBUTOR_ID ," +
						"    ops_notice.is_authority " +
						"    FROM OPS_NOTICE" +
						"    INNER JOIN OPS_NOTICE_STAFF ON OPS_NOTICE.NOTICE_ID = OPS_NOTICE_STAFF.NOTICE_ID" +
						"    WHERE OPS_NOTICE.IS_ACTIVE = 1" +
						"    AND OPS_NOTICE_STAFF.IS_ACTIVE = 1" +
						//"    AND OPS_NOTICE_STAFF.CONTRIBUTOR_ID IN (7,8)" +
								" AND( " +
								"        CASE " +
								"            WHEN (ops_notice.is_authority = 0)  AND OPS_NOTICE_STAFF.CONTRIBUTOR_ID = 7" +
								"              THEN 1" +
								"            WHEN (ops_notice.is_authority = 1) AND OPS_NOTICE_STAFF.CONTRIBUTOR_ID = 8 " +
								"              THEN 1" +
								"            ELSE 0" +
								"        END" +
								"       ) = 1 " +
						"    AND OPS_NOTICE.IS_ARREST = 0" );
		
		if(req.getNOTICE_CODE() != null && !"".equals(req.getNOTICE_CODE())) {
			sqlBuilder.append(" AND LOWER(OPS_NOTICE.NOTICE_CODE) LIKE LOWER(REPLACE('%"+req.getNOTICE_CODE()+"%',' ','')) ");
		}
		
		if(req.getNOTICE_DATE_FROM() != null && !"".equals(req.getNOTICE_DATE_FROM()) && req.getNOTICE_DATE_TO() != null && !"".equals(req.getNOTICE_DATE_TO())) {
			sqlBuilder.append(" AND OPS_NOTICE.NOTICE_DATE BETWEEN  to_date(nvl('"+tempNoticeDateFrom+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempNoticeDateTo+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
		}
		
		if(req.getSTAFF_NAME() != null && !"".equals(req.getSTAFF_NAME())) {
			sqlBuilder.append(" AND " +
					"(" +
					"  LOWER(OPS_NOTICE_STAFF.TITLE_NAME_TH||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
					"  OR LOWER(OPS_NOTICE_STAFF.TITLE_NAME_EN||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
					"  OR LOWER(OPS_NOTICE_STAFF.TITLE_SHORT_NAME_TH||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
					"  OR LOWER(OPS_NOTICE_STAFF.TITLE_SHORT_NAME_EN||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
					")");
		}
		
		if(req.getOFFICE_NAME() != null && !"".equals(req.getOFFICE_NAME())) {
			sqlBuilder.append(" AND LOWER(OPS_NOTICE.OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getOFFICE_NAME()+"%',' ','')) ");
		}
		
//		if(req.getSUSPECT_NAME() != null && !"".equals(req.getSUSPECT_NAME())) {
//			sqlBuilder.append(" (" +
//					"  LOWER(OPS_NOTICE_SUSPECT.TITLE_NAME_TH||OPS_NOTICE_SUSPECT.FIRST_NAME||OPS_NOTICE_SUSPECT.MIDDLE_NAME||OPS_NOTICE_SUSPECT.LAST_NAME||OPS_NOTICE_SUSPECT.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getSUSPECT_NAME()+"%',' ',''))" +
//					"  OR LOWER(OPS_NOTICE_SUSPECT.TITLE_NAME_EN||OPS_NOTICE_SUSPECT.FIRST_NAME||OPS_NOTICE_SUSPECT.MIDDLE_NAME||OPS_NOTICE_SUSPECT.LAST_NAME||OPS_NOTICE_SUSPECT.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getSUSPECT_NAME()+"%',' ',''))" +
//					"  OR LOWER(OPS_NOTICE_SUSPECT.TITLE_SHORT_NAME_TH||OPS_NOTICE_SUSPECT.FIRST_NAME||OPS_NOTICE_SUSPECT.MIDDLE_NAME||OPS_NOTICE_SUSPECT.LAST_NAME||OPS_NOTICE_SUSPECT.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getSUSPECT_NAME()+"%',' ',''))" +
//					"  OR LOWER(OPS_NOTICE_SUSPECT.TITLE_SHORT_NAME_EN||OPS_NOTICE_SUSPECT.FIRST_NAME||OPS_NOTICE_SUSPECT.MIDDLE_NAME||OPS_NOTICE_SUSPECT.LAST_NAME||OPS_NOTICE_SUSPECT.OTHER_NAME) LIKE LOWER(REPLACE('%"+req.getSUSPECT_NAME()+"%',' ',''))" +
//					") ");
//		}
		
		sqlBuilder.append(" "+str+" ORDER BY OPS_NOTICE.NOTICE_CODE DESC ");

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

	@Override
	public Boolean ArrestNoticeupdByCon(List<ArrestNoticeupdByConReq> request) {

		if(request != null) {
	    	log.info("[Sub] Size : "+request.size());
	    	
		    if(request.size() > 0){
		    	
		    	for(ArrestNoticeupdByConReq req : request){
		    		StringBuilder sqlBuilderSub = new StringBuilder()
		    				.append("UPDATE OPS_NOTICE "
		    						+ "SET "
		    						+"ARREST_ID = '"+req.getARREST_ID()+"',"
		    						+"IS_ARREST = '1' "
		    						+ "WHERE NOTICE_ID = '"+req.getNOTICE_ID()+"' ");
		    				log.info("[SQL] : "+sqlBuilderSub.toString());
		    				
		    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
		    	}
		    }
	    }
		
		return true;
	}

	@Override
	public Boolean ArrestNoticeupdDelete(List<ArrestNoticeupdDeleteReq> request) {

		if(request != null) {
	    	log.info("[Sub] Size : "+request.size());
	    	
		    if(request.size() > 0){
		    	
		    	for(ArrestNoticeupdDeleteReq req : request){
		    		StringBuilder sqlBuilderSub = new StringBuilder()
		    				.append("UPDATE OPS_NOTICE "
		    						+ "SET "
		    						+"ARREST_ID = '0',"
		    						+"IS_ARREST = '0' "
		    						+ "WHERE NOTICE_ID = '"+req.getNOTICE_ID()+"' ");
		    				log.info("[SQL] : "+sqlBuilderSub.toString());
		    				
		    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
		    	}
		    }
	    }
		
		return true;
	}
}
