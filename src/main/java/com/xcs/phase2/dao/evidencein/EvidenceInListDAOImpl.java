package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.evidencein.EvidenceInList;
import com.xcs.phase2.request.evidencein.EvidenceInListgetByConAdvReq;
import com.xcs.phase2.request.evidencein.EvidenceInListgetByKeywordReq;
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
public class EvidenceInListDAOImpl extends EvidenceInExt implements EvidenceInListDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInListDAOImpl.class);

    @Override
    public List<EvidenceInList> EvidenceInListgetByKeyword(final EvidenceInListgetByKeywordReq req) {


        String str = "";

        if (req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if (req.getACCOUNT_OFFICE_CODE().length() == 6) {
                if ("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                } else if ("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " and substr(OPS_EVIDENCE_IN.OFFICE_CODE, 1, 2) = substr('" + req.getACCOUNT_OFFICE_CODE() + "', 1, 2)";
                } else if ("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " and substr(OPS_EVIDENCE_IN.OFFICE_CODE, 1, 4) = substr('" + req.getACCOUNT_OFFICE_CODE() + "', 1, 4)";
                } else {
                    str = " and OPS_EVIDENCE_IN.OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "'";
                }
            }
        }


        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_ID," +
                        "    OPS_EVIDENCE_IN.PROVE_ID," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_CODE," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_DATE," +
                        "    (select TITLE_NAME_TH||''||FIRST_NAME||' '||LAST_NAME as Inspector from OPS_EVIDENCE_IN_STAFF where CONTRIBUTOR_ID=60 and IS_ACTIVE = 1 and evidence_in_id=ops_evidence_in.evidence_in_id)as INSPECTOR," +
                        "    Inspector.OPERATION_OFFICE_NAME as INSPECTOR_OPERATION_OFFICE_NAME," +
                        "    OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "    OPS_EVIDENCE_IN.DELIVERY_DATE," +
                        "    (select TITLE_NAME_TH||''||FIRST_NAME||' '||LAST_NAME as Carrier from OPS_EVIDENCE_IN_STAFF where CONTRIBUTOR_ID=59 and IS_ACTIVE = 1 and evidence_in_id=ops_evidence_in.evidence_in_id)as CARRIER," +
                        "    Carrier.OPERATION_OFFICE_NAME as CARRIER_OPERATION_OFFICE_NAME," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_TYPE," +
                        "    OPS_LAWSUIT.LAWSUIT_NO," +
                        "    TO_CHAR (OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_YEAR," +
                        "    OPS_LAWSUIT.LAWSUIT_DATE," +
                        "    OPS_LAWSUIT_STAFF.TITLE_NAME_TH||''||OPS_LAWSUIT_STAFF.FIRST_NAME||' '||OPS_LAWSUIT_STAFF.LAST_NAME as LAWSUIT_STAFF," +
                        "    OPS_PROVE.PROVE_NO," +
                        "    OPS_PROVE.PROVE_DATE," +
                        "    OPS_PROVE_STAFF.TITLE_NAME_TH||''||OPS_PROVE_STAFF.FIRST_NAME||' '||OPS_PROVE_STAFF.LAST_NAME as PROVE_STAFF," +
                        "    OPS_EVIDENCE_IN.IS_ACTIVE" +
                        "    from OPS_EVIDENCE_IN" +
                        "    left join OPS_EVIDENCE_IN_ITEM on OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID" +
                        "    left join OPS_EVIDENCE_STOCK_BALANCE on OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID = OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID" +
                        "    left join MAS_WAREHOUSE on OPS_EVIDENCE_STOCK_BALANCE.WAREHOUSE_ID = MAS_WAREHOUSE.WAREHOUSE_ID " +
                        "    left join OPS_EVIDENCE_IN_STAFF Inspector on OPS_EVIDENCE_IN.EVIDENCE_IN_ID = Inspector.EVIDENCE_IN_ID " +
                        "    AND Inspector.CONTRIBUTOR_ID = 60" +
                        "    AND Inspector.IS_ACTIVE = 1" +
                        "    left join OPS_EVIDENCE_IN_STAFF Carrier on OPS_EVIDENCE_IN.EVIDENCE_IN_ID = Carrier.EVIDENCE_IN_ID " +
                        "    AND Carrier.CONTRIBUTOR_ID = 59" +
                        "    AND Carrier.IS_ACTIVE = 1" +
                        "    left join OPS_PROVE on OPS_EVIDENCE_IN.PROVE_ID = OPS_PROVE.PROVE_ID" +
                        "    left join OPS_PROVE_STAFF on OPS_PROVE.PROVE_ID = OPS_PROVE_STAFF.PROVE_ID" +
                        "    AND OPS_PROVE_STAFF.CONTRIBUTOR_ID = 25" +
                        "    AND OPS_PROVE_STAFF.IS_ACTIVE = 1" +
                        "    left join OPS_LAWSUIT on OPS_PROVE.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
                        "    left join OPS_LAWSUIT_STAFF on OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID" +
                        "    AND OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID = 16" +
                        "    AND OPS_LAWSUIT_STAFF.IS_ACTIVE = 1" +
                        "    WHERE OPS_EVIDENCE_IN.IS_ACTIVE = 1" +
                        "    AND" +
                        "    (" +
                        "        lower(OPS_EVIDENCE_IN.EVIDENCE_IN_CODE)like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(Inspector.TITLE_NAME_TH||Inspector.FIRST_NAME||Inspector.LAST_NAME) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(Inspector.OPERATION_OFFICE_NAME) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(OPS_EVIDENCE_IN.DELIVERY_NO) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(Carrier.TITLE_NAME_TH||Carrier.FIRST_NAME||Carrier.LAST_NAME) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(Carrier.OPERATION_OFFICE_NAME) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(MAS_WAREHOUSE.WAREHOUSE_NAME) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(OPS_LAWSUIT.LAWSUIT_NO)like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(OPS_LAWSUIT.LAWSUIT_NO_YEAR)like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(OPS_LAWSUIT_STAFF.TITLE_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "        OR lower(OPS_PROVE.PROVE_NO) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    ) "+str+" order by OPS_EVIDENCE_IN.EVIDENCE_IN_ID asc ");

        log.info("[SQL ] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceInList mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInList item = new EvidenceInList();

                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setEVIDENCE_IN_CODE(rs.getString("EVIDENCE_IN_CODE"));
                item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                item.setINSPECTOR(rs.getString("INSPECTOR"));
                item.setINSPECTOR_OPERATION_OFFICE_NAME(rs.getString("INSPECTOR_OPERATION_OFFICE_NAME"));
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                item.setCARRIER(rs.getString("CARRIER"));
                item.setCARRIER_OPERATION_OFFICE_NAME(rs.getString("CARRIER_OPERATION_OFFICE_NAME"));
                item.setWAREHOUSE_NAME(rs.getString("WAREHOUSE_NAME"));
                item.setEVIDENCE_IN_TYPE(rs.getInt("EVIDENCE_IN_TYPE"));

                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_YEAR(rs.getString("LAWSUIT_YEAR"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setLAWSUIT_STAFF(rs.getString("LAWSUIT_STAFF"));

                item.setPROVE_NO(rs.getString("PROVE_NO"));
                item.setPROVE_DATE(rs.getString("PROVE_DATE"));
                item.setPROVE_STAFF(rs.getString("PROVE_STAFF"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setEvidenceInStaff(getEvidenceInStaff(rs.getInt("EVIDENCE_IN_ID"),req.getACCOUNT_OFFICE_CODE()));
                return item;

            }
        });
        return dataList;

    }

    @Override
    public List<EvidenceInList> EvidenceInListgetByConAdv(final EvidenceInListgetByConAdvReq req) {


        String str = "";

        if (req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if (req.getACCOUNT_OFFICE_CODE().length() == 6) {
                if ("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                } else if ("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " and substr(OPS_EVIDENCE_IN.OFFICE_CODE, 1, 2) = substr('" + req.getACCOUNT_OFFICE_CODE() + "', 1, 2)";
                } else if ("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " and substr(OPS_EVIDENCE_IN.OFFICE_CODE, 1, 4) = substr('" + req.getACCOUNT_OFFICE_CODE() + "', 1, 4)";
                } else {
                    str = " and OPS_EVIDENCE_IN.OFFICE_CODE = '" + req.getACCOUNT_OFFICE_CODE() + "'";
                }
            }
        }


        String tempEvidenceInDateFrom = "";
        String tempEvidenceInDateTo = "";
        String tempDeliveryDateFrom = "";
        String tempDeliveryDateTo = "";
        String tempProveDateFrom = "";
        String tempProveDateTo = "";
        String tempLawsuitDateFrom = "";
        String tempLawsuitDateTo = "";


        if (!"".equals(req.getEVIDENCE_IN_DATE_START())) {
            tempEvidenceInDateFrom = String.format("%s %s", req.getEVIDENCE_IN_DATE_START(), Pattern.TIME_FROM);
        }

        if (!"".equals(req.getEVIDENCE_IN_DATE_TO())) {
            tempEvidenceInDateTo = String.format("%s %s", req.getEVIDENCE_IN_DATE_TO(), Pattern.TIME_TO);
        }

        if (!"".equals(req.getDELIVERY_DATE_START())) {
            tempDeliveryDateFrom = String.format("%s %s", req.getDELIVERY_DATE_START(), Pattern.TIME_FROM);
        }

        if (!"".equals(req.getDELIVERY_DATE_TO())) {
            tempDeliveryDateTo = String.format("%s %s", req.getDELIVERY_DATE_TO(), Pattern.TIME_TO);
        }

        if (!"".equals(req.getPROVE_DATE_START())) {
            tempProveDateFrom = String.format("%s %s", req.getPROVE_DATE_START(), Pattern.TIME_FROM);
        }

        if (!"".equals(req.getPROVE_DATE_TO())) {
            tempProveDateTo = String.format("%s %s", req.getPROVE_DATE_TO(), Pattern.TIME_TO);
        }

        if (!"".equals(req.getLAWSUIT_DATE_START())) {
            tempLawsuitDateFrom = String.format("%s %s", req.getLAWSUIT_DATE_START(), Pattern.TIME_FROM);
        }

        if (!"".equals(req.getLAWSUIT_DATE_TO())) {
            tempLawsuitDateTo = String.format("%s %s", req.getLAWSUIT_DATE_TO(), Pattern.TIME_TO);
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_ID," +
                        "    OPS_EVIDENCE_IN.PROVE_ID," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_CODE," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_DATE," +
                        "    (select TITLE_NAME_TH||''||FIRST_NAME||' '||LAST_NAME as Inspector from OPS_EVIDENCE_IN_STAFF where CONTRIBUTOR_ID=60 and IS_ACTIVE = 1 and evidence_in_id=ops_evidence_in.evidence_in_id)as INSPECTOR," +
                        "    Inspector.OPERATION_OFFICE_NAME as INSPECTOR_OPERATION_OFFICE_NAME," +
                        "    OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "    OPS_EVIDENCE_IN.DELIVERY_DATE," +
                        "    (select TITLE_NAME_TH||''||FIRST_NAME||' '||LAST_NAME as Carrier from OPS_EVIDENCE_IN_STAFF where CONTRIBUTOR_ID=59 and IS_ACTIVE = 1 and evidence_in_id=ops_evidence_in.evidence_in_id)as CARRIER," +
                        "    Carrier.OPERATION_OFFICE_NAME as CARRIER_OPERATION_OFFICE_NAME," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_TYPE," +
                        "    OPS_LAWSUIT.LAWSUIT_NO," +
                        "    TO_CHAR (OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_YEAR," +
                        "    OPS_LAWSUIT.LAWSUIT_DATE," +
                        "    OPS_LAWSUIT_STAFF.TITLE_NAME_TH||''||OPS_LAWSUIT_STAFF.FIRST_NAME||' '||OPS_LAWSUIT_STAFF.LAST_NAME as LAWSUIT_STAFF," +
                        "    OPS_PROVE.PROVE_NO," +
                        "    OPS_PROVE.PROVE_DATE," +
                        "    OPS_PROVE_STAFF.TITLE_NAME_TH||''||OPS_PROVE_STAFF.FIRST_NAME||' '||OPS_PROVE_STAFF.LAST_NAME as PROVE_STAFF," +
                        "    OPS_EVIDENCE_IN.IS_ACTIVE" +
                        "    from OPS_EVIDENCE_IN" +
                        "    left join OPS_EVIDENCE_IN_ITEM on OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID" +
                        "    left join OPS_EVIDENCE_STOCK_BALANCE on OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID = OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID" +
                        "    left join MAS_WAREHOUSE on OPS_EVIDENCE_STOCK_BALANCE.WAREHOUSE_ID = MAS_WAREHOUSE.WAREHOUSE_ID " +
                        "    left join OPS_EVIDENCE_IN_STAFF Inspector on OPS_EVIDENCE_IN.EVIDENCE_IN_ID = Inspector.EVIDENCE_IN_ID " +
                        "    AND Inspector.CONTRIBUTOR_ID = 60" +
                        "    AND Inspector.IS_ACTIVE = 1" +
                        "    left join OPS_EVIDENCE_IN_STAFF Carrier on OPS_EVIDENCE_IN.EVIDENCE_IN_ID = Carrier.EVIDENCE_IN_ID " +
                        "    AND Carrier.CONTRIBUTOR_ID = 59" +
                        "    AND Carrier.IS_ACTIVE = 1" +
                        "    left join OPS_PROVE on OPS_EVIDENCE_IN.PROVE_ID = OPS_PROVE.PROVE_ID" +
                        "    left join OPS_PROVE_STAFF on OPS_PROVE.PROVE_ID = OPS_PROVE_STAFF.PROVE_ID" +
                        "    AND OPS_PROVE_STAFF.CONTRIBUTOR_ID = 25" +
                        "    AND OPS_PROVE_STAFF.IS_ACTIVE = 1" +
                        "    left join OPS_LAWSUIT on OPS_PROVE.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
                        "    left join OPS_LAWSUIT_STAFF on OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID" +
                        "    AND OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID = 16" +
                        "    AND OPS_LAWSUIT_STAFF.IS_ACTIVE = 1" +
                        "    WHERE OPS_EVIDENCE_IN.IS_ACTIVE = 1" );

        if (req.getEVIDENCE_IN_CODE() != null && !"".equals(req.getEVIDENCE_IN_CODE())) {
            sqlBuilder.append(" and lower(OPS_EVIDENCE_IN.EVIDENCE_IN_CODE)like lower(replace ('%" + req.getEVIDENCE_IN_CODE() + "%',' ','')) ");
        }

        if (req.getEVIDENCE_IN_DATE_START() != null && !"".equals(req.getEVIDENCE_IN_DATE_START()) && req.getEVIDENCE_IN_DATE_TO() != null && !"".equals(req.getEVIDENCE_IN_DATE_TO())) {
            sqlBuilder.append(" and OPS_EVIDENCE_IN.EVIDENCE_IN_DATE between to_date(nvl('" + tempEvidenceInDateFrom + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('" + tempEvidenceInDateTo + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if (req.getRECEIVER_NAME() != null && !"".equals(req.getRECEIVER_NAME())) {
            sqlBuilder.append("  AND lower(Inspector.TITLE_NAME_TH||Inspector.FIRST_NAME||Inspector.LAST_NAME) like lower(replace ('%"+req.getRECEIVER_NAME()+"%',' ','')) ");
        }

        if (req.getRECEIVER_OFFICE_NAME() != null && !"".equals(req.getRECEIVER_OFFICE_NAME())) {
            sqlBuilder.append("  AND lower(Inspector.OPERATION_OFFICE_NAME) like lower(replace ('%"+req.getRECEIVER_OFFICE_NAME()+"%',' ','')) ");
        }

        if (req.getDELIVERY_NO() != null && !"".equals(req.getDELIVERY_NO())) {
            sqlBuilder.append("  AND lower(OPS_EVIDENCE_IN.DELIVERY_NO) like lower(replace ('%"+req.getDELIVERY_NO()+"%',' ','')) ");
        }

        if (req.getDELIVERY_DATE_START() != null && !"".equals(req.getDELIVERY_DATE_START()) && req.getDELIVERY_DATE_TO() != null && !"".equals(req.getDELIVERY_DATE_TO())) {
            sqlBuilder.append(" and OPS_EVIDENCE_IN.DELIVERY_DATE between to_date(nvl('" + tempDeliveryDateFrom + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('" + tempDeliveryDateTo + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if (req.getDELIVER_NAME() != null && !"".equals(req.getDELIVER_NAME())) {
            sqlBuilder.append("  AND lower(Carrier.TITLE_NAME_TH||Carrier.FIRST_NAME||Carrier.LAST_NAME) like lower(replace ('%"+req.getDELIVER_NAME()+"%',' ','')) ");
        }

        if (req.getDELIVER_OFFICE_NAME() != null && !"".equals(req.getDELIVER_OFFICE_NAME())) {
            sqlBuilder.append("  AND lower(Carrier.OPERATION_OFFICE_NAME) like lower(replace ('%"+req.getRECEIVER_OFFICE_NAME()+"%',' ','')) ");
        }

        if (req.getWAREHOUSE_NAME() != null && !"".equals(req.getWAREHOUSE_NAME())) {
            sqlBuilder.append("  AND lower(MAS_WAREHOUSE.WAREHOUSE_NAME) like lower(replace ('%"+req.getWAREHOUSE_NAME()+"%',' ','')) ");
        }

        if (req.getEVIDENCE_IN_TYPE() != null && req.getEVIDENCE_IN_TYPE() != 0) {
            sqlBuilder.append("  AND OPS_EVIDENCE_IN.EVIDENCE_IN_TYPE = '"+req.getEVIDENCE_IN_TYPE()+"' ");
        }

        if (req.getLAWSUIT_NO() != null && !"".equals(req.getLAWSUIT_NO())) {
            sqlBuilder.append("  AND lower(OPS_LAWSUIT.LAWSUIT_NO)like lower(replace ('%"+req.getLAWSUIT_NO()+"%',' ','')) ");
        }

        if (req.getLAWSUIT_DATE_START() != null && !"".equals(req.getLAWSUIT_DATE_START()) && req.getLAWSUIT_DATE_TO() != null && !"".equals(req.getLAWSUIT_DATE_TO())) {
            sqlBuilder.append(" and OPS_LAWSUIT.LAWSUIT_DATE between to_date(nvl('" + tempLawsuitDateFrom + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('" + tempLawsuitDateTo + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if (req.getLAWSUIT_STAFF() != null && !"".equals(req.getLAWSUIT_STAFF())) {
            sqlBuilder.append("  AND lower(OPS_LAWSUIT_STAFF.TITLE_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) like lower(replace ('%"+req.getLAWSUIT_STAFF()+"%',' ','')) ");
        }

        if (req.getPROVE_NO() != null && !"".equals(req.getPROVE_NO())) {
            sqlBuilder.append("  AND lower(OPS_PROVE.PROVE_NO) like lower(replace ('%"+req.getPROVE_NO()+"%',' ','')) ");
        }

        if (req.getPROVE_NO_YEAR() != null && !"".equals(req.getPROVE_NO_YEAR())) {
            sqlBuilder.append("  AND lower(OPS_PROVE.PROVE_NO_YEAR)like lower(replace ('%"+req.getPROVE_NO_YEAR()+"%',' ','')) ");
        }

        if (req.getPROVE_DATE_START() != null && !"".equals(req.getPROVE_DATE_START()) && req.getPROVE_DATE_TO() != null && !"".equals(req.getPROVE_DATE_TO())) {
            sqlBuilder.append(" and OPS_PROVE.PROVE_DATE between to_date(nvl('" + tempProveDateFrom + "','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('" + tempProveDateTo + "','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if (req.getPROVE_STAFF() != null && !"".equals(req.getPROVE_STAFF())) {
            sqlBuilder.append("  AND lower(OPS_PROVE_STAFF.TITLE_NAME_TH||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) like lower(replace ('%"+req.getPROVE_STAFF()+"%',' ','')) ");
        }

        sqlBuilder.append( str + " order by OPS_EVIDENCE_IN.EVIDENCE_IN_ID asc ");



        @SuppressWarnings("unchecked")
        List<EvidenceInList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceInList mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInList item = new EvidenceInList();

                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setEVIDENCE_IN_CODE(rs.getString("EVIDENCE_IN_CODE"));
                item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                item.setINSPECTOR(rs.getString("INSPECTOR"));
                item.setINSPECTOR_OPERATION_OFFICE_NAME(rs.getString("INSPECTOR_OPERATION_OFFICE_NAME"));
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                item.setCARRIER(rs.getString("CARRIER"));
                item.setCARRIER_OPERATION_OFFICE_NAME(rs.getString("CARRIER_OPERATION_OFFICE_NAME"));
                item.setWAREHOUSE_NAME(rs.getString("WAREHOUSE_NAME"));
                item.setEVIDENCE_IN_TYPE(rs.getInt("EVIDENCE_IN_TYPE"));

                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_YEAR(rs.getString("LAWSUIT_YEAR"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setLAWSUIT_STAFF(rs.getString("LAWSUIT_STAFF"));

                item.setPROVE_NO(rs.getString("PROVE_NO"));
                item.setPROVE_DATE(rs.getString("PROVE_DATE"));
                item.setPROVE_STAFF(rs.getString("PROVE_STAFF"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setEvidenceInStaff(getEvidenceInStaff(rs.getInt("EVIDENCE_IN_ID"),req.getACCOUNT_OFFICE_CODE()));
                return item;

            }
        });
        return dataList;

    }
}
