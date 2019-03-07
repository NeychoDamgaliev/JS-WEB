package exodia.web.controllers;

import exodia.domain.models.binding.DocumentScheduleBindingModel;
import exodia.domain.models.service.DocumentServiceModel;
import exodia.domain.models.view.DocumentDetailsViewModel;
import exodia.domain.models.view.DocumentPrintViewModel;
import exodia.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Neycho Damgaliev on 3/6/2019.
 */

@Controller
public class DocumentController {

    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentController(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/schedule")
    public ModelAndView schedule(ModelAndView modelAndView, HttpSession session) {

        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("redirect:/login");
        } else {
            modelAndView.setViewName("schedule");
        }
        return modelAndView;
    }

    @PostMapping("/schedule")
    public ModelAndView scheduleConfirm(@ModelAttribute("model") DocumentScheduleBindingModel model,
                                        ModelAndView modelAndView) {
        DocumentServiceModel documentServiceModel = this.documentService.scheduleDocument(
                this.modelMapper.map(model, DocumentServiceModel.class)
        );
        if (documentServiceModel == null) {
            throw new IllegalArgumentException("Document creation failed!");
        }

        modelAndView.setViewName("redirect:/details/" + documentServiceModel.getId());

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") String id, ModelAndView modelAndView,
                                HttpSession session) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("redirect:/login");
        } else {

            DocumentServiceModel documentServiceModel = this.documentService.findDocumentById(id);

            if (documentServiceModel == null) {
                throw new IllegalArgumentException("Document not found!");
            }
            modelAndView.setViewName("details");
            modelAndView.addObject("document", this.modelMapper.map(documentServiceModel, DocumentDetailsViewModel.class));
        }

        return modelAndView;
    }

    @GetMapping("/print/{id}")
    public ModelAndView print(@PathVariable("id") String id, ModelAndView modelAndView,
                              HttpSession session) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("redirect:/login");
        } else {
            DocumentPrintViewModel documentPrintViewModel = this.modelMapper.map(
                    this.documentService.findDocumentById(id), DocumentPrintViewModel.class);
            if(documentPrintViewModel==null) {
                throw new IllegalArgumentException("Document not found!");
            }
            modelAndView.addObject("model", documentPrintViewModel);
            modelAndView.setViewName("print");
        }
        return modelAndView;
    }

    @PostMapping("/print/{id}")
    public ModelAndView printConfirm(@PathVariable("id") String id, ModelAndView modelAndView,
                                     HttpSession session) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("redirect:/login");
        } else {
             if(!this.documentService.printDocumentById(id)){
                 throw new IllegalArgumentException("Document cannot be printed!");
             }
        }
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }
}
