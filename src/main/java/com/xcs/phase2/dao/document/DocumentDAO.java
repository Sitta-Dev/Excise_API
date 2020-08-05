package com.xcs.phase2.dao.document;


import com.xcs.phase2.model.document.Document;
import com.xcs.phase2.request.document.DocumentupdDeleteReq;
import com.xcs.phase2.request.document.GetDocumentByConReq;
import com.xcs.phase2.response.document.DocumentinsAllResponse;

import java.util.List;

public interface DocumentDAO {

    public List<Document> GetImagePerson();
    public List<Document> GetDocumentByCon(GetDocumentByConReq req);
    public DocumentinsAllResponse DocumentinsAll(Document req);
    public Boolean DocumentupdDelete(DocumentupdDeleteReq req);
    public String getPath(String documentId);
}
