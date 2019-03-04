package realestate.domain.models.binding;

import org.springframework.lang.NonNull;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * Created by Neycho Damgaliev on 3/4/2019.
 */
public class OfferFindBindingModel {

    private BigDecimal familyBudget;
    private String familyApartmentType;
    private String familyName;

    public OfferFindBindingModel() {
    }

    @NonNull
    @DecimalMin("0.0001")
    public BigDecimal getFamilyBudget() {
        return familyBudget;
    }

    public void setFamilyBudget(BigDecimal familyBudget) {
        this.familyBudget = familyBudget;
    }

    @NonNull
    @NotEmpty
    public String getFamilyApartmentType() {
        return familyApartmentType;
    }

    public void setFamilyApartmentType(String familyApartmentType) {
        this.familyApartmentType = familyApartmentType;
    }

    @NonNull
    @NotEmpty
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
