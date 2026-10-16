package modern.records;

public record AccountHolder(String name, String email) {

    public AccountHolder {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Account holder name cannot be empty");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Account holder email must contain @");
        }

    }

}
