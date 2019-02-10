package registerApplicaion.domain.models.binding;

import java.math.BigDecimal;

/**
 * Created by Neycho Damgaliev on 2/10/2019.
 */
public class EmployeeRegisterBM {

    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;
    private Integer age;


    public EmployeeRegisterBM() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
