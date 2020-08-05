package com.xcs.phase2.dao.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.compare.CompareDetailFine;
import com.xcs.phase2.request.compare.CompareDetailFineupdDeleteReq;
import com.xcs.phase2.response.compare.CompareDetailFineResponse;
import com.xcs.phase2.response.compare.CompareDetailFineinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompareDetailFineDAOImpl extends CompareExt implements CompareDetailFineDAO{

    private static final Logger log = LoggerFactory.getLogger(CompareDetailFineDAOImpl.class);

    @Override
    public CompareDetailFineinsAllResponse CompareDetailFineinsAll(List<CompareDetailFine> req) {

        CompareDetailFineinsAllResponse res = new CompareDetailFineinsAllResponse();

        try {


            if(req != null) {
                log.info("[Sub] Size : "+req.size());
                List<CompareDetailFineResponse> list = new ArrayList<CompareDetailFineResponse>();
                if(req.size() > 0){

                    for(CompareDetailFine item2 : req){

                        String FINE_ID = getSequences("SELECT OPS_COMPARE_FINE_SEQ.NEXTVAL FROM DUAL");
                        CompareDetailFineResponse  obj = new CompareDetailFineResponse();
                        obj.setFINE_ID(Integer.parseInt(FINE_ID));

                        StringBuilder sqlBuilderCompareDetailFine = new StringBuilder()
                                .append("Insert into OPS_COMPARE_DETAIL_FINE ( " +
                                        "FINE_ID," +
                                        "COMPARE_DETAIL_ID," +
                                        "PRODUCT_ID," +
                                        "FINE_RATE," +
                                        "VAT," +
                                        "FINE," +
                                        "NET_FINE," +
                                        "OLD_PAYMENT_FINE," +
                                        "PAYMENT_FINE," +
                                        "DIFFERENCE_PAYMENT_FINE," +
                                        "TREASURY_MONEY," +
                                        "BRIBE_MONEY," +
                                        "REWARD_MONEY," +
                                        "IS_ACTIVE " +
                                        " ) values (" +
                                        "'"+FINE_ID+"'," +
                                        "'"+item2.getCOMPARE_DETAIL_ID()+"'," +
                                        "'"+item2.getPRODUCT_ID()+"'," +
                                        "'"+item2.getFINE_RATE()+"'," +
                                        "'"+item2.getVAT()+"'," +
                                        "'"+item2.getFINE()+"'," +
                                        "'"+item2.getNET_FINE()+"'," +
                                        "'"+item2.getOLD_PAYMENT_FINE()+"'," +
                                        "'"+item2.getPAYMENT_FINE()+"'," +
                                        "'"+item2.getDIFFERENCE_PAYMENT_FINE()+"'," +
                                        "'"+item2.getTREASURY_MONEY()+"'," +
                                        "'"+item2.getBRIBE_MONEY()+"'," +
                                        "'"+item2.getREWARD_MONEY()+"'," +
                                        "'"+item2.getIS_ACTIVE()+"'" +
                                        " )");
                        log.info("[SQL] : "+sqlBuilderCompareDetailFine.toString());
                        getJdbcTemplate().update(sqlBuilderCompareDetailFine.toString(), new Object[] {});

                        list.add(obj);
                    }
                }
                res.setCompareDetailFine(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setCompareDetailFine(null);
            return res;
        }
    }

    @Override
    public Boolean CompareDetailFineupdByCon(List<CompareDetailFine> request) {

        if(request != null) {
            log.info("[Sub] Size : "+request.size());

            if(request.size() > 0){

                for(CompareDetailFine req : request){

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE OPS_COMPARE_DETAIL_FINE "
                                    + "SET "
                                    + "COMPARE_DETAIL_ID=  '"+req.getCOMPARE_DETAIL_ID()+"', "
                                    + "PRODUCT_ID=  '"+req.getPRODUCT_ID()+"', "
                                    + "FINE_RATE=  '"+req.getFINE_RATE()+"', "
                                    + "VAT=  '"+req.getVAT()+"', "
                                    + "FINE=  '"+req.getFINE()+"', "
                                    + "NET_FINE=  '"+req.getNET_FINE()+"', "
                                    + "OLD_PAYMENT_FINE=  '"+req.getOLD_PAYMENT_FINE()+"', "
                                    + "PAYMENT_FINE=  '"+req.getPAYMENT_FINE()+"', "
                                    + "DIFFERENCE_PAYMENT_FINE=  '"+req.getDIFFERENCE_PAYMENT_FINE()+"', "
                                    + "TREASURY_MONEY=  '"+req.getTREASURY_MONEY()+"', "
                                    + "BRIBE_MONEY=  '"+req.getBRIBE_MONEY()+"', "
                                    + "REWARD_MONEY=  '"+req.getREWARD_MONEY()+"', "
                                    +"IS_ACTIVE = '"+req.getIS_ACTIVE()+"' "
                                    + "WHERE FINE_ID = '"+req.getFINE_ID()+"' ");
                    log.info("[SQL] : "+sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
                }
            }
        }

        return true;
    }

    @Override
    public Boolean CompareDetailFineupdDelete(List<CompareDetailFineupdDeleteReq> req) {

        if(req != null) {
            log.info("[Sub] Size : "+req.size());

            if(req.size() > 0){

                for(CompareDetailFineupdDeleteReq item : req){

                    StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_COMPARE_DETAIL_FINE SET IS_ACTIVE = '0' WHERE FINE_ID = '"+item.getFINE_ID()+"' ");
                    log.info("[SQL] Sub : "+sqlBuilder.toString());
                    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

                }
            }
        }

        return true;
    }
}
