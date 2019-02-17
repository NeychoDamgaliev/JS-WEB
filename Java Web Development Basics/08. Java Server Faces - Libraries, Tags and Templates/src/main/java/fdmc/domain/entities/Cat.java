package fdmc.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Neycho Damgaliev on 2/16/2019.
 */
@Entity
@Table(name = "cats")
public class Cat extends BaseEntity {
    private String name;
    private String breed;
    private String color;
    private Integer age;
    private String gender;
    private BigDecimal price;
    private Date addedOn;
    private Boolean hasPassport;

    public Cat() {
    }

    // name - String, must contain at least 2 characters, shouldn’t be longer than 10.
    @Column(name = "name", nullable = false)
    @Length(min = 2, max = 10)
    public String getName() {
        return name;
    }

    //  breed - String, must contain at least 5 characters, shouldn’t be longer than 20.
    @Column(name = "breed", nullable = false)
    @Length(min = 5, max = 20)
    public String getBreed() {
        return breed;
    }

    // color – a String.
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    // age – an Integer, must be between 1 and 31.
    @Column(name = "age", nullable = false)
    @Min(value = 1)
    @Max(value = 31)
    public Integer getAge() {
        return age;
    }

    // gender – a String.
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    // price – a decimal value, must be at least 0.01.
    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.01")
    public BigDecimal getPrice() {
        return price;
    }

    // addedOn – a date without time.
    @Column(name = "added_on",nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getAddedOn() {
        return addedOn;
    }

    // hasPassport – a boolean value.
    @Column(name = "has_passport", columnDefinition = "boolean default false", nullable = false)
    public Boolean getHasPassport() {
        return hasPassport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public void setHasPassport(Boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}
