package com.xcs.phase2.dao.master;

import com.xcs.phase2.model.master.MasLawGroupSubSectionRule;
import com.xcs.phase2.model.master.New_MasLawGroupSection;
import com.xcs.phase2.request.master.*;
import com.xcs.phase2.response.master.MasLawGroupSubSectionRuleinsAllResponse;

import java.util.List;

public interface MasLawGroupSubSectionRuleDAO {

    public List<MasLawGroupSubSectionRule> MasLawGroupSubSectionRulegetByKeyword(MasLawGroupSubSectionRulegetByKeywordReq req);
    public List<MasLawGroupSubSectionRule> MasLawGroupSubSectionRulegetByConAdv(MasLawGroupSubSectionRulegetByConAdvReq req);
    public MasLawGroupSubSectionRule MasLawGroupSubSectionRulegetByCon(MasLawGroupSubSectionRulegetByConReq req);
    public MasLawGroupSubSectionRuleinsAllResponse MasLawGroupSubSectionRuleinsAll(MasLawGroupSubSectionRule req);
    public Boolean MasLawGroupSubSectionRuleupdAll(MasLawGroupSubSectionRule req);
    public Boolean MasLawGroupSubSectionRuleupdDelete(MasLawGroupSubSectionRuleupdDeleteReq req);

    public List<New_MasLawGroupSection> New_MasLawGroupSubSectionRulegetByConAdv(New_MasLawGroupSubSectionRulegetByConAdvReq req);

}
