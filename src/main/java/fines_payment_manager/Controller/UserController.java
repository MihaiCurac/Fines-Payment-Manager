package fines_payment_manager.Controller;

import fines_payment_manager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{email}/{password}")
    public ResponseEntity<String> logIn(@PathVariable String email, @PathVariable String password) {
        int checkUser = userService.checkUser(email, password);
        if (checkUser == 1) {
            return new ResponseEntity<>("Successful login into police employee account.", HttpStatus.OK);
        }
        else if (checkUser == 2) {
            return new ResponseEntity<>("Successful login into post office employee account.", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Login failed. Please check your credentials.", HttpStatus.UNAUTHORIZED);
        }
    }
}
