package org.softuni.exam.domain.models.service;

import javax.validation.constraints.NotNull;

public class DocumentServiceModel {
    private String id;

    private String title;

    private String content;

    public DocumentServiceModel() {
    }

    @NotNull
    public String getId() {
        return id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    @NotNull
    public String getContent() {
        return content;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
