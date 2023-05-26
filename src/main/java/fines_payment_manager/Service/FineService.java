package fines_payment_manager.Service;

import fines_payment_manager.Model.Fine;
import fines_payment_manager.Repository.FineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FineService {

    @Autowired
    private FineRepository fineRepository;

    public void addFine(Fine fine) {
        fineRepository.save(fine);
    }

    public void updateFine(Fine fine) {
        fineRepository.save(fine);
    }

    public void deleteFine(int fineId) {
        fineRepository.deleteById(fineId);
    }

    public List<String> getAllFinesStr() {
        List<Fine> fines = new ArrayList<>();
        fineRepository.findAll().forEach(fines::add);
        List<String> finesString = new ArrayList<>();
        for (Fine fine : fines) {
            finesString.add(fine.toString());
        }
        return finesString;
    }

    public List<Fine> getAllFines() {
        List<Fine> fines = new ArrayList<>();
        fineRepository.findAll().forEach(fines::add);
        return fines;
    }

    public List<String> getAllFinesByDriverId(int driverId) {
        List<Fine> fines = new ArrayList<>();
        fineRepository.findAllByDriverId(driverId).forEach(fines::add);
        List<String> finesString = new ArrayList<>();
        for (Fine fine : fines) {
            finesString.add(fine.toString());
        }
        return finesString;
    }

    public String getFineStr(int id) {
        Fine fine = fineRepository.findById(id).orElse(null);
        if (fine == null) {
            throw new EntityNotFoundException("Fine with id " + id + " not found");
        }
        return fine.toString();
    }

    public Fine getFine(int id) {
        Fine fine = fineRepository.findById(id).orElse(null);
        if (fine == null) {
            throw new EntityNotFoundException("Fine with id " + id + " not found");
        }
        return fine;
    }
}
