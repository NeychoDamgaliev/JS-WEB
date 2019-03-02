package org.softuni.exam.web.beans;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.service.DocumentServiceModel;
import org.softuni.exam.domain.models.views.DocumentViewModel;
import org.softuni.exam.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Created by Neycho Damgaliev on 2/24/2019.
 */
@Named(value = "documentPrintBean")
@RequestScoped
public class DocumentPrintBean extends BaseBean {

    private DocumentViewModel document;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentPrintBean() {
    }

    @Inject
    public DocumentPrintBean(DocumentService documentService, ModelMapper modelMapper) {
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

    public void print(String id) {
        printToPdf();

        if(id!=null && !"".equals(id)) {
            this.documentService.printDocument(id);
        }

        this.redirect("/home");
    }


    private void printToPdf(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request =
                    (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response =
                    (HttpServletResponse)context.getExternalContext().getResponse();
            ByteArrayOutputStream pdfOutputStream =
                    new ByteArrayOutputStream();


            Document document =
                    new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, pdfOutputStream);

            document.addTitle("doc export");
            document.addAuthor("SoftUni Exam");

            document.open();

            document.add(new Paragraph("Title: " + this.document.getTitle()+System.lineSeparator(),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));

            Paragraph paragraph1 = new Paragraph();
            Chunk text = new Chunk("Content: " + this.document.getContent(),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));

            paragraph1.add(text);
            document.add(paragraph1);
           document.close();


            // Header
            response.setHeader("Expires", "0");
            response.setHeader("Content-Disposition", "attachment; filename=doc.pdf");
            response.setContentType("application/pdf");


            response.setContentLength(pdfOutputStream.size());

            // Write the PDF
            ServletOutputStream responseOutputStream =
                    response.getOutputStream();
            responseOutputStream.write(pdfOutputStream.toByteArray());
            responseOutputStream.flush();
            responseOutputStream.close();

            this.redirect("/");
        } catch (Exception e) {
        }
    }
}
