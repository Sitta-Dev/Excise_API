package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.arrest.*;
import com.xcs.phase2.request.arrest.ArrestgetByConReq;
import com.xcs.phase2.request.arrest.ArrestupdDeleteReq;
import com.xcs.phase2.response.arrest.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@Service
@Transactional
public class ArrestDAOImpl extends ArrestExt implements ArrestDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestDAOImpl.class);

	@Override
	public Arrest ArrestgetByCon(ArrestgetByConReq req) {
		

		
		StringBuilder sqlBuilder = new StringBuilder()
			    .append("select " +
			    		"ARREST_ID," +
			    		"OFFICE_ID," +
			    		"ARREST_CODE," +
			    		"OFFICE_CODE," +
			    		"OFFICE_NAME," +
			    		"to_char(ARREST_DATE,'"+ Pattern.FORMAT_DATETIME+"') as ARREST_DATE," +
			    		"to_char(OCCURRENCE_DATE,'"+Pattern.FORMAT_DATETIME+"') as OCCURRENCE_DATE," +
			    		"BEHAVIOR_1," +
			    		"BEHAVIOR_2," +
			    		"BEHAVIOR_3," +
			    		"BEHAVIOR_4," +
			    		"BEHAVIOR_5," +
			    		"TESTIMONY," +
			    		"IS_REQUEST," +
			    		"REQUEST_DESC," +
			    		"IS_LAWSUIT_COMPLETE," +
			    		"IS_ACTIVE," +
			    		"to_char(CREATE_DATE,'"+Pattern.FORMAT_DATETIME+"') as CREATE_DATE," +
			    		"CREATE_USER_ACCOUNT_ID," +
			    		"to_char(UPDATE_DATE,'"+Pattern.FORMAT_DATETIME+"') as UPDATE_DATE," +
			    		"UPDATE_USER_ACCOUNT_ID " +
			    		"from OPS_ARREST  where IS_ACTIVE = 1 ");
				sqlBuilder.append("and ARREST_ID = '"+req.getARREST_ID()+"'");

				log.info("[SQL] : "+sqlBuilder.toString());
		
		return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Arrest>() {

			public Arrest extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {

					Arrest item = new Arrest();
					item.setARREST_ID(rs.getInt("ARREST_ID"));
					item.setOFFICE_ID(rs.getInt("OFFICE_ID"));
					item.setARREST_CODE(rs.getString("ARREST_CODE"));
					item.setOFFICE_CODE(rs.getString("OFFICE_CODE"));
					item.setOFFICE_NAME(rs.getString("OFFICE_NAME"));
					item.setARREST_DATE(rs.getString("ARREST_DATE"));
					item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
					item.setBEHAVIOR_1(rs.getString("BEHAVIOR_1"));
					item.setBEHAVIOR_2(rs.getString("BEHAVIOR_2"));
					item.setBEHAVIOR_3(rs.getString("BEHAVIOR_3"));
					item.setBEHAVIOR_4(rs.getString("BEHAVIOR_4"));
					item.setBEHAVIOR_5(rs.getString("BEHAVIOR_5"));
					item.setTESTIMONY(rs.getString("TESTIMONY"));
					item.setIS_REQUEST(rs.getInt("IS_REQUEST"));
					item.setREQUEST_DESC(rs.getString("REQUEST_DESC"));
					item.setIS_LAWSUIT_COMPLETE(rs.getInt("IS_LAWSUIT_COMPLETE"));
					item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
					item.setCREATE_DATE(rs.getString("CREATE_DATE"));
					item.setCREATE_USER_ACCOUNT_ID(rs.getInt("CREATE_USER_ACCOUNT_ID"));
					item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
					item.setUPDATE_USER_ACCOUNT_ID(rs.getInt("UPDATE_USER_ACCOUNT_ID"));
					item.setArrestStaff(getArrestStaff(rs.getInt("ARREST_ID")));
					item.setArrestLocale(getArrestLocale(rs.getInt("ARREST_ID")));
					item.setArrestLawbreaker(getArrestLawbreaker(rs.getInt("ARREST_ID")));
					item.setArrestProduct(getArrestProduct(rs.getInt("ARREST_ID")));
					item.setArrestIndictment(getArrestIndictment(rs.getInt("ARREST_ID")));
					item.setArrestNotice(getArrestNotice(rs.getInt("ARREST_ID")));
					item.setArrestPurityCert(getArrestPurityCert(rs.getInt("ARREST_ID")));
					item.setArrestSearchWarrant(getArrestSearchWarrant(rs.getInt("ARREST_ID")));
                	
					return item;
				}
				
				return null;
			}
		});
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ArrestinsAllResponse ArrestinsAll(Arrest req) {
		
		ArrestinsAllResponse res = new ArrestinsAllResponse();
		
		try {
			
			String ARREST_ID = getSequences("SELECT OPS_ARREST_SEQ.NEXTVAL FROM DUAL");
			StringBuilder sqlBuilder = new StringBuilder()
		    .append("Insert into OPS_ARREST " +
		    		"(ARREST_ID," +
		    		"OFFICE_ID," +
		    		"ARREST_CODE," +
		    		"OFFICE_CODE," +
		    		"OFFICE_NAME," +
		    		"ARREST_DATE," +
		    		"OCCURRENCE_DATE," +
		    		"BEHAVIOR_1," +
		    		"BEHAVIOR_2," +
		    		"BEHAVIOR_3," +
		    		"BEHAVIOR_4," +
		    		"BEHAVIOR_5," +
		    		"TESTIMONY," +
		    		"IS_REQUEST," +
		    		"REQUEST_DESC," +
		    		"IS_LAWSUIT_COMPLETE," +
		    		"IS_ACTIVE," +
		    		"CREATE_DATE," +
		    		"CREATE_USER_ACCOUNT_ID," +
		    		"UPDATE_DATE," +
		    		"UPDATE_USER_ACCOUNT_ID) " +
		    		"values (" +
		    		"'"+ARREST_ID+"'," +
		    		"'"+req.getOFFICE_ID()+"'," +
		    		"'"+req.getARREST_CODE()+"'," +
		    		"'"+req.getOFFICE_CODE()+"'," +
		    		"'"+req.getOFFICE_NAME()+"'," +
		    		"TO_TIMESTAMP_TZ('"+req.getARREST_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
		    		"TO_TIMESTAMP_TZ('"+req.getOCCURRENCE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
		    		"'"+req.getBEHAVIOR_1()+"'," +
		    		"'"+req.getBEHAVIOR_2()+"'," +
		    		"'"+req.getBEHAVIOR_3()+"'," +
		    		"'"+req.getBEHAVIOR_4()+"'," +
		    		"'"+req.getBEHAVIOR_5()+"'," +
		    		"'"+req.getTESTIMONY()+"'," +
		    		"'"+req.getIS_REQUEST()+"'," +
		    		"'"+req.getREQUEST_DESC()+"'," +
		    		"'"+req.getIS_LAWSUIT_COMPLETE()+"'," +
		    		"'"+req.getIS_ACTIVE()+"'," +
		    		"TO_TIMESTAMP_TZ('"+req.getCREATE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
		    		"'"+req.getCREATE_USER_ACCOUNT_ID()+"'," +
		    		"TO_TIMESTAMP_TZ('"+req.getUPDATE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
		    		"'"+req.getUPDATE_USER_ACCOUNT_ID()+"')");
			
			log.info("[SQL] : "+sqlBuilder.toString());
			getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
			res.setARREST_ID(Integer.parseInt(ARREST_ID));
			
			
			if(req.getArrestStaff() != null) {
		    	log.info("[Sub] Size : "+req.getArrestStaff().size());
		    	List<ArrestStaffResponse> list = new ArrayList<ArrestStaffResponse>();
		    	
			    if(req.getArrestStaff().size() > 0){
			    	
			    	for(ArrestStaff item : req.getArrestStaff()){
			    		
			    		String STAFF_ID = getSequences("SELECT \"OPS_ARREST_STAFF_SEQ\".NEXTVAL FROM DUAL");
			    		ArrestStaffResponse  obj = new ArrestStaffResponse();
			    		obj.setSTAFF_ID(Integer.parseInt(STAFF_ID));
			    		
			    		StringBuilder sqlBuilderSub = new StringBuilder()
					    	    .append("Insert into OPS_ARREST_STAFF " +
					    	    		"(STAFF_ID," +
					    	    		"ARREST_ID," +
					    	    		"STAFF_REF_ID," +
					    	    		"TITLE_ID," +
					    	    		"STAFF_CODE," +
					    	    		"ID_CARD," +
					    	    		"STAFF_TYPE," +
					    	    		"TITLE_NAME_TH," +
					    	    		"TITLE_NAME_EN," +
					    	    		"TITLE_SHORT_NAME_TH," +
					    	    		"TITLE_SHORT_NAME_EN," +
					    	    		"FIRST_NAME," +
					    	    		"LAST_NAME," +
					    	    		"AGE," +
					    	    		"OPERATION_POS_CODE," +
					    	    		"OPREATION_POS_NAME," +
					    	    		"OPREATION_POS_LEVEL," +
					    	    		"OPERATION_POS_LEVEL_NAME," +
					    	    		"OPERATION_DEPT_CODE," +
					    	    		"OPERATION_DEPT_NAME," +
					    	    		"OPERATION_DEPT_LEVEL," +
					    	    		"OPERATION_UNDER_DEPT_CODE," +
					    	    		"OPERATION_UNDER_DEPT_NAME," +
					    	    		"OPERATION_UNDER_DEPT_LEVEL," +
					    	    		"OPERATION_WORK_DEPT_CODE," +
					    	    		"OPERATION_WORK_DEPT_NAME," +
					    	    		"OPERATION_WORK_DEPT_LEVEL," +
					    	    		"OPERATION_OFFICE_CODE," +
					    	    		"OPERATION_OFFICE_NAME," +
					    	    		"OPERATION_OFFICE_SHORT_NAME," +
					    	    		"MANAGEMENT_POS_CODE," +
					    	    		"MANAGEMENT_POS_NAME," +
					    	    		"MANAGEMENT_POS_LEVEL," +
					    	    		"MANAGEMENT_POS_LEVEL_NAME," +
					    	    		"MANAGEMENT_DEPT_CODE," +
					    	    		"MANAGEMENT_DEPT_NAME," +
					    	    		"MANAGEMENT_DEPT_LEVEL," +
					    	    		"MANAGEMENT_UNDER_DEPT_CODE," +
					    	    		"MANAGEMENT_UNDER_DEPT_NAME," +
					    	    		"MANAGEMENT_UNDER_DEPT_LEVEL," +
					    	    		"MANAGEMENT_WORK_DEPT_CODE," +
					    	    		"MANAGEMENT_WORK_DEPT_NAME," +
					    	    		"MANAGEMENT_WORK_DEPT_LEVEL," +
					    	    		"MANAGEMENT_OFFICE_CODE," +
					    	    		"MANAGEMENT_OFFICE_NAME," +
					    	    		"MANAGEMENT_OFFICE_SHORT_NAME," +
					    	    		"REPRESENT_POS_CODE," +
					    	    		"REPRESENT_POS_NAME," +
					    	    		"REPRESENT_POS_LEVEL," +
					    	    		"REPRESENT_POS_LEVEL_NAME," +
					    	    		"REPRESENT_DEPT_CODE," +
					    	    		"REPRESENT_DEPT_NAME," +
					    	    		"REPRESENT_DEPT_LEVEL," +
					    	    		"REPRESENT_UNDER_DEPT_CODE," +
					    	    		"REPRESENT_UNDER_DEPT_NAME," +
					    	    		"REPRESENT_UNDER_DEPT_LEVEL," +
					    	    		"REPRESENT_WORK_DEPT_CODE," +
					    	    		"REPRESENT_WORK_DEPT_NAME," +
					    	    		"REPRESENT_WORK_DEPT_LEVEL," +
					    	    		"REPRESENT_OFFICE_CODE," +
					    	    		"REPRESENT_OFFICE_NAME," +
					    	    		"REPRESENT_OFFICE_SHORT_NAME," +
					    	    		"STATUS," +
					    	    		"REMARK," +
					    	    		"CONTRIBUTOR_ID," +
					    	    		"IS_ACTIVE) " +
					    	    		"values (" +
					    	    		"'"+STAFF_ID+"'," +
					    	    		"'"+ARREST_ID+"'," +
					    	    		"'"+item.getSTAFF_REF_ID()+"'," +
					    	    		"'"+item.getTITLE_ID()+"'," +
					    	    		"'"+item.getSTAFF_CODE()+"'," +
					    	    		"'"+item.getID_CARD()+"'," +
					    	    		"'"+item.getSTAFF_TYPE()+"'," +
					    	    		"'"+item.getTITLE_NAME_TH()+"'," +
					    	    		"'"+item.getTITLE_NAME_EN()+"'," +
					    	    		"'"+item.getTITLE_SHORT_NAME_TH()+"'," +
					    	    		"'"+item.getTITLE_SHORT_NAME_EN()+"'," +
					    	    		"'"+item.getFIRST_NAME()+"'," +
					    	    		"'"+item.getLAST_NAME()+"'," +
					    	    		"'"+item.getAGE()+"'," +
					    	    		"'"+item.getOPERATION_POS_CODE()+"'," +
					    	    		"'"+item.getOPREATION_POS_NAME()+"'," +
					    	    		"'"+item.getOPREATION_POS_LEVEL()+"'," +
					    	    		"'"+item.getOPERATION_POS_LEVEL_NAME()+"'," +
					    	    		"'"+item.getOPERATION_DEPT_CODE()+"'," +
					    	    		"'"+item.getOPERATION_DEPT_NAME()+"'," +
					    	    		"'"+item.getOPERATION_DEPT_LEVEL()+"'," +
					    	    		"'"+item.getOPERATION_UNDER_DEPT_CODE()+"'," +
					    	    		"'"+item.getOPERATION_UNDER_DEPT_NAME()+"'," +
					    	    		"'"+item.getOPERATION_UNDER_DEPT_LEVEL()+"'," +
					    	    		"'"+item.getOPERATION_WORK_DEPT_CODE()+"'," +
					    	    		"'"+item.getOPERATION_WORK_DEPT_NAME()+"'," +
					    	    		"'"+item.getOPERATION_WORK_DEPT_LEVEL()+"'," +
					    	    		"'"+item.getOPERATION_OFFICE_CODE()+"'," +
					    	    		"'"+item.getOPERATION_OFFICE_NAME()+"'," +
					    	    		"'"+item.getOPERATION_OFFICE_SHORT_NAME()+"'," +
					    	    		"'"+item.getMANAGEMENT_POS_CODE()+"'," +
					    	    		"'"+item.getMANAGEMENT_POS_NAME()+"'," +
					    	    		"'"+item.getMANAGEMENT_POS_LEVEL()+"'," +
					    	    		"'"+item.getMANAGEMENT_POS_LEVEL_NAME()+"'," +
					    	    		"'"+item.getMANAGEMENT_DEPT_CODE()+"'," +
					    	    		"'"+item.getMANAGEMENT_DEPT_NAME()+"'," +
					    	    		"'"+item.getMANAGEMENT_DEPT_LEVEL()+"'," +
					    	    		"'"+item.getMANAGEMENT_UNDER_DEPT_CODE()+"'," +
					    	    		"'"+item.getMANAGEMENT_UNDER_DEPT_NAME()+"'," +
					    	    		"'"+item.getMANAGEMENT_UNDER_DEPT_LEVEL()+"'," +
					    	    		"'"+item.getMANAGEMENT_WORK_DEPT_CODE()+"'," +
					    	    		"'"+item.getMANAGEMENT_WORK_DEPT_NAME()+"'," +
					    	    		"'"+item.getMANAGEMENT_WORK_DEPT_LEVEL()+"'," +
					    	    		"'"+item.getMANAGEMENT_OFFICE_CODE()+"'," +
					    	    		"'"+item.getMANAGEMENT_OFFICE_NAME()+"'," +
					    	    		"'"+item.getMANAGEMENT_OFFICE_SHORT_NAME()+"'," +
					    	    		"'"+item.getREPRESENT_POS_CODE()+"'," +
					    	    		"'"+item.getREPRESENT_POS_NAME()+"'," +
					    	    		"'"+item.getREPRESENT_POS_LEVEL()+"'," +
					    	    		"'"+item.getREPRESENT_POS_LEVEL_NAME()+"'," +
					    	    		"'"+item.getREPRESENT_DEPT_CODE()+"'," +
					    	    		"'"+item.getREPRESENT_DEPT_NAME()+"'," +
					    	    		"'"+item.getREPRESENT_DEPT_LEVEL()+"'," +
					    	    		"'"+item.getREPRESENT_UNDER_DEPT_CODE()+"'," +
					    	    		"'"+item.getREPRESENT_UNDER_DEPT_NAME()+"'," +
					    	    		"'"+item.getREPRESENT_UNDER_DEPT_LEVEL()+"'," +
					    	    		"'"+item.getREPRESENT_WORK_DEPT_CODE()+"'," +
					    	    		"'"+item.getREPRESENT_WORK_DEPT_NAME()+"'," +
					    	    		"'"+item.getREPRESENT_WORK_DEPT_LEVEL()+"'," +
					    	    		"'"+item.getREPRESENT_OFFICE_CODE()+"'," +
					    	    		"'"+item.getREPRESENT_OFFICE_NAME()+"'," +
					    	    		"'"+item.getREPRESENT_OFFICE_SHORT_NAME()+"'," +
					    	    		"'"+item.getSTATUS()+"'," +
					    	    		"'"+item.getREMARK()+"'," +
					    	    		"'"+item.getCONTRIBUTOR_ID()+"'," +
					    	    		"'"+item.getIS_ACTIVE()+"' )");
					    		log.info("[SQL] Sub : "+sqlBuilderSub.toString());
					    	    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
					    	    
					    	    
			    		  list.add(obj);
			    	}
			    }
			    res.setArrestStaff(list);
		    }
			
			if(req.getArrestLocale() != null) {
		    	log.info("[Sub] Size : "+req.getArrestLocale().size());
		    	List<ArrestLocaleResponse> list = new ArrayList<ArrestLocaleResponse>();
		    	
			    if(req.getArrestLocale().size() > 0){
			    	for(ArrestLocale item : req.getArrestLocale()){
			    		
			    		String LOCALE_ID = getSequences("SELECT OPS_ARREST_LOCALE_SEQ.NEXTVAL FROM DUAL");
			    		ArrestLocaleResponse  obj = new ArrestLocaleResponse();
			    		obj.setLOCALE_ID(Integer.parseInt(LOCALE_ID));

			    		StringBuilder sqlBuilderSub = new StringBuilder()
			    			    .append("Insert into OPS_ARREST_LOCALE " +
			    			    		"(LOCALE_ID," +
			    			    		"ARREST_ID," +
			    			    		"SUB_DISTRICT_ID," +
			    			    		"GPS," +
			    			    		"ADDRESS_NO," +
			    			    		"VILLAGE_NO," +
			    			    		"BUILDING_NAME," +
			    			    		"ROOM_NO," +
			    			    		"FLOOR," +
			    			    		"VILLAGE_NAME," +
			    			    		"ALLEY," +
			    			    		"LANE," +
			    			    		"ROAD," +
			    			    		"ADDRESS_TYPE," +
			    			    		"POLICE_STATION," +
			    			    		"ADDRESS_STATUS," +
										"LOCATION," +
			    			    		"IS_ACTIVE) " +
			    			    		"values (" +
			    			    		"'"+LOCALE_ID+"'," +
					    	    		"'"+ARREST_ID+"'," +
					    	    		"'"+item.getSUB_DISTRICT_ID()+"'," +
					    	    		"'"+item.getGPS()+"'," +
					    	    		"'"+item.getADDRESS_NO()+"'," +
					    	    		"'"+item.getVILLAGE_NO()+"'," +
					    	    		"'"+item.getBUILDING_NAME()+"'," +
					    	    		"'"+item.getROOM_NO()+"'," +
					    	    		"'"+item.getFLOOR()+"'," +
					    	    		"'"+item.getVILLAGE_NAME()+"'," +
					    	    		"'"+item.getALLEY()+"'," +
					    	    		"'"+item.getLANE()+"'," +
					    	    		"'"+item.getROAD()+"'," +
					    	    		"'"+item.getADDRESS_TYPE()+"'," +
					    	    		"'"+item.getPOLICE_STATION()+"'," +
					    	    		"'"+item.getADDRESS_STATUS()+"'," +
										"'"+item.getLOCATION()+"'," +
					    	    		"'"+item.getIS_ACTIVE()+"' )");
			    				log.info("[SQL] : "+sqlBuilderSub.toString());
			    				
			    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
			    		list.add(obj);
			    	}
			    }
			    res.setArrestLocale(list);
		    }
			
			if(req.getArrestLawbreaker() != null) {
		    	log.info("[Sub] Size : "+req.getArrestLawbreaker().size());
		    	List<ArrestLawbreakerResponse> list = new ArrayList<ArrestLawbreakerResponse>();
		    	
			    if(req.getArrestLawbreaker().size() > 0){
			    	for(ArrestLawbreaker item : req.getArrestLawbreaker()){
			    		
			    		String LAWBREAKER_ID = getSequences("SELECT \"OPS_ARREST_LAWBREAKER_SEQ\".NEXTVAL FROM DUAL");
			    		ArrestLawbreakerResponse  obj = new ArrestLawbreakerResponse();
			    		obj.setLAWBREAKER_ID(Integer.parseInt(LAWBREAKER_ID));
			    		obj.setPERSON_ID(item.getPERSON_ID());

			    		StringBuilder sqlBuilderSub = new StringBuilder()
			    			    .append("Insert into OPS_ARREST_LAWBREAKER (" +
			    			    		"LAWBREAKER_ID," +
			    			    		"ARREST_ID," +
			    			    		"PERSON_ID," +
			    			    		"TITLE_ID," +
			    			    		"PERSON_TYPE," +
			    			    		"ENTITY_TYPE," +
			    			    		"TITLE_NAME_TH," +
			    			    		"TITLE_NAME_EN," +
			    			    		"TITLE_SHORT_NAME_TH," +
			    			    		"TITLE_SHORT_NAME_EN," +
			    			    		"FIRST_NAME," +
			    			    		"MIDDLE_NAME," +
			    			    		"LAST_NAME," +
			    			    		"OTHER_NAME," +
			    			    		"COMPANY_REGISTRATION_NO," +
			    			    		"EXCISE_REGISTRATION_NO," +
			    			    		"ID_CARD," +
			    			    		"AGE," +
			    			    		"PASSPORT_NO," +
			    			    		"CAREER," +
			    			    		"PERSON_DESC," +
			    			    		"EMAIL," +
			    			    		"TEL_NO," +
			    			    		"MISTREAT_NO," +
										"COMPANY_NAME," +
			    			    		"IS_ACTIVE) " +
			    			    		"values (" +
			    			    		"'"+LAWBREAKER_ID+"'," +
					    	    		"'"+ARREST_ID+"'," +
					    	    		"'"+item.getPERSON_ID()+"'," +
			    			    		"'"+item.getTITLE_ID()+"'," +
			    			    		"'"+item.getPERSON_TYPE()+"'," +
			    			    		"'"+item.getENTITY_TYPE()+"'," +
			    			    		"'"+item.getTITLE_NAME_TH()+"'," +
			    			    		"'"+item.getTITLE_NAME_EN()+"'," +
			    			    		"'"+item.getTITLE_SHORT_NAME_TH()+"'," +
			    			    		"'"+item.getTITLE_SHORT_NAME_EN()+"'," +
			    			    		"'"+item.getFIRST_NAME()+"'," +
			    			    		"'"+item.getMIDDLE_NAME()+"'," +
			    			    		"'"+item.getLAST_NAME()+"'," +
			    			    		"'"+item.getOTHER_NAME()+"'," +
			    			    		"'"+item.getCOMPANY_REGISTRATION_NO()+"'," +
			    			    		"'"+item.getEXCISE_REGISTRATION_NO()+"'," +
			    			    		"'"+item.getID_CARD()+"'," +
			    			    		"'"+item.getAGE()+"'," +
			    			    		"'"+item.getPASSPORT_NO()+"'," +
			    			    		"'"+item.getCAREER()+"'," +
			    			    		"'"+item.getPERSON_DESC()+"'," +
			    			    		"'"+item.getEMAIL()+"'," +
			    			    		"'"+item.getTEL_NO()+"'," +
			    			    		"'"+item.getMISTREAT_NO()+"'," +
										"'"+item.getCOMPANY_NAME()+"'," +
			    			    		"'"+item.getIS_ACTIVE()+"' )");
			    				log.info("[SQL] : "+sqlBuilderSub.toString());
			    				
			    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
			    		list.add(obj);
			    	}
			    }
			    res.setArrestLawbreaker(list);
		    }
			
			if(req.getArrestProduct() != null) {
		    	log.info("[Sub] Size : "+req.getArrestProduct().size());
		    	List<ArrestProductResponse> list = new ArrayList<ArrestProductResponse>();
		    	
			    if(req.getArrestProduct().size() > 0){
			    	for(ArrestProduct item : req.getArrestProduct()){
			    		
			    		String PRODUCT_ID = getSequences("SELECT \"OPS_ARREST_PRODUCT_SEQ\".NEXTVAL FROM DUAL");
			    		ArrestProductResponse  obj = new ArrestProductResponse();
			    		obj.setPRODUCT_ID(Integer.parseInt(PRODUCT_ID));
						obj.setPRODUCT_MAPPING_ID(item.getPRODUCT_MAPPING_ID());
						obj.setPRODUCT_REF_CODE(item.getPRODUCT_REF_CODE());

			    		StringBuilder sqlBuilderSub = new StringBuilder()
			    			    .append("Insert into OPS_ARREST_PRODUCT (" +
			    			    		"PRODUCT_ID," +
			    			    		"ARREST_ID," +
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
			    			    		"IS_ACTIVE) " +
			    			    		"values (" +
			    			    		"'"+PRODUCT_ID+"'," +
					    	    		"'"+ARREST_ID+"'," +
					    	    		"'"+item.getPRODUCT_MAPPING_ID()+"'," +
			    			    		"'"+item.getPRODUCT_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_REF_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_GROUP_ID()+"'," +
			    			    		"'"+item.getPRODUCT_CATEGORY_ID()+"'," +
			    			    		"'"+item.getPRODUCT_TYPE_ID()+"'," +
			    			    		"'"+item.getPRODUCT_SUBTYPE_ID()+"'," +
			    			    		"'"+item.getPRODUCT_SUBSETTYPE_ID()+"'," +
			    			    		"'"+item.getPRODUCT_BRAND_ID()+"'," +
			    			    		"'"+item.getPRODUCT_SUBBRAND_ID()+"'," +
			    			    		"'"+item.getPRODUCT_MODEL_ID()+"'," +
			    			    		"'"+item.getPRODUCT_TAXDETAIL_ID()+"'," +
			    			    		"'"+item.getSIZES_UNIT_ID()+"'," +
			    			    		"'"+item.getQUATITY_UNIT_ID()+"'," +
			    			    		"'"+item.getVOLUMN_UNIT_ID()+"'," +
			    			    		"'"+item.getPRODUCT_GROUP_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_GROUP_NAME()+"'," +
			    			    		"'"+item.getPRODUCT_CATEGORY_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_CATEGORY_NAME()+"'," +
			    			    		"'"+item.getPRODUCT_TYPE_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_TYPE_NAME()+"'," +
			    			    		"'"+item.getPRODUCT_SUBTYPE_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_SUBTYPE_NAME()+"'," +
			    			    		"'"+item.getPRODUCT_SUBSETTYPE_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_SUBSETTYPE_NAME()+"'," +
			    			    		"'"+item.getPRODUCT_BRAND_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_BRAND_NAME_TH()+"'," +
			    			    		"'"+item.getPRODUCT_BRAND_NAME_EN()+"'," +
			    			    		"'"+item.getPRODUCT_SUBBRAND_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_SUBBRAND_NAME_TH()+"'," +
			    			    		"'"+item.getPRODUCT_SUBBRAND_NAME_EN()+"'," +
			    			    		"'"+item.getPRODUCT_MODEL_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_MODEL_NAME_TH()+"'," +
			    			    		"'"+item.getPRODUCT_MODEL_NAME_EN()+"'," +
			    			    		"'"+item.getIS_TAX_VALUE()+"'," +
			    			    		"'"+item.getTAX_VALUE()+"'," +
			    			    		"'"+item.getIS_TAX_VOLUMN()+"'," +
			    			    		"'"+item.getTAX_VOLUMN()+"'," +
			    			    		"'"+item.getTAX_VOLUMN_UNIT()+"'," +
			    			    		"'"+item.getLICENSE_PLATE()+"'," +
			    			    		"'"+item.getENGINE_NO()+"'," +
			    			    		"'"+item.getCHASSIS_NO()+"'," +
			    			    		"'"+item.getPRODUCT_DESC()+"'," +
			    			    		"'"+item.getSUGAR()+"'," +
			    			    		"'"+item.getCO2()+"'," +
			    			    		"'"+item.getDEGREE()+"'," +
			    			    		"'"+item.getPRICE()+"'," +
			    			    		"'"+item.getSIZES()+"'," +
			    			    		"'"+item.getSIZES_UNIT()+"'," +
			    			    		"'"+item.getQUANTITY()+"'," +
			    			    		"'"+item.getQUANTITY_UNIT()+"'," +
			    			    		"'"+item.getVOLUMN()+"'," +
			    			    		"'"+item.getVOLUMN_UNIT()+"'," +
			    			    		"'"+item.getREMARK()+"'," +
			    			    		"'"+item.getIS_DOMESTIC()+"'," +
			    			    		"'"+item.getIS_ILLEGAL()+"'," +
			    			    		"'"+item.getIS_ACTIVE()+"' )");
			    				log.info("[SQL] : "+sqlBuilderSub.toString());
			    				
			    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
			    		
			    		
			    		if(item.getArrestProductMapping() != null) {
					    	log.info("[Sub] Size : "+item.getArrestProductMapping().size());
					    	List<ArrestProductMappingResponse> list1 = new ArrayList<ArrestProductMappingResponse>();
					    	
						    if(item.getArrestProductMapping().size() > 0){
						    	for(ArrestProductMapping item1 : item.getArrestProductMapping()){
						    		
						    		String PRODUCT_MAPPING_ID = getSequences("SELECT \"OPS_ARREST_PRODUCT_MAPPING_SEQ\".NEXTVAL FROM DUAL");
						    		ArrestProductMappingResponse  obj1 = new ArrestProductMappingResponse();
						    		obj1.setPRODUCT_MAPPING_ID(Integer.parseInt(PRODUCT_MAPPING_ID));
									obj1.setPRODUCT_ID(Integer.parseInt(PRODUCT_ID));
									obj1.setPRODUCT_REF_CODE(item1.getPRODUCT_REF_CODE());

						    		StringBuilder sqlBuilderSub1 = new StringBuilder()
						    			    .append("Insert into OPS_ARREST_PRODUCT_MAPPING (" +
						    			    		"PRODUCT_MAPPING_ID," +
						    			    		"PRODUCT_ID," +
						    			    		"PRODUCT_MAPPING_REF_ID," +
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
						    			    		"PRODUCT_RESULT," +
						    			    		"SCIENCE_RESULT_DESC," +
						    			    		"VAT," +
						    			    		"IS_DOMESTIC," +
						    			    		"IS_ILLEGAL," +
						    			    		"IS_SCIENCE," +
						    			    		"IS_ACTIVE)" +
						    			    		"values (" +
						    			    		"'"+PRODUCT_MAPPING_ID+"'," +
								    	    		"'"+PRODUCT_ID+"'," +
						    			    		"'"+item1.getPRODUCT_MAPPING_REF_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_REF_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_GROUP_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_CATEGORY_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_TYPE_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBTYPE_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBSETTYPE_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_BRAND_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBBRAND_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_MODEL_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_TAXDETAIL_ID()+"'," +
						    			    		"'"+item1.getSIZES_UNIT_ID()+"'," +
						    			    		"'"+item1.getQUATITY_UNIT_ID()+"'," +
						    			    		"'"+item1.getVOLUMN_UNIT_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_GROUP_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_GROUP_NAME()+"'," +
						    			    		"'"+item1.getPRODUCT_CATEGORY_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_CATEGORY_NAME()+"'," +
						    			    		"'"+item1.getPRODUCT_TYPE_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_TYPE_NAME()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBTYPE_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBTYPE_NAME()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBSETTYPE_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBSETTYPE_NAME()+"'," +
						    			    		"'"+item1.getPRODUCT_BRAND_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_BRAND_NAME_TH()+"'," +
						    			    		"'"+item1.getPRODUCT_BRAND_NAME_EN()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBBRAND_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBBRAND_NAME_TH()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBBRAND_NAME_EN()+"'," +
						    			    		"'"+item1.getPRODUCT_MODEL_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_MODEL_NAME_TH()+"'," +
						    			    		"'"+item1.getPRODUCT_MODEL_NAME_EN()+"'," +
						    			    		"'"+item1.getIS_TAX_VALUE()+"'," +
						    			    		"'"+item1.getTAX_VALUE()+"'," +
						    			    		"'"+item1.getIS_TAX_VOLUMN()+"'," +
						    			    		"'"+item1.getTAX_VOLUMN()+"'," +
						    			    		"'"+item1.getTAX_VOLUMN_UNIT()+"'," +
						    			    		"'"+item1.getLICENSE_PLATE()+"'," +
						    			    		"'"+item1.getENGINE_NO()+"'," +
						    			    		"'"+item1.getCHASSIS_NO()+"'," +
						    			    		"'"+item1.getPRODUCT_DESC()+"'," +
						    			    		"'"+item1.getSUGAR()+"'," +
						    			    		"'"+item1.getCO2()+"'," +
						    			    		"'"+item1.getDEGREE()+"'," +
						    			    		"'"+item1.getPRICE()+"'," +
						    			    		"'"+item1.getSIZES()+"'," +
						    			    		"'"+item1.getSIZES_UNIT()+"'," +
						    			    		"'"+item1.getQUANTITY()+"'," +
						    			    		"'"+item1.getQUANTITY_UNIT()+"'," +
						    			    		"'"+item1.getVOLUMN()+"'," +
						    			    		"'"+item1.getVOLUMN_UNIT()+"'," +
						    			    		"'"+item1.getREMARK()+"'," +
						    			    		"'"+item1.getPRODUCT_RESULT()+"'," +
						    			    		"'"+item1.getSCIENCE_RESULT_DESC()+"'," +
						    			    		"'"+item1.getVAT()+"'," +
						    			    		"'"+item1.getIS_DOMESTIC()+"'," +
						    			    		"'"+item1.getIS_ILLEGAL()+"'," +
						    			    		"'"+item1.getIS_SCIENCE()+"'," +
						    			    		"1)");
						    				log.info("[SQL] : "+sqlBuilderSub1.toString());
						    				
						    		getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[] {});
						    		list1.add(obj1);
						    	}
						    }
						    obj.setArrestProductMapping(list1);
					    }
			    		
			    		list.add(obj);
			    	}
			    }
			    res.setArrestProduct(list);
		    }
			
			res.setIsSuccess(Message.TRUE);
		    res.setMsg(Message.COMPLETE);
		    
		    return res;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setIsSuccess(Message.FALSE);
		    res.setMsg(e.getMessage());
		    res.setARREST_ID(0);
		    res.setArrestLawbreaker(null);
		    res.setArrestLocale(null);
		    res.setArrestProduct(null);
		    res.setArrestStaff(null);
			return res;
		}
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
	public Boolean ArrestupdByCon(Arrest req) {
		

		StringBuilder sqlBuilder = new StringBuilder()
		.append("UPDATE OPS_ARREST "
				+ "SET OFFICE_ID =  '"+req.getOFFICE_ID()+"', "
				+ " ARREST_CODE = '"+req.getARREST_CODE()+"', "
				+ " OFFICE_CODE = '"+req.getOFFICE_CODE()+"', "
				+ " OFFICE_NAME =  '"+req.getOFFICE_NAME()+"', "
				+ " ARREST_DATE = TO_TIMESTAMP_TZ('"+req.getARREST_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ " OCCURRENCE_DATE = TO_TIMESTAMP_TZ('"+req.getOCCURRENCE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ " BEHAVIOR_1 =  '"+req.getBEHAVIOR_1()+"', "
				+ " BEHAVIOR_2 =  '"+req.getBEHAVIOR_2()+"', "
				+ " BEHAVIOR_3 =  '"+req.getBEHAVIOR_3()+"', "
				+ " BEHAVIOR_4 =  '"+req.getBEHAVIOR_4()+"', "
				+ " BEHAVIOR_5 =  '"+req.getBEHAVIOR_5()+"', "
				+ " TESTIMONY = '"+req.getTESTIMONY()+"', "
				+ " REQUEST_DESC = '"+req.getREQUEST_DESC()+"', "
				+ " IS_REQUEST = '"+req.getIS_REQUEST()+"', "
				+ " IS_LAWSUIT_COMPLETE = '"+req.getIS_LAWSUIT_COMPLETE()+"', "
				+ " IS_ACTIVE = '"+req.getIS_ACTIVE()+"', "
				+ " CREATE_DATE = TO_TIMESTAMP_TZ('"+req.getCREATE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ " CREATE_USER_ACCOUNT_ID = '"+req.getCREATE_USER_ACCOUNT_ID()+"', "
				+ " UPDATE_DATE = TO_TIMESTAMP_TZ('"+req.getUPDATE_DATE()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ " UPDATE_USER_ACCOUNT_ID = '"+req.getUPDATE_USER_ACCOUNT_ID()+"'"
				+ " WHERE ARREST_ID = '"+req.getARREST_ID()+"' ");

		log.info("[SQL] : "+sqlBuilder.toString());
		getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		
		if(req.getArrestLocale() != null) {
	    	log.info("[Sub] Size : "+req.getArrestLocale().size());
	    	
		    if(req.getArrestLocale().size() > 0){
		    	for(ArrestLocale item : req.getArrestLocale()){
		    		
		    		StringBuilder sqlBuilderSub = new StringBuilder()
		    				.append("UPDATE OPS_ARREST_LOCALE "
		    						+ "SET "
		    						+ "ARREST_ID = '"+req.getARREST_ID()+"', "
		    						+ "SUB_DISTRICT_ID = '"+item.getSUB_DISTRICT_ID()+"', "
		    						+ "GPS = '"+item.getGPS()+"', "
		    						+ "ADDRESS_NO = '"+item.getADDRESS_NO()+"', "
		    						+ "VILLAGE_NO = '"+item.getVILLAGE_NO()+"', "
		    						+ "BUILDING_NAME = '"+item.getBUILDING_NAME()+"', "
		    						+ "ROOM_NO = '"+item.getROOM_NO()+"', "
		    						+ "FLOOR = '"+item.getFLOOR()+"', "
		    						+ "VILLAGE_NAME = '"+item.getVILLAGE_NAME()+"', "
		    						+ "ALLEY = '"+item.getALLEY()+"', "
		    						+ "LANE = '"+item.getLANE()+"', "
		    						+ "ROAD = '"+item.getROAD()+"', "
		    						+ "ADDRESS_TYPE = '"+item.getADDRESS_TYPE()+"', "
		    						+ "POLICE_STATION = '"+item.getPOLICE_STATION()+"', "
									+ "LOCATION = '"+item.getLOCATION()+"', "
		    						+ "ADDRESS_STATUS = '"+item.getADDRESS_STATUS()+"', "
		    						+ "IS_ACTIVE = '"+item.getIS_ACTIVE()+"' "
		    						+ "WHERE LOCALE_ID = '"+item.getLOCALE_ID()+"' ");
		    				log.info("[SQL] : "+sqlBuilderSub.toString());
		    				
		    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
		    	}
		    }
	    }
		
		return true;
	}

	@Override
	public Boolean ArrestupdDelete(ArrestupdDeleteReq req) {
		
		StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_ARREST SET IS_ACTIVE = '0' WHERE ARREST_ID = '"+req.getARREST_ID()+"' ");
		StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_ARREST_STAFF SET IS_ACTIVE = '0' WHERE ARREST_ID = '"+req.getARREST_ID()+"' ");
		StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_ARREST_LOCALE SET IS_ACTIVE = '0' WHERE ARREST_ID = '"+req.getARREST_ID()+"' ");
		StringBuilder sqlBuilder4 = new StringBuilder().append("UPDATE OPS_ARREST_LAWBREAKER SET IS_ACTIVE = '0' WHERE ARREST_ID = '"+req.getARREST_ID()+"' ");
		StringBuilder sqlBuilder5 = new StringBuilder().append("UPDATE OPS_ARREST_PRODUCT SET IS_ACTIVE = '0' WHERE ARREST_ID = '"+req.getARREST_ID()+"' ");
		StringBuilder sqlBuilder6 = new StringBuilder().append("UPDATE OPS_ARREST_PRODUCT_MAPPING SET IS_ACTIVE = '0' WHERE PRODUCT_ID in(select PRODUCT_ID from OPS_ARREST_PRODUCT where ARREST_ID = '"+req.getARREST_ID()+"')");
		StringBuilder sqlBuilder7 = new StringBuilder().append("UPDATE OPS_ARREST_INDICTMENT SET IS_ACTIVE = '0' WHERE ARREST_ID = '"+req.getARREST_ID()+"' ");
		StringBuilder sqlBuilder8 = new StringBuilder().append("UPDATE OPS_ARREST_INDICTMENT_PRODUCT SET IS_ACTIVE = '0' WHERE INDICTMENT_ID in(select INDICTMENT_ID from OPS_ARREST_INDICTMENT where ARREST_ID = '"+req.getARREST_ID()+"')");
		StringBuilder sqlBuilder9 = new StringBuilder().append("UPDATE OPS_ARREST_INDICTMENT_DETAIL SET IS_ACTIVE = '0' WHERE INDICTMENT_ID in(select INDICTMENT_ID from OPS_ARREST_INDICTMENT where ARREST_ID = '"+req.getARREST_ID()+"')");
		
		getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
		getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
		getJdbcTemplate().update(sqlBuilder3.toString(), new Object[] {});
		getJdbcTemplate().update(sqlBuilder4.toString(), new Object[] {});
		getJdbcTemplate().update(sqlBuilder5.toString(), new Object[] {});
		getJdbcTemplate().update(sqlBuilder6.toString(), new Object[] {});
		getJdbcTemplate().update(sqlBuilder7.toString(), new Object[] {});
		getJdbcTemplate().update(sqlBuilder8.toString(), new Object[] {});
		getJdbcTemplate().update(sqlBuilder9.toString(), new Object[] {});
		return true;
	}
}
