package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.revenue.NewRevenueList;
import com.xcs.phase2.model.revenue.RevenueDetail;
import com.xcs.phase2.model.revenue.RevenueList;
import com.xcs.phase2.model.revenue.RevenueStaff;
import com.xcs.phase2.request.revenue.*;
import com.xcs.phase2.response.revenue.RevenueDetailResponse;
import com.xcs.phase2.response.revenue.RevenueStaffResponse;
import com.xcs.phase2.response.revenue.RevenueinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RevenueListDAOImpl extends RevenueExt implements RevenueListDAO {

    private static final Logger log = LoggerFactory.getLogger(RevenueListDAOImpl.class);

    @Override
    public List<RevenueList> RevenuegetByKeyword(final RevenuegetByKeywordReq req) {
        // TODO Auto-generated method stub

        String str = "";

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {


                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " and substr(OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE, 1, 2) = substr('"+req.getACCOUNT_OFFICE_CODE()+"', 1, 2) " ;
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " and substr(OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE, 1, 4) = substr('"+req.getACCOUNT_OFFICE_CODE()+"', 1, 4) " ;
                }else {
                    str = " and OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' " ;
                }

            }

        }

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("with temp as(" +
                        "  select" +
                        "    OPS_REVENUE.REVENUE_ID," +
                        "    OPS_REVENUE.DELIVERY_OFFICE_ID," +
                        "    OPS_REVENUE.RECEIVE_OFFICE_ID," +
                        "    OPS_REVENUE.REVENUE_CODE," +
                        "    OPS_REVENUE.DELIVERY_OFFICE_CODE," +
                        "    OPS_REVENUE.DELIVERY_OFFICE_NAME," +
                        "    OPS_REVENUE.RECEIVE_OFFICE_CODE," +
                        "    OPS_REVENUE.RECEIVE_OFFICE_NAME," +
                        "    OPS_REVENUE.REVENUE_NO," +
                        "    to_char(OPS_REVENUE.REVENUE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as REVENUE_DATE," +
                        "    OPS_REVENUE.REVENUE_STATUS," +
                        "    OPS_REVENUE.REVENUE_COUNT," +
                        "    OPS_REVENUE.FINE," +
                        "    OPS_REVENUE.TREASURY_MONEY," +
                        "    OPS_REVENUE.BRIBE_MONEY," +
                        "    OPS_REVENUE. REWARD_MONEY," +
                        "    OPS_REVENUE.RECEIVE_REF_NO," +
                        "    to_char(OPS_REVENUE.RECEIVE_DATE,'"+Pattern.FORMAT_DATETIME+"') as RECEIVE_DATE," +
                        "    OPS_REVENUE.IS_ACTIVE " +
                        "  FROM " +
                        "  OPS_REVENUE" +
                        "  inner join OPS_REVENUE_STAFF on OPS_REVENUE.REVENUE_ID = OPS_REVENUE_STAFF.REVENUE_ID  " +
                        "  inner join OPS_REVENUE_DETAIL on OPS_REVENUE.REVENUE_ID = OPS_REVENUE_DETAIL.REVENUE_ID" +
                        "  inner join OPS_COMPARE_DETAIL on OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID = OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID" +
                        "  inner join OPS_COMPARE_MAPPING on OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID" +
                        "  inner join OPS_COMPARE on OPS_COMPARE_MAPPING.COMPARE_ID = OPS_COMPARE.COMPARE_ID" +
                        "  where OPS_REVENUE.IS_ACTIVE = 1" +
                        "  and OPS_REVENUE_STAFF.IS_ACTIVE = 1" +
                        "  and OPS_REVENUE_DETAIL.IS_ACTIVE = 1 and OPS_COMPARE_DETAIL.IS_ACTIVE = 1 and OPS_COMPARE.IS_ACTIVE = 1 " +
                        "  and " +
                        "  (" +
                        "    lower(OPS_REVENUE.REVENUE_CODE) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ','')) " +
                        "    or lower(OPS_REVENUE_STAFF.TITLE_NAME_TH||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME ) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    or lower(OPS_REVENUE_STAFF.OPERATION_OFFICE_NAME) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  )" +str+
                        "  order by OPS_REVENUE.REVENUE_ID asc" +
                        ")select DISTINCT * from temp");

        log.info("[SQL]  : " + sqlBuilderDetail.toString());
        System.out.println("[SQL] [AdjustCompareListgetByKeyword]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<RevenueList> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public RevenueList mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueList item = new RevenueList();
                item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                item.setDELIVERY_OFFICE_ID(rs.getInt("DELIVERY_OFFICE_ID"));
                item.setRECEIVE_OFFICE_ID(rs.getInt("RECEIVE_OFFICE_ID"));
                item.setREVENUE_CODE(rs.getString("REVENUE_CODE"));
                item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                item.setRECEIVE_OFFICE_CODE(rs.getString("RECEIVE_OFFICE_CODE"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setREVENUE_NO(rs.getString("REVENUE_NO"));
                item.setREVENUE_DATE(rs.getString("REVENUE_DATE"));
                item.setREVENUE_STATUS(rs.getInt("REVENUE_STATUS"));
                item.setREVENUE_COUNT(rs.getString("REVENUE_COUNT"));
                item.setFINE(rs.getFloat("FINE"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                item.setRECEIVE_REF_NO(rs.getString("RECEIVE_REF_NO"));
                item.setRECEIVE_DATE(rs.getString("RECEIVE_DATE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setRevenueDetail(getRevenueDetail(rs.getInt("REVENUE_ID"),req.getACCOUNT_OFFICE_CODE()));
                item.setRevenueStaff(getRevenueStaff(rs.getInt("REVENUE_ID")));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<RevenueList> RevenuegetByConAdv(final RevenuegetByConAdvReq req) {
        // TODO Auto-generated method stub

        String str = "";
        String tempDateFrom   = "";
        String tempDateTo = "";


        if(!"".equals(req.getREVENUE_DATE_FROM())) {
            tempDateFrom = String.format("%s %s", req.getREVENUE_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getREVENUE_DATE_TO())) {
            tempDateTo = String.format("%s %s", req.getREVENUE_DATE_TO(),Pattern.TIME_TO);
        }

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {


                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " and substr(OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE, 1, 2) = substr('"+req.getACCOUNT_OFFICE_CODE()+"', 1, 2) " ;
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " and substr(OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE, 1, 4) = substr('"+req.getACCOUNT_OFFICE_CODE()+"', 1, 4) " ;
                }else {
                    str = " and OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"' " ;
                }

            }

        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("with temp as(" +
                        "  select" +
                        "    OPS_REVENUE.REVENUE_ID," +
                        "    OPS_REVENUE.DELIVERY_OFFICE_ID," +
                        "    OPS_REVENUE.RECEIVE_OFFICE_ID," +
                        "    OPS_REVENUE.REVENUE_CODE," +
                        "    OPS_REVENUE.DELIVERY_OFFICE_CODE," +
                        "    OPS_REVENUE.DELIVERY_OFFICE_NAME," +
                        "    OPS_REVENUE.RECEIVE_OFFICE_CODE," +
                        "    OPS_REVENUE.RECEIVE_OFFICE_NAME," +
                        "    OPS_REVENUE.REVENUE_NO," +
                        "    to_char(OPS_REVENUE.REVENUE_DATE,'"+Pattern.FORMAT_DATETIME+"') as REVENUE_DATE," +
                        "    OPS_REVENUE.REVENUE_STATUS," +
                        "    OPS_REVENUE.REVENUE_COUNT," +
                        "    OPS_REVENUE.FINE," +
                        "    OPS_REVENUE.TREASURY_MONEY," +
                        "    OPS_REVENUE.BRIBE_MONEY," +
                        "    OPS_REVENUE. REWARD_MONEY," +
                        "    OPS_REVENUE.RECEIVE_REF_NO," +
                        "    to_char(OPS_REVENUE.RECEIVE_DATE,'"+Pattern.FORMAT_DATETIME+"') as RECEIVE_DATE," +
                        "    OPS_REVENUE.IS_ACTIVE " +
                        "  FROM " +
                        "  OPS_REVENUE" +
                        "  inner join OPS_REVENUE_STAFF on OPS_REVENUE.REVENUE_ID = OPS_REVENUE_STAFF.REVENUE_ID  " +
                        "  inner join OPS_REVENUE_DETAIL on OPS_REVENUE.REVENUE_ID = OPS_REVENUE_DETAIL.REVENUE_ID" +
                        "  inner join OPS_COMPARE_DETAIL on OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID = OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID" +
                        "  inner join OPS_COMPARE_MAPPING on OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID" +
                        "  inner join OPS_COMPARE on OPS_COMPARE_MAPPING.COMPARE_ID = OPS_COMPARE.COMPARE_ID" +
                        "  where OPS_REVENUE.IS_ACTIVE = 1" +
                        "  and OPS_REVENUE_STAFF.IS_ACTIVE = 1" +
                        "  and OPS_REVENUE_DETAIL.IS_ACTIVE = 1 and OPS_COMPARE_DETAIL.IS_ACTIVE = 1 and OPS_COMPARE.IS_ACTIVE = 1 " );


        if(req.getREVENUE_CODE() != null && !"".equals(req.getREVENUE_CODE())) {
            sqlBuilder.append(" and lower(OPS_REVENUE.REVENUE_CODE) like lower(replace ('%"+req.getREVENUE_CODE()+"%',' ','')) ");
        }

        if(req.getREVENUE_DATE_FROM() != null && !"".equals(req.getREVENUE_DATE_FROM()) && req.getREVENUE_DATE_TO() != null && !"".equals(req.getREVENUE_DATE_TO())) {
            sqlBuilder.append(" AND OPS_REVENUE.REVENUE_DATE BETWEEN  to_date(nvl('"+tempDateFrom+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempDateTo+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getSTAFF_NAME_SEND() != null && !"".equals(req.getSTAFF_NAME_SEND())) {
            sqlBuilder.append(" and  (lower(OPS_REVENUE_STAFF.TITLE_NAME_TH||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME ) like lower(replace('%"+req.getSTAFF_NAME_SEND()+"%',' ','')) AND OPS_REVENUE_STAFF.CONTRIBUTOR_ID = '36' ) ");
        }

        if(req.getSTAFF_NAME_SIGN() != null && !"".equals(req.getSTAFF_NAME_SIGN())) {
            sqlBuilder.append(" and  (lower(OPS_REVENUE_STAFF.TITLE_NAME_TH||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME ) like lower(replace('%"+req.getSTAFF_NAME_SIGN()+"%',' ','')) AND OPS_REVENUE_STAFF.CONTRIBUTOR_ID = '37') ");
        }

        if(req.getCOMPARE_NO() != null && !"".equals(req.getCOMPARE_NO())) {
            sqlBuilder.append(" and lower(OPS_COMPARE.COMPARE_NO) like lower(replace ('%"+req.getCOMPARE_NO()+"%',' ','')) ");
        }

        if(req.getREVENUE_STATUS() != null && !"".equals(req.getREVENUE_STATUS())) {
            sqlBuilder.append("  and OPS_REVENUE.REVENUE_STATUS = '"+req.getREVENUE_STATUS()+"' ");
        }

        sqlBuilder.append(str +" order by OPS_REVENUE.REVENUE_ID asc )select DISTINCT * from temp" );



        log.info("[SQL]  : " + sqlBuilder.toString());
        System.out.println("[SQL] [AdjustCompareListgetByKeyword]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueList mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueList item = new RevenueList();
                item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                item.setDELIVERY_OFFICE_ID(rs.getInt("DELIVERY_OFFICE_ID"));
                item.setRECEIVE_OFFICE_ID(rs.getInt("RECEIVE_OFFICE_ID"));
                item.setREVENUE_CODE(rs.getString("REVENUE_CODE"));
                item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                item.setRECEIVE_OFFICE_CODE(rs.getString("RECEIVE_OFFICE_CODE"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setREVENUE_NO(rs.getString("REVENUE_NO"));
                item.setREVENUE_DATE(rs.getString("REVENUE_DATE"));
                item.setREVENUE_STATUS(rs.getInt("REVENUE_STATUS"));
                item.setREVENUE_COUNT(rs.getString("REVENUE_COUNT"));
                item.setFINE(rs.getFloat("FINE"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                item.setRECEIVE_REF_NO(rs.getString("RECEIVE_REF_NO"));
                item.setRECEIVE_DATE(rs.getString("RECEIVE_DATE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setRevenueDetail(getRevenueDetail(rs.getInt("REVENUE_ID"),req.getACCOUNT_OFFICE_CODE()));
                item.setRevenueStaff(getRevenueStaff(rs.getInt("REVENUE_ID")));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public RevenueList RevenuegetByCon(final RevenuegetByConReq req) {



        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "REVENUE_ID," +
                        "DELIVERY_OFFICE_ID," +
                        "RECEIVE_OFFICE_ID," +
                        "REVENUE_CODE," +
                        "DELIVERY_OFFICE_CODE," +
                        "DELIVERY_OFFICE_NAME," +
                        "RECEIVE_OFFICE_CODE," +
                        "RECEIVE_OFFICE_NAME," +
                        "REVENUE_NO," +
                        "to_char(REVENUE_DATE,'"+Pattern.FORMAT_DATETIME+"') as REVENUE_DATE," +
                        "REVENUE_STATUS," +
                        "REVENUE_COUNT," +
                        "FINE," +
                        "TREASURY_MONEY," +
                        "BRIBE_MONEY," +
                        "REWARD_MONEY," +
                        "RECEIVE_REF_NO," +
                        "to_char(RECEIVE_DATE,'"+Pattern.FORMAT_DATETIME+"') as RECEIVE_DATE," +
                        "IS_ACTIVE" +
                        " from OPS_REVENUE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and REVENUE_ID = '"+req.getREVENUE_ID()+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<RevenueList>() {

            public RevenueList extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    RevenueList item = new RevenueList();
                    item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                    item.setDELIVERY_OFFICE_ID(rs.getInt("DELIVERY_OFFICE_ID"));
                    item.setRECEIVE_OFFICE_ID(rs.getInt("RECEIVE_OFFICE_ID"));
                    item.setREVENUE_CODE(rs.getString("REVENUE_CODE"));
                    item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                    item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                    item.setRECEIVE_OFFICE_CODE(rs.getString("RECEIVE_OFFICE_CODE"));
                    item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                    item.setREVENUE_NO(rs.getString("REVENUE_NO"));
                    item.setREVENUE_DATE(rs.getString("REVENUE_DATE"));
                    item.setREVENUE_STATUS(rs.getInt("REVENUE_STATUS"));
                    item.setREVENUE_COUNT(rs.getString("REVENUE_COUNT"));
                    item.setFINE(rs.getFloat("FINE"));
                    item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                    item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                    item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                    item.setRECEIVE_REF_NO(rs.getString("RECEIVE_REF_NO"));
                    item.setRECEIVE_DATE(rs.getString("RECEIVE_DATE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    item.setRevenueDetail(getRevenueDetail(rs.getInt("REVENUE_ID"),req.getOFFICE_CODE()));
                    item.setRevenueStaff(getRevenueStaff(rs.getInt("REVENUE_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public RevenueinsAllResponse RevenueinsAll(RevenueList req) {

        RevenueinsAllResponse res = new RevenueinsAllResponse();

        try {

            String REVENUE_ID = getSequences("SELECT OPS_REVENUE_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_REVENUE (" +
                            "REVENUE_ID," +
                            "DELIVERY_OFFICE_ID," +
                            "RECEIVE_OFFICE_ID," +
                            "REVENUE_CODE," +
                            "DELIVERY_OFFICE_CODE," +
                            "DELIVERY_OFFICE_NAME," +
                            "RECEIVE_OFFICE_CODE," +
                            "RECEIVE_OFFICE_NAME," +
                            "REVENUE_NO," +
                            "REVENUE_DATE," +
                            "REVENUE_STATUS," +
                            "REVENUE_COUNT," +
                            "FINE," +
                            "TREASURY_MONEY," +
                            "BRIBE_MONEY," +
                            "REWARD_MONEY," +
                            "RECEIVE_REF_NO," +
                            "RECEIVE_DATE," +
                            "IS_ACTIVE" +
                            ") values (" +
                            "'" + REVENUE_ID + "'," +
                            "'" + req.getDELIVERY_OFFICE_ID() + "'," +
                            "'" + req.getRECEIVE_OFFICE_ID() + "'," +
                            "'" + req.getREVENUE_CODE() + "'," +
                            "'" + req.getDELIVERY_OFFICE_CODE() + "'," +
                            "'" + req.getDELIVERY_OFFICE_NAME() + "'," +
                            "'" + req.getRECEIVE_OFFICE_CODE() + "'," +
                            "'" + req.getRECEIVE_OFFICE_NAME() + "'," +
                            "'" + req.getREVENUE_NO() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getREVENUE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getREVENUE_STATUS() + "'," +
                            "'" + req.getREVENUE_COUNT() + "'," +
                            "'" + req.getFINE() + "'," +
                            "'" + req.getTREASURY_MONEY() + "'," +
                            "'" + req.getBRIBE_MONEY() + "'," +
                            "'" + req.getREWARD_MONEY() + "'," +
                            "'" + req.getRECEIVE_REF_NO() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getRECEIVE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getIS_ACTIVE() + "')");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setREVENUE_ID(Integer.parseInt(REVENUE_ID));


            if (req.getRevenueStaff() != null) {
                log.info("[Sub] Size : " + req.getRevenueStaff().size());
                List<RevenueStaffResponse> list = new ArrayList<RevenueStaffResponse>();

                if (req.getRevenueStaff().size() > 0) {

                    for (RevenueStaff item : req.getRevenueStaff()) {

                        String STAFF_ID = getSequences("SELECT OPS_REVENUE_STAFF_SEQ.NEXTVAL FROM DUAL");
                        RevenueStaffResponse obj = new RevenueStaffResponse();
                        obj.setSTAFF_ID(Integer.parseInt(STAFF_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_REVENUE_STAFF (" +
                                        "STAFF_ID," +
                                        "REVENUE_ID," +
                                        "STAFF_REF_ID," +
                                        "TITLE_ID," +
                                        "STAFF_CODE," +
                                        "ID_CARD," +
                                        "STAFF_TYPE," +
                                        "TITLE_NAME_TH," +
                                        "TITLE_NAME_EN," +
                                        "TITLE_SHORT_NAME_TH," +
                                        "TITLE_SHORT_NAME_EN," +
                                        "FIRST_NAME," +
                                        "LAST_NAME," +
                                        "AGE," +
                                        "OPERATION_POS_CODE," +
                                        "OPREATION_POS_NAME," +
                                        "OPREATION_POS_LEVEL," +
                                        "OPERATION_POS_LEVEL_NAME," +
                                        "OPERATION_DEPT_CODE," +
                                        "OPERATION_DEPT_NAME," +
                                        "OPERATION_DEPT_LEVEL," +
                                        "OPERATION_UNDER_DEPT_CODE," +
                                        "OPERATION_UNDER_DEPT_NAME," +
                                        "OPERATION_UNDER_DEPT_LEVEL," +
                                        "OPERATION_WORK_DEPT_CODE," +
                                        "OPERATION_WORK_DEPT_NAME," +
                                        "OPERATION_WORK_DEPT_LEVEL," +
                                        "OPERATION_OFFICE_CODE," +
                                        "OPERATION_OFFICE_NAME," +
                                        "OPERATION_OFFICE_SHORT_NAME," +
                                        "MANAGEMENT_POS_CODE," +
                                        "MANAGEMENT_POS_NAME," +
                                        "MANAGEMENT_POS_LEVEL," +
                                        "MANAGEMENT_POS_LEVEL_NAME," +
                                        "MANAGEMENT_DEPT_CODE," +
                                        "MANAGEMENT_DEPT_NAME," +
                                        "MANAGEMENT_DEPT_LEVEL," +
                                        "MANAGEMENT_UNDER_DEPT_CODE," +
                                        "MANAGEMENT_UNDER_DEPT_NAME," +
                                        "MANAGEMENT_UNDER_DEPT_LEVEL," +
                                        "MANAGEMENT_WORK_DEPT_CODE," +
                                        "MANAGEMENT_WORK_DEPT_NAME," +
                                        "MANAGEMENT_WORK_DEPT_LEVEL," +
                                        "MANAGEMENT_OFFICE_CODE," +
                                        "MANAGEMENT_OFFICE_NAME," +
                                        "MANAGEMENT_OFFICE_SHORT_NAME," +
                                        "REPRESENT_POS_CODE," +
                                        "REPRESENT_POS_NAME," +
                                        "REPRESENT_POS_LEVEL," +
                                        "REPRESENT_POS_LEVEL_NAME," +
                                        "REPRESENT_DEPT_CODE," +
                                        "REPRESENT_DEPT_NAME," +
                                        "REPRESENT_DEPT_LEVEL," +
                                        "REPRESENT_UNDER_DEPT_CODE," +
                                        "REPRESENT_UNDER_DEPT_NAME," +
                                        "REPRESENT_UNDER_DEPT_LEVEL," +
                                        "REPRESENT_WORK_DEPT_CODE," +
                                        "REPRESENT_WORK_DEPT_NAME," +
                                        "REPRESENT_WORK_DEPT_LEVEL," +
                                        "REPRESENT_OFFICE_CODE," +
                                        "REPRESENT_OFFICE_NAME," +
                                        "REPRESENT_OFFICE_SHORT_NAME," +
                                        "STATUS," +
                                        "REMARK," +
                                        "CONTRIBUTOR_ID," +
                                        "IS_ACTIVE " +
                                        ") values (" +
                                        "'" + STAFF_ID + "'," +
                                        "'" + REVENUE_ID + "'," +
                                        "'" + item.getSTAFF_REF_ID() + "'," +
                                        "'" + item.getTITLE_ID() + "'," +
                                        "'" + item.getSTAFF_CODE() + "'," +
                                        "'" + item.getID_CARD() + "'," +
                                        "'" + item.getSTAFF_TYPE() + "'," +
                                        "'" + item.getTITLE_NAME_TH() + "'," +
                                        "'" + item.getTITLE_NAME_EN() + "'," +
                                        "'" + item.getTITLE_SHORT_NAME_TH() + "'," +
                                        "'" + item.getTITLE_SHORT_NAME_EN() + "'," +
                                        "'" + item.getFIRST_NAME() + "'," +
                                        "'" + item.getLAST_NAME() + "'," +
                                        "'" + item.getAGE() + "'," +
                                        "'" + item.getOPERATION_POS_CODE() + "'," +
                                        "'" + item.getOPREATION_POS_NAME() + "'," +
                                        "'" + item.getOPREATION_POS_LEVEL() + "'," +
                                        "'" + item.getOPERATION_POS_LEVEL_NAME() + "'," +
                                        "'" + item.getOPERATION_DEPT_CODE() + "'," +
                                        "'" + item.getOPERATION_DEPT_NAME() + "'," +
                                        "'" + item.getOPERATION_DEPT_LEVEL() + "'," +
                                        "'" + item.getOPERATION_UNDER_DEPT_CODE() + "'," +
                                        "'" + item.getOPERATION_UNDER_DEPT_NAME() + "'," +
                                        "'" + item.getOPERATION_UNDER_DEPT_LEVEL() + "'," +
                                        "'" + item.getOPERATION_WORK_DEPT_CODE() + "'," +
                                        "'" + item.getOPERATION_WORK_DEPT_NAME() + "'," +
                                        "'" + item.getOPERATION_WORK_DEPT_LEVEL() + "'," +
                                        "'" + item.getOPERATION_OFFICE_CODE() + "'," +
                                        "'" + item.getOPERATION_OFFICE_NAME() + "'," +
                                        "'" + item.getOPERATION_OFFICE_SHORT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_POS_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_POS_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_POS_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_POS_LEVEL_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_DEPT_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_DEPT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_DEPT_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_UNDER_DEPT_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_UNDER_DEPT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_UNDER_DEPT_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_WORK_DEPT_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_WORK_DEPT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_WORK_DEPT_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_OFFICE_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_OFFICE_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_OFFICE_SHORT_NAME() + "'," +
                                        "'" + item.getREPRESENT_POS_CODE() + "'," +
                                        "'" + item.getREPRESENT_POS_NAME() + "'," +
                                        "'" + item.getREPRESENT_POS_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_POS_LEVEL_NAME() + "'," +
                                        "'" + item.getREPRESENT_DEPT_CODE() + "'," +
                                        "'" + item.getREPRESENT_DEPT_NAME() + "'," +
                                        "'" + item.getREPRESENT_DEPT_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_UNDER_DEPT_CODE() + "'," +
                                        "'" + item.getREPRESENT_UNDER_DEPT_NAME() + "'," +
                                        "'" + item.getREPRESENT_UNDER_DEPT_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_WORK_DEPT_CODE() + "'," +
                                        "'" + item.getREPRESENT_WORK_DEPT_NAME() + "'," +
                                        "'" + item.getREPRESENT_WORK_DEPT_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_OFFICE_CODE() + "'," +
                                        "'" + item.getREPRESENT_OFFICE_NAME() + "'," +
                                        "'" + item.getREPRESENT_OFFICE_SHORT_NAME() + "'," +
                                        "'" + item.getSTATUS() + "'," +
                                        "'" + item.getREMARK() + "'," +
                                        "'" + item.getCONTRIBUTOR_ID() + "'," +
                                        "'" + item.getIS_ACTIVE() + "' )");
                        log.info("[SQL] Sub : " + sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});


                        list.add(obj);
                    }
                }
                res.setRevenueStaff(list);
            }

            if (req.getRevenueDetail() != null) {
                log.info("[Sub] Size : " + req.getRevenueDetail().size());
                List<RevenueDetailResponse> list = new ArrayList<RevenueDetailResponse>();

                if (req.getRevenueDetail().size() > 0) {
                    for (RevenueDetail item : req.getRevenueDetail()) {

                        String REVENUE_DETAIL_ID = getSequences("SELECT OPS_REVENUE_DETAIL_SEQ.NEXTVAL FROM DUAL");
                        RevenueDetailResponse obj = new RevenueDetailResponse();
                        obj.setREVENUE_DETAIL_ID(Integer.parseInt(REVENUE_DETAIL_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_REVENUE_DETAIL (" +
                                        "REVENUE_DETAIL_ID," +
                                        "REVENUE_ID," +
                                        "COMPARE_DETAIL_ID," +
                                        "REVENUE_STATUS," +
                                        "LAWSUIT_DETAIL_ID," +
                                        "PAYMENT_ID," +
                                        "IS_ACTIVE " +
                                        ")values (" +
                                        "'" + REVENUE_DETAIL_ID + "'," +
                                        "'" + REVENUE_ID + "'," +
                                        "'" + item.getCOMPARE_DETAIL_ID() + "'," +
                                        "'" + req.getREVENUE_STATUS() + "'," +
                                        "'" + item.getLAWSUIT_DETAIL_ID() + "'," +
                                        "'" + item.getPAYMENT_ID() + "'," +
                                        "'" + item.getIS_ACTIVE() + "' )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setRevenueDetail(list);
            }


            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setREVENUE_ID(0);
            res.setRevenueStaff(null);
            res.setRevenueDetail(null);
            return res;
        }
    }

    @Override
    public Boolean RevenueupdByCon(RevenueList req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_REVENUE SET "
                        + "DELIVERY_OFFICE_ID=  '" + req.getDELIVERY_OFFICE_ID() + "', "
                        + "RECEIVE_OFFICE_ID=  '" + req.getRECEIVE_OFFICE_ID() + "', "
                        + "REVENUE_CODE=  '" + req.getREVENUE_CODE() + "', "
                        + "DELIVERY_OFFICE_CODE=  '" + req.getDELIVERY_OFFICE_CODE() + "', "
                        + "DELIVERY_OFFICE_NAME=  '" + req.getDELIVERY_OFFICE_NAME() + "', "
                        + "RECEIVE_OFFICE_CODE=  '" + req.getRECEIVE_OFFICE_CODE() + "', "
                        + "RECEIVE_OFFICE_NAME=  '" + req.getRECEIVE_OFFICE_NAME() + "', "
                        + "REVENUE_NO=  '" + req.getREVENUE_NO() + "', "
                        + "REVENUE_DATE = TO_TIMESTAMP_TZ('" + req.getREVENUE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "REVENUE_STATUS=  '" + req.getREVENUE_STATUS() + "', "
                        + "REVENUE_COUNT=  '" + req.getREVENUE_COUNT() + "', "
                        + "FINE=  '" + req.getFINE() + "', "
                        + "TREASURY_MONEY=  '" + req.getTREASURY_MONEY() + "', "
                        + "BRIBE_MONEY=  '" + req.getBRIBE_MONEY() + "', "
                        + "REWARD_MONEY=  '" + req.getREWARD_MONEY() + "', "
                        + "RECEIVE_REF_NO=  '" + req.getRECEIVE_REF_NO() + "', "
                        + "RECEIVE_DATE = TO_TIMESTAMP_TZ('" + req.getRECEIVE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE REVENUE_ID = '" + req.getREVENUE_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        if (req.getRevenueDetail() != null) {
            log.info("[Sub] Size : " + req.getRevenueDetail().size());

            if (req.getRevenueDetail().size() > 0) {
                for (RevenueDetail item : req.getRevenueDetail()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_REVENUE_DETAIL "
                                    + "SET "
                                    + "REVENUE_ID=  '"+item.getREVENUE_ID()+"', "
                                    + "COMPARE_DETAIL_ID=  '"+item.getCOMPARE_DETAIL_ID()+"', "
                                    + "REVENUE_STATUS=  '"+item.getREVENUE_STATUS()+"', "
                                    + "IS_ACTIVE=  '"+item.getIS_ACTIVE()+"', "
                                    + "LAWSUIT_DETAIL_ID=  '"+item.getLAWSUIT_DETAIL_ID()+"', "
                                    + "PAYMENT_ID=  '"+item.getPAYMENT_ID()+"' "
                                    + "WHERE REVENUE_DETAIL_ID = '" + item.getREVENUE_DETAIL_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                }
            }
        }

        if (req.getRevenueStaff() != null) {
            log.info("[Sub] Size : " + req.getRevenueStaff().size());

            if (req.getRevenueStaff().size() > 0) {
                for (RevenueStaff item : req.getRevenueStaff()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_REVENUE_STAFF "
                                    + "SET "
                                    + "REVENUE_ID=  '"+item.getREVENUE_ID()+"', "
                                    + "STAFF_REF_ID=  '"+item.getSTAFF_REF_ID()+"', "
                                    + "TITLE_ID=  '"+item.getTITLE_ID()+"', "
                                    + "STAFF_CODE=  '"+item.getSTAFF_CODE()+"', "
                                    + "ID_CARD=  '"+item.getID_CARD()+"', "
                                    + "STAFF_TYPE=  '"+item.getSTAFF_TYPE()+"', "
                                    + "TITLE_NAME_TH=  '"+item.getTITLE_NAME_TH()+"', "
                                    + "TITLE_NAME_EN=  '"+item.getTITLE_NAME_EN()+"', "
                                    + "TITLE_SHORT_NAME_TH=  '"+item.getTITLE_SHORT_NAME_TH()+"', "
                                    + "TITLE_SHORT_NAME_EN=  '"+item.getTITLE_SHORT_NAME_EN()+"', "
                                    + "FIRST_NAME=  '"+item.getFIRST_NAME()+"', "
                                    + "LAST_NAME=  '"+item.getLAST_NAME()+"', "
                                    + "AGE=  '"+item.getAGE()+"', "
                                    + "OPERATION_POS_CODE=  '"+item.getOPERATION_POS_CODE()+"', "
                                    + "OPREATION_POS_NAME=  '"+item.getOPREATION_POS_NAME()+"', "
                                    + "OPREATION_POS_LEVEL=  '"+item.getOPREATION_POS_LEVEL()+"', "
                                    + "OPERATION_POS_LEVEL_NAME=  '"+item.getOPERATION_POS_LEVEL_NAME()+"', "
                                    + "OPERATION_DEPT_CODE=  '"+item.getOPERATION_DEPT_CODE()+"', "
                                    + "OPERATION_DEPT_NAME=  '"+item.getOPERATION_DEPT_NAME()+"', "
                                    + "OPERATION_DEPT_LEVEL=  '"+item.getOPERATION_DEPT_LEVEL()+"', "
                                    + "OPERATION_UNDER_DEPT_CODE=  '"+item.getOPERATION_UNDER_DEPT_CODE()+"', "
                                    + "OPERATION_UNDER_DEPT_NAME=  '"+item.getOPERATION_UNDER_DEPT_NAME()+"', "
                                    + "OPERATION_UNDER_DEPT_LEVEL=  '"+item.getOPERATION_UNDER_DEPT_LEVEL()+"', "
                                    + "OPERATION_WORK_DEPT_CODE=  '"+item.getOPERATION_WORK_DEPT_CODE()+"', "
                                    + "OPERATION_WORK_DEPT_NAME=  '"+item.getOPERATION_WORK_DEPT_NAME()+"', "
                                    + "OPERATION_WORK_DEPT_LEVEL=  '"+item.getOPERATION_WORK_DEPT_LEVEL()+"', "
                                    + "OPERATION_OFFICE_CODE=  '"+item.getOPERATION_OFFICE_CODE()+"', "
                                    + "OPERATION_OFFICE_NAME=  '"+item.getOPERATION_OFFICE_NAME()+"', "
                                    + "OPERATION_OFFICE_SHORT_NAME=  '"+item.getOPERATION_OFFICE_SHORT_NAME()+"', "
                                    + "MANAGEMENT_POS_CODE=  '"+item.getMANAGEMENT_POS_CODE()+"', "
                                    + "MANAGEMENT_POS_NAME=  '"+item.getMANAGEMENT_POS_NAME()+"', "
                                    + "MANAGEMENT_POS_LEVEL=  '"+item.getMANAGEMENT_POS_LEVEL()+"', "
                                    + "MANAGEMENT_POS_LEVEL_NAME=  '"+item.getMANAGEMENT_POS_LEVEL_NAME()+"', "
                                    + "MANAGEMENT_DEPT_CODE=  '"+item.getMANAGEMENT_DEPT_CODE()+"', "
                                    + "MANAGEMENT_DEPT_NAME=  '"+item.getMANAGEMENT_DEPT_NAME()+"', "
                                    + "MANAGEMENT_DEPT_LEVEL=  '"+item.getMANAGEMENT_DEPT_LEVEL()+"', "
                                    + "MANAGEMENT_UNDER_DEPT_CODE=  '"+item.getMANAGEMENT_UNDER_DEPT_CODE()+"', "
                                    + "MANAGEMENT_UNDER_DEPT_NAME=  '"+item.getMANAGEMENT_UNDER_DEPT_NAME()+"', "
                                    + "MANAGEMENT_UNDER_DEPT_LEVEL=  '"+item.getMANAGEMENT_UNDER_DEPT_LEVEL()+"', "
                                    + "MANAGEMENT_WORK_DEPT_CODE=  '"+item.getMANAGEMENT_WORK_DEPT_CODE()+"', "
                                    + "MANAGEMENT_WORK_DEPT_NAME=  '"+item.getMANAGEMENT_WORK_DEPT_NAME()+"', "
                                    + "MANAGEMENT_WORK_DEPT_LEVEL=  '"+item.getMANAGEMENT_WORK_DEPT_LEVEL()+"', "
                                    + "MANAGEMENT_OFFICE_CODE=  '"+item.getMANAGEMENT_OFFICE_CODE()+"', "
                                    + "MANAGEMENT_OFFICE_NAME=  '"+item.getMANAGEMENT_OFFICE_NAME()+"', "
                                    + "MANAGEMENT_OFFICE_SHORT_NAME=  '"+item.getMANAGEMENT_OFFICE_SHORT_NAME()+"', "
                                    + "REPRESENT_POS_CODE=  '"+item.getREPRESENT_POS_CODE()+"', "
                                    + "REPRESENT_POS_NAME=  '"+item.getREPRESENT_POS_NAME()+"', "
                                    + "REPRESENT_POS_LEVEL=  '"+item.getREPRESENT_POS_LEVEL()+"', "
                                    + "REPRESENT_POS_LEVEL_NAME=  '"+item.getREPRESENT_POS_LEVEL_NAME()+"', "
                                    + "REPRESENT_DEPT_CODE=  '"+item.getREPRESENT_DEPT_CODE()+"', "
                                    + "REPRESENT_DEPT_NAME=  '"+item.getREPRESENT_DEPT_NAME()+"', "
                                    + "REPRESENT_DEPT_LEVEL=  '"+item.getREPRESENT_DEPT_LEVEL()+"', "
                                    + "REPRESENT_UNDER_DEPT_CODE=  '"+item.getREPRESENT_UNDER_DEPT_CODE()+"', "
                                    + "REPRESENT_UNDER_DEPT_NAME=  '"+item.getREPRESENT_UNDER_DEPT_NAME()+"', "
                                    + "REPRESENT_UNDER_DEPT_LEVEL=  '"+item.getREPRESENT_UNDER_DEPT_LEVEL()+"', "
                                    + "REPRESENT_WORK_DEPT_CODE=  '"+item.getREPRESENT_WORK_DEPT_CODE()+"', "
                                    + "REPRESENT_WORK_DEPT_NAME=  '"+item.getREPRESENT_WORK_DEPT_NAME()+"', "
                                    + "REPRESENT_WORK_DEPT_LEVEL=  '"+item.getREPRESENT_WORK_DEPT_LEVEL()+"', "
                                    + "REPRESENT_OFFICE_CODE=  '"+item.getREPRESENT_OFFICE_CODE()+"', "
                                    + "REPRESENT_OFFICE_NAME=  '"+item.getREPRESENT_OFFICE_NAME()+"', "
                                    + "REPRESENT_OFFICE_SHORT_NAME=  '"+item.getREPRESENT_OFFICE_SHORT_NAME()+"', "
                                    + "STATUS=  '"+item.getSTATUS()+"', "
                                    + "REMARK=  '"+item.getREMARK()+"', "
                                    + "CONTRIBUTOR_ID=  '"+item.getCONTRIBUTOR_ID()+"', "
                                    + "IS_ACTIVE=  '"+item.getIS_ACTIVE()+"' "
                                    + "WHERE STAFF_ID = '" + item.getSTAFF_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                }
            }
        }

        return true;
    }

    @Override
    public Boolean RevenueupdDelete(RevenueupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_REVENUE SET IS_ACTIVE = '0' WHERE REVENUE_ID = '"+req.getREVENUE_ID()+"' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_REVENUE_DETAIL SET IS_ACTIVE = '0' WHERE REVENUE_ID = '"+req.getREVENUE_ID()+"' ");
        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_REVENUE_STAFF SET IS_ACTIVE = '0' WHERE REVENUE_ID = '"+req.getREVENUE_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[] {});
        return true;
    }

    @Override
    public Boolean RevenuePaymentupdByCon(RevenuePaymentupdByConReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_PAYMENT SET IS_REVENUE = '"+req.getIS_REVENUE()+"' WHERE PAYMENT_ID = '"+req.getPAYMENT_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        return true;
    }

    @Override
    public List<NewRevenueList> RevenueListgetByKeyword(final RevenuegetByKeywordReq req) {
        // TODO Auto-generated method stub

        String str = "";

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {


                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND " +
                            "   ( " +
                            "   SUBSTR(OPS_REVENUE.DELIVERY_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "   )";
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND " +
                            "   ( " +
                            "   SUBSTR(OPS_REVENUE.DELIVERY_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "   )";
                }else {
                    str = " AND" +
                            "   (" +
                            "   OPS_REVENUE.DELIVERY_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "   OR OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "   OR OPS_REVENUE_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "   OR OPS_REVENUE_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "   )" ;
                }

            }

        }

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("    select DISTINCT " +
                        "    OPS_REVENUE.*" +
                        "    from" +
                        "    OPS_REVENUE" +
                        "    INNER JOIN OPS_REVENUE_DETAIL ON OPS_REVENUE_DETAIL.REVENUE_ID = OPS_REVENUE.REVENUE_ID and OPS_REVENUE_DETAIL.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_REVENUE_STAFF ON OPS_REVENUE.REVENUE_ID = OPS_REVENUE_STAFF.REVENUE_ID AND OPS_REVENUE_STAFF.CONTRIBUTOR_ID IN (36,37) " +
                        "    LEFT JOIN OPS_PAYMENT ON OPS_PAYMENT.PAYMENT_ID = OPS_REVENUE_DETAIL.PAYMENT_ID  and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_LAWSUIT_DETAIL ON OPS_PAYMENT.LAWSUIT_DETAIL_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID AND OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_COMPARE_DETAIL ON OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID = OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID AND OPS_COMPARE_DETAIL.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID AND OPS_COMPARE_MAPPING.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_COMPARE ON OPS_COMPARE_MAPPING.COMPARE_ID = OPS_COMPARE.COMPARE_ID AND OPS_COMPARE.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_LAWSUIT " +
                        "        ON ( OPS_COMPARE.LAWSUIT_ID   = (" +
                        "            CASE WHEN OPS_PAYMENT.COMPARE_DETAIL_ID !=0 THEN  OPS_LAWSUIT.LAWSUIT_ID end) " +
                        "            and OPS_PAYMENT.IS_ACTIVE = 1 " +
                        "        or OPS_LAWSUIT_DETAIL.LAWSUIT_ID =(   " +
                        "            CASE WHEN OPS_PAYMENT.LAWSUIT_DETAIL_ID !=0 THEN OPS_LAWSUIT.LAWSUIT_ID end )" +
                        "            and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "    ) " +
                        "    WHERE OPS_REVENUE.IS_ACTIVE = 1" +
                        "    AND OPS_REVENUE_STAFF.IS_ACTIVE = 1" +
                        "    AND OPS_REVENUE_DETAIL.IS_ACTIVE = 1" +
                        "    AND OPS_REVENUE.REVENUE_STATUS IN (0,1,2) " +
                        "    AND " +
                        "    (" +
                        "      LOWER(OPS_REVENUE.REVENUE_CODE) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(DELIVERY_OFFICE_NAME)LIKE LOWER ('%"+req.getTEXT_SEARCH()+"%') " +
                        "      OR LOWER(OPS_REVENUE_STAFF.TITLE_NAME_TH||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_REVENUE_STAFF.TITLE_NAME_EN||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_REVENUE_STAFF.TITLE_SHORT_NAME_TH||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_REVENUE_STAFF.TITLE_SHORT_NAME_EN||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))  " +
                        "    )" +str+
                        "    order by  OPS_REVENUE.REVENUE_DATE DESC");

        log.info("[SQL]  : " + sqlBuilderDetail.toString());
        System.out.println("[SQL] [AdjustCompareListgetByKeyword]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<NewRevenueList> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public NewRevenueList mapRow(ResultSet rs, int rowNum) throws SQLException {
                NewRevenueList item = new NewRevenueList();
                item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                item.setDELIVERY_OFFICE_ID(rs.getInt("DELIVERY_OFFICE_ID"));
                item.setRECEIVE_OFFICE_ID(rs.getInt("RECEIVE_OFFICE_ID"));
                item.setREVENUE_CODE(rs.getString("REVENUE_CODE"));
                item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                item.setRECEIVE_OFFICE_CODE(rs.getString("RECEIVE_OFFICE_CODE"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setREVENUE_NO(rs.getString("REVENUE_NO"));
                item.setREVENUE_DATE(rs.getString("REVENUE_DATE"));
                item.setREVENUE_STATUS(rs.getInt("REVENUE_STATUS"));
                item.setREVENUE_COUNT(rs.getString("REVENUE_COUNT"));
                item.setFINE(rs.getFloat("FINE"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                item.setRECEIVE_REF_NO(rs.getString("RECEIVE_REF_NO"));
                item.setRECEIVE_DATE(rs.getString("RECEIVE_DATE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                System.out.println(rs.getInt("REVENUE_ID"));
                item.setRevenueStaff(getRevenueStaff(rs.getInt("REVENUE_ID")));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<NewRevenueList> RevenueListgetByConAdv(RevenuegetByConAdvReq req) {
        // TODO Auto-generated method stub

        String str = "";
        String tempDateFrom   = "";
        String tempDateTo = "";


        if(!"".equals(req.getREVENUE_DATE_FROM())) {
            tempDateFrom = String.format("%s %s", req.getREVENUE_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getREVENUE_DATE_TO())) {
            tempDateTo = String.format("%s %s", req.getREVENUE_DATE_TO(),Pattern.TIME_TO);
        }

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {


                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND " +
                            "   ( " +
                            "   SUBSTR(OPS_REVENUE.DELIVERY_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "   )";
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND " +
                            "   ( " +
                            "   SUBSTR(OPS_REVENUE.DELIVERY_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "   OR SUBSTR(OPS_REVENUE_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "   )";
                }else {
                    str = " AND" +
                            "   (" +
                            "   OPS_REVENUE.DELIVERY_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "   OR OPS_REVENUE_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "   OR OPS_REVENUE_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "   OR OPS_REVENUE_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "   )" ;
                }

            }

        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select DISTINCT " +
                        "    OPS_REVENUE.*" +
                        "    from" +
                        "    OPS_REVENUE" +
                        "    INNER JOIN OPS_REVENUE_DETAIL ON OPS_REVENUE_DETAIL.REVENUE_ID = OPS_REVENUE.REVENUE_ID and OPS_REVENUE_DETAIL.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_REVENUE_STAFF ON OPS_REVENUE.REVENUE_ID = OPS_REVENUE_STAFF.REVENUE_ID AND OPS_REVENUE_STAFF.CONTRIBUTOR_ID IN (36,37) " +
                        "    LEFT JOIN OPS_PAYMENT ON OPS_PAYMENT.PAYMENT_ID = OPS_REVENUE_DETAIL.PAYMENT_ID  and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_LAWSUIT_DETAIL ON OPS_PAYMENT.LAWSUIT_DETAIL_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID AND OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_COMPARE_DETAIL ON OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID = OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID AND OPS_COMPARE_DETAIL.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID AND OPS_COMPARE_MAPPING.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_COMPARE ON OPS_COMPARE_MAPPING.COMPARE_ID = OPS_COMPARE.COMPARE_ID AND OPS_COMPARE.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_LAWSUIT " +
                        "        ON ( OPS_COMPARE.LAWSUIT_ID   = (" +
                        "            CASE WHEN OPS_PAYMENT.COMPARE_DETAIL_ID !=0 THEN  OPS_LAWSUIT.LAWSUIT_ID end) " +
                        "            and OPS_PAYMENT.IS_ACTIVE = 1 " +
                        "        or OPS_LAWSUIT_DETAIL.LAWSUIT_ID =(   " +
                        "            CASE WHEN OPS_PAYMENT.LAWSUIT_DETAIL_ID !=0 THEN OPS_LAWSUIT.LAWSUIT_ID end )" +
                        "            and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "    ) " +
                        "    WHERE OPS_REVENUE.IS_ACTIVE = 1" +
                        "    AND OPS_REVENUE_STAFF.IS_ACTIVE = 1" +
                        "    AND OPS_REVENUE_DETAIL.IS_ACTIVE = 1" +
                        "    AND OPS_REVENUE.REVENUE_STATUS IN (0,1,2) "+str );

        if(req.getREVENUE_CODE() != null && !"".equals(req.getREVENUE_CODE())) {
            sqlBuilder.append(" AND LOWER(OPS_REVENUE.REVENUE_CODE) like lower(replace ('%"+req.getREVENUE_CODE()+"%',' ','')) ");
        }

        if(req.getLAWSUILT_IS_OUTSIDE() != null && !"".equals(req.getLAWSUILT_IS_OUTSIDE())) {
            sqlBuilder.append(" AND OPS_LAWSUIT.IS_OUTSIDE = "+req.getLAWSUILT_IS_OUTSIDE()+" ");
        }

        if(req.getREVENUE_DATE_FROM() != null && !"".equals(req.getREVENUE_DATE_FROM()) && req.getREVENUE_DATE_TO() != null && !"".equals(req.getREVENUE_DATE_TO())) {
            sqlBuilder.append(" AND OPS_REVENUE.REVENUE_DATE BETWEEN  to_date(nvl('"+tempDateFrom+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempDateTo+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getDELIVERY_OFFICE_NAME() != null && !"".equals(req.getDELIVERY_OFFICE_NAME())) {
            sqlBuilder.append(" AND LOWER(DELIVERY_OFFICE_NAME)LIKE LOWER  ('%"+req.getDELIVERY_OFFICE_NAME()+"%') ");
        }

        if(req.getSTAFF_NAME_SEND() != null && !"".equals(req.getSTAFF_NAME_SEND())) {
            sqlBuilder.append("    AND " +
                    "    (" +
                    "      LOWER(OPS_REVENUE_STAFF.TITLE_NAME_TH||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME_SEND()+"%',' ',''))" +
                    "      OR LOWER(OPS_REVENUE_STAFF.TITLE_NAME_EN||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME_SEND()+"%',' ',''))" +
                    "      OR LOWER(OPS_REVENUE_STAFF.TITLE_SHORT_NAME_TH||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME_SEND()+"%',' ',''))" +
                    "      OR LOWER(OPS_REVENUE_STAFF.TITLE_SHORT_NAME_EN||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME_SEND()+"%',' ',''))" +
                    "      AND OPS_REVENUE_STAFF.CONTRIBUTOR_ID = '36'" +
                    "    )");
        }

        if(req.getSTAFF_NAME_SIGN() != null && !"".equals(req.getSTAFF_NAME_SIGN())) {
            sqlBuilder.append("    AND " +
                    "    (" +
                    "      LOWER(OPS_REVENUE_STAFF.TITLE_NAME_TH||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME_SIGN()+"%',' ',''))" +
                    "      OR LOWER(OPS_REVENUE_STAFF.TITLE_NAME_EN||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME_SIGN()+"%',' ',''))" +
                    "      OR LOWER(OPS_REVENUE_STAFF.TITLE_SHORT_NAME_TH||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME_SIGN()+"%',' ',''))" +
                    "      OR LOWER(OPS_REVENUE_STAFF.TITLE_SHORT_NAME_EN||OPS_REVENUE_STAFF.FIRST_NAME||OPS_REVENUE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME_SIGN()+"%',' ',''))" +
                    "      AND OPS_REVENUE_STAFF.CONTRIBUTOR_ID = '37'" +
                    "    )");
        }



        if(req.getCOMPARE_NO() != null && !"".equals(req.getCOMPARE_NO())) {
            sqlBuilder.append(" AND OPS_LAWSUIT.COMPARE_NO ='"+req.getCOMPARE_NO()+"' ");
        }

        if(req.getCOMPARE_NO_YEAR() != null && !"".equals(req.getCOMPARE_NO_YEAR())) {
            sqlBuilder.append(" AND (to_number(TO_CHAR(OPS_LAWSUIT.COMPARE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >='"+req.getCOMPARE_NO_YEAR()+"' AND  to_number(TO_CHAR(OPS_LAWSUIT.COMPARE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getCOMPARE_NO_YEAR()+"')");
        }

        if(req.getLAWSUIT_NO() != null && !"".equals(req.getLAWSUIT_NO())) {
            sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_NO ='"+req.getLAWSUIT_NO()+"' ");
        }

        if(req.getLAWSUIT_NO_YEAR() != null && !"".equals(req.getLAWSUIT_NO_YEAR())) {
            sqlBuilder.append(" AND (to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >='"+req.getLAWSUIT_NO_YEAR()+"' AND  to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getCOMPARE_NO_YEAR()+"')");
        }

        if(req.getCOMPARE_IS_OUTSIDE() != null && !"".equals(req.getCOMPARE_IS_OUTSIDE())) {
            sqlBuilder.append(" AND OPS_COMPARE.IS_OUTSIDE = "+req.getCOMPARE_IS_OUTSIDE()+" ");
        }

        if(req.getREVENUE_STATUS() != null && !"".equals(req.getREVENUE_STATUS())) {
            sqlBuilder.append("  and OPS_REVENUE.REVENUE_STATUS = '"+req.getREVENUE_STATUS()+"' ");
        }


        System.out.println("[SQL] [AdjustCompareListgetByKeyword]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NewRevenueList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NewRevenueList mapRow(ResultSet rs, int rowNum) throws SQLException {
                NewRevenueList item = new NewRevenueList();
                item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                item.setDELIVERY_OFFICE_ID(rs.getInt("DELIVERY_OFFICE_ID"));
                item.setRECEIVE_OFFICE_ID(rs.getInt("RECEIVE_OFFICE_ID"));
                item.setREVENUE_CODE(rs.getString("REVENUE_CODE"));
                item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                item.setRECEIVE_OFFICE_CODE(rs.getString("RECEIVE_OFFICE_CODE"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setREVENUE_NO(rs.getString("REVENUE_NO"));
                item.setREVENUE_DATE(rs.getString("REVENUE_DATE"));
                item.setREVENUE_STATUS(rs.getInt("REVENUE_STATUS"));
                item.setREVENUE_COUNT(rs.getString("REVENUE_COUNT"));
                item.setFINE(rs.getFloat("FINE"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                item.setRECEIVE_REF_NO(rs.getString("RECEIVE_REF_NO"));
                item.setRECEIVE_DATE(rs.getString("RECEIVE_DATE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setRevenueStaff(getRevenueStaff(rs.getInt("REVENUE_ID")));

                return item;
            }
        });
        return dataList;
    }
}
