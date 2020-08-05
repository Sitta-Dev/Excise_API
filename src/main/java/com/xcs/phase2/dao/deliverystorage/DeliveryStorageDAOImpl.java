package com.xcs.phase2.dao.deliverystorage;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.dao.master.MasStaffDAO;
import com.xcs.phase2.model.deliverystorage.*;
import com.xcs.phase2.model.master.MasStaff;
import com.xcs.phase2.request.deliverystorage.DeliverStoragegetByConReq;
import com.xcs.phase2.request.deliverystorage.DeliverStoragegetByCreateReq;
import com.xcs.phase2.request.deliverystorage.DeliverStoragegetProductReq;
import com.xcs.phase2.request.deliverystorage.DeliverStorageupdDeleteReq;
import com.xcs.phase2.response.deliverystorage.DeliveryStorageinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class DeliveryStorageDAOImpl extends DeliveryStorageExt implements DeliveryStorageDAO {

    private static final Logger log = LoggerFactory.getLogger(DeliveryStorageDAOImpl.class);

    @Autowired
    private MasStaffDAO masStaffDAO;


    @Override
    public DeliveryStorage DeliverStoragegetByCreate(DeliverStoragegetByCreateReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT " +
                        "    OPS_ARREST.ARREST_ID," +
                        "    OPS_ARREST.ARREST_CODE," +
                        "    TO_CHAR(OPS_ARREST.OCCURRENCE_DATE, 'dd MON yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as OCCURRENCE_DATE," +
                        "    TO_CHAR(OPS_ARREST.OCCURRENCE_DATE, 'HH:MM') as OCCURRENCE_TIME," +
                        "    case when OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH is null or OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_ARREST_STAFF.TITLE_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME " +
                        "    else OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME end as ARREST_STAFF_NAME, " +
                        "    OPS_ARREST_STAFF.OPREATION_POS_NAME OPERATION_POS_NAME, " +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_NAME, " +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_ARREST.OFFICE_NAME ARREST_OFFICE_NAME" +
                        "    FROM OPS_ARREST" +
                        "    INNER JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID = OPS_ARREST_INDICTMENT.ARREST_ID AND OPS_ARREST_INDICTMENT.IS_ACTIVE =1" +
                        "    INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID AND OPS_ARREST_STAFF.IS_ACTIVE =1" +
                        "    WHERE OPS_ARREST.ARREST_ID = "+req.getARREST_ID());

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<DeliveryStorage>() {

            public DeliveryStorage extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    DeliveryStorage item = new DeliveryStorage();
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setARREST_CODE(rs.getString("ARREST_CODE"));
                    item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                    item.setOCCURRENCE_TIME(rs.getString("OCCURRENCE_TIME"));
                    item.setARREST_STAFF_NAME(rs.getString("ARREST_STAFF_NAME"));
                    item.setOPERATION_POS_NAME(rs.getString("OPERATION_POS_NAME"));
                    item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                    item.setOPERATION_OFFICE_SHORT_NAME(rs.getString("OPERATION_OFFICE_SHORT_NAME"));
                    item.setARREST_OFFICE_NAME(rs.getString("ARREST_OFFICE_NAME"));
                    item.setDeliveryStorageLawsuitDetail(getDeliveryStorageLawsuitDetail(rs.getInt("ARREST_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public List<DeliveryStorageProduct> DeliverStorageetProduct(DeliverStoragegetProductReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("with temp1 as (" +
                        "    SELECT DISTINCT " +
                        "    OPS_ARREST_PRODUCT.PRODUCT_ID," +
                        "    OPS_ARREST_PRODUCT.PRODUCT_GROUP_ID," +
                        "    OPS_ARREST_PRODUCT.PRODUCT_GROUP_CODE," +
                        "    OPS_ARREST_PRODUCT.PRODUCT_GROUP_NAME," +
                        "    OPS_ARREST_PRODUCT.PRODUCT_CATEGORY_CODE," +
                        "    OPS_ARREST_PRODUCT.PRODUCT_CATEGORY_NAME," +
                        "    OPS_ARREST_PRODUCT.PRODUCT_DESC," +
                        "    OPS_ARREST_PRODUCT.DEGREE, " +
                        "    OPS_ARREST_PRODUCT.PRICE," +
                        "    OPS_ARREST_PRODUCT.SIZES, " +
                        "    OPS_ARREST_PRODUCT.SIZES_UNIT, " +
                        "    OPS_ARREST_PRODUCT.QUANTITY," +
                        "    OPS_ARREST_PRODUCT.QUANTITY_UNIT," +
                        "    OPS_ARREST_PRODUCT.VOLUMN, " +
                        "    OPS_ARREST_PRODUCT.VOLUMN_UNIT" +
                        "    FROM OPS_ARREST" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID = OPS_ARREST_INDICTMENT.ARREST_ID AND OPS_ARREST_INDICTMENT.IS_ACTIVE =1" +
                        "    LEFT JOIN OPS_ARREST_PRODUCT ON OPS_ARREST.ARREST_ID = OPS_ARREST_PRODUCT.ARREST_ID AND OPS_ARREST_PRODUCT.IS_ACTIVE=1" +
                        "    WHERE OPS_ARREST.ARREST_ID = "+req.getARREST_ID() +
                        ")," +
                        "temp2 as (" +
                        "    SELECT DISTINCT " +
                        "    OPS_REMAIN_PRODUCT.REMAIN_PRODUCT_ID," +
                        "    OPS_REMAIN_PRODUCT.ARREST_PRODUCT_ID," +
                        "    OPS_REMAIN_PRODUCT.REMAIN_SIZES," +
                        "    OPS_REMAIN_PRODUCT.REMAIN_SIZES_UNIT," +
                        "    OPS_REMAIN_PRODUCT.REMAIN_QUANTITY," +
                        "    OPS_REMAIN_PRODUCT.REMAIN_QUANTITY_UNIT," +
                        "    OPS_REMAIN_PRODUCT.REMAIN_VOLUMN," +
                        "    OPS_REMAIN_PRODUCT.REMAIN_VOLUMN_UNIT" +
                        "    FROM OPS_ARREST LEFT JOIN OPS_REMAIN_PRODUCT ON OPS_ARREST.ARREST_ID = OPS_REMAIN_PRODUCT.ARREST_ID  AND OPS_REMAIN_PRODUCT.IS_ACTIVE =1" +
                        "    WHERE OPS_ARREST.ARREST_ID = "+req.getARREST_ID() +
                        ") " +
                        "select * from temp1 left join temp2 on(temp1.PRODUCT_ID = temp2.ARREST_PRODUCT_ID)");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<DeliveryStorageProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public DeliveryStorageProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                DeliveryStorageProduct item = new DeliveryStorageProduct();
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
                item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setDEGREE(rs.getFloat("DEGREE"));
                item.setPRICE(rs.getFloat("PRICE"));
                item.setSIZES(rs.getFloat("SIZES"));
                item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
                item.setQUANTITY(rs.getFloat("QUANTITY"));
                item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
                item.setVOLUMN(rs.getFloat("VOLUMN"));
                item.setVOLUMN_UNIT(rs.getString("VOLUMN_UNIT"));
                item.setREMAIN_PRODUCT_ID(rs.getInt("REMAIN_PRODUCT_ID"));
                item.setARREST_PRODUCT_ID(rs.getInt("ARREST_PRODUCT_ID"));
                item.setREMAIN_SIZES(rs.getFloat("REMAIN_SIZES"));
                item.setREMAIN_SIZES_UNIT(rs.getString("REMAIN_SIZES_UNIT"));
                item.setREMAIN_QUANTITY(rs.getFloat("REMAIN_QUANTITY"));
                item.setREMAIN_QUANTITY_UNIT(rs.getString("REMAIN_QUANTITY_UNIT"));
                item.setREMAIN_VOLUMN(rs.getFloat("REMAIN_VOLUMN"));
                item.setREMAIN_VOLUMN_UNIT(rs.getString("REMAIN_VOLUMN_UNIT"));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public DeliveryStorageinsAllResponse DeliveryStorageinsAll(DeliveryStorageEvidenceIn req) {

        DeliveryStorageinsAllResponse res = new DeliveryStorageinsAllResponse();

        try {

            String EVIDENCE_IN_ID = getSequences("SELECT OPS_EVIDENCE_IN_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("INSERT INTO OPS_EVIDENCE_IN (" +
                            "EVIDENCE_IN_ID," +
                            "ARREST_ID," +
                            "DELIVERY_CODE," +
                            "DELIVERY_NO," +
                            "DELIVERY_DATE," +
                            "DELIVERY_OFFICE_CODE," +
                            "DELIVERY_OFFICE_NAME," +
                            "DELIVERY_TITTLE," +
                            "DELIVERY_DEAR," +
                            "REMARK," +
                            "DELIVERY_TYPE," +
                            "IS_RECEIVE," +
                            "EVIDENCE_IN_TYPE," +
                            "IS_ACTIVE," +
                            "IS_EDIT" +
                            " ) VALUES (" +
                            "'" + EVIDENCE_IN_ID + "', " +
                            "'" + req.getARREST_ID() + "'," +
                            "'" + req.getDELIVERY_CODE() + "'," +
                            "'" + req.getDELIVERY_NO() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getDELIVERY_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            " (select office_code from mas_office where office_short_name = '"+req.getDELIVERY_OFFICE_NAME()+"')," +
                            "'" + req.getDELIVERY_OFFICE_NAME() + "'," +
                            "'" + req.getDELIVERY_TITTLE() + "'," +
                            "'" + req.getDELIVERY_DEAR() + "'," +
                            "'" + req.getREMARK() + "'," +
                            "'0'," +
                            "'0'," +
                            "'0'," +
                            "'1'," +
                            "'1'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setEVIDENCE_IN_ID(Integer.parseInt(EVIDENCE_IN_ID));


            if (req.getDeliveryStorageEvidenceInItem() != null) {
                log.info("[Sub] Size : " + req.getDeliveryStorageEvidenceInItem().size());

                if (req.getDeliveryStorageEvidenceInItem().size() > 0) {

                    for (DeliveryStorageEvidenceInItem item : req.getDeliveryStorageEvidenceInItem()) {

                        String EVIDENCE_IN_ITEM_ID = getSequences("SELECT OPS_EVIDENCE_IN_ITEM_SEQ.NEXTVAL FROM DUAL");

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("INSERT INTO OPS_EVIDENCE_IN_ITEM (" +
                                        "EVIDENCE_IN_ITEM_ID," + //
                                        "EVIDENCE_IN_ITEM_CODE," + //
                                        "EVIDENCE_IN_ID," + //
                                        "PRODUCT_GROUP_ID," + //
                                        "PRODUCT_GROUP_CODE," +
                                        "PRODUCT_GROUP_NAME," +
                                        "PRODUCT_CATEGORY_CODE," +
                                        "PRODUCT_CATEGORY_NAME," +//
                                        "PRODUCT_DESC," +
                                        "DEGREE," +
                                        "PRICE," +
                                        "DELIVERY_QTY," +
                                        "DELIVERY_QTY_UNIT," +
                                        "DELIVERY_SIZE," +
                                        "DELIVERY_SIZE_UNIT," +
                                        "DELIVERY_NET_VOLUMN," +
                                        "DELIVERY_NET_VOLUMN_UNIT," +
                                        "IS_DOMESTIC," +
                                        "IS_ACTIVE" +
                                        " ) VALUES (" +
                                        "'" + EVIDENCE_IN_ITEM_ID + "', " +
                                        //"'" + item.getEVIDENCE_IN_ITEM_CODE() + "'," +
                                        "null," +
                                        "'" + EVIDENCE_IN_ID + "'," +
                                        "'" + item.getPRODUCT_GROUP_ID() + "'," +
                                        "" + item.getPRODUCT_GROUP_CODE() + "," +
                                        "'" + item.getPRODUCT_GROUP_NAME() + "'," +
                                        "" + item.getPRODUCT_CATEGORY_CODE() + "," +
                                        "'" + item.getPRODUCT_CATEGORY_NAME() + "'," +
                                        "'" + item.getPRODUCT_DESC() + "'," +
                                        "'" + item.getDEGREE() + "'," +
                                        "'" + item.getPRICE() + "'," +
                                        "'" + item.getDELIVERY_QTY() + "'," +
                                        "'" + item.getDELIVERY_QTY_UNIT() + "'," +
                                        "'" + item.getDELIVERY_SIZE() + "'," +
                                        "'" + item.getDELIVERY_SIZE_UNIT() + "'," +
                                        "'" + item.getDELIVERY_NET_VOLUMN() + "'," +
                                        "'" + item.getDELIVERY_NET_VOLUMN_UNIT() + "'," +
                                        "'0'," +
                                        "'1')");
                        log.info("[SQL] Sub : " + sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                    }
                }
            }

            if (req.getDeliveryStorageEvidenceInStaff() != null) {
                log.info("[Sub] Size : " + req.getDeliveryStorageEvidenceInStaff().size());

                if (req.getDeliveryStorageEvidenceInStaff().size() > 0) {
                    for (DeliveryStorageEvidenceInStaff staff : req.getDeliveryStorageEvidenceInStaff()) {

                        MasStaff item = masStaffDAO.MasStaffgetById(staff.getSTAFF_ID());

                        String EVIDENCE_IN_STAFF_ID = getSequences("SELECT OPS_EVIDENCE_IN_STAFF_SEQ.NEXTVAL FROM DUAL");

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
                                        "'" + item.getSTAFF_ID() + "'," +
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
                                        "''," +
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
                                        "'59'," +
                                        "'1') ");
                        log.info("[SQL] : " + sqlBuilderSubStaff.toString());

                        getJdbcTemplate().update(sqlBuilderSubStaff.toString(), new Object[]{});
                    }
                }
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setEVIDENCE_IN_ID(0);
            return res;
        }

    }

    @Override
    public DeliveryStorageEvidenceIn DeliverStoragegetByCon(DeliverStoragegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT " +
                        "    EVIDENCE_IN_ID," +
                        "    ARREST_ID," +
                        "    DELIVERY_CODE," +
                        "    DELIVERY_NO," +
                        "    TO_CHAR(DELIVERY_DATE, 'yyyy-MM-dd') as DELIVERY_DATE," +
                        "    TO_CHAR(DELIVERY_DATE, 'HH:MM') as DELIVERY_TIME," +
                        "    DELIVERY_OFFICE_CODE," +
                        "    DELIVERY_OFFICE_NAME," +
                        "    DELIVERY_TITTLE," +
                        "    DELIVERY_DEAR," +
                        "    REMARK " +
                        " FROM OPS_EVIDENCE_IN" +
                        " WHERE IS_ACTIVE = 1 AND EVIDENCE_IN_ID = "+req.getEVIDENCE_IN_ID());

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<DeliveryStorageEvidenceIn>() {

            public DeliveryStorageEvidenceIn extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    DeliveryStorageEvidenceIn item = new DeliveryStorageEvidenceIn();
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                    item.setDELIVERY_CODE(rs.getString("DELIVERY_CODE"));
                    item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                    item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                    item.setDELIVERY_TIME(rs.getString("DELIVERY_TIME"));
                    item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                    item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                    item.setDELIVERY_TITTLE(rs.getString("DELIVERY_TITTLE"));
                    item.setDELIVERY_DEAR(rs.getString("DELIVERY_DEAR"));
                    item.setREMARK(rs.getString("REMARK"));
                    item.setDeliveryStorageEvidenceInItem(getDeliveryStorageEvidenceInItem(rs.getInt("EVIDENCE_IN_ID")));
                    item.setDeliveryStorageEvidenceInStaff(getDeliveryStorageEvidenceInStaff(rs.getInt("EVIDENCE_IN_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public Boolean DeliverStorageupdByCon(DeliveryStorageEvidenceIn req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_EVIDENCE_IN SET "
                        + "ARREST_ID=  '" + req.getARREST_ID() + "', "
                        + "DELIVERY_CODE=  '" + req.getDELIVERY_CODE() + "', "
                        + "DELIVERY_NO=  '" + req.getDELIVERY_NO() + "', "
                        + "DELIVERY_DATE=  TO_TIMESTAMP_TZ('" + req.getDELIVERY_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "DELIVERY_OFFICE_CODE=  (select office_code from mas_office where office_short_name = '"+req.getDELIVERY_OFFICE_NAME()+"'), "
                        + "DELIVERY_OFFICE_NAME=  '" + req.getDELIVERY_OFFICE_NAME() + "', "
                        + "REMARK=  '" + req.getREMARK() + "', "
                        + "DELIVERY_TITTLE=  '" + req.getDELIVERY_TITTLE() + "', "
                        + "DELIVERY_DEAR=  '" + req.getDELIVERY_DEAR() + "' "
                        + " WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        if (req.getDeliveryStorageEvidenceInStaff() != null) {
            log.info("[Sub] Size : " + req.getDeliveryStorageEvidenceInStaff().size());

            if (req.getDeliveryStorageEvidenceInStaff().size() > 0) {
                for (DeliveryStorageEvidenceInStaff staff : req.getDeliveryStorageEvidenceInStaff()) {

                    getJdbcTemplate().update("DELETE FROM OPS_EVIDENCE_IN_STAFF WHERE EVIDENCE_IN_ID = '"+req.getEVIDENCE_IN_ID()+"' AND CONTRIBUTOR_ID = 59 AND IS_ACTIVE = '1'", new Object[]{});

                    MasStaff item = masStaffDAO.MasStaffgetById(staff.getSTAFF_ID());

                    String EVIDENCE_IN_STAFF_ID = getSequences("SELECT OPS_EVIDENCE_IN_STAFF_SEQ.NEXTVAL FROM DUAL");

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
                                    "'" + req.getEVIDENCE_IN_ID() + "', " +
                                    "'" + item.getSTAFF_ID() + "'," +
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
                                    "''," +
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
                                    "'59'," +
                                    "'1') ");
                    log.info("[SQL] : " + sqlBuilderSubStaff.toString());

                    getJdbcTemplate().update(sqlBuilderSubStaff.toString(), new Object[]{});

                }
            }
        }
        return true;
    }

    @Override
    public Boolean DeliverStorageupdDelete(DeliverStorageupdDeleteReq req) {


        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_EVIDENCE_IN SET IS_ACTIVE = '0' WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_EVIDENCE_IN_ITEM SET IS_ACTIVE = '0' WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");
        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_EVIDENCE_IN_STAFF SET IS_ACTIVE = '0' WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");

        log.info("[SQL] ops_evidence_out : " + sqlBuilder1.toString());
        log.info("[SQL] ops_evidence_out_item : " + sqlBuilder2.toString());
        log.info("[SQL] ops_evidence_out_staff : " + sqlBuilder3.toString());

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[]{});
        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[]{});


        return true;

    }
}
