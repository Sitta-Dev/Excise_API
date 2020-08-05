package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.evidenceout.EvidenceOutIn;
import com.xcs.phase2.request.evidenceout.EvidenceOutIngetByKeywordReq;
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
public class EvidenceOutInDAOImpl extends EvidenceOutExt implements EvidenceOutInDAO{

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutInDAOImpl.class);

    @Override
    public List<EvidenceOutIn> EvidenceOutIngetByKeyword(EvidenceOutIngetByKeywordReq req) {


        String str = "";

        if("1".equals(req.getEVIDENCE_IN_TYPE())) {
            str = " and OPS_EVIDENCE_IN.EVIDENCE_IN_TYPE = '1' ";
        }else {
            str = " and OPS_EVIDENCE_IN.EVIDENCE_IN_TYPE <> '1' ";
        }


        StringBuilder sqlBuilder = new StringBuilder()
                .append("with temp as( select " +
                        "OPS_EVIDENCE_IN.EVIDENCE_IN_ID," +
                        "OPS_EVIDENCE_IN.PROVE_ID," +
                        "OPS_EVIDENCE_IN.EVIDENCE_IN_CODE," +
                        "to_char(OPS_EVIDENCE_IN.EVIDENCE_IN_DATE,'"+ Pattern.FORMAT_DATETIME+"') as EVIDENCE_IN_DATE, "+
                        "to_char(OPS_EVIDENCE_IN.RETURN_DATE,'"+Pattern.FORMAT_DATETIME+"') as RETURN_DATE, "+
                        "OPS_EVIDENCE_IN.IS_RECEIVE," +
                        "OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "to_char(OPS_EVIDENCE_IN.DELIVERY_DATE,'"+Pattern.FORMAT_DATETIME+"') as DELIVERY_DATE, "+
                        "OPS_EVIDENCE_IN.EVIDENCE_IN_TYPE," +
                        "OPS_EVIDENCE_IN.REMARK," +
                        "OPS_EVIDENCE_IN.IS_ACTIVE," +
                        "OPS_EVIDENCE_IN.IS_EDIT" +
                        " from OPS_EVIDENCE_IN" +
                        " left join OPS_EVIDENCE_IN_ITEM on OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID" +
                        " left join OPS_EVIDENCE_STOCK_BALANCE on OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID = OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID" +
                        " where OPS_EVIDENCE_IN_ITEM.IS_ACTIVE = 1" +
                        " and OPS_EVIDENCE_STOCK_BALANCE.IS_FINISH < 2" +
                        " and OPS_EVIDENCE_STOCK_BALANCE.WAREHOUSE_ID = '"+req.getWAREHOUSE_ID()+"' " + str +
                        " ) select DISTINCT * from temp");



        log.info("[SQL ] : "+sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutIn> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceOutIn mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutIn item = new EvidenceOutIn();
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setEVIDENCE_IN_CODE(rs.getString("EVIDENCE_IN_CODE"));
                item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                item.setRETURN_DATE(rs.getString("RETURN_DATE"));
                item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                item.setEVIDENCE_IN_TYPE(rs.getInt("EVIDENCE_IN_TYPE"));
                item.setREMARK(rs.getString("REMARK"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setIS_EDIT(rs.getInt("IS_EDIT"));

                //item.setEvidenceOutProve(getEvidenceOutProve(rs.getInt("PROVE_ID")));
                item.setEvidenceOutInItem(getEvidenceOutInItem(rs.getInt("EVIDENCE_IN_ID")));
                //item.setEvidenceOutInStaff(getEvidenceOutInStaff(rs.getInt("EVIDENCE_IN_ID")));
                return item;

            }
        });
        return dataList;

    }


}
