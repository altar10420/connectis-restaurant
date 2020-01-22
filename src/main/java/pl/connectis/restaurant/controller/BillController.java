package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.BillDTO;
import pl.connectis.restaurant.domain.BillHibernate;
import pl.connectis.restaurant.service.BillService;

import java.util.Optional;

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
        Optional<BillHibernate> billOptional = billService.getBill(id);
        //TODO throw some exception if failed
        return new BillDTO(billOptional.get());
    }

}
