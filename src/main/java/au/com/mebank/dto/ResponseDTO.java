/**
 *
 */
package au.com.mebank.dto;

import java.math.BigDecimal;

/**
 * This class used as data transfer object for the response
 *
 * @author Usman Hassan
 *
 */
public class ResponseDTO extends BaseDTO {

	private BigDecimal accountBalance;
	private Integer numberOfTransactions;
	private String errorCode;
	private String errorResponse;
	
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(String errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Integer getNumberOfTransactions() {
		return numberOfTransactions;
	}
	public void setNumberOfTransactions(Integer numberOfTransactions) {
		this.numberOfTransactions = numberOfTransactions;
	}
	
	

}
