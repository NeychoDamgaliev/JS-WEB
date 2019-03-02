package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.binding.DocumentCreateBindingModel;
import org.softuni.exam.domain.models.service.DocumentServiceModel;
import org.softuni.exam.service.DocumentService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Neycho Damgaliev on 2/24/2019.
 */
@Named(value = "scheduleCreateBean")
@RequestScoped
public class ScheduleCreateBean extends BaseBean {

    private DocumentCreateBindingModel document;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public ScheduleCreateBean() {
    }

    @Inject
    public ScheduleCreateBean(DocumentCreateBindingModel document, DocumentService documentService, ModelMapper modelMapper) {
        this.document = document;
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    public DocumentCreateBindingModel getDocument() {
        return document;
    }

    public void setDocument(DocumentCreateBindingModel document) {
        this.document = document;
    }

    public void schedule() {
        DocumentServiceModel document = this.documentService.scheduleDocument(
                this.modelMapper.map(this.document,DocumentServiceModel.class)
        );

        if (document != null) {

                this.redirect("/details/" + document.getId());


        }
    }
}
