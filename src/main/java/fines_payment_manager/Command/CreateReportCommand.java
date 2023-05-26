package fines_payment_manager.Command;

import fines_payment_manager.Model.Driver;
import fines_payment_manager.Model.Fine;
import fines_payment_manager.Model.PoliceStation;
import fines_payment_manager.Service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class CreateReportCommand implements Command {

    private FineService fineService;

    @Autowired
    public CreateReportCommand(FineService fineService) {
        this.fineService = fineService;
    }

    @Override
    public void execute() {
        List<Fine> fines = fineService.getAllFines();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("reports.txt", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Fine fine : fines) {
                Driver driver = fine.getDriver();
                PoliceStation policeStation = fine.getPolice_station();
                if (fine.getStatus().equals("Paid")) {
                    printWriter.println("-> Driver " + driver.getName() + " with ID " + driver.getId() +
                            " has paid the " + fine.getType() + " fine with ID " + fine.getId() +
                            " in the amount of " + fine.getAmount() + " issued by police station " +
                            policeStation.getName() + ".\n" + "The driver's address is " +
                            driver.getAddress() + " , their driver's license ID is " +
                            driver.getDriving_license() + " and their identity card number is " +
                            driver.getIdentity_card() + ".\n" + "The police station's address is " +
                            policeStation.getAddress() + " and its phone number is " +
                            policeStation.getPhone_number() + "."
                            );
                }
                else {
                    printWriter.println("-> Driver " + driver.getName() + " with ID " + driver.getId() +
                            " has not yet paid the " + fine.getType() + " fine with ID " + fine.getId() +
                            " in the amount of " + fine.getAmount() + " issued by police station " +
                            policeStation.getName() + ".\n" + "The driver's address is " +
                            driver.getAddress() + " , their driver's license ID is " +
                            driver.getDriving_license() + " and their identity card number is " +
                            driver.getIdentity_card() + ".\n" + "The police station's address is " +
                            policeStation.getAddress() + " and its phone number is " +
                            policeStation.getPhone_number() + "."
                    );
                }
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
