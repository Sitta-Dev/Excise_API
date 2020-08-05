package com.xcs.phase2.dao.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.MasLawGroupSubSection;
import com.xcs.phase2.request.master.MasLawGroupSubSectiongetByConReq;
import com.xcs.phase2.response.master.MasLawGroupSubSectioninsAllResponse;
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
public class MasLawGroupSubSectionDAOImpl extends MasterExt implements MasLawGroupSubSectionDAO{

    private static final Logger log = LoggerFactory.getLogger(MasLawGroupSubSectionDAOImpl.class);

    @Override
    public MasLawGroupSubSection MasLawGroupSubSectiongetByCon(MasLawGroupSubSectiongetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT DISTINCT *" +
                        " FROM MAS_LAW_GROUP_SUBSECTION" +
                        " WHERE MAS_LAW_GROUP_SUBSECTION.IS_ACTIVE = 1 AND MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID = '"+req.getSUBSECTION_ID()+"' ");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasLawGroupSubSection>() {

            public MasLawGroupSubSection extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasLawGroupSubSection item = new MasLawGroupSubSection();
                    item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                    item.setSECTION_ID(rs.getInt("SECTION_ID"));
                    item.setSUBSECTION_NO(rs.getString("SUBSECTION_NO"));
                    item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                    item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));


                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public MasLawGroupSubSectioninsAllResponse MasLawGroupSubSectioninsAll(MasLawGroupSubSection req) {

        MasLawGroupSubSectioninsAllResponse res = new MasLawGroupSubSectioninsAllResponse();

        try {

            String SUBSECTION_ID = getSequences("SELECT MAS_LAW_GROUP_SUBSECTION_SEQ.NEXTVAL FROM DUAL");

            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into MAS_LAW_GROUP_SUBSECTION ( " +
                            "SUBSECTION_ID," +
                            "SECTION_ID," +
                            "SUBSECTION_NO," +
                            "SUBSECTION_NAME," +
                            "SUBSECTION_DESC," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + SUBSECTION_ID + "'," +
                            "'" + req.getSECTION_ID() + "'," +
                            "'" + req.getSUBSECTION_NO() + "'," +
                            "'" + req.getSUBSECTION_NAME() + "'," +
                            "'" + req.getSUBSECTION_DESC() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setSUBSECTION_ID(Integer.parseInt(SUBSECTION_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setSUBSECTION_ID(0);
            return res;
        }
    }

    @Override
    public Boolean MasLawGroupSubSectionupdAll(MasLawGroupSubSection req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE MAS_LAW_GROUP_SUBSECTION SET "
                        + "SECTION_ID=  '"+req.getSECTION_ID()+"', "
                        + "SUBSECTION_NO=  '"+req.getSUBSECTION_NO()+"', "
                        + "SUBSECTION_NAME=  '"+req.getSUBSECTION_NAME()+"', "
                        + "SUBSECTION_DESC=  '"+req.getSUBSECTION_DESC()+"', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE SUBSECTION_ID = '" + req.getSUBSECTION_ID() + "' ");
        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});


        return true;
    }
}
