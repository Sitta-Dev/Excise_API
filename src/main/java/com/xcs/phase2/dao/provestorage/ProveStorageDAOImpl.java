package com.xcs.phase2.dao.provestorage;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.dao.compare.TransactionRunningDAO;
import com.xcs.phase2.dao.master.MasStaffDAO;
import com.xcs.phase2.model.compare.TransactionRunning;
import com.xcs.phase2.model.master.MasStaff;
import com.xcs.phase2.model.provestorage.*;
import com.xcs.phase2.request.compare.TransactionRunninggetByConReq;
import com.xcs.phase2.request.compare.TransactionRunningupdByConReq;
import com.xcs.phase2.request.provestorage.ProveStoragegetByConReq;
import com.xcs.phase2.request.provestorage.ProveStoragegetByCreateReq;
import com.xcs.phase2.request.provestorage.ProveStoragegetProductReq;
import com.xcs.phase2.request.provestorage.ProveStorageupdDeleteReq;
import com.xcs.phase2.units.StringUtil;
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
public class ProveStorageDAOImpl extends ProveStorageExt implements ProveStorageDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveStorageDAOImpl.class);

    @Autowired
    private MasStaffDAO masStaffDAO;

    @Autowired
    private TransactionRunningDAO transactionRunningDAO;


    @Override
    public ProveStorage ProveStoragegetByCreate(ProveStoragegetByCreateReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    OPS_EVIDENCE_IN.DELIVERY_CODE," +
                        "    OPS_EVIDENCE_IN.DELIVERY_OFFICE_NAME," +
                        "    OPS_EVIDENCE_IN.DELIVERY_NO," +
                        "    TO_CHAR(OPS_EVIDENCE_IN.DELIVERY_DATE, 'dd MON yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as DELIVERY_DATE," +
                        "    TO_CHAR(OPS_EVIDENCE_IN.DELIVERY_DATE, 'HH:MM') as DELIVERY_TIME," +
                        "    OPS_EVIDENCE_IN.DELIVERY_TITTLE," +
                        "    OPS_EVIDENCE_IN.DELIVERY_DEAR," +
                        "    case when OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_TH is null or OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_EVIDENCE_IN_STAFF.TITLE_NAME_TH ||''|| OPS_EVIDENCE_IN_STAFF.FIRST_NAME ||' '|| OPS_EVIDENCE_IN_STAFF.LAST_NAME " +
                        "    else OPS_EVIDENCE_IN_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_EVIDENCE_IN_STAFF.FIRST_NAME ||' '|| OPS_EVIDENCE_IN_STAFF.LAST_NAME end as DELIVERY_FULL_NAME, " +
                        "    OPS_EVIDENCE_IN_STAFF.OPREATION_POS_NAME as DELIVERY_OPERATION_POS_NAME, " +
                        "    OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_SHORT_NAME as DELIVERY_OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_ARREST.ARREST_CODE," +
                        "    TO_CHAR(OPS_ARREST.OCCURRENCE_DATE, 'dd MON yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') as OCCURRENCE_DATE," +
                        "    TO_CHAR(OPS_ARREST.OCCURRENCE_DATE, 'HH:MM') as OCCURRENCE_TIME," +
                        "    case when OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH is null or OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH = 'null' THEN OPS_ARREST_STAFF.TITLE_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME " +
                        "    else OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH ||''|| OPS_ARREST_STAFF.FIRST_NAME ||' '|| OPS_ARREST_STAFF.LAST_NAME end as ARREST_STAFF_NAME, " +
                        "    OPS_ARREST_STAFF.OPREATION_POS_NAME ARREST_OPREATION_POS_NAME,  " +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_SHORT_NAME ARREST_OPERATION_OFFICE_SHORT_NAME," +
                        "    OPS_ARREST.OFFICE_NAME ARREST_OFFICE_NAME," +
                        "    OPS_EVIDENCE_IN.EVIDENCE_IN_ID," +
                        "    OPS_EVIDENCE_IN.DELIVERY_OFFICE_CODE," +
                        "    OPS_EVIDENCE_IN_STAFF.OPERATION_OFFICE_NAME," +
                        "    OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID," +
                        "    OPS_ARREST.ARREST_ID," +
                        "    OPS_ARREST_STAFF.OPERATION_OFFICE_NAME ARREST_OPERATION_OFFICE_NAME" +
                        "    FROM OPS_EVIDENCE_IN" +
                        "    INNER JOIN OPS_ARREST ON OPS_EVIDENCE_IN.ARREST_ID = OPS_ARREST.ARREST_ID AND OPS_ARREST.IS_ACTIVE =1" +
                        "    INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID AND OPS_ARREST_STAFF.IS_ACTIVE =1" +
                        "    INNER JOIN OPS_EVIDENCE_IN_STAFF ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID = OPS_EVIDENCE_IN_STAFF.EVIDENCE_IN_ID " +
                        "    AND OPS_EVIDENCE_IN_STAFF.IS_ACTIVE =1" +
                        "    AND OPS_EVIDENCE_IN_STAFF.CONTRIBUTOR_ID = 59 " +
                        "    WHERE  OPS_EVIDENCE_IN.EVIDENCE_IN_ID = "+req.getEVIDENCE_IN_ID());

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<ProveStorage>() {

            public ProveStorage extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    ProveStorage item = new ProveStorage();
                    item.setDELIVERY_CODE(rs.getString("DELIVERY_CODE"));
                    item.setDELIVERY_OFFICE_NAME(rs.getString("DELIVERY_OFFICE_NAME"));
                    item.setDELIVERY_NO(rs.getString("DELIVERY_NO"));
                    item.setDELIVERY_DATE(rs.getString("DELIVERY_DATE"));
                    item.setDELIVERY_TIME(rs.getString("DELIVERY_TIME"));
                    item.setDELIVERY_TITTLE(rs.getString("DELIVERY_TITTLE"));
                    item.setDELIVERY_DEAR(rs.getString("DELIVERY_DEAR"));
                    item.setDELIVERY_FULL_NAME(rs.getString("DELIVERY_FULL_NAME"));
                    item.setDELIVERY_OPERATION_POS_NAME(rs.getString("DELIVERY_OPERATION_POS_NAME"));
                    item.setDELIVERY_OPERATION_OFFICE_SHORT_NAME(rs.getString("DELIVERY_OPERATION_OFFICE_SHORT_NAME"));
                    item.setARREST_CODE(rs.getString("ARREST_CODE"));
                    item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                    item.setOCCURRENCE_TIME(rs.getString("OCCURRENCE_TIME"));
                    item.setARREST_STAFF_NAME(rs.getString("ARREST_STAFF_NAME"));
                    item.setARREST_OPREATION_POS_NAME(rs.getString("ARREST_OPREATION_POS_NAME"));
                    item.setARREST_OPERATION_OFFICE_SHORT_NAME(rs.getString("ARREST_OPERATION_OFFICE_SHORT_NAME"));
                    item.setARREST_OFFICE_NAME(rs.getString("ARREST_OFFICE_NAME"));
                    item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                    item.setDELIVERY_OFFICE_CODE(rs.getString("DELIVERY_OFFICE_CODE"));
                    item.setOPERATION_OFFICE_NAME(rs.getString("OPERATION_OFFICE_NAME"));
                    item.setCONTRIBUTOR_ID(rs.getInt("CONTRIBUTOR_ID"));
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setARREST_OPERATION_OFFICE_NAME(rs.getString("ARREST_OPERATION_OFFICE_NAME"));

                    item.setProveStorageLawsuitDetail(getProveStorageLawsuitDetail(rs.getInt("ARREST_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public List<ProveStorageProduct> ProveStorageProduct(ProveStoragegetProductReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT " +
                        "        OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID," +
                        "        OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE," +
                        "        OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID," +
                        "        OPS_EVIDENCE_IN_ITEM.PRODUCT_DESC," +
                        "        OPS_EVIDENCE_IN_ITEM.DELIVERY_QTY," +
                        "        OPS_EVIDENCE_IN_ITEM.DELIVERY_QTY_UNIT," +
                        "        OPS_EVIDENCE_IN_ITEM.DELIVERY_SIZE," +
                        "        OPS_EVIDENCE_IN_ITEM.DELIVERY_SIZE_UNIT," +
                        "        OPS_EVIDENCE_IN_ITEM.DELIVERY_NET_VOLUMN," +
                        "        OPS_EVIDENCE_IN_ITEM.DELIVERY_NET_VOLUMN_UNIT" +
                        "    FROM OPS_EVIDENCE_IN_ITEM " +
                        "    WHERE EVIDENCE_IN_ID = "+req.getEVIDENCE_IN_ID()+"");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<ProveStorageProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveStorageProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveStorageProduct item = new ProveStorageProduct();
                item.setEVIDENCE_IN_ITEM_ID(rs.getInt("EVIDENCE_IN_ITEM_ID"));
                item.setEVIDENCE_IN_ITEM_CODE(rs.getString("EVIDENCE_IN_ITEM_CODE"));
                item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setDELIVERY_QTY(rs.getFloat("DELIVERY_QTY"));
                item.setDELIVERY_QTY_UNIT(rs.getString("DELIVERY_QTY_UNIT"));
                item.setDELIVERY_SIZE(rs.getFloat("DELIVERY_SIZE"));
                item.setDELIVERY_SIZE_UNIT(rs.getString("DELIVERY_SIZE_UNIT"));
                item.setDELIVERY_NET_VOLUMN(rs.getFloat("DELIVERY_NET_VOLUMN"));
                item.setDELIVERY_NET_VOLUMN_UNIT(rs.getString("DELIVERY_NET_VOLUMN_UNIT"));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public Boolean ProveStorageinsAll(ProveStorageEvidenceIn req) {


        try {

            StringBuilder sqlBuilder = new StringBuilder()
                    .append("UPDATE OPS_EVIDENCE_IN SET "
                            + "EVIDENCE_IN_CODE=  '" + req.getEVIDENCE_IN_CODE() + "', "
                            + "EVIDENCE_IN_DATE=  TO_TIMESTAMP_TZ('" + req.getEVIDENCE_IN_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                            + "RECEIVE_OFFICE_CODE=  (select office_code from mas_office where office_short_name = '"+req.getRECEIVE_OFFICE_NAME()+"'), "
                            + "RECEIVE_OFFICE_NAME=  '" + req.getRECEIVE_OFFICE_NAME() + "', "
                            + "COMMENT1=  '" + req.getCOMMENT1() + "', "
                            + "IS_RECEIVE=  '1', "
                            + "IS_EDIT=  '0' "
                            + " WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");

            log.info("[SQL] : " + sqlBuilder.toString());
           getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});


            if (req.getProveStorageEvidenceInItem() != null) {
                log.info("[Sub] Size : " + req.getProveStorageEvidenceInItem().size());

                if (req.getProveStorageEvidenceInItem().size() > 0) {

                    for (ProveStorageEvidenceInItem item : req.getProveStorageEvidenceInItem()) {



                        // getEVIDENCE_IN_ITEM_CODE
                        String TABLE = "OPS_EVIDENCE_IN_IN";
                        String PREFIX = "IN";
                        String EVIDENCE_IN_ITEM_CODE = "";
                        //String OFFICE_CODE = this.getOfficeCode((req.getRECEIVE_OFFICE_CODE()));
                        String OFFICE_CODE = req.getRECEIVE_OFFICE_CODE();
                        String year = StringUtil.getYear();

                        System.out.println("OFFICE_CODE : "+OFFICE_CODE);

                        TransactionRunninggetByConReq req1 = new TransactionRunninggetByConReq();
                        req1.setRUNNING_OFFICE_CODE(OFFICE_CODE);
                        req1.setRUNNING_TABLE(TABLE);

                        TransactionRunning obj = transactionRunningDAO.getTransactionRunninggetByCon(req1);

                        System.out.println("obj : "+OFFICE_CODE);

                        if(obj != null){
                            int runningNo = obj.getRUNNING_NO()+1;
                            EVIDENCE_IN_ITEM_CODE = PREFIX+OFFICE_CODE+year+String.format("%05d" , runningNo);
                            TransactionRunningupdByConReq transactionRunning = new TransactionRunningupdByConReq();
                            transactionRunning.setRUNNING_ID(obj.getRUNNING_ID());
                            transactionRunningDAO.TransactionRunningupdByCon(transactionRunning);
                        }else{
                            EVIDENCE_IN_ITEM_CODE = PREFIX+OFFICE_CODE+year+String.format("%05d" , 1);
                            TransactionRunning transactionRunning = new TransactionRunning();
                            transactionRunning.setRUNNING_OFFICE_CODE(OFFICE_CODE);
                            transactionRunning.setRUNNING_TABLE(TABLE);
                            transactionRunning.setRUNNING_PREFIX(PREFIX);
                            transactionRunning.setRUNNING_OFFICE_ID(2);
                            transactionRunningDAO.TransactionRunninginsAll(transactionRunning);
                        }

                        System.out.println("EVIDENCE_IN_ITEM_CODE : "+EVIDENCE_IN_ITEM_CODE);

                        StringBuilder sqlBuilderInItem = new StringBuilder()
                                .append("UPDATE OPS_EVIDENCE_IN_ITEM SET "
                                        + "EVIDENCE_IN_ITEM_CODE=  '" + EVIDENCE_IN_ITEM_CODE + "', "
                                        + "DAMAGE_QTY=  '" + item.getDAMAGE_QTY() + "', "
                                        + "DAMAGE_QTY_UNIT=  '" + item.getDAMAGE_QTY_UNIT() + "', "
                                        + "DAMAGE_SIZE=  '" + item.getDAMAGE_SIZE() + "', "
                                        + "DAMAGE_SIZE_UNIT=  '" + item.getDAMAGE_SIZE_UNIT() + "', "
                                        + "DAMAGE_NET_VOLUMN=  '" + item.getDAMAGE_NET_VOLUMN() + "', "
                                        + "DAMAGE_NET_VOLUMN_UNIT=  '" + item.getDAMAGE_NET_VOLUMN_UNIT() + "', "
                                        + "REMARK=  '" + item.getREMARK() + "' "
                                        + " WHERE EVIDENCE_IN_ITEM_ID = '" + item.getEVIDENCE_IN_ITEM_ID() + "' ");
                        log.info("[SQL] : " + sqlBuilderInItem.toString());
                        getJdbcTemplate().update(sqlBuilderInItem.toString(), new Object[]{});


                        String STOCK_ID = getSequences("SELECT OPS_EVIDENCE_STOCK_BALANCE_SEQ.NEXTVAL FROM DUAL");

                        StringBuilder sqlBuilderSub1 = new StringBuilder()
                                .append("INSERT INTO OPS_EVIDENCE_STOCK_BALANCE (" +
                                        "STOCK_ID," +
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
                                        "'" + item.getEVIDENCE_IN_ITEM_ID() + "'," +
                                        "'" + item.getRECEIVE_QTY() + "'," +
                                        "'" + item.getRECEIVE_QTY_UNIT() + "'," +
                                        "'" + item.getRECEIVE_SIZE() + "'," +
                                        "'" + item.getRECEIVE_SIZE_UNIT() + "'," +
                                        "'" + item.getRECEIVE_NET_VOLUMN() + "'," +
                                        "'" + item.getRECEIVE_NET_VOLUMN_UNIT() + "'," +
                                        "'" + item.getBALANCE_QTY() + "'," +
                                        "'" + item.getBALANCE_QTY_UNIT() + "'," +
                                        "'" + item.getBALANCE_SIZE() + "'," +
                                        "'" + item.getBALANCE_SIZE_UNIT() + "'," +
                                        "'" + item.getBALANCE_NET_VOLUMN() + "'," +
                                        "'" + item.getBALANCE_NET_VOLUMN_UNIT() + "'," +
                                        "'2'," +
                                        "'1')");
                        log.info("[SQL] Sub : " + sqlBuilderSub1.toString());
                        getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[]{});
                    }
                }
            }

            if (req.getProveStorageEvidenceInStaff() != null) {
                log.info("[Sub] Size : " + req.getProveStorageEvidenceInStaff().size());

                if (req.getProveStorageEvidenceInStaff().size() > 0) {
                    for (ProveStorageEvidenceInStaff staff : req.getProveStorageEvidenceInStaff()) {

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
                                        "'" + staff.getCONTRIBUTOR_ID() + "'," +
                                        "'1') ");
                        log.info("[SQL] : " + sqlBuilderSubStaff.toString());

                        getJdbcTemplate().update(sqlBuilderSubStaff.toString(), new Object[]{});
                    }
                }
            }


            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProveStorageEvidenceIn ProveStoragegetByCon(ProveStoragegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" SELECT " +
                        "    EVIDENCE_IN_ID," +
                        "    EVIDENCE_IN_CODE," +
                        "    TO_CHAR(EVIDENCE_IN_DATE, 'yyyy-MM-dd') as EVIDENCE_IN_DATE," +
                        "    TO_CHAR(EVIDENCE_IN_DATE, 'HH:MM') as EVIDENCE_IN_TIME," +
                        "    RECEIVE_OFFICE_CODE," +
                        "    RECEIVE_OFFICE_NAME," +
                        "    COMMENT1 " +
                        " FROM OPS_EVIDENCE_IN" +
                        " WHERE IS_ACTIVE = 1 AND EVIDENCE_IN_ID = "+req.getEVIDENCE_IN_ID());

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<ProveStorageEvidenceIn>() {

            public ProveStorageEvidenceIn extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    ProveStorageEvidenceIn item = new ProveStorageEvidenceIn();
                    item.setEVIDENCE_IN_ID(rs.getInt("EVIDENCE_IN_ID"));
                    item.setEVIDENCE_IN_CODE(rs.getString("EVIDENCE_IN_CODE"));
                    item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                    item.setEVIDENCE_IN_TIME(rs.getString("EVIDENCE_IN_TIME"));
                    item.setRECEIVE_OFFICE_CODE(rs.getString("RECEIVE_OFFICE_CODE"));
                    item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                    item.setCOMMENT1(rs.getString("COMMENT1"));
                    item.setProveStorageEvidenceInItem(getProveStorageEvidenceInItem(rs.getInt("EVIDENCE_IN_ID")));
                    item.setProveStorageEvidenceInStaff(getProveStorageEvidenceInStaff(rs.getInt("EVIDENCE_IN_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public Boolean ProveStorageupdByCon(ProveStorageEvidenceIn req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_EVIDENCE_IN SET "
                        + "EVIDENCE_IN_CODE=  '" + req.getEVIDENCE_IN_CODE() + "', "
                        + "EVIDENCE_IN_DATE=  TO_TIMESTAMP_TZ('" + req.getEVIDENCE_IN_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "RECEIVE_OFFICE_CODE=  (select office_code from mas_office where office_short_name = '"+req.getRECEIVE_OFFICE_NAME()+"'), "
                        + "RECEIVE_OFFICE_NAME=  '" + req.getRECEIVE_OFFICE_NAME() + "', "
                        + "COMMENT1=  '" + req.getCOMMENT1() + "', "
                        + "IS_RECEIVE=  '1', "
                        + "IS_EDIT=  '0' "
                        + " WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        if (req.getProveStorageEvidenceInItem() != null) {
            log.info("[Sub] Size : " + req.getProveStorageEvidenceInItem().size());

            if (req.getProveStorageEvidenceInItem().size() > 0) {

                for (ProveStorageEvidenceInItem item : req.getProveStorageEvidenceInItem()) {

                    StringBuilder sqlBuilderInItem = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_IN_ITEM SET "
                                    + "EVIDENCE_IN_ITEM_CODE=  '" + item.getEVIDENCE_IN_ITEM_CODE() + "', "
                                    + "DAMAGE_QTY=  '" + item.getDAMAGE_QTY() + "', "
                                    + "DAMAGE_QTY_UNIT=  '" + item.getDAMAGE_QTY_UNIT() + "', "
                                    + "DAMAGE_SIZE=  '" + item.getDAMAGE_SIZE() + "', "
                                    + "DAMAGE_SIZE_UNIT=  '" + item.getDAMAGE_SIZE_UNIT() + "', "
                                    + "DAMAGE_NET_VOLUMN=  '" + item.getDAMAGE_NET_VOLUMN() + "', "
                                    + "DAMAGE_NET_VOLUMN_UNIT=  '" + item.getDAMAGE_NET_VOLUMN_UNIT() + "', "
                                    + "REMARK=  '" + item.getREMARK() + "' "
                                    + " WHERE EVIDENCE_IN_ITEM_ID = '" + item.getEVIDENCE_IN_ITEM_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderInItem.toString());
                    getJdbcTemplate().update(sqlBuilderInItem.toString(), new Object[]{});

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_STOCK_BALANCE SET "
                                    + "RECEIVE_QTY=  '" + item.getRECEIVE_QTY() + "', "
                                    + "RECEIVE_QTY_UNIT=  '" + item.getRECEIVE_QTY_UNIT() + "', "
                                    + "RECEIVE_SIZE=  '" + item.getRECEIVE_SIZE() + "', "
                                    + "RECEIVE_SIZE_UNIT=  '" + item.getRECEIVE_SIZE_UNIT() + "', "
                                    + "RECEIVE_NET_VOLUMN=  '" + item.getRECEIVE_NET_VOLUMN() + "', "
                                    + "RECEIVE_NET_VOLUMN_UNIT=  '" + item.getRECEIVE_NET_VOLUMN_UNIT() + "', "
                                    + "BALANCE_QTY=  '" + item.getBALANCE_QTY() + "', "
                                    + "BALANCE_QTY_UNIT=  '" + item.getBALANCE_QTY_UNIT() + "', "
                                    + "BALANCE_SIZE=  '" + item.getBALANCE_SIZE() + "', "
                                    + "BALANCE_SIZE_UNIT=  '" + item.getBALANCE_SIZE_UNIT() + "', "
                                    + "BALANCE_NET_VOLUMN=  '" + item.getBALANCE_NET_VOLUMN() + "', "
                                    + "BALANCE_NET_VOLUMN_UNIT=  '" + item.getBALANCE_NET_VOLUMN_UNIT() + "' "
                                    + " WHERE STOCK_ID = '" + item.getSTOCK_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());
                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                }
            }
        }

        if (req.getProveStorageEvidenceInStaff() != null) {
            log.info("[Sub] Size : " + req.getProveStorageEvidenceInStaff().size());

            if (req.getProveStorageEvidenceInStaff().size() > 0) {

                getJdbcTemplate().update("DELETE FROM OPS_EVIDENCE_IN_STAFF WHERE EVIDENCE_IN_ID = '"+req.getEVIDENCE_IN_ID()+"' AND CONTRIBUTOR_ID IN(60,61,62,63) AND IS_ACTIVE = '1'", new Object[]{});

                for (ProveStorageEvidenceInStaff staff : req.getProveStorageEvidenceInStaff()) {

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
                                    "'" + staff.getCONTRIBUTOR_ID()+"'," +
                                    "'1') ");
                    log.info("[SQL] : " + sqlBuilderSubStaff.toString());

                    getJdbcTemplate().update(sqlBuilderSubStaff.toString(), new Object[]{});

                }
            }
        }

        return true;
    }

    @Override
    public Boolean ProveStorageupdDelete(ProveStorageupdDeleteReq req) {


        getJdbcTemplate().update("DELETE FROM OPS_EVIDENCE_STOCK_BALANCE WHERE EVIDENCE_IN_ITEM_ID IN (SELECT EVIDENCE_IN_ITEM_ID FROM OPS_EVIDENCE_IN_ITEM WHERE EVIDENCE_IN_ID = '"+req.getEVIDENCE_IN_ID()+"')", new Object[]{});


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_EVIDENCE_IN SET "
                        + "EVIDENCE_IN_CODE=  NULL, "
                        + "EVIDENCE_IN_DATE=  NULL, "
                        + "RECEIVE_OFFICE_CODE=  NULL, "
                        + "RECEIVE_OFFICE_NAME=  NULL, "
                        + "COMMENT1=  NULL, "
                        + "IS_RECEIVE=  '0', "
                        + "IS_EDIT=  '0' "
                        + " WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        StringBuilder sqlBuilderInItem = new StringBuilder()
                .append("UPDATE OPS_EVIDENCE_IN_ITEM SET "
                        + "EVIDENCE_IN_ITEM_CODE=  NULL, "
                        + "DAMAGE_QTY=  NULL, "
                        + "DAMAGE_QTY_UNIT=  NULL, "
                        + "DAMAGE_SIZE=  NULL, "
                        + "DAMAGE_SIZE_UNIT=  NULL, "
                        + "DAMAGE_NET_VOLUMN=  NULL, "
                        + "DAMAGE_NET_VOLUMN_UNIT=  NULL, "
                        + "REMARK=  NULL "
                        + " WHERE EVIDENCE_IN_ITEM_ID = '" + req.getEVIDENCE_IN_ID() + "' ");
        log.info("[SQL] : " + sqlBuilderInItem.toString());
        getJdbcTemplate().update(sqlBuilderInItem.toString(), new Object[]{});


        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_EVIDENCE_IN_STAFF SET IS_ACTIVE = '0' WHERE EVIDENCE_IN_ID = '" + req.getEVIDENCE_IN_ID() + "' AND CONTRIBUTOR_ID <> 59 ");


        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[]{});


        return true;

    }

    protected Integer getCount(String RUNNING_OFFICE_CODE) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT count(*) cnt" +
                        "    FROM OPS_TRANSACTION_RUNNING" +
                        "    WHERE RUNNING_TABLE = 'OPS_EVIDENCE_IN_IN'" +
                        "    AND RUNNING_OFFICE_CODE = '"+RUNNING_OFFICE_CODE+"'" +
                        "    AND RUNNING_YEAR = TO_CHAR(SYSDATE, 'yy' ,'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Integer>() {

            public Integer extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {


                    return rs.getInt("cnt");
                }

                return null;
            }
        });
    }

    protected String getOfficeCode(String OFFICE_SHORT_NAME) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT OFFICE_CODE FROM MAS_OFFICE WHERE OFFICE_SHORT_NAME = '"+OFFICE_SHORT_NAME+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<String>() {

            public String extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {


                    return rs.getString("OFFICE_CODE");
                }

                return null;
            }
        });
    }
}
