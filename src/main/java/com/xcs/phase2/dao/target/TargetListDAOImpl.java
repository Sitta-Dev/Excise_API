package com.xcs.phase2.dao.target;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.target.TargetList;
import com.xcs.phase2.request.target.TargetListgetByConAdvReq;
import com.xcs.phase2.request.target.TargetListgetByKeywordReq;
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
public class TargetListDAOImpl extends TargetExt implements TargetListDAO{

    private static final Logger log = LoggerFactory.getLogger(TargetListDAOImpl.class);

    @Override
    public List<TargetList> TargetListgetByKeyword(TargetListgetByKeywordReq req) {
        // TODO Auto-generated method stub

        String str = "";

        if(req.getOFFICE_CODE() != null && !"".equals(req.getOFFICE_CODE()) && !"00".equals(req.getOFFICE_CODE())) {

            if(req.getOFFICE_CODE().length() == 6) {

                if("00".equals(req.getOFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getOFFICE_CODE().substring(2, 6))) {

                    str = " AND SUBSTR(OPS_TARGET.OFFICE_CODE,1,2) = SUBSTR('"+req.getOFFICE_CODE()+"',1,2) " ;

                }else if("00".equals(req.getOFFICE_CODE().substring(4, 6))) {

                    str = " AND SUBSTR(OPS_TARGET.OFFICE_CODE,1,4) = SUBSTR('"+req.getOFFICE_CODE()+"',1,4) " ;

                }else {

                    str = " AND OPS_TARGET.OFFICE_CODE = '"+req.getOFFICE_CODE()+"' " ;
                }
            }
        }

        String sb = " AND " +
                " ( " +
                "    OPS_TARGET.TARGET_CODE = ''" +
                "    OR OPS_TARGET.BUDGET_YEAR || TO_CHAR(OPS_TARGET.BUDGET_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') = '"+req.getTEXT_SEARCH()+"'" +
                "    OR OPS_TARGET_ITEM.SEQUENCE = '"+req.getTEXT_SEARCH()+"'" +
                "    OR lower(OPS_TARGET.OFFICE_NAME)like lower(replace('%"+req.getTEXT_SEARCH()+"%','',''))" +
                "    OR lower(MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME)like lower(replace('%"+req.getTEXT_SEARCH()+"%','',''))" +
                " ) ";

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT " +
                        "OPS_TARGET.TARGET_ID," +
                        "OPS_TARGET.TARGET_CODE," +
                        "OPS_TARGET_ITEM.IS_SEND," +
                        "TO_CHAR(OPS_TARGET.BUDGET_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS BUDGET_YEAR," +
                        "OPS_TARGET_ITEM.SEQUENCE," +
                        "OPS_TARGET_ITEM.TARGET_ITEM_DATE," +
                        "OPS_TARGET.OFFICE_NAME, " +
                        "MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME," +
                        "OPS_TARGET_ITEM.LAWSUIT_TYPE_TARGET,OPS_TARGET_ITEM.ITEM_ID," +
                        "OPS_TARGET_ITEM.IS_ACTIVE" +
                        " FROM" +
                        " OPS_TARGET" +
                        " LEFT JOIN OPS_TARGET_ITEM ON OPS_TARGET.TARGET_ID=OPS_TARGET_ITEM.TARGET_ID" +
                        " LEFT JOIN MAS_PRODUCT_GROUP ON OPS_TARGET_ITEM.PRODUCT_GROUP_ID=MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID" +
                        " WHERE " +
                        " OPS_TARGET.IS_ACTIVE = 1  " +
                        " AND OPS_TARGET.OFFICE_CODE = '"+req.getOFFICE_CODE()+"' ");
        sqlBuilder.append(sb);



        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<TargetList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public TargetList mapRow(ResultSet rs, int rowNum) throws SQLException {
                TargetList item = new TargetList();
                item.setTARGET_ID(rs.getInt("TARGET_ID"));
                item.setTARGET_CODE(rs.getString("TARGET_CODE"));
                item.setIS_SEND(rs.getInt("IS_SEND"));
                item.setBUDGET_YEAR(rs.getString("BUDGET_YEAR"));
                item.setSEQUENCE(rs.getInt("SEQUENCE"));
                item.setTARGET_ITEM_DATE(rs.getString("TARGET_ITEM_DATE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
                item.setLAWSUIT_TYPE_TARGET(rs.getInt("LAWSUIT_TYPE_TARGET"));
                item.setITEM_ID(rs.getInt("ITEM_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<TargetList> TargetListgetByConAdv(TargetListgetByConAdvReq req) {
        // TODO Auto-generated method stub

        String tempBUDGET_YEAR_FROM = "";
        String tempBUDGET_YEAR_TO = "";
        String tempTARGET_ITEM_DATE_FROM = "";
        String tempTARGET_ITEM_DATE_TO = "";

        String str = "";

        if(req.getOFFICE_CODE() != null && !"".equals(req.getOFFICE_CODE()) && !"00".equals(req.getOFFICE_CODE())) {

            if(req.getOFFICE_CODE().length() == 6) {

                if("00".equals(req.getOFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getOFFICE_CODE().substring(2, 6))) {

                    str = " AND SUBSTR(OPS_TARGET.OFFICE_CODE,1,2) = SUBSTR('"+req.getOFFICE_CODE()+"',1,2) " ;

                }else if("00".equals(req.getOFFICE_CODE().substring(4, 6))) {

                    str = " AND SUBSTR(OFFICE_CODE,1,4) = SUBSTR('"+req.getOFFICE_CODE()+"',1,4) " ;

                }else {

                    str = " AND OFFICE_CODE = '"+req.getOFFICE_CODE()+"' " ;
                }

            }

        }


//        if (!"".equals(req.getBUDGET_YEAR_FROM())) {
//            tempBUDGET_YEAR_FROM = String.format("%s %s", req.getBUDGET_YEAR_FROM(), Pattern.TIME_FROM);
//        }
//
//        if (!"".equals(req.getBUDGET_YEAR_TO())) {
//            tempBUDGET_YEAR_TO = String.format("%s %s", req.getBUDGET_YEAR_TO(), Pattern.TIME_TO);
//        }

        if (!"".equals(req.getTARGET_ITEM_DATE_FROM())) {
            tempTARGET_ITEM_DATE_FROM = String.format("%s %s", req.getTARGET_ITEM_DATE_FROM(), Pattern.TIME_FROM);
        }

        if (!"".equals(req.getTARGET_ITEM_DATE_TO())) {
            tempTARGET_ITEM_DATE_TO = String.format("%s %s", req.getTARGET_ITEM_DATE_TO(), Pattern.TIME_TO);
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT " +
                        "OPS_TARGET.TARGET_ID," +
                        "OPS_TARGET.TARGET_CODE," +
                        "OPS_TARGET_ITEM.IS_SEND," +
                        "TO_CHAR(OPS_TARGET.BUDGET_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS BUDGET_YEAR," +
                        "OPS_TARGET_ITEM.SEQUENCE," +
                        "OPS_TARGET_ITEM.TARGET_ITEM_DATE," +
                        "OPS_TARGET.OFFICE_NAME, " +
                        "MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME," +
                        "OPS_TARGET_ITEM.LAWSUIT_TYPE_TARGET," +
                        "OPS_TARGET_ITEM.ITEM_ID," +
                        "OPS_TARGET_ITEM.IS_ACTIVE" +
                        " FROM" +
                        " OPS_TARGET" +
                        " LEFT JOIN OPS_TARGET_ITEM ON OPS_TARGET.TARGET_ID=OPS_TARGET_ITEM.TARGET_ID" +
                        " LEFT JOIN MAS_PRODUCT_GROUP ON OPS_TARGET_ITEM.PRODUCT_GROUP_ID=MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID" +
                        " WHERE " +
                        " OPS_TARGET.IS_ACTIVE = 1 "+str);

        if (req.getTARGET_CODE() != null && !"".equals(req.getTARGET_CODE())) {
            sqlBuilder.append(" AND OPS_TARGET.TARGET_CODE = '" + req.getTARGET_CODE() + "' ");
        }

        if (req.getIS_SEND() != null && !"".equals(req.getIS_SEND())) {
            sqlBuilder.append(" AND OPS_TARGET_ITEM.IS_SEND = '" + req.getIS_SEND() + "' ");
        }

        if (req.getBUDGET_YEAR_FROM() != null && !"".equals(req.getBUDGET_YEAR_FROM()) && req.getBUDGET_YEAR_TO() != null && !"".equals(req.getBUDGET_YEAR_TO())) {
            sqlBuilder.append(" AND (to_number(TO_CHAR(OPS_TARGET.BUDGET_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getBUDGET_YEAR_FROM()+"' AND to_number(TO_CHAR(OPS_TARGET.BUDGET_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getBUDGET_YEAR_TO()+"') ");
        }

        if (req.getSEQUENCE() != null && !"".equals(req.getSEQUENCE())) {
            sqlBuilder.append(" or OPS_TARGET_ITEM.SEQUENCE = '" + req.getSEQUENCE() + "' ");
        }

        if (req.getTARGET_ITEM_DATE_FROM() != null && !"".equals(req.getTARGET_ITEM_DATE_FROM()) && req.getTARGET_ITEM_DATE_TO() != null && !"".equals(req.getTARGET_ITEM_DATE_TO())) {
            sqlBuilder.append(" AND OPS_TARGET_ITEM.TARGET_ITEM_DATE BETWEEN  to_date(nvl('" + tempTARGET_ITEM_DATE_FROM + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('" + tempTARGET_ITEM_DATE_TO + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if (req.getOFFICE_NAME() != null && !"".equals(req.getOFFICE_NAME())) {
            sqlBuilder.append(" AND lower(OPS_TARGET.OFFICE_NAME)like lower(replace('%" + req.getOFFICE_NAME() + "%','','')) ");
        }

        if (req.getPRODUCT_GROUP_NAME() != null && !"".equals(req.getPRODUCT_GROUP_NAME())) {
            sqlBuilder.append(" AND lower(MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME)like lower(replace('%" + req.getPRODUCT_GROUP_NAME() + "%','','')) ");
        }

        if (req.getLAWSUIT_TYPE_TARGET() != null && !"".equals(req.getLAWSUIT_TYPE_TARGET())) {
            sqlBuilder.append(" AND OPS_TARGET_ITEM.LAWSUIT_TYPE_TARGET = '" + req.getLAWSUIT_TYPE_TARGET() + "' ");
        }

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<TargetList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public TargetList mapRow(ResultSet rs, int rowNum) throws SQLException {
                TargetList item = new TargetList();
                item.setTARGET_ID(rs.getInt("TARGET_ID"));
                item.setTARGET_CODE(rs.getString("TARGET_CODE"));
                item.setIS_SEND(rs.getInt("IS_SEND"));
                item.setBUDGET_YEAR(rs.getString("BUDGET_YEAR"));
                item.setSEQUENCE(rs.getInt("SEQUENCE"));
                item.setTARGET_ITEM_DATE(rs.getString("TARGET_ITEM_DATE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
                item.setLAWSUIT_TYPE_TARGET(rs.getInt("LAWSUIT_TYPE_TARGET"));
                item.setITEM_ID(rs.getInt("ITEM_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;
    }

}
