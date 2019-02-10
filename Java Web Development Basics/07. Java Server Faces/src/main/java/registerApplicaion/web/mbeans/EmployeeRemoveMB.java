package registerApplicaion.web.mbeans;

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
public class EmployeeRemoveMB {

    private EmployeeService employeeService;

    public EmployeeRemoveMB() {
    }

    @Inject
    public EmployeeRemoveMB(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void remove(String id) throws IOException {

        this.employeeService.removeEmployee(id);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}
