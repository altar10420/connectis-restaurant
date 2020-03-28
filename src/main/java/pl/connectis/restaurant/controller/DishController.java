package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.DishDTO;
import pl.connectis.restaurant.domain.DishHibernate;
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

        return new DishDTO(dishService.getDish(id));
    }

    @GetMapping(path = "/menu/{page}/{amountOnPage}")
    public List<DishDTO> getDishesByPage(@PathVariable("page") Integer page,
                                         @PathVariable("amountOnPage") Integer amountOnPage) {
        List<DishHibernate> dishList = dishService.getDishesByPage(page, amountOnPage);
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

    public void updateDish(@PathVariable("id") Long id, @RequestBody DishDTO dishDTO) {

        DishHibernate dishHibernate = dishHibernateRepository.getById(id);

        dishService.updateDish(id,
                dishDTO.getName(),
                dishDTO.getDescription(),
                dishDTO.getPrice(),
                dishDTO.getAvailable());
    }

    @DeleteMapping(path = "/remove/{id}")
    public void removeDish(@PathVariable("id") Long id) {

        Optional<DishHibernate> dishOptional = dishHibernateRepository.findById(id);

        if (!dishOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }

        dishService.removeDish(id);
    }
}
