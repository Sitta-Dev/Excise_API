package com.xcs.phase2.dao.target;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.target.Target;
import com.xcs.phase2.model.target.TargetItem;
import com.xcs.phase2.model.target.TargetItemDetail;
import com.xcs.phase2.model.target.TargetgetByCon;
import com.xcs.phase2.request.target.IsSentTargetupdByConReq;
import com.xcs.phase2.request.target.TargetgetByConReq;
import com.xcs.phase2.request.target.TargetupdByConReq;
import com.xcs.phase2.request.target.TargetupdDeleteReq;
import com.xcs.phase2.response.target.TargetItemDetailResponse;
import com.xcs.phase2.response.target.TargetItemResponse;
import com.xcs.phase2.response.target.TargetinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TargetDAOImpl extends TargetExt implements TargetDAO {

    private static final Logger log = LoggerFactory.getLogger(TargetDAOImpl.class);

    @Override
    public List<TargetgetByCon> TargetgetByCon(TargetgetByConReq req) {
        // TODO Auto-generated method stub

        String str = "";

        if(req.getOFFICE_CODE() != null && !"".equals(req.getOFFICE_CODE()) && !"00".equals(req.getOFFICE_CODE())) {

            if(req.getOFFICE_CODE().length() == 6) {

                if("00".equals(req.getOFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getOFFICE_CODE().substring(2, 6))) {

                    str = " AND SUBSTR(OPS_TARGET.OFFICE_CODE,1,2) = SUBSTR('"+req.getOFFICE_CODE()+"',1,2) " ;

                }else if("00".equals(req.getOFFICE_CODE().substring(4, 6))) {

                    str = " AND SUBSTR(OPS_TARGET.OFFICE_CODE,1,4) = SUBSTR('"+req.getOFFICE_CODE()+"',1,4) " ;

                }else {

                    str = " AND OPS_TARGET.OFFICE_CODE = '"+req.getOFFICE_CODE()+"' " ;
                }

            }

        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT distinct" +
                        "  OPS_TARGET.TARGET_CODE," +
                        "  OPS_TARGET_ITEM.SEQUENCE," +
                        "  TO_CHAR(OPS_TARGET.BUDGET_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS BUDGET_YEAR," +
                        "  MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME," +
                        "  OPS_TARGET_ITEM.LAWSUIT_TYPE_TARGET," +
                        "  OPS_TARGET.OFFICE_NAME," +
                        "  (" +
                        "    SELECT COUNT(OPS_LAWSUIT.LAWSUIT_ID) " +
                        "    FROM OPS_LAWSUIT" +
                        "    WHERE " +
                        "    OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT.IS_LAWSUIT = 1" +
                        "    AND TO_CHAR(LAWSUIT_NO_YEAR,'yyyy') = TO_CHAR(trunc(sysdate, 'YEAR')-1, 'yyyy')" +
                        "  )AS OLD_QTY," +
                        "  (" +
                        "    SELECT SUM(OPS_PAYMENT.FINE) " +
                        "    FROM OPS_LAWSUIT" +
                        "    JOIN OPS_LAWSUIT_DETAIL ON OPS_LAWSUIT.LAWSUIT_ID=OPS_LAWSUIT_DETAIL.LAWSUIT_ID" +
                        "    JOIN OPS_PAYMENT ON OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID=OPS_PAYMENT.LAWSUIT_DETAIL_ID " +
                        "    WHERE " +
                        "    LAWSUIT_NO_YEAR = trunc(sysdate, 'YEAR')-1" +
                        "  )AS SUM_FINE," +
                        "  OPS_TARGET_ITEM_DETAIL.MONTH," +
                        "  (" +
                        "    SELECT COUNT(OPS_LAWSUIT.LAWSUIT_ID) " +
                        "    FROM OPS_LAWSUIT" +
                        "    WHERE " +
                        "    TO_CHAR(LAWSUIT_DATE,'yyyy')=TO_CHAR(OPS_TARGET.BUDGET_YEAR, 'yyyy')" +
                        "    AND TO_NUMBER(TO_CHAR(LAWSUIT_NO_YEAR,'mm'))=OPS_TARGET_ITEM_DETAIL.MONTH" +
                        "    AND OPS_LAWSUIT.IS_ACTIVE = 1" +
                        "    AND OPS_LAWSUIT.IS_LAWSUIT = 1" +
                        "    AND TO_CHAR(LAWSUIT_NO_YEAR,'yyyy') = TO_CHAR(trunc(sysdate, 'YEAR')-1, 'yyyy')" +
                        "  )AS OLD_QTY_MONTH," +
                        "  OPS_TARGET_ITEM_DETAIL.QTY_CASE," +
                        "  OPS_TARGET_ITEM_DETAIL.FINE," +
                        "  OPS_TARGET_ITEM_DETAIL.BRIBE," +
                        "  OPS_TARGET_ITEM_DETAIL.REWARD," +
                        "  OPS_TARGET_ITEM_DETAIL.TREASURY_MONEY," +
                        "  OPS_TARGET_ITEM_DETAIL.ITEM_DETAIL_ID," +
                        "  OPS_TARGET_ITEM_DETAIL.FINE_PERCENT," +
                        "  OPS_TARGET_ITEM_DETAIL.QTY_CASE_PERCENT" +
                        "  FROM" +
                        "  OPS_TARGET" +
                        "  LEFT JOIN OPS_TARGET_ITEM ON OPS_TARGET.TARGET_ID=OPS_TARGET_ITEM.TARGET_ID" +
                        "  LEFT JOIN OPS_TARGET_ITEM_DETAIL ON OPS_TARGET_ITEM.ITEM_ID=OPS_TARGET_ITEM_DETAIL.ITEM_ID" +
                        "  LEFT JOIN MAS_PRODUCT_GROUP ON OPS_TARGET_ITEM.PRODUCT_GROUP_ID=MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID" +
                        "  WHERE OPS_TARGET_ITEM.ITEM_ID = '"+req.getITEM_ID()+"' " + str +
                        "  order by OPS_TARGET_ITEM_DETAIL.MONTH");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<TargetgetByCon> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public TargetgetByCon mapRow(ResultSet rs, int rowNum) throws SQLException {
                TargetgetByCon item = new TargetgetByCon();
                item.setTARGET_CODE(rs.getString("TARGET_CODE"));
                item.setSEQUENCE(rs.getInt("SEQUENCE"));
                item.setBUDGET_YEAR(rs.getString("BUDGET_YEAR"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
                item.setLAWSUIT_TYPE_TARGET(rs.getInt("LAWSUIT_TYPE_TARGET"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setOLD_QTY(rs.getInt("OLD_QTY"));
                item.setSUM_FINE(rs.getFloat("SUM_FINE"));
                item.setMONTH(rs.getInt("MONTH"));
                item.setOLD_QTY_MONTH(rs.getInt("OLD_QTY_MONTH"));
                item.setQTY_CASE(rs.getFloat("QTY_CASE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setBRIBE(rs.getFloat("BRIBE"));
                item.setREWARD(rs.getFloat("REWARD"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));

                item.setITEM_DETAIL_ID(rs.getInt("ITEM_DETAIL_ID"));
                item.setFINE_PERCENT(rs.getFloat("FINE_PERCENT"));
                item.setQTY_CASE_PERCENT(rs.getFloat("QTY_CASE_PERCENT"));

                return item;
            }
        });

        return dataList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TargetinsAllResponse TargetinsAll(Target req) {

        TargetinsAllResponse res = new TargetinsAllResponse();

        try {

            String TARGET_ID = getSequences("SELECT OPS_TARGET_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_TARGET ( " +
                            "TARGET_ID," +
                            "OFFICE_ID," +
                            "OFFICE_CODE," +
                            "OFFICE_NAME," +
                            "TARGET_CODE," +
                            "BUDGET_YEAR," +
                            "TARGET_DATE," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + TARGET_ID + "'," +
                            "'" + req.getOFFICE_ID() + "'," +
                            "'" + req.getOFFICE_CODE() + "'," +
                            "'" + req.getOFFICE_NAME() + "'," +
                            "'" + req.getTARGET_CODE() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getBUDGET_YEAR() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + req.getTARGET_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setTARGET_ID(Integer.parseInt(TARGET_ID));


            if (req.getTargetItem() != null) {
                log.info("[Sub] Size : " + req.getTargetItem().size());
                List<TargetItemResponse> list = new ArrayList<TargetItemResponse>();

                if (req.getTargetItem().size() > 0) {
                    for (TargetItem item : req.getTargetItem()) {

                        String ITEM_ID = getSequences("SELECT OPS_TARGET_ITEM_SEQ.NEXTVAL FROM DUAL");
                        TargetItemResponse obj = new TargetItemResponse();
                        obj.setITEM_ID(Integer.parseInt(ITEM_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into OPS_TARGET_ITEM ( " +
                                        "ITEM_ID," +
                                        "TARGET_ID," +
                                        "PRODUCT_GROUP_ID," +
                                        "TARGET_ITEM_DATE," +
                                        "OLD_QTY," +
                                        "OLD_FINE," +
                                        "SEQUENCE," +
                                        "IS_SEND," +
                                        "IS_ACTIVE," +
                                        "LAWSUIT_TYPE_TARGET," +
                                        "OLD_BRIBE," +
                                        "OLD_REWARD," +
                                        "OLD_TREASURY" +
                                        " ) values (" +
                                        "'" + ITEM_ID + "'," +
                                        "'" + TARGET_ID + "'," +
                                        "'" + item.getPRODUCT_GROUP_ID() + "'," +
                                        "TO_TIMESTAMP_TZ('" + item.getTARGET_ITEM_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                                        "'" + item.getOLD_QTY() + "'," +
                                        "'" + item.getOLD_FINE() + "'," +
                                        "'" + item.getSEQUENCE() + "'," +
                                        "'" + item.getIS_SEND() + "'," +
                                        "'" + item.getIS_ACTIVE() + "'," +
                                        "'" + item.getLAWSUIT_TYPE_TARGET() + "'," +
                                        "'" + item.getOLD_BRIBE() + "'," +
                                        "'" + item.getOLD_REWARD() + "'," +
                                        "'" + item.getOLD_TREASURY() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);

                        if (item.getTargetItemDetail() != null) {
                            log.info("[Sub] Size : " + item.getTargetItemDetail().size());
                            List<TargetItemDetailResponse> list1 = new ArrayList<TargetItemDetailResponse>();

                            if (item.getTargetItemDetail().size() > 0) {

                                int month = 0;

                                for (TargetItemDetail item1 : item.getTargetItemDetail()) {

                                    String ITEM_DETAIL_ID = getSequences("SELECT OPS_TARGET_ITEM_DETAIL_SEQ.NEXTVAL FROM DUAL");
                                    TargetItemDetailResponse obj1 = new TargetItemDetailResponse();
                                    obj1.setITEM_DETAIL_ID(Integer.parseInt(ITEM_DETAIL_ID));

                                    StringBuilder sqlBuilderSub1 = new StringBuilder()
                                            .append("Insert into OPS_TARGET_ITEM_DETAIL ( " +
                                                    "ITEM_DETAIL_ID," +
                                                    "ITEM_ID," +
                                                    "OLD_QTY," +
                                                    "QTY_CASE," +
                                                    "QTY_CASE_PERCENT," +
                                                    "OLD_FINE," +
                                                    "FINE," +
                                                    "FINE_PERCENT," +
                                                    "TREASURY_MONEY," +
                                                    "MONTH," +
                                                    "OLD_BRIBE," +
                                                    "BRIBE," +
                                                    "OLD_REWARD," +
                                                    "REWARD," +
                                                    "OLD_TREASURY" +
                                                    " ) values (" +
                                                    "'" + ITEM_DETAIL_ID + "'," +
                                                    "'" + ITEM_ID + "'," +
                                                    "'" + item1.getOLD_QTY() + "'," +
                                                    "'" + item1.getQTY_CASE() + "'," +
                                                    "'" + item1.getQTY_CASE_PERCENT() + "'," +
                                                    "'" + item1.getOLD_FINE() + "'," +
                                                    "'" + item1.getFINE() + "'," +
                                                    "'" + item1.getFINE_PERCENT() + "'," +
                                                    "'" + item1.getTREASURY_MONEY() + "'," +
                                                    "'" + month + "'," +
                                                    "'" + item1.getOLD_BRIBE() + "'," +
                                                    "'" + item1.getBRIBE() + "'," +
                                                    "'" + item1.getOLD_REWARD() + "'," +
                                                    "'" + item1.getREWARD() + "'," +
                                                    "'" + item1.getOLD_TREASURY() + "'" +
                                                    " )");
                                    log.info("[SQL] : " + sqlBuilderSub1.toString());

                                    getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[]{});
                                    list1.add(obj1);

                                    month++;
                                }
                            }
                            obj.setTargetItemDetail(list1);
                        }
                    }
                }
                res.setTargetItem(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setTARGET_ID(0);
            res.setTargetItem(null);
            return res;
        }
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean TargetupdByCon(List<TargetupdByConReq> request) {

        if(request != null) {
            log.info("[Sub] Size : "+request.size());

            if(request.size() > 0){

                for(TargetupdByConReq req : request){

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_TARGET_ITEM "
                                    + "SET "
                                    + "TARGET_ID=  '"+req.getTARGET_ID()+"', "
                                    + "PRODUCT_GROUP_ID=  '"+req.getPRODUCT_GROUP_ID()+"', "
                                    + "TARGET_ITEM_DATE = TO_TIMESTAMP_TZ('"+req.getTARGET_ITEM_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
                                    + "OLD_QTY=  '"+req.getOLD_QTY()+"', "
                                    + "OLD_FINE=  '"+req.getOLD_FINE()+"', "
                                    + "SEQUENCE=  '"+req.getSEQUENCE()+"', "
                                    + "IS_SEND=  '"+req.getIS_SEND()+"', "
                                    + "IS_ACTIVE=  '"+req.getIS_ACTIVE()+"', "
                                    + "LAWSUIT_TYPE_TARGET=  '"+req.getLAWSUIT_TYPE_TARGET()+"', "
                                    + "OLD_BRIBE=  '"+req.getOLD_BRIBE()+"', "
                                    + "OLD_REWARD=  '"+req.getOLD_REWARD()+"', "
                                    + "OLD_TREASURY=  '"+req.getOLD_TREASURY()+"' "
                                    + "WHERE ITEM_ID = '"+req.getITEM_ID()+"' ");
                    log.info("[SQL] : "+sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});

                    if(req.getTargetItemDetail().size() > 0){

                        for(TargetItemDetail req1 : req.getTargetItemDetail()){

                            StringBuilder sqlBuilderSub1 = new StringBuilder()
                                    .append("UPDATE OPS_TARGET_ITEM_DETAIL "
                                            + "SET "
                                            + "ITEM_ID=  '"+req1.getITEM_ID()+"', "
                                            + "OLD_QTY=  '"+req1.getOLD_QTY()+"', "
                                            + "QTY_CASE=  '"+req1.getQTY_CASE()+"', "
                                            + "QTY_CASE_PERCENT=  '"+req1.getQTY_CASE_PERCENT()+"', "
                                            + "OLD_FINE=  '"+req1.getOLD_FINE()+"', "
                                            + "FINE=  '"+req1.getFINE()+"', "
                                            + "FINE_PERCENT=  '"+req1.getFINE_PERCENT()+"', "
                                            + "TREASURY_MONEY=  '"+req1.getTREASURY_MONEY()+"', "
                                            + "MONTH=  '"+req1.getMONTH()+"', "
                                            + "OLD_BRIBE=  '"+req1.getOLD_BRIBE()+"', "
                                            + "BRIBE=  '"+req1.getBRIBE()+"', "
                                            + "OLD_REWARD=  '"+req1.getOLD_REWARD()+"', "
                                            + "REWARD=  '"+req1.getREWARD()+"', "
                                            + "OLD_TREASURY=  '"+req1.getOLD_TREASURY()+"' "
                                            + "WHERE ITEM_DETAIL_ID = '"+req1.getITEM_DETAIL_ID()+"' ");
                            log.info("[SQL] : "+sqlBuilderSub1.toString());

                            getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[] {});
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public Boolean IsSentTargetupdByCon(IsSentTargetupdByConReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder()
                .append("UPDATE OPS_TARGET_ITEM SET IS_SEND = '1' WHERE ITEM_ID = '"+req.getITEM_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        return true;
    }

    @Override
    public Boolean TargetupdDelete(TargetupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder()
                .append("UPDATE OPS_TARGET_ITEM SET IS_ACTIVE = '0' WHERE ITEM_ID = '"+req.getITEM_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        return true;
    }
}
