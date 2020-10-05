package com.xcs.phase2.dao.compare;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.compare.CompareList;
import com.xcs.phase2.request.compare.CompareListgetByConAdvReq;
import com.xcs.phase2.request.compare.CompareListgetByKeywordReq;
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
public class CompareListDAOImpl extends CompareExt implements CompareListDAO {

    private static final Logger log = LoggerFactory.getLogger(CompareListDAOImpl.class);

    @Override
    public List<CompareList> CompareListgetByKeyword(CompareListgetByKeywordReq req) {
        // TODO Auto-generated method stub

        String str = "";

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND" +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            ") " ;
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND " +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            " )";
                }else {
                    str = " AND" +
                            " (" +
                            "  OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            " )";
                }

            }

        }

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("with temp as (    " +
                        "    SELECT DISTINCT " +
                        "    OPS_COMPARE.COMPARE_ID," +
                        "    OPS_LAWSUIT.LAWSUIT_ID," +
                        "    OPS_ARREST.ARREST_ID," +
                        "    OPS_ARREST_INDICTMENT.INDICTMENT_ID," +
                        "    OPS_ARREST.ARREST_CODE," +
                        "    OPS_ARREST.ARREST_DATE," +
                        "    OPS_ARREST.OCCURRENCE_DATE," +
                        "    case when OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH is null or OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_ARREST_STAFF.TITLE_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME " +
                        "    else OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME end as ARREST_STAFF_NAME, " +
                        "    OPS_ARREST_STAFF.OPREATION_POS_NAME, " +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_NAME, " +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_ARREST.OFFICE_NAME ARREST_OFFICE_NAME," +
                        "    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH," +
                        "    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN," +
                        "    MAS_DISTRICT.DISTRICT_NAME_TH," +
                        "    MAS_DISTRICT.DISTRICT_NAME_EN," +
                        "    MAS_PROVINCE.PROVINCE_NAME_TH," +
                        "    MAS_PROVINCE.PROVINCE_NAME_EN," +
                        "    MAS_LAW_GROUP_SECTION.SECTION_NAME," +
                        "    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID," +
                        "    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME, " +
                        "    MAS_LAW_GUILTBASE.GUILTBASE_NAME, " +
                        "    MAS_LAW_PENALTY.SECTION_ID, " +
                        "    MAS_LAW_PENALTY.PENALTY_DESC, " +
                        "    case when OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH is null or OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_LAWSUIT_STAFF.TITLE_NAME_TH ||''|| OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME " +
                        "    else OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME end as LAWSUIT_STAF_NAME, " +
                        "    OPS_LAWSUIT_STAFF.OPREATION_POS_NAME LAWSUIT_OPREATION_POS_NAME, " +
                        "    OPS_LAWSUIT_STAFF.OPERATION_OFFICE_NAME LAWSUIT_OPERATION_OFFICE_NAME, " +
                        "    OPS_LAWSUIT_STAFF.OPERATION_OFFICE_SHORT_NAME LAWSUIT_OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_LAWSUIT.OFFICE_NAME LAWSUIT_OFFICE_NAME," +
                        "    OPS_LAWSUIT.IS_OUTSIDE LAWSUIT_IS_OUTSIDE," +
                        "    CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO," +
                        "    TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO_YEAR," +
                        "    OPS_LAWSUIT.LAWSUIT_DATE, " +
                        "    OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE," +
                        "    OPS_LAWSUIT_DETAIL.LAWSUIT_END," +
                        "    case when OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH is null or OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_PROVE_STAFF.TITLE_NAME_TH ||''|| OPS_PROVE_STAFF.FIRST_NAME ||' '|| OPS_PROVE_STAFF.LAST_NAME " +
                        "    else OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_PROVE_STAFF.FIRST_NAME ||' '|| OPS_PROVE_STAFF.LAST_NAME end as PROVE_STAFF_NAME, " +
                        "    OPS_PROVE_STAFF.OPREATION_POS_NAME PROVE_OPREATION_POS_NAME, " +
                        "    OPS_PROVE_STAFF.OPERATION_OFFICE_NAME PROVE_OPERATION_OFFICE_NAME, " +
                        "    OPS_PROVE_STAFF.OPERATION_OFFICE_SHORT_NAME PROVE_OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_PROVE.IS_OUTSIDE PROVE_IS_OUTSIDE," +
                        "    CASE WHEN OPS_PROVE.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_PROVE.PROVE_NO || CASE WHEN OPS_PROVE.PROVE_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_PROVE.PROVE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS PROVE_NO," +
                        "    TO_CHAR(OPS_PROVE.PROVE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS PROVE_NO_YEAR," +
                        "    OPS_PROVE.RECEIVE_DOC_DATE RECEIVE_DOC_DATE, " +
                        "    OPS_PROVE.RECEIVE_OFFICE_NAME RECEIVE_OFFICE_NAME," +
                        "    case when OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH is null or OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_COMPARE_STAFF.TITLE_NAME_TH ||''|| OPS_COMPARE_STAFF.FIRST_NAME ||' '|| OPS_COMPARE_STAFF.LAST_NAME " +
                        "    else OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_COMPARE_STAFF.FIRST_NAME ||' '|| OPS_COMPARE_STAFF.LAST_NAME end as COMPARE_STAFF_NAME, " +
                        "    OPS_COMPARE_STAFF.OPREATION_POS_NAME COMPARE_OPREATION_POS_NAME, " +
                        "    OPS_COMPARE_STAFF.OPERATION_OFFICE_NAME COMPARE_OPERATION_OFFICE_NAME, " +
                        "    OPS_COMPARE_STAFF.OPERATION_OFFICE_SHORT_NAME COMPARE_OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_COMPARE.IS_OUTSIDE COMPARE_IS_OUTSIDE," +
                        "    CASE WHEN OPS_COMPARE.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_COMPARE.COMPARE_NO || CASE WHEN OPS_COMPARE.COMPARE_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_NO," +
                        "    TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR,'YYYY','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_NO_YEAR," +
                        "    OPS_COMPARE.COMPARE_DATE," +
                        "    OPS_COMPARE.OFFICE_NAME COMPARE_OFFICE_NAME," +
                        "    CASE WHEN OPS_COMPARE.COMPARE_NO IS NOT NULL THEN 'เปรียบเทียบแล้ว' else 'ยังไม่เปรียบเทียบ' END COMPARE_STATUS," +
                        "    OPS_PROVE.IS_ACTIVE PROVE_IS_ACTIVE," +
                        "    MAS_LAW_GUILTBASE.IS_PROVE, " +
                        "    OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
                        "    FROM OPS_LAWSUIT" +
                        "    LEFT JOIN OPS_COMPARE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_COMPARE.LAWSUIT_ID AND OPS_COMPARE.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_MAPPING.COMPARE_ID " +
                        "    LEFT JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID = OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID AND OPS_COMPARE_DETAIL.COMPARE_TYPE = 1 " +
                        "    LEFT JOIN OPS_COMPARE_STAFF ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_STAFF.COMPARE_ID AND OPS_COMPARE_STAFF.IS_ACTIVE = 1 AND OPS_COMPARE_STAFF.CONTRIBUTOR_ID = 27" +
                        "    INNER JOIN OPS_ARREST_INDICTMENT ON OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID" +
                        "    INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
                        "    INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
                        "    INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
                        "    INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
                        "    INNER JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID" +
                        "    INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID" +
                        "    INNER JOIN OPS_LAWSUIT_STAFF ON OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID" +
                        "    LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID AND OPS_PROVE.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_PROVE_STAFF ON OPS_PROVE.PROVE_ID = OPS_PROVE_STAFF.PROVE_ID AND OPS_PROVE_STAFF.IS_ACTIVE = 1 AND OPS_PROVE_STAFF.CONTRIBUTOR_ID = 25" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_LAWSUIT_DETAIL ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID AND OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "    INNER JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID" +
                        "    INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
                        "    INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID" +
                        "    INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID" +
                        "    LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID AND OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
                        "    WHERE OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT.IS_LAWSUIT = 1" +
                        "    AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                       // "    AND OPS_COMPARE_DETAIL.COMPARE_TYPE = 1" +
                        "    AND OPS_ARREST.IS_ACTIVE = 1" +
                        "    AND OPS_ARREST_STAFF.IS_ACTIVE = 1" +
                        "    AND OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14" +
                        "    AND OPS_LAWSUIT_STAFF.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID = 16" +
                        "    AND " +
                        "    (" +
                        "      LOWER(OPS_ARREST.ARREST_CODE) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_NAME_TH||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')  " +
                        "      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_NAME_EN||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_NAME_TH||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_NAME_EN||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "      OR LOWER(MAS_LAW_GUILTBASE.GUILTBASE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "      OR LOWER(MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH||MAS_DISTRICT.DISTRICT_NAME_TH||MAS_PROVINCE.PROVINCE_NAME_TH) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +	
                        "      OR LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_PROVE_STAFF.TITLE_NAME_TH||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_PROVE_STAFF.TITLE_NAME_EN||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_PROVE_STAFF.TITLE_SHORT_NAME_EN||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_PROVE.RECEIVE_OFFICE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "      OR LOWER(OPS_COMPARE_STAFF.TITLE_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_COMPARE_STAFF.TITLE_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "      OR LOWER (OPS_COMPARE.OFFICE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "    )" +str+
                        "    ORDER BY OPS_ARREST.OCCURRENCE_DATE DESC , OPS_ARREST.ARREST_CODE DESC" +
                        ")" +
                        " select " +
                        "    COMPARE_ID," +
                        "    LAWSUIT_ID," +
                        "    ARREST_ID," +
                        "    INDICTMENT_ID," +
                        "    ARREST_CODE," +
                        "    ARREST_DATE," +
                        "    OCCURRENCE_DATE," +
                        "    ARREST_STAFF_NAME," +
                        "    OPREATION_POS_NAME," +
                        "    OPERATION_OFFICE_NAME," +
                        "    OPERATION_OFFICE_SHORT_NAME," +
                        "    ARREST_OFFICE_NAME," +
                        "    SUB_DISTRICT_NAME_TH," +
                        "    SUB_DISTRICT_NAME_EN," +
                        "    DISTRICT_NAME_TH," +
                        "    DISTRICT_NAME_EN," +
                        "    PROVINCE_NAME_TH," +
                        "    PROVINCE_NAME_EN," +
                        "    SECTION_NAME," +
                        "    SUBSECTION_ID," +
                        "    SUBSECTION_NAME," +
                        "    GUILTBASE_NAME," +
                        "    SECTION_ID," +
                        "    PENALTY_DESC," +
                        "    LAWSUIT_STAF_NAME," +
                        "    LAWSUIT_OPREATION_POS_NAME," +
                        "    LAWSUIT_OPERATION_OFFICE_NAME," +
                        "    LAWSUIT_OPERATION_OFFICE_SHORT_NAME," +
                        "    LAWSUIT_OFFICE_NAME," +
                        "    LAWSUIT_IS_OUTSIDE," +
                        "    LAWSUIT_NO," +
                        "    LAWSUIT_NO_YEAR," +
                        "    LAWSUIT_DATE," +
                        "    LAWSUIT_TYPE," +
                        "    LAWSUIT_END," +
                        "    PROVE_STAFF_NAME," +
                        "    PROVE_OPREATION_POS_NAME," +
                        "    PROVE_OPERATION_OFFICE_NAME," +
                        "    PROVE_OPERATION_OFFICE_SHORT_NAME," +
                        "    PROVE_IS_OUTSIDE," +
                        "    PROVE_NO," +
                        "    PROVE_NO_YEAR," +
                        "    RECEIVE_DOC_DATE," +
                        "    RECEIVE_OFFICE_NAME," +
                        "    COMPARE_STAFF_NAME," +
                        "    COMPARE_OPREATION_POS_NAME," +
                        "    COMPARE_OPERATION_OFFICE_NAME," +
                        "    COMPARE_OPERATION_OFFICE_SHORT_NAME," +
                        "    COMPARE_IS_OUTSIDE," +
                        "    COMPARE_NO," +
                        "    COMPARE_NO_YEAR," +
                        "    COMPARE_DATE," +
                        "    COMPARE_OFFICE_NAME," +
                        "    COMPARE_STATUS," +
                        "    PROVE_IS_ACTIVE," +
                        "    IS_PROVE," +
                        "    LISTAGG(LAWBREAKER_ID, ',') WITHIN GROUP (" +
                        "    ORDER BY " +
                        "       COMPARE_ID," +
                        "        LAWSUIT_ID," +
                        "        ARREST_ID," +
                        "        INDICTMENT_ID," +
                        "        ARREST_CODE," +
                        "        ARREST_DATE," +
                        "        OCCURRENCE_DATE," +
                        "        ARREST_STAFF_NAME," +
                        "        OPREATION_POS_NAME," +
                        "        OPERATION_OFFICE_NAME," +
                        "        OPERATION_OFFICE_SHORT_NAME," +
                        "        ARREST_OFFICE_NAME," +
                        "        SUB_DISTRICT_NAME_TH," +
                        "        SUB_DISTRICT_NAME_EN," +
                        "        DISTRICT_NAME_TH," +
                        "        DISTRICT_NAME_EN," +
                        "        PROVINCE_NAME_TH," +
                        "        PROVINCE_NAME_EN," +
                        "        SECTION_NAME," +
                        "        SUBSECTION_ID," +
                        "        SUBSECTION_NAME," +
                        "        GUILTBASE_NAME," +
                        "        SECTION_ID," +
                        "        PENALTY_DESC," +
                        "        LAWSUIT_STAF_NAME," +
                        "        LAWSUIT_OPREATION_POS_NAME," +
                        "        LAWSUIT_OPERATION_OFFICE_NAME," +
                        "        LAWSUIT_OPERATION_OFFICE_SHORT_NAME," +
                        "        LAWSUIT_OFFICE_NAME," +
                        "        LAWSUIT_IS_OUTSIDE," +
                        "        LAWSUIT_NO," +
                        "        LAWSUIT_NO_YEAR," +
                        "        LAWSUIT_DATE," +
                        "        LAWSUIT_TYPE," +
                        "        LAWSUIT_END," +
                        "        PROVE_STAFF_NAME," +
                        "        PROVE_OPREATION_POS_NAME," +
                        "        PROVE_OPERATION_OFFICE_NAME," +
                        "        PROVE_OPERATION_OFFICE_SHORT_NAME," +
                        "        PROVE_IS_OUTSIDE," +
                        "        PROVE_NO," +
                        "        PROVE_NO_YEAR," +
                        "        RECEIVE_DOC_DATE," +
                        "        RECEIVE_OFFICE_NAME," +
                        "        COMPARE_STAFF_NAME," +
                        "        COMPARE_OPREATION_POS_NAME," +
                        "        COMPARE_OPERATION_OFFICE_NAME," +
                        "        COMPARE_OPERATION_OFFICE_SHORT_NAME," +
                        "        COMPARE_IS_OUTSIDE," +
                        "        COMPARE_NO," +
                        "        COMPARE_NO_YEAR," +
                        "        COMPARE_DATE," +
                        "        COMPARE_OFFICE_NAME," +
                        "        COMPARE_STATUS," +
                        "        PROVE_IS_ACTIVE," +
                        "        IS_PROVE" +
                        "    ) AS LAWBREAKER_ID" +
                        " from temp" +
                        " GROUP BY" +
                        "        COMPARE_ID," +
                        "        LAWSUIT_ID," +
                        "        ARREST_ID," +
                        "        INDICTMENT_ID," +
                        "        ARREST_CODE," +
                        "        ARREST_DATE," +
                        "        OCCURRENCE_DATE," +
                        "        ARREST_STAFF_NAME," +
                        "        OPREATION_POS_NAME," +
                        "        OPERATION_OFFICE_NAME," +
                        "        OPERATION_OFFICE_SHORT_NAME," +
                        "        ARREST_OFFICE_NAME," +
                        "        SUB_DISTRICT_NAME_TH," +
                        "        SUB_DISTRICT_NAME_EN," +
                        "        DISTRICT_NAME_TH," +
                        "        DISTRICT_NAME_EN," +
                        "        PROVINCE_NAME_TH," +
                        "        PROVINCE_NAME_EN," +
                        "        SECTION_NAME," +
                        "        SUBSECTION_ID," +
                        "        SUBSECTION_NAME," +
                        "        GUILTBASE_NAME," +
                        "        SECTION_ID," +
                        "        PENALTY_DESC," +
                        "        LAWSUIT_STAF_NAME," +
                        "        LAWSUIT_OPREATION_POS_NAME," +
                        "        LAWSUIT_OPERATION_OFFICE_NAME," +
                        "        LAWSUIT_OPERATION_OFFICE_SHORT_NAME," +
                        "        LAWSUIT_OFFICE_NAME," +
                        "        LAWSUIT_IS_OUTSIDE," +
                        "        LAWSUIT_NO," +
                        "        LAWSUIT_NO_YEAR," +
                        "        LAWSUIT_DATE," +
                        "        LAWSUIT_TYPE," +
                        "        LAWSUIT_END," +
                        "        PROVE_STAFF_NAME," +
                        "        PROVE_OPREATION_POS_NAME," +
                        "        PROVE_OPERATION_OFFICE_NAME," +
                        "        PROVE_OPERATION_OFFICE_SHORT_NAME," +
                        "        PROVE_IS_OUTSIDE," +
                        "        PROVE_NO," +
                        "        PROVE_NO_YEAR," +
                        "        RECEIVE_DOC_DATE," +
                        "        RECEIVE_OFFICE_NAME," +
                        "        COMPARE_STAFF_NAME," +
                        "        COMPARE_OPREATION_POS_NAME," +
                        "        COMPARE_OPERATION_OFFICE_NAME," +
                        "        COMPARE_OPERATION_OFFICE_SHORT_NAME," +
                        "        COMPARE_IS_OUTSIDE," +
                        "        COMPARE_NO," +
                        "        COMPARE_NO_YEAR," +
                        "        COMPARE_DATE," +
                        "        COMPARE_OFFICE_NAME," +
                        "        COMPARE_STATUS," +
                        "        PROVE_IS_ACTIVE," +
                        "        IS_PROVE"+
                        "		 ORDER BY OCCURRENCE_DATE DESC , ARREST_CODE DESC");


        log.info("[SQL]  : " + sqlBuilderDetail.toString());
        //System.out.println("[SQL] [AdjustCompareListgetByKeyword]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<CompareList> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public CompareList mapRow(ResultSet rs, int rowNum) throws SQLException {
                CompareList item = new CompareList();

                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setARREST_DATE(rs.getString("ARREST_DATE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setARREST_STAFF_NAME(rs.getString("ARREST_STAFF_NAME"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                item.setARREST_OFFICE_NAME(rs.getString("ARREST_OFFICE_NAME"));
                item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
                item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
                item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
                item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
                item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
                item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
                item.setLAWSUIT_STAF_NAME(rs.getString("LAWSUIT_STAF_NAME"));
                item.setLAWSUIT_OPREATION_POS_NAME(rs.getString("LAWSUIT_OPREATION_POS_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_NAME(rs.getString("LAWSUIT_OPERATION_OFFICE_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_SHORT_NAME(rs.getString("LAWSUIT_OPERATION_OFFICE_SHORT_NAME"));
                item.setLAWSUIT_OFFICE_NAME(rs.getString("LAWSUIT_OFFICE_NAME"));
                item.setLAWSUIT_IS_OUTSIDE(rs.getInt("LAWSUIT_IS_OUTSIDE"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setLAWSUIT_TYPE(rs.getString("LAWSUIT_TYPE"));
                item.setLAWSUIT_END(rs.getString("LAWSUIT_END"));
                item.setPROVE_STAFF_NAME(rs.getString("PROVE_STAFF_NAME"));
                item.setPROVE_OPREATION_POS_NAME(rs.getString("PROVE_OPREATION_POS_NAME"));
                item.setPROVE_OPERATION_OFFICE_NAME(rs.getString("PROVE_OPERATION_OFFICE_NAME"));
                item.setPROVE_OPERATION_OFFICE_SHORT_NAME(rs.getString("PROVE_OPERATION_OFFICE_SHORT_NAME"));
                item.setPROVE_IS_OUTSIDE(rs.getInt("PROVE_IS_OUTSIDE"));
                item.setPROVE_NO(rs.getString("PROVE_NO"));
                item.setPROVE_NO_YEAR(rs.getString("PROVE_NO_YEAR"));
                item.setRECEIVE_DOC_DATE(rs.getString("RECEIVE_DOC_DATE"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setCOMPARE_STAFF_NAME(rs.getString("COMPARE_STAFF_NAME"));
                item.setCOMPARE_OPREATION_POS_NAME(rs.getString("COMPARE_OPREATION_POS_NAME"));
                item.setCOMPARE_OPERATION_OFFICE_NAME(rs.getString("COMPARE_OPERATION_OFFICE_NAME"));
                item.setCOMPARE_OPERATION_OFFICE_SHORT_NAME(rs.getString("COMPARE_OPERATION_OFFICE_SHORT_NAME"));
                item.setCOMPARE_IS_OUTSIDE(rs.getInt("COMPARE_IS_OUTSIDE"));
                item.setCOMPARE_NO(rs.getString("COMPARE_NO"));
                item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                item.setCOMPARE_OFFICE_NAME(rs.getString("COMPARE_OFFICE_NAME"));
                item.setCOMPARE_STATUS(rs.getString("COMPARE_STATUS"));
                item.setPROVE_IS_ACTIVE(rs.getInt("PROVE_IS_ACTIVE"));
                item.setIS_PROVE(rs.getInt("IS_PROVE"));



                item.setArrestLawbreaker(getArrestLawbreakerByLAWBREAKER_ID(rs.getInt("INDICTMENT_ID")));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<CompareList> CompareListgetByConAdv(CompareListgetByConAdvReq req) {
        // TODO Auto-generated method stub

        String str = "";
        String tempCOMPARE_DATE_FROM   = "";
        String tempCOMPARE_DATE_TO = "";
        String tempOCCURRENCE_DATE_FROM   = "";
        String tempOCCURRENCE_DATE_TO = "";
        String tempLAWSUIT_DATE_FROM   = "";
        String tempLAWSUIT_DATE_TO = "";
        String tempRECEIVE_DOC_DATE_FROM   = "";
        String tempRECEIVE_DOC_DATE_TO = "";


        if(!"".equals(req.getRECEIVE_DOC_DATE_FROM())) {
            tempRECEIVE_DOC_DATE_FROM = String.format("%s %s", req.getRECEIVE_DOC_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getRECEIVE_DOC_DATE_TO())) {
            tempRECEIVE_DOC_DATE_TO = String.format("%s %s", req.getRECEIVE_DOC_DATE_TO(),Pattern.TIME_TO);
        }


        if(!"".equals(req.getLAWSUIT_DATE_FROM())) {
            tempLAWSUIT_DATE_FROM = String.format("%s %s", req.getLAWSUIT_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getLAWSUIT_DATE_TO())) {
            tempLAWSUIT_DATE_TO = String.format("%s %s", req.getLAWSUIT_DATE_TO(),Pattern.TIME_TO);
        }

        if(!"".equals(req.getCOMPARE_DATE_FROM())) {
            tempCOMPARE_DATE_FROM = String.format("%s %s", req.getCOMPARE_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getCOMPARE_DATE_TO())) {
            tempCOMPARE_DATE_TO = String.format("%s %s", req.getCOMPARE_DATE_TO(),Pattern.TIME_TO);
        }

        if(!"".equals(req.getOCCURRENCE_DATE_FROM())) {
            tempOCCURRENCE_DATE_FROM = String.format("%s %s", req.getOCCURRENCE_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getOCCURRENCE_DATE_TO())) {
            tempOCCURRENCE_DATE_TO = String.format("%s %s", req.getOCCURRENCE_DATE_TO(),Pattern.TIME_TO);
        }


        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND" +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            ") " ;
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND " +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            " )";
                }else {
                    str = " AND" +
                            " (" +
                            "  OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            " )";
                }

            }

        }

        StringBuilder sqlBuilder = new StringBuilder();

        if(req.getARREST_CODE() != null && !"".equals(req.getARREST_CODE())) {
            sqlBuilder.append(" AND LOWER(OPS_ARREST.ARREST_CODE) LIKE LOWER(REPLACE('%"+req.getARREST_CODE()+"%',' ','')) ");
        }

        if(req.getOCCURRENCE_DATE_FROM() != null && !"".equals(req.getOCCURRENCE_DATE_FROM()) && req.getOCCURRENCE_DATE_TO() != null && !"".equals(req.getOCCURRENCE_DATE_TO())) {
            sqlBuilder.append(" AND OPS_ARREST.OCCURRENCE_DATE BETWEEN  to_date(nvl('"+tempOCCURRENCE_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempOCCURRENCE_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getARREST_NAME() != null && !"".equals(req.getARREST_NAME())) {
            sqlBuilder.append( "AND" +
                    " ( " +
                    "  LOWER(OPS_ARREST_STAFF.TITLE_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_NAME()+"%',' ',''))" +
                    " )");
        }

        if(req.getLAWBREAKER_STAFF_NAME() != null && !"".equals(req.getLAWBREAKER_STAFF_NAME())) {
            sqlBuilder.append(" AND " +
                    " (" +
                    "  LOWER(OPS_ARREST_LAWBREAKER.TITLE_NAME_TH||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getLAWBREAKER_STAFF_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_NAME_EN||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getLAWBREAKER_STAFF_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_TH||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getLAWBREAKER_STAFF_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_ARREST_LAWBREAKER.TITLE_SHORT_NAME_EN||OPS_ARREST_LAWBREAKER.FIRST_NAME||OPS_ARREST_LAWBREAKER.LAST_NAME||OPS_ARREST_LAWBREAKER.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getLAWBREAKER_STAFF_NAME()+"%',' ',''))" +
                    " )");
        }

        if(req.getSUBSECTION_NAME() != null && !"".equals(req.getSUBSECTION_NAME())) {
            sqlBuilder.append("  AND LOWER(MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) LIKE LOWER('%"+req.getSUBSECTION_NAME()+"%') ");
        }

        if(req.getGUILTBASE_NAME() != null && !"".equals(req.getGUILTBASE_NAME())) {
            sqlBuilder.append("  AND LOWER (MAS_LAW_GUILTBASE.GUILTBASE_NAME) LIKE LOWER('%"+req.getGUILTBASE_NAME()+"%') ");
        }

        if(req.getARREST_OFFICE_NAME() != null && !"".equals(req.getARREST_OFFICE_NAME())) {
            sqlBuilder.append("  AND LOWER(MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH||MAS_DISTRICT.DISTRICT_NAME_TH||MAS_PROVINCE.PROVINCE_NAME_TH) LIKE LOWER(REPLACE('%"+req.getARREST_OFFICE_NAME()+"%',' ',''))");
        }

        if(req.getLAWSUIT_NO()!= null && !"".equals(req.getLAWSUIT_NO())) {
            sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_NO = '"+req.getLAWSUIT_NO()+"' ");
        }

        if(req.getLAWSUIT_NO_YEAR()!= null && !"".equals(req.getLAWSUIT_NO_YEAR())) {
            sqlBuilder.append(" AND (to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getLAWSUIT_NO_YEAR()+"' AND to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getLAWSUIT_NO_YEAR()+"') ");
        }

        if(req.getLAWSUIT_IS_OUTSIDE() != null && !"".equals(req.getLAWSUIT_IS_OUTSIDE())) {
            sqlBuilder.append(" AND OPS_LAWSUIT.IS_OUTSIDE = '"+req.getLAWSUIT_IS_OUTSIDE()+"' ");
        }

        if(req.getLAWSUIT_DATE_FROM() != null && !"".equals(req.getLAWSUIT_DATE_FROM()) && req.getLAWSUIT_DATE_TO() != null && !"".equals(req.getLAWSUIT_DATE_TO())) {
            sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_DATE BETWEEN  to_date(nvl('"+tempLAWSUIT_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempLAWSUIT_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getLAWSUIT_NAME() != null && !"".equals(req.getLAWSUIT_NAME())) {
            sqlBuilder.append( "AND" +
                    " ( " +
                    "  LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_NAME()+"%',' ','')) " +
                    " )");
        }

        if(req.getLAWSUIT_TYPE() != null && !"".equals(req.getLAWSUIT_TYPE())) {
            sqlBuilder.append(" AND OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE = '"+req.getLAWSUIT_TYPE()+"' ");
        }

        if(req.getPROVE_NO() != null && !"".equals(req.getPROVE_NO())) {
            sqlBuilder.append("  AND OPS_PROVE.PROVE_NO = '"+req.getPROVE_NO()+"' ");
        }

        if(req.getPROVE_NO_YEAR() != null && !"".equals(req.getPROVE_NO_YEAR())) {
            sqlBuilder.append(" AND (to_number(TO_CHAR(OPS_PROVE.PROVE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getPROVE_NO_YEAR()+"' AND to_number(TO_CHAR(OPS_PROVE.PROVE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getPROVE_NO_YEAR()+"') ");
        }

        if(req.getPROVE_IS_OUTSIDE() != null && !"".equals(req.getPROVE_IS_OUTSIDE())) {
            sqlBuilder.append("  AND OPS_PROVE.IS_OUTSIDE = '"+req.getPROVE_IS_OUTSIDE()+"' ");
        }

        if(req.getRECEIVE_DOC_DATE_FROM() != null && !"".equals(req.getRECEIVE_DOC_DATE_FROM()) && req.getRECEIVE_DOC_DATE_TO() != null && !"".equals(req.getRECEIVE_DOC_DATE_TO())) {
            sqlBuilder.append(" AND OPS_PROVE.RECEIVE_DOC_DATE BETWEEN  to_date(nvl('"+tempRECEIVE_DOC_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempRECEIVE_DOC_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getPROVE_NAME() != null && !"".equals(req.getPROVE_NAME())) {
            sqlBuilder.append( "AND" +
                    " ( " +
                    "  LOWER(OPS_PROVE_STAFF.TITLE_NAME_TH||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_PROVE_STAFF.TITLE_NAME_EN||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_PROVE_STAFF.TITLE_SHORT_NAME_EN||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ','')) " +
                    " )");
        }

        if(req.getRECEIVE_OFFICE_NAME() != null && !"".equals(req.getRECEIVE_OFFICE_NAME())) {
            sqlBuilder.append("  AND LOWER(OPS_PROVE.RECEIVE_OFFICE_NAME) LIKE LOWER('%"+req.getRECEIVE_OFFICE_NAME()+"%') ");
        }

        if(req.getCOMPARE_NO() != null && !"".equals(req.getCOMPARE_NO())) {
            sqlBuilder.append("  AND OPS_COMPARE.COMPARE_NO = '"+req.getCOMPARE_NO()+"' ");
        }

        if(req.getCOMPARE_NO_YEAR() != null && !"".equals(req.getCOMPARE_NO_YEAR())) {
            sqlBuilder.append("  AND (to_number(TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getCOMPARE_NO_YEAR()+"' AND to_number(TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getCOMPARE_NO_YEAR()+"') ");
        }

        if(req.getCOMPARE_IS_OUTSIDE() != null && !"".equals(req.getCOMPARE_IS_OUTSIDE())) {
            sqlBuilder.append("  AND OPS_COMPARE.IS_OUTSIDE = '"+req.getCOMPARE_IS_OUTSIDE()+"' ");
        }

        if(req.getCOMPARE_DATE_FROM() != null && !"".equals(req.getCOMPARE_DATE_FROM()) && req.getCOMPARE_DATE_TO() != null && !"".equals(req.getCOMPARE_DATE_TO())) {
            sqlBuilder.append(" AND OPS_COMPARE.COMPARE_DATE BETWEEN  to_date(nvl('"+tempCOMPARE_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempCOMPARE_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getCOMPARE_NAME() != null && !"".equals(req.getCOMPARE_NAME())) {
            sqlBuilder.append( "AND" +
                    " ( " +
                    "  LOWER(OPS_COMPARE_STAFF.TITLE_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_COMPARE_STAFF.TITLE_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_NAME()+"%',' ','')) " +
                    " )");
        }

        if(req.getCOMPARE_OFFICE_NAME() != null && !"".equals(req.getCOMPARE_OFFICE_NAME())) {
            sqlBuilder.append(" AND LOWER (OPS_COMPARE.OFFICE_NAME) LIKE LOWER('%"+req.getCOMPARE_OFFICE_NAME()+"%') ");
        }

        if(req.getCOMPARE_STATUS() != null && !"".equals(req.getCOMPARE_STATUS())) {
            if("0".equals(req.getCOMPARE_STATUS())){
                sqlBuilder.append(" and OPS_COMPARE.COMPARE_NO is null ");
            }else{
                sqlBuilder.append(" and OPS_COMPARE.COMPARE_NO is not null ");
            }

        }

        sqlBuilder.append(str);

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("with temp as (    " +
                        "    SELECT DISTINCT " +
                        "    OPS_COMPARE.COMPARE_ID," +
                        "    OPS_LAWSUIT.LAWSUIT_ID," +
                        "    OPS_ARREST.ARREST_ID," +
                        "    OPS_ARREST_INDICTMENT.INDICTMENT_ID," +
                        "    OPS_ARREST.ARREST_CODE," +
                        "    OPS_ARREST.ARREST_DATE," +
                        "    OPS_ARREST.OCCURRENCE_DATE," +
                        "    case when OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH is null or OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_ARREST_STAFF.TITLE_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME " +
                        "    else OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME end as ARREST_STAFF_NAME, " +
                        "    OPS_ARREST_STAFF.OPREATION_POS_NAME, " +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_NAME, " +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_ARREST.OFFICE_NAME ARREST_OFFICE_NAME," +
                        "    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH," +
                        "    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN," +
                        "    MAS_DISTRICT.DISTRICT_NAME_TH," +
                        "    MAS_DISTRICT.DISTRICT_NAME_EN," +
                        "    MAS_PROVINCE.PROVINCE_NAME_TH," +
                        "    MAS_PROVINCE.PROVINCE_NAME_EN," +
                        "    MAS_LAW_GROUP_SECTION.SECTION_NAME," +
                        "    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID," +
                        "    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME, " +
                        "    MAS_LAW_GUILTBASE.GUILTBASE_NAME, " +
                        "    MAS_LAW_PENALTY.SECTION_ID, " +
                        "    MAS_LAW_PENALTY.PENALTY_DESC, " +
                        "    case when OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH is null or OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_LAWSUIT_STAFF.TITLE_NAME_TH ||''|| OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME " +
                        "    else OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_LAWSUIT_STAFF.FIRST_NAME ||' '|| OPS_LAWSUIT_STAFF.LAST_NAME end as LAWSUIT_STAF_NAME, " +
                        "    OPS_LAWSUIT_STAFF.OPREATION_POS_NAME LAWSUIT_OPREATION_POS_NAME, " +
                        "    OPS_LAWSUIT_STAFF.OPERATION_OFFICE_NAME LAWSUIT_OPERATION_OFFICE_NAME, " +
                        "    OPS_LAWSUIT_STAFF.OPERATION_OFFICE_SHORT_NAME LAWSUIT_OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_LAWSUIT.OFFICE_NAME LAWSUIT_OFFICE_NAME," +
                        "    OPS_LAWSUIT.IS_OUTSIDE LAWSUIT_IS_OUTSIDE," +
                        "    CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO," +
                        "    TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO_YEAR," +
                        "    OPS_LAWSUIT.LAWSUIT_DATE, " +
                        "    OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE," +
                        "    OPS_LAWSUIT_DETAIL.LAWSUIT_END," +
                        "    case when OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH is null or OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_PROVE_STAFF.TITLE_NAME_TH ||''|| OPS_PROVE_STAFF.FIRST_NAME ||' '|| OPS_PROVE_STAFF.LAST_NAME " +
                        "    else OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_PROVE_STAFF.FIRST_NAME ||' '|| OPS_PROVE_STAFF.LAST_NAME end as PROVE_STAFF_NAME, " +
                        "    OPS_PROVE_STAFF.OPREATION_POS_NAME PROVE_OPREATION_POS_NAME, " +
                        "    OPS_PROVE_STAFF.OPERATION_OFFICE_NAME PROVE_OPERATION_OFFICE_NAME, " +
                        "    OPS_PROVE_STAFF.OPERATION_OFFICE_SHORT_NAME PROVE_OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_PROVE.IS_OUTSIDE PROVE_IS_OUTSIDE," +
                        "    CASE WHEN OPS_PROVE.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_PROVE.PROVE_NO || CASE WHEN OPS_PROVE.PROVE_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_PROVE.PROVE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS PROVE_NO," +
                        "    TO_CHAR(OPS_PROVE.PROVE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS PROVE_NO_YEAR," +
                        "    OPS_PROVE.RECEIVE_DOC_DATE RECEIVE_DOC_DATE, " +
                        "    OPS_PROVE.RECEIVE_OFFICE_NAME RECEIVE_OFFICE_NAME," +
                        "    case when OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH is null or OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_COMPARE_STAFF.TITLE_NAME_TH ||''|| OPS_COMPARE_STAFF.FIRST_NAME ||' '|| OPS_COMPARE_STAFF.LAST_NAME " +
                        "    else OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_COMPARE_STAFF.FIRST_NAME ||' '|| OPS_COMPARE_STAFF.LAST_NAME end as COMPARE_STAFF_NAME, " +
                        "    OPS_COMPARE_STAFF.OPREATION_POS_NAME COMPARE_OPREATION_POS_NAME, " +
                        "    OPS_COMPARE_STAFF.OPERATION_OFFICE_NAME COMPARE_OPERATION_OFFICE_NAME, " +
                        "    OPS_COMPARE_STAFF.OPERATION_OFFICE_SHORT_NAME COMPARE_OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_COMPARE.IS_OUTSIDE COMPARE_IS_OUTSIDE," +
                        "    CASE WHEN OPS_COMPARE.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_COMPARE.COMPARE_NO || CASE WHEN OPS_COMPARE.COMPARE_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_NO," +
                        "    TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR,'YYYY','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS COMPARE_NO_YEAR," +
                        "    OPS_COMPARE.COMPARE_DATE," +
                        "    OPS_COMPARE.OFFICE_NAME COMPARE_OFFICE_NAME," +
                        "    CASE WHEN OPS_COMPARE.COMPARE_NO IS NOT NULL THEN 'เปรียบเทียบแล้ว' else 'ยังไม่เปรียบเทียบ' END COMPARE_STATUS," +
                        "    OPS_PROVE.IS_ACTIVE PROVE_IS_ACTIVE," +
                        "    MAS_LAW_GUILTBASE.IS_PROVE, " +
                        "    OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
                        "    FROM OPS_LAWSUIT" +
                        "    LEFT JOIN OPS_COMPARE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_COMPARE.LAWSUIT_ID AND OPS_COMPARE.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_MAPPING.COMPARE_ID " +
                        "    LEFT JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID = OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID AND OPS_COMPARE_DETAIL.COMPARE_TYPE = 1 " +
                        "    LEFT JOIN OPS_COMPARE_STAFF ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_STAFF.COMPARE_ID AND OPS_COMPARE_STAFF.IS_ACTIVE = 1 AND OPS_COMPARE_STAFF.CONTRIBUTOR_ID = 27" +
                        "    INNER JOIN OPS_ARREST_INDICTMENT ON OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID" +
                        "    INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
                        "    INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
                        "    INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
                        "    INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
                        "    INNER JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID" +
                        "    INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID" +
                        "    INNER JOIN OPS_LAWSUIT_STAFF ON OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID" +
                        "    LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID AND OPS_PROVE.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_PROVE_STAFF ON OPS_PROVE.PROVE_ID = OPS_PROVE_STAFF.PROVE_ID AND OPS_PROVE_STAFF.IS_ACTIVE = 1 AND OPS_PROVE_STAFF.CONTRIBUTOR_ID = 25" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
                        "    LEFT JOIN OPS_LAWSUIT_DETAIL ON OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID = OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID AND OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "    INNER JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID" +
                        "    INNER JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
                        "    INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID" +
                        "    INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID" +
                        "    LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID AND OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1" +
                        "    WHERE OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT.IS_LAWSUIT = 1" +
                        "    AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        //"    AND OPS_COMPARE_DETAIL.COMPARE_TYPE  = 1" +
                        "    AND OPS_ARREST.IS_ACTIVE = 1" +
                        "    AND OPS_ARREST_STAFF.IS_ACTIVE = 1" +
                        "    AND OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14" +
                        "    AND OPS_LAWSUIT_STAFF.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID = 16" + sqlBuilder.toString() +
                        "    ORDER BY OPS_ARREST.OCCURRENCE_DATE DESC , OPS_ARREST.ARREST_CODE DESC" +
                        ")" +
                        "select " +
                        "    COMPARE_ID," +
                        "    LAWSUIT_ID," +
                        "    ARREST_ID," +
                        "    INDICTMENT_ID," +
                        "    ARREST_CODE," +
                        "    ARREST_DATE," +
                        "    OCCURRENCE_DATE," +
                        "    ARREST_STAFF_NAME," +
                        "    OPREATION_POS_NAME," +
                        "    OPERATION_OFFICE_NAME," +
                        "    OPERATION_OFFICE_SHORT_NAME," +
                        "    ARREST_OFFICE_NAME," +
                        "    SUB_DISTRICT_NAME_TH," +
                        "    SUB_DISTRICT_NAME_EN," +
                        "    DISTRICT_NAME_TH," +
                        "    DISTRICT_NAME_EN," +
                        "    PROVINCE_NAME_TH," +
                        "    PROVINCE_NAME_EN," +
                        "    SECTION_NAME," +
                        "    SUBSECTION_ID," +
                        "    SUBSECTION_NAME," +
                        "    GUILTBASE_NAME," +
                        "    SECTION_ID," +
                        "    PENALTY_DESC," +
                        "    LAWSUIT_STAF_NAME," +
                        "    LAWSUIT_OPREATION_POS_NAME," +
                        "    LAWSUIT_OPERATION_OFFICE_NAME," +
                        "    LAWSUIT_OPERATION_OFFICE_SHORT_NAME," +
                        "    LAWSUIT_OFFICE_NAME," +
                        "    LAWSUIT_IS_OUTSIDE," +
                        "    LAWSUIT_NO," +
                        "    LAWSUIT_NO_YEAR," +
                        "    LAWSUIT_DATE," +
                        "    LAWSUIT_TYPE," +
                        "    LAWSUIT_END," +
                        "    PROVE_STAFF_NAME," +
                        "    PROVE_OPREATION_POS_NAME," +
                        "    PROVE_OPERATION_OFFICE_NAME," +
                        "    PROVE_OPERATION_OFFICE_SHORT_NAME," +
                        "    PROVE_IS_OUTSIDE," +
                        "    PROVE_NO," +
                        "    PROVE_NO_YEAR," +
                        "    RECEIVE_DOC_DATE," +
                        "    RECEIVE_OFFICE_NAME," +
                        "    COMPARE_STAFF_NAME," +
                        "    COMPARE_OPREATION_POS_NAME," +
                        "    COMPARE_OPERATION_OFFICE_NAME," +
                        "    COMPARE_OPERATION_OFFICE_SHORT_NAME," +
                        "    COMPARE_IS_OUTSIDE," +
                        "    COMPARE_NO," +
                        "    COMPARE_NO_YEAR," +
                        "    COMPARE_DATE," +
                        "    COMPARE_OFFICE_NAME," +
                        "    COMPARE_STATUS," +
                        "    PROVE_IS_ACTIVE," +
                        "    IS_PROVE," +
                        "    LISTAGG(LAWBREAKER_ID, ',') WITHIN GROUP (" +
                        "    ORDER BY " +
                        "       COMPARE_ID," +
                        "        LAWSUIT_ID," +
                        "        ARREST_ID," +
                        "        INDICTMENT_ID," +
                        "        ARREST_CODE," +
                        "        ARREST_DATE," +
                        "        OCCURRENCE_DATE," +
                        "        ARREST_STAFF_NAME," +
                        "        OPREATION_POS_NAME," +
                        "        OPERATION_OFFICE_NAME," +
                        "        OPERATION_OFFICE_SHORT_NAME," +
                        "        ARREST_OFFICE_NAME," +
                        "        SUB_DISTRICT_NAME_TH," +
                        "        SUB_DISTRICT_NAME_EN," +
                        "        DISTRICT_NAME_TH," +
                        "        DISTRICT_NAME_EN," +
                        "        PROVINCE_NAME_TH," +
                        "        PROVINCE_NAME_EN," +
                        "        SECTION_NAME," +
                        "        SUBSECTION_ID," +
                        "        SUBSECTION_NAME," +
                        "        GUILTBASE_NAME," +
                        "        SECTION_ID," +
                        "        PENALTY_DESC," +
                        "        LAWSUIT_STAF_NAME," +
                        "        LAWSUIT_OPREATION_POS_NAME," +
                        "        LAWSUIT_OPERATION_OFFICE_NAME," +
                        "        LAWSUIT_OPERATION_OFFICE_SHORT_NAME," +
                        "        LAWSUIT_OFFICE_NAME," +
                        "        LAWSUIT_IS_OUTSIDE," +
                        "        LAWSUIT_NO," +
                        "        LAWSUIT_NO_YEAR," +
                        "        LAWSUIT_DATE," +
                        "        LAWSUIT_TYPE," +
                        "        LAWSUIT_END," +
                        "        PROVE_STAFF_NAME," +
                        "        PROVE_OPREATION_POS_NAME," +
                        "        PROVE_OPERATION_OFFICE_NAME," +
                        "        PROVE_OPERATION_OFFICE_SHORT_NAME," +
                        "        PROVE_IS_OUTSIDE," +
                        "        PROVE_NO," +
                        "        PROVE_NO_YEAR," +
                        "        RECEIVE_DOC_DATE," +
                        "        RECEIVE_OFFICE_NAME," +
                        "        COMPARE_STAFF_NAME," +
                        "        COMPARE_OPREATION_POS_NAME," +
                        "        COMPARE_OPERATION_OFFICE_NAME," +
                        "        COMPARE_OPERATION_OFFICE_SHORT_NAME," +
                        "        COMPARE_IS_OUTSIDE," +
                        "        COMPARE_NO," +
                        "        COMPARE_NO_YEAR," +
                        "        COMPARE_DATE," +
                        "        COMPARE_OFFICE_NAME," +
                        "        COMPARE_STATUS, " +
                        "        PROVE_IS_ACTIVE," +
                        "        IS_PROVE" +
                        "    ) AS LAWBREAKER_ID" +
                        " from temp" +
                        " GROUP BY" +
                        "        COMPARE_ID," +
                        "        LAWSUIT_ID," +
                        "        ARREST_ID," +
                        "        INDICTMENT_ID," +
                        "        ARREST_CODE," +
                        "        ARREST_DATE," +
                        "        OCCURRENCE_DATE," +
                        "        ARREST_STAFF_NAME," +
                        "        OPREATION_POS_NAME," +
                        "        OPERATION_OFFICE_NAME," +
                        "        OPERATION_OFFICE_SHORT_NAME," +
                        "        ARREST_OFFICE_NAME," +
                        "        SUB_DISTRICT_NAME_TH," +
                        "        SUB_DISTRICT_NAME_EN," +
                        "        DISTRICT_NAME_TH," +
                        "        DISTRICT_NAME_EN," +
                        "        PROVINCE_NAME_TH," +
                        "        PROVINCE_NAME_EN," +
                        "        SECTION_NAME," +
                        "        SUBSECTION_ID," +
                        "        SUBSECTION_NAME," +
                        "        GUILTBASE_NAME," +
                        "        SECTION_ID," +
                        "        PENALTY_DESC," +
                        "        LAWSUIT_STAF_NAME," +
                        "        LAWSUIT_OPREATION_POS_NAME," +
                        "        LAWSUIT_OPERATION_OFFICE_NAME," +
                        "        LAWSUIT_OPERATION_OFFICE_SHORT_NAME," +
                        "        LAWSUIT_OFFICE_NAME," +
                        "        LAWSUIT_IS_OUTSIDE," +
                        "        LAWSUIT_NO," +
                        "        LAWSUIT_NO_YEAR," +
                        "        LAWSUIT_DATE," +
                        "        LAWSUIT_TYPE," +
                        "        LAWSUIT_END," +
                        "        PROVE_STAFF_NAME," +
                        "        PROVE_OPREATION_POS_NAME," +
                        "        PROVE_OPERATION_OFFICE_NAME," +
                        "        PROVE_OPERATION_OFFICE_SHORT_NAME," +
                        "        PROVE_IS_OUTSIDE," +
                        "        PROVE_NO," +
                        "        PROVE_NO_YEAR," +
                        "        RECEIVE_DOC_DATE," +
                        "        RECEIVE_OFFICE_NAME," +
                        "        COMPARE_STAFF_NAME," +
                        "        COMPARE_OPREATION_POS_NAME," +
                        "        COMPARE_OPERATION_OFFICE_NAME," +
                        "        COMPARE_OPERATION_OFFICE_SHORT_NAME," +
                        "        COMPARE_IS_OUTSIDE," +
                        "        COMPARE_NO," +
                        "        COMPARE_NO_YEAR," +
                        "        COMPARE_DATE," +
                        "        COMPARE_OFFICE_NAME," +
                        "        COMPARE_STATUS," +
                        "        PROVE_IS_ACTIVE," +
                        "        IS_PROVE"+
                        "		 ORDER BY OCCURRENCE_DATE DESC , ARREST_CODE DESC");



        log.info("[SQL]  : " + sqlBuilderDetail.toString());
        System.out.println("[SQL] [AdjustCompareListgetByKeyword]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<CompareList> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public CompareList mapRow(ResultSet rs, int rowNum) throws SQLException {
                CompareList item = new CompareList();

                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setARREST_DATE(rs.getString("ARREST_DATE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setARREST_STAFF_NAME(rs.getString("ARREST_STAFF_NAME"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                item.setARREST_OFFICE_NAME(rs.getString("ARREST_OFFICE_NAME"));
                item.setSUB_DISTRICT_NAME_TH(rs.getString("SUB_DISTRICT_NAME_TH"));
                item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
                item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
                item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
                item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
                item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
                item.setLAWSUIT_STAF_NAME(rs.getString("LAWSUIT_STAF_NAME"));
                item.setLAWSUIT_OPREATION_POS_NAME(rs.getString("LAWSUIT_OPREATION_POS_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_NAME(rs.getString("LAWSUIT_OPERATION_OFFICE_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_SHORT_NAME(rs.getString("LAWSUIT_OPERATION_OFFICE_SHORT_NAME"));
                item.setLAWSUIT_OFFICE_NAME(rs.getString("LAWSUIT_OFFICE_NAME"));
                item.setLAWSUIT_IS_OUTSIDE(rs.getInt("LAWSUIT_IS_OUTSIDE"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setLAWSUIT_TYPE(rs.getString("LAWSUIT_TYPE"));
                item.setLAWSUIT_END(rs.getString("LAWSUIT_END"));
                item.setPROVE_STAFF_NAME(rs.getString("PROVE_STAFF_NAME"));
                item.setPROVE_OPREATION_POS_NAME(rs.getString("PROVE_OPREATION_POS_NAME"));
                item.setPROVE_OPERATION_OFFICE_NAME(rs.getString("PROVE_OPERATION_OFFICE_NAME"));
                item.setPROVE_OPERATION_OFFICE_SHORT_NAME(rs.getString("PROVE_OPERATION_OFFICE_SHORT_NAME"));
                item.setPROVE_IS_OUTSIDE(rs.getInt("PROVE_IS_OUTSIDE"));
                item.setPROVE_NO(rs.getString("PROVE_NO"));
                item.setPROVE_NO_YEAR(rs.getString("PROVE_NO_YEAR"));
                item.setRECEIVE_DOC_DATE(rs.getString("RECEIVE_DOC_DATE"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setCOMPARE_STAFF_NAME(rs.getString("COMPARE_STAFF_NAME"));
                item.setCOMPARE_OPREATION_POS_NAME(rs.getString("COMPARE_OPREATION_POS_NAME"));
                item.setCOMPARE_OPERATION_OFFICE_NAME(rs.getString("COMPARE_OPERATION_OFFICE_NAME"));
                item.setCOMPARE_OPERATION_OFFICE_SHORT_NAME(rs.getString("COMPARE_OPERATION_OFFICE_SHORT_NAME"));
                item.setCOMPARE_IS_OUTSIDE(rs.getInt("COMPARE_IS_OUTSIDE"));
                item.setCOMPARE_NO(rs.getString("COMPARE_NO"));
                item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                item.setCOMPARE_OFFICE_NAME(rs.getString("COMPARE_OFFICE_NAME"));
                item.setCOMPARE_STATUS(rs.getString("COMPARE_STATUS"));
                item.setPROVE_IS_ACTIVE(rs.getInt("PROVE_IS_ACTIVE"));
                item.setIS_PROVE(rs.getInt("IS_PROVE"));
                //item.setArrestLawbreaker(getArrestLawbreakerByLAWBREAKER_ID(rs.getString("LAWBREAKER_ID")));

                item.setArrestLawbreaker(getArrestLawbreakerByLAWBREAKER_ID(rs.getInt("INDICTMENT_ID")));

                return item;
            }
        });

        return dataList;
    }
}
