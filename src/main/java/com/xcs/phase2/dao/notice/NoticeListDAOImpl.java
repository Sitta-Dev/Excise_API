package com.xcs.phase2.dao.notice;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.notice.NoticeList;
import com.xcs.phase2.request.notice.NoticeListgetByConAdvReq;
import com.xcs.phase2.request.notice.NoticeListgetByKeywordReq;
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
public class NoticeListDAOImpl extends NoticeExt implements NoticeListDAO{

    private static final Logger log = LoggerFactory.getLogger(NoticeListDAOImpl.class);

    @Override
    public List<NoticeList> NoticeListgetByKeyword(final NoticeListgetByKeywordReq req) {

        String str = "";

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

                if("000".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 3))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND " +
                            "    ( " +
                            "      SUBSTR(OPS_NOTICE.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "    )";
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND " +
                            "    ( " +
                            "      SUBSTR(OPS_NOTICE.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "    )";
                }else {
                    str = " AND " +
                            "    (" +
                            "      OPS_NOTICE.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "      OR OPS_NOTICE_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "      OR OPS_NOTICE_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "      OR OPS_NOTICE_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "    )";
                }
            }
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select distinct " +
                        "    OPS_NOTICE.NOTICE_ID," +
                        "    OPS_NOTICE.NOTICE_CODE," +
                        "    OPS_NOTICE.NOTICE_DATE," +
                        "    OPS_NOTICE.NOTICE_DUE_DATE," +
                        "    OPS_NOTICE.OFFICE_NAME," +
                        "    OPS_NOTICE.IS_ARREST," +
                        "    OPS_NOTICE.IS_AUTHORITY" +
                        "    from OPS_NOTICE" +
                        "    left join OPS_NOTICE_STAFF on OPS_NOTICE.NOTICE_ID = OPS_NOTICE_STAFF.NOTICE_ID  " +
//                        "    left join OPS_NOTICE_SUSPECT on OPS_NOTICE.NOTICE_ID = OPS_NOTICE_SUSPECT.NOTICE_ID " +
//                        "    and OPS_NOTICE_SUSPECT.IS_ACTIVE = 1" +
                        "    where OPS_NOTICE.IS_ACTIVE = 1" +
                        "    and OPS_NOTICE_STAFF.IS_ACTIVE = 1" +
                        "    and OPS_NOTICE_STAFF.CONTRIBUTOR_ID in (7,8)" +
                        "    and " +
                        "    (" +
                                "  Lower(OPS_NOTICE.NOTICE_CODE) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                                "  OR LOWER(OPS_NOTICE_STAFF.TITLE_NAME_TH||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                                "  OR LOWER(OPS_NOTICE_STAFF.TITLE_NAME_EN||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                                "  OR LOWER(OPS_NOTICE_STAFF.TITLE_SHORT_NAME_TH||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                                "  OR LOWER(OPS_NOTICE_STAFF.TITLE_SHORT_NAME_EN||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
//                                "  OR LOWER(OPS_NOTICE_SUSPECT.TITLE_NAME_TH||OPS_NOTICE_SUSPECT.FIRST_NAME||OPS_NOTICE_SUSPECT.LAST_NAME||OPS_NOTICE_SUSPECT.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
//                                "  OR LOWER(OPS_NOTICE_SUSPECT.TITLE_NAME_EN||OPS_NOTICE_SUSPECT.FIRST_NAME||OPS_NOTICE_SUSPECT.LAST_NAME||OPS_NOTICE_SUSPECT.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
//                                "  OR LOWER(OPS_NOTICE_SUSPECT.TITLE_SHORT_NAME_TH||OPS_NOTICE_SUSPECT.FIRST_NAME||OPS_NOTICE_SUSPECT.LAST_NAME||OPS_NOTICE_SUSPECT.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
//                                "  OR LOWER(OPS_NOTICE_SUSPECT.TITLE_SHORT_NAME_EN||OPS_NOTICE_SUSPECT.FIRST_NAME||OPS_NOTICE_SUSPECT.LAST_NAME||OPS_NOTICE_SUSPECT.COMPANY_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                                "  OR lower(OPS_NOTICE.OFFICE_NAME) like lower ('%"+req.getTEXT_SEARCH()+"%') " +
                        "    )"+str +
                        "    order by OPS_NOTICE.NOTICE_DATE ,NOTICE_CODE desc ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NoticeList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NoticeList mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeList item = new NoticeList();
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
                item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
                item.setNOTICE_DATE(rs.getString("NOTICE_DATE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setIS_ARREST(rs.getInt("IS_ARREST"));

                item.setNoticeStaff(getNoticeStaff(rs.getInt("NOTICE_ID"),rs.getInt("IS_AUTHORITY")));
                item.setNoticeSuspect(getNoticeSuspect(rs.getInt("NOTICE_ID")));


                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<NoticeList> NoticeListgetByConAdv(final NoticeListgetByConAdvReq req) {

        String str = "";
        String tempNOTICE_DATE_START_FROM = "";
        String tempNOTICE_DATE_START_TO = "";

        if(!"".equals(req.getDATE_START_FROM())) {
            tempNOTICE_DATE_START_FROM = String.format("%s %s", req.getDATE_START_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getDATE_START_TO())) {
            tempNOTICE_DATE_START_TO = String.format("%s %s", req.getDATE_START_TO(),Pattern.TIME_TO);
        }

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

                if("000".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 3))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND " +
                            "    ( " +
                            "      SUBSTR(OPS_NOTICE.OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "    )";
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND " +
                            "    ( " +
                            "      SUBSTR(OPS_NOTICE.OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "      OR SUBSTR(OPS_NOTICE_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "    )";
                }else {
                    str = " AND " +
                            "    (" +
                            "      OPS_NOTICE.OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "      OR OPS_NOTICE_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "      OR OPS_NOTICE_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "      OR OPS_NOTICE_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "    )";
                }
            }
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select distinct " +
                        "    OPS_NOTICE.NOTICE_ID," +
                        "    OPS_NOTICE.NOTICE_CODE," +
                        "    OPS_NOTICE.NOTICE_DATE," +
                        "    OPS_NOTICE.NOTICE_DUE_DATE," +
                        "    OPS_NOTICE.OFFICE_NAME," +
                        "    OPS_NOTICE.IS_ARREST," +
                        "    OPS_NOTICE.IS_AUTHORITY" +
                        "    from OPS_NOTICE" +
                        "    left join OPS_NOTICE_STAFF on OPS_NOTICE.NOTICE_ID = OPS_NOTICE_STAFF.NOTICE_ID  " +
//                        "    left join OPS_NOTICE_SUSPECT on OPS_NOTICE.NOTICE_ID = OPS_NOTICE_SUSPECT.NOTICE_ID " +
//                        "    and OPS_NOTICE_SUSPECT.IS_ACTIVE = 1" +
                        "    where OPS_NOTICE.IS_ACTIVE = 1" +
                        "    and OPS_NOTICE_STAFF.IS_ACTIVE = 1" +
                        "    and OPS_NOTICE_STAFF.CONTRIBUTOR_ID in (7,8)" );



        if(req.getNOTICE_CODE() != null && !"".equals(req.getNOTICE_CODE())) {
            sqlBuilder.append(" AND LOWER(OPS_NOTICE.NOTICE_CODE) LIKE LOWER(REPLACE('%"+req.getNOTICE_CODE()+"%',' ','')) ");
        }

        if(req.getDATE_START_FROM() != null && !"".equals(req.getDATE_START_FROM()) && req.getDATE_START_TO() != null && !"".equals(req.getDATE_START_TO())) {
            sqlBuilder.append(" AND OPS_NOTICE.NOTICE_DATE BETWEEN  to_date(nvl('"+tempNOTICE_DATE_START_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempNOTICE_DATE_START_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getSTAFF_NAME() != null && !"".equals(req.getSTAFF_NAME())) {
            sqlBuilder.append(" AND " +
                    " (" +
                    "  LOWER(OPS_NOTICE_STAFF.TITLE_NAME_TH||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_NOTICE_STAFF.TITLE_NAME_EN||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_NOTICE_STAFF.TITLE_SHORT_NAME_TH||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_NOTICE_STAFF.TITLE_SHORT_NAME_EN||OPS_NOTICE_STAFF.FIRST_NAME||OPS_NOTICE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ',''))" +
                    " ) ");
        }

//        if(req.getSUSPECT_NAME() != null && !"".equals(req.getSUSPECT_NAME())) {
//            sqlBuilder.append(" AND LOWER(OPS_NOTICE_SUSPECT.FIRST_NAME||OPS_NOTICE_SUSPECT.MIDDLE_NAME||OPS_NOTICE_SUSPECT.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSUSPECT_NAME()+"%',' ','')) ");
//        }

        if(req.getOFFICE_NAME() != null && !"".equals(req.getOFFICE_NAME())) {
            sqlBuilder.append(" AND LOWER(OPS_NOTICE.OFFICE_NAME) LIKE LOWER('%"+req.getOFFICE_NAME()+"%') ");
        }

        if(req.getIS_ARREST() != null && !"".equals(req.getIS_ARREST())) {
            sqlBuilder.append(" AND OPS_NOTICE.IS_ARREST = '"+req.getIS_ARREST()+"' ");
        }

        sqlBuilder.append(str+" order by OPS_NOTICE.NOTICE_DATE ,NOTICE_CODE desc ");

        System.out.println("xx"+sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NoticeList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public NoticeList mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeList item = new NoticeList();
                item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
                item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
                item.setNOTICE_DATE(rs.getString("NOTICE_DATE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setIS_ARREST(rs.getInt("IS_ARREST"));

                item.setNoticeStaff(getNoticeStaff(rs.getInt("NOTICE_ID"),rs.getInt("IS_AUTHORITY")));
                item.setNoticeSuspect(getNoticeSuspect(rs.getInt("NOTICE_ID")));

                return item;
            }
        });

        return dataList;
    }
}
