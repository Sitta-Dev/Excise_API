package com.xcs.phase2.dao.deliverystorage;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.deliverystorage.DeliveryStorageList;
import com.xcs.phase2.request.deliverystorage.DeliverstorageListgetByConAdvReq;
import com.xcs.phase2.request.deliverystorage.DeliverstorageListgetByKeywordReq;
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
public class DeliveryStorageListDAOImpl extends DeliveryStorageExt implements DeliveryStorageListDAO {

    private static final Logger log = LoggerFactory.getLogger(DeliveryStorageListDAOImpl.class);

    @Override
    public List<DeliveryStorageList> DeliverstorageListgetByKeyword(DeliverstorageListgetByKeywordReq req) {
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
                .append("with temp as(" +
                        "    SELECT DISTINCT" +
                        "        OPS_ARREST.ARREST_ID," +
                        "        OPS_ARREST.ARREST_CODE," +
                        "        TO_CHAR(OPS_ARREST.OCCURRENCE_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as OCCURRENCE_DATE," +
                        "        TO_CHAR(OPS_LAWSUIT.LAWSUIT_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as LAWSUIT_DATE," +
                        "        OPS_EVIDENCE_IN.EVIDENCE_IN_ID," +
                        "        OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "        TO_CHAR(OPS_EVIDENCE_IN.DELIVERY_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as DELIVERY_DATE," +
                        "        OPS_EVIDENCE_IN.DELIVERY_OFFICE_NAME," +
                        "       DECODE(OPS_EVIDENCE_IN.IS_RECEIVE,1,1,0,0,-1) AS IS_RECEIVE," +
                        "        (SELECT TITLE_SHORT_NAME_TH||''||FIRST_NAME||' '||LAST_NAME  FROM OPS_EVIDENCE_IN_STAFF WHERE CONTRIBUTOR_ID=59 AND IS_ACTIVE = 1 AND EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID) AS DELIVERY_FULL_NAME," +
                        "       DECODE(OPS_EVIDENCE_IN.IS_RECEIVE,1,'นำส่งแล้ว',0,'นำส่งแล้ว','ยังไม่นำส่ง') AS IS_RECEIVE_TEXT," +
                        "        OPS_ARREST.OCCURRENCE_DATE AS OCCURRENCE_DATE_SORT," +
                        "        OPS_LAWSUIT.LAWSUIT_DATE AS LAWSUIT_DATE_SORT," +
                        "        OPS_EVIDENCE_IN.DELIVERY_DATE AS DELIVERY_DATE_SORT," +
                        "        MAS_LAW_GUILTBASE.IS_PROVE,OPS_PROVE.PROVE_ID, " +
                        "        CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO " +
                        "    FROM OPS_ARREST" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID = OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "    LEFT JOIN OPS_ARREST_PRODUCT ON OPS_ARREST.ARREST_ID = OPS_ARREST_PRODUCT.ARREST_ID" +
                        "    LEFT JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
                        "    LEFT JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID AND OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID" +
                        "    LEFT JOIN OPS_EVIDENCE_IN ON OPS_ARREST.ARREST_ID = OPS_EVIDENCE_IN.ARREST_ID AND OPS_EVIDENCE_IN.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_EVIDENCE_IN_STAFF ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID" +
                        "    AND OPS_EVIDENCE_IN_STAFF.IS_ACTIVE = 1" +
                        "    AND OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID = 59" +
                        "    WHERE OPS_ARREST.IS_ACTIVE = 1" +
                        "    AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT.IS_LAWSUIT = 1 " +
                        "    AND OPS_ARREST_PRODUCT.PRODUCT_ID is not null" +
                        //"    AND OPS_EVIDENCE_IN.IS_RECEIVE <> 1" +
                        "    AND (" +
                        "            LOWER(OPS_ARREST.ARREST_CODE) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "            OR LOWER(OPS_EVIDENCE_IN.DELIVERY_NO) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "            OR LOWER(OPS_EVIDENCE_IN.DELIVERY_OFFICE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "            OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_NAME_TH||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "            OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_NAME_EN||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "            OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_TH||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "            OR LOWER(OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_EN||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||OPS_EVIDENCE_IN_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    )" + str +
                        ") " +
                        "select " +
                        "    ARREST_ID," +
                        "    ARREST_CODE," +
                        "    OCCURRENCE_DATE," +
                        "    LAWSUIT_DATE," +
                        "    EVIDENCE_IN_ID," +
                        "    DELIVERY_NO," +
                        "    DELIVERY_DATE," +
                        "    DELIVERY_OFFICE_NAME," +
                        "    IS_RECEIVE," +
                        "    DELIVERY_FULL_NAME," +
                        "    IS_RECEIVE_TEXT," +
                        "    OCCURRENCE_DATE_SORT," +
                        "    LAWSUIT_DATE_SORT," +
                        "    DELIVERY_DATE_SORT," +
                        "    IS_PROVE," +
                        "    PROVE_ID," +
                        "    LISTAGG(LAWSUIT_NO,',') WITHIN GROUP(" +
                        "    ORDER BY" +
                        "        ARREST_ID," +
                        "        ARREST_CODE," +
                        "        OCCURRENCE_DATE," +
                        "        LAWSUIT_DATE," +
                        "        EVIDENCE_IN_ID," +
                        "        DELIVERY_NO," +
                        "        DELIVERY_DATE," +
                        "        DELIVERY_OFFICE_NAME," +
                        "        IS_RECEIVE," +
                        "        DELIVERY_FULL_NAME," +
                        "        IS_RECEIVE_TEXT," +
                        "        OCCURRENCE_DATE_SORT," +
                        "        LAWSUIT_DATE_SORT," +
                        "        DELIVERY_DATE_SORT," +
                        "        IS_PROVE," +
                        "        PROVE_ID" +
                        "    ) AS LAWSUIT_NO " +
                        "   FROM TEMP " +
                        "   GROUP BY " +
                        "    ARREST_ID," +
                        "    ARREST_CODE," +
                        "    OCCURRENCE_DATE," +
                        "    LAWSUIT_DATE," +
                        "    EVIDENCE_IN_ID," +
                        "    DELIVERY_NO," +
                        "    DELIVERY_DATE," +
                        "    DELIVERY_OFFICE_NAME," +
                        "    IS_RECEIVE," +
                        "    DELIVERY_FULL_NAME," +
                        "    IS_RECEIVE_TEXT, " +
                        "    OCCURRENCE_DATE_SORT, " +
                        "    LAWSUIT_DATE_SORT, " +
                        "    DELIVERY_DATE_SORT," +
                        "    IS_PROVE," +
                        "    PROVE_ID " +
//                        "order by to_date(occurrence_date,'dd MON yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') desc");
                		"order by OCCURRENCE_DATE_SORT desc");

        log.info("[SQL]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<DeliveryStorageList> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public DeliveryStorageList mapRow(ResultSet rs, int rowNum) throws SQLException {

                //System.out.println("IS_PROVE "+rs.getInt("IS_PROVE")+"  " +"PROVE_ID "+rs.getInt("PROVE_ID"));

                if(rs.getInt("IS_PROVE") == 1 && rs.getInt("PROVE_ID") == 0){
                    return null;
                }

                DeliveryStorageList item = new DeliveryStorageList();
                item.setARREST_ID(rs.getString("ARREST_ID"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                item.setDELIVERY_FULL_NAME(rs.getString("DELIVERY_FULL_NAME"));
                item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));
                item.setIS_RECEIVE_TEXT(rs.getString("IS_RECEIVE_TEXT"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));

                item.setOCCURRENCE_DATE_SORT(rs.getString("OCCURRENCE_DATE_SORT"));
                item.setLAWSUIT_DATE_SORT(rs.getString("LAWSUIT_DATE_SORT"));
                item.setDELIVERY_DATE_SORT(rs.getString("DELIVERY_DATE_SORT"));

                return item;
            }
        });
        while (dataList.remove(null)) {}
        return dataList;
    }

    @Override
    public List<DeliveryStorageList> DeliverstorageListgetByConAdv(DeliverstorageListgetByConAdvReq req) {
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

        String tempOCCURRENCE_DATE_START   = "";
        String tempOCCURRENCE_DATE_END = "";
        String tempLAWSUIT_DATE_START   = "";
        String tempLAWSUIT_DATE_END = "";
        String tempDELIVERY_DATE_START   = "";
        String tempDELIVERY_DATE_END = "";


        if(!"".equals(req.getOCCURRENCE_DATE_START())) {
            tempOCCURRENCE_DATE_START = String.format("%s %s", req.getOCCURRENCE_DATE_START(), Pattern.TIME_FROM);
        }

        if(!"".equals(req.getOCCURRENCE_DATE_END())) {
            tempOCCURRENCE_DATE_END = String.format("%s %s", req.getOCCURRENCE_DATE_END(),Pattern.TIME_TO);
        }


        if(!"".equals(req.getLAWSUIT_DATE_START())) {
            tempLAWSUIT_DATE_START = String.format("%s %s", req.getLAWSUIT_DATE_START(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getLAWSUIT_DATE_END())) {
            tempLAWSUIT_DATE_END = String.format("%s %s", req.getLAWSUIT_DATE_END(),Pattern.TIME_TO);
        }

        if(!"".equals(req.getDELIVERY_DATE_START())) {
            tempDELIVERY_DATE_START = String.format("%s %s", req.getDELIVERY_DATE_START(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getDELIVERY_DATE_END())) {
            tempDELIVERY_DATE_END = String.format("%s %s", req.getDELIVERY_DATE_END(),Pattern.TIME_TO);
        }

        StringBuilder sqlBuilder = new StringBuilder();
        StringBuilder sqlBuilder1 = new StringBuilder();

        if(req.getARREST_CODE() != null && !"".equals(req.getARREST_CODE())) {
            sqlBuilder.append(" AND LOWER(OPS_ARREST.ARREST_CODE) LIKE LOWER(REPLACE('%"+req.getARREST_CODE()+"%',' ','')) ");
        }

        if(req.getOCCURRENCE_DATE_START() != null && !"".equals(req.getOCCURRENCE_DATE_START()) && req.getOCCURRENCE_DATE_END() != null && !"".equals(req.getOCCURRENCE_DATE_END())) {
            sqlBuilder.append(" AND OPS_ARREST.OCCURRENCE_DATE BETWEEN  to_date(nvl('"+tempOCCURRENCE_DATE_START+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempOCCURRENCE_DATE_END+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getLAWSUIT_NO()!= null && !"".equals(req.getLAWSUIT_NO())) {
            sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_NO = '"+req.getLAWSUIT_NO()+"' ");
        }

        if(req.getLAWSUIT_NO_YEAR()!= null && !"".equals(req.getLAWSUIT_NO_YEAR())) {
            sqlBuilder.append(" AND (to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getLAWSUIT_NO_YEAR()+"' AND to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getLAWSUIT_NO_YEAR()+"') ");
        }

        if(req.getLAWSUIT_DATE_START() != null && !"".equals(req.getLAWSUIT_DATE_START()) && req.getLAWSUIT_DATE_END() != null && !"".equals(req.getLAWSUIT_DATE_END())) {
            sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_DATE BETWEEN  to_date(nvl('"+tempLAWSUIT_DATE_START+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempLAWSUIT_DATE_END+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
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

        if(req.getIS_RECEIVE() != null && !"".equals(req.getIS_RECEIVE())) {
            if("0".equals(req.getIS_RECEIVE())){
                sqlBuilder1.append("  AND temp.IS_RECEIVE in (0,1) ");
            }else{
                sqlBuilder1.append("  AND temp.IS_RECEIVE = '"+req.getIS_RECEIVE()+"' ");
            }


        }

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("with temp as(" +
                        "    SELECT DISTINCT" +
                        "        OPS_ARREST.ARREST_ID," +
                        "        OPS_ARREST.ARREST_CODE," +
                        "        TO_CHAR(OPS_ARREST.OCCURRENCE_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as OCCURRENCE_DATE," +
                        "        TO_CHAR(OPS_LAWSUIT.LAWSUIT_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as LAWSUIT_DATE," +
                        "        OPS_EVIDENCE_IN.EVIDENCE_IN_ID," +
                        "        OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "        TO_CHAR(OPS_EVIDENCE_IN.DELIVERY_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as DELIVERY_DATE," +
                        "        OPS_EVIDENCE_IN.DELIVERY_OFFICE_NAME," +
                        "        DECODE(OPS_EVIDENCE_IN.IS_RECEIVE,1,1,0,0,-1) AS IS_RECEIVE," +
                        "        (SELECT TITLE_SHORT_NAME_TH||''||FIRST_NAME||' '||LAST_NAME  FROM OPS_EVIDENCE_IN_STAFF WHERE CONTRIBUTOR_ID=59 AND IS_ACTIVE = 1 AND EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID) AS DELIVERY_FULL_NAME," +
                        "        DECODE(OPS_EVIDENCE_IN.IS_RECEIVE,1,'นำส่งแล้ว',0,'นำส่งแล้ว','ยังไม่นำส่ง') AS IS_RECEIVE_TEXT," +
                        "        OPS_ARREST.OCCURRENCE_DATE AS OCCURRENCE_DATE_SORT," +
                        "        OPS_LAWSUIT.LAWSUIT_DATE AS LAWSUIT_DATE_SORT," +
                        "        OPS_EVIDENCE_IN.DELIVERY_DATE AS DELIVERY_DATE_SORT," +
                        "        MAS_LAW_GUILTBASE.IS_PROVE,OPS_PROVE.PROVE_ID, " +
                        "        CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO" +
                        "    FROM OPS_ARREST" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID = OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "    LEFT JOIN OPS_ARREST_PRODUCT ON OPS_ARREST.ARREST_ID = OPS_ARREST_PRODUCT.ARREST_ID " +
                        "    LEFT JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
                        "    LEFT JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID AND OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID" +
                        "    LEFT JOIN OPS_EVIDENCE_IN ON OPS_ARREST.ARREST_ID = OPS_EVIDENCE_IN.ARREST_ID AND OPS_EVIDENCE_IN.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_EVIDENCE_IN_STAFF ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID" +
                        "    AND OPS_EVIDENCE_IN_STAFF.IS_ACTIVE = 1" +
                        "    AND OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID = 59" +
                        "    WHERE OPS_ARREST.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT.IS_LAWSUIT = 1 " +
                        "    AND OPS_ARREST_PRODUCT.PRODUCT_ID is not null" +
                        //"    AND OPS_EVIDENCE_IN.IS_RECEIVE <> 1" +
                        "    AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" + str + sqlBuilder.toString()+
                        ") " +
                        "select " +
                        "    ARREST_ID," +
                        "    ARREST_CODE," +
                        "    OCCURRENCE_DATE," +
                        "    LAWSUIT_DATE," +
                        "    EVIDENCE_IN_ID," +
                        "    DELIVERY_NO," +
                        "    DELIVERY_DATE," +
                        "    DELIVERY_OFFICE_NAME," +
                        "    IS_RECEIVE," +
                        "    DELIVERY_FULL_NAME," +
                        "    IS_RECEIVE_TEXT," +
                        "    OCCURRENCE_DATE_SORT," +
                        "    LAWSUIT_DATE_SORT," +
                        "    DELIVERY_DATE_SORT," +
                        "    IS_PROVE," +
                        "    PROVE_ID," +
                        "    LISTAGG(LAWSUIT_NO,',') WITHIN GROUP(" +
                        "    ORDER BY" +
                        "        ARREST_ID," +
                        "        ARREST_CODE," +
                        "        OCCURRENCE_DATE," +
                        "        LAWSUIT_DATE," +
                        "        EVIDENCE_IN_ID," +
                        "        DELIVERY_NO," +
                        "        DELIVERY_DATE," +
                        "        DELIVERY_OFFICE_NAME," +
                        "        IS_RECEIVE," +
                        "        DELIVERY_FULL_NAME," +
                        "        IS_RECEIVE_TEXT," +
                        "        OCCURRENCE_DATE_SORT," +
                        "        LAWSUIT_DATE_SORT," +
                        "        DELIVERY_DATE_SORT," +
                        "        IS_PROVE," +
                        "        PROVE_ID" +
                        "    ) AS LAWSUIT_NO " +
                        "from temp where 1=1 " +sqlBuilder1.toString()+
                        "GROUP BY " +
                        "    ARREST_ID," +
                        "    ARREST_CODE," +
                        "    OCCURRENCE_DATE," +
                        "    LAWSUIT_DATE," +
                        "    EVIDENCE_IN_ID," +
                        "    DELIVERY_NO," +
                        "    DELIVERY_DATE," +
                        "    DELIVERY_OFFICE_NAME," +
                        "    IS_RECEIVE," +
                        "    DELIVERY_FULL_NAME," +
                        "    IS_RECEIVE_TEXT, " +
                        "    OCCURRENCE_DATE_SORT, " +
                        "    LAWSUIT_DATE_SORT, " +
                        "    DELIVERY_DATE_SORT," +
                        "    IS_PROVE," +
                        "    PROVE_ID " +
//                        "order by to_date(occurrence_date,'dd MON yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') desc");
                		"order by OCCURRENCE_DATE_SORT desc");

        log.info("[SQL]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<DeliveryStorageList> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public DeliveryStorageList mapRow(ResultSet rs, int rowNum) throws SQLException {


                if(rs.getInt("IS_PROVE") == 1 && rs.getInt("PROVE_ID") == 0){
                    return null;
                }

                DeliveryStorageList item = new DeliveryStorageList();
                item.setARREST_ID(rs.getString("ARREST_ID"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                item.setDELIVERY_FULL_NAME(rs.getString("DELIVERY_FULL_NAME"));
                item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));
                item.setIS_RECEIVE_TEXT(rs.getString("IS_RECEIVE_TEXT"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));

                item.setOCCURRENCE_DATE_SORT(rs.getString("OCCURRENCE_DATE_SORT"));
                item.setLAWSUIT_DATE_SORT(rs.getString("LAWSUIT_DATE_SORT"));
                item.setDELIVERY_DATE_SORT(rs.getString("DELIVERY_DATE_SORT"));

                return item;
            }
        });
        while (dataList.remove(null)) {}
        return dataList;
    }

}
