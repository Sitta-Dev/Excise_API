package com.xcs.phase2.dao.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.master.MasLaw;
import com.xcs.phase2.model.master.MasLawGroupSection;
import com.xcs.phase2.model.master.MasLawGroupSubSection;
import com.xcs.phase2.request.master.MasLawGroupSectiongetByConAdvReq;
import com.xcs.phase2.request.master.MasLawGroupSectiongetByConReq;
import com.xcs.phase2.request.master.MasLawGroupSectiongetByKeywordReq;
import com.xcs.phase2.request.master.MasLawGroupSectionupdDeleteReq;
import com.xcs.phase2.response.master.MasLawGroupSectioninsAllResponse;
import com.xcs.phase2.response.master.MasLawGroupSubSectionResponse;
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
public class MasLawGroupSectionDAOImpl extends MasterExt implements MasLawGroupSectionDAO {

    private static final Logger log = LoggerFactory.getLogger(MasLawGroupSectionDAOImpl.class);

    @Override
    public List<MasLaw> MasLawGroupSectiongetByKeyword(MasLawGroupSectiongetByKeywordReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    MAS_LAW_GROUP.LAW_GROUP_ID," +
                        "    MAS_LAW_GROUP.LAW_GROUP_NO," +
                        "    MAS_LAW_GROUP.LAW_GROUP_NAME," +
                        "    MAS_LAW_GROUP.PART_NO," +
                        "    MAS_LAW_GROUP.PART_NAME," +
                        "    MAS_LAW_GROUP_SECTION.SECTION_ID," +
                        "    MAS_LAW_GROUP_SECTION.SECTION_NAME" +
                        "    FROM MAS_LAW_GROUP" +
                        "    LEFT JOIN MAS_LAW_GROUP_SECTION on MAS_LAW_GROUP.LAW_GROUP_ID=MAS_LAW_GROUP_SECTION.LAW_GROUP_ID" +
                        "    WHERE MAS_LAW_GROUP.IS_ACTIVE = 1" +
                        "    AND LOWER (MAS_LAW_GROUP.LAW_GROUP_NAME) like lower(replace ('%" + req.getTEXT_SEARCH() + "%',' ',''))" +
                        "    OR LOWER (MAS_LAW_GROUP.PART_NAME) like lower(replace ('%" + req.getTEXT_SEARCH() + "%',' ',''))" +
                        "    OR LOWER (MAS_LAW_GROUP_SECTION.SECTION_NAME) like lower(replace ('%" + req.getTEXT_SEARCH() + "%',' ',''))" +
                        "    ORDER BY MAS_LAW_GROUP.LAW_GROUP_NO asc");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasLaw> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasLaw mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasLaw item = new MasLaw();
                item.setLAW_GROUP_ID(rs.getInt("LAW_GROUP_ID"));
                item.setLAW_GROUP_NO(rs.getString("LAW_GROUP_NO"));
                item.setLAW_GROUP_NAME(rs.getString("LAW_GROUP_NAME"));
                item.setPART_NO(rs.getString("PART_NO"));
                item.setPART_NAME(rs.getString("PART_NAME"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<MasLaw> MasLawGroupSectiongetByConAdv(MasLawGroupSectiongetByConAdvReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    MAS_LAW_GROUP.LAW_GROUP_ID," +
                        "    MAS_LAW_GROUP.LAW_GROUP_NO," +
                        "    MAS_LAW_GROUP.LAW_GROUP_NAME," +
                        "    MAS_LAW_GROUP.PART_NO," +
                        "    MAS_LAW_GROUP.PART_NAME," +
                        "    MAS_LAW_GROUP_SECTION.SECTION_ID," +
                        "    MAS_LAW_GROUP_SECTION.SECTION_NAME" +
                        "    FROM MAS_LAW_GROUP" +
                        "    LEFT JOIN MAS_LAW_GROUP_SECTION on MAS_LAW_GROUP.LAW_GROUP_ID=MAS_LAW_GROUP_SECTION.LAW_GROUP_ID" +
                        "    WHERE MAS_LAW_GROUP.IS_ACTIVE = 1");

        if (!StringUtils.isEmpty(req.getLAW_GROUP_ID())) {
            sqlBuilder.append(" AND MAS_LAW_GROUP.LAW_GROUP_ID = '" + req.getLAW_GROUP_ID() + "' ");
        }

        if (!StringUtils.isEmpty(req.getPART_NO())) {
            sqlBuilder.append(" AND MAS_LAW_GROUP.PART_NO = '" + req.getPART_NO() + "' ");
        }

        if (!StringUtils.isEmpty(req.getSECTION_ID())) {
            sqlBuilder.append(" AND MAS_LAW_GROUP_SECTION.SECTION_ID = '" + req.getSECTION_ID() + "' ");
        }

        sqlBuilder.append(" ORDER BY MAS_LAW_GROUP.LAW_GROUP_NO asc ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasLaw> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasLaw mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasLaw item = new MasLaw();
                item.setLAW_GROUP_ID(rs.getInt("LAW_GROUP_ID"));
                item.setLAW_GROUP_NO(rs.getString("LAW_GROUP_NO"));
                item.setLAW_GROUP_NAME(rs.getString("LAW_GROUP_NAME"));
                item.setPART_NO(rs.getString("PART_NO"));
                item.setPART_NAME(rs.getString("PART_NAME"));
                item.setSECTION_ID(rs.getInt("SECTION_ID"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public MasLawGroupSection MasLawGroupSectiongetByCon(MasLawGroupSectiongetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select DISTINCT * " +
                        " from MAS_LAW_GROUP_SECTION" +
                        " where is_active = 1 and section_id = '" + req.getSECTION_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());

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
                    item.setEFFECTIVE_DATE(rs.getString("EFFECTIVE_DATE"));
                    item.setEXPIRE_DATE(rs.getString("EXPIRE_DATE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));


                    item.setMasLawGroupSubSections(getMasLawGroupSubSection(rs.getInt("SECTION_ID")));
                    item.setMasLawGroup(getMasLawGroup(rs.getInt("LAW_GROUP_ID")));


                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public MasLawGroupSectioninsAllResponse MasLawGroupSectioninsAll(MasLawGroupSection req) {

        MasLawGroupSectioninsAllResponse res = new MasLawGroupSectioninsAllResponse();

        try {

            String SECTION_ID = getSequences("SELECT MAS_LAW_GROUP_SECTION_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into MAS_LAW_GROUP_SECTION ( " +
                            "SECTION_ID," +
                            "LAW_GROUP_ID," +
                            "SECTION_NAME," +
                            "SECTION_DESC_1," +
                            "SECTION_DESC_2," +
                            "SECTION_DESC_3," +
                            "EFFECTIVE_DATE," +
                            "EXPIRE_DATE," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + SECTION_ID + "'," +
                            "'" + req.getLAW_GROUP_ID() + "'," +
                            "'" + req.getSECTION_NAME() + "'," +
                            "'" + req.getSECTION_DESC_1() + "'," +
                            "'" + req.getSECTION_DESC_2() + "'," +
                            "'" + req.getSECTION_DESC_3() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getEFFECTIVE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + req.getEXPIRE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setSECTION_ID(Integer.parseInt(SECTION_ID));


            if (req.getMasLawGroupSubSections() != null) {
                log.info("[Sub] Size : " + req.getMasLawGroupSubSections().size());
                List<MasLawGroupSubSectionResponse> list = new ArrayList<MasLawGroupSubSectionResponse>();

                if (req.getMasLawGroupSubSections().size() > 0) {
                    for (MasLawGroupSubSection item : req.getMasLawGroupSubSections()) {

                        String SUBSECTION_ID = getSequences("SELECT MAS_LAW_GROUP_SUBSECTION_SEQ.NEXTVAL FROM DUAL");
                        MasLawGroupSubSectionResponse obj = new MasLawGroupSubSectionResponse();
                        obj.setSUBSECTION_ID(Integer.parseInt(SUBSECTION_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into MAS_LAW_GROUP_SUBSECTION ( " +
                                        "SUBSECTION_ID," +
                                        "SECTION_ID," +
                                        "SUBSECTION_NO," +
                                        "SUBSECTION_NAME," +
                                        "SUBSECTION_DESC," +
                                        "IS_ACTIVE" +
                                        " ) values (" +
                                        "'" + SUBSECTION_ID + "'," +
                                        "'" + SECTION_ID + "'," +
                                        "'" + item.getSUBSECTION_NO() + "'," +
                                        "'" + item.getSUBSECTION_NAME() + "'," +
                                        "'" + item.getSUBSECTION_DESC() + "'," +
                                        "'" + item.getIS_ACTIVE() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);

                    }
                }
                res.setMasLawGroupSubSection(list);
            }

//            if (req.getMasLawPenalties() != null) {
//                log.info("[Sub] Size : " + req.getMasLawPenalties().size());
//                List<MasLawPenaltyResponse> list = new ArrayList<MasLawPenaltyResponse>();
//
//                if (req.getMasLawPenalties().size() > 0) {
//                    for (MasLawPenalty item : req.getMasLawPenalties()) {
//
//                        String PENALTY_ID = getSequences("SELECT MAS_LAW_PENALTY_SEQ.NEXTVAL FROM DUAL");
//                        MasLawPenaltyResponse obj = new MasLawPenaltyResponse();
//                        obj.setPENALTY_ID(Integer.parseInt(PENALTY_ID));
//
//                        StringBuilder sqlBuilderSub = new StringBuilder()
//                                .append("Insert into MAS_LAW_PENALTY ( " +
//                                        "PENALTY_ID," +
//                                        "SECTION_ID," +
//                                        "PENALTY_DESC," +
//                                        "IS_FINE_PRISON," +
//                                        "IS_FINE," +
//                                        "FINE_RATE_MIN," +
//                                        "FINE_RATE_MAX," +
//                                        "FINE_MIN," +
//                                        "FINE_MAX," +
//                                        "IS_IMPRISON," +
//                                        "PRISON_RATE_MIN," +
//                                        "PRISON_RATE_MAX," +
//                                        "IS_TAX_PAID," +
//                                        "IS_ACTIVE" +
//                                        " ) values (" +
//                                        "'" + PENALTY_ID + "'," +
//                                        "'" + SECTION_ID + "'," +
//                                        "'" + item.getPENALTY_DESC() + "'," +
//                                        "'" + item.getIS_FINE_PRISON() + "'," +
//                                        "'" + item.getIS_FINE() + "'," +
//                                        "'" + item.getFINE_RATE_MIN() + "'," +
//                                        "'" + item.getFINE_RATE_MAX() + "'," +
//                                        "'" + item.getFINE_MIN() + "'," +
//                                        "'" + item.getFINE_MAX() + "'," +
//                                        "'" + item.getIS_IMPRISON() + "'," +
//                                        "'" + item.getPRISON_RATE_MIN() + "'," +
//                                        "'" + item.getPRISON_RATE_MAX() + "'," +
//                                        "'" + item.getIS_TAX_PAID() + "'," +
//                                        "'" + item.getIS_ACTIVE() + "'" +
//                                        " )");
//                        log.info("[SQL] : " + sqlBuilderSub.toString());
//
//                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
//                        list.add(obj);
//
//                    }
//                }
//                res.setMasLawPenalty(list);
//            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setSECTION_ID(0);
            res.setMasLawGroupSubSection(null);
            return res;
        }
    }

    @Override
    public Boolean MasLawGroupSectionpupdAll(MasLawGroupSection req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE MAS_LAW_GROUP_SECTION SET "
                        + "LAW_GROUP_ID=  '" + req.getLAW_GROUP_ID() + "', "
                        + "SECTION_NAME=  '" + req.getSECTION_NAME() + "', "
                        + "SECTION_DESC_1=  '" + req.getSECTION_DESC_1() + "', "
                        + "SECTION_DESC_2=  '" + req.getSECTION_DESC_2() + "', "
                        + "SECTION_DESC_3=  '" + req.getSECTION_DESC_3() + "', "
                        + " EFFECTIVE_DATE = TO_TIMESTAMP_TZ('" + req.getEFFECTIVE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + " EXPIRE_DATE = TO_TIMESTAMP_TZ('" + req.getEXPIRE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE SECTION_ID = '" + req.getSECTION_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});


        if (req.getMasLawGroupSubSections() != null) {
            log.info("[Sub] Size : " + req.getMasLawGroupSubSections().size());

            if (req.getMasLawGroupSubSections().size() > 0) {
                for (MasLawGroupSubSection item : req.getMasLawGroupSubSections()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE MAS_LAW_GROUP_SUBSECTION SET "
                                    + "SECTION_ID=  '" + item.getSECTION_ID() + "', "
                                    + "SUBSECTION_NO=  '" + item.getSUBSECTION_NO() + "', "
                                    + "SUBSECTION_NAME=  '" + item.getSUBSECTION_NAME() + "', "
                                    + "SUBSECTION_DESC=  '" + item.getSUBSECTION_DESC() + "', "
                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
                                    + " WHERE SUBSECTION_ID = '" + item.getSUBSECTION_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());


                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                }
            }
        }

//        if (req.getMasLawPenalties() != null) {
//            log.info("[Sub] Size : " + req.getMasLawPenalties().size());
//
//            if (req.getMasLawPenalties().size() > 0) {
//                for (MasLawPenalty item : req.getMasLawPenalties()) {
//
//                    StringBuilder sqlBuilderSub = new StringBuilder()
//                            .append("UPDATE MAS_LAW_PENALTY SET "
//                                    + "SECTION_ID=  '" + item.getSECTION_ID() + "', "
//                                    + "PENALTY_DESC=  '" + item.getPENALTY_DESC() + "', "
//                                    + "IS_FINE_PRISON=  '" + item.getIS_FINE_PRISON() + "', "
//                                    + "IS_FINE=  '" + item.getIS_FINE() + "', "
//                                    + "FINE_RATE_MIN=  '" + item.getFINE_RATE_MIN() + "', "
//                                    + "FINE_RATE_MAX=  '" + item.getFINE_RATE_MAX() + "', "
//                                    + "FINE_MIN=  '" + item.getFINE_MIN() + "', "
//                                    + "FINE_MAX=  '" + item.getFINE_MAX() + "', "
//                                    + "IS_IMPRISON=  '" + item.getIS_IMPRISON() + "', "
//                                    + "PRISON_RATE_MIN=  '" + item.getPRISON_RATE_MIN() + "', "
//                                    + "PRISON_RATE_MAX=  '" + item.getPRISON_RATE_MAX() + "', "
//                                    + "IS_TAX_PAID=  '" + item.getIS_TAX_PAID() + "', "
//                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
//                                    + " WHERE PENALTY_ID = '" + item.getPENALTY_ID() + "' ");
//                    log.info("[SQL] : " + sqlBuilderSub.toString());
//
//
//                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
//
//                }
//            }
//        }

        return true;
    }

    @Override
    public Boolean MasLawGroupSectionupdDelete(MasLawGroupSectionupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE MAS_LAW_GROUP_SECTION SET IS_ACTIVE = '0' WHERE SECTION_ID = '" + req.getSECTION_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        return true;
    }
}
