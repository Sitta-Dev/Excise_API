package com.xcs.phase2.dao.target;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.target.TargetItemDetail;
import com.xcs.phase2.response.target.TargetItemDeatailinsAllResponse;
import com.xcs.phase2.response.target.TargetItemDetailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TargetItemDetailDAOImpl extends TargetExt implements TargetItemDetailDAO {

    private static final Logger log = LoggerFactory.getLogger(TargetItemDetailDAOImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TargetItemDeatailinsAllResponse TargetItemDeatailinsAll(List<TargetItemDetail> req) {

        TargetItemDeatailinsAllResponse res = new TargetItemDeatailinsAllResponse();

        try {

            if (req != null) {
                log.info("[Sub] Size : " + req.size());
                List<TargetItemDetailResponse> list = new ArrayList<TargetItemDetailResponse>();

                if (req.size() > 0) {
                    for (TargetItemDetail item1 : req) {

                        int month = 0;

                        String ITEM_DETAIL_ID = getSequences("SELECT OPS_TARGET_ITEM_DETAIL_SEQ.NEXTVAL FROM DUAL");
                        TargetItemDetailResponse obj = new TargetItemDetailResponse();
                        obj.setITEM_DETAIL_ID(Integer.parseInt(ITEM_DETAIL_ID));

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
                                        "'" + item1.getITEM_ID() + "'," +
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

                        list.add(obj);

                        month++;

                    }
                }
                res.setTargetItemDetail(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setTargetItemDetail(null);
            return res;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean TargetItemDetailupdByCon(List<TargetItemDetail> request) {

        if (request != null) {
            log.info("[Sub] Size : " + request.size());

            if (request.size() > 0) {

                for (TargetItemDetail req1 : request) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_TARGET_ITEM_DETAIL "
                                    + "SET "
                                    + "ITEM_ID=  '" + req1.getITEM_ID() + "', "
                                    + "OLD_QTY=  '" + req1.getOLD_QTY() + "', "
                                    + "QTY_CASE=  '" + req1.getQTY_CASE() + "', "
                                    + "QTY_CASE_PERCENT=  '" + req1.getQTY_CASE_PERCENT() + "', "
                                    + "OLD_FINE=  '" + req1.getOLD_FINE() + "', "
                                    + "FINE=  '" + req1.getFINE() + "', "
                                    + "FINE_PERCENT=  '" + req1.getFINE_PERCENT() + "', "
                                    + "TREASURY_MONEY=  '" + req1.getTREASURY_MONEY() + "', "
                                    + "MONTH=  '" + req1.getMONTH() + "', "
                                    + "OLD_BRIBE=  '" + req1.getOLD_BRIBE() + "', "
                                    + "BRIBE=  '" + req1.getBRIBE() + "', "
                                    + "OLD_REWARD=  '" + req1.getOLD_REWARD() + "', "
                                    + "REWARD=  '" + req1.getREWARD() + "', "
                                    + "OLD_TREASURY=  '" + req1.getOLD_TREASURY() + "' "
                                    + "WHERE ITEM_DETAIL_ID = '" + req1.getITEM_DETAIL_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                }
            }
        }

        return true;
    }
}
