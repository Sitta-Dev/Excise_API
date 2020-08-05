package com.xcs.phase2.dao.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.*;
import com.xcs.phase2.request.master.*;
import com.xcs.phase2.response.master.MasLawGroupSubSectionRuleinsAllResponse;
import com.xcs.phase2.response.master.MasLawGuiltbaseFineResponse;
import com.xcs.phase2.response.master.MasLawGuiltbaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MasLawGroupSubSectionRuleDAOImpl extends MasterExt implements MasLawGroupSubSectionRuleDAO {

    private static final Logger log = LoggerFactory.getLogger(MasLawGroupSubSectionRuleDAOImpl.class);

    @Override
    public List<MasLawGroupSubSectionRule> MasLawGroupSubSectionRulegetByKeyword(MasLawGroupSubSectionRulegetByKeywordReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    MAS_LAW_GROUP_SUBSECTION_RULE.*" +
                        "    FROM MAS_LAW_GROUP_SUBSECTION_RULE" +
                        "    LEFT JOIN MAS_LAW_GROUP_SECTION on MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID " +
                        "    AND MAS_LAW_GROUP_SECTION.IS_ACTIVE = 1" +
                        "    LEFT JOIN MAS_LAW_GROUP_SUBSECTION on MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_GROUP_SUBSECTION.SECTION_ID " +
                        "    AND MAS_LAW_GROUP_SUBSECTION.IS_ACTIVE = 1" +
                        "    LEFT JOIN MAS_LAW_GUILTBASE on MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID = MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID " +
                        "    AND MAS_LAW_GUILTBASE.IS_ACTIVE = 1" +
                        "    WHERE MAS_LAW_GROUP_SUBSECTION_RULE.IS_ACTIVE = 1" +
                        "    AND LOWER (MAS_LAW_GROUP_SECTION.SECTION_NAME) like lower(replace ('%" + req.getTEXT_SEARCH() + "%',' ',''))" +
                        "    OR LOWER (MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) like lower(replace ('%" + req.getTEXT_SEARCH() + "%',' ',''))" +
                        "    OR LOWER (MAS_LAW_GUILTBASE.GUILTBASE_NAME) like lower(replace ('%" + req.getTEXT_SEARCH() + "%',' ',''))" +
                        "    OR LOWER (MAS_LAW_GROUP_SECTION.SECTION_ID) like lower(replace ('%" + req.getTEXT_SEARCH() + "%',' ',''))" +
                        "    OR LOWER (MAS_LAW_GUILTBASE.FINE) like lower(replace ('%" + req.getTEXT_SEARCH() + "%',' ',''))" +
                        "    OR LOWER (MAS_LAW_GROUP_SUBSECTION_RULE.FINE_TYPE) like lower(replace ('%" + req.getTEXT_SEARCH() + "%',' ',''))" +
                        "    ORDER BY MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID asc");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasLawGroupSubSectionRule> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasLawGroupSubSectionRule mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasLawGroupSubSectionRule item = new MasLawGroupSubSectionRule();
                item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setMasLawGroupSection(masLawGroupSection(rs.getInt("SUBSECTION_ID")));
                item.setMasLawGroupSubSection(masLawGroupSubSection(rs.getInt("SUBSECTION_ID")));
                item.setMasLawGuiltbase(getMasLawGuiltbase(rs.getInt("SUBSECTION_RULE_ID")));

                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<MasLawGroupSubSectionRule> MasLawGroupSubSectionRulegetByConAdv(MasLawGroupSubSectionRulegetByConAdvReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    MAS_LAW_GROUP_SUBSECTION_RULE.*" +
                        "    FROM MAS_LAW_GROUP_SUBSECTION_RULE" +
                        "    LEFT JOIN MAS_LAW_GROUP_SECTION on MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID " +
                        "    AND MAS_LAW_GROUP_SECTION.IS_ACTIVE = 1" +
                        "    LEFT JOIN MAS_LAW_GROUP_SUBSECTION on MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_GROUP_SUBSECTION.SECTION_ID " +
                        "    AND MAS_LAW_GROUP_SUBSECTION.IS_ACTIVE = 1" +
                        "    LEFT JOIN MAS_LAW_GUILTBASE on MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID = MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID " +
                        "    AND MAS_LAW_GUILTBASE.IS_ACTIVE = 1" +
                        "    WHERE MAS_LAW_GROUP_SUBSECTION_RULE.IS_ACTIVE = 1");


        if (!StringUtils.isEmpty(req.getSECTION_NAME())) {
            sqlBuilder.append(" AND LOWER (MAS_LAW_GROUP_SECTION.SECTION_NAME) like lower(replace ('%" + req.getSECTION_NAME() + "%',' ','')) ");
        }

        if (!StringUtils.isEmpty(req.getSUBSECTION_NAME())) {
            sqlBuilder.append(" AND LOWER (MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) like lower(replace ('%" + req.getSUBSECTION_NAME() + "%',' ','')) ");
        }

        if (!StringUtils.isEmpty(req.getGUILTBASE_NAME())) {
            sqlBuilder.append(" AND LOWER (MAS_LAW_GUILTBASE.GUILTBASE_NAME) like lower(replace ('%" + req.getGUILTBASE_NAME() + "%',' ','')) ");
        }

        if (!StringUtils.isEmpty(req.getSECTION_ID())) {
            sqlBuilder.append(" AND LOWER (MAS_LAW_GROUP_SECTION.SECTION_ID) like lower(replace ('%" + req.getSECTION_ID() + "%',' ','')) ");
        }

        if (!StringUtils.isEmpty(req.getFINE())) {
            sqlBuilder.append(" AND LOWER (MAS_LAW_GUILTBASE.FINE) like lower(replace ('%" + req.getFINE() + "%',' ','')) ");
        }

        if (!StringUtils.isEmpty(req.getFINE_TYPE())) {
            sqlBuilder.append(" AND MAS_LAW_GROUP_SUBSECTION_RULE.FINE_TYPE = '" + req.getFINE_TYPE() + "' ");
        }

        sqlBuilder.append(" ORDER BY MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID asc ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasLawGroupSubSectionRule> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasLawGroupSubSectionRule mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasLawGroupSubSectionRule item = new MasLawGroupSubSectionRule();
                item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setMasLawGroupSection(masLawGroupSection(rs.getInt("SUBSECTION_ID")));
                item.setMasLawGroupSubSection(masLawGroupSubSection(rs.getInt("SUBSECTION_ID")));
                item.setMasLawGuiltbase(getMasLawGuiltbase(rs.getInt("SUBSECTION_RULE_ID")));

                return item;
            }
        });

        return dataList;

    }

    @Override
    public MasLawGroupSubSectionRule MasLawGroupSubSectionRulegetByCon(MasLawGroupSubSectionRulegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select * from MAS_LAW_GROUP_SUBSECTION_RULE where is_active = 1 and subsection_rule_id = '" + req.getSUBSECTION_RULE_ID() + "'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasLawGroupSubSectionRule>() {

            public MasLawGroupSubSectionRule extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasLawGroupSubSectionRule item = new MasLawGroupSubSectionRule();
                    item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                    item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                    item.setSECTION_ID(rs.getInt("SECTION_ID"));
                    item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    item.setMasLawGroupSection(masLawGroupSection(rs.getInt("SUBSECTION_ID")));
                    item.setMasLawGroupSubSection(masLawGroupSubSection(rs.getInt("SUBSECTION_ID")));
                    item.setMasLawGuiltbase(getMasLawGuiltbase(rs.getInt("SUBSECTION_RULE_ID")));

                    return item;
                }

                return null;
            }
        });

    }

    @Override
    public MasLawGroupSubSectionRuleinsAllResponse MasLawGroupSubSectionRuleinsAll(MasLawGroupSubSectionRule req) {

        MasLawGroupSubSectionRuleinsAllResponse res = new MasLawGroupSubSectionRuleinsAllResponse();

        try {

            String SUBSECTION_RULE_ID = getSequences("SELECT MAS_LAW_GROUP_SUBSECTION_RULE_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into MAS_LAW_GROUP_SUBSECTION_RULE ( " +
                            "SUBSECTION_RULE_ID," +
                            "SUBSECTION_ID," +
                            "SECTION_ID," +
                            "FINE_TYPE," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + SUBSECTION_RULE_ID + "'," +
                            "'" + req.getSUBSECTION_ID() + "'," +
                            "'" + req.getSECTION_ID() + "'," +
                            "'" + req.getFINE_TYPE() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setSUBSECTION_RULE_ID(Integer.parseInt(SUBSECTION_RULE_ID));


            if (req.getMasLawGuiltbase() != null) {
                log.info("[Sub] Size : " + req.getMasLawGuiltbase().size());
                List<MasLawGuiltbaseResponse> list = new ArrayList<MasLawGuiltbaseResponse>();

                if (req.getMasLawGuiltbase().size() > 0) {
                    for (MasLawGuiltbase item : req.getMasLawGuiltbase()) {

                        String GUILTBASE_ID = getSequences("SELECT MAS_LAW_GUILTBASE_SEQ.NEXTVAL FROM DUAL");
                        MasLawGuiltbaseResponse obj = new MasLawGuiltbaseResponse();
                        obj.setGUILTBASE_ID(Integer.parseInt(GUILTBASE_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into MAS_LAW_GUILTBASE ( " +
                                        "GUILTBASE_ID," +
                                        "SUBSECTION_RULE_ID," +
                                        "GUILTBASE_NAME," +
                                        "FINE," +
                                        "IS_PROVE," +
                                        "IS_COMPARE," +
                                        "REMARK," +
                                        "IS_ACTIVE" +
                                        " ) values (" +
                                        "'" + GUILTBASE_ID + "'," +
                                        "'" + SUBSECTION_RULE_ID + "'," +
                                        "'" + item.getGUILTBASE_NAME() + "'," +
                                        "'" + item.getFINE() + "'," +
                                        "'" + item.getIS_PROVE() + "'," +
                                        "'" + item.getIS_COMPARE() + "'," +
                                        "'" + item.getREMARK() + "'," +
                                        "'" + item.getIS_ACTIVE() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);

                    }
                }
                res.setMasLawGuiltbaseResponse(list);
            }

            if (req.getMasLawGuiltbaseFines() != null) {
                log.info("[Sub] Size : " + req.getMasLawGuiltbaseFines().size());
                List<MasLawGuiltbaseFineResponse> list = new ArrayList<MasLawGuiltbaseFineResponse>();

                if (req.getMasLawGuiltbaseFines().size() > 0) {
                    for (MasLawGuiltbaseFine item : req.getMasLawGuiltbaseFines()) {

                        String FINE_ID = getSequences("SELECT MAS_LAW_GUILTBASE_FINE_SEQ.NEXTVAL FROM DUAL");
                        MasLawGuiltbaseFineResponse obj = new MasLawGuiltbaseFineResponse();
                        obj.setFINE_ID(Integer.parseInt(FINE_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into MAS_LAW_GUILTBASE_FINE ( " +
                                        "FINE_ID," +
                                        "SUBSECTION_RULE_ID," +
                                        "PRODUCT_GROUP_ID," +
                                        "MISTREAT_START_NO," +
                                        "MISTREAT_TO_NO," +
                                        "IS_FINE," +
                                        "FINE_RATE," +
                                        "MISTREAT_DESC," +
                                        "MISTREAT_START_VOLUMN," +
                                        "MISTREAT_TO_VOLUMN," +
                                        "FINE_AMOUNT," +
                                        "FINE_TAX," +
                                        "IS_ACTIVE" +
                                        " ) values (" +
                                        "'" + FINE_ID + "'," +
                                        "'" + SUBSECTION_RULE_ID + "'," +
                                        "'" + item.getPRODUCT_GROUP_ID() + "'," +
                                        "'" + item.getMISTREAT_START_NO() + "'," +
                                        "'" + item.getMISTREAT_TO_NO() + "'," +
                                        "'" + item.getIS_FINE() + "'," +
                                        "'" + item.getFINE_RATE() + "'," +
                                        "'" + item.getMISTREAT_DESC() + "'," +
                                        "'" + item.getMISTREAT_START_VOLUMN() + "'," +
                                        "'" + item.getMISTREAT_TO_VOLUMN() + "'," +
                                        "'" + item.getFINE_AMOUNT() + "'," +
                                        "'" + item.getFINE_TAX() + "'," +
                                        "'" + item.getIS_ACTIVE() + "'" +
                                        " )");

                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);

                    }
                }
                res.setMasLawGuiltbaseFineResponses(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setSUBSECTION_RULE_ID(0);
            res.setMasLawGuiltbaseResponse(null);
            return res;
        }
    }

    @Override
    public Boolean MasLawGroupSubSectionRuleupdAll(MasLawGroupSubSectionRule req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE MAS_LAW_GROUP_SUBSECTION_RULE SET "
                        + "SUBSECTION_ID=  '" + req.getSUBSECTION_ID() + "', "
                        + "SECTION_ID=  '" + req.getSECTION_ID() + "', "
                        + "FINE_TYPE=  '" + req.getFINE_TYPE() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE SUBSECTION_RULE_ID = '" + req.getSUBSECTION_RULE_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});


        if (req.getMasLawGuiltbase() != null) {
            log.info("[Sub] Size : " + req.getMasLawGuiltbase().size());

            if (req.getMasLawGuiltbase().size() > 0) {
                for (MasLawGuiltbase item : req.getMasLawGuiltbase()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE MAS_LAW_GUILTBASE SET "
                                    + "SUBSECTION_RULE_ID=  '" + item.getSUBSECTION_RULE_ID() + "', "
                                    + "GUILTBASE_NAME=  '" + item.getGUILTBASE_NAME() + "', "
                                    + "FINE=  '" + item.getFINE() + "', "
                                    + "IS_PROVE=  '" + item.getIS_PROVE() + "', "
                                    + "IS_COMPARE=  '" + item.getIS_COMPARE() + "', "
                                    + "REMARK=  '" + item.getREMARK() + "', "
                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
                                    + " WHERE GUILTBASE_ID = '" + item.getGUILTBASE_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());


                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                }
            }
        }

        if (req.getMasLawGuiltbaseFines() != null) {
            log.info("[Sub] Size : " + req.getMasLawGuiltbaseFines().size());

            if (req.getMasLawGuiltbaseFines().size() > 0) {
                for (MasLawGuiltbaseFine item : req.getMasLawGuiltbaseFines()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE MAS_LAW_GUILTBASE_FINE SET "
                                    + "SUBSECTION_RULE_ID=  '"+item.getSUBSECTION_RULE_ID()+"', "
                                    + "PRODUCT_GROUP_ID=  '"+item.getPRODUCT_GROUP_ID()+"', "
                                    + "MISTREAT_START_NO=  '"+item.getMISTREAT_START_NO()+"', "
                                    + "MISTREAT_TO_NO=  '"+item.getMISTREAT_TO_NO()+"', "
                                    + "IS_FINE=  '"+item.getIS_FINE()+"', "
                                    + "FINE_RATE=  '"+item.getFINE_RATE()+"', "
                                    + "MISTREAT_DESC=  '"+item.getMISTREAT_DESC()+"', "
                                    + "MISTREAT_START_VOLUMN=  '"+item.getMISTREAT_START_VOLUMN()+"', "
                                    + "MISTREAT_TO_VOLUMN=  '"+item.getMISTREAT_TO_VOLUMN()+"', "
                                    + "FINE_AMOUNT=  '"+item.getFINE_AMOUNT()+"', "
                                    + "FINE_TAX=  '"+item.getFINE_TAX()+"', "
                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
                                    + " WHERE FINE_ID = '" + item.getFINE_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());


                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                }
            }
        }

        return true;
    }

    @Override
    public Boolean MasLawGroupSubSectionRuleupdDelete(MasLawGroupSubSectionRuleupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE MAS_LAW_GROUP_SUBSECTION_RULE SET IS_ACTIVE = '0' WHERE SUBSECTION_RULE_ID = '"+req.getSUBSECTION_RULE_ID()+"' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE MAS_LAW_GUILTBASE SET IS_ACTIVE = '0' WHERE SUBSECTION_RULE_ID = '"+req.getSUBSECTION_RULE_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
        return true;
    }

    @Override
    public List<New_MasLawGroupSection> New_MasLawGroupSubSectionRulegetByConAdv(New_MasLawGroupSubSectionRulegetByConAdvReq req) {


        StringBuilder sqlBuilder = new StringBuilder();

        if (!StringUtils.isEmpty(req.getSECTION_SECTION_ID())) {
            sqlBuilder.append(" AND MAS_LAW_GROUP_SECTION.SECTION_ID =  "+req.getSECTION_SECTION_ID());
        }

        if (!StringUtils.isEmpty(req.getSUBSECTION_NAME())) {
            sqlBuilder.append(" AND LOWER(MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) like lower('"+req.getSUBSECTION_NAME()+"%') ");
        }

        if (!StringUtils.isEmpty(req.getFINE_TYPE())) {
            sqlBuilder.append(" AND MAS_LAW_GROUP_SUBSECTION_RULE.FINE_TYPE =  "+req.getFINE_TYPE());
        }

        if (!StringUtils.isEmpty(req.getGUILTBASE_NAME())) {
            sqlBuilder.append(" AND LOWER(MAS_LAW_GUILTBASE.GUILTBASE_NAME) like lower('%"+req.getGUILTBASE_NAME()+"%') ");
        }

        if (!StringUtils.isEmpty(req.getFINE())) {
            sqlBuilder.append(" AND LOWER(MAS_LAW_GUILTBASE.FINE) like lower('%"+req.getFINE()+"%') ");
        }

        if (!StringUtils.isEmpty(req.getPENALTY_SECTION_ID())) {
            sqlBuilder.append(" AND MAS_LAW_PENALTY.SECTION_ID = "+req.getPENALTY_SECTION_ID());
        }

        StringBuilder sqlBuilder1 = new StringBuilder()
                .append("with temp as (" +
                        "    SELECT DISTINCT " +
                        "    MAS_LAW_GROUP_SECTION.SECTION_ID," +
                        "    MAS_LAW_GROUP_SECTION.LAW_GROUP_ID," +
                        "    MAS_LAW_GROUP_SECTION.SECTION_NAME," +
                        "    MAS_LAW_GROUP_SECTION.SECTION_DESC_1,   " +
                        "    MAS_LAW_GROUP_SECTION.SECTION_DESC_2," +
                        "    MAS_LAW_GROUP_SECTION.SECTION_DESC_3," +
                        "    MAS_LAW_GROUP_SECTION.IS_ACTIVE," +
                        "    MAS_LAW_GROUP_SECTION. EFFECTIVE_DATE," +
                        "    MAS_LAW_GROUP_SECTION.EXPIRE_DATE," +
                        "    MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
                        "    FROM MAS_LAW_GROUP_SECTION" +
                        "    LEFT JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_GROUP_SUBSECTION.SECTION_ID AND MAS_LAW_GROUP_SUBSECTION.IS_ACTIVE = 1" +
                        "    INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID AND MAS_LAW_GROUP_SUBSECTION_RULE.IS_ACTIVE =1" +
                        "    LEFT JOIN MAS_LAW_GUILTBASE ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID = MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID AND MAS_LAW_GUILTBASE.IS_ACTIVE =1" +
                        "    LEFT JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID AND MAS_LAW_PENALTY.IS_ACTIVE =1" +
                        "    WHERE 1=1 " + sqlBuilder.toString() +
                        "    ORDER BY MAS_LAW_GROUP_SECTION.SECTION_ID asc" +
                        ") " +
                        "select " +
                        "    SECTION_ID," +
                        "    LAW_GROUP_ID," +
                        "    SECTION_NAME," +
                        "    SECTION_DESC_1," +
                        "    SECTION_DESC_2," +
                        "    SECTION_DESC_3," +
                        "    IS_ACTIVE," +
                        "    EFFECTIVE_DATE," +
                        "    EXPIRE_DATE," +
                        "    LISTAGG(SUBSECTION_ID, ',') WITHIN GROUP (ORDER BY " +
                        "    SECTION_ID," +
                        "    LAW_GROUP_ID," +
                        "    SECTION_NAME," +
                        "    SECTION_DESC_1," +
                        "    SECTION_DESC_2," +
                        "    SECTION_DESC_3," +
                        "    IS_ACTIVE," +
                        "    EFFECTIVE_DATE," +
                        "    EXPIRE_DATE) AS SUBSECTION_ID " +
                        " from temp " +
                        " group by " +
                        "    SECTION_ID," +
                        "    LAW_GROUP_ID," +
                        "    SECTION_NAME," +
                        "    SECTION_DESC_1," +
                        "    SECTION_DESC_2," +
                        "    SECTION_DESC_3," +
                        "    IS_ACTIVE," +
                        "    EFFECTIVE_DATE," +
                        "    EXPIRE_DATE");


        log.info("[SQL]  : " + sqlBuilder1.toString());

        @SuppressWarnings("unchecked")
        List<New_MasLawGroupSection> dataList = getJdbcTemplate().query(sqlBuilder1.toString(), new RowMapper() {

            public New_MasLawGroupSection mapRow(ResultSet rs, int rowNum) throws SQLException {
                New_MasLawGroupSection item = new New_MasLawGroupSection();
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setLAW_GROUP_ID(rs.getInt("LAW_GROUP_ID"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                item.setSECTION_DESC_1(rs.getString("SECTION_DESC_1"));
                item.setSECTION_DESC_2(rs.getString("SECTION_DESC_2"));
                item.setSECTION_DESC_3(rs.getString("SECTION_DESC_3"));
                item.setEFFECTIVE_DATE(rs.getString("EFFECTIVE_DATE"));
                item.setEXPIRE_DATE(rs.getString("EXPIRE_DATE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setMasLawGroupSubSection(getNew_MasLawGroupSubSectionNew(rs.getString("SUBSECTION_ID")));

                return item;
            }
        });

        return dataList;

    }
}
