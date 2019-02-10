package registerApplicaion.web.mbeans;

import org.modelmapper.ModelMapper;
import registerApplicaion.domain.models.binding.EmployeeRegisterBM;
import registerApplicaion.domain.models.service.EmployeeServiceModel;
import registerApplicaion.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

/**
 * Created by Neycho Damgaliev on 2/10/2019.
 */

@Named
@RequestScoped
public class EmployeeRegisterMB {

    private EmployeeRegisterBM employeeRegisterBM;

    private EmployeeService employeeService;

    private ModelMapper modelMapper;

    public EmployeeRegisterMB() {
        this.employeeRegisterBM = new EmployeeRegisterBM();
    }

    @Inject
    public EmployeeRegisterMB(EmployeeService employeeService, ModelMapper modelMapper) {
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    public EmployeeRegisterBM getEmployeeRegisterBM() {
        return employeeRegisterBM;
    }

    public void setEmployeeRegisterBM(EmployeeRegisterBM employeeRegisterBM) {
        this.employeeRegisterBM = employeeRegisterBM;
    }

    public void register() throws IOException {

        this.employeeService.saveEmployee(
                this.modelMapper.map(
                        this.employeeRegisterBM, EmployeeServiceModel.class)
        );

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}
