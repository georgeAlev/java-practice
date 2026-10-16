package modern.records;

import modern.records.enums.AccountStatus;
import modern.records.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StatementPrinterTests {

    private Account account;
    private Transaction deposit;
    private Transaction withdrawal;
    private StatementPrinter printer;

    @BeforeEach
    void setUp() {
        // transactions
        deposit = new Transaction(UUID.fromString("3f2b8c1e-0000-4000-8000-000000000001"), TransactionType.DEPOSIT,
                LocalDate.of(2026,9,25), new Money(new BigDecimal(300), "EUR"));
        withdrawal = new Transaction(UUID.fromString("3f2b8c1e-0000-4000-8000-000000000002"), TransactionType.WITHDRAWAL,
                LocalDate.of(2026,9,26), new Money(new BigDecimal(100), "EUR"));

        // account
        account = activeAccountWith(new BigDecimal(200));

        printer = new StatementPrinter();
    }

    static Account activeAccountWith(BigDecimal balance) {
        var accountHolder = new AccountHolder("Eric Cantona", "ericantona@gmail.com");
        return new Account(UUID.fromString("a1b2c3d4-0000-4000-8000-000000000100"), accountHolder,
                new Money(balance, "EUR"), AccountStatus.ACTIVE);
    }

    @Test
    public void statementShowsHeaderAndOneLinePerTransactionInOrder() {
        var transactions = List.of(deposit, withdrawal);

        var expected = """
                Statement for Eric Cantona
                Account: a1b2c3d4-0000-4000-8000-000000000100
                2026-09-25  DEPOSIT       300.00 EUR
                2026-09-26  WITHDRAWAL    100.00 EUR
                """;

        assertEquals(expected, printer.print(account, transactions));
    }


}
