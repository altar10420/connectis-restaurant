package pl.connectis.restaurant.controller;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.ClientDTO;
import pl.connectis.restaurant.domain.ClientHibernate;
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
        Optional<ClientHibernate> clientOptional = clientService.getClient(id);
        //TODO throw some exception if failed to get
        return new ClientDTO(clientOptional.get());
    }

    @GetMapping(path = "/menu/{page}")
    public List<ClientDTO> getClientMenuPage(@PathVariable("page") Integer page) {
        List<ClientHibernate> clientList = clientService.getClientMenuPage(page);
        List<ClientDTO> clientDTOList = new ArrayList<>();

        for (ClientHibernate client : clientList) {
            clientDTOList.add(new ClientDTO(client));
        }
        return clientDTOList;
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

//    @PutMapping("/{id}")
//    public ResponseEntity<ClientHibernate> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO drinkDTO){
//        Optional<ClientHibernate> clientHibernateOptional = clientHibernateRepository.findById(id);
//        ClientHibernate _clientHibernate = clientHibernateOptional.get();
//        if(clientHibernateOptional.isPresent()){
//            _clientHibernate.setName(drinkDTO.getName());
//            _clientHibernate.setSurname(drinkDTO.getSurname());
//            _clientHibernate.setDiscount(drinkDTO.getDiscount());
//        }
//        return new ResponseEntity<>(clientHibernateRepository.save(_clientHibernate), HttpStatus.OK);
//    }

    @DeleteMapping(path = "/{id}")
    public String removeClient(@PathVariable("id") Long id) {
        clientService.removeClient(id);
        //TODO throw some message/exception if failed
        return "REMOVED";
    }
}


