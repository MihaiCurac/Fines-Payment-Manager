package fines_payment_manager.Command;

import fines_payment_manager.Model.PoliceStation;
import fines_payment_manager.Service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePoliceStationCommand implements Command{

    private PoliceStation policeStation;
    private PoliceStationService policeStationService;

    @Autowired
    public UpdatePoliceStationCommand(PoliceStationService policeStationService) {
        this.policeStationService = policeStationService;
    }

    @Override
    public void execute() {
        policeStationService.updatePoliceStation(policeStation);
    }

    public void setPoliceStation(PoliceStation policeStation) {
        this.policeStation = policeStation;
    }
}
