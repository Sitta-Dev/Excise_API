package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.NewProveArrestIndictment;
import com.xcs.phase2.model.prove.ProveArrestIndictmentProduct;
import com.xcs.phase2.request.prove.ProveArrestIndictmentProductgetByConReq;
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
public class ProveArrestIndictmentProductDAOImpl extends ProveExt implements ProveArrestIndictmentProductDAO {

    private static final Logger log = LoggerFactory.getLogger(ProveArrestIndictmentProductDAOImpl.class);

    @Override
    public List<ProveArrestIndictmentProduct> ProveArrestIndictmentProductgetByCon(ProveArrestIndictmentProductgetByConReq req) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "OPS_ARREST_PRODUCT.PRODUCT_ID," +
                        "OPS_ARREST_PRODUCT.PRODUCT_DESC," +
                        "OPS_ARREST_PRODUCT.DEGREE," +
                        "OPS_ARREST_PRODUCT.SUGAR," +
                        "OPS_ARREST_PRODUCT.CO2," +
                        "OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_INDICTMENT_ID," +
                        "OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID," +
                        "OPS_ARREST_INDICTMENT_PRODUCT.SIZES," +
                        "OPS_ARREST_INDICTMENT_PRODUCT.SIZES_UNIT," +
                        "OPS_ARREST_INDICTMENT_PRODUCT.QUANTITY," +
                        "OPS_ARREST_INDICTMENT_PRODUCT.QUANTITY_UNIT," +
                        "OPS_ARREST_INDICTMENT_PRODUCT.VOLUMN," +
                        "OPS_ARREST_INDICTMENT_PRODUCT.VOLUMN_UNIT," +
                        "OPS_ARREST_PRODUCT.PRODUCT_CODE," +
                        "OPS_ARREST_PRODUCT.PRODUCT_GROUP_ID," +
                        "OPS_ARREST_PRODUCT.PRODUCT_GROUP_CODE," +
                        "OPS_ARREST_PRODUCT.PRODUCT_CATEGORY_ID," +
                        "OPS_ARREST_PRODUCT.PRODUCT_CATEGORY_CODE" +
                        " from OPS_ARREST_INDICTMENT_PRODUCT" +
                        " left join OPS_ARREST_PRODUCT on OPS_ARREST_PRODUCT.PRODUCT_ID=OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID and OPS_ARREST_PRODUCT.IS_ACTIVE=1" +
                        " Where OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID = (select max(INDICTMENT_DETAIL_ID) as INDICTMENT_DETAIL_ID  from OPS_ARREST_INDICTMENT_DETAIL where INDICTMENT_ID = '"+req.getINDICTMENT_ID()+"') ");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<ProveArrestIndictmentProduct> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ProveArrestIndictmentProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProveArrestIndictmentProduct item = new ProveArrestIndictmentProduct();
                item.setPRODUCT_INDICTMENT_ID(rs.getInt("PRODUCT_INDICTMENT_ID"));
                item.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setPRODUCT_DESC(rs.getString("PRODUCT_DESC"));
                item.setDEGREE(rs.getFloat("DEGREE"));
                item.setSUGAR(rs.getFloat("SUGAR"));
                item.setCO2(rs.getFloat("CO2"));
                item.setSIZES(rs.getFloat("SIZES"));
                item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
                item.setQUANTITY(rs.getFloat("QUANTITY"));
                item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
                item.setVOLUMN(rs.getFloat("VOLUMN"));
                item.setVOLUMN_UNIT(rs.getString("VOLUMN_UNIT"));

                item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
                item.setPRODUCT_GROUP_ID(rs.getString("PRODUCT_GROUP_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setPRODUCT_CATEGORY_ID(rs.getString("PRODUCT_CATEGORY_ID"));
                item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));

                item.setArrestProduct(getArrestProductByProductId(rs.getInt("PRODUCT_ID")));
                //item.setIS_ILLEGAL(rs.getInt("IS_ILLEGAL"));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public NewProveArrestIndictment NewProveArrestIndictmentProductgetByCon(ProveArrestIndictmentProductgetByConReq req) {



        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select * from ops_arrest_indictment where IS_ACTIVE = 1 and indictment_id = "+req.getINDICTMENT_ID());

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<NewProveArrestIndictment>() {

            public NewProveArrestIndictment extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    NewProveArrestIndictment item = new NewProveArrestIndictment();
                    item.setARREST_ID(rs.getInt("ARREST_ID"));
                    item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                    item.setGUILTBASE_ID(rs.getInt("GUILTBASE_ID"));
                    item.setFINE_ESTIMATE(rs.getFloat("FINE_ESTIMATE"));
                    item.setIS_LAWSUIT_COMPLETE(rs.getInt("IS_LAWSUIT_COMPLETE"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    item.setProveArrestProduct(getNewProveArrestProduct(rs.getInt("ARREST_ID")));

                    return item;
                }

                return null;
            }
        });
    }
}
