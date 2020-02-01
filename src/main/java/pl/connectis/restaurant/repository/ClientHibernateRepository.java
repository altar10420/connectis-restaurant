package pl.connectis.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import pl.connectis.restaurant.domain.ClientHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;

public interface ClientHibernateRepository
        extends CrudRepository<ClientHibernate, Long> {

    default ClientHibernate getById(Long clientId) {
        return findById(clientId).orElseThrow(
                () -> new EntityDoesNotExistException("Client id: " + clientId + " not found"));
    }
}
