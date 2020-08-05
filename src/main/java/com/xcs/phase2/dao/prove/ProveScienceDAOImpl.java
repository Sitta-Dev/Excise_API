package com.xcs.phase2.dao.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.prove.ProveScience;
import com.xcs.phase2.request.prove.ProveSciencegetByConReq;
import com.xcs.phase2.request.prove.ProveScienceupdDeleteReq;
import com.xcs.phase2.response.prove.ProveScienceinsAllResponse;
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
public class ProveScienceDAOImpl extends ProveExt implements ProveScienceDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveDAOImpl.class);

    @Override
    public List<ProveScience> ProveSciencegetByCon(ProveSciencegetByConReq req) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "SCIENCE_ID," +
                        "PROVE_ID," +
                        "SCIENCE_CODE," +
                        "DELIVERY_DOC_NO_1," +
                        "DELIVERY_DOC_NO_2," +
                        "to_char(DELIVERY_DOC_DATE,'" + Pattern.FORMAT_DATETIME + "') as DELIVERY_DOC_DATE," +
                        "REQUEST_DOC_NO," +
                        "to_char(REQUEST_DOC_DATE,'" + Pattern.FORMAT_DATETIME + "') as REQUEST_DOC_DATE," +
                        "RESULT_DOC_NO," +
                        "to_char(RESULT_DOC_DATE,'" + Pattern.FORMAT_DATETIME + "') as RESULT_DOC_DATE," +
                        "SCIENCE_RESULT_DESC," +
                        "IS_ACTIVE" +
                        " from OPS_PROVE_SCIENCE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and PROVE_ID = '" + req.getPROVE_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveScience> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveScience mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveScience item = new ProveScience();
                item.setSCIENCE_ID(rs.getInt("SCIENCE_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setSCIENCE_CODE(rs.getString("SCIENCE_CODE"));
                item.setDELIVERY_DOC_NO_1(rs.getString("DELIVERY_DOC_NO_1"));
                item.setDELIVERY_DOC_NO_2(rs.getString("DELIVERY_DOC_NO_2"));
                item.setDELIVERY_DOC_DATE(rs.getString("DELIVERY_DOC_DATE"));
                item.setREQUEST_DOC_NO(rs.getString("REQUEST_DOC_NO"));
                item.setREQUEST_DOC_DATE(rs.getString("REQUEST_DOC_DATE"));
                item.setRESULT_DOC_NO(rs.getString("RESULT_DOC_NO"));
                item.setRESULT_DOC_DATE(rs.getString("RESULT_DOC_DATE"));
                item.setSCIENCE_RESULT_DESC(rs.getString("SCIENCE_RESULT_DESC"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public ProveScienceinsAllResponse ProveScienceinsAll(ProveScience req) {

        ProveScienceinsAllResponse res = new ProveScienceinsAllResponse();

        try {

            String SCIENCE_ID = getSequences("SELECT OPS_PROVE_SCIENCE_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_PROVE_SCIENCE ( " +
                            "SCIENCE_ID," +
                            "PROVE_ID," +
                            "SCIENCE_CODE," +
                            "DELIVERY_DOC_NO_1," +
                            "DELIVERY_DOC_NO_2," +
                            "DELIVERY_DOC_DATE," +
                            "REQUEST_DOC_NO," +
                            "REQUEST_DOC_DATE," +
                            "RESULT_DOC_NO," +
                            "RESULT_DOC_DATE," +
                            "SCIENCE_RESULT_DESC," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + SCIENCE_ID + "'," +
                            "'" + req.getPROVE_ID() + "'," +
                            "'" + req.getSCIENCE_CODE() + "'," +
                            "'" + req.getDELIVERY_DOC_NO_1() + "'," +
                            "'" + req.getDELIVERY_DOC_NO_2() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getDELIVERY_DOC_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getREQUEST_DOC_NO() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getREQUEST_DOC_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getRESULT_DOC_NO() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getRESULT_DOC_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getSCIENCE_RESULT_DESC() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setSCIENCE_ID(Integer.parseInt(SCIENCE_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setSCIENCE_ID(0);
            return res;
        }
    }

    @Override
    public Boolean ProveScienceupdByCon(ProveScience req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_PROVE_SCIENCE SET "
                        + "PROVE_ID=  '" + req.getPROVE_ID() + "', "
                        + "SCIENCE_CODE=  '" + req.getSCIENCE_CODE() + "', "
                        + "DELIVERY_DOC_NO_1=  '" + req.getDELIVERY_DOC_NO_1() + "', "
                        + "DELIVERY_DOC_NO_2=  '" + req.getDELIVERY_DOC_NO_2() + "', "
                        + "DELIVERY_DOC_DATE = TO_TIMESTAMP_TZ('" + req.getDELIVERY_DOC_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "REQUEST_DOC_NO=  '" + req.getREQUEST_DOC_NO() + "', "
                        + "REQUEST_DOC_DATE = TO_TIMESTAMP_TZ('" + req.getREQUEST_DOC_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "RESULT_DOC_NO=  '" + req.getRESULT_DOC_NO() + "', "
                        + "RESULT_DOC_DATE = TO_TIMESTAMP_TZ('" + req.getRESULT_DOC_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "SCIENCE_RESULT_DESC=  '" + req.getSCIENCE_RESULT_DESC() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE SCIENCE_ID = '" + req.getSCIENCE_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        return true;
    }

    @Override
    public Boolean ProveScienceupdDelete(ProveScienceupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_PROVE_SCIENCE SET IS_ACTIVE = '0' WHERE SCIENCE_ID = '" + req.getSCIENCE_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        return true;
    }
}
