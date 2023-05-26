package fines_payment_manager.Command;

import org.springframework.stereotype.Service;

@Service
public class CommandExecutor {

    public void execute(Command command) {
        command.execute();
    }
}
