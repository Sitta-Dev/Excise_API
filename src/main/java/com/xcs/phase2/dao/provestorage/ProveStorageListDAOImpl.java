package com.xcs.phase2.dao.provestorage;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.provestorage.ProveStorageList;
import com.xcs.phase2.request.provestorage.ProvestorageListgetByConAdvReq;
import com.xcs.phase2.request.provestorage.ProvestorageListgetByKeywordReq;
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
public class ProveStorageListDAOImpl extends ProveStorageExt implements ProveStorageListDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveStorageListDAOImpl.class);

    @Override
    public List<ProveStorageList> ProvestorageListgetByKeyword(ProvestorageListgetByKeywordReq req) {
        // TODO Auto-generated method stub

        String str = "";

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND" +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            ") " ;
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND " +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            " )";
                }else {
                    str = " AND" +
                            " (" +
                            "  OPS_LAWSUIT.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_EVIDENCE_IN_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            " )";
                }
            }
        }

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("    with temp as(" +
                        "        SELECT DISTINCT" +
                        "            OPS_ARREST.ARREST_ID," +
                        "            OPS_ARREST.ARREST_CODE," +
                        "            OPS_ARREST.ARREST_DATE," +
                        "            OPS_EVIDENCE_IN.EVIDENCE_IN_ID," +
                        "            CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO," +
                        "            OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "            TO_CHAR(OPS_EVIDENCE_IN.DELIVERY_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as DELIVERY_DATE," +
                        "            OPS_EVIDENCE_IN.DELIVERY_OFFICE_NAME," +
                        "            (SELECT TITLE_NAME_TH||''||FIRST_NAME||' '||LAST_NAME  FROM OPS_EVIDENCE_IN_STAFF WHERE CONTRIBUTOR_ID=59 AND IS_ACTIVE = 1 AND EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID) AS DELIVERY_FULL_NAME," +
                        "            DECODE(OPS_EVIDENCE_IN.IS_RECEIVE,1,1,0,0,0) AS IS_RECEIVE,        " +
                        "            DECODE(OPS_EVIDENCE_IN.IS_RECEIVE,1,'ตรวจรับแล้ว',0,'ยังไม่ตรวจรับ','ยังไม่ตรวจรับ') AS IS_RECEIVE_TEXT," +
                        "            OPS_EVIDENCE_IN.EVIDENCE_IN_CODE," +
                        "            TO_CHAR(OPS_EVIDENCE_IN.EVIDENCE_IN_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as EVIDENCE_IN_DATE," +
                        "            OPS_EVIDENCE_IN.RECEIVE_OFFICE_NAME," +
                        "            (SELECT LISTAGG(TITLE_NAME_TH||''||FIRST_NAME||' '||LAST_NAME, ', ')  WITHIN GROUP (ORDER BY TITLE_NAME_TH||''||FIRST_NAME||' '||LAST_NAME) FROM OPS_EVIDENCE_IN_STAFF WHERE CONTRIBUTOR_ID <> 59  AND EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID AND IS_ACTIVE = 1) AS PROVE_FULL_NAME" +
                        "        FROM OPS_ARREST" +
                        "        INNER JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID = OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "        INNER JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID AND OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "        INNER JOIN OPS_EVIDENCE_IN ON OPS_ARREST.ARREST_ID = OPS_EVIDENCE_IN.ARREST_ID AND OPS_EVIDENCE_IN.IS_ACTIVE = 1" +
                        "        LEFT JOIN OPS_EVIDENCE_IN_STAFF ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID" +
                        "        AND OPS_EVIDENCE_IN_STAFF.IS_ACTIVE = 1" +
                        "        AND OPS_EVIDENCE_IN.IS_RECEIVE in (0,1)" +
                        "        WHERE OPS_ARREST.IS_ACTIVE = 1" +
                        "        AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "        AND (" +
                        "                LOWER(OPS_ARREST.ARREST_CODE) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "                OR LOWER(OPS_EVIDENCE_IN.DELIVERY_NO) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "                OR LOWER(OPS_EVIDENCE_IN.DELIVERY_OFFICE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "                OR LOWER(OPS_EVIDENCE_IN.EVIDENCE_IN_CODE) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "                OR LOWER(OPS_EVIDENCE_IN.RECEIVE_OFFICE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "                OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_NAME_TH||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "                OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_NAME_EN||OPS_EVIDENCE_IN_STAFF.TITLE_NAME_EN||OPS_EVIDENCE_IN_STAFF.TITLE_NAME_EN) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "                OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_TH||OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_TH||OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_TH) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "                OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_EN||OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_EN||OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_EN) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "       )" + str +
                        "    ) " +
                        "    select " +
                        "        ARREST_ID," +
                        "        ARREST_CODE," +
                        "        ARREST_DATE," +
                        "        EVIDENCE_IN_ID," +
                        "        DELIVERY_NO," +
                        "        DELIVERY_DATE," +
                        "        DELIVERY_OFFICE_NAME," +
                        "        IS_RECEIVE," +
                        "        DELIVERY_FULL_NAME," +
                        "        IS_RECEIVE_TEXT," +
                        "        EVIDENCE_IN_CODE," +
                        "        EVIDENCE_IN_DATE," +
                        "        RECEIVE_OFFICE_NAME," +
                        "        PROVE_FULL_NAME," +
                        "        LISTAGG(LAWSUIT_NO,',') WITHIN GROUP(" +
                        "        ORDER BY" +
                        "            ARREST_ID," +
                        "            ARREST_CODE," +
                        "            ARREST_DATE," +
                        "            EVIDENCE_IN_ID," +
                        "            DELIVERY_NO," +
                        "            DELIVERY_DATE," +
                        "            DELIVERY_OFFICE_NAME," +
                        "            IS_RECEIVE," +
                        "            DELIVERY_FULL_NAME," +
                        "            IS_RECEIVE_TEXT," +
                        "            EVIDENCE_IN_CODE," +
                        "            EVIDENCE_IN_DATE," +
                        "            RECEIVE_OFFICE_NAME," +
                        "            PROVE_FULL_NAME" +
                        "        ) AS LAWSUIT_NO " +
                        "    from temp " +
                        "    GROUP BY " +
                        "        ARREST_ID," +
                        "        ARREST_CODE," +
                        "        ARREST_DATE," +
                        "        EVIDENCE_IN_ID," +
                        "        DELIVERY_NO," +
                        "        DELIVERY_DATE," +
                        "        DELIVERY_OFFICE_NAME," +
                        "        IS_RECEIVE," +
                        "        DELIVERY_FULL_NAME," +
                        "        IS_RECEIVE_TEXT," +
                        "        EVIDENCE_IN_CODE," +
                        "        EVIDENCE_IN_DATE," +
                        "        RECEIVE_OFFICE_NAME," +
                        "        PROVE_FULL_NAME" +
                        "    ORDER BY ARREST_DATE DESC");

        log.info("[SQL]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<ProveStorageList> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public ProveStorageList mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveStorageList item = new ProveStorageList();
                item.setARREST_ID(rs.getString("ARREST_ID"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setARREST_DATE(rs.getString("ARREST_DATE"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                item.setDELIVERY_FULL_NAME(rs.getString("DELIVERY_FULL_NAME"));
                item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                item.setEVIDENCE_IN_CODE(rs.getString("EVIDENCE_IN_CODE"));
                item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                item.setPROVE_FULL_NAME(rs.getString("PROVE_FULL_NAME"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));
                item.setIS_RECEIVE_TEXT(rs.getString("IS_RECEIVE_TEXT"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<ProveStorageList>
    ProvestorageListgetByConAdv(ProvestorageListgetByConAdvReq req) {
        // TODO Auto-generated method stub

        String str = "";

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND" +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            ") " ;
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND " +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            " )";
                }else {
                    str = " AND" +
                            " (" +
                            "  OPS_LAWSUIT.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_EVIDENCE_IN_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            " )";
                }
            }
        }

        String tempEVIDENCE_IN_DATE_START   = "";
        String tempEVIDENCE_IN_DATE_END = "";
        String tempDELIVERY_DATE_START   = "";
        String tempDELIVERY_DATE_END = "";


        if(!"".equals(req.getEVIDENCE_IN_DATE_START())) {
            tempEVIDENCE_IN_DATE_START = String.format("%s %s", req.getEVIDENCE_IN_DATE_START(), Pattern.TIME_FROM);
        }

        if(!"".equals(req.getEVIDENCE_IN_DATE_END())) {
            tempEVIDENCE_IN_DATE_END = String.format("%s %s", req.getEVIDENCE_IN_DATE_END(),Pattern.TIME_TO);
        }

        if(!"".equals(req.getDELIVERY_DATE_START())) {
            tempDELIVERY_DATE_START = String.format("%s %s", req.getDELIVERY_DATE_START(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getDELIVERY_DATE_END())) {
            tempDELIVERY_DATE_END = String.format("%s %s", req.getDELIVERY_DATE_END(),Pattern.TIME_TO);
        }

        StringBuilder sqlBuilder = new StringBuilder();

        if(req.getARREST_CODE() != null && !"".equals(req.getARREST_CODE())) {
            sqlBuilder.append(" AND LOWER(OPS_ARREST.ARREST_CODE) LIKE LOWER(REPLACE('%"+req.getARREST_CODE()+"%',' ','')) ");
        }

        if(req.getEVIDENCE_IN_DATE_START() != null && !"".equals(req.getEVIDENCE_IN_DATE_START()) && req.getEVIDENCE_IN_DATE_END() != null && !"".equals(req.getEVIDENCE_IN_DATE_END())) {
            sqlBuilder.append(" AND OPS_EVIDENCE_IN.EVIDENCE_IN_DATE  BETWEEN to_date(nvl('"+tempEVIDENCE_IN_DATE_START+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempEVIDENCE_IN_DATE_END+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getLAWSUIT_NO()!= null && !"".equals(req.getLAWSUIT_NO())) {
            sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_NO = '"+req.getLAWSUIT_NO()+"' ");
        }

        if(req.getLAWSUIT_NO_YEAR()!= null && !"".equals(req.getLAWSUIT_NO_YEAR())) {
            sqlBuilder.append(" AND (to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getLAWSUIT_NO_YEAR()+"' AND to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getLAWSUIT_NO_YEAR()+"') ");
        }

        if(req.getDELIVERY_NO() != null && !"".equals(req.getDELIVERY_NO())) {
            sqlBuilder.append("  AND LOWER(OPS_EVIDENCE_IN.DELIVERY_NO) LIKE LOWER('%"+req.getDELIVERY_NO()+"%')  ");
        }

        if(req.getDELIVERY_DATE_START() != null && !"".equals(req.getDELIVERY_DATE_START()) && req.getDELIVERY_DATE_END() != null && !"".equals(req.getDELIVERY_DATE_END())) {
            sqlBuilder.append(" AND OPS_EVIDENCE_IN.DELIVERY_DATE BETWEEN  to_date(nvl('"+tempDELIVERY_DATE_START+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempDELIVERY_DATE_END+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getDELIVERY_OFFICE_NAME() != null && !"".equals(req.getDELIVERY_OFFICE_NAME())) {
            sqlBuilder.append(" AND LOWER(OPS_EVIDENCE_IN.DELIVERY_OFFICE_NAME) LIKE LOWER('%"+req.getDELIVERY_OFFICE_NAME()+"%')  ");
        }

        if(req.getDELIVERY_NAME() != null && !"".equals(req.getDELIVERY_NAME())) {
            sqlBuilder.append(" AND " +
                    " (" +
                    "  LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_NAME_TH||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getDELIVERY_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_NAME_EN||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getDELIVERY_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_TH||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getDELIVERY_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_EN||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getDELIVERY_NAME()+"%',' ',''))" +
                    " )");
        }

        if(req.getEVIDENCE_IN_CODE() != null && !"".equals(req.getEVIDENCE_IN_CODE())) {
            sqlBuilder.append("  AND LOWER(OPS_EVIDENCE_IN.EVIDENCE_IN_CODE) LIKE LOWER('%"+req.getEVIDENCE_IN_CODE()+"%')  ");
        }

        if(req.getPROVE_NAME() != null && !"".equals(req.getPROVE_NAME())) {
            sqlBuilder.append(" AND " +
                    " (" +
                    "  LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_NAME_TH||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_NAME_EN||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_TH||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_EN||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ',''))" +
                    " )");
        }

        if(req.getRECEIVE_OFFICE_NAME() != null && !"".equals(req.getRECEIVE_OFFICE_NAME())) {
            sqlBuilder.append(" AND LOWER(OPS_EVIDENCE_IN.RECEIVE_OFFICE_NAME) LIKE LOWER('%"+req.getRECEIVE_OFFICE_NAME()+"%')  ");
        }


        if(req.getIS_RECEIVE() != null && !"".equals(req.getIS_RECEIVE())) {
            sqlBuilder.append(" AND OPS_EVIDENCE_IN.IS_RECEIVE  = "+req.getIS_RECEIVE()+"  ");
        }

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("    with temp as(" +
                        "        SELECT DISTINCT" +
                        "            OPS_ARREST.ARREST_ID," +
                        "            OPS_ARREST.ARREST_CODE," +
                        "            OPS_ARREST.ARREST_DATE," +
                        "            OPS_EVIDENCE_IN.EVIDENCE_IN_ID," +
                        "            CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO," +
                        "            OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "            TO_CHAR(OPS_EVIDENCE_IN.DELIVERY_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as DELIVERY_DATE," +
                        "            OPS_EVIDENCE_IN.DELIVERY_OFFICE_NAME," +
                        "            (SELECT TITLE_NAME_TH||''||FIRST_NAME||' '||LAST_NAME  FROM OPS_EVIDENCE_IN_STAFF WHERE CONTRIBUTOR_ID=59 AND IS_ACTIVE = 1 AND EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID) AS DELIVERY_FULL_NAME," +
                        "            DECODE(OPS_EVIDENCE_IN.IS_RECEIVE,1,1,0,0,0) AS IS_RECEIVE,        " +
                        "            DECODE(OPS_EVIDENCE_IN.IS_RECEIVE,1,'ตรวจรับแล้ว',0,'ยังไม่ตรวจรับ','ยังไม่ตรวจรับ') AS IS_RECEIVE_TEXT," +
                        "            OPS_EVIDENCE_IN.EVIDENCE_IN_CODE," +
                        "            TO_CHAR(OPS_EVIDENCE_IN.EVIDENCE_IN_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as EVIDENCE_IN_DATE," +
                        "            OPS_EVIDENCE_IN.RECEIVE_OFFICE_NAME," +
                        "            (SELECT LISTAGG(TITLE_NAME_TH||''||FIRST_NAME||' '||LAST_NAME, ', ')  WITHIN GROUP (ORDER BY TITLE_NAME_TH||''||FIRST_NAME||' '||LAST_NAME) FROM OPS_EVIDENCE_IN_STAFF WHERE CONTRIBUTOR_ID <> 59  AND EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID AND IS_ACTIVE = 1) AS PROVE_FULL_NAME " +
                        "        FROM OPS_ARREST" +
                        "        INNER JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID = OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "        INNER JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID AND OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "        INNER JOIN OPS_EVIDENCE_IN ON OPS_ARREST.ARREST_ID = OPS_EVIDENCE_IN.ARREST_ID AND OPS_EVIDENCE_IN.IS_ACTIVE = 1" +
                        "        LEFT JOIN OPS_EVIDENCE_IN_STAFF ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID" +
                        "        AND OPS_EVIDENCE_IN_STAFF.IS_ACTIVE = 1" +
                        "        AND OPS_EVIDENCE_IN.IS_RECEIVE in (0,1)" +
                        "        WHERE OPS_ARREST.IS_ACTIVE = 1" +
                        "        AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" + str + sqlBuilder.toString()+
                        "    ) " +
                        "    select " +
                        "        ARREST_ID," +
                        "        ARREST_CODE," +
                        "        ARREST_DATE," +
                        "        EVIDENCE_IN_ID," +
                        "        DELIVERY_NO," +
                        "        DELIVERY_DATE," +
                        "        DELIVERY_OFFICE_NAME," +
                        "        IS_RECEIVE," +
                        "        DELIVERY_FULL_NAME," +
                        "        IS_RECEIVE_TEXT," +
                        "        EVIDENCE_IN_CODE," +
                        "        EVIDENCE_IN_DATE," +
                        "        RECEIVE_OFFICE_NAME," +
                        "        PROVE_FULL_NAME," +
                        "        LISTAGG(LAWSUIT_NO,',') WITHIN GROUP(" +
                        "        ORDER BY" +
                        "            ARREST_ID," +
                        "            ARREST_CODE," +
                        "            ARREST_DATE," +
                        "            EVIDENCE_IN_ID," +
                        "            DELIVERY_NO," +
                        "            DELIVERY_DATE," +
                        "            DELIVERY_OFFICE_NAME," +
                        "            IS_RECEIVE," +
                        "            DELIVERY_FULL_NAME," +
                        "            IS_RECEIVE_TEXT," +
                        "            EVIDENCE_IN_CODE," +
                        "            EVIDENCE_IN_DATE," +
                        "            RECEIVE_OFFICE_NAME," +
                        "            PROVE_FULL_NAME" +
                        "        ) AS LAWSUIT_NO " +
                        "    from temp " +
                        "    GROUP BY " +
                        "        ARREST_ID," +
                        "        ARREST_CODE," +
                        "        ARREST_DATE," +
                        "        EVIDENCE_IN_ID," +
                        "        DELIVERY_NO," +
                        "        DELIVERY_DATE," +
                        "        DELIVERY_OFFICE_NAME," +
                        "        IS_RECEIVE," +
                        "        DELIVERY_FULL_NAME," +
                        "        IS_RECEIVE_TEXT," +
                        "        EVIDENCE_IN_CODE," +
                        "        EVIDENCE_IN_DATE," +
                        "        RECEIVE_OFFICE_NAME," +
                        "        PROVE_FULL_NAME" +
                        "    ORDER BY ARREST_DATE DESC");

        log.info("[SQL]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<ProveStorageList> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public ProveStorageList mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveStorageList item = new ProveStorageList();
                item.setARREST_ID(rs.getString("ARREST_ID"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setARREST_DATE(rs.getString("ARREST_DATE"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                item.setDELIVERY_FULL_NAME(rs.getString("DELIVERY_FULL_NAME"));
                item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                item.setEVIDENCE_IN_CODE(rs.getString("EVIDENCE_IN_CODE"));
                item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                item.setPROVE_FULL_NAME(rs.getString("PROVE_FULL_NAME"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));
                item.setIS_RECEIVE_TEXT(rs.getString("IS_RECEIVE_TEXT"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));

                return item;
            }
        });

        return dataList;
    }

}
