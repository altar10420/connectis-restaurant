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

        if(!employeeService.getEmployee(id).isPresent()) {
            throw new EntityDoesNotExistException();
        }
        return new EmployeeDTO(employeeService.getEmployee(id).get());
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
        Optional<EmployeeHibernate> employeeOptional = employeeHibernateRepository.findById(id);

        if(!employeeOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }

        employeeService.updateEmployee(id,
                employeeDTO.getName(),
                employeeDTO.getSurname(),
                employeeDTO.getPosition(),
                employeeDTO.getSalary(),
                employeeDTO.getPesel(),
                employeeDTO.getManagerId());
    }

    @DeleteMapping(path = "/{id}")
    public void removeEmployee(@PathVariable("id") Long id) {

        Optional<EmployeeHibernate> employeeOptional = employeeHibernateRepository.findById(id);

        if(!employeeOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }
        employeeService.removeEmployee(id);
    }
}
