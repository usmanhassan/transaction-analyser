package au.com.mebank.parser;
import au.com.mebank.dto.RequestDTO;
import au.com.mebank.dto.ResponseDTO;


/**
 * @author Usman
 *
 */
public interface FileParser {

	/**
	 * It do the following
	 * 1) Reads the CSV file
	 * 2) Filter the records based on the criteria
	 * 3) sum the transaction amount and number of transactions
	 * 4) return the response with amount and number of transactions
	 * @param requestDTO contains the criteria
	 * @return then amount and number of transactions
	 */
	public ResponseDTO parse(RequestDTO requestDTO);
}
