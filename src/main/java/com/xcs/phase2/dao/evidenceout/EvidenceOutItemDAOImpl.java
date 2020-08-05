package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.evidenceout.EvidenceOutItem;
import com.xcs.phase2.model.evidenceout.EvidenceOutItemNew;
import com.xcs.phase2.request.evidenceout.EvidenceOutItemupdDeleteReq;
import com.xcs.phase2.response.evidenceout.EvidenceOutItemResponse;
import com.xcs.phase2.response.evidenceout.EvidenceOutIteminsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EvidenceOutItemDAOImpl extends EvidenceOutExt implements EvidenceOutItemDAO {

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutItemDAOImpl.class);

    @Override
    public EvidenceOutIteminsAllResponse EvidenceOutIteminsAll(List<EvidenceOutItemNew> req) {

        EvidenceOutIteminsAllResponse res = new EvidenceOutIteminsAllResponse();

        try {
            if (req != null) {
                log.info("[Sub] Size : " + req.size());
                List<EvidenceOutItemResponse> list = new ArrayList<EvidenceOutItemResponse>();
                if (req.size() > 0) {

                    for (EvidenceOutItemNew item : req) {

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
                                        "IS_RETURN," +
                                        "COST," +
                                        "RECEIPT_NO," +
                                        "BOOK_NO," +
                                        "IS_ACTIVE" +
                                        " ) VALUES (" +
                                        "'" + EVIDENCE_OUT_ITEM_ID + "', " +
                                        "'" + item.getEVIDENCE_OUT_ID() + "', " +
                                        "'" + item.getSTOCK_ID() + "'," +
                                        "'" + item.getQTY() + "'," +
                                        "'" + item.getQTY_UNIT() + "'," +
                                        "'" + item.getPRODUCT_SIZE() + "'," +
                                        "'" + item.getPRODUCT_SIZE_UNIT() + "'," +
                                        "'" + item.getNET_VOLUMN() + "'," +
                                        "'" + item.getNET_VOLUMN_UNIT() + "'," +
                                        "'" + item.getIS_RETURN() + "'," +
                                        "'" + item.getCOST() + "'," +
                                        "'" + item.getRECEIPT_NO() + "'," +
                                        "'" + item.getBOOK_NO() + "'," +
                                        "'" + item.getIS_ACTIVE() + "' )");
                        log.info("[SQL] Sub : " + sqlBuilderSub.toString());
                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

                        UpdateIsFinishStockBalance(item.getSTOCK_ID());

                        list.add(obj);
                    }
                }
                res.setEvidenceOutItemResponse(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setEvidenceOutItemResponse(null);
            return res;
        }

    }

    @Override
    public Boolean EvidenceOutItemupdByCon(List<EvidenceOutItem> req) {

        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(EvidenceOutItem item : req){

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_OUT_ITEM SET "
                                    + "EVIDENCE_OUT_ID=  '" + item.getEVIDENCE_OUT_ID() + "', "
                                    + "STOCK_ID=  '" + item.getSTOCK_ID() + "', "
                                    + "QTY=  '" + item.getQTY() + "', "
                                    + "QTY_UNIT=  '" + item.getQTY_UNIT() + "', "
                                    + "PRODUCT_SIZE=  '" + item.getPRODUCT_SIZE() + "', "
                                    + "PRODUCT_SIZE_UNIT=  '" + item.getPRODUCT_SIZE_UNIT() + "', "
                                    + "NET_VOLUMN=  '" + item.getNET_VOLUMN() + "', "
                                    + "NET_VOLUMN_UNIT=  '" + item.getNET_VOLUMN_UNIT() + "', "
                                    + "IS_RETURN=  '" + item.getIS_RETURN() + "', "
                                    + "IS_ACTIVE=  '" + item.getIS_ACTIVE() + "' "
                                    + " WHERE EVIDENCE_OUT_ITEM_ID = '" + item.getEVIDENCE_OUT_ITEM_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());
                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});

                    EvidenceOutStockBalanceupdByCon(item.getSTOCK_ID(), item.getQTY());
                }
            }
        }

        return true;

    }

    @Override
    public Boolean EvidenceOutItemupdDelete(List<EvidenceOutItemupdDeleteReq> req) {


        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(EvidenceOutItemupdDeleteReq item : req){

                    StringBuilder sqlBuilder = new StringBuilder()
                            .append("UPDATE OPS_EVIDENCE_OUT_ITEM SET IS_ACTIVE = '0' WHERE EVIDENCE_OUT_ITEM_ID = '"+item.getEVIDENCE_OUT_ITEM_ID()+"'");
                    log.info("[SQL] Sub : "+sqlBuilder.toString());
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

                    EvidenceOutStockBalanceupdByCon(item.getSTOCK_ID(), item.getBALANCE_QTY());
                }
            }
        }

        return true;
    }
}
