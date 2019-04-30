/**
 *
 */
package au.com.mebank.dto;

/**
 * This class used as data transfer object for the request
 *
 * @author Usman Hassan
 *
 */
public class RequestDTO extends BaseDTO {

	private String accountID;
	private String fromDate;
	private String toDate;
	private String filePath;

	public RequestDTO(String accountID, String fromDate, String toDate, String filePath) {
		super();
		this.accountID = accountID;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.filePath = filePath;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
