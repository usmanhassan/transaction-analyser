package au.com.mebank.constants;

import java.time.format.DateTimeFormatter;

public class TransactionConstants {

	private TransactionConstants() {
	    throw new IllegalStateException("Constant class");
	  }

	  private static final String DATE_FORMATTER= "dd/MM/yyyy HH:mm:ss";
	  public static final DateTimeFormatter TRANSACTION_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(DATE_FORMATTER);
	  public static final DateTimeFormatter TRANSACTION_DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
	  public static final String CSV_SEPRATOR = ",";

}
