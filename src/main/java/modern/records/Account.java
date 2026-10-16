package modern.records;

import modern.records.enums.AccountStatus;
import modern.records.exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.UUID;

 public record Account(UUID id, AccountHolder holder, Money balance, AccountStatus status) {

    public Account {
        if (balance.amount().compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("Account balance cannot be a negative value");
        }
    }

    public Account deposit(Money depositAmount) {
        if (this.accountIsClosed()) {
            throw new IllegalArgumentException("Account Status is %s. Deposit is not allowed.".formatted(AccountStatus.CLOSED));
        }

        var newBalance = this.balance().plus(depositAmount);
        return new Account(this.id(), this.holder(), newBalance, this.status());
    }

    public Account withdraw(Money withdrawAmount) throws InsufficientFundsException {

        if (this.accountIsClosed()) {
            throw new IllegalArgumentException("Account Status is %s. Withdrawal is not allowed.".formatted(AccountStatus.CLOSED));
        }

        if(!this.withdrawalAmountInRange(withdrawAmount)) {
            throw new InsufficientFundsException("Withdrawal value exceeds account's balance");
        }
        var newBalance = this.balance().minus(withdrawAmount);
        return new Account(this.id(), this.holder(), newBalance, this.status());
    }

    private boolean accountIsClosed() {
        return !this.status().equals(AccountStatus.ACTIVE);
    }

    private boolean withdrawalAmountInRange(Money withdrawAmount) {
        var eq = this.balance().amount().compareTo(withdrawAmount.amount()) == 0;
        var gr = this.balance().amount().compareTo(withdrawAmount.amount()) > 0;

        return (gr || eq);
    }

}
