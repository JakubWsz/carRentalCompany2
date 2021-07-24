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

    public void addNewClient(Client client) {
       if (isEmailExists(client)) {
           client.setModificationDate(LocalDateTime.now());
           clientStore.save(client);
        } else throw new RuntimeException("Account with this email already exist");
    }

    private boolean isEmailExists(Client client) {
        List<Client> clients = new ArrayList<>(clientStore.findByEmail(client.getEmail()));
       return clients.stream()
                .anyMatch(optionalClient -> optionalClient.getEmail().equals(client.getEmail()));
    }
}