package fines_payment_manager.Service;

import fines_payment_manager.Model.PoliceStation;
import fines_payment_manager.Repository.PoliceStationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoliceStationService {

    @Autowired
    private PoliceStationRepository policeStationRepository;

    public void updatePoliceStation(PoliceStation policeStation) {
        policeStationRepository.save(policeStation);
    }

    public List<String> getAllPoliceStations(){
        List<PoliceStation> policeStations = new ArrayList<>();
        policeStationRepository.findAll().forEach(policeStations::add);
        List<String> policeStationsString = new ArrayList<>();
        for (PoliceStation policeStation : policeStations) {
            policeStationsString.add(policeStation.toString());
        }
        return policeStationsString;
    }

    public String getPoliceStationStr(int id){
        PoliceStation policeStation = policeStationRepository.findById(id).orElse(null);
        if (policeStation == null) {
            throw new EntityNotFoundException("Police station with id " + id + " not found");
        }
        return policeStation.toString();
    }

    public PoliceStation getPoliceStation(int id){
        PoliceStation policeStation = policeStationRepository.findById(id).orElse(null);
        if (policeStation == null) {
            throw new EntityNotFoundException("Police station with id " + id + " not found");
        }
        return policeStation;
    }
}
