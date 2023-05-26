package fines_payment_manager.Notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

@Component
public class EmailObserver implements Observer {

    private String logFineDetails;
    @Autowired
    private final EmailService emailService;

    @Autowired
    public EmailObserver(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void update(Observable o, Object fine) {
        this.setLogFineDetails((String) fine);
        emailService.sendSimpleMessage("email", "New paid fine", logFineDetails);
    }

    public void setLogFineDetails(String logFineDetails) {
        this.logFineDetails = logFineDetails;
    }

}
