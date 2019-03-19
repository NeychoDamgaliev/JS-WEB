package residentevil.web.domain.models.binding;

import org.springframework.format.annotation.DateTimeFormat;

import residentevil.web.domain.entities.Capital;
import residentevil.web.domain.entities.Virus;
import residentevil.web.domain.enums.Creator;
import residentevil.web.domain.enums.Magnitude;
import residentevil.web.domain.enums.Mutation;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.metamodel.Bindable;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */
public class VirusBindingModel {

    private String name;
    private String description;
    private String sideEffects;
    private Creator creator;
    private Boolean isDeadly;
    private Boolean isCurable;
    private Mutation mutation;
    private Integer turnoverRate;
    private Integer hoursUntilTurn;
    private Magnitude magnitude;
    private LocalDate releasedOn;
        private List<String> capitals;
//    private List<CapitalBindingModel> capitals;

    public VirusBindingModel() {
    }

    @NotNull
    @Size(min = 3, max = 10, message = "Invalid Name!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 5, max = 100, message = "Invalid Description!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Size(max = 50, message = "Maximum of 50 symbols allowed!")
    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @NotNull(message = "Can't be empty!")
    @Enumerated(EnumType.STRING)
    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Boolean getDeadly() {
        return isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getCurable() {
        return isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @NotNull(message = "Mutation cannot be null")
    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @NotNull(message = "Invalid turnover rate")
    @Min(value = 0, message = "Invalid turnover rate")
    @Max(value = 100, message = "Invalid turnover rate")
    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @NotNull(message = "Invalid hours until turn")
    @Min(value = 1, message = "Invalid hours until turn")
    @Max(value = 12, message = "Invalid hours until turn")
    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    @NotNull(message = "You must select magnitude")
    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent()
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }


//    @NotNull(message = "You must select capitals")
//    @NotEmpty(message = "You must select capitals")
//    public List<CapitalBindingModel> getCapitals() {
//        return capitals;
//    }

//    public void setCapitals(List<CapitalBindingModel> capitals) {
//        this.capitals = capitals;
//    }


        @NotNull(message = "You must select capitals")
    @NotEmpty(message = "You must select capitals")
    public List<String> getCapitals() {
        return capitals;
    }
//
    public void setCapitals(List<String> capitals) {
        this.capitals = capitals;
    }


}
