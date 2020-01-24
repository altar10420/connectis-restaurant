package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.domain.ClientHibernate;
import pl.connectis.restaurant.repository.ClientHibernateRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientServiceImpl implements ClientService {

    private final ClientHibernateRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientHibernateRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Long createClient(String name, String surname, BigDecimal discount) {
        return null;
    }

    @Override
    public Optional<ClientHibernate> getClient(Long id) {
        return clientRepository.findById(id);
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
