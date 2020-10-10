package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.revenue.IncPayment;
import com.xcs.phase2.model.revenue.RevenueIncPayment;
import com.xcs.phase2.model.revenue.RevenueIncPaymentType;
import com.xcs.phase2.request.revenue.IncPaymentListGetByConReq;
import com.xcs.phase2.request.revenue.IncPaymentListUpdDeleteReq;
import com.xcs.phase2.response.revenue.IncPaymentListinsAllResponse;
import com.xcs.phase2.response.revenue.RevenueIncPaymentResponse;
import com.xcs.phase2.response.revenue.RevenueIncPaymentTypeResponse;
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
public class IncPaymentListinsAllDAOImpl extends RevenueExt implements IncPaymentListDAO{

    private static final Logger log = LoggerFactory.getLogger(IncPaymentListinsAllDAOImpl.class);


  @Override
  @Transactional(rollbackFor = Exception.class)
  public IncPaymentListinsAllResponse IncPaymentListinsAll(RevenueIncPayment req) {
  	
  	IncPaymentListinsAllResponse res = new IncPaymentListinsAllResponse();
		
		try {
			
			if(req != null) {
			    if (req.getIncPayment().size() > 0) {
			    	
			    	List<RevenueIncPaymentResponse> list = new ArrayList<RevenueIncPaymentResponse>();   	
			    	
			    	for(IncPayment item : req.getIncPayment()) { 	
			    		String INC_PAYMENT_ID = getSequences("SELECT INC_PAYMENT_SEQ.NEXTVAL FROM DUAL");
			    		RevenueIncPaymentResponse obj = new RevenueIncPaymentResponse();
			    		obj.setINC_PAYMENT_ID(Integer.parseInt(INC_PAYMENT_ID));

			    		StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("Insert into INC_PAYMENT ( " +
                                    "INC_PAYMENT_ID," +
                                    "SEQ_NO," +
                                    "GROUPID," +
                                    "TAX_AMT," +
                                    "BRIBE_AMT," +
                                    "REWARD_AMT," +
                                    "COUNT_NUM," +
                                    "REVENUE_ID," +
                                    "IS_ACTIVE," +
                                    "COMPARE_DETAIL_ID" +
                                    " ) values (" +
                                    "'" + INC_PAYMENT_ID + "'," +
                                    "'" + item.getSEQ_NO() + "'," +
                                    "'" + item.getGROUPID() + "'," +
                                    "'" + item.getTAX_AMT() + "'," +
                                    "'" + item.getBRIBE_AMT() + "'," +
                                    "'" + item.getREWARD_AMT() + "'," +
                                    "'" + item.getCOUNT_NUM() + "'," +
                                    "'" + item.getREVENUE_ID() + "'," +
                                    "'" + item.getIS_ACTIVE() + "'," +
                                    "'" + item.getCOMPARE_DETAIL_ID() + "'" +
                                    " )");
			    		log.info("[SQL] : " + sqlBuilderSub.toString());
			    				
			    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
					    list.add(obj);
					    
			    		}//for IncPayment
			    	res.setRevenueIncPayment(list);
			    }//if IncPayment
			    		if (req.getRevenueIncPaymentType().size() > 0) {
			    			
			    			List<RevenueIncPaymentTypeResponse> list1 = new ArrayList<RevenueIncPaymentTypeResponse>();
			    			
			    			for (RevenueIncPaymentType item1 : req.getRevenueIncPaymentType()) {
			    				
			    				String INC_PAYMENT_TYPE_ID = getSequences("SELECT INC_PAYMENT_TYPE_SEQ.NEXTVAL FROM DUAL");
			    				RevenueIncPaymentTypeResponse obj1 = new RevenueIncPaymentTypeResponse();
			    			    obj1.setINC_PAYMENT_TYPE_ID(Integer.parseInt(INC_PAYMENT_TYPE_ID));
			    				
			    				StringBuilder sqlBuilderSub1 = new StringBuilder()
			    				  .append("Insert into INC_PAYMENT_TYPE ( " +
			    				          "INC_PAYMENT_TYPE_ID," +
			    				          "REVENUE_ID," +
			    				          "PAYMENT_TYPE," +
			    				          "BANK_CODE," +
			    				          "BRANCH_CODE," +
			    				          "CHEQUE_TYPE," +
			    				          "CHEQUE_FLAG," +
			    				          "CHWQUE_NO," +
			    				          "CHEQUE_DATE," +
			    				          "PAYMENT_AMT," +
			    				          "ADJUST_TYPE," +
			    			              "PAYMENT_ID," +
			    			              "IS_ACTIVE" +
			    				          " ) values (" +
			    				          "'"+INC_PAYMENT_TYPE_ID+"'," +
			    				          "'"+item1.getREVENUE_ID()+"'," +
			    				          "'"+item1.getPAYMENT_TYPE()+"'," +
			    				          "'"+item1.getBANK_CODE()+"'," +
			                              "'"+item1.getBRANCH_CODE()+"'," +
			    				          "'"+item1.getCHEQUE_TYPE()+"'," +
			    				          "'"+item1.getCHEQUE_FLAG()+"'," +
			    				          "'"+item1.getCHWQUE_NO()+"'," +
			    				          "'"+item1.getCHEQUE_DATE()+"'," +
			    				          "'"+item1.getPAYMENT_AMT()+"'," +
			    				          "'"+item1.getADJUST_TYPE()+"'," +
			    				          "'"+item1.getPAYMENT_ID()+"'," +
			    				          "'"+item1.getIS_ACTIVE()+"'" +
			    				          " )");
			    				          log.info("[SQL] : " + sqlBuilderSub1.toString());
			    				
			    				          getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[]{});
			    				          list1.add(obj1);
			    				  }//for RevenueIncPaymentType
			    			res.setRevenueIncPaymentType(list1);
			    		}//if RevenueIncPaymentType	
		    }//if
			
			res.setIsSuccess(Message.TRUE);
		    res.setMsg(Message.COMPLETE);
		    
		    return res;
			
		}//try
		catch (Exception e) {
			e.printStackTrace();
			res.setIsSuccess(Message.FALSE);
		    res.setMsg(e.getMessage());
			return res;
		}
  }
    

    @Override
    public RevenueIncPayment IncPaymentListGetByCon(IncPaymentListGetByConReq req) {
    	@SuppressWarnings("unchecked")
    	
    	RevenueIncPayment item = new RevenueIncPayment();
    	item.setIncPayment(getIncPayment(req.getREVENUE_ID()));
    	item.setRevenueIncPaymentType(getRevenueIncPaymentType(req.getREVENUE_ID()));
        item.setRevenueIncCompareDetail(getRevenueIncCompareDetail(req.getREVENUE_ID()));
		return item;	
    }


    @Override
    public boolean IncPaymentListUpdDelete(IncPaymentListUpdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE INC_PAYMENT SET IS_ACTIVE = '0' WHERE REVENUE_ID = '"+req.getREVENUE_ID()+"' ");
        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE INC_PAYMENT_TYPE SET IS_ACTIVE = '0' WHERE REVENUE_ID = "+req.getREVENUE_ID()+"  ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
        return true;
    }
}
