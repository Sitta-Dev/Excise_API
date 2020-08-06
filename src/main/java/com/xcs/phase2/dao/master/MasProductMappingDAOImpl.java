package com.xcs.phase2.dao.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.master.MasProductMapping;
import com.xcs.phase2.model.master.ProductMapping;
import com.xcs.phase2.request.master.MasProductMappinggetByConAdvReq;
import com.xcs.phase2.request.master.MasProductMappinggetByConReq;
import com.xcs.phase2.request.master.MasProductMappinggetByKeywordReq;
import com.xcs.phase2.request.master.MasProductMappingupdDeleteReq;
import com.xcs.phase2.response.master.MasProductMappinginsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class MasProductMappingDAOImpl extends MasterExt implements MasProductMappingDAO {

	private static final Logger log = LoggerFactory.getLogger(MasProductMappingDAOImpl.class);

	@Override
	public List<ProductMapping> MasProductMappinggetByKeyword(MasProductMappinggetByKeywordReq req) {

		StringBuilder sqlBuilder = new StringBuilder().append("  SELECT DISTINCT" + "  MAS_PRODUCT_MAPPING.*,"
				+ "  MAS_PRODUCT_GROUP.PRODUCT_GROUP_CODE," + "  MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME,"
				+ "  MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_TH," + "  MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_EN,"
				+ "  MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_TH,"
				+ "  MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_EN," + "  MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_TH,"
				+ "  MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_EN" + "  FROM MAS_PRODUCT_MAPPING"
				+ "  LEFT JOIN MAS_PRODUCT_BRAND ON MAS_PRODUCT_MAPPING.PRODUCT_BRAND_ID = MAS_PRODUCT_BRAND.PRODUCT_BRAND_ID"
				+ "  LEFT JOIN MAS_PRODUCT_SUBBRAND ON MAS_PRODUCT_MAPPING.PRODUCT_SUBBRAND_ID = MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_ID "
				+ "  LEFT JOIN MAS_PRODUCT_MODEL ON MAS_PRODUCT_MAPPING.PRODUCT_MODEL_ID = MAS_PRODUCT_MODEL.PRODUCT_MODEL_ID "
				+ "  LEFT JOIN MAS_PRODUCT_GROUP ON MAS_PRODUCT_MAPPING.PRODUCT_GROUP_ID = MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID "
				+ "  LEFT JOIN MAS_PRODUCT_TYPE ON MAS_PRODUCT_MAPPING.PRODUCT_TYPE_ID = MAS_PRODUCT_TYPE.PRODUCT_TYPE_ID"
				+ "  LEFT JOIN MAS_PRODUCT_SUBTYPE ON MAS_PRODUCT_MAPPING.PRODUCT_SUBTYPE_ID = MAS_PRODUCT_SUBTYPE.PRODUCT_SUBTYPE_ID"
				+ "  LEFT JOIN MAS_PRODUCT_SUBSETTYPE ON MAS_PRODUCT_MAPPING.PRODUCT_SUBSETTYPE_ID = MAS_PRODUCT_SUBSETTYPE.PRODUCT_SUBTYPE_ID"
				+ "  LEFT JOIN MAS_PRODUCT_CATEGORY ON MAS_PRODUCT_MAPPING.PRODUCT_CATEGORY_ID = MAS_PRODUCT_CATEGORY.PRODUCT_CATEGORY_ID"
				+ "  LEFT JOIN MAS_PRODUCT_CATEGORY_GROUP ON MAS_PRODUCT_MAPPING.CATEGORY_GROUP_CODE = MAS_PRODUCT_CATEGORY_GROUP.CATEGORY_GROUP"
				+ "  WHERE MAS_PRODUCT_MAPPING.IS_ACTIVE = 1" + "  AND LOWER ("
				+ "        MAS_PRODUCT_MAPPING.SIZES_UNIT || " + "        MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_TH || "
				+ "        MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_EN || "
				+ "        MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_TH || "
				+ "        MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_EN || "
				+ "        MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_TH || "
				+ "        MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_EN " + "    ) LIKE LOWER(REPLACE('%"
				+ req.getTEXT_SEARCH() + "%',' ',''))");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ProductMapping> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ProductMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductMapping item = new ProductMapping();
				item.setPRODUCT_MAPPING_ID(rs.getInt("PRODUCT_MAPPING_ID"));
				item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
				item.setPRODUCT_REF_CODE(rs.getString("PRODUCT_REF_CODE"));
				item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
				item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
				item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
				item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
//                item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));                
				item.setPRODUCT_TYPE_ID(rs.getInt("PRODUCT_TYPE_ID"));
//                item.setPRODUCT_TYPE_NAME(rs.getString("PRODUCT_TYPE_NAME"));
				item.setPRODUCT_SUBTYPE_ID(rs.getInt("PRODUCT_SUBTYPE_ID"));
//                item.setPRODUCT_SUBTYPE_NAME(rs.getString("PRODUCT_SUBTYPE_NAME"));
				item.setPRODUCT_SUBSETTYPE_ID(rs.getInt("PRODUCT_SUBSETTYPE_ID"));
//                item.setPRODUCT_SUBSETTYPE_NAME(rs.getString("PRODUCT_SUBSETTYPE_NAME"));
				item.setPRODUCT_BRAND_ID(rs.getInt("PRODUCT_BRAND_ID"));
				item.setPRODUCT_BRAND_NAME_TH(rs.getString("PRODUCT_BRAND_NAME_TH"));
				item.setPRODUCT_BRAND_NAME_EN(rs.getString("PRODUCT_BRAND_NAME_EN"));
				item.setPRODUCT_SUBBRAND_ID(rs.getInt("PRODUCT_SUBBRAND_ID"));
				item.setPRODUCT_SUBBRAND_NAME_TH(rs.getString("PRODUCT_SUBBRAND_NAME_TH"));
				item.setPRODUCT_SUBBRAND_NAME_EN(rs.getString("PRODUCT_SUBBRAND_NAME_EN"));
				item.setPRODUCT_MODEL_ID(rs.getInt("PRODUCT_MODEL_ID"));
				item.setPRODUCT_MODEL_NAME_TH(rs.getString("PRODUCT_MODEL_NAME_TH"));
				item.setPRODUCT_MODEL_NAME_EN(rs.getString("PRODUCT_MODEL_NAME_EN"));
				item.setPRODUCT_TAXDETAIL_ID(rs.getInt("PRODUCT_TAXDETAIL_ID"));
//                item.setTAX_VALUE(rs.getString("TAX_VALUE"));
//                item.setTAX_VOLUMN(rs.getString("TAX_VOLUMN"));
//                item.setTAX_VOLUMN_UNIT(rs.getString("TAX_VOLUMN_UNIT"));
				item.setUNIT_ID(rs.getInt("UNIT_ID"));
				item.setSUGAR(rs.getFloat("SUGAR"));
				item.setCO2(rs.getFloat("CO2"));
				item.setDEGREE(rs.getFloat("DEGREE"));
				item.setPRICE(rs.getFloat("PRICE"));
				item.setSIZES(rs.getFloat("SIZES"));
				item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
				item.setIS_DOMESTIC(rs.getInt("IS_DOMESTIC"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				item.setCREATE_DATE(rs.getString("CREATE_DATE"));
				item.setCREATE_USER_ACCOUNT_ID(rs.getLong("CREATE_USER_ACCOUNT_ID"));
				item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
				item.setUPDATE_USER_ACCOUNT_ID(rs.getLong("UPDATE_USER_ACCOUNT_ID"));
				item.setCATEGORY_GROUP_CODE(rs.getString("CATEGORY_GROUP_CODE"));
				item.setCATEGORY_CODE(rs.getString("CATEGORY_CODE"));
				item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
				item.setLAW_DUTY_CODE(rs.getString("LAW_DUTY_CODE"));
				item.setEXPIRE_DATE(rs.getString("EXPIRE_DATE"));
				item.setPRODUCT_NAME_DESC(rs.getString("PRODUCT_NAME_DESC"));
//                item.setIS_TAX_VALUE(rs.getInt("IS_TAX_VALUE"));
//                item.setIS_TAX_VOLUMN(rs.getInt("IS_TAX_VOLUMN"));

				return item;
			}
		});

		return dataList;

	}

	@Override
	public List<ProductMapping> MasProductMappinggetByConAdv(MasProductMappinggetByConAdvReq req) {

		StringBuilder sqlBuilder = new StringBuilder().append("  SELECT DISTINCT " + "  MAS_PRODUCT_MAPPING.*,"
				+ "  MAS_PRODUCT_GROUP.PRODUCT_GROUP_CODE," + "  MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME,"
				+ "  MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_TH," + "  MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_EN,"
				+ "  MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_TH,"
				+ "  MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_EN," + "  MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_TH,"
				+ "  MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_EN" + " FROM MAS_PRODUCT_MAPPING"
				+ "  LEFT JOIN MAS_PRODUCT_BRAND ON MAS_PRODUCT_MAPPING.PRODUCT_BRAND_ID = MAS_PRODUCT_BRAND.PRODUCT_BRAND_ID "
				+ "  LEFT JOIN MAS_PRODUCT_SUBBRAND ON MAS_PRODUCT_MAPPING.PRODUCT_SUBBRAND_ID = MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_ID"
				+ "  LEFT JOIN MAS_PRODUCT_MODEL ON MAS_PRODUCT_MAPPING.PRODUCT_MODEL_ID = MAS_PRODUCT_MODEL.PRODUCT_MODEL_ID"
				+ "  LEFT JOIN MAS_PRODUCT_GROUP ON MAS_PRODUCT_MAPPING.PRODUCT_GROUP_ID = MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID"
				+ "  LEFT JOIN MAS_PRODUCT_TYPE ON MAS_PRODUCT_MAPPING.PRODUCT_TYPE_ID = MAS_PRODUCT_TYPE.PRODUCT_TYPE_ID"
				+ "  LEFT JOIN MAS_PRODUCT_SUBTYPE ON MAS_PRODUCT_MAPPING.PRODUCT_SUBTYPE_ID = MAS_PRODUCT_SUBTYPE.PRODUCT_SUBTYPE_ID"
				+ "  LEFT JOIN MAS_PRODUCT_SUBSETTYPE ON MAS_PRODUCT_MAPPING.PRODUCT_SUBSETTYPE_ID = MAS_PRODUCT_SUBSETTYPE.PRODUCT_SUBTYPE_ID"
				+ "  LEFT JOIN MAS_PRODUCT_CATEGORY ON MAS_PRODUCT_MAPPING.PRODUCT_CATEGORY_ID = MAS_PRODUCT_CATEGORY.PRODUCT_CATEGORY_ID"
				+ "  LEFT JOIN MAS_PRODUCT_CATEGORY_GROUP ON MAS_PRODUCT_MAPPING.CATEGORY_GROUP_CODE = MAS_PRODUCT_CATEGORY_GROUP.CATEGORY_GROUP"
				+ "  WHERE MAS_PRODUCT_MAPPING.IS_ACTIVE = 1");

		if (!StringUtils.isEmpty(req.getPRODUCT_GROUP_ID())) {
			sqlBuilder.append(" AND MAS_PRODUCT_MAPPING.PRODUCT_GROUP_ID = '" + req.getPRODUCT_GROUP_ID() + "' ");
		}

		if (!StringUtils.isEmpty(req.getPRODUCT_CODE())) {
			sqlBuilder.append(" AND MAS_PRODUCT_MAPPING.PRODUCT_CODE = '" + req.getPRODUCT_CODE() + "' ");
		}

		if (!StringUtils.isEmpty(req.getPRODUCT_NAME_DESC())) {
			sqlBuilder.append(" AND LOWER (MAS_PRODUCT_MAPPING.PRODUCT_NAME_DESC) LIKE LOWER(REPLACE('%"
					+ req.getPRODUCT_NAME_DESC() + "%',' ','')) ");
		}

		if (!StringUtils.isEmpty(req.getPRODUCT_CATEGORY_ID())) {
			sqlBuilder.append(" AND MAS_PRODUCT_MAPPING.PRODUCT_CATEGORY_ID = '" + req.getPRODUCT_CATEGORY_ID() + "' ");
		}

		if (!StringUtils.isEmpty(req.getPRODUCT_TYPE_ID())) {
			sqlBuilder.append(" AND MAS_PRODUCT_MAPPING.PRODUCT_TYPE_ID = '" + req.getPRODUCT_TYPE_ID() + "' ");
		}

		if (!StringUtils.isEmpty(req.getSIZES())) {
			sqlBuilder.append(
					" AND LOWER (MAS_PRODUCT_MAPPING.SIZES) LIKE LOWER(REPLACE('%" + req.getSIZES() + "%',' ','')) ");
		}

		if (!StringUtils.isEmpty(req.getSIZES_UNIT())) {
			sqlBuilder.append(" AND LOWER (MAS_PRODUCT_MAPPING.SIZES_UNIT) LIKE LOWER(REPLACE('" + req.getSIZES_UNIT()
					+ "%',' ','')) ");
		}

		if (!StringUtils.isEmpty(req.getPRODUCT_BRAND_NAME())) {
			sqlBuilder.append("  AND LOWER (" + " MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_TH || "
					+ "  MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_EN " + "  ) " + "LIKE LOWER(REPLACE('%"
					+ req.getPRODUCT_BRAND_NAME() + "%',' ',''))");
		}

		if (!StringUtils.isEmpty(req.getPRODUCT_SUBBRAND_NAME())) {
			sqlBuilder.append("  AND LOWER (" + " MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_TH || "
					+ "  MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_EN " + "  ) " + "LIKE LOWER(REPLACE('%"
					+ req.getPRODUCT_SUBBRAND_NAME() + "%',' ',''))");
		}

		if (!StringUtils.isEmpty(req.getPRODUCT_MODEL_NAME())) {
			sqlBuilder.append("  AND LOWER (" + " MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_TH || "
					+ "  MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_EN " + "  ) " + "LIKE LOWER(REPLACE('%"
					+ req.getPRODUCT_MODEL_NAME() + "%',' ',''))");
		}

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<ProductMapping> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public ProductMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductMapping item = new ProductMapping();
				item.setPRODUCT_MAPPING_ID(rs.getInt("PRODUCT_MAPPING_ID"));
				item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
				item.setPRODUCT_REF_CODE(rs.getString("PRODUCT_REF_CODE"));
				item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
				item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
				item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
				item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
//				item.setPRODUCT_CATEGORY_NAME(rs.getString("PRODUCT_CATEGORY_NAME"));
				item.setPRODUCT_TYPE_ID(rs.getInt("PRODUCT_TYPE_ID"));
//				item.setPRODUCT_TYPE_NAME(rs.getString("PRODUCT_TYPE_NAME"));
				item.setPRODUCT_SUBTYPE_ID(rs.getInt("PRODUCT_SUBTYPE_ID"));
//				item.setPRODUCT_SUBTYPE_NAME(rs.getString("PRODUCT_SUBTYPE_NAME"));
				item.setPRODUCT_SUBSETTYPE_ID(rs.getInt("PRODUCT_SUBSETTYPE_ID"));
//				item.setPRODUCT_SUBSETTYPE_NAME(rs.getString("PRODUCT_SUBSETTYPE_NAME"));
				item.setPRODUCT_BRAND_ID(rs.getInt("PRODUCT_BRAND_ID"));
				item.setPRODUCT_BRAND_NAME_TH(rs.getString("PRODUCT_BRAND_NAME_TH"));
				item.setPRODUCT_BRAND_NAME_EN(rs.getString("PRODUCT_BRAND_NAME_EN"));
				item.setPRODUCT_SUBBRAND_ID(rs.getInt("PRODUCT_SUBBRAND_ID"));
				item.setPRODUCT_SUBBRAND_NAME_TH(rs.getString("PRODUCT_SUBBRAND_NAME_TH"));
				item.setPRODUCT_SUBBRAND_NAME_EN(rs.getString("PRODUCT_SUBBRAND_NAME_EN"));
				item.setPRODUCT_MODEL_ID(rs.getInt("PRODUCT_MODEL_ID"));
				item.setPRODUCT_MODEL_NAME_TH(rs.getString("PRODUCT_MODEL_NAME_TH"));
				item.setPRODUCT_MODEL_NAME_EN(rs.getString("PRODUCT_MODEL_NAME_EN"));
				item.setPRODUCT_TAXDETAIL_ID(rs.getInt("PRODUCT_TAXDETAIL_ID"));
//				item.setTAX_VALUE(rs.getString("TAX_VALUE"));
//				item.setTAX_VOLUMN(rs.getString("TAX_VOLUMN"));
//				item.setTAX_VOLUMN_UNIT(rs.getString("TAX_VOLUMN_UNIT"));
				item.setUNIT_ID(rs.getInt("UNIT_ID"));
				item.setSUGAR(rs.getFloat("SUGAR"));
				item.setCO2(rs.getFloat("CO2"));
				item.setDEGREE(rs.getFloat("DEGREE"));
				item.setPRICE(rs.getFloat("PRICE"));
				item.setSIZES(rs.getFloat("SIZES"));
				item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
				item.setIS_DOMESTIC(rs.getInt("IS_DOMESTIC"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
				item.setCREATE_DATE(rs.getString("CREATE_DATE"));
				item.setCREATE_USER_ACCOUNT_ID(rs.getLong("CREATE_USER_ACCOUNT_ID"));
				item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
				item.setUPDATE_USER_ACCOUNT_ID(rs.getLong("UPDATE_USER_ACCOUNT_ID"));
				item.setCATEGORY_GROUP_CODE(rs.getString("CATEGORY_GROUP_CODE"));
				item.setCATEGORY_CODE(rs.getString("CATEGORY_CODE"));
				item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
				item.setLAW_DUTY_CODE(rs.getString("LAW_DUTY_CODE"));
				item.setEXPIRE_DATE(rs.getString("EXPIRE_DATE"));
				item.setPRODUCT_NAME_DESC(rs.getString("PRODUCT_NAME_DESC"));
//				item.setIS_TAX_VALUE(rs.getInt("IS_TAX_VALUE"));
//				item.setIS_TAX_VOLUMN(rs.getInt("IS_TAX_VOLUMN"));

				return item;
			}
		});

		return dataList;

	}

	@Override
	public MasProductMapping MasProductMappinggetByCon(MasProductMappinggetByConReq req) {

		StringBuilder sqlBuilder = new StringBuilder().append(" SELECT DISTINCT "
				+ "MAS_PRODUCT_MAPPING.PRODUCT_MAPPING_ID," + "MAS_PRODUCT_MAPPING.PRODUCT_CODE,"
				+ "MAS_PRODUCT_MAPPING.PRODUCT_REF_CODE," + "MAS_PRODUCT_MAPPING.PRODUCT_GROUP_ID,"
				+ "MAS_PRODUCT_MAPPING.PRODUCT_CATEGORY_ID," + "MAS_PRODUCT_MAPPING.PRODUCT_TYPE_ID,"
				+ "MAS_PRODUCT_MAPPING.PRODUCT_SUBTYPE_ID," + "MAS_PRODUCT_MAPPING.PRODUCT_SUBSETTYPE_ID,"
				+ "MAS_PRODUCT_MAPPING.PRODUCT_BRAND_ID," + "MAS_PRODUCT_MAPPING.PRODUCT_SUBBRAND_ID,"
				+ "MAS_PRODUCT_MAPPING.PRODUCT_MODEL_ID," + "MAS_PRODUCT_MAPPING.PRODUCT_TAXDETAIL_ID,"
				+ "MAS_PRODUCT_MAPPING.UNIT_ID," + "MAS_PRODUCT_MAPPING.SUGAR," + "MAS_PRODUCT_MAPPING.CO2,"
				+ "MAS_PRODUCT_MAPPING.DEGREE," + "MAS_PRODUCT_MAPPING.PRICE," + "MAS_PRODUCT_MAPPING.SIZES,"
				+ "MAS_PRODUCT_MAPPING.SIZES_UNIT," + "MAS_PRODUCT_MAPPING.IS_DOMESTIC,"
				+ "MAS_PRODUCT_MAPPING.IS_ACTIVE," + "to_char(MAS_PRODUCT_MAPPING.CREATE_DATE,'"
				+ Pattern.FORMAT_DATETIME + "') as CREATE_DATE," + "MAS_PRODUCT_MAPPING.CREATE_USER_ACCOUNT_ID,"
				+ "to_char(MAS_PRODUCT_MAPPING.UPDATE_DATE,'" + Pattern.FORMAT_DATETIME + "') as UPDATE_DATE,"
				+ "MAS_PRODUCT_MAPPING.UPDATE_USER_ACCOUNT_ID," + "MAS_PRODUCT_MAPPING.CATEGORY_GROUP_CODE,"
				+ "MAS_PRODUCT_MAPPING.CATEGORY_CODE," + "MAS_PRODUCT_MAPPING.QUANTITY_UNIT,"
				+ "to_char(MAS_PRODUCT_MAPPING.EXPIRE_DATE,'" + Pattern.FORMAT_DATETIME + "') as EXPIRE_DATE,"
				+ "MAS_PRODUCT_MAPPING.LAW_DUTY_CODE," + "MAS_PRODUCT_MAPPING.PRODUCT_NAME_DESC"
				+ " FROM MAS_PRODUCT_MAPPING"
				+ " LEFT JOIN (SELECT PRODUCT_GROUP_ID,PRODUCT_GROUP_NAME FROM MAS_PRODUCT_GROUP) MAS_PRODUCT_GROUP on MAS_PRODUCT_MAPPING.PRODUCT_GROUP_ID=MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID"
				+ " LEFT JOIN (SELECT PRODUCT_CODE,PRODUCT_NAME FROM MAS_PRODUCT_PRC) MAS_PRODUCT_PRC on MAS_PRODUCT_MAPPING.PRODUCT_CODE=MAS_PRODUCT_PRC.PRODUCT_CODE"
				+ " LEFT JOIN (SELECT PRODUCT_CODE,CATEGORY_GROUP_DESC FROM MAS_PRODUCT_CATEGORY_GROUP) MAS_PRODUCT_CATEGORY_GROUP on MAS_PRODUCT_MAPPING.PRODUCT_CODE=MAS_PRODUCT_CATEGORY_GROUP.PRODUCT_CODE"
				+ " LEFT JOIN (SELECT PRODUCT_CODE,CATEGORY_DESC FROM MAS_PRODUCT_CATEGORY_RDB) MAS_PRODUCT_CATEGORY_RDB on MAS_PRODUCT_MAPPING.PRODUCT_CODE=MAS_PRODUCT_CATEGORY_RDB.PRODUCT_CODE"
				+ " LEFT JOIN (SELECT PRODUCT_BRAND_ID,PRODUCT_BRAND_CODE,NVL(MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_TH,MAS_PRODUCT_BRAND.PRODUCT_BRAND_NAME_EN) AS BRAND_NAME FROM MAS_PRODUCT_BRAND) MAS_PRODUCT_BRAND on MAS_PRODUCT_MAPPING.PRODUCT_BRAND_ID=MAS_PRODUCT_BRAND.PRODUCT_BRAND_ID"
				+ " LEFT JOIN (SELECT PRODUCT_BRAND_CODE,NVL(MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_TH,MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_EN) AS PRODUCT_SUBBRAND FROM MAS_PRODUCT_SUBBRAND) MAS_PRODUCT_SUBBRAND on MAS_PRODUCT_BRAND.PRODUCT_BRAND_CODE=MAS_PRODUCT_SUBBRAND.PRODUCT_BRAND_CODE"
				+ " LEFT JOIN (SELECT PRODUCT_MODEL_ID,NVL(MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_TH,MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_EN) AS PRODUCT_MODEL FROM MAS_PRODUCT_MODEL) MAS_PRODUCT_MODEL on MAS_PRODUCT_MAPPING.PRODUCT_MODEL_ID=MAS_PRODUCT_MODEL.PRODUCT_MODEL_ID"
				+ " LEFT JOIN (SELECT UNIT_ID,NVL(MAS_PRODUCT_UNIT.UNIT_NAME_TH,MAS_PRODUCT_UNIT.UNIT_NAME_EN) AS PRODUCT_UNIT FROM MAS_PRODUCT_UNIT) MAS_PRODUCT_UNIT on MAS_PRODUCT_UNIT.UNIT_ID=MAS_PRODUCT_MAPPING.UNIT_ID"
				+ " where MAS_PRODUCT_MAPPING.PRODUCT_MAPPING_ID = '" + req.getPRODUCT_MAPPING_ID() + "'");

		log.info("[SQL] : " + sqlBuilder.toString());

		return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasProductMapping>() {

			public MasProductMapping extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {

					MasProductMapping item = new MasProductMapping();
					item.setPRODUCT_MAPPING_ID(rs.getInt("PRODUCT_MAPPING_ID"));
					item.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
					item.setPRODUCT_REF_CODE(rs.getString("PRODUCT_REF_CODE"));
					item.setPRODUCT_GROUP_ID(rs.getInt("PRODUCT_GROUP_ID"));
					item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
					item.setPRODUCT_TYPE_ID(rs.getInt("PRODUCT_TYPE_ID"));
					item.setPRODUCT_SUBTYPE_ID(rs.getInt("PRODUCT_SUBTYPE_ID"));
					item.setPRODUCT_SUBSETTYPE_ID(rs.getInt("PRODUCT_SUBSETTYPE_ID"));
					item.setPRODUCT_BRAND_ID(rs.getInt("PRODUCT_BRAND_ID"));
					item.setPRODUCT_SUBBRAND_ID(rs.getInt("PRODUCT_SUBBRAND_ID"));
					item.setPRODUCT_MODEL_ID(rs.getInt("PRODUCT_MODEL_ID"));
					item.setPRODUCT_TAXDETAIL_ID(rs.getInt("PRODUCT_TAXDETAIL_ID"));
					item.setUNIT_ID(rs.getInt("UNIT_ID"));
					item.setSUGAR(rs.getFloat("SUGAR"));
					item.setCO2(rs.getFloat("CO2"));
					item.setDEGREE(rs.getFloat("DEGREE"));
					item.setPRICE(rs.getFloat("PRICE"));
					item.setSIZES(rs.getFloat("SIZES"));
					item.setSIZES_UNIT(rs.getString("SIZES_UNIT"));
					item.setIS_DOMESTIC(rs.getInt("IS_DOMESTIC"));
					item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
					item.setCREATE_DATE(rs.getString("CREATE_DATE"));
					item.setCREATE_USER_ACCOUNT_ID(rs.getLong("CREATE_USER_ACCOUNT_ID"));
					item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
					item.setUPDATE_USER_ACCOUNT_ID(rs.getLong("UPDATE_USER_ACCOUNT_ID"));
					item.setCATEGORY_GROUP_CODE(rs.getString("CATEGORY_GROUP_CODE"));
					item.setCATEGORY_CODE(rs.getString("CATEGORY_CODE"));
					item.setQUANTITY_UNIT(rs.getString("QUANTITY_UNIT"));
					item.setEXPIRE_DATE(rs.getString("EXPIRE_DATE"));
					item.setLAW_DUTY_CODE(rs.getString("LAW_DUTY_CODE"));
					item.setPRODUCT_NAME_DESC(rs.getString("PRODUCT_NAME_DESC"));
					return item;
				}

				return null;
			}
		});
	}

	@Override
	public MasProductMappinginsAllResponse MasProductMappinginsAll(MasProductMapping req) {

		MasProductMappinginsAllResponse res = new MasProductMappinginsAllResponse();

		try {

			String PRODUCT_MAPPING_ID = getSequences("SELECT MAS_PRODUCT_MAPPING_SEQ.NEXTVAL FROM DUAL");
			StringBuilder sqlBuilder = new StringBuilder().append("Insert into MAS_PRODUCT_MAPPING ( "
					+ "PRODUCT_MAPPING_ID," + "PRODUCT_CODE," + "PRODUCT_REF_CODE," + "PRODUCT_GROUP_ID,"
					+ "PRODUCT_CATEGORY_ID," + "PRODUCT_TYPE_ID," + "PRODUCT_SUBTYPE_ID," + "PRODUCT_SUBSETTYPE_ID,"
					+ "PRODUCT_BRAND_ID," + "PRODUCT_SUBBRAND_ID," + "PRODUCT_MODEL_ID," + "PRODUCT_TAXDETAIL_ID,"
					+ "UNIT_ID," + "SUGAR," + "CO2," + "DEGREE," + "PRICE," + "SIZES," + "SIZES_UNIT," + "IS_DOMESTIC,"
					+ "IS_ACTIVE," + "CREATE_DATE," + "CREATE_USER_ACCOUNT_ID," + "UPDATE_DATE,"
					+ "UPDATE_USER_ACCOUNT_ID," + "CATEGORY_GROUP_CODE," + "CATEGORY_CODE," + "QUANTITY_UNIT,"
					+ "EXPIRE_DATE," + "LAW_DUTY_CODE," + "PRODUCT_NAME_DESC" + " ) values (" + "'" + PRODUCT_MAPPING_ID
					+ "'," + "'" + req.getPRODUCT_CODE() + "'," + "'" + req.getPRODUCT_REF_CODE() + "'," + "'"
					+ req.getPRODUCT_GROUP_ID() + "'," + "'" + req.getPRODUCT_CATEGORY_ID() + "'," + "'"
					+ req.getPRODUCT_TYPE_ID() + "'," + "'" + req.getPRODUCT_SUBTYPE_ID() + "'," + "'"
					+ req.getPRODUCT_SUBSETTYPE_ID() + "'," + "'" + req.getPRODUCT_BRAND_ID() + "'," + "'"
					+ req.getPRODUCT_SUBBRAND_ID() + "'," + "'" + req.getPRODUCT_MODEL_ID() + "'," + "'"
					+ req.getPRODUCT_TAXDETAIL_ID() + "'," + "'" + req.getUNIT_ID() + "'," + "'" + req.getSUGAR() + "',"
					+ "'" + req.getCO2() + "'," + "'" + req.getDEGREE() + "'," + "'" + req.getPRICE() + "'," + "'"
					+ req.getSIZES() + "'," + "'" + req.getSIZES_UNIT() + "'," + "'" + req.getIS_DOMESTIC() + "'," + "'"
					+ req.getIS_ACTIVE() + "'," + "TO_TIMESTAMP_TZ('" + req.getCREATE_DATE() + "', '"
					+ Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " + "'" + req.getCREATE_USER_ACCOUNT_ID()
					+ "'," + "TO_TIMESTAMP_TZ('" + req.getUPDATE_DATE() + "', '"
					+ Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " + "'" + req.getUPDATE_USER_ACCOUNT_ID()
					+ "'," + "'" + req.getCATEGORY_GROUP_CODE() + "'," + "'" + req.getCATEGORY_CODE() + "'," + "'"
					+ req.getQUANTITY_UNIT() + "'," + "TO_TIMESTAMP_TZ('" + req.getEXPIRE_DATE() + "', '"
					+ Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " + "'" + req.getLAW_DUTY_CODE() + "',"
					+ "'" + req.getPRODUCT_NAME_DESC() + "'" + " )");

			log.info("[SQL] : " + sqlBuilder.toString());
			getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
			res.setPRODUCT_MAPPING_ID(Integer.parseInt(PRODUCT_MAPPING_ID));

			res.setIsSuccess(Message.TRUE);
			res.setMsg(Message.COMPLETE);

			return res;

		} catch (Exception e) {
			e.printStackTrace();
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
			res.setPRODUCT_MAPPING_ID(0);
			return res;
		}
	}

	@Override
	public Boolean MasProductMappingupdAll(MasProductMapping req) {

		StringBuilder sqlBuilder = new StringBuilder().append("UPDATE MAS_PRODUCT_MAPPING SET " + "PRODUCT_CODE=  '"
				+ req.getPRODUCT_CODE() + "', " + "PRODUCT_REF_CODE=  '" + req.getPRODUCT_REF_CODE() + "', "
				+ "PRODUCT_GROUP_ID=  '" + req.getPRODUCT_GROUP_ID() + "', " + "PRODUCT_CATEGORY_ID=  '"
				+ req.getPRODUCT_CATEGORY_ID() + "', " + "PRODUCT_TYPE_ID=  '" + req.getPRODUCT_TYPE_ID() + "', "
				+ "PRODUCT_SUBTYPE_ID=  '" + req.getPRODUCT_SUBTYPE_ID() + "', " + "PRODUCT_SUBSETTYPE_ID=  '"
				+ req.getPRODUCT_SUBSETTYPE_ID() + "', " + "PRODUCT_BRAND_ID=  '" + req.getPRODUCT_BRAND_ID() + "', "
				+ "PRODUCT_SUBBRAND_ID=  '" + req.getPRODUCT_SUBBRAND_ID() + "', " + "PRODUCT_MODEL_ID=  '"
				+ req.getPRODUCT_MODEL_ID() + "', " + "PRODUCT_TAXDETAIL_ID=  '" + req.getPRODUCT_TAXDETAIL_ID() + "', "
				+ "UNIT_ID=  '" + req.getUNIT_ID() + "', " + "SUGAR=  '" + req.getSUGAR() + "', " + "CO2=  '"
				+ req.getCO2() + "', " + "DEGREE=  '" + req.getDEGREE() + "', " + "PRICE=  '" + req.getPRICE() + "', "
				+ "SIZES=  '" + req.getSIZES() + "', " + "SIZES_UNIT=  '" + req.getSIZES_UNIT() + "', "
				+ "IS_DOMESTIC=  '" + req.getIS_DOMESTIC() + "', " + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "', "
				+ "CREATE_DATE = TO_TIMESTAMP_TZ('" + req.getCREATE_DATE() + "', '"
				+ Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " + "CREATE_USER_ACCOUNT_ID=  '"
				+ req.getCREATE_USER_ACCOUNT_ID() + "', " + " UPDATE_DATE = TO_TIMESTAMP_TZ('" + req.getUPDATE_DATE()
				+ "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " + "UPDATE_USER_ACCOUNT_ID=  '"
				+ req.getUPDATE_USER_ACCOUNT_ID() + "', " + "CATEGORY_GROUP_CODE=  '" + req.getCATEGORY_GROUP_CODE()
				+ "', " + "CATEGORY_CODE=  '" + req.getCATEGORY_CODE() + "', " + "QUANTITY_UNIT=  '"
				+ req.getQUANTITY_UNIT() + "', " + "EXPIRE_DATE = TO_TIMESTAMP_TZ('" + req.getEXPIRE_DATE() + "', '"
				+ Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " + "LAW_DUTY_CODE=  '" + req.getLAW_DUTY_CODE()
				+ "', " + "PRODUCT_NAME_DESC=  '" + req.getPRODUCT_NAME_DESC() + "' " + " WHERE PRODUCT_MAPPING_ID = '"
				+ req.getPRODUCT_MAPPING_ID() + "' ");

		log.info("[SQL] : " + sqlBuilder.toString());
		getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});

		return true;
	}

	@Override
	public Boolean MasProductMappingupdDelete(MasProductMappingupdDeleteReq req) {

		StringBuilder sqlBuilder1 = new StringBuilder()
				.append("UPDATE MAS_PRODUCT_MAPPING SET IS_ACTIVE = '0' WHERE PRODUCT_MAPPING_ID = '"
						+ req.getPRODUCT_MAPPING_ID() + "' ");

		getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
		return true;
	}
}
