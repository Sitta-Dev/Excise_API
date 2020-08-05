package com.xcs.phase2.dao.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.MasLawPenalty;
import com.xcs.phase2.request.master.MasLawGroupSectionPenaltygetByConReq;
import com.xcs.phase2.request.master.MasLawPenaltygetByConReq;
import com.xcs.phase2.response.master.MasLawPenaltyinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@Transactional
public class MasLawPenaltyDAOImpl extends MasterExt implements MasLawPenaltyDAO {

    private static final Logger log = LoggerFactory.getLogger(MasLawPenaltyDAOImpl.class);

    @Override
    public MasLawPenalty MasLawPenaltygetByCon(MasLawPenaltygetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select * from MAS_LAW_PENALTY where IS_ACTIVE = 1 and PENALTY_ID = '" + req.getPENALTY_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasLawPenalty>() {

            public MasLawPenalty extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasLawPenalty item = new MasLawPenalty();

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

                    //testCommit
                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public MasLawPenalty MasLawGroupSectionPenaltygetByCon(MasLawGroupSectionPenaltygetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select * from MAS_LAW_PENALTY where IS_ACTIVE = 1 and SECTION_ID = '" + req.getSECTION_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasLawPenalty>() {

            public MasLawPenalty extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasLawPenalty item = new MasLawPenalty();

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

                return null;
            }
        });
    }

    @Override
    public MasLawPenaltyinsAllResponse MasLawPenaltyinsAll(MasLawPenalty req) {

        MasLawPenaltyinsAllResponse res = new MasLawPenaltyinsAllResponse();

        try {

            String PENALTY_ID = getSequences("SELECT MAS_LAW_PENALTY_SEQ.NEXTVAL FROM DUAL");

            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into MAS_LAW_PENALTY ( " +
                            "PENALTY_ID," +
                            "SECTION_ID," +
                            "PENALTY_DESC," +
                            "IS_FINE_PRISON," +
                            "IS_FINE," +
                            "FINE_RATE_MIN," +
                            "FINE_RATE_MAX," +
                            "FINE_MIN," +
                            "FINE_MAX," +
                            "IS_IMPRISON," +
                            "PRISON_RATE_MIN," +
                            "PRISON_RATE_MAX," +
                            "IS_TAX_PAID," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + PENALTY_ID + "'," +
                            "'" + req.getSECTION_ID() + "'," +
                            "'" + req.getPENALTY_DESC() + "'," +
                            "'" + req.getIS_FINE_PRISON() + "'," +
                            "'" + req.getIS_FINE() + "'," +
                            "'" + req.getFINE_RATE_MIN() + "'," +
                            "'" + req.getFINE_RATE_MAX() + "'," +
                            "'" + req.getFINE_MIN() + "'," +
                            "'" + req.getFINE_MAX() + "'," +
                            "'" + req.getIS_IMPRISON() + "'," +
                            "'" + req.getPRISON_RATE_MIN() + "'," +
                            "'" + req.getPRISON_RATE_MAX() + "'," +
                            "'" + req.getIS_TAX_PAID() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setPENALTY_ID(Integer.parseInt(PENALTY_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setPENALTY_ID(0);
            return res;
        }
    }

    @Override
    public Boolean MasLawPenaltyupdAll(MasLawPenalty req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE MAS_LAW_PENALTY SET "
                        + "SECTION_ID=  '" + req.getSECTION_ID() + "', "
                        + "PENALTY_DESC=  '" + req.getPENALTY_DESC() + "', "
                        + "IS_FINE_PRISON=  '" + req.getIS_FINE_PRISON() + "', "
                        + "IS_FINE=  '" + req.getIS_FINE() + "', "
                        + "FINE_RATE_MIN=  '" + req.getFINE_RATE_MIN() + "', "
                        + "FINE_RATE_MAX=  '" + req.getFINE_RATE_MAX() + "', "
                        + "FINE_MIN=  '" + req.getFINE_MIN() + "', "
                        + "FINE_MAX=  '" + req.getFINE_MAX() + "', "
                        + "IS_IMPRISON=  '" + req.getIS_IMPRISON() + "', "
                        + "PRISON_RATE_MIN=  '" + req.getPRISON_RATE_MIN() + "', "
                        + "PRISON_RATE_MAX=  '" + req.getPRISON_RATE_MAX() + "', "
                        + "IS_TAX_PAID=  '" + req.getIS_TAX_PAID() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE PENALTY_ID = '" + req.getPENALTY_ID() + "' ");
        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});


        return true;
    }
}
