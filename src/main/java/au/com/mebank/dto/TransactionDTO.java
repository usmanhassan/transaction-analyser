/**
 *
 */
package au.com.mebank.dto;

import java.time.LocalDateTime;

/**
 * Used as placeholder for the csv line data
 *
 * @author Usman Hassan
 *
 */
public class TransactionDTO extends BaseDTO {

	public enum TransactionType {
		PAYMENT, REVERSAL
	}

	private String transactionId;
	private String fromAccountId;
	private String toAccountId;
	private LocalDateTime createdAt;
	private String amount;
	private TransactionType transactionType;
	private String relatedTransaction;

	@Override
	public String toString() {
		return "TransactionDTO [transactionId=" + transactionId + ", fromAccountId=" + fromAccountId + ", toAccountId="
				+ toAccountId + ", createdAt=" + createdAt + ", amount=" + amount + ", transactionType="
				+ transactionType + ", relatedTransaction=" + relatedTransaction + "]";
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getRelatedTransaction() {
		return relatedTransaction;
	}

	public void setRelatedTransaction(String relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
	}

}
