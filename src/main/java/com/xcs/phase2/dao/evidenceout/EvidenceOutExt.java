package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.evidenceout.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EvidenceOutExt {

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutExt.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected String getSequences(String strSql) {
        log.info("[SQL]  : " + strSql);
        return getJdbcTemplate().queryForObject(strSql, String.class);
    }

    protected Boolean EvidenceOutStockBalanceupdByCon(int StockID, float BalanceQty) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_EVIDENCE_STOCK_BALANCE " +
                        "SET BALANCE_QTY = '" + BalanceQty + "' " +
                        "WHERE STOCK_ID = " + StockID);


        log.info("[SQL] EvidenceOutStockBalanceupdByCon : " + sqlBuilder.toString());

        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        return true;
    }

    protected Boolean UpdateIsFinishStockBalance(int StockID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_EVIDENCE_STOCK_BALANCE " +
                        "SET IS_FINISH = '1' " +
                        "WHERE STOCK_ID = " + StockID);


        log.info("[SQL] EvidenceOutStockBalanceupdByCon : " + sqlBuilder.toString());

        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        return true;
    }

    protected List<EvidenceOutInItem> getEvidenceOutInItem(int EVIDENCE_IN_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "EVIDENCE_IN_ITEM_ID," +
                        "EVIDENCE_IN_ITEM_CODE," +
                        "EVIDENCE_IN_ID," +
                        "PRODUCT_MAPPING_ID," +
                        "PRODUCT_CODE," +
                        "PRODUCT_REF_CODE," +
                        "PRODUCT_GROUP_ID," +
                        "PRODUCT_CATEGORY_ID," +
                        "PRODUCT_TYPE_ID," +
                        "PRODUCT_SUBTYPE_ID," +
                        "PRODUCT_SUBSETTYPE_ID," +
                        "PRODUCT_BRAND_ID," +
                        "PRODUCT_SUBBRAND_ID," +
                        "PRODUCT_MODEL_ID," +
                        "PRODUCT_TAXDETAIL_ID," +
                        "PRODUCT_GROUP_CODE," +
                        "PRODUCT_GROUP_NAME," +
                        "PRODUCT_CATEGORY_CODE," +
                        "PRODUCT_CATEGORY_NAME," +
                        "PRODUCT_TYPE_CODE," +
                        "PRODUCT_TYPE_NAME," +
                        "PRODUCT_SUBTYPE_CODE," +
                        "PRODUCT_SUBTYPE_NAME," +
                        "PRODUCT_SUBSETTYPE_CODE," +
                        "PRODUCT_SUBSETTYPE_NAME," +
                        "PRODUCT_BRAND_CODE," +
                        "PRODUCT_BRAND_NAME_TH," +
                        "PRODUCT_BRAND_NAME_EN," +
                        "PRODUCT_SUBBRAND_CODE," +
                        "PRODUCT_SUBBRAND_NAME_TH," +
                        "PRODUCT_SUBBRAND_NAME_EN," +
                        "PRODUCT_MODEL_CODE," +
                        "PRODUCT_MODEL_NAME_TH," +
                        "PRODUCT_MODEL_NAME_EN," +
                        "LICENSE_PLATE," +
                        "ENGINE_NO," +
                        "CHASSIS_NO," +
                        "PRODUCT_DESC," +
                        "SUGAR," +
                        "CO2," +
                        "DEGREE," +
                        "PRICE," +
                        "DELIVERY_QTY," +
                        "DELIVERY_QTY_UNIT," +
                        "DELIVERY_SIZE," +
                        "DELIVERY_SIZE_UNIT," +
                        "DELIVERY_NET_VOLUMN," +
                        "DELIVERY_NET_VOLUMN_UNIT," +
                        "DAMAGE_QTY," +
                        "DAMAGE_QTY_UNIT," +
                        "DAMAGE_SIZE," +
                        "DAMAGE_SIZE_UNIT," +
                        "DAMAGE_NET_VOLUMN," +
                        "DAMAGE_NET_VOLUMN_UNIT," +
                        "IS_DOMESTIC," +
                        "REMARK," +
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_IN_ITEM where EVIDENCE_IN_ID = " + EVIDENCE_IN_ID + " and IS_ACTIVE = 1");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutInItem> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceOutInItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutInItem item = new EvidenceOutInItem();

                item.setEVIDENCE_IN_ITEM_ID(rs.getInt("EVIDENCE_IN_ITEM_ID"));
                item.setEVIDENCE_IN_ITEM_CODE(rs.getString("EVIDENCE_IN_ITEM_CODE"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setPRODUCT_MAPPING_ID(rs.getInt("PRODUCT_MAPPING_ID"));
                item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
                item.setPRODUCT_REF_CODE(rs.getString("PRODUCT_REF_CODE"));
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
                item.setPRODUCT_TYPE_ID(rs.getInt("PRODUCT_TYPE_ID"));
                item.setPRODUCT_SUBTYPE_ID(rs.getInt("PRODUCT_SUBTYPE_ID"));
                item.setPRODUCT_SUBSETTYPE_ID(rs.getInt("PRODUCT_SUBSETTYPE_ID"));
                item.setPRODUCT_BRAND_ID(rs.getInt("PRODUCT_BRAND_ID"));
                item.setPRODUCT_SUBBRAND_ID(rs.getInt("PRODUCT_SUBBRAND_ID"));
                item.setPRODUCT_MODEL_ID(rs.getInt("PRODUCT_MODEL_ID"));
                item.setPRODUCT_TAXDETAIL_ID(rs.getInt("PRODUCT_TAXDETAIL_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getInt("PRODUCT_GROUP_CODE"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
                item.setPRODUCT_CATEGORY_CODE(rs.getInt("PRODUCT_CATEGORY_CODE"));
                item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));
                item.setPRODUCT_TYPE_CODE(rs.getInt("PRODUCT_TYPE_CODE"));
                item.setPRODUCT_TYPE_NAME(rs.getString("PRODUCT_TYPE_NAME"));
                item.setPRODUCT_SUBTYPE_CODE(rs.getInt("PRODUCT_SUBTYPE_CODE"));
                item.setPRODUCT_SUBTYPE_NAME(rs.getString("PRODUCT_SUBTYPE_NAME"));
                item.setPRODUCT_SUBSETTYPE_CODE(rs.getInt("PRODUCT_SUBSETTYPE_CODE"));
                item.setPRODUCT_SUBSETTYPE_NAME(rs.getString("PRODUCT_SUBSETTYPE_NAME"));
                item.setPRODUCT_BRAND_CODE(rs.getInt("PRODUCT_BRAND_CODE"));
                item.setPRODUCT_BRAND_NAME_TH(rs.getString("PRODUCT_BRAND_NAME_TH"));
                item.setPRODUCT_BRAND_NAME_EN(rs.getString("PRODUCT_BRAND_NAME_EN"));
                item.setPRODUCT_SUBBRAND_CODE(rs.getInt("PRODUCT_SUBBRAND_CODE"));
                item.setPRODUCT_SUBBRAND_NAME_TH(rs.getString("PRODUCT_SUBBRAND_NAME_TH"));
                item.setPRODUCT_SUBBRAND_NAME_EN(rs.getString("PRODUCT_SUBBRAND_NAME_EN"));
                item.setPRODUCT_MODEL_CODE(rs.getInt("PRODUCT_MODEL_CODE"));
                item.setPRODUCT_MODEL_NAME_TH(rs.getString("PRODUCT_MODEL_NAME_TH"));
                item.setPRODUCT_MODEL_NAME_EN(rs.getString("PRODUCT_MODEL_NAME_EN"));
                item.setLICENSE_PLATE(rs.getString("LICENSE_PLATE"));
                item.setENGINE_NO(rs.getString("ENGINE_NO"));
                item.setCHASSIS_NO(rs.getString("CHASSIS_NO"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setSUGAR(rs.getFloat("SUGAR"));
                item.setCO2(rs.getFloat("CO2"));
                item.setDEGREE(rs.getFloat("DEGREE"));
                item.setPRICE(rs.getFloat("PRICE"));
                item.setDELIVERY_QTY(rs.getFloat("DELIVERY_QTY"));
                item.setDELIVERY_QTY_UNIT(rs.getString("DELIVERY_QTY_UNIT"));
                item.setDELIVERY_SIZE(rs.getFloat("DELIVERY_SIZE"));
                item.setDELIVERY_SIZE_UNIT(rs.getString("DELIVERY_SIZE_UNIT"));
                item.setDELIVERY_NET_VOLUMN(rs.getFloat("DELIVERY_NET_VOLUMN"));
                item.setDELIVERY_NET_VOLUMN_UNIT(rs.getString("DELIVERY_NET_VOLUMN_UNIT"));
                item.setDAMAGE_QTY(rs.getFloat("DAMAGE_QTY"));
                item.setDAMAGE_QTY_UNIT(rs.getString("DAMAGE_QTY_UNIT"));
                item.setDAMAGE_SIZE(rs.getFloat("DAMAGE_SIZE"));
                item.setDAMAGE_SIZE_UNIT(rs.getString("DAMAGE_SIZE_UNIT"));
                item.setDAMAGE_NET_VOLUMN(rs.getFloat("DAMAGE_NET_VOLUMN"));
                item.setDAMAGE_NET_VOLUMN_UNIT(rs.getString("DAMAGE_NET_VOLUMN_UNIT"));
                item.setIS_DOMESTIC(rs.getInt("IS_DOMESTIC"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setREMARK(rs.getString("REMARK"));
                return item;
            }
        });

        return dataList;

    }

    protected List<EvidenceOutInStaff> getEvidenceOutInStaff(int EVIDENCE_IN_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "EVIDENCE_IN_STAFF_ID," +
                        "EVIDENCE_IN_ID," +
                        "STAFF_REF_ID," +
                        "TITLE_ID," +
                        "STAFF_CODE," +
                        "ID_CARD," +
                        "STAFF_TYPE," +
                        "TITLE_NAME_TH," +
                        "TITLE_NAME_EN," +
                        "TITLE_SHORT_NAME_TH," +
                        "TITLE_SHORT_NAME_EN," +
                        "FIRST_NAME," +
                        "LAST_NAME," +
                        "AGE," +
                        "OPERATION_POS_CODE," +
                        "OPREATION_POS_NAME," +
                        "OPREATION_POS_LEVEL," +
                        "OPERATION_POS_LEVEL_NAME," +
                        "OPERATION_DEPT_CODE," +
                        "OPERATION_DEPT_NAME," +
                        "OPERATION_DEPT_LEVEL," +
                        "OPERATION_UNDER_DEPT_CODE," +
                        "OPERATION_UNDER_DEPT_NAME," +
                        "OPERATION_UNDER_DEPT_LEVEL," +
                        "OPERATION_WORK_DEPT_CODE," +
                        "OPERATION_WORK_DEPT_NAME," +
                        "OPERATION_WORK_DEPT_LEVEL," +
                        "OPERATION_OFFICE_CODE," +
                        "OPERATION_OFFICE_NAME," +
                        "OPERATION_OFFICE_SHORT_NAME," +
                        "MANAGEMENT_POS_CODE," +
                        "MANAGEMENT_POS_NAME," +
                        "MANAGEMENT_POS_LEVEL," +
                        "MANAGEMENT_POS_LEVEL_NAME," +
                        "MANAGEMENT_DEPT_CODE," +
                        "MANAGEMENT_DEPT_NAME," +
                        "MANAGEMENT_DEPT_LEVEL," +
                        "MANAGEMENT_UNDER_DEPT_CODE," +
                        "MANAGEMENT_UNDER_DEPT_NAME," +
                        "MANAGEMENT_UNDER_DEPT_LEVEL," +
                        "MANAGEMENT_WORK_DEPT_CODE," +
                        "MANAGEMENT_WORK_DEPT_NAME," +
                        "MANAGEMENT_WORK_DEPT_LEVEL," +
                        "MANAGEMENT_OFFICE_CODE," +
                        "MANAGEMENT_OFFICE_NAME," +
                        "MANAGEMENT_OFFICE_SHORT_NAME," +
                        "REPRESENT_POS_CODE," +
                        "REPRESENT_POS_NAME," +
                        "REPRESENT_POS_LEVEL," +
                        "REPRESENT_POS_LEVEL_NAME," +
                        "REPRESENT_DEPT_CODE," +
                        "REPRESENT_DEPT_NAME," +
                        "REPRESENT_DEPT_LEVEL," +
                        "REPRESENT_UNDER_DEPT_CODE," +
                        "REPRESENT_UNDER_DEPT_NAME," +
                        "REPRESENT_UNDER_DEPT_LEVEL," +
                        "REPRESENT_WORK_DEPT_CODE," +
                        "REPRESENT_WORK_DEPT_NAME," +
                        "REPRESENT_WORK_DEPT_LEVEL," +
                        "REPRESENT_OFFICE_CODE," +
                        "REPRESENT_OFFICE_NAME," +
                        "REPRESENT_OFFICE_SHORT_NAME," +
                        "STATUS," +
                        "REMARK," +
                        "CONTRIBUTOR_ID," +
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_IN_STAFF where EVIDENCE_IN_ID = " + EVIDENCE_IN_ID + " and IS_ACTIVE = 1");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutInStaff> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceOutInStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutInStaff item = new EvidenceOutInStaff();
                item.setEVIDENCE_IN_STAFF_ID(rs.getInt("EVIDENCE_IN_STAFF_ID"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setSTAFF_REF_ID(rs.getInt("STAFF_REF_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setSTAFF_CODE(rs.getString("STAFF_CODE"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setSTAFF_TYPE(rs.getInt("STAFF_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setAGE(rs.getInt("AGE"));
                item.setOPERATION_POS_CODE(rs.getString("OPERATION_POS_CODE"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setOPREATION_POS_LEVEL(rs.getString("OPREATION_POS_LEVEL"));
                item.setOPERATION_POS_LEVEL_NAME(rs.getString("OPERATION_POS_LEVEL_NAME"));
                item.setOPERATION_DEPT_CODE(rs.getString("OPERATION_DEPT_CODE"));
                item.setOPERATION_DEPT_NAME(rs.getString("OPERATION_DEPT_NAME"));
                item.setOPERATION_DEPT_LEVEL(rs.getInt("OPERATION_DEPT_LEVEL"));
                item.setOPERATION_UNDER_DEPT_CODE(rs.getString("OPERATION_UNDER_DEPT_CODE"));
                item.setOPERATION_UNDER_DEPT_NAME(rs.getString("OPERATION_UNDER_DEPT_NAME"));
                item.setOPERATION_UNDER_DEPT_LEVEL(rs.getInt("OPERATION_UNDER_DEPT_LEVEL"));
                item.setOPERATION_WORK_DEPT_CODE(rs.getString("OPERATION_WORK_DEPT_CODE"));
                item.setOPERATION_WORK_DEPT_NAME(rs.getString("OPERATION_WORK_DEPT_NAME"));
                item.setOPERATION_WORK_DEPT_LEVEL(rs.getInt("OPERATION_WORK_DEPT_LEVEL"));
                item.setOPERATION_OFFICE_CODE(rs.getString("OPERATION_OFFICE_CODE"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                item.setMANAGEMENT_POS_CODE(rs.getString("MANAGEMENT_POS_CODE"));
                item.setMANAGEMENT_POS_NAME(rs.getString("MANAGEMENT_POS_NAME"));
                item.setMANAGEMENT_POS_LEVEL(rs.getString("MANAGEMENT_POS_LEVEL"));
                item.setMANAGEMENT_POS_LEVEL_NAME(rs.getString("MANAGEMENT_POS_LEVEL_NAME"));
                item.setMANAGEMENT_DEPT_CODE(rs.getString("MANAGEMENT_DEPT_CODE"));
                item.setMANAGEMENT_DEPT_NAME(rs.getString("MANAGEMENT_DEPT_NAME"));
                item.setMANAGEMENT_DEPT_LEVEL(rs.getInt("MANAGEMENT_DEPT_LEVEL"));
                item.setMANAGEMENT_UNDER_DEPT_CODE(rs.getString("MANAGEMENT_UNDER_DEPT_CODE"));
                item.setMANAGEMENT_UNDER_DEPT_NAME(rs.getString("MANAGEMENT_UNDER_DEPT_NAME"));
                item.setMANAGEMENT_UNDER_DEPT_LEVEL(rs.getInt("MANAGEMENT_UNDER_DEPT_LEVEL"));
                item.setMANAGEMENT_WORK_DEPT_CODE(rs.getString("MANAGEMENT_WORK_DEPT_CODE"));
                item.setMANAGEMENT_WORK_DEPT_NAME(rs.getString("MANAGEMENT_WORK_DEPT_NAME"));
                item.setMANAGEMENT_WORK_DEPT_LEVEL(rs.getInt("MANAGEMENT_WORK_DEPT_LEVEL"));
                item.setMANAGEMENT_OFFICE_CODE(rs.getString("MANAGEMENT_OFFICE_CODE"));
                item.setMANAGEMENT_OFFICE_NAME(rs.getString("MANAGEMENT_OFFICE_NAME"));
                item.setMANAGEMENT_OFFICE_SHORT_NAME(rs.getString("MANAGEMENT_OFFICE_SHORT_NAME"));
                item.setREPRESENT_POS_CODE(rs.getString("REPRESENT_POS_CODE"));
                item.setREPRESENT_POS_NAME(rs.getString("REPRESENT_POS_NAME"));
                item.setREPRESENT_POS_LEVEL(rs.getString("REPRESENT_POS_LEVEL"));
                item.setREPRESENT_POS_LEVEL_NAME(rs.getString("REPRESENT_POS_LEVEL_NAME"));
                item.setREPRESENT_DEPT_CODE(rs.getString("REPRESENT_DEPT_CODE"));
                item.setREPRESENT_DEPT_NAME(rs.getString("REPRESENT_DEPT_NAME"));
                item.setREPRESENT_DEPT_LEVEL(rs.getInt("REPRESENT_DEPT_LEVEL"));
                item.setREPRESENT_UNDER_DEPT_CODE(rs.getString("REPRESENT_UNDER_DEPT_CODE"));
                item.setREPRESENT_UNDER_DEPT_NAME(rs.getString("REPRESENT_UNDER_DEPT_NAME"));
                item.setREPRESENT_UNDER_DEPT_LEVEL(rs.getInt("REPRESENT_UNDER_DEPT_LEVEL"));
                item.setREPRESENT_WORK_DEPT_CODE(rs.getString("REPRESENT_WORK_DEPT_CODE"));
                item.setREPRESENT_WORK_DEPT_NAME(rs.getString("REPRESENT_WORK_DEPT_NAME"));
                item.setREPRESENT_WORK_DEPT_LEVEL(rs.getInt("REPRESENT_WORK_DEPT_LEVEL"));
                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setREPRESENT_OFFICE_SHORT_NAME(rs.getString("REPRESENT_OFFICE_SHORT_NAME"));
                item.setSTATUS(rs.getInt("STATUS"));
                item.setREMARK(rs.getString("REMARK"));
                item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;

    }

    protected List<EvidenceOutStockBalance> getEvidenceOutStockBalance(int value, String type) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "STOCK_ID," +
                        "WAREHOUSE_ID," +
                        "EVIDENCE_IN_ITEM_ID," +
                        "RECEIVE_QTY," +
                        "RECEIVE_QTY_UNIT," +
                        "RECEIVE_SIZE," +
                        "RECEIVE_SIZE_UNIT," +
                        "RECEIVE_NET_VOLUMN," +
                        "RECEIVE_NET_VOLUMN_UNIT," +
                        "BALANCE_QTY," +
                        "BALANCE_QTY_UNIT," +
                        "BALANCE_SIZE," +
                        "BALANCE_SIZE_UNIT," +
                        "BALANCE_NET_VOLUMN," +
                        "BALANCE_NET_VOLUMN_UNIT," +
                        "IS_FINISH," +
                        "IS_RECEIVE" +
                        " from OPS_EVIDENCE_STOCK_BALANCE ");

        if ("IN".equals(type)) {
            sqlBuilderDetail.append("where EVIDENCE_IN_ITEM_ID =  " + value);
        } else {
            sqlBuilderDetail.append("where STOCK_ID =  " + value);
        }

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutStockBalance> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceOutStockBalance mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutStockBalance item = new EvidenceOutStockBalance();

                item.setSTOCK_ID(rs.getInt("STOCK_ID"));
                item.setWAREHOUSE_ID(rs.getInt("WAREHOUSE_ID"));
                item.setEVIDENCE_IN_ITEM_ID(rs.getInt("EVIDENCE_IN_ITEM_ID"));
                item.setRECEIVE_QTY(rs.getInt("RECEIVE_QTY"));
                item.setRECEIVE_QTY_UNIT(rs.getString("RECEIVE_QTY_UNIT"));
                item.setRECEIVE_SIZE(rs.getFloat("RECEIVE_SIZE"));
                item.setRECEIVE_SIZE_UNIT(rs.getString("RECEIVE_SIZE_UNIT"));
                item.setRECEIVE_NET_VOLUMN(rs.getFloat("RECEIVE_NET_VOLUMN"));
                item.setRECEIVE_NET_VOLUMN_UNIT(rs.getString("RECEIVE_NET_VOLUMN_UNIT"));
                item.setBALANCE_QTY(rs.getFloat("BALANCE_QTY"));
                item.setBALANCE_QTY_UNIT(rs.getString("BALANCE_QTY_UNIT"));
                item.setBALANCE_SIZE(rs.getFloat("BALANCE_SIZE"));
                item.setBALANCE_SIZE_UNIT(rs.getString("BALANCE_SIZE_UNIT"));
                item.setBALANCE_NET_VOLUMN(rs.getFloat("BALANCE_NET_VOLUMN"));
                item.setBALANCE_NET_VOLUMN_UNIT(rs.getString("BALANCE_NET_VOLUMN_UNIT"));
                item.setIS_FINISH(rs.getInt("IS_FINISH"));
                item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));
                return item;
            }
        });

        return dataList;

    }

    protected List<EvidenceOutItem> getEvidenceOutItem(int EVIDENCE_OUT_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "EVIDENCE_OUT_ITEM_ID," +
                        "EVIDENCE_OUT_ID," +
                        "STOCK_ID," +
                        "QTY," +
                        "QTY_UNIT," +
                        "PRODUCT_SIZE," +
                        "PRODUCT_SIZE_UNIT," +
                        "NET_VOLUMN," +
                        "NET_VOLUMN_UNIT," +
                        "IS_RETURN," +
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_OUT_ITEM where IS_ACTIVE = 1 and EVIDENCE_OUT_ID = '" + EVIDENCE_OUT_ID + "' ");



        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutItem> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceOutItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutItem item = new EvidenceOutItem();

                item.setEVIDENCE_OUT_ITEM_ID(rs.getInt("EVIDENCE_OUT_ITEM_ID"));
                item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
                item.setSTOCK_ID(rs.getInt("STOCK_ID"));
                item.setQTY(rs.getFloat("QTY"));
                item.setQTY_UNIT(rs.getString("QTY_UNIT"));
                item.setPRODUCT_SIZE(rs.getInt("PRODUCT_SIZE"));
                item.setPRODUCT_SIZE_UNIT(rs.getString("PRODUCT_SIZE_UNIT"));
                item.setNET_VOLUMN(rs.getFloat("NET_VOLUMN"));
                item.setNET_VOLUMN_UNIT(rs.getString("NET_VOLUMN_UNIT"));
                item.setIS_RETURN(rs.getInt("IS_RETURN"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setEvidenceOutStockBalance(getEvidenceOutStockBalance(rs.getInt("STOCK_ID"),"OUT"));

                return item;
            }
        });

        return dataList;

    }

    protected List<EvidenceOutDetail> getEvidenceOutDetail(int EVIDENCE_OUT_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "EVIDENCE_OUT_DETAIL_ID," +
                        "EVIDENCE_OUT_ID," +
                        "EVIDENCE_IN_ID," +
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_OUT_DETAIL where IS_ACTIVE = 1 and EVIDENCE_OUT_ID = '" + EVIDENCE_OUT_ID + "' ");


        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutDetail> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceOutDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutDetail item = new EvidenceOutDetail();

                item.setEVIDENCE_OUT_DETAIL_ID(rs.getInt("EVIDENCE_OUT_DETAIL_ID"));
                item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setEvidenceOutIn(getEvidenceOutIn(rs.getInt("EVIDENCE_IN_ID")));

                return item;
            }
        });

        return dataList;

    }

    protected EvidenceOutIn getEvidenceOutIn(int EVIDENCE_IN_ID) {


        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "EVIDENCE_IN_ID," +
                        "PROVE_ID," +
                        "ARREST_ID," +
                        "DELIVERY_CODE," +
                        "DELIVERY_NO," +
                        "DELIVERY_DATE," +
                        "DELIVERY_OFFICE_CODE," +
                        "DELIVERY_OFFICE_NAME," +
                        "DELIVERY_TITTLE," +
                        "DELIVERY_DEAR," +
                        "RETURN_DATE," +
                        "REMARK," +
                        "DELIVERY_TYPE," +
                        "EVIDENCE_IN_CODE," +
                        "EVIDENCE_IN_DATE," +
                        "RECEIVE_OFFICE_CODE," +
                        "RECEIVE_OFFICE_NAME," +
                        "COMMENT1," +
                        "IS_RECEIVE," +
                        "EVIDENCE_IN_TYPE," +
                        "IS_EDIT," +
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_IN where IS_ACTIVE = 1 and EVIDENCE_IN_ID = '" + EVIDENCE_IN_ID + "' ");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        return getJdbcTemplate().query(sqlBuilderDetail.toString(), new ResultSetExtractor<EvidenceOutIn>() {

            public EvidenceOutIn extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceOutIn item = new EvidenceOutIn();
                    item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                    item.setPROVE_ID(rs.getInt("PROVE_ID"));
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setDELIVERY_CODE(rs.getString("DELIVERY_CODE"));
                    item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                    item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                    item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                    item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                    item.setDELIVERY_TITTLE(rs.getString("DELIVERY_TITTLE"));
                    item.setDELIVERY_DEAR(rs.getString("DELIVERY_DEAR"));
                    item.setRETURN_DATE(rs.getString("RETURN_DATE"));
                    item.setREMARK(rs.getString("REMARK"));
                    item.setDELIVERY_TYPE(rs.getInt("DELIVERY_TYPE"));
                    item.setEVIDENCE_IN_CODE(rs.getString("EVIDENCE_IN_CODE"));
                    item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                    item.setRECEIVE_OFFICE_CODE(rs.getInt("RECEIVE_OFFICE_CODE"));
                    item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                    item.setCOMMENT1(rs.getString("COMMENT1"));
                    item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));
                    item.setEVIDENCE_IN_TYPE(rs.getInt("EVIDENCE_IN_TYPE"));
                    item.setIS_EDIT(rs.getInt("IS_EDIT"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));


                    //item.setEvidenceOutProve(getEvidenceOutProve(rs.getInt("PROVE_ID")));
                    item.setEvidenceOutInItem(getEvidenceOutInItem(rs.getInt("EVIDENCE_IN_ID")));
                    //item.setEvidenceOutInStaff(getEvidenceOutInStaff(rs.getInt("EVIDENCE_IN_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    protected EvidenceOutProve getEvidenceOutProve(int PROVE_ID) {


        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "PROVE_ID," +
                        "LAWSUIT_ID," +
                        "DELIVERY_OFFICE_ID," +
                        "RECEIVE_OFFICE_ID," +
                        "PROVE_TYPE," +
                        "DELIVERY_DOC_NO_1," +
                        "DELIVERY_DOC_NO_2," +
                        "to_char(DELIVERY_DOC_DATE,'" + Pattern.FORMAT_DATETIME + "') as DELIVERY_DOC_DATE, " +
                        "DELIVERY_OFFICE_CODE," +
                        "DELIVERY_OFFICE_NAME," +
                        "RECEIVE_OFFICE_CODE," +
                        "RECEIVE_OFFICE_NAME," +
                        "PROVE_NO," +
                        "to_char(PROVE_NO_YEAR,'" + Pattern.FORMAT_DATETIME + "') as PROVE_NO_YEAR, " +
                        "to_char(RECEIVE_DOC_DATE,'" + Pattern.FORMAT_DATETIME + "') as RECEIVE_DOC_DATE, " +
                        "COMMAND," +
                        "LAWSUIT_NO," +
                        "to_char(LAWSUIT_NO_YEAR,'" + Pattern.FORMAT_DATETIME + "') as LAWSUIT_NO_YEAR, " +
                        "to_char(OCCURRENCE_DATE,'" + Pattern.FORMAT_DATETIME + "') as OCCURRENCE_DATE, " +
                        "OUT_OFFICE_NAME," +
                        "IS_OUTSIDE," +
                        "IS_SCIENCE," +
                        "IS_RECEIVE," +
                        "IS_ACTIVE," +
                        "to_char(PROVE_DATE,'" + Pattern.FORMAT_DATETIME + "') as PROVE_DATE, " +
                        "DELIVERY_PROVE_DOC_NO_1," +
                        "DELIVERY_PROVE_DOC_NO_2" +
                        " from OPS_PROVE where IS_ACTIVE = 1 and PROVE_ID = '" + PROVE_ID + "' ");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        return getJdbcTemplate().query(sqlBuilderDetail.toString(), new ResultSetExtractor<EvidenceOutProve>() {

            public EvidenceOutProve extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceOutProve item = new EvidenceOutProve();
                    item.setPROVE_ID(rs.getInt("PROVE_ID"));
                    item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                    item.setDELIVERY_OFFICE_ID(rs.getInt("DELIVERY_OFFICE_ID"));
                    item.setRECEIVE_OFFICE_ID(rs.getInt("RECEIVE_OFFICE_ID"));
                    item.setPROVE_TYPE(rs.getInt("PROVE_TYPE"));
                    item.setDELIVERY_DOC_NO_1(rs.getString("DELIVERY_DOC_NO_1"));
                    item.setDELIVERY_DOC_NO_2(rs.getString("DELIVERY_DOC_NO_2"));
                    item.setDELIVERY_DOC_DATE(rs.getString("DELIVERY_DOC_DATE"));
                    item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                    item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                    item.setRECEIVE_OFFICE_CODE(rs.getString("RECEIVE_OFFICE_CODE"));
                    item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                    item.setPROVE_NO(rs.getString("PROVE_NO"));
                    item.setPROVE_NO_YEAR(rs.getString("PROVE_NO_YEAR"));
                    item.setRECEIVE_DOC_DATE(rs.getString("RECEIVE_DOC_DATE"));
                    item.setCOMMAND(rs.getString("COMMAND"));
                    item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                    item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                    item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                    item.setOUT_OFFICE_NAME(rs.getString("OUT_OFFICE_NAME"));
                    item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                    item.setIS_SCIENCE(rs.getInt("IS_SCIENCE"));
                    item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setPROVE_DATE(rs.getString("PROVE_DATE"));
                    item.setDELIVERY_PROVE_DOC_NO_1(rs.getString("DELIVERY_PROVE_DOC_NO_1"));
                    item.setDELIVERY_PROVE_DOC_NO_2(rs.getString("DELIVERY_PROVE_DOC_NO_2"));

                    item.setEvidenceOutLawsuit(getEvidenceOutLawsuit(rs.getInt("LAWSUIT_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    private EvidenceOutLawsuit getEvidenceOutLawsuit(int LAWSUIT_ID) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "LAWSUIT_ID," +
                        "INDICTMENT_ID," +
                        "OFFICE_ID," +
                        "OFFICE_CODE," +
                        "OFFICE_NAME," +
                        "IS_LAWSUIT," +
                        "REMARK_NOT_LAWSUIT," +
                        "LAWSUIT_NO," +
                        "to_char(LAWSUIT_NO_YEAR,'" + Pattern.FORMAT_DATETIME + "') as LAWSUIT_NO_YEAR," +
                        "to_char(LAWSUIT_DATE,'" + Pattern.FORMAT_DATETIME + "') as LAWSUIT_DATE," +
                        "TESTIMONY," +
                        "DELIVERY_DOC_NO_1," +
                        "DELIVERY_DOC_NO_2," +
                        "to_char(DELIVERY_DOC_DATE,'" + Pattern.FORMAT_DATETIME + "') as DELIVERY_DOC_DATE," +
                        "IS_OUTSIDE," +
                        "IS_SEIZE," +
                        "IS_ACTIVE " +
                        "from OPS_LAWSUIT  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and LAWSUIT_ID = '" + LAWSUIT_ID + "'");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceOutLawsuit>() {

            public EvidenceOutLawsuit extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceOutLawsuit item = new EvidenceOutLawsuit();
                    item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                    item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                    item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                    item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                    item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                    item.setIS_LAWSUIT(rs.getInt("IS_LAWSUIT"));
                    item.setREMARK_NOT_LAWSUIT(rs.getString("REMARK_NOT_LAWSUIT"));
                    item.setLAWSUIT_NO(rs.getInt("LAWSUIT_NO"));
                    item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                    item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                    item.setTESTIMONY(rs.getString("TESTIMONY"));
                    item.setDELIVERY_DOC_NO_1(rs.getString("DELIVERY_DOC_NO_1"));
                    item.setDELIVERY_DOC_NO_2(rs.getString("DELIVERY_DOC_NO_2"));
                    item.setDELIVERY_DOC_DATE(rs.getString("DELIVERY_DOC_DATE"));
                    item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                    item.setIS_SEIZE(rs.getInt("IS_SEIZE"));

                    item.setEvidenceOutArrest(getEvidenceOutArrest(rs.getInt("INDICTMENT_ID")));
                    item.setEvidenceOutGuiltbase(getEvidenceOutGuiltbase(rs.getInt("INDICTMENT_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    private EvidenceOutArrest getEvidenceOutArrest(int INDICTMENT_ID) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "ARREST_ID," +
                        "OFFICE_ID," +
                        "ARREST_CODE," +
                        "OFFICE_CODE," +
                        "OFFICE_NAME," +
                        "to_char(ARREST_DATE,'" + Pattern.FORMAT_DATETIME + "') as ARREST_DATE," +
                        "to_char(OCCURRENCE_DATE,'" + Pattern.FORMAT_DATETIME + "') as OCCURRENCE_DATE," +
                        "BEHAVIOR_1," +
                        "BEHAVIOR_2," +
                        "BEHAVIOR_3," +
                        "BEHAVIOR_4," +
                        "BEHAVIOR_5," +
                        "TESTIMONY," +
                        "IS_REQUEST," +
                        "REQUEST_DESC," +
                        "IS_LAWSUIT_COMPLETE" +
                        " from OPS_ARREST  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and ARREST_ID =  (select ARREST_ID from OPS_ARREST_INDICTMENT where INDICTMENT_ID = '"+INDICTMENT_ID+"' ) ");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceOutArrest>() {

            public EvidenceOutArrest extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceOutArrest item = new EvidenceOutArrest();
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                    item.setARREST_CODE(rs.getString("ARREST_CODE"));
                    item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                    item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                    item.setARREST_DATE(rs.getString("ARREST_DATE"));
                    item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                    item.setBEHAVIOR_1(rs.getString("BEHAVIOR_1"));
                    item.setBEHAVIOR_2(rs.getString("BEHAVIOR_2"));
                    item.setBEHAVIOR_3(rs.getString("BEHAVIOR_3"));
                    item.setBEHAVIOR_4(rs.getString("BEHAVIOR_4"));
                    item.setBEHAVIOR_5(rs.getString("BEHAVIOR_5"));
                    item.setTESTIMONY(rs.getString("TESTIMONY"));
                    item.setIS_REQUEST(rs.getInt("IS_REQUEST"));
                    item.setREQUEST_DESC(rs.getString("REQUEST_DESC"));
                    item.setIS_LAWSUIT_COMPLETE(rs.getInt("IS_LAWSUIT_COMPLETE"));

                    return item;
                }

                return null;
            }
        });
    }

    private EvidenceOutGuiltbase getEvidenceOutGuiltbase(int INDICTMENT_ID) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "GUILTBASE_ID," +
                        "SUBSECTION_RULE_ID," +
                        "GUILTBASE_NAME," +
                        "FINE," +
                        "IS_PROVE," +
                        "IS_COMPARE," +
                        "REMARK," +
                        "IS_ACTIVE" +
                        " from MAS_LAW_GUILTBASE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and GUILTBASE_ID =  (select GUILTBASE_ID from OPS_ARREST_INDICTMENT where INDICTMENT_ID = '"+INDICTMENT_ID+"' ) ");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceOutGuiltbase>() {

            public EvidenceOutGuiltbase extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceOutGuiltbase item = new EvidenceOutGuiltbase();
                    item.setGUILTBASE_ID(rs.getInt("GUILTBASE_ID"));
                    item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                    item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                    item.setFINE(rs.getString("FINE"));
                    item.setIS_PROVE(rs.getInt("IS_PROVE"));
                    item.setIS_COMPARE(rs.getInt("IS_COMPARE"));
                    item.setREMARK(rs.getString("REMARK"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setEvidenceOutGroupSubsectionRule(getEvidenceOutGroupSubsectionRule(rs.getInt("SUBSECTION_RULE_ID")));
                    return item;
                }

                return null;
            }
        });
    }

    private EvidenceOutGroupSubsectionRule getEvidenceOutGroupSubsectionRule(int SUBSECTION_RULE_ID) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "SUBSECTION_RULE_ID," +
                        "SUBSECTION_ID," +
                        "SECTION_ID," +
                        "FINE_TYPE," +
                        "IS_ACTIVE" +
                        " from MAS_LAW_GROUP_SUBSECTION_RULE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and SUBSECTION_RULE_ID =  '"+SUBSECTION_RULE_ID+"'  ");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceOutGroupSubsectionRule>() {

            public EvidenceOutGroupSubsectionRule extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceOutGroupSubsectionRule item = new EvidenceOutGroupSubsectionRule();
                    item.setSUBSECTION_RULE_ID(rs.getInt("SUBSECTION_RULE_ID"));
                    item.setSUBSECTION_ID(rs.getInt("SUBSECTION_ID"));
                    item.setSECTION_ID(rs.getInt("SECTION_ID"));
                    item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setEvidenceOutGroupSubsection(getEvidenceOutGroupSubsection(rs.getInt("SUBSECTION_ID")));
                    return item;
                }

                return null;
            }
        });
    }

    private EvidenceOutGroupSubsection getEvidenceOutGroupSubsection(int SUBSECTION_ID) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "SUBSECTION_ID," +
                        "SECTION_ID," +
                        "SUBSECTION_NO," +
                        "SUBSECTION_NAME," +
                        "SUBSECTION_DESC," +
                        "IS_ACTIVE" +
                        " from MAS_LAW_GROUP_SUBSECTION  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and SUBSECTION_ID =  '"+SUBSECTION_ID+"'  ");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceOutGroupSubsection>() {

            public EvidenceOutGroupSubsection extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceOutGroupSubsection item = new EvidenceOutGroupSubsection();
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

    protected List<EvidenceOutStaff> getEvidenceOutStaff(int EVIDENCE_OUT_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "EVIDENCE_OUT_STAFF_ID," +
                        "EVIDENCE_OUT_ID," +
                        "STAFF_REF_ID," +
                        "TITLE_ID," +
                        "STAFF_CODE," +
                        "ID_CARD," +
                        "STAFF_TYPE," +
                        "TITLE_NAME_TH," +
                        "TITLE_NAME_EN," +
                        "TITLE_SHORT_NAME_TH," +
                        "TITLE_SHORT_NAME_EN," +
                        "FIRST_NAME," +
                        "LAST_NAME," +
                        "AGE," +
                        "OPERATION_POS_CODE," +
                        "OPREATION_POS_NAME," +
                        "OPREATION_POS_LEVEL," +
                        "OPERATION_POS_LEVEL_NAME," +
                        "OPERATION_DEPT_CODE," +
                        "OPERATION_DEPT_NAME," +
                        "OPERATION_DEPT_LEVEL," +
                        "OPERATION_UNDER_DEPT_CODE," +
                        "OPERATION_UNDER_DEPT_NAME," +
                        "OPERATION_UNDER_DEPT_LEVEL," +
                        "OPERATION_WORK_DEPT_CODE," +
                        "OPERATION_WORK_DEPT_NAME," +
                        "OPERATION_WORK_DEPT_LEVEL," +
                        "OPERATION_OFFICE_CODE," +
                        "OPERATION_OFFICE_NAME," +
                        "OPERATION_OFFICE_SHORT_NAME," +
                        "MANAGEMENT_POS_CODE," +
                        "MANAGEMENT_POS_NAME," +
                        "MANAGEMENT_POS_LEVEL," +
                        "MANAGEMENT_POS_LEVEL_NAME," +
                        "MANAGEMENT_DEPT_CODE," +
                        "MANAGEMENT_DEPT_NAME," +
                        "MANAGEMENT_DEPT_LEVEL," +
                        "MANAGEMENT_UNDER_DEPT_CODE," +
                        "MANAGEMENT_UNDER_DEPT_NAME," +
                        "MANAGEMENT_UNDER_DEPT_LEVEL," +
                        "MANAGEMENT_WORK_DEPT_CODE," +
                        "MANAGEMENT_WORK_DEPT_NAME," +
                        "MANAGEMENT_WORK_DEPT_LEVEL," +
                        "MANAGEMENT_OFFICE_CODE," +
                        "MANAGEMENT_OFFICE_NAME," +
                        "MANAGEMENT_OFFICE_SHORT_NAME," +
                        "REPRESENT_POS_CODE," +
                        "REPRESENT_POS_NAME," +
                        "REPRESENT_POS_LEVEL," +
                        "REPRESENT_POS_LEVEL_NAME," +
                        "REPRESENT_DEPT_CODE," +
                        "REPRESENT_DEPT_NAME," +
                        "REPRESENT_DEPT_LEVEL," +
                        "REPRESENT_UNDER_DEPT_CODE," +
                        "REPRESENT_UNDER_DEPT_NAME," +
                        "REPRESENT_UNDER_DEPT_LEVEL," +
                        "REPRESENT_WORK_DEPT_CODE," +
                        "REPRESENT_WORK_DEPT_NAME," +
                        "REPRESENT_WORK_DEPT_LEVEL," +
                        "REPRESENT_OFFICE_CODE," +
                        "REPRESENT_OFFICE_NAME," +
                        "REPRESENT_OFFICE_SHORT_NAME," +
                        "STATUS," +
                        "REMARK," +
                        "CONTRIBUTOR_ID," +
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_OUT_STAFF where EVIDENCE_OUT_ID = " + EVIDENCE_OUT_ID + " and IS_ACTIVE = 1 and CONTRIBUTOR_ID = 77");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutStaff> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceOutStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutStaff item = new EvidenceOutStaff();
                item.setEVIDENCE_OUT_STAFF_ID(rs.getInt("EVIDENCE_OUT_STAFF_ID"));
                item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
                item.setSTAFF_REF_ID(rs.getInt("STAFF_REF_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setSTAFF_CODE(rs.getString("STAFF_CODE"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setSTAFF_TYPE(rs.getInt("STAFF_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setAGE(rs.getInt("AGE"));
                item.setOPERATION_POS_CODE(rs.getString("OPERATION_POS_CODE"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setOPREATION_POS_LEVEL(rs.getString("OPREATION_POS_LEVEL"));
                item.setOPERATION_POS_LEVEL_NAME(rs.getString("OPERATION_POS_LEVEL_NAME"));
                item.setOPERATION_DEPT_CODE(rs.getString("OPERATION_DEPT_CODE"));
                item.setOPERATION_DEPT_NAME(rs.getString("OPERATION_DEPT_NAME"));
                item.setOPERATION_DEPT_LEVEL(rs.getInt("OPERATION_DEPT_LEVEL"));
                item.setOPERATION_UNDER_DEPT_CODE(rs.getString("OPERATION_UNDER_DEPT_CODE"));
                item.setOPERATION_UNDER_DEPT_NAME(rs.getString("OPERATION_UNDER_DEPT_NAME"));
                item.setOPERATION_UNDER_DEPT_LEVEL(rs.getInt("OPERATION_UNDER_DEPT_LEVEL"));
                item.setOPERATION_WORK_DEPT_CODE(rs.getString("OPERATION_WORK_DEPT_CODE"));
                item.setOPERATION_WORK_DEPT_NAME(rs.getString("OPERATION_WORK_DEPT_NAME"));
                item.setOPERATION_WORK_DEPT_LEVEL(rs.getInt("OPERATION_WORK_DEPT_LEVEL"));
                item.setOPERATION_OFFICE_CODE(rs.getString("OPERATION_OFFICE_CODE"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                item.setMANAGEMENT_POS_CODE(rs.getString("MANAGEMENT_POS_CODE"));
                item.setMANAGEMENT_POS_NAME(rs.getString("MANAGEMENT_POS_NAME"));
                item.setMANAGEMENT_POS_LEVEL(rs.getString("MANAGEMENT_POS_LEVEL"));
                item.setMANAGEMENT_POS_LEVEL_NAME(rs.getString("MANAGEMENT_POS_LEVEL_NAME"));
                item.setMANAGEMENT_DEPT_CODE(rs.getString("MANAGEMENT_DEPT_CODE"));
                item.setMANAGEMENT_DEPT_NAME(rs.getString("MANAGEMENT_DEPT_NAME"));
                item.setMANAGEMENT_DEPT_LEVEL(rs.getInt("MANAGEMENT_DEPT_LEVEL"));
                item.setMANAGEMENT_UNDER_DEPT_CODE(rs.getString("MANAGEMENT_UNDER_DEPT_CODE"));
                item.setMANAGEMENT_UNDER_DEPT_NAME(rs.getString("MANAGEMENT_UNDER_DEPT_NAME"));
                item.setMANAGEMENT_UNDER_DEPT_LEVEL(rs.getInt("MANAGEMENT_UNDER_DEPT_LEVEL"));
                item.setMANAGEMENT_WORK_DEPT_CODE(rs.getString("MANAGEMENT_WORK_DEPT_CODE"));
                item.setMANAGEMENT_WORK_DEPT_NAME(rs.getString("MANAGEMENT_WORK_DEPT_NAME"));
                item.setMANAGEMENT_WORK_DEPT_LEVEL(rs.getInt("MANAGEMENT_WORK_DEPT_LEVEL"));
                item.setMANAGEMENT_OFFICE_CODE(rs.getString("MANAGEMENT_OFFICE_CODE"));
                item.setMANAGEMENT_OFFICE_NAME(rs.getString("MANAGEMENT_OFFICE_NAME"));
                item.setMANAGEMENT_OFFICE_SHORT_NAME(rs.getString("MANAGEMENT_OFFICE_SHORT_NAME"));
                item.setREPRESENT_POS_CODE(rs.getString("REPRESENT_POS_CODE"));
                item.setREPRESENT_POS_NAME(rs.getString("REPRESENT_POS_NAME"));
                item.setREPRESENT_POS_LEVEL(rs.getString("REPRESENT_POS_LEVEL"));
                item.setREPRESENT_POS_LEVEL_NAME(rs.getString("REPRESENT_POS_LEVEL_NAME"));
                item.setREPRESENT_DEPT_CODE(rs.getString("REPRESENT_DEPT_CODE"));
                item.setREPRESENT_DEPT_NAME(rs.getString("REPRESENT_DEPT_NAME"));
                item.setREPRESENT_DEPT_LEVEL(rs.getInt("REPRESENT_DEPT_LEVEL"));
                item.setREPRESENT_UNDER_DEPT_CODE(rs.getString("REPRESENT_UNDER_DEPT_CODE"));
                item.setREPRESENT_UNDER_DEPT_NAME(rs.getString("REPRESENT_UNDER_DEPT_NAME"));
                item.setREPRESENT_UNDER_DEPT_LEVEL(rs.getInt("REPRESENT_UNDER_DEPT_LEVEL"));
                item.setREPRESENT_WORK_DEPT_CODE(rs.getString("REPRESENT_WORK_DEPT_CODE"));
                item.setREPRESENT_WORK_DEPT_NAME(rs.getString("REPRESENT_WORK_DEPT_NAME"));
                item.setREPRESENT_WORK_DEPT_LEVEL(rs.getInt("REPRESENT_WORK_DEPT_LEVEL"));
                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setREPRESENT_OFFICE_SHORT_NAME(rs.getString("REPRESENT_OFFICE_SHORT_NAME"));
                item.setSTATUS(rs.getInt("STATUS"));
                item.setREMARK(rs.getString("REMARK"));
                item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;

    }

    protected List<EvidenceOutStaff> getEvidenceOutStaffAll(int EVIDENCE_OUT_ID) {

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("select " +
                        "EVIDENCE_OUT_STAFF_ID," +
                        "EVIDENCE_OUT_ID," +
                        "STAFF_REF_ID," +
                        "TITLE_ID," +
                        "STAFF_CODE," +
                        "ID_CARD," +
                        "STAFF_TYPE," +
                        "TITLE_NAME_TH," +
                        "TITLE_NAME_EN," +
                        "TITLE_SHORT_NAME_TH," +
                        "TITLE_SHORT_NAME_EN," +
                        "FIRST_NAME," +
                        "LAST_NAME," +
                        "AGE," +
                        "OPERATION_POS_CODE," +
                        "OPREATION_POS_NAME," +
                        "OPREATION_POS_LEVEL," +
                        "OPERATION_POS_LEVEL_NAME," +
                        "OPERATION_DEPT_CODE," +
                        "OPERATION_DEPT_NAME," +
                        "OPERATION_DEPT_LEVEL," +
                        "OPERATION_UNDER_DEPT_CODE," +
                        "OPERATION_UNDER_DEPT_NAME," +
                        "OPERATION_UNDER_DEPT_LEVEL," +
                        "OPERATION_WORK_DEPT_CODE," +
                        "OPERATION_WORK_DEPT_NAME," +
                        "OPERATION_WORK_DEPT_LEVEL," +
                        "OPERATION_OFFICE_CODE," +
                        "OPERATION_OFFICE_NAME," +
                        "OPERATION_OFFICE_SHORT_NAME," +
                        "MANAGEMENT_POS_CODE," +
                        "MANAGEMENT_POS_NAME," +
                        "MANAGEMENT_POS_LEVEL," +
                        "MANAGEMENT_POS_LEVEL_NAME," +
                        "MANAGEMENT_DEPT_CODE," +
                        "MANAGEMENT_DEPT_NAME," +
                        "MANAGEMENT_DEPT_LEVEL," +
                        "MANAGEMENT_UNDER_DEPT_CODE," +
                        "MANAGEMENT_UNDER_DEPT_NAME," +
                        "MANAGEMENT_UNDER_DEPT_LEVEL," +
                        "MANAGEMENT_WORK_DEPT_CODE," +
                        "MANAGEMENT_WORK_DEPT_NAME," +
                        "MANAGEMENT_WORK_DEPT_LEVEL," +
                        "MANAGEMENT_OFFICE_CODE," +
                        "MANAGEMENT_OFFICE_NAME," +
                        "MANAGEMENT_OFFICE_SHORT_NAME," +
                        "REPRESENT_POS_CODE," +
                        "REPRESENT_POS_NAME," +
                        "REPRESENT_POS_LEVEL," +
                        "REPRESENT_POS_LEVEL_NAME," +
                        "REPRESENT_DEPT_CODE," +
                        "REPRESENT_DEPT_NAME," +
                        "REPRESENT_DEPT_LEVEL," +
                        "REPRESENT_UNDER_DEPT_CODE," +
                        "REPRESENT_UNDER_DEPT_NAME," +
                        "REPRESENT_UNDER_DEPT_LEVEL," +
                        "REPRESENT_WORK_DEPT_CODE," +
                        "REPRESENT_WORK_DEPT_NAME," +
                        "REPRESENT_WORK_DEPT_LEVEL," +
                        "REPRESENT_OFFICE_CODE," +
                        "REPRESENT_OFFICE_NAME," +
                        "REPRESENT_OFFICE_SHORT_NAME," +
                        "STATUS," +
                        "REMARK," +
                        "CONTRIBUTOR_ID," +
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_OUT_STAFF where EVIDENCE_OUT_ID = " + EVIDENCE_OUT_ID + " and IS_ACTIVE = 1");

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceOutStaff> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceOutStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceOutStaff item = new EvidenceOutStaff();
                item.setEVIDENCE_OUT_STAFF_ID(rs.getInt("EVIDENCE_OUT_STAFF_ID"));
                item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
                item.setSTAFF_REF_ID(rs.getInt("STAFF_REF_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setSTAFF_CODE(rs.getString("STAFF_CODE"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setSTAFF_TYPE(rs.getInt("STAFF_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setAGE(rs.getInt("AGE"));
                item.setOPERATION_POS_CODE(rs.getString("OPERATION_POS_CODE"));
                item.setOPREATION_POS_NAME(rs.getString("OPREATION_POS_NAME"));
                item.setOPREATION_POS_LEVEL(rs.getString("OPREATION_POS_LEVEL"));
                item.setOPERATION_POS_LEVEL_NAME(rs.getString("OPERATION_POS_LEVEL_NAME"));
                item.setOPERATION_DEPT_CODE(rs.getString("OPERATION_DEPT_CODE"));
                item.setOPERATION_DEPT_NAME(rs.getString("OPERATION_DEPT_NAME"));
                item.setOPERATION_DEPT_LEVEL(rs.getInt("OPERATION_DEPT_LEVEL"));
                item.setOPERATION_UNDER_DEPT_CODE(rs.getString("OPERATION_UNDER_DEPT_CODE"));
                item.setOPERATION_UNDER_DEPT_NAME(rs.getString("OPERATION_UNDER_DEPT_NAME"));
                item.setOPERATION_UNDER_DEPT_LEVEL(rs.getInt("OPERATION_UNDER_DEPT_LEVEL"));
                item.setOPERATION_WORK_DEPT_CODE(rs.getString("OPERATION_WORK_DEPT_CODE"));
                item.setOPERATION_WORK_DEPT_NAME(rs.getString("OPERATION_WORK_DEPT_NAME"));
                item.setOPERATION_WORK_DEPT_LEVEL(rs.getInt("OPERATION_WORK_DEPT_LEVEL"));
                item.setOPERATION_OFFICE_CODE(rs.getString("OPERATION_OFFICE_CODE"));
                item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                item.setMANAGEMENT_POS_CODE(rs.getString("MANAGEMENT_POS_CODE"));
                item.setMANAGEMENT_POS_NAME(rs.getString("MANAGEMENT_POS_NAME"));
                item.setMANAGEMENT_POS_LEVEL(rs.getString("MANAGEMENT_POS_LEVEL"));
                item.setMANAGEMENT_POS_LEVEL_NAME(rs.getString("MANAGEMENT_POS_LEVEL_NAME"));
                item.setMANAGEMENT_DEPT_CODE(rs.getString("MANAGEMENT_DEPT_CODE"));
                item.setMANAGEMENT_DEPT_NAME(rs.getString("MANAGEMENT_DEPT_NAME"));
                item.setMANAGEMENT_DEPT_LEVEL(rs.getInt("MANAGEMENT_DEPT_LEVEL"));
                item.setMANAGEMENT_UNDER_DEPT_CODE(rs.getString("MANAGEMENT_UNDER_DEPT_CODE"));
                item.setMANAGEMENT_UNDER_DEPT_NAME(rs.getString("MANAGEMENT_UNDER_DEPT_NAME"));
                item.setMANAGEMENT_UNDER_DEPT_LEVEL(rs.getInt("MANAGEMENT_UNDER_DEPT_LEVEL"));
                item.setMANAGEMENT_WORK_DEPT_CODE(rs.getString("MANAGEMENT_WORK_DEPT_CODE"));
                item.setMANAGEMENT_WORK_DEPT_NAME(rs.getString("MANAGEMENT_WORK_DEPT_NAME"));
                item.setMANAGEMENT_WORK_DEPT_LEVEL(rs.getInt("MANAGEMENT_WORK_DEPT_LEVEL"));
                item.setMANAGEMENT_OFFICE_CODE(rs.getString("MANAGEMENT_OFFICE_CODE"));
                item.setMANAGEMENT_OFFICE_NAME(rs.getString("MANAGEMENT_OFFICE_NAME"));
                item.setMANAGEMENT_OFFICE_SHORT_NAME(rs.getString("MANAGEMENT_OFFICE_SHORT_NAME"));
                item.setREPRESENT_POS_CODE(rs.getString("REPRESENT_POS_CODE"));
                item.setREPRESENT_POS_NAME(rs.getString("REPRESENT_POS_NAME"));
                item.setREPRESENT_POS_LEVEL(rs.getString("REPRESENT_POS_LEVEL"));
                item.setREPRESENT_POS_LEVEL_NAME(rs.getString("REPRESENT_POS_LEVEL_NAME"));
                item.setREPRESENT_DEPT_CODE(rs.getString("REPRESENT_DEPT_CODE"));
                item.setREPRESENT_DEPT_NAME(rs.getString("REPRESENT_DEPT_NAME"));
                item.setREPRESENT_DEPT_LEVEL(rs.getInt("REPRESENT_DEPT_LEVEL"));
                item.setREPRESENT_UNDER_DEPT_CODE(rs.getString("REPRESENT_UNDER_DEPT_CODE"));
                item.setREPRESENT_UNDER_DEPT_NAME(rs.getString("REPRESENT_UNDER_DEPT_NAME"));
                item.setREPRESENT_UNDER_DEPT_LEVEL(rs.getInt("REPRESENT_UNDER_DEPT_LEVEL"));
                item.setREPRESENT_WORK_DEPT_CODE(rs.getString("REPRESENT_WORK_DEPT_CODE"));
                item.setREPRESENT_WORK_DEPT_NAME(rs.getString("REPRESENT_WORK_DEPT_NAME"));
                item.setREPRESENT_WORK_DEPT_LEVEL(rs.getInt("REPRESENT_WORK_DEPT_LEVEL"));
                item.setREPRESENT_OFFICE_CODE(rs.getString("REPRESENT_OFFICE_CODE"));
                item.setREPRESENT_OFFICE_NAME(rs.getString("REPRESENT_OFFICE_NAME"));
                item.setREPRESENT_OFFICE_SHORT_NAME(rs.getString("REPRESENT_OFFICE_SHORT_NAME"));
                item.setSTATUS(rs.getInt("STATUS"));
                item.setREMARK(rs.getString("REMARK"));
                item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;

    }

}
