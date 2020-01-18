package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.DishDTO;
import pl.connectis.restaurant.domain.model.Dish;
import pl.connectis.restaurant.domain.service.DishService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping(path = "/{id}")
    public DishDTO getDish(@PathVariable("id") Long id) {
        Optional<Dish> dishOptional = dishService.getDish(id);
        //TODO throw some exception if failed to get
        return new DishDTO(dishOptional.get());
    }

    @PostMapping(path = "/")
    public Long createDish(@RequestBody DishDTO dishDTO) {
        Long dishId = dishService.createDish(
                dishDTO.getName(),
                dishDTO.getDescription(),
                dishDTO.getPrice(),
                dishDTO.getAvailable()
        );
        return dishId;
    }
}
