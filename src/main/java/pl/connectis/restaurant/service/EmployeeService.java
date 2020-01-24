package pl.connectis.restaurant.service;

import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.EmployeeHibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeHibernate createEmployee(
            String name,
            String surname,
            String position,
            BigDecimal salary,
            Long pesel,
            Long managerId
    );

    List<EmployeeHibernate> getEmployeeMenuPage(Integer page);

    Optional<EmployeeHibernate> getEmployee(Long id);

    List<EmployeeHibernate> getAllEmployee(Pageable pageable);

    void removeEmployee(Long id);
}
