package fines_payment_manager.Command;

import fines_payment_manager.Model.Driver;
import fines_payment_manager.Repository.DriverRepository;
import fines_payment_manager.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddDriverCommand implements Command{

    private Driver driver;
    private DriverService driverService;

    @Autowired
    public AddDriverCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public void execute() {
        driverService.addDriver(driver);
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
