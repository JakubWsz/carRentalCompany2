package pl.kuba.domain;

import pl.kuba.entities.Client;

public interface ClientStore {
    Client save(Client client);

    Client findByEmail(String email);
}
