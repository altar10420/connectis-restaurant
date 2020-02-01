package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.restaurant.domain.EmployeeHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.EmployeeHibernateRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeHibernateRepository employeeHibernateRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeHibernateRepository employeeHibernateRepository) {
        this.employeeHibernateRepository = employeeHibernateRepository;
    }

    @Override
    public Long createEmployee(String name,
                               String surname,
                               String position,
                               BigDecimal salary,
                               Long pesel,
                               Long managerId) {
        EmployeeHibernate employeeHibernate = new EmployeeHibernate(
                null,
                name,
                surname,
                position,
                salary,
                pesel,
                managerId
        );

        employeeHibernateRepository.save(employeeHibernate);
        return employeeHibernate.getId();
    }

    @Override
    public EmployeeHibernate getEmployee(Long id) {
        return employeeHibernateRepository.getById(id);
    }

    @Override
    public List<EmployeeHibernate> getAllEmployees() {

        Iterable<EmployeeHibernate> employees = employeeHibernateRepository.findAll();

        List<EmployeeHibernate> employeeList = new ArrayList<>();

        for (EmployeeHibernate employee : employees) {
            employeeList.add(employee);
        }

        return employeeList;
    }

    @Override
    @Transactional
    public void updateEmployee(Long id, String name, String surname, String position, BigDecimal salary, Long pesel, Long managerId) {
        Optional<EmployeeHibernate> optionalEmployeeHibernate = employeeHibernateRepository.findById(id);
        if (!optionalEmployeeHibernate.isPresent()) {
            throw new EntityDoesNotExistException();
        }

        EmployeeHibernate employeeHibernate = optionalEmployeeHibernate.get();
        employeeHibernate.setName(name);
        employeeHibernate.setSurname(surname);
        employeeHibernate.setPosition(position);
        employeeHibernate.setSalary(salary);
        employeeHibernate.setPesel(pesel);
        employeeHibernate.setManagerId(managerId);

        employeeHibernateRepository.save(employeeHibernate);
    }


    @Override
    public void removeEmployee(Long id) {
        employeeHibernateRepository.deleteById(id);
    }

}
