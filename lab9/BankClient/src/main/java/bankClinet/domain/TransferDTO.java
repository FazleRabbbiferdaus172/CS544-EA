package bankClinet.domain;

public record TransferDTO(long fromAccountNumber, long toAccountNumber, double amount, String description) {
}
