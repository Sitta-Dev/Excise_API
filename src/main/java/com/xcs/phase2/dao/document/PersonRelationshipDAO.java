package com.xcs.phase2.dao.document;

import com.xcs.phase2.model.document.PersonRelationship;
import com.xcs.phase2.request.document.PersonRelationshipgetByPersonIdReq;

import java.util.List;

public interface PersonRelationshipDAO {

    public List<PersonRelationship> PersonRelationshipgetByPersonId(PersonRelationshipgetByPersonIdReq req);
}
