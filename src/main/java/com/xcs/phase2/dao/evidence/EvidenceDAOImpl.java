package com.xcs.phase2.dao.evidence;

import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.evidence.*;
import com.xcs.phase2.request.evidence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class EvidenceDAOImpl extends EvidenceExt implements EvidenceDAO{

    private static final Logger log = LoggerFactory.getLogger(EvidenceDAOImpl.class);

    @Override
    public EvidenceAccount EvidenceAccountgetByCon(EvidenceAccountgetByConReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT nvl(SUM(OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY),0) as BALANCE_QTY" +
                        "  FROM OPS_EVIDENCE_STOCK_BALANCE" +
                        "  JOIN OPS_EVIDENCE_IN_ITEM ON OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID = OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID" +
                        "  JOIN OPS_EVIDENCE_IN ON OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID = OPS_EVIDENCE_IN.EVIDENCE_IN_ID" +
                        "  JOIN (SELECT EVIDENCE_IN_ID,OFFICE_CODE,TRUNC(EVIDENCE_OUT_DATE)as EVIDENCE_OUT_DATE FROM OPS_EVIDENCE_OUT group by EVIDENCE_IN_ID,OFFICE_CODE,TRUNC(EVIDENCE_OUT_DATE))OPS_EVIDENCE_OUT on OPS_EVIDENCE_IN.EVIDENCE_IN_ID=OPS_EVIDENCE_OUT.EVIDENCE_IN_ID" +
                        "  WHERE OPS_EVIDENCE_OUT.EVIDENCE_OUT_DATE >= trunc(sysdate)" +
                        "  AND OPS_EVIDENCE_OUT.OFFICE_CODE = '"+req.getOFFICE_CODE()+"' " +
                        "  GROUP BY OPS_EVIDENCE_OUT.EVIDENCE_IN_ID");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceAccount>() {

            public EvidenceAccount extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceAccount item = new EvidenceAccount();
                    item.setBALANCE_QTY(rs.getInt("BALANCE_QTY"));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public EvidenceAccountWarehouse EvidenceAccountWarehoustgetByCon(EvidenceAccountWarehoustgetByConReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT " +
                        "  nvl(SUM(CASE WHEN EVIDENCE_OUT_TYPE = '0' AND EVIDENCE_OUT_TYPE = '1' THEN OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY ELSE 0 END),0) AS inside," +
                        "  nvl(SUM(CASE WHEN EVIDENCE_OUT_TYPE = '2' THEN OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY ELSE 0 END),0) AS destroy," +
                        "  nvl(SUM(CASE WHEN EVIDENCE_OUT_TYPE = '3' THEN OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY ELSE 0 END),0) AS sell," +
                        "  nvl(SUM(CASE WHEN EVIDENCE_OUT_TYPE = '4' THEN OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY ELSE 0 END),0) AS lend," +
                        "  nvl(SUM(CASE WHEN EVIDENCE_OUT_TYPE = '5' THEN OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY ELSE 0 END),0) AS keep," +
                        "  nvl(SUM(CASE WHEN EVIDENCE_OUT_TYPE = '7' THEN OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY ELSE 0 END),0) AS donate," +
                        "  nvl(SUM(CASE WHEN EVIDENCE_OUT_TYPE = '8' THEN OPS_EVIDENCE_STOCK_BALANCE.BALANCE_QTY ELSE 0 END),0) AS transfer" +
                        "  FROM OPS_EVIDENCE_STOCK_BALANCE " +
                        "  JOIN OPS_EVIDENCE_IN_ITEM ON OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID = OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID " +
                        "  JOIN OPS_EVIDENCE_IN ON OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID = OPS_EVIDENCE_IN.EVIDENCE_IN_ID " +
                        "  JOIN (SELECT EVIDENCE_IN_ID,EVIDENCE_OUT_TYPE,OFFICE_CODE,TRUNC(EVIDENCE_OUT_DATE)as EVIDENCE_OUT_DATE FROM OPS_EVIDENCE_OUT group by EVIDENCE_IN_ID,EVIDENCE_OUT_TYPE,OFFICE_CODE,TRUNC(EVIDENCE_OUT_DATE))OPS_EVIDENCE_OUT on OPS_EVIDENCE_IN.EVIDENCE_IN_ID=OPS_EVIDENCE_OUT.EVIDENCE_IN_ID " +
                        "  JOIN MAS_WAREHOUSE ON OPS_EVIDENCE_STOCK_BALANCE.WAREHOUSE_ID = MAS_WAREHOUSE.WAREHOUSE_ID " +
                        "  WHERE OPS_EVIDENCE_OUT.EVIDENCE_OUT_DATE >= trunc(sysdate)" +
                        "  AND OPS_EVIDENCE_OUT.OFFICE_CODE = '"+req.getOFFICE_CODE()+"' " +
                        "  AND MAS_WAREHOUSE.WAREHOUSE_ID = '"+req.getWAREHOUSE_ID()+"' " +
                        "  GROUP BY MAS_WAREHOUSE.WAREHOUSE_ID " );

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<EvidenceAccountWarehouse>() {

            public EvidenceAccountWarehouse extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    EvidenceAccountWarehouse item = new EvidenceAccountWarehouse();
                    item.setINSIDE(rs.getInt("INSIDE"));
                    item.setDESTROY(rs.getInt("DESTROY"));
                    item.setSELL(rs.getInt("SELL"));
                    item.setLEND(rs.getInt("LEND"));
                    item.setKEEP(rs.getInt("KEEP"));
                    item.setDONATE(rs.getInt("DONATE"));
                    item.setTRANSFER(rs.getInt("TRANSFER"));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public List<EvidenceAccountProduct> EvidenceAccountProductgetByCon(EvidenceAccountProductgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT" +
                        "  OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID," +
                        "  OPS_EVIDENCE_OUT.EVIDENCE_OUT_CODE," +
                        "  to_char(EVIDENCE_OUT_DATE,'" + Pattern.FORMAT_DATETIME + "') as EVIDENCE_OUT_DATE " +
                        "  FROM OPS_EVIDENCE_IN" +
                        "  JOIN OPS_EVIDENCE_IN_ITEM ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID=OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID" +
                        "  JOIN OPS_EVIDENCE_OUT ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID=OPS_EVIDENCE_OUT.EVIDENCE_IN_ID" +
                        "  JOIN OPS_EVIDENCE_STOCK_BALANCE ON OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID=OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID" +
                        "  JOIN MAS_WAREHOUSE ON OPS_EVIDENCE_STOCK_BALANCE.WAREHOUSE_ID = MAS_WAREHOUSE.WAREHOUSE_ID" +
                        "  WHERE OPS_EVIDENCE_OUT.EVIDENCE_OUT_TYPE = '"+req.getEVIDENCE_OUT_TYPE()+"'" +
                        "  AND OPS_EVIDENCE_OUT.OFFICE_CODE = '"+req.getOFFICE_CODE()+"'" +
                        "  AND MAS_WAREHOUSE.WAREHOUSE_ID = '"+req.getWAREHOUSE_ID()+"' ");

        log.info("[SQL ] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceAccountProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceAccountProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceAccountProduct item = new EvidenceAccountProduct();

                item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
                item.setEVIDENCE_OUT_CODE(rs.getString("EVIDENCE_OUT_CODE"));
                item.setEVIDENCE_OUT_DATE(rs.getString("EVIDENCE_OUT_DATE"));

                return item;

            }
        });
        return dataList;

    }

    @Override
    public List<EvidenceAccountProductDetail> EvidenceAccountProductDetailgetByCon(EvidenceAccountProductDetailgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT" +
                        "  OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID," +
                        "  OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_CODE," +
                        "  to_char(OPS_EVIDENCE_IN.EVIDENCE_IN_DATE,'" + Pattern.FORMAT_DATETIME + "') as EVIDENCE_IN_DATE, " +
                        "  MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME||'/'||MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_TH||'/'||MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_TH as PRODUCT_NAME" +
                        "  FROM OPS_EVIDENCE_OUT" +
                        "  LEFT JOIN OPS_EVIDENCE_IN on OPS_EVIDENCE_OUT.EVIDENCE_IN_ID = OPS_EVIDENCE_IN.EVIDENCE_IN_ID" +
                        "  LEFT JOIN OPS_EVIDENCE_IN_ITEM ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID=OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID" +
                        "  LEFT JOIN OPS_EVIDENCE_STOCK_BALANCE ON OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID=OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID" +
                        "  LEFT JOIN MAS_WAREHOUSE ON OPS_EVIDENCE_STOCK_BALANCE.WAREHOUSE_ID = MAS_WAREHOUSE.WAREHOUSE_ID" +
                        "  LEFT JOIN MAS_PRODUCT_GROUP ON OPS_EVIDENCE_IN_ITEM.PRODUCT_GROUP_ID=MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID" +
                        "  LEFT JOIN MAS_PRODUCT_BRAND ON OPS_EVIDENCE_IN_ITEM.PRODUCT_BRAND_ID=MAS_PRODUCT_BRAND.PRODUCT_BRAND_ID" +
                        "  LEFT JOIN MAS_PRODUCT_MODEL ON OPS_EVIDENCE_IN_ITEM.PRODUCT_MODEL_ID=MAS_PRODUCT_MODEL.PRODUCT_MODEL_ID" +
                        "  WHERE OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID = '"+req.getEVIDENCE_OUT_ID()+"' " +
                        "  AND OPS_EVIDENCE_OUT.OFFICE_CODE = '"+req.getOFFICE_CODE()+"' " +
                        "  AND MAS_WAREHOUSE.WAREHOUSE_ID = '"+req.getWAREHOUSE_ID()+"' ");

        log.info("[SQL ] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceAccountProductDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceAccountProductDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceAccountProductDetail item = new EvidenceAccountProductDetail();

                item.setEVIDENCE_IN_ITEM_ID(rs.getInt("EVIDENCE_IN_ITEM_ID"));
                item.setEVIDENCE_IN_ITEM_CODE(rs.getString("EVIDENCE_IN_ITEM_CODE"));
                item.setEVIDENCE_IN_DATE(rs.getString("EVIDENCE_IN_DATE"));
                item.setNAME(rs.getString("NAME"));

                return item;

            }
        });
        return dataList;

    }

    @Override
    public List<EvidenceAccountHistory> EvidenceAccountHistory(EvidenceAccountHistoryReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT" +
                        "  OPS_EVIDENCE_OUT.EVIDENCE_OUT_ID," +
                        "  OPS_EVIDENCE_OUT.EVIDENCE_OUT_CODE," +
                        "  to_char(OPS_EVIDENCE_OUT.EVIDENCE_OUT_DATE,'" + Pattern.FORMAT_DATETIME + "') as EVIDENCE_OUT_DATE " +
                        "  FROM OPS_EVIDENCE_IN" +
                        "  JOIN OPS_EVIDENCE_IN_ITEM ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID=OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ID" +
                        "  JOIN OPS_EVIDENCE_OUT ON OPS_EVIDENCE_IN.EVIDENCE_IN_ID=OPS_EVIDENCE_OUT.EVIDENCE_IN_ID" +
                        "  JOIN OPS_EVIDENCE_STOCK_BALANCE ON OPS_EVIDENCE_IN_ITEM.EVIDENCE_IN_ITEM_ID=OPS_EVIDENCE_STOCK_BALANCE.EVIDENCE_IN_ITEM_ID" +
                        "  JOIN MAS_WAREHOUSE ON OPS_EVIDENCE_STOCK_BALANCE.WAREHOUSE_ID = MAS_WAREHOUSE.WAREHOUSE_ID" +
                        "  WHERE OPS_EVIDENCE_OUT.OFFICE_CODE = '"+req.getOFFICE_CODE()+"' ");

        log.info("[SQL ] : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<EvidenceAccountHistory> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public EvidenceAccountHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
                EvidenceAccountHistory item = new EvidenceAccountHistory();

                item.setEVIDENCE_OUT_ID(rs.getInt("EVIDENCE_OUT_ID"));
                item.setEVIDENCE_OUT_CODE(rs.getString("EVIDENCE_OUT_CODE"));
                item.setEVIDENCE_OUT_DATE(rs.getString("EVIDENCE_OUT_DATE"));
                return item;

            }
        });
        return dataList;

    }
}
