package fines_payment_manager.Command;

import fines_payment_manager.Repository.DriverRepository;
import fines_payment_manager.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteDriverCommand implements Command {

    private int driverId;
    private DriverService driverService;

    @Autowired
    public DeleteDriverCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public void execute() {
        driverService.deleteDriver(driverId);
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
}
