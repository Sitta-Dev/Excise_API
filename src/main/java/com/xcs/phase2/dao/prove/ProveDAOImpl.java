package com.xcs.phase2.dao.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.prove.Prove;
import com.xcs.phase2.model.prove.ProveCompare;
import com.xcs.phase2.request.prove.ProveComparegetByProveIDReq;
import com.xcs.phase2.request.prove.ProveVerifyProveNoReq;
import com.xcs.phase2.request.prove.ProvegetByConReq;
import com.xcs.phase2.request.prove.ProveupdDeleteReq;
import com.xcs.phase2.response.prove.CourtJudgmentResponse;
import com.xcs.phase2.response.prove.ProveinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ProveDAOImpl extends ProveExt implements ProveDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveDAOImpl.class);

    @Override
    public Prove ProvegetByCon(ProvegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "PROVE_ID," +
                        "LAWSUIT_ID," +
                        "DELIVERY_OFFICE_ID," +
                        "RECEIVE_OFFICE_ID," +
                        "PROVE_TYPE," +
                        "DELIVERY_DOC_NO_1," +
                        "DELIVERY_DOC_NO_2," +
                        "to_char(DELIVERY_DOC_DATE,'" + Pattern.FORMAT_DATETIME + "') as DELIVERY_DOC_DATE," +
                        "DELIVERY_OFFICE_CODE," +
                        "DELIVERY_OFFICE_NAME," +
                        "RECEIVE_OFFICE_CODE," +
                        "RECEIVE_OFFICE_NAME," +
                        "PROVE_NO," +
                        "EXTRACT(YEAR FROM PROVE_NO_YEAR) as PROVE_NO_YEAR ," +
                        "to_char(RECEIVE_DOC_DATE,'" + Pattern.FORMAT_DATETIME + "') as RECEIVE_DOC_DATE," +
                        "COMMAND," +
                        "LAWSUIT_NO," +
                        "EXTRACT(YEAR FROM LAWSUIT_NO_YEAR) as LAWSUIT_NO_YEAR ," +
                        "to_char(OCCURRENCE_DATE,'" + Pattern.FORMAT_DATETIME + "') as OCCURRENCE_DATE," +
                        "OUT_OFFICE_NAME," +
                        "IS_OUTSIDE," +
                        "IS_SCIENCE," +
                        "IS_RECEIVE," +
                        "to_char(PROVE_DATE,'" + Pattern.FORMAT_DATETIME + "') as PROVE_DATE," +
                        "DELIVERY_PROVE_DOC_NO_1," +
                        "DELIVERY_PROVE_DOC_NO_2," +
                        "PROVE_RESULT," +
                        "IS_ACTIVE" +
                        " from OPS_PROVE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and PROVE_ID = '" + req.getPROVE_ID() + "'");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Prove>() {

            public Prove extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    Prove item = new Prove();
                    item.setPROVE_ID(rs.getInt("PROVE_ID"));
                    item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                    item.setDELIVERY_OFFICE_ID(rs.getInt("DELIVERY_OFFICE_ID"));
                    item.setRECEIVE_OFFICE_ID(rs.getInt("RECEIVE_OFFICE_ID"));
                    item.setPROVE_TYPE(rs.getInt("PROVE_TYPE"));
                    item.setDELIVERY_DOC_NO_1(rs.getString("DELIVERY_DOC_NO_1"));
                    item.setDELIVERY_DOC_NO_2(rs.getString("DELIVERY_DOC_NO_2"));
                    item.setDELIVERY_DOC_DATE(rs.getString("DELIVERY_DOC_DATE"));
                    item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                    item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                    item.setRECEIVE_OFFICE_CODE(rs.getString("RECEIVE_OFFICE_CODE"));
                    item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                    item.setPROVE_NO(rs.getString("PROVE_NO"));
                    item.setPROVE_NO_YEAR(rs.getString("PROVE_NO_YEAR"));
                    item.setRECEIVE_DOC_DATE(rs.getString("RECEIVE_DOC_DATE"));
                    item.setCOMMAND(rs.getString("COMMAND"));
                    item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                    item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                    item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                    item.setOUT_OFFICE_NAME(rs.getString("OUT_OFFICE_NAME"));
                    item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                    item.setIS_SCIENCE(rs.getInt("IS_SCIENCE"));
                    item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));

                    item.setPROVE_DATE(rs.getString("PROVE_DATE"));
                    item.setDELIVERY_PROVE_DOC_NO_1(rs.getString("DELIVERY_PROVE_DOC_NO_1"));
                    item.setDELIVERY_PROVE_DOC_NO_2(rs.getString("DELIVERY_PROVE_DOC_NO_2"));
                    item.setPROVE_RESULT(rs.getString("PROVE_RESULT"));

                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    return item;
                }

                return null;
            }
        });
    }

    @Override
    @Transactional( rollbackFor = Exception.class)
    public ProveinsAllResponse ProveinsAll(Prove req) {

        ProveinsAllResponse res = new ProveinsAllResponse();

        try {

            String PROVE_ID = getSequences("SELECT OPS_PROVE_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_PROVE ( " +
                            "PROVE_ID," +
                            "LAWSUIT_ID," +
                            "DELIVERY_OFFICE_ID," +
                            "RECEIVE_OFFICE_ID," +
                            "PROVE_TYPE," +
                            "DELIVERY_DOC_NO_1," +
                            "DELIVERY_DOC_NO_2," +
                            "DELIVERY_DOC_DATE," +
                            "DELIVERY_OFFICE_CODE," +
                            "DELIVERY_OFFICE_NAME," +
                            "RECEIVE_OFFICE_CODE," +
                            "RECEIVE_OFFICE_NAME," +
                            "PROVE_NO," +
                            "PROVE_NO_YEAR," +
                            "RECEIVE_DOC_DATE," +
                            "COMMAND," +
                            "LAWSUIT_NO," +
                            "LAWSUIT_NO_YEAR," +
                            "OCCURRENCE_DATE," +
                            "OUT_OFFICE_NAME," +
                            "IS_OUTSIDE," +
                            "IS_SCIENCE," +
                            "IS_RECEIVE," +
                            "PROVE_DATE," +
                            "DELIVERY_PROVE_DOC_NO_1," +
                            "DELIVERY_PROVE_DOC_NO_2," +
                            "PROVE_RESULT," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + PROVE_ID + "'," +
                            "'" + req.getLAWSUIT_ID() + "'," +
                            "'" + req.getDELIVERY_OFFICE_ID() + "'," +
                            "'" + req.getRECEIVE_OFFICE_ID() + "'," +
                            "'" + req.getPROVE_TYPE() + "'," +
                            "'" + req.getDELIVERY_DOC_NO_1() + "'," +
                            "'" + req.getDELIVERY_DOC_NO_2() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getDELIVERY_DOC_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getDELIVERY_OFFICE_CODE() + "'," +
                            "'" + req.getDELIVERY_OFFICE_NAME() + "'," +
                            "'" + req.getRECEIVE_OFFICE_CODE() + "'," +
                            "'" + req.getRECEIVE_OFFICE_NAME() + "'," +
                            "'" + req.getPROVE_NO() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getPROVE_NO_YEAR() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + req.getRECEIVE_DOC_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getCOMMAND() + "'," +
                            "'" + req.getLAWSUIT_NO() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getLAWSUIT_NO_YEAR() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + req.getOCCURRENCE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getOUT_OFFICE_NAME() + "'," +
                            "'" + req.getIS_OUTSIDE() + "'," +
                            "'" + req.getIS_SCIENCE() + "'," +
                            "'" + req.getIS_RECEIVE() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getPROVE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getDELIVERY_PROVE_DOC_NO_1() + "'," +
                            "'" + req.getDELIVERY_PROVE_DOC_NO_2() + "'," +
                            "'" + req.getPROVE_RESULT() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setPROVE_ID(Integer.parseInt(PROVE_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setPROVE_ID(0);
            return res;
        }
    }

    @Override
    public Boolean ProveupdByCon(Prove req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_PROVE SET "
                        + "LAWSUIT_ID=  '" + req.getLAWSUIT_ID() + "', "
                        + "DELIVERY_OFFICE_ID=  '" + req.getDELIVERY_OFFICE_ID() + "', "
                        + "RECEIVE_OFFICE_ID=  '" + req.getRECEIVE_OFFICE_ID() + "', "
                        + "PROVE_TYPE=  '" + req.getPROVE_TYPE() + "', "
                        + "DELIVERY_DOC_NO_1=  '" + req.getDELIVERY_DOC_NO_1() + "', "
                        + "DELIVERY_DOC_NO_2=  '" + req.getDELIVERY_DOC_NO_2() + "', "
                        + "DELIVERY_DOC_DATE = TO_TIMESTAMP_TZ('" + req.getDELIVERY_DOC_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "DELIVERY_OFFICE_CODE=  '" + req.getDELIVERY_OFFICE_CODE() + "', "
                        + "DELIVERY_OFFICE_NAME=  '" + req.getDELIVERY_OFFICE_NAME() + "', "
                        + "RECEIVE_OFFICE_CODE=  '" + req.getRECEIVE_OFFICE_CODE() + "', "
                        + "RECEIVE_OFFICE_NAME=  '" + req.getRECEIVE_OFFICE_NAME() + "', "
                        + "PROVE_NO=  '" + req.getPROVE_NO() + "', "
                        + "PROVE_NO_YEAR = TO_TIMESTAMP_TZ('" + req.getPROVE_NO_YEAR() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "RECEIVE_DOC_DATE = TO_TIMESTAMP_TZ('" + req.getRECEIVE_DOC_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "COMMAND=  '" + req.getCOMMAND() + "', "
                        + "LAWSUIT_NO=  '" + req.getLAWSUIT_NO() + "', "
                        + "LAWSUIT_NO_YEAR = TO_TIMESTAMP_TZ('" + req.getLAWSUIT_NO_YEAR() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "OCCURRENCE_DATE = TO_TIMESTAMP_TZ('" + req.getOCCURRENCE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "OUT_OFFICE_NAME=  '" + req.getOUT_OFFICE_NAME() + "', "
                        + "IS_OUTSIDE=  '" + req.getIS_OUTSIDE() + "', "
                        + "IS_SCIENCE=  '" + req.getIS_SCIENCE() + "', "
                        + "IS_RECEIVE=  '" + req.getIS_RECEIVE() + "', "
                        + "PROVE_DATE = TO_TIMESTAMP_TZ('" + req.getPROVE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "DELIVERY_PROVE_DOC_NO_1=  '" + req.getDELIVERY_PROVE_DOC_NO_1() + "', "
                        + "DELIVERY_PROVE_DOC_NO_2=  '" + req.getDELIVERY_PROVE_DOC_NO_2() + "', "
                        + "PROVE_RESULT=  '" + req.getPROVE_RESULT() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE PROVE_ID = '" + req.getPROVE_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        return true;
    }

    @Override
    public Boolean ProveupdDelete(ProveupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_PROVE SET IS_ACTIVE = '0' WHERE PROVE_ID = '" + req.getPROVE_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        return true;
    }

    @Override
    public List<Prove> ProveVerifyProveNo(ProveVerifyProveNoReq req) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select * " +
                        "    from OPS_PROVE" +
                        "    where OPS_PROVE.IS_ACTIVE = 1" +
                        "    AND OPS_PROVE.PROVE_NO = "+req.getPROVE_NO()+"" +
                        "    AND EXTRACT(YEAR FROM OPS_PROVE.PROVE_NO_YEAR) = '"+req.getPROVE_NO_YEAR()+"'" +
                        "    AND OPS_PROVE.IS_OUTSIDE = "+req.getIS_OUTSIDE()+
                        "    AND OPS_PROVE.PROVE_TYPE = "+req.getPROVE_TYPE()+
                        "    AND OPS_PROVE.RECEIVE_OFFICE_CODE = '"+req.getRECEIVE_OFFICE_CODE()+"'");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<Prove> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public Prove mapRow(ResultSet rs, int rowNum) throws SQLException {
                Prove item = new Prove();
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setDELIVERY_OFFICE_ID(rs.getInt("DELIVERY_OFFICE_ID"));
                item.setRECEIVE_OFFICE_ID(rs.getInt("RECEIVE_OFFICE_ID"));
                item.setPROVE_TYPE(rs.getInt("PROVE_TYPE"));
                item.setDELIVERY_DOC_NO_1(rs.getString("DELIVERY_DOC_NO_1"));
                item.setDELIVERY_DOC_NO_2(rs.getString("DELIVERY_DOC_NO_2"));
                item.setDELIVERY_DOC_DATE(rs.getString("DELIVERY_DOC_DATE"));
                item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                item.setRECEIVE_OFFICE_CODE(rs.getString("RECEIVE_OFFICE_CODE"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setPROVE_NO(rs.getString("PROVE_NO"));
                item.setPROVE_NO_YEAR(rs.getString("PROVE_NO_YEAR"));
                item.setRECEIVE_DOC_DATE(rs.getString("RECEIVE_DOC_DATE"));
                item.setCOMMAND(rs.getString("COMMAND"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setOUT_OFFICE_NAME(rs.getString("OUT_OFFICE_NAME"));
                item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                item.setIS_SCIENCE(rs.getInt("IS_SCIENCE"));
                item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));

                item.setPROVE_DATE(rs.getString("PROVE_DATE"));
                item.setDELIVERY_PROVE_DOC_NO_1(rs.getString("DELIVERY_PROVE_DOC_NO_1"));
                item.setDELIVERY_PROVE_DOC_NO_2(rs.getString("DELIVERY_PROVE_DOC_NO_2"));
                item.setPROVE_RESULT(rs.getString("PROVE_RESULT"));

                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public ProveCompare ProveComparegetByProveID(ProveComparegetByProveIDReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT  DISTINCT" +
                        "    OPS_PROVE.PROVE_ID," +
                        "    OPS_LAWSUIT.INDICTMENT_ID," +
                        "    OPS_LAWSUIT.LAWSUIT_ID," +
                        "    OPS_COMPARE.COMPARE_ID" +
                        "    FROM OPS_LAWSUIT" +
                        "    INNER JOIN OPS_PROVE on OPS_PROVE.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
                        "    left JOIN OPS_COMPARE on OPS_COMPARE.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
                        "    AND OPS_COMPARE.IS_ACTIVE = 1" +
                        "    left join OPS_COMPARE_MAPPING on OPS_COMPARE_MAPPING.COMPARE_ID = OPS_COMPARE.COMPARE_ID" +
                        "    AND OPS_COMPARE_MAPPING.IS_ACTIVE = 1" +
                        "    left JOIN OPS_COMPARE_DETAIL on OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID" +
                        "    AND OPS_COMPARE_DETAIL.IS_ACTIVE = 1" +
                        "    WHERE OPS_LAWSUIT.IS_ACTIVE =1" +
                        "    AND OPS_PROVE.IS_ACTIVE = 1" +
                        "    AND OPS_PROVE.PROVE_ID = "+req.getPROVE_ID());

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<ProveCompare>() {

            public ProveCompare extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    ProveCompare item = new ProveCompare();
                    item.setPROVE_ID(rs.getInt("PROVE_ID"));
                    item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                    item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                    item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                    System.out.println(rs.getInt("COMPARE_ID"));

                    if(rs.getInt("COMPARE_ID") == 0){
                        item.setProveCompareDetail(getProveCompareDetailByLawsuitId(rs.getInt("LAWSUIT_ID")));
                    }else{
                        item.setProveCompareDetail(getProveCompareDetailByCompareId(rs.getInt("COMPARE_ID")));
                    }

                    return item;
                }

                return null;
            }
        });
    }
    
    @Override
    public List<CourtJudgmentResponse>  ProveVerifyCourtJudgment(ProvegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT OPS_LAWSUIT_DETAIL.FINE"+
                		" FROM OPS_PROVE"+
                		" INNER JOIN OPS_LAWSUIT ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID"+
                		" AND OPS_LAWSUIT.IS_ACTIVE = 1"+
                		" INNER JOIN OPS_LAWSUIT_DETAIL ON OPS_LAWSUIT_DETAIL.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID"+
                		" AND OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1"+
                        " AND OPS_PROVE.PROVE_ID = "+req.getPROVE_ID());

        log.info("[SQL] : " + sqlBuilder.toString());

        	@SuppressWarnings("unchecked")
            List<CourtJudgmentResponse> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

                public CourtJudgmentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                	CourtJudgmentResponse item = new CourtJudgmentResponse();
                    item.setFINE(rs.getInt("FINE"));

                    return item;
                }
            });

            return dataList;

        }
}
