package fines_payment_manager.Command;

import fines_payment_manager.Model.Fine;
import fines_payment_manager.Notifications.EmailObserver;
import fines_payment_manager.Notifications.EmailService;
import fines_payment_manager.Notifications.LogObserver;
import fines_payment_manager.Notifications.PaidFineNotification;
import fines_payment_manager.Service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterPaidFineCommand implements Command{

    private int fineId;
    private FineService fineService;
    @Autowired
    private EmailService emailService;

    @Autowired
    public RegisterPaidFineCommand(FineService fineService) {
        this.fineService = fineService;
    }

    @Override
    public void execute() {
        Fine fine = fineService.getFine(fineId);
        fine.setStatus("Paid");
        fineService.updateFine(fine);
        PaidFineNotification notification = new PaidFineNotification();
        EmailObserver emailObserver = new EmailObserver(emailService);
        LogObserver logObserver = new LogObserver("log.txt");
        notification.addObserver(emailObserver);
        notification.addObserver(logObserver);
        notification.setPaidFine("The following fine has just been registered as paid: " + fine);
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

}
