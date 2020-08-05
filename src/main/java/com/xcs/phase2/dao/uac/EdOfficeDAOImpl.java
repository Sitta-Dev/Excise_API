package com.xcs.phase2.dao.uac;

import com.xcs.phase2.model.uac.EdOffice;
import com.xcs.phase2.request.uac.EDOfficeAreagetbyConReq;
import com.xcs.phase2.request.uac.EDOfficeBranchgetbyConReq;
import com.xcs.phase2.request.uac.EDOfficeDepartmentgetbyConReq;
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
public class EdOfficeDAOImpl extends UacExt implements EdOfficeDAO {

    private static final Logger log = LoggerFactory.getLogger(EdOfficeDAOImpl.class);

    @Override
    public List<EdOffice> EDOfficeDepartmentgetbyCon(EDOfficeDepartmentgetbyConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  select " +
                        "  ID," +
                        "  OFFCODE," +
                        "  OFFNAME," +
                        "  SHORT_NAME," +
                        "  INDC_OFF," +
                        "  TAMBOL_CODE," +
                        "  SUPOFFCODE," +
                        "  to_char(BEGIN_DATE,'YYYY-MM-DD') as BEGIN_DATE," +
                        "  to_char(END_DATE,'YYYY-MM-DD') as END_DATE," +
                        "  UPD_USERID," +
                        "  to_char(UPD_DATE,'YYYY-MM-DD') as UPD_DATE" +
                        "  from mas_ed_office" +
                        "  where " +
                        "  substr(offcode,1,2)|| '0000' = substr('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) || '0000'  " +
                        "  or substr(offcode,1,4)|| '00' = substr('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) || '00'     " +
                        "  or offcode = '"+req.getACCOUNT_OFFICE_CODE()+"'     " +
                        "  order by offcode");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EdOffice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EdOffice mapRow(ResultSet rs, int rowNum) throws SQLException {
                EdOffice item = new EdOffice();
                item.setID(rs.getInt("ID"));
                item.setOFFCODE(rs.getString("OFFCODE"));
                item.setOFFNAME(rs.getString("OFFNAME"));
                item.setSHORT_NAME(rs.getString("SHORT_NAME"));
                item.setINDC_OFF(rs.getString("INDC_OFF"));
                item.setTAMBOL_CODE(rs.getString("TAMBOL_CODE"));
                item.setSUPOFFCODE(rs.getString("SUPOFFCODE"));
                item.setBEGIN_DATE(rs.getString("BEGIN_DATE"));
                item.setEND_DATE(rs.getString("END_DATE"));
                item.setUPD_USERID(rs.getString("UPD_USERID"));
                item.setUPD_DATE(rs.getString("UPD_DATE"));


                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<EdOffice> EDOfficeAreagetbyCon(EDOfficeAreagetbyConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  select " +
                        "  ID," +
                        "  OFFCODE," +
                        "  OFFNAME," +
                        "  SHORT_NAME," +
                        "  INDC_OFF," +
                        "  TAMBOL_CODE," +
                        "  SUPOFFCODE," +
                        "  to_char(BEGIN_DATE,'YYYY-MM-DD') as BEGIN_DATE," +
                        "  to_char(END_DATE,'YYYY-MM-DD') as END_DATE," +
                        "  UPD_USERID," +
                        "  to_char(UPD_DATE,'YYYY-MM-DD') as UPD_DATE" +
                        "  from mas_ed_office" +
                        "  where " +
                        "  substr(offcode,1,2)|| '0000' = substr('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) || '0000'  " +
                        "  and substr(offcode,1,4)|| '00' = substr('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) || '00'     " +
                        "  or offcode = '"+req.getACCOUNT_OFFICE_CODE()+"'     " +
                        "  order by offcode");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EdOffice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EdOffice mapRow(ResultSet rs, int rowNum) throws SQLException {
                EdOffice item = new EdOffice();
                item.setID(rs.getInt("ID"));
                item.setOFFCODE(rs.getString("OFFCODE"));
                item.setOFFNAME(rs.getString("OFFNAME"));
                item.setSHORT_NAME(rs.getString("SHORT_NAME"));
                item.setINDC_OFF(rs.getString("INDC_OFF"));
                item.setTAMBOL_CODE(rs.getString("TAMBOL_CODE"));
                item.setSUPOFFCODE(rs.getString("SUPOFFCODE"));
                item.setBEGIN_DATE(rs.getString("BEGIN_DATE"));
                item.setEND_DATE(rs.getString("END_DATE"));
                item.setUPD_USERID(rs.getString("UPD_USERID"));
                item.setUPD_DATE(rs.getString("UPD_DATE"));


                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<EdOffice> EDOfficeBranchgetbyCon(EDOfficeBranchgetbyConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  select " +
                        "  ID," +
                        "  OFFCODE," +
                        "  OFFNAME," +
                        "  SHORT_NAME," +
                        "  INDC_OFF," +
                        "  TAMBOL_CODE," +
                        "  SUPOFFCODE," +
                        "  to_char(BEGIN_DATE,'YYYY-MM-DD') as BEGIN_DATE," +
                        "  to_char(END_DATE,'YYYY-MM-DD') as END_DATE," +
                        "  UPD_USERID," +
                        "  to_char(UPD_DATE,'YYYY-MM-DD') as UPD_DATE" +
                        "  from mas_ed_office" +
                        "  where " +
                        "  substr(offcode,1,2)|| '0000' = substr('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) || '0000'  " +
                        "  and substr(offcode,1,4)|| '00' = substr('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) || '00'     " +
                        "  and offcode = '"+req.getACCOUNT_OFFICE_CODE()+"'     " +
                        "  order by offcode");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EdOffice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EdOffice mapRow(ResultSet rs, int rowNum) throws SQLException {
                EdOffice item = new EdOffice();
                item.setID(rs.getInt("ID"));
                item.setOFFCODE(rs.getString("OFFCODE"));
                item.setOFFNAME(rs.getString("OFFNAME"));
                item.setSHORT_NAME(rs.getString("SHORT_NAME"));
                item.setINDC_OFF(rs.getString("INDC_OFF"));
                item.setTAMBOL_CODE(rs.getString("TAMBOL_CODE"));
                item.setSUPOFFCODE(rs.getString("SUPOFFCODE"));
                item.setBEGIN_DATE(rs.getString("BEGIN_DATE"));
                item.setEND_DATE(rs.getString("END_DATE"));
                item.setUPD_USERID(rs.getString("UPD_USERID"));
                item.setUPD_DATE(rs.getString("UPD_DATE"));


                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<EdOffice> EDOfficeDepartmentgetAll() {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  select " +
                        "  ID," +
                        "  OFFCODE," +
                        "  OFFNAME," +
                        "  SHORT_NAME," +
                        "  INDC_OFF," +
                        "  TAMBOL_CODE," +
                        "  SUPOFFCODE," +
                        "  to_char(BEGIN_DATE,'YYYY-MM-DD') as BEGIN_DATE," +
                        "  to_char(END_DATE,'YYYY-MM-DD') as END_DATE," +
                        "  UPD_USERID," +
                        "  to_char(UPD_DATE,'YYYY-MM-DD') as UPD_DATE" +
                        "  from mas_ed_office" +
                        "  where indc_off = 'D' and substr(offcode,5,2) = '00' " +
                        "  order by offcode");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EdOffice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EdOffice mapRow(ResultSet rs, int rowNum) throws SQLException {
                EdOffice item = new EdOffice();
                item.setID(rs.getInt("ID"));
                item.setOFFCODE(rs.getString("OFFCODE"));
                item.setOFFNAME(rs.getString("OFFNAME"));
                item.setSHORT_NAME(rs.getString("SHORT_NAME"));
                item.setINDC_OFF(rs.getString("INDC_OFF"));
                item.setTAMBOL_CODE(rs.getString("TAMBOL_CODE"));
                item.setSUPOFFCODE(rs.getString("SUPOFFCODE"));
                item.setBEGIN_DATE(rs.getString("BEGIN_DATE"));
                item.setEND_DATE(rs.getString("END_DATE"));
                item.setUPD_USERID(rs.getString("UPD_USERID"));
                item.setUPD_DATE(rs.getString("UPD_DATE"));


                return item;
            }
        });

        return dataList;

    }
}
