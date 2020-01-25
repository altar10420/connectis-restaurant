package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import pl.connectis.restaurant.domain.*;
import pl.connectis.restaurant.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BillServiceImpl implements BillService {

    private final BillHibernateRepository billRepository;
    private final DishHibernateRepository dishRepository;
    private final DrinkHibernateRepository drinkRepository;
    private final ClientHibernateRepository clientRepository;
    private final EmployeeHibernateRepository employeeRepository;


    @Autowired
    public BillServiceImpl(BillHibernateRepository billRepository,
                           DishHibernateRepository dishRepository,
                           DrinkHibernateRepository drinkRepository,
                           ClientHibernateRepository clientRepository,
                           EmployeeHibernateRepository employeeRepository) {
        this.billRepository = billRepository;
        this.dishRepository = dishRepository;
        this.drinkRepository = drinkRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Long createBill(
            LocalDateTime date,
            BigDecimal price,
            BigDecimal tip,
            List<DishHibernate> dishes,
            List<DrinkHibernate> drinks,
            Long clientId,
            Long employeeId) {

        Optional<ClientHibernate> clientOptional = clientRepository.findById(clientId);
        if (!clientOptional.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }

        Optional<EmployeeHibernate> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }

        BillHibernate bill = new BillHibernate(
            null,
            LocalDateTime.now(),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
                new ArrayList<>(),
                new ArrayList<>(),
                clientOptional.get(),
                employeeOptional.get()
        );

        billRepository.save(bill);

        return bill.getId();
    }

    @Override
    public Optional<BillHibernate> getBill(Long billId) {
        return billRepository.findById(billId);
    }

    @Override
    public Long addDish(Long billId, Long dishId) {

        Optional<BillHibernate> billOptional = billRepository.findById(billId);
        if (!billOptional.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }

        Optional<DishHibernate> dishOptional = dishRepository.findById(dishId);
        if (!dishOptional.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }

        BillHibernate bill = billOptional.get();

        DishHibernate dish = dishOptional.get();

        ClientHibernate client = bill.getClient();

        BigDecimal tip = new BigDecimal(String.valueOf(dish.getPrice())).multiply(new BigDecimal(0.05));

        bill.getDishes().add(dish);

        bill.setPrice(bill.getPrice().add(dish.getPrice().multiply(client.getDiscount())).add(tip));

        bill.setTip(tip);

        billRepository.save(bill);

        return billId;
    }

    @Override
    public Long addDrink(Long billId, Long drinkId) {

        Optional<BillHibernate> billOptional = billRepository.findById(billId);
        if (!billOptional.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }

        Optional<DrinkHibernate> drinkOptional = drinkRepository.findById(drinkId);
        if (!drinkOptional.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }

        BillHibernate bill = billOptional.get();

        DrinkHibernate drink = drinkOptional.get();

        ClientHibernate client = bill.getClient();

        BigDecimal tip = new BigDecimal(String.valueOf(drink.getPrice())).multiply(new BigDecimal(0.02));

        bill.getDrinks().add(drink);

        bill.setPrice(bill.getPrice().add(drink.getPrice().multiply(client.getDiscount())).add(tip));

        bill.setTip(tip);

        billRepository.save(bill);

        return billId;
    }

    @Override
    public void removeBill(Long billId) {

        Optional<BillHibernate> billOptional = billRepository.findById(billId);
        if (!billOptional.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }

        billRepository.deleteById(billId);
    }
}
