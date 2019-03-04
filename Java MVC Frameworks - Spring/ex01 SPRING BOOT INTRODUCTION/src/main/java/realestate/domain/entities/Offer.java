package realestate.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Neycho Damgaliev on 3/3/2019.
 */
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;

    public Offer() {
    }

    @Column(name = "appartment_rent")
    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    @Column(name = "appartment_type")
    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    @Column(name = "agency_commission")
    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
