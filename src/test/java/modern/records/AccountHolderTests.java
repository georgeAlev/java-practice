package modern.records;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class AccountHolderTests {

    @Test
    public void emptyNameIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new AccountHolder("", "test@example.com"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"smithgmail.com", "acdc2hotmail.com", "otroswindowslive.com"})
    public void emailIsInvalid(String email) {
        assertThrows(IllegalArgumentException.class, () -> new AccountHolder("TestName", email));
    }

}
