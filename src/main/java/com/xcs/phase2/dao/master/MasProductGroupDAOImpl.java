package com.xcs.phase2.dao.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.MasProductGroup;
import com.xcs.phase2.request.master.MasProductGroupgetByConReq;
import com.xcs.phase2.request.master.MasProductGroupupdDeleteReq;
import com.xcs.phase2.response.master.MasProductGroupinsAllResponse;
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
public class MasProductGroupDAOImpl extends MasterExt implements MasProductGroupDAO{

    private static final Logger log = LoggerFactory.getLogger(MasProductGroupDAOImpl.class);


    @Override
    public List<MasProductGroup> MasProductGroupgetByCon(MasProductGroupgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT *" +
                        "  FROM MAS_PRODUCT_GROUP" +
                        "  WHERE MAS_PRODUCT_GROUP.IS_ACTIVE = 1" );

                if(req.getTEXT_SEARCH() != null && !"".equals(req.getTEXT_SEARCH())){
                    sqlBuilder.append(" AND( LOWER(PRODUCT_GROUP_CODE) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ','')) OR LOWER(PRODUCT_GROUP_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))) ");
                }

                if(req.getPRODUCT_GROUP_ID() != null && !"".equals(req.getPRODUCT_GROUP_ID())){
                    sqlBuilder.append(" AND PRODUCT_GROUP_ID = '"+req.getPRODUCT_GROUP_ID()+"' ");
                }

                if(req.getGROUP_CODE_OLD() != null && !"".equals(req.getGROUP_CODE_OLD())){
                    sqlBuilder.append(" AND ( PRODUCT_GROUP_CODE_OLD1 = '"+req.getGROUP_CODE_OLD()+"' or PRODUCT_GROUP_CODE_OLD2 = '"+req.getGROUP_CODE_OLD()+"' or PRODUCT_GROUP_CODE_OLD3 = '"+req.getGROUP_CODE_OLD()+"') ");
                }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductGroup> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductGroup item = new MasProductGroup();
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
                item.setPRODUCT_GROUP_CODE_OLD1(rs.getString("PRODUCT_GROUP_CODE_OLD1"));
                item.setPRODUCT_GROUP_CODE_OLD2(rs.getString("PRODUCT_GROUP_CODE_OLD2"));
                item.setPRODUCT_GROUP_CODE_OLD3(rs.getString("PRODUCT_GROUP_CODE_OLD3"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));


                return item;
            }
        });

        return dataList;

    }

    @Override
    public MasProductGroupinsAllResponse MasProductGroupinsAll(MasProductGroup req) {
    	
    	MasProductGroupinsAllResponse res = new MasProductGroupinsAllResponse();
    
    	try {
    		String PRODUCT_GROUP_ID = getSequences("SELECT MAS_PRODUCT_GROUP_SEQ.NEXTVAL FROM DUAL");
    		
    		log.info("[getSequences] PRODUCT_GROUP_ID : "+PRODUCT_GROUP_ID);
    		
    		StringBuilder sqlBuilder = new StringBuilder()
			    .append("Insert into MAS_PRODUCT_GROUP (" +
			    		"PRODUCT_GROUP_ID," +
			    		"PRODUCT_GROUP_CODE," +
			    		"PRODUCT_GROUP_NAME," +
			    		"PRODUCT_GROUP_CODE_OLD1," +
			    		"PRODUCT_GROUP_CODE_OLD2," +
			    		"PRODUCT_GROUP_CODE_OLD3," +
			    		"IS_ACTIVE) " +
			    		"values (" +
			    		"'"+PRODUCT_GROUP_ID+"'," +
	    	    		"'"+req.getPRODUCT_GROUP_CODE()+"'," +
	    	    		"'"+req.getPRODUCT_GROUP_NAME()+"'," +
			    		"'"+req.getPRODUCT_GROUP_CODE_OLD1()+"'," +
			    		"'"+req.getPRODUCT_GROUP_CODE_OLD2()+"'," +
			    		"'"+req.getPRODUCT_GROUP_CODE_OLD3()+"'," +
			    		"'"+req.getIS_ACTIVE()+"' )");
    		
    		
				log.info("[SQL] : "+sqlBuilder.toString());
				
				getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
	            res.setPRODUCT_GROUP_ID(Integer.parseInt(PRODUCT_GROUP_ID));

	            res.setIsSuccess(Message.TRUE);
	            res.setMsg(Message.COMPLETE);

	            return res; 
	            
    	} catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setPRODUCT_GROUP_ID(0);
            return res;
        }
    	
    }
    
    @Override
    public Boolean MasProductGroupupdAll(MasProductGroup req) {
        return null;
    }

    @Override
    public Boolean MasProductGroupupdDelete(MasProductGroupupdDeleteReq req) {
        return null;
    }
}
