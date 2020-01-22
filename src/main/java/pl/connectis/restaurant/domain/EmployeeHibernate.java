package pl.connectis.restaurant.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class EmployeeHibernate {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    private String position;

    private BigDecimal salary;

    private Long managerId;

    private Long pesel;

    //TODO check relation and its config
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillHibernate> bills;

    public EmployeeHibernate(Long id, String name, String surname, String position, BigDecimal salary, Long managerId, Long pesel) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.salary = salary;
        this.managerId = managerId;
        this.pesel = pesel;
    }

    public EmployeeHibernate() {

    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeHibernate employeeHibernate = (EmployeeHibernate) o;
        return Objects.equals(id, employeeHibernate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                ", salary='" + salary + '\'' +
                ", managerId=" + managerId +
                ", pesel=" + pesel +
                '}';
    }
}
