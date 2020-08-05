package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.evidencein.*;
import com.xcs.phase2.model.evidenceout.EvidenceOutStaff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EvidenceInExt {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInExt.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected String getSequences(String strSql) {
        log.info("[SQL]  : " + strSql);
        return getJdbcTemplate().queryForObject(strSql, String.class);
    }

    protected List<EvidenceInItem> getEvidenceInItem(int EVIDENCE_IN_ID) {

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
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_IN_ITEM where IS_ACTIVE = 1 and EVIDENCE_IN_ID = '" + EVIDENCE_IN_ID + "' ");


        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInItem> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceInItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInItem item = new EvidenceInItem();

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

                item.setEvidenceOutStockBalance(getEvidenceInStockBalance(rs.getInt("EVIDENCE_IN_ITEM_ID")));

                return item;
            }
        });

        return dataList;

    }

    protected List<EvidenceInStaff> getEvidenceInStaff(int EVIDENCE_IN_ID , String OFFICE_CODE) {

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

        if(!StringUtils.isEmpty(OFFICE_CODE)){
            sqlBuilderDetail.append(" AND OPERATION_OFFICE_CODE = '"+OFFICE_CODE+"' ");
        }

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInStaff> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceInStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInStaff item = new EvidenceInStaff();
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


    protected List<EvidenceInOutItem> getEvidenceInOutItem(int EVIDENCE_OUT_ID) {

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
        List<EvidenceInOutItem> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceInOutItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInOutItem item = new EvidenceInOutItem();

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

                item.setEvidenceInStockBalance(getEvidenceInStockBalanceForEvidenceInOut(rs.getInt("STOCK_ID")));

                return item;
            }
        });

        return dataList;

    }

    private List<EvidenceInStockBalance> getEvidenceInStockBalance(int EVIDENCE_IN_ITEM_ID) {

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

        sqlBuilderDetail.append("where EVIDENCE_IN_ITEM_ID =  " + EVIDENCE_IN_ITEM_ID);

        log.info("[SQL] : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInStockBalance> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public EvidenceInStockBalance mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInStockBalance item = new EvidenceInStockBalance();

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

    private EvidenceInStockBalanceForEvidenceInOut getEvidenceInStockBalanceForEvidenceInOut(int STOCK_ID) {


        StringBuilder sqlBuilder = new StringBuilder()
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

        sqlBuilder.append("where STOCK_ID =  " + STOCK_ID);

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceInStockBalanceForEvidenceInOut>() {

            public EvidenceInStockBalanceForEvidenceInOut extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceInStockBalanceForEvidenceInOut item = new EvidenceInStockBalanceForEvidenceInOut();
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

                    item.setEvidenceInItem(getEvidenceInItemForEvidenceInOut(rs.getInt("EVIDENCE_IN_ITEM_ID")));


                    return item;
                }

                return null;
            }
        });
    }

    private EvidenceInItem getEvidenceInItemForEvidenceInOut(int EVIDENCE_IN_ITEM_ID) {


        StringBuilder sqlBuilder = new StringBuilder()
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
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_IN_ITEM where IS_ACTIVE = 1 and EVIDENCE_IN_ITEM_ID = '" + EVIDENCE_IN_ITEM_ID + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceInItem>() {

            public EvidenceInItem extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceInItem item = new EvidenceInItem();
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


                    return item;
                }

                return null;
            }
        });
    }

    protected List<EvidenceInNew> getEvidenceIn(int PROVE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "EVIDENCE_IN_ID," +
                        "PROVE_ID," +
                        "EVIDENCE_IN_CODE," +
                        "to_char(EVIDENCE_IN_DATE,'" + Pattern.FORMAT_DATETIME + "') as EVIDENCE_IN_DATE, " +
                        "to_char(RETURN_DATE,'" + Pattern.FORMAT_DATETIME + "') as RETURN_DATE, " +
                        "IS_RECEIVE," +
                        "DELIVERY_NO," +
                        "to_char(DELIVERY_DATE,'" + Pattern.FORMAT_DATETIME + "') as DELIVERY_DATE, " +
                        "EVIDENCE_IN_TYPE," +
                        "REMARK," +
                        "IS_ACTIVE," +
                        "IS_EDIT " +
                        " from OPS_EVIDENCE_IN  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and PROVE_ID =  '" + PROVE_ID + "'  ");


        log.info("[SQL] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInNew> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceInNew mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInNew item = new EvidenceInNew();
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setEVIDENCE_IN_CODE(rs.getString("EVIDENCE_IN_CODE"));
                item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                item.setRETURN_DATE(rs.getString("RETURN_DATE"));
                item.setIS_RECEIVE(rs.getInt("IS_RECEIVE"));
                item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                item.setEVIDENCE_IN_TYPE(rs.getInt("EVIDENCE_IN_TYPE"));
                item.setREMARK(rs.getString("REMARK"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setIS_EDIT(rs.getInt("IS_EDIT"));

                item.setEvidenceInOut(getEvidenceInOutNew(rs.getInt("EVIDENCE_IN_ID")));

                return item;
            }
        });

        return dataList;

    }

    protected List<EvidenceInOutNew> getEvidenceInOutNew(int EVIDENCE_IN_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "EVIDENCE_OUT_ID," +
                        "EVIDENCE_IN_ID," +
                        "WAREHOUSE_ID," +
                        "OFFICE_CODE," +
                        "EVIDENCE_OUT_CODE," +
                        "to_char(EVIDENCE_OUT_DATE,'" + Pattern.FORMAT_DATETIME + "') as EVIDENCE_OUT_DATE, " +
                        "EVIDENCE_OUT_TYPE," +
                        "EVIDENCE_OUT_NO," +
                        "to_char(EVIDENCE_OUT_NO_DATE,'" + Pattern.FORMAT_DATETIME + "') as EVIDENCE_OUT_NO_DATE, " +
                        "BOOK_NO," +
                        "RECEIPT_NO," +
                        "to_char(PAY_DATE,'" + Pattern.FORMAT_DATETIME + "') as PAY_DATE, " +
                        "to_char(APPROVE_DATE,'" + Pattern.FORMAT_DATETIME + "') as APPROVE_DATE, " +
                        "to_char(RETURN_DATE,'" + Pattern.FORMAT_DATETIME + "') as RETURN_DATE, " +
                        "REMARK," +
                        "APPROVE_NO," +
                        "IS_ACTIVE" +
                        " from OPS_EVIDENCE_OUT  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and EVIDENCE_IN_ID =  '"+EVIDENCE_IN_ID+"'  ");


        log.info("[SQL] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceInOutNew> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceInOutNew mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceInOutNew item = new EvidenceInOutNew();
                item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setWAREHOUSE_ID(rs.getInt("WAREHOUSE_ID"));
                item.setOFFICE_CODE(rs.getInt("OFFICE_CODE"));
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

                return item;
            }
        });

        return dataList;

    }

    protected List<EvidenceOutStaff> getEvidenceOutStaff(int EVIDENCE_OUT_ID , String OFFICE_CODE) {

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

        if(!StringUtils.isEmpty(OFFICE_CODE)){
            sqlBuilderDetail.append(" AND OPERATION_OFFICE_CODE = '"+OFFICE_CODE+"' ");
        }

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
