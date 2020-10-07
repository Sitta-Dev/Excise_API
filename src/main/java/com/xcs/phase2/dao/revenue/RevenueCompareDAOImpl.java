package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.revenue.NewRevenueCourt;
import com.xcs.phase2.model.revenue.RevenueCompare;
import com.xcs.phase2.request.revenue.*;
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
public class RevenueCompareDAOImpl extends RevenueExt implements RevenueCompareDAO {

    private static final Logger log = LoggerFactory.getLogger(RevenueCompareDAOImpl.class);

    @Override
    public List<RevenueCompare> RevenueComparegetByCreate(RevenueComparegetByCreateReq req) {
        // TODO Auto-generated method stub


        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_COMPARE.COMPARE_ID," +
                        "    OPS_COMPARE.LAWSUIT_ID," +
                        "    OPS_COMPARE.OFFICE_ID," +
                        "    OPS_COMPARE.TREASURY_RATE AS TREASURY_RATE," +
                        "    OPS_COMPARE.BRIBE_RATE AS BRIBE_RATE," +
                        "    OPS_COMPARE.REWARD_RATE AS REWARD_RATE," +
                        "    OPS_COMPARE.OFFICE_CODE," +
                        "    OPS_COMPARE.OFFICE_NAME," +
                        "    OPS_COMPARE.IS_OUTSIDE," +
                        "    CASE WHEN OPS_COMPARE.IS_OUTSIDE =0 THEN 'เปรียบเทียบในสถานที่ทำการ' " +
                        "    WHEN OPS_COMPARE.IS_OUTSIDE =1 THEN 'เปรียบเทียบนอกสถานที่ทำการ'  END COMPARE_IS_OUTSIDE," +
                        "    CASE WHEN OPS_COMPARE.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_COMPARE.COMPARE_NO || CASE WHEN OPS_COMPARE.COMPARE_NO IS NOT NULL THEN '/' END " +
                        "    || TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_NO," +
                        "    TO_CHAR(OPS_COMPARE.COMPARE_DATE,'dd-mm-yyyy hh:mm:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_DATE," +
                        "    OPS_COMPARE.IS_ACTIVE," +
                        "    OPS_COMPARE_DETAIL.IS_TEMP_RELEASE," +
                        "    OPS_COMPARE.COMPARE_NO" +
                        "    from OPS_COMPARE_DETAIL " +
                        "    LEFT JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID AND OPS_COMPARE_MAPPING.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_COMPARE ON OPS_COMPARE_MAPPING.COMPARE_ID = OPS_COMPARE.COMPARE_ID AND OPS_COMPARE.IS_ACTIVE = 1" +
                        "    where LOWER(OPS_COMPARE.OFFICE_CODE)LIKE LOWER('%"+req.getOFFICE_CODE()+"%')" +
                        "    and OPS_COMPARE_DETAIL.IS_REVENUE = 0 AND OPS_COMPARE_DETAIL.IS_TEMP_RELEASE = 0"+
                        " 	 ORDER BY OPS_COMPARE.COMPARE_NO DESC");




        log.info("[SQL]  : " + sqlBuilderDetail.toString());
        System.out.println("[SQL] [RevenueComparegetByCreate]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCompare> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public RevenueCompare mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueCompare item = new RevenueCompare();
                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                item.setTREASURY_RATE(rs.getFloat("TREASURY_RATE"));
                item.setBRIBE_RATE(rs.getFloat("BRIBE_RATE"));
                item.setREWARD_RATE(rs.getFloat("REWARD_RATE"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setCOMPARE_NO(rs.getString("COMPARE_NO"));
                item.setCOMPARE_IS_OUTSIDE(rs.getString("COMPARE_IS_OUTSIDE"));
                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setIS_TEMP_RELEASE(rs.getInt("IS_TEMP_RELEASE"));
                item.setRevenueCompareMapping(getRevenueCompareMapping(rs.getInt("COMPARE_ID")));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<RevenueCompare> RevenueComparegetByCon(RevenueComparegetByConReq req) {
        // TODO Auto-generated method stub


        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_REVENUE_DETAIL.REVENUE_ID," +
                        //"    OPS_REVENUE_DETAIL.REVENUE_DETAIL_ID," +
                        "    OPS_COMPARE.COMPARE_ID," +
                        "    OPS_COMPARE.LAWSUIT_ID," +
                        "    OPS_COMPARE.OFFICE_ID," +
                        "    OPS_COMPARE.TREASURY_RATE AS TREASURY_RATE," +
                        "    OPS_COMPARE.BRIBE_RATE AS BRIBE_RATE," +
                        "    OPS_COMPARE.REWARD_RATE AS REWARD_RATE," +
                        "    OPS_COMPARE.OFFICE_CODE," +
                        "    OPS_COMPARE.OFFICE_NAME," +
                        "    OPS_COMPARE.IS_OUTSIDE," +
                        "    CASE WHEN OPS_COMPARE.IS_OUTSIDE = 0 THEN 'เปรียบเทียบในสถานที่ทำการ' WHEN OPS_COMPARE.IS_OUTSIDE =1 THEN 'เปรียบเทียบนอกสถานที่ทำการ'  END COMPARE_IS_OUTSIDE," +
                        "    CASE WHEN OPS_COMPARE.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_COMPARE.COMPARE_NO || CASE WHEN OPS_COMPARE.COMPARE_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_NO," +
                        "    TO_CHAR(OPS_COMPARE.COMPARE_DATE,'dd-mm-yyyy hh:mm:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_DATE," +
                        "    OPS_COMPARE.IS_ACTIVE" +
                        "    FROM OPS_REVENUE_DETAIL" +
                        "    INNER JOIN OPS_COMPARE_DETAIL ON OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID  = OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID AND  OPS_COMPARE_DETAIL.IS_ACTIVE = 1" +
                        "    INNER JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID AND OPS_COMPARE_MAPPING.IS_ACTIVE=1" +
                        "    INNER JOIN OPS_COMPARE ON OPS_COMPARE_MAPPING.COMPARE_ID = OPS_COMPARE.COMPARE_ID AND OPS_COMPARE.IS_ACTIVE = 1" +
                        "    WHERE OPS_REVENUE_DETAIL.IS_ACTIVE = 1" +
                        "    AND OPS_REVENUE_DETAIL.REVENUE_ID = "+req.getREVENUE_ID() +
                        "    ORDER BY COMPARE_DATE");




        log.info("[SQL]  [RevenueComparegetByCon]: " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCompare> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueCompare mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueCompare item = new RevenueCompare();
                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                item.setTREASURY_RATE(rs.getFloat("TREASURY_RATE"));
                item.setBRIBE_RATE(rs.getFloat("BRIBE_RATE"));
                item.setREWARD_RATE(rs.getFloat("REWARD_RATE"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setCOMPARE_NO(rs.getString("COMPARE_NO"));
                item.setCOMPARE_IS_OUTSIDE(rs.getString("COMPARE_IS_OUTSIDE"));
                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setRevenueCompareMapping(getRevenueCompareMappingByCon(rs.getInt("COMPARE_ID")));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<NewRevenueCourt> RevenueCourtgetByCon(RevenueCourtgetByConReq req) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select DISTINCT " +
                        "    OPS_REVENUE_DETAIL.REVENUE_ID," +
                        "    OPS_LAWSUIT.LAWSUIT_ID, " +
                        "    OPS_LAWSUIT.INDICTMENT_ID, " +
                        "    OPS_LAWSUIT.OFFICE_ID, " +
                        "    OPS_LAWSUIT.OFFICE_CODE, " +
                        "    OPS_LAWSUIT.OFFICE_NAME, " +
                        "    CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS Lawsuilt_no," +
                        "    OPS_LAWSUIT.IS_LAWSUIT," +
                        "    OPS_LAWSUIT.LAWSUIT_DATE," +
                        "    OPS_LAWSUIT.IS_OUTSIDE" +
                        "    from OPS_REVENUE_DETAIL " +
                        "    INNER JOIN OPS_LAWSUIT_DETAIL on OPS_REVENUE_DETAIL.LAWSUIT_DETAIL_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID" +
                        "    and OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "    INNER JOIN OPS_LAWSUIT on OPS_LAWSUIT_DETAIL.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
                        "    and OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    where " +
                        "    OPS_REVENUE_DETAIL.IS_ACTIVE = 1" +
                        "    and OPS_REVENUE_DETAIL.REVENUE_ID = '"+req.getREVENUE_ID()+"'" +
                        "    ORDER BY OPS_LAWSUIT.LAWSUIT_DATE DESC ");




        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NewRevenueCourt> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NewRevenueCourt mapRow(ResultSet rs, int rowNum) throws SQLException {
                NewRevenueCourt item = new NewRevenueCourt();
                item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setLAWSUILT_NO(rs.getString("LAWSUILT_NO"));
                item.setIS_LAWSUIT(rs.getInt("IS_LAWSUIT"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                item.setRevenueLawsuitDetail(getNewRevenueLawsuitDetailByCourt(rs.getInt("LAWSUIT_ID")));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public boolean RevenueCourtupdStatus(RevenueCourtupdStatusReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_REVENUE SET REVENUE_STATUS = '2' WHERE REVENUE_ID = '"+req.getREVENUE_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        return true;
    }

    @Override
    public boolean RevenueCompareStatus(RevenueCompareStatusReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL SET IS_REVENUE = '"+req.getIS_REVENUE()+"' WHERE COMPARE_DETAIL_ID = '"+req.getCOMPARE_DETAIL_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        return true;
    }

    @Override
    public boolean RevenueReturnupdREFno(RevenueReturnupdREFnoReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder()
                .append(" UPDATE OPS_REVENUE SET " +
                        " RECEIVE_REF_NO = '"+req.getRECEIVE_REF_NO()+"' ," +
                        " RECEIVE_DATE = TO_TIMESTAMP_TZ('"+req.getRECEIVE_DATE()+"', '"+ Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"') "+
                        "WHERE REVENUE_ID = '"+req.getREVENUE_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        return true;
    }




}
