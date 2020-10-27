package com.xcs.phase2.dao.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.MasProductUnit;
import com.xcs.phase2.model.master.MasProductUnitMapping;
import com.xcs.phase2.request.master.MasProductUnitgetByConAdvReq;
import com.xcs.phase2.request.master.MasProductUnitgetByConReq;
import com.xcs.phase2.request.master.MasProductUnitgetByKeywordReq;
import com.xcs.phase2.request.master.MasProductUnitupdDeleteReq;
import com.xcs.phase2.response.master.MasProductUnitgetByConformasResponse;
import com.xcs.phase2.response.master.MasProductUnitinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class MasProductUnitDAOImpl extends MasterExt implements MasProductUnitDAO {

    private static final Logger log = LoggerFactory.getLogger(MasProductUnitDAOImpl.class);

    @Override
    public List<MasProductUnit> MasProductUnitgetByKeyword(MasProductUnitgetByKeywordReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT *" +
                        "    FROM MAS_PRODUCT_UNIT" +
                        "    WHERE IS_ACTIVE = 1" +
                        "    AND" +
                        "    (  " +
                        "        lower(UNIT_NAME_TH) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "        OR lower(UNIT_NAME_EN) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "        OR lower(UNIT_SHORT_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "    ) ORDER BY UNIT_ID asc" );

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductUnit> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductUnit mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductUnit item = new MasProductUnit();
                item.setUNIT_ID(rs.getInt("UNIT_ID"));
                item.setUNIT_NAME_TH(rs.getString("UNIT_NAME_TH"));
                item.setUNIT_NAME_EN(rs.getString("UNIT_NAME_EN"));
                item.setUNIT_SHORT_NAME(rs.getString("UNIT_SHORT_NAME"));
                item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                item.setCREATE_USER_ACCOUNT_ID(rs.getInt("CREATE_USER_ACCOUNT_ID"));
                item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                item.setEFEXPIRE_DATE(rs.getString("EFEXPIRE_DATE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setUNIT_CODE(rs.getString("UNIT_CODE"));
                return item;
            }
        });
        return dataList;
    }

    @Override
    public List<MasProductUnit> MasProductUnitgetByConAdv(MasProductUnitgetByConAdvReq req) {
    	StringBuilder sqlBuilder = new StringBuilder()
                .append("   SELECT DISTINCT MAS_PRODUCT_UNIT.*"+
                		"	FROM MAS_PRODUCT_UNIT"+
                		"	LEFT JOIN MAS_PRODUCT_UNIT_MAPPING ON MAS_PRODUCT_UNIT_MAPPING.UNIT_CODE = MAS_PRODUCT_UNIT.UNIT_CODE"+
                		"	AND MAS_PRODUCT_UNIT_MAPPING.IS_ACTIVE = 1"+
                		"	where MAS_PRODUCT_UNIT.IS_ACTIVE = 1" );
    	
    	 if(req.getTEXT_SEARCH() != null && !"".equals(req.getTEXT_SEARCH())){
             sqlBuilder.append(" AND (lower(UNIT_NAME_TH) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')"+        
             				" OR lower(UNIT_NAME_EN) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')"+        
             				" OR lower(UNIT_SHORT_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%'))");
         }
    	
    	 if(req.getPRODUCT_GROUP_CODE() != null && !"".equals(req.getPRODUCT_GROUP_CODE())){
             sqlBuilder.append(" AND PRODUCT_GROUP_CODE= '"+req.getPRODUCT_GROUP_CODE()+"'");
         }
    	
    	 sqlBuilder.append(" ORDER BY UNIT_NAME_TH asc");
    	
    	log.info("[SQL]  MasProductUnitgetByConAdv : " + sqlBuilder.toString());
    	
    	 @SuppressWarnings("unchecked")
         List<MasProductUnit> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

             public MasProductUnit mapRow(ResultSet rs, int rowNum) throws SQLException {
                 MasProductUnit item = new MasProductUnit();
                 item.setUNIT_ID(rs.getInt("UNIT_ID"));
                 item.setUNIT_NAME_TH(rs.getString("UNIT_NAME_TH"));
                 item.setUNIT_NAME_EN(rs.getString("UNIT_NAME_EN"));
                 item.setUNIT_SHORT_NAME(rs.getString("UNIT_SHORT_NAME"));
                 item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                 item.setCREATE_USER_ACCOUNT_ID(rs.getInt("CREATE_USER_ACCOUNT_ID"));
                 item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                 item.setEFEXPIRE_DATE(rs.getString("EFEXPIRE_DATE"));
                 item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                 item.setUNIT_CODE(rs.getString("UNIT_CODE"));
                 return item;
             }
         });
         return dataList;
    }

    @Override
    public List<MasProductUnitMapping> MasProductUnitgetByCon(MasProductUnitgetByConReq req) {
    	
    	StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT MAS_PRODUCT_UNIT.UNIT_ID, MAS_PRODUCT_UNIT.UNIT_NAME_TH, MAS_PRODUCT_UNIT.UNIT_NAME_EN, MAS_PRODUCT_UNIT.UNIT_SHORT_NAME, MAS_PRODUCT_UNIT.IS_ACTIVE, MAS_PRODUCT_UNIT.UNIT_CODE,"+
                		" MAS_PRODUCT_UNIT_MAPPING.UNIT_MAPPING_ID, MAS_PRODUCT_UNIT_MAPPING.PRODUCT_GROUP_CODE, MAS_PRODUCT_UNIT_MAPPING.USED_FOR, MAS_PRODUCT_UNIT_MAPPING.PRODUCT_CATEGORY_CODE"+
                		" FROM MAS_PRODUCT_UNIT INNER JOIN MAS_PRODUCT_UNIT_MAPPING ON MAS_PRODUCT_UNIT.UNIT_CODE = MAS_PRODUCT_UNIT_MAPPING.UNIT_CODE"+
                		" WHERE MAS_PRODUCT_UNIT_MAPPING.IS_ACTIVE = 1" );

        
        if(req.getUNIT_NAME() != null && !"".equals(req.getUNIT_NAME())){
            sqlBuilder.append(" AND ((lower(UNIT_NAME_TH) LIKE LOWER('%"+req.getUNIT_NAME()+"%')"+        
            				" OR lower(UNIT_NAME_EN) LIKE LOWER('%"+req.getUNIT_NAME()+"%')"+        
            				" OR lower(UNIT_SHORT_NAME) LIKE LOWER('%"+req.getUNIT_NAME()+"%'))))");
        }
        if(req.getUNIT_ID() != 0 && !"".equals(req.getUNIT_ID())){
            sqlBuilder.append(" AND MAS_PRODUCT_UNIT.UNIT_ID = '"+req.getUNIT_ID()+"'");
        }
        if(req.getUSED_FOR() != null && !"".equals(req.getUSED_FOR())){
            sqlBuilder.append("AND MAS_PRODUCT_UNIT_MAPPING.USED_FOR = '"+req.getUSED_FOR()+"'");
        }
        if(req.getPRODUCT_GROUP_CODE() != null && !"".equals(req.getPRODUCT_GROUP_CODE())){
            sqlBuilder.append("AND MAS_PRODUCT_UNIT_MAPPING.PRODUCT_GROUP_CODE = '"+req.getPRODUCT_GROUP_CODE()+"'");
        }
    		
        log.info("[SQL MasProductUnitgetByCon]  : " + sqlBuilder.toString());
        
        @SuppressWarnings("unchecked")
        List<MasProductUnitMapping> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductUnitMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
            	
            	MasProductUnitMapping item = new MasProductUnitMapping();
                item.setUNIT_ID(rs.getInt("UNIT_ID"));
                item.setUNIT_NAME_TH(rs.getString("UNIT_NAME_TH"));
                item.setUNIT_NAME_EN(rs.getString("UNIT_NAME_EN"));
                item.setUNIT_SHORT_NAME(rs.getString("UNIT_SHORT_NAME"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setUNIT_CODE(rs.getString("UNIT_CODE"));
                item.setUNIT_MAPPING_ID(rs.getInt("UNIT_MAPPING_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setUSED_FOR(rs.getString("USED_FOR"));
                item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                return item;
            }
        });
        return dataList;
        
    }

    @Override
    public MasProductUnitinsAllResponse MasProductUnitinsAll(MasProductUnit req) {
    	
    	MasProductUnitinsAllResponse res = new MasProductUnitinsAllResponse ();
    	
       	String UNIT_ID = getSequences("SELECT MAS_PRODUCT_UNIT_SEQ1.NEXTVAL FROM DUAL");
    	
    	StringBuilder sqlBuilder = new StringBuilder()
                .append(" INSERT INTO MAS_PRODUCT_UNIT ("+
                		" UNIT_ID,"+ 
                		" UNIT_NAME_TH,"+
                		" UNIT_NAME_EN,"+
                		" UNIT_SHORT_NAME,"+ 
                		" CREATE_DATE,"+ 
                		" CREATE_USER_ACCOUNT_ID,"+
                		" UPDATE_DATE,"+
                		" EFEXPIRE_DATE,"+
                		" IS_ACTIVE,"+
                		" UNIT_CODE)"+
                		" VALUES ("+
                		" '"+UNIT_ID+"',"+
                		" '"+req.getUNIT_NAME_TH()+"',"+
                		" '"+req.getUNIT_NAME_EN()+"',"+
                		" '"+req.getUNIT_SHORT_NAME()+"',"+
                		" (SELECT SYSTIMESTAMP FROM dual),"+
                		" '"+req.getCREATE_USER_ACCOUNT_ID()+"',"+
                		" NULL,"+
                		" NULL,"+
                		" 1,"+
                		" '"+UNIT_ID+"')" );
    	log.info("[SQL UNIT_ID]  : " + UNIT_ID);
    	log.info("[SQL MasProductUnitinsAll]  : " + sqlBuilder.toString());
    	
    	getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
        res.setUNIT_ID(Integer.parseInt(UNIT_ID));

        res.setIsSuccess(Message.TRUE);
        res.setMsg(Message.COMPLETE);
    	
        return res;
    }

    @Override
    public Boolean MasProductUnitupdAll(MasProductUnit req) {
    	
    	StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE MAS_PRODUCT_UNIT SET "+
                		" UNIT_NAME_TH = " +"'"+req.getUNIT_NAME_TH()+"'," +
                		" UNIT_NAME_EN = " +"'"+req.getUNIT_NAME_EN()+"'," +
                		" UNIT_SHORT_NAME = " +"'"+req.getUNIT_SHORT_NAME()+"'," +
                		" UPDATE_DATE = (SELECT SYSTIMESTAMP FROM dual)" +
                        " WHERE UNIT_ID = " +"'"+req.getUNIT_ID()+"'" );
	    	    		
		log.info("[SQL MasProductUnitupdAll] : "+sqlBuilder.toString());
		
		getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
		
        return true;
    }

    
    @Override
    public Boolean MasProductUnitupdDelete(MasProductUnitupdDeleteReq req) {
        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE MAS_PRODUCT_UNIT SET IS_ACTIVE = '0' WHERE UNIT_ID = '"+req.getUNIT_ID()+"' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE MAS_PRODUCT_UNIT_MAPPING SET IS_ACTIVE = '0' WHERE UNIT_CODE = "+req.getUNIT_ID()+"  ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
        return true;
 }
    
    @Override
    public MasProductUnitgetByConformasResponse MasProductUnitgetByConformas(MasProductUnitgetByConReq req) {
    	
    	StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT "+
                		" UNIT_ID,"+ 
                		" UNIT_NAME_TH,"+
                		" UNIT_NAME_EN,"+
                		" UNIT_SHORT_NAME,"+ 
                		" UNIT_CODE"+
                		" FROM MAS_PRODUCT_UNIT"+
                		" WHERE UNIT_ID = "+req.getUNIT_ID());
    	
    	log.info("[SQL MasProductUnitgetByConformas]  : " + sqlBuilder.toString());
    	
        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasProductUnitgetByConformasResponse>() {

            public MasProductUnitgetByConformasResponse extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                	MasProductUnitgetByConformasResponse item = new MasProductUnitgetByConformasResponse();
                    item.setUNIT_ID(rs.getInt("UNIT_ID"));
                    item.setUNIT_NAME_TH(rs.getString("UNIT_NAME_TH"));
                    item.setUNIT_NAME_EN(rs.getString("UNIT_NAME_EN"));
                    item.setUNIT_SHORT_NAME(rs.getString("UNIT_SHORT_NAME"));
                    item.setUNIT_CODE(rs.getString("UNIT_CODE"));
                    item.setMasProductUnitMapping(MasProductMappinggetByCon(rs.getString("UNIT_CODE")));

                    return item;
                }

                return null;
            }
        });
    }
}

