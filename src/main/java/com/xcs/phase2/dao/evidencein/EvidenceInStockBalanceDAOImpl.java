package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.evidencein.EvidenceInStockBalance;
import com.xcs.phase2.model.evidencein.EvidenceInventoryList;
import com.xcs.phase2.model.evidenceout.EvidenceOutStockBalanceByLawsuitNo;
import com.xcs.phase2.request.evidencein.EvidenceInventoryListgetByLawsuitNoReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutStockBalancegetByEvidenceOutIdReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutStockBalancegetByLawsuitNoReq;
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
public class EvidenceInStockBalanceDAOImpl extends EvidenceInExt implements EvidenceInStockBalanceDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInStockBalanceDAOImpl.class);

    @Override
    public Boolean EvidenceInStockBalanceupdByCon(List<EvidenceInStockBalance> req) {

        if (req != null) {

            if (req.size() > 0) {

                for (EvidenceInStockBalance item : req) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_STOCK_BALANCE SET "
                                    + "WAREHOUSE_ID=  '" + item.getWAREHOUSE_ID() + "', "
                                    + "EVIDENCE_IN_ITEM_ID=  '" + item.getEVIDENCE_IN_ITEM_ID() + "', "
                                    + "RECEIVE_QTY=  '" + item.getRECEIVE_QTY() + "', "
                                    + "RECEIVE_QTY_UNIT=  '" + item.getRECEIVE_QTY_UNIT() + "', "
                                    + "RECEIVE_SIZE=  '" + item.getRECEIVE_SIZE() + "', "
                                    + "RECEIVE_SIZE_UNIT=  '" + item.getRECEIVE_SIZE_UNIT() + "', "
                                    + "RECEIVE_NET_VOLUMN=  '" + item.getRECEIVE_NET_VOLUMN() + "', "
                                    + "RECEIVE_NET_VOLUMN_UNIT=  '" + item.getRECEIVE_NET_VOLUMN_UNIT() + "', "
                                    + "BALANCE_QTY=  '" + item.getBALANCE_QTY() + "', "
                                    + "BALANCE_QTY_UNIT=  '" + item.getBALANCE_QTY_UNIT() + "', "
                                    + "BALANCE_SIZE=  '" + item.getBALANCE_SIZE() + "', "
                                    + "BALANCE_SIZE_UNIT=  '" + item.getBALANCE_SIZE_UNIT() + "', "
                                    + "BALANCE_NET_VOLUMN=  '" + item.getBALANCE_NET_VOLUMN() + "', "
                                    + "BALANCE_NET_VOLUMN_UNIT=  '" + item.getBALANCE_NET_VOLUMN_UNIT() + "', "
                                    + "IS_FINISH=  '" + item.getIS_FINISH() + "', "
                                    + "IS_RECEIVE=  '" + item.getIS_RECEIVE() + "' "
                                    + " WHERE STOCK_ID = '" + item.getSTOCK_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());
                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                }
            }
        }


        return true;
    }

    @Override
    public List<EvidenceInventoryList> EvidenceInventoryListgetByLawsuitNo(EvidenceInventoryListgetByLawsuitNoReq req) {

        String str = "";

        if (req.getOFFICE_CODE() != null && !"".equals(req.getOFFICE_CODE()) && !"00".equals(req.getOFFICE_CODE())) {

            if (req.getOFFICE_CODE().length() == 6) {
                if ("00".equals(req.getOFFICE_CODE().substring(0, 2))) {
                    str = " ";
                } else if ("0000".equals(req.getOFFICE_CODE().substring(2, 6))) {
                    str = " and substr(OPS_LAWSUIT.OFFICE_CODE, 1, 2) = substr('" + req.getOFFICE_CODE() + "', 1, 2)";
                } else if ("00".equals(req.getOFFICE_CODE().substring(4, 6))) {
                    str = " and substr(OPS_LAWSUIT.OFFICE_CODE, 1, 4) = substr('" + req.getOFFICE_CODE() + "', 1, 4)";
                } else {
                    str = " and OPS_LAWSUIT.OFFICE_CODE = '" + req.getOFFICE_CODE() + "'";
                }
            }
        }

        String tempLawsuitDateFrom = "";
        String tempLawsuitDateTo = "";
        String tempEvidenceInDateFrom = "";
        String tempEvidenceInDateTo = "";

        if (!"".equals(req.getLAWSUIT_DATE_START())) {
            tempLawsuitDateFrom = String.format("%s %s", req.getLAWSUIT_DATE_START(), Pattern.TIME_FROM);
        }

        if (!"".equals(req.getLAWSUIT_DATE_END())) {
            tempLawsuitDateTo = String.format("%s %s", req.getLAWSUIT_DATE_END(), Pattern.TIME_TO);
        }

        if (!"".equals(req.getEVIDENCE_IN_DATE_START())) {
            tempEvidenceInDateFrom = String.format("%s %s", req.getEVIDENCE_IN_DATE_START(), Pattern.TIME_FROM);
        }

        if (!"".equals(req.getEVIDENCE_IN_DATE_END())) {
            tempEvidenceInDateTo = String.format("%s %s", req.getEVIDENCE_IN_DATE_END(), Pattern.TIME_TO);
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select " +
                        "    OPS_LAWSUIT.LAWSUIT_ID," +
                        "    OPS_LAWSUIT.LAWSUIT_NO," +
                        "    OPS_LAWSUIT.IS_OUTSIDE," +
                        "    TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO_YEAR," +
                        "    OPS_LAWSUIT.LAWSUIT_DATE," +
                        "    OPS_PROVE.DELIVERY_DOC_NO_1," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_DATE," +
                        "    OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE," +
                        "    OPS_EVIDENCE_IN_ITEM.PRODUCT_DESC," +
                        "    OPS_EVIDENCE_STOCK_BALANCE.STOCK_ID," +
                        "    OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY," +
                        "    OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY_UNIT," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_TYPE," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_ID," +
                        "    OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_CODE," +
                        "    OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_NAME," +
                        "    OPS_EVIDENCE_IN.DELIVERY_NO " +
//                        "    OPS_EVIDENCE_IN_STAFF.TITLE_NAME_TH," +
//                        "    OPS_EVIDENCE_IN_STAFF.FIRST_NAME," +
//                        "    OPS_EVIDENCE_IN_STAFF.LAST_NAME," +
//                        "    OPS_EVIDENCE_IN_STAFF.OPREATION_POS_NAME," +
//                        "    OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID" +
                        "    from OPS_LAWSUIT " +
                        "    INNER join OPS_PROVE on OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID AND OPS_PROVE.IS_ACTIVE = 1 AND OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    INNER join OPS_EVIDENCE_IN on OPS_PROVE.PROVE_ID = OPS_EVIDENCE_IN.PROVE_ID AND OPS_EVIDENCE_IN.IS_ACTIVE = 1" +
                        "    INNER join OPS_EVIDENCE_IN_STAFF on OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID = OPS_EVIDENCE_IN.EVIDENCE_IN_ID AND OPS_EVIDENCE_IN_STAFF.IS_ACTIVE = 1 AND OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID=60" +
                        "    INNER join OPS_EVIDENCE_IN_ITEM on OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID = OPS_EVIDENCE_IN.EVIDENCE_IN_ID AND OPS_EVIDENCE_IN_ITEM.IS_ACTIVE = 1" +
                        "    INNER join OPS_EVIDENCE_STOCK_BALANCE on OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID = OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID AND OPS_EVIDENCE_STOCK_BALANCE.IS_FINISH=2 WHERE 1=1 " + str);

                if (req.getLAWSUIT_NO() != null && !"".equals(req.getLAWSUIT_NO())) {
                    sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_NO = '"+req.getLAWSUIT_NO()+"' ");
                }

                if (req.getLAWSUIT_NO_YEAR() != null && !"".equals(req.getLAWSUIT_NO_YEAR())) {
                    sqlBuilder.append(" AND (to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getLAWSUIT_NO_YEAR()+"' AND to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getLAWSUIT_NO_YEAR()+"')" );
                }

                if (req.getIS_OUTSIDE() != null && !"".equals(req.getIS_OUTSIDE())) {
                    sqlBuilder.append(" AND OPS_LAWSUIT.IS_OUTSIDE = '"+req.getIS_OUTSIDE()+"'");
                }

                if (req.getDELIVERY_DOC_NO_1() != null && !"".equals(req.getDELIVERY_DOC_NO_1())) {
                    sqlBuilder.append(" AND OPS_PROVE.DELIVERY_DOC_NO_1 = '"+req.getDELIVERY_DOC_NO_1()+"' ");
                }

                if (req.getEVIDENCE_IN_ITEM_CODE() != null && !"".equals(req.getEVIDENCE_IN_ITEM_CODE())) {
                    sqlBuilder.append(" AND OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE = '"+req.getEVIDENCE_IN_ITEM_CODE()+"'");
                }

                if (req.getLAWSUIT_DATE_START() != null && !"".equals(req.getLAWSUIT_DATE_START()) && req.getLAWSUIT_DATE_END() != null && !"".equals(req.getLAWSUIT_DATE_END())) {
                    sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_DATE between to_date(nvl('" + tempLawsuitDateFrom + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('" + tempLawsuitDateTo + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
                }

                if (req.getEVIDENCE_IN_DATE_START() != null && !"".equals(req.getEVIDENCE_IN_DATE_START()) && req.getEVIDENCE_IN_DATE_END() != null && !"".equals(req.getEVIDENCE_IN_DATE_END())) {
                    sqlBuilder.append(" AND OPS_EVIDENCE_IN.EVIDENCE_IN_DATE between to_date(nvl('" + tempEvidenceInDateFrom + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('" + tempEvidenceInDateTo + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
                }



        log.info("[SQL ] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInventoryList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceInventoryList mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInventoryList item = new EvidenceInventoryList();

                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setDELIVERY_DOC_NO_1(rs.getString("DELIVERY_DOC_NO_1"));
                item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                item.setEVIDENCE_IN_ITEM_CODE(rs.getString("EVIDENCE_IN_ITEM_CODE"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setSTOCK_ID(rs.getInt("STOCK_ID"));
                item.setBALANCE_QTY(rs.getFloat("BALANCE_QTY"));
                item.setBALANCE_QTY_UNIT(rs.getString("BALANCE_QTY_UNIT"));
                item.setEVIDENCE_IN_TYPE(rs.getString("EVIDENCE_IN_TYPE"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setOPERATION_OFFICE_CODE(rs.getString("OPERATION_OFFICE_CODE"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
//                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
//                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
//                item.setLAST_NAME(rs.getString("LAST_NAME"));
//                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
//                item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));

                return item;

            }
        });
        return dataList;

    }

    @Override
    public List<EvidenceOutStockBalanceByLawsuitNo> EvidenceOutStockBalanceByLawsuitNo(EvidenceOutStockBalancegetByLawsuitNoReq req) {

        StringBuilder sqlBuilderParam = new StringBuilder();
        String tempLAWSUIT_DATE_FROM = "";
        String tempLAWSUIT_DATE_TO = "";
        String tempDELIVERY_DATE_FROM = "";
        String tempDELIVERY_DATE_TO = "";
        String tempEVIDENCE_IN_DATE_FROM = "";
        String tempEVIDENCE_IN_DATE_TO = "";


        if(!"".equals(req.getLAWSUIT_DATE_FROM())) {
            tempLAWSUIT_DATE_FROM = String.format("%s %s", req.getLAWSUIT_DATE_FROM(), Pattern.TIME_FROM);
        }

        if(!"".equals(req.getLAWSUIT_DATE_TO())) {
            tempLAWSUIT_DATE_TO = String.format("%s %s", req.getLAWSUIT_DATE_TO(),Pattern.TIME_TO);
        }

        if(!"".equals(req.getDELIVERY_DATE_FROM())) {
            tempDELIVERY_DATE_FROM = String.format("%s %s", req.getDELIVERY_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getDELIVERY_DATE_TO())) {
            tempDELIVERY_DATE_TO = String.format("%s %s", req.getDELIVERY_DATE_TO(),Pattern.TIME_TO);
        }

        if(!"".equals(req.getEVIDENCE_IN_DATE_FROM())) {
            tempEVIDENCE_IN_DATE_FROM = String.format("%s %s", req.getEVIDENCE_IN_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getEVIDENCE_IN_DATE_TO())) {
            tempEVIDENCE_IN_DATE_TO = String.format("%s %s", req.getEVIDENCE_IN_DATE_TO(),Pattern.TIME_TO);
        }

        if(req.getLAWSUIT_DATE_FROM() != null && !"".equals(req.getLAWSUIT_DATE_FROM()) && req.getLAWSUIT_DATE_TO() != null && !"".equals(req.getLAWSUIT_DATE_TO())) {
            sqlBuilderParam.append(" AND OPS_LAWSUIT.LAWSUIT_DATE between to_date(nvl('"+tempLAWSUIT_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempLAWSUIT_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getDELIVERY_DATE_FROM() != null && !"".equals(req.getDELIVERY_DATE_FROM()) && req.getDELIVERY_DATE_TO() != null && !"".equals(req.getDELIVERY_DATE_TO())) {
            sqlBuilderParam.append(" AND OPS_EVIDENCE_IN.DELIVERY_DATE between to_date(nvl('"+tempDELIVERY_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempDELIVERY_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getEVIDENCE_IN_DATE_FROM() != null && !"".equals(req.getEVIDENCE_IN_DATE_FROM()) && req.getEVIDENCE_IN_DATE_TO() != null && !"".equals(req.getEVIDENCE_IN_DATE_TO())) {
            sqlBuilderParam.append(" AND OPS_EVIDENCE_IN.EVIDENCE_IN_DATE between to_date(nvl('"+tempEVIDENCE_IN_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempEVIDENCE_IN_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getLAWSUIT_NO() != null && !"".equals(req.getLAWSUIT_NO())) {
            sqlBuilderParam.append(" AND OPS_LAWSUIT.LAWSUIT_NO ='"+req.getLAWSUIT_NO()+"' ");
        }

        if(req.getLAWSUIT_NO_YEAR()!= null && !"".equals(req.getLAWSUIT_NO_YEAR())) {
            sqlBuilderParam.append(" AND (to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getLAWSUIT_NO_YEAR()+"' AND to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getLAWSUIT_NO_YEAR()+"') ");
        }

        if(req.getDELIVERY_NO() != null && !"".equals(req.getDELIVERY_NO())) {
            sqlBuilderParam.append(" AND LOWER(OPS_EVIDENCE_IN.DELIVERY_NO)LIKE LOWER('%"+req.getDELIVERY_NO()+"%') ");
        }

        if(req.getIS_OUTSIDE() != null && !"".equals(req.getIS_OUTSIDE())) {
            sqlBuilderParam.append(" AND OPS_LAWSUIT.IS_OUTSIDE ='"+req.getIS_OUTSIDE()+"' ");
        }


        StringBuilder sqlBuilder = new StringBuilder()
                .append(" with temp as(" +
                        "    SELECT DISTINCT" +
                        "    CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') " +
                        "    || ' (' ||TO_CHAR(OPS_LAWSUIT.LAWSUIT_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') || ')' AS LAWSUIT_NO," +
                        "    OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "    OPS_EVIDENCE_IN.DELIVERY_DATE," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_DATE," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_ID" +
                        "    FROM OPS_LAWSUIT" +
                        "    INNER JOIN OPS_ARREST_INDICTMENT ON OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID AND OPS_ARREST_INDICTMENT.IS_ACTIVE =1" +
                        "    INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID AND OPS_ARREST.IS_ACTIVE =1" +
                        "    INNER JOIN OPS_EVIDENCE_IN ON OPS_ARREST.ARREST_ID = OPS_EVIDENCE_IN.ARREST_ID AND OPS_EVIDENCE_IN.IS_ACTIVE =1" +
                        "    LEFT JOIN OPS_EVIDENCE_IN_ITEM ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID AND OPS_EVIDENCE_IN_ITEM.IS_ACTIVE =1" +
                        "    LEFT JOIN OPS_EVIDENCE_STOCK_BALANCE ON OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID = OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID " +
                        "    LEFT JOIN OPS_EVIDENCE_OUT_DETAIL ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_OUT_DETAIL.EVIDENCE_IN_ID AND OPS_EVIDENCE_OUT_DETAIL.IS_ACTIVE =1" +
                        "    LEFT JOIN OPS_EVIDENCE_OUT ON OPS_EVIDENCE_OUT_DETAIL.EVIDENCE_OUT_ID = OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID AND OPS_EVIDENCE_OUT.IS_ACTIVE =1" +
                        "    LEFT JOIN OPS_EVIDENCE_OUT_ITEM ON OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID = OPS_EVIDENCE_OUT_ITEM.EVIDENCE_OUT_ID AND OPS_EVIDENCE_OUT_ITEM.IS_ACTIVE = 1" +
                        "    WHERE OPS_LAWSUIT.IS_ACTIVE = 1 " + sqlBuilderParam.toString()+
                        "    AND OPS_EVIDENCE_IN.IS_RECEIVE = 1 " +
                        " )" +
                        " select" +
                        "     DELIVERY_NO," +
                        "     TO_CHAR( DELIVERY_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as  DELIVERY_DATE," +
                        "     TO_CHAR( EVIDENCE_IN_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as  EVIDENCE_IN_DATE," +
                        "     EVIDENCE_IN_ID," +
                        "     LISTAGG(LAWSUIT_NO,',') WITHIN GROUP(" +
                        "     ORDER BY DELIVERY_NO , DELIVERY_DATE , EVIDENCE_IN_DATE , EVIDENCE_IN_ID" +
                        "     ) AS LAWSUIT_NO" +
                        " from temp" +
                        " GROUP BY DELIVERY_NO , DELIVERY_DATE , EVIDENCE_IN_DATE , EVIDENCE_IN_ID");




        log.info("[SQL ] : "+sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutStockBalanceByLawsuitNo> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceOutStockBalanceByLawsuitNo mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutStockBalanceByLawsuitNo item = new EvidenceOutStockBalanceByLawsuitNo();
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setEvidenceInItem(getEvidenceInItem(rs.getInt("EVIDENCE_IN_ID")));

                return item;

            }
        });
        return dataList;

    }

    @Override
    public List<EvidenceOutStockBalanceByLawsuitNo> EvidenceOutStockBalanceByEvidenceOutId(EvidenceOutStockBalancegetByEvidenceOutIdReq req) {



        StringBuilder sqlBuilder = new StringBuilder()
                .append(" with temp as(" +
                        "    SELECT DISTINCT" +
                        "    CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') " +
                        "    || ' (' ||TO_CHAR(OPS_LAWSUIT.LAWSUIT_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') || ')' AS LAWSUIT_NO," +
                        "    OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "    OPS_EVIDENCE_IN.DELIVERY_DATE," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_DATE," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_ID" +
                        "    FROM OPS_LAWSUIT" +
                        "    INNER JOIN OPS_ARREST_INDICTMENT ON OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID AND OPS_ARREST_INDICTMENT.IS_ACTIVE =1" +
                        "    INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID AND OPS_ARREST.IS_ACTIVE =1" +
                        "    INNER JOIN OPS_EVIDENCE_IN ON OPS_ARREST.ARREST_ID = OPS_EVIDENCE_IN.ARREST_ID AND OPS_EVIDENCE_IN.IS_ACTIVE =1" +
                        "    LEFT JOIN OPS_EVIDENCE_IN_ITEM ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID AND OPS_EVIDENCE_IN_ITEM.IS_ACTIVE =1" +
                        "    LEFT JOIN OPS_EVIDENCE_STOCK_BALANCE ON OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID = OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID " +
                        "    LEFT JOIN OPS_EVIDENCE_OUT_DETAIL ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_OUT_DETAIL.EVIDENCE_IN_ID AND OPS_EVIDENCE_OUT_DETAIL.IS_ACTIVE =1" +
                        "    LEFT JOIN OPS_EVIDENCE_OUT ON OPS_EVIDENCE_OUT_DETAIL.EVIDENCE_OUT_ID = OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID AND OPS_EVIDENCE_OUT.IS_ACTIVE =1" +
                        "    LEFT JOIN OPS_EVIDENCE_OUT_ITEM ON OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID = OPS_EVIDENCE_OUT_ITEM.EVIDENCE_OUT_ID AND OPS_EVIDENCE_OUT_ITEM.IS_ACTIVE = 1" +
                        "    WHERE OPS_LAWSUIT.IS_ACTIVE = 1 " +
                        "    AND OPS_EVIDENCE_IN.IS_RECEIVE = 1 " +
                        " )" +
                        " select" +
                        "     DELIVERY_NO," +
                        "     TO_CHAR( DELIVERY_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as  DELIVERY_DATE," +
                        "     TO_CHAR( EVIDENCE_IN_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as  EVIDENCE_IN_DATE," +
                        "     EVIDENCE_IN_ID," +
                        "     LISTAGG(LAWSUIT_NO,',') WITHIN GROUP(" +
                        "     ORDER BY DELIVERY_NO , DELIVERY_DATE , EVIDENCE_IN_DATE , EVIDENCE_IN_ID" +
                        "     ) AS LAWSUIT_NO" +
                        " from temp where EVIDENCE_IN_ID IN (SELECT EVIDENCE_IN_ID FROM OPS_EVIDENCE_OUT_DETAIL WHERE EVIDENCE_OUT_ID = "+req.getEVIDENCE_OUT_ID()+") " +
                        " GROUP BY DELIVERY_NO , DELIVERY_DATE , EVIDENCE_IN_DATE , EVIDENCE_IN_ID");




        log.info("[SQL ] : "+sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutStockBalanceByLawsuitNo> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceOutStockBalanceByLawsuitNo mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutStockBalanceByLawsuitNo item = new EvidenceOutStockBalanceByLawsuitNo();
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setEvidenceInItem(getEvidenceInItem(rs.getInt("EVIDENCE_IN_ID")));

                return item;

            }
        });
        return dataList;

    }
}
