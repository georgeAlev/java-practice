package modern.records;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    public void nullAmountIsIllegal() {
        assertThrows(IllegalArgumentException.class, () -> new Money(null, "EUR"));
    }

    @Test
    public void negativeAmountIsIllegal() {
        assertThrows(IllegalArgumentException.class, () -> new Money(new BigDecimal(-100), "EUR"));
    }

    @Test
    public void amountCanBeZero() {
        assertDoesNotThrow(() -> new Money(new BigDecimal(0), "EUR"));
    }

    @Test
    public void scaledAmountsAreEqual() {
        var money = new Money(new BigDecimal(1), "EUR");
        var amount = new BigDecimal("1.00");
        assertEquals(amount, money.amount());
    }

    @Test
    public void nullCurrencyIsIllegal() {
        assertThrows(IllegalArgumentException.class, () -> new Money(new BigDecimal(100), null));
    }

    @Test
    public void plusIsIllegalForDifferentCurrencies() {
        var hundred = new Money(new BigDecimal(100), "EUR");
        var twenty = new Money(new BigDecimal(20), "USD");
        assertThrows(IllegalArgumentException.class, () -> hundred.plus(twenty));
    }

    @Test
    public void plusIsValid() {
        var hundred = new Money(new BigDecimal(100), "EUR");
        var twenty = new Money(new BigDecimal(20), "EUR");

        var newAmount = hundred.plus(twenty);

        // plus returns a new Money holding the sum
        assertEquals(new Money(new BigDecimal(120), "EUR"), newAmount);
        // the original is unchanged (Money is immutable); scale 2 because Money normalizes amounts to 2 decimals
        assertEquals(new BigDecimal(100).setScale(2, RoundingMode.UNNECESSARY), hundred.amount());
    }

    @Test
    public void minusIsValid() {
        var hundred = new Money(new BigDecimal(100), "EUR");
        var twenty = new Money(new BigDecimal(20), "EUR");

        var newAmount = hundred.minus(twenty);

        // minus returns a new Money holding the difference
        assertEquals(new Money(new BigDecimal(80), "EUR"), newAmount);
        // the original is unchanged (Money is immutable); scale 2 because Money normalizes amounts to 2 decimals
        assertEquals(new BigDecimal(100).setScale(2, RoundingMode.UNNECESSARY), hundred.amount());
    }
}
