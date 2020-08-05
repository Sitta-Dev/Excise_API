package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.model.revenue.NewRevenueCourtDetail;
import com.xcs.phase2.model.revenue.RevenueCourtDetail;
import com.xcs.phase2.request.revenue.RevenueCourtDetailDeleteReq;
import com.xcs.phase2.request.revenue.RevenueCourtDetailgetByConReq;
import com.xcs.phase2.request.revenue.RevenueCourtDetailgetByCreateReq;
import com.xcs.phase2.request.revenue.RevenueCourtDetailupdByConReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class RevenueCourtDetailDAOImpl extends RevenueExt implements RevenueCourtDetailDAO {

    private static final Logger log = LoggerFactory.getLogger(RevenueCourtDetailDAOImpl.class);

    @Override
    public List<RevenueCourtDetail> RevenueCourtDetailgetByCon(RevenueCourtDetailgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    select      " +
                        "    OPS_LAWSUIT.*," +
                        "    OPS_LAWSUIT_DETAIL.*," +
                        "    OPS_ARREST_LAWBREAKER.*" +
                        "    from OPS_LAWSUIT" +
                        "    inner join OPS_LAWSUIT_DETAIL on OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_ID and OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE = 0" +
                        "    left  join OPS_ARREST_INDICTMENT_DETAIL on OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID" +
                        "    left join OPS_ARREST_LAWBREAKER on OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
                        "    where OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID = '"+req.getLAWSUIT_DETAIL_ID()+"'");



        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RevenueCourtDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString() , new RowMapper() {

            public RevenueCourtDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                RevenueCourtDetail item = new RevenueCourtDetail();
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setIS_LAWSUIT(rs.getInt("IS_LAWSUIT"));
                item.setREMARK_NOT_LAWSUIT(rs.getString("REMARK_NOT_LAWSUIT"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR(rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setTESTIMONY(rs.getString("TESTIMONY"));
                item.setDELIVERY_DOC_NO_1(rs.getString("DELIVERY_DOC_NO_1"));
                item.setDELIVERY_DOC_NO_2(rs.getString("DELIVERY_DOC_NO_2"));
                item.setDELIVERY_DOC_DATE(rs.getString("DELIVERY_DOC_DATE"));
                item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                item.setIS_SEIZE(rs.getInt("IS_SEIZE"));

                item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
                item.setLAWSUIT_ID_1(rs.getInt("LAWSUIT_ID"));
                item.setINDICTMENT_DETAIL_ID(rs.getInt("INDICTMENT_DETAIL_ID"));
                item.setCOURT_ID(rs.getInt("COURT_ID"));
                item.setLAWSUIT_TYPE(rs.getInt("LAWSUIT_TYPE"));
                item.setLAWSUIT_END(rs.getInt("LAWSUIT_END"));
                item.setCOURT_NAME(rs.getString("COURT_NAME"));
                item.setUNDECIDE_NO_1(rs.getInt("UNDECIDE_NO_1"));
                item.setUNDECIDE_NO_YEAR_1(rs.getString("UNDECIDE_NO_YEAR_1"));
                item.setDECIDE_NO_1(rs.getInt("DECIDE_NO_1"));
                item.setDECIDE_NO_YEAR_1(rs.getString("DECIDE_NO_YEAR_1"));
                item.setUNDECIDE_NO_2(rs.getInt("UNDECIDE_NO_2"));
                item.setUNDECIDE_NO_YEAR_2(rs.getString("UNDECIDE_NO_YEAR_2"));
                item.setDECIDE_NO_2(rs.getInt("DECIDE_NO_2"));
                item.setDECIDE_NO_YEAR_2(rs.getString("DECIDE_NO_YEAR_2"));
                item.setJUDGEMENT_NO(rs.getInt("JUDGEMENT_NO"));
                item.setJUDGEMENT_NO_YEAR(rs.getString("JUDGEMENT_NO_YEAR"));
                item.setJUDGEMENT_DATE(rs.getString("JUDGEMENT_DATE"));
                item.setIS_IMPRISON(rs.getInt("IS_IMPRISON"));
                item.setIMPRISON_TIME(rs.getString("IMPRISON_TIME"));
                item.setIMPRISON_TIME_UNIT(rs.getString("IMPRISON_TIME_UNIT"));
                item.setIS_FINE(rs.getInt("IS_FINE"));
                item.setFINE(rs.getFloat("FINE"));
                item.setIS_PAYONCE(rs.getInt("IS_PAYONCE"));
                item.setFINE_DATE(rs.getString("FINE_DATE"));
                item.setPAYMENT_PERIOD(rs.getInt("PAYMENT_PERIOD"));
                item.setPAYMENT_PERIOD_DUE(rs.getInt("PAYMENT_PERIOD_DUE"));
                item.setPAYMENT_PERIOD_DUE_UNIT(rs.getInt("PAYMENT_PERIOD_DUE_UNIT"));
                item.setPAYMENT_CHANNEL(rs.getInt("PAYMENT_CHANNEL"));
                item.setPAYMENT_BANK(rs.getInt("PAYMENT_BANK"));
                item.setPAYMENT_REF_NO(rs.getString("PAYMENT_REF_NO"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
                item.setIS_DISMISS(rs.getString("IS_DISMISS"));
                item.setUNJUDGEMENT_NO(rs.getString("UNJUDGEMENT_NO"));
                item.setUNJUDGEMENT_NO_YEAR(rs.getString("UNJUDGEMENT_NO_YEAR"));
                item.setLAWBREAKER_ID(rs.getInt("LAWBREAKER_ID"));

                item.setARREST_ID(rs.getInt("ARREST_ID"));
                item.setPERSON_ID(rs.getInt("PERSON_ID"));
                item.setTITLE_ID(rs.getInt("TITLE_ID"));
                item.setPERSON_TYPE(rs.getInt("PERSON_TYPE"));
                item.setENTITY_TYPE(rs.getInt("ENTITY_TYPE"));
                item.setTITLE_NAME_TH(rs.getString("TITLE_NAME_TH"));
                item.setTITLE_NAME_EN(rs.getString("TITLE_NAME_EN"));
                item.setTITLE_SHORT_NAME_TH(rs.getString("TITLE_SHORT_NAME_TH"));
                item.setTITLE_SHORT_NAME_EN(rs.getString("TITLE_SHORT_NAME_EN"));
                item.setFIRST_NAME(rs.getString("FIRST_NAME"));
                item.setMIDDLE_NAME(rs.getString("MIDDLE_NAME"));
                item.setLAST_NAME(rs.getString("LAST_NAME"));
                item.setOTHER_NAME(rs.getString("OTHER_NAME"));
                item.setCOMPANY_NAME(rs.getString("COMPANY_NAME"));
                item.setCOMPANY_REGISTRATION_NO(rs.getString("COMPANY_REGISTRATION_NO"));
                item.setEXCISE_REGISTRATION_NO(rs.getString("EXCISE_REGISTRATION_NO"));
                item.setID_CARD(rs.getString("ID_CARD"));
                item.setAGE(rs.getInt("AGE"));
                item.setPASSPORT_NO(rs.getString("PASSPORT_NO"));
                item.setCAREER(rs.getString("CAREER"));
                item.setPERSON_DESC(rs.getString("PERSON_DESC"));
                item.setEMAIL(rs.getString("EMAIL"));
                item.setTEL_NO(rs.getString("TEL_NO"));
                item.setMISTREAT_NO(rs.getInt("MISTREAT_NO"));

                return item;
            }
        });

        return dataList;
    }

    @Override
    public Boolean RevenueCourtDetailupdByCon(RevenueCourtDetailupdByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_LAWSUIT_DETAIL SET IS_FINE = '3' WHERE LAWSUIT_DETAIL_ID = '"+req.getLAWSUIT_DETAIL_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
        return true;
    }

    @Override
    public Boolean RevenueCourtDetailDelete(RevenueCourtDetailDeleteReq req) {

        StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_PAYMENT SET IS_REVENUE = '0' WHERE PAYMENT_ID = '"+req.getPAYMENT_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
        return true;
    }

    @Override
    public List<NewRevenueCourtDetail> RevenueCourtDetailgetByCreate(RevenueCourtDetailgetByCreateReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("     select DISTINCT " +
                        "     OPS_LAWSUIT.LAWSUIT_ID, " +
                        "     OPS_LAWSUIT.INDICTMENT_ID, " +
                        "     OPS_LAWSUIT.OFFICE_ID, " +
                        "     OPS_LAWSUIT.OFFICE_CODE, " +
                        "     OPS_LAWSUIT.OFFICE_NAME, " +
                        "     CASE WHEN OPS_LAWSUIT.IS_OUTSIDE = '1' THEN 'น. ' END || OPS_LAWSUIT.LAWSUIT_NO || CASE WHEN OPS_LAWSUIT.LAWSUIT_NO IS NOT NULL THEN '/' END || TO_CHAR(OPS_LAWSUIT.LAWSUIT_NO_YEAR, 'YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') AS LAWSUIT_NO," +
                        "     OPS_LAWSUIT.IS_LAWSUIT," +
                        "     OPS_LAWSUIT.LAWSUIT_DATE," +
                        "     OPS_LAWSUIT.IS_OUTSIDE," +
                        "     OPS_ARREST_INDICTMENT.ARREST_ID" +
                        "     from " +
                        "     OPS_LAWSUIT" +
                        "     inner join OPS_LAWSUIT_DETAIL on OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_ID " +
                        "     and OPS_LAWSUIT_DETAIL.LAWSUIT_TYPE = 0" +
                        "     and OPS_LAWSUIT_DETAIL.IS_ACTIVE = 1" +
                        "     inner join OPS_PAYMENT on OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID = OPS_PAYMENT.LAWSUIT_DETAIL_ID " +
                        "     and OPS_PAYMENT.IS_ACTIVE = 1" +
                        "     and OPS_PAYMENT.IS_REVENUE = 0" +
                        "     left join OPS_ARREST_INDICTMENT_DETAIL on OPS_LAWSUIT_DETAIL.INDICTMENT_DETAIL_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_DETAIL_ID" +
                        "     inner join OPS_ARREST_INDICTMENT on OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID " +
                        "     and OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        "     where OPS_LAWSUIT.OFFICE_CODE = '"+req.getOFFICE_CODE()+"'" +
                        "     and OPS_ARREST_INDICTMENT_DETAIL.IS_ACTIVE = 1" +
                        "     order by OPS_LAWSUIT.LAWSUIT_DATE DESC");



        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<NewRevenueCourtDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString() , new RowMapper() {

            public NewRevenueCourtDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

                NewRevenueCourtDetail item = new NewRevenueCourtDetail();

                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setLAWSUIT_NO(rs.getString("LAWSUIT_NO"));
                item.setIS_LAWSUIT(rs.getInt("IS_LAWSUIT"));
                item.setLAWSUIT_DATE(rs.getString("LAWSUIT_DATE"));
                item.setIS_OUTSIDE(rs.getInt("IS_OUTSIDE"));
                item.setARREST_ID(rs.getInt("ARREST_ID"));

                item.setLawsuiltDetail(getNewRevenueLawsuitDetail(rs.getInt("LAWSUIT_ID")));
                item.setNotice(getNewRevenueNotice(rs.getInt("ARREST_ID")));

                return item;
            }
        });

        return dataList;
    }
}
