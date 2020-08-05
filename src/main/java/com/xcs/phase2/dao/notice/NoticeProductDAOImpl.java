package com.xcs.phase2.dao.notice;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.notice.NoticeProduct;
import com.xcs.phase2.request.notice.NoticeProductupdDeleteReq;
import com.xcs.phase2.response.notice.NoticeProductinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoticeProductDAOImpl extends NoticeExt implements NoticeProductDAO {

    private static final Logger log = LoggerFactory.getLogger(NoticeProductDAOImpl.class);


    @Override
    public NoticeProductinsAllResponse NoticeProductinsAll(NoticeProduct item) {

        NoticeProductinsAllResponse res = new NoticeProductinsAllResponse();

        try {

            String PRODUCT_ID = getSequences("SELECT OPS_NOTICE_PRODUCT_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_NOTICE_PRODUCT ( " +
                            "PRODUCT_ID," +
                            "NOTICE_ID," +
                            "PRODUCT_MAPPING_ID," +
                            "PRODUCT_CODE," +
                            "PRODUCT_REF_CODE," +
                            "PRODUCT_GROUP_ID," +
                            "PRODUCT_CATEGORY_ID," +
                            "PRODUCT_TYPE_ID," +
                            "PRODUCT_SUBTYPE_ID," +
                            "PRODUCT_SUBSETTYPE_ID," +
                            "PRODUCT_BRAND_ID," +
                            "PRODUCT_SUBBRAND_ID," +
                            "PRODUCT_MODEL_ID," +
                            "PRODUCT_TAXDETAIL_ID," +
                            "SIZES_UNIT_ID," +
                            "QUATITY_UNIT_ID," +
                            "VOLUMN_UNIT_ID," +
                            "PRODUCT_GROUP_CODE," +
                            "PRODUCT_GROUP_NAME," +
                            "PRODUCT_CATEGORY_CODE," +
                            "PRODUCT_CATEGORY_NAME," +
                            "PRODUCT_TYPE_CODE," +
                            "PRODUCT_TYPE_NAME," +
                            "PRODUCT_SUBTYPE_CODE," +
                            "PRODUCT_SUBTYPE_NAME," +
                            "PRODUCT_SUBSETTYPE_CODE," +
                            "PRODUCT_SUBSETTYPE_NAME," +
                            "PRODUCT_BRAND_CODE," +
                            "PRODUCT_BRAND_NAME_TH," +
                            "PRODUCT_BRAND_NAME_EN," +
                            "PRODUCT_SUBBRAND_CODE," +
                            "PRODUCT_SUBBRAND_NAME_TH," +
                            "PRODUCT_SUBBRAND_NAME_EN," +
                            "PRODUCT_MODEL_CODE," +
                            "PRODUCT_MODEL_NAME_TH," +
                            "PRODUCT_MODEL_NAME_EN," +
                            "IS_TAX_VALUE," +
                            "TAX_VALUE," +
                            "IS_TAX_VOLUMN," +
                            "TAX_VOLUMN," +
                            "TAX_VOLUMN_UNIT," +
                            "LICENSE_PLATE," +
                            "ENGINE_NO," +
                            "CHASSIS_NO," +
                            "PRODUCT_DESC," +
                            "SUGAR," +
                            "CO2," +
                            "DEGREE," +
                            "PRICE," +
                            "SIZES," +
                            "SIZES_UNIT," +
                            "QUANTITY," +
                            "QUANTITY_UNIT," +
                            "VOLUMN," +
                            "VOLUMN_UNIT," +
                            "REMARK," +
                            "IS_DOMESTIC," +
                            "IS_ILLEGAL," +
                            "IS_ACTIVE " +
                            " ) values (" +
                            "'" + PRODUCT_ID + "'," +
                            "'" + item.getNOTICE_ID() + "'," +
                            "'" + item.getPRODUCT_MAPPING_ID() + "'," +
                            "'" + item.getPRODUCT_CODE() + "'," +
                            "'" + item.getPRODUCT_REF_CODE() + "'," +
                            "'" + item.getPRODUCT_GROUP_ID() + "'," +
                            "'" + item.getPRODUCT_CATEGORY_ID() + "'," +
                            "'" + item.getPRODUCT_TYPE_ID() + "'," +
                            "'" + item.getPRODUCT_SUBTYPE_ID() + "'," +
                            "'" + item.getPRODUCT_SUBSETTYPE_ID() + "'," +
                            "'" + item.getPRODUCT_BRAND_ID() + "'," +
                            "'" + item.getPRODUCT_SUBBRAND_ID() + "'," +
                            "'" + item.getPRODUCT_MODEL_ID() + "'," +
                            "'" + item.getPRODUCT_TAXDETAIL_ID() + "'," +
                            "'" + item.getSIZES_UNIT_ID() + "'," +
                            "'" + item.getQUATITY_UNIT_ID() + "'," +
                            "'" + item.getVOLUMN_UNIT_ID() + "'," +
                            "'" + item.getPRODUCT_GROUP_CODE() + "'," +
                            "'" + item.getPRODUCT_GROUP_NAME() + "'," +
                            "'" + item.getPRODUCT_CATEGORY_CODE() + "'," +
                            "'" + item.getPRODUCT_CATEGORY_NAME() + "'," +
                            "'" + item.getPRODUCT_TYPE_CODE() + "'," +
                            "'" + item.getPRODUCT_TYPE_NAME() + "'," +
                            "'" + item.getPRODUCT_SUBTYPE_CODE() + "'," +
                            "'" + item.getPRODUCT_SUBTYPE_NAME() + "'," +
                            "'" + item.getPRODUCT_SUBSETTYPE_CODE() + "'," +
                            "'" + item.getPRODUCT_SUBSETTYPE_NAME() + "'," +
                            "'" + item.getPRODUCT_BRAND_CODE() + "'," +
                            "'" + item.getPRODUCT_BRAND_NAME_TH() + "'," +
                            "'" + item.getPRODUCT_BRAND_NAME_EN() + "'," +
                            "'" + item.getPRODUCT_SUBBRAND_CODE() + "'," +
                            "'" + item.getPRODUCT_SUBBRAND_NAME_TH() + "'," +
                            "'" + item.getPRODUCT_SUBBRAND_NAME_EN() + "'," +
                            "'" + item.getPRODUCT_MODEL_CODE() + "'," +
                            "'" + item.getPRODUCT_MODEL_NAME_TH() + "'," +
                            "'" + item.getPRODUCT_MODEL_NAME_EN() + "'," +
                            "'" + item.getIS_TAX_VALUE() + "'," +
                            "'" + item.getTAX_VALUE() + "'," +
                            "'" + item.getIS_TAX_VOLUMN() + "'," +
                            "'" + item.getTAX_VOLUMN() + "'," +
                            "'" + item.getTAX_VOLUMN_UNIT() + "'," +
                            "'" + item.getLICENSE_PLATE() + "'," +
                            "'" + item.getENGINE_NO() + "'," +
                            "'" + item.getCHASSIS_NO() + "'," +
                            "'" + item.getPRODUCT_DESC() + "'," +
                            "'" + item.getSUGAR() + "'," +
                            "'" + item.getCO2() + "'," +
                            "'" + item.getDEGREE() + "'," +
                            "'" + item.getPRICE() + "'," +
                            "'" + item.getSIZES() + "'," +
                            "'" + item.getSIZES_UNIT() + "'," +
                            "'" + item.getQUANTITY() + "'," +
                            "'" + item.getQUANTITY_UNIT() + "'," +
                            "'" + item.getVOLUMN() + "'," +
                            "'" + item.getVOLUMN_UNIT() + "'," +
                            "'" + item.getREMARK() + "'," +
                            "'" + item.getIS_DOMESTIC() + "'," +
                            "'" + item.getIS_ILLEGAL() + "'," +
                            "'" + item.getIS_ACTIVE() + "'" +
                            " )");
            log.info("[SQL] : " + sqlBuilder.toString());

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setPRODUCT_ID(Integer.parseInt(PRODUCT_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setPRODUCT_ID(0);
            return res;
        }
    }

    @Override
    public Boolean NoticeProductupdByCon(NoticeProduct req) {

        StringBuilder sqlBuilderSub = new StringBuilder()
                .append("UPDATE OPS_NOTICE_PRODUCT "
                        + "SET "
                        + "NOTICE_ID=  '" + req.getNOTICE_ID() + "', "
                        + "PRODUCT_MAPPING_ID=  '" + req.getPRODUCT_MAPPING_ID() + "', "
                        + "PRODUCT_CODE=  '" + req.getPRODUCT_CODE() + "', "
                        + "PRODUCT_REF_CODE=  '" + req.getPRODUCT_REF_CODE() + "', "
                        + "PRODUCT_GROUP_ID=  '" + req.getPRODUCT_GROUP_ID() + "', "
                        + "PRODUCT_CATEGORY_ID=  '" + req.getPRODUCT_CATEGORY_ID() + "', "
                        + "PRODUCT_TYPE_ID=  '" + req.getPRODUCT_TYPE_ID() + "', "
                        + "PRODUCT_SUBTYPE_ID=  '" + req.getPRODUCT_SUBTYPE_ID() + "', "
                        + "PRODUCT_SUBSETTYPE_ID=  '" + req.getPRODUCT_SUBSETTYPE_ID() + "', "
                        + "PRODUCT_BRAND_ID=  '" + req.getPRODUCT_BRAND_ID() + "', "
                        + "PRODUCT_SUBBRAND_ID=  '" + req.getPRODUCT_SUBBRAND_ID() + "', "
                        + "PRODUCT_MODEL_ID=  '" + req.getPRODUCT_MODEL_ID() + "', "
                        + "PRODUCT_TAXDETAIL_ID=  '" + req.getPRODUCT_TAXDETAIL_ID() + "', "
                        + "SIZES_UNIT_ID=  '" + req.getSIZES_UNIT_ID() + "', "
                        + "QUATITY_UNIT_ID=  '" + req.getQUATITY_UNIT_ID() + "', "
                        + "VOLUMN_UNIT_ID=  '" + req.getVOLUMN_UNIT_ID() + "', "
                        + "PRODUCT_GROUP_CODE=  '" + req.getPRODUCT_GROUP_CODE() + "', "
                        + "PRODUCT_GROUP_NAME=  '" + req.getPRODUCT_GROUP_NAME() + "', "
                        + "PRODUCT_CATEGORY_CODE=  '" + req.getPRODUCT_CATEGORY_CODE() + "', "
                        + "PRODUCT_CATEGORY_NAME=  '" + req.getPRODUCT_CATEGORY_NAME() + "', "
                        + "PRODUCT_TYPE_CODE=  '" + req.getPRODUCT_TYPE_CODE() + "', "
                        + "PRODUCT_TYPE_NAME=  '" + req.getPRODUCT_TYPE_NAME() + "', "
                        + "PRODUCT_SUBTYPE_CODE=  '" + req.getPRODUCT_SUBTYPE_CODE() + "', "
                        + "PRODUCT_SUBTYPE_NAME=  '" + req.getPRODUCT_SUBTYPE_NAME() + "', "
                        + "PRODUCT_SUBSETTYPE_CODE=  '" + req.getPRODUCT_SUBSETTYPE_CODE() + "', "
                        + "PRODUCT_SUBSETTYPE_NAME=  '" + req.getPRODUCT_SUBSETTYPE_NAME() + "', "
                        + "PRODUCT_BRAND_CODE=  '" + req.getPRODUCT_BRAND_CODE() + "', "
                        + "PRODUCT_BRAND_NAME_TH=  '" + req.getPRODUCT_BRAND_NAME_TH() + "', "
                        + "PRODUCT_BRAND_NAME_EN=  '" + req.getPRODUCT_BRAND_NAME_EN() + "', "
                        + "PRODUCT_SUBBRAND_CODE=  '" + req.getPRODUCT_SUBBRAND_CODE() + "', "
                        + "PRODUCT_SUBBRAND_NAME_TH=  '" + req.getPRODUCT_SUBBRAND_NAME_TH() + "', "
                        + "PRODUCT_SUBBRAND_NAME_EN=  '" + req.getPRODUCT_SUBBRAND_NAME_EN() + "', "
                        + "PRODUCT_MODEL_CODE=  '" + req.getPRODUCT_MODEL_CODE() + "', "
                        + "PRODUCT_MODEL_NAME_TH=  '" + req.getPRODUCT_MODEL_NAME_TH() + "', "
                        + "PRODUCT_MODEL_NAME_EN=  '" + req.getPRODUCT_MODEL_NAME_EN() + "', "
                        + "IS_TAX_VALUE=  '" + req.getIS_TAX_VALUE() + "', "
                        + "TAX_VALUE=  '" + req.getTAX_VALUE() + "', "
                        + "IS_TAX_VOLUMN=  '" + req.getIS_TAX_VOLUMN() + "', "
                        + "TAX_VOLUMN=  '" + req.getTAX_VOLUMN() + "', "
                        + "TAX_VOLUMN_UNIT=  '" + req.getTAX_VOLUMN_UNIT() + "', "
                        + "LICENSE_PLATE=  '" + req.getLICENSE_PLATE() + "', "
                        + "ENGINE_NO=  '" + req.getENGINE_NO() + "', "
                        + "CHASSIS_NO=  '" + req.getCHASSIS_NO() + "', "
                        + "PRODUCT_DESC=  '" + req.getPRODUCT_DESC() + "', "
                        + "SUGAR=  '" + req.getSUGAR() + "', "
                        + "CO2=  '" + req.getCO2() + "', "
                        + "DEGREE=  '" + req.getDEGREE() + "', "
                        + "PRICE=  '" + req.getPRICE() + "', "
                        + "SIZES=  '" + req.getSIZES() + "', "
                        + "SIZES_UNIT=  '" + req.getSIZES_UNIT() + "', "
                        + "QUANTITY=  '" + req.getQUANTITY() + "', "
                        + "QUANTITY_UNIT=  '" + req.getQUANTITY_UNIT() + "', "
                        + "VOLUMN=  '" + req.getVOLUMN() + "', "
                        + "VOLUMN_UNIT=  '" + req.getVOLUMN_UNIT() + "', "
                        + "REMARK=  '" + req.getREMARK() + "', "
                        + "IS_DOMESTIC=  '" + req.getIS_DOMESTIC() + "', "
                        + "IS_ILLEGAL=  '" + req.getIS_ILLEGAL() + "', "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + "WHERE PRODUCT_ID = '" + req.getPRODUCT_ID() + "' ");
        log.info("[SQL] : " + sqlBuilderSub.toString());

        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});

        return true;
    }

    @Override
    public Boolean NoticeProductupdDelete(NoticeProductupdDeleteReq req) {

        StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_NOTICE_PRODUCT SET IS_ACTIVE = '0' WHERE PRODUCT_ID = '" + req.getPRODUCT_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder2.toString(), new Object[]{});
        return true;
    }
}
