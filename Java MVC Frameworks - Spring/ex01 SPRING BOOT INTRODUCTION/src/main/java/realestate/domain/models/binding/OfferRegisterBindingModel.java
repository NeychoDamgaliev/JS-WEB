package realestate.domain.models.binding;

import java.math.BigDecimal;

/**
 * Created by Neycho Damgaliev on 3/4/2019.
 */
public class OfferRegisterBindingModel {
    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;

    public OfferRegisterBindingModel() {
    }


    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
