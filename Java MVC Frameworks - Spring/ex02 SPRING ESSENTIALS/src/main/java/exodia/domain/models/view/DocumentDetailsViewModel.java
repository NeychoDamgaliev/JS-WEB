package exodia.domain.models.view;

/**
 * Created by Neycho Damgaliev on 3/6/2019.
 */
public class DocumentDetailsViewModel {
    private String id;
    private String title;
    private String content;

    public DocumentDetailsViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
