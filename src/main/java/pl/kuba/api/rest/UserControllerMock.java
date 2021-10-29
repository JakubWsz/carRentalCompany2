package pl.kuba.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kuba.api.dto.request.user.LoginUserRequest;
import pl.kuba.api.dto.response.user.UserView;

@RestController
@RequestMapping("/user")
public class UserControllerMock {

    @PostMapping("/login")
    public ResponseEntity<UserView> login(@RequestBody LoginUserRequest loginUserRequest) {
        if (loginUserRequest.getUsername().equals("user") && loginUserRequest.getPassword().equals("user")) {
            UserView userView = new UserView("1", "user", "REGULAR");
            return new ResponseEntity<>(userView, HttpStatus.OK);
        }
        if (loginUserRequest.getUsername().equals("admin") && loginUserRequest.getPassword().equals("admin")) {
            UserView userView = new UserView("2", "admin", "MODERATOR");
            return new ResponseEntity<>(userView, HttpStatus.OK);
        }
        if (loginUserRequest.getUsername().equals("moderator") && loginUserRequest.getPassword().equals("moderator")) {
            UserView userView = new UserView("3", "moderator", "MODERATOR");
            return new ResponseEntity<>(userView, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
