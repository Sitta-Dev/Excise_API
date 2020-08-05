package com.xcs.phase2.dao.target;


import com.xcs.phase2.model.target.TargetDetail;
import com.xcs.phase2.request.target.TargetDetailgetByConReq;
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
public class TargetDetailDAOImpl extends TargetExt implements TargetDetailDAO {

    private static final Logger log = LoggerFactory.getLogger(TargetDetailDAOImpl.class);

    @Override
    public List<TargetDetail> TargetDetailgetByCon(TargetDetailgetByConReq req) {
        // TODO Auto-generated method stub

        String str = "";

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND SUBSTR(OPS_TARGET.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2)" ;
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND SUBSTR(OPS_TARGET.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4)" ;
                }else {
                    str = " AND OPS_TARGET.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' " ;
                }

            }

        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT DISTINCT" +
                        "  (" +
                        "    SELECT COUNT(OPS_LAWSUIT.LAWSUIT_ID) " +
                        "    FROM OPS_LAWSUIT" +
                        "    WHERE " +
                        "    OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT.IS_LAWSUIT = 1" +
                        "    AND TO_CHAR(LAWSUIT_NO_YEAR,'yyyy') = TO_CHAR(trunc(sysdate, 'YEAR')-1, 'yyyy')" +
                        "  )AS OLD_QTY," +
                        "  (" +
                        "    SELECT SUM(OPS_PAYMENT.FINE) " +
                        "    FROM OPS_LAWSUIT" +
                        "    JOIN OPS_LAWSUIT_DETAIL ON OPS_LAWSUIT.LAWSUIT_ID=OPS_LAWSUIT_DETAIL.LAWSUIT_ID" +
                        "    JOIN OPS_PAYMENT ON OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID=OPS_PAYMENT.LAWSUIT_DETAIL_ID " +
                        "    WHERE " +
                        "    LAWSUIT_NO_YEAR = trunc(sysdate, 'YEAR')-1" +
                        "  )AS SUM_FINE," +
                        "  OPS_TARGET_ITEM.TARGET_ID" +
                        "  FROM OPS_TARGET" +
                        "  LEFT JOIN OPS_TARGET_ITEM ON OPS_TARGET.TARGET_ID=OPS_TARGET_ITEM.TARGET_ID" +
                        "  LEFT JOIN OPS_TARGET_ITEM_DETAIL ON OPS_TARGET_ITEM.ITEM_ID=OPS_TARGET_ITEM_DETAIL.ITEM_ID where 1=1 ");
        sqlBuilder.append(str);

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<TargetDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public TargetDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                TargetDetail item = new TargetDetail();
                item.setTARGET_ID(rs.getInt("TARGET_ID"));
                item.setOLD_QTY(rs.getInt("OLD_QTY"));
                item.setSUM_FINE(rs.getFloat("SUM_FINE"));

                return item;
            }
        });

        return dataList;
    }
}
