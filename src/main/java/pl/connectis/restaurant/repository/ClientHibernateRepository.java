package pl.connectis.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import pl.connectis.restaurant.domain.ClientHibernate;

public interface ClientHibernateRepository extends CrudRepository<ClientHibernate, Long> {
}
