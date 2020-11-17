package com.xcs.phase2.dao.other;

import com.xcs.phase2.dao.prove.ProveExt;
import com.xcs.phase2.model.master.MasProductGroup;
import com.xcs.phase2.model.other.*;
import com.xcs.phase2.request.other.*;
import com.xcs.phase2.response.Other.MistreatDetailgetBySubsectionResponse;

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
public class OtherDAOImpl extends ProveExt implements OtherDAO{

    private static final Logger log = LoggerFactory.getLogger(OtherDAOImpl.class);

    @Override
    public List<CountOffenseOfZone> CountOffenseOfZoneByTime(CountOffenseOfZoneByTimeReq req) {
        // TODO Auto-generated method stub

        String str = "";

        if(!StringUtils.isEmpty(req.getLAWSUIT_DATE_FROM()) && !StringUtils.isEmpty(req.getLAWSUIT_DATE_TO())){
            str = " and OPS_LAWSUIT.LAWSUIT_DATE BETWEEN TO_DATE('"+req.getLAWSUIT_DATE_FROM()+"', 'YYYY-MM-DD') AND TO_DATE ('"+req.getLAWSUIT_DATE_TO()+"', 'YYYY-MM-DD')";
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT " +
                        "  ed_office_D.ID," +
                        "  ed_office_D.SUPOFFCODE," +
                        "  ed_office_D.OFFCODE," +
                        "  ed_office_D.OFFNAME," +
                        "  ed_office_D.SHORT_NAME," +
                        "  ed_office_D.INDC_OFF," +
                        "  case when ed_office_D.ID is null then ed_office_E.ID end as zone_ID," +
                        "  case when ed_office_D.OFFCODE is null then ed_office_E.OFFCODE end as zone_OFFCODE," +
                        "  case when ed_office_D.OFFNAME is null then ed_office_E.OFFNAME end as zone_OFFNAME," +
                        "  case when ed_office_D.SHORT_NAME is null then ed_office_E.SHORT_NAME end as zone_SHORT_NAME," +
                        "  case when ed_office_D.INDC_OFF is null then ed_office_E.INDC_OFF end as zone_INDC_OFF," +
                        "  count(ed_office_E.ID) as count" +
                        "  FROM OPS_LAWSUIT" +
                        "  inner join OPS_ARREST_INDICTMENT on OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID" +
                        "  and OPS_LAWSUIT.IS_ACTIVE =1" +
                        "  and OPS_LAWSUIT.IS_LAWSUIT = 1" +
                        "  and OPS_ARREST_INDICTMENT.IS_ACTIVE =1" +
                        "  inner join OPS_ARREST on OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "  and OPS_ARREST.IS_ACTIVE =1" +
                        "  inner join OPS_ARREST_LOCALE on OPS_ARREST.ARREST_ID = OPS_ARREST_LOCALE.ARREST_ID" +
                        "  and OPS_ARREST_LOCALE.IS_ACTIVE =1" +
                        "  inner join MAS_SUB_DISTRICT on OPS_ARREST_LOCALE.SUB_DISTRICT_ID = MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
                        "  and MAS_SUB_DISTRICT.IS_ACTIVE =1" +
                        "  inner join MAS_ED_OFFICE office on OPS_LAWSUIT.OFFICE_CODE =  office.OFFCODE" +
                        "  inner join MAS_ED_OFFICE ed_office_E on office.SUPOFFCODE =  ed_office_E.OFFCODE" +
                        "  left join MAS_ED_OFFICE ed_office_D on ed_office_E.SUPOFFCODE =  ed_office_D.OFFCODE" +
                        "  where 1=1 " + str +
                        "  GROUP BY " +
                        "  ed_office_D.ID," +
                        "  ed_office_D.SUPOFFCODE," +
                        "  ed_office_D.OFFCODE," +
                        "  ed_office_D.OFFNAME," +
                        "  ed_office_D.SHORT_NAME," +
                        "  ed_office_D.INDC_OFF," +
                        "  case when ed_office_D.ID is null then ed_office_E.ID end ," +
                        "  case when ed_office_D.OFFCODE is null then ed_office_E.OFFCODE end ," +
                        "  case when ed_office_D.OFFNAME is null then ed_office_E.OFFNAME end ," +
                        "  case when ed_office_D.SHORT_NAME is null then ed_office_E.SHORT_NAME end ," +
                        "  case when ed_office_D.OFFCODE is null then ed_office_E.OFFCODE end ," +
                        "  case when ed_office_D.INDC_OFF is null then ed_office_E.INDC_OFF end " +
                        "  order by ed_office_D.ID");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<CountOffenseOfZone> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {


            public CountOffenseOfZone mapRow(ResultSet rs, int rowNum) throws SQLException {
                CountOffenseOfZone item = new CountOffenseOfZone();
                item.setCOUNT(rs.getInt("COUNT"));
                item.setID(rs.getInt("ID"));
                item.setSUPOFFCODE(rs.getString("SUPOFFCODE"));
                item.setOFFCODE(rs.getString("OFFCODE"));
                item.setOFFNAME(rs.getString("OFFNAME"));
                item.setSHORT_NAME(rs.getString("SHORT_NAME"));
                item.setINDC_OFF(rs.getString("INDC_OFF"));
                item.setZONE_ID(rs.getInt("ZONE_ID"));
                item.setZONE_OFFCODE(rs.getString("ZONE_OFFCODE"));
                item.setZONE_OFFNAME(rs.getString("ZONE_OFFNAME"));
                item.setZONE_SHORT_NAME(rs.getString("ZONE_SHORT_NAME"));
                item.setZONE_INDC_OFF(rs.getString("ZONE_INDC_OFF"));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<CountOffenseOfArea> CountOffenseOfAreaByZone(CountOffenseOfAreaByZoneReq req) {
        // TODO Auto-generated method stub

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT" +
                        "  MAS_ED_OFFICE.SUPOFFCODE," +
                        "  MAS_ED_OFFICE.OFFCODE," +
                        "  MAS_ED_OFFICE.OFFNAME," +
                        "  CASE WHEN " +
                        "  (" +
                        "    select LAWSUIT_AMOUNT from " +
                        "    (" +
                        "      select count(lawsuit_id) as LAWSUIT_AMOUNT,supoffcode from ops_lawsuit A JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      WHERE A.IS_ACTIVE=1 " +
                        "      AND A.IS_LAWSUIT=1" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  ) " +
                        "  IS NULL THEN " +
                        "  (" +
                        "    select LAWSUIT_AMOUNT from " +
                        "    (" +
                        "      select count(lawsuit_id) as LAWSUIT_AMOUNT,offcode from ops_lawsuit A JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      WHERE A.IS_ACTIVE=1 " +
                        "      AND A.IS_LAWSUIT=1" +
                        "      group by offcode" +
                        "    )ABC WHERE ABC.offcode=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  ELSE" +
                        "  (" +
                        "    select LAWSUIT_AMOUNT from " +
                        "    (" +
                        "      select count(lawsuit_id) as LAWSUIT_AMOUNT,supoffcode from ops_lawsuit A JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      WHERE A.IS_ACTIVE=1 " +
                        "      AND A.IS_LAWSUIT=1" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  END" +
                        "  AS LAWSUIT_AMOUNT," +
                        "  CASE WHEN " +
                        "  (" +
                        "    select PAYMENT_FINE from " +
                        "    (" +
                        "      select SUM(PAYMENT_FINE) as PAYMENT_FINE,supoffcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  IS NOT NULL THEN" +
                        "  (" +
                        "    select PAYMENT_FINE from " +
                        "    (" +
                        "      select SUM(PAYMENT_FINE) as PAYMENT_FINE,supoffcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  ELSE" +
                        "  (" +
                        "    select PAYMENT_FINE from " +
                        "    (" +
                        "      select SUM(PAYMENT_FINE) as PAYMENT_FINE,offcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by offcode" +
                        "    )ABC WHERE ABC.offcode=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  END AS PAYMENT_FINE," +
                        "  CASE WHEN " +
                        "  (" +
                        "    select FINE from " +
                        "    (" +
                        "      select SUM(FINE) as FINE,supoffcode from OPS_LAWSUIT A" +
                        "      JOIN OPS_LAWSUIT_DETAIL ON A.LAWSUIT_ID=OPS_LAWSUIT_DETAIL.LAWSUIT_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  IS NOT NULL THEN" +
                        "  (" +
                        "    select FINE from " +
                        "    (" +
                        "      select SUM(FINE) as FINE,supoffcode from OPS_LAWSUIT A" +
                        "      JOIN OPS_LAWSUIT_DETAIL ON A.LAWSUIT_ID=OPS_LAWSUIT_DETAIL.LAWSUIT_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by" +
                        "      supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  ELSE" +
                        "  (" +
                        "    select FINE from " +
                        "    (" +
                        "      select SUM(FINE) as FINE,offcode from OPS_LAWSUIT A" +
                        "      JOIN OPS_LAWSUIT_DETAIL ON A.LAWSUIT_ID=OPS_LAWSUIT_DETAIL.LAWSUIT_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by  offcode" +
                        "    )ABC WHERE ABC.OFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  END AS FINE," +
                        "  CASE WHEN " +
                        "  (" +
                        "    select FINE_ESTIMATE from " +
                        "    (" +
                        "      select SUM(FINE_ESTIMATE) as FINE_ESTIMATE,supoffcode from OPS_ARREST A" +
                        "      JOIN  OPS_ARREST_INDICTMENT ON A.ARREST_ID=OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  IS NOT NULL THEN" +
                        "  (" +
                        "    select FINE_ESTIMATE from " +
                        "    (" +
                        "      select SUM(FINE_ESTIMATE) as FINE_ESTIMATE,supoffcode from OPS_ARREST A" +
                        "      JOIN  OPS_ARREST_INDICTMENT ON A.ARREST_ID=OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  ELSE" +
                        "  (" +
                        "    select FINE_ESTIMATE from " +
                        "    (" +
                        "      select SUM(FINE_ESTIMATE) as FINE_ESTIMATE,offcode from OPS_ARREST A" +
                        "      JOIN  OPS_ARREST_INDICTMENT ON A.ARREST_ID=OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by offcode" +
                        "    )ABC WHERE ABC.OFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  END AS FINE_ESTIMATE," +
                        "  CASE WHEN " +
                        "  (" +
                        "    select BRIBE_MONEY from " +
                        "    (" +
                        "      select SUM(BRIBE_MONEY) as BRIBE_MONEY,supoffcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  IS NOT NULL THEN" +
                        "  (" +
                        "    select BRIBE_MONEY from " +
                        "    (" +
                        "      select SUM(BRIBE_MONEY) as BRIBE_MONEY,supoffcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  ELSE" +
                        "  (" +
                        "    select BRIBE_MONEY from " +
                        "    (" +
                        "      select SUM(BRIBE_MONEY) as BRIBE_MONEY,offcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by offcode" +
                        "    )ABC WHERE ABC.offcode=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  END AS BRIBE_MONEY," +
                        "  CASE WHEN " +
                        "  (" +
                        "    select REWARD_MONEY from " +
                        "    (" +
                        "      select SUM(REWARD_MONEY) as REWARD_MONEY,supoffcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  IS NOT NULL THEN" +
                        "  (" +
                        "    select REWARD_MONEY from " +
                        "    (" +
                        "      select SUM(REWARD_MONEY) as REWARD_MONEY,supoffcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  ELSE" +
                        "  (" +
                        "    select REWARD_MONEY from " +
                        "    (" +
                        "      select SUM(REWARD_MONEY) as REWARD_MONEY,offcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by offcode" +
                        "    )ABC WHERE ABC.offcode=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  END AS REWARD_MONEY," +
                        "  CASE WHEN " +
                        "  (" +
                        "    select TREASURY_MONEY from " +
                        "    (" +
                        "      select SUM(TREASURY_MONEY) as TREASURY_MONEY,supoffcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  IS NOT NULL THEN" +
                        "  (" +
                        "    select TREASURY_MONEY from " +
                        "    (" +
                        "      select SUM(TREASURY_MONEY) as TREASURY_MONEY,supoffcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by supoffcode" +
                        "    )ABC WHERE ABC.SUPOFFCODE=MAS_ED_OFFICE.OFFCODE" +
                        "  )" +
                        "  ELSE" +
                        "  (" +
                        "    select TREASURY_MONEY from " +
                        "    (" +
                        "      select SUM(TREASURY_MONEY) as TREASURY_MONEY,offcode from OPS_COMPARE A" +
                        "      JOIN OPS_COMPARE_MAPPING ON A.COMPARE_ID=OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "      JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID=OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID" +
                        "      JOIN " +
                        "      (" +
                        "        SELECT s.supoffcode,offcode" +
                        "        FROM MAS_ED_OFFICE s" +
                        "        CONNECT" +
                        "        BY PRIOR offcode = supoffcode" +
                        "        start with supoffcode is null" +
                        "      ) B on A.office_code=B.offcode" +
                        "      group by" +
                        "      offcode" +
                        "    )ABC WHERE ABC.offcode=MAS_ED_OFFICE.OFFCODE" +
                        "    )" +
                        "  END AS TREASURY_MONEY" +
                        "  FROM MAS_ED_OFFICE" +
                        "  LEFT JOIN OPS_LAWSUIT ON OPS_LAWSUIT.OFFICE_CODE = MAS_ED_OFFICE.OFFCODE and IS_LAWSUIT=1" +
                        "  LEFT JOIN OPS_COMPARE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_COMPARE.LAWSUIT_ID" +
                        "  LEFT JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_MAPPING.COMPARE_ID" +
                        "  LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID" +
                        "  LEFT JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "  WHERE MAS_ED_OFFICE.INDC_OFF = 'E'" +
                        "  AND MAS_ED_OFFICE.SUPOFFCODE = '"+req.getSUPOFFCODE()+"' " +
                        "  AND OPS_LAWSUIT.LAWSUIT_DATE BETWEEN TO_DATE('"+req.getLAWSUIT_DATE_FROM()+"', 'YYYY-MM-DD') AND TO_DATE ('"+req.getLAWSUIT_DATE_TO()+"', 'YYYY-MM-DD')" +
                        "  GROUP BY" +
                        "  MAS_ED_OFFICE.SUPOFFCODE," +
                        "  MAS_ED_OFFICE.OFFCODE," +
                        "  MAS_ED_OFFICE.OFFNAME" +
                        "  ORDER BY ABS(OFFCODE)");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<CountOffenseOfArea> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public CountOffenseOfArea mapRow(ResultSet rs, int rowNum) throws SQLException {
                CountOffenseOfArea item = new CountOffenseOfArea();
                item.setSUPOFFCODE(rs.getString("SUPOFFCODE"));
                item.setOFFCODE(rs.getString("OFFCODE"));
                item.setOFFNAME(rs.getString("OFFNAME"));
                item.setLAWSUIT_AMOUNT(rs.getInt("LAWSUIT_AMOUNT"));
                item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setFINE_ESTIMATE(rs.getFloat("FINE_ESTIMATE"));
                item.setBRIBE_MONEY(rs.getFloat("BRIBE_MONEY"));
                item.setREWARD_MONEY(rs.getFloat("REWARD_MONEY"));
                item.setTREASURY_MONEY(rs.getFloat("TREASURY_MONEY"));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<LawbreakeNetwork> LawbreakeNetworkgetByCon(LawbreakeNetworkgetByConReq req) {
        // TODO Auto-generated method stub


        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT" +
                        "    'ผู้ต้องหา' AS TYPE," +
                        "    OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
                        "    MAS_PERSON_PHOTO.PHOTO," +
                        "    CASE WHEN OPS_ARREST_LAWBREAKER.PERSON_TYPE=0 THEN OPS_ARREST_LAWBREAKER.TITLE_NAME_TH WHEN OPS_ARREST_LAWBREAKER.PERSON_TYPE=1 THEN OPS_ARREST_LAWBREAKER.TITLE_NAME_EN END || ' ' || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' ' || OPS_ARREST_LAWBREAKER.LAST_NAME AS ARREST_LAWBREAKER_NAME, " +
                        "    OPS_ARREST.ARREST_CODE, " +
                        "    NULL as LAWSUIT_NO," +
                        "    NULL as LAWSUIT_NO_YEAR," +
                        "    OPS_ARREST.OCCURRENCE_DATE," +
                        "    CASE WHEN OPS_ARREST_LOCALE.ADDRESS_NO IS NOT NULL THEN OPS_ARREST_LOCALE.ADDRESS_NO END as ADDRESS," +
                        "    CASE WHEN OPS_ARREST_LOCALE.VILLAGE_NO IS NOT NULL THEN 'หมู่ที่ ' || OPS_ARREST_LOCALE.VILLAGE_NO END as VILLAGE_NO," +
                        "    CASE WHEN OPS_ARREST_LOCALE.BUILDING_NAME IS NOT NULL THEN 'ชื่ออาคาร ' || OPS_ARREST_LOCALE.BUILDING_NAME END as BUILDING_NAME," +
                        "    CASE WHEN OPS_ARREST_LOCALE.ROOM_NO IS NOT NULL THEN 'เลขที่ห้อง '|| OPS_ARREST_LOCALE.ROOM_NO END as ROOM_NO," +
                        "    CASE WHEN OPS_ARREST_LOCALE.FLOOR IS NOT NULL THEN 'ชั้น '|| OPS_ARREST_LOCALE.FLOOR END as FLOOR," +
                        "    CASE WHEN OPS_ARREST_LOCALE.VILLAGE_NAME IS NOT NULL THEN 'หมู่บ้าน ' || OPS_ARREST_LOCALE.VILLAGE_NAME END as VILLAGE_NAME," +
                        "    CASE WHEN OPS_ARREST_LOCALE.ALLEY IS NOT NULL THEN 'ตรอก '|| OPS_ARREST_LOCALE.ALLEY END as ALLEY," +
                        "    CASE WHEN OPS_ARREST_LOCALE.LANE IS NOT NULL THEN 'ซอย '|| OPS_ARREST_LOCALE.LANE END as LANE," +
                        "    CASE WHEN OPS_ARREST_LOCALE.ROAD IS NOT NULL THEN 'ถนน '|| OPS_ARREST_LOCALE.ROAD END as ROAD," +
                        "    MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH|| '/' ||MAS_DISTRICT.DISTRICT_NAME_TH|| '/' ||MAS_PROVINCE.PROVINCE_NAME_TH as LOCATION, " +
                        "    MAS_LAW_GROUP_SECTION.SECTION_NAME as SECTION_NAME," +
                        "    MAS_LAW_GUILTBASE.GUILTBASE_NAME as GUILTBASE_NAME," +
                        "    (SELECT PERSON_DAD.TITLE_NAME_TH || ' ' || PERSON_DAD.FIRST_NAME || ' ' || PERSON_DAD.LAST_NAME FROM MAS_PERSON_RELATIONSHIP PERSON_DAD JOIN MAS_RELATIONSHIP ON PERSON_DAD.RELATIONSHIP_ID=MAS_RELATIONSHIP.RELATIONSHIP_ID WHERE PERSON_DAD.PERSON_ID=MAS_PERSON.PERSON_ID AND MAS_RELATIONSHIP.RELATIONSHIP_ID = 1)AS DAD," +
                        "    (SELECT PERSON_DAD.TITLE_NAME_TH || ' ' || PERSON_DAD.FIRST_NAME || ' ' || PERSON_DAD.LAST_NAME FROM MAS_PERSON_RELATIONSHIP PERSON_DAD JOIN MAS_RELATIONSHIP ON PERSON_DAD.RELATIONSHIP_ID=MAS_RELATIONSHIP.RELATIONSHIP_ID WHERE PERSON_DAD.PERSON_ID=MAS_PERSON.PERSON_ID AND MAS_RELATIONSHIP.RELATIONSHIP_ID = 2)AS MOM," +
                        "    MAS_PRODUCT_GROUP.PRODUCT_GROUP_NAME as PRODUCT_GROUP_NAME" +
                        "    FROM OPS_ARREST " +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID=OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "    LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST.ARREST_ID=OPS_ARREST_LAWBREAKER.ARREST_ID" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_PRODUCT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID" +
                        "    LEFT JOIN OPS_ARREST_LOCALE ON OPS_ARREST.ARREST_ID=OPS_ARREST_LOCALE.ARREST_ID" +
                        "    LEFT JOIN MAS_PRODUCT_GROUP ON OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID=MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID" +
                        "    LEFT JOIN MAS_PERSON ON OPS_ARREST_LAWBREAKER.PERSON_ID=MAS_PERSON.PERSON_ID" +
                        "    LEFT JOIN MAS_PERSON_RELATIONSHIP ON MAS_PERSON.PERSON_ID=MAS_PERSON_RELATIONSHIP.PERSON_ID" +
                        "    LEFT JOIN MAS_RELATIONSHIP ON MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID=MAS_RELATIONSHIP.RELATIONSHIP_ID" +
                        "    LEFT JOIN MAS_PERSON_PHOTO ON MAS_PERSON.PERSON_ID=MAS_PERSON_PHOTO.PERSON_ID" +
                        "    LEFT JOIN MAS_LAW_GUILTBASE on MAS_LAW_GUILTBASE.GUILTBASE_ID=OPS_ARREST_INDICTMENT.GUILTBASE_ID and MAS_LAW_GUILTBASE.IS_PROVE=1" +
                        "    LEFT JOIN MAS_LAW_GROUP_SUBSECTION_RULE on MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID=MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID" +
                        "    LEFT JOIN MAS_LAW_GROUP_SECTION on MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID" +
                        "    LEFT JOIN MAS_SUB_DISTRICT ON OPS_ARREST_LOCALE.SUB_DISTRICT_ID=MAS_SUB_DISTRICT.SUB_DISTRICT_ID" +
                        "    LEFT JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID=MAS_DISTRICT.DISTRICT_ID" +
                        "    LEFT JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID=MAS_PROVINCE.PROVINCE_ID" +
                        "    LEFT JOIN MAS_PERSON_RELATIONSHIP PERSON_RELATIVE ON PERSON_RELATIVE.PERSON_ID=MAS_PERSON.PERSON_ID" +
                        "    WHERE OPS_ARREST_LAWBREAKER.LAWBREAKER_ID = '"+req.getLAWBREAKER_ID()+"'" +
                        "    UNION ALL" +
                        "    SELECT DISTINCT" +
                        "    'คดีร่วมกัน' AS TYPE," +
                        "    OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
                        "    MAS_PERSON_PHOTO.PHOTO," +
                        "    CASE WHEN OPS_ARREST_LAWBREAKER.PERSON_TYPE=0 THEN OPS_ARREST_LAWBREAKER.TITLE_NAME_TH WHEN OPS_ARREST_LAWBREAKER.PERSON_TYPE=1 THEN OPS_ARREST_LAWBREAKER.TITLE_NAME_EN END || ' ' || OPS_ARREST_LAWBREAKER.FIRST_NAME || ' ' || OPS_ARREST_LAWBREAKER.LAST_NAME AS ARREST_LAWBREAKER_NAME," +
                        "    NULL, " +
                        "    OPS_LAWSUIT.LAWSUIT_NO," +
                        "    OPS_LAWSUIT.LAWSUIT_NO_YEAR," +
                        "    OPS_ARREST.OCCURRENCE_DATE," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL" +
                        "    FROM OPS_LAWSUIT" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_DETAIL ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT_PRODUCT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID=OPS_ARREST_INDICTMENT_PRODUCT.INDICTMENT_ID" +
                        "    LEFT JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        "    LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST.ARREST_ID=OPS_ARREST_LAWBREAKER.ARREST_ID" +
                        "    LEFT JOIN MAS_PRODUCT_GROUP ON OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID=MAS_PRODUCT_GROUP.PRODUCT_GROUP_ID" +
                        "    LEFT JOIN MAS_PERSON ON OPS_ARREST_LAWBREAKER.PERSON_ID=MAS_PERSON.PERSON_ID" +
                        "    LEFT JOIN MAS_PERSON_RELATIONSHIP ON MAS_PERSON.PERSON_ID=MAS_PERSON_RELATIONSHIP.PERSON_ID" +
                        "    LEFT JOIN MAS_RELATIONSHIP ON MAS_PERSON_RELATIONSHIP.RELATIONSHIP_ID=MAS_RELATIONSHIP.RELATIONSHIP_ID" +
                        "    LEFT JOIN MAS_PERSON_PHOTO ON MAS_PERSON.PERSON_ID=MAS_PERSON_PHOTO.PERSON_ID" +
                        "    WHERE OPS_ARREST.ARREST_ID='"+req.getARREST_ID()+"'" +
                        "    UNION ALL" +
                        "    SELECT DISTINCT" +
                        "    'เครือญาติ' AS TYPE," +
                        "    OPS_ARREST_LAWBREAKER.LAWBREAKER_ID," +
                        "    MAS_PERSON_PHOTO.PHOTO, " +
                        "    NVL(MAS_PERSON_RELATIONSHIP.TITLE_NAME_TH,MAS_PERSON_RELATIONSHIP.TITLE_NAME_EN) || ' ' || MAS_PERSON_RELATIONSHIP.FIRST_NAME || ' ' || MAS_PERSON_RELATIONSHIP.LAST_NAME AS MAS_PERSON_RELATIONSHIP_NAME," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL," +
                        "    NULL" +
                        "    FROM MAS_PERSON_RELATIONSHIP" +
                        "    LEFT JOIN OPS_ARREST_LAWBREAKER ON OPS_ARREST_LAWBREAKER.PERSON_ID=MAS_PERSON_RELATIONSHIP.PERSON_ID" +
                        "    LEFT JOIN OPS_ARREST ON OPS_ARREST.ARREST_ID=OPS_ARREST_LAWBREAKER.ARREST_ID" +
                        "    LEFT JOIN OPS_ARREST_INDICTMENT ON OPS_ARREST.ARREST_ID=OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "    LEFT JOIN OPS_LAWSUIT ON OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_LAWSUIT.INDICTMENT_ID" +
                        "    LEFT JOIN MAS_PERSON_PHOTO ON MAS_PERSON_PHOTO.PERSON_ID=MAS_PERSON_RELATIONSHIP.PERSON_ID" +
                        "    WHERE OPS_ARREST_LAWBREAKER.LAWBREAKER_ID = '"+req.getLAWBREAKER_ID()+"'");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<LawbreakeNetwork> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {


            public LawbreakeNetwork mapRow(ResultSet rs, int rowNum) throws SQLException {
                LawbreakeNetwork item = new LawbreakeNetwork();
                item.setTYPE(rs.getString("TYPE"));
                item.setLAWBREAKER_ID(rs.getString("LAWBREAKER_ID"));
                item.setPHOTO(rs.getString("PHOTO"));
                item.setARREST_LAWBREAKER_NAME(rs.getString("ARREST_LAWBREAKER_NAME"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setADDRESS(rs.getString("ADDRESS"));
                item.setVILLAGE_NO(rs.getString("VILLAGE_NO"));
                item.setBUILDING_NAME(rs.getString("BUILDING_NAME"));
                item.setROOM_NO(rs.getString("ROOM_NO"));
                item.setFLOOR(rs.getString("FLOOR"));
                item.setVILLAGE_NAME(rs.getString("VILLAGE_NAME"));
                item.setALLEY(rs.getString("ALLEY"));
                item.setLANE(rs.getString("LANE"));
                item.setROAD(rs.getString("ROAD"));
                item.setLOCATION(rs.getString("LOCATION"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setDAD(rs.getString("DAD"));
                item.setMOM(rs.getString("MOM"));
                item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<TimeLineList> TimeLineListgetByCon(TimeLineListgetByConReq req) {
        // TODO Auto-generated method stub



        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select" +
                        "    ops_notice.notice_code," +
                        "    ops_arrest.arrest_code," +
                        "    ops_lawsuit.lawsuit_id," +
                        "    ops_prove.prove_id," +
                        "    ops_compare.compare_id," +
                        "    ops_revenue.revenue_id," +
                        "    ops_request_bribe_reward.bribe_reward_id" +
                        "    from ops_arrest" +
                        "    left join ops_notice on ops_notice.arrest_id=ops_arrest.arrest_id and ops_notice.is_active=1" +
                        "    left join ops_arrest_indictment on ops_arrest_indictment.arrest_id=ops_arrest.arrest_id" +
                        "    left join ops_lawsuit on ops_lawsuit.indictment_id=ops_arrest_indictment.indictment_id and ops_lawsuit.is_active=1" +
                        "    left join ops_prove on ops_prove.lawsuit_id=ops_lawsuit.lawsuit_id and ops_prove.is_active=1" +
                        "    left join ops_compare on ops_compare.lawsuit_id=ops_lawsuit.lawsuit_id and ops_compare.is_active=1" +
                        "    left join ops_compare_mapping on ops_compare_mapping.compare_id = ops_compare.compare_id" +
                        "    left join ops_compare_detail on ops_compare_detail.compare_mapping_id=ops_compare_mapping.compare_mapping_id" +
                        "    left join ops_revenue_detail on ops_revenue_detail.compare_detail_id=ops_compare_detail.compare_detail_id" +
                        "    left join ops_revenue on ops_revenue.revenue_id=ops_revenue_detail.revenue_id and ops_revenue.is_active = 1" +
                        "    left join ops_request_bribe_reward on ops_request_bribe_reward.indictment_id=ops_lawsuit.indictment_id" +
                        "    left join ops_request_bribe on ops_request_bribe.bribe_reward_id = ops_request_bribe_reward.bribe_reward_id and ops_request_bribe.is_active =1" +
                        "    left join ops_request_reward on ops_request_reward.bribe_reward_id = ops_request_bribe_reward.bribe_reward_id and ops_request_reward.is_active =1 where 1=1 ");

        if(!StringUtils.isEmpty(req.getNOTICE_CODE())){
            sqlBuilder.append(" and ops_notice.notice_code = '"+req.getNOTICE_CODE()+"' ");
        }

        if(!StringUtils.isEmpty(req.getARREST_CODE())){
            sqlBuilder.append(" and ops_arrest.arrest_code = '"+req.getARREST_CODE()+"' ");
        }

        if(!StringUtils.isEmpty(req.getLAWSUIT_ID())){
            sqlBuilder.append(" and ops_lawsuit.lawsuit_id = '"+req.getLAWSUIT_ID()+"' ");
        }

        if(!StringUtils.isEmpty(req.getPROVE_ID())){
            sqlBuilder.append(" and ops_prove.prove_id = '"+req.getPROVE_ID()+"' ");
        }

        if(!StringUtils.isEmpty(req.getCOMPARE_ID())){
            sqlBuilder.append(" and ops_compare.compare_id = '"+req.getCOMPARE_ID()+"' ");
        }

        if(!StringUtils.isEmpty(req.getREVENUE_ID())){
            sqlBuilder.append(" and ops_revenue.revenue_id = '"+req.getREVENUE_ID()+"' ");
        }

        if(!StringUtils.isEmpty(req.getBRIBE_REWARD_ID())){
            sqlBuilder.append(" and bribe_reward_id = '"+req.getBRIBE_REWARD_ID()+"' ");
        }

        sqlBuilder.append("    group by" +
                "    ops_notice.notice_code," +
                "    ops_arrest.arrest_code," +
                "    ops_lawsuit.lawsuit_id," +
                "    ops_prove.prove_id," +
                "    ops_compare.compare_id," +
                "    ops_revenue.revenue_id," +
                "    ops_request_bribe_reward.bribe_reward_id");

        log.info("[SQL] : " + sqlBuilder.toString());


        @SuppressWarnings("unchecked")
        List<TimeLineList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {


            public TimeLineList mapRow(ResultSet rs, int rowNum) throws SQLException {
                TimeLineList item = new TimeLineList();
                item.setNOTICE_CODE(rs.getString("NOTICE_CODE"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setPROVE_ID(rs.getInt("PROVE_ID"));
                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setREVENUE_ID(rs.getInt("REVENUE_ID"));
                item.setBRIBE_REWARD_ID(rs.getInt("BRIBE_REWARD_ID"));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public Running LawsuitRunningLawsuitNo(RunningNoReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT nvl(max(OPS_LAWSUIT.LAWSUIT_NO),0) + 1 as RUNNING_NO" +
                        "    FROM OPS_LAWSUIT" +
                        "    WHERE OPS_LAWSUIT.OFFICE_CODE ='"+req.getOFFICE_CODE()+"'" +
                        "    AND OPS_LAWSUIT.IS_OUTSIDE = "+req.getIS_OUTSIDE()+" " +
                        "    AND (to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getYEAR()+"' " +
                        "    AND to_number(TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getYEAR()+"')");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Running>() {

            public Running extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    Running item = new Running();
                    item.setRUNNING_NO(rs.getInt("RUNNING_NO"));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public Running ProveRunningProveNo(RunningNoReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT nvl(max(OPS_PROVE.PROVE_NO),0) + 1 as RUNNING_NO" +
                        "    FROM OPS_PROVE" +
                        "    WHERE OPS_PROVE.RECEIVE_OFFICE_CODE ='"+req.getOFFICE_CODE()+"'" +
                        "    AND OPS_PROVE.IS_OUTSIDE = "+req.getIS_OUTSIDE()+" " +
                        "    AND (to_number(TO_CHAR(OPS_PROVE.PROVE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getYEAR()+"' " +
                        "    AND to_number(TO_CHAR(OPS_PROVE.PROVE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getYEAR()+"')");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Running>() {

            public Running extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    Running item = new Running();
                    item.setRUNNING_NO(rs.getInt("RUNNING_NO"));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public Running CompareRunningCompareNo(RunningNoReq req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT nvl(max(OPS_COMPARE.COMPARE_NO),0) + 1 as RUNNING_NO" +
                        "    FROM OPS_COMPARE" +
                        "    WHERE OPS_COMPARE.OFFICE_CODE ='"+req.getOFFICE_CODE()+"'" +
                        "    AND OPS_COMPARE.IS_OUTSIDE = "+req.getIS_OUTSIDE()+" " +
                        "    AND (to_number(TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) >= '"+req.getYEAR()+"' " +
                        "    AND to_number(TO_CHAR(OPS_COMPARE.COMPARE_NO_YEAR,'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')) <='"+req.getYEAR()+"')");

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Running>() {

            public Running extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    Running item = new Running();
                    item.setRUNNING_NO(rs.getInt("RUNNING_NO"));

                    return item;
                }

                return null;
            }
        });
    }

 	public List<MistreatDetailgetBySubsectionResponse> MistreatDetailgetBySubsection(MistreatDetailgetBySubsectionResponseReq req) {
 		StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT DISTINCT "
                		+ "OPS_ARREST.ARREST_ID, "
                		+ "ARREST_CODE, "
                		+ "OCCURRENCE_DATE, "
                		+ "SUB_DISTRICT_NAME_TH||' '||DISTRICT_NAME_TH||' '||PROVINCE_NAME_TH ARREST_LOCALE, "
                		+ "OPS_ARREST_STAFF.TITLE_NAME_TH||''||OPS_ARREST_STAFF.FIRST_NAME||' '||OPS_ARREST_STAFF.LAST_NAME ARREST_STAFF, "
                		+ "OPS_ARREST_STAFF.MANAGEMENT_OFFICE_SHORT_NAME ARREST_STAFF_OFFICE, "
                		+ "MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID, "
                		+ "CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO, "
                		+ "OPS_LAWSUIT_STAFF.TITLE_NAME_TH||''||OPS_LAWSUIT_STAFF.FIRST_NAME||' '||OPS_LAWSUIT_STAFF.LAST_NAME LAWSUIT_STAFF, "
                		+ "OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_SHORT_NAME LAWSUIT_STAFF_OFFICE, "
                		+ "OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE, "
                		+ "CASE WHEN OPS_COMPARE_DETAIL.PAYMENT_FINE IS NULL THEN OPS_LAWSUIT_DETAIL.FINE ELSE OPS_COMPARE_DETAIL.PAYMENT_FINE END PAYMENT_FINE, "
                		+ "OPS_ARREST_INDICTMENT.INDICTMENT_ID "
                		+ "from OPS_ARREST_LAWBREAKER "
                		+ "INNER JOIN OPS_ARREST on OPS_ARREST.ARREST_ID = OPS_ARREST_LAWBREAKER.ARREST_ID "
                		+ "INNER JOIN OPS_ARREST_LOCALE on OPS_ARREST_LOCALE.ARREST_ID = OPS_ARREST.ARREST_ID  "
                		+ "AND OPS_ARREST_LOCALE.IS_ACTIVE =1 "
                		+ "INNER JOIN MAS_SUB_DISTRICT on MAS_SUB_DISTRICT.SUB_DISTRICT_ID = OPS_ARREST_LOCALE.SUB_DISTRICT_ID  "
                		+ "INNER JOIN MAS_DISTRICT on MAS_DISTRICT.DISTRICT_ID = MAS_SUB_DISTRICT.DISTRICT_ID "
                		+ "INNER JOIN MAS_PROVINCE on MAS_PROVINCE.PROVINCE_ID = MAS_DISTRICT.PROVINCE_ID "
                		+ "INNER JOIN OPS_ARREST_STAFF on OPS_ARREST_STAFF.ARREST_ID = OPS_ARREST.ARREST_ID "
                		+ "AND OPS_ARREST_STAFF.IS_ACTIVE =1 "
                		+ "AND OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14 "
                		+ "INNER JOIN OPS_ARREST_INDICTMENT_DETAIL on OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID  "
                		+ "AND OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE =1 "
                		+ "INNER JOIN OPS_ARREST_INDICTMENT on OPS_ARREST_INDICTMENT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID  "
                		+ "AND OPS_ARREST_INDICTMENT.IS_ACTIVE =1 "
                		+ "INNER JOIN MAS_LAW_GUILTBASE on MAS_LAW_GUILTBASE.GUILTBASE_ID = OPS_ARREST_INDICTMENT.GUILTBASE_ID  "
                		+ "INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE on MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID = MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID  "
                		+ "INNER JOIN MAS_LAW_GROUP_SUBSECTION on MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID  "
                		+ "INNER JOIN OPS_LAWSUIT on OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID "
                		+ "AND IS_LAWSUIT = 1 "
                		+ "AND OPS_LAWSUIT.IS_ACTIVE =1 "
                		+ "INNER JOIN OPS_LAWSUIT_STAFF on OPS_LAWSUIT_STAFF.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID "
                		+ "AND OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID = 16 "
                		+ "AND OPS_LAWSUIT_STAFF.IS_ACTIVE =1 "
                		+ "LEFT JOIN OPS_COMPARE_MAPPING ON OPS_COMPARE_MAPPING.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID "
                		+ "AND OPS_COMPARE_MAPPING.IS_ACTIVE =1 "
                		+ "LEFT JOIN OPS_COMPARE_DETAIL ON OPS_COMPARE_DETAIL.COMPARE_MAPPING_ID = OPS_COMPARE_MAPPING.COMPARE_MAPPING_ID "
                		+ "AND OPS_COMPARE_DETAIL.IS_ACTIVE =1 "
                		+ "LEFT JOIN OPS_LAWSUIT_DETAIL ON OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID "
                		+ "where "
                		+ "PERSON_ID = "+req.getPERSON_ID()
                		+ " AND MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID = "+req.getSUBSECTION_ID()
                		+ " AND OPS_ARREST.OCCURRENCE_DATE BETWEEN  to_date(nvl('2018-01-01 23:59','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+req.getOCCURRENCE_DATE()+" 23:59','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI') "
                		+ "AND OPS_ARREST_LAWBREAKER.IS_ACTIVE =1 "
                		+ "AND OPS_ARREST.IS_ACTIVE =1 "
                		+ "AND OPS_LAWSUIT_DETAIL.IS_ACTIVE =1 "
                		+ "ORDER by OCCURRENCE_DATE asc");

        log.info("[SQL MistreatDetailgetBySubsection] : " + sqlBuilder.toString());
 		
        @SuppressWarnings("unchecked")
        List<MistreatDetailgetBySubsectionResponse> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {


            public MistreatDetailgetBySubsectionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                MistreatDetailgetBySubsectionResponse item = new MistreatDetailgetBySubsectionResponse();
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setARREST_LOCALE(rs.getString("ARREST_LOCALE"));
                item.setARREST_STAFF(rs.getString("ARREST_STAFF"));
                item.setARREST_STAFF_OFFICE(rs.getString("ARREST_STAFF_OFFICE"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_STAFF(rs.getString("LAWSUIT_STAFF"));
                item.setLAWSUIT_STAFF_OFFICE(rs.getString("LAWSUIT_STAFF_OFFICE"));
                item.setPAYMENT_FINE(rs.getFloat("PAYMENT_FINE"));
                item.setProductGroup(getMistreatDetailProductGroup(rs.getInt("INDICTMENT_ID")));

                return item;
            }
        });

        return dataList;

	}
 	
 	protected List<MasProductGroup> getMistreatDetailProductGroup(int INDICTMENT_ID) {

		StringBuilder sqlBuilder = new StringBuilder().
				append(" select DISTINCT PRODUCT_GROUP_NAME "
						+ " from OPS_ARREST_INDICTMENT_PRODUCT"
						+ " INNER JOIN OPS_ARREST_PRODUCT ON OPS_ARREST_PRODUCT.PRODUCT_ID = OPS_ARREST_INDICTMENT_PRODUCT.PRODUCT_ID"
						+ " WHERE INDICTMENT_ID = "+INDICTMENT_ID
						+ " AND OPS_ARREST_INDICTMENT_PRODUCT.IS_ACTIVE = 1"
						+ " AND OPS_ARREST_PRODUCT.IS_ACTIVE = 1" );

		log.info("[SQL getRevenueIncCompareDetail]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<MasProductGroup> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public MasProductGroup mapRow(ResultSet rs, int rowNum) throws SQLException {

				MasProductGroup item = new MasProductGroup();
				item.setPRODUCT_GROUP_NAME(rs.getString("PRODUCT_GROUP_NAME"));
				return item;
			}
		});

		return dataList;
	}


}
