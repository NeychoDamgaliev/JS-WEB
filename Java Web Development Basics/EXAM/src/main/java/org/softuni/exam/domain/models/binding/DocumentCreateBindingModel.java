package org.softuni.exam.domain.models.binding;

/**
 * Created by Neycho Damgaliev on 2/24/2019.
 */
public class DocumentCreateBindingModel {

    private String title;

    private String content;

    public DocumentCreateBindingModel() {
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
