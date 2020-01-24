package pl.connectis.restaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.domain.ClientHibernate;

public interface ClientHibernateRepository extends PagingAndSortingRepository {
    Page<ClientHibernate> findByNameContaining(String partOfName, Pageable pageable);
}
