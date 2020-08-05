package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasOffice;
import com.xcs.phase2.request.master.MasOfficegetByConReq;
import com.xcs.phase2.response.master.MasOfficeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class MasOfficeDAOImpl extends MasterExt implements MasOfficeDAO{

    private static final Logger log = LoggerFactory.getLogger(MasOfficeDAOImpl.class);


    @Override
    public List<MasOffice> MasOfficegetByCon(MasOfficegetByConReq req) {

        String str = "";

        if(!StringUtils.isEmpty(req.getOFFICE_ID())){

            str = " AND MAS_OFFICE.OFFICE_ID = '"+req.getOFFICE_ID()+"'";
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT MAS_OFFICE.*" +
                        "  FROM MAS_OFFICE" +
                        "  WHERE " +
                        "  (" +
                        "    LOWER(MAS_OFFICE.OFFICE_CODE) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "    OR LOWER(MAS_OFFICE.OFFICE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                        "    OR LOWER(MAS_OFFICE.OFFICE_SHORT_NAME) LIKE LOWER ('%"+req.getTEXT_SEARCH()+"%') " +
                        "  )" +
                        "  AND MAS_OFFICE.IS_ACTIVE = 1 " +str+" order by OFFICE_NAME asc ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasOffice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasOffice mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasOffice item = new MasOffice();
                item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
                item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
                item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
                item.setOFFICE_SHORT_NAME(rs.getString("OFFICE_SHORT_NAME"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public MasOfficeResponse MasOfficegetByConList(MasOfficegetByConReq req) {
        MasOfficeResponse masOfficeResponse = new MasOfficeResponse();
        masOfficeResponse.setRESPONSE_DATA(this.MasOfficegetByCon(req));
        return masOfficeResponse;
    }
}
