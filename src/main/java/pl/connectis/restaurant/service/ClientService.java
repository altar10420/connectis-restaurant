package pl.connectis.restaurant.service;

import pl.connectis.restaurant.domain.ClientHibernate;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {
    Long createClient(
            String name,
            String surname,
            BigDecimal discount
    );

    ClientHibernate getClient(Long id);

    List<ClientHibernate> getAllClients();

    void removeClient(Long id);

    void updateClient(Long id, String name, String surname, BigDecimal discount);
}
