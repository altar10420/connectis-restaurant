package pl.connectis.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import pl.connectis.restaurant.domain.BillHibernate;

public interface BillHibernateRepository
        extends CrudRepository<BillHibernate, Long> {

}
