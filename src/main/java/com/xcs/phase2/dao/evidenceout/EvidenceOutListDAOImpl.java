package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.evidenceout.EvidenceOutList;
import com.xcs.phase2.request.evidenceout.EvidenceOutListgetByConAdvReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutListgetByKeywordReq;
import org.apache.commons.lang3.StringUtils;
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
public class EvidenceOutListDAOImpl extends EvidenceOutExt implements EvidenceOutListDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutListDAOImpl.class);

    @Override
    public List<EvidenceOutList> EvidenceOutListgetByKeyword(EvidenceOutListgetByKeywordReq req) {


        String str = "";

        if(req.getOPERATION_OFFICE_CODE() != null && !"".equals(req.getOPERATION_OFFICE_CODE()) && !"00".equals(req.getOPERATION_OFFICE_CODE())) {

            if(req.getOPERATION_OFFICE_CODE().length() == 6) {
                if("00".equals(req.getOPERATION_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getOPERATION_OFFICE_CODE().substring(2, 6))) {
                    str = " AND SUBSTR(OPS_EVIDENCE_OUT.OFFICE_CODE,1,2) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,2) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,2) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,2) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,2)";
                }else if("00".equals(req.getOPERATION_OFFICE_CODE().substring(4, 6))) {
                    str = " AND SUBSTR(OPS_EVIDENCE_OUT.OFFICE_CODE,1,4) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,4) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,4) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,4) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,4)";
                }else {
                    str = " AND (OPS_EVIDENCE_OUT.OFFICE_CODE = '"+req.getOPERATION_OFFICE_CODE()+"' " +
                            " OR OPS_EVIDENCE_OUT_STAFF.OPERATION_OFFICE_CODE = '"+req.getOPERATION_OFFICE_CODE()+"' " +
                            " OR OPS_EVIDENCE_OUT_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getOPERATION_OFFICE_CODE()+"' " +
                            " OR OPS_EVIDENCE_OUT_STAFF.REPRESENT_OFFICE_CODE = '"+req.getOPERATION_OFFICE_CODE()+"' )";
                }
            }
        }
            
        StringBuilder sqlBuilder = new StringBuilder()
                .append("	SELECT DISTINCT " +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_CODE," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_DATE," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_TYPE," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_NO," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_NO_DATE," +
                        "	OPS_EVIDENCE_OUT.BOOK_NO," +
                        "	OPS_EVIDENCE_OUT.RECEIPT_NO," +
                        "	OPS_EVIDENCE_OUT.PAY_DATE," +
                        "	OPS_EVIDENCE_OUT.APPROVE_DATE," +
                        "	OPS_EVIDENCE_OUT.RETURN_DATE," +
                        "	OPS_EVIDENCE_OUT.APPROVE_NO," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_IN_ID," +
                        "	OPS_EVIDENCE_OUT.SEND_TO_OFFICE_CODE ," +
                        "	OPS_EVIDENCE_OUT.SEND_TO_OFFICE_NAME ," +
                        "	TO_CHAR(OPS_EVIDENCE_OUT.EVIDENCE_OUT_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS TEXT_EVIDENCE_OUT_DATE," +
                        "	TO_CHAR(OPS_EVIDENCE_OUT.APPROVE_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS TEXT_APPROVE_DATE" +
                        "	FROM OPS_EVIDENCE_OUT" +
                        "	LEFT JOIN OPS_EVIDENCE_OUT_STAFF ON OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID = OPS_EVIDENCE_OUT_STAFF.EVIDENCE_OUT_ID " +
                        "	AND OPS_EVIDENCE_OUT_STAFF.IS_ACTIVE=1" +
                        "	AND OPS_EVIDENCE_OUT.IS_ACTIVE=1 " +
                        "	WHERE 1=1 " +
                        "	AND" +
                        "	(" +
                        "	    lower(OPS_EVIDENCE_OUT.EVIDENCE_OUT_CODE)like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "	    OR lower(OPS_EVIDENCE_OUT.EVIDENCE_OUT_NO)like lower('%"+req.getTEXT_SEARCH()+"%')" +
                        "	    OR lower(OPS_EVIDENCE_OUT_STAFF.TITLE_NAME_TH ||OPS_EVIDENCE_OUT_STAFF.FIRST_NAME ||OPS_EVIDENCE_OUT_STAFF.LAST_NAME)like lower('%"+req.getTEXT_SEARCH()+"%')" +
                        "	    OR lower(OPS_EVIDENCE_OUT.APPROVE_NO)like lower('%"+req.getTEXT_SEARCH()+"%')" +
                        "	)");

        int type = req.getEVIDENCE_OUT_TYPE().length;
        System.out.println ("type "+type);
        
        if(req.getEVIDENCE_OUT_TYPE().length > 0) {
            String strArr = StringUtils.join(req.getEVIDENCE_OUT_TYPE(), ",");
            sqlBuilder.append(" and OPS_EVIDENCE_OUT.EVIDENCE_OUT_TYPE in ("+ strArr +") ");
        }
        
        
        if (req.getEVIDENCE_OUT_TYPE().length ==1){
        		String a = req.getEVIDENCE_OUT_TYPE()[0];
        		System.out.println("a : "+a);
        		if(a.equals("6")) {
                    sqlBuilder.append(" AND OPS_EVIDENCE_OUT_STAFF.CONTRIBUTOR_ID in (65)");
                    System.out.println ("equals1 : "+ a.equals("6") );
                }
                else {
                	sqlBuilder.append(" AND OPS_EVIDENCE_OUT_STAFF.CONTRIBUTOR_ID in (77)");
                	System.out.println ("equals2 : "+ req.getEVIDENCE_OUT_TYPE().equals(0) );
                	System.out.println ("getEVIDENCE_OUT_TYPE : "+ req.getEVIDENCE_OUT_TYPE());
                }
        		
        		
        		
        	
        }
        


        sqlBuilder.append( str +" order by OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID asc ");

        log.info("[SQL ] : "+sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceOutList mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutList item = new EvidenceOutList();

                item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
                item.setEVIDENCE_OUT_CODE(rs.getString("EVIDENCE_OUT_CODE"));
                item.setEVIDENCE_OUT_DATE(rs.getString("EVIDENCE_OUT_DATE"));
                item.setEVIDENCE_OUT_TYPE(rs.getString("EVIDENCE_OUT_TYPE"));
                item.setEVIDENCE_OUT_NO(rs.getString("EVIDENCE_OUT_NO"));
                item.setEVIDENCE_OUT_NO_DATE(rs.getString("EVIDENCE_OUT_NO_DATE"));
                item.setBOOK_NO(rs.getString("BOOK_NO"));
                item.setRECEIPT_NO(rs.getString("RECEIPT_NO"));
                item.setPAY_DATE(rs.getString("PAY_DATE"));
                item.setAPPROVE_DATE(rs.getString("APPROVE_DATE"));
                item.setRETURN_DATE(rs.getString("RETURN_DATE"));
                item.setAPPROVE_NO(rs.getString("APPROVE_NO"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setSEND_TO_OFFICE_CODE(rs.getString("SEND_TO_OFFICE_CODE"));
                item.setSEND_TO_OFFICE_NAME(rs.getString("SEND_TO_OFFICE_NAME"));
                item.setTEXT_EVIDENCE_OUT_DATE(rs.getString("TEXT_EVIDENCE_OUT_DATE"));
                item.setTEXT_APPROVE_DATE(rs.getString("TEXT_APPROVE_DATE"));
                item.setEvidenceOutStaff(getEvidenceOutStaff(rs.getInt("EVIDENCE_OUT_ID")));
                return item;

            }
        });
        return dataList;

    }

    @Override
    public List<EvidenceOutList> EvidenceOutListgetByConAdv(EvidenceOutListgetByConAdvReq req) {


        String str = "";

        if(req.getOPERATION_OFFICE_CODE() != null && !"".equals(req.getOPERATION_OFFICE_CODE()) && !"00".equals(req.getOPERATION_OFFICE_CODE())) {

            if(req.getOPERATION_OFFICE_CODE().length() == 6) {
                if("00".equals(req.getOPERATION_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getOPERATION_OFFICE_CODE().substring(2, 6))) {
                    str = " AND SUBSTR(OPS_EVIDENCE_OUT.OFFICE_CODE,1,2) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,2) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,2) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,2) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,2)";
                }else if("00".equals(req.getOPERATION_OFFICE_CODE().substring(4, 6))) {
                    str = " AND SUBSTR(OPS_EVIDENCE_OUT.OFFICE_CODE,1,4) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,4) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,4) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,4) " +
                            " OR SUBSTR(OPS_EVIDENCE_OUT_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getOPERATION_OFFICE_CODE()+"',1,4)";
                }else {
                    str = " AND (OPS_EVIDENCE_OUT.OFFICE_CODE = '"+req.getOPERATION_OFFICE_CODE()+"' " +
                            " OR OPS_EVIDENCE_OUT_STAFF.OPERATION_OFFICE_CODE = '"+req.getOPERATION_OFFICE_CODE()+"' " +
                            " OR OPS_EVIDENCE_OUT_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getOPERATION_OFFICE_CODE()+"' " +
                            " OR OPS_EVIDENCE_OUT_STAFF.REPRESENT_OFFICE_CODE = '"+req.getOPERATION_OFFICE_CODE()+"' )";
                }
            }
        }

        String tempEvidenceOutDateFrom = "";
        String tempEvidenceOutDateTo = "";
        String tempEvidenceOutApproveDateFrom = "";
        String tempEvidenceOutApproveDateTo = "";


        if(!"".equals(req.getEVIDENCE_OUT_DATE_FROM())) {
            tempEvidenceOutDateFrom = String.format("%s %s", req.getEVIDENCE_OUT_DATE_FROM(), Pattern.TIME_FROM);
        }

        if(!"".equals(req.getEVIDENCE_OUT_DATE_TO())) {
            tempEvidenceOutDateTo = String.format("%s %s", req.getEVIDENCE_OUT_DATE_TO(),Pattern.TIME_TO);
        }

        if(!"".equals(req.getEVIDENCE_OUT_APPROVE_DATE_FROM())) {
            tempEvidenceOutApproveDateFrom = String.format("%s %s", req.getEVIDENCE_OUT_APPROVE_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getEVIDENCE_OUT_APPROVE_DATE_TO())) {
            tempEvidenceOutApproveDateTo = String.format("%s %s", req.getEVIDENCE_OUT_APPROVE_DATE_TO(),Pattern.TIME_TO);
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("	SELECT DISTINCT " +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_CODE," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_DATE," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_TYPE," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_NO," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_OUT_NO_DATE," +
                        "	OPS_EVIDENCE_OUT.BOOK_NO," +
                        "	OPS_EVIDENCE_OUT.RECEIPT_NO," +
                        "	OPS_EVIDENCE_OUT.PAY_DATE," +
                        "	OPS_EVIDENCE_OUT.APPROVE_DATE," +
                        "	OPS_EVIDENCE_OUT.RETURN_DATE," +
                        "	OPS_EVIDENCE_OUT.APPROVE_NO," +
                        "	OPS_EVIDENCE_OUT.EVIDENCE_IN_ID," +
                        "	OPS_EVIDENCE_OUT.SEND_TO_OFFICE_CODE ," +
                        "	OPS_EVIDENCE_OUT.SEND_TO_OFFICE_NAME ," +
                        "	TO_CHAR(OPS_EVIDENCE_OUT.EVIDENCE_OUT_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS TEXT_EVIDENCE_OUT_DATE," +
                        "	TO_CHAR(OPS_EVIDENCE_OUT.APPROVE_DATE, 'dd MONTH yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS TEXT_APPROVE_DATE" +
                        "	FROM OPS_EVIDENCE_OUT" +
                        "	LEFT JOIN OPS_EVIDENCE_OUT_STAFF ON OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID = OPS_EVIDENCE_OUT_STAFF.EVIDENCE_OUT_ID " +
                        "	AND OPS_EVIDENCE_OUT_STAFF.IS_ACTIVE=1" +
                        "	AND OPS_EVIDENCE_OUT.IS_ACTIVE=1 " +
                        "	WHERE 1=1 AND OPS_EVIDENCE_OUT_STAFF.CONTRIBUTOR_ID in (77) ");

        if(req.getEVIDENCE_OUT_TYPE().length > 0) {
            String strArr = StringUtils.join(req.getEVIDENCE_OUT_TYPE(), ",");
            sqlBuilder.append(" and OPS_EVIDENCE_OUT.EVIDENCE_OUT_TYPE in ("+ strArr +") ");
        }

        if(req.getEVIDENCE_OUT_CODE() != null && !"".equals(req.getEVIDENCE_OUT_CODE())) {
            sqlBuilder.append(" AND lower(OPS_EVIDENCE_OUT.EVIDENCE_OUT_CODE)like lower('%"+req.getEVIDENCE_OUT_CODE()+"%') ");
        }

        if(req.getEVIDENCE_OUT_DATE_FROM() != null && !"".equals(req.getEVIDENCE_OUT_DATE_FROM()) && req.getEVIDENCE_OUT_DATE_TO() != null && !"".equals(req.getEVIDENCE_OUT_DATE_TO())) {
            sqlBuilder.append(" and OPS_EVIDENCE_OUT.EVIDENCE_OUT_DATE between to_date(nvl('"+tempEvidenceOutDateFrom+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempEvidenceOutDateTo+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getEVIDENCE_OUT_NO() != null && !"".equals(req.getEVIDENCE_OUT_NO())) {
            sqlBuilder.append(" AND lower(OPS_EVIDENCE_OUT.EVIDENCE_OUT_NO)like lower('%"+req.getEVIDENCE_OUT_NO()+"%') ");
        }

        if(req.getEVIDENCE_OUT_APPROVE_DATE_FROM() != null && !"".equals(req.getEVIDENCE_OUT_APPROVE_DATE_FROM()) && req.getEVIDENCE_OUT_APPROVE_DATE_TO() != null && !"".equals(req.getEVIDENCE_OUT_APPROVE_DATE_TO())) {
            sqlBuilder.append(" and OPS_EVIDENCE_OUT.APPROVE_DATE between to_date(nvl('"+tempEvidenceOutApproveDateFrom+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempEvidenceOutApproveDateTo+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }


        if(req.getSTAFF_NAME() != null && !"".equals(req.getSTAFF_NAME())) {
            sqlBuilder.append(" AND (" +
                    " LOWER(OPS_EVIDENCE_OUT_STAFF.TITLE_NAME_TH||OPS_EVIDENCE_OUT_STAFF.FIRST_NAME||OPS_EVIDENCE_OUT_STAFF.LAST_NAME) like lower('%"+req.getSTAFF_NAME()+"%') " +
                    " OR LOWER(OPS_EVIDENCE_OUT_STAFF.TITLE_NAME_EN||OPS_EVIDENCE_OUT_STAFF.FIRST_NAME||OPS_EVIDENCE_OUT_STAFF.LAST_NAME) like lower('%"+req.getSTAFF_NAME()+"%') " +
                    " OR LOWER(OPS_EVIDENCE_OUT_STAFF.TITLE_SHORT_NAME_TH||OPS_EVIDENCE_OUT_STAFF.FIRST_NAME||OPS_EVIDENCE_OUT_STAFF.LAST_NAME) like lower('%"+req.getSTAFF_NAME()+"%') " +
                    " OR LOWER(OPS_EVIDENCE_OUT_STAFF.TITLE_SHORT_NAME_EN||OPS_EVIDENCE_OUT_STAFF.FIRST_NAME||OPS_EVIDENCE_OUT_STAFF.LAST_NAME) like lower('%"+req.getSTAFF_NAME()+"%')  " +
                    " ) ");
        }

        sqlBuilder.append( str + " ORDER BY OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID asc ");

        log.info("[SQL ] : "+sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceOutList mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutList item = new EvidenceOutList();

                item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
                item.setEVIDENCE_OUT_CODE(rs.getString("EVIDENCE_OUT_CODE"));
                item.setEVIDENCE_OUT_DATE(rs.getString("EVIDENCE_OUT_DATE"));
                item.setEVIDENCE_OUT_TYPE(rs.getString("EVIDENCE_OUT_TYPE"));
                item.setEVIDENCE_OUT_NO(rs.getString("EVIDENCE_OUT_NO"));
                item.setEVIDENCE_OUT_NO_DATE(rs.getString("EVIDENCE_OUT_NO_DATE"));
                item.setBOOK_NO(rs.getString("BOOK_NO"));
                item.setRECEIPT_NO(rs.getString("RECEIPT_NO"));
                item.setPAY_DATE(rs.getString("PAY_DATE"));
                item.setAPPROVE_DATE(rs.getString("APPROVE_DATE"));
                item.setRETURN_DATE(rs.getString("RETURN_DATE"));
                item.setAPPROVE_NO(rs.getString("APPROVE_NO"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setSEND_TO_OFFICE_CODE(rs.getString("SEND_TO_OFFICE_CODE"));
                item.setSEND_TO_OFFICE_NAME(rs.getString("SEND_TO_OFFICE_NAME"));
                item.setTEXT_EVIDENCE_OUT_DATE(rs.getString("TEXT_EVIDENCE_OUT_DATE"));
                item.setTEXT_APPROVE_DATE(rs.getString("TEXT_APPROVE_DATE"));
                item.setEvidenceOutStaff(getEvidenceOutStaff(rs.getInt("EVIDENCE_OUT_ID")));
                return item;

            }
        });
        return dataList;

    }
}
