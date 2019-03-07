package exodia.domain.models.view;

/**
 * Created by Neycho Damgaliev on 3/6/2019.
 */
public class DocumentHomeViewModel {

    private String id;
    private String title;

    public DocumentHomeViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        if(this.title.length()>=12) {
            return this.title.substring(0,12)+"...";
        }
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
