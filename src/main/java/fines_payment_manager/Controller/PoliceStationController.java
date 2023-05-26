package fines_payment_manager.Controller;

import fines_payment_manager.Command.CommandExecutor;
import fines_payment_manager.Command.UpdatePoliceStationCommand;
import fines_payment_manager.Model.PoliceStation;
import fines_payment_manager.Service.PoliceStationService;
import fines_payment_manager.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class PoliceStationController {

    @Autowired
    private CommandExecutor commandExecutor;
    @Autowired
    private UpdatePoliceStationCommand updatePoliceStationCommand;
    @Autowired
    private PoliceStationService policeStationService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT, value = "/{email}/{password}/policeStations")
    public ResponseEntity<String> updatePoliceStation(@PathVariable String email, @PathVariable String password, @RequestBody PoliceStation policeStation) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>("Login failed. You need a valid police employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        updatePoliceStationCommand.setPoliceStation(policeStation);
        commandExecutor.execute(updatePoliceStationCommand);
        return new ResponseEntity<>( "Police station updated successfully", HttpStatus.OK);
    }

    @RequestMapping("/{email}/{password}/policeStations")
    public ResponseEntity<List<String>> getAllPoliceStations(@PathVariable String email, @PathVariable String password) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>(Collections.singletonList("Login failed. You need a valid police employee account to access this resource."), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(policeStationService.getAllPoliceStations(), HttpStatus.OK);
    }

    @RequestMapping("/{email}/{password}/policeStations/current")
    public ResponseEntity<String> getPoliceStation(@PathVariable String email, @PathVariable String password) {
        if (userService.checkUser(email, password) != 1) {
            return new ResponseEntity<>("Login failed. You need a valid police employee account to access this resource.", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userService.getUserByEmail(email).getPoliceStation().toString(), HttpStatus.OK);
    }

}
