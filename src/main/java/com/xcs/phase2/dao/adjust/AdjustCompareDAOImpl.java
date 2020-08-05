package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.adjust.AdjustCompare;
import com.xcs.phase2.request.adjust.AdjustComparegetByConReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@Transactional
public class AdjustCompareDAOImpl extends AdjustExt implements AdjustCompareDAO{

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareDAOImpl.class);

    @Override
    public AdjustCompare AdjustComparegetByCon(AdjustComparegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "COMPARE_ID," +
                        "LAWSUIT_ID," +
                        "OFFICE_ID," +
                        "TREASURY_RATE," +
                        "BRIBE_RATE," +
                        "REWARD_RATE," +
                        "OFFICE_CODE," +
                        "OFFICE_NAME," +
                        "COMPARE_NO," +
                        "to_char(COMPARE_NO_YEAR,'"+ Pattern.FORMAT_DATETIME+"') as COMPARE_NO_YEAR," +
                        "to_char(COMPARE_DATE,'"+Pattern.FORMAT_DATETIME+"') as COMPARE_DATE," +
                        "IS_OUTSIDE," +
                        "IS_ACTIVE," +
                        "to_char(CREATE_DATE,'"+Pattern.FORMAT_DATETIME+"') as CREATE_DATE," +
                        "CREATE_USER_ACCOUNT_ID," +
                        "to_char(UPDATE_DATE,'"+Pattern.FORMAT_DATETIME+"') as UPDATE_DATE," +
                        "UPDATE_USER_ACCOUNT_ID " +
                        "from OPS_COMPARE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and COMPARE_ID = '"+req.getCOMPARE_ID()+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<AdjustCompare>() {

            public AdjustCompare extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    AdjustCompare item = new AdjustCompare();
                    item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                    item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                    item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                    item.setTREASURY_RATE(rs.getFloat("TREASURY_RATE"));
                    item.setBRIBE_RATE(rs.getFloat("BRIBE_RATE"));
                    item.setREWARD_RATE(rs.getFloat("REWARD_RATE"));
                    item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                    item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                    item.setCOMPARE_NO(rs.getInt("COMPARE_NO"));
                    item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
                    item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                    item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                    item.setCREATE_USER_ACCOUNT_ID(rs.getInt("CREATE_USER_ACCOUNT_ID"));
                    item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                    item.setUPDATE_USER_ACCOUNT_ID(rs.getInt("UPDATE_USER_ACCOUNT_ID"));


                    item.setAdjustCompareMapping(getCompareMapping(rs.getInt("COMPARE_ID")));
                    item.setAdjustCompareStaff(getCompareStaff(rs.getInt("COMPARE_ID")));

                    return item;
                }

                return null;
            }
        });
    }
}
