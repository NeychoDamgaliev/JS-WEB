package chushka.domain.models.view;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */
public class ProductDetailsViewModel {

    private String name;
    private String description;
    private String type;

    public ProductDetailsViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
