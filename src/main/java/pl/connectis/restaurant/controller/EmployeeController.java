package pl.connectis.restaurant.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.EmployeeDTO;
import pl.connectis.restaurant.domain.EmployeeHibernate;
import pl.connectis.restaurant.repository.EmployeeHibernateRepository;
import pl.connectis.restaurant.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping(path = "/menu/{page}")
    public List<EmployeeDTO> getEmployeeMenuPage(@PathVariable("page") Integer page) {
        List<EmployeeHibernate> employeeList = employeeService.getEmployeeMenuPage(page);
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (EmployeeHibernate employee : employeeList) {
            employeeDTOList.add(new EmployeeDTO(employee));
        }
        return employeeDTOList;
    }

    @PostMapping(path = "/")
    public Long createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Long employeeId = employeeService.createEmployee(
                employeeDTO.getName(),
                employeeDTO.getSurname(),
                employeeDTO.getPosition(),
                employeeDTO.getSalary(),
                employeeDTO.getManagerId(),
                employeeDTO.getPesel()
        );
        return employeeId;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeHibernate> updateDrink(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO){
        Optional<EmployeeHibernate> employeeHibernateOptional = employeeHibernateRepository.findById(id);
        EmployeeHibernate _employeeHibernate = employeeHibernateOptional.get();
        if(employeeHibernateOptional.isPresent()){
            _employeeHibernate.setName(employeeDTO.getName());
            _employeeHibernate.setSurname(employeeDTO.getSurname());
            _employeeHibernate.setPosition(employeeDTO.getPosition());
            _employeeHibernate.setSalary(employeeDTO.getSalary());
            _employeeHibernate.setManagerId(employeeDTO.getManagerId());
            _employeeHibernate.setPesel(employeeDTO.getPesel());
        }
        return new ResponseEntity<>(employeeHibernateRepository.save(_employeeHibernate), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public String removeEmployee(@PathVariable("id") Long id) {
        employeeService.removeEmployee(id);
        //TODO throw some message/exception if failed
        return "REMOVED";
    }
}
