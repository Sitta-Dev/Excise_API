package com.xcs.phase2.dao.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.prove.ProveLawbreaker;
import com.xcs.phase2.request.prove.ProveLawbreakergetByConReq;
import com.xcs.phase2.request.prove.ProveLawbreakerupdDeleteReq;
import com.xcs.phase2.response.prove.ProveLawbreakerinsAllResponse;
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
public class ProveLawbreakerDAOImpl extends ProveExt implements ProveLawbreakerDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveLawbreakerDAOImpl.class);

    @Override
    public List<ProveLawbreaker> ProveLawbreakergetByCon(ProveLawbreakergetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT " +
                        "LAWBREAKER_ID," +
                        "PROVE_ID," +
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
                        "IS_ACTIVE" +
                        " FROM OPS_PROVE_LAWBREAKER" +
                        " WHERE IS_ACTIVE = 1 AND PROVE_ID = '" + req.getPROVE_ID() + "' ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<ProveLawbreaker> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveLawbreaker mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveLawbreaker item = new ProveLawbreaker();
                item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setPERSON_ID(rs.getInt("PERSON_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setPERSON_TYPE(rs.getInt("PERSON_TYPE"));
                item.setENTITY_TYPE(rs.getInt("ENTITY_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setMIDDLE_NAME(rs.getString("MIDDLE_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setOTHER_NAME(rs.getString("OTHER_NAME"));
                item.setCOMPANY_REGISTRATION_NO(rs.getString("COMPANY_REGISTRATION_NO"));
                item.setEXCISE_REGISTRATION_NO(rs.getString("EXCISE_REGISTRATION_NO"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setAGE(rs.getInt("AGE"));
                item.setPASSPORT_NO(rs.getString("PASSPORT_NO"));
                item.setCAREER(rs.getString("CAREER"));
                item.setPERSON_DESC(rs.getString("PERSON_DESC"));
                item.setEMAIL(rs.getString("EMAIL"));
                item.setTEL_NO(rs.getString("TEL_NO"));
                item.setMISTREAT_NO(rs.getInt("MISTREAT_NO"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public ProveLawbreakerinsAllResponse ProveLawbreakerinsAll(ProveLawbreaker req) {

        ProveLawbreakerinsAllResponse res = new ProveLawbreakerinsAllResponse();

        try {

            String LAWBREAKER_ID = getSequences("SELECT OPS_PROVE_LAWBREAKER_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_PROVE_LAWBREAKER ( " +
                            "LAWBREAKER_ID," +
                            "PROVE_ID," +
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
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + LAWBREAKER_ID + "'," +
                            "'" + req.getPROVE_ID() + "'," +
                            "'" + req.getPERSON_ID() + "'," +
                            "'" + req.getTITLE_ID() + "'," +
                            "'" + req.getPERSON_TYPE() + "'," +
                            "'" + req.getENTITY_TYPE() + "'," +
                            "'" + req.getTITLE_NAME_TH() + "'," +
                            "'" + req.getTITLE_NAME_EN() + "'," +
                            "'" + req.getTITLE_SHORT_NAME_TH() + "'," +
                            "'" + req.getTITLE_SHORT_NAME_EN() + "'," +
                            "'" + req.getFIRST_NAME() + "'," +
                            "'" + req.getMIDDLE_NAME() + "'," +
                            "'" + req.getLAST_NAME() + "'," +
                            "'" + req.getOTHER_NAME() + "'," +
                            "'" + req.getCOMPANY_REGISTRATION_NO() + "'," +
                            "'" + req.getEXCISE_REGISTRATION_NO() + "'," +
                            "'" + req.getID_CARD() + "'," +
                            "'" + req.getAGE() + "'," +
                            "'" + req.getPASSPORT_NO() + "'," +
                            "'" + req.getCAREER() + "'," +
                            "'" + req.getPERSON_DESC() + "'," +
                            "'" + req.getEMAIL() + "'," +
                            "'" + req.getTEL_NO() + "'," +
                            "'" + req.getMISTREAT_NO() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setLAWBREAKER_ID(Integer.parseInt(LAWBREAKER_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setLAWBREAKER_ID(0);
            return res;
        }
    }

    @Override
    public Boolean ProveLawbreakerupdByCon(ProveLawbreaker req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_PROVE_LAWBREAKER SET "
                        + "PROVE_ID=  '" + req.getPROVE_ID() + "', "
                        + "PERSON_ID=  '" + req.getPERSON_ID() + "', "
                        + "TITLE_ID=  '" + req.getTITLE_ID() + "', "
                        + "PERSON_TYPE=  '" + req.getPERSON_TYPE() + "', "
                        + "ENTITY_TYPE=  '" + req.getENTITY_TYPE() + "', "
                        + "TITLE_NAME_TH=  '" + req.getTITLE_NAME_TH() + "', "
                        + "TITLE_NAME_EN=  '" + req.getTITLE_NAME_EN() + "', "
                        + "TITLE_SHORT_NAME_TH=  '" + req.getTITLE_SHORT_NAME_TH() + "', "
                        + "TITLE_SHORT_NAME_EN=  '" + req.getTITLE_SHORT_NAME_EN() + "', "
                        + "FIRST_NAME=  '" + req.getFIRST_NAME() + "', "
                        + "MIDDLE_NAME=  '" + req.getMIDDLE_NAME() + "', "
                        + "LAST_NAME=  '" + req.getLAST_NAME() + "', "
                        + "OTHER_NAME=  '" + req.getOTHER_NAME() + "', "
                        + "COMPANY_REGISTRATION_NO=  '" + req.getCOMPANY_REGISTRATION_NO() + "', "
                        + "EXCISE_REGISTRATION_NO=  '" + req.getEXCISE_REGISTRATION_NO() + "', "
                        + "ID_CARD=  '" + req.getID_CARD() + "', "
                        + "AGE=  '" + req.getAGE() + "', "
                        + "PASSPORT_NO=  '" + req.getPASSPORT_NO() + "', "
                        + "CAREER=  '" + req.getCAREER() + "', "
                        + "PERSON_DESC=  '" + req.getPERSON_DESC() + "', "
                        + "EMAIL=  '" + req.getEMAIL() + "', "
                        + "TEL_NO=  '" + req.getTEL_NO() + "', "
                        + "MISTREAT_NO=  '" + req.getMISTREAT_NO() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE LAWBREAKER_ID = '" + req.getLAWBREAKER_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        return true;
    }

    @Override
    public Boolean ProveLawbreakerupdDelete(ProveLawbreakerupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_PROVE_LAWBREAKER SET IS_ACTIVE = '0' WHERE LAWBREAKER_ID = '" + req.getLAWBREAKER_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        return true;
    }
}
