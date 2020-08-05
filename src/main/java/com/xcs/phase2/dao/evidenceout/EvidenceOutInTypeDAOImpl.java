package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.model.evidenceout.EvidenceOutInType;
import com.xcs.phase2.request.evidenceout.EvidenceOutInTypegetByConReq;
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
public class EvidenceOutInTypeDAOImpl extends EvidenceOutExt implements EvidenceOutInTypeDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutInTypeDAOImpl.class);

    @Override
    public EvidenceOutInType EvidenceOutInTypegetByCon(EvidenceOutInTypegetByConReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("Select" +
                        " distinct OPS_EVIDENCE_IN.EVIDENCE_IN_TYPE" +
                        " from " +
                        " OPS_EVIDENCE_OUT" +
                        " left join OPS_EVIDENCE_OUT_ITEM on OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID=OPS_EVIDENCE_OUT_ITEM.EVIDENCE_OUT_ID" +
                        " left join OPS_EVIDENCE_OUT_STAFF on OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID=OPS_EVIDENCE_OUT_STAFF.EVIDENCE_OUT_ID" +
                        " left join OPS_EVIDENCE_STOCK_BALANCE on OPS_EVIDENCE_OUT_ITEM.STOCK_ID=OPS_EVIDENCE_STOCK_BALANCE.STOCK_ID" +
                        " left join OPS_EVIDENCE_IN_ITEM on OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID=OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID" +
                        " left join OPS_EVIDENCE_IN on OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID" +
                        " where OPS_EVIDENCE_OUT.EVIDENCE_IN_ID = "+req.getEVIDENCE_IN_ID());

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceOutInType>() {

            public EvidenceOutInType extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceOutInType item = new EvidenceOutInType();
                    item.setEVIDENCE_IN_TYPE(rs.getInt("EVIDENCE_IN_TYPE"));
                    return item;
                }

                return null;
            }
        });

    }
}
