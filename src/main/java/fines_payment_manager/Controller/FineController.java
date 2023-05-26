package fines_payment_manager.Controller;

import fines_payment_manager.Command.*;
import fines_payment_manager.Model.Fine;
import fines_payment_manager.Service.FineService;
import fines_payment_manager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class FineController {
    //exactly the same as in src\main\java\fines_payment_manager\Controller\DriverController.java, but with Fine instead of Driver

    @Autowired
    private CommandExecutor commandExecutor;
    @Autowired
    private AddFineCommand addFineCommand;
    @Autowired
    private UpdateFineCommand updateFineCommand;
    @Autowired
    private DeleteFineCommand deleteFineCommand;
    @Autowired
    private RegisterPaidFineCommand registerPaidFineCommand;
    @Autowired
    private CreateReportCommand createReportCommand;
    @Autowired
    private FineService fineService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/{email}/{password}/drivers/{driver_id}/fines")
    public ResponseEntity<String> addFine(@PathVariable String email, @PathVariable String password, @PathVariable int driver_id, @RequestBody Fine fine) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>("Login failed. You need a valid police employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        addFineCommand.setFine(fine);
        addFineCommand.setDriverId(driver_id);
        addFineCommand.setPoliceStation(userService.getUserByEmail(email).getPoliceStation());
        commandExecutor.execute(addFineCommand);
        return new ResponseEntity<>("Fine added successfully", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{email}/{password}/drivers/{driver_id}/fines/{id}")
    public ResponseEntity<String> updateFine(@PathVariable String email, @PathVariable String password, @PathVariable int driver_id, @RequestBody Fine fine) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>("Login failed. You need a valid police employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        updateFineCommand.setFine(fine);
        updateFineCommand.setDriverId(driver_id);
        updateFineCommand.setPoliceStation(userService.getUserByEmail(email).getPoliceStation());
        commandExecutor.execute(updateFineCommand);
        return new ResponseEntity<>("Fine updated successfully", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{email}/{password}/drivers/{driver_id}/fines/{id}")
    public ResponseEntity<String> deleteFine(@PathVariable String email, @PathVariable String password, @PathVariable int id, @PathVariable String driver_id) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>("Login failed. You need a valid police employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        deleteFineCommand.setFineId(id);
        commandExecutor.execute(deleteFineCommand);
        return new ResponseEntity<>("Fine added successfully", HttpStatus.OK);
    }

    @RequestMapping("/{email}/{password}/fines")
    public  ResponseEntity<List<String>> getAllFines(@PathVariable String email, @PathVariable String password) {
        int checkUser = userService.checkUser(email, password);
        if (checkUser != 1 && checkUser != 2) {
            return new ResponseEntity<>(Collections.singletonList("Login failed. You need a valid account to access this resource."), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(fineService.getAllFinesStr(), HttpStatus.OK);
    }

    @RequestMapping("/{email}/{password}/drivers/{driver_id}/fines")
    public ResponseEntity<List<String>> getAllFinesForDriver(@PathVariable String email, @PathVariable String password, @PathVariable int driver_id) {
        int checkUser = userService.checkUser(email, password);
        if (checkUser != 1 && checkUser != 2) {
            return new ResponseEntity<>(Collections.singletonList("Login failed. You need a valid account to access this resource."), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(fineService.getAllFinesByDriverId(driver_id), HttpStatus.OK);
    }

    @RequestMapping("/{email}/{password}/fines/{id}")
    public ResponseEntity<String> getFine(@PathVariable String email, @PathVariable String password, @PathVariable int id) {
        int checkUser = userService.checkUser(email, password);
        if (checkUser != 1 && checkUser != 2) {
            return new ResponseEntity<>("Login failed. You need a valid account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(fineService.getFineStr(id), HttpStatus.OK);
    }

    @RequestMapping("/{email}/{password}/drivers/{driver_id}/fines/{id}/register")
    public ResponseEntity<String> registerPaidFine(@PathVariable String email, @PathVariable String password, @PathVariable int id, @PathVariable String driver_id) {
        if (userService.checkUser(email, password) != 2) {
            return new ResponseEntity<>("Login failed. You need a valid post office employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        registerPaidFineCommand.setFineId(id);
        commandExecutor.execute(registerPaidFineCommand);
        return new ResponseEntity<>("Fine with id " + id + " registered as paid successfully. Notifications to log file and by email have been sent.", HttpStatus.OK);
    }

    @RequestMapping("/{email}/{password}/fines/report")
    public ResponseEntity<String> reportFines(@PathVariable String email, @PathVariable String password) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>("Login failed. You need a valid police employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        commandExecutor.execute(createReportCommand);
        return new ResponseEntity<>("Report about the fines' situation created successfully.", HttpStatus.OK);
    }

}
