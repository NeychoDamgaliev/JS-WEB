package registerApplicaion.services;

import org.modelmapper.ModelMapper;
import registerApplicaion.domain.entities.Employee;
import registerApplicaion.domain.models.service.EmployeeServiceModel;
import registerApplicaion.domain.models.views.EmployeeListViewModel;
import registerApplicaion.repository.EmployeeRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/10/2019.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean saveEmployee(EmployeeServiceModel esm) {
        try {
            this.employeeRepository.save(
                    this.modelMapper.map(esm, Employee.class)
            );
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<EmployeeServiceModel> findAllEmployees() {
        return this.employeeRepository.findAll().stream()
                .map(e -> this.modelMapper.map(e, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeEmployee(String id) {
        try {
            this.employeeRepository.remove(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
