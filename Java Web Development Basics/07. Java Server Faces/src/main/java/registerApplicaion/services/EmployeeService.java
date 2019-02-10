package registerApplicaion.services;

import registerApplicaion.domain.models.service.EmployeeServiceModel;
import registerApplicaion.domain.models.views.EmployeeListViewModel;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/10/2019.
 */
public interface EmployeeService {

    boolean saveEmployee(EmployeeServiceModel esm);

    List<EmployeeServiceModel> findAllEmployees();

    boolean removeEmployee(String id);
}
