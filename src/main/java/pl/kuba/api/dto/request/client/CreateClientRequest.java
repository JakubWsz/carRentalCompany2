package pl.kuba.api.dto.request.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateClientRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
}
