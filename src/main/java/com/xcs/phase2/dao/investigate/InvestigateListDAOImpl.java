package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.investigate.InvestigateList;
import com.xcs.phase2.request.investigate.InvestigateListgetByConAdvReq;
import com.xcs.phase2.request.investigate.InvestigateListgetByKeywordReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class InvestigateListDAOImpl extends InvestigateExt implements InvestigateListDAO {

    private static final Logger log = LoggerFactory.getLogger(InvestigateListDAOImpl.class);

    @Override
    public List<InvestigateList> InvestigateListgetByKeyword(InvestigateListgetByKeywordReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT " +
                        "  DISTINCT OPS_INVESTIGATE.INVESTIGATE_CODE," +
                        "  OPS_INVESTIGATE.INVESTIGATE_NO," +
                        "  OPS_INVESTIGATE.SUBJECT," +
                        "  to_char(OPS_INVESTIGATE.DATE_START,'" + Pattern.FORMAT_DATETIME + "') as DATE_START, " +
                        "  to_char(OPS_INVESTIGATE.DATE_END,'" + Pattern.FORMAT_DATETIME + "') as DATE_END, " +
                        "  max(OPS_INVESTIGATE_DETAIL.INVESTIGATE_SEQUENCE) over (PARTITION BY OPS_INVESTIGATE.INVESTIGATE_CODE) AS INVESTIGATE_SEQUENCE" +
                        "  from OPS_INVESTIGATE" +
                        "  inner join OPS_INVESTIGATE_DETAIL on OPS_INVESTIGATE.INVESTIGATE_ID = OPS_INVESTIGATE_DETAIL.INVESTIGATE_ID" +
                        "  inner join OPS_INVESTIGATE_DETAIL_STAFF on OPS_INVESTIGATE_DETAIL.INVESTIGATE_DETAIL_ID = OPS_INVESTIGATE_DETAIL_STAFF.INVESTIGATE_DETAIL_ID" +
                        "  where OPS_INVESTIGATE.IS_ACTIVE = 1" +
                        "  and OPS_INVESTIGATE_DETAIL.IS_ACTIVE = 1" +
                        "  and OPS_INVESTIGATE_DETAIL_STAFF.IS_ACTIVE = 1" +
                        "  and OPS_INVESTIGATE_DETAIL_STAFF.STAFF_CODE = '"+req.getSTAFF_CODE()+"'" +
                        "  and" +
                        "  (" +
                        "    lower(OPS_INVESTIGATE.INVESTIGATE_CODE)like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    or lower(OPS_INVESTIGATE.INVESTIGATE_NO) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    or lower(OPS_INVESTIGATE.SUBJECT) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  )" +
                        "  group by " +
                        "  OPS_INVESTIGATE.INVESTIGATE_CODE, " +
                        "  OPS_INVESTIGATE.INVESTIGATE_NO," +
                        "  OPS_INVESTIGATE.SUBJECT," +
                        "  OPS_INVESTIGATE.DATE_START," +
                        "  OPS_INVESTIGATE.DATE_END," +
                        "  OPS_INVESTIGATE_DETAIL.INVESTIGATE_SEQUENCE" +
                        "  order by OPS_INVESTIGATE.INVESTIGATE_CODE asc");



        log.info("[SQL ] : "+sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<InvestigateList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public InvestigateList mapRow(ResultSet rs, int rowNum) throws SQLException {
                InvestigateList item = new InvestigateList();
                item.setINVESTIGATE_CODE(rs.getString("INVESTIGATE_CODE"));
                item.setINVESTIGATE_NO(rs.getString("INVESTIGATE_NO"));
                item.setSUBJECT(rs.getString("SUBJECT"));
                item.setDATE_START(rs.getString("DATE_START"));
                item.setDATE_END(rs.getString("DATE_END"));
                item.setINVESTIGATE_SEQUENCE(rs.getInt("INVESTIGATE_SEQUENCE"));

                return item;

            }
        });
        return dataList;

    }

    @Override
    public List<InvestigateList> InvestigateListgetByConAdv(InvestigateListgetByConAdvReq req) {

        String tempDATE_START = "";
        String tempDATE_END = "";


        if(!"".equals(req.getDATE_START())) {
            tempDATE_START = String.format("%s %s", req.getDATE_START(), Pattern.TIME_FROM);
        }

        if(!"".equals(req.getDATE_END())) {
            tempDATE_END = String.format("%s %s", req.getDATE_END(),Pattern.TIME_TO);
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT " +
                        "  DISTINCT OPS_INVESTIGATE.INVESTIGATE_CODE," +
                        "  OPS_INVESTIGATE.INVESTIGATE_NO," +
                        "  OPS_INVESTIGATE.SUBJECT," +
                        "  to_char(OPS_INVESTIGATE.DATE_START,'" + Pattern.FORMAT_DATETIME + "') as DATE_START, " +
                        "  to_char(OPS_INVESTIGATE.DATE_END,'" + Pattern.FORMAT_DATETIME + "') as DATE_END, " +
                        "  max(OPS_INVESTIGATE_DETAIL.INVESTIGATE_SEQUENCE) over (PARTITION BY OPS_INVESTIGATE.INVESTIGATE_CODE) AS INVESTIGATE_SEQUENCE" +
                        "  from OPS_INVESTIGATE" +
                        "  inner join OPS_INVESTIGATE_DETAIL on OPS_INVESTIGATE.INVESTIGATE_ID = OPS_INVESTIGATE_DETAIL.INVESTIGATE_ID" +
                        "  inner join OPS_INVESTIGATE_DETAIL_STAFF on OPS_INVESTIGATE_DETAIL.INVESTIGATE_DETAIL_ID = OPS_INVESTIGATE_DETAIL_STAFF.INVESTIGATE_DETAIL_ID" +
                        "  where OPS_INVESTIGATE.IS_ACTIVE = 1" +
                        "  and OPS_INVESTIGATE_DETAIL.IS_ACTIVE = 1" +
                        "  and OPS_INVESTIGATE_DETAIL_STAFF.IS_ACTIVE = 1" );

        if(!StringUtils.isEmpty(req.getSTAFF_CODE())){
            sqlBuilder.append(" and OPS_INVESTIGATE_DETAIL_STAFF.STAFF_CODE = '"+req.getSTAFF_CODE()+"' ");
        }

        if(!StringUtils.isEmpty(req.getINVESTIGATE_CODE())){
            sqlBuilder.append(" and lower(OPS_INVESTIGATE.INVESTIGATE_CODE) like lower(replace('%"+req.getINVESTIGATE_CODE()+"%',' ','')) ");
        }

        if(!StringUtils.isEmpty(req.getINVESTIGATE_NO())){
            sqlBuilder.append(" and lower(OPS_INVESTIGATE.INVESTIGATE_NO) like lower(replace('%"+req.getINVESTIGATE_NO()+"%',' ','')) ");
        }

        if(!StringUtils.isEmpty(req.getSUBJECT())){
            sqlBuilder.append(" and lower(OPS_INVESTIGATE.SUBJECT) like lower(replace('%"+req.getSUBJECT()+"%',' ','')) ");
        }

        if(req.getDATE_START() != null && !"".equals(req.getDATE_START()) && req.getDATE_END() != null && !"".equals(req.getDATE_END())) {
            sqlBuilder.append(" and OPS_INVESTIGATE.DATE_START between to_date(nvl('"+tempDATE_START+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempDATE_END+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }


        sqlBuilder.append("  group by " +
                        "  OPS_INVESTIGATE.INVESTIGATE_CODE, " +
                        "  OPS_INVESTIGATE.INVESTIGATE_NO," +
                        "  OPS_INVESTIGATE.SUBJECT," +
                        "  OPS_INVESTIGATE.DATE_START," +
                        "  OPS_INVESTIGATE.DATE_END," +
                        "  OPS_INVESTIGATE_DETAIL.INVESTIGATE_SEQUENCE" +
                        "  order by OPS_INVESTIGATE.INVESTIGATE_CODE asc");





        log.info("[SQL ] : "+sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<InvestigateList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public InvestigateList mapRow(ResultSet rs, int rowNum) throws SQLException {
                InvestigateList item = new InvestigateList();
                item.setINVESTIGATE_CODE(rs.getString("INVESTIGATE_CODE"));
                item.setINVESTIGATE_NO(rs.getString("INVESTIGATE_NO"));
                item.setSUBJECT(rs.getString("SUBJECT"));
                item.setDATE_START(rs.getString("DATE_START"));
                item.setDATE_END(rs.getString("DATE_END"));
                item.setINVESTIGATE_SEQUENCE(rs.getInt("INVESTIGATE_SEQUENCE"));

                return item;

            }
        });
        return dataList;

    }
}
