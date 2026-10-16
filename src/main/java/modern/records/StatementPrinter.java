package modern.records;

import java.util.List;

public class StatementPrinter {

    public String print(Account account, List<Transaction> transactionList) {

        StringBuilder statement = new StringBuilder("""
                Statement for %s
                Account: %s
                """.formatted(account.holder().name(), account.id()));

        for (var transaction: transactionList) {
            var transactionInfo = """
                    %-10s  %-12s %7s %3s
                    """.formatted(transaction.date(), transaction.type(), transaction.value().amount(),
                    transaction.value().currency());
            statement.append(transactionInfo);
        }

        return statement.toString();
    }

}
