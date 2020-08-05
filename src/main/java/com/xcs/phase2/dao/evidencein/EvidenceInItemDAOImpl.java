package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.evidencein.EvidenceInItem;
import com.xcs.phase2.model.evidencein.EvidenceInStockBalance;
import com.xcs.phase2.request.evidencein.EvidenceInItemupdDeleteReq;
import com.xcs.phase2.response.evidencein.EvidenceInItemResponse;
import com.xcs.phase2.response.evidencein.EvidenceInIteminsAllResponse;
import com.xcs.phase2.response.evidencein.EvidenceInStockBalanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EvidenceInItemDAOImpl extends EvidenceInExt implements EvidenceInItemDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInItemDAOImpl.class);

    @Override
    public EvidenceInIteminsAllResponse EvidenceInIteminsAll(List<EvidenceInItem> req) {

        EvidenceInIteminsAllResponse res = new EvidenceInIteminsAllResponse();

        try {
            if (req != null) {
                log.info("[Sub] Size : " + req.size());
                List<EvidenceInItemResponse> list = new ArrayList<EvidenceInItemResponse>();
                if (req.size() > 0) {

                    for (EvidenceInItem item : req) {

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
                                        "'" + item.getEVIDENCE_IN_ID() + "'," +
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

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setEvidenceInItem(null);
            return res;
        }

    }

    @Override
    public Boolean EvidenceInItemupdByCon(List<EvidenceInItem> request) {

        if (request != null) {
            log.info("[Sub] Size : " + request.size());

            if (request.size() > 0) {

                for (EvidenceInItem req : request) {

                    StringBuilder sqlBuilder = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_IN_ITEM SET "
                                    + "EVIDENCE_IN_ITEM_CODE=  '" + req.getEVIDENCE_IN_ITEM_CODE() + "', "
                                    + "EVIDENCE_IN_ID=  '" + req.getEVIDENCE_IN_ID() + "', "
                                    + "PRODUCT_MAPPING_ID=  '" + req.getPRODUCT_MAPPING_ID() + "', "
                                    + "PRODUCT_CODE=  '" + req.getPRODUCT_CODE() + "', "
                                    + "PRODUCT_REF_CODE=  '" + req.getPRODUCT_REF_CODE() + "', "
                                    + "PRODUCT_GROUP_ID=  '" + req.getPRODUCT_GROUP_ID() + "', "
                                    + "PRODUCT_CATEGORY_ID=  '" + req.getPRODUCT_CATEGORY_ID() + "', "
                                    + "PRODUCT_TYPE_ID=  '" + req.getPRODUCT_TYPE_ID() + "', "
                                    + "PRODUCT_SUBTYPE_ID=  '" + req.getPRODUCT_SUBTYPE_ID() + "', "
                                    + "PRODUCT_SUBSETTYPE_ID=  '" + req.getPRODUCT_SUBSETTYPE_ID() + "', "
                                    + "PRODUCT_BRAND_ID=  '" + req.getPRODUCT_BRAND_ID() + "', "
                                    + "PRODUCT_SUBBRAND_ID=  '" + req.getPRODUCT_SUBBRAND_ID() + "', "
                                    + "PRODUCT_MODEL_ID=  '" + req.getPRODUCT_MODEL_ID() + "', "
                                    + "PRODUCT_TAXDETAIL_ID=  '" + req.getPRODUCT_TAXDETAIL_ID() + "', "
                                    + "PRODUCT_GROUP_CODE=  '" + req.getPRODUCT_GROUP_CODE() + "', "
                                    + "PRODUCT_GROUP_NAME=  '" + req.getPRODUCT_GROUP_NAME() + "', "
                                    + "PRODUCT_CATEGORY_CODE=  '" + req.getPRODUCT_CATEGORY_CODE() + "', "
                                    + "PRODUCT_CATEGORY_NAME=  '" + req.getPRODUCT_CATEGORY_NAME() + "', "
                                    + "PRODUCT_TYPE_CODE=  '" + req.getPRODUCT_TYPE_CODE() + "', "
                                    + "PRODUCT_TYPE_NAME=  '" + req.getPRODUCT_TYPE_NAME() + "', "
                                    + "PRODUCT_SUBTYPE_CODE=  '" + req.getPRODUCT_SUBTYPE_CODE() + "', "
                                    + "PRODUCT_SUBTYPE_NAME=  '" + req.getPRODUCT_SUBTYPE_NAME() + "', "
                                    + "PRODUCT_SUBSETTYPE_CODE=  '" + req.getPRODUCT_SUBSETTYPE_CODE() + "', "
                                    + "PRODUCT_SUBSETTYPE_NAME=  '" + req.getPRODUCT_SUBSETTYPE_NAME() + "', "
                                    + "PRODUCT_BRAND_CODE=  '" + req.getPRODUCT_BRAND_CODE() + "', "
                                    + "PRODUCT_BRAND_NAME_TH=  '" + req.getPRODUCT_BRAND_NAME_TH() + "', "
                                    + "PRODUCT_BRAND_NAME_EN=  '" + req.getPRODUCT_BRAND_NAME_EN() + "', "
                                    + "PRODUCT_SUBBRAND_CODE=  '" + req.getPRODUCT_SUBBRAND_CODE() + "', "
                                    + "PRODUCT_SUBBRAND_NAME_TH=  '" + req.getPRODUCT_SUBBRAND_NAME_TH() + "', "
                                    + "PRODUCT_SUBBRAND_NAME_EN=  '" + req.getPRODUCT_SUBBRAND_NAME_EN() + "', "
                                    + "PRODUCT_MODEL_CODE=  '" + req.getPRODUCT_MODEL_CODE() + "', "
                                    + "PRODUCT_MODEL_NAME_TH=  '" + req.getPRODUCT_MODEL_NAME_TH() + "', "
                                    + "PRODUCT_MODEL_NAME_EN=  '" + req.getPRODUCT_MODEL_NAME_EN() + "', "
                                    + "LICENSE_PLATE=  '" + req.getLICENSE_PLATE() + "', "
                                    + "ENGINE_NO=  '" + req.getENGINE_NO() + "', "
                                    + "CHASSIS_NO=  '" + req.getCHASSIS_NO() + "', "
                                    + "PRODUCT_DESC=  '" + req.getPRODUCT_DESC() + "', "
                                    + "SUGAR=  '" + req.getSUGAR() + "', "
                                    + "CO2=  '" + req.getCO2() + "', "
                                    + "DEGREE=  '" + req.getDEGREE() + "', "
                                    + "PRICE=  '" + req.getPRICE() + "', "
                                    + "DELIVERY_QTY=  '" + req.getDELIVERY_QTY() + "', "
                                    + "DELIVERY_QTY_UNIT=  '" + req.getDELIVERY_QTY_UNIT() + "', "
                                    + "DELIVERY_SIZE=  '" + req.getDELIVERY_SIZE() + "', "
                                    + "DELIVERY_SIZE_UNIT=  '" + req.getDELIVERY_SIZE_UNIT() + "', "
                                    + "DELIVERY_NET_VOLUMN=  '" + req.getDELIVERY_NET_VOLUMN() + "', "
                                    + "DELIVERY_NET_VOLUMN_UNIT=  '" + req.getDELIVERY_NET_VOLUMN_UNIT() + "', "
                                    + "DAMAGE_QTY=  '" + req.getDAMAGE_QTY() + "', "
                                    + "DAMAGE_QTY_UNIT=  '" + req.getDAMAGE_QTY_UNIT() + "', "
                                    + "DAMAGE_SIZE=  '" + req.getDAMAGE_SIZE() + "', "
                                    + "DAMAGE_SIZE_UNIT=  '" + req.getDAMAGE_SIZE_UNIT() + "', "
                                    + "DAMAGE_NET_VOLUMN=  '" + req.getDAMAGE_NET_VOLUMN() + "', "
                                    + "DAMAGE_NET_VOLUMN_UNIT=  '" + req.getDAMAGE_NET_VOLUMN_UNIT() + "', "
                                    + "IS_DOMESTIC=  '" + req.getIS_DOMESTIC() + "', "
                                    + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                                    + " WHERE EVIDENCE_IN_ITEM_ID = '" + req.getEVIDENCE_IN_ITEM_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilder.toString());
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

                    if (req.getEvidenceOutStockBalance() != null) {
                        log.info("[Sub] Size : " + req.getEvidenceOutStockBalance().size());

                        if (req.getEvidenceOutStockBalance().size() > 0) {

                            for (EvidenceInStockBalance item : req.getEvidenceOutStockBalance()) {

                                StringBuilder sqlBuilderSub = new StringBuilder()
                                        .append("UPDATE OPS_EVIDENCE_STOCK_BALANCE SET "
                                                + "WAREHOUSE_ID=  '" + item.getWAREHOUSE_ID() + "', "
                                                + "EVIDENCE_IN_ITEM_ID=  '" + item.getEVIDENCE_IN_ITEM_ID() + "', "
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
                                                + "BALANCE_NET_VOLUMN_UNIT=  '" + item.getBALANCE_NET_VOLUMN_UNIT() + "', "
                                                + "IS_FINISH=  '" + item.getIS_FINISH() + "', "
                                                + "IS_RECEIVE=  '" + item.getIS_RECEIVE() + "' "
                                                + " WHERE STOCK_ID = '" + item.getSTOCK_ID() + "' ");
                                log.info("[SQL] : " + sqlBuilderSub.toString());
                                getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                            }
                        }
                    }
                }
            }
        }

        return true;

    }

    @Override
    public Boolean EvidenceInItemupdDelete(List<EvidenceInItemupdDeleteReq> req) {


        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(EvidenceInItemupdDeleteReq item : req){

                    StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_EVIDENCE_IN_ITEM SET IS_ACTIVE = '0' WHERE EVIDENCE_IN_ITEM_ID = '"+item.getEVIDENCE_IN_ITEM_ID()+"'");
                    StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_EVIDENCE_STOCK_BALANCE SET IS_FINISH = '0' WHERE EVIDENCE_IN_ITEM_ID = '"+item.getEVIDENCE_IN_ITEM_ID()+"'");
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
                    getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});

                }
            }
        }

        return true;
    }
}
