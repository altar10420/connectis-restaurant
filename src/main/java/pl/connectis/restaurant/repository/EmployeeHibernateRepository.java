package pl.connectis.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import pl.connectis.restaurant.domain.EmployeeHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;

public interface EmployeeHibernateRepository
        extends CrudRepository<EmployeeHibernate, Long> {

    default EmployeeHibernate getById(Long employeeId) {
        return findById(employeeId).orElseThrow(
                () -> new EntityDoesNotExistException("Employee id: " + employeeId + " not found"));
    }

}
