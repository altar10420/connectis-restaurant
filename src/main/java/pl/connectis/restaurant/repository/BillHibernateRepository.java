package pl.connectis.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import pl.connectis.restaurant.domain.BillHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;

public interface BillHibernateRepository
        extends CrudRepository<BillHibernate, Long> {

    default BillHibernate getById(Long billId) {
        return findById(billId).orElseThrow(
                () -> new EntityDoesNotExistException("Bill id: " + billId + " not found"));
    }

}
