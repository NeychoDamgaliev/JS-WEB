package org.softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.models.service.DocumentServiceModel;
import org.softuni.exam.repository.DocumentRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/24/2019.
 */
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentServiceModel getDocumentById(String id) {
        return this.modelMapper.map(
                this.documentRepository.findById(id),
                DocumentServiceModel.class);
    }

    @Override
    public DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel) {
        return this.modelMapper.map(
                this.documentRepository
                        .save(this.modelMapper.map(documentServiceModel, Document.class)),
                DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> findAll() {
       return this.documentRepository.findAll()
        .stream()
        .map(d->this.modelMapper.map(d,DocumentServiceModel.class))
        .collect(Collectors.toList());
    }

    @Override
    public void printDocument(String id) {
        this.documentRepository.deleteDocumentById(id);
    }
}
