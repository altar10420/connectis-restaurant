package pl.connectis.restaurant.controller.dto;

import pl.connectis.restaurant.domain.EmployeeHibernate;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeDTO implements Serializable {
    private Long id;

    private String name;

    private String surname;

    private String position;

    private BigDecimal salary;

    private Long managerId;

    private Long pesel;

    public EmployeeDTO(){
    }

    public EmployeeDTO(EmployeeHibernate employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.position = employee.getPosition();
        this.salary = employee.getSalary();
        this.pesel = employee.getPesel();
        this.managerId = employee.getManagerId();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

}
