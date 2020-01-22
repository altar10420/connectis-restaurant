package pl.connectis.restaurant.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.domain.ClientHibernate;
import pl.connectis.restaurant.service.ClientService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientServiceImpl implements ClientService {

    @Override
    public ClientHibernate createClient(String name, String surname, BigDecimal discount) {
        return null;
    }

    @Override
    public Optional<ClientHibernate> getClient(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ClientHibernate> getAllClient(Pageable pageable) {
        return null;
    }

    @Override
    public List<ClientHibernate> getClientMenuPage(int page) {
        return null;
    }

    @Override
    public void removeClient(Long id) {

    }
}
