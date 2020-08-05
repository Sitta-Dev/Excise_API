package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.model.evidencein.EvidenceInOut;
import com.xcs.phase2.request.evidencein.EvidenceInOutgetByWarehouseIDReq;
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
public class EvidenceInOutDAOImpl extends EvidenceInExt implements EvidenceInOutDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInOutDAOImpl.class);

    @Override
    public List<EvidenceInOut> EvidenceInOutgetByWarehouseID(EvidenceInOutgetByWarehouseIDReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "EVIDENCE_OUT_ID," +
                        "to_char(EVIDENCE_OUT_DATE,'YYYY-MM-DD') as EVIDENCE_OUT_DATE," +
                        "to_char(EVIDENCE_OUT_DATE,'HH24:MI:SS') as EVIDENCE_OUT_TIME," +
                        "EVIDENCE_OUT_TYPE," +
                        "EVIDENCE_OUT_NO," +
                        "to_char(EVIDENCE_OUT_NO_DATE,'YYYY-MM-DD') as EVIDENCE_OUT_NO_DATE," +
                        "to_char(EVIDENCE_OUT_NO_DATE,'HH24:MI:SS') as EVIDENCE_OUT_NO_TIME," +
                        "BOOK_NO," +
                        "RECEIPT_NO," +
                        "to_char(PAY_DATE,'YYYY-MM-DD') as PAY_DATE," +
                        "to_char(PAY_DATE,'HH24:MI:SS') as PAY_TIME," +
                        "to_char(APPROVE_DATE,'YYYY-MM-DD') as APPROVE_DATE," +
                        "to_char(APPROVE_DATE,'HH24:MI:SS') as APPROVE_TIME," +
                        "to_char(RETURN_DATE,'YYYY-MM-DD HH24:MI:SS') as RETURN_DATE," +
                        "REMARK," +
                        "WAREHOUSE_ID," +
                        "APPROVE_NO," +
                        "EVIDENCE_OUT_CODE," +
                        "OFFICE_CODE," +
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_OUT" +
                        " where IS_ACTIVE = 1 and WAREHOUSE_ID = '" + req.getWAREHOUSE_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInOut> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceInOut mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInOut item = new EvidenceInOut();
                item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
                item.setEVIDENCE_OUT_DATE(rs.getString("EVIDENCE_OUT_DATE"));
                item.setEVIDENCE_OUT_TIME(rs.getString("EVIDENCE_OUT_TIME"));
                item.setEVIDENCE_OUT_TYPE(rs.getInt("EVIDENCE_OUT_TYPE"));
                item.setEVIDENCE_OUT_NO(rs.getString("EVIDENCE_OUT_NO"));
                item.setEVIDENCE_OUT_NO_DATE(rs.getString("EVIDENCE_OUT_NO_DATE"));
                item.setEVIDENCE_OUT_NO_TIME(rs.getString("EVIDENCE_OUT_NO_TIME"));
                item.setBOOK_NO(rs.getString("BOOK_NO"));
                item.setRECEIPT_NO(rs.getString("RECEIPT_NO"));
                item.setPAY_DATE(rs.getString("PAY_DATE"));
                item.setPAY_TIME(rs.getString("PAY_TIME"));
                item.setAPPROVE_DATE(rs.getString("APPROVE_DATE"));
                item.setAPPROVE_TIME(rs.getString("APPROVE_TIME"));
                item.setRETURN_DATE(rs.getString("RETURN_DATE"));
                item.setREMARK(rs.getString("REMARK"));
                item.setWAREHOUSE_ID(rs.getInt("WAREHOUSE_ID"));
                item.setAPPROVE_NO(rs.getString("APPROVE_NO"));
                item.setEVIDENCE_OUT_CODE(rs.getString("EVIDENCE_OUT_CODE"));
                item.setOFFICE_CODE(rs.getInt("OFFICE_CODE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setEvidenceInOutItem(getEvidenceInOutItem(rs.getInt("EVIDENCE_OUT_ID")));

                return item;
            }
        });

        return dataList;

    }
}
