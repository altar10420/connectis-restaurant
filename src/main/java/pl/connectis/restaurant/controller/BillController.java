package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.BillDTO;
import pl.connectis.restaurant.service.BillService;


@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping(path = "/{id}")
    public BillDTO getBill(@PathVariable("id") Long id) {

        return new BillDTO(billService.getBill(id).get());
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

        return billId;
    }

    @PostMapping(path = "/{id}/addDish")
    public Long addDish(@PathVariable("id") Long id,
                        @RequestParam(name = "dish") Long dishId) {

        Long billId = billService.addDish(id, dishId);

        return billId;
    }

    @PostMapping(path = "/{id}/addDrink")
    public Long addDrink(@PathVariable("id") Long id,
                        @RequestParam(name = "drink") Long drinkId) {

        Long billId = billService.addDrink(id, drinkId);

        return billId;
    }

    @DeleteMapping(path = "/{id}")
    public void removeBill(@PathVariable("id") Long id) {
        billService.removeBill(id);
    }

}
