package pl.connectis.restaurant.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.domain.model.Employee;
import pl.connectis.restaurant.domain.service.EmployeeRepository;
import pl.connectis.restaurant.infrastructure.entity.EmployeeHibernate;
import pl.connectis.restaurant.infrastructure.repository.EmployeeHibernateRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeHibernateRepository employeeHibernateRepository;

    @Autowired
    public EmployeeRepositoryImpl(EmployeeHibernateRepository employeeHibernateRepository) {
        this.employeeHibernateRepository = employeeHibernateRepository;
    }

    @Override
    public Employee createEmployee(Long id,
                                   String name,
                                   String surname,
                                   String position,
                                   String salary,
                                   Long managerId,
                                   BigInteger pesel) {
        EmployeeHibernate employeeHibernate = new EmployeeHibernate(
                null,
                name,
                surname,
                position,
                salary,
                managerId,
                pesel
        );

        EmployeeHibernateRepository.save(employeeHibernate);
        return toDomain(employeeHibernate);
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeHibernateRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Employee> getAllEmployee(Pageable pageable) {
        Page<EmployeeHibernate> page = employeeHibernateRepository.findAll(pageable);
        List<EmployeeHibernate> hibernates = page.getContent();
        return hibernates.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getEmployeeMenuPage(int page) {
        Page<EmployeeHibernate> employeeList = employeeHibernateRepository.findAll(PageRequest.of(page, 10));
        List<EmployeeHibernate> hibernates = employeeList.getContent();

        return hibernates.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void removeEmployee(Long id) {
        employeeHibernateRepository.deleteById(id);
    }

    public Employee toDomain(EmployeeHibernate hibernate) {
        return new Employee(
                hibernate.getId(),
                hibernate.getName(),
                hibernate.getSurname(),
                hibernate.getPosition(),
                hibernate.getSalary(),
                hibernate.getManagerId(),
                hibernate.getPesel()
        );
    }
}
