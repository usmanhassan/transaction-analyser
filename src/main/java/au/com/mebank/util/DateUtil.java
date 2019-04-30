/**
 *
 */
package au.com.mebank.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper class for date relates operations
 * 
 * @author Usman Hassan
 *
 */
public class DateUtil {
  
  private DateUtil() {
    throw new IllegalStateException("Utility class");
  }


	/**
	 * convert the string date into localdatetime
	 * 
	 * @param date
	 * @param dateFormat
	 * @return LocalDatetime
	 * @throws DateTimeParseException
	 */
  public static LocalDateTime convertStringToDateWithTime(String date, DateTimeFormatter dateFormat) {

    return LocalDateTime.parse(date.trim(), dateFormat);
  }

	/**
	 * compare the dates
	 * 
	 * @param transactionDate
	 * @param matchDay
	 * @return true if date matches otherwise false
	 */
	public static boolean isDateEqual(LocalDateTime transactionDate, LocalDate matchDay) {

		final int compare = transactionDate.toLocalDate().compareTo(matchDay);
		
		return compare==0?true:false;
	}

	/**
	 * To convert the string date in to LocalDate with out time
	 * 
	 * @param date
	 * @param format
	 * @return LocalDate with no time
	 */
	public static LocalDate convertToDateWithOutTime(String date, DateTimeFormatter format) {

		return LocalDate.parse(date, format);
	}

}
