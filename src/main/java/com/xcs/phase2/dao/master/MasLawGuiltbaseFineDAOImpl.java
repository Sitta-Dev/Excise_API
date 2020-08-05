package com.xcs.phase2.dao.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.MasLawGuiltbaseFine;
import com.xcs.phase2.request.master.MasLawGuiltbaseFinegetByConReq;
import com.xcs.phase2.response.master.MasLawGuiltbaseFineinsAllResponse;
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
public class MasLawGuiltbaseFineDAOImpl extends MasterExt implements MasLawGuiltbaseFineDAO {

    private static final Logger log = LoggerFactory.getLogger(MasLawGuiltbaseFineDAOImpl.class);


    @Override
    public List<MasLawGuiltbaseFine> MasLawGuiltbaseFinegetByCon(MasLawGuiltbaseFinegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT" +
                        "    MAS_LAW_GUILTBASE_FINE.*," +
                        "    MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME" +
                        "    FROM MAS_LAW_GUILTBASE_FINE" +
                        "    LEFT JOIN MAS_PRODUCT_GROUP on MAS_LAW_GUILTBASE_FINE.PRODUCT_GROUP_ID = MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID" +
                        "    AND MAS_PRODUCT_GROUP.IS_ACTIVE = 1" +
                        "    WHERE MAS_LAW_GUILTBASE_FINE.IS_ACTIVE = 1" +
                        "    AND MAS_LAW_GUILTBASE_FINE.SUBSECTION_RULE_ID = '"+req.getSUBSECTION_RULE_ID()+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasLawGuiltbaseFine> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasLawGuiltbaseFine mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasLawGuiltbaseFine item = new MasLawGuiltbaseFine();
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
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));

                return item;
            }
        });

        return dataList;

    }

    @Override
    public MasLawGuiltbaseFineinsAllResponse MasLawGuiltbaseFineinsAll(MasLawGuiltbaseFine req) {

        MasLawGuiltbaseFineinsAllResponse res = new MasLawGuiltbaseFineinsAllResponse();

        try {

            String FINE_ID = getSequences("SELECT MAS_LAW_GUILTBASE_FINE_SEQ.NEXTVAL FROM DUAL");

            StringBuilder sqlBuilder = new StringBuilder()
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
                            "MISTREAT_START_UNIT," +
                            "MISTREAT_TO_UNIT," +
                            "STATUS_VOLUMN," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + FINE_ID + "'," +
                            "'" + req.getSUBSECTION_RULE_ID() + "'," +
                            "'" + req.getPRODUCT_GROUP_ID() + "'," +
                            "'" + req.getMISTREAT_START_NO() + "'," +
                            "'" + req.getMISTREAT_TO_NO() + "'," +
                            "'" + req.getIS_FINE() + "'," +
                            "'" + req.getFINE_RATE() + "'," +
                            "'" + req.getMISTREAT_DESC() + "'," +
                            "'" + req.getMISTREAT_START_VOLUMN() + "'," +
                            "'" + req.getMISTREAT_TO_VOLUMN() + "'," +
                            "'" + req.getFINE_AMOUNT() + "'," +
                            "'" + req.getFINE_TAX() + "'," +
                            "'" + req.getMISTREAT_START_UNIT() + "'," +
                            "'" + req.getMISTREAT_TO_UNIT() + "'," +
                            "'" + req.getSTATUS_VOLUMN() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");





            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setFINE_ID(Integer.parseInt(FINE_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setFINE_ID(0);
            return res;
        }
    }

    @Override
    public Boolean MasLawGuiltbaseFineupdAll(MasLawGuiltbaseFine req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE MAS_LAW_GUILTBASE_FINE SET "
                        + "SUBSECTION_RULE_ID=  '"+req.getSUBSECTION_RULE_ID()+"', "
                        + "PRODUCT_GROUP_ID=  '"+req.getPRODUCT_GROUP_ID()+"', "
                        + "MISTREAT_START_NO=  '"+req.getMISTREAT_START_NO()+"', "
                        + "MISTREAT_TO_NO=  '"+req.getMISTREAT_TO_NO()+"', "
                        + "IS_FINE=  '"+req.getIS_FINE()+"', "
                        + "FINE_RATE=  '"+req.getFINE_RATE()+"', "
                        + "MISTREAT_DESC=  '"+req.getMISTREAT_DESC()+"', "
                        + "MISTREAT_START_VOLUMN=  '"+req.getMISTREAT_START_VOLUMN()+"', "
                        + "MISTREAT_TO_VOLUMN=  '"+req.getMISTREAT_TO_VOLUMN()+"', "
                        + "FINE_AMOUNT=  '"+req.getFINE_AMOUNT()+"', "
                        + "FINE_TAX=  '"+req.getFINE_TAX()+"', "
                        + "MISTREAT_START_UNIT=  '"+req.getMISTREAT_START_UNIT()+"', "
                        + "MISTREAT_TO_UNIT=  '"+req.getMISTREAT_TO_UNIT()+"', "
                        + "STATUS_VOLUMN=  '"+req.getSTATUS_VOLUMN()+"', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE FINE_ID = '" + req.getFINE_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});


        return true;
    }
}
