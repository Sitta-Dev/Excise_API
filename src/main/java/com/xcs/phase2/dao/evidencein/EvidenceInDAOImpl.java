package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.evidencein.EvidenceIn;
import com.xcs.phase2.model.evidencein.EvidenceInItem;
import com.xcs.phase2.model.evidencein.EvidenceInStaff;
import com.xcs.phase2.model.evidencein.EvidenceInStockBalance;
import com.xcs.phase2.request.evidencein.EvidenceIngetByConReq;
import com.xcs.phase2.request.evidencein.EvidenceInupdDeleteReq;
import com.xcs.phase2.response.evidencein.EvidenceInItemResponse;
import com.xcs.phase2.response.evidencein.EvidenceInStaffResponse;
import com.xcs.phase2.response.evidencein.EvidenceInStockBalanceResponse;
import com.xcs.phase2.response.evidencein.EvidenceIninsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EvidenceInDAOImpl extends EvidenceInExt implements EvidenceInDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInListDAOImpl.class);

    @Override
    public EvidenceIn EvidenceIngetByCon(EvidenceIngetByConReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select" +
                        "    OPS_EVIDENCE_STOCK_BALANCE.WAREHOUSE_ID," +
                        "    MAS_WAREHOUSE.WAREHOUSE_NAME," +
                        "    OPS_EVIDENCE_IN.*" +
                        "    from OPS_EVIDENCE_IN" +
                        "    left join OPS_EVIDENCE_IN_ITEM on OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID" +
                        "    and OPS_EVIDENCE_IN_ITEM.IS_ACTIVE = 1" +
                        "    left join OPS_EVIDENCE_STOCK_BALANCE on OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID= OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID" +
                        "    left join OPS_EVIDENCE_IN_STAFF on OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID" +
                        "    and OPS_EVIDENCE_IN_STAFF.IS_ACTIVE = 1" +
                        "    left join MAS_WAREHOUSE on OPS_EVIDENCE_STOCK_BALANCE.WAREHOUSE_ID = MAS_WAREHOUSE.WAREHOUSE_ID" +
                        "    where OPS_EVIDENCE_IN_ITEM.IS_ACTIVE = 1" );

        if(!StringUtils.isEmpty(req.getEVIDENCE_IN_ID())){
            sqlBuilder.append("and OPS_EVIDENCE_IN.EVIDENCE_IN_ID =  '" + req.getEVIDENCE_IN_ID() + "'  ");
        }

        if(!StringUtils.isEmpty(req.getPROVE_ID())){
            sqlBuilder.append("and OPS_EVIDENCE_IN.PROVE_ID =  '" + req.getPROVE_ID() + "'  ");
        }

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceIn>() {

            public EvidenceIn extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceIn item = new EvidenceIn();
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

                    item.setWAREHOUSE_ID(rs.getInt("WAREHOUSE_ID"));
                    item.setWAREHOUSE_NAME(rs.getString("WAREHOUSE_NAME"));


                    item.setEvidenceInItem(getEvidenceInItem(rs.getInt("EVIDENCE_IN_ID")));
                    item.setEvidenceInStaff(getEvidenceInStaff(rs.getInt("EVIDENCE_IN_ID"),""));


                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public EvidenceIninsAllResponse EvidenceIninsAll(EvidenceIn req) {

        EvidenceIninsAllResponse res = new EvidenceIninsAllResponse();

        try {

            String EVIDENCE_IN_ID = getSequences("SELECT OPS_EVIDENCE_IN_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("INSERT INTO OPS_EVIDENCE_IN (" +
                            "EVIDENCE_IN_ID," +
                            "PROVE_ID," +
                            "EVIDENCE_IN_CODE," +
                            "EVIDENCE_IN_DATE," +
                            "RETURN_DATE," +
                            "IS_RECEIVE," +
                            "DELIVERY_NO," +
                            "DELIVERY_DATE," +
                            "EVIDENCE_IN_TYPE," +
                            "REMARK," +
                            "IS_ACTIVE," +
                            "IS_EDIT" +
                            " ) VALUES (" +
                            "'" + EVIDENCE_IN_ID + "', " +
                            "'" + req.getPROVE_ID() + "'," +
                            "'" + req.getEVIDENCE_IN_CODE() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getEVIDENCE_IN_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + req.getRETURN_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getIS_RECEIVE() + "'," +
                            "'" + req.getDELIVERY_NO() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getDELIVERY_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getEVIDENCE_IN_TYPE() + "'," +
                            "'" + req.getREMARK() + "'," +
                            "'" + req.getIS_ACTIVE() + "'," +
                            "'" + req.getIS_EDIT() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setEVIDENCE_IN_ID(Integer.parseInt(EVIDENCE_IN_ID));


            if (req.getEvidenceInItem() != null) {
                log.info("[Sub] Size : " + req.getEvidenceInItem().size());
                List<EvidenceInItemResponse> list = new ArrayList<EvidenceInItemResponse>();

                if (req.getEvidenceInItem().size() > 0) {

                    for (EvidenceInItem item : req.getEvidenceInItem()) {

                        String EVIDENCE_IN_ITEM_ID = getSequences("SELECT OPS_EVIDENCE_IN_ITEM_SEQ.NEXTVAL FROM DUAL");
                        EvidenceInItemResponse obj = new EvidenceInItemResponse();
                        obj.setEVIDENCE_IN_ITEM_ID(Integer.parseInt(EVIDENCE_IN_ITEM_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("INSERT INTO OPS_EVIDENCE_IN_ITEM (" +
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
                                        " ) VALUES (" +
                                        "'" + EVIDENCE_IN_ITEM_ID + "', " +
                                        "'" + item.getEVIDENCE_IN_ITEM_CODE() + "'," +
                                        "'" + EVIDENCE_IN_ID + "'," +
                                        "'" + item.getPRODUCT_MAPPING_ID() + "'," +
                                        "'" + item.getPRODUCT_CODE() + "'," +
                                        "'" + item.getPRODUCT_REF_CODE() + "'," +
                                        "'" + item.getPRODUCT_GROUP_ID() + "'," +
                                        "'" + item.getPRODUCT_CATEGORY_ID() + "'," +
                                        "'" + item.getPRODUCT_TYPE_ID() + "'," +
                                        "'" + item.getPRODUCT_SUBTYPE_ID() + "'," +
                                        "'" + item.getPRODUCT_SUBSETTYPE_ID() + "'," +
                                        "'" + item.getPRODUCT_BRAND_ID() + "'," +
                                        "'" + item.getPRODUCT_SUBBRAND_ID() + "'," +
                                        "'" + item.getPRODUCT_MODEL_ID() + "'," +
                                        "'" + item.getPRODUCT_TAXDETAIL_ID() + "'," +
                                        "'" + item.getPRODUCT_GROUP_CODE() + "'," +
                                        "'" + item.getPRODUCT_GROUP_NAME() + "'," +
                                        "'" + item.getPRODUCT_CATEGORY_CODE() + "'," +
                                        "'" + item.getPRODUCT_CATEGORY_NAME() + "'," +
                                        "'" + item.getPRODUCT_TYPE_CODE() + "'," +
                                        "'" + item.getPRODUCT_TYPE_NAME() + "'," +
                                        "'" + item.getPRODUCT_SUBTYPE_CODE() + "'," +
                                        "'" + item.getPRODUCT_SUBTYPE_NAME() + "'," +
                                        "'" + item.getPRODUCT_SUBSETTYPE_CODE() + "'," +
                                        "'" + item.getPRODUCT_SUBSETTYPE_NAME() + "'," +
                                        "'" + item.getPRODUCT_BRAND_CODE() + "'," +
                                        "'" + item.getPRODUCT_BRAND_NAME_TH() + "'," +
                                        "'" + item.getPRODUCT_BRAND_NAME_EN() + "'," +
                                        "'" + item.getPRODUCT_SUBBRAND_CODE() + "'," +
                                        "'" + item.getPRODUCT_SUBBRAND_NAME_TH() + "'," +
                                        "'" + item.getPRODUCT_SUBBRAND_NAME_EN() + "'," +
                                        "'" + item.getPRODUCT_MODEL_CODE() + "'," +
                                        "'" + item.getPRODUCT_MODEL_NAME_TH() + "'," +
                                        "'" + item.getPRODUCT_MODEL_NAME_EN() + "'," +
                                        "'" + item.getLICENSE_PLATE() + "'," +
                                        "'" + item.getENGINE_NO() + "'," +
                                        "'" + item.getCHASSIS_NO() + "'," +
                                        "'" + item.getPRODUCT_DESC() + "'," +
                                        "'" + item.getSUGAR() + "'," +
                                        "'" + item.getCO2() + "'," +
                                        "'" + item.getDEGREE() + "'," +
                                        "'" + item.getPRICE() + "'," +
                                        "'" + item.getDELIVERY_QTY() + "'," +
                                        "'" + item.getDELIVERY_QTY_UNIT() + "'," +
                                        "'" + item.getDELIVERY_SIZE() + "'," +
                                        "'" + item.getDELIVERY_SIZE_UNIT() + "'," +
                                        "'" + item.getDELIVERY_NET_VOLUMN() + "'," +
                                        "'" + item.getDELIVERY_NET_VOLUMN_UNIT() + "'," +
                                        "'" + item.getDAMAGE_QTY() + "'," +
                                        "'" + item.getDAMAGE_QTY_UNIT() + "'," +
                                        "'" + item.getDAMAGE_SIZE() + "'," +
                                        "'" + item.getDAMAGE_SIZE_UNIT() + "'," +
                                        "'" + item.getDAMAGE_NET_VOLUMN() + "'," +
                                        "'" + item.getDAMAGE_NET_VOLUMN_UNIT() + "'," +
                                        "'" + item.getIS_DOMESTIC() + "'," +
                                        "'" + item.getIS_ACTIVE() + "')");
                        log.info("[SQL] Sub : " + sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                        if (item.getEvidenceOutStockBalance() != null) {
                            log.info("[Sub] Size : " + item.getEvidenceOutStockBalance().size());
                            List<EvidenceInStockBalanceResponse> list1 = new ArrayList<EvidenceInStockBalanceResponse>();

                            if (item.getEvidenceOutStockBalance().size() > 0) {

                                for (EvidenceInStockBalance item1 : item.getEvidenceOutStockBalance()) {

                                    String STOCK_ID = getSequences("SELECT OPS_EVIDENCE_STOCK_BALANCE_SEQ.NEXTVAL FROM DUAL");
                                    EvidenceInStockBalanceResponse obj1 = new EvidenceInStockBalanceResponse();
                                    obj1.setSTOCK_ID(Integer.parseInt(STOCK_ID));

                                    StringBuilder sqlBuilderSub1 = new StringBuilder()
                                            .append("INSERT INTO OPS_EVIDENCE_STOCK_BALANCE (" +
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
                                                    " ) VALUES (" +
                                                    "'" + STOCK_ID + "', " +
                                                    "'" + item1.getWAREHOUSE_ID() + "'," +
                                                    "'" + EVIDENCE_IN_ITEM_ID + "'," +
                                                    "'" + item1.getRECEIVE_QTY() + "'," +
                                                    "'" + item1.getRECEIVE_QTY_UNIT() + "'," +
                                                    "'" + item1.getRECEIVE_SIZE() + "'," +
                                                    "'" + item1.getRECEIVE_SIZE_UNIT() + "'," +
                                                    "'" + item1.getRECEIVE_NET_VOLUMN() + "'," +
                                                    "'" + item1.getRECEIVE_NET_VOLUMN_UNIT() + "'," +
                                                    "'" + item1.getBALANCE_QTY() + "'," +
                                                    "'" + item1.getBALANCE_QTY_UNIT() + "'," +
                                                    "'" + item1.getBALANCE_SIZE() + "'," +
                                                    "'" + item1.getBALANCE_SIZE_UNIT() + "'," +
                                                    "'" + item1.getBALANCE_NET_VOLUMN() + "'," +
                                                    "'" + item1.getBALANCE_NET_VOLUMN_UNIT() + "'," +
                                                    "'" + item1.getIS_FINISH() + "'," +
                                                    "'" + item1.getIS_RECEIVE() + "')");
                                    log.info("[SQL] Sub : " + sqlBuilderSub1.toString());
                                    getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[]{});


                                    list1.add(obj1);
                                }
                            }
                            obj.setEvidenceInStockBalance(list1);
                        }


                        list.add(obj);
                    }
                }
                res.setEvidenceInItem(list);
            }

            if (req.getEvidenceInStaff() != null) {
                log.info("[Sub] Size : " + req.getEvidenceInStaff().size());
                List<EvidenceInStaffResponse> list = new ArrayList<EvidenceInStaffResponse>();

                if (req.getEvidenceInStaff().size() > 0) {
                    for (EvidenceInStaff item : req.getEvidenceInStaff()) {

                        String EVIDENCE_IN_STAFF_ID = getSequences("SELECT OPS_EVIDENCE_IN_STAFF_SEQ.NEXTVAL FROM DUAL");
                        EvidenceInStaffResponse obj = new EvidenceInStaffResponse();
                        obj.setEVIDENCE_IN_STAFF_ID(Integer.parseInt(EVIDENCE_IN_STAFF_ID));

                        StringBuilder sqlBuilderSubStaff = new StringBuilder()
                                .append("INSERT INTO OPS_EVIDENCE_IN_STAFF (" +
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
                                        "IS_ACTIVE " +
                                        " ) VALUES (" +
                                        "'" + EVIDENCE_IN_STAFF_ID + "', " +
                                        "'" + EVIDENCE_IN_ID + "', " +
                                        "'" + item.getSTAFF_REF_ID() + "'," +
                                        "'" + item.getTITLE_ID() + "'," +
                                        "'" + item.getSTAFF_CODE() + "'," +
                                        "'" + item.getID_CARD() + "'," +
                                        "'" + item.getSTAFF_TYPE() + "'," +
                                        "'" + item.getTITLE_NAME_TH() + "'," +
                                        "'" + item.getTITLE_NAME_EN() + "'," +
                                        "'" + item.getTITLE_SHORT_NAME_TH() + "'," +
                                        "'" + item.getTITLE_SHORT_NAME_EN() + "'," +
                                        "'" + item.getFIRST_NAME() + "'," +
                                        "'" + item.getLAST_NAME() + "'," +
                                        "'" + item.getAGE() + "'," +
                                        "'" + item.getOPERATION_POS_CODE() + "'," +
                                        "'" + item.getOPREATION_POS_NAME() + "'," +
                                        "'" + item.getOPREATION_POS_LEVEL() + "'," +
                                        "'" + item.getOPERATION_POS_LEVEL_NAME() + "'," +
                                        "'" + item.getOPERATION_DEPT_CODE() + "'," +
                                        "'" + item.getOPERATION_DEPT_NAME() + "'," +
                                        "'" + item.getOPERATION_DEPT_LEVEL() + "'," +
                                        "'" + item.getOPERATION_UNDER_DEPT_CODE() + "'," +
                                        "'" + item.getOPERATION_UNDER_DEPT_NAME() + "'," +
                                        "'" + item.getOPERATION_UNDER_DEPT_LEVEL() + "'," +
                                        "'" + item.getOPERATION_WORK_DEPT_CODE() + "'," +
                                        "'" + item.getOPERATION_WORK_DEPT_NAME() + "'," +
                                        "'" + item.getOPERATION_WORK_DEPT_LEVEL() + "'," +
                                        "'" + item.getOPERATION_OFFICE_CODE() + "'," +
                                        "'" + item.getOPERATION_OFFICE_NAME() + "'," +
                                        "'" + item.getOPERATION_OFFICE_SHORT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_POS_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_POS_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_POS_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_POS_LEVEL_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_DEPT_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_DEPT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_DEPT_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_UNDER_DEPT_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_UNDER_DEPT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_UNDER_DEPT_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_WORK_DEPT_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_WORK_DEPT_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_WORK_DEPT_LEVEL() + "'," +
                                        "'" + item.getMANAGEMENT_OFFICE_CODE() + "'," +
                                        "'" + item.getMANAGEMENT_OFFICE_NAME() + "'," +
                                        "'" + item.getMANAGEMENT_OFFICE_SHORT_NAME() + "'," +
                                        "'" + item.getREPRESENT_POS_CODE() + "'," +
                                        "'" + item.getREPRESENT_POS_NAME() + "'," +
                                        "'" + item.getREPRESENT_POS_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_POS_LEVEL_NAME() + "'," +
                                        "'" + item.getREPRESENT_DEPT_CODE() + "'," +
                                        "'" + item.getREPRESENT_DEPT_NAME() + "'," +
                                        "'" + item.getREPRESENT_DEPT_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_UNDER_DEPT_CODE() + "'," +
                                        "'" + item.getREPRESENT_UNDER_DEPT_NAME() + "'," +
                                        "'" + item.getREPRESENT_UNDER_DEPT_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_WORK_DEPT_CODE() + "'," +
                                        "'" + item.getREPRESENT_WORK_DEPT_NAME() + "'," +
                                        "'" + item.getREPRESENT_WORK_DEPT_LEVEL() + "'," +
                                        "'" + item.getREPRESENT_OFFICE_CODE() + "'," +
                                        "'" + item.getREPRESENT_OFFICE_NAME() + "'," +
                                        "'" + item.getREPRESENT_OFFICE_SHORT_NAME() + "'," +
                                        "'" + item.getSTATUS() + "'," +
                                        "'" + item.getREMARK() + "'," +
                                        "'" + item.getCONTRIBUTOR_ID() + "'," +
                                        "'" + item.getIS_ACTIVE() + "') ");
                        log.info("[SQL] : " + sqlBuilderSubStaff.toString());

                        getJdbcTemplate().update(sqlBuilderSubStaff.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setEvidenceInStaff(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setEVIDENCE_IN_ID(0);
            res.setEvidenceInItem(null);
            res.setEvidenceInStaff(null);
            return res;
        }

    }

    @Override
    public Boolean EvidenceInupdByCon(EvidenceIn req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_EVIDENCE_IN SET "
                        + "PROVE_ID=  '" + req.getPROVE_ID() + "', "
                        + "EVIDENCE_IN_CODE=  '" + req.getEVIDENCE_IN_CODE() + "', "
                        + "EVIDENCE_IN_DATE=  TO_TIMESTAMP_TZ('" + req.getEVIDENCE_IN_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "RETURN_DATE=  TO_TIMESTAMP_TZ('" + req.getRETURN_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "IS_RECEIVE=  '" + req.getIS_RECEIVE() + "', "
                        + "DELIVERY_NO=  '" + req.getDELIVERY_NO() + "', "
                        + "DELIVERY_DATE=  TO_TIMESTAMP_TZ('" + req.getDELIVERY_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "EVIDENCE_IN_TYPE=  '" + req.getEVIDENCE_IN_TYPE() + "', "
                        + "REMARK=  '" + req.getREMARK() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "', "
                        + "IS_EDIT=  '" + req.getIS_EDIT() + "' "
                        + " WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        if (req.getEvidenceInStaff() != null) {
            log.info("[Sub] Size : " + req.getEvidenceInStaff().size());

            if (req.getEvidenceInStaff().size() > 0) {
                for (EvidenceInStaff item : req.getEvidenceInStaff()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_IN_STAFF SET "
                                    + "EVIDENCE_IN_ID=  '" + item.getEVIDENCE_IN_ID() + "', "
                                    + "STAFF_REF_ID=  '" + item.getSTAFF_REF_ID() + "', "
                                    + "TITLE_ID=  '" + item.getTITLE_ID() + "', "
                                    + "STAFF_CODE=  '" + item.getSTAFF_CODE() + "', "
                                    + "ID_CARD=  '" + item.getID_CARD() + "', "
                                    + "STAFF_TYPE=  '" + item.getSTAFF_TYPE() + "', "
                                    + "TITLE_NAME_TH=  '" + item.getTITLE_NAME_TH() + "', "
                                    + "TITLE_NAME_EN=  '" + item.getTITLE_NAME_EN() + "', "
                                    + "TITLE_SHORT_NAME_TH=  '" + item.getTITLE_SHORT_NAME_TH() + "', "
                                    + "TITLE_SHORT_NAME_EN=  '" + item.getTITLE_SHORT_NAME_EN() + "', "
                                    + "FIRST_NAME=  '" + item.getFIRST_NAME() + "', "
                                    + "LAST_NAME=  '" + item.getLAST_NAME() + "', "
                                    + "AGE=  '" + item.getAGE() + "', "
                                    + "OPERATION_POS_CODE=  '" + item.getOPERATION_POS_CODE() + "', "
                                    + "OPREATION_POS_NAME=  '" + item.getOPREATION_POS_NAME() + "', "
                                    + "OPREATION_POS_LEVEL=  '" + item.getOPREATION_POS_LEVEL() + "', "
                                    + "OPERATION_POS_LEVEL_NAME=  '" + item.getOPERATION_POS_LEVEL_NAME() + "', "
                                    + "OPERATION_DEPT_CODE=  '" + item.getOPERATION_DEPT_CODE() + "', "
                                    + "OPERATION_DEPT_NAME=  '" + item.getOPERATION_DEPT_NAME() + "', "
                                    + "OPERATION_DEPT_LEVEL=  '" + item.getOPERATION_DEPT_LEVEL() + "', "
                                    + "OPERATION_UNDER_DEPT_CODE=  '" + item.getOPERATION_UNDER_DEPT_CODE() + "', "
                                    + "OPERATION_UNDER_DEPT_NAME=  '" + item.getOPERATION_UNDER_DEPT_NAME() + "', "
                                    + "OPERATION_UNDER_DEPT_LEVEL=  '" + item.getOPERATION_UNDER_DEPT_LEVEL() + "', "
                                    + "OPERATION_WORK_DEPT_CODE=  '" + item.getOPERATION_WORK_DEPT_CODE() + "', "
                                    + "OPERATION_WORK_DEPT_NAME=  '" + item.getOPERATION_WORK_DEPT_NAME() + "', "
                                    + "OPERATION_WORK_DEPT_LEVEL=  '" + item.getOPERATION_WORK_DEPT_LEVEL() + "', "
                                    + "OPERATION_OFFICE_CODE=  '" + item.getOPERATION_OFFICE_CODE() + "', "
                                    + "OPERATION_OFFICE_NAME=  '" + item.getOPERATION_OFFICE_NAME() + "', "
                                    + "OPERATION_OFFICE_SHORT_NAME=  '" + item.getOPERATION_OFFICE_SHORT_NAME() + "', "
                                    + "MANAGEMENT_POS_CODE=  '" + item.getMANAGEMENT_POS_CODE() + "', "
                                    + "MANAGEMENT_POS_NAME=  '" + item.getMANAGEMENT_POS_NAME() + "', "
                                    + "MANAGEMENT_POS_LEVEL=  '" + item.getMANAGEMENT_POS_LEVEL() + "', "
                                    + "MANAGEMENT_POS_LEVEL_NAME=  '" + item.getMANAGEMENT_POS_LEVEL_NAME() + "', "
                                    + "MANAGEMENT_DEPT_CODE=  '" + item.getMANAGEMENT_DEPT_CODE() + "', "
                                    + "MANAGEMENT_DEPT_NAME=  '" + item.getMANAGEMENT_DEPT_NAME() + "', "
                                    + "MANAGEMENT_DEPT_LEVEL=  '" + item.getMANAGEMENT_DEPT_LEVEL() + "', "
                                    + "MANAGEMENT_UNDER_DEPT_CODE=  '" + item.getMANAGEMENT_UNDER_DEPT_CODE() + "', "
                                    + "MANAGEMENT_UNDER_DEPT_NAME=  '" + item.getMANAGEMENT_UNDER_DEPT_NAME() + "', "
                                    + "MANAGEMENT_UNDER_DEPT_LEVEL=  '" + item.getMANAGEMENT_UNDER_DEPT_LEVEL() + "', "
                                    + "MANAGEMENT_WORK_DEPT_CODE=  '" + item.getMANAGEMENT_WORK_DEPT_CODE() + "', "
                                    + "MANAGEMENT_WORK_DEPT_NAME=  '" + item.getMANAGEMENT_WORK_DEPT_NAME() + "', "
                                    + "MANAGEMENT_WORK_DEPT_LEVEL=  '" + item.getMANAGEMENT_WORK_DEPT_LEVEL() + "', "
                                    + "MANAGEMENT_OFFICE_CODE=  '" + item.getMANAGEMENT_OFFICE_CODE() + "', "
                                    + "MANAGEMENT_OFFICE_NAME=  '" + item.getMANAGEMENT_OFFICE_NAME() + "', "
                                    + "MANAGEMENT_OFFICE_SHORT_NAME=  '" + item.getMANAGEMENT_OFFICE_SHORT_NAME() + "', "
                                    + "REPRESENT_POS_CODE=  '" + item.getREPRESENT_POS_CODE() + "', "
                                    + "REPRESENT_POS_NAME=  '" + item.getREPRESENT_POS_NAME() + "', "
                                    + "REPRESENT_POS_LEVEL=  '" + item.getREPRESENT_POS_LEVEL() + "', "
                                    + "REPRESENT_POS_LEVEL_NAME=  '" + item.getREPRESENT_POS_LEVEL_NAME() + "', "
                                    + "REPRESENT_DEPT_CODE=  '" + item.getREPRESENT_DEPT_CODE() + "', "
                                    + "REPRESENT_DEPT_NAME=  '" + item.getREPRESENT_DEPT_NAME() + "', "
                                    + "REPRESENT_DEPT_LEVEL=  '" + item.getREPRESENT_DEPT_LEVEL() + "', "
                                    + "REPRESENT_UNDER_DEPT_CODE=  '" + item.getREPRESENT_UNDER_DEPT_CODE() + "', "
                                    + "REPRESENT_UNDER_DEPT_NAME=  '" + item.getREPRESENT_UNDER_DEPT_NAME() + "', "
                                    + "REPRESENT_UNDER_DEPT_LEVEL=  '" + item.getREPRESENT_UNDER_DEPT_LEVEL() + "', "
                                    + "REPRESENT_WORK_DEPT_CODE=  '" + item.getREPRESENT_WORK_DEPT_CODE() + "', "
                                    + "REPRESENT_WORK_DEPT_NAME=  '" + item.getREPRESENT_WORK_DEPT_NAME() + "', "
                                    + "REPRESENT_WORK_DEPT_LEVEL=  '" + item.getREPRESENT_WORK_DEPT_LEVEL() + "', "
                                    + "REPRESENT_OFFICE_CODE=  '" + item.getREPRESENT_OFFICE_CODE() + "', "
                                    + "REPRESENT_OFFICE_NAME=  '" + item.getREPRESENT_OFFICE_NAME() + "', "
                                    + "REPRESENT_OFFICE_SHORT_NAME=  '" + item.getREPRESENT_OFFICE_SHORT_NAME() + "', "
                                    + "STATUS=  '" + item.getSTATUS() + "', "
                                    + "REMARK=  '" + item.getREMARK() + "', "
                                    + "CONTRIBUTOR_ID=  '" + item.getCONTRIBUTOR_ID() + "', "
                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
                                    + " WHERE EVIDENCE_IN_STAFF_ID = '" + item.getEVIDENCE_IN_STAFF_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                }
            }
        }

        return true;
    }

    @Override
    public Boolean EvidenceInupdDelete(EvidenceInupdDeleteReq req) {


        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_EVIDENCE_IN SET IS_ACTIVE = '0' WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_EVIDENCE_IN_ITEM SET IS_ACTIVE = '0' WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");
        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_EVIDENCE_IN_STAFF SET IS_ACTIVE = '0' WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");
        StringBuilder sqlBuilder4 = new StringBuilder().append("UPDATE OPS_EVIDENCE_STOCK_BALANCE SET IS_FINISH = '0' WHERE EVIDENCE_IN_ITEM_ID in(select EVIDENCE_IN_ITEM_ID from OPS_EVIDENCE_IN_ITEM where EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "')  ");

        log.info("[SQL] ops_evidence_out : " + sqlBuilder1.toString());
        log.info("[SQL] ops_evidence_out_item : " + sqlBuilder2.toString());
        log.info("[SQL] ops_evidence_out_staff : " + sqlBuilder3.toString());

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder4.toString(), new Object[]{});


        return true;

    }
}
