package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.restaurant.domain.EmployeeHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.EmployeeHibernateRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
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
    public Optional<EmployeeHibernate> getEmployee(Long id) {
        return employeeHibernateRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<EmployeeHibernate> getAllEmployee(Pageable pageable) {
        Page<EmployeeHibernate> page = employeeHibernateRepository.findAll(pageable);
        List<EmployeeHibernate> hibernates = page.getContent();
        return hibernates.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateEmployee(Long id, String name, String surname, String position, BigDecimal salary, Long pesel, Long managerId){
        Optional<EmployeeHibernate> optionalEmployeeHibernate = employeeHibernateRepository.findById(id);
        if (!optionalEmployeeHibernate.isPresent()){
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

    public EmployeeHibernate toDomain(EmployeeHibernate hibernate) {
        return new EmployeeHibernate(
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
