package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.binding.DocumentCreateBindingModel;
import org.softuni.exam.domain.models.service.DocumentServiceModel;
import org.softuni.exam.domain.models.views.DocumentViewModel;
import org.softuni.exam.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Neycho Damgaliev on 2/24/2019.
 */
@Named(value = "documentDetailsBean")
@RequestScoped
public class DocumentDetailsBean extends BaseBean {

    private DocumentViewModel document;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentDetailsBean() {
    }

    @Inject
    public DocumentDetailsBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        String id = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");

        DocumentServiceModel documentById = this.documentService.getDocumentById(id);
        if (documentById == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        this.document = this.modelMapper.map(documentById,DocumentViewModel.class);
    }

    public DocumentViewModel getDocument() {
        return document;
    }

    public void setDocument(DocumentViewModel document) {
        this.document = document;
    }
}
