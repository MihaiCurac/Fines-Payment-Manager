package fines_payment_manager.Command;

import fines_payment_manager.Model.Fine;
import fines_payment_manager.Model.PoliceStation;
import fines_payment_manager.Repository.FineRepository;
import fines_payment_manager.Service.DriverService;
import fines_payment_manager.Service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateFineCommand implements Command {

    private Fine fine;
    private int driverId;
    private PoliceStation policeStation;
    private FineService fineService;
    private DriverService driverService;

    @Autowired
    public UpdateFineCommand(FineService fineService, DriverService driverService) {
        this.fineService = fineService;
        this.driverService = driverService;
    }

    @Override
    public void execute() {
        fine.setDriver(driverService.getDriver(driverId));
        fine.setPolice_station(policeStation);
        fineService.updateFine(fine);
    }

    public void setFine(Fine fine) {
        this.fine = fine;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public PoliceStation getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(PoliceStation policeStation) {
        this.policeStation = policeStation;
    }
}
