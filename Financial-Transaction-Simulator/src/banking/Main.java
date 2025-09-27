package banking;

import banking.cli.Menu;
import banking.controller.Controller;
import banking.service.BankService;
import banking.sessionmanager.SessionManager;
import banking.transactionlogger.TransactionLogger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        SessionManager sessionManager = new SessionManager(bankService);
        TransactionLogger transactionLogger = new TransactionLogger();
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(bankService, sessionManager, transactionLogger, scanner);
        Menu menu = new Menu(sessionManager, controller);

        menu.start();
    }
}