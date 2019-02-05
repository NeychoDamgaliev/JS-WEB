package meTube.domain.models.views;

/**
 * Created by Neycho Damgaliev on 2/3/2019.
 */
public class TubeDetailsVM {

    private String title;
    private String description;
    private String youTubeLink;
    private String uploader;

    public TubeDetailsVM() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
