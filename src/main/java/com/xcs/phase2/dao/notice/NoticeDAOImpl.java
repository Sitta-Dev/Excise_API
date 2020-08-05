package com.xcs.phase2.dao.notice;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.notice.*;
import com.xcs.phase2.request.notice.NoticegetByConReq;
import com.xcs.phase2.request.notice.NoticeupdDeleteReq;
import com.xcs.phase2.response.notice.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class NoticeDAOImpl extends NoticeExt implements NoticeDAO {

    private static final Logger log = LoggerFactory.getLogger(NoticeDAOImpl.class);

    @Override
    public Notice NoticegetByCon(NoticegetByConReq req) {



        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "NOTICE_ID," +
                        "ARREST_ID," +
                        "OFFICE_ID," +
                        "NOTICE_CODE," +
                        "OFFICE_CODE," +
                        "OFFICE_NAME," +
                        "to_char(NOTICE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as NOTICE_DATE," +
                        "NOTICE_DUE," +
                        "to_char(NOTICE_DUE_DATE,'"+Pattern.FORMAT_DATETIME+"') as NOTICE_DUE_DATE," +
                        "COMMUNICATION_CHANNEL," +
                        "IS_ARREST," +
                        "IS_AUTHORITY," +
                        "IS_ACTIVE," +
                        "IS_MATCH," +
                        "to_char(CREATE_DATE,'"+Pattern.FORMAT_DATETIME+"') as CREATE_DATE," +
                        "CREATE_USER_ACCOUNT_ID," +
                        "to_char(UPDATE_DATE,'"+Pattern.FORMAT_DATETIME+"') as UPDATE_DATE," +
                        "UPDATE_USER_ACCOUNT_ID" +
                        " from OPS_NOTICE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and NOTICE_ID = '"+req.getNOTICE_ID()+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Notice>() {

            public Notice extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    Notice item = new Notice();
                    item.setNOTICE_ID(rs.getInt("NOTICE_ID"));
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                    item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
                    item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                    item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                    item.setNOTICE_DATE(rs.getString("NOTICE_DATE"));
                    item.setNOTICE_DUE(rs.getInt("NOTICE_DUE"));
                    item.setNOTICE_DUE_DATE(rs.getString("NOTICE_DUE_DATE"));
                    item.setCOMMUNICATION_CHANNEL(rs.getInt("COMMUNICATION_CHANNEL"));
                    item.setIS_ARREST(rs.getInt("IS_ARREST"));
                    item.setIS_AUTHORITY(rs.getInt("IS_AUTHORITY"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setIS_MATCH(rs.getInt("IS_MATCH"));
                    item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                    item.setCREATE_USER_ACCOUNT_ID(rs.getInt("CREATE_USER_ACCOUNT_ID"));
                    item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                    item.setUPDATE_USER_ACCOUNT_ID(rs.getInt("UPDATE_USER_ACCOUNT_ID"));

                    item.setNoticeInformer(getNoticeInformer(rs.getInt("NOTICE_ID")));
                    item.setNoticeStaff(getNoticeStaff(rs.getInt("NOTICE_ID"),99));
                    item.setNoticeLocale(getNoticeLocale(rs.getInt("NOTICE_ID")));
                    item.setNoticeProduct(getNoticeProduct(rs.getInt("NOTICE_ID")));
                    item.setNoticeSuspect(getNoticeSuspect(rs.getInt("NOTICE_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public NoticeinsAllResponse NoticeinsAll(Notice req) {

        NoticeinsAllResponse res = new NoticeinsAllResponse();

        try {

            String NOTICE_ID = getSequences("SELECT OPS_NOTICE_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_NOTICE ( " +
                            "NOTICE_ID," +
                            "ARREST_ID," +
                            "OFFICE_ID," +
                            "NOTICE_CODE," +
                            "OFFICE_CODE," +
                            "OFFICE_NAME," +
                            "NOTICE_DATE," +
                            "NOTICE_DUE," +
                            "NOTICE_DUE_DATE," +
                            "COMMUNICATION_CHANNEL," +
                            "IS_ARREST," +
                            "IS_AUTHORITY," +
                            "IS_ACTIVE," +
                            "IS_MATCH," +
                            "CREATE_DATE," +
                            "CREATE_USER_ACCOUNT_ID," +
                            "UPDATE_DATE," +
                            "UPDATE_USER_ACCOUNT_ID" +
                            " ) values (" +
                            "'" + NOTICE_ID + "'," +
                            "'" + req.getARREST_ID() + "'," +
                            "'" + req.getOFFICE_ID() + "'," +
                            "'" + req.getNOTICE_CODE() + "'," +
                            "'" + req.getOFFICE_CODE() + "'," +
                            "'" + req.getOFFICE_NAME() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getNOTICE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getNOTICE_DUE() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getNOTICE_DUE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getCOMMUNICATION_CHANNEL() + "'," +
                            "'" + req.getIS_ARREST() + "'," +
                            "'" + req.getIS_AUTHORITY() + "'," +
                            "'" + req.getIS_ACTIVE() + "'," +
                            "'" + req.getIS_MATCH() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getCREATE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getCREATE_USER_ACCOUNT_ID() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getUPDATE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getUPDATE_USER_ACCOUNT_ID() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setNOTICE_ID(Integer.parseInt(NOTICE_ID));


            if (req.getNoticeInformer() != null) {
                log.info("[Sub] Size : " + req.getNoticeInformer().size());
                List<NoticeInformerResponse> list = new ArrayList<NoticeInformerResponse>();

                if (req.getNoticeInformer().size() > 0) {
                    for (NoticeInformer item : req.getNoticeInformer()) {

                        String INFORMER_ID = getSequences("SELECT OPS_NOTICE_INFORMER_SEQ.NEXTVAL FROM DUAL");
                        NoticeInformerResponse obj = new NoticeInformerResponse();
                        obj.setINFORMER_ID(Integer.parseInt(INFORMER_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_NOTICE_INFORMER ( " +
                                        "INFORMER_ID," +
                                        "NOTICE_ID," +
                                        "TITLE_ID," +
                                        "SUB_DISTRICT_ID," +
                                        "INFORMER_STATUS," +
                                        "TITLE_NAME_TH," +
                                        "TITLE_NAME_EN," +
                                        "TITLE_SHORT_NAME_TH," +
                                        "TITLE_SHORT_NAME_EN," +
                                        "FIRST_NAME," +
                                        "MIDDLE_NAME," +
                                        "LAST_NAME," +
                                        "OTHER_NAME," +
                                        "ID_CARD," +
                                        "AGE," +
                                        "CAREER," +
                                        "POSITION," +
                                        "PERSON_DESC," +
                                        "EMAIL," +
                                        "TEL_NO," +
                                        "INFORMER_INFO," +
                                        "GPS," +
                                        "ADDRESS_NO," +
                                        "VILLAGE_NO," +
                                        "BUILDING_NAME," +
                                        "ROOM_NO," +
                                        "FLOOR," +
                                        "VILLAGE_NAME," +
                                        "ALLEY," +
                                        "LANE," +
                                        "ROAD," +
                                        "INFORMER_PHOTO," +
                                        "INFORMER_FINGER_PRINT," +
                                        "IS_ACTIVE " +
                                        " ) values (" +
                                        "'" + INFORMER_ID + "'," +
                                        "'" + NOTICE_ID + "'," +
                                        "'" + item.getTITLE_ID() + "'," +
                                        "'" + item.getSUB_DISTRICT_ID() + "'," +
                                        "'" + item.getINFORMER_STATUS() + "'," +
                                        "'" + item.getTITLE_NAME_TH() + "'," +
                                        "'" + item.getTITLE_NAME_EN() + "'," +
                                        "'" + item.getTITLE_SHORT_NAME_TH() + "'," +
                                        "'" + item.getTITLE_SHORT_NAME_EN() + "'," +
                                        "'" + item.getFIRST_NAME() + "'," +
                                        "'" + item.getMIDDLE_NAME() + "'," +
                                        "'" + item.getLAST_NAME() + "'," +
                                        "'" + item.getOTHER_NAME() + "'," +
                                        "'" + item.getID_CARD() + "'," +
                                        "'" + item.getAGE() + "'," +
                                        "'" + item.getCAREER() + "'," +
                                        "'" + item.getPOSITION() + "'," +
                                        "'" + item.getPERSON_DESC() + "'," +
                                        "'" + item.getEMAIL() + "'," +
                                        "'" + item.getTEL_NO() + "'," +
                                        "'" + item.getINFORMER_INFO() + "'," +
                                        "'" + item.getGPS() + "'," +
                                        "'" + item.getADDRESS_NO() + "'," +
                                        "'" + item.getVILLAGE_NO() + "'," +
                                        "'" + item.getBUILDING_NAME() + "'," +
                                        "'" + item.getROOM_NO() + "'," +
                                        "'" + item.getFLOOR() + "'," +
                                        "'" + item.getVILLAGE_NAME() + "'," +
                                        "'" + item.getALLEY() + "'," +
                                        "'" + item.getLANE() + "'," +
                                        "'" + item.getROAD() + "'," +
                                        "'" + item.getINFORMER_PHOTO() + "'," +
                                        "'" + item.getINFORMER_FINGER_PRINT() + "'," +
                                        "'" + item.getIS_ACTIVE() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setNoticeInformer(list);
            }

            if (req.getNoticeStaff() != null) {
                log.info("[Sub] Size : " + req.getNoticeStaff().size());
                List<NoticeStaffResponse> list = new ArrayList<NoticeStaffResponse>();

                if (req.getNoticeStaff().size() > 0) {
                    for (NoticeStaff item : req.getNoticeStaff()) {

                        String STAFF_ID = getSequences("SELECT OPS_NOTICE_STAFF_SEQ.NEXTVAL FROM DUAL");
                        NoticeStaffResponse obj = new NoticeStaffResponse();
                        obj.setSTAFF_ID(Integer.parseInt(STAFF_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_NOTICE_STAFF ( " +
                                        "STAFF_ID," +
                                        "NOTICE_ID," +
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
                                        " ) values (" +
                                        "'" + STAFF_ID + "'," +
                                        "'" + NOTICE_ID + "'," +
                                        "'"+item.getSTAFF_REF_ID()+"'," +
                                        "'"+item.getTITLE_ID()+"'," +
                                        "'"+item.getSTAFF_CODE()+"'," +
                                        "'"+item.getID_CARD()+"'," +
                                        "'"+item.getSTAFF_TYPE()+"'," +
                                        "'"+item.getTITLE_NAME_TH()+"'," +
                                        "'"+item.getTITLE_NAME_EN()+"'," +
                                        "'"+item.getTITLE_SHORT_NAME_TH()+"'," +
                                        "'"+item.getTITLE_SHORT_NAME_EN()+"'," +
                                        "'"+item.getFIRST_NAME()+"'," +
                                        "'"+item.getLAST_NAME()+"'," +
                                        "'"+item.getAGE()+"'," +
                                        "'"+item.getOPERATION_POS_CODE()+"'," +
                                        "'"+item.getOPREATION_POS_NAME()+"'," +
                                        "'"+item.getOPREATION_POS_LEVEL()+"'," +
                                        "'"+item.getOPERATION_POS_LEVEL_NAME()+"'," +
                                        "'"+item.getOPERATION_DEPT_CODE()+"'," +
                                        "'"+item.getOPERATION_DEPT_NAME()+"'," +
                                        "'"+item.getOPERATION_DEPT_LEVEL()+"'," +
                                        "'"+item.getOPERATION_UNDER_DEPT_CODE()+"'," +
                                        "'"+item.getOPERATION_UNDER_DEPT_NAME()+"'," +
                                        "'"+item.getOPERATION_UNDER_DEPT_LEVEL()+"'," +
                                        "'"+item.getOPERATION_WORK_DEPT_CODE()+"'," +
                                        "'"+item.getOPERATION_WORK_DEPT_NAME()+"'," +
                                        "'"+item.getOPERATION_WORK_DEPT_LEVEL()+"'," +
                                        "'"+item.getOPERATION_OFFICE_CODE()+"'," +
                                        "'"+item.getOPERATION_OFFICE_NAME()+"'," +
                                        "'"+item.getOPERATION_OFFICE_SHORT_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_POS_CODE()+"'," +
                                        "'"+item.getMANAGEMENT_POS_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_POS_LEVEL()+"'," +
                                        "'"+item.getMANAGEMENT_POS_LEVEL_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_DEPT_CODE()+"'," +
                                        "'"+item.getMANAGEMENT_DEPT_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_DEPT_LEVEL()+"'," +
                                        "'"+item.getMANAGEMENT_UNDER_DEPT_CODE()+"'," +
                                        "'"+item.getMANAGEMENT_UNDER_DEPT_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_UNDER_DEPT_LEVEL()+"'," +
                                        "'"+item.getMANAGEMENT_WORK_DEPT_CODE()+"'," +
                                        "'"+item.getMANAGEMENT_WORK_DEPT_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_WORK_DEPT_LEVEL()+"'," +
                                        "'"+item.getMANAGEMENT_OFFICE_CODE()+"'," +
                                        "'"+item.getMANAGEMENT_OFFICE_NAME()+"'," +
                                        "'"+item.getMANAGEMENT_OFFICE_SHORT_NAME()+"'," +
                                        "'"+item.getREPRESENT_POS_CODE()+"'," +
                                        "'"+item.getREPRESENT_POS_NAME()+"'," +
                                        "'"+item.getREPRESENT_POS_LEVEL()+"'," +
                                        "'"+item.getREPRESENT_POS_LEVEL_NAME()+"'," +
                                        "'"+item.getREPRESENT_DEPT_CODE()+"'," +
                                        "'"+item.getREPRESENT_DEPT_NAME()+"'," +
                                        "'"+item.getREPRESENT_DEPT_LEVEL()+"'," +
                                        "'"+item.getREPRESENT_UNDER_DEPT_CODE()+"'," +
                                        "'"+item.getREPRESENT_UNDER_DEPT_NAME()+"'," +
                                        "'"+item.getREPRESENT_UNDER_DEPT_LEVEL()+"'," +
                                        "'"+item.getREPRESENT_WORK_DEPT_CODE()+"'," +
                                        "'"+item.getREPRESENT_WORK_DEPT_NAME()+"'," +
                                        "'"+item.getREPRESENT_WORK_DEPT_LEVEL()+"'," +
                                        "'"+item.getREPRESENT_OFFICE_CODE()+"'," +
                                        "'"+item.getREPRESENT_OFFICE_NAME()+"'," +
                                        "'"+item.getREPRESENT_OFFICE_SHORT_NAME()+"'," +
                                        "'"+item.getSTATUS()+"'," +
                                        "'"+item.getREMARK()+"'," +
                                        "'"+item.getCONTRIBUTOR_ID()+"'," +
                                        "'" + item.getIS_ACTIVE() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setNoticeStaff(list);
            }

            if (req.getNoticeLocale() != null) {
                log.info("[Sub] Size : " + req.getNoticeLocale().size());
                List<NoticeLocaleResponse> list = new ArrayList<NoticeLocaleResponse>();

                if (req.getNoticeLocale().size() > 0) {
                    for (NoticeLocale item : req.getNoticeLocale()) {

                        String LOCALE_ID = getSequences("SELECT OPS_NOTICE_LOCALE_SEQ.NEXTVAL FROM DUAL");
                        NoticeLocaleResponse obj = new NoticeLocaleResponse();
                        obj.setLOCALE_ID(Integer.parseInt(LOCALE_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_NOTICE_LOCALE ( " +
                                        "LOCALE_ID," +
                                        "NOTICE_ID," +
                                        "SUB_DISTRICT_ID," +
                                        "GPS," +
                                        "ADDRESS_NO," +
                                        "VILLAGE_NO," +
                                        "BUILDING_NAME," +
                                        "ROOM_NO," +
                                        "FLOOR," +
                                        "VILLAGE_NAME," +
                                        "ALLEY," +
                                        "LANE," +
                                        "ROAD," +
                                        "ADDRESS_TYPE," +
                                        "ADDRESS_STATUS," +
                                        "POLICE_STATION," +
                                        "LOCATION," +
                                        "IS_ACTIVE " +
                                        " ) values (" +
                                        "'" + LOCALE_ID + "'," +
                                        "'" + NOTICE_ID + "'," +
                                        "'" + item.getSUB_DISTRICT_ID() + "'," +
                                        "'" + item.getGPS() + "'," +
                                        "'" + item.getADDRESS_NO() + "'," +
                                        "'" + item.getVILLAGE_NO() + "'," +
                                        "'" + item.getBUILDING_NAME() + "'," +
                                        "'" + item.getROOM_NO() + "'," +
                                        "'" + item.getFLOOR() + "'," +
                                        "'" + item.getVILLAGE_NAME() + "'," +
                                        "'" + item.getALLEY() + "'," +
                                        "'" + item.getLANE() + "'," +
                                        "'" + item.getROAD() + "'," +
                                        "'" + item.getADDRESS_TYPE() + "'," +
                                        "'" + item.getADDRESS_STATUS() + "'," +
                                        "'" + item.getPOLICE_STATION() + "'," +
                                        "'" + item.getLOCATION() + "'," +
                                        "'" + item.getIS_ACTIVE() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setNoticeLocale(list);
            }

            if (req.getNoticeProduct() != null) {
                log.info("[Sub] Size : " + req.getNoticeProduct().size());
                List<NoticeProductResponse> list = new ArrayList<NoticeProductResponse>();

                if (req.getNoticeProduct().size() > 0) {
                    for (NoticeProduct item : req.getNoticeProduct()) {

                        String PRODUCT_ID = getSequences("SELECT OPS_NOTICE_PRODUCT_SEQ.NEXTVAL FROM DUAL");
                        NoticeProductResponse obj = new NoticeProductResponse();
                        obj.setPRODUCT_ID(Integer.parseInt(PRODUCT_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_NOTICE_PRODUCT ( " +
                                        "PRODUCT_ID," +
                                        "NOTICE_ID," +
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
                                        "SIZES_UNIT_ID," +
                                        "QUATITY_UNIT_ID," +
                                        "VOLUMN_UNIT_ID," +
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
                                        "IS_TAX_VALUE," +
                                        "TAX_VALUE," +
                                        "IS_TAX_VOLUMN," +
                                        "TAX_VOLUMN," +
                                        "TAX_VOLUMN_UNIT," +
                                        "LICENSE_PLATE," +
                                        "ENGINE_NO," +
                                        "CHASSIS_NO," +
                                        "PRODUCT_DESC," +
                                        "SUGAR," +
                                        "CO2," +
                                        "DEGREE," +
                                        "PRICE," +
                                        "SIZES," +
                                        "SIZES_UNIT," +
                                        "QUANTITY," +
                                        "QUANTITY_UNIT," +
                                        "VOLUMN," +
                                        "VOLUMN_UNIT," +
                                        "REMARK," +
                                        "IS_DOMESTIC," +
                                        "IS_ILLEGAL," +
                                        "IS_ACTIVE " +
                                        " ) values (" +
                                        "'" + PRODUCT_ID + "'," +
                                        "'" + NOTICE_ID + "'," +
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
                                        "'" + item.getSIZES_UNIT_ID() + "'," +
                                        "'" + item.getQUATITY_UNIT_ID() + "'," +
                                        "'" + item.getVOLUMN_UNIT_ID() + "'," +
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
                                        "'" + item.getIS_TAX_VALUE() + "'," +
                                        "'" + item.getTAX_VALUE() + "'," +
                                        "'" + item.getIS_TAX_VOLUMN() + "'," +
                                        "'" + item.getTAX_VOLUMN() + "'," +
                                        "'" + item.getTAX_VOLUMN_UNIT() + "'," +
                                        "'" + item.getLICENSE_PLATE() + "'," +
                                        "'" + item.getENGINE_NO() + "'," +
                                        "'" + item.getCHASSIS_NO() + "'," +
                                        "'" + item.getPRODUCT_DESC() + "'," +
                                        "'" + item.getSUGAR() + "'," +
                                        "'" + item.getCO2() + "'," +
                                        "'" + item.getDEGREE() + "'," +
                                        "'" + item.getPRICE() + "'," +
                                        "'" + item.getSIZES() + "'," +
                                        "'" + item.getSIZES_UNIT() + "'," +
                                        "'" + item.getQUANTITY() + "'," +
                                        "'" + item.getQUANTITY_UNIT() + "'," +
                                        "'" + item.getVOLUMN() + "'," +
                                        "'" + item.getVOLUMN_UNIT() + "'," +
                                        "'" + item.getREMARK() + "'," +
                                        "'" + item.getIS_DOMESTIC() + "'," +
                                        "'" + item.getIS_ILLEGAL() + "'," +
                                        "'" + item.getIS_ACTIVE() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setNoticeProduct(list);
            }

            if (req.getNoticeSuspect() != null) {
                log.info("[Sub] Size : " + req.getNoticeSuspect().size());
                List<NoticeSuspectResponse> list = new ArrayList<NoticeSuspectResponse>();

                if (req.getNoticeSuspect().size() > 0) {
                    for (NoticeSuspect item : req.getNoticeSuspect()) {

                        String SUSPECT_ID = getSequences("SELECT OPS_NOTICE_SUSPECT_SEQ.NEXTVAL FROM DUAL");
                        NoticeSuspectResponse obj = new NoticeSuspectResponse();
                        obj.setSUSPECT_ID(Integer.parseInt(SUSPECT_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_NOTICE_SUSPECT ( " +
                                        "SUSPECT_ID," +
                                        "NOTICE_ID," +
                                        "PERSON_ID," +
                                        "TITLE_ID," +
                                        "PERSON_TYPE," +
                                        "ENTITY_TYPE," +
                                        "TITLE_NAME_TH," +
                                        "TITLE_NAME_EN," +
                                        "TITLE_SHORT_NAME_TH," +
                                        "TITLE_SHORT_NAME_EN," +
                                        "FIRST_NAME," +
                                        "MIDDLE_NAME," +
                                        "LAST_NAME," +
                                        "OTHER_NAME," +
                                        "COMPANY_REGISTRATION_NO," +
                                        "EXCISE_REGISTRATION_NO," +
                                        "ID_CARD," +
                                        "AGE," +
                                        "PASSPORT_NO," +
                                        "CAREER," +
                                        "PERSON_DESC," +
                                        "EMAIL," +
                                        "TEL_NO," +
                                        "MISTREAT_NO," +
                                        "IS_ACTIVE," +
                                        "COMPANY_NAME" +
                                        " ) values (" +
                                        "'" + SUSPECT_ID + "'," +
                                        "'" + NOTICE_ID + "'," +
                                        "'" + item.getPERSON_ID() + "'," +
                                        "'" + item.getTITLE_ID() + "'," +
                                        "'" + item.getPERSON_TYPE() + "'," +
                                        "'" + item.getENTITY_TYPE() + "'," +
                                        "'" + item.getTITLE_NAME_TH() + "'," +
                                        "'" + item.getTITLE_NAME_EN() + "'," +
                                        "'" + item.getTITLE_SHORT_NAME_TH() + "'," +
                                        "'" + item.getTITLE_SHORT_NAME_EN() + "'," +
                                        "'" + item.getFIRST_NAME() + "'," +
                                        "'" + item.getMIDDLE_NAME() + "'," +
                                        "'" + item.getLAST_NAME() + "'," +
                                        "'" + item.getOTHER_NAME() + "'," +
                                        "'" + item.getCOMPANY_REGISTRATION_NO() + "'," +
                                        "'" + item.getEXCISE_REGISTRATION_NO() + "'," +
                                        "'" + item.getID_CARD() + "'," +
                                        "'" + item.getAGE() + "'," +
                                        "'" + item.getPASSPORT_NO() + "'," +
                                        "'" + item.getCAREER() + "'," +
                                        "'" + item.getPERSON_DESC() + "'," +
                                        "'" + item.getEMAIL() + "'," +
                                        "'" + item.getTEL_NO() + "'," +
                                        "'" + item.getMISTREAT_NO() + "'," +
                                        "'" + item.getIS_ACTIVE() + "'," +
                                        "'" + item.getCOMPANY_NAME() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setNoticeSuspect(list);
            }


            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setNOTICE_ID(0);
            return res;
        }
    }

    @Override
    public Boolean NoticeupdByCon(Notice req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE OPS_NOTICE SET "
                        + "ARREST_ID=  '" + req.getARREST_ID() + "', "
                        + "OFFICE_ID=  '" + req.getOFFICE_ID() + "', "
                        + "NOTICE_CODE=  '" + req.getNOTICE_CODE() + "', "
                        + "OFFICE_CODE=  '" + req.getOFFICE_CODE() + "', "
                        + "OFFICE_NAME=  '" + req.getOFFICE_NAME() + "', "
                        + "NOTICE_DATE = TO_TIMESTAMP_TZ('" + req.getNOTICE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "NOTICE_DUE=  '" + req.getNOTICE_DUE() + "', "
                        + "NOTICE_DUE_DATE = TO_TIMESTAMP_TZ('" + req.getNOTICE_DUE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "COMMUNICATION_CHANNEL=  '" + req.getCOMMUNICATION_CHANNEL() + "', "
                        + "IS_ARREST=  '" + req.getIS_ARREST() + "', "
                        + "IS_AUTHORITY=  '" + req.getIS_AUTHORITY() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "', "
                        + "IS_MATCH=  '" + req.getIS_MATCH() + "', "
                        + "CREATE_DATE = TO_TIMESTAMP_TZ('" + req.getCREATE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "CREATE_USER_ACCOUNT_ID=  '" + req.getCREATE_USER_ACCOUNT_ID() + "', "
                        + "UPDATE_DATE = TO_TIMESTAMP_TZ('" + req.getUPDATE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "UPDATE_USER_ACCOUNT_ID=  '" + req.getUPDATE_USER_ACCOUNT_ID() + "' "
                        + " WHERE NOTICE_ID = '" + req.getNOTICE_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        if (req.getNoticeInformer() != null) {
            log.info("[Sub] Size : " + req.getNoticeInformer().size());

            if (req.getNoticeInformer().size() > 0) {
                for (NoticeInformer item : req.getNoticeInformer()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_NOTICE_INFORMER SET "
                                    + "NOTICE_ID=  '" + item.getNOTICE_ID() + "', "
                                    + "TITLE_ID=  '" + item.getTITLE_ID() + "', "
                                    + "SUB_DISTRICT_ID=  '" + item.getSUB_DISTRICT_ID() + "', "
                                    + "INFORMER_STATUS=  '" + item.getINFORMER_STATUS() + "', "
                                    + "TITLE_NAME_TH=  '" + item.getTITLE_NAME_TH() + "', "
                                    + "TITLE_NAME_EN=  '" + item.getTITLE_NAME_EN() + "', "
                                    + "TITLE_SHORT_NAME_TH=  '" + item.getTITLE_SHORT_NAME_TH() + "', "
                                    + "TITLE_SHORT_NAME_EN=  '" + item.getTITLE_SHORT_NAME_EN() + "', "
                                    + "FIRST_NAME=  '" + item.getFIRST_NAME() + "', "
                                    + "MIDDLE_NAME=  '" + item.getMIDDLE_NAME() + "', "
                                    + "LAST_NAME=  '" + item.getLAST_NAME() + "', "
                                    + "OTHER_NAME=  '" + item.getOTHER_NAME() + "', "
                                    + "ID_CARD=  '" + item.getID_CARD() + "', "
                                    + "AGE=  '" + item.getAGE() + "', "
                                    + "CAREER=  '" + item.getCAREER() + "', "
                                    + "POSITION=  '" + item.getPOSITION() + "', "
                                    + "PERSON_DESC=  '" + item.getPERSON_DESC() + "', "
                                    + "EMAIL=  '" + item.getEMAIL() + "', "
                                    + "TEL_NO=  '" + item.getTEL_NO() + "', "
                                    + "INFORMER_INFO=  '" + item.getINFORMER_INFO() + "', "
                                    + "GPS=  '" + item.getGPS() + "', "
                                    + "ADDRESS_NO=  '" + item.getADDRESS_NO() + "', "
                                    + "VILLAGE_NO=  '" + item.getVILLAGE_NO() + "', "
                                    + "BUILDING_NAME=  '" + item.getBUILDING_NAME() + "', "
                                    + "ROOM_NO=  '" + item.getROOM_NO() + "', "
                                    + "FLOOR=  '" + item.getFLOOR() + "', "
                                    + "VILLAGE_NAME=  '" + item.getVILLAGE_NAME() + "', "
                                    + "ALLEY=  '" + item.getALLEY() + "', "
                                    + "LANE=  '" + item.getLANE() + "', "
                                    + "ROAD=  '" + item.getROAD() + "', "
                                    + "INFORMER_PHOTO=  '" + item.getINFORMER_PHOTO() + "', "
                                    + "INFORMER_FINGER_PRINT=  '" + item.getINFORMER_FINGER_PRINT() + "', "
                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
                                    + " WHERE INFORMER_ID = '" + item.getINFORMER_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());


                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});


                }
            }
        }

        if (req.getNoticeLocale() != null) {
            log.info("[Sub] Size : " + req.getNoticeLocale().size());

            if (req.getNoticeLocale().size() > 0) {
                for (NoticeLocale item : req.getNoticeLocale()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_NOTICE_LOCALE SET "
                                    + "NOTICE_ID=  '" + item.getNOTICE_ID() + "', "
                                    + "SUB_DISTRICT_ID=  '" + item.getSUB_DISTRICT_ID() + "', "
                                    + "GPS=  '" + item.getGPS() + "', "
                                    + "ADDRESS_NO=  '" + item.getADDRESS_NO() + "', "
                                    + "VILLAGE_NO=  '" + item.getVILLAGE_NO() + "', "
                                    + "BUILDING_NAME=  '" + item.getBUILDING_NAME() + "', "
                                    + "ROOM_NO=  '" + item.getROOM_NO() + "', "
                                    + "FLOOR=  '" + item.getFLOOR() + "', "
                                    + "VILLAGE_NAME=  '" + item.getVILLAGE_NAME() + "', "
                                    + "ALLEY=  '" + item.getALLEY() + "', "
                                    + "LANE=  '" + item.getLANE() + "', "
                                    + "ROAD=  '" + item.getROAD() + "', "
                                    + "ADDRESS_TYPE=  '" + item.getADDRESS_TYPE() + "', "
                                    + "ADDRESS_STATUS=  '" + item.getADDRESS_STATUS() + "', "
                                    + "POLICE_STATION=  '" + item.getPOLICE_STATION() + "', "
                                    + "LOCATION=  '" + item.getLOCATION() + "', "
                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
                                    + " WHERE LOCALE_ID = '" + item.getLOCALE_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());


                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});


                }
            }
        }

        return true;
    }

    @Override
    public Boolean NoticeupdDelete(NoticeupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_NOTICE SET IS_ACTIVE = '0' WHERE NOTICE_ID = '"+req.getNOTICE_ID()+"' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_NOTICE_STAFF SET IS_ACTIVE = '0' WHERE NOTICE_ID = '"+req.getNOTICE_ID()+"' ");
        StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_NOTICE_INFORMER SET IS_ACTIVE = '0' WHERE NOTICE_ID = '"+req.getNOTICE_ID()+"' ");
        StringBuilder sqlBuilder4 = new StringBuilder().append("UPDATE OPS_NOTICE_LOCALE SET IS_ACTIVE = '0' WHERE NOTICE_ID = '"+req.getNOTICE_ID()+"' ");
        StringBuilder sqlBuilder5 = new StringBuilder().append("UPDATE OPS_NOTICE_PRODUCT SET IS_ACTIVE = '0' WHERE NOTICE_ID = '"+req.getNOTICE_ID()+"' ");
        StringBuilder sqlBuilder6 = new StringBuilder().append("UPDATE OPS_NOTICE_SUSPECT SET IS_ACTIVE = '0' WHERE NOTICE_ID = '"+req.getNOTICE_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder3.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder4.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder5.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder6.toString(), new Object[] {});
        return true;
    }
}
