package registerApplicaion.web.mbeans;

import org.modelmapper.ModelMapper;
import registerApplicaion.domain.models.views.EmployeeListViewModel;
import registerApplicaion.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/10/2019.
 */

@Named
@RequestScoped
public class EmployeesListMB {

    private List<EmployeeListViewModel> employees;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeesListMB() {
    }

    @Inject
    public EmployeesListMB(EmployeeService employeeService, ModelMapper modelMapper) {

        this.employeeService = employeeService;
        this.modelMapper = modelMapper;

        this.employees = this.employeeService.findAllEmployees()
                .stream()
                .map(e->this.modelMapper.map(e,EmployeeListViewModel.class))
                .collect(Collectors.toList());
    }

    public BigDecimal totalMoneyNeeded() {
        BigDecimal sum = this.employees.stream()
                .map(e->e.getSalary())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum;
    }
    public BigDecimal averageSalary() {
        BigDecimal salarySum = this.employees.stream()
                .map(e->e.getSalary())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if(employees.size() != 0) {
            return salarySum.divide(BigDecimal.valueOf(employees.size()));
        }
        return BigDecimal.ZERO;
    }
    public List<EmployeeListViewModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeListViewModel> employees) {
        this.employees = employees;
    }
}
