package pl.kuba.api.apies;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kuba.domain.services.ClientService;
import pl.kuba.entities.Client;

@RestController
@RequestMapping("/client")
public class ClientAPI {
    private final ClientService clientService;

    public ClientAPI(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public void addNewClient(Client client) {
        clientService.addNewClient(client);
    }
}
