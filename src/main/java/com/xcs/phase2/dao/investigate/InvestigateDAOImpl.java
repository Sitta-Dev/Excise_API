package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.investigate.Investigate;
import com.xcs.phase2.request.investigate.InvestigategetByConReq;
import com.xcs.phase2.request.investigate.InvestigateupdDeleteReq;
import com.xcs.phase2.response.investigate.InvestigateinsAllResponse;
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
public class InvestigateDAOImpl extends InvestigateExt implements InvestigateDAO {

    private static final Logger log = LoggerFactory.getLogger(InvestigateDAOImpl.class);

    @Override
    public Investigate InvestigategetByCon(InvestigategetByConReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "INVESTIGATE_ID," +
                        "INVESTIGATE_CODE," +
                        "INVESTIGATE_NO," +
                        "INVESTIGATE_NO_YEAR," +
                        "to_char(DATE_START,'" + Pattern.FORMAT_DATETIME + "') as DATE_START, " +
                        "to_char(DATE_END,'" + Pattern.FORMAT_DATETIME + "') as DATE_END, " +
                        "SUBJECT," +
                        "IS_ACTIVE" +
                        " from OPS_INVESTIGATE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and INVESTIGATE_ID =  '" + req.getINVESTIGATE_ID() + "'  ");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Investigate>() {

            public Investigate extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    Investigate item = new Investigate();
                    item.setINVESTIGATE_ID(rs.getInt("INVESTIGATE_ID"));
                    item.setINVESTIGATE_CODE(rs.getString("INVESTIGATE_CODE"));
                    item.setINVESTIGATE_NO(rs.getInt("INVESTIGATE_NO"));
                    item.setINVESTIGATE_NO_YEAR(rs.getInt("INVESTIGATE_NO_YEAR"));
                    item.setDATE_START(rs.getString("DATE_START"));
                    item.setDATE_END(rs.getString("DATE_END"));
                    item.setSUBJECT(rs.getString("SUBJECT"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setInvestigateDetail(getInvestigateDetail(rs.getInt("INVESTIGATE_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public InvestigateinsAllResponse InvestigateinsAll(Investigate req) {

        InvestigateinsAllResponse res = new InvestigateinsAllResponse();

        try {

            String INVESTIGATE_ID = getSequences("SELECT OPS_INVESTIGATE_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("INSERT INTO OPS_INVESTIGATE (" +
                            "INVESTIGATE_ID," +
                            "INVESTIGATE_CODE," +
                            "INVESTIGATE_NO," +
                            "INVESTIGATE_NO_YEAR," +
                            "DATE_START," +
                            "DATE_END," +
                            "SUBJECT," +
                            "IS_ACTIVE" +
                            " ) VALUES (" +
                            "'" + INVESTIGATE_ID + "', " +
                            "'" + req.getINVESTIGATE_CODE() + "'," +
                            "'" + req.getINVESTIGATE_NO() + "'," +
                            "'" + req.getINVESTIGATE_NO_YEAR() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getDATE_START() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + req.getDATE_END() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getSUBJECT() + "'," +
                            "'1')");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

            res.setINVESTIGATE_ID(Integer.parseInt(INVESTIGATE_ID));
            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setINVESTIGATE_ID(0);
            return res;
        }

    }

    @Override
    public Boolean InvestigateupdByCon(Investigate req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_INVESTIGATE SET "
                        + "INVESTIGATE_CODE=  '" + req.getINVESTIGATE_CODE() + "', "
                        + "INVESTIGATE_NO=  '" + req.getINVESTIGATE_NO() + "', "
                        + "INVESTIGATE_NO_YEAR=  '" + req.getINVESTIGATE_NO_YEAR() + "', "
                        + "DATE_START=  TO_TIMESTAMP_TZ('" + req.getDATE_START() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "DATE_END=  TO_TIMESTAMP_TZ('" + req.getDATE_END() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "SUBJECT=  '" + req.getSUBJECT() + "', "
                        + "IS_ACTIVE='" + req.getIS_ACTIVE() + "' "
                        + " WHERE INVESTIGATE_ID = '" + req.getINVESTIGATE_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        return true;
    }

    @Override
    public Boolean InvestigateupdDelete(InvestigateupdDeleteReq req) {


        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_INVESTIGATE SET IS_ACTIVE = '0' WHERE INVESTIGATE_ID = '" + req.getINVESTIGATE_ID() + "' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_INVESTIGATE_DETAIL SET IS_ACTIVE = '0' WHERE INVESTIGATE_ID = '" + req.getINVESTIGATE_ID() + "' ");

        log.info("[SQL] OPS_INVESTIGATE : " + sqlBuilder1.toString());
        log.info("[SQL] OPS_INVESTIGATE_DETAIL : " + sqlBuilder2.toString());

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[]{});


        return true;

    }
}
