package com.xcs.phase2.dao.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.MasLawGuiltbase;
import com.xcs.phase2.response.master.MasLawGuiltbaseinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MasLawGuiltbaseDAOImpl extends MasterExt implements MasLawGuiltbaseDAO{

    private static final Logger log = LoggerFactory.getLogger(MasLawPenaltyDAOImpl.class);

    @Override
    public MasLawGuiltbaseinsAllResponse MasLawGuiltbaseinsAll(MasLawGuiltbase req) {

        MasLawGuiltbaseinsAllResponse res = new MasLawGuiltbaseinsAllResponse();

        try {

            String GUILTBASE_ID = getSequences("SELECT MAS_LAW_GUILTBASE_SEQ.NEXTVAL FROM DUAL");

            StringBuilder sqlBuilder = new StringBuilder()
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
                            "'" + req.getSUBSECTION_RULE_ID() + "'," +
                            "'" + req.getGUILTBASE_NAME() + "'," +
                            "'" + req.getFINE() + "'," +
                            "'" + req.getIS_PROVE() + "'," +
                            "'" + req.getIS_COMPARE() + "'," +
                            "'" + req.getREMARK() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setGUILTBASE_ID(Integer.parseInt(GUILTBASE_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setGUILTBASE_ID(0);
            return res;
        }
    }

    @Override
    public Boolean MasLawGuiltbaseupdAll(MasLawGuiltbase req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE MAS_LAW_GUILTBASE SET "
                        + "SUBSECTION_RULE_ID=  '" + req.getSUBSECTION_RULE_ID() + "', "
                        + "GUILTBASE_NAME=  '" + req.getGUILTBASE_NAME() + "', "
                        + "FINE=  '" + req.getFINE() + "', "
                        + "IS_PROVE=  '" + req.getIS_PROVE() + "', "
                        + "IS_COMPARE=  '" + req.getIS_COMPARE() + "', "
                        + "REMARK=  '" + req.getREMARK() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE GUILTBASE_ID = '" + req.getGUILTBASE_ID() + "' ");
        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});


        return true;
    }
}
