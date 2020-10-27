package com.xcs.phase2.dao.master;

import com.xcs.phase2.model.master.*;
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

public class MasterExt {

    private static final Logger log = LoggerFactory.getLogger(MasterExt.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected String getSequences(String strSql) {
        log.info("[SQL]  : " + strSql);
        return getJdbcTemplate().queryForObject(strSql, String.class);
    }

    protected List<MasLawGroupSubSection> getMasLawGroupSubSection(int SECTION_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select * " +
                        "    from MAS_LAW_GROUP_SUBSECTION" +
                        "    where is_active = 1 and section_id = '"+SECTION_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasLawGroupSubSection> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasLawGroupSubSection mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasLawGroupSubSection item = new MasLawGroupSubSection();
                item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setSUBSECTION_NO(rs.getString("SUBSECTION_NO"));
                item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;
    }

    protected MasLawGroup getMasLawGroup(int LAW_GROUP_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select * " +
                        "    from MAS_LAW_GROUP" +
                        "    where is_active = 1 and LAW_GROUP_ID = '"+LAW_GROUP_ID+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasLawGroup>() {

            public MasLawGroup extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasLawGroup item = new MasLawGroup();
                    item.setLAW_GROUP_ID(rs.getInt("LAW_GROUP_ID"));
                    item.setLAW_GROUP_NO(rs.getString("LAW_GROUP_NO"));
                    item.setLAW_GROUP_NAME(rs.getString("LAW_GROUP_NAME"));
                    item.setPART_NO(rs.getString("PART_NO"));
                    item.setPART_NAME(rs.getString("PART_NAME"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setEFFECTIVE_DATE(rs.getString("EFFECTIVE_DATE"));
                    item.setEXPIRE_DATE(rs.getString("EXPIRE_DATE"));

                    return item;
                }

                return null;
            }
        });
    }

    protected MasLawGroupSection masLawGroupSection(int SECTION_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select * " +
                        "    from MAS_LAW_GROUP_SECTION" +
                        "    where is_active = 1 and SECTION_ID = '"+SECTION_ID+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasLawGroupSection>() {

            public MasLawGroupSection extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasLawGroupSection item = new MasLawGroupSection();
                    item.setSECTION_ID(rs.getInt("SECTION_ID"));
                    item.setLAW_GROUP_ID(rs.getInt("LAW_GROUP_ID"));
                    item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                    item.setSECTION_DESC_1(rs.getString("SECTION_DESC_1"));
                    item.setSECTION_DESC_2(rs.getString("SECTION_DESC_2"));
                    item.setSECTION_DESC_3(rs.getString("SECTION_DESC_3"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    return item;
                }

                return null;
            }
        });
    }

    protected MasLawGroupSubSection masLawGroupSubSection(int SUBSECTION_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select * " +
                        "    from MAS_LAW_GROUP_SUBSECTION" +
                        "    where is_active = 1 and SUBSECTION_ID = '"+SUBSECTION_ID+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasLawGroupSubSection>() {

            public MasLawGroupSubSection extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasLawGroupSubSection item = new MasLawGroupSubSection();
                    item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                    item.setSECTION_ID(rs.getInt("SECTION_ID"));
                    item.setSUBSECTION_NO(rs.getString("SUBSECTION_NO"));
                    item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                    item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    return item;
                }

                return null;
            }
        });
    }

    protected List<MasLawGuiltbase> getMasLawGuiltbase(int SUBSECTION_RULE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select * " +
                        "    from MAS_LAW_GUILTBASE" +
                        "    where is_active = 1 and SUBSECTION_RULE_ID = '"+SUBSECTION_RULE_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasLawGuiltbase> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasLawGuiltbase mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasLawGuiltbase item = new MasLawGuiltbase();
                item.setGUILTBASE_ID(rs.getInt("GUILTBASE_ID"));
                item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setFINE(rs.getString("FINE"));
                item.setIS_PROVE(rs.getInt("IS_PROVE"));
                item.setIS_COMPARE(rs.getInt("IS_COMPARE"));
                item.setREMARK(rs.getString("REMARK"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;
    }

    // ============================= new

    protected List<New_MasLawGroupSubSectionNew> getNew_MasLawGroupSubSectionNew(String SUBSECTION_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select * " +
                        "    from MAS_LAW_GROUP_SUBSECTION" +
                        "    where is_active = 1 and subsection_id in ("+SUBSECTION_ID+") ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<New_MasLawGroupSubSectionNew> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public New_MasLawGroupSubSectionNew mapRow(ResultSet rs, int rowNum) throws SQLException {
                New_MasLawGroupSubSectionNew item = new New_MasLawGroupSubSectionNew();
                item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setSUBSECTION_NO(rs.getString("SUBSECTION_NO"));
                item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setMasLawGroupSubSectionRule(getNew_MasLawGroupSubSectionRule(rs.getInt("SUBSECTION_ID")));
                return item;
            }
        });

        return dataList;
    }

    protected List<New_MasLawGroupSubSectionRule> getNew_MasLawGroupSubSectionRule(int SUBSECTION_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select * " +
                        "    from MAS_LAW_GROUP_SUBSECTION_RULE" +
                        "    where is_active = 1 and subsection_id = "+SUBSECTION_ID);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<New_MasLawGroupSubSectionRule> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public New_MasLawGroupSubSectionRule mapRow(ResultSet rs, int rowNum) throws SQLException {
                New_MasLawGroupSubSectionRule item = new New_MasLawGroupSubSectionRule();
                item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setMasLawGuiltbase(getNew_MasLawGuiltbase(rs.getInt("SUBSECTION_RULE_ID")));
                item.setMasLawPenalty(getNew_MasLawPenalty(rs.getInt("SECTION_ID")));
                item.setMasLawGuiltbaseFine(getNew_MasLawGuiltbaseFinegetByCon(rs.getInt("SUBSECTION_RULE_ID")));



                return item;
            }
        });

        return dataList;
    }

    protected List<New_MasLawGuiltbase> getNew_MasLawGuiltbase(int SUBSECTION_RULE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select * " +
                        "    from MAS_LAW_GUILTBASE" +
                        "    where is_active = 1 and SUBSECTION_RULE_ID = '"+SUBSECTION_RULE_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<New_MasLawGuiltbase> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public New_MasLawGuiltbase mapRow(ResultSet rs, int rowNum) throws SQLException {
                New_MasLawGuiltbase item = new New_MasLawGuiltbase();
                item.setGUILTBASE_ID(rs.getInt("GUILTBASE_ID"));
                item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setFINE(rs.getString("FINE"));
                item.setIS_PROVE(rs.getInt("IS_PROVE"));
                item.setIS_COMPARE(rs.getInt("IS_COMPARE"));
                item.setREMARK(rs.getString("REMARK"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;
    }

    protected List<New_MasLawPenalty> getNew_MasLawPenalty(int SECTION_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select * " +
                        "    from MAS_LAW_PENALTY" +
                        "    where is_active = 1 and SECTION_ID = '"+SECTION_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<New_MasLawPenalty> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public New_MasLawPenalty mapRow(ResultSet rs, int rowNum) throws SQLException {
                New_MasLawPenalty item = new New_MasLawPenalty();
                item.setPENALTY_ID(rs.getInt("PENALTY_ID"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
                item.setIS_FINE_PRISON(rs.getInt("IS_FINE_PRISON"));
                item.setIS_FINE(rs.getInt("IS_FINE"));
                item.setFINE_RATE_MIN(rs.getFloat("FINE_RATE_MIN"));
                item.setFINE_RATE_MAX(rs.getFloat("FINE_RATE_MAX"));
                item.setFINE_MIN(rs.getFloat("FINE_MIN"));
                item.setFINE_MAX(rs.getFloat("FINE_MAX"));
                item.setIS_IMPRISON(rs.getInt("IS_IMPRISON"));
                item.setPRISON_RATE_MIN(rs.getFloat("PRISON_RATE_MIN"));
                item.setPRISON_RATE_MAX(rs.getFloat("PRISON_RATE_MAX"));
                item.setIS_TAX_PAID(rs.getInt("IS_TAX_PAID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;
    }

    public List<New_MasLawGuiltbaseFine> getNew_MasLawGuiltbaseFinegetByCon(int SUBSECTION_RULE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT * " +
                        "    FROM MAS_LAW_GUILTBASE_FINE " +
                        "    WHERE IS_ACTIVE = 1" +
                        "    AND SUBSECTION_RULE_ID = '"+SUBSECTION_RULE_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<New_MasLawGuiltbaseFine> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public New_MasLawGuiltbaseFine mapRow(ResultSet rs, int rowNum) throws SQLException {
                New_MasLawGuiltbaseFine item = new New_MasLawGuiltbaseFine();
                item.setFINE_ID(rs.getInt("FINE_ID"));
                item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setMISTREAT_START_NO(rs.getInt("MISTREAT_START_NO"));
                item.setMISTREAT_TO_NO(rs.getInt("MISTREAT_TO_NO"));
                item.setIS_FINE(rs.getInt("IS_FINE"));
                item.setFINE_RATE(rs.getFloat("FINE_RATE"));
                item.setMISTREAT_DESC(rs.getString("MISTREAT_DESC"));
                item.setMISTREAT_START_VOLUMN(rs.getFloat("MISTREAT_START_VOLUMN"));
                item.setMISTREAT_TO_VOLUMN(rs.getFloat("MISTREAT_TO_VOLUMN"));
                item.setFINE_AMOUNT(rs.getFloat("FINE_AMOUNT"));
                item.setFINE_TAX(rs.getFloat("FINE_TAX"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setMISTREAT_START_UNIT(rs.getInt("MISTREAT_START_UNIT"));
                item.setMISTREAT_TO_UNIT(rs.getInt("MISTREAT_TO_UNIT"));
                item.setSTATUS_VOLUMN(rs.getInt("STATUS_VOLUMN"));

                return item;
            }
        });

        return dataList;

    }
    
    public List<MasProductUnitMapping> MasProductMappinggetByCon(String UNIT_CODE) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("   SELECT UNIT_MAPPING_ID, "+
                		"	PRODUCT_GROUP_CODE, "+
                		"	UNIT_CODE, "+
                		"	USED_FOR "+
                        "   FROM  MAS_PRODUCT_UNIT_MAPPING " +
                        "   WHERE IS_ACTIVE = 1" +
                        "   AND UNIT_CODE = '"+UNIT_CODE+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductUnitMapping> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductUnitMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
            	MasProductUnitMapping item = new MasProductUnitMapping();
                item.setUNIT_MAPPING_ID(rs.getInt("UNIT_MAPPING_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setUNIT_CODE(rs.getString("UNIT_CODE"));
                item.setUSED_FOR(rs.getString("USED_FOR"));

                return item;
            }
        });

        return dataList;

    }

}
