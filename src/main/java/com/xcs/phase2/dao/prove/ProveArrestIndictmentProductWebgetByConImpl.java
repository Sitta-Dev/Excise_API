package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.ProveArrestIndictmentProductWeb;
import com.xcs.phase2.request.prove.ProveArrestIndictmentProductWebgetByConReq;
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
public class ProveArrestIndictmentProductWebgetByConImpl extends ProveExt implements ProveArrestIndictmentProductWebDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveArrestIndictmentProductWebgetByConImpl.class);

    @Override
    public List<ProveArrestIndictmentProductWeb> ProveArrestIndictmentProductWebgetByCon(ProveArrestIndictmentProductWebgetByConReq req) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_PROVE_PRODUCT.IS_SCIENCE," +
                        "    OPS_PROVE_PRODUCT.IS_PROVE," +
                        "    OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID," +
                        "    OPS_ARREST_PRODUCT.PRODUCT_DESC," +
                        "    (" +
                        "        SELECT COUNT(OPS_ARREST_PRODUCT_JOIN.INDICTMENT_ID_SUB)" +
                        "        FROM OPS_ARREST_PRODUCT_JOIN" +
                        "        JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID = OPS_ARREST_PRODUCT_JOIN.INDICTMENT_ID_SUB AND OPS_ARREST_PRODUCT_JOIN.IS_ACTIVE=1" +
                        "        WHERE OPS_ARREST_PRODUCT_JOIN.INDICTMENT_ID_SUB= '"+req.getINDICTMENT_ID()+"' " +
                        "    )AS OLD_QTY" +
                        "    FROM OPS_ARREST_INDICTMENT_PRODUCT " +
                        "    JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID and OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE = 1" +
                        "    JOIN OPS_ARREST_PRODUCT on OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID=OPS_ARREST_PRODUCT.PRODUCT_ID and OPS_ARREST_PRODUCT.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_LAWSUIT on OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID and OPS_LAWSUIT.IS_ACTIVE=1" +
                        "    LEFT JOIN OPS_PROVE on OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID and OPS_PROVE.IS_ACTIVE=1" +
                        "    LEFT JOIN OPS_PROVE_PRODUCT on OPS_PROVE.PROVE_ID = OPS_PROVE_PRODUCT.PROVE_ID and OPS_PROVE_PRODUCT.IS_ACTIVE=1" +
                        "    Where OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE=1" +
                        "    AND OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID= '"+req.getINDICTMENT_ID()+"' ");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveArrestIndictmentProductWeb> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveArrestIndictmentProductWeb mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveArrestIndictmentProductWeb item = new ProveArrestIndictmentProductWeb();
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setIS_SCIENCE(rs.getInt("IS_SCIENCE"));
                item.setIS_PROVE(rs.getInt("IS_PROVE"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setOLD_QTY(rs.getInt("OLD_QTY"));

                return item;
            }
        });

        return dataList;
    }
}
