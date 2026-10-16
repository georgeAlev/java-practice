package modern.records;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount, String currency) {

    public Money {
        if (amount == null)  {
            throw new IllegalArgumentException("Money value cannot be a null");
        }

        if (amount.compareTo(new BigDecimal(0)) < 0 ) {
            throw new IllegalArgumentException("Money value cannot be a negative value");
        }

        amount = amount.setScale(2, RoundingMode.UNNECESSARY);

        if (currency == null )  {
            throw new IllegalArgumentException("Money currency cannot be a null");
        }
    }

    public Money plus(Money other) {
         if (!this.currency.equals(other.currency())) {
            throw new IllegalArgumentException("Cannot add money amounts with different currencies");
         }

        return new Money(this.amount.add(other.amount()), this.currency());
    }

    public Money minus(Money other) {
        if (!this.currency.equals(other.currency())) {
            throw new IllegalArgumentException("Cannot add money amounts with different currencies");
        }

        return new Money(this.amount.subtract(other.amount()), this.currency());
    }

}
