package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.dao.master.MasStaffDAO;
import com.xcs.phase2.model.evidenceout.EvidenceOut;
import com.xcs.phase2.model.evidenceout.EvidenceOutDetail;
import com.xcs.phase2.model.evidenceout.EvidenceOutItem;
import com.xcs.phase2.model.evidenceout.EvidenceOutStaff;
import com.xcs.phase2.model.master.MasStaff;
import com.xcs.phase2.request.evidenceout.EvidenceOutgetByConReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutupdDeleteReq;
import com.xcs.phase2.response.evidenceout.EvidenceOutDetailResponse;
import com.xcs.phase2.response.evidenceout.EvidenceOutItemResponse;
import com.xcs.phase2.response.evidenceout.EvidenceOutStaffResponse;
import com.xcs.phase2.response.evidenceout.EvidenceOutinsAllResponse;
import com.xcs.phase2.units.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EvidenceOutDAOImpl extends EvidenceOutExt implements EvidenceOutDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutListDAOImpl.class);

    @Autowired
    private MasStaffDAO masStaffDAO;


    @Override
    public EvidenceOut EvidenceOutgetByCon(EvidenceOutgetByConReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "EVIDENCE_OUT_ID," +
                        "OFFICE_CODE," +
                        "EVIDENCE_OUT_CODE," +
                        "    TO_CHAR(EVIDENCE_OUT_DATE, 'yyyy-MM-dd') as EVIDENCE_OUT_DATE," +
                        "    TO_CHAR(EVIDENCE_OUT_DATE, 'HH:MM') as EVIDENCE_OUT_TIME," +
                        "EVIDENCE_OUT_TYPE," +
                        "EVIDENCE_OUT_NO," +
                        "    TO_CHAR(EVIDENCE_OUT_NO_DATE, 'yyyy-MM-dd') as EVIDENCE_OUT_NO_DATE," +
                        "    TO_CHAR(EVIDENCE_OUT_NO_DATE, 'HH:MM') as EVIDENCE_OUT_NO_TIME," +
                        "BOOK_NO," +
                        "RECEIPT_NO," +
                        "PAY_DATE," +
                        "    TO_CHAR(APPROVE_DATE, 'yyyy-MM-dd') as APPROVE_DATE," +
                        "    TO_CHAR(APPROVE_DATE, 'HH:MM') as APPROVE_TIME," +
                        "RETURN_DATE," +
                        "REMARK," +
                        "APPROVE_NO," +
                        "IS_ACTIVE," +
                        "EVIDENCE_IN_ID," +
                        "WAREHOUSE_ID," +
                        "DELIVERY," +
                        "REMARK_CANCEL," +
                        "SEND_TO_OFFICE_CODE," +
                        "SEND_TO_OFFICE_NAME " +
                        " from OPS_EVIDENCE_OUT  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and EVIDENCE_OUT_ID =  '"+req.getEVIDENCE_OUT_ID()+"'  ");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceOut>() {

            public EvidenceOut extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceOut item = new EvidenceOut();
                    item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
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
                    item.setDELIVERY(rs.getString("DELIVERY"));
                    item.setREMARK_CANCEL(rs.getString("REMARK_CANCEL"));
                    item.setSEND_TO_OFFICE_CODE(rs.getString("SEND_TO_OFFICE_CODE"));
                    item.setSEND_TO_OFFICE_NAME(rs.getString("SEND_TO_OFFICE_NAME"));

                    item.setEVIDENCE_OUT_TIME(rs.getString("EVIDENCE_OUT_TIME"));
                    item.setEVIDENCE_OUT_NO_TIME(rs.getString("EVIDENCE_OUT_NO_TIME"));
                    item.setAPPROVE_TIME(rs.getString("APPROVE_TIME"));

                    item.setEvidenceOutItem(getEvidenceOutItem(rs.getInt("EVIDENCE_OUT_ID")));
                    item.setEvidenceOutStaff(getEvidenceOutStaffAll(rs.getInt("EVIDENCE_OUT_ID")));
                    item.setEvidenceOutDetail(getEvidenceOutDetail(rs.getInt("EVIDENCE_OUT_ID")));



                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public EvidenceOutinsAllResponse EvidenceOutinsAll(EvidenceOut req) {

        EvidenceOutinsAllResponse res = new EvidenceOutinsAllResponse();

        try {

            String EVIDENCE_OUT_ID = getSequences("SELECT OPS_EVIDENCE_OUT_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("INSERT INTO OPS_EVIDENCE_OUT (" +
                            "EVIDENCE_OUT_ID," +
                            "OFFICE_CODE," +
                            "EVIDENCE_OUT_CODE," +
                            "EVIDENCE_OUT_DATE," +
                            "EVIDENCE_OUT_TYPE," +
                            "EVIDENCE_OUT_NO," +
                            "EVIDENCE_OUT_NO_DATE," +
                            "BOOK_NO," +
                            "RECEIPT_NO," +
                            "PAY_DATE," +
                            "APPROVE_DATE," +
                            "RETURN_DATE," +
                            "REMARK," +
                            "APPROVE_NO," +
                            "IS_ACTIVE," +
                            "EVIDENCE_IN_ID," +
                            "WAREHOUSE_ID," +
                            "DELIVERY," +
                            "REMARK_CANCEL," +
                            "SEND_TO_OFFICE_CODE," +
                            "SEND_TO_OFFICE_NAME" +
                            " ) VALUES (" +
                            "'"+EVIDENCE_OUT_ID+"'," +
                            "'"+req.getOFFICE_CODE()+"'," +
                            "'"+req.getEVIDENCE_OUT_CODE()+"'," +
                            "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getEVIDENCE_OUT_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'"+req.getEVIDENCE_OUT_TYPE()+"'," +
                            "'"+req.getEVIDENCE_OUT_NO()+"'," +
                            "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getEVIDENCE_OUT_NO_DATE()) + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'"+req.getBOOK_NO()+"'," +
                            "'"+req.getRECEIPT_NO()+"'," +
                            "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getPAY_DATE())  + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getAPPROVE_DATE())  + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getRETURN_DATE())  + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'"+req.getREMARK()+"'," +
                            "'"+req.getAPPROVE_NO()+"'," +
                            "'1'," +
                            "'"+req.getEVIDENCE_IN_ID()+"'," +
                            "'"+req.getWAREHOUSE_ID()+"'," +
                            "'"+req.getDELIVERY()+"'," +
                            "'"+req.getREMARK_CANCEL()+"'," +
                            "'"+req.getSEND_TO_OFFICE_CODE()+"'," +
                            "'"+req.getSEND_TO_OFFICE_NAME()+"')");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setEVIDENCE_OUT_ID(Integer.parseInt(EVIDENCE_OUT_ID));

            if (req.getEvidenceOutDetail() != null) {
                log.info("[Sub] Size : " + req.getEvidenceOutDetail().size());
                List<EvidenceOutDetailResponse> list = new ArrayList<EvidenceOutDetailResponse>();

                if (req.getEvidenceOutDetail().size() > 0) {

                    for (EvidenceOutDetail item : req.getEvidenceOutDetail()) {

                        String EVIDENCE_OUT_DETAIL_ID = getSequences("SELECT OPS_EVIDENCE_OUT_DETAIL_SEQ.NEXTVAL FROM DUAL");
                        EvidenceOutDetailResponse obj = new EvidenceOutDetailResponse();
                        obj.setEVIDENCE_OUT_DETAIL_ID(Integer.parseInt(EVIDENCE_OUT_DETAIL_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("INSERT INTO OPS_EVIDENCE_OUT_DETAIL (" +
                                        "EVIDENCE_OUT_DETAIL_ID," +
                                        "EVIDENCE_OUT_ID," +
                                        "EVIDENCE_IN_ID," +
                                        "IS_ACTIVE" +
                                        " ) VALUES (" +
                                        "'" + EVIDENCE_OUT_DETAIL_ID + "', " +
                                        "'" + EVIDENCE_OUT_ID + "', " +
                                        "'" + item.getEVIDENCE_IN_ID() + "'," +
                                        "'" + item.getIS_ACTIVE() + "' )");
                        log.info("[SQL] Sub : " + sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setEvidenceOutDetailResponse(list);
            }

            if (req.getEvidenceOutItem() != null) {
                log.info("[Sub] Size : " + req.getEvidenceOutItem().size());
                List<EvidenceOutItemResponse> list = new ArrayList<EvidenceOutItemResponse>();

                if (req.getEvidenceOutItem().size() > 0) {

                    for (EvidenceOutItem item : req.getEvidenceOutItem()) {

                        String EVIDENCE_OUT_ITEM_ID = getSequences("SELECT OPS_EVIDENCE_OUT_ITEM_SEQ.NEXTVAL FROM DUAL");
                        EvidenceOutItemResponse obj = new EvidenceOutItemResponse();
                        obj.setEVIDENCE_OUT_ITEM_ID(Integer.parseInt(EVIDENCE_OUT_ITEM_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("INSERT INTO OPS_EVIDENCE_OUT_ITEM (" +
                                        "EVIDENCE_OUT_ITEM_ID," +
                                        "EVIDENCE_OUT_ID," +
                                        "STOCK_ID," +
                                        "QTY," +
                                        "QTY_UNIT," +
                                        "PRODUCT_SIZE," +
                                        "PRODUCT_SIZE_UNIT," +
                                        "NET_VOLUMN," +
                                        "NET_VOLUMN_UNIT," +
//                                        "IS_RETURN," +
//                                        "COST," +
//                                        "RECEIPT_NO," +
//                                        "BOOK_NO," +
                                        "IS_ACTIVE" +
                                        " ) VALUES (" +
                                        "'" + EVIDENCE_OUT_ITEM_ID + "', " +
                                        "'" + EVIDENCE_OUT_ID + "', " +
                                        "'" + item.getSTOCK_ID() + "'," +
                                        "'" + item.getQTY() + "'," +
                                        "'" + item.getQTY_UNIT() + "'," +
                                        "'" + item.getPRODUCT_SIZE() + "'," +
                                        "'" + item.getPRODUCT_SIZE_UNIT() + "'," +
                                        "'" + item.getNET_VOLUMN() + "'," +
                                        "'" + item.getNET_VOLUMN_UNIT() + "'," +
//                                        "'" + item.getIS_RETURN() + "'," +
//                                        "'" + item.getCOST() + "'," +
//                                        "'" + item.getRECEIPT_NO() + "'," +
//                                        "'" + item.getBOOK_NO() + "'," +
                                        "'1' )");
                        log.info("[SQL] Sub : " + sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        UpdateIsFinishStockBalance(item.getSTOCK_ID());
                        list.add(obj);
                    }
                }
                res.setEvidenceOutItemResponse(list);
            }

            if (req.getEvidenceOutStaff() != null) {
                log.info("[Sub] Size : " + req.getEvidenceOutStaff().size());
                List<EvidenceOutStaffResponse> list = new ArrayList<EvidenceOutStaffResponse>();

                if (req.getEvidenceOutStaff().size() > 0) {
                    for (EvidenceOutStaff staff : req.getEvidenceOutStaff()) {

                        String EVIDENCE_OUT_STAFF_ID = getSequences("SELECT OPS_EVIDENCE_OUT_STAFF_SEQ.NEXTVAL FROM DUAL");
                        EvidenceOutStaffResponse obj = new EvidenceOutStaffResponse();
                        obj.setEVIDENCE_OUT_STAFF_ID(Integer.parseInt(EVIDENCE_OUT_STAFF_ID));

                        MasStaff item = masStaffDAO.MasStaffgetById(staff.getSTAFF_ID());

                        StringBuilder sqlBuilderSubStaff = new StringBuilder()
                                .append("INSERT INTO OPS_EVIDENCE_OUT_STAFF (" +
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
                                        "IS_ACTIVE " +
                                        " ) VALUES (" +
                                        "'" + EVIDENCE_OUT_STAFF_ID + "', " +
                                        "'" + EVIDENCE_OUT_ID + "', " +
                                        "'" + staff.getSTAFF_ID() + "'," +
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
                                        "'1' )");
                        log.info("[SQL] : " + sqlBuilderSubStaff.toString());

                        getJdbcTemplate().update(sqlBuilderSubStaff.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setEvidenceOutStaffResponse(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setEVIDENCE_OUT_ID(0);
            res.setEvidenceOutDetailResponse(null);
            res.setEvidenceOutStaffResponse(null);
            return res;
        }

    }

    @Override
    public Boolean EvidenceOutupdByCon(EvidenceOut req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_EVIDENCE_OUT SET "
                        + "OFFICE_CODE	=  '"+req.getOFFICE_CODE()+"', "
                        + "EVIDENCE_OUT_CODE=  '"+req.getEVIDENCE_OUT_CODE()+"', "
                        + "EVIDENCE_OUT_DATE=  TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getEVIDENCE_OUT_DATE())  + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "EVIDENCE_OUT_TYPE=  '"+req.getEVIDENCE_OUT_TYPE()+"', "
                        + "EVIDENCE_OUT_NO=  '"+req.getEVIDENCE_OUT_NO()+"', "
                        + "EVIDENCE_OUT_NO_DATE=  TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getEVIDENCE_OUT_NO_DATE())  + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "BOOK_NO=  '"+req.getBOOK_NO()+"', "
                        + "RECEIPT_NO=  '"+req.getRECEIPT_NO()+"', "
                        + "PAY_DATE=  TO_TIMESTAMP_TZ('" +  StringUtil.checkNull(req.getPAY_DATE())   + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "APPROVE_DATE=  TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getAPPROVE_DATE())   + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "RETURN_DATE=  TO_TIMESTAMP_TZ('" + StringUtil.checkNull(req.getRETURN_DATE())  + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "REMARK=  '"+req.getREMARK()+"', "
                        + "APPROVE_NO=  '"+req.getAPPROVE_NO()+"', "
                        + "IS_ACTIVE=  '"+req.getIS_ACTIVE()+"', "
                        + "EVIDENCE_IN_ID=  '"+req.getEVIDENCE_IN_ID()+"', "
                        + "WAREHOUSE_ID=  '"+req.getWAREHOUSE_ID()+"', "
                        + "DELIVERY=  '"+req.getDELIVERY()+"', "
                        + "REMARK_CANCEL=  '"+req.getREMARK_CANCEL()+"', "
                        + "SEND_TO_OFFICE_CODE=  '"+req.getSEND_TO_OFFICE_CODE()+"', "
                        + "SEND_TO_OFFICE_NAME=  '"+req.getSEND_TO_OFFICE_NAME()+"' "
                        + " WHERE EVIDENCE_OUT_ID = '" + req.getEVIDENCE_OUT_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

//        if (req.getEvidenceOutDetail() != null) {
//            log.info("[Sub] Size : " + req.getEvidenceOutDetail().size());
//
//            if (req.getEvidenceOutDetail().size() > 0) {
//                for (EvidenceOutDetail item : req.getEvidenceOutDetail()) {
//
//                    StringBuilder sqlBuilderSub = new StringBuilder()
//                            .append("UPDATE OPS_EVIDENCE_OUT_ITEM SET "
//                                    + "EVIDENCE_OUT_ID=  '" + item.getEVIDENCE_OUT_ID() + "', "
//                                    + "EVIDENCE_IN_ID=  '" + item.getEVIDENCE_IN_ID() + "', "
//                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
//                                    + " WHERE EVIDENCE_OUT_DETAIL_ID = '" + item.getEVIDENCE_OUT_DETAIL_ID() + "' ");
//                    log.info("[SQL] : " + sqlBuilderSub.toString());
//
//                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
//                }
//            }
//        }


        if (req.getEvidenceOutStaff() != null) {
            log.info("[Sub] Size : " + req.getEvidenceOutStaff().size());

            if (req.getEvidenceOutStaff().size() > 0) {
                for (EvidenceOutStaff staff : req.getEvidenceOutStaff()) {

                    getJdbcTemplate().update("DELETE FROM OPS_EVIDENCE_OUT_STAFF WHERE EVIDENCE_OUT_ID = '"+req.getEVIDENCE_OUT_ID()+"' AND CONTRIBUTOR_ID = "+staff.getCONTRIBUTOR_ID()+" AND IS_ACTIVE = '1'", new Object[]{});

                    String EVIDENCE_OUT_STAFF_ID = getSequences("SELECT OPS_EVIDENCE_OUT_STAFF_SEQ.NEXTVAL FROM DUAL");

                    MasStaff item = masStaffDAO.MasStaffgetById(staff.getSTAFF_ID());

                    StringBuilder sqlBuilderSubStaff = new StringBuilder()
                            .append("INSERT INTO OPS_EVIDENCE_OUT_STAFF (" +
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
                                    "IS_ACTIVE " +
                                    " ) VALUES (" +
                                    "'" + EVIDENCE_OUT_STAFF_ID + "', " +
                                    "'" + req.getEVIDENCE_OUT_ID() + "', " +
                                    "'" + staff.getSTAFF_ID() + "'," +
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
                                    "'1' )");
                    log.info("[SQL] : " + sqlBuilderSubStaff.toString());

                    getJdbcTemplate().update(sqlBuilderSubStaff.toString(), new Object[]{});

//                    StringBuilder sqlBuilderSub = new StringBuilder()
//                            .append("UPDATE OPS_EVIDENCE_OUT_STAFF SET "
//                                    + "EVIDENCE_OUT_ID=  '" + item.getEVIDENCE_OUT_ID() + "', "
//                                    + "STAFF_REF_ID=  '" + item.getSTAFF_REF_ID() + "', "
//                                    + "TITLE_ID=  '" + item.getTITLE_ID() + "', "
//                                    + "STAFF_CODE=  '" + item.getSTAFF_CODE() + "', "
//                                    + "ID_CARD=  '" + item.getID_CARD() + "', "
//                                    + "STAFF_TYPE=  '" + item.getSTAFF_TYPE() + "', "
//                                    + "TITLE_NAME_TH=  '" + item.getTITLE_NAME_TH() + "', "
//                                    + "TITLE_NAME_EN=  '" + item.getTITLE_NAME_EN() + "', "
//                                    + "TITLE_SHORT_NAME_TH=  '" + item.getTITLE_SHORT_NAME_TH() + "', "
//                                    + "TITLE_SHORT_NAME_EN=  '" + item.getTITLE_SHORT_NAME_EN() + "', "
//                                    + "FIRST_NAME=  '" + item.getFIRST_NAME() + "', "
//                                    + "LAST_NAME=  '" + item.getLAST_NAME() + "', "
//                                    + "AGE=  '" + item.getAGE() + "', "
//                                    + "OPERATION_POS_CODE=  '" + item.getOPERATION_POS_CODE() + "', "
//                                    + "OPREATION_POS_NAME=  '" + item.getOPREATION_POS_NAME() + "', "
//                                    + "OPREATION_POS_LEVEL=  '" + item.getOPREATION_POS_LEVEL() + "', "
//                                    + "OPERATION_POS_LEVEL_NAME=  '" + item.getOPERATION_POS_LEVEL_NAME() + "', "
//                                    + "OPERATION_DEPT_CODE=  '" + item.getOPERATION_DEPT_CODE() + "', "
//                                    + "OPERATION_DEPT_NAME=  '" + item.getOPERATION_DEPT_NAME() + "', "
//                                    + "OPERATION_DEPT_LEVEL=  '" + item.getOPERATION_DEPT_LEVEL() + "', "
//                                    + "OPERATION_UNDER_DEPT_CODE=  '" + item.getOPERATION_UNDER_DEPT_CODE() + "', "
//                                    + "OPERATION_UNDER_DEPT_NAME=  '" + item.getOPERATION_UNDER_DEPT_NAME() + "', "
//                                    + "OPERATION_UNDER_DEPT_LEVEL=  '" + item.getOPERATION_UNDER_DEPT_LEVEL() + "', "
//                                    + "OPERATION_WORK_DEPT_CODE=  '" + item.getOPERATION_WORK_DEPT_CODE() + "', "
//                                    + "OPERATION_WORK_DEPT_NAME=  '" + item.getOPERATION_WORK_DEPT_NAME() + "', "
//                                    + "OPERATION_WORK_DEPT_LEVEL=  '" + item.getOPERATION_WORK_DEPT_LEVEL() + "', "
//                                    + "OPERATION_OFFICE_CODE=  '" + item.getOPERATION_OFFICE_CODE() + "', "
//                                    + "OPERATION_OFFICE_NAME=  '" + item.getOPERATION_OFFICE_NAME() + "', "
//                                    + "OPERATION_OFFICE_SHORT_NAME=  '" + item.getOPERATION_OFFICE_SHORT_NAME() + "', "
//                                    + "MANAGEMENT_POS_CODE=  '" + item.getMANAGEMENT_POS_CODE() + "', "
//                                    + "MANAGEMENT_POS_NAME=  '" + item.getMANAGEMENT_POS_NAME() + "', "
//                                    + "MANAGEMENT_POS_LEVEL=  '" + item.getMANAGEMENT_POS_LEVEL() + "', "
//                                    + "MANAGEMENT_POS_LEVEL_NAME=  '" + item.getMANAGEMENT_POS_LEVEL_NAME() + "', "
//                                    + "MANAGEMENT_DEPT_CODE=  '" + item.getMANAGEMENT_DEPT_CODE() + "', "
//                                    + "MANAGEMENT_DEPT_NAME=  '" + item.getMANAGEMENT_DEPT_NAME() + "', "
//                                    + "MANAGEMENT_DEPT_LEVEL=  '" + item.getMANAGEMENT_DEPT_LEVEL() + "', "
//                                    + "MANAGEMENT_UNDER_DEPT_CODE=  '" + item.getMANAGEMENT_UNDER_DEPT_CODE() + "', "
//                                    + "MANAGEMENT_UNDER_DEPT_NAME=  '" + item.getMANAGEMENT_UNDER_DEPT_NAME() + "', "
//                                    + "MANAGEMENT_UNDER_DEPT_LEVEL=  '" + item.getMANAGEMENT_UNDER_DEPT_LEVEL() + "', "
//                                    + "MANAGEMENT_WORK_DEPT_CODE=  '" + item.getMANAGEMENT_WORK_DEPT_CODE() + "', "
//                                    + "MANAGEMENT_WORK_DEPT_NAME=  '" + item.getMANAGEMENT_WORK_DEPT_NAME() + "', "
//                                    + "MANAGEMENT_WORK_DEPT_LEVEL=  '" + item.getMANAGEMENT_WORK_DEPT_LEVEL() + "', "
//                                    + "MANAGEMENT_OFFICE_CODE=  '" + item.getMANAGEMENT_OFFICE_CODE() + "', "
//                                    + "MANAGEMENT_OFFICE_NAME=  '" + item.getMANAGEMENT_OFFICE_NAME() + "', "
//                                    + "MANAGEMENT_OFFICE_SHORT_NAME=  '" + item.getMANAGEMENT_OFFICE_SHORT_NAME() + "', "
//                                    + "REPRESENT_POS_CODE=  '" + item.getREPRESENT_POS_CODE() + "', "
//                                    + "REPRESENT_POS_NAME=  '" + item.getREPRESENT_POS_NAME() + "', "
//                                    + "REPRESENT_POS_LEVEL=  '" + item.getREPRESENT_POS_LEVEL() + "', "
//                                    + "REPRESENT_POS_LEVEL_NAME=  '" + item.getREPRESENT_POS_LEVEL_NAME() + "', "
//                                    + "REPRESENT_DEPT_CODE=  '" + item.getREPRESENT_DEPT_CODE() + "', "
//                                    + "REPRESENT_DEPT_NAME=  '" + item.getREPRESENT_DEPT_NAME() + "', "
//                                    + "REPRESENT_DEPT_LEVEL=  '" + item.getREPRESENT_DEPT_LEVEL() + "', "
//                                    + "REPRESENT_UNDER_DEPT_CODE=  '" + item.getREPRESENT_UNDER_DEPT_CODE() + "', "
//                                    + "REPRESENT_UNDER_DEPT_NAME=  '" + item.getREPRESENT_UNDER_DEPT_NAME() + "', "
//                                    + "REPRESENT_UNDER_DEPT_LEVEL=  '" + item.getREPRESENT_UNDER_DEPT_LEVEL() + "', "
//                                    + "REPRESENT_WORK_DEPT_CODE=  '" + item.getREPRESENT_WORK_DEPT_CODE() + "', "
//                                    + "REPRESENT_WORK_DEPT_NAME=  '" + item.getREPRESENT_WORK_DEPT_NAME() + "', "
//                                    + "REPRESENT_WORK_DEPT_LEVEL=  '" + item.getREPRESENT_WORK_DEPT_LEVEL() + "', "
//                                    + "REPRESENT_OFFICE_CODE=  '" + item.getREPRESENT_OFFICE_CODE() + "', "
//                                    + "REPRESENT_OFFICE_NAME=  '" + item.getREPRESENT_OFFICE_NAME() + "', "
//                                    + "REPRESENT_OFFICE_SHORT_NAME=  '" + item.getREPRESENT_OFFICE_SHORT_NAME() + "', "
//                                    + "STATUS=  '" + item.getSTATUS() + "', "
//                                    + "REMARK=  '" + item.getREMARK() + "', "
//                                    + "CONTRIBUTOR_ID=  '" + item.getCONTRIBUTOR_ID() + "', "
//                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
//                                    + " WHERE EVIDENCE_OUT_STAFF_ID = '" + item.getEVIDENCE_OUT_STAFF_ID() + "' ");
//                    log.info("[SQL] : " + sqlBuilderSub.toString());
//
//                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                }
            }
        }

        return true;
    }

    @Override
    public Boolean EvidenceOutupdDelete(EvidenceOutupdDeleteReq req) {


        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_EVIDENCE_OUT SET IS_ACTIVE = '0' WHERE EVIDENCE_OUT_ID = '"+req.getEVIDENCE_OUT_ID()+"' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_EVIDENCE_OUT_DETAIL SET IS_ACTIVE = '0' WHERE EVIDENCE_OUT_ID = '"+req.getEVIDENCE_OUT_ID()+"' ");
        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_EVIDENCE_OUT_STAFF SET IS_ACTIVE = '0' WHERE EVIDENCE_OUT_ID = '"+req.getEVIDENCE_OUT_ID()+"' ");

        log.info("[SQL] ops_evidence_out : "+sqlBuilder1.toString());
        log.info("[SQL] ops_evidence_out_item : "+sqlBuilder2.toString());
        log.info("[SQL] ops_evidence_out_staff : "+sqlBuilder3.toString());

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[] {});

//        StringBuilder sqlBuilder = new StringBuilder()
//                .append("select STOCK_ID , QTY from OPS_EVIDENCE_OUT_ITEM where EVIDENCE_OUT_ID = "+req.getEVIDENCE_OUT_ID()+" and IS_ACTIVE = '1' ");
//
//        log.info("[SQL ] : "+sqlBuilder.toString());
//
//        @SuppressWarnings("unchecked")
//        List<EvidenceOutStockBalanceupdByConReq> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
//
//            public EvidenceOutStockBalanceupdByConReq mapRow(ResultSet rs, int rowNum) throws SQLException {
//                EvidenceOutStockBalanceupdByConReq item = new EvidenceOutStockBalanceupdByConReq();
//                item.setSTOCK_ID(rs.getInt("STOCK_ID"));
//                item.setBALANCE_QTY(rs.getInt("BALANCE_QTY"));
//                return item;
//            }
//        });
//
//        for(EvidenceOutStockBalanceupdByConReq item : dataList) {
//            EvidenceOutStockBalanceupdByCon(item.getSTOCK_ID(),item.getBALANCE_QTY());
//        }

        return true;

    }
}
