package com.xcs.phase2.dao.evidencein;

import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.evidencein.EvidenceInBook;
import com.xcs.phase2.request.evidencein.EvidenceInBookListgetByConAdvReq;
import com.xcs.phase2.request.evidencein.EvidenceInBookListgetByKeywordReq;
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
public class EvidenceInBookDAOImpl extends EvidenceInExt implements EvidenceInBookDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInBookDAOImpl.class);

    @Override
    public List<EvidenceInBook> EvidenceInBookListgetByKeyword(final EvidenceInBookListgetByKeywordReq req) {

        String str = "";

        if (req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if (req.getACCOUNT_OFFICE_CODE().length() == 6) {
                if ("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                } else if ("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " and substr(OPS_EVIDENCE_OUT.OFFICE_CODE, 1, 2) = substr('" + req.getACCOUNT_OFFICE_CODE() + "', 1, 2)";
                } else if ("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " and substr(OPS_EVIDENCE_OUT.OFFICE_CODE, 1, 4) = substr('" + req.getACCOUNT_OFFICE_CODE() + "', 1, 4)";
                } else {
                    str = " and OPS_EVIDENCE_OUT.OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "'";
                }
            }
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_EVIDENCE_OUT.*" +
                        "    FROM OPS_EVIDENCE_OUT" +
                        "    LEFT JOIN OPS_EVIDENCE_OUT_STAFF on OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID = OPS_EVIDENCE_OUT_STAFF.EVIDENCE_OUT_ID " +
                        "    and OPS_EVIDENCE_OUT_STAFF.CONTRIBUTOR_ID = 77" +
                        "    WHERE" +
                        "    OPS_EVIDENCE_OUT.IS_ACTIVE = 1" +
                        "    AND" +
                        "    (" +
                        "        lower(OPS_EVIDENCE_OUT.EVIDENCE_OUT_NO)like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(OPS_EVIDENCE_OUT.EVIDENCE_OUT_CODE)like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(OPS_EVIDENCE_OUT_STAFF.TITLE_NAME_TH||''||OPS_EVIDENCE_OUT_STAFF.FIRST_NAME||''||OPS_EVIDENCE_OUT_STAFF.LAST_NAME)like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(OPS_EVIDENCE_OUT_STAFF.OPERATION_OFFICE_NAME)like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    ) " +str);

        log.info("[SQL ] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInBook> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceInBook mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInBook item = new EvidenceInBook();

                item.setEVIDENCE_OUT_ID(rs.getString("EVIDENCE_OUT_ID"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setEVIDENCE_OUT_CODE(rs.getString("EVIDENCE_OUT_CODE"));
                item.setEVIDENCE_OUT_DATE(rs.getString("EVIDENCE_OUT_DATE"));
                item.setEVIDENCE_OUT_TYPE(rs.getInt("EVIDENCE_OUT_TYPE"));
                item.setEVIDENCE_OUT_NO(rs.getString("EVIDENCE_OUT_NO"));
                item.setEVIDENCE_OUT_NO_DATE(rs.getString("EVIDENCE_OUT_NO_DATE"));
                item.setBOOK_NO(rs.getString("BOOK_NO"));
                item.setRECEIPT_NO(rs.getString("RECEIPT_NO"));
                item.setPAY_DATE(rs.getString("PAY_DATE"));
                item.setAPPROVE_DATE(rs.getString("APPROVE_DATE"));
                item.setRETURN_DATE(rs.getString("RETURN_DATE"));
                item.setREMARK(rs.getString("REMARK"));
                item.setAPPROVE_NO(rs.getString("APPROVE_NO"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setWAREHOUSE_ID(rs.getInt("WAREHOUSE_ID"));


                item.setEvidenceOutStaff(getEvidenceOutStaff(rs.getInt("EVIDENCE_IN_ID"),req.getACCOUNT_OFFICE_CODE()));
                return item;

            }
        });
        return dataList;
    }

    @Override
    public List<EvidenceInBook> EvidenceInBookListgetByConAdv(final EvidenceInBookListgetByConAdvReq req) {

        String str = "";

        if (req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if (req.getACCOUNT_OFFICE_CODE().length() == 6) {
                if ("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                } else if ("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " and substr(OPS_EVIDENCE_OUT.OFFICE_CODE, 1, 2) = substr('" + req.getACCOUNT_OFFICE_CODE() + "', 1, 2)";
                } else if ("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " and substr(OPS_EVIDENCE_OUT.OFFICE_CODE, 1, 4) = substr('" + req.getACCOUNT_OFFICE_CODE() + "', 1, 4)";
                } else {
                    str = " and OPS_EVIDENCE_OUT.OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "'";
                }
            }
        }

        String tempReturnDateFrom = "";
        String tempReturnDateTo = "";


        if (!"".equals(req.getRETURN_DATE_FROM())) {
            tempReturnDateFrom = String.format("%s %s", req.getRETURN_DATE_FROM(), Pattern.TIME_FROM);
        }

        if (!"".equals(req.getRETURN_DATE_TO())) {
            tempReturnDateTo = String.format("%s %s", req.getRETURN_DATE_TO(), Pattern.TIME_TO);
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_EVIDENCE_OUT.*" +
                        "    FROM OPS_EVIDENCE_OUT" +
                        "    LEFT JOIN OPS_EVIDENCE_OUT_STAFF on OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID = OPS_EVIDENCE_OUT_STAFF.EVIDENCE_OUT_ID " +
                        "    and OPS_EVIDENCE_OUT_STAFF.CONTRIBUTOR_ID = 77" +
                        "    WHERE" +
                        "    OPS_EVIDENCE_OUT.IS_ACTIVE = 1" +str );

        if (req.getEVIDENCE_OUT_NO() != null && !"".equals(req.getEVIDENCE_OUT_NO())) {
            sqlBuilder.append("  AND lower(OPS_EVIDENCE_OUT.EVIDENCE_OUT_NO)like lower(replace ('%"+req.getEVIDENCE_OUT_NO()+"%',' ','')) ");
        }

        if (req.getEVIDENCE_OUT_CODE() != null && !"".equals(req.getEVIDENCE_OUT_CODE())) {
            sqlBuilder.append("  AND lower(OPS_EVIDENCE_OUT.EVIDENCE_OUT_CODE)like lower(replace ('%"+req.getEVIDENCE_OUT_CODE()+"%',' ','')) ");
        }

        if (req.getEVIDENCE_OUT_STAFF() != null && !"".equals(req.getEVIDENCE_OUT_STAFF())) {
            sqlBuilder.append("  AND lower(OPS_EVIDENCE_OUT_STAFF.TITLE_NAME_TH||''||OPS_EVIDENCE_OUT_STAFF.FIRST_NAME||''||OPS_EVIDENCE_OUT_STAFF.LAST_NAME)like lower(replace ('%"+req.getEVIDENCE_OUT_STAFF()+"%',' ','')) ");
        }

        if (req.getOPERATION_OFFICE_NAME() != null && !"".equals(req.getOPERATION_OFFICE_NAME())) {
            sqlBuilder.append("  AND lower(OPS_EVIDENCE_OUT_STAFF.OPERATION_OFFICE_NAME)like lower(replace ('%"+req.getOPERATION_OFFICE_NAME()+"%',' ','')) ");
        }

        if (req.getRETURN_DATE_FROM() != null && !"".equals(req.getRETURN_DATE_FROM()) && req.getRETURN_DATE_TO() != null && !"".equals(req.getRETURN_DATE_TO())) {
            sqlBuilder.append(" and OPS_EVIDENCE_IN.DELIVERY_DATE between to_date(nvl('" + tempReturnDateFrom + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('" + tempReturnDateTo + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }



        log.info("[SQL ] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInBook> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceInBook mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInBook item = new EvidenceInBook();

                item.setEVIDENCE_OUT_ID(rs.getString("EVIDENCE_OUT_ID"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setEVIDENCE_OUT_CODE(rs.getString("EVIDENCE_OUT_CODE"));
                item.setEVIDENCE_OUT_DATE(rs.getString("EVIDENCE_OUT_DATE"));
                item.setEVIDENCE_OUT_TYPE(rs.getInt("EVIDENCE_OUT_TYPE"));
                item.setEVIDENCE_OUT_NO(rs.getString("EVIDENCE_OUT_NO"));
                item.setEVIDENCE_OUT_NO_DATE(rs.getString("EVIDENCE_OUT_NO_DATE"));
                item.setBOOK_NO(rs.getString("BOOK_NO"));
                item.setRECEIPT_NO(rs.getString("RECEIPT_NO"));
                item.setPAY_DATE(rs.getString("PAY_DATE"));
                item.setAPPROVE_DATE(rs.getString("APPROVE_DATE"));
                item.setRETURN_DATE(rs.getString("RETURN_DATE"));
                item.setREMARK(rs.getString("REMARK"));
                item.setAPPROVE_NO(rs.getString("APPROVE_NO"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setWAREHOUSE_ID(rs.getInt("WAREHOUSE_ID"));


                item.setEvidenceOutStaff(getEvidenceOutStaff(rs.getInt("EVIDENCE_IN_ID"),req.getACCOUNT_OFFICE_CODE()));
                return item;

            }
        });
        return dataList;
    }
}
