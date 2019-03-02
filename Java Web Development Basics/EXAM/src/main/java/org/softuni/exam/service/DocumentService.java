package org.softuni.exam.service;

import org.softuni.exam.domain.models.service.DocumentServiceModel;
import org.softuni.exam.domain.models.service.UserServiceModel;

import java.util.List;

public interface DocumentService {

    DocumentServiceModel getDocumentById(String id);

    DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel);

    List<DocumentServiceModel> findAll();

    void printDocument(String id);
}
