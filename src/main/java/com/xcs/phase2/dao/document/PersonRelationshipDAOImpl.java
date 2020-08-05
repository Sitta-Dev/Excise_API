package com.xcs.phase2.dao.document;

import com.xcs.phase2.model.document.PersonRelationship;
import com.xcs.phase2.request.document.PersonRelationshipgetByPersonIdReq;
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
public class PersonRelationshipDAOImpl extends DocumentExt implements PersonRelationshipDAO{

    private static final Logger log = LoggerFactory.getLogger(PersonRelationshipDAOImpl.class);

    @Override
    public List<PersonRelationship> PersonRelationshipgetByPersonId(PersonRelationshipgetByPersonIdReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
                        "    MAS_PERSON_PHOTO.PHOTO, " +
                        "    CASE WHEN OPS_ARREST_LAWBREAKER.PERSON_TYPE=0 THEN OPS_ARREST_LAWBREAKER.TITLE_NAME_TH WHEN OPS_ARREST_LAWBREAKER.PERSON_TYPE=1 THEN OPS_ARREST_LAWBREAKER.TITLE_NAME_EN END || ' ' || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' ' || OPS_ARREST_LAWBREAKER.LAST_NAME AS ARREST_LAWBREAKER_NAME, " +
                        "    OPS_ARREST.ARREST_CODE, " +
                        "    OPS_ARREST.OCCURRENCE_DATE, " +
                        "    CASE WHEN OPS_ARREST_LOCALE.ADDRESS_NO IS NOT NULL THEN OPS_ARREST_LOCALE.ADDRESS_NO END AS ADDRESS," +
                        "    CASE WHEN OPS_ARREST_LOCALE.VILLAGE_NO IS NOT NULL THEN 'หมู่ที่ ' || OPS_ARREST_LOCALE.VILLAGE_NO END AS VILLAGE," +
                        "    CASE WHEN OPS_ARREST_LOCALE.BUILDING_NAME IS NOT NULL THEN 'ชื่ออาคาร ' || OPS_ARREST_LOCALE.BUILDING_NAME END as BUILDING," +
                        "    CASE WHEN OPS_ARREST_LOCALE.ROOM_NO IS NOT NULL THEN 'เลขที่ห้อง '|| OPS_ARREST_LOCALE.ROOM_NO END as ROOM," +
                        "    CASE WHEN OPS_ARREST_LOCALE.FLOOR IS NOT NULL THEN 'ชั้น '|| OPS_ARREST_LOCALE.FLOOR END as FLOOR," +
                        "    CASE WHEN OPS_ARREST_LOCALE.VILLAGE_NAME IS NOT NULL THEN 'หมู่บ้าน ' || OPS_ARREST_LOCALE.VILLAGE_NAME END as VILLAGE_NAME," +
                        "    CASE WHEN OPS_ARREST_LOCALE.ALLEY IS NOT NULL THEN 'ตรอก '|| OPS_ARREST_LOCALE.ALLEY END as ALLEY," +
                        "    CASE WHEN OPS_ARREST_LOCALE.LANE IS NOT NULL THEN 'ซอย '|| OPS_ARREST_LOCALE.LANE END as LANE," +
                        "    CASE WHEN OPS_ARREST_LOCALE.ROAD IS NOT NULL THEN 'ถนน '|| OPS_ARREST_LOCALE.ROAD END as ROAD," +
                        "    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH|| '/' ||MAS_DISTRICT.DISTRICT_NAME_TH|| '/' ||MAS_PROVINCE.PROVINCE_NAME_TH as PROVINCE, " +
                        "    OPS_ARREST_INDICTMENT.GUILTBASE_ID, " +
                        "    MAS_LAW_GUILTBASE.GUILTBASE_NAME, " +
                        "    MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME " +
                        "    FROM OPS_ARREST " +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID=OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "    LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST.ARREST_ID=OPS_ARREST_LAWBREAKER.ARREST_ID" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_PRODUCT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID" +
                        "    LEFT JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID=OPS_ARREST_LOCALE.ARREST_ID" +
                        "    LEFT JOIN MAS_PRODUCT_GROUP ON OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID=MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID" +
                        "    LEFT JOIN MAS_PERSON ON OPS_ARREST_LAWBREAKER.PERSON_ID=MAS_PERSON.PERSON_ID" +
                        "    LEFT JOIN MAS_PERSON_RELATIONSHIP ON MAS_PERSON.PERSON_ID=MAS_PERSON_RELATIONSHIP.PERSON_ID" +
                        "    LEFT JOIN MAS_RELATIONSHIP ON MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID=MAS_RELATIONSHIP.RELATIONSHIP_ID" +
                        "    LEFT JOIN MAS_PERSON_PHOTO ON MAS_PERSON.PERSON_ID=MAS_PERSON_PHOTO.PERSON_ID" +
                        "    LEFT JOIN MAS_LAW_GUILTBASE on MAS_LAW_GUILTBASE.GUILTBASE_ID=OPS_ARREST_INDICTMENT.GUILTBASE_ID and MAS_LAW_GUILTBASE.IS_PROVE=1" +
                        "    LEFT JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID=MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
                        "    LEFT JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID=MAS_DISTRICT.DISTRICT_ID" +
                        "    LEFT JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID=MAS_PROVINCE.PROVINCE_ID" +
                        "    LEFT JOIN MAS_PERSON_RELATIONSHIP PERSON_RELATIVE ON PERSON_RELATIVE.PERSON_ID=MAS_PERSON.PERSON_ID" +
                        "    WHERE OPS_ARREST_LAWBREAKER.IS_ACTIVE = 1 and ARREST_CODE ='"+req.getARREST_CODE()+"'" +
                        "    UNION ALL" +
                        "    SELECT DISTINCT" +
                        "    OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
                        "    MAS_PERSON_PHOTO.PHOTO, " +
                        "    NVL(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH,MAS_PERSON_RELATIONSHIP.TITLE_NAME_EN) || ' ' || MAS_PERSON_RELATIONSHIP.FIRST_NAME || ' ' || MAS_PERSON_RELATIONSHIP.LAST_NAME AS MAS_PERSON_RELATIONSHIP_NAME," +
                        "    OPS_ARREST.ARREST_CODE, " +
                        "    OPS_ARREST.OCCURRENCE_DATE, " +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL" +
                        "    FROM MAS_PERSON_RELATIONSHIP" +
                        "    LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST_LAWBREAKER.PERSON_ID=MAS_PERSON_RELATIONSHIP.PERSON_ID" +
                        "    LEFT JOIN OPS_ARREST ON OPS_ARREST.ARREST_ID=OPS_ARREST_LAWBREAKER.ARREST_ID" +
                        "    LEFT JOIN MAS_PERSON_PHOTO ON MAS_PERSON_PHOTO.PERSON_ID=MAS_PERSON_RELATIONSHIP.PERSON_ID" +
                        "    WHERE MAS_PERSON_RELATIONSHIP.PERSON_ID = '"+req.getPERSON_ID()+"' ");


        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<PersonRelationship> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public PersonRelationship mapRow(ResultSet rs, int rowNum) throws SQLException {
                PersonRelationship item = new PersonRelationship();
                item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
                item.setPHOTO(rs.getString("PHOTO"));
                item.setARREST_LAWBREAKER_NAME(rs.getString("ARREST_LAWBREAKER_NAME"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setADDRESS(rs.getString("ADDRESS"));
                item.setVILLAGE(rs.getString("VILLAGE"));
                item.setBUILDING(rs.getString("BUILDING"));
                item.setROOM(rs.getString("ROOM"));
                item.setFLOOR(rs.getString("FLOOR"));
                item.setVILLAGE_NAME(rs.getString("VILLAGE_NAME"));
                item.setALLEY(rs.getString("ALLEY"));
                item.setLANE(rs.getString("LANE"));
                item.setROAD(rs.getString("ROAD"));
                item.setPROVINCE(rs.getString("PROVINCE"));
                item.setGUILTBASE_ID(rs.getInt("GUILTBASE_ID"));
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));

                return item;
            }
        });

        return dataList;

    }
}
