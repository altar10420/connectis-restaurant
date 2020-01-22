package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.DrinkDTO;
import pl.connectis.restaurant.domain.DrinkHibernate;
import pl.connectis.restaurant.service.DrinkService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drink")
public class DrinkController {

    private final DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping(path = "/{id}")
    public DrinkDTO getDish(@PathVariable("id") Long id) {
        Optional<DrinkHibernate> drinkOptional = drinkService.getDrink(id);
        //TODO throw some exception if failed to get
        return new DrinkDTO(drinkOptional.get());
    }

    @GetMapping(path = "/menu/{page}")
    public List<DrinkDTO> getDrinkMenuPage(@PathVariable("page") Integer page) {
        List<DrinkHibernate> drinkList = drinkService.getDrinkMenuPage(page);
        List<DrinkDTO> drinkDTOList = new ArrayList<>();

        for (DrinkHibernate drink : drinkList) {
            drinkDTOList.add(new DrinkDTO(drink));
        }
        return drinkDTOList;
    }

    @PostMapping(path = "/")
    public Long createDrink(@RequestBody DrinkDTO drinkDTO) {
        Long drinkId = drinkService.createDrink(
                drinkDTO.getName(),
                drinkDTO.getDescription(),
                drinkDTO.getPrice(),
                drinkDTO.getAvailable(),
                drinkDTO.getPortion_ml()
        );
        return drinkId;
    }
}

