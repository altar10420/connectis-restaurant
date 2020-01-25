package pl.connectis.restaurant.service;

import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.EmployeeHibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Long createEmployee(
            String name,
            String surname,
            String position,
            BigDecimal salary,
            Long pesel,
            Long managerId
    );

    Optional<EmployeeHibernate> getEmployee(Long id);

    List<EmployeeHibernate> getAllEmployee(Pageable pageable);

    void updateEmployee(Long id, String name, String surname, String position, BigDecimal salary, Long pesel, Long managerId);

    void removeEmployee(Long id);
}
