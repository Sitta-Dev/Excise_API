package com.xcs.phase2.dao.target;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.target.TargetItem;
import com.xcs.phase2.response.target.TargetItemResponse;
import com.xcs.phase2.response.target.TargetIteminsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TargetItemDAOImpl extends TargetExt implements TargetItemDAO{

    private static final Logger log = LoggerFactory.getLogger(TargetItemDAOImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TargetIteminsAllResponse TargetIteminsAll(List<TargetItem> req) {

        TargetIteminsAllResponse res = new TargetIteminsAllResponse();

        try {

            if (req != null) {
                log.info("[Sub] Size : " + req.size());
                List<TargetItemResponse> list = new ArrayList<TargetItemResponse>();

                if (req.size() > 0) {
                    for (TargetItem item : req) {

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
                                        "'" + item.getTARGET_ID() + "'," +
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
            res.setTargetItem(null);
            return res;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean TargetItemupdByCon(List<TargetItem> request) {

        if(request != null) {
            log.info("[Sub] Size : "+request.size());

            if(request.size() > 0){

                for(TargetItem req : request){

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
                }
            }
        }

        return true;
    }
}
