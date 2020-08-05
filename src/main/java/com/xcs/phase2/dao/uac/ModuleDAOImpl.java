package com.xcs.phase2.dao.uac;


import com.xcs.phase2.model.uac.Module;
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
public class ModuleDAOImpl extends UacExt implements ModuleDAO {

    private static final Logger log = LoggerFactory.getLogger(ModuleDAOImpl.class);

    @Override
    public List<Module> ModulegetAll() {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "MODULE_ID," +
                        "MODULE_CODE," +
                        "MODULE_NAME," +
                        "SEQUENCE," +
                        "IS_ACTIVE" +
                        " from MAS_MODULE where IS_ACTIVE = 1 order by SEQUENCE ");


        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<Module> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public Module mapRow(ResultSet rs, int rowNum) throws SQLException {
                Module item = new Module();


                item.setMODULE_ID(rs.getInt("MODULE_ID"));
                item.setMODULE_CODE(rs.getString("MODULE_CODE"));
                item.setMODULE_NAME(rs.getString("MODULE_NAME"));
                item.setSEQUENCE(rs.getInt("SEQUENCE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setModuleDetail(getModuleDetail(rs.getInt("MODULE_ID")));

                return item;
            }
        });

        return dataList;

    }
}
