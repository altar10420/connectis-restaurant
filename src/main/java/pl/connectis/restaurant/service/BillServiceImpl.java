package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.domain.BillHibernate;
import pl.connectis.restaurant.domain.DishHibernate;
import pl.connectis.restaurant.repository.BillHibernateRepository;
import pl.connectis.restaurant.repository.DishHibernateRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BillServiceImpl implements BillService {

    private final BillHibernateRepository billRepository;
    private final DishHibernateRepository dishRepository;

    @Autowired
    public BillServiceImpl(BillHibernateRepository billRepository,
                           DishHibernateRepository dishRepository) {
        this.billRepository = billRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public Long createBill(
            LocalDateTime date,
            BigDecimal price,
            BigDecimal tip,
            List<Long> dishes) {

        List<DishHibernate> dishList = new ArrayList<>();
        for (Long dishId: dishes) {
            Optional<DishHibernate> dishOptional = dishRepository.findById(dishId);
            if (!dishOptional.isPresent()) {
                System.out.println("No entity!");
            }
            dishList.add(dishOptional.get());
        }

        BillHibernate bill = new BillHibernate(
                null,
                date,
                price,
                tip
        );

        bill.setDishes(dishList);

        billRepository.save(bill);

        return bill.getId();
    }

    @Override
    public Optional<BillHibernate> getBill(Long billId) {
        return billRepository.findById(billId);
    }
}
