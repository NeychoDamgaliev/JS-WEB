package exodia.service;

import exodia.domain.models.service.DocumentServiceModel;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 3/6/2019.
 */

public interface DocumentService {

    DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel);

    DocumentServiceModel findDocumentById(String id);

    List<DocumentServiceModel> findAllDocuments();

    boolean printDocumentById(String id);
}
