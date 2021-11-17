package pl.kuba.domain.services;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Client;
import pl.kuba.infrastructure.persistence.ClientRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientStore;

    public ClientService(ClientRepository clientRepository) {
        this.clientStore = clientRepository;
    }

    public void addNewClient(String firstname, String lastname, String email, String address) {
        if (clientStore.findByEmail(email).isEmpty()) {
            Client client = new Client(firstname, lastname, email, address);
            client.setModificationDate(LocalDateTime.now());
            clientStore.save(client);
        } else throw new RuntimeException("Account with this email already exist");
    }
}