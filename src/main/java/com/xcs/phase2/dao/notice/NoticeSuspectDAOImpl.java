package com.xcs.phase2.dao.notice;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.notice.NoticeSuspect;
import com.xcs.phase2.request.notice.NoticeSuspectupdDeleteReq;
import com.xcs.phase2.response.notice.NoticeSupectinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoticeSuspectDAOImpl extends NoticeExt implements NoticeSuspectDAO{

    private static final Logger log = LoggerFactory.getLogger(NoticeSuspectDAOImpl.class);

    @Override
    public NoticeSupectinsAllResponse NoticeSupectinsAll(NoticeSuspect item) {

        NoticeSupectinsAllResponse res = new NoticeSupectinsAllResponse();

        try {

            String SUSPECT_ID = getSequences("SELECT OPS_NOTICE_SUSPECT_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_NOTICE_SUSPECT ( " +
                            "SUSPECT_ID," +
                            "NOTICE_ID," +
                            "PERSON_ID," +
                            "TITLE_ID," +
                            "PERSON_TYPE," +
                            "ENTITY_TYPE," +
                            "TITLE_NAME_TH," +
                            "TITLE_NAME_EN," +
                            "TITLE_SHORT_NAME_TH," +
                            "TITLE_SHORT_NAME_EN," +
                            "FIRST_NAME," +
                            "MIDDLE_NAME," +
                            "LAST_NAME," +
                            "OTHER_NAME," +
                            "COMPANY_REGISTRATION_NO," +
                            "EXCISE_REGISTRATION_NO," +
                            "ID_CARD," +
                            "AGE," +
                            "PASSPORT_NO," +
                            "CAREER," +
                            "PERSON_DESC," +
                            "EMAIL," +
                            "TEL_NO," +
                            "MISTREAT_NO," +
                            "IS_ACTIVE," +
                            "COMPANY_NAME" +
                            " ) values (" +
                            "'" + SUSPECT_ID + "'," +
                            "'" + item.getNOTICE_ID() + "'," +
                            "'" + item.getPERSON_ID() + "'," +
                            "'" + item.getTITLE_ID() + "'," +
                            "'" + item.getPERSON_TYPE() + "'," +
                            "'" + item.getENTITY_TYPE() + "'," +
                            "'" + item.getTITLE_NAME_TH() + "'," +
                            "'" + item.getTITLE_NAME_EN() + "'," +
                            "'" + item.getTITLE_SHORT_NAME_TH() + "'," +
                            "'" + item.getTITLE_SHORT_NAME_EN() + "'," +
                            "'" + item.getFIRST_NAME() + "'," +
                            "'" + item.getMIDDLE_NAME() + "'," +
                            "'" + item.getLAST_NAME() + "'," +
                            "'" + item.getOTHER_NAME() + "'," +
                            "'" + item.getCOMPANY_REGISTRATION_NO() + "'," +
                            "'" + item.getEXCISE_REGISTRATION_NO() + "'," +
                            "'" + item.getID_CARD() + "'," +
                            "'" + item.getAGE() + "'," +
                            "'" + item.getPASSPORT_NO() + "'," +
                            "'" + item.getCAREER() + "'," +
                            "'" + item.getPERSON_DESC() + "'," +
                            "'" + item.getEMAIL() + "'," +
                            "'" + item.getTEL_NO() + "'," +
                            "'" + item.getMISTREAT_NO() + "'," +
                            "'" + item.getIS_ACTIVE() + "'," +
                            "'" + item.getCOMPANY_NAME() + "'" +
                            " )");
            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setSUSPECT_ID(Integer.parseInt(SUSPECT_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setSUSPECT_ID(0);
            return res;
        }
    }

    @Override
    public Boolean NoticeSuspectupdDelete(NoticeSuspectupdDeleteReq req) {

        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_NOTICE_SUSPECT SET IS_ACTIVE = '0' WHERE SUSPECT_ID = '" + req.getSUSPECT_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[]{});
        return true;
    }
}
