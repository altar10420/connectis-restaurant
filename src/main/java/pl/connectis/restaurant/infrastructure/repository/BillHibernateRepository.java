package pl.connectis.restaurant.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import pl.connectis.restaurant.infrastructure.entity.BillHibernate;

public interface BillHibernateRepository
        extends CrudRepository<BillHibernate, Long> {

}
