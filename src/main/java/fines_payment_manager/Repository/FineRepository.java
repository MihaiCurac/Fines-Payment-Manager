package fines_payment_manager.Repository;

import fines_payment_manager.Model.Fine;
import org.springframework.data.repository.CrudRepository;

public interface FineRepository extends CrudRepository<Fine, Integer> {
    Iterable<Fine> findAllByDriverId(int driverId);
}
