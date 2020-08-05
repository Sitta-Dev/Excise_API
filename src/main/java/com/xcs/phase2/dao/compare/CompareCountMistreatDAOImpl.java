package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.CompareCountMistreat;
import com.xcs.phase2.request.compare.CompareCountMistreatgetByConReq;
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
public class CompareCountMistreatDAOImpl extends CompareExt implements CompareCountMistreatDAO{

    private static final Logger log = LoggerFactory.getLogger(CompareCountMistreatDAOImpl.class);

    @Override
    public CompareCountMistreat CompareCountMistreatgetByCon(CompareCountMistreatgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT COUNT(*) as MISTREAT " +
                        "FROM" +
                        "(" +
                        "  SELECT OPS_LAWSUIT.LAWSUIT_ID" +
                        "  FROM OPS_ARREST_LAWBREAKER" +
                        "  INNER JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_LAWBREAKER.LAWBREAKER_ID = OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID" +
                        "  INNER JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID" +
                        "  INNER JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID" +
                        "  INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
                        "  INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
                        "  WHERE OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
                        "  AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
                        "  AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "  AND OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "  AND OPS_LAWSUIT.IS_LAWSUIT = 1" +
                        "  AND OPS_ARREST_LAWBREAKER.PERSON_ID = '"+req.getPERSON_ID()+"' " +
                        "  AND MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = '"+req.getSUBSECTION_ID()+"' " +
                        "  GROUP BY OPS_LAWSUIT.LAWSUIT_ID" +
                        ")");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<CompareCountMistreat>() {

            public CompareCountMistreat extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    CompareCountMistreat item = new CompareCountMistreat();
                    item.setMISTREAT(rs.getInt("MISTREAT"));

                    return item;
                }

                return null;
            }
        });
    }
}
