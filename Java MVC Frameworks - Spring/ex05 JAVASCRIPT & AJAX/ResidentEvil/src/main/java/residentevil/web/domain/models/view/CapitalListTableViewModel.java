package residentevil.web.domain.models.view;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */
public class CapitalListTableViewModel {

    private String id;
    private String name;
    private Double latitude;
    private Double longitude;

    public CapitalListTableViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
