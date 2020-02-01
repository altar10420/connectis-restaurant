package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.ClientDTO;
import pl.connectis.restaurant.domain.ClientHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.ClientHibernateRepository;
import pl.connectis.restaurant.service.ClientService;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    private final ClientHibernateRepository clientHibernateRepository;

    @Autowired
    public ClientController(ClientService clientService, ClientHibernateRepository clientHibernateRepository) {
        this.clientService = clientService;
        this.clientHibernateRepository = clientHibernateRepository;
    }

    @GetMapping(path = "/{id}")
    public ClientDTO getClient(@PathVariable("id") Long id) {

        return new ClientDTO(clientService.getClient(id));
    }

    @PostMapping(path = "/")
    public Long createClient(@RequestBody ClientDTO clientDTO) {
        Long clientId = clientService.createClient(
                clientDTO.getName(),
                clientDTO.getSurname(),
                clientDTO.getDiscount()
        );
        return clientId;
    }

    @PutMapping("/{id}")
    public void updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO) {
        Optional<ClientHibernate> clientOptional = clientHibernateRepository.findById(id);

        if (!clientOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }

        clientService.updateClient(id,
                clientDTO.getName(),
                clientDTO.getSurname(),
                clientDTO.getDiscount()
        );
    }

    @DeleteMapping(path = "/{id}")
    public void removeClient(@PathVariable("id") Long id) {
        Optional<ClientHibernate> clientOptional = clientHibernateRepository.findById(id);

        if (!clientOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }
        clientService.removeClient(id);
    }
}


