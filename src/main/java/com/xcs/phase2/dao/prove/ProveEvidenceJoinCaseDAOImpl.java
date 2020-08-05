package com.xcs.phase2.dao.prove;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.prove.ProveEvidenceJoinCase;
import com.xcs.phase2.request.prove.ProveEvidenceJoinCasegetByConReq;
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
public class ProveEvidenceJoinCaseDAOImpl extends ProveExt implements ProveEvidenceJoinCaseDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveEvidenceJoinCaseDAOImpl.class);


    @Override
    public List<ProveEvidenceJoinCase> ProveEvidenceJoinCasegetByCon(ProveEvidenceJoinCasegetByConReq req) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_ARREST_INDICTMENT.INDICTMENT_ID," +
                        "    OPS_ARREST_PRODUCT_JOIN.INDICTMENT_ID_SUB," +
                        "    OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID," +
                        "    OPS_LAWSUIT.LAWSUIT_NO||'/'||TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as LAWSUIT_NO," +
                        "    OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID," +
                        "    OPS_ARREST_LAWBREAKER.TITLE_NAME_TH||''||OPS_ARREST_LAWBREAKER.FIRST_NAME||' '||OPS_ARREST_LAWBREAKER.LAST_NAME as LAWBREAKER_NAME," +
                        "    OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID," +
                        "    OPS_ARREST_PRODUCT.PRODUCT_DESC," +
                        "    MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID," +
                        "    MAS_LAW_GUILTBASE.GUILTBASE_NAME," +
                        "    OPS_LAWSUIT_STAFF.TITLE_NAME_TH||''||OPS_LAWSUIT_STAFF.FIRST_NAME||' '||OPS_LAWSUIT_STAFF.LAST_NAME as LAWSUIT_STAFF," +
                        "    to_char(OPS_LAWSUIT.LAWSUIT_DATE,'" + Pattern.FORMAT_DATETIME + "') as LAWSUIT_DATE" +
                        "    FROM OPS_ARREST_PRODUCT_JOIN" +
                        "    INNER JOIN OPS_ARREST_INDICTMENT on OPS_ARREST_PRODUCT_JOIN.INDICTMENT_ID_SUB = OPS_ARREST_INDICTMENT.INDICTMENT_ID and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "    INNER JOIN OPS_ARREST_INDICTMENT_DETAIL on OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID and OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
                        "    INNER JOIN OPS_ARREST_LAWBREAKER on OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID and OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
                        "    JOIN OPS_ARREST_INDICTMENT_PRODUCT on OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID and OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE = 1" +
                        "    JOIN OPS_ARREST_PRODUCT on OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID=OPS_ARREST_PRODUCT.PRODUCT_ID and OPS_ARREST_PRODUCT.IS_ACTIVE = 1" +
                        "    JOIN MAS_LAW_GUILTBASE on MAS_LAW_GUILTBASE.GUILTBASE_ID=OPS_ARREST_INDICTMENT.GUILTBASE_ID and MAS_LAW_GUILTBASE.IS_ACTIVE=1" +
                        "    JOIN MAS_LAW_GROUP_SUBSECTION_RULE on MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID=MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID and MAS_LAW_GROUP_SUBSECTION_RULE.IS_ACTIVE=1" +
                        "    JOIN OPS_LAWSUIT on OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID and OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    JOIN OPS_LAWSUIT_STAFF on OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID and OPS_LAWSUIT_STAFF.IS_ACTIVE = 1" +
                        "    WHERE OPS_ARREST_PRODUCT_JOIN.INDICTMENT_ID_MAIN = '"+req.getINDICTMENT_ID()+"' ");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveEvidenceJoinCase> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveEvidenceJoinCase mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveEvidenceJoinCase item = new ProveEvidenceJoinCase();

                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setINDICTMENT_ID_SUB(rs.getInt("INDICTMENT_ID_SUB"));
                item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
                item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWBREAKER_NAME(rs.getString("LAWBREAKER_NAME"));
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setLAWSUIT_STAFF(rs.getString("LAWSUIT_STAFF"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));

                return item;
            }
        });

        return dataList;
    }
}
