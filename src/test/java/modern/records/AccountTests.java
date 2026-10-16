package modern.records;

import modern.records.enums.AccountStatus;
import modern.records.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTests {

    private Account account;
    private Money money;

    @BeforeEach
    public void setUp() {
        money = new Money(new BigDecimal(100), "EUR" );
        account = activeAccountWith(money.amount());
    }

    // one recipe for building a test account, shared by setUp and the parameterized tests
    private static Account activeAccountWith(BigDecimal balance) {
        var accountHolder = new AccountHolder("Tom Simpson", "simpsontom@gmail.com");
        return new Account(UUID.randomUUID(), accountHolder, new Money(balance, "EUR"), AccountStatus.ACTIVE);
    }

    private static Account closedAccountWith(BigDecimal balance) {
        var active = activeAccountWith(balance);
        return new Account(active.id(), active.holder(), active.balance(), AccountStatus.CLOSED);
    }

    @ParameterizedTest
    @CsvSource({
            "100    , 356.25, 456.25",
            "0      ,  50   ,  50"   ,
            "1000.10, 150   , 1150.10",
    })
    public void depositIsValid(BigDecimal balance, BigDecimal amount, BigDecimal expected) {
        // each row builds its own account, so the starting balance is visible in the row
        var original = activeAccountWith(balance);

        var deposited = original.deposit(new Money(amount, "EUR"));

        // the new account holds the increased balance, same id and holder
        assertEquals(
                new Account(original.id(), original.holder(), new Money(expected, "EUR"), AccountStatus.ACTIVE),
                deposited);


        // the original is unchanged (Account is immutable)
        assertEquals(new Money(balance, "EUR"), original.balance());
    }

    @ParameterizedTest
    @CsvSource({
            "100    , 40    , 60"     ,
            "100    , 100   , 0"      ,
            "1000.10, 0.10  , 1000.00",
    })
    public void withdrawIsValid(BigDecimal balance, BigDecimal amount, BigDecimal expected) throws InsufficientFundsException {
        var original = activeAccountWith(balance);

        var withdrawn = original.withdraw(new Money(amount, "EUR"));

        // the new account holds the decreased balance, same id, holder and status
        assertEquals(
                new Account(original.id(), original.holder(), new Money(expected, "EUR"), AccountStatus.ACTIVE),
                withdrawn);

        // the original is unchanged (Account is immutable)
        assertEquals(new Money(balance, "EUR"), original.balance());
    }

    @Test
    public void withdrawBeyondBalanceIsNotValid() {
        var tooMuch = new Money(new BigDecimal("100.01"), "EUR");

        var ex = assertThrows(InsufficientFundsException.class, () -> this.account.withdraw(tooMuch));
        assertEquals("Withdrawal value exceeds account's balance", ex.getMessage());

        // the failed withdrawal leaves the account untouched
        assertEquals(this.money, this.account.balance());
    }

    @Test
    public void depositOnClosedAccountIsNotValid() {
        var closed = closedAccountWith(this.money.amount());

        assertThrows(IllegalArgumentException.class, () -> closed.deposit(new Money(new BigDecimal(10), "EUR")));
    }

    @Test
    public void withdrawOnClosedAccountIsNotValid() {
        var closed = closedAccountWith(this.money.amount());

        assertThrows(IllegalArgumentException.class, () -> closed.withdraw(new Money(new BigDecimal(10), "EUR")));
    }


}
