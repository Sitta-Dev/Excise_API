package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.adjust.AdjustCompareArrest;
import com.xcs.phase2.model.adjust.AdjustCompareArrestNew;
import com.xcs.phase2.request.adjust.AdjustCompareArrestgetByIndictmentIDReq;
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
public class AdjustCompareArrestDAOImpl extends AdjustExt implements AdjustCompareArrestDAO{

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareArrestDAOImpl.class);

    @Override
    public List<AdjustCompareArrest> AdjustCompareArrestgetByIndictmentID(AdjustCompareArrestgetByIndictmentIDReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT " +
                        "OPS_ARREST_INDICTMENT.INDICTMENT_ID," +
                        "OPS_LAWSUIT.LAWSUIT_ID," +
                        "OPS_PROVE.PROVE_ID," +
                        "OPS_ARREST.ARREST_ID," +
                        "OPS_ARREST.ARREST_CODE," +
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
                        "MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID," +
                        "MAS_LAW_GROUP_SUBSECTION_RULE.FINE_TYPE," +
                        "MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID," +
                        "MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME," +
                        "MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC," +
                        "MAS_LAW_GROUP_SECTION.SECTION_NAME," +
                        "MAS_LAW_GROUP_SECTION.SECTION_ID," +
                        "MAS_LAW_PENALTY.PENALTY_DESC" +
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
        List<AdjustCompareArrest> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public AdjustCompareArrest mapRow(ResultSet rs, int rowNum) throws SQLException {
                AdjustCompareArrest item = new AdjustCompareArrest();
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
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
                item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));

                item.setAdjustCompareArrestIndictmentDetail(getCompareArrestIndictmentDetail(rs.getInt("INDICTMENT_ID")));
                item.setAdjustCompareProveProduct(getCompareProveProduct(rs.getInt("PROVE_ID")));
                item.setAdjustCompareGuiltbaseFine(getCompareGuiltbaseFine(rs.getInt("SUBSECTION_RULE_ID")));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<AdjustCompareArrestNew> AdjustCompareArrestgetByIndictmentIDNew(AdjustCompareArrestgetByIndictmentIDReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT DISTINCT" +
                        "  OPS_COMPARE.COMPARE_ID," +
                        "  OPS_COMPARE.COMPARE_NO," +
                        "  OPS_COMPARE.COMPARE_NO_YEAR," +
                        "  to_char(OPS_COMPARE.COMPARE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as COMPARE_DATE," +
                        "  OPS_COMPARE_STAFF.TITLE_NAME_TH||''||OPS_COMPARE_STAFF.FIRST_NAME||''||OPS_COMPARE_STAFF.LAST_NAME AS COMPARE_STAFF," +
                        "  OPS_COMPARE_STAFF.OPREATION_POS_NAME," +
                        "  OPS_ARREST_INDICTMENT.INDICTMENT_ID," +
                        "  OPS_ARREST.ARREST_ID," +
                        "  OPS_ARREST.ARREST_CODE," +
                        "  to_char(OPS_ARREST.OCCURRENCE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as OCCURRENCE_DATE," +
                        "  OPS_ARREST_STAFF.TITLE_NAME_TH||''||OPS_ARREST_STAFF.FIRST_NAME||' '||OPS_ARREST_STAFF.LAST_NAME AS ACCUSER_NAME," +
                        "  OPS_ARREST_STAFF.OPREATION_POS_NAME ACCUSER_OPREATION_POS_NAME," +
                        "  OPS_ARREST_STAFF.OPERATION_OFFICE_NAME ACCUSER_OPERATION_OFFICE_NAME," +
                        "  MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH||'/'||MAS_DISTRICT.DISTRICT_NAME_TH||'/'||MAS_PROVINCE.PROVINCE_NAME_TH AS LOACTION_NAME," +
                        "  OPS_LAWSUIT.LAWSUIT_ID," +
                        "  OPS_LAWSUIT.LAWSUIT_NO," +
                        "  OPS_LAWSUIT.LAWSUIT_NO_YEAR," +
                        "  OPS_LAWSUIT.IS_OUTSIDE LAWSUIT_IS_OUTSIDE," +
                        "  OPS_LAWSUIT.LAWSUIT_DATE," +
                        "  OPS_PROVE.PROVE_ID," +
                        "  OPS_PROVE.PROVE_NO," +
                        "  OPS_PROVE.PROVE_NO_YEAR," +
                        "  OPS_PROVE.IS_OUTSIDE PROVE_IS_OUTSIDE," +
                        "  OPS_PROVE.RECEIVE_DOC_DATE," +
                        "  MAS_LAW_GUILTBASE.GUILTBASE_NAME," +
                        "  MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID," +
                        "  MAS_LAW_GROUP_SUBSECTION_RULE.FINE_TYPE," +
                        "  MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID," +
                        "  MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME," +
                        "  MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC," +
                        "  MAS_LAW_GROUP_SECTION.SECTION_NAME," +
                        "  MAS_LAW_PENALTY.PENALTY_DESC" +
                        "  FROM OPS_ARREST_INDICTMENT" +
                        "  INNER JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID" +
                        "  LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID" +
                        "  AND OPS_PROVE.IS_ACTIVE = 1" +
                        "  LEFT JOIN OPS_COMPARE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_COMPARE.LAWSUIT_ID" +
                        "  AND OPS_COMPARE.IS_ACTIVE = 1" +
                        "  LEFT JOIN OPS_COMPARE_STAFF ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_STAFF.COMPARE_ID" +
                        "  AND OPS_COMPARE_STAFF.CONTRIBUTOR_ID = 27" +
                        "  INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "  INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID" +
                        "  INNER JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID" +
                        "  INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
                        "  INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID" +
                        "  INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID" +
                        "  LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID" +
                        "  AND OPS_PROVE.IS_ACTIVE = 1" +
                        "  INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
                        "  INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
                        "  INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
                        "  INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
                        "  INNER JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID " +
                        "  WHERE OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "  AND OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "  AND OPS_ARREST.IS_ACTIVE = 1" +
                        "  AND OPS_ARREST_STAFF.IS_ACTIVE = 1" +
                        "  AND OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14" +
                        "  AND OPS_ARREST_LOCALE.IS_ACTIVE = 1" +
                        "  AND OPS_ARREST_INDICTMENT.INDICTMENT_ID = '"+req.getINDICTMENT_ID()+"' ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<AdjustCompareArrestNew> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public AdjustCompareArrestNew mapRow(ResultSet rs, int rowNum) throws SQLException {
                AdjustCompareArrestNew item = new AdjustCompareArrestNew();
                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setCOMPARE_NO(rs.getString("COMPARE_NO"));
                item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                item.setCOMPARE_STAFF(rs.getString("COMPARE_STAFF"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setACCUSER_NAME(rs.getString("ACCUSER_NAME"));
                item.setACCUSER_OPREATION_POS_NAME(rs.getString("ACCUSER_OPREATION_POS_NAME"));
                item.setACCUSER_OPERATION_OFFICE_NAME(rs.getString("ACCUSER_OPERATION_OFFICE_NAME"));
                item.setLOACTION_NAME(rs.getString("LOACTION_NAME"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_IS_OUTSIDE(rs.getString("LAWSUIT_IS_OUTSIDE"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setPROVE_NO(rs.getString("PROVE_NO"));
                item.setPROVE_NO_YEAR(rs.getString("PROVE_NO_YEAR"));
                item.setPROVE_IS_OUTSIDE(rs.getString("PROVE_IS_OUTSIDE"));
                item.setRECEIVE_DOC_DATE(rs.getString("RECEIVE_DOC_DATE"));
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setSUBSECTION_RULE_ID(rs.getString("SUBSECTION_RULE_ID"));
                item.setFINE_TYPE(rs.getString("FINE_TYPE"));
                item.setSUBSECTION_ID(rs.getString("SUBSECTION_ID"));
                item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));


                item.setAdjustCompareArrestIndictmentDetail(getCompareArrestIndictmentDetail(rs.getInt("INDICTMENT_ID")));
                item.setAdjustCompareProveProduct(getCompareProveProduct(rs.getInt("PROVE_ID")));
                item.setAdjustCompareGuiltbaseFine(getCompareGuiltbaseFine(rs.getInt("SUBSECTION_RULE_ID")));

                return item;
            }
        });

        return dataList;
    }
}
