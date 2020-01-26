package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.DishDTO;
import pl.connectis.restaurant.controller.dto.DrinkDTO;
import pl.connectis.restaurant.domain.DishHibernate;
import pl.connectis.restaurant.domain.DrinkHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.DishHibernateRepository;
import pl.connectis.restaurant.service.DishService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    private final DishHibernateRepository dishHibernateRepository;

    @Autowired
    public DishController(DishService dishService, DishHibernateRepository dishHibernateRepository) {
        this.dishService = dishService;
        this.dishHibernateRepository = dishHibernateRepository;
    }

    @GetMapping(path = "/{id}")
    public DishDTO getDish(@PathVariable("id") Long id) {
        if(!dishService.getDish(id).isPresent()) {
            throw new EntityDoesNotExistException();
        }
        return new DishDTO(dishService.getDish(id).get());
    }

    @GetMapping(path = "/menu/{page}")
    public List<DishDTO> getDishMenuPage(@PathVariable("page") Integer page) {
        List<DishHibernate> dishList = dishService.getDishMenuPage(page);
        List<DishDTO> dishDTOList = new ArrayList<>();

        for (DishHibernate dish : dishList) {
            dishDTOList.add(new DishDTO(dish));
        }
        return dishDTOList;
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

    @PutMapping("/{id}")

    public void updateDish(@PathVariable("id") Long id, @RequestBody DishDTO dishDTO){
        Optional<DishHibernate> dishOptional = dishHibernateRepository.findById(id);

        if(!dishOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }

        dishService.updateDish(id,
                dishDTO.getName(),
                dishDTO.getDescription(),
                dishDTO.getPrice(),
                dishDTO.getAvailable());
    }

    @DeleteMapping(path = "/remove/{id}")
    public void removeDish(@PathVariable("id") Long id) {

        Optional<DishHibernate> dishOptional = dishHibernateRepository.findById(id);

        if(!dishOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }

        dishService.removeDish(id);
    }
}
