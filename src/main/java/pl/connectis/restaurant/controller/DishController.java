package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.DishDTO;
import pl.connectis.restaurant.domain.DishHibernate;
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
        Optional<DishHibernate> dishOptional = dishService.getDish(id);
        //TODO throw some exception if failed
        return new DishDTO(dishOptional.get());
    }

    @GetMapping(path = "/menu/{page}")
    public List<DishDTO> getDishMenuPage(@PathVariable("page") Integer page) {
        List<DishHibernate> dishList = dishService.getDishMenuPage(page);
        List<DishDTO> dishDTOList = new ArrayList<>();

        for (DishHibernate dish : dishList) {
            dishDTOList.add(new DishDTO(dish));
        }
        //TODO throw some message/exception if failed
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
        //TODO throw some message/exception if failed
        return dishId;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishHibernate> updateDish(@PathVariable("id") Long id, @RequestBody DishDTO drinkDTO){
        Optional<DishHibernate> dishHibernateOptional = dishHibernateRepository.findById(id);
        DishHibernate _dishHibernate = dishHibernateOptional.get();
        if(dishHibernateOptional.isPresent()){
            _dishHibernate.setName(drinkDTO.getName());
            _dishHibernate.setDescription(drinkDTO.getDescription());
            _dishHibernate.setPrice(drinkDTO.getPrice());
            _dishHibernate.setAvailable(drinkDTO.getAvailable());
        }
        return new ResponseEntity<>(dishHibernateRepository.save(_dishHibernate), HttpStatus.OK);
    }

    @DeleteMapping(path = "/remove/{id}")
    public String removeDish(@PathVariable("id") Long id) {
        dishService.removeDish(id);
        //TODO throw some message/exception if failed
        return "REMOVED";
    }
}
