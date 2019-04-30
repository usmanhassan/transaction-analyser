/**
 *
 */
package au.com.mebank.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

import au.com.mebank.constants.TransactionConstants;
import au.com.mebank.dto.RequestDTO;

/**
 * Helper class for validation
 * 
 * @author Usman Hassan
 *
 */
public class ValidationUtil {

	private static final Logger log = Logger.getLogger(ValidationUtil.class.getName());

	private ValidationUtil() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Validate the input line
	 * 
	 * @param line
	 */
	public static void vaidateLine(String[] line) {

		if (line.length != 3 || StringUtil.isBlank(line[0]) || StringUtil.isBlank(line[1])
				|| StringUtil.isBlank(line[2])) {

			throw new IllegalArgumentException("The line is not in correct format");
		}
		// DateUtil.convertStringToDateWithTime(line[1],FraudConstants.TRANSACTION_DATE_TIME_FORMAT);
		Double.parseDouble(line[2]); // just to validate the amount
	}

	/**
	 * Validate the data input
	 * 
	 * @param request
	 * @return true if in correct formation
	 * @throws IllegalArgumentException if format is not correct
	 */
	public static boolean validateInput(RequestDTO request) {
		boolean valid = true;
		validateDate(request.getFromDate(), TransactionConstants.TRANSACTION_DATE_TIME_FORMAT);
		validateDate(request.getToDate(), TransactionConstants.TRANSACTION_DATE_TIME_FORMAT);
		validateAccountId(request.getAccountID());
		validatePath(request.getFilePath());
		
		return valid;
	}

	/**
	 * Validate the threshold amount
	 * 
	 * @param amount
	 * @return true if in correct format
	 * @throws IllegalArgumentException if format is not correct
	 */
	public static boolean validateAccountId(String accountId) {
		boolean isValid = true;
		if (StringUtil.isBlank(accountId)) {
			log.severe("Invalid account Id");
			throw new IllegalArgumentException("Invalid Account Id please provide value");
		}

		return isValid;
	}

	/**
	 * Validate the date format
	 * 
	 * @param date
	 * @param format
	 * @return true if validated otherwise false
	 * @throws IllegalArgumentException if date format is wrong
	 */
	public static boolean validateDate(String date, DateTimeFormatter format) {
		boolean isValid = false;
		try {
			LocalDate.parse(date, format);
			isValid = true;
		} catch (final DateTimeParseException e) {
			System.out.println(e);
			log.severe("Wrong Date Format argument : expected YYYY-mm-DD");
			throw new IllegalArgumentException("Wrong Date Format : expected YYYY-mm-DD", e);
		}

		return isValid;
	}

	/**
	 * Check if the file exist in the given path
	 * 
	 * @param filePath
	 * @return false if file not exist
	 * @throws IllegalArgumentException if path is not valid
	 */
	public static boolean validatePath(String filePath) {

		final Path path = Paths.get(filePath);
		boolean isValid = path.toFile().exists();

		if (!isValid) {
			log.severe("Wrong file location argument. File does not exist in the given path");
			throw new IllegalArgumentException("File does not exist in the given path");
		}

		return isValid;
	}

	
	public static void main(String args[]) {
		
		//validateDate("20/10/2018 12:00:00", TransactionConstants.TRANSACTION_DATE_TIME_FORMAT)
	}
}
