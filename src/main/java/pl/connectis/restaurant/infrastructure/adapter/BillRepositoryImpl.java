package pl.connectis.restaurant.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import pl.connectis.restaurant.domain.model.Bill;
import pl.connectis.restaurant.domain.service.BillRepository;
import pl.connectis.restaurant.infrastructure.entity.BillHibernate;
import pl.connectis.restaurant.infrastructure.repository.BillHibernateRepository;
import pl.connectis.restaurant.infrastructure.repository.DishHibernateRepository;

import java.util.stream.Collectors;

public class BillRepositoryImpl implements BillRepository {

    private final BillHibernateRepository billHibernateRepository;
    private final DishHibernateRepository dishHibernateRepository;

    @Autowired
    public BillRepositoryImpl(BillHibernateRepository billHibernateRepository,
                              DishHibernateRepository dishHibernateRepository) {
        this.billHibernateRepository = billHibernateRepository;
        this.dishHibernateRepository = dishHibernateRepository;
    }




    //TODO needs to be changed to contain Client (also change constructors in Bill class then!!!)
    public Bill toDomain(BillHibernate hibernate) {
        return new Bill(
                hibernate.getDate(),
                hibernate.getPrice(),
                hibernate.getTip(),
                hibernate.getDishes()
        );
    }

//    public Bill toDomain(BillHibernate hibernate) {
//        return new Bill(
//                hibernate.getDate(),
//                hibernate.getPrice(),
//                hibernate.getTip(),
//                hibernate.getDishes()
//                        .stream()
//                        .map(dishHibernateRepository::toDomain)
//                        .collect(Collectors.toList())
//        );
//    }



}
