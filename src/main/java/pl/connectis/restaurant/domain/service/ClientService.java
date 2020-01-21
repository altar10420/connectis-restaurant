package pl.connectis.restaurant.domain.service;

import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.model.Client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ClientService {

    Long createClient(
            String name,
            String surname,
            BigDecimal discount
    );

    Optional<Client> getClient(Long id);

    List<Client> getAllClient(Pageable pageable);

    List<Client> getClientMenuPage(int page);

    void removeClient(Long id);
}
