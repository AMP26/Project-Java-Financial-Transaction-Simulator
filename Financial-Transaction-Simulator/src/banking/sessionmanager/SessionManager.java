package banking.sessionmanager;

import banking.service.BankService;
import banking.model.User;

public class SessionManager {

    private final BankService bankService;
    private Long currentAccountNumber = null;

    public SessionManager(BankService bankService) {
        this.bankService = bankService;
    }

    public boolean login(long accountNumber) {
        User user = bankService.getUserByAccountNumber(accountNumber);
        if (user != null) {
            currentAccountNumber = accountNumber;
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        return currentAccountNumber != null;
    }

    public Long getCurrentAccountNumber() {
        return currentAccountNumber;
    }

    public void logout() {
        currentAccountNumber = null;
    }
}

