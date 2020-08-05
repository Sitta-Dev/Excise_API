package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.model.investigate.InvestigateLawsuitResultCount;
import com.xcs.phase2.request.investigate.InvestigateLawsuitResultCountgetByLawbreakerIDReq;
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
public class InvestigateLawsuitResultCountDAOImpl extends InvestigateExt implements InvestigateLawsuitResultCountDAO {

    private static final Logger log = LoggerFactory.getLogger(InvestigateDetailProductDAOImpl.class);

    @Override
    public InvestigateLawsuitResultCount InvestigateLawsuitResultCountgetByLawbreakerID(InvestigateLawsuitResultCountgetByLawbreakerIDReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("  select count(*) as RESULT_COUNT" +
                        "  from" +
                        "  (" +
                        "    select " +
                        "    OPS_LAWSUIT.LAWSUIT_ID" +
                        "    from OPS_ARREST_LAWBREAKER" +
                        "    inner join OPS_ARREST_INDICTMENT_DETAIL on OPS_ARREST_LAWBREAKER.LAWBREAKER_ID = OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID" +
                        "    inner join OPS_ARREST_INDICTMENT on OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID" +
                        "    inner join OPS_LAWSUIT on OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID" +
                        "    where OPS_ARREST_LAWBREAKER.MISTREAT_NO = '"+req.getMISTREAT_NO()+"' " +
                        "    and OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1 " +
                        "    and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "    and OPS_LAWSUIT.IS_LAWSUIT = 1" +
                        "    group by OPS_LAWSUIT.LAWSUIT_ID" +
                        "  )" );

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<InvestigateLawsuitResultCount>() {

            public InvestigateLawsuitResultCount extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    InvestigateLawsuitResultCount item = new InvestigateLawsuitResultCount();
                    item.setRESULT_COUNT(rs.getInt("RESULT_COUNT"));

                    return item;
                }

                return null;
            }
        });
    }
}
