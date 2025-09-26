package banking.transactionlogger;

import banking.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void log(User user, String message) {
        if (user == null) return;

        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] %s", timestamp, message);
        user.addTransactionLog(logMessage);
        System.out.println("Transaction Log: " + logMessage);
    }
}
