package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.model.evidenceout.EvidenceInventory;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByConDetailReq;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByConReq;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByEvidenceInItemCodeReq;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByOfficeNameReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class EvidenceInventoryDAOImpl extends EvidenceOutExt implements EvidenceInventoryDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutListDAOImpl.class);

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return super.getJdbcTemplate();
    }

    @Override
    public List<EvidenceInventory> EvidenceInventoryListgetByOfficeName(EvidenceInventoryListgetByOfficeNameReq req) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append(" select " +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE," +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_NAME," +
                        "sum(OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY) as BALANCE_QTY" +
                        " from" +
                        " OPS_EVIDENCE_IN" +
                        " left join OPS_EVIDENCE_IN_STAFF on OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID " +
                        " left join OPS_EVIDENCE_IN_ITEM on OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID" +
                        " left join OPS_EVIDENCE_STOCK_BALANCE on OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID=OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID" +
                        " WHERE OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID = 42 and OPS_EVIDENCE_STOCK_BALANCE.IS_FINISH <> 1" +
                        " AND lower(OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_NAME) like lower(replace ('%" + req.getOPERATION_OFFICE_NAME() + "%',' ',''))" +
                        " Group by OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE,OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_NAME");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInventory> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceInventory mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInventory item = new EvidenceInventory();

                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setBALANCE_QTY(rs.getFloat("BALANCE_QTY"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<EvidenceInventory> EvidenceInventoryListgetByEvidenceInItemCode(EvidenceInventoryListgetByEvidenceInItemCodeReq req) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append(" select " +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE," +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_NAME," +
                        "sum(OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY) as BALANCE_QTY" +
                        " from" +
                        " OPS_EVIDENCE_IN" +
                        " left join OPS_EVIDENCE_IN_STAFF on OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID " +
                        " left join OPS_EVIDENCE_IN_ITEM on OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID" +
                        " left join OPS_EVIDENCE_STOCK_BALANCE on OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID=OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID" +
                        " WHERE OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID = 42 and OPS_EVIDENCE_STOCK_BALANCE.IS_FINISH <> 1" +
                        " AND lower(OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE) like lower(replace ('%" + req.getEVIDENCE_IN_ITEM_CODE() + "%',' ',''))" +
                        " Group by OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE,OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_NAME");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInventory> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceInventory mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInventory item = new EvidenceInventory();

                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setBALANCE_QTY(rs.getFloat("BALANCE_QTY"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<EvidenceInventory> EvidenceInventoryListgetByCon(EvidenceInventoryListgetByConReq req) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE," +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_NAME," +
                        "OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE," +
                        "OPS_EVIDENCE_IN_ITEM.PRODUCT_DESC," +
                        "OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY," +
                        "OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY_UNIT" +
                        " from" +
                        " OPS_EVIDENCE_IN" +
                        " left join OPS_EVIDENCE_IN_STAFF on OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID " +
                        " left join OPS_EVIDENCE_IN_ITEM on OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID" +
                        " left join OPS_EVIDENCE_STOCK_BALANCE on OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID=OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID" +
                        " WHERE OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID = 42" +
                        " AND OPS_EVIDENCE_STOCK_BALANCE.IS_FINISH<>1" +
                        " AND OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE = '" + req.getREPRESENT_OFFICE_CODE() + "' " +
                        " Group by" +
                        " OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE," +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_NAME," +
                        "OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE," +
                        "OPS_EVIDENCE_IN_ITEM.PRODUCT_DESC," +
                        "OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY," +
                        "OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY_UNIT");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInventory> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceInventory mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInventory item = new EvidenceInventory();

                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setEVIDENCE_IN_ITEM_CODE(rs.getString("EVIDENCE_IN_ITEM_CODE"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setBALANCE_QTY(rs.getFloat("BALANCE_QTY"));
                item.setBALANCE_QTY_UNIT(rs.getString("BALANCE_QTY_UNIT"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<EvidenceInventory> EvidenceInventoryListgetByConDetail(EvidenceInventoryListgetByConDetailReq req) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE," +
                        "OPS_EVIDENCE_IN_ITEM.PRODUCT_DESC," +
                        "sum(OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY) as BALANCE_QTY," +
                        "OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY_UNIT," +
                        "OPS_EVIDENCE_IN.EVIDENCE_IN_TYPE," +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE," +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_NAME," +
                        "OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "OPS_EVIDENCE_IN_STAFF.TITLE_NAME_TH," +
                        "OPS_EVIDENCE_IN_STAFF.FIRST_NAME," +
                        "OPS_EVIDENCE_IN_STAFF.LAST_NAME," +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_POS_NAME," +
                        "OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID" +
                        " from" +
                        " OPS_EVIDENCE_IN" +
                        " left join OPS_EVIDENCE_IN_STAFF on OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID " +
                        " left join OPS_EVIDENCE_IN_ITEM on OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID=OPS_EVIDENCE_IN.EVIDENCE_IN_ID" +
                        " left join OPS_EVIDENCE_STOCK_BALANCE on OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID=OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID" +
                        " WHERE OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID = 13" +
                        " AND OPS_EVIDENCE_STOCK_BALANCE.IS_FINISH<>1" +
                        " AND OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE = '" + req.getEVIDENCE_IN_ITEM_CODE() + "'" +
                        " Group by" +
                        " OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE," +
                        "OPS_EVIDENCE_IN_ITEM.PRODUCT_DESC," +
                        "OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY," +
                        "OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY_UNIT," +
                        "OPS_EVIDENCE_IN.EVIDENCE_IN_TYPE," +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_CODE," +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_OFFICE_NAME," +
                        "OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "OPS_EVIDENCE_IN_STAFF.TITLE_NAME_TH," +
                        "OPS_EVIDENCE_IN_STAFF.FIRST_NAME," +
                        "OPS_EVIDENCE_IN_STAFF.LAST_NAME," +
                        "OPS_EVIDENCE_IN_STAFF.REPRESENT_POS_NAME," +
                        "OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInventory> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceInventory mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInventory item = new EvidenceInventory();

                item.setEVIDENCE_IN_ITEM_CODE(rs.getString("EVIDENCE_IN_ITEM_CODE"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setBALANCE_QTY(rs.getFloat("BALANCE_QTY"));
                item.setBALANCE_QTY_UNIT(rs.getString("BALANCE_QTY_UNIT"));
                item.setEVIDENCE_IN_TYPE(rs.getInt("EVIDENCE_IN_TYPE"));
                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setREPRESENT_POS_NAME(rs.getString("REPRESENT_POS_NAME"));
                item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
                return item;
            }
        });

        return dataList;

    }
}
