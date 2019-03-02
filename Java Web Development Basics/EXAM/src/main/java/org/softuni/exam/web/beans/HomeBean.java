package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.views.DocumentHomeViewModel;
import org.softuni.exam.domain.models.views.DocumentViewModel;
import org.softuni.exam.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/24/2019.
 */
@Named(value = "homeBean")
@RequestScoped
public class HomeBean extends BaseBean {

    private List<DocumentHomeViewModel> documents;

    private ModelMapper modelMapper;

    private DocumentService documentService;

    public HomeBean() {
    }

    @Inject
    public HomeBean(ModelMapper modelMapper, DocumentService documentService) {
        this.modelMapper = modelMapper;
        this.documentService = documentService;
    }

    @PostConstruct
    private void init() {
        this.documents = this.documentService.findAll()
        .stream()
        .map(d->this.modelMapper.map(d,DocumentHomeViewModel.class))
        .collect(Collectors.toList());
    }

    public List<DocumentHomeViewModel> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentHomeViewModel> documents) {
        this.documents = documents;
    }

    public void print(String id) {
        this.redirect("/print/"+id);
    }

    public void details(String id) {
        this.redirect("/details/"+id);
    }
}
