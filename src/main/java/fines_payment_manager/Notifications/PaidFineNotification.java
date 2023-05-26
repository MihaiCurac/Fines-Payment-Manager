package fines_payment_manager.Notifications;

import java.util.Observable;

public class PaidFineNotification extends Observable {

    private String paidFineDetails;

    public void setPaidFine(String paidFineDetails) {
        this.paidFineDetails = paidFineDetails;
        setChanged();
        notifyObservers(paidFineDetails);
    }
}
