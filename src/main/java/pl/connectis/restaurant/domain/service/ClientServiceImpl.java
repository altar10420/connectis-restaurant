package pl.connectis.restaurant.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.domain.model.Client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Long createClient(String name,
            String surname,
            BigDecimal discount) {
        Client client = clientRepository.createClient(
                name,
                surname,
                discount
        );
        return client.getId();
    }

    @Override
    public Optional<Client> getClient(Long id) {
        return clientRepository.getClient(id);
    }

    @Override
    public List<Client> getAllClients(Pageable pageable) {
        return clientRepository.getAllClient(pageable);
    }

    @Override
    public void removeClient(Long id) {

    }
}
