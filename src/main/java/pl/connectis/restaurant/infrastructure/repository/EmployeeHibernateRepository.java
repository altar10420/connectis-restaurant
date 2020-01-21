package pl.connectis.restaurant.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.infrastructure.entity.EmployeeHibernate;

public interface EmployeeHibernateRepository extends PagingAndSortingRepository<EmployeeHibernate, Long> {

    Page<EmployeeHibernate> findByNameContaining(String partOfName, Pageable pageable);
}
