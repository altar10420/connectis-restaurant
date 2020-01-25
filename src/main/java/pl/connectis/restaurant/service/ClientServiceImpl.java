package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.domain.ClientHibernate;
import pl.connectis.restaurant.domain.ClientHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
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

        ClientHibernate clientHibernate = new ClientHibernate(
                    null,
                    name,
                    surname,
                    discount
            );

            clientRepository.save(clientHibernate);
            return clientHibernate.getId();
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
    public void removeClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void updateClient(Long id, String name, String surname, BigDecimal discount) {
        Optional<ClientHibernate> optionalClientHibernate = clientRepository.findById(id);
        if (!optionalClientHibernate.isPresent()){
            throw new EntityDoesNotExistException();
        }

        ClientHibernate client = optionalClientHibernate.get();

        client.setName(name);
        client.setSurname(surname);
        client.setDiscount(discount);

        clientRepository.save(client);
    }
}
