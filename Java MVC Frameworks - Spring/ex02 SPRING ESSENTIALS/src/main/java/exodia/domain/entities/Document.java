package exodia.domain.entities;

import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Neycho Damgaliev on 3/6/2019.
 */

@Entity
@Table(name = "documents")
public class Document extends BaseEntity {

    private String title;
    private String content;

    public Document() {
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content", nullable = false, columnDefinition = "text")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
