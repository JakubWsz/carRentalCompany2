package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Client;
import pl.kuba.infrastructure.ClientRepository;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addNewClient(Client client) {
        String clientEmailInDB = clientRepository.findByEmail(client.getEmail()).getEmail();
        if (clientEmailInDB == null) {
            clientRepository.save(client);
        } else throw new RuntimeException("Account with this email already exist");
    }
}