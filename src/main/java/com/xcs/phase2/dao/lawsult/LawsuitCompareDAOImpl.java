package com.xcs.phase2.dao.lawsult;

import com.xcs.phase2.model.lawsult.LawsuitCompare;
import com.xcs.phase2.request.lawsult.LawsuiltComparegetByLawsuitIDReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;


@Service
@Transactional
public class LawsuitCompareDAOImpl extends LawsultExt implements LawsuitCompareDAO{

	private static final Logger log = LoggerFactory.getLogger(LawsuitCompareDAOImpl.class);

	@Override
	public LawsuitCompare LawsuiltComparegetByLawsuitID(LawsuiltComparegetByLawsuitIDReq req) {
		
		StringBuilder sqlBuilder = new StringBuilder()
			    .append("SELECT " +
			    		"OPS_COMPARE.COMPARE_ID, " +
			    		"OPS_LAWSUIT.LAWSUIT_ID" +
			    		" FROM OPS_LAWSUIT" +
			    		" INNER JOIN OPS_COMPARE on OPS_LAWSUIT.LAWSUIT_ID = OPS_COMPARE.LAWSUIT_ID" +
			    		" WHERE OPS_LAWSUIT.IS_ACTIVE = 1" +
			    		" AND OPS_COMPARE.IS_ACTIVE = 1" +
			    		" AND OPS_LAWSUIT.LAWSUIT_ID = '"+req.getLAWSUIT_ID()+"' ");

		log.info("[SQL]  : " + sqlBuilder.toString());
		
		return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<LawsuitCompare>() {

			public LawsuitCompare extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					
					LawsuitCompare item = new LawsuitCompare();
					item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
					item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
					return item;
				}
				
				return null;
			}
		});

		

	}

	
}
