package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.revenue.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RevenueExt {

    private static final Logger log = LoggerFactory.getLogger(RevenueExt.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected String getSequences(String strSql) {
        log.info("[SQL]  : " + strSql);
        return getJdbcTemplate().queryForObject(strSql, String.class);
    }

    //=================== RevenueStaff

    protected List<RevenueStaff> getRevenueStaff(int REVENUE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
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
                        "from OPS_REVENUE_STAFF  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and REVENUE_ID = '"+REVENUE_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueStaff item = new RevenueStaff();
                item.setSTAFF_ID(rs.getInt("STAFF_ID"));
                item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                item.setSTAFF_REF_ID(rs.getInt("STAFF_REF_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setSTAFF_CODE(rs.getString("STAFF_CODE"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setSTAFF_TYPE(rs.getInt("STAFF_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setAGE(rs.getInt("AGE"));
                item.setOPERATION_POS_CODE(rs.getString("OPERATION_POS_CODE"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setOPREATION_POS_LEVEL(rs.getString("OPREATION_POS_LEVEL"));
                item.setOPERATION_POS_LEVEL_NAME(rs.getString("OPERATION_POS_LEVEL_NAME"));
                item.setOPERATION_DEPT_CODE(rs.getString("OPERATION_DEPT_CODE"));
                item.setOPERATION_DEPT_NAME(rs.getString("OPERATION_DEPT_NAME"));
                item.setOPERATION_DEPT_LEVEL(rs.getInt("OPERATION_DEPT_LEVEL"));
                item.setOPERATION_UNDER_DEPT_CODE(rs.getString("OPERATION_UNDER_DEPT_CODE"));
                item.setOPERATION_UNDER_DEPT_NAME(rs.getString("OPERATION_UNDER_DEPT_NAME"));
                item.setOPERATION_UNDER_DEPT_LEVEL(rs.getInt("OPERATION_UNDER_DEPT_LEVEL"));
                item.setOPERATION_WORK_DEPT_CODE(rs.getString("OPERATION_WORK_DEPT_CODE"));
                item.setOPERATION_WORK_DEPT_NAME(rs.getString("OPERATION_WORK_DEPT_NAME"));
                item.setOPERATION_WORK_DEPT_LEVEL(rs.getInt("OPERATION_WORK_DEPT_LEVEL"));
                item.setOPERATION_OFFICE_CODE(rs.getString("OPERATION_OFFICE_CODE"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                item.setMANAGEMENT_POS_CODE(rs.getString("MANAGEMENT_POS_CODE"));
                item.setMANAGEMENT_POS_NAME(rs.getString("MANAGEMENT_POS_NAME"));
                item.setMANAGEMENT_POS_LEVEL(rs.getString("MANAGEMENT_POS_LEVEL"));
                item.setMANAGEMENT_POS_LEVEL_NAME(rs.getString("MANAGEMENT_POS_LEVEL_NAME"));
                item.setMANAGEMENT_DEPT_CODE(rs.getString("MANAGEMENT_DEPT_CODE"));
                item.setMANAGEMENT_DEPT_NAME(rs.getString("MANAGEMENT_DEPT_NAME"));
                item.setMANAGEMENT_DEPT_LEVEL(rs.getInt("MANAGEMENT_DEPT_LEVEL"));
                item.setMANAGEMENT_UNDER_DEPT_CODE(rs.getString("MANAGEMENT_UNDER_DEPT_CODE"));
                item.setMANAGEMENT_UNDER_DEPT_NAME(rs.getString("MANAGEMENT_UNDER_DEPT_NAME"));
                item.setMANAGEMENT_UNDER_DEPT_LEVEL(rs.getInt("MANAGEMENT_UNDER_DEPT_LEVEL"));
                item.setMANAGEMENT_WORK_DEPT_CODE(rs.getString("MANAGEMENT_WORK_DEPT_CODE"));
                item.setMANAGEMENT_WORK_DEPT_NAME(rs.getString("MANAGEMENT_WORK_DEPT_NAME"));
                item.setMANAGEMENT_WORK_DEPT_LEVEL(rs.getInt("MANAGEMENT_WORK_DEPT_LEVEL"));
                item.setMANAGEMENT_OFFICE_CODE(rs.getString("MANAGEMENT_OFFICE_CODE"));
                item.setMANAGEMENT_OFFICE_NAME(rs.getString("MANAGEMENT_OFFICE_NAME"));
                item.setMANAGEMENT_OFFICE_SHORT_NAME(rs.getString("MANAGEMENT_OFFICE_SHORT_NAME"));
                item.setREPRESENT_POS_CODE(rs.getString("REPRESENT_POS_CODE"));
                item.setREPRESENT_POS_NAME(rs.getString("REPRESENT_POS_NAME"));
                item.setREPRESENT_POS_LEVEL(rs.getString("REPRESENT_POS_LEVEL"));
                item.setREPRESENT_POS_LEVEL_NAME(rs.getString("REPRESENT_POS_LEVEL_NAME"));
                item.setREPRESENT_DEPT_CODE(rs.getString("REPRESENT_DEPT_CODE"));
                item.setREPRESENT_DEPT_NAME(rs.getString("REPRESENT_DEPT_NAME"));
                item.setREPRESENT_DEPT_LEVEL(rs.getInt("REPRESENT_DEPT_LEVEL"));
                item.setREPRESENT_UNDER_DEPT_CODE(rs.getString("REPRESENT_UNDER_DEPT_CODE"));
                item.setREPRESENT_UNDER_DEPT_NAME(rs.getString("REPRESENT_UNDER_DEPT_NAME"));
                item.setREPRESENT_UNDER_DEPT_LEVEL(rs.getInt("REPRESENT_UNDER_DEPT_LEVEL"));
                item.setREPRESENT_WORK_DEPT_CODE(rs.getString("REPRESENT_WORK_DEPT_CODE"));
                item.setREPRESENT_WORK_DEPT_NAME(rs.getString("REPRESENT_WORK_DEPT_NAME"));
                item.setREPRESENT_WORK_DEPT_LEVEL(rs.getInt("REPRESENT_WORK_DEPT_LEVEL"));
                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setREPRESENT_OFFICE_SHORT_NAME(rs.getString("REPRESENT_OFFICE_SHORT_NAME"));
                item.setSTATUS(rs.getInt("STATUS"));
                item.setREMARK(rs.getString("REMARK"));
                item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;

    }

    //=================== RevenueDetail

    protected List<RevenueDetail> getRevenueDetail(int REVENUE_ID, final String OFFICE_CODE) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "REVENUE_DETAIL_ID," +
                        "REVENUE_ID," +
                        "COMPARE_DETAIL_ID," +
                        "REVENUE_STATUS," +
                        "LAWSUIT_DETAIL_ID," +
                        "PAYMENT_ID," +
                        "IS_ACTIVE " +
                        " from OPS_REVENUE_DETAIL  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and REVENUE_ID = '"+REVENUE_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueDetail item = new RevenueDetail();
                item.setREVENUE_DETAIL_ID(rs.getInt("REVENUE_DETAIL_ID"));
                item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setREVENUE_STATUS(rs.getInt("REVENUE_STATUS"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setRevenueCompareDetail(getRevenueCompareDetailByCompareDetailId(rs.getInt("COMPARE_DETAIL_ID"),OFFICE_CODE));

                return item;
            }
        });

        return dataList;

    }

    protected List<RevenueCompareStaff> getRevenueCompareStaff(int COMPARE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "STAFF_ID," +
                        "COMPARE_ID," +
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
                        "from OPS_COMPARE_STAFF  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and COMPARE_ID = '"+COMPARE_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCompareStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueCompareStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueCompareStaff item = new RevenueCompareStaff();
                item.setSTAFF_ID(rs.getInt("STAFF_ID"));
                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setSTAFF_REF_ID(rs.getInt("STAFF_REF_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setSTAFF_CODE(rs.getString("STAFF_CODE"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setSTAFF_TYPE(rs.getInt("STAFF_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setAGE(rs.getInt("AGE"));
                item.setOPERATION_POS_CODE(rs.getString("OPERATION_POS_CODE"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setOPREATION_POS_LEVEL(rs.getString("OPREATION_POS_LEVEL"));
                item.setOPERATION_POS_LEVEL_NAME(rs.getString("OPERATION_POS_LEVEL_NAME"));
                item.setOPERATION_DEPT_CODE(rs.getString("OPERATION_DEPT_CODE"));
                item.setOPERATION_DEPT_NAME(rs.getString("OPERATION_DEPT_NAME"));
                item.setOPERATION_DEPT_LEVEL(rs.getInt("OPERATION_DEPT_LEVEL"));
                item.setOPERATION_UNDER_DEPT_CODE(rs.getString("OPERATION_UNDER_DEPT_CODE"));
                item.setOPERATION_UNDER_DEPT_NAME(rs.getString("OPERATION_UNDER_DEPT_NAME"));
                item.setOPERATION_UNDER_DEPT_LEVEL(rs.getInt("OPERATION_UNDER_DEPT_LEVEL"));
                item.setOPERATION_WORK_DEPT_CODE(rs.getString("OPERATION_WORK_DEPT_CODE"));
                item.setOPERATION_WORK_DEPT_NAME(rs.getString("OPERATION_WORK_DEPT_NAME"));
                item.setOPERATION_WORK_DEPT_LEVEL(rs.getInt("OPERATION_WORK_DEPT_LEVEL"));
                item.setOPERATION_OFFICE_CODE(rs.getString("OPERATION_OFFICE_CODE"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                item.setMANAGEMENT_POS_CODE(rs.getString("MANAGEMENT_POS_CODE"));
                item.setMANAGEMENT_POS_NAME(rs.getString("MANAGEMENT_POS_NAME"));
                item.setMANAGEMENT_POS_LEVEL(rs.getString("MANAGEMENT_POS_LEVEL"));
                item.setMANAGEMENT_POS_LEVEL_NAME(rs.getString("MANAGEMENT_POS_LEVEL_NAME"));
                item.setMANAGEMENT_DEPT_CODE(rs.getString("MANAGEMENT_DEPT_CODE"));
                item.setMANAGEMENT_DEPT_NAME(rs.getString("MANAGEMENT_DEPT_NAME"));
                item.setMANAGEMENT_DEPT_LEVEL(rs.getInt("MANAGEMENT_DEPT_LEVEL"));
                item.setMANAGEMENT_UNDER_DEPT_CODE(rs.getString("MANAGEMENT_UNDER_DEPT_CODE"));
                item.setMANAGEMENT_UNDER_DEPT_NAME(rs.getString("MANAGEMENT_UNDER_DEPT_NAME"));
                item.setMANAGEMENT_UNDER_DEPT_LEVEL(rs.getInt("MANAGEMENT_UNDER_DEPT_LEVEL"));
                item.setMANAGEMENT_WORK_DEPT_CODE(rs.getString("MANAGEMENT_WORK_DEPT_CODE"));
                item.setMANAGEMENT_WORK_DEPT_NAME(rs.getString("MANAGEMENT_WORK_DEPT_NAME"));
                item.setMANAGEMENT_WORK_DEPT_LEVEL(rs.getInt("MANAGEMENT_WORK_DEPT_LEVEL"));
                item.setMANAGEMENT_OFFICE_CODE(rs.getString("MANAGEMENT_OFFICE_CODE"));
                item.setMANAGEMENT_OFFICE_NAME(rs.getString("MANAGEMENT_OFFICE_NAME"));
                item.setMANAGEMENT_OFFICE_SHORT_NAME(rs.getString("MANAGEMENT_OFFICE_SHORT_NAME"));
                item.setREPRESENT_POS_CODE(rs.getString("REPRESENT_POS_CODE"));
                item.setREPRESENT_POS_NAME(rs.getString("REPRESENT_POS_NAME"));
                item.setREPRESENT_POS_LEVEL(rs.getString("REPRESENT_POS_LEVEL"));
                item.setREPRESENT_POS_LEVEL_NAME(rs.getString("REPRESENT_POS_LEVEL_NAME"));
                item.setREPRESENT_DEPT_CODE(rs.getString("REPRESENT_DEPT_CODE"));
                item.setREPRESENT_DEPT_NAME(rs.getString("REPRESENT_DEPT_NAME"));
                item.setREPRESENT_DEPT_LEVEL(rs.getInt("REPRESENT_DEPT_LEVEL"));
                item.setREPRESENT_UNDER_DEPT_CODE(rs.getString("REPRESENT_UNDER_DEPT_CODE"));
                item.setREPRESENT_UNDER_DEPT_NAME(rs.getString("REPRESENT_UNDER_DEPT_NAME"));
                item.setREPRESENT_UNDER_DEPT_LEVEL(rs.getInt("REPRESENT_UNDER_DEPT_LEVEL"));
                item.setREPRESENT_WORK_DEPT_CODE(rs.getString("REPRESENT_WORK_DEPT_CODE"));
                item.setREPRESENT_WORK_DEPT_NAME(rs.getString("REPRESENT_WORK_DEPT_NAME"));
                item.setREPRESENT_WORK_DEPT_LEVEL(rs.getInt("REPRESENT_WORK_DEPT_LEVEL"));
                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setREPRESENT_OFFICE_SHORT_NAME(rs.getString("REPRESENT_OFFICE_SHORT_NAME"));
                item.setSTATUS(rs.getInt("STATUS"));
                item.setREMARK(rs.getString("REMARK"));
                item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;

    }

    protected List<RevenueCompareMapping> getRevenueCompareMapping(int COMPARE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "COMPARE_MAPPING_ID," +
                        "COMPARE_ID," +
                        "INDICTMENT_DETAIL_ID," +
                        "PAST_LAWSUIT_ID," +
                        "IS_EVER_WRONG," +
                        "IS_ACTIVE " +
                        "from OPS_COMPARE_MAPPING  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and COMPARE_ID = '"+COMPARE_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCompareMapping> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueCompareMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueCompareMapping item = new RevenueCompareMapping();
                item.setCOMPARE_MAPPING_ID(rs.getInt("COMPARE_MAPPING_ID"));
                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
                item.setPAST_LAWSUIT_ID(rs.getInt("PAST_LAWSUIT_ID"));
                item.setIS_EVER_WRONG(rs.getInt("IS_EVER_WRONG"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setRevenueCompareDetail(getRevenueCompareDetail(rs.getInt("COMPARE_MAPPING_ID")));
                item.setRevenueCompareArrestIndictmentDetail(getRevenueCompareArrestIndictmentDetail(rs.getInt("INDICTMENT_DETAIL_ID")));
                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueCompareMapping> getRevenueCompareMappingByCon(int COMPARE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID," +
                        "    OPS_COMPARE_MAPPING.COMPARE_ID," +
                        "    OPS_COMPARE_MAPPING.INDICTMENT_DETAIL_ID," +
                        "    OPS_COMPARE_MAPPING.PAST_LAWSUIT_ID," +
                        "    OPS_COMPARE_MAPPING.IS_EVER_WRONG," +
                        "    OPS_COMPARE_MAPPING.IS_ACTIVE" +
                        "    FROM OPS_REVENUE_DETAIL" +
                        "    INNER JOIN OPS_COMPARE_DETAIL ON OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID  = OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID AND  OPS_COMPARE_DETAIL.IS_ACTIVE = 1" +
                        "    INNER JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID AND OPS_COMPARE_MAPPING.IS_ACTIVE=1" +
                        "    WHERE OPS_REVENUE_DETAIL.IS_ACTIVE = 1" +
                        "    AND OPS_COMPARE_MAPPING.COMPARE_ID = "+COMPARE_ID);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCompareMapping> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueCompareMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
                RevenueCompareMapping item = new RevenueCompareMapping();
                item.setCOMPARE_MAPPING_ID(rs.getInt("COMPARE_MAPPING_ID"));
                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
                item.setPAST_LAWSUIT_ID(rs.getInt("PAST_LAWSUIT_ID"));
                item.setIS_EVER_WRONG(rs.getInt("IS_EVER_WRONG"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setRevenueCompareDetail(getRevenueCompareDetailByCon(rs.getInt("COMPARE_MAPPING_ID")));
                item.setRevenueCompareArrestIndictmentDetail(getRevenueCompareArrestIndictmentDetailByCon(rs.getInt("INDICTMENT_DETAIL_ID")));
                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueCompareDetail> getRevenueCompareDetail(int COMPARE_MAPPING_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select DISTINCT" +
                        "    OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID," +
                        "    OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID," +
                        "    OPS_COMPARE_DETAIL.RECEIPT_OFFICE_ID," +
                        "    OPS_COMPARE_DETAIL.APPROVE_OFFICE_ID," +
                        "    OPS_COMPARE_DETAIL.MISTREAT_NO," +
                        "    OPS_COMPARE_DETAIL.OLD_PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL.PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL.DIFFERENCE_PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL.TREASURY_MONEY," +
                        "    OPS_COMPARE_DETAIL.BRIBE_MONEY," +
                        "    OPS_COMPARE_DETAIL.REWARD_MONEY," +
                        "    OPS_COMPARE_DETAIL.PAYMENT_FINE_DUE_DATE," +
                        "    OPS_COMPARE_DETAIL.PAYMENT_VAT_DUE_DATE," +
                        "    OPS_COMPARE_DETAIL.INSURANCE," +
                        "    OPS_COMPARE_DETAIL.GAURANTEE," +
                        "    OPS_COMPARE_DETAIL.PAYMENT_DATE," +
                        "    OPS_COMPARE_DETAIL.RECEIPT_TYPE," +
                        "    OPS_COMPARE_DETAIL.RECEIPT_BOOK_NO || CASE WHEN OPS_COMPARE_DETAIL.RECEIPT_BOOK_NO IS NOT NULL THEN '/' END || OPS_COMPARE_DETAIL.RECEIPT_NO AS RECEIPT_BOOK_NO," +
                        "    OPS_COMPARE_DETAIL.RECEIPT_OFFICE_CODE," +
                        "    OPS_COMPARE_DETAIL.RECEIPT_OFFICE_NAME," +
                        "    OPS_COMPARE_DETAIL.APPROVE_OFFICE_CODE," +
                        "    OPS_COMPARE_DETAIL.APPROVE_OFFICE_NAME," +
                        "    OPS_COMPARE_DETAIL.APPROVE_DATE," +
                        "    OPS_COMPARE_DETAIL.APPROVE_TYPE," +
                        "    OPS_COMPARE_DETAIL.COMMAND_NO," +
                        "    OPS_COMPARE_DETAIL.COMMAND_DATE," +
                        "    OPS_COMPARE_DETAIL.REMARK_NOT_AGREE," +
                        "    OPS_COMPARE_DETAIL.REMARK_NOT_APPROVE," +
                        "    OPS_COMPARE_DETAIL.FACT," +
                        "    OPS_COMPARE_DETAIL.COMPARE_REASON," +
                        "    OPS_COMPARE_DETAIL.ADJUST_REASON," +
                        "    CASE WHEN OPS_COMPARE_DETAIL.COMPARE_TYPE =0 THEN 'ปรับลง' " +
                        "         WHEN OPS_COMPARE_DETAIL.COMPARE_TYPE =1 THEN 'เปรียบเทียบคดี' " +
                        "         WHEN OPS_COMPARE_DETAIL.COMPARE_TYPE =2 THEN 'ปรับเพิ่ม' END COMPARE_TYPE, " +
                        "    OPS_COMPARE_DETAIL.IS_REQUEST," +
                        "    OPS_COMPARE_DETAIL.IS_TEMP_RELEASE," +
                        "    OPS_COMPARE_DETAIL.IS_INSURANCE," +
                        "    OPS_COMPARE_DETAIL.IS_GAURANTEE," +
                        "    CASE WHEN OPS_COMPARE_DETAIL.IS_PAYMENT =0 THEN 'ยังไม่ชำระค่าปรับ' " +
                        "         WHEN OPS_COMPARE_DETAIL.IS_PAYMENT =1 THEN 'ชำระค่าปรับแล้ว' END IS_PAYMENT,     " +
                        "         CASE WHEN OPS_COMPARE_DETAIL.IS_REVENUE =0 THEN 'ยังไม่นำส่งเงินรายได้'  " +
                        "         WHEN OPS_COMPARE_DETAIL.IS_REVENUE =1 THEN 'นำส่งเงินรายได้'  " +
                        "         WHEN OPS_COMPARE_DETAIL.IS_REVENUE =2 THEN 'ระบบรายได้รับแล้ว' END IS_REVENUE, " +
                        "    OPS_COMPARE_DETAIL.IS_AGREE," +
                        "    OPS_COMPARE_DETAIL.IS_APPROVE," +
                        "    OPS_COMPARE_DETAIL.IS_AUTHORITY," +
                        "    OPS_COMPARE_DETAIL.IS_ACTIVE," +
                        "    OPS_COMPARE_DETAIL.DID" +
                        "    from OPS_COMPARE_DETAIL" +
                        "    where OPS_COMPARE_DETAIL.IS_ACTIVE = 1 AND OPS_COMPARE_DETAIL.IS_REVENUE =0 ");
        sqlBuilder.append("and COMPARE_MAPPING_ID = '"+COMPARE_MAPPING_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCompareDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueCompareDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueCompareDetail item = new RevenueCompareDetail();
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setCOMPARE_MAPPING_ID(rs.getInt("COMPARE_MAPPING_ID"));
                item.setRECEIPT_OFFICE_ID(rs.getInt("RECEIPT_OFFICE_ID"));
                item.setAPPROVE_OFFICE_ID(rs.getInt("APPROVE_OFFICE_ID"));
                item.setMISTREAT_NO(rs.getInt("MISTREAT_NO"));
                item.setOLD_PAYMENT_FINE(rs.getFloat("OLD_PAYMENT_FINE"));
                item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
                item.setDIFFERENCE_PAYMENT_FINE(rs.getFloat("DIFFERENCE_PAYMENT_FINE"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                item.setPAYMENT_FINE_DUE_DATE(rs.getString("PAYMENT_FINE_DUE_DATE"));
                item.setPAYMENT_VAT_DUE_DATE(rs.getString("PAYMENT_VAT_DUE_DATE"));
                item.setINSURANCE(rs.getString("INSURANCE"));
                item.setGAURANTEE(rs.getString("GAURANTEE"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setRECEIPT_TYPE(rs.getInt("RECEIPT_TYPE"));
                item.setRECEIPT_BOOK_NO(rs.getString("RECEIPT_BOOK_NO"));
                item.setRECEIPT_OFFICE_CODE(rs.getString("RECEIPT_OFFICE_CODE"));
                item.setRECEIPT_OFFICE_NAME(rs.getString("RECEIPT_OFFICE_NAME"));
                item.setAPPROVE_OFFICE_CODE(rs.getString("APPROVE_OFFICE_CODE"));
                item.setAPPROVE_OFFICE_NAME(rs.getString("APPROVE_OFFICE_NAME"));
                item.setAPPROVE_DATE(rs.getString("APPROVE_DATE"));
                item.setAPPROVE_TYPE(rs.getString("APPROVE_TYPE"));
                item.setCOMMAND_NO(rs.getString("COMMAND_NO"));
                item.setCOMMAND_DATE(rs.getString("COMMAND_DATE"));
                item.setREMARK_NOT_AGREE(rs.getString("REMARK_NOT_AGREE"));
                item.setREMARK_NOT_APPROVE(rs.getString("REMARK_NOT_APPROVE"));
                item.setFACT(rs.getString("FACT"));
                item.setCOMPARE_REASON(rs.getString("COMPARE_REASON"));
                item.setADJUST_REASON(rs.getString("ADJUST_REASON"));
                item.setCOMPARE_TYPE(rs.getString("COMPARE_TYPE"));
                item.setIS_REQUEST(rs.getInt("IS_REQUEST"));
                item.setIS_TEMP_RELEASE(rs.getInt("IS_TEMP_RELEASE"));
                item.setIS_INSURANCE(rs.getInt("IS_INSURANCE"));
                item.setIS_GAURANTEE(rs.getInt("IS_GAURANTEE"));
                item.setIS_PAYMENT(rs.getString("IS_PAYMENT"));
                item.setIS_REVENUE(rs.getString("IS_REVENUE"));
                item.setIS_AGREE(rs.getInt("IS_AGREE"));
                item.setIS_APPROVE(rs.getInt("IS_APPROVE"));
                item.setIS_AUTHORITY(rs.getInt("IS_AUTHORITY"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setDID(rs.getString("DID"));
                item.setRevenuePayment(getRevenuePayment(rs.getInt("COMPARE_DETAIL_ID")));
                item.setRevenueCompareDetailFine(getRevenueCompareDetailFine(rs.getInt("COMPARE_DETAIL_ID")));
                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueCompareDetail> getRevenueCompareDetailByCon(int COMPARE_MAPPING_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select DISTINCT" +
                        "    OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID," +
                        "    OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID," +
                        "    OPS_COMPARE_DETAIL.RECEIPT_OFFICE_ID," +
                        "    OPS_COMPARE_DETAIL.APPROVE_OFFICE_ID," +
                        "    OPS_COMPARE_DETAIL.MISTREAT_NO," +
                        "    OPS_COMPARE_DETAIL.OLD_PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL.PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL.DIFFERENCE_PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL.TREASURY_MONEY," +
                        "    OPS_COMPARE_DETAIL.BRIBE_MONEY," +
                        "    OPS_COMPARE_DETAIL.REWARD_MONEY," +
                        "    OPS_COMPARE_DETAIL.PAYMENT_FINE_DUE_DATE," +
                        "    OPS_COMPARE_DETAIL.PAYMENT_VAT_DUE_DATE," +
                        "    OPS_COMPARE_DETAIL.INSURANCE," +
                        "    OPS_COMPARE_DETAIL.GAURANTEE," +
                        "    OPS_COMPARE_DETAIL.PAYMENT_DATE," +
                        "    OPS_COMPARE_DETAIL.RECEIPT_TYPE," +
                        "    OPS_COMPARE_DETAIL.RECEIPT_BOOK_NO || CASE WHEN OPS_COMPARE_DETAIL.RECEIPT_BOOK_NO IS NOT NULL THEN '/' END || OPS_COMPARE_DETAIL.RECEIPT_NO AS RECEIPT_BOOK_NO," +
                        "    OPS_COMPARE_DETAIL.RECEIPT_OFFICE_CODE," +
                        "    OPS_COMPARE_DETAIL.RECEIPT_OFFICE_NAME," +
                        "    OPS_COMPARE_DETAIL.APPROVE_OFFICE_CODE," +
                        "    OPS_COMPARE_DETAIL.APPROVE_OFFICE_NAME," +
                        "    OPS_COMPARE_DETAIL.APPROVE_DATE," +
                        "    OPS_COMPARE_DETAIL.APPROVE_TYPE," +
                        "    OPS_COMPARE_DETAIL.COMMAND_NO," +
                        "    OPS_COMPARE_DETAIL.COMMAND_DATE," +
                        "    OPS_COMPARE_DETAIL.REMARK_NOT_AGREE," +
                        "    OPS_COMPARE_DETAIL.REMARK_NOT_APPROVE," +
                        "    OPS_COMPARE_DETAIL.FACT," +
                        "    OPS_COMPARE_DETAIL.COMPARE_REASON," +
                        "    OPS_COMPARE_DETAIL.ADJUST_REASON," +
                        "    CASE WHEN OPS_COMPARE_DETAIL.COMPARE_TYPE =0 THEN 'ปรับลง' " +
                        "         WHEN OPS_COMPARE_DETAIL.COMPARE_TYPE =1 THEN 'เปรียบเทียบคดี' " +
                        "         WHEN OPS_COMPARE_DETAIL.COMPARE_TYPE =2 THEN 'ปรับเพิ่ม' END COMPARE_TYPE, " +
                        "    OPS_COMPARE_DETAIL.IS_REQUEST," +
                        "    OPS_COMPARE_DETAIL.IS_TEMP_RELEASE," +
                        "    OPS_COMPARE_DETAIL.IS_INSURANCE," +
                        "    OPS_COMPARE_DETAIL.IS_GAURANTEE," +
                        "    CASE WHEN OPS_COMPARE_DETAIL.IS_PAYMENT =0 THEN 'ยังไม่ชำระค่าปรับ' " +
                        "         WHEN OPS_COMPARE_DETAIL.IS_PAYMENT =1 THEN 'ชำระค่าปรับแล้ว' END IS_PAYMENT,     " +
                        "         CASE WHEN OPS_COMPARE_DETAIL.IS_REVENUE =0 THEN 'ยังไม่นำส่งเงินรายได้'  " +
                        "         WHEN OPS_COMPARE_DETAIL.IS_REVENUE =1 THEN 'นำส่งเงินรายได้'  " +
                        "         WHEN OPS_COMPARE_DETAIL.IS_REVENUE =2 THEN 'ระบบรายได้รับแล้ว' END IS_REVENUE, " +
                        "    OPS_COMPARE_DETAIL.IS_AGREE," +
                        "    OPS_COMPARE_DETAIL.IS_APPROVE," +
                        "    OPS_COMPARE_DETAIL.IS_AUTHORITY," +
                        "    OPS_COMPARE_DETAIL.IS_ACTIVE," +
                        "    OPS_COMPARE_DETAIL.DID" +
                        "    from OPS_REVENUE_DETAIL" +
                        "    INNER JOIN OPS_COMPARE_DETAIL ON OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID  = OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID AND  OPS_COMPARE_DETAIL.IS_ACTIVE = 1 " +
                        "    INNER JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID AND OPS_COMPARE_MAPPING.IS_ACTIVE=1 ");
        sqlBuilder.append(" where  OPS_REVENUE_DETAIL.IS_ACTIVE = 1 AND OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = '"+COMPARE_MAPPING_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCompareDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueCompareDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueCompareDetail item = new RevenueCompareDetail();
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setCOMPARE_MAPPING_ID(rs.getInt("COMPARE_MAPPING_ID"));
                item.setRECEIPT_OFFICE_ID(rs.getInt("RECEIPT_OFFICE_ID"));
                item.setAPPROVE_OFFICE_ID(rs.getInt("APPROVE_OFFICE_ID"));
                item.setMISTREAT_NO(rs.getInt("MISTREAT_NO"));
                item.setOLD_PAYMENT_FINE(rs.getFloat("OLD_PAYMENT_FINE"));
                item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
                item.setDIFFERENCE_PAYMENT_FINE(rs.getFloat("DIFFERENCE_PAYMENT_FINE"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                item.setPAYMENT_FINE_DUE_DATE(rs.getString("PAYMENT_FINE_DUE_DATE"));
                item.setPAYMENT_VAT_DUE_DATE(rs.getString("PAYMENT_VAT_DUE_DATE"));
                item.setINSURANCE(rs.getString("INSURANCE"));
                item.setGAURANTEE(rs.getString("GAURANTEE"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setRECEIPT_TYPE(rs.getInt("RECEIPT_TYPE"));
                item.setRECEIPT_BOOK_NO(rs.getString("RECEIPT_BOOK_NO"));
                item.setRECEIPT_OFFICE_CODE(rs.getString("RECEIPT_OFFICE_CODE"));
                item.setRECEIPT_OFFICE_NAME(rs.getString("RECEIPT_OFFICE_NAME"));
                item.setAPPROVE_OFFICE_CODE(rs.getString("APPROVE_OFFICE_CODE"));
                item.setAPPROVE_OFFICE_NAME(rs.getString("APPROVE_OFFICE_NAME"));
                item.setAPPROVE_DATE(rs.getString("APPROVE_DATE"));
                item.setAPPROVE_TYPE(rs.getString("APPROVE_TYPE"));
                item.setCOMMAND_NO(rs.getString("COMMAND_NO"));
                item.setCOMMAND_DATE(rs.getString("COMMAND_DATE"));
                item.setREMARK_NOT_AGREE(rs.getString("REMARK_NOT_AGREE"));
                item.setREMARK_NOT_APPROVE(rs.getString("REMARK_NOT_APPROVE"));
                item.setFACT(rs.getString("FACT"));
                item.setCOMPARE_REASON(rs.getString("COMPARE_REASON"));
                item.setADJUST_REASON(rs.getString("ADJUST_REASON"));
                item.setCOMPARE_TYPE(rs.getString("COMPARE_TYPE"));
                item.setIS_REQUEST(rs.getInt("IS_REQUEST"));
                item.setIS_TEMP_RELEASE(rs.getInt("IS_TEMP_RELEASE"));
                item.setIS_INSURANCE(rs.getInt("IS_INSURANCE"));
                item.setIS_GAURANTEE(rs.getInt("IS_GAURANTEE"));
                item.setIS_PAYMENT(rs.getString("IS_PAYMENT"));
                item.setIS_REVENUE(rs.getString("IS_REVENUE"));
                item.setIS_AGREE(rs.getInt("IS_AGREE"));
                item.setIS_APPROVE(rs.getInt("IS_APPROVE"));
                item.setIS_AUTHORITY(rs.getInt("IS_AUTHORITY"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setDID(rs.getString("DID"));
                item.setRevenuePayment(getRevenuePaymentByCon(rs.getInt("COMPARE_DETAIL_ID"))); //ok
                item.setRevenueCompareDetailFine(getRevenueCompareDetailFineByCon(rs.getInt("COMPARE_DETAIL_ID")));
                return item;
            }
        });

        return dataList;
    }

    protected List<RevenuePayment> getRevenuePaymentByCon(int COMPARE_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_PAYMENT.PAYMENT_ID," +
                        "    OPS_PAYMENT.COMPARE_DETAIL_ID," +
                        "    OPS_PAYMENT.LAWSUIT_DETAIL_ID," +
                        "    OPS_PAYMENT.FINE_TYPE," +
//                        "    CASE WHEN OPS_PAYMENT.FINE_TYPE =0 THEN 'ส่งฟ้องศาล'" +
//                        "       WHEN OPS_PAYMENT.FINE_TYPE =1 THEN 'เปรียบเทียบคดี' END FINE_TYPE," +
                        "    OPS_PAYMENT.FINE," +
                        "    OPS_PAYMENT.PAYMENT_PERIOD_NO," +
                        "    TO_CHAR(OPS_PAYMENT.PAYMENT_DATE,'dd-mm-yyyy hh:mm:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS PAYMENT_DATE," +
//                        "       CASE WHEN OPS_PAYMENT.PAYMENT_CHANNEL =1 THEN 'เงินสด' " +
//                        "       WHEN OPS_PAYMENT.PAYMENT_CHANNEL =2 THEN 'เช็คพาณิชย์'" +
//                        "       WHEN OPS_PAYMENT.PAYMENT_CHANNEL =5 THEN 'บัตรเดบิท'" +
//                        "       WHEN OPS_PAYMENT.PAYMENT_CHANNEL =6 THEN 'บัตรเครดิต'" +
//                        "       WHEN OPS_PAYMENT.PAYMENT_CHANNEL =8 THEN 'โอนเงินอิเล็กทรอนิกส์' END PAYMENT_CHANNEL," +
                        "    OPS_PAYMENT.PAYMENT_CHANNEL," +
                        "    OPS_PAYMENT.PAYMENT_BANK," +
                        "    OPS_PAYMENT.PAYMENT_REF_NO," +
                        "    OPS_PAYMENT.IS_REQUEST_REWARD," +
                        "    OPS_PAYMENT.IS_REVENUE," +
                        "    OPS_PAYMENT.IS_ACTIVE" +
                        "    FROM OPS_REVENUE_DETAIL" +
                        "    INNER JOIN OPS_COMPARE_DETAIL ON OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID  = OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID AND  OPS_COMPARE_DETAIL.IS_ACTIVE = 1" +
                        "    INNER JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID AND OPS_COMPARE_MAPPING.IS_ACTIVE=1" +
                        "    INNER JOIN OPS_PAYMENT ON OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID = OPS_PAYMENT.COMPARE_DETAIL_ID " +
                        "    WHERE OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID = "+COMPARE_DETAIL_ID +
                        "    ORDER BY OPS_PAYMENT.PAYMENT_ID ASC");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenuePayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenuePayment mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenuePayment item = new RevenuePayment();
                item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setPAYMENT_PERIOD_NO(rs.getInt("PAYMENT_PERIOD_NO"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setIS_REQUEST_REWARD(rs.getInt("IS_REQUEST_REWARD"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setPAYMENT_CHANNEL(rs.getInt("PAYMENT_CHANNEL"));
                item.setPAYMENT_BANK(rs.getString("PAYMENT_BANK"));
                item.setPAYMENT_REF_NO(rs.getString("PAYMENT_REF_NO"));
                item.setIS_REVENUE(rs.getInt("IS_REVENUE"));

                return item;
            }
        });

        return dataList;
    }

    protected List<RevenuePayment> getRevenuePayment(int COMPARE_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select * from OPS_PAYMENT where IS_ACTIVE = 1 and COMPARE_DETAIL_ID = "+COMPARE_DETAIL_ID);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenuePayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenuePayment mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenuePayment item = new RevenuePayment();
                item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setPAYMENT_PERIOD_NO(rs.getInt("PAYMENT_PERIOD_NO"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setIS_REQUEST_REWARD(rs.getInt("IS_REQUEST_REWARD"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setPAYMENT_CHANNEL(rs.getInt("PAYMENT_CHANNEL"));
                item.setPAYMENT_BANK(rs.getString("PAYMENT_BANK"));
                item.setPAYMENT_REF_NO(rs.getString("PAYMENT_REF_NO"));
                item.setIS_REVENUE(rs.getInt("IS_REVENUE"));

                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueCompareDetailFine> getRevenueCompareDetailFine(int COMPARE_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_COMPARE_DETAIL_FINE.FINE_ID," +
                        "    OPS_COMPARE_DETAIL_FINE.COMPARE_DETAIL_ID," +
                        "    OPS_COMPARE_DETAIL_FINE.PRODUCT_ID," +
                        "    OPS_PROVE_PRODUCT.PRODUCT_GROUP_ID," +
                        "    OPS_PROVE_PRODUCT.PRODUCT_GROUP_CODE," +
                        "    OPS_COMPARE_DETAIL_FINE.FINE_RATE," +
                        "    OPS_COMPARE_DETAIL_FINE.VAT," +
                        "    OPS_COMPARE_DETAIL_FINE.FINE," +
                        "    OPS_COMPARE_DETAIL_FINE.NET_FINE," +
                        "    OPS_COMPARE_DETAIL_FINE.OLD_PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL_FINE.PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL_FINE.DIFFERENCE_PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL_FINE.TREASURY_MONEY," +
                        "    OPS_COMPARE_DETAIL_FINE.BRIBE_MONEY," +
                        "    OPS_COMPARE_DETAIL_FINE.REWARD_MONEY," +
                        "    OPS_COMPARE_DETAIL_FINE.IS_ACTIVE" +
                        "    FROM OPS_COMPARE " +
                        "    LEFT JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_MAPPING.COMPARE_ID AND OPS_COMPARE_MAPPING.IS_ACTIVE=1" +
                        "    LEFT JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID = OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID AND OPS_COMPARE_DETAIL.IS_ACTIVE=1" +
                        "    LEFT JOIN OPS_COMPARE_DETAIL_FINE ON OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID = OPS_COMPARE_DETAIL_FINE.COMPARE_DETAIL_ID AND OPS_COMPARE_DETAIL_FINE.IS_ACTIVE=1 " +
                        "    LEFT JOIN OPS_PROVE_PRODUCT ON OPS_COMPARE_DETAIL_FINE.PRODUCT_ID = OPS_PROVE_PRODUCT.PRODUCT_ID AND OPS_PROVE_PRODUCT.IS_ACTIVE=1 " +
                        "    WHERE OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID = "+COMPARE_DETAIL_ID);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCompareDetailFine> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueCompareDetailFine mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueCompareDetailFine item = new RevenueCompareDetailFine();

                item.setFINE_ID(rs.getInt("FINE_ID"));
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setFINE_RATE(rs.getFloat("FINE_RATE"));
                item.setVAT(rs.getFloat("VAT"));
                item.setFINE(rs.getFloat("FINE"));
                item.setNET_FINE(rs.getFloat("NET_FINE"));
                item.setOLD_PAYMENT_FINE(rs.getFloat("OLD_PAYMENT_FINE"));
                item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
                item.setDIFFERENCE_PAYMENT_FINE(rs.getFloat("DIFFERENCE_PAYMENT_FINE"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueCompareDetailFine> getRevenueCompareDetailFineByCon(int COMPARE_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_COMPARE_DETAIL_FINE.FINE_ID," +
                        "    OPS_COMPARE_DETAIL_FINE.COMPARE_DETAIL_ID," +
                        "    OPS_COMPARE_DETAIL_FINE.PRODUCT_ID," +
                        "    OPS_PROVE_PRODUCT.PRODUCT_GROUP_ID," +
                        "    OPS_PROVE_PRODUCT.PRODUCT_GROUP_CODE," +
                        "    OPS_COMPARE_DETAIL_FINE.FINE_RATE," +
                        "    OPS_COMPARE_DETAIL_FINE.VAT," +
                        "    OPS_COMPARE_DETAIL_FINE.FINE," +
                        "    OPS_COMPARE_DETAIL_FINE.NET_FINE," +
                        "    OPS_COMPARE_DETAIL_FINE.OLD_PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL_FINE.PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL_FINE.DIFFERENCE_PAYMENT_FINE," +
                        "    OPS_COMPARE_DETAIL_FINE.TREASURY_MONEY," +
                        "    OPS_COMPARE_DETAIL_FINE.BRIBE_MONEY," +
                        "    OPS_COMPARE_DETAIL_FINE.REWARD_MONEY," +
                        "    OPS_COMPARE_DETAIL_FINE.IS_ACTIVE" +
                        "    FROM OPS_REVENUE_DETAIL " +
                        "    INNER JOIN OPS_COMPARE_DETAIL ON OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID  = OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID AND  OPS_COMPARE_DETAIL.IS_ACTIVE = 1" +
                        "    INNER JOIN OPS_COMPARE_DETAIL_FINE ON OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID = OPS_COMPARE_DETAIL_FINE.COMPARE_DETAIL_ID AND OPS_COMPARE_DETAIL_FINE.IS_ACTIVE=1" +
                        "    INNER JOIN OPS_PROVE_PRODUCT ON OPS_COMPARE_DETAIL_FINE.PRODUCT_ID = OPS_PROVE_PRODUCT.PRODUCT_ID AND OPS_PROVE_PRODUCT.IS_ACTIVE=1  " +
                        "    WHERE OPS_COMPARE_DETAIL.COMPARE_DETAIL_ID = "+COMPARE_DETAIL_ID);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCompareDetailFine> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueCompareDetailFine mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueCompareDetailFine item = new RevenueCompareDetailFine();

                item.setFINE_ID(rs.getInt("FINE_ID"));
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setFINE_RATE(rs.getFloat("FINE_RATE"));
                item.setVAT(rs.getFloat("VAT"));
                item.setFINE(rs.getFloat("FINE"));
                item.setNET_FINE(rs.getFloat("NET_FINE"));
                item.setOLD_PAYMENT_FINE(rs.getFloat("OLD_PAYMENT_FINE"));
                item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
                item.setDIFFERENCE_PAYMENT_FINE(rs.getFloat("DIFFERENCE_PAYMENT_FINE"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueCourtDetail> getRevenueCourtDetail(int INDICTMENT_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  select " +
                        "  LAWSUIT_DETAIL_ID," +
                        "  LAWSUIT_ID," +
                        "  COURT_ID," +
                        "  LAWSUIT_TYPE," +
                        "  LAWSUIT_END," +
                        "  COURT_NAME," +
                        "  UNDECIDE_NO_1," +
                        "to_char(UNDECIDE_NO_YEAR_1,'"+Pattern.FORMAT_DATETIME+"') as UNDECIDE_NO_YEAR_1," +
                        "  DECIDE_NO_1," +
                        "to_char(DECIDE_NO_YEAR_1,'"+Pattern.FORMAT_DATETIME+"') as DECIDE_NO_YEAR_1," +
                        "  UNDECIDE_NO_2," +
                        "to_char(UNDECIDE_NO_YEAR_2,'"+Pattern.FORMAT_DATETIME+"') as UNDECIDE_NO_YEAR_2," +
                        "  DECIDE_NO_2," +
                        "to_char(DECIDE_NO_YEAR_2,'"+Pattern.FORMAT_DATETIME+"') as DECIDE_NO_YEAR_2," +
                        "  JUDGEMENT_NO," +
                        "to_char(JUDGEMENT_NO_YEAR,'"+Pattern.FORMAT_DATETIME+"') as JUDGEMENT_NO_YEAR," +
                        "  IS_FINE," +
                        "to_char(FINE_DATE,'"+Pattern.FORMAT_DATETIME+"') as FINE_DATE," +
                        "  PAYMENT_REF_NO," +
                        "to_char(PAYMENT_DATE,'"+Pattern.FORMAT_DATETIME+"') as PAYMENT_DATE," +
                        "  IS_ACTIVE" +
                        "  from OPS_LAWSUIT_DETAIL where IS_ACTIVE = '1'" +
                        "  and INDICTMENT_DETAIL_ID = '"+INDICTMENT_DETAIL_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCourtDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueCourtDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueCourtDetail item = new RevenueCourtDetail();
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setCOURT_ID(rs.getInt("COURT_ID"));
                item.setLAWSUIT_TYPE(rs.getInt("LAWSUIT_TYPE"));
                item.setLAWSUIT_END(rs.getInt("LAWSUIT_END"));
                item.setCOURT_NAME(rs.getString("COURT_NAME"));
                item.setUNDECIDE_NO_1(rs.getInt("UNDECIDE_NO_1"));
                item.setUNDECIDE_NO_YEAR_1(rs.getString("UNDECIDE_NO_YEAR_1"));
                item.setDECIDE_NO_1(rs.getInt("DECIDE_NO_1"));
                item.setDECIDE_NO_YEAR_1(rs.getString("DECIDE_NO_YEAR_1"));
                item.setUNDECIDE_NO_2(rs.getInt("UNDECIDE_NO_2"));
                item.setUNDECIDE_NO_YEAR_2(rs.getString("UNDECIDE_NO_YEAR_2"));
                item.setDECIDE_NO_2(rs.getInt("DECIDE_NO_2"));
                item.setDECIDE_NO_YEAR_2(rs.getString("DECIDE_NO_YEAR_2"));
                item.setJUDGEMENT_NO(rs.getInt("JUDGEMENT_NO"));
                item.setJUDGEMENT_NO_YEAR(rs.getString("JUDGEMENT_NO_YEAR"));
                item.setIS_FINE(rs.getInt("IS_FINE"));
                item.setFINE_DATE(rs.getString("FINE_DATE"));
                item.setPAYMENT_REF_NO(rs.getString("PAYMENT_REF_NO"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                //item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;
    }

    protected RevenueCompareArrestIndictmentDetail getRevenueCompareArrestIndictmentDetail(int INDICTMENT_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "INDICTMENT_DETAIL_ID," +
                        "INDICTMENT_ID," +
                        "LAWBREAKER_ID," +
                        "IS_ACTIVE," +
                        "FINE_ESTIMATE " +
                        " from OPS_ARREST_INDICTMENT_DETAIL  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and INDICTMENT_DETAIL_ID = '"+INDICTMENT_DETAIL_ID+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<RevenueCompareArrestIndictmentDetail>() {

            public RevenueCompareArrestIndictmentDetail extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    RevenueCompareArrestIndictmentDetail item = new RevenueCompareArrestIndictmentDetail();
                    item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
                    item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                    item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setFINE_ESTIMATE(rs.getFloat("FINE_ESTIMATE"));
                    item.setRevenueLawbreaker(getRevenueLawbreaker(rs.getInt("LAWBREAKER_ID")));
                    item.setRevenueProductDetail(getRevenueProductDetail(rs.getInt("INDICTMENT_ID")));
                    item.setRevenueNotice(getRevenueNotice(rs.getInt("INDICTMENT_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    protected RevenueCompareArrestIndictmentDetail getRevenueCompareArrestIndictmentDetailByCon(int INDICTMENT_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID," +
                        "    OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID," +
                        "    OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID," +
                        "    OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE," +
                        "    OPS_ARREST_INDICTMENT_DETAIL.FINE_ESTIMATE" +
                        "    FROM OPS_ARREST_INDICTMENT_DETAIL" +
                        "    INNER JOIN OPS_COMPARE_MAPPING ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_COMPARE_MAPPING.INDICTMENT_DETAIL_ID" +
                        "    WHERE OPS_COMPARE_MAPPING.INDICTMENT_DETAIL_ID = "+INDICTMENT_DETAIL_ID);

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<RevenueCompareArrestIndictmentDetail>() {

            public RevenueCompareArrestIndictmentDetail extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    RevenueCompareArrestIndictmentDetail item = new RevenueCompareArrestIndictmentDetail();
                    item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
                    item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                    item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setFINE_ESTIMATE(rs.getFloat("FINE_ESTIMATE"));
                    item.setRevenueLawbreaker(getRevenueLawbreakerByCon(rs.getInt("LAWBREAKER_ID")));
                    item.setRevenueProductDetail(getRevenueProductDetailByCon(rs.getInt("INDICTMENT_ID")));
                    item.setRevenueNotice(getRevenueNoticeByCon(rs.getInt("INDICTMENT_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    protected RevenueLawbreaker getRevenueLawbreaker(int LAWBREAKER_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "LAWBREAKER_ID," +
                        "ARREST_ID," +
                        "PERSON_ID," +
                        "TITLE_ID," +
                        "PERSON_TYPE," +
                        "ENTITY_TYPE," +
                        "TITLE_NAME_TH," +
                        "TITLE_NAME_EN," +
                        "TITLE_SHORT_NAME_TH," +
                        "TITLE_SHORT_NAME_EN," +
                        "FIRST_NAME," +
                        "MIDDLE_NAME," +
                        "LAST_NAME," +
                        "OTHER_NAME," +
                        "COMPANY_NAME," +
                        "COMPANY_REGISTRATION_NO," +
                        "EXCISE_REGISTRATION_NO," +
                        "ID_CARD," +
                        "AGE," +
                        "PASSPORT_NO," +
                        "CAREER," +
                        "PERSON_DESC," +
                        "EMAIL," +
                        "TEL_NO," +
                        "MISTREAT_NO," +
                        "COMPANY_NAME," +
                        "IS_ACTIVE" +
                        " from OPS_ARREST_LAWBREAKER  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and LAWBREAKER_ID = '"+LAWBREAKER_ID+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<RevenueLawbreaker>() {

            public RevenueLawbreaker extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    RevenueLawbreaker item = new RevenueLawbreaker();
                    item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setPERSON_ID(rs.getInt("PERSON_ID"));
                    item.setTITLE_ID(rs.getInt("TITLE_ID"));
                    item.setPERSON_TYPE(rs.getInt("PERSON_TYPE"));
                    item.setENTITY_TYPE(rs.getInt("ENTITY_TYPE"));
                    item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                    item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                    item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                    item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                    item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                    item.setMIDDLE_NAME(rs.getString("MIDDLE_NAME"));
                    item.setLAST_NAME(rs.getString("LAST_NAME"));
                    item.setOTHER_NAME(rs.getString("OTHER_NAME"));
                    item.setCOMPANY_NAME(rs.getString("COMPANY_NAME"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    return item;
                }

                return null;
            }
        });
    }

    protected RevenueLawbreaker getRevenueLawbreakerByCon(int LAWBREAKER_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
                        "OPS_ARREST_LAWBREAKER.ARREST_ID," +
                        "OPS_ARREST_LAWBREAKER.PERSON_ID," +
                        "OPS_ARREST_LAWBREAKER.TITLE_ID," +
                        "OPS_ARREST_LAWBREAKER.PERSON_TYPE," +
                        "OPS_ARREST_LAWBREAKER.ENTITY_TYPE," +
                        "OPS_ARREST_LAWBREAKER.TITLE_NAME_TH," +
                        "OPS_ARREST_LAWBREAKER.TITLE_NAME_EN," +
                        "OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH," +
                        "OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN," +
                        "OPS_ARREST_LAWBREAKER.FIRST_NAME," +
                        "OPS_ARREST_LAWBREAKER.MIDDLE_NAME," +
                        "OPS_ARREST_LAWBREAKER.LAST_NAME," +
                        "OPS_ARREST_LAWBREAKER.OTHER_NAME," +
                        "OPS_ARREST_LAWBREAKER.COMPANY_NAME," +
                        "OPS_ARREST_LAWBREAKER.COMPANY_REGISTRATION_NO," +
                        "OPS_ARREST_LAWBREAKER.EXCISE_REGISTRATION_NO," +
                        "OPS_ARREST_LAWBREAKER.ID_CARD," +
                        "OPS_ARREST_LAWBREAKER.AGE," +
                        "OPS_ARREST_LAWBREAKER.PASSPORT_NO," +
                        "OPS_ARREST_LAWBREAKER.CAREER," +
                        "OPS_ARREST_LAWBREAKER.PERSON_DESC," +
                        "OPS_ARREST_LAWBREAKER.EMAIL," +
                        "OPS_ARREST_LAWBREAKER.TEL_NO," +
                        "OPS_ARREST_LAWBREAKER.MISTREAT_NO," +
                        "OPS_ARREST_LAWBREAKER.COMPANY_NAME," +
                        "OPS_ARREST_LAWBREAKER.IS_ACTIVE" +
                        " from OPS_ARREST_LAWBREAKER " +
                        " INNER JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_LAWBREAKER.LAWBREAKER_ID = OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID ");
        sqlBuilder.append(" where OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = '"+LAWBREAKER_ID+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<RevenueLawbreaker>() {

            public RevenueLawbreaker extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    RevenueLawbreaker item = new RevenueLawbreaker();
                    item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setPERSON_ID(rs.getInt("PERSON_ID"));
                    item.setTITLE_ID(rs.getInt("TITLE_ID"));
                    item.setPERSON_TYPE(rs.getInt("PERSON_TYPE"));
                    item.setENTITY_TYPE(rs.getInt("ENTITY_TYPE"));
                    item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                    item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                    item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                    item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                    item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                    item.setMIDDLE_NAME(rs.getString("MIDDLE_NAME"));
                    item.setLAST_NAME(rs.getString("LAST_NAME"));
                    item.setOTHER_NAME(rs.getString("OTHER_NAME"));
                    item.setCOMPANY_NAME(rs.getString("COMPANY_NAME"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    return item;
                }

                return null;
            }
        });
    }

    protected List<RevenueProductDetail> getRevenueProductDetail(int INDICTMENT_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select " +
                        "    OPS_ARREST_PRODUCT.* "+
                        "    from OPS_ARREST" +
                        "    inner join OPS_ARREST_INDICTMENT on OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    inner join OPS_ARREST_PRODUCT on OPS_ARREST_PRODUCT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    where OPS_ARREST_INDICTMENT.INDICTMENT_ID = "+INDICTMENT_DETAIL_ID);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueProductDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueProductDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueProductDetail item = new RevenueProductDetail();
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setPRODUCT_MAPPING_ID(rs.getInt("PRODUCT_MAPPING_ID"));
                item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
                item.setPRODUCT_REF_CODE(rs.getString("PRODUCT_REF_CODE"));
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
                item.setPRODUCT_TYPE_ID(rs.getInt("PRODUCT_TYPE_ID"));
                item.setPRODUCT_SUBTYPE_ID(rs.getInt("PRODUCT_SUBTYPE_ID"));
                item.setPRODUCT_SUBSETTYPE_ID(rs.getInt("PRODUCT_SUBSETTYPE_ID"));
                item.setPRODUCT_BRAND_ID(rs.getInt("PRODUCT_BRAND_ID"));
                item.setPRODUCT_SUBBRAND_ID(rs.getInt("PRODUCT_SUBBRAND_ID"));
                item.setPRODUCT_MODEL_ID(rs.getInt("PRODUCT_MODEL_ID"));
                item.setPRODUCT_TAXDETAIL_ID(rs.getInt("PRODUCT_TAXDETAIL_ID"));
                item.setSIZES_UNIT_ID(rs.getInt("SIZES_UNIT_ID"));
                item.setQUATITY_UNIT_ID(rs.getInt("QUATITY_UNIT_ID"));
                item.setVOLUMN_UNIT_ID(rs.getInt("VOLUMN_UNIT_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
                item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));
                item.setPRODUCT_TYPE_CODE(rs.getString("PRODUCT_TYPE_CODE"));
                item.setPRODUCT_TYPE_NAME(rs.getString("PRODUCT_TYPE_NAME"));
                item.setPRODUCT_SUBTYPE_CODE(rs.getString("PRODUCT_SUBTYPE_CODE"));
                item.setPRODUCT_SUBTYPE_NAME(rs.getString("PRODUCT_SUBTYPE_NAME"));
                item.setPRODUCT_SUBSETTYPE_CODE(rs.getString("PRODUCT_SUBSETTYPE_CODE"));
                item.setPRODUCT_SUBSETTYPE_NAME(rs.getString("PRODUCT_SUBSETTYPE_NAME"));
                item.setPRODUCT_BRAND_CODE(rs.getString("PRODUCT_BRAND_CODE"));
                item.setPRODUCT_BRAND_NAME_TH(rs.getString("PRODUCT_BRAND_NAME_TH"));
                item.setPRODUCT_BRAND_NAME_EN(rs.getString("PRODUCT_BRAND_NAME_EN"));
                item.setPRODUCT_SUBBRAND_CODE(rs.getString("PRODUCT_SUBBRAND_CODE"));
                item.setPRODUCT_SUBBRAND_NAME_TH(rs.getString("PRODUCT_SUBBRAND_NAME_TH"));
                item.setPRODUCT_SUBBRAND_NAME_EN(rs.getString("PRODUCT_SUBBRAND_NAME_EN"));
                item.setPRODUCT_MODEL_CODE(rs.getString("PRODUCT_MODEL_CODE"));
                item.setPRODUCT_MODEL_NAME_TH(rs.getString("PRODUCT_MODEL_NAME_TH"));
                item.setPRODUCT_MODEL_NAME_EN(rs.getString("PRODUCT_MODEL_NAME_EN"));
                item.setIS_TAX_VALUE(rs.getInt("IS_TAX_VALUE"));
                item.setTAX_VALUE(rs.getFloat("TAX_VALUE"));
                item.setIS_TAX_VOLUMN(rs.getInt("IS_TAX_VOLUMN"));
                item.setTAX_VOLUMN(rs.getFloat("TAX_VOLUMN"));
                item.setTAX_VOLUMN_UNIT(rs.getString("TAX_VOLUMN_UNIT"));
                item.setLICENSE_PLATE(rs.getString("LICENSE_PLATE"));
                item.setENGINE_NO(rs.getString("ENGINE_NO"));
                item.setCHASSIS_NO(rs.getString("CHASSIS_NO"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setSUGAR(rs.getFloat("SUGAR"));
                item.setCO2(rs.getFloat("CO2"));
                item.setDEGREE(rs.getFloat("DEGREE"));
                item.setPRICE(rs.getFloat("PRICE"));
                item.setSIZES(rs.getFloat("SIZES"));
                item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
                item.setQUANTITY(rs.getFloat("QUANTITY"));
                item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
                item.setVOLUMN(rs.getFloat("VOLUMN"));
                item.setVOLUMN_UNIT(rs.getString("VOLUMN_UNIT"));
                item.setREMARK(rs.getString("REMARK"));
                item.setIS_DOMESTIC(rs.getInt("IS_DOMESTIC"));
                item.setIS_ILLEGAL(rs.getInt("IS_ILLEGAL"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setPRODUCT_DESC_MAS(rs.getString("PRODUCT_DESC_MAS"));
                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueProductDetail> getRevenueProductDetailByCon(int INDICTMENT_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select " +
                        "    OPS_ARREST_PRODUCT.* "+
                        "    from OPS_ARREST" +
                        "    inner join OPS_ARREST_INDICTMENT on OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    inner join OPS_ARREST_PRODUCT on OPS_ARREST_PRODUCT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    where OPS_ARREST_INDICTMENT.INDICTMENT_ID = "+INDICTMENT_DETAIL_ID);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueProductDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueProductDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueProductDetail item = new RevenueProductDetail();
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setPRODUCT_MAPPING_ID(rs.getInt("PRODUCT_MAPPING_ID"));
                item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
                item.setPRODUCT_REF_CODE(rs.getString("PRODUCT_REF_CODE"));
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
                item.setPRODUCT_TYPE_ID(rs.getInt("PRODUCT_TYPE_ID"));
                item.setPRODUCT_SUBTYPE_ID(rs.getInt("PRODUCT_SUBTYPE_ID"));
                item.setPRODUCT_SUBSETTYPE_ID(rs.getInt("PRODUCT_SUBSETTYPE_ID"));
                item.setPRODUCT_BRAND_ID(rs.getInt("PRODUCT_BRAND_ID"));
                item.setPRODUCT_SUBBRAND_ID(rs.getInt("PRODUCT_SUBBRAND_ID"));
                item.setPRODUCT_MODEL_ID(rs.getInt("PRODUCT_MODEL_ID"));
                item.setPRODUCT_TAXDETAIL_ID(rs.getInt("PRODUCT_TAXDETAIL_ID"));
                item.setSIZES_UNIT_ID(rs.getInt("SIZES_UNIT_ID"));
                item.setQUATITY_UNIT_ID(rs.getInt("QUATITY_UNIT_ID"));
                item.setVOLUMN_UNIT_ID(rs.getInt("VOLUMN_UNIT_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
                item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));
                item.setPRODUCT_TYPE_CODE(rs.getString("PRODUCT_TYPE_CODE"));
                item.setPRODUCT_TYPE_NAME(rs.getString("PRODUCT_TYPE_NAME"));
                item.setPRODUCT_SUBTYPE_CODE(rs.getString("PRODUCT_SUBTYPE_CODE"));
                item.setPRODUCT_SUBTYPE_NAME(rs.getString("PRODUCT_SUBTYPE_NAME"));
                item.setPRODUCT_SUBSETTYPE_CODE(rs.getString("PRODUCT_SUBSETTYPE_CODE"));
                item.setPRODUCT_SUBSETTYPE_NAME(rs.getString("PRODUCT_SUBSETTYPE_NAME"));
                item.setPRODUCT_BRAND_CODE(rs.getString("PRODUCT_BRAND_CODE"));
                item.setPRODUCT_BRAND_NAME_TH(rs.getString("PRODUCT_BRAND_NAME_TH"));
                item.setPRODUCT_BRAND_NAME_EN(rs.getString("PRODUCT_BRAND_NAME_EN"));
                item.setPRODUCT_SUBBRAND_CODE(rs.getString("PRODUCT_SUBBRAND_CODE"));
                item.setPRODUCT_SUBBRAND_NAME_TH(rs.getString("PRODUCT_SUBBRAND_NAME_TH"));
                item.setPRODUCT_SUBBRAND_NAME_EN(rs.getString("PRODUCT_SUBBRAND_NAME_EN"));
                item.setPRODUCT_MODEL_CODE(rs.getString("PRODUCT_MODEL_CODE"));
                item.setPRODUCT_MODEL_NAME_TH(rs.getString("PRODUCT_MODEL_NAME_TH"));
                item.setPRODUCT_MODEL_NAME_EN(rs.getString("PRODUCT_MODEL_NAME_EN"));
                item.setIS_TAX_VALUE(rs.getInt("IS_TAX_VALUE"));
                item.setTAX_VALUE(rs.getFloat("TAX_VALUE"));
                item.setIS_TAX_VOLUMN(rs.getInt("IS_TAX_VOLUMN"));
                item.setTAX_VOLUMN(rs.getFloat("TAX_VOLUMN"));
                item.setTAX_VOLUMN_UNIT(rs.getString("TAX_VOLUMN_UNIT"));
                item.setLICENSE_PLATE(rs.getString("LICENSE_PLATE"));
                item.setENGINE_NO(rs.getString("ENGINE_NO"));
                item.setCHASSIS_NO(rs.getString("CHASSIS_NO"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setSUGAR(rs.getFloat("SUGAR"));
                item.setCO2(rs.getFloat("CO2"));
                item.setDEGREE(rs.getFloat("DEGREE"));
                item.setPRICE(rs.getFloat("PRICE"));
                item.setSIZES(rs.getFloat("SIZES"));
                item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
                item.setQUANTITY(rs.getFloat("QUANTITY"));
                item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
                item.setVOLUMN(rs.getFloat("VOLUMN"));
                item.setVOLUMN_UNIT(rs.getString("VOLUMN_UNIT"));
                item.setREMARK(rs.getString("REMARK"));
                item.setIS_DOMESTIC(rs.getInt("IS_DOMESTIC"));
                item.setIS_ILLEGAL(rs.getInt("IS_ILLEGAL"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setPRODUCT_DESC_MAS(rs.getString("PRODUCT_DESC_MAS"));
                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueNotice> getRevenueNotice(int INDICTMENT_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select " +
                        "    OPS_NOTICE.NOTICE_ID," +
                        "    OPS_NOTICE.ARREST_ID," +
                        "    OPS_NOTICE.NOTICE_CODE," +
                        "    OPS_NOTICE.IS_ARREST," +
                        "    OPS_NOTICE.IS_ACTIVE" +
                        "    from OPS_ARREST" +
                        "    inner join OPS_ARREST_INDICTMENT on OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    inner join OPS_NOTICE on OPS_NOTICE.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    where OPS_ARREST_INDICTMENT.INDICTMENT_ID = "+INDICTMENT_DETAIL_ID);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueNotice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueNotice mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueNotice item = new RevenueNotice();
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
                item.setIS_ARREST(rs.getInt("IS_ARREST"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueNotice> getRevenueNoticeByCon(int INDICTMENT_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select " +
                        "    OPS_NOTICE.NOTICE_ID," +
                        "    OPS_NOTICE.ARREST_ID," +
                        "    OPS_NOTICE.NOTICE_CODE," +
                        "    OPS_NOTICE.IS_ARREST," +
                        "    OPS_NOTICE.IS_ACTIVE" +
                        "    from OPS_ARREST" +
                        "    inner join OPS_ARREST_INDICTMENT on OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    inner join OPS_NOTICE on OPS_NOTICE.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    where OPS_ARREST_INDICTMENT.INDICTMENT_ID = "+INDICTMENT_DETAIL_ID);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueNotice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueNotice mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueNotice item = new RevenueNotice();
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
                item.setIS_ARREST(rs.getInt("IS_ARREST"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;
    }

    protected RevenueCompareDetail getRevenueCompareDetailByCompareDetailId(int COMPARE_DETAIL_ID,final String OFFICE_CODE) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "COMPARE_DETAIL_ID," +
                        "COMPARE_MAPPING_ID," +
                        "RECEIPT_OFFICE_ID," +
                        "APPROVE_OFFICE_ID," +
                        "MISTREAT_NO," +
                        "OLD_PAYMENT_FINE," +
                        "PAYMENT_FINE," +
                        "DIFFERENCE_PAYMENT_FINE," +
                        "TREASURY_MONEY," +
                        "BRIBE_MONEY," +
                        "REWARD_MONEY," +
                        "to_char(PAYMENT_FINE_DUE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as PAYMENT_FINE_DUE_DATE," +
                        "to_char(PAYMENT_VAT_DUE_DATE,'"+Pattern.FORMAT_DATETIME+"') as PAYMENT_VAT_DUE_DATE," +
                        "INSURANCE," +
                        "GAURANTEE," +
                        "to_char(PAYMENT_DATE,'"+Pattern.FORMAT_DATETIME+"') as PAYMENT_DATE," +
                        "RECEIPT_TYPE," +
                        "RECEIPT_BOOK_NO," +
                        "RECEIPT_NO," +
                        "RECEIPT_OFFICE_CODE," +
                        "RECEIPT_OFFICE_NAME," +
                        "APPROVE_OFFICE_CODE," +
                        "APPROVE_OFFICE_NAME," +
                        "to_char(APPROVE_DATE,'"+Pattern.FORMAT_DATETIME+"') as APPROVE_DATE," +
                        "APPROVE_TYPE," +
                        "COMMAND_NO," +
                        "to_char(COMMAND_DATE,'"+Pattern.FORMAT_DATETIME+"') as COMMAND_DATE," +
                        "REMARK_NOT_AGREE," +
                        "REMARK_NOT_APPROVE," +
                        "FACT," +
                        "COMPARE_REASON," +
                        "ADJUST_REASON," +
                        "COMPARE_TYPE," +
                        "IS_REQUEST," +
                        "IS_TEMP_RELEASE," +
                        "IS_INSURANCE," +
                        "IS_GAURANTEE," +
                        "IS_PAYMENT," +
                        "IS_REVENUE," +
                        "IS_AGREE," +
                        "IS_APPROVE," +
                        "IS_AUTHORITY," +
                        "IS_ACTIVE " +
                        " from OPS_COMPARE_DETAIL  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and COMPARE_DETAIL_ID = '"+COMPARE_DETAIL_ID+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<RevenueCompareDetail>() {

            public RevenueCompareDetail extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    RevenueCompareDetail item = new RevenueCompareDetail();
                    item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                    item.setCOMPARE_MAPPING_ID(rs.getInt("COMPARE_MAPPING_ID"));
                    item.setRECEIPT_OFFICE_ID(rs.getInt("RECEIPT_OFFICE_ID"));
                    item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
                    item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                    item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                    item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                    item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                    item.setRECEIPT_TYPE(rs.getInt("RECEIPT_TYPE"));
                    item.setRECEIPT_BOOK_NO(rs.getString("RECEIPT_BOOK_NO"));
                    item.setRECEIPT_NO(rs.getInt("RECEIPT_NO"));
                    item.setCOMPARE_TYPE(rs.getString("COMPARE_TYPE"));
                    item.setIS_PAYMENT(rs.getString("IS_PAYMENT"));
                    item.setIS_REVENUE(rs.getString("IS_REVENUE"));
                    item.setIS_AUTHORITY(rs.getInt("IS_AUTHORITY"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                   // item.setRevenueCompare(getRevenueCompare(rs.getInt("IS_REVENUE"),OFFICE_CODE));

                    return item;
                }

                return null;
            }
        });
    }


//    public List<RevenueCompare> getRevenueCompare(int IS_REVENUE ,String OFFICE_CODE) {
//        // TODO Auto-generated method stub
//
//
//        StringBuilder sqlBuilderDetail = new StringBuilder()
//                .append(" with temp as( " +
//                        "  select      " +
//                        "  OPS_COMPARE.COMPARE_ID," +
//                        "  OPS_COMPARE.LAWSUIT_ID," +
//                        "  OPS_COMPARE.OFFICE_ID," +
//                        "  OPS_COMPARE. TREASURY_RATE," +
//                        "  OPS_COMPARE.BRIBE_RATE," +
//                        "  OPS_COMPARE.REWARD_RATE," +
//                        "  OPS_COMPARE.OFFICE_CODE," +
//                        "  OPS_COMPARE.OFFICE_NAME," +
//                        "  OPS_COMPARE.COMPARE_NO," +
//                        "  OPS_COMPARE.COMPARE_NO_YEAR," +
//                        "  OPS_COMPARE.COMPARE_DATE," +
//                        "  OPS_COMPARE.IS_OUTSIDE," +
//                        "  OPS_COMPARE.IS_ACTIVE" +
//                        "  from " +
//                        "  OPS_COMPARE" +
//                        "  left join OPS_COMPARE_STAFF on OPS_COMPARE.COMPARE_ID = OPS_COMPARE_STAFF.COMPARE_ID" +
//                        "  left join OPS_COMPARE_MAPPING on OPS_COMPARE.COMPARE_ID = OPS_COMPARE_MAPPING.COMPARE_ID" +
//                        "  left join OPS_COMPARE_DETAIL on OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID = OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
//                        "  left join OPS_LAWSUIT_DETAIL on OPS_COMPARE_MAPPING.INDICTMENT_DETAIL_ID = OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID" +
//                        "  left join OPS_ARREST_INDICTMENT_DETAIL on OPS_COMPARE_MAPPING.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID" +
//                        "  left join OPS_ARREST_LAWBREAKER on OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
//                        "  left join OPS_ARREST_INDICTMENT_PRODUCT on OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID" +
//                        "  left join OPS_ARREST_PRODUCT on OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID = OPS_ARREST_PRODUCT.PRODUCT_ID" +
//                        "  where OPS_COMPARE_DETAIL.IS_ACTIVE = '1'      " +
//                        "  and OPS_COMPARE_DETAIL.IS_ACTIVE = '1'     " +
//                        "  and OPS_COMPARE.IS_ACTIVE = '1'" +
//                        "  and OPS_COMPARE_STAFF.IS_ACTIVE = '1'     " +
//                        "  and OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = '1'" +
//                        "  and OPS_ARREST_LAWBREAKER.IS_ACTIVE = '1'" +
//                        "  and OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE = '1'" +
//                        "  and OPS_ARREST_PRODUCT.IS_ACTIVE = '1'" +
//                        "  and OPS_COMPARE_DETAIL.IS_REVENUE = '"+IS_REVENUE+"'      " +
//                        "  and OPS_COMPARE_STAFF.CONTRIBUTOR_ID = '28'     " +
//                        "  and OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE = '"+OFFICE_CODE+"'     " +
//                        "  order by OPS_COMPARE_DETAIL.RECEIPT_NO asc" +
//                        ")select DISTINCT * from temp");
//
//        log.info("[SQL]  : " + sqlBuilderDetail.toString());
//        System.out.println("[SQL] [AdjustCompareListgetByKeyword]  : " + sqlBuilderDetail.toString());
//
//        @SuppressWarnings("unchecked")
//        List<RevenueCompare> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {
//
//            public RevenueCompare mapRow(ResultSet rs, int rowNum) throws SQLException {
//                RevenueCompare item = new RevenueCompare();
//                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
//                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
//                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
//                item.setTREASURY_RATE(rs.getFloat("TREASURY_RATE"));
//                item.setBRIBE_RATE(rs.getFloat("BRIBE_RATE"));
//                item.setREWARD_RATE(rs.getFloat("REWARD_RATE"));
//                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
//                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
//                item.setCOMPARE_NO(rs.getInt("COMPARE_NO"));
//                item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
//                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
//                item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
//                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
//
//                item.setRevenueCompareStaff(getRevenueCompareStaff(rs.getInt("COMPARE_ID")));
//                item.setRevenueCompareMapping(getRevenueCompareMapping(rs.getInt("COMPARE_ID")));
//                return item;
//            }
//        });
//
//        return dataList;
//    }

    protected List<NewRevenueLawsuitDetail> getNewRevenueLawsuitDetail(int LAWSUIT_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select DISTINCT " +
                        "     OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID," +
                        "     OPS_LAWSUIT_DETAIL.LAWSUIT_ID," +
                        "     OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID," +
                        "     OPS_LAWSUIT_DETAIL.COURT_ID," +
                        "     OPS_LAWSUIT_DETAIL.COURT_NAME," +
                        "     OPS_LAWSUIT_DETAIL.UNDECIDE_NO_1," +
                        "     OPS_LAWSUIT_DETAIL.UNDECIDE_NO_YEAR_1," +
                        "     OPS_LAWSUIT_DETAIL.DECIDE_NO_1," +
                        "     OPS_LAWSUIT_DETAIL.DECIDE_NO_YEAR_1," +
                        "     OPS_LAWSUIT_DETAIL.UNDECIDE_NO_2," +
                        "     OPS_LAWSUIT_DETAIL.UNDECIDE_NO_YEAR_2," +
                        "     OPS_LAWSUIT_DETAIL.DECIDE_NO_2," +
                        "     OPS_LAWSUIT_DETAIL.DECIDE_NO_YEAR_2," +
                        "     OPS_LAWSUIT_DETAIL.JUDGEMENT_NO," +
                        "     OPS_LAWSUIT_DETAIL.JUDGEMENT_NO_YEAR," +
                        "     OPS_LAWSUIT_DETAIL.JUDGEMENT_DATE," +
                        "     OPS_LAWSUIT_DETAIL.FINE," +
                        "     OPS_LAWSUIT_DETAIL.IS_PAYONCE," +
                        "     OPS_LAWSUIT_DETAIL.FINE_DATE," +
                        "     OPS_LAWSUIT_DETAIL.PAYMENT_PERIOD," +
                        "     OPS_LAWSUIT_DETAIL.PAYMENT_DATE," +
                        "     OPS_LAWSUIT_DETAIL.UNJUDGEMENT_NO," +
                        "     OPS_LAWSUIT_DETAIL.UNJUDGEMENT_NO_YEAR," +
                        "     OPS_ARREST_INDICTMENT.ARREST_ID," +
                        "     OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
                        "     case when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 0 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is not null then " +
                        "        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
                        "          OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)    " +
                        "     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 0 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is null then  " +
                        "        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
                        "          OPS_ARREST_LAWBREAKER.TITLE_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)  " +
                        "     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 1 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN is not null then  " +
                        "        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
                        "          OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)" +
                        "     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 1 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN is null then  " +
                        "        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
                        "          OPS_ARREST_LAWBREAKER.TITLE_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)" +
                        "     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 2 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is not null then  OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH ||  OPS_ARREST_LAWBREAKER.COMPANY_NAME  " +
                        "     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 2 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is null then  OPS_ARREST_LAWBREAKER.TITLE_NAME_TH ||  OPS_ARREST_LAWBREAKER.COMPANY_NAME end as LAWBREAKER_NAME" +
                        "     from " +
                        "     OPS_LAWSUIT" +
                        "     inner join OPS_LAWSUIT_DETAIL on OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_ID " +
                        "     and OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE = 0" +
                        "     and OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "     inner join OPS_PAYMENT on OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID = OPS_PAYMENT.LAWSUIT_DETAIL_ID " +
                        "     and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "     and OPS_PAYMENT.IS_REVENUE = 0" +
                        "     left join OPS_ARREST_INDICTMENT_DETAIL on OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID" +
                        "     left join OPS_ARREST_LAWBREAKER on OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
                        "     and OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
                        "     inner join OPS_ARREST_INDICTMENT on OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID " +
                        "     and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "     where OPS_LAWSUIT.LAWSUIT_ID = '"+LAWSUIT_ID+"'" +
                        "     order by OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID DESC");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NewRevenueLawsuitDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NewRevenueLawsuitDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                NewRevenueLawsuitDetail item = new NewRevenueLawsuitDetail();
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
                item.setCOURT_ID(rs.getInt("COURT_ID"));
                item.setCOURT_NAME(rs.getString("COURT_NAME"));
                item.setUNDECIDE_NO_1(rs.getInt("UNDECIDE_NO_1"));
                item.setUNDECIDE_NO_YEAR_1(rs.getString("UNDECIDE_NO_YEAR_1"));
                item.setDECIDE_NO_1(rs.getInt("DECIDE_NO_1"));
                item.setDECIDE_NO_YEAR_1(rs.getString("DECIDE_NO_YEAR_1"));
                item.setUNDECIDE_NO_2(rs.getInt("UNDECIDE_NO_2"));
                item.setUNDECIDE_NO_YEAR_2(rs.getString("UNDECIDE_NO_YEAR_2"));
                item.setDECIDE_NO_2(rs.getInt("DECIDE_NO_2"));
                item.setDECIDE_NO_YEAR_2(rs.getString("DECIDE_NO_YEAR_2"));
                item.setJUDGEMENT_NO(rs.getInt("JUDGEMENT_NO"));
                item.setJUDGEMENT_NO_YEAR(rs.getString("JUDGEMENT_NO_YEAR"));
                item.setJUDGEMENT_DATE(rs.getString("JUDGEMENT_DATE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setIS_PAYONCE(rs.getInt("IS_PAYONCE"));
                item.setFINE_DATE(rs.getString("FINE_DATE"));
                item.setPAYMENT_PERIOD(rs.getString("PAYMENT_PERIOD"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setUNJUDGEMENT_NO(rs.getString("UNJUDGEMENT_NO"));
                item.setUNJUDGEMENT_NO_YEAR(rs.getString("UNJUDGEMENT_NO_YEAR"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
                item.setLAWBREAKER_NAME(rs.getString("LAWBREAKER_NAME"));
                item.setPayment(getNewRevenuePayment(rs.getInt("LAWSUIT_DETAIL_ID")));
                return item;
            }
        });

        return dataList;
    }

    protected List<NewRevenueLawsuitDetail> getNewRevenueLawsuitDetailByCourt(int LAWSUIT_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select DISTINCT " +
                        "     OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID," +
                        "     OPS_LAWSUIT_DETAIL.LAWSUIT_ID," +
                        "     OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID," +
                        "     OPS_LAWSUIT_DETAIL.COURT_ID," +
                        "     OPS_LAWSUIT_DETAIL.COURT_NAME," +
                        "     OPS_LAWSUIT_DETAIL.UNDECIDE_NO_1," +
                        "     OPS_LAWSUIT_DETAIL.UNDECIDE_NO_YEAR_1," +
                        "     OPS_LAWSUIT_DETAIL.DECIDE_NO_1," +
                        "     OPS_LAWSUIT_DETAIL.DECIDE_NO_YEAR_1," +
                        "     OPS_LAWSUIT_DETAIL.UNDECIDE_NO_2," +
                        "     OPS_LAWSUIT_DETAIL.UNDECIDE_NO_YEAR_2," +
                        "     OPS_LAWSUIT_DETAIL.DECIDE_NO_2," +
                        "     OPS_LAWSUIT_DETAIL.DECIDE_NO_YEAR_2," +
                        "     OPS_LAWSUIT_DETAIL.JUDGEMENT_NO," +
                        "     OPS_LAWSUIT_DETAIL.JUDGEMENT_NO_YEAR," +
                        "     OPS_LAWSUIT_DETAIL.JUDGEMENT_DATE," +
                        "     OPS_LAWSUIT_DETAIL.FINE," +
                        "     OPS_LAWSUIT_DETAIL.IS_PAYONCE," +
                        "     OPS_LAWSUIT_DETAIL.FINE_DATE," +
                        "     OPS_LAWSUIT_DETAIL.PAYMENT_PERIOD," +
                        "     OPS_LAWSUIT_DETAIL.PAYMENT_DATE," +
                        "     OPS_LAWSUIT_DETAIL.UNJUDGEMENT_NO," +
                        "     OPS_LAWSUIT_DETAIL.UNJUDGEMENT_NO_YEAR," +
                        "     OPS_ARREST_LAWBREAKER.ARREST_ID," +
                        "     OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
                        "     OPS_ARREST_LAWBREAKER.PERSON_TYPE," +
                        "     OPS_ARREST_LAWBREAKER.ENTITY_TYPE," +
                        "     case when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 0 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is not null then " +
                        "        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
                        "          OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)    " +
                        "     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 0 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is null then  " +
                        "        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
                        "          OPS_ARREST_LAWBREAKER.TITLE_NAME_TH || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)  " +
                        "     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 1 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN is not null then  " +
                        "        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
                        "          OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)" +
                        "     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 1 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN is null then  " +
                        "        ( case when OPS_ARREST_LAWBREAKER.MIDDLE_NAME is null or  OPS_ARREST_LAWBREAKER.MIDDLE_NAME  = 'null' then OPS_ARREST_LAWBREAKER.TITLE_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME else" +
                        "          OPS_ARREST_LAWBREAKER.TITLE_NAME_EN || OPS_ARREST_LAWBREAKER.FIRST_NAME ||  ' '  ||OPS_ARREST_LAWBREAKER.MIDDLE_NAME || ' '  || OPS_ARREST_LAWBREAKER.LAST_NAME end)" +
                        "     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 2 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is not null then  OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH ||  OPS_ARREST_LAWBREAKER.COMPANY_NAME  " +
                        "     when OPS_ARREST_LAWBREAKER.PERSON_TYPE = 2 and OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH is null then  OPS_ARREST_LAWBREAKER.TITLE_NAME_TH ||  OPS_ARREST_LAWBREAKER.COMPANY_NAME end as LAWBREAKER_NAME ," +
                        "     OPS_ARREST_LAWBREAKER.MISTREAT_NO " +
                        "     from OPS_REVENUE_DETAIL" +
                        "     INNER JOIN OPS_LAWSUIT_DETAIL on OPS_REVENUE_DETAIL.LAWSUIT_DETAIL_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID and OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1 " +
                        "     INNER JOIN OPS_LAWSUIT on OPS_LAWSUIT_DETAIL.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID and OPS_LAWSUIT.IS_ACTIVE = 1 " +
                        "     INNER JOIN OPS_PAYMENT on OPS_REVENUE_DETAIL.PAYMENT_ID = OPS_PAYMENT.PAYMENT_ID and OPS_PAYMENT.IS_ACTIVE = 1 " +
                        "     LEFT  JOIN OPS_ARREST_INDICTMENT_DETAIL on OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID and OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1 " +
                        "     LEFT  JOIN OPS_ARREST_LAWBREAKER on OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID and OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1  " +
                        "     INNER JOIN OPS_ARREST_INDICTMENT on OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1 " +
                        "     where OPS_REVENUE_DETAIL.IS_ACTIVE = 1 and OPS_LAWSUIT.LAWSUIT_ID = '"+LAWSUIT_ID+"'" +
                        "     order by OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID asc ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NewRevenueLawsuitDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NewRevenueLawsuitDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                NewRevenueLawsuitDetail item = new NewRevenueLawsuitDetail();
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
                item.setCOURT_ID(rs.getInt("COURT_ID"));
                item.setCOURT_NAME(rs.getString("COURT_NAME"));
                item.setUNDECIDE_NO_1(rs.getInt("UNDECIDE_NO_1"));
                item.setUNDECIDE_NO_YEAR_1(rs.getString("UNDECIDE_NO_YEAR_1"));
                item.setDECIDE_NO_1(rs.getInt("DECIDE_NO_1"));
                item.setDECIDE_NO_YEAR_1(rs.getString("DECIDE_NO_YEAR_1"));
                item.setUNDECIDE_NO_2(rs.getInt("UNDECIDE_NO_2"));
                item.setUNDECIDE_NO_YEAR_2(rs.getString("UNDECIDE_NO_YEAR_2"));
                item.setDECIDE_NO_2(rs.getInt("DECIDE_NO_2"));
                item.setDECIDE_NO_YEAR_2(rs.getString("DECIDE_NO_YEAR_2"));
                item.setJUDGEMENT_NO(rs.getInt("JUDGEMENT_NO"));
                item.setJUDGEMENT_NO_YEAR(rs.getString("JUDGEMENT_NO_YEAR"));
                item.setJUDGEMENT_DATE(rs.getString("JUDGEMENT_DATE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setIS_PAYONCE(rs.getInt("IS_PAYONCE"));
                item.setFINE_DATE(rs.getString("FINE_DATE"));
                item.setPAYMENT_PERIOD(rs.getString("PAYMENT_PERIOD"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setUNJUDGEMENT_NO(rs.getString("UNJUDGEMENT_NO"));
                item.setUNJUDGEMENT_NO_YEAR(rs.getString("UNJUDGEMENT_NO_YEAR"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
                item.setLAWBREAKER_NAME(rs.getString("LAWBREAKER_NAME"));
                item.setPERSON_TYPE(rs.getInt("PERSON_TYPE"));
                item.setENTITY_TYPE(rs.getInt("ENTITY_TYPE"));
                item.setMISTREAT_NO(rs.getInt("MISTREAT_NO"));

                item.setPayment(getNewRevenuePaymentByCourt(rs.getInt("LAWSUIT_DETAIL_ID")));
                item.setNotice(getNewRevenueNotice(rs.getInt("ARREST_ID")));
                return item;
            }
        });

        return dataList;
    }

    protected List<NewRevenuePayment> getNewRevenuePayment(int LAWSUIT_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("     select DISTINCT " +
                        "     OPS_PAYMENT.*, " +
                        "     OPS_PAYMENT.PAYMENT_PERIOD_NO || '/' || OPS_LAWSUIT_DETAIL.PAYMENT_PERIOD as PAYMENT_PAY," +
                        "     case when OPS_PAYMENT.PAYMENT_CHANNEL = '1' then 'เงินสด'" +
                        "     when OPS_PAYMENT.PAYMENT_CHANNEL = '2' then 'เช็คพาณิชย์' " +
                        "     when OPS_PAYMENT.PAYMENT_CHANNEL = '5' then 'บัตรเดบิต' " +
                        "     when OPS_PAYMENT.PAYMENT_CHANNEL = '6' then 'บัตรเครดิต' " +
                        "     when OPS_PAYMENT.PAYMENT_CHANNEL = '8' then 'โอนเงินอิเล็กทรอนิกส์' end  AS PAYMENT_CHANNEL_NAME," +
                        "     case when OPS_NOTICE.NOTICE_CODE is not null then OPS_PAYMENT.FINE/2 else 0 end as BRIBE_MONEY," +
                        "     case when OPS_NOTICE.NOTICE_CODE is not null then OPS_PAYMENT.FINE/2 else OPS_PAYMENT.FINE end as REWARD_MONEY," +
                        "     case when OPS_PAYMENT.FINE is not null then 0 end as TREASURY_MONEY" +
                        "     from OPS_LAWSUIT" +
                        "     inner join OPS_LAWSUIT_DETAIL on OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_ID " +
                        "     and OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE = 0" +
                        "     and OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "     inner join OPS_PAYMENT on OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID = OPS_PAYMENT.LAWSUIT_DETAIL_ID " +
                        "     and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "     and OPS_PAYMENT.IS_REVENUE = 0" +
                        "     left join OPS_ARREST_INDICTMENT_DETAIL on OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID" +
                        "     left join OPS_ARREST_LAWBREAKER on OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
                        "     and OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
                        "     inner join OPS_ARREST_INDICTMENT on OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID " +
                        "     and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "     left join OPS_NOTICE on OPS_ARREST_INDICTMENT.ARREST_ID = OPS_NOTICE.ARREST_ID and OPS_NOTICE.IS_ACTIVE = 1" +
                        "     where OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID = '"+LAWSUIT_DETAIL_ID+"'" +
                        "     and OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
                        "     order by OPS_PAYMENT.PAYMENT_DATE asc");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NewRevenuePayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NewRevenuePayment mapRow(ResultSet rs, int rowNum) throws SQLException {

                NewRevenuePayment item = new NewRevenuePayment();
                item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setPAYMENT_PERIOD_NO(rs.getInt("PAYMENT_PERIOD_NO"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setIS_REQUEST_REWARD(rs.getInt("IS_REQUEST_REWARD"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setPAYMENT_CHANNEL(rs.getInt("PAYMENT_CHANNEL"));
                item.setPAYMENT_BANK(rs.getString("PAYMENT_BANK"));
                item.setPAYMENT_REF_NO(rs.getString("PAYMENT_REF_NO"));
                item.setIS_REVENUE(rs.getInt("IS_REVENUE"));
                item.setPAYMENT_PAY(rs.getString("PAYMENT_PAY"));
                item.setPAYMENT_CHANNEL_NAME(rs.getString("PAYMENT_CHANNEL_NAME"));
                item.setBRIBE_MONEY(rs.getInt("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getInt("REWARD_MONEY"));
                item.setTREASURY_MONEY(rs.getInt("TREASURY_MONEY"));

                return item;
            }
        });

        return dataList;
    }

    protected List<NewRevenuePayment> getNewRevenuePaymentByCourt(int LAWSUIT_DETAIL_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("     select DISTINCT " +
                        "     OPS_PAYMENT.*, " +
                        "     OPS_PAYMENT.PAYMENT_PERIOD_NO || '/' || OPS_LAWSUIT_DETAIL.PAYMENT_PERIOD as PAYMENT_PAY," +
                        "     case when OPS_PAYMENT.PAYMENT_CHANNEL = '1' then 'เงินสด'" +
                        "     when OPS_PAYMENT.PAYMENT_CHANNEL = '2' then 'เช็คพาณิชย์' " +
                        "     when OPS_PAYMENT.PAYMENT_CHANNEL = '5' then 'บัตรเดบิต' " +
                        "     when OPS_PAYMENT.PAYMENT_CHANNEL = '6' then 'บัตรเครดิต' " +
                        "     when OPS_PAYMENT.PAYMENT_CHANNEL = '8' then 'โอนเงินอิเล็กทรอนิกส์' end  AS PAYMENT_CHANNEL_NAME," +
                        "     case when OPS_NOTICE.NOTICE_CODE is not null then OPS_PAYMENT.FINE/2 else 0 end as BRIBE_MONEY," +
                        "     case when OPS_NOTICE.NOTICE_CODE is not null then OPS_PAYMENT.FINE/2 else OPS_PAYMENT.FINE end as REWARD_MONEY," +
                        "     case when OPS_PAYMENT.FINE is not null then 0 end as TREASURY_MONEY" +
                        "     from OPS_REVENUE_DETAIL" +
                        "     INNER JOIN OPS_LAWSUIT_DETAIL on OPS_REVENUE_DETAIL.LAWSUIT_DETAIL_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID and OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1 " +
                        "     INNER JOIN OPS_LAWSUIT on OPS_LAWSUIT_DETAIL.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID and OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "     INNER JOIN OPS_PAYMENT on OPS_REVENUE_DETAIL.PAYMENT_ID = OPS_PAYMENT.PAYMENT_ID and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "     LEFT  JOIN OPS_ARREST_INDICTMENT_DETAIL on OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID and OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
                        "     INNER JOIN OPS_ARREST_INDICTMENT on OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1 " +
                        "     LEFT  JOIN OPS_NOTICE on OPS_ARREST_INDICTMENT.ARREST_ID = OPS_NOTICE.ARREST_ID and OPS_NOTICE.IS_ACTIVE = 1 " +
                        "     where OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID = '"+LAWSUIT_DETAIL_ID+"'" +
                        "     and OPS_REVENUE_DETAIL.IS_ACTIVE = 1" +
                        "     order by OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID asc");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NewRevenuePayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NewRevenuePayment mapRow(ResultSet rs, int rowNum) throws SQLException {

                NewRevenuePayment item = new NewRevenuePayment();
                item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setPAYMENT_PERIOD_NO(rs.getInt("PAYMENT_PERIOD_NO"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setIS_REQUEST_REWARD(rs.getInt("IS_REQUEST_REWARD"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setPAYMENT_CHANNEL(rs.getInt("PAYMENT_CHANNEL"));
                item.setPAYMENT_BANK(rs.getString("PAYMENT_BANK"));
                item.setPAYMENT_REF_NO(rs.getString("PAYMENT_REF_NO"));
                item.setIS_REVENUE(rs.getInt("IS_REVENUE"));
                item.setPAYMENT_PAY(rs.getString("PAYMENT_PAY"));
                item.setPAYMENT_CHANNEL_NAME(rs.getString("PAYMENT_CHANNEL_NAME"));
                item.setBRIBE_MONEY(rs.getInt("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getInt("REWARD_MONEY"));
                item.setTREASURY_MONEY(rs.getInt("TREASURY_MONEY"));

                return item;
            }
        });

        return dataList;
    }

    protected List<NewRevenueNotice> getNewRevenueNotice(int ARREST_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select DISTINCT OPS_NOTICE.*" +
                        " from OPS_NOTICE" +
                        " where OPS_NOTICE.ARREST_ID = '"+ARREST_ID+"'" +
                        " and OPS_NOTICE.IS_ACTIVE = 1" +
                        " order by OPS_NOTICE.NOTICE_CODE asc");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NewRevenueNotice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NewRevenueNotice mapRow(ResultSet rs, int rowNum) throws SQLException {

                NewRevenueNotice item = new NewRevenueNotice();
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setNOTICE_DATE(rs.getString("NOTICE_DATE"));
                item.setNOTICE_DUE(rs.getInt("NOTICE_DUE"));
                item.setNOTICE_DUE_DATE(rs.getString("NOTICE_DUE_DATE"));
                item.setCOMMUNICATION_CHANNEL(rs.getInt("COMMUNICATION_CHANNEL"));
                item.setIS_ARREST(rs.getInt("IS_ARREST"));
                item.setIS_AUTHORITY(rs.getInt("IS_AUTHORITY"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setIS_MATCH(rs.getInt("IS_MATCH"));
                item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                item.setCREATE_USER_ACCOUNT_ID(rs.getInt("CREATE_USER_ACCOUNT_ID"));
                item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                item.setUPDATE_USER_ACCOUNT_ID(rs.getInt("UPDATE_USER_ACCOUNT_ID"));

                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueSearchStatus1Detail> getRevenueSearchStatus1Detail(int REVENUE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select DISTINCT" +
                        "    OPS_REVENUE_DETAIL.REVENUE_DETAIL_ID," +
                        "    OPS_REVENUE_DETAIL.COMPARE_DETAIL_ID, " +
                        "    OPS_REVENUE_DETAIL.LAWSUIT_DETAIL_ID," +
                        "    OPS_REVENUE_DETAIL.PAYMENT_ID," +
                        "    OPS_REVENUE_DETAIL.REVENUE_STATUS" +
                        "    from OPS_REVENUE_DETAIL " +
                        "    INNER JOIN OPS_PAYMENT ON OPS_PAYMENT.PAYMENT_ID = OPS_REVENUE_DETAIL.PAYMENT_ID and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "    where OPS_REVENUE_DETAIL.IS_ACTIVE = 1" +
                        "    and OPS_REVENUE_DETAIL.REVENUE_ID = '"+REVENUE_ID+"'" +
                        "    order by OPS_REVENUE_DETAIL.REVENUE_ID asc");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueSearchStatus1Detail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueSearchStatus1Detail mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueSearchStatus1Detail item = new RevenueSearchStatus1Detail();
                item.setREVENUE_DETAIL_ID(rs.getInt("REVENUE_DETAIL_ID"));
                item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
                item.setREVENUE_STATUS(rs.getInt("REVENUE_STATUS"));
                return item;
            }
        });

        return dataList;
    }

    protected List<RevenueIncPaymentType> getRevenueIncPaymentType(int INC_PAYMENT_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select * from inc_payment_type where is_active = 1 and inc_payment_id =  "+INC_PAYMENT_ID);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueIncPaymentType> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RevenueIncPaymentType mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueIncPaymentType item = new RevenueIncPaymentType();
                item.setINC_PAYMENT_TYPE_ID(rs.getInt("INC_PAYMENT_TYPE_ID"));
                item.setINC_PAYMENT_ID(rs.getInt("INC_PAYMENT_ID"));
                item.setPAYMENT_TYPE(rs.getInt("PAYMENT_TYPE"));
                item.setBANK_CODE(rs.getString("BANK_CODE"));
                item.setBRANCH_CODE(rs.getString("BRANCH_CODE"));
                item.setCHEQUE_TYPE(rs.getString("CHEQUE_TYPE"));
                item.setCHEQUE_FLAG(rs.getString("CHEQUE_FLAG"));
                item.setCHWQUE_NO(rs.getString("CHWQUE_NO"));
                item.setCHEQUE_DATE(rs.getString("CHEQUE_DATE"));
                item.setPAYMENT_AMT(rs.getInt("PAYMENT_AMT"));
                item.setADJUST_TYPE(rs.getInt("ADJUST_TYPE"));
                item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;
    }




}
