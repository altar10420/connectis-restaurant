package pl.connectis.restaurant.controller;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.ClientDTO;
import pl.connectis.restaurant.controller.dto.DishDTO;
import pl.connectis.restaurant.domain.ClientHibernate;
import pl.connectis.restaurant.domain.DishHibernate;
import pl.connectis.restaurant.domain.DrinkHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.ClientHibernateRepository;
import pl.connectis.restaurant.service.ClientService;

import java.util.ArrayList;
import java.util.List;
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
        if(!clientService.getClient(id).isPresent()) {
            throw new EntityDoesNotExistException();
        }
        return new ClientDTO(clientService.getClient(id).get());
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
    public void updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO){
        Optional<ClientHibernate> clientOptional = clientHibernateRepository.findById(id);

        if(!clientOptional.isPresent()) {
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

        if(!clientOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }
        clientService.removeClient(id);
    }
}


