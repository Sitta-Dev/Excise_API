package com.xcs.phase2.dao.other;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.other.RemainProduct;
import com.xcs.phase2.response.Other.RemainProductinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RemainProductDAOImpl extends OtherExt implements RemainProductDAO {

    private static final Logger log = LoggerFactory.getLogger(RemainProductDAOImpl.class);






    @Override
    public RemainProductinsAllResponse RemainProductinsAll(RemainProduct req) {

        RemainProductinsAllResponse res = new RemainProductinsAllResponse();


        StringBuilder sqlBuilder1 = new StringBuilder().append("DELETE FROM OPS_REMAIN_PRODUCT WHERE ARREST_PRODUCT_ID = "+req.getARREST_PRODUCT_ID()+" AND ARREST_ID = "+req.getARREST_ID()+" ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});



        String REMAIN_PRODUCT_ID = getSequences("SELECT OPS_REMAIN_PRODUCT_SEQ.NEXTVAL FROM DUAL");
        StringBuilder sqlBuilder = new StringBuilder()
                .append("Insert into OPS_REMAIN_PRODUCT ( " +
                        "REMAIN_PRODUCT_ID," +
                        "ARREST_PRODUCT_ID," +
                        "REMAIN_SIZES," +
                        "REMAIN_SIZES_UNIT," +
                        "REMAIN_QUANTITY," +
                        "REMAIN_QUANTITY_UNIT," +
                        "REMAIN_VOLUMN," +
                        "REMAIN_VOLUMN_UNIT," +
                        "ARREST_ID," +
                        "IS_STATUS," +
                        "PRODUCT_DESC," +
                        "REMARK," +
                        "IS_ACTIVE" +
                        " ) values (" +
                        "'" + REMAIN_PRODUCT_ID + "'," +
                        "'"+req.getARREST_PRODUCT_ID()+"'," +
                        "'"+req.getREMAIN_SIZES()+"'," +
                        "'"+req.getREMAIN_SIZES_UNIT()+"'," +
                        "'"+req.getREMAIN_QUANTITY()+"'," +
                        "'"+req.getREMAIN_QUANTITY_UNIT()+"'," +
                        "'"+req.getREMAIN_VOLUMN()+"'," +
                        "'"+req.getREMAIN_VOLUMN_UNIT()+"'," +
                        "'"+req.getARREST_ID()+"'," +
                        "'"+req.getIS_STATUS()+"'," +
                        "'"+req.getPRODUCT_DESC()+"'," +
                        "'"+req.getREMARK()+"'," +
                        "'" + 1 + "'" +
                        " )");


        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
        res.setREMAIN_PRODUCT_ID(Integer.parseInt(REMAIN_PRODUCT_ID));

        res.setIsSuccess(Message.TRUE);
        res.setMsg(Message.COMPLETE);

        return res;

    }
}
