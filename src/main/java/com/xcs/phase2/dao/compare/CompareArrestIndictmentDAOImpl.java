package com.xcs.phase2.dao.compare;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.compare.CompareArrestIndictment;
import com.xcs.phase2.model.compare.CompareArrestgetByIndictment;
import com.xcs.phase2.request.compare.CompareArrestgetByIndictmentIDReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class CompareArrestIndictmentDAOImpl extends CompareExt implements CompareArrestIndictmentDAO{

    private static final Logger log = LoggerFactory.getLogger(CompareListDAOImpl.class);

    @Override
    public List<CompareArrestIndictment> CompareArrestgetByIndictmentID(CompareArrestgetByIndictmentIDReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT " +
                        "OPS_ARREST_INDICTMENT.INDICTMENT_ID," +
                        "OPS_LAWSUIT.LAWSUIT_ID," +
                        "OPS_PROVE.PROVE_ID," +
                        "OPS_ARREST.ARREST_ID," +
                        "OPS_ARREST.ARREST_CODE," +
                        "OPS_ARREST.OFFICE_CODE," +
                        "OPS_ARREST.OFFICE_NAME," +
                        "to_char(OPS_ARREST.OCCURRENCE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as OCCURRENCE_DATE," +
                        "OPS_ARREST_STAFF.TITLE_NAME_TH ACCUSER_TITLE_NAME_TH," +
                        "OPS_ARREST_STAFF.TITLE_NAME_EN ACCUSER_TITLE_NAME_EN," +
                        "OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH ACCUSER_TITLE_SHORT_NAME_TH," +
                        "OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN ACCUSER_TITLE_SHORT_NAME_EN," +
                        "OPS_ARREST_STAFF.FIRST_NAME ACCUSER_FIRST_NAME," +
                        "OPS_ARREST_STAFF.LAST_NAME ACCUSER_LAST_NAME," +
                        "OPS_ARREST_STAFF.OPERATION_POS_CODE ACCUSER_OPERATION_POS_CODE," +
                        "OPS_ARREST_STAFF.OPREATION_POS_NAME ACCUSER_OPREATION_POS_NAME," +
                        "OPS_ARREST_STAFF.OPREATION_POS_LEVEL ACCUSER_OPREATION_POS_LEVEL," +
                        "OPS_ARREST_STAFF.OPERATION_POS_LEVEL_NAME ACCUSER_OPERATION_POS_LEVEL_NAME," +
                        "OPS_ARREST_STAFF.OPERATION_DEPT_CODE ACCUSER_OPERATION_DEPT_CODE," +
                        "OPS_ARREST_STAFF.OPERATION_DEPT_NAME ACCUSER_OPERATION_DEPT_NAME," +
                        "OPS_ARREST_STAFF.OPERATION_DEPT_LEVEL ACCUSER_OPERATION_DEPT_LEVEL," +
                        "OPS_ARREST_STAFF.OPERATION_UNDER_DEPT_CODE ACCUSER_OPERATION_UNDER_DEPT_CODE," +
                        "OPS_ARREST_STAFF.OPERATION_UNDER_DEPT_NAME ACCUSER_OPERATION_UNDER_DEPT_NAME," +
                        "OPS_ARREST_STAFF.OPERATION_UNDER_DEPT_LEVEL ACCUSER_OPERATION_UNDER_DEPT_LEVEL," +
                        "OPS_ARREST_STAFF.OPERATION_WORK_DEPT_CODE ACCUSER_OPERATION_WORK_DEPT_CODE," +
                        "OPS_ARREST_STAFF.OPERATION_WORK_DEPT_NAME ACCUSER_OPERATION_WORK_DEPT_NAME," +
                        "OPS_ARREST_STAFF.OPERATION_WORK_DEPT_LEVEL ACCUSER_OPERATION_WORK_DEPT_LEVEL," +
                        "OPS_ARREST_STAFF.OPERATION_OFFICE_CODE ACCUSER_OPERATION_OFFICE_CODE," +
                        "OPS_ARREST_STAFF.OPERATION_OFFICE_NAME ACCUSER_OPERATION_OFFICE_NAME," +
                        "OPS_ARREST_STAFF.OPERATION_OFFICE_SHORT_NAME ACCUSER_OPERATION_OFFICE_SHORT_NAME," +
                        "OPS_ARREST_STAFF.MANAGEMENT_POS_CODE ACCUSER_MANAGEMENT_POS_CODE," +
                        "OPS_ARREST_STAFF.MANAGEMENT_POS_NAME ACCUSER_MANAGEMENT_POS_NAME," +
                        "OPS_ARREST_STAFF.MANAGEMENT_POS_LEVEL ACCUSER_MANAGEMENT_POS_LEVEL," +
                        "OPS_ARREST_STAFF.MANAGEMENT_POS_LEVEL_NAME ACCUSER_MANAGEMENT_POS_LEVEL_NAME," +
                        "OPS_ARREST_STAFF.MANAGEMENT_DEPT_CODE ACCUSER_MANAGEMENT_DEPT_CODE," +
                        "OPS_ARREST_STAFF.MANAGEMENT_DEPT_NAME ACCUSER_MANAGEMENT_DEPT_NAME," +
                        "OPS_ARREST_STAFF.MANAGEMENT_DEPT_LEVEL ACCUSER_MANAGEMENT_DEPT_LEVEL," +
                        "OPS_ARREST_STAFF.MANAGEMENT_UNDER_DEPT_CODE ACCUSER_MANAGEMENT_UNDER_DEPT_CODE," +
                        "OPS_ARREST_STAFF.MANAGEMENT_UNDER_DEPT_NAME ACCUSER_MANAGEMENT_UNDER_DEPT_NAME," +
                        "OPS_ARREST_STAFF.MANAGEMENT_UNDER_DEPT_LEVEL ACCUSER_MANAGEMENT_UNDER_DEPT_LEVEL," +
                        "OPS_ARREST_STAFF.MANAGEMENT_WORK_DEPT_CODE ACCUSER_MANAGEMENT_WORK_DEPT_CODE," +
                        "OPS_ARREST_STAFF.MANAGEMENT_WORK_DEPT_NAME ACCUSER_MANAGEMENT_WORK_DEPT_NAME," +
                        "OPS_ARREST_STAFF.MANAGEMENT_WORK_DEPT_LEVEL ACCUSER_MANAGEMENT_WORK_DEPT_LEVEL," +
                        "OPS_ARREST_STAFF.MANAGEMENT_OFFICE_CODE ACCUSER_MANAGEMENT_OFFICE_CODE," +
                        "OPS_ARREST_STAFF.MANAGEMENT_OFFICE_NAME ACCUSER_MANAGEMENT_OFFICE_NAME," +
                        "OPS_ARREST_STAFF.MANAGEMENT_OFFICE_SHORT_NAME ACCUSER_MANAGEMENT_OFFICE_SHORT_NAME," +
                        "OPS_ARREST_STAFF.REPRESENT_POS_CODE ACCUSER_REPRESENT_POS_CODE," +
                        "OPS_ARREST_STAFF.REPRESENT_POS_NAME ACCUSER_REPRESENT_POS_NAME," +
                        "OPS_ARREST_STAFF.REPRESENT_POS_LEVEL ACCUSER_REPRESENT_POS_LEVEL," +
                        "OPS_ARREST_STAFF.REPRESENT_POS_LEVEL_NAME ACCUSER_REPRESENT_POS_LEVEL_NAME," +
                        "OPS_ARREST_STAFF.REPRESENT_DEPT_CODE ACCUSER_REPRESENT_DEPT_CODE," +
                        "OPS_ARREST_STAFF.REPRESENT_DEPT_NAME ACCUSER_REPRESENT_DEPT_NAME," +
                        "OPS_ARREST_STAFF.REPRESENT_DEPT_LEVEL ACCUSER_REPRESENT_DEPT_LEVEL," +
                        "OPS_ARREST_STAFF.REPRESENT_UNDER_DEPT_CODE ACCUSER_REPRESENT_UNDER_DEPT_CODE," +
                        "OPS_ARREST_STAFF.REPRESENT_UNDER_DEPT_NAME ACCUSER_REPRESENT_UNDER_DEPT_NAME," +
                        "OPS_ARREST_STAFF.REPRESENT_UNDER_DEPT_LEVEL ACCUSER_REPRESENT_UNDER_DEPT_LEVEL," +
                        "OPS_ARREST_STAFF.REPRESENT_WORK_DEPT_CODE ACCUSER_REPRESENT_WORK_DEPT_CODE," +
                        "OPS_ARREST_STAFF.REPRESENT_WORK_DEPT_NAME ACCUSER_REPRESENT_WORK_DEPT_NAME," +
                        "OPS_ARREST_STAFF.REPRESENT_WORK_DEPT_LEVEL ACCUSER_REPRESENT_WORK_DEPT_LEVEL," +
                        "OPS_ARREST_STAFF.REPRESENT_OFFICE_CODE ACCUSER_REPRESENT_OFFICE_CODE," +
                        "OPS_ARREST_STAFF.REPRESENT_OFFICE_NAME ACCUSER_REPRESENT_OFFICE_NAME," +
                        "OPS_ARREST_STAFF.REPRESENT_OFFICE_SHORT_NAME ACCUSER_REPRESENT_OFFICE_SHORT_NAME," +
                        "MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH," +
                        "MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN," +
                        "MAS_DISTRICT.DISTRICT_NAME_TH," +
                        "MAS_DISTRICT.DISTRICT_NAME_EN," +
                        "MAS_PROVINCE.PROVINCE_NAME_TH," +
                        "MAS_PROVINCE.PROVINCE_NAME_EN," +
                        "OPS_LAWSUIT.LAWSUIT_NO," +
                        "to_char(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'"+ Pattern.FORMAT_DATETIME+"') as LAWSUIT_NO_YEAR," +
                        "OPS_LAWSUIT.IS_OUTSIDE LAWSUIT_IS_OUTSIDE," +
                        "to_char(OPS_LAWSUIT.LAWSUIT_DATE,'"+ Pattern.FORMAT_DATETIME+"') as LAWSUIT_DATE," +
                        "OPS_PROVE.PROVE_NO," +
                        "to_char(OPS_PROVE.PROVE_NO_YEAR,'"+ Pattern.FORMAT_DATETIME+"') as PROVE_NO_YEAR," +
                        "OPS_PROVE.IS_OUTSIDE PROVE_IS_OUTSIDE," +
                        "to_char(OPS_PROVE.RECEIVE_DOC_DATE,'"+ Pattern.FORMAT_DATETIME+"') as RECEIVE_DOC_DATE," +
                        "MAS_LAW_GUILTBASE.GUILTBASE_NAME," +
                        "MAS_LAW_GUILTBASE.FINE," +
                        "MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID," +
                        "MAS_LAW_GROUP_SUBSECTION_RULE.FINE_TYPE," +
                        "MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID," +
                        "MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME," +
                        "MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC," +
                        "MAS_LAW_GROUP_SECTION.SECTION_ID," +
                        "MAS_LAW_GROUP_SECTION.SECTION_NAME," +
                        "MAS_LAW_PENALTY.PENALTY_DESC," +
                        "MAS_LAW_PENALTY.FINE_RATE_MIN," +
                        "MAS_LAW_PENALTY.FINE_RATE_MAX," +
                        "MAS_LAW_PENALTY.FINE_MIN," +
                        "MAS_LAW_PENALTY.FINE_MAX" +
                        " FROM OPS_ARREST_INDICTMENT" +
                        " INNER JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID" +
                        " LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID" +
                        " AND OPS_PROVE.IS_ACTIVE = 1" +
                        " INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        " INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID" +
                        " INNER JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID" +
                        " INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
                        " INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID" +
                        " INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID" +
                        " LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID AND OPS_PROVE.IS_ACTIVE = 1" +
                        " INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
                        " INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
                        " INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
                        " INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
                        " INNER JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID" +
                        " WHERE OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        " AND OPS_LAWSUIT.IS_ACTIVE = 1" +
                        " AND OPS_ARREST.IS_ACTIVE = 1" +
                        " AND OPS_ARREST_STAFF.IS_ACTIVE = 1" +
                        " AND OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14" +
                        " AND OPS_ARREST_LOCALE.IS_ACTIVE = 1" +
                        " AND OPS_ARREST_INDICTMENT.INDICTMENT_ID = '"+req.getINDICTMENT_ID()+"' ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<CompareArrestIndictment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public CompareArrestIndictment mapRow(ResultSet rs, int rowNum) throws SQLException {
                CompareArrestIndictment item = new CompareArrestIndictment();
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setACCUSER_TITLE_NAME_TH(rs.getString("ACCUSER_TITLE_NAME_TH"));
                item.setACCUSER_TITLE_NAME_EN(rs.getString("ACCUSER_TITLE_NAME_EN"));
                item.setACCUSER_TITLE_SHORT_NAME_TH(rs.getString("ACCUSER_TITLE_SHORT_NAME_TH"));
                item.setACCUSER_TITLE_SHORT_NAME_EN(rs.getString("ACCUSER_TITLE_SHORT_NAME_EN"));
                item.setACCUSER_FIRST_NAME(rs.getString("ACCUSER_FIRST_NAME"));
                item.setACCUSER_LAST_NAME(rs.getString("ACCUSER_LAST_NAME"));
                item.setACCUSER_OPERATION_POS_CODE(rs.getString("ACCUSER_OPERATION_POS_CODE"));
                item.setACCUSER_OPREATION_POS_NAME(rs.getString("ACCUSER_OPREATION_POS_NAME"));
                item.setACCUSER_OPREATION_POS_LEVEL(rs.getString("ACCUSER_OPREATION_POS_LEVEL"));
                item.setACCUSER_OPERATION_POS_LEVEL_NAME(rs.getString("ACCUSER_OPERATION_POS_LEVEL_NAME"));
                item.setACCUSER_OPERATION_DEPT_CODE(rs.getString("ACCUSER_OPERATION_DEPT_CODE"));
                item.setACCUSER_OPERATION_DEPT_NAME(rs.getString("ACCUSER_OPERATION_DEPT_NAME"));
                item.setACCUSER_OPERATION_DEPT_LEVEL(rs.getInt("ACCUSER_OPERATION_DEPT_LEVEL"));
                item.setACCUSER_OPERATION_UNDER_DEPT_CODE(rs.getString("ACCUSER_OPERATION_UNDER_DEPT_CODE"));
                item.setACCUSER_OPERATION_UNDER_DEPT_NAME(rs.getString("ACCUSER_OPERATION_UNDER_DEPT_NAME"));
                item.setACCUSER_OPERATION_UNDER_DEPT_LEVEL(rs.getInt("ACCUSER_OPERATION_UNDER_DEPT_LEVEL"));
                item.setACCUSER_OPERATION_WORK_DEPT_CODE(rs.getString("ACCUSER_OPERATION_WORK_DEPT_CODE"));
                item.setACCUSER_OPERATION_WORK_DEPT_NAME(rs.getString("ACCUSER_OPERATION_WORK_DEPT_NAME"));
                item.setACCUSER_OPERATION_WORK_DEPT_LEVEL(rs.getInt("ACCUSER_OPERATION_WORK_DEPT_LEVEL"));
                item.setACCUSER_OPERATION_OFFICE_CODE(rs.getString("ACCUSER_OPERATION_OFFICE_CODE"));
                item.setACCUSER_OPERATION_OFFICE_NAME(rs.getString("ACCUSER_OPERATION_OFFICE_NAME"));
                item.setACCUSER_OPERATION_OFFICE_SHORT_NAME(rs.getString("ACCUSER_OPERATION_OFFICE_SHORT_NAME"));

                item.setACCUSER_MANAGEMENT_POS_CODE(rs.getString("ACCUSER_MANAGEMENT_POS_CODE"));
                item.setACCUSER_MANAGEMENT_POS_NAME(rs.getString("ACCUSER_MANAGEMENT_POS_NAME"));
                item.setACCUSER_MANAGEMENT_POS_LEVEL(rs.getString("ACCUSER_MANAGEMENT_POS_LEVEL"));
                item.setACCUSER_MANAGEMENT_POS_LEVEL_NAME(rs.getString("ACCUSER_MANAGEMENT_POS_LEVEL_NAME"));
                item.setACCUSER_MANAGEMENT_DEPT_CODE(rs.getString("ACCUSER_MANAGEMENT_DEPT_CODE"));
                item.setACCUSER_MANAGEMENT_DEPT_NAME(rs.getString("ACCUSER_MANAGEMENT_DEPT_NAME"));

                item.setACCUSER_MANAGEMENT_DEPT_LEVEL(rs.getInt("ACCUSER_MANAGEMENT_DEPT_LEVEL"));
                item.setACCUSER_MANAGEMENT_UNDER_DEPT_CODE(rs.getString("ACCUSER_MANAGEMENT_UNDER_DEPT_CODE"));
                item.setACCUSER_MANAGEMENT_UNDER_DEPT_NAME(rs.getString("ACCUSER_MANAGEMENT_UNDER_DEPT_NAME"));
                item.setACCUSER_MANAGEMENT_UNDER_DEPT_LEVEL(rs.getInt("ACCUSER_MANAGEMENT_UNDER_DEPT_LEVEL"));
                item.setACCUSER_MANAGEMENT_WORK_DEPT_CODE(rs.getString("ACCUSER_MANAGEMENT_WORK_DEPT_CODE"));
                item.setACCUSER_MANAGEMENT_WORK_DEPT_NAME(rs.getString("ACCUSER_MANAGEMENT_WORK_DEPT_NAME"));
                item.setACCUSER_MANAGEMENT_WORK_DEPT_LEVEL(rs.getInt("ACCUSER_MANAGEMENT_WORK_DEPT_LEVEL"));
                item.setACCUSER_MANAGEMENT_OFFICE_CODE(rs.getString("ACCUSER_MANAGEMENT_OFFICE_CODE"));
                item.setACCUSER_MANAGEMENT_OFFICE_NAME(rs.getString("ACCUSER_MANAGEMENT_OFFICE_NAME"));
                item.setACCUSER_MANAGEMENT_OFFICE_SHORT_NAME(rs.getString("ACCUSER_MANAGEMENT_OFFICE_SHORT_NAME"));
                item.setACCUSER_REPRESENT_POS_CODE(rs.getString("ACCUSER_REPRESENT_POS_CODE"));
                item.setACCUSER_REPRESENT_POS_NAME(rs.getString("ACCUSER_REPRESENT_POS_NAME"));
                item.setACCUSER_REPRESENT_POS_LEVEL(rs.getString("ACCUSER_REPRESENT_POS_LEVEL"));
                item.setACCUSER_REPRESENT_POS_LEVEL_NAME(rs.getString("ACCUSER_REPRESENT_POS_LEVEL_NAME"));
                item.setACCUSER_REPRESENT_DEPT_CODE(rs.getString("ACCUSER_REPRESENT_DEPT_CODE"));
                item.setACCUSER_REPRESENT_DEPT_NAME(rs.getString("ACCUSER_REPRESENT_DEPT_NAME"));
                item.setACCUSER_REPRESENT_DEPT_LEVEL(rs.getInt("ACCUSER_REPRESENT_DEPT_LEVEL"));
                item.setACCUSER_REPRESENT_UNDER_DEPT_CODE(rs.getString("ACCUSER_REPRESENT_UNDER_DEPT_CODE"));
                item.setACCUSER_REPRESENT_UNDER_DEPT_NAME(rs.getString("ACCUSER_REPRESENT_UNDER_DEPT_NAME"));
                item.setACCUSER_REPRESENT_UNDER_DEPT_LEVEL(rs.getInt("ACCUSER_REPRESENT_UNDER_DEPT_LEVEL"));
                item.setACCUSER_REPRESENT_WORK_DEPT_CODE(rs.getString("ACCUSER_REPRESENT_WORK_DEPT_CODE"));
                item.setACCUSER_REPRESENT_WORK_DEPT_NAME(rs.getString("ACCUSER_REPRESENT_WORK_DEPT_NAME"));
                item.setACCUSER_REPRESENT_WORK_DEPT_LEVEL(rs.getInt("ACCUSER_REPRESENT_WORK_DEPT_LEVEL"));
                item.setACCUSER_REPRESENT_OFFICE_CODE(rs.getString("ACCUSER_REPRESENT_OFFICE_CODE"));
                item.setACCUSER_REPRESENT_OFFICE_NAME(rs.getString("ACCUSER_REPRESENT_OFFICE_NAME"));
                item.setACCUSER_REPRESENT_OFFICE_SHORT_NAME(rs.getString("ACCUSER_REPRESENT_OFFICE_SHORT_NAME"));
                item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
                item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
                item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
                item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
                item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
                item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));
                item.setLAWSUIT_NO(rs.getInt("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_IS_OUTSIDE(rs.getInt("LAWSUIT_IS_OUTSIDE"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setPROVE_NO(rs.getInt("PROVE_NO"));
                item.setPROVE_NO_YEAR(rs.getString("PROVE_NO_YEAR"));
                item.setPROVE_IS_OUTSIDE(rs.getInt("PROVE_IS_OUTSIDE"));
                item.setRECEIVE_DOC_DATE(rs.getString("RECEIVE_DOC_DATE"));
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setFINE(rs.getString("FINE"));
                item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));


                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));

                item.setFINE_RATE_MIN(rs.getString("FINE_RATE_MIN"));
                item.setFINE_RATE_MAX(rs.getString("FINE_RATE_MAX"));
                item.setFINE_MIN(rs.getString("FINE_MIN"));
                item.setFINE_MAX(rs.getString("FINE_MAX"));

                item.setCompareArrestIndictmentProduct(getCompareArrestIndictmentProduct(rs.getInt("INDICTMENT_ID")));
                item.setCompareArrestIndictmentDetail(getCompareArrestIndictmentDetail(rs.getInt("INDICTMENT_ID")));
                item.setCompareProveProduct(getCompareProveProduct(rs.getInt("PROVE_ID")));
                item.setCompareGuiltbaseFine(getCompareGuiltbaseFine(rs.getInt("SUBSECTION_RULE_ID")));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public CompareArrestgetByIndictment CompareArrestgetByIndictment(CompareArrestgetByIndictmentIDReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT " +
                        "    OPS_ARREST_INDICTMENT.INDICTMENT_ID," +
                        "    OPS_LAWSUIT.LAWSUIT_ID," +
                        "    OPS_PROVE.PROVE_ID," +
                        "    OPS_ARREST.ARREST_ID," +
                        "    OPS_ARREST.ARREST_CODE," +
                        "    OPS_ARREST.OCCURRENCE_DATE, " +
                        "    OPS_ARREST.OFFICE_NAME, " +
                        "    CASE WHEN OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH IS NULL THEN " +
                        "      OPS_ARREST_STAFF.TITLE_NAME_TH||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME else" +
                        "      OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME end " +
                        "      AS ARREST_STAFF_NAME , " +
                        "    OPS_ARREST_STAFF.OPREATION_POS_NAME, " +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_NAME, " +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_SHORT_NAME, " +
                        "    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH," +
                        "    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN," +
                        "    MAS_DISTRICT.DISTRICT_NAME_TH," +
                        "    MAS_DISTRICT.DISTRICT_NAME_EN," +
                        "    MAS_PROVINCE.PROVINCE_NAME_TH," +
                        "    MAS_PROVINCE.PROVINCE_NAME_EN," +
                        "    MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID," +
                        "    MAS_LAW_GROUP_SECTION.SECTION_NAME," +
                        "    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID, " +
                        "    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME, " +
                        "    MAS_LAW_GUILTBASE.GUILTBASE_NAME, " +
                        "    MAS_LAW_PENALTY.SECTION_ID, " +
                        "    MAS_LAW_PENALTY.PENALTY_DESC, " +
                        "    MAS_LAW_GROUP_SUBSECTION_RULE.FINE_TYPE," +
                        "    MAS_LAW_PENALTY.FINE_RATE_MIN," +
                        "    MAS_LAW_PENALTY.FINE_RATE_MAX," +
                        "    MAS_LAW_PENALTY.FINE_MIN," +
                        "    MAS_LAW_PENALTY.FINE_MAX," +
                        "    OPS_LAWSUIT.IS_OUTSIDE LAWSUIT_IS_OUTSIDE," +
                        "    CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO, " +
                        "    TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO_YEAR," +
//                        "    CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || " +
//                        "    TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO," +
                        "    OPS_LAWSUIT.LAWSUIT_DATE, " +
                        "    OPS_PROVE.IS_OUTSIDE PROVE_IS_OUTSIDE," +
                        "    CASE WHEN OPS_PROVE.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_PROVE.PROVE_NO || CASE WHEN OPS_PROVE.PROVE_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_PROVE.PROVE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS PROVE_NO," +
                        "    TO_CHAR(OPS_PROVE.PROVE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS PROVE_NO_YEAR," +
//                        "    CASE WHEN OPS_PROVE.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_PROVE.PROVE_NO || CASE WHEN OPS_PROVE.PROVE_NO IS NOT NULL THEN '/' END || " +
//                        "    TO_CHAR(OPS_PROVE.PROVE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS PROVE_NO," +
                        "    OPS_PROVE.RECEIVE_DOC_DATE" +
                        "    FROM OPS_ARREST_INDICTMENT" +
                        "    INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID " +
                        "    INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID" +
                        "    INNER JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID" +
                        "    INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
                        "    INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID" +
                        "    INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID" +
                        "    INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID " +
                        "    INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
                        "    INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
                        "    INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
                        "    INNER JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID" +
                        "    INNER JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID " +
                        "    LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID AND OPS_PROVE.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_PRODUCT ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID AND OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID AND OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_PROVE_PRODUCT ON OPS_PROVE.PROVE_ID = OPS_PROVE_PRODUCT.PROVE_ID AND OPS_PROVE.IS_ACTIVE = 1" +
                        "    WHERE OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "    AND OPS_ARREST.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    AND OPS_ARREST_STAFF.IS_ACTIVE = 1" +
                        "    AND OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14" +
                        "    AND OPS_ARREST_LOCALE.IS_ACTIVE = 1" +
                        "    AND OPS_ARREST_INDICTMENT.INDICTMENT_ID = '"+req.getINDICTMENT_ID()+"' ");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<CompareArrestgetByIndictment>() {

            public CompareArrestgetByIndictment extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    CompareArrestgetByIndictment item = new CompareArrestgetByIndictment();
                    item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                    item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                    item.setPROVE_ID(rs.getInt("PROVE_ID"));
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setARREST_CODE(rs.getString("ARREST_CODE"));
                    item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                    item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                    item.setARREST_STAFF_NAME(rs.getString("ARREST_STAFF_NAME"));
                    item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                    item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                    item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                    item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
                    item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
                    item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
                    item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
                    item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
                    item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));
                    item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                    item.setSUBSECTION_ID(rs.getString("SUBSECTION_ID"));
                    item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                    item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                    item.setSECTION_ID(rs.getInt("SECTION_ID"));
                    item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
                    item.setFINE_TYPE(rs.getString("FINE_TYPE"));
                    item.setFINE_RATE_MIN(rs.getString("FINE_RATE_MIN"));
                    item.setFINE_RATE_MAX(rs.getString("FINE_RATE_MAX"));
                    item.setFINE_MIN(rs.getString("FINE_MIN"));
                    item.setFINE_MAX(rs.getString("FINE_MAX"));
                    item.setLAWSUIT_IS_OUTSIDE(rs.getString("LAWSUIT_IS_OUTSIDE"));
                    item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                    item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                    item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                    item.setPROVE_IS_OUTSIDE(rs.getInt("PROVE_IS_OUTSIDE"));
                    item.setPROVE_NO(rs.getString("PROVE_NO"));
                    item.setPROVE_NO_YEAR(rs.getString("PROVE_NO_YEAR"));
                    item.setRECEIVE_DOC_DATE(rs.getString("RECEIVE_DOC_DATE"));

                    item.setCompareArrestIndictmentDetail(getCompareLawbreaker(rs.getInt("INDICTMENT_ID")));
                    item.setCompareGuiltbaseFine(getCompareGuiltbaseFine(rs.getInt("SUBSECTION_RULE_ID")));
                    return item;
                }

                return null;
            }
        });
    }
}
