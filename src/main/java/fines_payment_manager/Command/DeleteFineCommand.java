package fines_payment_manager.Command;

import fines_payment_manager.Repository.FineRepository;
import fines_payment_manager.Service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteFineCommand implements Command {

    private int fineId;
    private FineService fineService;

    @Autowired
    public DeleteFineCommand (FineService fineService) {
        this.fineService = fineService;
    }

    @Override
    public void execute() {
        fineService.deleteFine(fineId);
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }
}
