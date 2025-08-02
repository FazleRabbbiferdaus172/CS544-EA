package bank.service.dto;

public record TransferDTO(long fromAccountNumber, long toAccountNumber, double amount, String description) {
}
