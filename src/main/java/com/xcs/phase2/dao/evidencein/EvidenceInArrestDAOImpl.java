package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.model.evidencein.EvidenceInArrest;
import com.xcs.phase2.request.evidencein.EvidenceInArrestgetByProveIDReq;
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
public class EvidenceInArrestDAOImpl extends EvidenceInExt implements EvidenceInArrestDAO{

    private static final Logger log = LoggerFactory.getLogger(EvidenceInArrestDAOImpl.class);

    @Override
    public List<EvidenceInArrest> EvidenceInArrestgetByProveID(EvidenceInArrestgetByProveIDReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_ARREST.ARREST_CODE," +
                        "    OPS_ARREST.OCCURRENCE_DATE," +
                        "    OPS_ARREST.OFFICE_NAME," +
                        "    OPS_ARREST_STAFF.TITLE_NAME_TH||''||OPS_ARREST_STAFF.FIRST_NAME||' '||OPS_ARREST_STAFF.LAST_NAME as ARREST_STAFF," +
                        "    OPS_ARREST_STAFF.OPREATION_POS_NAME as ARREST_OPREATION_POS_NAME," +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_NAME as ARREST_OPERATION_OFFICE_NAME," +
                        "    SUBSECTIONRULE.SUBSECTION_RULE_ID," +
                        "    SUBSECTION.SUBSECTION_ID," +
                        "    SUBSECTION.SUBSECTION_NAME," +
                        "    SUBSECTION.SUBSECTION_NO," +
                        "    GUILTBASE.GUILTBASE_ID," +
                        "    GUILTBASE.GUILTBASE_NAME," +
                        "    SECTION2.SECTION_ID," +
                        "    PENALTY.PENALTY_DESC," +
                        "    OPS_LAWSUIT.LAWSUIT_ID," +
                        "    OPS_LAWSUIT.LAWSUIT_NO," +
                        "    OPS_LAWSUIT.LAWSUIT_NO_YEAR," +
                        "    OPS_LAWSUIT.LAWSUIT_DATE," +
                        "    OPS_LAWSUIT_STAFF.TITLE_NAME_TH||''||OPS_LAWSUIT_STAFF.FIRST_NAME||' '||OPS_LAWSUIT_STAFF.LAST_NAME as LAWSUIT_STAFF," +
                        "    OPS_LAWSUIT_STAFF.OPREATION_POS_NAME as LAWSUIT_OPREATION_POS_NAME," +
                        "    OPS_LAWSUIT_STAFF.OPERATION_OFFICE_NAME as LAWSUIT_OPERATION_OFFICE_NAME," +
                        "    OPS_LAWSUIT.IS_OUTSIDE as LAWSUIT_IS_OUTSIDE," +
                        "    OPS_PROVE.PROVE_ID," +
                        "    OPS_PROVE.PROVE_NO," +
                        "    OPS_PROVE.PROVE_NO_YEAR," +
                        "    OPS_PROVE.PROVE_DATE," +
                        "    OPS_PROVE_STAFF.TITLE_NAME_TH||''||OPS_PROVE_STAFF.FIRST_NAME||' '||OPS_PROVE_STAFF.LAST_NAME as PROVE_STAFF," +
                        "    OPS_PROVE_STAFF.OPREATION_POS_NAME as PROVE_OPREATION_POS_NAME," +
                        "    OPS_PROVE.IS_OUTSIDE as PROVE_IS_OUTSIDE," +
                        "    OPS_PROVE.RECEIVE_OFFICE_NAME," +
                        "    OPS_PROVE.DELIVERY_OFFICE_NAME," +
                        "    OPS_PROVE.DELIVERY_DOC_NO_1," +
                        "    OPS_PROVE.DELIVERY_DOC_NO_2," +
                        "    OPS_PROVE.DELIVERY_DOC_DATE," +
                        "    OPS_EVIDENCE_IN_STAFF.TITLE_NAME_TH||''||OPS_EVIDENCE_IN_STAFF.FIRST_NAME||' '||OPS_EVIDENCE_IN_STAFF.LAST_NAME as EVIDENCE_IN_STAFF," +
                        "    OPS_EVIDENCE_IN_STAFF.OPREATION_POS_NAME as EVIDENCE_IN_OPREATION_POS_NAME," +
                        "    OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_NAME as EVIDENCE_IN_OPERATION_OFFICE_NAME," +
                        "    OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_SHORT_NAME" +
                        "    from OPS_PROVE" +
                        "    left join OPS_LAWSUIT on OPS_PROVE.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
                        "    left join OPS_ARREST_INDICTMENT on OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID" +
                        "    and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "    left join OPS_ARREST on OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    and OPS_ARREST.IS_ACTIVE = 1" +
                        "    left join OPS_ARREST_STAFF on OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID" +
                        "    and OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14 and OPS_ARREST_STAFF.IS_ACTIVE = 1 " +
                        "    left join OPS_ARREST_LOCALE on OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID" +
                        "    and OPS_ARREST_LOCALE.IS_ACTIVE = 1" +
                        "    left join OPS_LAWSUIT on OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID" +
                        "    and OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    left join OPS_LAWSUIT_STAFF on OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID" +
                        "    and OPS_LAWSUIT_STAFF.IS_ACTIVE = 1" +
                        "    left join OPS_PROVE_STAFF on OPS_PROVE.PROVE_ID = OPS_PROVE_STAFF.PROVE_ID" +
                        "    and OPS_PROVE_STAFF.IS_ACTIVE = 1" +
                        "    left join MAS_LAW_GUILTBASE GUILTBASE on OPS_ARREST_INDICTMENT.GUILTBASE_ID = GUILTBASE.GUILTBASE_ID" +
                        "    and GUILTBASE.IS_ACTIVE = 1" +
                        "    left join MAS_LAW_GROUP_SUBSECTION_RULE SUBSECTIONRULE on GUILTBASE.SUBSECTION_RULE_ID = SUBSECTIONRULE.SUBSECTION_RULE_ID" +
                        "    and SUBSECTIONRULE.IS_ACTIVE = 1" +
                        "    left join MAS_LAW_GROUP_SUBSECTION SUBSECTION on SUBSECTIONRULE.SUBSECTION_ID = SUBSECTION.SUBSECTION_ID" +
                        "    and SUBSECTION.IS_ACTIVE = 1" +
                        "    left join MAS_LAW_GROUP_SECTION SECTION on SUBSECTION.SECTION_ID = SECTION.SECTION_ID" +
                        "    and SECTION.IS_ACTIVE = 1" +
                        "    left join MAS_LAW_GROUP_SECTION SECTION2 on SUBSECTIONRULE.SECTION_ID = SECTION2.SECTION_ID" +
                        "    and SECTION2.IS_ACTIVE = 1" +
                        "    left join MAS_LAW_PENALTY PENALTY on SECTION2.SECTION_ID = PENALTY.SECTION_ID" +
                        "    and PENALTY.IS_ACTIVE = 1" +
                        "    left join OPS_PROVE on OPS_PROVE.PROVE_ID = OPS_PROVE.PROVE_ID" +
                        "    and OPS_PROVE.IS_ACTIVE = 1" +
                        "    left join OPS_EVIDENCE_IN on OPS_PROVE.PROVE_ID = OPS_EVIDENCE_IN.PROVE_ID" +
                        "    and OPS_EVIDENCE_IN.IS_ACTIVE = 1" +
                        "    left join OPS_EVIDENCE_IN_STAFF on OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID" +
                        "    and OPS_EVIDENCE_IN_STAFF.IS_ACTIVE = 1" +
                        "    and OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID = 59" +
                        "    left join MAS_SUB_DISTRICT on OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
                        "    left join MAS_DISTRICT on MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID" +
                        "    left join MAS_PROVINCE on MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID" +
                        "    where OPS_PROVE.IS_ACTIVE = 1" +
                        "    and OPS_PROVE.PROVE_ID = '"+req.getPROVE_ID()+"' ");


        log.info("[SQL ] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInArrest> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceInArrest mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInArrest item = new EvidenceInArrest();

                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setARREST_STAFF(rs.getString("ARREST_STAFF"));
                item.setARREST_OPREATION_POS_NAME(rs.getString("ARREST_OPREATION_POS_NAME"));
                item.setARREST_OPERATION_OFFICE_NAME(rs.getString("ARREST_OPERATION_OFFICE_NAME"));
                item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                item.setSUBSECTION_NO(rs.getString("SUBSECTION_NO"));
                item.setGUILTBASE_ID(rs.getInt("GUILTBASE_ID"));
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setLAWSUIT_STAFF(rs.getString("LAWSUIT_STAFF"));
                item.setLAWSUIT_OPREATION_POS_NAME(rs.getString("LAWSUIT_OPREATION_POS_NAME"));
                item.setLAWSUIT_OPERATION_OFFICE_NAME(rs.getString("LAWSUIT_OPERATION_OFFICE_NAME"));
                item.setLAWSUIT_IS_OUTSIDE(rs.getInt("LAWSUIT_IS_OUTSIDE"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setPROVE_NO(rs.getString("PROVE_NO"));
                item.setPROVE_NO_YEAR(rs.getString("PROVE_NO_YEAR"));
                item.setPROVE_DATE(rs.getString("PROVE_DATE"));
                item.setPROVE_STAFF(rs.getString("PROVE_STAFF"));
                item.setPROVE_OPREATION_POS_NAME(rs.getString("PROVE_OPREATION_POS_NAME"));
                item.setPROVE_IS_OUTSIDE(rs.getInt("PROVE_IS_OUTSIDE"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                item.setDELIVERY_DOC_NO_1(rs.getString("DELIVERY_DOC_NO_1"));
                item.setDELIVERY_DOC_NO_2(rs.getString("DELIVERY_DOC_NO_2"));
                item.setDELIVERY_DOC_DATE(rs.getString("DELIVERY_DOC_DATE"));
                item.setEVIDENCE_IN_STAFF(rs.getString("EVIDENCE_IN_STAFF"));
                item.setEVIDENCE_IN_OPREATION_POS_NAME(rs.getString("EVIDENCE_IN_OPREATION_POS_NAME"));
                item.setEVIDENCE_IN_OPERATION_OFFICE_NAME(rs.getString("EVIDENCE_IN_OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));



                return item;

            }
        });
        return dataList;

    }
}
