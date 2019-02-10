package registerApplicaion.repository;

import registerApplicaion.domain.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/10/2019.
 */
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Employee save(Employee employee) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(employee);
        this.entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public List<Employee> findAll() {

        this.entityManager.getTransaction().begin();

            List<Employee> employees =
                    this.entityManager.createQuery("SELECT e FROM Employee e ", Employee.class)
                    .getResultList();
        this.entityManager.getTransaction().commit();

        return employees;
    }

    @Override
    public Employee findById(String id) {
        this.entityManager.getTransaction().begin();

        List<Employee> employee =
                this.entityManager.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class)
                .setParameter("id",id)
                .getResultList();
        this.entityManager.getTransaction().commit();

        if(employee.size()!=0) {
            return employee.get(0);
        }
        return null;
    }

    @Override
    public void remove(String id) {
        Employee employee = this.findById(id);
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(employee);
        this.entityManager.getTransaction().commit();

    }


}
