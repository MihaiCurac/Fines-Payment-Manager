package fines_payment_manager.Service;

import fines_payment_manager.Model.Driver;
import fines_payment_manager.Repository.DriverRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public void updateDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public void deleteDriver(int driverId) {
        driverRepository.deleteById(driverId);
    }

    public List<String> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        driverRepository.findAll().forEach(drivers::add);
        List<String> driversString = new ArrayList<>();
        for (Driver driver : drivers) {
            driversString.add(driver.toString());
        }
        return driversString;
    }

    public String getDriverStr(int id) {
        Driver driver = driverRepository.findById(id).orElse(null);
        if (driver == null) {
            throw new EntityNotFoundException("Driver with id " + id + " not found");
        }
        return driver.toString();
    }

    public Driver getDriver(int id) {
        Driver driver = driverRepository.findById(id).orElse(null);
        if (driver == null) {
            throw new EntityNotFoundException("Driver with id " + id + " not found");
        }
        return driver;
    }
}
