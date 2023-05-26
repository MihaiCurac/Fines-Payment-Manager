package fines_payment_manager.Controller;

import fines_payment_manager.Command.AddDriverCommand;
import fines_payment_manager.Command.CommandExecutor;
import fines_payment_manager.Command.DeleteDriverCommand;
import fines_payment_manager.Command.UpdateDriverCommand;
import fines_payment_manager.Model.Driver;
import fines_payment_manager.Service.DriverService;
import fines_payment_manager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class DriverController {

    @Autowired
    private CommandExecutor commandExecutor;
    @Autowired
    private AddDriverCommand addDriverCommand;
    @Autowired
    private UpdateDriverCommand updateDriverCommand;
    @Autowired
    private DeleteDriverCommand deleteDriverCommand;
    @Autowired
    private DriverService driverService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/{email}/{password}/drivers")
    public ResponseEntity<String> addDriver(@PathVariable String email, @PathVariable String password, @RequestBody Driver driver) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>("Login failed. You need a valid police employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        addDriverCommand.setDriver(driver);
        commandExecutor.execute(addDriverCommand);
        return new ResponseEntity<>("Driver added successfully", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{email}/{password}/drivers")
    public ResponseEntity<String> updateDriver(@PathVariable String email, @PathVariable String password, @RequestBody Driver driver) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>("Login failed. You need a valid police employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        updateDriverCommand.setDriver(driver);
        commandExecutor.execute(updateDriverCommand);
        return new ResponseEntity<>("Driver updated successfully", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{email}/{password}/drivers/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable String email, @PathVariable String password, @PathVariable int id) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>("Login failed. You need a valid police employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        deleteDriverCommand.setDriverId(id);
        commandExecutor.execute(deleteDriverCommand);
        return new ResponseEntity<>("Driver deleted successfully", HttpStatus.OK);
    }

    @RequestMapping("/{email}/{password}/drivers")
    public ResponseEntity<List<String>> getAllDrivers(@PathVariable String email, @PathVariable String password) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>(Collections.singletonList("Login failed. You need a valid police employee account to access this resource."), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(driverService.getAllDrivers(), HttpStatus.OK);
    }

    @RequestMapping("/{email}/{password}/drivers/{id}")
    public ResponseEntity<String> getDriver(@PathVariable String email, @PathVariable String password, @PathVariable int id) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>( "Login failed. You need a valid police employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(driverService.getDriverStr(id), HttpStatus.OK);
    }

}
