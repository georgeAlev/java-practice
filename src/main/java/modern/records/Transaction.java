package modern.records;

import modern.records.enums.TransactionType;

import java.time.LocalDate;
import java.util.UUID;

public record Transaction(UUID id, TransactionType type, LocalDate date, Money value) {

}
