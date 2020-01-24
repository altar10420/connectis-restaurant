package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.controller.dto.ClientDTO;
import pl.connectis.restaurant.controller.dto.EmployeeDTO;
import pl.connectis.restaurant.domain.*;
import pl.connectis.restaurant.repository.BillHibernateRepository;
import pl.connectis.restaurant.repository.ClientHibernateRepository;
import pl.connectis.restaurant.repository.DishHibernateRepository;
import pl.connectis.restaurant.repository.EmployeeHibernateRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BillServiceImpl implements BillService {

    private final BillHibernateRepository billRepository;
    private final DishHibernateRepository dishRepository;
    private final ClientHibernateRepository clientRepository;
    private final EmployeeHibernateRepository employeeRepository;


    @Autowired
    public BillServiceImpl(BillHibernateRepository billRepository,
                           DishHibernateRepository dishRepository,
                           ClientHibernateRepository clientRepository,
                           EmployeeHibernateRepository employeeRepository) {
        this.billRepository = billRepository;
        this.dishRepository = dishRepository;
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

//        List<DishHibernate> dishList = new ArrayList<>();
//        for (Long dishId: dishes) {
//            Optional<DishHibernate> dishOptional = dishRepository.findById(dishId);
//            if (!dishOptional.isPresent()) {
//                System.out.println("No entity!");
//            }
//            dishList.add(dishOptional.get());
//        }

//        BillHibernate bill = new BillHibernate(
//                null,
//                date,
//                price,
//                tip
//        );
        Optional<ClientHibernate> clientOptional = clientRepository.findById(clientId);
        if (!clientOptional.isPresent()) {
            //TODO some exception here???
            System.out.println("NOT FOUND");
        }

//        //TODO should I use DTO here?
//        ClientDTO clientDTO = new ClientDTO(
//                clientOptional.get().getId(),
//                clientOptional.get().getName(),
//                clientOptional.get().getSurname(),
//                clientOptional.get().getDiscount()
//        );

        Optional<EmployeeHibernate> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) {
            //TODO some exception here???
            System.out.println("NOT FOUND");
        }

//        //TODO should I use DTO here?
//        EmployeeDTO employeeDTO = new EmployeeDTO(
//                employeeOptional.get().getId(),
//                employeeOptional.get().getName(),
//                employeeOptional.get().getSurname(),
//                employeeOptional.get().getPosition(),
//                employeeOptional.get().getSalary(),
//                employeeOptional.get().getManagerId(),
//                employeeOptional.get().getPesel()
//        );

        BillHibernate bill = new BillHibernate(
            null,
            LocalDateTime.now(),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
                new ArrayList<>(),
                new ArrayList<>(),
                clientOptional.get(),
                employeeOptional.get()
//                null,
//                null


        );

//        bill.setClient(clientDTO.toHibernate(clientDTO));
//        bill.setDishes(dishList);

        billRepository.save(bill);

        return bill.getId();
    }

    @Override
    public Optional<BillHibernate> getBill(Long billId) {
        return billRepository.findById(billId);
    }

    @Override
    public Long addDish(Long billId, Long dishId) {

        BillHibernate bill = billRepository.findById(billId).get();

        bill.getDishes().add(dishRepository.findById(dishId).get());

        billRepository.save(bill);

        return billId;
    }
}
