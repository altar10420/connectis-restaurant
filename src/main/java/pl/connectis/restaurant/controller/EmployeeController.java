package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.connectis.restaurant.controller.dto.EmployeeDTO;
import pl.connectis.restaurant.domain.EmployeeHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.EmployeeHibernateRepository;
import pl.connectis.restaurant.service.EmployeeService;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeHibernateRepository employeeHibernateRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeHibernateRepository employeeHibernateRepository) {
        this.employeeService = employeeService;
        this.employeeHibernateRepository = employeeHibernateRepository;
    }

    @GetMapping(path = "/{id}")
    public EmployeeDTO getEmployee(@PathVariable("id") Long id) {
        Optional<EmployeeHibernate> employeeOptional = employeeService.getEmployee(id);
        //TODO throw some exception if failed to get
        return new EmployeeDTO(employeeOptional.get());
    }

    @PostMapping(path = "/")
    public Long createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Long employeeId = employeeService.createEmployee(
                employeeDTO.getName(),
                employeeDTO.getSurname(),
                employeeDTO.getPosition(),
                employeeDTO.getSalary(),
                employeeDTO.getPesel(),
                employeeDTO.getManagerId()
        );
        return employeeId;
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO) {
        Optional<EmployeeHibernate> employeeHibernateOptional = employeeHibernateRepository.findById(id);
        EmployeeHibernate employeeHibernate = employeeHibernateOptional.get();

//        if (!employeeHibernateOptional.isPresent()){
//            throw new EntityDoesNotExistException();
//        }


        employeeHibernate.setName(employeeDTO.getName());
        employeeHibernate.setSurname(employeeDTO.getSurname());
        employeeHibernate.setPosition(employeeDTO.getPosition());
        employeeHibernate.setSalary(employeeDTO.getSalary());
        employeeHibernate.setManagerId(employeeDTO.getManagerId());
        employeeHibernate.setPesel(employeeDTO.getPesel());

//        return new ResponseEntity<>(employeeHibernateRepository.save(employeeHibernate), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public String removeEmployee(@PathVariable("id") Long id) {
        employeeService.removeEmployee(id);
        //TODO throw some message/exception if failed
        return "REMOVED";
    }
}
