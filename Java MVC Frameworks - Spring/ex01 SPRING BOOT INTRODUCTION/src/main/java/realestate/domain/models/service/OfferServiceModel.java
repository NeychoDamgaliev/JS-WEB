package realestate.domain.models.service;


import org.springframework.lang.NonNull;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * Created by Neycho Damgaliev on 3/4/2019.
 */
public class OfferServiceModel {

    private String id;
    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;

    public OfferServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    @DecimalMin("0.0001")
    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    @NonNull
    @NotEmpty
    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    @NonNull
    @DecimalMin("0")
    @DecimalMax("100")
    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }

}
