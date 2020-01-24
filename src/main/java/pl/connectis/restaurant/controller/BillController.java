package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.BillDTO;
import pl.connectis.restaurant.controller.dto.ClientDTO;
import pl.connectis.restaurant.domain.BillHibernate;
import pl.connectis.restaurant.service.BillService;
import pl.connectis.restaurant.service.ClientService;
import pl.connectis.restaurant.service.EmployeeService;

import java.util.Optional;

@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillService billService;
    //TODO is this ok to have Client and Employee service in BillController?
    private final ClientService clientService;
    private final EmployeeService employeeService;

    @Autowired
    public BillController(BillService billService,
                          ClientService clientService,
                          EmployeeService employeeService) {
        this.billService = billService;
        this.clientService = clientService;
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{id}")
    public BillDTO getBill(@PathVariable("id") Long id) {
        Optional<BillHibernate> billOptional = billService.getBill(id);
        //TODO throw some exception if failed
        return new BillDTO(billOptional.get());
    }

    @PostMapping(path = "/create")
    public Long createBill(@RequestParam(name = "employee") Long employeeId,
                        @RequestParam(name = "client") Long clientId) {

        Long billId = billService.createBill(
                null,
                null,
                null,
                null,
                null,
                clientId,
                employeeId
        );

        //TODO throw some message/exception if failed
        return billId;
    }

    @PostMapping(path = "/{id}/addDish")
    public Long addDish(@PathVariable("id") Long id,
                        @RequestParam(name = "dish") Long dishId) {

        Long billId = billService.addDish(id, dishId);

        return billId;
    }

}
