package pl.connectis.restaurant.domain.service;

import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.model.Employee;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee createEmployee(
            String name,
            String surname,
            String position,
            BigDecimal salary,
            BigInteger pesel
    );

    List<Employee> getEmployeeMenuPage(int page);

    Optional<Employee> getEmployee(Long id);

    List<Employee> getAllEmployee(Pageable pageable);

    void removeEmployee(Long id);
}
