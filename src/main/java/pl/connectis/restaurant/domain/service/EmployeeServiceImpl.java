package pl.connectis.restaurant.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.domain.model.Drink;
import pl.connectis.restaurant.domain.model.Employee;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Long createEmployee(String name, String surname, String position, BigDecimal salary, Long pesel, Long managerId) {
        Employee employee = employeeRepository.createEmployee(
                name,
                surname,
                position,
                salary,
                pesel,
                managerId
        );
        return employee.getId();
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.getEmployee(id);
    }

    @Override
    public List<Employee> getAllEmployee(Pageable pageable) {
        return employeeRepository.getAllEmployee(pageable);
    }

    @Override
    public List<Employee> getEmployeeMenuPage (int page){
        return employeeRepository.getEmployeeMenuPage(page);
    }

    @Override
    public void removeEmployee(Long id) {

    }
}
