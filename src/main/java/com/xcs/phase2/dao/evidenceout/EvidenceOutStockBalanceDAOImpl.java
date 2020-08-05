package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.request.evidenceout.EvidenceOutStockBalanceupdByConReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EvidenceOutStockBalanceDAOImpl extends EvidenceOutExt implements EvidenceOutStockBalanceDAO{

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutStockBalanceDAOImpl.class);

    @Override
    public Boolean EvidenceOutStockBalanceupdByCon(EvidenceOutStockBalanceupdByConReq req) {


        StringBuilder sqlBuilder1 = new StringBuilder()
                .append("UPDATE OPS_EVIDENCE_STOCK_BALANCE SET BALANCE_QTY = '"+req.getBALANCE_QTY()+"' , IS_FINISH = '"+req.getIS_FINISH()+"' WHERE STOCK_ID = '"+req.getSTOCK_ID()+"' ");

        log.info("[SQL] ops_evidence_out : "+sqlBuilder1.toString());

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});

        return true;

    }


}
