package com.xcs.phase2.dao.lawsult;

import com.xcs.phase2.model.lawsult.LawsuitProve;
import com.xcs.phase2.request.lawsult.LawsuiltProvegetByLawsuitIDReq;
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
public class LawsuitProveDAOImpl extends LawsultExt implements LawsuitProveDAO{
	
	private static final Logger log = LoggerFactory.getLogger(LawsuitProveDAOImpl.class);

	@Override
	public LawsuitProve LawsuiltProvegetByLawsuitID(LawsuiltProvegetByLawsuitIDReq req) {
		

		

		
		StringBuilder sqlBuilder = new StringBuilder()
			    .append("  SELECT OPS_PROVE.PROVE_ID, " +
						"  OPS_LAWSUIT.LAWSUIT_ID" +
						"  FROM OPS_LAWSUIT" +
						"  INNER JOIN OPS_PROVE on OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID" +
						"  WHERE OPS_LAWSUIT.IS_ACTIVE =1" +
						"  AND OPS_PROVE.IS_ACTIVE = 1" +
						"  AND OPS_LAWSUIT.LAWSUIT_ID = '"+req.getLAWSUIT_ID()+"' ");

				log.info("[SQL] : "+sqlBuilder.toString());
		
		return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<LawsuitProve>() {

			public LawsuitProve extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					
					LawsuitProve item = new LawsuitProve();
					item.setPROVE_ID(rs.getInt("PROVE_ID"));
					item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
					return item;
				}
				
				return null;
			}
		});
	


	}

}
