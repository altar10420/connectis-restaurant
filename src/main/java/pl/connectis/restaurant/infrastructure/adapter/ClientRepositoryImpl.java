package pl.connectis.restaurant.infrastructure.adapter;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.domain.model.Client;
import pl.connectis.restaurant.domain.service.ClientRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Override
    public Client createClient(String name, String surname, BigDecimal discount) {
        return null;
    }

    @Override
    public Optional<Client> getClient(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Client> getAllClient(Pageable pageable) {
        return null;
    }

    @Override
    public List<Client> getClientMenuPage(int page) {
        return null;
    }

    @Override
    public void removeClient(Long id) {

    }
}
